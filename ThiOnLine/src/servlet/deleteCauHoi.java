package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.TaiKhoan;
import utils.CAUHOI_DAO;
import utils.MyUtils;



/**
 * Servlet implementation class deleteSale
 */
@WebServlet("/XoaCauHoi")
public class deleteCauHoi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteCauHoi() {
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
        else if(loginedUser.getQuyen()!=4) 
		{
			response.setStatus(404);
			return;
		}
		
		int MaCauHoi= Integer.parseInt(request.getParameter("macauhoi"));
		CAUHOI_DAO ch;
		try {
			ch = new CAUHOI_DAO();
			ch.xoaCauHoi(MaCauHoi);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("QuanLyCauHoi");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
