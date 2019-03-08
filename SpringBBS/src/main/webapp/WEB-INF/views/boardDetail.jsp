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
		<table class="table table-striped">
			<thead>
				<tr>
					<td colspan='2' align="center"> 
	    <font  style="font-size:20pt" >${dto.title}</font>  
	  </td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td rowspan="4" align="center"> <!-- prc -->
	   				<a href="download.do?filename=${dto.img_file_name}&idx=${dto.bbs_idx}">  
	   				<img src ='${pageContext.request.contextPath}/resources/upload/${dto.img_file_name}' width=400 heigh=300 border=0>   
	   				</a>
				   </td>
					<td>번호 : ${dto.bbs_idx }</td>
				</tr>
				<tr>
					<td>시간 : ${dto.bbs_date} </td>
				</tr>
				<tr>
					<td rowspan="2">내용 : ${dto.content}</td>
				</tr>
			</tbody>
				<tr>
					<td colspan="2" align="center">
			<c:set var="id" value="<%=id %>"/>
			<c:if test="${dto.id eq id}">
						<a class="btn btn-default" href="delete.do?idx=${dto.bbs_idx}">삭제</a>
						<a class="btn btn-default" href="preEdit.do?idx=${dto.bbs_idx}">수정</a>
			</c:if>
				<a class="btn btn-default" href="list.do">목록으로</a>
					</td>
				</tr>
		</table>

	</div>
	<script type="text/javascript" src="./resources/js/jquery-3.1.1.js"></script>
	<script type="text/javascript" src="./resources/js/bootstrap.js"></script>
	<hr>
	 <c:import url="/reply_list.do" />
</body>
</html>



