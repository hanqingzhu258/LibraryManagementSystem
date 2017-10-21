package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jws.soap.SOAPBinding.Use;
import javax.security.auth.message.callback.PrivateKeyCallback.IssuerSerialNumRequest;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import beans.User;

public class Admin_Login_Servlet extends HttpServlet {

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

		/*System.out.println("the mothod is being used!");*/
		request.setCharacterEncoding("UTF-8");
		UserDao userDao=new UserDao();
		User admin=new User();
		admin.setIsAdmin(1);
		admin.setPassword(request.getParameter("password"));
		admin.setUserName(request.getParameter("userName"));
		int isSuccess=0;
		
		int userId=0;
		
		try{
			isSuccess=userDao.ifUserExist(admin);
			if(isSuccess==0){
				
				response.setContentType("text/html; charset=UTF-8"); 
				PrintWriter out = response.getWriter();  
		        out.println("<html>"); 
		       /* out.println("<meta name='content-type' content='text/html; charset=UTF-8'>");*/
		        out.println("<script type='text/javascript'>");
		        out.println("alert('登录失败！请重新登录！！');");   
		        out.println("window.location.href='login/admin_login.jsp';");   
		        out.println("</script>");      
		        out.println("</html>"); 
				
			}
			else{
				userId=userDao.getLoginUserId(admin);
			}
			/*request.setAttribute("isSuccess", isSuccess);*/
		}catch(Exception e){
			System.out.println("数据库查询过程出现异常！！！！");
		}
		request.getSession().setAttribute("userId",userId);
		
		response.setContentType("text/html; charset=UTF-8"); 
		PrintWriter out = response.getWriter();  
        out.println("<html>"); 
       /* out.println("<meta name='content-type' content='text/html; charset=UTF-8'>");*/
        out.println("<script type='text/javascript'>");
        out.println("alert('登录成功，请点击确定前往主页！！');");   
        out.println("window.location.href='homepage/admin_homepage.html';");   
        out.println("</script>");      
        out.println("</html>"); 

	}


}
