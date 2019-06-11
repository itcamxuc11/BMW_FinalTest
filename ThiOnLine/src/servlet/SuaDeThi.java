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

import beans.DeThi;
import beans.ND_DeThi;
import beans.TaiKhoan;
import connection.DBConnection;
import utils.DBUtils;
import utils.DETHI_PLUS_DAO;
import utils.MyUtils;

/**
 * Servlet implementation class SuaDeThi
 */
@WebServlet("/SuaDeThi")
public class SuaDeThi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SuaDeThi() {
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
		String err=null;
		DeThi dt = null;
		java.util.List<ND_DeThi> list = null;
		if(maDe==null ||maDe=="")
		{
			 maDe = MyUtils.getDeThi(session);
			 if(maDe==null || maDe=="") return ;
			 try
			 {
				 int soCauDe = Integer.parseInt(request.getParameter("de"));
				 int soCauTB = Integer.parseInt(request.getParameter("trungBinh"));
				 int soCauKho = Integer.parseInt(request.getParameter("kho"));
				 Connection conn = DBConnection.getMyConnection();
				 String	maMonHoc = DBUtils.layMaMonHoc(conn, maDe);
				 dt = new DeThi();
				 dt.setSoCauDe(soCauDe);
				 dt.setSoCauKho(soCauKho);
				 dt.setSoCauTrungBinh(soCauTB);
				 list = DETHI_PLUS_DAO.TaoDeThi(conn, maMonHoc, soCauDe, soCauTB, soCauKho);
			 }
			 catch (Exception e) {
				err = e.getMessage();
			}
		}
		else
		{
			try {
				Connection conn = DBConnection.getMyConnection();
				list = DETHI_PLUS_DAO.LayDeThi(conn, maDe);
				dt = DETHI_PLUS_DAO.LayThongTinDeThi(conn, maDe);
				MyUtils.setDeThi(session, maDe);
			}
			catch( Exception e) {
				err = e.getMessage();
			}
		}
		
			request.setAttribute("dsCauHoi",list );
			request.setAttribute("eror",err );
			request.setAttribute("soCauHoi", dt);
			System.out.println(dt.getSoCauDe());
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/Admin/SuaDeThi.jsp");
			dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			 String maDeThi = MyUtils.getDeThi(session);
			 ArrayList<String> listdata = null;
			 Gson gsonn = new Gson();
			 java.sql.Connection conn = DBConnection.getMyConnection();
			 listdata = gsonn.fromJson(arr, ArrayList.class);
			 DETHI_PLUS_DAO.XoaNDDeThi(conn, maDeThi);
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
