package com.zhima.traffic.comm;


/**
 * ConstantFinal概要说明：常量
 * @author lcy
 */
public class TraffFinal {
	/**
	 * 登录用户当前票号
	 */
	public static String USER_CURR_TICKET ="";

	/**
	 * 客运站类型
	 */
	
	public static final String ARR_ORGANIZE_TYPE[] = new String[]{"1-等级站","2-招呼站","3-简易站","4-合作站"};
	/**
	 * 客运站等级
	 */
	public static final String[] ARR_ORGANIZE_LEVEL = new String[]{"1-一级站","2-二级站","3-三级站","4-四级站","5-五车站","6-未评定"};
	
	/**
	 * 组织机构状态
	 */
	public static final String ARR_ORGANIZE_STATUS[] = new String[]{"0-停业","1-营业","2-整改","3-停业整改","4-歇业","5-注销","6-其他"};
	
	/**
	 * 是否销售点
	 */
	public static final String[] ARR_IF_SERVICE = new String[]{"0-否","1-是"};
	
	/**
	 * 销售点状态
	 */
	public static final String[] ARR_SERVICE_STATUS = new String[]{"0-无效","1-有效"};
	
	/**
	 * 配载状态设置
	 */
	public static final String[] ARR_DEAL_STATUS = new String[]{"0-关闭","1-开启"};
	
	/**
	 * 站点状态
	 */
	public static final String[] ARR_STATION_STATUS = new String[]{"0-无效","1-有效"};
	
	/**
	 * 线路到达方式
	 */
	public static final String[] ARR_ARRIVE_TYPE = new String[]{"1-直达","2-中转"};
	
	/**
	 * 线路类型
	 */
	public static final String[] ARR_ROAD_TYPE = new String[]{"1-高速公路","2-国、省道","3-乡村道路"};
	
	/**
	 * 乘车点乘车
	 */
	public static final String[] ARR_USING_SERVICE = new String[]{"0-无","1-有"};
	
	public static final String[] ARR_IF_USING = new String[]{"0-否","1-是"};
	
	/**
	 * 线路状态
	 */
	public static final String[] ARR_ROUTE_STATUS = new String[]{"0-停止运营","1-正在运营","2-临时停运"};
	
	/**
	 * 取整单位
	 */
	public static final String ARR_ROUND_UNIT[]={"1-1分","2-5分","3-1角","4-5角","5-1元"};
	
	/**
	 * 进位方式
	 */
	public static final String ARR_CARRY_MODE[]={"1-四舍五入","2-进位","3-舍位","4-3、7作5,2、8作10"};
	
	/**
	 * 票型种类
	 */
	public static final String[] ARR_FARETYPE_CLASS = new String[]{"1-全票","2-半票","3-免票"};
	
	/**
	 * 是否含免
	 */
	public static final String[] ARR_IF_HAVAFREE = new String[]{"0-否","1-是"};
	
	/**
	 * 票型状态
	 */
	public static final String[] ARR_FARETYPE_STATUS = new String[]{"0-无效","1-有效"};

	/**
	 * 结算归属方
	 */
	public static final String[] ARR_ITEM_BELONG = new String[]{"1-车站","2-车方"};
	
	/**
	 * 资质项类型
	 */
	public static final String[] ARR_CER_TYPE = new String[]{"1-车辆","2-驾驶员"};
	
	/**
	 * 资质项类型状态
	 */
	public static final String[] ARR_CER_STATUS = new String[]{"0-无效","1-有效"};

	/**
	 * 性别
	 */
	public static final String[] ARR_SEX = new String[]{"M-男","F-女"};
	
	/**
	 * 车辆状态
	 */
	public static final String[] ARR_CAR_STATUS = new String[]{"0-无效","1-有效"};
	
	/**
	 * 驾驶员状态
	 */
	public static final String[] ARR_DRIVER_STATUS = new String[]{"0-无效","1-有效"};
	
	/**
	 * 安检有效期
	 */
	public static final String[] ARR_SAFECAR_TYPE = new String[]{"1-趟","2-小时","3-天"};
	
	/**
	 * 资质审查有效期
	 */
	public static final String[] ARR_SAFECER_TYPE = new String[]{"1-趟","2-小时","3-天"};

	/**
	 * 计划类型
	 */
	public static final String ARR_PLAN_TYPE[] = new String[]{"固定班次","单日","双日","隔日","流水班次","包车","其他班次"};

