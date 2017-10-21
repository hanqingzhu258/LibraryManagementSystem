<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="beans.UB"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'user_book_borrowed.jsp' starting page</title>
    
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
		function	returnBook( returnBookId,borrowedCount){
			var returnCount=prompt("请填写还书数量：");
			if(returnCount==null){}
			else{
				if(returnCount>borrowedCount){alert("您并没有借阅这么多这本书！！");}
				else{
					var returnOk=confirm("您想归还"+returnCount+"本，确认归还？？？");
					if (returnOk==true){
						window.location.href="user_book_return?returnBookId="+returnBookId+"&returnCount="+returnCount;
					}else{}
				}
			}
			
		}
	</script>

  </head>
  
  <body>

	<table>
			<tr>
				<th>序号</th><th>书&nbsp;&nbsp;名</th><th>借阅数量</th><th>详情</th><th>还书</th>
			</tr>
			<%
					List<UB> ubs=(List<UB>)request.getAttribute("ubs");
					int count=1;
					for(UB ub:ubs){
			 %>
			 
			<tr>
				<td><%=count %></td>
				<td><%=ub.getBookName() %></td>
				<td><%=ub.getBookCount() %>本</td>
				<td><a href="user_book_detail?visitedBookId=<%=ub.getBookId()%>">详情</a></td>
				<td><a  style="cursor: pointer;" onclick="returnBook(<%=ub.getBookId()%>,<%=ub.getBookCount()%>)">还书</a></td>
				
			</tr>
			
			<%count++;} %>
			
		</table>


  </body>
</html>
