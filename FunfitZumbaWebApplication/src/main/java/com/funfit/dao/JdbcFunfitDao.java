package com.funfit.dao;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.funfit.model.*;

public class JdbcFunfitDao implements FunfitDaoInterface{
	
	private static Connection databaseConnection;
	PreparedStatement preparedStatement;
	
	final String TAG = "[" + getClass().getSimpleName() + "]";
	
	public JdbcFunfitDao(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println(TAG + "Driver Loaded");
			connectToDatabase();
		} catch (Exception e) {
			System.err.println("Exception Occured: " + e);
		}
	}
	
	public void connectToDatabase() {
	
		try {
			String url = "jdbc:mysql://localhost:3306/funfit_database";
			String user = "funfitadmin";
			String password = "mid-SUAVE2";
			databaseConnection = DriverManager.getConnection(url, user, password);
			System.out.println("Connection to the Funfit Database Created");
			} catch (Exception e) {
				System.err.println("Exception Occured: " + e);
		}
		
	}
	
	public void closeDatabaseConnection() {
		if (databaseConnection != null) {
			try {
				databaseConnection.close();
				System.out.println(TAG + "Connection to the Funfit Database Closed. Closed Status: " + databaseConnection.isClosed());
			} catch (Exception e) {
				System.err.println("Exception Occured: " + e);
			}
		}
		
		
	}
	
	@Override
	public int addBatch(Batch batch){
		int result = 0;
		try {
			String sql = "insert into Batch (batch_group, batch_name, batch_date_time) values(?, ?, ?)";
			preparedStatement = databaseConnection.prepareStatement(sql);
			preparedStatement.setString(1, batch.getBatch_Group());
			preparedStatement.setString(2, batch.getBatch_Name());
			preparedStatement.setTimestamp(3, Timestamp.valueOf(batch.getBatch_Date_Time()));
			
			result = preparedStatement.executeUpdate();
			String message = "";
			if(result > 0) {
				message = "Batch Added Successfully";
				System.out.println(TAG + message);
			} else {
				message = "Batch Not Added Please Try Again";
				System.err.println(TAG + message);
			}
		} catch (Exception e) {
			System.err.println("Exception Occured: " + e);
		}
		return result;
		
	}
	
	@Override
	public int addParticipant(Participant participant){
		int result = 0;
		try {
			String sql = "insert into Participant (name, phone, email) values(?, ?, ?)";
			preparedStatement = databaseConnection.prepareStatement(sql);
			preparedStatement.setString(1, participant.getName());
			preparedStatement.setString(2, participant.getPhone());
			preparedStatement.setString(3, participant.getEmail());
			
			result = preparedStatement.executeUpdate();
			String message = "";
			if(result > 0) {
				message = "Participant Added Successfully";
				System.out.println(TAG + message);
			} else {
				message = "Participant Not Added. Please Try Again";
				System.err.println(TAG + message);
			}
		} catch (Exception e) {
			System.err.println("Exception Occured: " + e);
		}	
		return result;
	}

	@Override
	public List<Batch> getAllBatches(){
		List<Batch> allBatches = new ArrayList<Batch>();
		try {
			String sql = "select * from Batch";
			preparedStatement = databaseConnection.prepareStatement(sql);
			ResultSet allBatchRows = preparedStatement.executeQuery();
			while(allBatchRows.next()) {
				allBatches.add(MapRowToBatch(allBatchRows));
			}
		} catch (Exception e) {
			System.err.println("Exception Occured: " + e);
		}
		return allBatches;
	}

	@Override
	public List<Participant> getAllParticipants(){
		List<Participant> allParticipants = new ArrayList<Participant>();
		try {
			String sql = "select * from Participant";
			Statement sqlStatement = databaseConnection.createStatement();
			
			ResultSet allParticipantRows = sqlStatement.executeQuery(sql);
			
			while(allParticipantRows.next()) {
				allParticipants.add(MapRowToParticipant(allParticipantRows));
			}
		} catch (Exception e) {
			System.err.println("Exception Occured: " + e);
		}
		return allParticipants;
	}

	@Override
	public int updateBatch(Batch batch){
		int result = 0;
		try {
			String sql = "update Batch set batch_group = ?, batch_name = ?, batch_date_time = ? where bid = ?";
			System.out.println("SQL is: " + sql);
			
			preparedStatement = databaseConnection.prepareStatement(sql);
			
			preparedStatement.setString(1, batch.getBatch_Group());
			preparedStatement.setString(2, batch.getBatch_Name());
			preparedStatement.setTimestamp(3, Timestamp.valueOf(batch.getBatch_Date_Time()));
			preparedStatement.setInt(4, batch.getBid());
			
			result = preparedStatement.executeUpdate();
			String message = "";
			if(result > 0) {
				message = "Batch Updated Successfully";
				System.out.println(TAG + message);
			} else {
				message = "Batch Not Updated. Please Try Again";
				System.err.println(TAG + message);
			}
			
		} catch (Exception e) {
			System.err.println("Exception Occured: " + e);
		}
		return result;
	}

	@Override
	public int updateParticipant(Participant participant){
		int result = 0;
		try {
			String sql = "update Participant set name = ? , phone = ? , email = ?, bid = ? where pid = ?";
			
			preparedStatement = databaseConnection.prepareStatement(sql);
			
			preparedStatement.setString(1, participant.getName());
			preparedStatement.setString(2, participant.getPhone());
			preparedStatement.setString(3, participant.getEmail());
			preparedStatement.setInt(4, participant.getBid());
			preparedStatement.setInt(5, participant.getPid());
			
			result = preparedStatement.executeUpdate();
			String message = "";
			if(result > 0) {
				message = "Participant Updated Successfully";
				System.out.println(TAG + message);
			} else {
				message = "Participant Not Updated. Please Try Again";
				System.err.println(TAG + message);
			}
		} catch (Exception e) {
			System.err.println("Exception Occured: " + e);
		}
		return result;
	}
	
	@Override
	public int deleteBatch(int bid){
		int result = 0;
		try {
			String sql = "delete from Batch where bid = ?";
			preparedStatement = databaseConnection.prepareStatement(sql);
			preparedStatement.setInt(1, bid);
			
			result = preparedStatement.executeUpdate();
			String message = "";
			if(result > 0) {
				message = "Batch Deleted Successfully";
				System.out.println(TAG + message);
			} else {
				message = "Batch Not Deleted. Please Try Again";
				System.err.println(TAG + message);
			}
		} catch (Exception e) {
			System.err.println("Exception Occured: " + e);
		}
		return result;
	}

	@Override
	public int deleteParticipant(int pid){
		int result = 0;
		try {
			String sql = "delete from Participant where pid = ?";
			preparedStatement = databaseConnection.prepareStatement(sql);
			preparedStatement.setInt(1, pid);
			
			result = preparedStatement.executeUpdate();
			String message = "";
			if(result > 0) {
				message = "Participant Deleted Successfully";
				System.out.println(TAG + message);
			} else {
				message = "Participant Not Deleted. Please Try Again";
				System.err.println(TAG + message);
			}
		} catch (Exception e) {
			System.err.println("Exception Occured: " + e);
		}
		return result;
	}
	
	
