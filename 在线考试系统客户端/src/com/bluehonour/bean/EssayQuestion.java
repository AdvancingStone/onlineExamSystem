package com.bluehonour.bean;

import java.io.Serializable;

public class EssayQuestion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer eid;
	private String e_Question; //问题
	private Integer mark;//分值
	
	public Integer getEid() {
		return eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

	public String getE_Question() {
		return e_Question;
	}

	public void setE_Question(String e_Question) {
		this.e_Question = e_Question;
	}

	public Integer getMark() {
		return mark;
	}

	public void setMark(Integer mark) {
		this.mark = mark;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public EssayQuestion(Integer eid, String eQuestion, Integer mark) {
		super();
		this.eid = eid;
		this.e_Question = eQuestion;
		this.mark = mark;
	}
	
	public EssayQuestion(String eQuestion, Integer mark) {
		super();
		this.e_Question = eQuestion;
		this.mark = mark;
	}
	
	public EssayQuestion(String eQuestion) {
		super();
		this.e_Question = eQuestion;
	}
	public EssayQuestion() {
		super();
	}
	@Override
	public String toString() {
		return "EssayQuestion [eid=" + eid + ", eQuestion=" + e_Question + ", mark=" + mark + "]";
	}
	
	
}
