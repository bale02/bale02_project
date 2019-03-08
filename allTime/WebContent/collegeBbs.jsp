<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="java.io.PrintWriter"%>

<!doctype html>
<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
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

<!-- Bootstrap core JavaScript-->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Page level plugin JavaScript-->
<script src="vendor/chart.js/Chart.min.js"></script>
<script src="vendor/datatables/jquery.dataTables.js"></script>
<script src="vendor/datatables/dataTables.bootstrap4.js"></script>

<!-- Custom scripts for all pages-->
<script src="js/sb-admin.min.js"></script>

<!-- Demo scripts for this page-->
<script src="js/demo/datatables-demo.js"></script>
<script src="js/demo/chart-area-demo.js"></script>

<!-- Bootstrap core CSS-->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- Custom fonts for this template-->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css">

<!-- Page level plugin CSS-->
<link href="vendor/datatables/dataTables.bootstrap4.css"
	rel="stylesheet">
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

<link rel="stylesheet" href="css/main.css">
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="css/bbs2.css">
<title>AllTime</title>
<script type="text/javascript">
	
<%String id = null;
			String nick = null;
			String college = null;
			if (session.getAttribute("id") != null) {
				nick = (String) session.getAttribute("nick");
				id = (String) session.getAttribute("id");
				college = (String) session.getAttribute("college");
			} else {
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('게시글을 보기 위해서는 로그인이 필요합니다.')");
				script.println("location.href='main.jsp'");
				script.println("</script>");

			}%>
	$(document).ready(function() {
		selectBbs2Function();
	});

	function submitFunction() {

		var title = $('#title').val();
		var content = $('#content').val();
		if (title == "" || content == "") {
			alert("항목을 입력해 주세요.");
		}
		$.ajax({
			url : "bbs2Controller",
			method : "POST",
			data : {
				bbs2Title : title,
				bbs2Content : content
			},
			success : function(result) {
				if (result == 1) {
					selectBbs2Function();
				} else if (result == 0) {
					autoClosingAlert('#warningMessage', 1000);
				} else {
					autoClosingAlert('#dangerMessage', 1000);
				}
			}
		});
		$('#content').val("");
		$('#title').val("");
	}

	function selectBbs2Function() {

		var list = document.getElementById("list");
		$.ajax({
			url : "bbs2ShowController",
			method : "POST",
			data : {
				type : "bbs2"
			},
			success : function(data) {

				var parsed = JSON.parse(data);
				var result = parsed.result;

				for (var i = 0; i < result.length; i++) {
					addCollegeBbs(result[i][0].value, result[i][1].value,
							result[i][2].value, result[i][3].value);
				}
			}
		});
		$('#list').empty();
	}

	function addCollegeBbs(title, nick, date, no) {

		$('#list').append(
				'<ul class="list-group" >' + '<li class="list-group-item">'
						+ '<div class="row">'
						+ '<div class="col-xs-10 col-md-11">'
						+ '<div><a href="bbs2ShowDetailController?num=' + no
						+ '"> ' + title
						+ '</a><div class="mic-info">By: <a href="#">' + nick
						+ '&nbsp;&nbsp;' + '</a> on &nbsp; ' + date
						+ '</div></div></div></div></li></ul>'

		);
	}
	function autoClosingAlert(selector, delay) {
		$(selector).show()

		window.setTimeout(function() {
			$(selector).hide()
		}, delay);
	}

	function searchFunction() {

		$.ajax({
			url : "bbs2ShowController",
			method : "POST",
			data : {
				type : "search",
				title : $('#search').val()
			},
			success : function(data) {

				var parsed = JSON.parse(data);
				var result = parsed.result;

				for (var i = 0; i < result.length; i++) {
					addCollegeBbs(result[i][0].value, result[i][1].value,
							result[i][2].value, result[i][3].value);
				}
			}
		});
		$('#list').empty();
	}
</script>
</head>

<body>
	<!-- nav bar -->

	<nav class="navbar navbar-expand  static-top" id="navbar">
		<img alt="" src="alltimeIcon_erase.png" width="30px;" height="40px;">&nbsp;&nbsp;
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
		<!--  form-->
		<div class="container">
			<br> <br>
			<div class="container">
				<div class="row">


					<div class="panel panel-default widget">
						<div class="panel-heading">
							<span class="glyphicon glyphicon-comment"></span>
							<h3 class="panel-title"><%=college%>
								게시판
							</h3>
							<br>
						</div>
						<div style="width: 1000px;" id="collegeInput">
							<form>
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" id="basic-addon1">제목</span>
									</div>
									<input type="text" class="form-control" placeholder="제목"
										aria-label="Username" aria-describedby="basic-addon1"
										id="title">
								</div>

								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text">내용</span>
									</div>
									<textarea class="form-control" aria-label="With textarea"
										id="content"></textarea>
								</div>
								<br>
								<button type="button" class="btn btn-danger" id="btn"
									onclick="submitFunction();">등록</button>
							</form>
						</div>
						<br>
						<form class="form-inline">
							<div class="form-group mx-sm-3 mb-2">
								<label for="inputPassword2" class="sr-only">검색</label> <input
									type="text" class="form-control" id="search"
									onkeyup="searchFunction();" placeholder="제목">
							</div>
							<button type="button" class="btn btn-primary mb-2" id="btn"
								onclick="searchFunction();">검색</button>
						</form>
						<div id="list"></div>

					</div>
				</div>
			</div>

		</div>
		<jsp:include page="login.jsp" flush="false"></jsp:include>


		<jsp:include page="register.jsp" flush="false"></jsp:include>
</body>


</html>