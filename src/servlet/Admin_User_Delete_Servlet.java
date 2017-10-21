package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDao;
import dao.UserDao;

public class Admin_User_Delete_Servlet extends HttpServlet {



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
	        out.println("window.parent.location.href='login/admin_login.jsp?';");    
	        out.println("</script>");      
	        out.println("</html>");  
	        return;
		}
		
		
		int userId=Integer.parseInt(request.getParameter("userId"));
		UserDao userDao=new UserDao();
		int isSuccess=0;
		try{
			isSuccess=userDao.delete(userId);
			/*response.sendRedirect("admin_user_list");*/
			
			response.setContentType("text/html; charset=UTF-8"); 
			PrintWriter out = response.getWriter();  
	        out.println("<html>"); 
	       /* out.println("<meta name='content-type' content='text/html; charset=UTF-8'>");*/
	        out.println("<script type='text/javascript'>");
	        out.println("alert('删除成功，请点击确定返回用户列表！！');");   
	        out.println("window.location.href='admin_user_listByPage?startCount=0';");   
	        out.println("</script>");      
	        out.println("</html>"); 
			
		}catch(Exception e){
			System.out.println("删除过程出现错误！！！");
			response.setContentType("text/html; charset=UTF-8"); 
			PrintWriter out = response.getWriter();  
	        out.println("<html>"); 
	       /* out.println("<meta name='content-type' content='text/html; charset=UTF-8'>");*/
	        out.println("<script type='text/javascript'>");
	        out.println("alert('删除失败，该用户已借阅过书籍，无法删除！！');");   
	        out.println("window.location.href='admin_user_listByPage?startCount=0';");   
	        out.println("</script>");      
	        out.println("</html>"); 
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

	}

}
