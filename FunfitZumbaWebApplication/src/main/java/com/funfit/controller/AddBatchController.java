package com.funfit.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;

import com.funfit.dao.FunfitDaoInterface;
import com.funfit.dao.JdbcFunfitDao;
import com.funfit.exceptions.DataBaseInsertException;
import com.funfit.model.Batch;

/**
 * Servlet implementation class BatchController
 */

public class AddBatchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FunfitDaoInterface batchDataSource;
	PrintWriter htmlWriter;   
    /**
     * @see HttpServlet#HttpServlet()
     */
	@SuppressWarnings("unchecked")
	
    public AddBatchController() throws IOException, SQLException{
        super();
		batchDataSource = new JdbcFunfitDao();
    }


	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Batch newBatch = new Batch();
		
		String newGroup = request.getParameter("batch_group");
		String newName = request.getParameter("batch_name");
		String newDateTime = request.getParameter("batch_date_time");

		newBatch.setBatch_Group(newGroup);
		newBatch.setBatch_Name(newName);
		newBatch.setBatch_Date_Time(LocalDateTime.parse(newDateTime));
		
		try {
			try {
				batchDataSource.addBatch(newBatch);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (DataBaseInsertException e) {
			e.printStackTrace();
		}
		response.sendRedirect("/HomePage.html");
	}

}
