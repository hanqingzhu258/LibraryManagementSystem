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

public class User_Book_Return_Servlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
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
		
		
		
		
		
		/* System.out.println("the book is being returned!!!"); */

		request.setCharacterEncoding("UTF-8");
		int returnBookId = Integer.parseInt(request
				.getParameter("returnBookId"));
		int returnCount = Integer.parseInt(request.getParameter("returnCount"));
		int userId = (Integer) request.getSession().getAttribute("userId");
		/* int totalBorrowedCount; */
		/* int bookRest; */
		int isSuccess;

		Book book;
		BookDao bookDao = new BookDao();
		UBDao ubDao = new UBDao();
		UB ub = new UB();
		ub.setBookCount(returnCount);
		ub.setUserId(userId);
		ub.setBookId(returnBookId);

		try {

			isSuccess = ubDao.returnBooks(ub);
			if (isSuccess == 0) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<html>");
				/*
				 * out.println(
				 * "<meta name='content-type' content='text/html; charset=UTF-8'>"
				 * );
				 */
				out.println("<script type='text/javascript'>");
				out.println("alert('还书失败！数量超过借阅总数，请重新还书！');");
				out.println("window.location.href='user_book_borrowed';");
				out.println("</script>");
				out.println("</html>");
			} else {

				// 将book表中的该书籍的已借数量减少
				book = bookDao.findBookById(returnBookId);
				book.setBookBorrowedCount(book.getBookBorrowedCount()
						- returnCount);
				bookDao.update(book);

				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<html>");
				/*
				 * out.println(
				 * "<meta name='content-type' content='text/html; charset=UTF-8'>"
				 * );
				 */
				out.println("<script type='text/javascript'>");
				out.println("alert('还书成功！点击查看借阅列表！');");
				out.println("window.location.href='user_book_borrowedByPage?startCount=0';");
				out.println("</script>");
				out.println("</html>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
