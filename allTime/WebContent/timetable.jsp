<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>


<!doctype html>
<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
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
<link rel="stylesheet" href="css/timetable.css">
<title>AllTime</title>
<script type="text/javascript">
	$(document).ready(function() {
		getTable();
	});

	function openSubjectSearch() {
		var classList = window.open("classList.jsp", "classList",
				"width=1400,height=500, scrollbars=yes, resizable=yes");
	}

	function openRegister() {

		var pop = window.open("classRegister.jsp", "pop",
				"width=850,height=420, scrollbars=yes, resizable=yes");
	}
	function sendClass(type, name, td, professor, place) {
		$.ajax({
			url : "putScheduleTableController",
			method : "POST",
			data : {
				type : type,
				classTitle : name,
				classTeacher : professor,
				classTime : td,
				classPlace : place
			},
			success : function(result) {
				if (result == 1) {
					getTable();
				} else if (result == -1) {
					alert('이미 해당시간에 수업이 존재합니다.');
					return;
				} else if (result == 0) {
					alert("졸업논문은 시간과 장소가 정해져있지 않습니다.");
					return;
				}
			}
		});
	}
	function getTable() {
		$.ajax({
			url : "showMyTimetableController",
			method : "POST",

			success : function(data) {
				var parsed = JSON.parse(data);
				var result = parsed.result;
				for (var i = 0; i < result.length; i++) {
					addTable(result[i][0].value, result[i][1].value,
							result[i][2].value, result[i][3].value);
				}
			}
		});
	}

	function addTable(title, teacher, place, time) {

		var a = time.split("#")[0];
		var n = time.split("#")[1];

		for (var i = 0; i < a.length - 1; i = i + 2) {

			var t = document.getElementById(n + a[i]);
			t.innerHTML = '<a href="deleteScheduleController?title=' + title
					+ '&teacher=' + teacher + ' "> <h5>' + title + '</a>'
					+ '</h5>' + teacher + '<br>' + place;

		}
	}
</script>
</head>
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
	<div>
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

		<div class="container" id="timeContainer" >
			<br> <br>
			<h3 style="color: #068587; font-family: 'Hanna';" >시간표</h3>
			<br>
			<table class="table table-bordered table-striped table-hover" >
				<thead>
					<tr class="table-head">
						<th class="col-md-2" style="width: 10%;"></th>
						<th class="col-md-2" style="width: 12%;">월</th>
						<th class="col-md-2" style="width: 12%;">화</th>
						<th class="col-md-2" style="width: 12%;">수</th>
						<th class="col-md-2" style="width: 12%;">목</th>
						<th class="col-md-2" style="width: 12%;">금</th>
						<th class="col-md-2" style="width: 12%;">토</th>
					</tr>
				</thead>
				<tbody>

					<tr id="tra">
						<th scope="row">1교시</th>
						<td id="1a"></td>
						<td id="1b"></td>
						<td id="1c"></td>
						<td id="1d"></td>
						<td id="1e"></td>
						<td id="1f"></td>

					</tr>
					<tr id="trb">
						<th scope="row">2교시</th>
						<td id="2a"></td>
						<td id="2b"></td>
						<td id="2c"></td>
						<td id="2d"></td>
						<td id="2e"></td>
						<td id="2f"></td>
					</tr>
					<tr id="tra">
						<th scope="row">3교시</th>
						<td id="3a"></td>
						<td id="3b"></td>
						<td id="3c"></td>
						<td id="3d"></td>
						<td id="3e"></td>
						<td id="3f"></td>
					</tr>
					<tr id="trb">
						<th scope="row">4교시</th>
						<td id="4a"></td>
						<td id="4b"></td>
						<td id="4c"></td>
						<td id="4d"></td>
						<td id="4e"></td>
						<td id="4f"></td>
					</tr>
					<tr id="tra">
						<th scope="row">5교시</th>
						<td id="5a"></td>
						<td id="5b"></td>
						<td id="5c"></td>
						<td id="5d"></td>
						<td id="5e"></td>
						<td id="5f"></td>
					</tr>
					<tr id="trb">
						<th scope="row">6교시</th>
						<td id="6a"></td>
						<td id="6b"></td>
						<td id="6c"></td>
						<td id="6d"></td>
						<td id="6e"></td>
						<td id="6f"></td>
					</tr>
					<tr id="tra">
						<th scope="row">7교시</th>
						<td id="7a"></td>
						<td id="7b"></td>
						<td id="7c"></td>
						<td id="7d"></td>
						<td id="7e"></td>
						<td id="7f"></td>
					</tr>
					<tr id="trb">
						<th scope="row">8교시</th>
						<td id="8a"></td>
						<td id="8b"></td>
						<td id="8c"></td>
						<td id="8d"></td>
						<td id="8e"></td>
						<td id="8f"></td>
					</tr>
					<tr id="tra">
						<th scope="row">9교시</th>
						<td id="9a"></td>
						<td id="9b"></td>
						<td id="9c"></td>
						<td id="9d"></td>
						<td id="9e"></td>
						<td id="9f"></td>
					</tr>
					<tr id="trb">
						<th scope="row">10교시</th>
						<td id="10a"></td>
						<td id="10b"></td>
						<td id="10c"></td>
						<td id="10d"></td>
						<td id="10e"></td>
						<td id="10f"></td>
					</tr>
				</tbody>
			</table>
			<div class="mx-auto"
				style="display: flex; align-items: center; justify-content: center; margin-bottom: 2rem;">
				<button type="button" class="btn btn-primary" id="btn"
					data-toggle="modal" onclick="openRegister();">수업 등록하기</button>
				&nbsp &nbsp
				<button type="button" class="btn btn-primary" data-toggle="modal"
					id="btn" data-target="#exampleModal" onclick="openSubjectSearch();">수업
					목록에서 찾기</button>
			</div>
		</div>

	</div>

	<jsp:include page="login.jsp" flush="false"></jsp:include>

	<jsp:include page="register.jsp" flush="false"></jsp:include>

</body>




</html>