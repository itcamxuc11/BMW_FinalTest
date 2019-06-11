package beans;

import java.sql.Date;

public class LopHoc {
	private String MaLop;
	private String TenLop;
	private Date NgayKetThuc;
	private int SoHocSinh;
	
	
	public String getMaLop() {
		return MaLop;
	}
	public void setMaLop(String maLop) {
		MaLop = maLop;
	}
	public String getTenLop() {
		return TenLop;
	}
	public void setTenLop(String tenLop) {
		TenLop = tenLop;
	}
	public Date getNgayKetThuc() {
		return NgayKetThuc;
	}
	public void setNgayKetThuc(Date ngayKetThuc) {
		NgayKetThuc = ngayKetThuc;
	}
	public int getSoHocSinh() {
		return SoHocSinh;
	}
	public void setSoHocSinh(int soHocSinh) {
		SoHocSinh = soHocSinh;
	}
}
