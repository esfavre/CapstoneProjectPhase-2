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

    <title>Funfit Zumba Update Batch</title>

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
        <h2>Update Batch</h2>
        <%
        int bid2Update = Integer.parseInt(request.getParameter("bid2Update"));
        JdbcFunfitDao dao = new JdbcFunfitDao();
        Batch batch2Update = dao.findBatchByBid(bid2Update);
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("E, MMM dd yyyy");
		DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
		String selectedM = "";
		String selectedE = "";
		String selectedZ = "";
		String selectedZG = "";
		
		if(batch2Update.getBatch_Group().equals("Morning")){
			selectedM = "selected";
		} else {
			selectedE ="selected";
		}
		
		if(batch2Update.getBatch_Name().equals("Zumba")){
			selectedZ = "selected";
		}else {
			selectedZG ="selected";
		}
        String batchDescription =  "Batch #" + bid2Update + " " + batch2Update.getBatch_Group() + " " + batch2Update.getBatch_Name() + " on " +  batch2Update.getBatch_Date_Time().format(dateFormat) + " at " + batch2Update.getBatch_Date_Time().format(timeFormat);
        %>
        <h3><%= batchDescription %></h3>
        <br>
  <div class="bs-example" data-example-id="basic-forms">
    <form action="updateBatch" method="post">
    <div class="form-group">
    	<label for="bid2Update">Batch ID</label>
    	<input type="text" name="bid2Update" class="form-control" id="bid2Update" value="<%= bid2Update %>" readonly>
    </div>
   	  <div class="form-group">
        <label for="newBatchGroup">Update Batch Group</label>
        <select class="form-control" name="newBatchGroup" required>
		  <option <%= selectedM %>>Morning</option>
		  <option <%= selectedE %>>Evening</option>
		</select>
      </div>
      <div class="form-group">
        <label for="batchName">Update Batch Name</label>
        <select class="form-control" name="newBatchName" required>
		  <option <%= selectedZ %>>Zumba</option>
		  <option <%= selectedZG %>>Zumba Gold</option>
		</select>
      </div>
      <div class="form-group">
        <label for="batchDateTime">Update Batch Date and Time</label>
        <input type="datetime-local" name="newBatchDateTime" class="form-control" id="batchDateTime" value="<%= batch2Update.getBatch_Date_Time() %>">
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