<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/main.css">
<div class="modal fade" id="login" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header" >
				<h5 class="modal-title" id="exampleModalLabel">로그인</h5>

			</div>
			<div class="modal-body">


				<form method="post" action="loginController">
					<div class="form-group">
						<div class="form-label-group">
							<label for="inputEmail">ID</label> <input type="text"
								id="inputId" class="form-control" placeholder="아이디"
								name="memberId" required="required" autofocus="autofocus">
						</div>
					</div>
					<div class="form-group">
						<div class="form-label-group">
						<label for="inputPassword">Password</label>
							<input type="password" name="memberPwd" id="inputPassword"
								class="form-control" placeholder="비밀번호" required="required">
							
						</div>
					</div>

					<button class="btn " type="submit" id="btn">Login</button>
				</form>
				<div class="text-center">
					<a class="d-block small mt-3" href="#register" data-toggle="modal">Register an
						Account</a>
				</div>
			</div>
		</div>
	</div>
</div>

<jsp:include page="register.jsp" flush="false"></jsp:include>
</body>

