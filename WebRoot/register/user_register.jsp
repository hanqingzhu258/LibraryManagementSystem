<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'user_register.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	<style type="text/css">
table,th {
	border: 1px solid gray;
}

td {
	text-align: center;
	border: 1px solid gray;
	vertical-align: center;
	width:150px;
}
</style>

<script type="text/javascript">
	function register() {
		var userName = document.myform.userName;
		var password = document.myform.password;

		 if (userName==""||password=="") {
			alert("参数设置不合理，请仔细检查并修改后，再次提交！！！");
		}else {
			myform.submit();
		}
	}
</script>

  </head>
  
  <body>
    
		<form action="user_register" method="post" name="myform">
			<table>
				<tr>
					<td>请输入您的用户名：</td>
					<td><input type="text" name="userName" id="userName"/></td>
				</tr>
				<tr>
					<td>请输入您的密码：</td>
					<td><input type="text" name="password" id="password"/></td>
				</tr>
				<tr>
					<td><input type="button" value="注册" onclick="register()"></td>
					<td><input type="reset" value="重置"></td>
				</tr>
			</table>
		</form>

  </body>
</html>
