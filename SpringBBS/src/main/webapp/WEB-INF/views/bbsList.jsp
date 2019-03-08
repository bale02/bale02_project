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
	<div class="container">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>날짜</th>
					<th>댓글</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="dto" items="${naver}">
			<tr>
				<td>${dto.bbs_idx}</td>
				<td><a href="detail.do?idx=${dto.bbs_idx}">${dto.title}</a></td>
				<td>${dto.id}</td>
				<td>${dto.bbs_date}</td>
				<td>${dto.cnt}</td>
			</tr>
			</c:forEach>
				<tr align="center">
					<td colspan="5">
					<ul class="pagination">
					<c:if test="${startpage>10}">
					<li><a href="list.do?pageNum=${startpage-10}">[이전]&nbsp</a></li>
				</c:if>
						<li><c:forEach var="i" begin="${startpage}" end="${endpage}">
					<c:choose>
						<c:when test="${i==pageNUM}">
							<font>[${i}]&nbsp</font>
						</c:when>
						<c:otherwise>
							<a href="list.do?pageNum=${i}${returnpage}">[${i}]</a>
						</c:otherwise>
					</c:choose>
				</c:forEach> </li>
						<c:if test="${endpage<pagecount}">
					<a href="list.do?pageNum=${startpage+10}">[다음]</a>
				</c:if>
					</ul>
					</td>
				</tr>
				<tr align="center">
			<td colspan="5">
				<form id="fm" action="list.do">
					<select name="keyfield" onchange="changeclear();">

						<option value="all" selected>전체검색</option>
						<option value="id"
							<c:if test="${skey == 'id'}">selected</c:if>>이름조회</option>
						<option value="title"
							<c:if test="${skey == 'title' }">selected</c:if>>제목검색</option>
						<option value="content"
							<c:if test="${skey == 'content' }">selected</c:if>>내용검색</option>
					</select> <input type="text" name="keyword" size=10 value="${sval}">
					<a class="btn btn-default" href="#" onclick="document.getElementById('fm').submit();">검색</a>
				</form>
			</td>
		</tr>
			</tbody>
	
		</table>
		
		<%if(id !=  null){ %>
		<a class="btn btn-default pull-right" href="write.do">글쓰기</a>
		<a class="btn btn-default pull-right" href="logout.do">로그아웃</a>
		<%}else{ %>
		<a class="btn btn-default pull-right" href="login.do">로그인</a>
		<a class="btn btn-default pull-right" href="join.do">회원가입</a>	
		<%} %>
	</div>
	<script type="text/javascript" src="./resources/js/jquery-3.1.1.js"></script>
	<script type="text/javascript" src="./resources/js/bootstrap.js"></script>
</body>
</html>



