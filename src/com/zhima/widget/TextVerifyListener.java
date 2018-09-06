package com.zhima.widget;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;

public class TextVerifyListener implements VerifyListener{  
    private int type;  
    public TextVerifyListener(int type){  
    	this.type=type;  
    }
    
    public void verifyText(VerifyEvent e) {
        if(type==1){//
            /*boolean b = "0123456789".indexOf(e.text) >= 0;  
            	e.doit = b;  //doit属性如果为true,则字符允许输入,反之不允许  
            return;*/
           /* Pattern pattern = Pattern.compile("^[0-9]+\\.{0,1}[0-9]{0,2}$");    
            Matcher matcher = pattern.matcher(e.text);    
            if (matcher.matches()){
            	//处理数字
            	e.doit = true;
            } else if (e.text.length() > 0){
            	//有字符情况,包含中文、空格
            	if (".".equals(e.text)){
            		e.doit = true;
            	}else{
            		e.doit = false; 
            	}
            } else {
            	e.doit = true;//控制键    
            }*/
        	String str = "";
        	if ("CCombo".equals(e.widget.getClass().getSimpleName())){
        		CCombo combo =  (CCombo)e.widget;
        		str = combo.getText();
        	} else if ("Combo".equals(e.widget.getClass().getSimpleName())){
        		Combo combo =  (Combo)e.widget;
        		str = combo.getText();
        	} else if ("Text".equals(e.widget.getClass().getSimpleName())){
        		Text text = (Text) e.widget;
        		str = text.getText();
        	}
        	//^[-+]?(\\d+(\\.\\d*)?|\\.\\d+)[dD]?$
        	//^[0-9]+\\.{0,1}[0-9]{0,2}$
        	Pattern pattern = Pattern.compile("^[-+]?(\\d+(\\.\\d*)?|\\.\\d+)$");
            Matcher matcher = pattern.matcher(str +e.text);    
            if (matcher.matches()){
            	e.doit = true;
            }else if("-".equals(e.text)){
        		e.doit = true;
        	}else{
            	e.doit = false;
            }			
        } else if(type==2){
        	//只能输入小数
        	Pattern pattern = Pattern.compile("^[0-9]+\\.{0,1}[0-9]{0,2}$");    
            Matcher matcher = pattern.matcher(e.text);    
            if (matcher.matches()){
            	//处理数字
            	e.doit = true;
            } else if (e.text.length() > 0){
            	//有字符情况,包含中文、空格
            	if (".".equals(e.text)){
            		e.doit = true;
            	}else{
            		e.doit = false; 
            	}
            } else {
            	e.doit = true;//控制键    
            }
        } else if(type==3){
        	//只能输入数字 
        	boolean b = "0123456789".indexOf(e.text) >= 0;  
        	e.doit = b;  //doit属性如果为true,则字符允许输入,反之不允许  
        	return;
        }

    }  

}
