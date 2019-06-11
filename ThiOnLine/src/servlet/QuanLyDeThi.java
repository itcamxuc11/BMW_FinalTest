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

import beans.DeThi;
import beans.MonHoc;
import beans.TaiKhoan;
import connection.DBConnection;
import utils.DBUtils;
import utils.DETHI_PLUS_DAO;
import utils.MyUtils;

/**
 * Servlet implementation class QuanLyDeThi
 */
@WebServlet("/QuanLyDeThi")
public class QuanLyDeThi extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QuanLyDeThi() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		TaiKhoan loginedUser = MyUtils.getTaiKhoanDangNhap(session);
		if (loginedUser == null) {
			response.sendRedirect(request.getContextPath() + "/Home");
			return;
		} else if (loginedUser.getQuyen() != 3)
		{
			response.setStatus(404);
			return;
		}
		List<DeThi> rsList = null;
		List<MonHoc> mhList = null;
		String err = null;
		int page = 1;
		String p = request.getParameter("page");
		int soTrang = 0;
		try {
			if (p != null)
				page = Integer.parseInt(p);
			Connection conn = DBConnection.getMyConnection();
			rsList = DETHI_PLUS_DAO.LayDSDeThi(conn, page);
			soTrang = DETHI_PLUS_DAO.tinhTongSoTrang(conn);
			mhList = DBUtils.LayMonHoc(conn);
		} catch (Exception e) {
			err = e.getMessage();
			System.out.println(err);
		}
		request.setAttribute("DSDeThi", rsList);
		request.setAttribute("dsMonHoc", mhList);
		request.setAttribute("error", err);
		request.setAttribute("trang", page);
		request.setAttribute("soTrang", soTrang);
		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/Admin/QuanLyDeThi.jsp");
		dispatcher.forward(request, response);

		response.getWriter().append("Served at: ").append(request.getContextPath());
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
