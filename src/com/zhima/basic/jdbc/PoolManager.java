package com.zhima.basic.jdbc;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.logicalcobwebs.proxool.ProxoolException;
import org.logicalcobwebs.proxool.ProxoolFacade;
import org.logicalcobwebs.proxool.admin.SnapshotIF;
import org.logicalcobwebs.proxool.configuration.JAXPConfigurator;

import com.zhima.basic.exception.UserSystemException;
import com.zhima.util.DateUtils;

/**
 * PoolManager概要说明：连接池管理
 * @author lcy
 */
public class PoolManager {
    /**
     * 是否打印连接信息
     */
    private static boolean showConn = false;
	public static boolean isShowConn() {
		return showConn;
	}

	public static void setShowConn(boolean showConn) {
		PoolManager.showConn = showConn;
	}

	/**
	 * 可用连接
	 */
	private static int activeCount=0;
	
	public PoolManager(){}
	
	@SuppressWarnings("static-access")
	public PoolManager(boolean showConn){
		this.showConn = showConn;
	}
	
	/**
	 * 构造函数:装载连接配置文件
	 * @param url
	 */
	public PoolManager(String url){
		try {
			File file = new File(url);
			if (file.exists()) {
				//System.out.println("file exists");
				JAXPConfigurator.configure(url, false);
			} else {
				//System.out.println("file create");
				String path = PoolManager.class.getClassLoader().getResource("").toURI().getPath();
				path =path.substring(1, path.length());
				JAXPConfigurator.configure(path + url, false);
			}
			
			/**
			PropertyConfigurator.configure(mysqlcon.class.getResource("/")+"proxool.properties"); 
		    Properties pro=new Properties();
		    ResourceBundle resource = ResourceBundle.getBundle("proxool");
		    pro.put("jdbc-0.proxool.alias",resource.getString("jdbc-0.proxool.alias"));
		    pro.put("jdbc-0.proxool.driver-url",resource.getString("jdbc-0.proxool.driver-url"));
		    pro.put("jdbc-0.proxool.driver-class",resource.getString("jdbc-0.proxool.driver-class"));
		    pro.put("jdbc-0.user",resource.getString("jdbc-0.user"));
		    pro.put("jdbc-0.password",resource.getString("jdbc-0.password"));
		    pro.put("jdbc-0.proxool.house-keeping-test-sql",resource.getString("jdbc-0.proxool.house-keeping-test-sql"));
		    pro.put("jdbc-0.proxool.maximum-connection-count",resource.getString("jdbc-0.proxool.maximum-connection-count"));
		    pro.put("jdbc-0.proxool.minimum-connection-count",resource.getString("jdbc-0.proxool.minimum-connection-count"));
		    PropertyConfigurator.configure(pro);
		    */
		} catch (Exception e) {
			e.printStackTrace();
			throw new UserSystemException("装载配置连接失败，请检查或设置连接。");
		} finally{
		}
	}
	
	/**
	 * getConnection方法描述：获取连接
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-12-5 下午03:33:12
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @return Connection
	 */
	public Connection getConnection(){
		try {
			Connection conn = DriverManager.getConnection("proxool.connection");
			showSnapshotInfo("获取连接 "+DateUtils.getNow(DateUtils.FORMAT_LONG),ProxoolFacade.getAlias(conn));
			return conn;
		}catch (ProxoolException e) {
			throw new UserSystemException("数据连接失败,请检查网络是否正常.");
		}catch (SQLException e) {
			throw new UserSystemException("数据连接失败,请检查网络是否正常.");
		} 
	}
	
	public Connection getConnection(String alias){
		try {
			Connection conn = DriverManager.getConnection("proxool."+alias);
			showSnapshotInfo("获取连接 "+DateUtils.getNow(DateUtils.FORMAT_LONG),ProxoolFacade.getAlias(conn));
			return conn;
		}catch (ProxoolException e) {
			throw new UserSystemException("数据连接失败,请检查网络是否正常.");
		}catch (SQLException e) {
			throw new UserSystemException("数据连接失败,请检查网络是否正常.");
		}
	}
	
	/**
	 * getConnection方法描述：获取连接
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-12-5 下午03:34:49
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param url 连接xml地址
	 * @return Connection 连接
	 */
	/*public Connection getConnection(String url){
		try {
			JAXPConfigurator.configure(url,false);
			Connection conn = DriverManager.getConnection("proxool.connection");
			showSnapshotInfo("获取连接 "+DateUtils.getNow(DateUtils.FORMAT_LONG));
			return conn;
		}catch (SQLException e) {
			throw new UserSystemException("数据连接失败,请检查网络是否正常.");
		} catch (ProxoolException e) {
			throw new UserSystemException("创建数据连接缓冲池错误.");
		}
	}*/
	
	/**
	 * beginConn方法描述：初始化连接事务
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-12-5 下午03:32:06
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param conn void
	 */
	public void beginConn(Connection conn){
		try {
			if(conn!=null){
				conn.setAutoCommit(false);
			}
		} catch (SQLException e) {
			throw new UserSystemException("初始化连接事务失败.");
		} 
	}
	
	/**
	 * rolbackConn方法描述：事务回滚
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-12-5 下午03:30:58
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param conn void
	 */
	public void rolbackConn(Connection conn){
		try {
			if(conn!=null){		
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserSystemException("事务回滚失败.");
		} 
	}
	
	/**
	 * freeConnection方法描述：关闭连接
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-12-5 下午03:30:21
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param conn void
	 */
	public void freeConnection(Connection conn){
		try {
			if(conn!=null){
				conn.close();
				showSnapshotInfo("关闭连接 "+DateUtils.getNow(DateUtils.FORMAT_LONG),ProxoolFacade.getAlias(conn));
			}
		}catch (ProxoolException e) {
			throw new UserSystemException("关闭连接失败.");
		}catch (SQLException e) {
			throw new UserSystemException("关闭连接失败.");
		} 
	}
	
	public boolean isBegin(Connection conn) {
		try {
			return !conn.getAutoCommit();
		} catch (SQLException e) {
			throw new UserSystemException(e);
		}
	}
	
	public boolean isClose(Connection conn) {
		try {
			return conn.isClosed();
		} catch (SQLException e) {
			throw new UserSystemException(e);
		}
	}
	
	/**
	 * showSnapshotInfo方法描述：显示连接池信息
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-12-5 下午03:36:25
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间) void
	 */
	private void showSnapshotInfo(String string,String alias){
		try {
			if (isShowConn()==true){
				SnapshotIF snapshot = ProxoolFacade.getSnapshot(alias,true);
				//获得活动连接数
				int currCount = snapshot.getActiveConnectionCount();
				//获得可得到的连接数
				int availableCount = snapshot.getAvailableConnectionCount();
				//获得总连接数
				int maxCount = snapshot.getMaximumConnectionCount();
				//当活动连接数变化时
				if(currCount != activeCount){
					System.out.println(string + ": active=" + currCount +
							" available=" + availableCount +
							" maximum=" + maxCount);
					activeCount=currCount;
				}
			}
		} catch (ProxoolException e) {
			throw new UserSystemException("显示连接池信息失败.");
		}
	}

}