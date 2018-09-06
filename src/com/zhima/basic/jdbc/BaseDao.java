package com.zhima.basic.jdbc;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.logicalcobwebs.proxool.ProxoolException;
import org.logicalcobwebs.proxool.ProxoolFacade;

import com.service.traffic.dao.SamLogDetailDao;
import com.zhima.basic.exception.UserBusinessException;
import com.zhima.basic.exception.UserSystemException;
import com.zhima.util.DateUtils;

/**
 * BaseDao概要说明：JDBC数据库操作
 * @author lcy
 */
public class BaseDao {
	/** 数据库类型 */
	public static String connType = "";
	/** 数据库连接 */
	private Connection conn;
	private static String alias;

	/** 预编译SQL语句对象 */
	private PreparedStatement prSt;
	/** 结果集 */
	private ResultSet reSt;
	/** 构造连接 */
	public BaseDao(Connection conn){
		try {
			this.conn = conn;
			DatabaseMetaData dameta = this.conn.getMetaData();
			connType = dameta.getDatabaseProductName();
			alias = ProxoolFacade.getAlias(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProxoolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * insert方法描述：添加数据
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-7-13 下午06:07:17
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param obj 对象
	 * @return String 主键
	 */
	protected String insert(Object obj,Map<String, Object> config){
		return insert(obj,true,config);
	}
	
	/**
	 * insertLog方法描述：添加日志用
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-7-13 下午06:09:42
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param obj 对象
	 * @return String 主键
	 */
	protected String insertLog(Object obj,Map<String, Object> config){
		return insert(obj,false,config);
	}

	/**
	 * insert方法描述：添加数据
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-7-13 下午06:04:37
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param obj 对象
	 * @param isLog 是否记录日志
	 * @return String 主键
	 */
	@SuppressWarnings("static-access")
	private String insert(Object obj,boolean isLog,Map<String, Object> config){
		String pkValue = null;
		OperObj operObj = new OperObj();
		try {
			//操作SQL
			StringBuffer fieldSql = new StringBuffer();
			fieldSql.append("insert into ");
			//赋值SQL
			StringBuffer valueSql = new StringBuffer();
			valueSql.append(" values (");
			
			operObj.toField(obj);
			String tabName = operObj.tabName;
			Param pk = operObj.fieldPK;
			List<Param> fields = operObj.fieldList;

			fieldSql.append(tabName+"(");
			//对象主键、附值
			String pkName = pk.getParamField();
			fieldSql.append(pkName+",");
			if(null != pk.getParamValue() && !"".equals(pk.getParamValue())){
				pkValue = pk.getParamValue();
			}else {
				List<String> pks = getPks(tabName,1,config);
				if (null != pks && pks.size()>0){
					pkValue = pks.get(0);
				}
			}
			if(pk.getParamType().equals("java.lang.Double")){
				valueSql.append(pkValue+",");
			}else{
				valueSql.append("'"+pkValue+"',");
			}
			//其他字段、附值
			for (int i = 0; i < fields.size(); i++) {
				Param field = fields.get(i);
				fieldSql.append(field.getParamField());
				valueSql.append("?");
				if((i+1) < fields.size()){
					fieldSql.append(","); 
					valueSql.append(",");
				}
			}
			fieldSql.append(")");
			valueSql.append(")");
			fieldSql.append(valueSql);
			Statement statement = null;
			if(connType.equals("Oracle")){
				//设置数据库为不自动提交
				if (null != operObj.blobList && operObj.blobList.size()>0){
					conn.setAutoCommit(false);
					statement = conn.createStatement();
				}
			}
			prSt = conn.prepareStatement(fieldSql.toString());
			for (int i = 0; i < fields.size(); i++) {
				Param field = fields.get(i);
				if(field.getParamType().equals("java.sql.Blob")){
					if(connType.equals("MySQL")){
				    	if (null != field.getBlobData()){
				    		InputStream stream = field.getBlobData().getBinaryStream();
							byte[] byes = new byte[stream.available()];
							stream.read(byes);	    	
					    	prSt.setBytes(i+1,byes);
						} else{
							prSt.setBytes(i+1,null);
						}
					}else if (connType.equals("Oracle")){
						prSt.setObject(i+1, oracle.sql.BLOB.empty_lob());
					}
				}else{
				    Object value = field.getParamType().valueOf(field.getParamValue());
				    if("".equals(value)){
						if(connType.equals("MySQL")){
							value = null;
						}
				    }
			    	prSt.setObject(i+1,value);
				}
			}
			prSt.executeUpdate();
			writeBlob(statement, operObj, tabName, pkName, pkValue);
			//数据添加记录日志
			if (isLog == true){
				SamLogDetailDao samLogDataDao = new SamLogDetailDao(conn);
				samLogDataDao.insertDataLog(obj,pkValue,config);
			}
		} catch (SQLException e) {
			throw new UserSystemException("数据添加出错。\r\n" + e.getMessage());
		} catch (IOException e) {
			throw new UserSystemException(e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new UserSystemException(e.getMessage());
		} finally {
			operObj = null;
			close();
		}
		return pkValue;
	}
	
	/**
	 * update方法描述：更新数据库记录
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-12-6 下午04:25:18
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param obj 更新的对象
	 */
	@SuppressWarnings("static-access")
	protected void update(Object obj,Map<String, Object> config){
		OperObj operObj = new OperObj();
		try {
			//操作SQL
			StringBuffer updateSql = new StringBuffer();
			updateSql.append("update ");
			operObj.toField(obj);
			String tabName = operObj.tabName;
			Param pk = operObj.fieldPK;
			List<Param> fields = operObj.fieldList;
			//获取数据表名
			updateSql.append(tabName+" set ");
			//更新字段、附值
			for(int i=0;i<fields.size();i++){
				Param field = fields.get(i);
				String fieldName = field.getParamField();
				updateSql.append(fieldName);
				updateSql.append("=?");
				if((i+1) < fields.size()){
					updateSql.append(",");
				}
			}
			//主键条件
			updateSql.append(" where "+pk.getParamField());
			updateSql.append("='"+String.valueOf(pk.getParamValue())+"'");
			Statement statement = null;
			if(connType.equals("Oracle")){
				//设置数据库为不自动提交
				if (null != operObj.blobList && operObj.blobList.size()>0){
					conn.setAutoCommit(false);
					statement = conn.createStatement();
				}
			}
			prSt = conn.prepareStatement(updateSql.toString());
			for(int i=0;i<fields.size();i++){
				Param field = fields.get(i);
				if(field.getParamType().equals("java.sql.Blob")){
					if(connType.equals("MySQL")){
						if (null != field.getBlobData()){
							InputStream stream = field.getBlobData().getBinaryStream();
							byte[] byes = new byte[stream.available()];
							stream.read(byes);	    	
					    	prSt.setBytes(i+1,byes);
						} else{
							prSt.setBytes(i+1,null);
						}
					}else{
						prSt.setObject(i+1, oracle.sql.BLOB.empty_lob());
					}
				}else{
					Object value = field.getParamType().valueOf(field.getParamValue());
				    if("".equals(value)){
				    	if(connType.equals("MySQL")){
							value = null;
						}
				    }
				    prSt.setObject(i+1,value);
				}
			}
			//数据修改记录日志
			SamLogDetailDao samLogDataDao = new SamLogDetailDao(conn);
			samLogDataDao.updateDataLog(pk.getParamField(),String.valueOf(pk.getParamValue()),obj,config);
			prSt.executeUpdate();
			writeBlob(statement, operObj, tabName, pk.getParamField(), pk.getParamValue());
		} catch (SQLException e) {
			throw new UserSystemException("数据更新出错。\r\n" + e.getMessage());
		} catch (IOException e) {
			throw new UserSystemException(e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new UserSystemException(e.getMessage());
		} finally {
			operObj = null;
			close();
		}
	}
	
	/**
	 * writeBlob方法描述：Oracle二进制数据写入
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-7-20 上午09:40:43
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param statement
	 * @param operObj　操作对象
	 * @param tabName　表名
	 * @param pkName　记录主键名
	 * @param pkValue 记录主键值
	 */
	public void writeBlob(Statement statement,OperObj operObj,String tabName, String pkName, String pkValue){
		try {
			if (connType.equals("Oracle")){
				if (null != operObj.blobList && operObj.blobList.size()>0){
					String sql = "";
					for (int i = 0; i < operObj.blobList.size(); i++) {
						Param field = operObj.blobList.get(i);
						if (i==0){
							sql =field.getParamField();
						}else{
							sql +="," + field.getParamField();
						}
					}
					sql = "select " + sql + " from " + tabName + " where " + pkName + " = '"  + pkValue + "' for update";
					reSt = statement.executeQuery(sql);   
			        if (reSt.next()) {
			        	for (int i = 0; i < operObj.blobList.size(); i++) {
							Param field = operObj.blobList.get(i);
							if (null != field.getBlobData()){
								//得到BLOB  
					            oracle.sql.BLOB blob = (oracle.sql.BLOB) reSt.getBlob(field.getParamField());   
					            //从得到的低级流构造一个高级流   
					            PrintStream ps = new PrintStream(blob.getBinaryOutputStream());   
					            BufferedInputStream bis = new BufferedInputStream(field.getBlobData().getBinaryStream());   
					            InputStream stream = field.getBlobData().getBinaryStream();
					            byte[] buff = new byte[stream.available()];
					            if (stream.available()>0){
					            	int n = 0;   
						            //从输入到输出   
									while ((n = bis.read(buff)) != -1) {   
									    ps.write(buff, 0, n);   
									}
					            }
					            //清空流的缓存   
					            ps.flush();   
					            //关闭流，注意一定要关   
					            ps.close();   
					            bis.close();  
							}
						}
			        }
			        statement.close();
				}
			}
		} catch (IOException e) {
			throw new UserSystemException(e.getMessage());
		} catch (SQLException e) {
			throw new UserSystemException(e.getMessage());
		}   
	}
	
	/**
	 * queryCount方法描述：查询记录数
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-12-6 上午09:45:38
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param sql SQL语句
	 * @param params 执行条件
	 * @return Integer 查询结果记录数
	 */
	@SuppressWarnings("rawtypes")
	public Integer queryCount(String sql, List params){
		Integer rowCount = 0;
		try {
			prSt = conn.prepareStatement(sql);
			if(null != params && params.size()>0){
				for (int i = 0; i < params.size(); i++) {
					prSt.setObject(i+1,params.get(i));
				}
			}
			reSt = prSt.executeQuery();
			while(reSt.next()){
				rowCount =reSt.getInt(1);
			}
			return rowCount;
		} catch (SQLException e) {
			throw new UserSystemException("数据查询出错。\r\n" + e.getMessage());
		} finally {
			close();
		}
	}
	
	@SuppressWarnings("rawtypes")
	public String queryString(String sql, List params){
		String string = "";
		try {
			prSt = conn.prepareStatement(sql);
			if(null != params && params.size()>0){
				for (int i = 0; i < params.size(); i++) {
					prSt.setObject(i+1,params.get(i));
				}
			}
			reSt = prSt.executeQuery();
			while(reSt.next()){
				string =reSt.getString(1);
			}
			return string;
		} catch (SQLException e) {
			throw new UserSystemException("数据查询出错。\r\n" + e.getMessage());
		} finally {
			close();
		}
	}
	
	/**
	 * queryAll方法描述：根据查询条件查询所有数据
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-12-5 下午05:19:44
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param sql SQL语句
	 * @param params 执行条件
	 * @param obj 转换的对象
	 * @return List<?> 查询结果对象集合
	 */
	@SuppressWarnings("rawtypes")
	public List<?> queryAll(String sql, List params, Object obj){
		OperObj operObj = new OperObj();
		try {
			prSt = conn.prepareStatement(sql);
			if(null != params && params.size()>0){
				for (int i = 0; i < params.size(); i++) {
					prSt.setObject(i+1,params.get(i));
				}
			}
			reSt = prSt.executeQuery();
			List<?> list = operObj.toObject(obj, reSt);
			return list;
		} catch (SQLException e) {
			throw new UserSystemException("数据查询出错。\r\n" + e.getMessage());
		} finally {
			operObj = null;
			close();
		}
	}
	
	/**
	 * queryPage方法描述：分页查询
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-12-6 上午10:43:07
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param sql SQL语句
	 * @param params 执行条件
	 * @param obj 转换的对象
	 * @param start 分页开始行号
	 * @param limit 分页结束行号
	 * @return List<?> 查询结果对象集合
	 */
	@SuppressWarnings("rawtypes")
	public List<?> queryPage(String sql, List params, Object obj,
			int start,int limit){
		StringBuffer querySql = null;
		OperObj operObj = new OperObj();
		 try {
			if(connType.equals("MySQL")){
				querySql = new StringBuffer(sql);
				querySql.append(" limit ");
				querySql.append(start);
				querySql.append(",");
				querySql.append(limit);
			}
			if(connType.equals("Oracle")){
				int startIndex = 0;
				int endIndex = 0;
				startIndex = start+1;
				endIndex = start+limit;
				querySql = new StringBuffer();
				querySql.append("select * from (");
				querySql.append("select rownum as ronum,tab.* from (");
				querySql.append(sql);
				querySql.append(") tab) where ronum between ");
				querySql.append(startIndex+" and " + endIndex);
			}
			prSt = conn.prepareStatement(querySql.toString());
			if(null != params && params.size()>0){
				for (int i = 0; i < params.size(); i++) {
					prSt.setObject(i+1,params.get(i));
				}
			}
			reSt = prSt.executeQuery();
			List<?> list = operObj.toObject(obj, reSt);
			return list;
		} catch (SQLException e) {
			throw new UserSystemException("数据查询出错。\r\n" + e.getMessage());
		} finally {
			operObj = null;
			close();
		}
	}
	
	/**
	 * updateSQL方法描述：执行SQL语句
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-12-6 上午10:54:12
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param sql SQL语句
	 * @param params 执行条件
	 * @return int 记录数.
	 */
	@SuppressWarnings("rawtypes")
	public int executeSql(String sql, List params){
		int count;
		try {
			//操作SQL
			prSt = conn.prepareStatement(sql);
			if(null!=params&&params.size()>0){
				for (int i = 0; i < params.size(); i++) {
					prSt.setObject(i+1, params.get(i));
				}
			}
			count = prSt.executeUpdate();
		} catch (SQLException e) {
			throw new UserSystemException("数据更新出错。\r\n" + e.getMessage());
		} finally {
			close();
		}
		return count;
	}
	
	/**
	 * Close方法描述：关闭连接
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-12-6 上午10:05:18
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间) void
	 */
	private void close() {
		try {
			if (reSt != null) {
				reSt.close();
			}
			if (prSt != null) {
				prSt.close();
			}
		} catch (SQLException e) {
			throw new UserSystemException("关闭连接出错。\r\n" + e.getMessage());
		}
	}
	
	/**
	 * getPks方法描述：获取数据库主键信息
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-12-7 上午11:25:13
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param tableName 数据库名
	 * @param count 主键数量
	 * @return List<String> 主键信息
	 */
	public static synchronized List<String> getPks(String tableName,int count,Map<String, Object> config){
		Connection conn = PoolHandler.pool.getConnection(alias);
		List<String> seqs = new ArrayList<String>();
		//数据操作
		try {
			PoolHandler.pool.beginConn(conn);
			String organizeSeq = config.get("organizeSeq").toString();
			String SQL_GETSEQ = "select seq_rules,seq_len,seq_value from sam_tableseq where organize_seq = ? and table_name = ? for update";
			String SQL_INSERT = "insert into sam_tableseq (organize_seq,table_name,seq_rules,seq_len,seq_value,create_time,update_time) values(?,?,?,?,?,?,?)";
			String SQL_UPDATE = "update sam_tableseq set seq_value= ? where organize_seq = ? and table_name=?";
			PreparedStatement pstmt = null;
			
			pstmt = conn.prepareStatement(SQL_GETSEQ);
			pstmt.setString(1, organizeSeq);
			pstmt.setString(2, tableName);
			ResultSet re = pstmt.executeQuery();
			String seqRules ="unit";
			Integer intLen = 30;
			Integer intSeq = 0;
			String currTime = DateUtils.getNow(DateUtils.FORMAT_LONG);
			if(re.next()){
				//数据库存在最大ID记录 ,并将最大序号更新回数据库中
				seqRules = re.getString(1);
				intLen = Integer.valueOf(re.getString(2));
				intSeq = Integer.valueOf(re.getString(3));
			    pstmt = conn.prepareStatement(SQL_UPDATE);
			    pstmt.setString(1, String.valueOf(intSeq + count));
			    pstmt.setString(2, organizeSeq);
			    pstmt.setString(3, tableName);
			    pstmt.executeUpdate();
			}else{
				//数据库不存在最大ID记录 就添加一个初始值 
				pstmt = conn.prepareStatement(SQL_INSERT);
				pstmt.setString(1, organizeSeq);
				pstmt.setString(2, tableName);
				pstmt.setString(3, seqRules);
				pstmt.setString(4, String.valueOf(intLen));
			    pstmt.setString(5, String.valueOf(count));
			    pstmt.setString(6, currTime);
			    pstmt.setString(7, currTime);
			    pstmt.executeUpdate();
			}
			conn.commit();
			String seqMark = "";
			String seqValue = "";
			String pkRules[] = seqRules.split(";");
			if (null != pkRules && pkRules.length>0){
				for (int i = 0; i < pkRules.length; i++) {
					if (pkRules[i].equals("unit")){
						if (null != config){
							String unit = config.get("organizeCode").toString();
							if (null != unit && unit.length()>0){
								seqMark += unit;
							}
						}
					} else if (pkRules[i].equals("user")){
						String user = config.get("userCode").toString();
						if (null != user && user.length()>0){
							seqMark += user;
						}
					} else {
						seqMark += DateUtils.getNow(pkRules[i]);
					}
				}
			}
			for (int i = 0; i < count; i++) {
				seqValue = getSeq(seqMark,intLen,intSeq+(i+1));
				seqs.add(seqValue);
			}
		} catch (Exception e) {
			e.printStackTrace();
			PoolHandler.pool.rolbackConn(conn);
			throw new UserSystemException("数据库表 " + tableName + " 生成主键失败。\r\n" + e.getMessage());
		}finally{
			PoolHandler.pool.freeConnection(conn);
		}
		return seqs;
	}
	
	/**
	 * getSeq方法描述：生成主键
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2012-12-7 下午09:52:44
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param pkMark 主键标识
	 * @param nextNo 主键编号
	 * @return String 可用主键
	 * @throws UserBusinessException
	 */
	private static synchronized String getSeq(String seqMark,int seqLen,int nextNo) throws UserSystemException {
		Integer count=(seqMark+nextNo).length();
		String tableSeq=null;
		if(count<=seqLen){
			tableSeq=seqMark+String.format("%0" + String.valueOf(seqLen-seqMark.length()) +"d", nextNo);
		}else if(count<=30){
			tableSeq=seqMark+nextNo;
		}else{
			throw new UserSystemException("主键编号大于" + seqLen + "位，请更改主键长度。");
		}
		return tableSeq;
	}
	
}
