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

    <title>Funfit Zumba Admin Page</title>

    <!-- Bootstrap core CSS -->
    <link href="https://getbootstrap.com/docs/3.4/dist/css/bootstrap.min.css" rel="stylesheet">
	
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="https://getbootstrap.com/docs/3.4/assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="navbar.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="https://getbootstrap.com/docs/3.4/assets/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://cdn.jsdelivr.net/npm/html5shiv@3.7.3/dist/html5shiv.min.js"></script>
      <script src="https://cdn.jsdelivr.net/npm/respond.js@1.4.2/dest/respond.min.js"></script>
    <![endif]-->
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
      
  <h2 id="tables-hover-rows">List of Zumba Batches</h2>
  <br>
  <div class="bs-example" data-example-id="hoverable-table">
    <table class="table table-hover">
      <thead>
        <tr>
          <th>Batch ID (bid)</th>
          <th>Batch Group</th>
          <th>Batch Name</th>
          <th>Batch Date and Time</th>
          <th>Delete/Update</th>
        </tr>
      </thead>
      <tbody>
      	<%
      	JdbcFunfitDao dao = new JdbcFunfitDao();
      	List<Batch> batches = new ArrayList<Batch>();
      	batches = dao.getAllBatches();
      	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("E, MMM dd yyyy");
		DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
		String formatedDate = "";
      	
      	if(batches.size() > 0){
   			for(Batch batch : batches){
   				formatedDate = batch.getBatch_Date_Time().format(dateFormat) + " " + batch.getBatch_Date_Time().format(timeFormat);
   				%>
   				<tr>
   				<th scope="row"><%= batch.getBid() %></th>
   				<td><%= batch.getBatch_Group() %></td>
   				<td><%= batch.getBatch_Name() %></td>
   				<td><%= formatedDate %></td>
   				<td><a href="DeleteBatch?id=<%= batch.getBid() %>">DELETE</a> | <a href="Update">UPDATE</a></td>
   				</tr> 
   				
   			<%
   			}
   			
      	} else {
			%>
			<tr>
				<td>No Batches Present></td>
			</tr>
			
		<% 
		}%>
      
  </tbody>
  </table>  
  </div><!-- /example -->
  <figure class="highlight"><pre>
  </div> <!-- /container -->
  <script src="https://code.jquery.com/jquery-1.12.4.min.js" integrity="sha384-nvAa0+6Qg9clwYCGGPpDQLVpLNn0fRaROjHqs13t4Ggj3Ez50XnGQqc/r8MhnRDZ" crossorigin="anonymous"></script>
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
    <script src="https://getbootstrap.com/docs/3.4/dist/js/bootstrap.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="https://getbootstrap.com/docs/3.4/assets/js/ie10-viewport-bug-workaround.js"></script>

</body>
</html>