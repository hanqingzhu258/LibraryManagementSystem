<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="beans.User" %>
<%@page import="dao.*" %>
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
  
  <body >
  
  		<p>
  	<%
  		int userId=(Integer)request.getSession().getAttribute("userId");
  		User admin=new User();
  		UserDao userDao=new UserDao();
  		admin=userDao.findUserById(userId);
  	 %>
  	 <span>用户： </span><span><%=admin.getUserName() %></span><span>  的用户列表</span>
  	</p>
  

			 <div>
			 	<span>现有用户数为：</span>
			 	<span><%=(Integer)request.getAttribute("totalCount")%></span>
			 	<span>人</span>
			 </div>
			 <br/>
		<table>
			<tr>
				<th>序号</th><th>用户Id</th><th>用户名</th><th>删除</th>
			</tr>
			<%
					List<User> users=(List<User>)request.getAttribute("users");
					int pageSize=10;
					int currentPage=(Integer)request.getAttribute("startCount");
					int count=currentPage*pageSize+1;
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
		
		<br/>
	<p><span>当前是第</span><span><%=currentPage+1 %><span>页</span></span></p>
				
	<div>
		<span><a href="admin_user_listByPage?startCount=0">首页</a></span>
		<span><a href="admin_user_listByPage?startCount=<%=currentPage-1%>">上一页</a></span>
		<%
			int totalCount=(Integer)request.getAttribute("totalCount");

			int totalPages=0;
			int exact=totalCount%pageSize;
			
			if(totalCount==0){totalPages=1;}
			else if(exact==0){
				totalPages=totalCount/pageSize;
			}else{
				totalPages=totalCount/pageSize+1;
			}
			int pageCount=0;
			int showPageCount=5;//总共只显示10页数据
			if(totalPages<=showPageCount){
				while(pageCount<=totalPages-1){
		 %>
		 <span><a href="admin_user_listByPage?startCount=<%=pageCount%>">第<%=pageCount+1 %>页</a></span>
		 <%	pageCount++;}
		 	}else{
		 		if(currentPage<showPageCount){
		 			while(pageCount<=showPageCount-1){
		  %>
		  <span><a href="admin_user_listByPage?startCount=<%=pageCount%>">第<%=pageCount+1 %>页</a></span>
		  <%	pageCount++; }
		  		}else{
		  	%>
		  	<span>…</span>
		  	<%
		  			while((pageCount+currentPage-showPageCount+1)<=currentPage){
		   %>
		   <span><a href="admin_user_listByPage?startCount=<%=pageCount+currentPage-showPageCount+1%>">第<%=pageCount+currentPage-showPageCount+2 %>页</a></span>
			<%
					pageCount++;}
			%>
			<span>…</span>
			<%
				}
			}	
			 %>

			 <span><a href="admin_user_listByPage?startCount=<%=currentPage+1%>">下一页</a></span>
			 <span><a href="admin_user_listByPage?startCount=<%=totalPages-1%>">尾页</a></span>
	</div>


  </body>
</html>
