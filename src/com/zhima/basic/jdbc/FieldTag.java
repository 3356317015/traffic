package com.zhima.basic.jdbc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * FieldTag概要说明：自定义标签
 */
@Target(value={ElementType.FIELD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface FieldTag {
	//属性的中文名
	String cName() default "";
	//属性的英文名
	String eName() default "";
	//对应数据库字段名
	String field() default "";
	//字段长度
	int length() default 0;
	//是否字段
	boolean db() default true;
	//键值对
	String match() default "";
	//是否主键
	boolean pk() default false;
}