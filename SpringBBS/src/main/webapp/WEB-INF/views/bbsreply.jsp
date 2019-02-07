<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>게시판</title>
<link href="<c:url value="./resources/css/bootstrap.css"/>"
	rel="stylesheet" />

</head>
<script type="text/javascript">
	function changeclear() {
		myform.keyword.value = "";
		myform.keyword.focus();
	}
</script>
<%
String id = null;

if(session.getAttribute("userid") != null ){
 	id=(String)session.getAttribute("userid");
}
%>
<body>

<c:set var="id" value="<%=id %>"/>
	<div class="container">
	<form action="replyinsert.do" id="fm" method="post">
		<table class="table table-striped">
			<%if(id!=null){ %>
				<tr>
					<td width="150">
						<input type="hidden" name="bbs_idx" value="${dto.bbs_idx}">
						<input type="hidden" name="id" value="<%=id%>">
						<input class="form-control" type="text" disabled="disabled" name="id" value="<%=id%>">
					</td>
					<td width="700" colspan="3">
						<input class="form-control" type="text" name="content" placeholder="입력">
					</td>
				<td width="150">
					<a class="btn btn-default" href="#" onclick="document.getElementById('fm').submit();">작성완료</a>
				</td>
				</tr>
				<%} %>
				
				<tr  align="center">
					<td colspan="5">댓 글</td>
				</tr>
				
				<c:forEach var="dto" items="${list}">
			<tr>
				<td width="100">${dto.bbsreply_idx}</td>
				<td width="150">${dto.id}</td>
				<td width="500">${dto.content}</td>
				<td width="200"><fmt:formatDate value="${dto.r_date}" pattern="MM-dd"/></td>
				
				<c:choose>
					<c:when test="${dto.id eq id}">
					<td width="100"><a class="btn btn-default" href="replydelete.do?idx=${dto.bbsreply_idx}&bbs_idx=${dto.bbs_idx}">삭제</a></td>
					</c:when>
					
					<c:otherwise>
					<td width="100"></td>
					</c:otherwise>
				</c:choose>
			</tr>
			</c:forEach>
		
			
	
		</table>
		</form>
	</div>
	<script type="text/javascript" src="./resources/js/jquery-3.1.1.js"></script>
	<script type="text/javascript" src="./resources/js/bootstrap.js"></script>
</body>
</html>



