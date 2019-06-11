package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

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
 * Servlet implementation class DongDeThi
 */
@WebServlet("/DongDeThi")
public class DongDeThi extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DongDeThi() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		TaiKhoan loginedUser = MyUtils.getTaiKhoanDangNhap(session);
		if (loginedUser == null) {
			response.sendRedirect(request.getContextPath() + "/Home");
			return;
		} else if (loginedUser.getQuyen() != 3)
		{
			response.setStatus(404);
			return;
		};
		String maDe = request.getParameter("made");
		try {
			Connection conn = DBConnection.getMyConnection();
			String lop = request.getParameter("lop");
			DETHI_PLUS_DAO.DongDeThi(conn, maDe, lop);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("GiaoDeThi?maDe="+maDe);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
