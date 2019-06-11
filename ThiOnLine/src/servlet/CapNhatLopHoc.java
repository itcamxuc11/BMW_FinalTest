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

import utils.LOPHOC_DAO;
import utils.MyUtils;

/**
 * Servlet implementation class CapNhatLopHoc
 */
@WebServlet("/CapNhatLopHoc")
public class CapNhatLopHoc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CapNhatLopHoc() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        
		String status = "";
		try {
			String maLop = request.getParameter("maLop");
			String tenLop =request.getParameter("tenLop");
			String ngayKetThuc = request.getParameter("ngayKetThuc");
			Connection conn = DBConnection.getMyConnection();
			if(maLop==null)
			{
				LOPHOC_DAO.ThemLopHoc(conn, tenLop, ngayKetThuc);
			}
			else
			{
				LOPHOC_DAO.SuaLopHoc(conn, maLop, tenLop, ngayKetThuc);
			}
			status = "ok";
		}
		catch(Exception e)
		{
			status = e.getMessage();
		}
		response.getWriter().write(status);
	}
}
