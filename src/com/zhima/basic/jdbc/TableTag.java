package com.zhima.basic.jdbc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * TableAnno概要说明：表注释
 * @author pengqunfei
 */
@Target(value={ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface TableTag {
	//英文名
	String eName() default "";
	//中文名
	String cName() default "";
	//数据库表明
	String tabName() default "";
}
