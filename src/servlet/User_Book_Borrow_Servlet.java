package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Book;
import beans.UB;
import dao.BookDao;
import dao.UBDao;

public class User_Book_Borrow_Servlet extends HttpServlet {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if(request.getSession().getAttribute("userId")==null){
			response.setContentType("text/html; charset=UTF-8"); 
			PrintWriter out = response.getWriter();  
	        out.println("<html>"); 
	       /* out.println("<meta name='content-type' content='text/html; charset=UTF-8'>");*/
	        out.println("<script type='text/javascript'>");
	        out.println("alert('您尚未登录，请先登录！！');");   
	        out.println("window.parent.location.href='login/user_login.jsp?';");    
	        out.println("</script>");      
	        out.println("</html>");  
	        return;
		}
		
		
		
		
		
		request.setCharacterEncoding("UTF-8");
		int borrowedBookId=Integer.parseInt(request.getParameter("borrowedBookId"));
		int borrowCount=Integer.parseInt(request.getParameter("borrowCount"));
		int userId=(Integer)request.getSession().getAttribute("userId");
		
		int totalBorrowedCount;
		
		BookDao bookDao=new BookDao();
		Book book=new Book();
		UBDao ubDao=new UBDao();
		UB ub=new UB();
		try{
			book=bookDao.findBookById(borrowedBookId);
			if(borrowCount>book.getBookRest()){
				response.setContentType("text/html; charset=UTF-8"); 
				PrintWriter out = response.getWriter();  
		        out.println("<html>"); 
		       /* out.println("<meta name='content-type' content='text/html; charset=UTF-8'>");*/
		        out.println("<script type='text/javascript'>");
		        out.println("alert('借阅失败，借阅数目超过书籍剩余数量！点击返回借阅！');");   
		        out.println("window.location.href='user_book_listByPage?startCount=0';");   
		        out.println("</script>");      
		        out.println("</html>"); 
			}else{
				totalBorrowedCount=ubDao.getUserAllBooksCount(userId);
				if(totalBorrowedCount+borrowCount>8){
					response.setContentType("text/html; charset=UTF-8"); 
					PrintWriter out = response.getWriter();  
			        out.println("<html>"); 
			       /* out.println("<meta name='content-type' content='text/html; charset=UTF-8'>");*/
			        out.println("<script type='text/javascript'>");
			        out.println("alert('借阅失败，借阅数目超过上限8本！点击返回借阅！');");   
			        out.println("window.location.href='user_book_listByPage?startCount=0';");     
			        out.println("</script>");      
			        out.println("</html>"); 
				}else{
					ub.setBookCount(borrowCount);
					ub.setBookId(borrowedBookId);
					ub.setBookName(book.getBookName());
					ub.setUserId(userId);
					ubDao.borrowBooks(ub);
					
					//将book表中的该书籍的以借数量增加
					book.setBookBorrowedCount(book.getBookBorrowedCount()+borrowCount);
					
					/*System.out.println("book.getBookBorrowedCount():"+book.getBookBorrowedCount());
					System.out.println("borrowCount:"+borrowCount);*/
					
					bookDao.update(book);
					
					response.setContentType("text/html; charset=UTF-8"); 
					PrintWriter out = response.getWriter();  
			        out.println("<html>"); 
			       /* out.println("<meta name='content-type' content='text/html; charset=UTF-8'>");*/
			        out.println("<script type='text/javascript'>");
			        out.println("alert('借阅成功！点击查看借阅列表！');");   
			        out.println("window.location.href='user_book_listByPage?startCount=0';");  
			        out.println("</script>");      
			        out.println("</html>"); 
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
		
	}

}
