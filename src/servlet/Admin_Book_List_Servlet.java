package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDao;
import beans.Book;

public class Admin_Book_List_Servlet extends HttpServlet {


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

		/*System.out.println("the get method is being used!");*/
		
		/*String userString=(String) request.getSession().getAttribute("user");
		if(userString==null){
			response.sendRedirect("login/admin_login.jsp");
			PrintWriter out = response.getWriter();  
	        out.println("<html>");      
	        out.println("<script>");      
	        out.println("window.open ('"+request.getContextPath()+"login/admin_login.jsp','_top')");      
	        out.println("</script>");      
	        out.println("</html>");    
	        
		}*/

		if(request.getSession().getAttribute("userId")==null){
			response.setContentType("text/html; charset=UTF-8"); 
			PrintWriter out = response.getWriter();  
	        out.println("<html>"); 
	       /* out.println("<meta name='content-type' content='text/html; charset=UTF-8'>");*/
	        out.println("<script type='text/javascript'>");
	        out.println("alert('您尚未登录，请先登录！！');");   
	        out.println("window.location.href='login/admin_login.jsp?';");   
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
			
			request.getRequestDispatcher("bookList/admin_book_list.jsp").forward(request, response);
			
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
		
		/*System.out.println("the post method is being used!");*/
		doGet(request, response);
		
	}


}
