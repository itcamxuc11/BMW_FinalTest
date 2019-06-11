package beans;

import java.sql.Timestamp;

public class LuotThi {
	private String MaDe;
	private String TieuDe;
	private String MaLop;
	private int ThoiLuong;
	private Timestamp BatDau;
	public String getMaDe() {
		return MaDe;
	}
	public void setMaDe(String maDe) {
		MaDe = maDe;
	}
	public String getTieuDe() {
		return TieuDe;
	}
	public void setTieuDe(String tieuDe) {
		TieuDe = tieuDe;
	}
	public String getMaLop() {
		return MaLop;
	}
	public void setMaLop(String maLop) {
		MaLop = maLop;
	}
	public int getThoiLuong() {
		return ThoiLuong;
	}
	public void setThoiLuong(int thoiLuong) {
		ThoiLuong = thoiLuong;
	}
	public Timestamp getBatDau() {
		return BatDau;
	}
	public void setBatDau(Timestamp batDau) {
		BatDau = batDau;
	}
}
