package com.zhima.basic.jdbc.demo;
  
 import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
  
 public class JdbcUtil {
     /**
      * driverName
      * */
     static{
         String driverName="oracle.jdbc.driver.OracleDriver";
         try{
             Class.forName(driverName);
         }catch(Exception e){
             e.printStackTrace();
         }
     }
     
     /**
      * getConnection
      * */
     public static Connection getConnection(){
         String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
         String userName="XXXXX";
         String pwd="XXXXXX";
         Connection con = null;
         try{
             con = DriverManager.getConnection(url,userName,pwd);
         }catch(Exception ee){
             ee.printStackTrace();
         }
         return con;
     }
     
     /**
      * close
      * */
     public static void close(ResultSet rs,Statement stmt,Connection con){
         try{
             if(rs!=null){rs.close();}
         }catch(Exception ee){
             ee.printStackTrace();
         }
         try{
             if(stmt!=null){stmt.close();}
         }catch(Exception ee){
             ee.printStackTrace();
         }
         try{
             if(con!=null){con.close();}
         }catch(Exception ee){
             ee.printStackTrace();
         }
     }
     
 }