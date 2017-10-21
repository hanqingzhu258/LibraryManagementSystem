<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="beans.Book"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'admin_book_detail.jsp' starting page</title>

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
	function update() {
		var bookNo = document.myform.bookNo;
		bookNo.disabled = "";
		var bookName = document.myform.bookName;
		bookName.disabled = "";
		var bookAuthor = document.myform.bookAuthor;
		bookAuthor.disabled = "";
		var bookPrice = document.myform.bookPrice;
		bookPrice.disabled = "";
		var bookInfo = document.myform.bookInfo;
		bookInfo.disabled = "";
		var bookTotalCount = document.myform.bookTotalCount;
		bookTotalCount.disabled ="";
	}

	function updateOk() {
		var bookNo = document.myform.bookNo;
		var bookName = document.myform.bookName;
		var bookAuthor = document.myform.bookAuthor;
		var bookPrice = document.myform.bookPrice;
		var bookInfo = document.myform.bookInfo;
		var bookTotalCount = document.myform.TotalCount;
		var disabled = bookNo.disabled;
		if (disabled != "") {
			alert("您尚未做出任何改动，无法提交，请点击左下方‘编辑’按钮，编辑后提交");
		}else if (bookNo.value == "" || bookName.value == ""
				|| bookAuthor == "" || bookPrice == "" || bookInfo == ""
				|| bookTotalCount < bookBorrowedCount) {
			alert("参数设置不合理，请仔细检查并修改后，再次提交！！！");
		}else {
			myform.submit();
		}
	}
</script>

</head>

<body>

	
	
	<%
		Book book = (Book) request.getAttribute("book");
		/* System.out.println(book.getBookId()); */
	%>
	<form action="admin_book_update" name="myform" method="post">
			<input type="hidden"  name="bookId" id="bookId" value="<%=book.getBookId()%>" />
			
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
				<td>总数:</td>
				<td><input type="text" disabled="disabled"
					name="bookTotalCount" id="bookTotalCount"
					value="<%=book.getBookTotalCount()%>"></td>
			</tr>
			<tr>
				<td>已借:</td>
				<td><%=book.getBookBorrowedCount()%>
				<input type="hidden" 
					name="bookBorrowedCount" id="bookBorrowedCount"
					value="<%=book.getBookBorrowedCount()%>">
				</td>
			</tr>
			<tr>
				<td><input type="button" onclick="update()" value="修改"></td>
				<td><input type="button" onclick="updateOk()" value="提交"></td>
				<!-- <td><input type="submit" value="提交"></td> -->
			</tr>

		</table>
	</form>
</body>
</html>
