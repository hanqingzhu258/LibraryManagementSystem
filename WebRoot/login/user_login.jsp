<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'admin_login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	<script type="text/javascript">
	
	function login(){
	
		var userName=document.getElementById("userName");
		var password=document.getElementById("password");
		
		if(userName.value==""){
			alert("用户名不能为空！！！");
		}else if(password.value==""){
			alert("密码不能为空！！！");
		}else{
			checkUser();
		}
	
	}
	
	function checkUser(){
		myform.submit();
	}
	
	</script>

  </head>
  
  <body align="center">
  
  
   <h1>图书管理系统</h1>
   <form action="user_login" method="post" id="myform">
	<table>
		<tr>
			<td>请输入您的用户名：</td>
			<td><input type="text" name="userName" id="userName"/></td>
		</tr>
		<tr>
			<td>请输入您的密码：</td>
			<td><input type="password" name="password" id="password"/></td>
		</tr>
		<tr>
			<td align="center"><input type="button" value="登录" onclick="login()"/></td>
			<td align="center"><a href="register/user_register.jsp">注册</a></td>
		</tr>
	</table>
	</form>

  </body>
</html>
