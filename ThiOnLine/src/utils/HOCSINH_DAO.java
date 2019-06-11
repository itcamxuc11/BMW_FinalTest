package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.HocSinh;
import beans.ThongTinTK;

public class HOCSINH_DAO {
	private static int np = 10;

	public static List<ThongTinTK> LayDSHocSinh(Connection conn, int page) throws SQLException {
		String sql = "execute pr_LayDanhSachHocSinh";
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		List<ThongTinTK> list = new ArrayList<ThongTinTK>();
		int max = page * np;
		int min = (page - 1) * np;
		int i = 0;
		while (rs.next() && i < max) {
			if (i >= min) {
				String tentk = rs.getString("TenTK");
				String name = rs.getString("TenNguoiDung");
				String ngaysinh = rs.getString("NgaySinh");
				String diachi = rs.getString("DiaChi");
				String sdt = rs.getString("SDT");
				int soluonglop = rs.getInt("SoLuong");
				ThongTinTK tk = new ThongTinTK();
				tk.setTenTK(tentk);
				tk.setTenNguoiDung(name);
				tk.setNgaySinh(ngaysinh);
				tk.setDiaChi(diachi);
				tk.setSDT(sdt);
				tk.setSoLuongLopHoc(soluonglop);
				list.add(tk);
			}
			i++;
		}
		return list;
	}

	public static int tinhTongSoTrang(Connection conn) throws SQLException {
		String sql = "select count(*) as SoLuong from TaiKhoan where Quyen = 1";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		int s = 0;
		if (rs.next()) {
			s = rs.getInt("SoLuong");
		}
		int soTrang = s / np;
		if (s % np != 0)
			soTrang++;
		return soTrang;
	}

	public static List<HocSinh> LayDSHocSinhChuaVaoLop(Connection conn, String maLop) throws SQLException {
		String sql = "exec pr_HocSinhChuaVaoLop'" + maLop + "'";
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		List<HocSinh> list = new ArrayList<>();
		while (rs.next()) {
			String tenTK = rs.getString("TenTK");
			String tenHocSinh = rs.getString("TenNguoiDung");
			HocSinh hs = new HocSinh();
			hs.setTenTK(tenTK);
			hs.setTenHocSinh(tenHocSinh);
			list.add(hs);
		}
		return list;
	}

	public static List<HocSinh> LayDSHocSinhDaVaoLop(Connection conn, String maLop) throws SQLException {
		String sql = "exec pr_HocSinhDaVaoLop'" + maLop + "'";
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		List<HocSinh> list = new ArrayList<>();
		while (rs.next()) {
			String tenTK = rs.getString("TenTK");
			String tenHocSinh = rs.getString("TenNguoiDung");
			HocSinh hs = new HocSinh();
			hs.setTenTK(tenTK);
			hs.setTenHocSinh(tenHocSinh);
			list.add(hs);
		}
		return list;
	}

	public static List<ThongTinTK> TimKiemHocSinh(Connection conn, String key) throws SQLException {
		String sql = "exec pr_TimKiemTaiKhoan'" + key + "'";
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		List<ThongTinTK> list = new ArrayList<ThongTinTK>();
		while(rs.next())
		{
			String tentk = rs.getString("TenTK");
			String name = rs.getString("TenNguoiDung");
			String ngaysinh = rs.getString("NgaySinh");
			String diachi = rs.getString("DiaChi");
			String sdt = rs.getString("SDT");
			int soluonglop = rs.getInt("SoLuong");
			ThongTinTK tk = new ThongTinTK();
			tk.setTenTK(tentk);
			tk.setTenNguoiDung(name);
			tk.setNgaySinh(ngaysinh);
			tk.setDiaChi(diachi);
			tk.setSDT(sdt);
			tk.setSoLuongLopHoc(soluonglop);
			list.add(tk);
		}
		return list;
	}
	
	public static void SuaTaiKhoan(Connection conn,String tenTK, String tenHS,String ngaySinh, String diaChi, String sdt) throws SQLException
	{
		String sql = "Update TaiKhoan \r\n" + 
				"set TenNguoiDung = ?, NgaySinh = ?, DiaChi = ?, SDT = ?\r\n" + 
				"where TenTK = ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, tenHS);
		pstm.setString(2, ngaySinh);
		pstm.setString(3, diaChi);
		pstm.setString(4, sdt);
		pstm.setString(5, tenTK);
		pstm.executeLargeUpdate();
	}
	
	public static void DangKiTaiKhoan(Connection conn, String tenTk, String ten, String matKhau) throws SQLException
	{
		String sql = 
			"Insert into TaiKhoan (TenTK,TenNguoiDung,MatKhau) values ('" + tenTk +
			"','" + ten +"','" + matKhau + "')";
		
		System.out.print(sql);
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.executeLargeUpdate();
	}
}
