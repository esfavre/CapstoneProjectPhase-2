package com.funfit.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.funfit.dao.JdbcFunfitDao;
import com.funfit.model.Participant;

/**
 * Servlet implementation class ParticipantServlet
 */
public class ParticipantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    PrintWriter htmlWriter;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ParticipantServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Participant participant = new Participant();
		String participantName = request.getParameter("participantName");
		String participantPhone = request.getParameter("participantPhone");
		String participantEmail = request.getParameter("participantEmail");
		
		participant.setName(participantName);
		participant.setPhone(participantPhone);
		participant.setEmail(participantEmail);
		
		System.out.println("[Participant Servlet] Participant Details: " + participant);
		JdbcFunfitDao dao = new JdbcFunfitDao();
		int result = dao.addParticipant(participant);
		dao.closeDatabaseConnection();
		response.setContentType("text/html");
		htmlWriter = response.getWriter();
		addHead(request, response);
		htmlWriter.print("<body>");
		addNavBar(request, response);
		String message = "";
		
		if(result > 0) {
			message = "Participant " + participant.getName() + " was sucessfully added to the database.";
		} else {
			message = "Participant " + participant.getName() + " was not added to the database. Please try again.";
		}
		
		addJumbotron(request, response, message);
		addScript(request, response);
		htmlWriter.print("</body>");
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		htmlWriter = response.getWriter();
		addHead(request, response);
		htmlWriter.print("<body");
		addNavBar(request, response);
		int pid = Integer.parseInt(request.getParameter("pid"));
		JdbcFunfitDao dao = new JdbcFunfitDao();
		int result = dao.deleteParticipant(pid);
		String message = "";
		if(result > 0) {
			message = "Participant Deleted Successfully.";
		} else {
			message = "Participant Not Deleted. Please Try Again.";
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
		htmlWriter.print("<title>Funfit Zumba Batch</title>");
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
