<%@page import="java.util.ArrayList"%>
<%@page import="com.funfit.dao.*"%>
<%@page import="com.funfit.model.*"%>
<%@page import="com.funfit.controller.*"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.util.List"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="">
    <link rel="canonical" href="https://getbootstrap.com/docs/3.4/examples/navbar/">

    <title>Funfit Zumba Update Participant</title>

    <!-- Bootstrap core CSS -->
    <link href="https://getbootstrap.com/docs/3.4/dist/css/bootstrap.min.css" rel="stylesheet">
	
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="https://getbootstrap.com/docs/3.4/assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="navbar.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="https://getbootstrap.com/docs/3.4/assets/js/ie-emulation-modes-warning.js"></script>

  </head>
<body>

<div class="container">

      <!-- Static navbar -->
      <nav class="navbar navbar-default">
        <div class="container-fluid">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="HomePage.html">Funfit Zumba</a>
          </div>
          <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
              <li class="nav-item"><a href="BatchParticipants.jsp">Batch Participants</a></li>
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Batches<span class="caret"></span></a>
                <ul class="dropdown-menu">
                  <li><a href="AddBatch.html">Add Batch</a></li>
                  <li><a href="Batches.jsp">Batches</a></li>
                </ul>
              </li>
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Participants <span class="caret"></span></a>
                <ul class="dropdown-menu">
                  <li><a href="AddParticipant.html">Add Participant</a></li>
                  <li><a href="Participants.jsp">Participants</a></li>
                </ul>
              </li>
            </ul>
          </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
      </nav>
	<div class="jumbotron">
        <h2>Update Participant</h2>
        <%
        int pid2Update = Integer.parseInt(request.getParameter("pid2Update"));
        JdbcFunfitDao dao = new JdbcFunfitDao();
       	Participant participant2Update = dao.findParticipantByPid(pid2Update);

        String participantDescription =  "Participant #" + pid2Update + "<br>" + "Name: " + participant2Update.getName() + "<br>" + "Phone Number: " + participant2Update.getPhone() + "<br>" + "Email: " + participant2Update.getEmail() + "<br>" + "Batch ID: " + participant2Update.getBid();
        %>
        <h4><%= participantDescription %></h4>
        <br>
  <div class="bs-example" data-example-id="basic-forms">
    <form action="updateParticipant" method="post">
    <div class="form-group">
    	<label for="pid2Update">Participant ID</label>
    	<input type="text" name="pid2Update" class="form-control" id="pid2Update" value="<%= pid2Update %>" readonly>
    </div>
    <div class="form-group">
        <label for="newParticipantName">Update Participant Name</label>
        <input type="text" name="newParticipantName" class="form-control" id="newParticipantName" placeholder="New Participant Name" value="<%= participant2Update.getName() %>">
      </div>
   	  <div class="form-group">
        <label for="newParticipantPhone">Update Participant Phone Number</label>
        <input type="text" name="newParticipantPhone" class="form-control" id="newParticipantPhone" placeholder="New Participant Phone Number" value="<%= participant2Update.getPhone() %>">
      </div>
      <div class="form-group">
        <label for="newParticipantEmail">Update Participant Email Address</label>
        <input type="email" name="newParticipantEmail" class="form-control" id="newParticipantEmail" placeholder="New Participant Email" value="<%= participant2Update.getEmail() %>">
      </div>
      <div class="form-group">
      	<label for="newParticipantBid">Update Participant Batch ID</label>
      	<input type="text" name="newParticipantBid" class="form-control" id="newParticipantBid" placeholder="New Participant Bid" value="<%= participant2Update.getBid() %>">
      </div>
      <br>
      <button type="submit" class="btn btn-default">Submit</button>
    </form>
       </div>
      </div>

  </div> <!-- /container -->
  <script src="https://code.jquery.com/jquery-1.12.4.min.js" integrity="sha384-nvAa0+6Qg9clwYCGGPpDQLVpLNn0fRaROjHqs13t4Ggj3Ez50XnGQqc/r8MhnRDZ" crossorigin="anonymous"></script>
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
    <script src="https://getbootstrap.com/docs/3.4/dist/js/bootstrap.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="https://getbootstrap.com/docs/3.4/assets/js/ie10-viewport-bug-workaround.js"></script>

</body>
</html>