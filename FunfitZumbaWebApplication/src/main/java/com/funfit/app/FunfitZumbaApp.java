//package com.funfit.app;
//
//import java.time.LocalDateTime;
//import java.time.Month;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.funfit.dao.JdbcFunfitDao;
//import com.funfit.model.*;
//
//public class FunfitZumbaApp {
//
//	public static void main(String[] args) {
//	
//		System.out.println("Welcome to Funfit Zumba Management App!");
//		
//		Batch batch = new Batch();
//		Participant participant = new Participant();
//	
//		batch.setBid(9);
//		batch.setBatch_Group("Evening");
//		batch.setBatch_Name("Zumba");
//		batch.setBatch_Date_Time(LocalDateTime.of(2024, Month.JUNE, 22, 20, 0, 0));
//		
//		participant.setPid(8);
//		participant.setName("Rei de Suave Ganondorf");
//		participant.setPhone("555-478-5820");
//		participant.setEmail("reidesuave@calamity.com");
//		participant.setBid(6);
//		
//		System.out.println("Connecting to Funfit Database.....");
//		JdbcFunfitDao dao = new JdbcFunfitDao();
//		dao.connectToDatabase();
//		dao.addBatch(batch);
//		dao.addParticipant(participant);
//		dao.updateBatch(batch);
//		dao.updateParticipant(participant);
//		List<Batch> batches = dao.getAllBatches();
//		List<Participant> participants = dao.getAllParticipants();
//		batches.forEach(bRef -> System.out.println(bRef));
//		participants.forEach(pRef -> System.out.println(pRef));
//		dao.deleteBatch(9);
//		dao.deleteParticipant(8);
//		
//		
//		dao.closeDatabaseConnection();
//
//	}
//
//}
