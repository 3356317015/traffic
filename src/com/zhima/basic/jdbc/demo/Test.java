package com.zhima.basic.jdbc.demo;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
   
public class Test {   
    private Connection conn;   
   
       
    public Connection getConnection() {   
        try {   
            Class.forName("oracle.jdbc.driver.OracleDriver");   
            conn = DriverManager.getConnection(   
                    "jdbc:oracle:thin:@192.168.1.126:1521:webapp", "system", "oracle");   
        } catch (ClassNotFoundException e) {   
            // TODO Auto-generated catch block   
            e.printStackTrace();   
        } catch (SQLException e) {   
            // TODO Auto-generated catch block   
            e.printStackTrace();   
        }   
        return conn;   
    }   
   
       
       
   
    public int insertImage(String path) throws Exception {   
        int i = 0;   
        Statement st = null;   
        ResultSet rs = null;   
        conn=this.getConnection();   
           
        conn.setAutoCommit(false);//设置数据库为不自动提交，必须的一步   
        st = conn.createStatement();   
        //先插入一个空对象，这里我调用了Empty_BLOB()函数  
        String sql  = "insert into image (id,image) values ('1',?)";
        PreparedStatement prSt = conn.prepareStatement(sql);
        prSt.setObject(1, oracle.sql.BLOB.empty_lob());
        prSt.executeUpdate();
       // i = st.executeUpdate("insert into image (id,image) values ('1',Empty_BLOB())");   
        //以行的方式锁定   
        rs = st.executeQuery("select image from image where id=(select max(id) from image) for update");   
        if (rs.next()) {   
            //得到流   
            oracle.sql.BLOB blob = (oracle.sql.BLOB) rs.getBlob(1);   
            //从得到的低级流构造一个高级流   
            PrintStream ps = new PrintStream(blob.getBinaryOutputStream());   
            BufferedInputStream bis = new BufferedInputStream(   
                    new FileInputStream(path));   
            byte[] buff = new byte[1024];   
            int n = 0;   
            //从输入到输出   
            while ((n = bis.read(buff)) != -1) {   
                ps.write(buff, 0, n);   
   
            }   
            //清空流的缓存   
            ps.flush();   
            //关闭流，注意一定要关   
            ps.close();   
            bis.close();   
        }   
        rs.close();   
        st.close();   
        conn.close();   
        return i;   
    }   
   
    public static void main(String[] args) throws Exception {   
        Test test=new Test();   
        test.insertImage("e:\\test.gif");   
        System.out.println("OK");   
   
   
    }   
   
} 