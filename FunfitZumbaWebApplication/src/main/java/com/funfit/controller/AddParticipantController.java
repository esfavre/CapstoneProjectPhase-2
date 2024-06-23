package com.funfit.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import com.funfit.dao.*;
import com.funfit.exceptions.DataBaseInsertException;
import com.funfit.model.Participant;


public class AddParticipantController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Add Participant to Database
		
		Participant newParticipant = new Participant();
		
		newParticipant.setName(request.getParameter("participantName"));
		newParticipant.setPhone(request.getParameter("participantPhone"));
		newParticipant.setEmail(request.getParameter("participantEmail"));
		newParticipant.setBid(Integer.parseInt("bid"));
		
		System.out.println("[Participant Servlet] Participant Details: " + newParticipant);
		/*
		String newName = request.getParameter("participantName");
		String newPhone = request.getParameter("participantPhone");
		String newEmail = request.getParameter("participantEmail");
		String newBid = request.getParameter("bid");
		
		newParticipant.setName(newName);
		newParticipant.setPhone(newPhone);
		newParticipant.setEmail(newEmail);
		newParticipant.setBid(Integer.parseInt(newBid));
		*/

		
		response.setContentType("text/html");
        PrintWriter htmlWriter = response.getWriter();
        htmlWriter.print("<div style=\"text-align:center;\">");
        
		
        String message = "";
		try {
			try {
				JdbcFunfitDao dao = new JdbcFunfitDao();
				int result = dao.addParticipant(newParticipant);
				dao.closeDatabaseConnection();
				
				if(result > 0) {
					message = newParticipant.getName() + "added to the Database";
				} else {
					message = newParticipant.getName() + "was not added successfully. Please try again";
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (DataBaseInsertException e) {
			e.printStackTrace();
		}
		htmlWriter.print("<p>" + message + "</p>");
		        
		htmlWriter.print("</div>");
		response.sendRedirect("/HomePage.html");
		
	}

}
