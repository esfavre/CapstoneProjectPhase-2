package com.funfit.dao;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.funfit.model.*;
import com.funfit.exceptions.*;

public class JdbcFunfitDao implements FunfitDaoInterface{
	
	private static Connection databaseConnection;
	PreparedStatement preparedStatement;
	
	
	public JdbcFunfitDao() throws SQLException{
		System.out.println("Connecting to the Funfit Database....");
		connectToDatabase();
	}
	
	@Override
	public int addBatch(Batch batch) throws DataBaseInsertException, SQLException {
		int result = 0;
		Batch newBatch = new Batch();
		String sql = "insert into Batch values(null, ?, ?, ?)";
		preparedStatement = databaseConnection.prepareStatement(sql);
		preparedStatement.setString(1, batch.getBatch_Group());
		preparedStatement.setString(2, batch.getBatch_Name());
		preparedStatement.setTimestamp(3, Timestamp.valueOf(batch.getBatch_Date_Time()));
		return result;
	}
	
	@Override
	public int addParticipant(Participant participant) throws DataBaseInsertException, SQLException {
		
		int result = 0;
		Participant newParticipant = new Participant();
		String sql = "insert into Participant values(null, ?, ?, ?, ?)";
		databaseConnection.prepareStatement(sql);
		
		preparedStatement.setString(1, participant.getName());
		preparedStatement.setString(2, participant.getPhone());
		preparedStatement.setString(3, participant.getEmail());
		preparedStatement.setInt(4, participant.getBid());
		
		result = preparedStatement.executeUpdate();
		
		return result;
	}

	@Override
	public List<Batch> getAllBatches() throws SQLException {
		List<Batch> allBatches = new ArrayList<Batch>();
		String sql = "select * from Batch";
		Statement sqlStatement = databaseConnection.createStatement();
		
		ResultSet allBatchRows = sqlStatement.executeQuery(sql);
		
		while(allBatchRows.next()) {
			allBatches.add(MapRowToBatch(allBatchRows));
		}
		
		return allBatches;
	}

	@Override
	public List<Participant> getAllParticipants() throws SQLException {
		List<Participant> allParticipants = new ArrayList<Participant>();
		String sql = "select * from Batch";
		Statement sqlStatement = databaseConnection.createStatement();
		
		ResultSet allParticipantRows = sqlStatement.executeQuery(sql);
		
		while(allParticipantRows.next()) {
			allParticipants.add(MapRowToParticipant(allParticipantRows));
		}
		
		return allParticipants;
	}

	@Override
	public Batch findBatchByBid(int bid) throws SQLException {
		
		String sql = "select * from Batch where bid = ?";
		preparedStatement = databaseConnection.prepareStatement(sql);
		
		preparedStatement.setInt(1, bid);
		
		ResultSet aBatchRow = preparedStatement.executeQuery();
		
		Batch aBatch = new Batch();
		
		if(aBatchRow.next()) {
			aBatch = MapRowToBatch(aBatchRow);
		}
		return aBatch;
	}

	@Override
	public Participant findParticipantByPid(int pid) throws SQLException {
		
		String sql = "select * from Participant where pid = ?";
		preparedStatement = databaseConnection.prepareStatement(sql);
		
		preparedStatement.setInt(1, pid);
		
		ResultSet aParticipantRow = preparedStatement.executeQuery();
		
		Participant aParticipant = new Participant();
		
		if(aParticipantRow.next()) {
			aParticipant = MapRowToParticipant(aParticipantRow);
		}
		return aParticipant;
	}

	@Override
	public List<Batch> findBatchByGroup(String batch_group) throws SQLException {
		
		List<Batch> batchesFound = new ArrayList<Batch>();
		
		String sql = "select * from Batch where batch_group like ?";
		String likeSqlString = "%" + batch_group + "%";
		preparedStatement = databaseConnection.prepareStatement(sql);
		
		preparedStatement.setString(1, likeSqlString);
		
		ResultSet rowsFromDataBase = preparedStatement.executeQuery();
		
		while(rowsFromDataBase.next()) {
			batchesFound.add(MapRowToBatch(rowsFromDataBase));
		}
		
		return batchesFound;
	}

	@Override
	public List<Batch> findBatchByName(String batch_name) throws SQLException {
		
		List<Batch> batchesFound = new ArrayList<Batch>();
				
			String sql = "select * from Batch where batch_name like ?";
			String likeSqlString = "%" + batch_name + "%";
			preparedStatement = databaseConnection.prepareStatement(sql);
			
			preparedStatement.setString(1, likeSqlString);
			
			ResultSet rowsFromDataBase = preparedStatement.executeQuery();
			
			while(rowsFromDataBase.next()) {
				batchesFound.add(MapRowToBatch(rowsFromDataBase));
			}
			
			return batchesFound;
		
	}

