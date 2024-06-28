package com.funfit.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import com.funfit.dao.JdbcFunfitDao;
import com.funfit.model.Batch;

/**
 * Servlet implementation class UpdateBatchServlet
 */
@WebServlet(urlPatterns = {"/updateBatch"})
public class UpdateBatchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    PrintWriter htmlWriter;
    public UpdateBatchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		htmlWriter = response.getWriter();
		addHead(request, response);
		htmlWriter.print("<body>");
		addNavBar(request, response);
		int bid2Update = Integer.parseInt(request.getParameter("bid2Update"));
		String newBatchGroup = request.getParameter("newBatchGroup");
		String newBatchName = request.getParameter("newBatchName");
		String newBatchDateTime = request.getParameter("newBatchDateTime");
		String message = ""; 
		JdbcFunfitDao dao = new JdbcFunfitDao();
		
		Batch batch2Update = dao.findBatchByBid(bid2Update);
		
		batch2Update.setBatch_Group(newBatchGroup);
		batch2Update.setBatch_Name(newBatchName);
		batch2Update.setBatch_Date_Time(LocalDateTime.parse(newBatchDateTime));
		
		int result = dao.updateBatch(batch2Update);
		dao.closeDatabaseConnection();
		
		if(result > 0) {
			message = "Batch Updated Successfully.";
		} else {
			message = "Batch Not Updated or Does Not Exist. Please Try Again";
		}
		addJumbotron(request, response, message);
		addScript(request, response);
		htmlWriter.print("</body>");
		
}
	
	protected void addHead(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html");
		htmlWriter = response.getWriter();
		htmlWriter.print("<head>");
		htmlWriter.print("<meta charset=\"utf-8\">");
		htmlWriter.print("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">");
		htmlWriter.print("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
		htmlWriter.print(" <meta name=\"description\" content=\"\">");
		htmlWriter.print("<meta name=\"author\" content=\"\">");
		htmlWriter.print("<link rel=\"icon\" href=\"\">");
		htmlWriter.print("<link rel=\"canonical\" href=\"https://getbootstrap.com/docs/3.4/examples/navbar/\">");
		htmlWriter.print("<title>Update Funfit Zumba Batch</title>");
		htmlWriter.print("<link href=\"https://getbootstrap.com/docs/3.4/dist/css/bootstrap.min.css\" rel=\"stylesheet\">");
		htmlWriter.print("<link href=\"https://getbootstrap.com/docs/3.4/assets/css/ie10-viewport-bug-workaround.css\" rel=\"stylesheet\">");
		htmlWriter.print("<link href=\"navbar.css\" rel=\"stylesheet\">");
		htmlWriter.print("<script src=\"https://getbootstrap.com/docs/3.4/assets/js/ie-emulation-modes-warning.js\"></script>");
		htmlWriter.print(" </head>");
		
	}
	
	protected void addNavBar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html");
		htmlWriter = response.getWriter();
		htmlWriter.print("<div class=\"container\">");
		htmlWriter.print("<nav class=\"navbar navbar-default\">");
		htmlWriter.print("<div class=\"container-fluid\">");
		htmlWriter.print("<div class=\"navbar-header\">");
		htmlWriter.print("<button type=\"button\" class=\"navbar-toggle collapsed\" data-toggle=\"collapse\" data-target=\"#navbar\" aria-expanded=\"false\" aria-controls=\"navbar\">");
		htmlWriter.print("<span class=\"sr-only\">Toggle navigation</span>");
		htmlWriter.print("<span class=\"icon-bar\"></span>");
		htmlWriter.print("<span class=\"icon-bar\"></span>");
		htmlWriter.print("<span class=\"icon-bar\"></span>");
		htmlWriter.print("</button>");
		htmlWriter.print("<a class=\"navbar-brand\" href=\"HomePage.html\">Funfit Zumba</a>");
		htmlWriter.print("</div>");
		htmlWriter.print("<div id=\"navbar\" class=\"navbar-collapse collapse\">");
		htmlWriter.print("<ul class=\"nav navbar-nav\">");
		htmlWriter.print("<li class=\"nav-item\"><a href=\"BatchParticipants.jsp\">Batch Participants</a></li>");
		htmlWriter.print("<li class=\"dropdown\">");
		htmlWriter.print("<a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\" role=\"button\" aria-haspopup=\"true\" aria-expanded=\"false\">Batches <span class=\"caret\"></span></a>");
		htmlWriter.print("<ul class=\"dropdown-menu\">");
		htmlWriter.print("<li><a href=\"AddBatch.html\">Add Batch</a></li>");
		htmlWriter.print("<li><a href=\"Batches.jsp\">Batches</a></li>");
		htmlWriter.print("</ul>");
		htmlWriter.print("</li>");
		htmlWriter.print("<li class=\"dropdown\">");
		htmlWriter.print("<a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\" role=\"button\" aria-haspopup=\"true\" aria-expanded=\"false\">Participants <span class=\"caret\"></span></a>");
		htmlWriter.print("<ul class=\"dropdown-menu\">");
		htmlWriter.print("<li><a href=\"AddParticipant.html\">Add Participant</a></li>");
		htmlWriter.print("<li><a href=\"Participants.jsp\">Participants</a></li>");
		htmlWriter.print("</ul>");
		htmlWriter.print("</li>");
		htmlWriter.print("</ul>");
		htmlWriter.print("</div>");
		htmlWriter.print("</div>");
		htmlWriter.print("</nav>");
	}
	
	protected void addJumbotron(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException{
		response.setContentType("text/html");
		htmlWriter = response.getWriter();
		htmlWriter.print("<div class=\"jumbotron\">");
		htmlWriter.print("<h2>" + message + "</h2>");
		htmlWriter.print("<br>");
		htmlWriter.print("<h3>Add Batches or Participants Here</h3>");
		htmlWriter.print("<br>");
		htmlWriter.print("<p>");
		htmlWriter.print("<a class=\"btn btn-lg btn-primary\" href=\"AddBatch.html\" role=\"button\">Add Batch &raquo;</a>");
		htmlWriter.print("&nbsp;");
		htmlWriter.print("&nbsp;");
		htmlWriter.print("<a class=\"btn btn-lg btn-primary\" href=\"AddParticipant.html\" role=\"button\">Add Participant &raquo;</a>");
		htmlWriter.print("</p>");
		htmlWriter.print("<br>");
		htmlWriter.print("<h3>Manage Batches or Participants Here</h3>");
		htmlWriter.print("<br>");
		htmlWriter.print("<p>");
		htmlWriter.print("<a class=\"btn btn-lg btn-primary\" href=\"Batches.jsp\" role=\"button\">Batches &raquo;</a>");
		htmlWriter.print("&nbsp;");
		htmlWriter.print("&nbsp;");
		htmlWriter.print("<a class=\"btn btn-lg btn-primary\" href=\"Participants.jsp\" role=\"button\">Participants &raquo;</a>");
		htmlWriter.print("&nbsp;");
		htmlWriter.print("&nbsp;");
		htmlWriter.print("<a class=\"btn btn-lg btn-primary\" href=\"BatchParticipants.jsp\" role=\"button\">Batch Participants &raquo;</a>");
		htmlWriter.print("</p>");
		htmlWriter.print("</div>");
		
	}
	
	protected void addScript(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html");
		htmlWriter = response.getWriter();
		htmlWriter.print("</div>"); //end of container
		htmlWriter.print("<script src=\"https://code.jquery.com/jquery-1.12.4.min.js\" integrity=\"sha384-nvAa0+6Qg9clwYCGGPpDQLVpLNn0fRaROjHqs13t4Ggj3Ez50XnGQqc/r8MhnRDZ\" crossorigin=\"anonymous\"></script>");
		htmlWriter.print("<script>window.jQuery || document.write('<script src=\"../../assets/js/vendor/jquery.min.js\"><\\/script>')</script>");
		htmlWriter.print("<script src=\"https://getbootstrap.com/docs/3.4/dist/js/bootstrap.min.js\"></script>");
		htmlWriter.print("<script src=\"https://getbootstrap.com/docs/3.4/assets/js/ie10-viewport-bug-workaround.js\"></script>");
	}
}