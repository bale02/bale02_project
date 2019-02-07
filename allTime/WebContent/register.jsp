<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/main.css">
<script type="text/javascript">
	var memberid = document.getElementById("memberId");
	var checkRequest = new XMLHttpRequest();

	function checkFunction() {
		var check = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
		var id = document.getElementById("memberId").value;
		if(check.test(id)){
			alert("한글을 포함하고있습니다.");
			return;
		}
		checkRequest.open("Post",
				"./idcheckController?id="
						+ encodeURIComponent(document
								.getElementById("memberId").value), true);
		checkRequest.onreadystatechange = checkProcess;
		checkRequest.send(null);
	}
	function checkProcess() {

		if (checkRequest.readyState == 4 && checkRequest.status == 200) {
			var result = checkRequest.responseText;

			if (result != 0) {
				alert('아이디가 중복되었습니다.');
			} else {
				alert('사용가능한 아이디입니다');
			}
		}
	}
	function openApi() {

		var pop = window.open("collegePopup.jsp", "pop",
				"width=570,height=420, scrollbars=yes, resizable=yes");

	}
	function getCollege(name) {
		document.getElementById("memberCollege").value = name;

	}
</script>

<body>
	<div class="modal fade" id="register" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">회원가입</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">

					<form id="registerForm" method="post" action="registerController"
						accept-charset="utf-8">
						<div class="form-group">
							<div class="form-row">
								<div class="col-md-6">
									<div class="form-label-group">
										<input type="text" id="memberId" name="memberId"
											class="form-control" placeholder="ID" required="required"
											autofocus="autofocus"> <label for="memberId">아이디</label>
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-label-group">
										<a class=" btn btn-primary btn-block" id="btn"
											onclick="checkFunction();" href="#">중복체크</a>
									</div>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="form-row">
								<div class="col-md-6">
									<div class="form-label-group">
										<input type="text" id="memberName" name="memberName"
											class="form-control" placeholder="Name" required="required">
										<label for="memberName">이름</label>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-label-group">
										<input type="text" id="memberNick" name="memberNick"
											class="form-control" placeholder="Nick Name"
											required="required"> <label for="nickName">별명</label>
									</div>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="form-row">
								<div class="col-md-6">
									<div class="form-label-group">
										<input type="password" id="memberPwd" name="memberPwd"
											class="form-control" placeholder="Password"
											required="required"> <label for="inputPassword">비밀번호</label>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-label-group">
										<input type="password" id="confirmPassword"
											class="form-control" placeholder="Confirm password"
											required="required"> <label for="confirmPassword">비밀번호
											확인</label>
									</div>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="form-label-group">
								<input type="email" id="memberEmail" name="memberEmail"
									class="form-control" placeholder="Email address"
									required="required"> <label for="inputEmail">이메일
									주소</label>
							</div>
						</div>
						<div class="form-group">
							<div class="form-label-group">
								<p>
									<label for="college">college</label>&nbsp;
									<button type="button" class="btn btn-primary btn-block"
										id="btn" onclick="openApi();">검색</button>
								</p>
								<input type="text" id="memberCollege" name="memberCollege"
									class="form-control" placeholder="College" required="required"
									readonly="readonly">

							</div>
						</div>
						<button class="btn btn-primary btn-block" id="btn" type="submit">회원가입</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>