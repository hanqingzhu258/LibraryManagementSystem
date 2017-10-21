<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="beans.*" %>
<%@ page import="dao.UserDao" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'user_book_listByPage.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<style type="text/css">
		table, th{
			  border: 1px solid gray;
 		 }
 		 td{
 		 	text-align:center;
 		 	 border: 1px solid gray;
 		 	 vertical-align:center;
 		 }
	</style>
	<script type="text/javascript">
		function	borrow( borrowedBookId){
			var borrowCount=prompt("请填写借阅数量：");

			if(borrowCount==null){}
			else{
				var borrowOk=confirm("您想借阅"+borrowCount+"本，确认借阅？？？");
				if (borrowOk==true){

					window.location.href="user_book_borrow?borrowedBookId="+borrowedBookId+
					"&borrowCount="+borrowCount;
				}else{}
			}
			
		}
	</script>

  </head>
  
  <body>
  
  <p>
  <%
  	int userId=(Integer)request.getSession().getAttribute("userId");
  	User user=new User();
  	UserDao userDao=new UserDao();
  	user=userDao.findUserById(userId);
   %>
   <span>用户： </span><span><%=user.getUserName() %></span><span>  的书籍列表</span>
  </p>
    
	<p>当前图书种类的总数为：<%=(Integer)request.getAttribute("totalCount") %></p>
	<table>
		<tr>
				<th>序号</th><th>书&nbsp;&nbsp;名</th><th>作&nbsp;&nbsp;者</th><th>总数</th><th>剩余</th><th>详情</th><th>借书</th>
		</tr>
		<%
			int pageSize=10;
			int currentPage=(Integer)request.getAttribute("startCount");
			List<Book> books=(List<Book>)request.getAttribute("books");
			int count=currentPage*pageSize+1;
			for(Book book:books){
		 %>
		 <tr>
				<td><%=count %></td>
				<td><%=book.getBookName() %></td>
				<td><%=book.getBookAuthor() %></td>
				<td><%=book.getBookTotalCount() %>本</td>
				<td><%=book.getBookRest() %>本</td>
				<td><a href="user_book_detail?visitedBookId=<%=book.getBookId()%>">详情</a></td>
				<td><a  style="cursor: pointer;" onclick="borrow(<%=book.getBookId()%>)">借书</a></td>
				
			</tr>
			<%count++;} %>

	</table>
	<br/>
	<p><span>当前是第</span><span><%=currentPage+1 %><span>页</span></span></p>
				
	<div>
		<span><a href="user_book_listByPage?startCount=0">首页</a></span>
		<span><a href="user_book_listByPage?startCount=<%=currentPage-1%>">上一页</a></span>
		<%
			int totalCount=(Integer)request.getAttribute("totalCount");

			int totalPages=0;
			int exact=totalCount%pageSize;
			if(exact==0){
				totalPages=totalCount/pageSize;
			}else{
				totalPages=totalCount/pageSize+1;
			}
			int pageCount=0;
			int showPageCount=5;//总共只显示10页数据
			if(totalPages<=showPageCount){
				while(pageCount<=totalPages-1){
		 %>
		 <span><a href="user_book_listByPage?startCount=<%=pageCount%>">第<%=pageCount+1 %>页</a></span>
		 <%	pageCount++;}
		 	}else{
		 		if(currentPage<showPageCount){
		 			while(pageCount<=showPageCount-1){
		  %>
		  <span><a href="user_book_listByPage?startCount=<%=pageCount%>">第<%=pageCount+1 %>页</a></span>
		  <%	pageCount++; }
		  		}else{
		  	%>
		  	<span>…</span>
		  	<%
		  			while((pageCount+currentPage-showPageCount+1)<=currentPage){
		   %>
		   <span><a href="user_book_listByPage?startCount=<%=pageCount+currentPage-showPageCount+1%>">第<%=pageCount+currentPage-showPageCount+2 %>页</a></span>
			<%
					pageCount++;}
			%>
			<span>…</span>
			<%
				}
			}	
			 %>

			 <span><a href="user_book_listByPage?startCount=<%=currentPage+1%>">下一页</a></span>
			 <span><a href="user_book_listByPage?startCount=<%=totalPages-1%>">尾页</a></span>
	</div>
		 

  </body>
</html>