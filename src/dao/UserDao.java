package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tools.JdbcUtil;

import com.mysql.jdbc.PreparedStatement;

import beans.User;

public class UserDao {

	//增加用户
	public void add(User user) throws Exception{
		Connection conn=null;
		PreparedStatement ps=null;
		try{
			conn=JdbcUtil.getConnection();
			String sql="insert into user values(null,?,?,0)";
			ps=(PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassword());
			ps.executeUpdate();
		}finally{
			JdbcUtil.free(null, ps, conn);
		}
	}
	
	//更新用户
	public void update(User user) throws Exception{
		Connection conn=null;
		PreparedStatement ps=null;
		try{
			conn=JdbcUtil.getConnection();
			String sql ="update user set userName=?,password=? where userId=?";
			ps=(PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassword());
			ps.setInt(3, user.getUserId());
			ps.executeUpdate();
		}finally{
			JdbcUtil.free(null, ps, conn);
		}
		
	}
	
	//删除用户
	public int delete(int userId) throws Exception{
		Connection conn=null;
		PreparedStatement ps=null;
		int isSuccess=0;
		try{
			conn=JdbcUtil.getConnection();
			String sql="delete from user where userId=?";
			ps=(PreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.executeUpdate();
			isSuccess=1;
		}finally{
			JdbcUtil.free(null, ps, conn);
		}
		return isSuccess;
	}
	
	//按userId查询用户
	public User findUserById(int userId) throws Exception{
		User user=null;
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			conn=JdbcUtil.getConnection();
			String sql="select * from user where userId=?";
			ps=(PreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, userId);
			
			rs=ps.executeQuery();
			if(rs.next()){
				user =new User();
				user.setUserId(rs.getInt(1));
				user.setUserName(rs.getString(2));
				user.setPassword(rs.getString(3));
			}
			
		}finally{
			JdbcUtil.free(rs, ps, conn);
		}
		return user;
	}
	
	//查询所有的用户
	public List<User> getAll() throws Exception{
		
		List<User> users=new ArrayList<User>();
		User user=null;
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try{
			conn=JdbcUtil.getConnection();
			String sql="select * from user where isAdmin=0";
			ps=(PreparedStatement) conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				user=new User();
				user.setUserId(rs.getInt(1));
				user.setUserName(rs.getString(2));
				user.setPassword(rs.getString(3));
				users.add(user);
			}
		}finally{
			JdbcUtil.free(rs, ps, conn);
		}
		return users;
	}
	
	//获取用户总数
	public int getUserTotalCount(){
		
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rSet=null;
		int totalCount=0;
		try{
			conn=JdbcUtil.getConnection();
			String sql="select count(*) from user where isAdmin=0";
			ps=(PreparedStatement) conn.prepareStatement(sql);
			rSet=ps.executeQuery();
			while(rSet.next()){
				totalCount=rSet.getInt(1);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			JdbcUtil.free(null, ps, conn);
		}
		
		return totalCount;
	}
	
	//分页查询用户
	public List<User> queryUserPageByPage(int startPage,int pageSize){
		
		List<User> users=new ArrayList<User>();
		
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			conn=JdbcUtil.getConnection();
			String sql="select * from user where isAdmin=0 limit ?,?";
			ps=(PreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, startPage*pageSize);
			ps.setInt(2, pageSize);
			rs=ps.executeQuery();
			while(rs.next()){
				
				User user=new User();
				user.setUserId(rs.getInt(1));
				user.setUserName(rs.getString(2));
				user.setPassword(rs.getString(3));
				users.add(user);
				
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			JdbcUtil.free(rs, ps, conn);
		}
		return users;
	}
	
	
	//判断注册用户名是否存在
	public boolean ifUserNameOk(String userName) throws Exception{
		
		Connection connection=null;
		PreparedStatement psPreparedStatement=null;
		ResultSet rSet=null;
		boolean ok=false;
		
		try{
			connection=JdbcUtil.getConnection();
			String sql="select * from user where userName=?";
			psPreparedStatement=(PreparedStatement) connection.prepareStatement(sql);
			psPreparedStatement.setString(1,userName);
			rSet=psPreparedStatement.executeQuery();
			if(rSet.next()){}
			else{
				ok=true;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JdbcUtil.free(rSet, psPreparedStatement, connection);
		}
		return ok;
	}
	
	//判断用户是否存在
	public int ifUserExist(User user)throws Exception {
		
		Connection connection=null;
		PreparedStatement psPreparedStatement=null;
		ResultSet resultSet=null;
		int exist=0;
		
		try{
			connection=JdbcUtil.getConnection();
			String sql="select * from user where userName=? and password=? and isAdmin=?";
			psPreparedStatement=(PreparedStatement) connection.prepareStatement(sql);
			psPreparedStatement.setString(1, user.getUserName());
			psPreparedStatement.setString(2, user.getPassword());
			psPreparedStatement.setInt(3, user.getIsAdmin());
			resultSet=psPreparedStatement.executeQuery();
			if(resultSet.next()){
				exist= 1;
			}else{}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JdbcUtil.free(resultSet, psPreparedStatement, connection);
		}
		return exist;
	}
	
	public int getLoginUserId(User user)throws Exception{
		Connection connection=null;
		PreparedStatement psPreparedStatement=null;
		ResultSet resultSet=null;
		int userId=0;
		try{
			connection=JdbcUtil.getConnection();
			String sql="select * from user where userName=? and password=? and isAdmin=?";
			psPreparedStatement=(PreparedStatement) connection.prepareStatement(sql);
			psPreparedStatement.setString(1, user.getUserName());
			psPreparedStatement.setString(2, user.getPassword());
			psPreparedStatement.setInt(3, user.getIsAdmin());
			resultSet=psPreparedStatement.executeQuery();
			if(resultSet.next()){
			
				userId=resultSet.getInt(1);
				
			}else{}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JdbcUtil.free(resultSet, psPreparedStatement, connection);
		}
		return userId;
	}
}
