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

import com.google.gson.Gson;

import beans.BG_DeThi;
import beans.LopHoc;
import beans.TaiKhoan;
import connection.DBConnection;

import utils.DETHI_PLUS_DAO;
import utils.LOPHOC_DAO;
import utils.MyUtils;

/**
 * Servlet implementation class GiaoDeThi
 */
@WebServlet("/GiaoDeThi")
public class GiaoDeThi extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GiaoDeThi() {
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
		} else if (loginedUser.getQuyen() != 3) {
			response.setStatus(404);
			return;
		}

		List<BG_DeThi> list = null;
		List<LopHoc> list2 = null;
		String err = null;
		String maDe = request.getParameter("maDe");
		try {
			Connection conn = DBConnection.getMyConnection();
			list = DETHI_PLUS_DAO.LayDSLopDaGanDeThi(conn, maDe);
			list2 = LOPHOC_DAO.LopHocChuaThi(conn, maDe);
		} catch (Exception e) {
			err = e.getMessage();
			System.out.print(err);
		}
		request.setAttribute("lopDaGan", list);
		request.setAttribute("lopChuaGan", list2);
		request.setAttribute("error", err);
		request.setAttribute("maDe", maDe);
		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/Admin/GanDeThi.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
			}

		String jsonString = request.getParameter("querry");
		BG_DeThi bg = new BG_DeThi();
		String status = "";
		try {
			Gson gson = new Gson();
			bg = gson.fromJson(jsonString, BG_DeThi.class);
			Connection conn = DBConnection.getMyConnection();
			String them = request.getParameter("add");
			if (them == null) {
				DETHI_PLUS_DAO.GiaoDeThi(conn, bg);
			} else {
				DETHI_PLUS_DAO.GiaoLaiDeThi(conn, bg);
			}
			status = "ok";
		} catch (Exception e) {
			status = e.getMessage();
			System.out.println(status);
		}
		response.getWriter().write(status);
	}

}
