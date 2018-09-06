package com.zhima.util.time;

import com.sun.jna.Platform;

public class NativeFactory {

	public static JNative newNative(){
		if(Platform.isWindows()){
			return new WindowsImpl();
		}
		return new LinuxImpl();
	}
}
