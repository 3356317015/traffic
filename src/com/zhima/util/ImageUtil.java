package com.zhima.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;

import com.zhima.util.blob.BlobImpl;
import com.zhima.util.blob.SerializableBlob;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


public class ImageUtil {
	/**
	 * 将byte数组转换为Base64的字符串
	 * @param bytes byte数组
	 * @return
	 */
	public static String encode(byte[] bytes){
		return new BASE64Encoder().encode(bytes);
//		String str = new String(bytes);
//	    return str;
	}
	
	/**
	 * 将Base64的字符串转换为byte数组
	 * @param encodeStr
	 * @return
	 * @throws IOException
	 */
	public static byte[] decode(String encodeStr){
		try {
			BASE64Decoder decoder = new BASE64Decoder();  
			byte[] bt = decoder.decodeBuffer(encodeStr);
			return bt;
		} catch (IOException e) {
			return null;
		}
//		byte[] byteArray = encodeStr.getBytes();
//	    return byteArray;
	}
	
	/**
	 * 将java.mysql.Blob大二进制的对象转为base64的字符串
	 * @param blob
	 * @return
	 */
	public static String blobToBase64(Blob blob){
		if (null != blob){
			byte[] bytes = blobToBytes(blob);
			return encode(bytes);
		}else{
			return null;
		}
	}
	
	/**
	 * 将java.mysql.Blob的对象转换为byte数组
	 * @param blob 大二进制对象
	 * @return
	 */
	public static byte[] blobToBytes(Blob blob){
		try {
			BufferedInputStream is = new BufferedInputStream(blob.getBinaryStream());
			byte[] bytes = new byte[(int) blob.length()];
			int len = bytes.length;
			int offset = 0;
			int read = 0;
			while (offset < len && (read = is.read(bytes, offset, len - offset)) >= 0) {
				offset += read;
			}
			return bytes;
		} catch (Exception e) {
			return null;
		} /*finally {
			try {
				is.close();
				is = null;
			} catch (IOException e) {
				return null;
			}
		}*/
	}
	
	
	/**
	 * 将java.mysql.Blob的对象转换为byte数组
	 * @param blob 大二进制对象
	 * @return
	 */
	public static Blob Base64ToBlob(String base64){
		try {
			if (null != base64 && base64.length()>0){
				byte[] by = ImageUtil.decode(base64);
				InputStream in = new ByteArrayInputStream(by);
				Blob blob = null;
				while((in.read(by))>0){	
					blob = new SerializableBlob( new BlobImpl(by));
				}
				return blob;
			}else{
				return null;
			}

		} catch (IOException e) {
			return null;
		}
	}
	public static void main(String[] args) {
		
		try {
			FileInputStream in = new FileInputStream("D:\\logo.ico");
			byte[] by = new byte[Integer.valueOf(in.available())];
			//int len;
			while((in.read(by))>0){	
				Blob driverBmp = new SerializableBlob( new BlobImpl(by));
				String string = ImageUtil.blobToBase64(driverBmp);
				System.out.println(string);
				
				
				byte[] bs = ImageUtil.decode(string);
				File file = new File("D:\\logo1.ico");

				//建立输出字节流
				FileOutputStream fos = new FileOutputStream(file);

				//用FileOutputStream 的write方法写入字节数组
				fos.write(bs);
				System.out.println("写入成功");

				//为了节省IO流的开销，需要关闭
				fos.close();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

