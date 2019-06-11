package utils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.CauHoi;
import connection.DBConnection;

public class CAUHOI_DAO {
	private Connection conn;
	private int np = 10;

	public CAUHOI_DAO() throws ClassNotFoundException, SQLException {
		conn = DBConnection.getMyConnection();
	}

	public void themCauHoi(String NoiDung, String DapAnA, String DapAnB, String DapAnC, String DapAnD, String DapAnDung,
			int CapDo, String MonHoc) throws SQLException {
		String sql = "insert into CauHoi values (?,?,?,?,?,?,?,?)";
		CallableStatement cstm = conn.prepareCall(sql);
		cstm.setString(1, NoiDung);
		cstm.setString(2, DapAnA);
		cstm.setString(3, DapAnB);
		cstm.setString(4, DapAnC);
		cstm.setString(5, DapAnD);
		cstm.setString(6, DapAnDung);
		cstm.setInt(7, CapDo);
		cstm.setString(8, MonHoc);

		cstm.executeUpdate();
	}

	public CauHoi layThongTinCauHoi(String id) throws SQLException {
		int maCauHoi = Integer.parseInt(id);
		String sql = "Select * from CauHoi where MaCauHoi = ?";
		PreparedStatement cstm = conn.prepareStatement(sql);
		cstm.setInt(1, maCauHoi);
		ResultSet rs = cstm.executeQuery();
		if (rs.next()) {
			CauHoi ch = new CauHoi();
			ch.setMaCauHoi(rs.getInt("MaCauHoi"));
			ch.setNoiDung(rs.getString("NoiDung"));
			ch.setDapAnA(rs.getString("DapAnA"));
			ch.setDapAnB(rs.getString("DapAnB"));
			ch.setDapAnC(rs.getString("DapAnC"));
			ch.setDapAnD(rs.getString("DapAnD"));
			ch.setDapAnDung(rs.getString("DapAnDung"));
			ch.setCapDo(rs.getInt("CapDo"));
			ch.setMonHoc(rs.getString("MonHoc"));
			return ch;
		}
		return null;
	}

	public void xoaCauHoi(int MaCauHoi) throws SQLException {
		String sql = "delete from CauHoi where MaCauHoi = ?";
		CallableStatement cstm = conn.prepareCall(sql);
		cstm.setInt(1, MaCauHoi);
		cstm.executeUpdate();
	}

	public void suaCauHoi(String MaCauHoi, String NoiDung, String DapAnA, String DapAnB, String DapAnC, String DapAnD,
			String DapAnDung, int CapDo, String MonHoc) throws SQLException {
		String sql = "update CauHoi set NoiDung = ?, DapAnA = ?, DapAnB = ?, DapAnC = ?, DapAnD =?,\n"
				+ "DapAnDung = ?,CapDo = ?, MonHoc = ? where MaCauHoi = ?";
		CallableStatement cstm = conn.prepareCall(sql);
		cstm.setString(1, NoiDung);
		cstm.setString(2, DapAnA);
		cstm.setString(3, DapAnB);
		cstm.setString(4, DapAnC);
		cstm.setString(5, DapAnD);
		cstm.setString(6, DapAnDung);
		cstm.setInt(7, CapDo);
		cstm.setString(8, MonHoc);
		int maCauHoi = Integer.parseInt(MaCauHoi);
		cstm.setInt(9, maCauHoi);
		cstm.executeUpdate();
	}

	public List<CauHoi> xemDSCauHoi(int page) throws SQLException {
		List<CauHoi> ds = new ArrayList<CauHoi>();
		Statement st = conn.createStatement();
		String sql = "select * from CauHoi";
		ResultSet rs = st.executeQuery(sql);
		int max = page * np;
		int min = (page-1)*np;
		int i=0;
		while (rs.next() && i < max) {
			if(i>=min)
			{
				CauHoi ch = new CauHoi();
				ch.setMaCauHoi(rs.getInt("MaCauHoi"));
				ch.setNoiDung(rs.getString("NoiDung"));
				ch.setDapAnA(rs.getString("DapAnA"));
				ch.setDapAnB(rs.getString("DapAnB"));
				ch.setDapAnC(rs.getString("DapAnC"));
				ch.setDapAnD(rs.getString("DapAnD"));
				ch.setDapAnDung(rs.getString("DapAnDung"));
				ch.setCapDo(rs.getInt("CapDo"));
				ch.setMonHoc(rs.getString("MonHoc"));
				ds.add(ch);
			}
			i++;
		}
		return ds;
	}
	
	public int tinhTongSoTrang() throws SQLException{
		String sql = "select count(*) as SoLuong from CauHoi";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		int s = 0;
		if(rs.next())
		{
			s= rs.getInt("SoLuong");
		}
		int soTrang = s/np;
		if(s%np!=0) soTrang++;
		return soTrang;
	}
}
