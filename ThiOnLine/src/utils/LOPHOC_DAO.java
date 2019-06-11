package utils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import beans.LopHoc;

public class LOPHOC_DAO {
	public static List<LopHoc> LayDSLopHoc(Connection conn, String TenTK) throws SQLException {
		String sql = "execute pr_DSLopHoc'" + TenTK + "'";
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		List<LopHoc> list = new ArrayList<LopHoc>();
		while (rs.next()) {
			String malop = rs.getString("MaLop");
			String tenlop = rs.getString("TenLop");
			LopHoc lh = new LopHoc();
			lh.setMaLop(malop);
			lh.setTenLop(tenlop);
			list.add(lh);
		}
		return list;
	}
	
	public static List<LopHoc> LayDSLopHoc(Connection conn) throws SQLException {
		String sql = "exec pr_LayDSlopHoc";
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		List<LopHoc> list = new ArrayList<LopHoc>();
		while (rs.next()) {
			String malop = rs.getString("MaLop");
			String tenlop = rs.getString("TenLop");
			Date ngayKetThuc = rs.getDate("NgayKetThuc");
			int soHocSinh = rs.getInt("SoLuong");
			LopHoc lh = new LopHoc();
			lh.setMaLop(malop);
			lh.setTenLop(tenlop);
			lh.setNgayKetThuc(ngayKetThuc);
			lh.setSoHocSinh(soHocSinh);
			list.add(lh);
		}
		return list;
	}
	
	public static List<LopHoc> LopHocChuaThi(Connection conn, String maDe) throws SQLException {
		String sql = "pr_LopHocChuaThi'" + maDe + "'";
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		List<LopHoc> list = new ArrayList<LopHoc>();
		while (rs.next()) {
			String malop = rs.getString("MaLop");
			String tenlop = rs.getString("TenLop");
			LopHoc lh = new LopHoc();
			lh.setMaLop(malop);
			lh.setTenLop(tenlop);
			list.add(lh);
		}
		return list;
	}
	
	public static void ThemLopHoc(Connection conn, String tenLop, String ngayKetThuc)
			throws SQLException, ParseException {
		String maLop = "LH" + Long.toString(System.currentTimeMillis());
		String sql = "insert into LopHoc values(?,?,?)";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, maLop);
		pstm.setString(2, tenLop);
		pstm.setString(3, ngayKetThuc);
		pstm.executeUpdate();
	}

	public static void SuaLopHoc(Connection conn, String maLop, String tenLop, String ngayKetThuc) throws SQLException {
		String sql = "update LopHoc set TenLop=?, NgayKetThuc=? where MaLop=?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, tenLop);
		pstm.setString(2, ngayKetThuc);
		pstm.setString(3, maLop);
		pstm.executeUpdate();
	}

	public static void XoaKhoiLop(Connection conn, String TenTK, String MaLop) throws SQLException {
		String sql = "DELETE FROM TV_LopHoc WHERE TV_LopHoc.MaLopHoc = ? and TV_LopHoc.TenTK = ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, MaLop);
		pstm.setString(2, TenTK);
		pstm.executeUpdate();
	}

	public static void ThemVaoLop(Connection conn, String maLop, String tenTK) throws SQLException {
		String sql = "Insert into TV_LopHoc values (?,?)";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, maLop);
		pstm.setString(2, tenTK);
		pstm.executeUpdate();
	}
	
	public static void XoaLopHoc(Connection conn, String maLop) throws SQLException {
		String sql = "DeLete from LopHoc where MaLop = (?)";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, maLop);
		pstm.executeUpdate();
	}
	
}
