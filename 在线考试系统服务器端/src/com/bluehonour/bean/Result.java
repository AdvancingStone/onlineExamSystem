package com.bluehonour.bean;

import java.io.Serializable;

public class Result implements Serializable{

	private int rid;
	private int id;
	private int record;
	private int erid;
	public int getErid() {
		return erid;
	}
	public void setErid(int erid) {
		this.erid = erid;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRecord() {
		return record;
	}
	public void setRecord(int record) {
		this.record = record;
	}
	
	
	public Result(int id, int record, int erid) {
		super();
		this.id = id;
		this.record = record;
		this.erid = erid;
	}
	public Result(int rid, int id, int record, int erid) {
		super();
		this.rid = rid;
		this.id = id;
		this.record = record;
		this.erid = erid;
	}
	
	@Override
	public String toString() {
		return "Result [rid=" + rid + ", id=" + id + ", record=" + record + ", erid=" + erid + "]";
	}
	public Result() {
		super();
	}
	
}
