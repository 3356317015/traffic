package com.zhima.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import com.zhima.basic.exception.UserBusinessException;


/**
 * HZFinal概要说明：hz静态方法类
 * @author LukeLu
 */
public class MoneyUtil {
	
	public static void main(String[] args){
		try {
			double value = 1231123.423423423;
			for(int i=0;i<1000;i++){
				value = value / 1.32423423423;
			
				value = MoneyUtil.convertPrice(1, 1, value);
			
				System.out.println("=="+i+"=="+String.valueOf(value));
			}
		} catch (UserBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * getValueByUnit方法慨述:根据取整单位、进位方式计算得到的值
	 * 创 建  人：鲁承毅
	 * 创建时间：2011-4-26 下午01:51:36
	 * 修 改  人：(修改了该文件，请填上修改人的名字)
	 * 修改日期：(请填上修改该文件时的日期)
	 * @param hzunit_id 取整单位id号，{"1","1分"}前面的数字。
	 * @param carryway_id {"1","四舍五入"}前面的数字。
	 * @param value 原值
	 * @return double  计算后的结果值
	 * @throws UserBusinessException 
	 */
	public static double convertPrice(int roundUnit,int carryMode ,double value) throws UserBusinessException{
		double rs_value = 0.00;
		//定义的临时变量
		double floor = 0.00;
		double tmpval = 0.00;
		DecimalFormat df=new DecimalFormat("0.00");
		
		if (roundUnit < 1 || roundUnit >6){
			throw new UserBusinessException("取整单位序号不在限定的范围中。");
		}
		if (carryMode < 1 || carryMode > 4){
			throw new UserBusinessException("进位方式序号不在限定的范围中。");
		}
		switch (roundUnit) {
		//以1分为单位
		case 1:
			switch (carryMode) {
			//四舍五入法
			case 1:
				value = value * 100.00 ;
				value = Double.valueOf(df.format(value));
				rs_value = Math.round(value);
				rs_value = rs_value /100;
				break;
			//直接进位法
			case 2:
				value = value * 100.00;
				value = Double.valueOf(df.format(value));
				rs_value = Math.ceil(value);
				rs_value = rs_value /100.00;
				break;
			//直接舍位法
			case 3:
				value = value * 100;
				value = Double.valueOf(df.format(value));
				rs_value = Math.floor(value);
				rs_value = rs_value /100;
				break;
			//3、7作5,2、8作10
			case 4:
				value = value * 100;
				value = Double.valueOf(df.format(value));
				rs_value = Math.round(value);
				rs_value = rs_value /100;
				break;
			default:
				break;
			}
			break;
		//以1角为单位
		case 2:
			switch (carryMode) {
			//四舍五入法
			case 1:
				value = value * 10;
				value = Double.valueOf(df.format(value));
				rs_value = Math.round(value);
				rs_value = rs_value /10;
				break;
			//直接进位法
			case 2:
				value = value * 10;
				value = Double.valueOf(df.format(value));
				rs_value = Math.ceil(value);
				rs_value = rs_value /10;
				break;
			//直接舍位法
			case 3:
				value = value * 10;
				value = Double.valueOf(df.format(value));
				rs_value = Math.floor(value);
				rs_value = rs_value /10;
				break;
			//3、7作5,2、8作10
			case 4:
				value = value * 10;
				value = Double.valueOf(df.format(value));
				rs_value = Math.round(value);
				rs_value = rs_value /10;
				break;
			default:
				break;
			}
			break;
		//以1元为单位
		case 3:
			switch (carryMode) {
			//四舍五入法
			case 1:
				rs_value = Math.round(value);
				break;
			//直接进位法
			case 2:
				rs_value = Math.ceil(value);
				break;
			//直接舍位法
			case 3:
				rs_value = Math.floor(value);
				break;
			//3、7作5,2、8作10
			case 4:
				rs_value = Math.round(value);
				break;
			default:
				break;
			}
			break;
		//以5分为单位
		case 4:
			switch (carryMode) {
			//直接进位法
			case 2:
				floor = Math.floor(value * 10.00);
				tmpval = (value * 10.00) - floor;
				if(tmpval == 0){
					rs_value = floor/10.00; 
				}
				if(tmpval >0 && tmpval <= 0.5){
					rs_value = (floor+0.5)/10.00;
				}
				if(tmpval > 0.5){
					rs_value = (floor+1.00)/10.00;
				}
				break;
			//直接舍位法
			case 3:
				floor = Math.floor(value * 10.00);
				tmpval = (value * 10.00) - floor;
				if(tmpval == 0){
					rs_value = floor/10.00; 
				}
				if(tmpval >0 && tmpval <= 0.5){
					rs_value = (floor)/10.00;
				}
				if(tmpval > 0.5){
					rs_value = (floor+1.00)/10.00;
				}
				break;
			//3、7作5,2、8作10
			default:
				floor = Math.floor(value * 10.00);
				tmpval = (value * 10.00) - floor;
				if(tmpval <=0.2){
					rs_value = floor/10.00; 
				}
				if(tmpval > 0.2 && tmpval < 0.8){
					rs_value = (floor+0.5)/10.00; 
				}
				if(tmpval >=0.8){
					rs_value = (floor+1.00)/10.00;
				}
				break;
			}
			break;
		//以5角为单位
		case 5:
			switch (carryMode) {
			//直接进位法
			case 2:
				floor = Math.floor(value * 1.00);
				tmpval = (value * 1.00) - floor;
				if(tmpval == 0){
					rs_value = floor/1.00; 
				}
				if(tmpval >0 && tmpval <= 0.5){
					rs_value = (floor+0.5)/1.00;
				}
				if(tmpval > 0.5){
					rs_value = (floor+1.00)/1.00;
				}
				break;
			//直接舍位法
			case 3:
				floor = Math.floor(value * 1.00);
				tmpval = (value * 1.00) - floor;
				if(tmpval == 0){
					rs_value = floor/1.00; 
				}
				if(tmpval >0 && tmpval <= 0.5){
					rs_value = (floor)/1.00;
				}
				if(tmpval > 0.5){
					rs_value = (floor+1.00)/1.00;
				}
				break;
			//3、7作5,2、8作10
			default:
				floor = Math.floor(value * 1.00);
				tmpval = (value * 1.00) - floor;
				if(tmpval <=0.2){
					rs_value = floor/1.00; 
				}
				if(tmpval > 0.2 && tmpval < 0.8){
					rs_value = (floor+0.5)/1.00; 
				}
				if(tmpval >=0.8){
					rs_value = (floor+1.00)/1.00;
				}
				break;
			}
			break;
		//以5元为单位
		case 6:
			switch (carryMode) {
			//直接进位法
			case 2:
				floor = Math.floor(value / 10.00);
				tmpval = (value / 10.00) - floor;
				if(tmpval == 0){
					rs_value = floor*10.00; 
				}
				if(tmpval >0 && tmpval <= 0.5){
					rs_value = (floor+0.5)*10.00;
				}
				if(tmpval > 0.5){
					rs_value = (floor+1.00)*10.00;
				}
				break;
			//直接舍位法
			case 3:
				floor = Math.floor(value / 10.00);
				tmpval = (value / 10.00) - floor;
				if(tmpval == 0){
					rs_value = floor*10.00; 
				}
				if(tmpval >0 && tmpval <= 0.5){
					rs_value = (floor)*10.00;
				}
				if(tmpval > 0.5){
					rs_value = (floor+1.00)*10.00;
				}
				break;
			//3、7作5,2、8作10
			default:
				floor = Math.floor(value / 10.00);
				tmpval = (value / 10.00) - floor;
				if(tmpval <=0.2){
					rs_value = floor*10.00; 
				}
				if(tmpval > 0.2 && tmpval < 0.8){
					rs_value = (floor+0.5)*10.00; 
				}
				if(tmpval >=0.8){
					rs_value = (floor+1.00)*10.00;
				}
				break;
			}
			break;
		default:
			break;
		}
		return  MoneyUtil.round(rs_value, 2);
	}
	
	/**
	 * round方法慨述:提供精确的小数位四舍五入处理。
	 * 创 建  人：鲁承毅
	 * 创建时间：2011-8-13 下午08:57:35
	 * 修 改  人：(修改了该文件，请填上修改人的名字)
	 * 修改日期：(请填上修改该文件时的日期)
	 * @param v 需要四舍五入的数字
	 * @param scale 小数点后保留几位
	 * @return double  四舍五入后的结果
	 * @throws
	 */
	public static double round(double v, int scale) {
		if(scale <= 0){
			scale = 2;
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	/**
	 * roundString方法慨述:精确的小数位四舍五入处理，并转换为String
	 * 创 建  人：鲁承毅
	 * 创建时间：2012-8-1 下午03:50:38
	 * 修 改  人：鲁承毅
	 * 修改日期：2012-8-1 下午03:50:38
	 * @param v 需要四舍五入的数字
	 * @param scale  小数点后保留几位
	 * @return String 四舍五入后的结果
	 * @throws
	 */
	public static String roundString(double v, int scale){
		DecimalFormat df=new DecimalFormat("0.00");
		if(scale <= 0){
			scale = 2;
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		Double db_value = b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
		return df.format(db_value);
	}
}
