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
import utils.DBUtils;
import utils.MyUtils;

/**
 * Servlet implementation class XoaHocSinh
 */
@WebServlet("/XoaHocSinh")
public class XoaHocSinh extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public XoaHocSinh() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

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
		
		String tenTK = request.getParameter("tenTK");
		String res ="";
		try {
			Connection conn = DBConnection.getMyConnection();
			DBUtils.XoaTaiKhoan(conn, tenTK);
			res="ok";
		}
		catch(Exception e)
		{
			res = e.getLocalizedMessage();
		}
		response.getWriter().write(res);
	}

}
