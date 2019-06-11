package utils;
import java.sql.Connection;
import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.DeThi;
import beans.LuotThi;
import beans.TaiKhoan;

@SuppressWarnings("unused")
public class MyUtils {
	 public static final String ATT_NAME_CONNECTION = "ATTRIBUTE_FOR_CONNECTION";
	 
	    private static final String ATT_NAME_USER_NAME = "ATTRIBUTE_FOR_STORE_USER_NAME_IN_COOKIE";
	 
	 
	    // Lưu trữ thông tin người dùng đã login vào Session.
	    public static void setTaiKhoanDangNhap(HttpSession session, TaiKhoan loginedUser) {
	        session.setAttribute("loginedUser", loginedUser);
	    }
	    
	    // Lấy thông tin người dùng lưu trữ trong Session.
	    public static TaiKhoan getTaiKhoanDangNhap(HttpSession session) {
	        TaiKhoan loginedUser = (TaiKhoan) session.getAttribute("loginedUser");
	        return loginedUser;
	    }
	    
	    //Xoa thong tin nguoi dung khoi session
	    public static void delTaiKhoanDangNhap(HttpSession session) {
	    	session.removeAttribute("loginedUser");
	    }
	 //Luu thonng tin luot thi vao Session
	    public static void setLuotThi(HttpSession session, LuotThi luotThi)
	    {
	    	session.setAttribute("luotThi", luotThi);
	    }
	    
	    //Lay luot thi ktu session, để chấm điểm
	    public static LuotThi getLuotThi(HttpSession session)
	    {
	    	LuotThi luotThi = (LuotThi) session.getAttribute("luotThi");
	    	return luotThi;
	    }
	    //Xoa luot thi khoi session
	    public static void delLuotThi(HttpSession session) {
	    	session.removeAttribute("uotThi");
	    }
	    //Luu mot de thi vao session, dùng trong việc sửa và thêm đề thi
	    // 
	    public static void setDeThi(HttpSession session, String deThi)
	    {
	    	session.setAttribute("deThi", deThi);
	    }
	    // Lay đề thi từ session
	    public static String getDeThi(HttpSession session)
	    {
	    	String deThi = (String)session.getAttribute("deThi");
	    	return deThi;
	    }
	    
	    //Xoa de thi khoi session
	    public static void delDeThi(HttpSession session)
	    {
	    	session.removeAttribute("deThi");
	    }
	    
	    public static String getMonHoc(HttpSession session)
	    {
	    	String deThi = (String)session.getAttribute("monHoc");
	    	return deThi;
	    }
	    
	    public static void setMonHoc(HttpSession session, String monHoc)
	    {
	    	session.setAttribute("monHoc", monHoc);
	    }
	    
	    // Lưu thông tin người dùng vào Cookie.
	    public static void storeUserCookie(HttpServletResponse response, TaiKhoan user) {
	        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, user.getTenTK());
	        // 1 ngày (Đã đổi ra giây)
	        cookieUserName.setMaxAge(24 * 60 * 60);
	        response.addCookie(cookieUserName);
	    }
	 
	    
	    public static String getUserNameInCookie(HttpServletRequest request) {
	        Cookie[] cookies = request.getCookies();
	        if (cookies != null) {
	            for (Cookie cookie : cookies) {
	                if (ATT_NAME_USER_NAME.equals(cookie.getName())) {
	                    return cookie.getValue();
	                }
	            }
	        }
	        return null;
	    }
	 
	    // Xóa Cookie của người dùng
	    public static void deleteUserCookie(HttpServletResponse response) {
	        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, null);
	        // 0 giây. (Cookie này sẽ hết hiệu lực ngay lập tức)
	        cookieUserName.setMaxAge(0);
	        response.addCookie(cookieUserName);
	    }
}
