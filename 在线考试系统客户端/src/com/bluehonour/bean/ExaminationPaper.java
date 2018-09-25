package com.bluehonour.bean;

import java.io.Serializable;
import java.util.List;

public class ExaminationPaper implements Serializable{

	private int id;//考生id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	private int sid;//试卷id
	private List<ChoiceQuestion> cqList;
	private List<EssayQuestion> eqList;
	public List<ChoiceQuestion> getCqList() {
		return cqList;
	}
	public void setCqList(List<ChoiceQuestion> cqList) {
		this.cqList = cqList;
	}
	public List<EssayQuestion> getEqList() {
		return eqList;
	}
	public void setEqList(List<EssayQuestion> eqList) {
		this.eqList = eqList;
	}
	public ExaminationPaper(List<ChoiceQuestion> cqList, List<EssayQuestion> eqList) {
		super();
		this.cqList = cqList;
		this.eqList = eqList;
	}
	public ExaminationPaper() {
		super();
	}
	public ExaminationPaper(int id, int sid, List<ChoiceQuestion> cqList, List<EssayQuestion> eqList) {
		super();
		this.id = id;
		this.sid = sid;
		this.cqList = cqList;
		this.eqList = eqList;
	}
	@Override
	public String toString() {
		return "ExaminationPaper [id=" + id + ", sid=" + sid + ", cqList=" + cqList + ", eqList=" + eqList + "]";
	}
	
	
}
