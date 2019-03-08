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
<link rel="stylesheet" href="css/bbs1.css">
<title>AllTime</title>
<script type="text/javascript">
   $(document).ready(function() {
      selectBbsFunction();
   });

   function submitFunction() {

      var title = $('#title').val();
      var content = $('#content').val();
      if(title==""||content==""){
         alert("항목을 입력해 주세요.");
      }
      $.ajax({
         url : "bbs1Controller",
         method : "POST",
         data : {
            bbs1Title : title,
            bbs1Content : content
         },
         success : function(result) {
            if (result == 1) {
               selectBbsFunction();
            }
         }
      });
      $('#content').val("");
      $('#title').val("");
   }

   function selectBbsFunction() {

      $.ajax({
         url : "bbs1ShowController",
         method : "POST",
         data : {
            type : "bbs1"
         },
         success : function(data) {

            var parsed = JSON.parse(data);
            var result = parsed.result;

            for (var i = 0; i < result.length; i++) {
               addBbs(result[i][0].value, result[i][1].value,
                     result[i][2].value, result[i][3].value);
            }
         }
      });
      $('#bbsBody').empty();
   }

   function addBbs(title, nick, date, no) {

      $('#bbsBody').append(
            '<ul class="list-group" id="" >'
                  + '<li class="list-group-item">' + '<div class="row">'
                  + '<div class="col-xs-10 col-md-11">'
                  + '<div><a href="bbs1ShowDetailController?num=' + no
                  + '"> ' + title
                  + '</a><div class="mic-info">By: <a href="#">' + nick
                  + '&nbsp;&nbsp;' + '</a> on &nbsp; ' + date
                  + '</div></div></div></div></li></ul>'

      );
   }

   function searchFunction() {

      $.ajax({
         url : "bbs1ShowController",
         method : "POST",
         data : {
            type : "search",
            title : $('#search').val()
         },
         success : function(data) {

            var parsed = JSON.parse(data);
            var result = parsed.result;

            for (var i = 0; i < result.length; i++) {
               addBbs(result[i][0].value, result[i][1].value,
                     result[i][2].value, result[i][3].value);
            }
         }
      });
      $('#bbsBody').empty();
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
      <div class="container">
         <br> <br>
         <h3 style="font-family: 'Hanna'; text-align: center; color: #112f41;">자유게시판</h3>

         <form role="form" id="bbs1In">
            <fieldset>
            <%if(id !=null){ %>
               <div class="form-group">
                  <input type="text" class="form-control" id="title"
                     placeholder="제목" autofocus="autofocus"></input><br>
                  <textarea class="form-control" rows="3" id="content"
                     placeholder="내용을 입력해주세요"></textarea>
               </div>
               <button onclick="submitFunction();" class="[ btn btn-success ]"
                  id="btn" data-loading-text="Loading...">등록</button>
         <%} %>
            </fieldset>
         </form>
         <br>
         <form class="form-inline" id="searchForm">
            <input class="form-control mr-sm-2"  type="search" style="font-family: 'Hanna';
            margin-left: 200px;"id="search";
               placeholder="Search" aria-label="Search" onkeyup="searchFunction();">
            <button  id="searchBtn" class="btn btn-outline-success my-2 my-sm-0" type="button"
            onclick="searchFunction();">Search</button>
         </form>
         <div
            style="position: relative; overflow: auto; height: -webkit-fill-available;">
            <br>
            
            <div class="panel-body" id="bbsBody"
               style="margin-left: 15%; width: 80%; position: absolute;"></div>
         </div>
      </div>

   </div>
   <jsp:include page="login.jsp" flush="false"></jsp:include>


   <jsp:include page="register.jsp" flush="false"></jsp:include>
</body>


</html>