//	@Override
//	public Batch findBatchByBid(int bid) throws SQLException {
//		
//		String sql = "select * from Batch where bid = ?";
//		preparedStatement = databaseConnection.prepareStatement(sql);
//		
//		preparedStatement.setInt(1, bid);
//		
//		ResultSet aBatchRow = preparedStatement.executeQuery();
//		
//		Batch aBatch = new Batch();
//		
//		if(aBatchRow.next()) {
//			aBatch = MapRowToBatch(aBatchRow);
//		}
//		return aBatch;
//	}
//
//	@Override
//	public Participant findParticipantByPid(int pid) throws SQLException {
//		
//		String sql = "select * from Participant where pid = ?";
//		preparedStatement = databaseConnection.prepareStatement(sql);
//		
//		preparedStatement.setInt(1, pid);
//		
//		ResultSet aParticipantRow = preparedStatement.executeQuery();
//		
//		Participant aParticipant = new Participant();
//		
//		if(aParticipantRow.next()) {
//			aParticipant = MapRowToParticipant(aParticipantRow);
//		}
//		return aParticipant;
//	}
//
//	@Override
//	public List<Batch> findBatchByGroup(String batch_group) throws SQLException {
//		
//		List<Batch> batchesFound = new ArrayList<Batch>();
//		
//		String sql = "select * from Batch where batch_group like ?";
//		String likeSqlString = "%" + batch_group + "%";
//		preparedStatement = databaseConnection.prepareStatement(sql);
//		
//		preparedStatement.setString(1, likeSqlString);
//		
//		ResultSet rowsFromDataBase = preparedStatement.executeQuery();
//		
//		while(rowsFromDataBase.next()) {
//			batchesFound.add(MapRowToBatch(rowsFromDataBase));
//		}
//		
//		return batchesFound;
//	}
//
//	@Override
//	public List<Batch> findBatchByName(String batch_name) throws SQLException {
//		
//		List<Batch> batchesFound = new ArrayList<Batch>();
//				
//			String sql = "select * from Batch where batch_name like ?";
//			String likeSqlString = "%" + batch_name + "%";
//			preparedStatement = databaseConnection.prepareStatement(sql);
//			
//			preparedStatement.setString(1, likeSqlString);
//			
//			ResultSet rowsFromDataBase = preparedStatement.executeQuery();
//			
//			while(rowsFromDataBase.next()) {
//				batchesFound.add(MapRowToBatch(rowsFromDataBase));
//			}
//			
//			return batchesFound;
//		
//	}
//
//	@Override
//	public List<Participant> findParticipantsByName(String name) throws SQLException {
//		
//		List<Participant> participantsFound = new ArrayList<Participant>();
//		
//		String sql = "select * from Batch where name like ?";
//		String likeSqlString = "%" + name + "%";
//		preparedStatement = databaseConnection.prepareStatement(sql);
//		
//		preparedStatement.setString(1, likeSqlString);
//		
//		ResultSet rowsFromDataBase = preparedStatement.executeQuery();
//		
//		while(rowsFromDataBase.next()) {
//			participantsFound.add(MapRowToParticipant(rowsFromDataBase));
//		}
//		
//		return participantsFound;
//	}
//
//	@Override
//	public List<Participant> findParticipantsByBatchId(int bid) throws SQLException {
//		
//		List<Participant> participantsFound = new ArrayList<Participant>();
//		
//		String sql = "select * from Participant where bid = ?";
//		preparedStatement = databaseConnection.prepareStatement(sql);
//		
//		preparedStatement.setInt(1, bid);
//		
//		ResultSet rowsFromDataBase = preparedStatement.executeQuery();
//	
//		
//		if(rowsFromDataBase.next()) {
//			participantsFound.add(MapRowToParticipant(rowsFromDataBase));
//		}
//		return participantsFound;
//	}

	private Batch MapRowToBatch(ResultSet aRow){
			
		Batch aBatch = new Batch();
		try {
			aBatch.setBid(aRow.getInt("bid"));
			aBatch.setBatch_Group(aRow.getString("batch_group"));
			aBatch.setBatch_Name(aRow.getString("batch_name"));
			aBatch.setBatch_Date_Time(aRow.getTimestamp("batch_date_time").toLocalDateTime());
		} catch (Exception e) {
			System.out.println("Exception Occured: " + e);
		}
		
		
		return aBatch;
		
	}

	
	
	private Participant MapRowToParticipant(ResultSet aRow){
		
		Participant aParticipant = new Participant();
		try {
			aParticipant.setPid(aRow.getInt("pid"));
			aParticipant.setName(aRow.getString("name"));
			aParticipant.setPhone(aRow.getString("phone"));
			aParticipant.setEmail(aRow.getString("email"));
			aParticipant.setBid(aRow.getInt("bid"));
		} catch (Exception e) {
			System.out.println("Exception Occured: " + e);
		}
		return aParticipant;
		
		
	}
	
	
	
	

	

}
