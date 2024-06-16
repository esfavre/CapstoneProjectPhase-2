package com.funfit.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Batch {
	
	private int bid;
	private String batch_group;
	private String batch_name;
	private LocalDateTime batch_date_time;

	
	public Batch() {
		
	}
	
	
	public Batch(int bid, String batch_group, String batch_name, LocalDateTime batch_date_time) {
		
		this.bid = bid;
		this.batch_group = batch_group;
		this.batch_name = batch_name;
		this.batch_date_time = batch_date_time;
		
	}


	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getBatch_Group() {
		return batch_group;
	}
	public void setBatch_Group(String batch_group) {
		this.batch_group = batch_group;
	}
	public String getBatch_Name() {
		return batch_name;
	}
	public void setBatch_Name(String batch_name) {
		this.batch_name = batch_name;
	}
	public LocalDateTime getBatch_Date_Time() {
		return batch_date_time;
	}
	public void setBatch_Date_Time(LocalDateTime batch_date_time) {
		this.batch_date_time = batch_date_time;
	}


	@Override
	public String toString() {
		return "Batch [bid=" + bid + ", batch_group=" + batch_group + ", batch_name=" + batch_name
				+ ", batch_date_time=" + batch_date_time + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(batch_date_time, batch_group, batch_name, bid);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Batch other = (Batch) obj;
		return Objects.equals(batch_date_time, other.batch_date_time) && Objects.equals(batch_group, other.batch_group)
				&& Objects.equals(batch_name, other.batch_name) && bid == other.bid;
	}
	
	
}
