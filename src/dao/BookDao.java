package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tools.JdbcUtil;

import com.mysql.jdbc.PreparedStatement;

import beans.Book;

public class BookDao {

	//增加书籍
	public int add(Book book) throws Exception{
		
		Connection connection=null;
		PreparedStatement pStatement=null;
		int isSuccess=0;
		try{
			connection=JdbcUtil.getConnection();
			String sql="insert into book values(null,?,?,?,?,?,?,?)";
			pStatement=(PreparedStatement) connection.prepareStatement(sql);
			pStatement.setString(1, book.getBookNo());
			pStatement.setString(2, book.getBookName());
			pStatement.setString(3, book.getBookAuthor());
			pStatement.setString(4, book.getBookPrice());
			pStatement.setString(5, book.getBookInfo());
			pStatement.setInt(6, book.getBookTotalCount());
			pStatement.setInt(7, book.getBookBorrowedCount());
			pStatement.executeUpdate();
			isSuccess=1;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return isSuccess;
	}
	
	//更新书籍
		public int update(Book book) throws Exception{
			
			/*System.out.println("the method update is being used!!!");*/
			
			Connection conn=null;
			PreparedStatement ps=null;
			int isSuccess=0;
			try{
				conn=JdbcUtil.getConnection();
				String sql ="update book set bookNo=?, bookName=?,bookAuthor=? ,bookPrice=?,bookInfo=?,bookTotalCount=? ,bookBorrowedCount=? where bookId=?";
				ps=(PreparedStatement) conn.prepareStatement(sql);
				ps.setString(1, book.getBookNo());
				ps.setString(2, book.getBookName());
				ps.setString(3,book.getBookAuthor());
				ps.setString(4,book.getBookPrice());
				ps.setString(5,book.getBookInfo());
				ps.setInt(6, book.getBookTotalCount());
				ps.setInt(7, book.getBookBorrowedCount());
				ps.setInt(8, book.getBookId());
				isSuccess=ps.executeUpdate();
			}finally{
				JdbcUtil.free(null, ps, conn);
			}
			return isSuccess;
		}
		
		//删除书籍
		public int delete(int bookId) throws Exception{
			Connection conn=null;
			PreparedStatement ps=null;
			int isSuccess=0;
			try{
				conn=JdbcUtil.getConnection();
				String sql="delete from book where bookId=?";
				ps=(PreparedStatement) conn.prepareStatement(sql);
				ps.setInt(1, bookId);
				ps.executeUpdate();
				isSuccess=1;
			}finally{
				JdbcUtil.free(null, ps, conn);
			}
			return isSuccess;
		}
		
		//按bookId查询书籍
		public Book findBookById(int userId) throws Exception{
			Book book=null;
			Connection conn=null;
			PreparedStatement ps=null;
			ResultSet rs=null;
			try{
				conn=JdbcUtil.getConnection();
				String sql="select * from book where bookId=?";
				ps=(PreparedStatement) conn.prepareStatement(sql);
				ps.setInt(1, userId);
				
				rs=ps.executeQuery();
				if(rs.next()){
					book=new Book();
					book.setBookId(rs.getInt(1));
					book.setBookNo(rs.getString(2));
					book.setBookName(rs.getString(3));
					book.setBookAuthor(rs.getString(4));
					book.setBookPrice(rs.getString(5));
					book.setBookInfo(rs.getString(6));
					book.setBookTotalCount(rs.getInt(7));
					book.setBookBorrowedCount(rs.getInt(8));
				}
				
			}finally{
				JdbcUtil.free(rs, ps, conn);
			}
			return book;
		}
		
		//查询所有的书籍
		public List<Book> getAll() throws Exception{
			
			List<Book> books=new ArrayList<Book>();
			Book book=null;
			Connection conn=null;
			PreparedStatement ps=null;
			ResultSet rs=null;
			
			try{
				conn=JdbcUtil.getConnection();
				String sql="select * from book";
				ps=(PreparedStatement) conn.prepareStatement(sql);
				rs=ps.executeQuery();
				while(rs.next()){
					book=new Book();
					book.setBookId(rs.getInt(1));
					book.setBookNo(rs.getString(2));
					book.setBookName(rs.getString(3));
					book.setBookAuthor(rs.getString(4));
					book.setBookPrice(rs.getString(5));
					book.setBookInfo(rs.getString(6));
					book.setBookTotalCount(rs.getInt(7));
					book.setBookBorrowedCount(rs.getInt(8));
					books.add(book);
				}
			}finally{
				JdbcUtil.free(rs, ps, conn);
			}
			return books;
		}
		
		//获取书籍总数
		public int getBookTotalCount(){
			
			Connection conn=null;
			PreparedStatement ps=null;
			ResultSet rs=null;
			int totalCount=0;
			try{
				conn=JdbcUtil.getConnection();
				String sql="select count(*) from book";
				ps=(PreparedStatement) conn.prepareStatement(sql);
				rs=ps.executeQuery();
				while(rs.next()){
					totalCount=rs.getInt(1);
				}
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				JdbcUtil.free(null, ps, conn);
			}
			
			return totalCount;
		}
		
		//分页查询书籍
		public List<Book> queryBookPageByPage(int startPage,int pageSize){
			
			List<Book> books=new ArrayList<Book>();
			Book book=null;
			Connection conn=null;
			PreparedStatement ps=null;
			ResultSet rs=null;
			try{
				conn=JdbcUtil.getConnection();
				String sql="select * from book  limit ?,?";
				ps=(PreparedStatement) conn.prepareStatement(sql);
				ps.setInt(1, startPage*pageSize);
				ps.setInt(2, pageSize);
				rs=ps.executeQuery();
				while(rs.next()){
					book=new Book();
					book.setBookId(rs.getInt(1));
					book.setBookNo(rs.getString(2));
					book.setBookName(rs.getString(3));
					book.setBookAuthor(rs.getString(4));
					book.setBookPrice(rs.getString(5));
					book.setBookInfo(rs.getString(6));
					book.setBookTotalCount(rs.getInt(7));
					book.setBookBorrowedCount(rs.getInt(8));
					books.add(book);
					
				}
				
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				JdbcUtil.free(rs, ps, conn);
			}
			return books;
		}
		
		//模糊查询书籍
		public List<Book> getBooksByCondition(String condition){
			
			Connection connection=null;
			PreparedStatement preparedStatement=null;
			ResultSet resultSet=null;
			List<Book> books=new ArrayList<Book>();
			Book book=null;
			
			try{
				connection=JdbcUtil.getConnection();
				String sqlString="select * from book where bookNo like ? or bookName like ? or bookAuthor like ? or bookInfo like ? ";
				preparedStatement=(PreparedStatement) connection.prepareStatement(sqlString);
				preparedStatement.setString(1, "%"+condition+"%");
				preparedStatement.setString(2, "%"+condition+"%");
				preparedStatement.setString(3, "%"+condition+"%");
				preparedStatement.setString(4, "%"+condition+"%");
				resultSet=preparedStatement.executeQuery();
				while(resultSet.next()){
					book=new Book();
					book.setBookId(resultSet.getInt(1));
					book.setBookNo(resultSet.getString(2));
					book.setBookName(resultSet.getString(3));
					book.setBookAuthor(resultSet.getString(4));
					book.setBookPrice(resultSet.getString(5));
					book.setBookInfo(resultSet.getString(6));
					book.setBookTotalCount(resultSet.getInt(7));
					book.setBookBorrowedCount(resultSet.getInt(8));
					books.add(book);
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				JdbcUtil.free(resultSet, preparedStatement, connection);
			}
			return books;
		}
	
}
