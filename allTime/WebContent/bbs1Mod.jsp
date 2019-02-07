<%@page import="net.ad.mvc.bbs.bbs1InsertReplyController"%>
<%@page import="java.util.Date"%>
<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>


<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
	integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
	integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
	crossorigin="anonymous"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- Bootstrap core JavaScript
    ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>


<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
	integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
	integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
	crossorigin="anonymous"></script>


<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/bbs1.css">

<title>AllDay</title>


</head>
<%
	String id = null;
	String nick = null;
	int no = 0;
	String content = null;
	String title = null;
	String college = null;
	if (session.getAttribute("id") != null) {
		nick = (String) session.getAttribute("nick");
		id = (String) session.getAttribute("id");
		no = Integer.parseInt(request.getParameter("num"));
		title = request.getParameter("title");
		content = request.getParameter("content");
		college = request.getParameter("college");
	}
%>
<body>
	<!-- nav bar -->

	<nav class="navbar navbar-expand  static-top" id="navbar">
	<img alt="" src="alltimeIcon_erase.png" width="30px;"height="40px;">&nbsp;&nbsp;
		<a class="navbar-brand mr-1" href="main.jsp" style="color: white;">AllTIME</a>
		<%
			if (id != null) {
		%>
		<span style="color: white;">&nbsp;<%=nick%>님&nbsp;
		</span>
		<%
			}
		%>
	</nav>
	<div id="body">
		<div id="sidebar">
			<!-- Sidebar -->
			<ul class="sidebar navbar-nav " id="sidebar">
				<li class="nav-item active"><a class="nav-link" href="main.jsp">
						<i class="fas fa-fw fa-tachometer-alt"></i> <span>MAIN</span>
				</a></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="pagesDropdown"
					role="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"> <i class="fas fa-fw fa-folder"></i> <span>INFO</span>
				</a>
					<div class="dropdown-menu" aria-labelledby="pagesDropdown">
						<%
							if (id == null) {
						%>

						<h6 class="dropdown-header">Login</h6>
						<a class="dropdown-item" href="#login" data-toggle="modal">Login</a>
						<a class="dropdown-item" href="#register" data-toggle="modal">Register</a>

						<%
							} else {
						%>

						<h6 class="dropdown-header"><%=nick%>님
						</h6>
						<a class="dropdown-item" href="infoController">My Info</a> <a
							class="dropdown-item" href="logout.jsp">Logout</a>

						<%
							}
						%>

					</div></li>
				<%
					if (id != null) {
				%>
				<li class="nav-item"><a class="nav-link" href="timetable.jsp">
						<i class="fas fa-fw fa-chart-area"></i> <span>시간표</span>
				</a></li>
				<li class="nav-item"><a class="nav-link" href="bbs.jsp"> <i
						class="fas fa-fw fa-table"></i> <span>자유 게시판</span>
				</a></li>
				<li class="nav-item"><a class="nav-link" href="collegeBbs.jsp">
						<i class="fas fa-fw fa-table"></i> <span><%=college%> 게시판</span>
				</a></li>
				<li class="nav-item"><a class="nav-link"
					href="evaluationBbs.jsp"> <i class="fas fa-fw fa-table"></i> <span>강의평가
							게시판</span>
				</a></li>
				<%
					}
				%>
			</ul>

		</div>
		<div class="container"><br><br>
			<form action="bbs1ModController" id="modForm"
				style="width: 70%; margin-left: 270px;">
				<input type="hidden" name="num" value=<%=no%>>
				<div class="form-group">
					<label for="exampleFormControlInput1">제목</label> <input type="text"
						class="form-control" id="title" value=<%=title%> placeholder="제목"
						name="title">
				</div>

				<div class="form-group">
					<label for="exampleFormControlTextarea1">내용</label>
					<textarea class="form-control" id="content" name="content" rows="3"><%=content%></textarea>
				</div>
				<button type="submit" class="btn btn-success">완료</button>
			</form>

		</div>

	</div>
</body>

</html>