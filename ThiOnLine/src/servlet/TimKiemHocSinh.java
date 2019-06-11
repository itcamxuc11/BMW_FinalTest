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

import beans.TaiKhoan;
import beans.ThongTinTK;
import connection.DBConnection;
import utils.HOCSINH_DAO;
import utils.MyUtils;

/**
 * Servlet implementation class TimKiemHocSinh
 */
@WebServlet("/TimKiemHocSinh")
public class TimKiemHocSinh extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TimKiemHocSinh() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setStatus(404);
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
        
		String key = request.getParameter("k");
		int page=1;
		String p = request.getParameter("page");
		int soTrang = 1;
    	List<ThongTinTK> list = null;
    	String err = "";
    	Connection conn;
		try {
			if(p!=null) page = Integer.parseInt(p);
			conn = DBConnection.getMyConnection();
			//soTrang = HOCSINH_DAO.tinhTongSoTrang(conn);
			list = HOCSINH_DAO.TimKiemHocSinh(conn, key);
			err = null;
		}
			catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			err = e.getMessage();
			
		}
		request.setAttribute("error", err);
		request.setAttribute("trang", page);
		request.setAttribute("soTrang", soTrang);
		request.setAttribute("DShocsinh", list);
		 RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/Admin/QuanLyHocSinh.jsp");
		 dispatcher.forward(request, response);
	}

}
