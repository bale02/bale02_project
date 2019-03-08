<%@page import="net.ad.mvc.bbs.bbs1InsertReplyController"%>
<%@page import="java.util.Date"%>
<%@page import="java.io.PrintWriter"%>

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>


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
	$(document).ready(function() {

		selectBbs2ReplyFunction();
	});

	function addComment() {

		var comment = $('#reply').val();
		var num = $('#num').val();
		$.ajax({
			url : "bbs2InsertReplyController",
			method : "POST",
			data : {
				num : num,
				bbs2ReplyContent : comment
			},
			success : function(result) {
				if (result == 1) {
					selectBbs2ReplyFunction();
				} 
			}
		});
		$('#reply').val("");

	}

	function selectBbs2ReplyFunction() {
		$('#replyList').val("");
		var num = $('#num').val();
		$.ajax({
			url : "bbs2ShowReplyController",
			method : "POST",
			data : {
				num : num
			},
			success : function(data) {

				var parsed = JSON.parse(data);
				var result = parsed.result;

				for (var i = 0; i < result.length; i++) {

					addReply(result[i][0].value, result[i][1].value,
							result[i][2].value, result[i][3].value,
							result[i][4].value, result[i][5].value);
				}
			}
		});
		$('#replyList').empty();
	}

	function addReply(name, comment, id, date, replynum, num) {

		$('#replyList')
				.append(
						'<strong class="pull-left primary-font" style= "font-family: \'Hanna\' ">'
						+ name
						+ '&nbsp;</strong><small class="pull-right"> <span'
					+ '   class="glyphicon glyphicon-time"></span>'
						+ date
						+ '</small><br><li class="ui-state-default">'
						+ comment
						+ '&nbsp; &nbsp; &nbsp; <input type="button" class="btn btn-secondary" id="btn"'
						+ 'value ="삭제" style="font-size:x-small" onclick = "location.href=\'bbs1DeleteReplyController?name='
						+ id + '&replynum=' + replynum + '&num=' + num
						+ '\'"></li><br>');
	}
	function autoClosingAlert(selector, delay) {
		$(selector).show()

		window.setTimeout(function() {
			$(selector).hide()
		}, delay);
	}
</script>
</head>
<%
	String id = null;
	String nick = null;
	int no = 0;
	String title = null;
	String content = null;
	String writer = null;
	String date = null;
	String bbsId = null;
	String college = null;
	if (session.getAttribute("id") != null) {
		nick = (String) session.getAttribute("nick");
		id = (String) session.getAttribute("id");
		no = Integer.parseInt(request.getParameter("num"));
		title = (String) request.getAttribute("bbs2Title");
		content = (String) request.getAttribute("bbs2Content");
		writer = (String) request.getAttribute("bbs2Nickname");
		date = (String) request.getAttribute("bbs2Date");
		bbsId = (String) request.getAttribute("bbs2Id");
		college = (String) session.getAttribute("college");
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
		<!--  form-->

		<div class="container-fluid">

			<!-- Breadcrumbs-->
			<ol class="breadcrumb">
				<li class="breadcrumb-item active"><%=college%> 게시판</li>
			</ol>

		</div>

		<div class="how-section1"
			style="margin-left: 300px; align-content: center;">
			<div id="view" style="padding-left: 15%">
				<div class="col-md-6">
					<h2
						style="text-align: center; margin-left: 20%; font-family: 'Hanna'; text-align: center; color: #112f41;">
						제목 :
						<%=title%></h2>
					<h4 class="subheading"
						style="text-align: center; margin-left: 20%; font-family: 'Hanna'; text-align: center; color: #112f41;">
						작성자:
						<%=writer%></h4>
					<br>
					<p class="text-muted"
						style="overflow: auto; margin-left: 10%; border-color: #ed553b; border: thick; width: 700px; height: 300px; border: solid; padding-left: 20px; padding-top: 10px;"><%=content%></p>
				</div>
				<div class="mx-auto"
					style="align-items: center; justify-content: center; margin-bottom: 2rem; padding-left: 5%;">
					<%
						if (id.equals(bbsId)) {
					%>

					<button type="button" class="btn btn-secondary" id="btn"
						onclick="location.href='bbs2Mod.jsp?num=<%=no%>&title=<%=title%>&content=<%=content%>'">수정</button>
					&nbsp;&nbsp;
					<button type="button" class="btn btn-secondary" id="btn"
						onclick="location.href='bbs2DeleteController?num=<%=no%>'">삭제</button>
					<%
						} else if (id.equals("admin")) {
					%>
					&nbsp;&nbsp;
					<button type="button" class="btn btn-secondary" id="btn"
						onclick="location.href='bbs2DeleteController?num=<%=no%>'">삭제</button>
					<%
						}
					%>
				</div>
			</div>
			<div class="container">
				<div class="col-lg-10 col-sm-10 text-center">
					<div class="well">
						<h4
							style="font-family: 'Hanna'; text-align: center; color: #112f41;">댓글</h4>
						<div class="input-group">
							<input type="hidden" id="num" value=<%=no%> /> <input
								type="text" id="reply"
								class="form-control input-sm chat-input"
								placeholder="댓글을 입력해주세요" />&nbsp; <span class="input-group-btn"
								onclick="addComment()"> <a href="#"
								class="btn btn-primary btn-sm" id="btn"><span
									class="glyphicon glyphicon-comment"></span> 입력</a>
							</span>
						</div>
						<hr data-brackets-id="12673">

					</div>

				</div>
				<ul data-brackets-id="12674" id="replyList"
					class="list-unstyled ui-sortable">
				</ul>
			</div>
		</div>
	</div>

	<jsp:include page="login.jsp" flush="false"></jsp:include>


	<jsp:include page="register.jsp" flush="false"></jsp:include>
</body>


</html>