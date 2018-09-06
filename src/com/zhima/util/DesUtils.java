
package com.zhima.util;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

/** *//** 
 * 字符串工具集合 
 */ 
public class DesUtils { 
	
	private Key key;
	
    /**
    *根据参数生成KEY
    *@param strKey
    */
	public DesUtils() {
		try{ 
			KeyGenerator _generator = KeyGenerator.getInstance("DES");
			_generator.init(new SecureRandom("".getBytes()));
			key = _generator.generateKey();
			_generator=null;
		}catch(Exception e){ 
			e.printStackTrace();
		}
	}
 
    /**
    *加密String明文输入,String密文输出 
    *@param strMing
    *@return
    */ 
	public String getEncString(String strMing) {
		String strMi = "";
		try {   
			return byte2hex(getEncCode(strMing.getBytes("utf-8")));
		} catch(Exception e){ 
			e.printStackTrace(); 
		} 
		return strMi; 
	} 
	
    /** 
    *解密 String密文输入,String明文输出
    *@param strMi
    *@return
    */
    public String getDesString(String strMi) {
    	String strMing = "";
		try {
			return new String(getDesCode(hex2byte(strMi.getBytes())),"utf-8");
		} catch(Exception e) {
			e.printStackTrace();
		}
		return strMing;
	}
    
    /** 
    *加密byte[]明文输入,byte[]密文输出 
    *@param byteS 
    *@return 
    */ 
    private byte[] getEncCode(byte[] byteS) {
		byte[] byteFina = null;
		Cipher cipher;
		try {
			cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE,key);
			byteFina = cipher.doFinal(byteS);
		}
		catch(Exception e) {
			e.printStackTrace();
		}finally {
			cipher = null;
		}
		return byteFina; 
	} 
    
    /** 
    *解密byte[]密文输入,byte[]明文输出 
    *@param byteD 
    *@return 
    */ 
    private byte[] getDesCode(byte[] byteD) { 
        Cipher cipher; 
        byte[] byteFina=null;
        try{ 
            cipher = Cipher.getInstance("DES"); 
            cipher.init(Cipher.DECRYPT_MODE,key); 
            byteFina = cipher.doFinal(byteD); 
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            cipher=null;
        }
        return byteFina;
    }
    
	/** 
	*二行制转字符串 
	*@param b 
	*@return 
	*/ 
    public static String byte2hex(byte[] b) {
    	//转成16进制字符串
    	String hs = ""; 
    	String stmp = ""; 
    	for (int n = 0; n < b.length; n++) { 
    		//整数转成十六进制表示
    		stmp = (java.lang.Integer.toHexString(b[n] & 0XFF)); 
    		if (stmp.length() == 1) 
    			hs = hs + "0" + stmp; 
    		else 
    			hs = hs + stmp; 
    	} 
    	return hs.toUpperCase();   //转成大写
    } 
   
    public static byte[] hex2byte(byte[] b) { 
    	if((b.length%2)!=0) 
    		throw new IllegalArgumentException("长度不是偶数"); 
    	byte[] b2 = new byte[b.length/2]; 
    	for (int n = 0; n < b.length; n+=2) { 
    		String item = new String(b,n,2); 
    		// 两位一组表示一个字节,把这样表示的16进制字符串，还原成一个进制字节
    		b2[n/2] = (byte)Integer.parseInt(item,16); 
    	} 
    	return b2; 
    } 

    public static void main(String[] args){
    	DesUtils des=new DesUtils();//实例化一个对像 
        String strEnc = des.getEncString("\\*/~!@#$%^&*<>[]{}//鲁承毅");//加密字符串,返回String的密文 
        System.out.println(strEnc);
        String strDes = des.getDesString(strEnc);//把String类型的密文解密 
        System.out.println(strDes);
        
 
    } 
} 