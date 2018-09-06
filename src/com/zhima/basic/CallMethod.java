package com.zhima.basic;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.zhima.basic.exception.UserBusinessException;
import com.zhima.frame.model.SamModuleRight;
import com.zhima.util.SWTResourceManager;
import com.zhima.util.log4j.LogUtil;
import com.zhima.widget.CButton;
import com.zhima.widget.FButton;
import com.zhima.widget.MsgBox;

public final class CallMethod {
	//回车是否跳下一个控件并得到焦点
	private boolean onNext = true;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void bindSelectMenu(final Object obj, SamModuleRight moduleRight){
		try {
			if (null != moduleRight && !"".equals(moduleRight.getRightMethod())){
				/*MusicPlay click = new MusicPlay(getClass().getResource("click.wav"));
				click.start();*/
				CommFinal.insertOperLog(moduleRight,"1","");
				Class cls = obj.getClass();
				//Method[] methods = cls.getMethods();获取类方法
				cls.getDeclaredMethod(moduleRight.getRightMethod()).invoke(obj);
				//cls.getDeclaredMethod(moduleRightBo.getRightEvent(), new Class[]{各参数类型}).invoke(this, new Object[]{"参数值"});
			}else{
				CommFinal.updateOperLog(moduleRight,"0","执行方法未设置。");
				MsgBox.warning(new Shell(), "执行方法未设置,请与管理员联系。");
			}
		} catch (Exception ex) {
			try {
				CommFinal.updateOperLog(moduleRight,"0","运行失败或方法设置不正确");
			} catch (UserBusinessException e) {
				LogUtil.operLog(ex, "E", true, "[" + moduleRight.getRightName() + "]运行失败或方法设置不正确,\r\n请与管理员联系。");
			}
			LogUtil.operLog(ex, "E", true, "[" + moduleRight.getRightName() + "]运行失败或方法设置不正确,\r\n请与管理员联系。");
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void bindSelectMenu(Object obj, String methodName) {
		try {
			if (null != methodName && !"".equals(methodName)){
				/*MusicPlay click = new MusicPlay(getClass().getResource("click.wav"));
				click.start();*/
				Class cls = obj.getClass();
				//Method[] methods = cls.getMethods();获取类方法
				cls.getDeclaredMethod(methodName).invoke(obj);
				//cls.getDeclaredMethod(moduleRightBo.getRightEvent(), new Class[]{各参数类型}).invoke(this, new Object[]{"参数值"});
			}else{
				MsgBox.warning(new Shell(), "执行方法未设置,请与管理员联系。");
			}
		} catch (Exception ex) {
			LogUtil.operLog(ex, "E", true, "[" + methodName + "]运行失败或方法设置不正确,\r\n请与管理员联系。");
		}
	}
	
	public void createBtnRight(Object object,Composite composite, List<SamModuleRight> rights){
		boolean flat = true;
		if (flat == true){
			List<FButton> fButtons = new ArrayList<FButton>();
			int n = 0;
			for (int i = 0; i < rights.size(); i++) {
				if ("button".equals(rights.get(i).getRightType().toLowerCase())){
					fButtons.add(new FButton(composite, SWT.NONE));
					fButtons.get(n).setLayout(new RowLayout());
					fButtons.get(n).createButton(rights.get(i));
					fButtons.get(n).bindSelectButton(object, rights.get(i));
					fButtons.get(n).bindMouseListener(object, rights.get(i));
					fButtons.get(n).forceFocus();
					n+=1;
				}
			}
		}else{
			if (null != rights && rights.size()>0){
				List<CButton> cButtons = new ArrayList<CButton>();
				int n = 0;
				for (int i = 0; i < rights.size(); i++) {
					if ("button".equals(rights.get(i).getRightType().toLowerCase())){
						cButtons.add(new CButton(composite, SWT.NONE));
						cButtons.get(n).setLayout(new RowLayout());
						cButtons.get(n).createButton(rights.get(i));
						cButtons.get(n).bindSelectButton(object, rights.get(i));
						cButtons.get(n).forceFocus();
						n+=1;
					}
				}
			}
		}
	}
	
	/**
	 * bindEnterEvent方法描述：对象绑定回车事件
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-7 上午11:45:45
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param object 类
	 * @param currentControl 当前控件
	 * @param nextControl 下一个控件
	 * @param eventName 方法名
	 */
	public void bindEnterEvent(final Object object,final Control currentControl,final Control nextControl,final String eventName){
		currentControl.addKeyListener(new KeyAdapter() {
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.keyCode == 13 || e.keyCode==SWT.KEYPAD_CR
						|| e.keyCode==16777227){
					if (null != eventName && !"".equals(eventName)){
						try {
							
							Class cls = object.getClass();
							cls.getDeclaredMethod(eventName).invoke(object);
						} catch (Exception e1) {
							LogUtil.operLog(e1, "E", true, "调用方法失败。");
							controlFocus(currentControl);
							return;
						}
					}
					if (isOnNext() == true){
						controlFocus(nextControl);
					}
				}
			}
		});
		if (null != nextControl){
			nextControl.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					if(e.keyCode==16777229){
						controlFocus(currentControl);
					}
				}
			});
		}
		//注册当前控件的得到焦点和失去焦点事件
		if (null != currentControl){
			currentControl.addFocusListener(focusAdapter(currentControl));
			//初始化CCombo背景色
			/*String ctrName = currentControl.getClass().getSimpleName();
			if(ctrName.equals("CCombo")){ 
				CCombo cCombo = (CCombo)currentControl;
				if (cCombo.getEditable()==true){
					cCombo.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
				}else{
					cCombo.setBackground(SWTResourceManager.getColor(240, 240 ,240));
				}
			}*/
		}
	}
	
	/**
	 * focusAdapter方法描述：焦点事件控制背景颜色
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-7-8 下午04:33:42
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param control
	 * @return FocusAdapter
	 */
	public FocusAdapter focusAdapter(final Control control){
		FocusAdapter focusAdapter = new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				String ctrName = control.getClass().getSimpleName();
				if (ctrName.equals("Button")){
					control.setBackground(StyleFinal.DIALOG_BACK_GROUND);
				} else if(ctrName.equals("Text")){
					Text text = (Text)control;
					if (text.getEditable()==true){
						text.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
					}else{
						text.setBackground(SWTResourceManager.getColor(240, 240 ,240));
					}
				} else if(ctrName.equals("CCombo")){ 
					CCombo cCombo = (CCombo)control;
					if (cCombo.getEditable()==true){
						cCombo.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
					}else{
						cCombo.setBackground(SWTResourceManager.getColor(240, 240 ,240));
					}
				} else{
					control.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
				}
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				control.setBackground(StyleFinal.FOCUS_BACKGROUND);
			}
		};
		return focusAdapter;
	}
	
	/**
	 * controlFocus方法描述：控制得到焦点
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-3-7 下午01:08:27
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param control void
	 */
	private void controlFocus(Control control){
		if (null != control){
			String ctrName = control.getClass().getSimpleName();
			if (ctrName.equals("Text")){
				Text text = (Text)control;
				text.forceFocus();
				if (null != text.getText() && !"".equals(text.getText())){
					text.selectAll();
				}
			} else if (ctrName.equals("Combo")){
				Combo combo = (Combo)control;
				combo.forceFocus();
			} else if (ctrName.equals("CCombo")){
				CCombo combo = (CCombo)control;
				combo.forceFocus();
			} else if (ctrName.equals("Button")){
				Button button = (Button)control;
				button.forceFocus();
			}
		}
	}

	public boolean isOnNext() {
		return onNext;
	}

	public void setOnNext(boolean onNext) {
		this.onNext = onNext;
	}


	
}  

