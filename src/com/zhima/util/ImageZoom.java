package com.zhima.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Display;

/**
 * ImageZoom概要说明：按指定大小缩放图像
 * @author lcy
 */
public class ImageZoom {
	
	/**
	 * getBtnImage方法描述：指定大小缩放图像
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-7-12 下午10:43:29
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param image 图像
	 * @param wide 宽
	 * @param high　高
	 * @return Image　新尺寸图像
	 */
	public static Image getImage(Blob image, int wide, int high){
		try {
			if(null != image){
		    	Blob blob = image;
		    	InputStream in = blob.getBinaryStream();
		    	Image newImage = new Image(Display.getCurrent(),in);
				in.close();
				ImageData imageData = newImage.getImageData();
				newImage = new Image(null, imageData.scaledTo(wide, high));
			    return newImage;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Image getImage(Image image, int wide, int high){
		if(null != image){
			ImageData imageData = image.getImageData();
			image = new Image(null, imageData.scaledTo(wide, high));
		    return image;
		}
		return null;
	}
}
