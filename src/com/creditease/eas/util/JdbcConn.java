package com.creditease.eas.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcConn {
	
	private static Connection conn = null;
	private static Statement stmt = null;
	private static ResultSet rs = null;	
	private static PreparedStatement pstmt = null;
	public static final String EAS02 = "eas02.properties";//金蝶库链接
	public static final String EASEXT = "easext.properties";//测试库链接

	 public static Properties getProperties(String strs){   
	        Properties props = new Properties();   
	        InputStream is = null;   
	       // String dd = JdbcConn.class.getClass().getClassLoader().getResource("src/db.properties").getPath();
	        try{
	        	//System.err.println(dd);
	            File file = new File("src/"+strs);     
	            is = new FileInputStream(file);    
	            props.load(is);   
	        }catch(Exception ex){   
	            ex.printStackTrace();   
	            return null;   
	        }finally{   
	            if(is != null){   
	                try {   
	                    is.close();   
	                } catch (IOException e) {   
	                    e.printStackTrace();   
	                }   
	            }   
	        }   
	        return props;   
	    }
	 

	 
	 public static Connection JdbcConnection(String strs){
	    	Properties props = getProperties(strs);  
	    	if(props!=null){   
	    		String driver = props.getProperty("jdbc.driverClassName");   
	            String url = props.getProperty("jdbc.url");   
	            String user = props.getProperty("jdbc.username");   
	            String password = props.getProperty("jdbc.password"); 
		    	try {
		    		
		    		Class.forName(driver);          //加载驱动    
		    		conn = DriverManager.getConnection(url, user, password);
		    	} catch (SQLException e) {
		    		System.out.println("连接失败");
		    		e.printStackTrace();
				} catch (ClassNotFoundException e) {
					System.out.println("加载驱动  失败");
					e.printStackTrace();
				}
	    	}
			
	    	return conn;
	    }
	 
		//获取预处理语句执行对象
		public static PreparedStatement getPreparedStatement(String sql) {
			try {
				conn = JdbcConn.JdbcConnection(EASEXT);
				pstmt = conn.prepareStatement(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return pstmt;
		}

	public static ResultSet queryResult(String db,String sql) {
		try {
			conn = JdbcConnection(db);
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	public static void ExcuteUpdate(String db,String sql){
		
		try{
			 conn=JdbcConnection(db);
			 stmt=conn.createStatement();
			 stmt.executeUpdate(sql);
			 
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(stmt!=null){stmt.close();}
				if(conn!=null){conn.close();}
				}catch(Exception e){}
}
	}
	
	
	
	public static void colseConn() {
		try {
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		conn = JdbcConn.JdbcConnection(EAS02);
    	System.out.println(conn);

	}
}
