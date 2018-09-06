package com.zhima.basic.jdbc.demo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.sql.BLOB;
  
 public class WriteAndReadFile {
     
     public static void main(String[] args){
         //1:先写入
        String path = "D://oracle2.zip";
         saveFile(path); 
         //2:再读出
        //getFile("1");
         
     }
  
     /**
      * 写入文件
     * 往blob里插入文件要先插入空值empty_blob()，再进行修改
     * @param filePath
      * @return
      */
     public static boolean saveFile(String filePath) {
         File file = new File(filePath);
         Connection conn = JdbcUtil.getConnection();
         try {
             java.sql.Statement st = conn.createStatement();
             conn.setAutoCommit(false);
             System.out.println("=====================save file begin========================");
 //          st.execute("insert into johnny_file values(1,'日常生活',empty_blob(),'这是一个很强大的文件',to_char(sysdate,'yyyy-MM-dd HH24:mi:ss'))");
             st.execute("insert into johnny_file values(2,'日常生活2',empty_blob(),'这是一个很强大的文件test',to_char(sysdate,'yyyy-MM-dd HH24:mi:ss'))");
             ResultSet rs = st
                     .executeQuery("select id,file_blob from johnny_file where id=2 for update");
             if (rs.next()) {
                 BLOB blob = (BLOB) rs.getBlob("file_blob");
                 OutputStream outStream = blob.getBinaryOutputStream();
                 InputStream fin = new FileInputStream(file);
                 byte[] b = new byte[blob.getBufferSize()];
                 int len = 0;
                 while ((len = fin.read(b)) != -1) {
                     outStream.write(b, 0, len);
                 }
                 fin.close();
                 outStream.flush();
                 outStream.close();
                 conn.commit();
                 conn.close();
             }
         } catch (SQLException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
             return false;
         } catch (FileNotFoundException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
             return false;
         } catch (IOException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
             return false;
         }
         System.out.println("=====================save file end========================");
         return true;
     }
  
     /**
      * 读取
     * 读取出的路径根据自己的需要修改
     * @param id
      */
     public static void getFile(String id) {
         Connection conn = JdbcUtil.getConnection();
         java.sql.Statement st;
         try {
             st = conn.createStatement();
             System.out.println("=====================get file begin========================");
             ResultSet rs = st
                     .executeQuery("select id,file_blob from johnny_file where id='"
                             + id + "'");
             if (rs.next()) {
                 BLOB blob = (BLOB) rs.getBlob("file_blob");
                 File file = new File("D://oracle.zip");
                 FileOutputStream output = new FileOutputStream(file);
                 InputStream input = blob.getBinaryStream();
                 byte[] buffer = new byte[1024];
                 int i = 0;
                 while ((i = input.read(buffer)) != -1) {
                     output.write(buffer, 0, i);
                 }
             }
             System.out.println("=====================get file end========================");
         } catch (Exception e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }
     }
  
     /**
      * 修改blob内容
     */
     public static void updateblob(String id) {
         Connection conn = JdbcUtil.getConnection();
         Statement stem = null;
         ResultSet rs = null;
         try {
             conn.setAutoCommit(false);
             stem = conn.createStatement();
             System.out.println("=====================update file begin========================");
             rs = stem.executeQuery("select file_blob from johnny_file where id='"
                     + id + "' for update");
  
             if (rs.next()) {
                 BLOB blob = (BLOB) rs.getBlob("file_blob");
                 OutputStream outStream = blob.getBinaryOutputStream();
                 InputStream fin = new FileInputStream("D://ok.zip");
                 byte[] b = new byte[blob.getBufferSize()];
                 int len = 0;
                 while ((len = fin.read(b)) != -1) {
                     outStream.write(b, 0, len);
                 }
                 fin.close();
                 outStream.flush();
                 outStream.close();
                 conn.commit();
                 conn.close();
             }
             System.out.println("=====================update file end========================");
         } catch (Exception ex) {
             try {
                 conn.rollback();
             } catch (SQLException e) {
                 // TODO Auto-generated catch block
                 e.printStackTrace();
             }
             System.out.println(ex.getMessage());
         }
     }
 }

 
