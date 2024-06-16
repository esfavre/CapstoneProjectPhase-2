package com.funfit.dao;

import com.funfit.model.Batch;
import com.funfit.model.Participant;
import com.funfit.exceptions.*;

import java.sql.SQLException;
import java.util.List;

public interface FunfitDaoInterface {
	
	public int addBatch(Batch batch) throws DataBaseInsertException, SQLException;

	public int addParticipant(Participant participant) throws DataBaseInsertException, SQLException;
	
	public List<Batch> getAllBatches() throws SQLException;
	
	public List<Participant> getAllParticipants() throws SQLException;
	
	public Batch findBatchByBid(int bid) throws SQLException;
	
	public Participant findParticipantByPid(int pid) throws SQLException;
	
	public List<Batch> findBatchByGroup(String batch_group) throws SQLException;
	
	public List<Batch> findBatchByName(String batch_name) throws SQLException; 
	
	public List<Participant> findParticipantsByName(String name) throws SQLException; 
	
	public List<Participant> findParticipantsByBatchId(int bid) throws SQLException; 
	
	public void updateBatch(Batch batch)  throws DataBaseUpdateException, DataBaseDeleteException, SQLException;
	
	public void updateParticipant(Participant participant) throws DataBaseUpdateException, DataBaseDeleteException, SQLException;
	
	public void deleteBatch(int bid)  throws DataBaseDeleteException, SQLException;
	
	public void deleteParticipant(int pid) throws DataBaseDeleteException, SQLException; 
}
