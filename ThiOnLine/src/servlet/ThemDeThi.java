package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import beans.*;
import connection.DBConnection;
import utils.DETHI_PLUS_DAO;
import utils.MyUtils;

/**
 * Servlet implementation class ThemDeThi
 */
@WebServlet("/ThemDeThi")
public class ThemDeThi extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ThemDeThi() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		String err = null;
		DeThi dt = null;
		java.util.List<ND_DeThi> list = null;
		int soCauDe = Integer.parseInt(request.getParameter("de"));
		int soCauTrungBinh = Integer.parseInt(request.getParameter("trungBinh"));
		int soCauKho = Integer.parseInt(request.getParameter("kho"));
		String maMonHoc = request.getParameter("monHoc");
		try {
			 dt = new DeThi();
			 dt.setSoCauDe(soCauDe);
			 dt.setSoCauKho(soCauKho);
			 dt.setSoCauTrungBinh(soCauTrungBinh);
			 Connection conn = DBConnection.getMyConnection();
			 if(maMonHoc!=null) {
				 list = DETHI_PLUS_DAO.TaoDeThi(conn, maMonHoc, soCauDe, soCauTrungBinh, soCauKho);
				 MyUtils.setMonHoc(session, maMonHoc);
			 }
			 else {
				maMonHoc =  MyUtils.getMonHoc(session);
				if(maMonHoc==null) return;
				list = DETHI_PLUS_DAO.TaoDeThi(conn, maMonHoc, soCauDe, soCauTrungBinh, soCauKho);
			 }
		}
		catch (Exception e) {
			err = e.getMessage();
		}
		
		request.setAttribute("soLuongCauHoi", dt);
		request.setAttribute("dsCauHoi", list);
		request.setAttribute("error", err);
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/Admin/ThemDeThi.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		
		 String status="";
		 try {
			 String arr =  request.getParameter("arr");
			 String maDeThi = request.getParameter("maDeThi");
			 ArrayList<String> listdata = null;
			 Gson gsonn = new Gson();
			 java.sql.Connection conn = DBConnection.getMyConnection();
			 listdata = gsonn.fromJson(arr, ArrayList.class);
			 DeThi dt = new DeThi();
			 dt.setMaDeThi(maDeThi);
			 dt.setMaMonHoc(MyUtils.getMonHoc(session));
			 DETHI_PLUS_DAO.LuuDeThi(conn, dt);
			 for(int i=0;i<listdata.size();i++)
			 {
				 DETHI_PLUS_DAO.LuuNDDeThi(conn, maDeThi, listdata.get(i));
			 }
			 status = "ok";
		 }
		 catch(Exception e) {
			 status = e.getMessage();
		 }
		 response.getWriter().write(status);
	}
}
