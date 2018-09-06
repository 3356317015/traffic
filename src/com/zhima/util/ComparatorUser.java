package com.zhima.util;

import java.lang.reflect.Method;
import java.util.Comparator;

import com.zhima.basic.exception.UserSystemException;


/**
 * ComparatorUser概要说明：List排序比较
 * @author lcy
 */
@SuppressWarnings("rawtypes")
public class ComparatorUser implements Comparator {
	//列字段名称
	private String columnName;
	
	//默认为升序排列
	private boolean arrowUp= true;

	public ComparatorUser(String columnName,boolean arrowUp) {
		this.columnName = columnName;
		this.arrowUp = arrowUp;
	}
	
	@SuppressWarnings("unchecked")
	public int compare(Object obj1, Object obj2) {
		int flag = 0;
		try {
			Class c1 = obj1.getClass();
			String funName = "get" + columnName.substring(0, 1).toUpperCase()+ columnName.substring(1);
			Method method1 = c1.getMethod(funName);

			Class c2 = obj2.getClass();
			Method method2 = c2.getMethod(funName);
			if(arrowUp){
				//从小到大排序
				if(c1.getDeclaredField(columnName).getType().equals(Integer.class)){
					flag = Integer.valueOf(method1.invoke(obj1).toString()) - Integer.valueOf(method2.invoke(obj2).toString());
				}else if(c1.getDeclaredField(columnName).getType().equals(Long.class)){
					flag = (int) (Long.valueOf(method1.invoke(obj1).toString()) - Long.valueOf(method2.invoke(obj2).toString()));
				}else if(c1.getDeclaredField(columnName).getType().equals(Double.class)){
					flag = (int) (Double.valueOf(method1.invoke(obj1).toString()) - Double.valueOf(method2.invoke(obj2).toString()));
				}else{
					flag = method1.invoke(obj1).toString().compareTo(method2.invoke(obj2).toString());
				}
			}else{
				//从大到小排序
				if(c1.getDeclaredField(columnName).getType().equals(Integer.class)){
					flag = Integer.valueOf(method2.invoke(obj2).toString()) - Integer.valueOf(method1.invoke(obj1).toString());
				}else if(c1.getDeclaredField(columnName).getType().equals(Long.class)){
					flag = (int) (Long.valueOf(method2.invoke(obj2).toString())- Long.valueOf(method1.invoke(obj1).toString()));
				}else if(c1.getDeclaredField(columnName).getType().equals(Double.class)){
					flag = (int) (Double.valueOf(method2.invoke(obj2).toString()) - Double.valueOf(method1.invoke(obj1).toString()));
				}else{
					flag = method2.invoke(obj2).toString().compareTo(method1.invoke(obj1).toString());
				}
			}
		} catch (Exception e) {
			throw new UserSystemException(e.getMessage());
		}
		return flag;
	}
}

