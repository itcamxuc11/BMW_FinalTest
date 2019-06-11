package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.CauHoi;
import beans.TaiKhoan;
import utils.CAUHOI_DAO;
import utils.MyUtils;

/**
 * Servlet implementation class SuaCauHoi
 */
@WebServlet("/SuaCauHoi")
public class SuaCauHoi extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SuaCauHoi() {
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
		} else if (loginedUser.getQuyen() != 4)
		{
			response.setStatus(404);
			return;
		}

		CAUHOI_DAO dao = null;
		CauHoi ch = null;
		try {
			String maCauHoi = request.getParameter("id");
			dao = new CAUHOI_DAO();
			ch = dao.layThongTinCauHoi(maCauHoi);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		request.setAttribute("cauHoi", ch);
		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/Admin/SuaCauHoi.jsp");
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
            return;}
        else if(loginedUser.getQuyen()!=4)
		{
			response.setStatus(404);
			return;
		}
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String MaCauHoi = request.getParameter("macauhoi");
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
			ch.suaCauHoi(MaCauHoi, NoiDung, DapAnA, DapAnB, DapAnC, DapAnD, DapAnDung, CapDo, MonHoc);
			status = "ok";

		} catch (Exception e) {
			System.out.print(e.getMessage());
			status = e.getMessage();
		}
		response.getWriter().write(status);
	}

}
