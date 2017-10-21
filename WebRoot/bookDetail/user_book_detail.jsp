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
    
    <title>My JSP 'user_book_detail.jsp' starting page</title>
    
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
}
</style>
	<script type="text/javascript">
		function	returnBook( returnBookId){
			var returnCount=prompt("请填写还书数量：");
			if(returnCount==null){}
			else{
				/* if(returnCount>borrowedCount){alert("您并没有借阅这么多这本书！！");} */
				/* else{ */
					var returnOk=confirm("您想归还"+returnCount+"本，确认归还？？？");
					if (returnOk==true){
						window.location.href="user_book_return?returnBookId="+returnBookId+"&returnCount="+returnCount;
					}else{}
				/* } */
			}
			
		}
	</script>

  </head>
  
  <body>
    
	<%
		Book book = (Book) request.getAttribute("book");
		/* System.out.println(book.getBookId()); */
	%>

			
		<table>
			<tr>
				<td>编号:</td>
				<td><input type="text" disabled="disabled" name="bookNo"
					id="bookNo" value="<%=book.getBookNo()%>" /></td>
			</tr>
			<tr>
				<td>名称:</td>
				<td><input type="text" disabled="disabled" name="bookName"
					id="bookName" value="<%=book.getBookName()%>" /></td>
			</tr>
			<tr>
				<td>作者:</td>
				<td><input type="text" disabled="disabled" name="bookAuthor"
					id="bookAuthor" value="<%=book.getBookAuthor()%>" /></td>
			</tr>
			<tr>
				<td>价格:</td>
				<td><input type="text" disabled="disabled" name="bookPrice"
					id="bookPrice" value="<%=book.getBookPrice()%>" /></td>
			</tr>
			<tr>
				<td>简介:</td>
				<td><textarea disabled="disabled" name="bookInfo" id="bookInfo"><%=book.getBookInfo()%></textarea></td>
			</tr>
			<tr>
				<td>剩余可借:</td>
				<td><%=book.getBookRest()%></td>
			</tr>
			<%-- <tr>
				<td colspan="2"><input type="button" onclick="returnBook(<%=book.getBookId()%>)" value="还书"></td>
				<!-- <td><input type="submit" value="提交"></td> -->
			</tr> --%>

		</table>

  </body>
</html>
