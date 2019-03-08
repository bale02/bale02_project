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
		<form action="bbsinsert.do" id="fm" enctype="multipart/form-data"
			method="post">
			<input type="hidden" name="id" value="<%=id%>"> 
			제목 : <input	class="form-control" type="text" name="title"
				placeholder="제목을 입력해주세요"><br> 
			내용 : <textarea style="resize : none;" class="form-control" rows="7" name="content"
				placeholder="내용을 입력해주세요"></textarea>
			<br> <input type="file" name="upload_f">
			<p>
				<br>
				<br>

				<a class="btn btn-default" href="#" onclick="document.getElementById('fm').submit();">작성완료</a>
				<a class="btn btn-default pull-right" href="list.do">취소</a>
		</form>

	</div>
	<script type="text/javascript" src="./resources/js/jquery-3.1.1.js"></script>
	<script type="text/javascript" src="./resources/js/bootstrap.js"></script>
</body>
</html>



