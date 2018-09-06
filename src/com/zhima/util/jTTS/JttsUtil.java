package com.zhima.util.jTTS;

import com.sun.jna.Library;
import com.sun.jna.Native;

/**
 * JTTS概要说明：捷通华声语音调用接口类
 */
public class JttsUtil {
	//文本编码
	public static int PARAM_CODEPAGE = 0;
	//音库
	public static int PARAM_VOICEID = 1;
	//基频
	public static int PARAM_PITCH = 2;
	//音量
	public static int PARAM_VOLUME = 3;
	//语速
	public static int PARAM_SPEED = 4;
	//标点符号读法 
	public static int PARAM_PUNCMODE = 5;
	//数字读法
	public static int PARAM_DIGITMODE = 6;
	//英文读法
	public static int PARAM_ENGMODE = 7;
	//标记检测
	public static int PARAM_TAGMODE = 8;
	//文本的领域
	public static int PARAM_DOMAIN = 9;
	//进行远程连接时的重试次数
	public static int PARAM_TRYTIMES = 10;
	//是否使用动态负载平衡 
	public static int PARAM_LOADBALANCE =11;
	//朗读风格
	public static int PARAM_VOICESTYLE =12;
	//背景音乐的序号
	public static int PARAM_BACKAUDIO =13;
	//背景音乐的音量
	public static int PARAM_BACKAUDIOVOLUME = 14;
	//背景音乐的属性 
	public static int PARAM_BACKAUDIOFLAG = 15;
	//输出缓冲区大小 
	public static int PARAM_VOICEBUFSIZE = 16;
	//插入信息缓冲区大小
	public static int PARAM_INSERTINFOSIZE = 17;	
	
	 public interface CLibrary extends Library{
		 CLibrary INSTANCE = (CLibrary)Native.loadLibrary("C:\\Program Files (x86)\\SinoVoice\\jTTS 5.0 Desktop\\Bin\\jTTS_ML",CLibrary.class);
		 /**
		  * jTTS_Init方法慨述:初始化jTTS系统核心，进行计算机资源的分配与整个语音合成系统的初始化工作，此函数在整个语音应用程序中只需调用一次
		  * @param pcszLibPath 初始化字符串。可以为NULL
		  * @param pcszSerialNo 字符串类型的产品序列号。可以为NULL
		  * @return int 0=(ERR_NONE)初始化成功;
		  * 			1=(ERR_ALREADYINIT)已经进行了初始化;
		  *             24-(ERR_NOTSUPPORT)所使用的系统和序列号所定义的版本型号不符;
		  *             25-(ERR_SECURITY)所传入的序列号长度不对或者不是16进制数字字符
		  *             31-(ERR_CONNECTSOCK)网络合成时，连接Socket错误 
		  */
		 int jTTS_Init(String pcszLibPath,String pcszSerialNo);
		 
		 /**
		  * jTTS_End方法慨述:释放jTTS核心占用的计算机资源
		  * @return int 0-(ERR_NONE)终止成功;
		  * 			2-(ERR_NOTINIT)尚未初始化 ;
		  * 			8-(ERR_PLAYING)当前正在播放或正处于暂停状态，将不能进行释放 
		  */
		 int jTTS_End();
		 
		 /**
		  * jTTS_FindVoice方法慨述:根据所要求的语言、领域、性别、年龄、音色名称等得到一个最匹配的音色。
		  * @param nLanguage 所要求音色的语言，为LANGUAGE_xxx，为-1表示不关心
		  * @param nGender 所要求音色的性别，为GENDER_xxx, 为-1表示不关心
		  * @param nAge 所要求音色的年龄，为AGE_xxx，为-1表示不关心
		  * @param szName 所要求音色的名称，为NULL或者空串表示不关心
		  * @param nDomain 所要求音色的领域，为DOMAIN_xxx, 为-1表示不关心
		  * @param pszVoiceId 函数返回时存音频输出色标识串的缓冲区，不能为NULL，必须保证大小大于等于ID_LEN(40) 个字节。
		  * @param pwMatchFlage 函数返回时存音频输出色匹配级别的缓冲区，如果为NULL，则不返回此值
		  * @return int 0-(ERR_NONE)找到了一个匹配的音色;
		  * 			24-(ERR_NOTSUPPORT)没有找到任何匹配的音色;
		  * 			27-(ERR_PARAM)pszVoiceID 为NULL; 
		  */
		 int jTTS_FindVoice(int nLanguage,int nGender,int nAge,String szName,int nDomain,String pszVoiceId,Long pwMatchFlage);
		 
