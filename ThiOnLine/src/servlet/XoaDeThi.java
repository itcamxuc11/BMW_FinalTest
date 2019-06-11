package servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.TaiKhoan;
import connection.DBConnection;
import utils.DETHI_PLUS_DAO;
import utils.MyUtils;

/**
 * Servlet implementation class XoaDeThi
 */
@WebServlet("/XoaDeThi")
public class XoaDeThi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public XoaDeThi() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
        TaiKhoan loginedUser = MyUtils.getTaiKhoanDangNhap(session);
        if (loginedUser == null) {
            response.sendRedirect(request.getContextPath() + "/Home");
            return;}
        else if(loginedUser.getQuyen()!=3)
		{
			response.setStatus(404);
			return;
		}
		
		String maDe = request.getParameter("maDe");
		try {
			Connection conn = DBConnection.getMyConnection();
			DETHI_PLUS_DAO.XoaDeThi(conn, maDe);
			response.sendRedirect("QuanLyDeThi");
		}
		catch(Exception e)
		{
			System.out.print(e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
