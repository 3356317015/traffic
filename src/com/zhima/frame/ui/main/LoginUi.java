package com.zhima.frame.ui.main;

import org.eclipse.swt.widgets.Display;

import com.zhima.basic.CommFinal;
import com.zhima.basic.StyleFinal;
import com.zhima.basic.jdbc.PoolHandler;
import com.zhima.util.log4j.LogUtil;

public class LoginUi {
	
	/**
	 * main方法描述：进入系统入口
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-12-4 下午05:13:17
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param args void
	 */
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		Display display = Display.getDefault();
		try {
			StyleFinal.initializeConfig();
			if (1==CommFinal.CONNECT_MODE){
				PoolHandler poolHandler = new PoolHandler();
				poolHandler.initPool();
				poolHandler.pool.setShowConn(true);
			}
			LoginDialog.open(display);
		} catch (Exception e) {
			LogUtil.operLog(e,"E",true,true);
		}
	}
	
}
