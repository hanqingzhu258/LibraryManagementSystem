<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="beans.Book" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'admin_book_query.jsp' starting page</title>
    
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
					window.location.href="user_book_borrow?borrowedBookId="+borrowedBookId+"&borrowCount="+borrowCount;
				}else{}
			}
			
		}
	</script>
	

  </head>
  
  <body>
   
   		<p>用户查询页面：</p>
   
		<%
			List<Book> books=(List<Book>)request.getAttribute("books");
			if(books.isEmpty()){
		 %>
			<p>当前无任何记录！！！</p>
		<%} else{%>
		<table>
			<tr>
				<th>序号</th><th>书&nbsp;&nbsp;名</th><th>作&nbsp;&nbsp;者</th><th>可借</th><th>详情</th><th>借书</th>
			</tr>
		<%
		 int count=1;
		 for(Book book:books){%>
			<tr>
				<td><%=count %></td>
				<td><%=book.getBookName() %></td>
				<td><%=book.getBookAuthor() %></td>
				<td><%=book.getBookRest() %>本</td>
				<td><a href="user_book_detail?visitedBookId=<%=book.getBookId()%>">详情</a></td>
				<td><a  style="cursor: pointer;" onclick="borrow(<%=book.getBookId()%>)">借书</a></td>
				
			</tr>
			
		<% count++;}} %>
		</table>
  </body>
</html>
