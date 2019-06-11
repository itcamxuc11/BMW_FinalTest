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

import beans.TaiKhoan;
import beans.ThongTinTK;
import connection.DBConnection;
import utils.DBUtils;
import utils.HOCSINH_DAO;
import utils.MyUtils;

/**
 * Servlet implementation class QuanLyHocSinh
 */
@WebServlet("/QuanLyHocSinh")
public class QuanLyHocSinh extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuanLyHocSinh() {
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
        // Nếu chưa đăng nhập (login).
        if (loginedUser == null) {
            // Redirect (Chuyển hướng) tới trang login.
            response.sendRedirect(request.getContextPath() + "/Home");
            return;
        }
        else if(loginedUser.getQuyen()!=2) 
		{
			response.setStatus(404);
			return;
		}
        else {
				int page=1;
				String p = request.getParameter("page");
				int soTrang = 0;
            	List<ThongTinTK> list = null;
            	String err = "";
            	Connection conn;
    			try {
    				if(p!=null) page = Integer.parseInt(p);
    				conn = DBConnection.getMyConnection();
    				soTrang = HOCSINH_DAO.tinhTongSoTrang(conn);
    				list = HOCSINH_DAO.LayDSHocSinh(conn,page);
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
        
		String json = request.getParameter("hocSinh");
		String status="";
		try {
			String tenTK = request.getParameter("tentk");
			Connection conn = DBConnection.getMyConnection();
			if(tenTK!=null) {
				String tenHS = request.getParameter("tenhs");
				String ngaySinh = request.getParameter("ngaysinh");
				String soDienThoai = request.getParameter("sdt");
				String diaChi = request.getParameter("diachi");
				HOCSINH_DAO.SuaTaiKhoan(conn, tenTK, tenHS, ngaySinh, diaChi, soDienThoai);
			}
			else
			{
				ThongTinTK tk = new ThongTinTK();
				Gson gson = new Gson();
				tk = gson.fromJson(json, ThongTinTK.class);
				DBUtils.ThemHocSinh(conn, tk);
			}
			status="ok";
		}
		catch(Exception e) {
			status = e.getMessage();
		}
		response.getWriter().write(status);
	}
}
