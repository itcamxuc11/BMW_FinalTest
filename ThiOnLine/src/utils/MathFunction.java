package utils;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class MathFunction {

	public static float ChamDiem(String baiThi, String dapAn) {
		baiThi = baiThi.toUpperCase();
		dapAn = dapAn.toUpperCase();
		float diem = 0;
		float dem = 0;
		float dodaidapan = dapAn.length();
		float dodaibaithi = baiThi.length();
		if (dodaidapan == dodaibaithi) {
			for (int i = 0; i < dodaibaithi; i++)
				if (baiThi.charAt(i) == dapAn.charAt(i))
					dem++;
		} else
			return -1;
		diem = (dem / dodaibaithi) * 10;
		return diem;
	}

	public static String ChuyenThanhChuKhongDau(String s) {

		String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(temp).replaceAll("");
	}

	public static String HashMD5(String md5) {
		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
			}
			return sb.toString();
		} catch (java.security.NoSuchAlgorithmException e) {
		}
		return null;
	}

}
