<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="beans.User" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'admin_user_list.jsp' starting page</title>
    
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
		function	deleteOk( userId){
			var deleteOk=confirm("删除后无法恢复，确认删除？？？");
			if (deleteOk==true){
				window.location.href="admin_user_delete?userId="+userId;
			}else{}
		}
	</script>

  </head>
  
  <body align="center">
    
    		<%
				int totalCount=(Integer)request.getAttribute("totalCount");
			 %>
			 <div>
			 	<span>现有用户数为：</span>
			 	<span><%=totalCount%></span>
			 	<span>人</span>
			 </div>
			 <br/>
		<table>
			<tr>
				<th>序号</th><th>用户Id</th><th>用户名</th><th>删除</th>
			</tr>
			<%
					List<User> users=(List<User>)request.getAttribute("users");
					int count=1;
					for(User user:users){
			 %>
			 
			<tr>
				<td><%=count %></td>
				<td><%=user.getUserId() %></td>
				<td><%=user.getUserName() %></td>
				<%-- <td><a href="admin_user_delete?userId=<%=user.getUserId()%>">删除</a></td> --%>
				<td><a  style="cursor: pointer;" onclick="deleteOk(<%=user.getUserId()%>)">删除</a></td>
			</tr>
			
			<%count++;} %>
			
		</table>  


  </body>
</html>
