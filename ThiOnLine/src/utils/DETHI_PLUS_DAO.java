package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import beans.BG_DeThi;
import beans.DeThi;
import beans.ND_DeThi;

public class DETHI_PLUS_DAO {
	private static int np = 10;

	public static List<BG_DeThi> LayDSDeThi(Connection conn, String tenTK) throws SQLException {
		List<BG_DeThi> list = new ArrayList<>();
		String sql = "execute pr_LayDsDeThi'" + tenTK + "'";
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			String maDeThi = rs.getString("MaDeThi");
			String tieuDe = rs.getString("TieuDe");
			String maLop = rs.getString("MaLop");
			String tenLop = rs.getString("TenLop");
			int thoiLuong = rs.getInt("ThoiLuong");
			Timestamp bd = rs.getTimestamp("TGBatDau");
			Timestamp kt = rs.getTimestamp("TGKetThuc");
			int soLanLamBai = rs.getInt("SoLanLamBai");
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
			String tGBatDau = dateFormat.format(bd);
			String tGKetThuc = dateFormat.format(kt);
			BG_DeThi bg = new BG_DeThi();
			bg.setMaDeThi(maDeThi);
			bg.setTieuDe(tieuDe);
			bg.setMaLop(maLop);
			bg.setTenLop(tenLop);
			bg.setThoiLuong(thoiLuong);
			bg.setTGBatDau(tGBatDau);
			bg.setSoLanLamBai(soLanLamBai);
			bg.setTGKetThuc(tGKetThuc);
			list.add(bg);
		}
		return list;
	}

	public static List<BG_DeThi> LayDSLopDaGanDeThi(Connection conn, String maDe) throws SQLException {
		List<BG_DeThi> list = new ArrayList<>();
		String sql = "select LopHoc.MaLop, TenLop, ThoiLuong, TieuDe, TGBatDau, TGKetThuc,SoLanLamBai\r\n"
				+ "from BG_DeThi, LopHoc \r\n" + "where MaDeThi = ? and LopHoc.MaLop = BG_DeThi.MaLop";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, maDe);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			String tieuDe = rs.getString("TieuDe");
			String maLop = rs.getString("MaLop");
			String tenLop = rs.getString("TenLop");
			int thoiLuong = rs.getInt("ThoiLuong");
			String tGBatDau = rs.getString("TGBatDau");
			String tGKetThuc = rs.getString("TGketThuc");
			int soLanLamBai = rs.getInt("soLanLamBai");
			BG_DeThi bg = new BG_DeThi();
			bg.setTieuDe(tieuDe);
			bg.setMaLop(maLop);
			bg.setTenLop(tenLop);
			bg.setThoiLuong(thoiLuong);
			bg.setTGBatDau(tGBatDau);
			bg.setTGKetThuc(tGKetThuc);
			bg.setSoLanLamBai(soLanLamBai);
			list.add(bg);
		}
		return list;
	}

	public static void GiaoDeThi(Connection conn, BG_DeThi bg) throws SQLException, ParseException {
		String sql = "INSERT INTO BG_DeThi VALUES (?,?,?,?,?,?,?)";
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		java.util.Date tmp1 = dateFormat.parse(bg.getTGBatDau());
		java.util.Date tmp2 = dateFormat.parse(bg.getTGKetThuc());
		Timestamp bd = new Timestamp(tmp1.getTime());
		Timestamp kt = new Timestamp(tmp2.getTime());
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, bg.getMaDeThi());
		pstm.setString(2, bg.getMaLop());
		pstm.setTimestamp(3, bd);
		pstm.setTimestamp(4, kt);
		pstm.setInt(5, bg.getThoiLuong());
		pstm.setString(6, bg.getTieuDe());
		pstm.setInt(7, bg.getSoLanLamBai());
		pstm.executeUpdate();
	}

	// Sua cac thong tin nhu thoi luong, so lan lam bai, tieu de....//
	public static void GiaoLaiDeThi(Connection conn, BG_DeThi bg) throws SQLException, ParseException {
		String sql = "Update BG_DeThi\r\n" + "set TieuDe=?, ThoiLuong = ?,SoLanLamBai=?, TGBatDau = ?, TGKetThuc=?\r\n"
				+ "where MaLop = ? and MaDeThi =?";
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		java.util.Date tmp1 = dateFormat.parse(bg.getTGBatDau());
		java.util.Date tmp2 = dateFormat.parse(bg.getTGKetThuc());
		Timestamp bd = new Timestamp(tmp1.getTime());
		Timestamp kt = new Timestamp(tmp2.getTime());
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, bg.getTieuDe());
		pstm.setInt(2, bg.getThoiLuong());
		pstm.setInt(3, bg.getSoLanLamBai());
		pstm.setTimestamp(4, bd);
		pstm.setTimestamp(5, kt);
		pstm.setString(6, bg.getMaLop());
		pstm.setString(7, bg.getMaDeThi());
		pstm.executeUpdate();
	}

	public static void LuuDeThi(Connection conn, DeThi deThi) throws SQLException {
		String sql = "insert into DeThi values (?,?,0,0,0)";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, deThi.getMaDeThi());
		pstm.setString(2, deThi.getMaMonHoc());
		pstm.executeUpdate();
	}

	public static void DongDeThi(Connection conn, String deThi, String lop) throws SQLException {
		String sql = "delete from BG_DeThi where MaDeThi = ? and MaLop = ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, deThi);
		pstm.setString(2, lop);
		pstm.executeUpdate();
	}

	public static List<ND_DeThi> LayDeThi(Connection conn, String MaDe) throws SQLException {
		String sql = "execute pr_LayDeThi '" + MaDe + "'";
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		List<ND_DeThi> list = new ArrayList<ND_DeThi>();
		while (rs.next()) {
			int maCauHoi = rs.getInt("MaCauHoi");
			String noiDung = rs.getString("NoiDung");
			String dapAnA = rs.getString("DapAnA");
			String dapAnB = rs.getString("DapAnB");
			String dapAnC = rs.getString("DapAnC");
			String dapAnD = rs.getString("DapAnD");
			String dapAnDung = rs.getString("DapAnDung");
			ND_DeThi ch = new ND_DeThi();
			ch.setMaCauHoi(maCauHoi);
			ch.setNoiDung(noiDung);
			ch.setDapAnA(dapAnA);
			ch.setDapAnB(dapAnB);
			ch.setDapAnC(dapAnC);
			ch.setDapAnD(dapAnD);
			ch.setDapAnDung(dapAnDung);
			list.add(ch);
		}
		return list;
	}

	private static List<ND_DeThi> TaoDeThi(Connection conn, int capDo, int soLuong, String maMonHoc)
			throws SQLException {
		List<ND_DeThi> rsList = new ArrayList<>();
		if (soLuong > 0) {
			Random rd = new Random();
			String sql = "select count(*) as SoLuong from CauHoi where CapDo = ? and MonHoc = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, capDo);
			pstm.setString(2, maMonHoc);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				int soLuongSanCo = rs.getInt("SoLuong");
				if (soLuongSanCo < soLuong) {
					if (capDo == 1)
						throw new ArithmeticException(
								"Số câu hỏi dễ bạn chọn vượt quá mức cung câp, số lượng tối đa hiện tại: "
										+ soLuongSanCo);
					else if (capDo == 2)
						throw new ArithmeticException(
								"Số câu hỏi trung bình bạn chọn vượt quá mức cung câp, số lượng tối đa hiện tại: "
										+ soLuongSanCo);
					else if (capDo == 3)
						throw new ArithmeticException(
								"Số câu hỏi khó bạn chọn vượt quá mức cung câp, số lượng tối đa hiện tại: "
										+ soLuongSanCo);
				} else {
					sql = "select MaCauHoi,NoiDung,DapAnA,DapAnB,DapAnC,DapAnD,DapAnDung from CauHoi where CapDo = ? and CauHoi.MonHoc = ?";
					pstm = conn.prepareStatement(sql);
					pstm.setInt(1, capDo);
					pstm.setString(2, maMonHoc);
					rs = pstm.executeQuery();
					List<ND_DeThi> tmpList = new ArrayList<>();
					while (rs.next()) {
						int maCauHoi = rs.getInt("MaCauHoi");
						String noiDung = rs.getString("NoiDung");
						String dapAnA = rs.getString("DapAnA");
						String dapAnB = rs.getString("DapAnB");
						String dapAnC = rs.getString("DapAnC");
						String dapAnD = rs.getString("DapAnD");
						String dapAnDug = rs.getString("DapAnDung");
						ND_DeThi ch = new ND_DeThi();
						ch.setMaCauHoi(maCauHoi);
						ch.setNoiDung(noiDung);
						ch.setDapAnA(dapAnA);
						ch.setDapAnB(dapAnB);
						ch.setDapAnC(dapAnC);
						ch.setDapAnD(dapAnD);
						ch.setDapAnDung(dapAnDug);
						tmpList.add(ch);
					}
					int i;
					while (rsList.size() < soLuong) {
						i = rd.nextInt(tmpList.size());
						rsList.add(tmpList.get(i));
						tmpList.remove(i);
					}
				}
			}
		}
		return rsList;
	}

	public static List<ND_DeThi> TaoDeThi(Connection conn, String maMonHoc, int soCauDe, int soCauTb, int soCauKho)
			throws SQLException {
		List<ND_DeThi> rsList = new ArrayList<>();
		rsList.addAll(TaoDeThi(conn, 1, soCauDe, maMonHoc));
		rsList.addAll(TaoDeThi(conn, 2, soCauTb, maMonHoc));
		rsList.addAll(TaoDeThi(conn, 3, soCauKho, maMonHoc));
		return rsList;
	}

	public static List<DeThi> LayDSDeThi(Connection conn, int page) throws SQLException {
		String sql = "select * from DeThi";
		List<DeThi> list = new ArrayList<>();
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		int max = page * np;
		int min = (page - 1) * np;
		int i = 0;
		while (rs.next() && i < max) {
			if (i >= min) {
				String maDeThi = rs.getString("MaDeThi");
				String maMonHoc = rs.getString("maMonHoc");
				int soCauDe = rs.getInt("SoCauDe");
				int soCauKho = rs.getInt("SoCauKho");
				int soCauTrungBinh = rs.getInt("SoCauTrungBinh");
				DeThi dt = new DeThi();
				dt.setMaDeThi(maDeThi);
				dt.setMaMonHoc(maMonHoc);
				dt.setSoCauDe(soCauDe);
				dt.setSoCauKho(soCauKho);
				dt.setSoCauTrungBinh(soCauTrungBinh);
				list.add(dt);
			}
			i++;
		}
		return list;
	}

	public static int tinhTongSoTrang(Connection conn) throws SQLException {
		String sql = "select count(*) as SoLuong from DeThi";
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

	public static DeThi LayThongTinDeThi(Connection conn, String maDe) throws SQLException {
		String sql = "select * from DeThi where MaDeThi = ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, maDe);
		ResultSet rs = pstm.executeQuery();
		if (rs.next()) {
			DeThi dt = new DeThi();
			dt.setMaMonHoc(rs.getString("MaMonHoc"));
			dt.setSoCauDe(rs.getInt("SoCauDe"));
			dt.setSoCauTrungBinh(rs.getInt("SoCauTrungBinh"));
			dt.setSoCauKho(rs.getInt("SoCauKho"));
			return dt;
		}
		return null;
	}

	public static String LayDapAn(Connection conn, String MaDe) throws SQLException {
		String sql = "execute pr_LayDapAn '" + MaDe + "'";
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		String dapAn = "";
		while (rs.next()) {
			dapAn = rs.getString("DapAn");
		}
		return dapAn;
	}

	public static void XoaNDDeThi(Connection conn, String maDe) throws SQLException {
		String sql = "exec pr_XoaHetNoiDungDeThi'" + maDe + "'";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.executeUpdate();
	}

	public static void LuuNDDeThi(Connection conn, String maDe, String MaCauHoi) throws SQLException {
		int maCauHoi = Integer.parseInt(MaCauHoi);
		String sql = "INSERT INTO ND_DeThi VALUES (?,?)";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, maCauHoi);
		pstm.setString(2, maDe);
		pstm.executeUpdate();
	}

	public static void XoaDeThi(Connection conn, String maDe) throws SQLException {
		String sql = "DeLete from DeThi where MaDeThi = (?)";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, maDe);
		pstm.executeUpdate();
	}

}
