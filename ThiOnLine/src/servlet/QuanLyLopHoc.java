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

import beans.HocSinh;
import beans.LopHoc;
import beans.TaiKhoan;
import connection.DBConnection;
import utils.HOCSINH_DAO;
import utils.LOPHOC_DAO;
import utils.MyUtils;

/**
 * Servlet implementation class QuanLyLopHoc
 */
@WebServlet("/QuanLyLopHoc")
public class QuanLyLopHoc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuanLyLopHoc() {
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
        else if(loginedUser.getQuyen()!=2)
		{
			response.setStatus(404);
			return;
		}
        
        
		String err = null;
		List<LopHoc>list= null;
		try {
			Connection conn = 	DBConnection.getMyConnection();
			list = LOPHOC_DAO.LayDSLopHoc(conn);
			request.setAttribute("dsLopHoc", list);
		}
		catch (Exception e) {
			err = e.getMessage();
			System.out.println(err);
			// TODO: handle exception
		}
        RequestDispatcher dispatcher  = this.getServletContext().getRequestDispatcher("/WEB-INF/views/Admin/QuanLyLopHoc.jsp");
        dispatcher.forward(request, response);
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
        else if(loginedUser.getQuyen()!=2) 
		{
			response.setStatus(404);
			return;
		}
		
		
		List<HocSinh> list = null;
		String responsejson = null;
		String err = null;
		String tool = request.getParameter("tool");
		String maLop = request.getParameter("maLop");
		try {
			Connection conn = DBConnection.getMyConnection();
			if(tool.equals("them"))
			{
				list = HOCSINH_DAO.LayDSHocSinhChuaVaoLop(conn, maLop);
			}
			else
			{
				list = HOCSINH_DAO.LayDSHocSinhDaVaoLop(conn, maLop);
			}
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			responsejson = new Gson().toJson(list);
			response.getWriter().write(responsejson);
		}
		catch (Exception e) {
			err = e.getMessage();
			System.out.println(err);
		}
	}
}
