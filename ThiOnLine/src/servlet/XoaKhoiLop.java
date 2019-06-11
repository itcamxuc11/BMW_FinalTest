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
 * Servlet implementation class XoaKhoiLop
 */
@WebServlet("/XoaKhoiLop")
public class XoaKhoiLop extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public XoaKhoiLop() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
		
		String status = "Loi roi";
		try
		{
			Connection conn = DBConnection.getMyConnection();
			String TenTK = request.getParameter("TenTK");
			String MaLop = request.getParameter("MaLop");
			LOPHOC_DAO.XoaKhoiLop(conn, TenTK, MaLop);
			status = "done";
			response.getWriter().write(status);
		}
		catch(Exception e)
		{		
			status = e.getLocalizedMessage();
			response.getWriter().write(status);
		}
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
		
		String status = "";
		try
		{
			Connection conn = DBConnection.getMyConnection();
			String tool = request.getParameter("tool");
			String tenTK = request.getParameter("tenTK");
			String maLop = request.getParameter("maLop");
			if(tool.equals("them"))
				LOPHOC_DAO.ThemVaoLop(conn, maLop, tenTK);
			else LOPHOC_DAO.XoaKhoiLop(conn, tenTK, maLop);
			status = "ok";
			response.getWriter().write(status);
		}
		catch(Exception e)
		{		
			status = e.getLocalizedMessage();
			response.getWriter().write(status);
		}
	}

}
