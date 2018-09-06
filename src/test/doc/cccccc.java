package test.doc;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class cccccc {
	/**
		 * 
		 * 解密
		 * 
		 * @param sSrc   原字符
		 * @param sKey   16位字符的key
		 * @return
		 * @throws Exception 
		 * @return String
		 * @exception 异常描述
		 * @see
		 */
		public static String Decrypt128(String sSrc, String sKey) throws Exception {
			if (sKey == null)
				return null;
			if (sKey.length() != 16)
				throw new IllegalArgumentException("Key length must be 16.");
			return doDecrypt(sSrc, sKey);
		}
		
		private static String doDecrypt(String sSrc, String sKey) throws Exception {
			try {
				byte[] raw = sKey.getBytes("ASCII");
				SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
				Cipher cipher = Cipher.getInstance("AES");
				cipher.init(2, skeySpec);
				byte[] encrypted1 = hex2byte(sSrc);
	
				byte[] original = cipher.doFinal(encrypted1);
				return new String(original,"utf-8");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return null;
		}
		
		/**
		 * 
		 * 十六进制的字符串转换成字节
		 * 
		 * @param strhex
		 * @return 
		 * @return byte[]
		 * @exception 异常描述
		 * @see
		 */
		public static byte[] hex2byte(String strhex) {
			if (strhex == null) {
				return null;
			}
			int l = strhex.length();
			if (l % 2 == 1) {
				return null;
			}
			byte[] b = new byte[l / 2];
			for (int i = 0; i != l / 2; i++) {
				b[i] = (byte) Integer.parseInt(strhex.substring(i * 2, i * 2 + 2), 16);
			}
			return b;
		}
}