	/**
	 * 计划状态
	 */
	public static final String ARR_PLAN_STATUS[] = new String[]{"销售","停班","保班","临时","撤班"};
	
	/**
	 * 座位状态
	 */
	public static final String[] ARR_SEAT_TYPE =  new String[]{"1-普通座","2-商务座","3-卧铺","4-其他"};

	/**
	 * 座位状态
	 */
	public static final String[] ARR_SEAT_STATE =  new String[]{"0-禁售","1-可售","2-已售","3-预留","4-配载"};

	/**
	 * 售票方式
	 */
	public static final String[] ARR_SALE_TYPE = new String[]{"1-本站售","2-终端售","3-异站售","4-代售点","5-网售","6-手机售"};

	/**
	 * 是否打印席位
	 */
	public static final String[] ARR_IF_PRINTSEAT = new String[]{"0-否","1-是"};

	/**
	 * 是否网售
	 */
	public static final String[] ARR_IF_NETSALE = new String[]{"0-否","1-是"};

	/**
	 * 是否配载
	 */
	public static final String[] ARR_IF_DEAL = new String[]{"0-否","1-是"};

	/**
	 * 是否始发
	 */
	public static final String[] ARR_IF_MAIN = new String[]{"0-否","1-是"};

	/**
	 * 价套状态
	 */
	public static final String[] ARR_FARESUIT_STATUS = new String[]{"0-无效","1-有效"};
	
	/**
	 * 是否同步配载站车辆信息
	 */
	public static final String[] ARR_SENDCAR = new String[]{"0-否","1-是"};

	/**
	 * 是否同步配载站驾驶员信息
	 */
	public static final String[] ARR_SENDDRIVER = new String[]{"0-否","1-是"};

	/**
	 * 是否同步配载站安检信息
	 */
	public static final String[] ARR_SENDSAFE = new String[]{"0-否","1-是"};
	
	/**
	 * 是否接收配载站车辆信息
	 */
	public static final String[] ARR_RECEIVECAR = new String[]{"0-否","1-是"};

	/**
	 * 是否接收配载站驾驶员信息
	 */
	public static final String[] ARR_RECEIVEDRIVER = new String[]{"0-否","1-是"};

	/**
	 * 是否接收配载站安检信息
	 */
	public static final String[] ARR_RECEIVESAFE = new String[]{"0-否","1-是"};
	
	/**
	 * 票据状态
	 */
	public static final String[] ARR_TICKET_STATE = new String[]{"0-作废","1-售票","2-检票","3-退票","4-改签","5-其他"};
	
	/**
	 * 报班状态
	 */
	public static final String[] ARR_REPORT_STATUS = new String[]{"0-否","1-是"};
	
	/**
	 * 打单状态
	 */
	public static final String[] ARR_PRINTBILL_STATUS = new String[]{"0-否","1-是"};
	
	/**
	 * 班次状态
	 */
	public static final String[] ARR_LINER_STATE = new String[]{"销售","停班","撤班","废班"};
	
	/**
	 * 报班类型
	 */
	public static final String[] ARR_REPORT_TYPE = new String[]{"正点","晚点","顶班"};

	/**
	 * 默认检票口
	 */
	public static final String[] ARR_DEFAULT_CHECK = new String[]{"0-否","1-是"};
	
	/**
	 * 票据类型
	 */
	public static final String ARR_CATEGORY[] = new String[]{"1-例检单","2-车票","3-结算单"};
	
	/**
	 * 计划类型
	 */
	public static final String ARR_LINER_TYPE[] = new String[]{"固定班次","加班","流水班次","包车","其他班次"};
	
	/**
	 * 票据操作类型
	 */
	public static final String ARR_OPER_TYPE[] = new String[]{"1-入库","2-发放"};

	public static final String[] ARR_POOL_STATUS = new String[]{"0-停用","1-未用","2-使用","3-用完"};

	public static final String[] ARR_COMPANY_STATUS = new String[]{"0-停止运营","1-正在运营"};

	/**
	 * 线路播音状态
	 */
	public static final String[] ARR_VOICE_STATUS = new String[]{"0-否","1-是"};
	
	/**
	 * 文本公告状态
	 */
	public static final String[] ARR_NOTICE_STATUS = new String[]{"0-无效","1-有效"};
	/**
	 * 语音公告状态
	 */
	public static final String[] ARR_SOUND_STATUS = new String[]{"0-无效","1-有效"};

	public static final String[] ARR_VOICE_TYPE = new String[]{"1-班次公告","2-文本公告","3-语音公告"};




}
