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
		function	deleteOk( visitedBookId){
			var deleteOk=confirm("删除后无法恢复，确认删除？？？");
			if (deleteOk==true){
				window.location.href="admin_book_delete?visitedBookId="+visitedBookId;
			}else{}
		}
	</script>
	

  </head>
  
  <body>
   
   <p>管理员查询页面：</p>
   
		<%
			List<Book> books=(List<Book>)request.getAttribute("books");
			if(books.isEmpty()){
		 %>
			<p>当前无任何记录！！！</p>
		<%} else{%>
		<table>
			<tr>
				<th>序号</th><th>书&nbsp;&nbsp;名</th><th>作&nbsp;&nbsp;者</th><th>总数</th><th>已借</th><th>详情</th><th>删除</th>
			</tr>
		<%
		 int count=1;
		 for(Book book:books){%>
			<tr>
				<td><%=count %></td>
				<td><%=book.getBookName() %></td>
				<td><%=book.getBookAuthor() %></td>
				<td><%=book.getBookTotalCount() %>本</td>
				<td><%=book.getBookBorrowedCount() %>本</td>
				<td><a href="admin_book_detail?visitedBookId=<%=book.getBookId()%>">详情</a></td>
				<%-- <td><a href="admin_book_delete?visitedBookId=<%=book.getBookId()%>">删除</a></td> --%>
				<td><a  style="cursor: pointer;" onclick="deleteOk(<%=book.getBookId()%>)">删除</a></td>
				
			</tr>
			
		<% count++;}} %>
		</table>
  </body>
</html>
