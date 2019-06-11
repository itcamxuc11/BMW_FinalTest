package servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import beans.TaiKhoan;
import connection.DBConnection;
import utils.DBUtils;
import utils.MyUtils;

/**
 * Servlet implementation class Home
 */
@WebServlet("/Home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/User/TrangChu.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String tenTK = request.getParameter("tenTK");
		String matKhau = request.getParameter("matKhau");
		String err = null;
		if (tenTK == null || matKhau == null || tenTK.length() == 0 || matKhau.length() == 0) {
            err = "Hãy nhập đủ tài khoản và mật khẩu";
        }
		else {
			try
			{
				Connection conn = DBConnection.getMyConnection();
				TaiKhoan tk = DBUtils.KiemTraDangNhap(conn, tenTK, matKhau);
				if(tk==null) err = "Tài khoản hoặc mật khẩu không đúng";
				else
				{
		            HttpSession session = request.getSession();
		            MyUtils.setTaiKhoanDangNhap(session, tk);
					if(tk.getQuyen()==1)
					{
			            response.sendRedirect(request.getContextPath() + "/TrangCaNhan");
			            return;
					}
					else if(tk.getQuyen()==2)
					{
						 response.sendRedirect(request.getContextPath() + "/QuanLyHocSinh");
						 return;
					}
					else if(tk.getQuyen()==3) 
					{
						 response.sendRedirect(request.getContextPath() + "/QuanLyDeThi");
						 return;
					}
					else if(tk.getQuyen()==4)
					{
						 response.sendRedirect(request.getContextPath() + "/QuanLyCauHoi");
						 return;
					}
				}
				
			}
			catch(Exception e)
			{
				err = e.getMessage();
			}

			request.setAttribute("Error", err);
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/User/TrangChu.jsp");
			dispatcher.forward(request, response);
		}
	}

}
