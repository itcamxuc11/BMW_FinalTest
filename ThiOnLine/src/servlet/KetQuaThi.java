package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.LuotThi;
import beans.TaiKhoan;
import connection.DBConnection;
import utils.DBUtils;
import utils.DETHI_PLUS_DAO;
import utils.MathFunction;
import utils.MyUtils;


import org.apache.pdfbox.pdmodel.PDDocument; 
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 * Servlet implementation class KetQuaThi
 */
@WebServlet("/KetQuaThi")
public class KetQuaThi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KetQuaThi() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	String err = "";
    	String path = getServletContext().getRealPath("/ketqua.pdf"); //Không hoạt động tốt nếu nhiều người cùng truy cập
    	float Diem = 0;
    	long thoiGianLamBai=0;
    	HttpSession session = request.getSession();
    	LuotThi luotThi = MyUtils.getLuotThi(session);
    	if(luotThi==null) return;
    	TaiKhoan tk = MyUtils.getTaiKhoanDangNhap(session);
		try {
			Connection conn = DBConnection.getMyConnection();
			Timestamp batDau = luotThi.getBatDau();
			Timestamp bayGio = new Timestamp(System.currentTimeMillis());
			thoiGianLamBai = (bayGio.getTime() - batDau.getTime())/1000;
			//Phát hiên gian lân
			if(thoiGianLamBai>luotThi.getThoiLuong()*60+5) Diem=-1;
			else
			{
				String baiThi= request.getParameter("baiThi");
				String dapAn= DETHI_PLUS_DAO.LayDapAn(conn, luotThi.getMaDe());
				Diem = MathFunction.ChamDiem(baiThi, dapAn);
				System.out.println(dapAn);
				err = null;
			}
			DBUtils.LuuKetQuaThi(conn, tk.getTenTK(), luotThi.getMaLop(), luotThi.getMaDe(), luotThi.getBatDau(), Diem);
			PDDocument document = new PDDocument();
			PDPage page = new PDPage();
			document.addPage(page);
			PDPageContentStream contentStream = new PDPageContentStream(document, page);
			contentStream.beginText(); 
			contentStream.newLineAtOffset(25, 700);
			contentStream.setFont(PDType1Font.HELVETICA ,25 );
			contentStream.setLeading(35.5f);
			contentStream.showText("Your score:                 "+Diem);  
			contentStream.newLine();
			contentStream.showText("Time to completion:    "+thoiGianLamBai + " (seccond)");  
			contentStream.newLine();
			contentStream.showText("Date:                           "+batDau);
			contentStream.newLine();
			contentStream.showText("Name:                         "
									+MathFunction.ChuyenThanhChuKhongDau(tk.getTenNguoiDung()));
			contentStream.endText();
			contentStream.close();			
			document.save(path);
			document.close();
		}
			catch (Exception e) {
			e.printStackTrace();
			err = e.getMessage();		
			System.out.println(err);
		}	
		request.setAttribute("savelink", path);
		request.setAttribute("error", err);
		request.setAttribute("phut", thoiGianLamBai/60);
		request.setAttribute("giay", thoiGianLamBai%60);
		request.setAttribute("Diem", Diem);
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/User/KetQuaThi.jsp");
		dispatcher.forward(request, response);
	}

}
