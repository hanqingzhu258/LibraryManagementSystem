<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'admin_book_add.jsp' starting page</title>
    
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
	function add() {
		var bookNo = document.myform.bookNo;
		var bookName = document.myform.bookName;
		var bookAuthor = document.myform.bookAuthor;
		var bookPrice = document.myform.bookPrice;
		var bookInfo = document.myform.bookInfo;
		var bookTotalCount = document.myform.TotalCount;
		 if (bookNo.value == "" || bookName.value == ""
				|| bookAuthor == "" || bookPrice == "" || bookInfo == ""
				|| bookTotalCount =="") {
			alert("参数设置不合理，请仔细检查并修改后，再次提交！！！");
		}else {
			myform.submit();
		}
	}
</script>

  </head>
  
  <body>
  
  <%
  	String isSuccess=request.getParameter("isSuccess");
  	if(isSuccess==null){}
  	else if(isSuccess.equals("0")){
   %>
   <script type="text/javascript">
   		alert("添加失败！");
   </script>
   <%}else if(isSuccess.equals("1")){
    %>
    <script type="text/javascript">
    	alert("添加成功，请点击返回书籍列表！");
    	window.location.href="admin_book_list";
    </script>
    <%-- <jsp:forward page="homepage/admin_homepage.jsp"></jsp:forward> --%>
    <%}%>
   
	<form action="admin_book_add" method="post" name="myform">
		<table>
			<tr>
				<td>请输入书编号：</td>
				<td><input type="text" name="bookNo" id="bookNo"/></td>
			</tr>
			<tr>
				<td>请输入书名称：</td>
				<td><input type="text" name="bookName" id="bookName"/></td>
			</tr>
			<tr>
				<td>请输入书作者：</td>
				<td><input type="text" name="bookAuthor" id="bookAuthor"/></td>
			</tr>
			<tr>
				<td>请输入书价格：</td>
				<td><input type="text" name="bookPrice" id="bookPrice"/></td>
			</tr>
			<tr>
				<td>情输入书简介:</td>
				<td><textarea name="bookInfo" id="bookInfo"></textarea></td>
			</tr>
			<tr>
				<td>请输入书总数：</td>
				<td><input type="text" name="bookTotalCount" id="bookTotalCount"/></td>
			</tr>
			<tr>
				<td><input type="button" onclick="add()" value="提交"/></td>
				<td><input type="reset" value="重置"/></td>
				<!-- <td><input type="submit" value="提交"></td> -->
			</tr>
		</table>
	</form>

  </body>
</html>