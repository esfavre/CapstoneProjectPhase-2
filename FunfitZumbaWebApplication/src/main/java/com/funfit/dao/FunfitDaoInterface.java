package com.funfit.dao;

import com.funfit.model.Batch;
import com.funfit.model.Participant;

import java.util.List;

public interface FunfitDaoInterface {
	
	public int addBatch(Batch batch);
//
	public int addParticipant(Participant participant);
//	
	public List<Batch> getAllBatches();
//	
	public List<Participant> getAllParticipants();
//	
//	public Batch findBatchByBid(int bid) throws SQLException;
//	
//	public Participant findParticipantByPid(int pid) throws SQLException;
//	
//	public List<Batch> findBatchByGroup(String batch_group) throws SQLException;
//	
//	public List<Batch> findBatchByName(String batch_name) throws SQLException; 
//	
//	public List<Participant> findParticipantsByName(String name) throws SQLException; 
//	
//	public List<Participant> findParticipantsByBatchId(int bid) throws SQLException; 
//	
	public void updateBatch(Batch batch);
//	
	public void updateParticipant(Participant participant);
//	
	public void deleteBatch(int bid);
//	
	public void deleteParticipant(int pid);
	
	public void connectToDatabase();
	
	public void closeDatabaseConnection(); 
}
