<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>

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
<link rel="stylesheet" href="css/timetable.css">
</head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<script type="text/javascript">
	function submitFunction() {
		var name = document.getElementById("className").value;
		var tt = document.getElementById("time");
		var time = tt.options[tt.selectedIndex].value;
		var professor = $('#professor').val();
		var place = $('#place').val();
		var day = getDay();
		var days = day.split(",");

		if (name == "" || name == null || professor == "" || professor == null) {
			alert('필수항목을 입력해주세요.');
		} else {
			for (var i = 0; i < days.length - 1; i++) {

				var td = days[i] + "," + "#" + time;
				opener.sendClass("man",name, td, professor, place);
			}

			window.close();
		}
	}
	function getDay() {
		var day = "";
		if (document.getElementById("checkbox1").checked)
			day += document.getElementById("checkbox1").value + ",";
		if (document.getElementById("checkbox2").checked)
			day += document.getElementById("checkbox2").value + ",";
		if (document.getElementById("checkbox3").checked)
			day += document.getElementById("checkbox3").value + ",";
		if (document.getElementById("checkbox4").checked)
			day += document.getElementById("checkbox4").value + ",";
		if (document.getElementById("checkbox5").checked)
			day += document.getElementById("checkbox5").value + ",";
		return day;
	}
</script>
<body style="background-color:  #112f41;">

	<form id="customsubjects" method="post"
		style="margin-left: 100px; margin-right: 100px; margin-top: 30px;">

		<h2>새 수업 추가</h2>

		<div class="input-group">
			<div class="input-group-prepend" >
				<span class="input-group-text">수업명(필수)</span>

			</div>
			<input type="text" id="className" class="form-control">
		</div>
		<p>
		<div class="input-group">
			<div class="input-group-prepend">
				<span class="input-group-text">교수명(필수)</span>

			</div>
			<input type="text" id="professor" class="form-control">
		</div>
		<p>
		<h5>시간/장소</h5>
		<div class="form-check form-check-inline">
			<input class="form-check-input" type="checkbox" id="checkbox1"
				value="a" checked> <label class="form-check-label"
				for="inlineCheckbox1">월</label>
		</div>
		<div class="form-check form-check-inline">
			<input class="form-check-input" type="checkbox" id="checkbox2"
				value="b"> <label class="form-check-label"
				for="inlineCheckbox2">화</label>
		</div>
		<div class="form-check form-check-inline">
			<input class="form-check-input" type="checkbox" id="checkbox3"
				value="c"> <label class="form-check-label"
				for="inlineCheckbox3">수</label>
		</div>
		<div class="form-check form-check-inline">
			<input class="form-check-input" type="checkbox" id="checkbox4"
				value="d"> <label class="form-check-label"
				for="inlineCheckbox3">목</label>
		</div>
		<div class="form-check form-check-inline">
			<input class="form-check-input" type="checkbox" id="checkbox5"
				value="e"> <label class="form-check-label"
				for="inlineCheckbox3">금</label>
		</div>
		<br> <select class="custom-select" id="time">
			<option value="1">1교시</option>
			<option value="2">2교시</option>
			<option value="3">3교시</option>
			<option value="4">4교시</option>
			<option value="5">5교시</option>
			<option value="6">6교시</option>
			<option value="7">7교시</option>
			<option value="8">8교시</option>
		</select> <br>
		<div class="input-group">
			<div class="input-group-prepend">
				<span class="input-group-text">장소</span>
			</div>
			<input type="text" class="form-control" id="place">
		</div>
		<br> <input class="btn btn-primary" type="button" id="regiBtn"
			onclick="submitFunction();" value="Submit"> <input
			class="btn btn-primary" type="reset" value="Reset" id="regiBtn">
	</form>
</body>

</html>