package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import beans.TaiKhoan;
import utils.CAUHOI_DAO;
import utils.MyUtils;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;

/**
 * Servlet implementation class ThemFileCauHoi
 */
@WebServlet("/ThemFileCauHoi")
@MultipartConfig
public class ThemFileCauHoi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThemFileCauHoi() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
        TaiKhoan loginedUser = MyUtils.getTaiKhoanDangNhap(session);
        if (loginedUser == null) {
            response.sendRedirect(request.getContextPath() + "/Home");
            return;}
        else if(loginedUser.getQuyen()!=4)
		{
			response.setStatus(404);
			return;
		}
		
		String status ="";
		try {
			Part filePart = request.getPart("file");
			 InputStream fileContent = filePart.getInputStream();
			 XSSFWorkbook workbook = new XSSFWorkbook(fileContent);
			 XSSFSheet sheet = workbook.getSheetAt(0);
			 Iterator<Row> rowIterator = sheet.iterator();
			 DataFormatter formatter = new DataFormatter();
			 CAUHOI_DAO dao = new CAUHOI_DAO();
			 while (rowIterator.hasNext()) {
		           Row row = rowIterator.next();
		           String NoiDung = formatter.formatCellValue(row.getCell(0));
		           String DapAnA = formatter.formatCellValue(row.getCell(1));
		           String DapAnB = formatter.formatCellValue(row.getCell(2));
		           String DapAnC = formatter.formatCellValue(row.getCell(3));
		           String DapAnD = formatter.formatCellValue(row.getCell(4));	
		           String DapAnDung = formatter.formatCellValue(row.getCell(5));
		           String cap = formatter.formatCellValue(row.getCell(6));
		           int CapDo = Integer.parseInt(cap);
		           String MonHoc = formatter.formatCellValue(row.getCell(7));
		           dao.themCauHoi(NoiDung, DapAnA, DapAnB, DapAnC, DapAnD, DapAnDung, CapDo, MonHoc);
		       }
			 workbook.close();
			 status = "ok";
		}
		 catch (Exception e) {
			System.out.println(e.getMessage());
			status = e.getMessage();
		}
		response.getWriter().write(status);
	}
}
