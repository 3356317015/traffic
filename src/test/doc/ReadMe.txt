D:\apache-tomcat-7.0.57\webapps\axis2\WEB-INF\services

WebService接口规范
请求参数
1.String(map) security:加密安全认证
	1.1 String ClientUrl:请求地址
	1.2 String ClientMac:Mac地址
	1.3 String UserCode:用户
	1.4 String PassWord:密码
	1.5 String Organize:机构
	1.6 String OperUser:操作用户
2.String(map) parameter:加密参数
	2.1 String s开头：文本参数
	2.2 String i开头：整型参数
	2.3 String d开头：浮点型参数
	2.4 String o开头：JavaBean
	2.5 String a开头：JavaBean List
3.String(map) result：加密返回结果
	3.1 String ResultCode:返回代码
		3.1.1 0＝成功，!0＝失败
		3.1.2 1开头＝系统定义错误
		3.1.3 2开头＝用户定义错误
	3.2 String Message:消息
	3.3 Result:返回结果
		3.3.1 String s开头：文本参数
		3.3.2 String i开头：整型参数
		3.3.3 String d开头：浮点型参数
		3.3.4 String o开头：JavaBean
		3.3.5 String a开头：JavaBean List


