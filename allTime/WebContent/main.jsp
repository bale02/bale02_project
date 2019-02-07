<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>


<!doctype html>
<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
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
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/main.css">
<title>AllTime</title>
<script type="text/javascript">
<%
String id = null;
String nick = null;
String college = null;
if (session.getAttribute("id") != null) {
	nick = (String) session.getAttribute("nick");
	id = (String) session.getAttribute("id");
	college = (String) session.getAttribute("college");
}
%>
	$(document).ready(function() {
		getbbs();
		getbbs2();
		getbbs3();
		chatSelectFunction();
		setInterval(function() {
			chatSelectFunction();
		}, 3000);
	});
	function sendloaction(){
		
		if(<%=id%>==null){
			alert("게시글을 보기 위해선 로그인이 필요합니다.");
		}
	}
	function getbbs3() {
		$.ajax({
			url : "bbs3MainShowController",
			method : "POST",

			success : function(data) {
				var parsed = JSON.parse(data);
				var result = parsed.result;
				for (var i = 0; i < result.length; i++) {
					addBbs3(result[i][0].value, result[i][1].value,
							result[i][2].value, result[i][3].value,
							result[i][4].value, result[i][5].value);
				}
			}
		});
		$('#bbsBody3').empty();
	}
	function addBbs3(classtitle, classteacher, title, nick, date, no) {

		$('#bbsBody3')
				.append('<li class="list-group-item">' + title + '</li>');
	}
	function getbbs2() {
		$.ajax({
			url : "bbs2MainShowController",
			method : "POST",

			success : function(data) {
				var parsed = JSON.parse(data);
				var result = parsed.result;
				for (var i = 0; i < result.length; i++) {
					addBbs2(result[i][0].value, result[i][1].value,
							result[i][2].value, result[i][3].value);
				}
			}
		});
		$('#bbsBody2').empty();
	}
	function addBbs2(title, nick, date, no) {
		$('#bbsBody2').append('<li class="list-group-item">' + title + '</li>');
	}
	function getbbs() {

		$.ajax({
			url : "mainShowbbsController",
			method : "POST",

			success : function(data) {
				var parsed = JSON.parse(data);
				var result = parsed.result;
				for (var i = 0; i < result.length; i++) {
					addBbs(result[i][0].value, result[i][1].value,
							result[i][2].value, result[i][3].value);
				}
			}
		});
		$('#bbsBody1').empty();
	}
	function addBbs(title, nick, date, no) {
		$('#bbsBody1').append('<li class="list-group-item">' + title + '</li>');
	}

	function chatSendFunction() {
		var chatContent = $('#chatContent').val();

		$.ajax({
			url : "chatInsertController",
			method : "POST",
			data : {

				chatContent : chatContent
			},
			success : function(data) {
				if (data == 1) {
					chatSelectFunction();
				}
			}
		});
		$("#msg").empty();
		$('#chatContent').val("");
	}
	function chatSelectFunction() {

		$.ajax({
			url : "chatShowController",
			method : "POST",

			success : function(data) {

				var parsed = JSON.parse(data);
				var result = parsed.result;
				for (var i = 0; i < result.length; i++) {
					addChat(result[i][0].value, result[i][1].value,
							result[i][2].value, result[i][3].value);
				}
			}
		});
		$("#msg").empty();
	}

	function addChat(nick, comment, date, id) {

		var chatId = $('#chatId').val();

		if (id == chatId) {

			$('#msg').append(
					'<div class="outgoing_msg"><div class="sent_msg"><font size="4px;">'
							+ comment + '</font><br><span class="time_date"> ' + date
							+ '</span></div></div>');
			$("#msg").scrollTop($("#msg")[0].scrollHeight);
		} else {

			$('#msg')
					.append(
							'<div class="incoming_msg"><div class="incoming_msg_img"></div><div class="received_msg"><div class="received_withd_msg">'
									+'<font size="4px;" color="black">'
									+ nick +'</font><font size="4px;">'
									+ ' : '+comment
									+ '</font><br>'
									+ date
									+ '<br></div></div></div>');
			$("#msg").scrollTop($("#msg")[0].scrollHeight);
		}
	}
</script>
</head>

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

	<div id="sidebar">
		<!-- Sidebar -->
		<ul class="sidebar navbar-nav " id="sidebar_u">
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
	<!--  main 본문-->
	<div class="container">
		<div class="row" id="bbsMain">
			<div class="col-md-4">
				<div class="card" style="width: 18rem;" id="bbs1">
					<div class="card-header" id="head1">자유 게시판</div>
					<ul class="list-group list-group-flush" id="bbsBody1">

						<li class="list-group-item">Vestibulum at eros</li>
					</ul>
					<button type="button" class="btn btn-lg btn-block  btn-custom" id="bbs3"
									onclick="location.href='bbs.jsp'"  style="background-color: #112f41;">더보기</button>
				</div>
			</div>

			<div class="col-md-4">
				<div class="card" style="width: 18rem;" id="bbs2">
					<div class="card-header" id="head2">학교별 게시판</div>
					<ul class="list-group list-group-flush" id="bbsBody2">
						<li class="list-group-item">Cras justo odio</li>
					</ul>
					<button type="button" class="btn btn-lg btn-block  btn-custom" id="bbs3"
									onclick="location.href='collegeBbs.jsp'" style="background-color:#068587; ">더보기</button>
				</div>
			</div>

			<div class="col-md-4">
				<div class="card" style="width: 18rem;" id="bbs3">
					<div class="card-header" id="head3">강의평가 게시판</div>
					<ul class="list-group list-group-flush" id="bbsBody3">

						<li class="list-group-item">Dapibus ac facilisis in</li>
					</ul>
					<button type="button" class="btn btn-lg btn-block  btn-custom" id="bbs3"
									onclick="sendloaction();"  style="background-color: #ed553b;">더보기</button>
				</div>
			</div>
		</div>

	</div>
	<br><br><br>
	<div class="container" align="center">
		<br>
		<h3 class=" text-center" style="font-family: 'Hanna';">채팅</h3>
		<br>


		<div class="messaging">
			<div class="mesgs">
				<div class="msg_history" id="msg"></div>
				<%
					if (id != null) {
				%>
				<br>
				<div class="type_name" align="left">
					<input type="text" value=<%=nick%> class="write_name"
						placeholder="이름" id="chatName" disabled="disabled" /> <input
						type="hidden" id="chatId" value=<%=id%> class="write_name"
						placeholder="이름" id="chatName" disabled="disabled" />
				</div>

				<p>
				<div class="type_msg" >
					<div class="input_msg_write">
						<input type="text" class="write_msg" placeholder="내용"
							id="chatContent"
							onkeypress="if(event.keyCode==13) {chatSendFunction(); return false;}" />
						<button class="msg_send_btn" id="btn"
							onclick="chatSendFunction();">→</button>
					</div>
				</div>


				<%
					}
				%>
			</div>
		</div>

	</div>

	<jsp:include page="login.jsp" flush="false"></jsp:include>


	<jsp:include page="register.jsp" flush="false"></jsp:include>
</body>




</html>