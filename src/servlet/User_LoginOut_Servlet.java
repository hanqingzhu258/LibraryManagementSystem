package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class User_LoginOut_Servlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public User_LoginOut_Servlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

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

		request.getSession().setAttribute("userId", null);
		/*response.sendRedirect("login/admin_login.jsp");*/
		
		response.setContentType("text/html; charset=UTF-8"); 
		PrintWriter out = response.getWriter();  
        out.println("<html>"); 
       /* out.println("<meta name='content-type' content='text/html; charset=UTF-8'>");*/
        out.println("<script type='text/javascript'>");
        out.println("alert('退出成功，点击确定返回登陆页面！！');");   
        out.println("window.location.href='login/user_login.jsp';");   
        out.println("</script>");      
        out.println("</html>"); 
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

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
