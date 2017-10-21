package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.UB;
import dao.UBDao;

public class User_Book_BorrowedByPage_Servlet extends HttpServlet {

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
		
		
		
		request.setCharacterEncoding("utf8");
		int userId = (Integer) request.getSession().getAttribute("userId");
		int startCount = Integer.parseInt(request.getParameter("startCount"));
		int pageSize = 10;
		int totalCount = 0;
		int totalPages;

		List<UB> ubs = new ArrayList<UB>();
		/* UB ub=new UB(); */
		UBDao ubDao = new UBDao();
		try {

			totalCount = ubDao.getUserAllBooksCount(userId);
			int exact = totalCount % pageSize;
			if(totalCount==0){totalPages = 1;}
			else if (exact == 0) {
				totalPages = totalCount / pageSize;
			}else {
				totalPages = totalCount / pageSize + 1;
			}

			if (startCount < 0) {

				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<html>");
				out.println("<script type='text/javascript'>");
				out.println("alert('已经是首页，没有上一页！点击确定返回首页！');");
				out.println("window.location.href='user_book_borrowedByPage?startCount=0';");
				out.println("</script>");
				out.println("</html>");

			} else if (startCount <= totalPages - 1) {

				ubs = ubDao.getUserAllBooks(userId);
				request.setAttribute("ubs", ubs);
				request.setAttribute("startCount", startCount);
				request.setAttribute("totalCount", totalCount);				
				request.getRequestDispatcher(
						"userBooks/user_book_borrowedByPage.jsp").forward(
						request, response);
			} else {
				startCount = startCount - 1;
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<html>");
				out.println("<script type='text/javascript'>");
				out.println("alert('已经是尾页，没有下一页！点击确定返回尾页！');");
				out.println("window.location.href='user_book_borrowedByPage?startCount="
						+ startCount + "';");
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

		doGet(request, response);
		
	}

}
