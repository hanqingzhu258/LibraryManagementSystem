package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import tools.JdbcUtil;

import com.mysql.jdbc.PreparedStatement;

import beans.UB;

public class UBDao {

	// 用户借书
	public void borrowBooks(UB ub) throws Exception {

		Connection conn = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();

			String sql = "select bookCount from ub where userId=? and bookId=?";
			ps1 = (PreparedStatement) conn.prepareStatement(sql);
			ps1.setInt(1, ub.getUserId());
			ps1.setInt(2, ub.getBookId());
			rs = ps1.executeQuery();
			if (rs.next()) {
				sql = "update ub set bookCount=? where userId=? and bookId=?";
				ps2 = (PreparedStatement) conn.prepareStatement(sql);
				ps2.setInt(1, rs.getInt(1) + ub.getBookCount());
				ps2.setInt(2, ub.getUserId());
				ps2.setInt(3, ub.getBookId());
				ps2.executeUpdate();
			} else {
				sql = "insert into ub values(null,?,?,?,?)";
				ps3 = (PreparedStatement) conn.prepareStatement(sql);
				ps3.setInt(1, ub.getUserId());
				ps3.setInt(2, ub.getBookId());
				ps3.setString(3, ub.getBookName());
				ps3.setInt(4, ub.getBookCount());
				ps3.executeUpdate();
			}
		} finally {
			JdbcUtil.free(rs, ps1, conn);
			JdbcUtil.free(null, ps2, conn);
			JdbcUtil.free(null, ps3, conn);
		}

	}

	// 用户换书
	public int returnBooks(UB ub) throws Exception {

		Connection conn = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3=null;
		ResultSet rs = null;
		int userTotalBook = 0;
		int isSuccess = 0;
		try {
			conn = JdbcUtil.getConnection();
			String sql = "select bookCount from ub where userId=? and bookId=?";
			ps1 = (PreparedStatement) conn.prepareStatement(sql);
			ps1.setInt(1, ub.getUserId());
			ps1.setInt(2, ub.getBookId());
			rs = ps1.executeQuery();
			if (rs.next()) {
				userTotalBook = rs.getInt(1);
				if (userTotalBook < ub.getBookCount()) {

				} else if(userTotalBook>ub.getBookCount()) {
					sql = "update ub set bookCount=? where userId=? and bookId=?";
					ps2 = (PreparedStatement) conn.prepareStatement(sql);
					ps2.setInt(1, rs.getInt(1) - ub.getBookCount());
					ps2.setInt(2, ub.getUserId());
					ps2.setInt(3, ub.getBookId());
					ps2.executeUpdate();
					isSuccess = 1;
				}else{
					sql="delete from ub where userId=? and bookId=?";
					ps3=(PreparedStatement)conn.prepareStatement(sql);
					ps3.setInt(1, ub.getUserId());
					ps3.setInt(2, ub.getBookId());
					ps3.executeUpdate();
					isSuccess=1;
				}
				
			} else {
				System.out.println("警告！！！还书过程中出现错误！！！！");
				return isSuccess;
			}
		} finally {
			JdbcUtil.free(rs, ps1, conn);
			JdbcUtil.free(null, ps2, conn);
		}
		return isSuccess;
	}

	// 统计用户所借图书的总量
	public int getUserAllBooksCount(int userId) throws Exception {

		int totalCount = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			String sql = "select bookCount from ub where userId=?";
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			while (rs.next()) {
				totalCount += rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.free(rs, ps, conn);
		}
		return totalCount;
	}

	// 获取用户所借的所有书籍
	public List<UB> getUserAllBooks(int userId) throws Exception {

		List<UB> ubs = new ArrayList<UB>();
		UB ub = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = JdbcUtil.getConnection();
			String sql = "select * from ub where userId=? and bookCount<>0";
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			while (rs.next()) {
				ub = new UB();
				ub.setUserId(rs.getInt(2));
				ub.setId(rs.getInt(1));
				ub.setBookId(rs.getInt(3));
				ub.setBookName(rs.getString(4));
				ub.setBookCount(rs.getInt(5));
				ubs.add(ub);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.free(rs, ps, conn);
		}
		return ubs;
	}

}
