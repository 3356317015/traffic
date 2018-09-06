/**
 *******************************************************************************
 * 
 * (c) Copyright 2011 合智智能交通有限责任公司
 *
 * 文 件  名：SerialConnectionException.java
 * 系统名称：kylwsp
 * 模块名称：(请更改成该模块名称)
 * 创 建  人：梁平
 * 创建日期：2011-4-17 上午06:14:38
 * 修 改 人：(修改了该文件，请填上修改人的名字)
 * 修改日期：(请填上修改该文件时的日期)  
 * 版     本： V1.0.0
 *******************************************************************************  
 */
package com.zhima.basic.exception;

/** SerialConnectionException概要说明：串口连接异常
 * @author lp
 */
public class SerialConnException extends Exception {

    /** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */ 
	private static final long serialVersionUID = -8206663590231691816L;

	/***
	 * 
	 * 构造函数:异常信息
	 * @param str
	 */
    public SerialConnException(String str) {
        super(str);
    }
}
