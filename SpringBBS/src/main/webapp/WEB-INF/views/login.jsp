<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>

</head>

<body>
<p style="margin:50px">
	<div class="container" align="center">
			<form action="logincheck.do" id="fm" method="post">
		<table class="table table-striped">
				<tr>
					<td>userid : </td>
					<td><input type="text" name="userid" placeholder="id입력"></td>
				</tr>
				<tr>
					<td>password : </td>
					<td><input type="password" name="pwd"  placeholder="pw입력"></td>
					
				</tr>
				<tr>
					<td align="center" colspan="2">
					<a class="btn btn-default" href="#" onclick="document.getElementById('fm').submit();">로그인</a>
				    <a class="btn btn-default pull-right" href="join.do">회원가입</a>
					</td>
				</tr>
			
		</table>
			</form>
		
		
	</div>
	<script type="text/javascript" src="./resources/js/jquery-3.1.1.js"></script>
	<script type="text/javascript" src="./resources/js/bootstrap.js"></script>
</body>
</html>