	@Override
	public List<Participant> findParticipantsByName(String name) throws SQLException {
		
		List<Participant> participantsFound = new ArrayList<Participant>();
		
		String sql = "select * from Batch where name like ?";
		String likeSqlString = "%" + name + "%";
		preparedStatement = databaseConnection.prepareStatement(sql);
		
		preparedStatement.setString(1, likeSqlString);
		
		ResultSet rowsFromDataBase = preparedStatement.executeQuery();
		
		while(rowsFromDataBase.next()) {
			participantsFound.add(MapRowToParticipant(rowsFromDataBase));
		}
		
		return participantsFound;
	}

	@Override
	public List<Participant> findParticipantsByBatchId(int bid) throws SQLException {
		
		List<Participant> participantsFound = new ArrayList<Participant>();
		
		String sql = "select * from Participant where bid = ?";
		preparedStatement = databaseConnection.prepareStatement(sql);
		
		preparedStatement.setInt(1, bid);
		
		ResultSet rowsFromDataBase = preparedStatement.executeQuery();
	
		
		if(rowsFromDataBase.next()) {
			participantsFound.add(MapRowToParticipant(rowsFromDataBase));
		}
		return participantsFound;
	}

	@Override
	public void updateBatch(Batch batch) throws DataBaseUpdateException, DataBaseDeleteException, SQLException {
		
		String sql = "update Batch "
				+"set batch_group = ? "
				+ ", batch_name = ?"
				+ ", batch_date_time = ?"
				+ " where bid = ?";
		
		preparedStatement = databaseConnection.prepareStatement(sql);
		
		preparedStatement.setString(1, batch.getBatch_Group());
		preparedStatement.setString(2, batch.getBatch_Name());
		preparedStatement.setTimestamp(3, Timestamp.valueOf(batch.getBatch_Date_Time()));
		preparedStatement.setInt(4, batch.getBid());
		
		int numRowsUpdated = preparedStatement.executeUpdate();
	}

	@Override
	public void updateParticipant(Participant participant)
			throws DataBaseUpdateException, DataBaseDeleteException, SQLException {
		String sql = "update Participant "
				+"set name = ? "
				+ ", phone = ? "
				+ ", email = ?"
				+ ", bid = ?"
				+ " where pid = ?";
		
		preparedStatement = databaseConnection.prepareStatement(sql);
		
		preparedStatement.setString(1, participant.getName());
		preparedStatement.setString(2, participant.getPhone());
		preparedStatement.setString(3, participant.getEmail());
		preparedStatement.setInt(4, participant.getBid());
		preparedStatement.setInt(5, participant.getPid());
		
		int numRowsUpdated = preparedStatement.executeUpdate();
		
	}

	@Override
	public void deleteBatch(int bid) throws DataBaseDeleteException, SQLException {
		
		String sql = "delete from Batch where bid = ?";
		preparedStatement = databaseConnection.prepareStatement(sql);
		preparedStatement.setInt(1, bid);
		
		int numRowsDeleted = preparedStatement.executeUpdate();
	}

	@Override
	public void deleteParticipant(int pid) throws DataBaseDeleteException, SQLException {
		
		String sql = "delete from Batch where pid = ?";
		preparedStatement = databaseConnection.prepareStatement(sql);
		preparedStatement.setInt(1, pid);
		
	}
	
	private Batch MapRowToBatch(ResultSet aRow) throws SQLException {
		
		Batch aBatch = new Batch();
		
		aBatch.setBid(aRow.getInt("bid"));
		aBatch.setBatch_Group(aRow.getString("batch_group"));
		aBatch.setBatch_Name(aRow.getString("batch_name"));
		aBatch.setBatch_Date_Time(aRow.getTimestamp("batch_date_time").toLocalDateTime());
		
		return aBatch;
		
	}
	
	private Participant MapRowToParticipant(ResultSet aRow) throws SQLException {
		
		Participant aParticipant = new Participant();
		
		aParticipant.setPid(aRow.getInt("pid"));
		aParticipant.setName(aRow.getString("name"));
		aParticipant.setPhone(aRow.getString("phone"));
		aParticipant.setEmail(aRow.getString("email"));
		aParticipant.setBid(aRow.getInt("bid"));
		
		return aParticipant;
	}
	
	private void connectToDatabase() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost/funfit_database";
			String user = "funfitadmin";
			String password = "mid-SUAVE2";
			databaseConnection = DriverManager.getConnection(url, user, password);
			} catch (Exception e) {
				e.printStackTrace();
		}
		
	}
	
	public static void closeDatabaseConnection() {
		
		try {
			databaseConnection.close();
			System.out.println("Connection to the Funfit Database Closed");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	

}
