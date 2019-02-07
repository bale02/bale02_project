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
function chkDupId(){
	  var userId = $('#userid').val();
	  
	  if($("#userid").val() == ''){alert('ID를 입력해주세요.'); return;}
	  
	  $.ajax({
	     type : 'POST',  
	     data:"userid="+ prmId,
	     dataType : 'text',
	     url : '/checkid.do',  
	     success : function(rData, textStatus, xhr) {
	      var chkRst = rData;
	      if(chkRst == 0){
	       alert("등록 가능 합니다.");
	       $("#idChk").val('Y');
	      }else{
	       alert("중복 되어 있습니다.");
	       $("#idChk").val('N');
	      }
	     },
	     error : function(xhr, status, e) {  
	      alert(e);
	     }
	  });  
	 }


	 // 등록....
	 function insertChk(){
	  
	  var frm = document.companyForm; 
	  
	  if(!chkVal('insertId','아이디'))return false;
	  if($("#idChk").val() == 'N'){alert('ID체크를 해주세요.'); return;}

</script>

<body>
	<div class="container">
		<form action="memberinsert.do" id="fm" method="post">
		<table class="table table-striped">
			<thead>
				<tr>
					<th colspan="3"> 회원가입 </th>
					
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>아이디</td>
					<td><input type="text" name="userid" id="userid" maxlength="30" /> &nbsp
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" name="pwd"></td>
					
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name ="name" placeholder="홍길동"></td>					
				</tr>
				<tr>
					<td>성별</td>
					<td>
					<input type="radio" name="gender" value="male" checked>남자 &nbsp
	 	 			<input type="radio" name="gender" value="female">여자</td>					
				</tr>
		</table>
		
		
					<a class="btn btn-default" href="#" onclick="document.getElementById('fm').submit();">회원가입</a>
				    <a class="btn btn-default pull-right" href="list.do">취소</a>
		</form>
	</div>
	<script type="text/javascript" src="./resources/js/jquery-3.1.1.js"></script>
	<script type="text/javascript" src="./resources/js/bootstrap.js"></script>
</body>
</html>



