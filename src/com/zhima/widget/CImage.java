package com.zhima.widget;

import java.io.FileInputStream;
import java.sql.Blob;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import com.zhima.basic.exception.UserSystemException;
import com.zhima.util.ImageZoom;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.blob.BlobImpl;
import com.zhima.util.blob.SerializableBlob;

/**
 * CButton概要说明：自定义按钮
 * @author lcy
 */
public class CImage extends Composite {
	private static Label lbImage;
	/**
	 * 构造函数:自定义按钮
	 * @param parent
	 * @param style
	 */
	public CImage(Composite parent, int style) {
		super(parent, style);
		setLayout(new FormLayout());
		
		lbImage = new Label(this, SWT.NONE);
		lbImage.setAlignment(SWT.CENTER);
		FormData data = new FormData();
		data.top = new FormAttachment(0);
		data.left = new FormAttachment(0);
		data.right = new FormAttachment(100);
		data.bottom = new FormAttachment(100);
		lbImage.setLayoutData(data);
		Menu menu = new Menu(lbImage);
		MenuItem clearImage = new MenuItem(menu,SWT.CASCADE);
		clearImage.setText("清除图片");
		lbImage.setMenu(menu);
		clearImage.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent ev) {
				lbImage.setImage(null);
				lbImage.setData(null);
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});

		lbImage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
				FileDialog dialog = new FileDialog(getShell(),SWT.OPEN|SWT.MULTI);
				dialog.setFilterPath("");
				dialog.setFilterExtensions(new String[]{"*.ico","*.png","*.*"});
				dialog.setFilterNames(new String[]{"*.ico","*.png","*.*"});
				String file = dialog.open();
				if(null != file){
					StringBuffer filePathBuff = new StringBuffer();
					filePathBuff.append(file);
					String filePath = filePathBuff.toString();
					Image image = SWTResourceManager.getImage(filePath);
					ImageData imageData = image.getImageData();
					image = new Image(null, imageData.scaledTo(lbImage.getBounds().width,   
							lbImage.getBounds().height));
					lbImage.setImage(image);
					lbImage.setData(getImage(filePath));
				}
			}
		});
	}
	
	/**
	 * getBlob方法描述：得到BLOB
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-8-17 下午09:21:58
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return Blob
	 */
	public Blob getBlob(){
		return (Blob) lbImage.getData();
	}
	
	/**
	 * getImage方法描述：得到图像
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-7-13 下午09:41:13
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return Blob
	 */
	private Blob getImage(String filePath){
		try {
			FileInputStream in = new FileInputStream(filePath);
			int fileSize = in.available();
			if(fileSize > 4194304){
				throw new UserSystemException("图片大小不能超过4M。");
			}
			byte[] by = new byte[Integer.valueOf(fileSize)];
			Blob blob = null;
			while(in.read(by)>0){
				blob = new SerializableBlob(new BlobImpl(by));
			}
		    return blob;
		} catch (Exception e) {
			throw new UserSystemException(e.getMessage());
		}
	}
	
	
	/**
	 * setImage方法描述：设置图像
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-7-13 下午09:41:44
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param blob void
	 */
	public void setImage(Blob blob,int width, int height){
		lbImage.setImage(ImageZoom.getImage(blob,width,height));
		lbImage.setData(blob);
	}
	
	public void clear(){
		lbImage.setImage(null);
		lbImage.setData(null);
	}
	
}
