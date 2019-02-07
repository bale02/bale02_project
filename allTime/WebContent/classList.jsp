
<%@page import="net.ad.mvc.schedule.getScheduleListController"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<script
src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
crossorigin="anonymous">
</script>
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

function putTimeTable(college, name, professor, tp) {
 
    if (college == "전북대") {
       var place = tp.split(",")[0];
       opener.sendClass("auto",name, tp, professor, place);
       window.close();
    }else if (college == "경희대") {
       var place = tp.split("(")[1].split(")")[0];
       opener.sendClass("auto",name, tp, professor, place);
       window.close();
    }

    else if (college == "서울대") {
     var place="";
     for(var i=0;i<tp.split("#").length;i++){
    	 place += tp.split("#")[i].split(" ")[1]+"#";
     }
     opener.sendClass("auto",name, tp, professor, place);
     window.close();

    } 
 }
</script>
<body style="margin-left: 10px; margin-right: 10px;">

	<br>
	<h3 style="margin-left: 15px;">Class Search</h3>
	<div class="col-md-9">
		<input type="text" id="myInput" onkeyup="myFunction()"
			class="form-control" placeholder="Search Class Name">
		<script>
                            $(document).ready(function () {
                                $("#myInput").on("keyup", function () {
                                    var value = $(this).val().toLowerCase();
                                    $("#tbody tr").filter(function () {
                                        $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
                                    });
                                });
                            });
                        </script>
	</div>
	<br>
	<%
		String school = (String) session.getAttribute("college");
		ArrayList<String> list = null;
		if (school.equals("전북대")) {
			list = getScheduleListController.collegeCBNU;
		} else if (school.equals("경희대")) {
			list = getScheduleListController.collegeKHU;
		} else if (school.equals("서울대")) {
			list = getScheduleListController.collegeSNU;
		}
	%>
	<table class="table">
		<thead class="thead" id="classListHead">
			<tr>
				<th scope="col">#</th>
				<th scope="col">구분</th>
				<th scope="col">과목명</th>
				<th scope="col">학점</th>
				<th scope="col">담당교수</th>
				<th scope="col">강의실/시간</th>
				<th scope="col">대상</th>
			</tr>
		</thead>
		<tbody id="tbody">
			<%
				for (int i = 0; i < list.size(); i++) {
			%>
			<tr>
				<td><%=i + 1%></td>
				<td><%=list.get(i).split("#")[0]%></td>
				<td><a href="#"
					onclick='putTimeTable("<%=school%>","<%=list.get(i).split("#")[1]%>",
			"<%=list.get(i).split("#")[3]%>","<%=list.get(i).split("#")[4].replaceAll("\n", "#")%>");'><%=list.get(i).split("#")[1]%></a></td>
				<td><%=list.get(i).split("#")[2]%></td>
				<td><%=list.get(i).split("#")[3]%></td>
				<td><%=list.get(i).split("#")[4]%></td>
				<td><%=list.get(i).split("#")[5]%></td>
			</tr>
			<%
				}
			%>

			<th>
		</tbody>
	</table>



</body>
<!-- Bootstrap core JavaScript-->
<script src="vendor/jquery/jquery.min.js">
	
</script>
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
</html>