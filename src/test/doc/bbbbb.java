package test.doc;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class bbbbb {
	/**
	 * 
	 * 加密
	 * 
	 * @param sSrc   原字符
	 * @param sKey   16位字符的key
	 * @return
	 * @throws Exception 
	 * @return String
	 * @exception 异常描述
	 * @see
	 */
	public static String Encrypt128(String sSrc, String sKey) throws Exception {
		if (sKey == null)
			return null;
		if (sKey.length() != 16)
			throw new IllegalArgumentException("Key length must be 16.");
		return doEncrypt(sSrc, sKey);
	}
	
	private static String doEncrypt(String sSrc, String sKey) throws Exception {
		byte[] raw = sKey.getBytes("ASCII");
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher = Cipher.getInstance("AES");//创建密码器
		cipher.init(1, skeySpec);// 初始化
		byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));//加密
		
		//字节转换成十六进制的字符串
		return byte2hex(encrypted).toLowerCase();
	}
	
		/**
	 * 
	 * 字节转换成十六进制的字符串
	 * 
	 * @param b
	 * @return 
	 * @return String
	 * @exception 异常描述
	 * @see
	 */
	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = Integer.toHexString(b[n] & 0xFF);
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else {
				hs = hs + stmp;
			}
		}
		return hs.toUpperCase();
	}
}

