package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.CAUHOI_DAO;

/**
 * Servlet implementation class ThemCauHoi
 */
@WebServlet("/ThemCauHoi")
public class ThemCauHoi extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ThemCauHoi() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/Admin/ThemCauHoi.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String NoiDung = request.getParameter("noidung");
		String DapAnA = request.getParameter("dapana");
		String DapAnB = request.getParameter("dapanb");
		String DapAnC = request.getParameter("dapanc");
		String DapAnD = request.getParameter("dapand");
		String DapAnDung = request.getParameter("dapandung");
		int CapDo = Integer.parseInt(request.getParameter("capdo"));
		String MonHoc = request.getParameter("monhoc");
		String status = null;
		CAUHOI_DAO ch = null;
		try {
			ch = new CAUHOI_DAO();
			ch.themCauHoi(NoiDung, DapAnA, DapAnB, DapAnC, DapAnD, DapAnDung, CapDo, MonHoc);
			status = "ok";

		} catch (Exception e) {
			System.out.print(e.getMessage());
			status = e.getMessage();
		}
		response.getWriter().write(status);
	}

}
