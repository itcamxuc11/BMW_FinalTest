package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import beans.LuotThi;
import beans.MonHoc;
import beans.TaiKhoan;
import beans.ThongTinTK;

public class DBUtils {

	public static TaiKhoan KiemTraDangNhap(Connection conn, String tenTK, String matKhau) throws SQLException {
		String sql = "select TenTK, TenNguoiDung, Quyen from TaiKhoan where TenTK = ? and MatKhau = ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, tenTK);
		pstm.setString(2, matKhau);
		ResultSet rs = pstm.executeQuery();
		if (rs.next()) {
			TaiKhoan tk = new TaiKhoan();
			tk.setTenTK(tenTK);
			String tenNguoiDung = rs.getString("TenNguoiDung");
			int quyen = rs.getInt("Quyen");
			tk.setTenNguoiDung(tenNguoiDung);
			tk.setQuyen(quyen);
			return tk;
		}
		return null;
	}


	public static ThongTinTK LayThongTin(Connection conn, String TenTK) throws SQLException {
		String sql = "select TenNguoiDung,NgaySinh,DiaChi,SDT from TaiKhoan where TenTK = ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, TenTK);
		ResultSet rs = pstm.executeQuery();
		ThongTinTK tk = new ThongTinTK();
		if (rs.next()) {
			String name = rs.getString("TenNguoiDung");
			String ngaysinh = rs.getString("NgaySinh");
			String diachi = rs.getString("DiaChi");
			String sdt = rs.getString("SDT");
			tk.setTenTK(TenTK);
			tk.setTenNguoiDung(name);
			tk.setNgaySinh(ngaysinh);
			tk.setDiaChi(diachi);
			tk.setSDT(sdt);
		}
		return tk;
	}


	

	public static String layMaMonHoc(Connection conn, String maDe) throws SQLException {
		String result = null;
		String sql = "select MaMonHoc from DeThi where MaDeThi = ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, maDe);
		ResultSet rs = pstm.executeQuery();
		if (rs.next()) {
			result = rs.getString("MaMonHoc");
		}
		return result;
	}

	public static LuotThi KiemTraDuocPhepThi(Connection conn, String tenTK, String maDe, String maLop)
			throws SQLException {
		String sql = "pr_KiemTraDuocPhepThi '" + tenTK + "','" + maDe + "','" + maLop + "'";
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		if (rs.next()) {
			LuotThi lt = new LuotThi();
			lt.setMaDe(maDe);
			lt.setMaLop(maLop);
			String tieuDe = rs.getString("TieuDe");
			int thoiLuong = rs.getInt("ThoiLuong");
			Timestamp batDau = new Timestamp(System.currentTimeMillis());
			lt.setTieuDe(tieuDe);
			lt.setThoiLuong(thoiLuong);
			lt.setBatDau(batDau);
			return lt;
		}
		return null;
	}

	public static List<MonHoc> LayMonHoc(Connection conn) throws SQLException {
		String sql = "select * from MonHoc";
		List<MonHoc> list = new ArrayList<>();
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			String maMonHoc = rs.getString("MaMon");
			String tenMonHoc = rs.getString("TenMon");
			MonHoc mh = new MonHoc();
			mh.setMaMonHoc(maMonHoc);
			mh.setTenMonHoc(tenMonHoc);
			list.add(mh);
		}
		return list;
	}

	public static void XoaTaiKhoan(Connection conn, String tenTK) throws SQLException {
		String sql = "DeLete from TaiKhoan where tenTK = (?)";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, tenTK);
		pstm.executeUpdate();
	}

	public static void ThemHocSinh(Connection conn, ThongTinTK tk) throws SQLException {
		String sql = "INSERT INTO TaiKhoan VALUES (?,?,?,?,?,?,1)";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, tk.getTenTK());
		pstm.setString(2, tk.getMatKhau());
		pstm.setString(3, tk.getTenNguoiDung());
		pstm.setString(4, tk.getNgaySinh());
		pstm.setString(5, tk.getDiaChi());
		pstm.setString(6, tk.getSDT());
		pstm.executeUpdate();
	}
	
	public static void LuuKetQuaThiTamThoi (Connection conn, String tenTk, String lop, String deThi, Timestamp ngayLam ) throws SQLException
	{
		String sql = "insert into KetQuaThi values(?,?,?,?,?)";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, tenTk);
		pstm.setString(2, lop);
		pstm.setString(3, deThi);
		pstm.setTimestamp(4, ngayLam);
		pstm.setFloat(5, 0);
		pstm.executeUpdate();
	}
	
	public static void LuuKetQuaThi(Connection conn, String tenTk, String lop, String deThi, Timestamp ngayLam,float diem) throws SQLException
	{
		String sql = "update KetQuaThi set Diem = ?\r\n" + 
				"where TenTK = ? and MaLop = ? and MaDeThi = ? and NgayLamBai = ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setFloat(1, diem);
		pstm.setString(2, tenTk);
		pstm.setString(3, lop);
		pstm.setString(4, deThi);
		pstm.setTimestamp(5, ngayLam);
		pstm.setFloat(5, 0);
		pstm.executeUpdate();
	}
}
