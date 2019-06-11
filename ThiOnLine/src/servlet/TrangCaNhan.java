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

import beans.BG_DeThi;
import beans.LopHoc;
import beans.TaiKhoan;
import beans.ThongTinTK;
import connection.DBConnection;
import utils.DBUtils;
import utils.DETHI_PLUS_DAO;
import utils.LOPHOC_DAO;
import utils.MyUtils;

/**
 * Servlet implementation class TrangCaNhan
 */
@WebServlet("/TrangCaNhan")
public class TrangCaNhan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TrangCaNhan() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		  // Kiểm tra người dùng đã đăng nhập (login) chưa.
		HttpSession session = request.getSession();
        TaiKhoan loginedUser = MyUtils.getTaiKhoanDangNhap(session);
        // Nếu chưa đăng nhập (login).
        if (loginedUser == null) {

            response.sendRedirect(request.getContextPath() + "/Home");
            return;
        }
        ThongTinTK thongTinTK = null;
        List<BG_DeThi> dsDeThi = null;
        List<LopHoc> dsLopHoc = null;
        String err = null;
        try {
        	Connection conn = DBConnection.getMyConnection();
        	thongTinTK = DBUtils.LayThongTin(conn, loginedUser.getTenTK());
        	dsDeThi = DETHI_PLUS_DAO.LayDSDeThi(conn, loginedUser.getTenTK());
        	dsLopHoc = LOPHOC_DAO.LayDSLopHoc(conn, loginedUser.getTenTK());
        	request.setAttribute("dsDeThi", dsDeThi);
        	request.setAttribute("dsLopHoc", dsLopHoc);
        	request.setAttribute("user", thongTinTK);
        }
        catch(Exception e) {
        	err = e.getMessage();
        }
        request.setAttribute("error", err);
        RequestDispatcher dispatcher  = this.getServletContext().getRequestDispatcher("/WEB-INF/views/User/TrangCaNhan.jsp");
        dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
