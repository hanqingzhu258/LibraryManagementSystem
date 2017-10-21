package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Book;
import dao.BookDao;

public class User_Book_List_Servlet extends HttpServlet {



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
		
		
		BookDao bookDao=new BookDao();
		List<Book>books=new ArrayList<Book>();
		int totalCount=0;
		try{
			books=bookDao.getAll();
			totalCount=bookDao.getBookTotalCount();
			
			request.setAttribute("books", books);
			request.setAttribute("totalCount", totalCount);
			
			request.getRequestDispatcher("bookList/user_book_list.jsp").forward(request, response);
			
		}catch(Exception e){
			System.out.println("书籍查询过程出现错误！！！！");
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
