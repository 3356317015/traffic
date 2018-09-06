package com.zhima.util.jTTS;

import java.io.UnsupportedEncodingException;

public class Test {

	/** main方法慨述:
	 * <br>@param args 
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		int i = JttsUtil.CLibrary.INSTANCE.jTTS_Init(null, null);
		System.out.println("=jTTS_Init=="+i);
		
		boolean bl_voice = JttsUtil.CLibrary.INSTANCE.jTTS_IsVoiceSupported("84316E85-143E-4410-B00B-9DF681684C6C");
		System.out.println(bl_voice);
		
		int b = JttsUtil.CLibrary.INSTANCE.jTTS_PreLoad("84316E85-143E-4410-B00B-9DF681684C6C");
		System.out.println("==jTTS_PreLoad=="+b);
		
		i = JttsUtil.CLibrary.INSTANCE.jTTS_SetParam(JttsUtil.PARAM_CODEPAGE, 65001);
		System.out.println("==PARAM_CODEPAGE=="+i);
		
		i = JttsUtil.CLibrary.INSTANCE.jTTS_SetParam(JttsUtil.PARAM_ENGMODE, 1);
		System.out.println("==PARAM_ENGMODE=="+i);
		
		i = JttsUtil.CLibrary.INSTANCE.jTTS_SetParam(JttsUtil.PARAM_DOMAIN, 0);
		System.out.println("==PARAM_DOMAIN=="+i);

		i = JttsUtil.CLibrary.INSTANCE.jTTS_Play("中国2123421421321423", 4096);
		System.out.println("=jTTS_Play=="+i);
		
		int state = JttsUtil.CLibrary.INSTANCE.jTTS_GetStatus();
		while(state !=3){
			state = JttsUtil.CLibrary.INSTANCE.jTTS_GetStatus();
		}
		if(state == 3){
			i = JttsUtil.CLibrary.INSTANCE.jTTS_End();
			System.out.println("=jTTS_End=="+i);
		}
	}

}
