package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Book;
import dao.BookDao;

public class Admin_Book_Add_Servlet extends HttpServlet {


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

		doPost(request, response);
		
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
		
		if(request.getSession().getAttribute("userId")==null){
			response.setContentType("text/html; charset=UTF-8"); 
			PrintWriter out = response.getWriter();  
	        out.println("<html>"); 
	       /* out.println("<meta name='content-type' content='text/html; charset=UTF-8'>");*/
	        out.println("<script type='text/javascript'>");
	        out.println("alert('您尚未登录，请先登录！！');");   
	        out.println("window.parent.location.href='login/admin_login.jsp?';");   
	        out.println("</script>");      
	        out.println("</html>");  
	        return;
		}
		
		
		int isSuccess=0;
		request.setCharacterEncoding("UTF-8");
		Book book=new Book();
		/*System.out.println("bookId"+request.getParameter("bid"));
		System.out.println("bookNo"+request.getParameter("bookNo"));
		System.out.println("bookName"+request.getParameter("bookName"));
		System.out.println("bookAuthor"+request.getParameter("bookAuthor"));
		System.out.println("bookTotalCount"+request.getParameter("bookTotalCount"));
		System.out.println("bookBorrowedCount"+request.getParameter("bookBorrowedCount"));*/
		book.setBookNo(request.getParameter("bookNo"));
		book.setBookName(request.getParameter("bookName"));
		book.setBookAuthor(request.getParameter("bookAuthor"));
		book.setBookPrice(request.getParameter("bookPrice"));
		book.setBookInfo(request.getParameter("bookInfo"));
		book.setBookTotalCount(Integer.parseInt(request.getParameter("bookTotalCount")));
		BookDao bookDao=new BookDao();
		try{
			isSuccess=bookDao.add(book);
			/*response.sendRedirect("admin_book_list");*/
			/*request.getRequestDispatcher("bookAdd/admin_book_add.jsp?isSuccess="+isSuccess).forward(request, response);*/
			response.setContentType("text/html; charset=UTF-8"); 
			PrintWriter out = response.getWriter();  
	        out.println("<html>"); 
	       /* out.println("<meta name='content-type' content='text/html; charset=UTF-8'>");*/
	        out.println("<script type='text/javascript'>");
	        out.println("alert('添加成功，请点击确定进行跳转！！');");   
	        out.println("window.location.href='admin_book_listByPage?startCount=0';");   
	        out.println("</script>");      
	        out.println("</html>");   
			
			
		}catch(Exception e){
			System.out.println("添加书籍信息过程出现问题！！！！！");
		e.printStackTrace();
		}

	}

}