		 /**
		  * jTTS_PreLoad方法慨述:使用此函数预先载入一个音库。 
		  * @param szVoiceID 所要载入的音色的ID串 
		  * @return int 0-(ERR_NONE)系统支持此音色 ,
		  * 			6-(ERR_OPENLIB)打开库错误, 
		  * 			27-(ERR_PARAM)传入参数错误,
		  * 			24-(ERR_NOTSUPPORT)音色不支持，或远程调用
		  */
		 int jTTS_PreLoad(String szVoiceID);
		 
		 /**
		  * jTTS_Play方法慨述:利用音频输出设备播放指定的文本内容。
		  * @param pcszText 指向待播放的文本缓冲区的指针
		  * @param dwFlage 播放选项：可以由PLAY_xxx, PLAYCONTENT_xxx, PLAYMODE_xxx 相或而成
		  * @return int (ERR_NONE)开始正常播放 
		  * 			(ERR_NOTINIT)尚未调用jTTSInit函数进行过初始化
		  * 			(ERR_OPENDEVICE)打开音频输出设备错误
		  * 			(ERR_PLAYING)当前正在播放或正处于暂停状态，将不能播放当前指定文本
		  * 			(ERR_INVALIDTEXT)pcszText为NULL
		  * 			(ERR_STARTTHREAD)启动语音合成线程出错
		  */
		 int jTTS_Play(String pcszText,int dwFlage);
		 
		 /**
		  * jTTS_Stop方法慨述:如果当前语音合成系统处于播放或者暂停状态，将中止当前的播放。如果当前并非处于上述状态，则不做任何操作
		  * @return int (ERR_NONE)正常中止当前的播放
		  * 			(ERR_DONOTHING)当前并非处于播放状态或暂停状态，无需中止
		  * 			(ERR_NOTINIT)语音合成系统系统尚未初始化
		  */
		 int jTTS_Stop();
		 
		 /**
		  * jTTS_Pause方法慨述:如果语音合成系统当前处于播放状态，将暂停播放。如果当前已经处于暂停状态或者不是在播放状态，将不做任何操作
		  * @return int (ERR_NONE)正常暂停了当前的播放
		  * 			(ERR_DONOTHING)当前已经处于暂停状态或不是在播放状态，无需暂停
		  * 			(ERR_NOTINIT)语音合成系统尚进行未初始化
		  */			
		 int jTTS_Pause();
		 
		 /**
		  * jTTS_Resume方法慨述:如果语音合成系统当前处于暂停状态，将恢复播放。如果并非处于暂停状态，将不做任何操作
		  * @return int (ERR_NONE)正常恢复了当前的播放
		  * 			(ERR_DONOTHING)当前不是处于暂停状态，无需恢复
		  * 			(ERR_NOTINIT)语音合成系统尚未初始化
		  */
		 int jTTS_Resume();
		 
		 /**
		  * jTTS_GetStatus方法慨述:获得当前语音合成系统的状态
		  * @return int (STATUS_NOTINIT)尚未进行正常初始化
		  * 			(STATUS_READING)语音合成系统正处于播放状态
		  * 			(STATUS_PAUSE)语音合成系统正处于暂停状态
		  * 			(STATUS_IDLE)语音合成系统处于空闲状态
		  */
		 int jTTS_GetStatus();
		
		 /**
		  * jTTS_SetParam方法慨述:设置语音合成使用的默认参数
		  * @param nParam 要设置何种参数
		  * @param dwValue 参数的值 
		  * @return int (ERR_NONE)正常设置 
		  * 			(ERR_CONFIG)dwValue的值在有效范围之外
		  * 			(ERR_PARAM)nParam不是有效的值
		  */
		 int jTTS_SetParam(int nParam,int dwValue);
		 
		 /**
		  * jTTS_GetParam方法慨述:根据特定的语音合成参数名称，获得当前语音合成系统所使用的参数
		  * @param nParam 要获取何种参数的值 
		  * @param pdwValue 参数的值将存放于此
		  * @return int (ERR_NONE)正常设置
		  * 			(ERR_PARAM)nParam 不是有效的值
		  */
		 int jTTS_GetParam(int nParam,int pdwValue);
		 
		 /**
		  * jTTS_IsVoiceSupported方法慨述:查看系统当前是否支持某个特定的音色
		  * @param szVoiceID 音色的标识字符串 
		  * @return boolean (true)系统支持此音色,
		  * 				(false)pszVoiceID格式不合法或者系统不支持此音色 
		  */
		 boolean jTTS_IsVoiceSupported(String szVoiceID);
		
	 }
}


