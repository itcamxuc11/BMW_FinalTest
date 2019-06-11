package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import connection.DBConnection;
import utils.HOCSINH_DAO;

/**
 * Servlet implementation class DangKi
 */
@WebServlet("/DangKi")
public class DangKi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DangKi() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ten = request.getParameter("ten");
		String tenTK = request.getParameter("tenTK");
		String matKhau = request.getParameter("pass");
		String matKhauRe = request.getParameter("repass");
		String result = "";
		if(tenTK.length()<2) result = "Đăng kí không thành công";
		else
		{
			if(matKhau.length()<3) result = "Mật khẩu cần ít nhất 3 kí tự";
			else if(!matKhau.equals(matKhauRe)) result = "Xác nhận mật khẩu không đúng";
			else {
				try {
					Connection conn = DBConnection.getMyConnection();
					HOCSINH_DAO.DangKiTaiKhoan(conn, tenTK, ten, matKhau);
					result = "Đăng kí thành công";
				}
				catch (SQLException e) {
					// TODO: handle exception
					result = "Tài khoản đã tồn tại";
				}
				catch (Exception e) {
					result = e.getMessage();
				}
			}
		}
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().write(result);
	}

}
