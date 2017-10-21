package tools;

import java.util.Properties;
import java.sql.*;

public final class JdbcUtil {

	private static String driver;
	private static String url;
	private static String user;
	private static String password;
	private static Properties pr=new Properties();
	
	
	
	private JdbcUtil(){}
	
	static {
		
		try{
			pr.load(JdbcUtil.class.getClassLoader().getResourceAsStream("db.properties"));
			driver=pr.getProperty("driver");
			url=pr.getProperty("url");
			user=pr.getProperty("username");
			password=pr.getProperty("password");
			Class.forName(driver);
		}catch(Exception e){
			throw new ExceptionInInitializerError(e);
		}
	}
	
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url,user,password);
	}
	
	public static void free(ResultSet rs,Statement st,Connection conn){
		try{
			if(rs!=null) rs.close();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			
			try{
				if(st!=null) st.close();
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				try{
					if(conn!=null) conn.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
			
			
		}
	}
	
}