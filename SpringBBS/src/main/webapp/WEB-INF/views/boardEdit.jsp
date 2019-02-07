<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>게시판</title>
<link href="<c:url value="./resources/css/bootstrap.css"/>"
	rel="stylesheet" />

</head>


<%
	String id = null;

	if (session.getAttribute("userid") != null) {
		id = (String) session.getAttribute("userid");
	}
%>
<body>
	<p style="margin: 50px">
	<div class="container">
	<form action="edit.do" enctype="multipart/form-data" id="fm" method="post">
		<table class="table table-striped">
			<thead>
				<tr>
					<td colspan='2' align="center"> 
	    <font  style="font-size:20pt" > <input	class="form-control" type="text" name="title"
				value="${dto.title}"> </font>  
	  </td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td rowspan="4" align="center"> <!-- prc -->
	   				
	   				<img src ='${pageContext.request.contextPath}/resources/upload/${dto.img_file_name}' width=400 height=300 border=0>   
	   				
				   </td>
				   
					<td>번호 : ${dto.bbs_idx }<input type="hidden" name="bbs_idx" value="${dto.bbs_idx }"/></td>
					
				</tr>
				<tr>
					<td>시간 : ${dto.bbs_date} </td>
					
				</tr>
				<tr>
					<td rowspan="2">내용 : <textarea style="resize : none;" class="form-control" rows="7" name="content" >${dto.content}</textarea> </td>
				</tr>
			</tbody>
			<tr>
				<td colspan="2" align="center">
					<input type="file" name="upload_f">
				</td>
			</tr>
				<tr>
					<td colspan="2" align="center">
			<c:set var="id" value="<%=id %>"/>
			<c:if test="${dto.id eq id}">
						<a class="btn btn-default" href="delete.do?idx=${dto.bbs_idx}">삭제</a>
						<a class="btn btn-default" href="#" onclick="document.getElementById('fm').submit();">수정완료</a>
			</c:if>
				<a class="btn btn-default" href="list.do">목록으로</a>
					</td>
				</tr>
		</table>
		</form>

	</div>
	<script type="text/javascript" src="./resources/js/jquery-3.1.1.js"></script>
	<script type="text/javascript" src="./resources/js/bootstrap.js"></script>
</body>
</html>



