
<%@page import="org.jsoup.nodes.Element"%>
<%@page import="org.jsoup.select.Elements"%>
<%@page import="org.jsoup.nodes.Document"%>
<%@page import="org.jsoup.Jsoup"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Allday</title>

<!-- Bootstrap core CSS-->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom fonts for this template-->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css">

<!-- Custom styles for this template-->
<link href="css/sb-admin.css" rel="stylesheet">
<link rel="stylesheet" href="css/main.css">
<%
	request.setCharacterEncoding("UTF-8");
	String inputYn = request.getParameter("inputYn");
	String roadFullAddr = request.getParameter("roadFullAddr");
%>
</head>
<script type="text/javascript">
	function giveCollege(name) {
		opener.getCollege(name);
		window.close();
	}
</script>

	<link
		href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
		rel="stylesheet" id="bootstrap-css">

<body>
	<script
		src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
	<script
		src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<!------ Include the above in your HEAD tag ---------->

	<div class="container search-table">
		<div class="search-box">
			<div class="row">
				<div class="modal-header" style="width: 1000px;">
					<h5 class="modal-title" id="ModalLabel"
						style="font-family: 'Hanna'; color: #fff;">학교검색</h5>
				</div>
				<div class="col-md-9">
					<div class="form-group">
						<br> <input type="text" id="myInput" onkeyup="myFunction()"
							class="form-control" placeholder="검색 학교 이름" autofocus="autofocus">
					</div>

					<script>
						$(document)
								.ready(
										function() {
											$("#myInput")
													.on(
															"keyup",
															function() {
																var value = $(
																		this)
																		.val()
																		.toLowerCase();
																$("#myTable li")
																		.filter(
																				function() {
																					$(
																							this)
																							.toggle(
																									$(
																											this)
																											.text()
																											.toLowerCase()
																											.indexOf(
																													value) > -1)
																				});
															});
										});
					</script>
				</div>
			</div>
		</div>

		<%
			Document doc = Jsoup.connect("https://everytime.kr/").get();
			Elements element = doc.select("div.campuslist");
		%>
		<div class="search-list">
			<div class="table" id="myTable">
				<%
					int num = 1;
					for (Element el : element.select(".name")) {
				%>
				<ul class="list-group list-group-flush" id="Body">
					<li class="list-group-item"><a class="d-block mt-3"
						onclick="giveCollege('<%=el.text()%>');" id="name" href="#"
						style="text-align: left; color: #feb134"><%=el.text()%></a></li>

				</ul>

				<%
					num++;
					}
				%>
			</div>



		</div>
	</div>
</body>
</html>