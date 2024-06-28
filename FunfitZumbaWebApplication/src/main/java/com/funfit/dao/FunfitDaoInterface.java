package com.funfit.dao;

import com.funfit.model.Batch;
import com.funfit.model.Participant;

import java.util.List;

public interface FunfitDaoInterface {
	
	public int addBatch(Batch batch);

	public int addParticipant(Participant participant);
	
	public List<Batch> getAllBatches();
	
	public List<Participant> getAllParticipants();
	
	public Batch findBatchByBid(int bid);
	
	public Participant findParticipantByPid(int pid);
	
	public List<Participant> findParticipantsByBatchId(int bid); 
	
	public int updateBatch(Batch batch);
	
	public int updateParticipant(Participant participant);
	
	public int deleteBatch(int bid);
	
	public int deleteParticipant(int pid);
	
	public void connectToDatabase();
	
	public void closeDatabaseConnection(); 
}
