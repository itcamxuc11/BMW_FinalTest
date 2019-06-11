package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.LuotThi;
import beans.ND_DeThi;
import beans.TaiKhoan;
import connection.DBConnection;
import utils.DBUtils;
import utils.DETHI_PLUS_DAO;
import utils.MyUtils;

/**
 * Servlet implementation class Example
 */
@WebServlet("/Example")
public class Example extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Example() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		TaiKhoan daDangNhap = MyUtils.getTaiKhoanDangNhap(session);
		if(daDangNhap==null)
		{
			response.sendRedirect("Home");
		}
		else
		{
			String tenTK = daDangNhap.getTenTK();
			String maDe = request.getParameter("maDe");
			String maLop = request.getParameter("maLop");
	    	List<ND_DeThi> list = null;
	    	LuotThi luotThi = null;
	    	String err=null;
			if(maDe==null||maLop==null||maDe==""||maLop=="") response.setStatus(404);
			else
			{
				try {
						Connection conn = DBConnection.getMyConnection();
						luotThi= DBUtils.KiemTraDuocPhepThi(conn, tenTK, maDe, maLop);
						if(luotThi==null) response.setStatus(404);
						else
						{
							// Luu ket qá»§a ua vá»›i sá»‘ Ä‘iÃªm = 0, trÃ¡nh trÆ°á»�ng há»£p bá»� bÃ i thi
							System.out.println(luotThi.getBatDau());
							//DBUtils.LuuKetQuaThiTamThoi(conn, tenTK, maLop, maDe, luotThi.getBatDau());
							MyUtils.setLuotThi(session, luotThi);
							list = DETHI_PLUS_DAO.LayDeThi(conn, maDe);
							request.setAttribute("luotThi",luotThi);
							request.setAttribute("DSCauHoi", list);
							RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/User/BaiThi.jsp");
							dispatcher.forward(request,response);
						}
					}
					catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						err = e.getMessage();
						System.out.println(err);
						//response.setStatus(404);
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
}
