package com.bluehonour.bean;

import java.io.Serializable;

public class ChoiceQuestion implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer cid;
	private String c_Question;
	private String right_Answer;// 正确答案 （A B C D）
	private String a_Answer; // a选项答案
	private String b_Answer;
	private String c_Answer;
	private String d_Answer;
	private Integer mark;//分值
	
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getC_Question() {
		return c_Question;
	}
	public void setC_Question(String c_Question) {
		this.c_Question = c_Question;
	}
	public String getRight_Answer() {
		return right_Answer;
	}
	public void setRight_Answer(String right_Answer) {
		this.right_Answer = right_Answer;
	}
	public String getA_Answer() {
		return a_Answer;
	}
	public void setA_Answer(String a_Answer) {
		this.a_Answer = a_Answer;
	}
	public String getB_Answer() {
		return b_Answer;
	}
	public void setB_Answer(String b_Answer) {
		this.b_Answer = b_Answer;
	}
	public String getC_Answer() {
		return c_Answer;
	}
	public void setC_Answer(String c_Answer) {
		this.c_Answer = c_Answer;
	}
	public String getD_Answer() {
		return d_Answer;
	}
	public void setD_Answer(String d_Answer) {
		this.d_Answer = d_Answer;
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
	public ChoiceQuestion(Integer cid, String cQuestion, String rightAnswer, String aAnswer, String bAnswer,
			String cAnswer, String dAnswer, Integer mark) {
		super();
		this.cid = cid;
		this.c_Question = cQuestion;
		this.right_Answer = rightAnswer;
		this.a_Answer = aAnswer;
		this.b_Answer = bAnswer;
		this.c_Answer = cAnswer;
		this.d_Answer = dAnswer;
		this.mark = mark;
	}
	public ChoiceQuestion() {
		super();
	}
	
	public ChoiceQuestion(String cQuestion, String rightAnswer, String aAnswer, String bAnswer, String cAnswer,
			String dAnswer, Integer mark) {
		super();
		this.c_Question = cQuestion;
		this.right_Answer = rightAnswer;
		this.a_Answer = aAnswer;
		this.b_Answer = bAnswer;
		this.c_Answer = cAnswer;
		this.d_Answer = dAnswer;
		this.mark = mark;
	}
	
	public ChoiceQuestion(String cQuestion, String rightAnswer, String aAnswer, String bAnswer, String cAnswer,
			String dAnswer) {
		super();
		this.c_Question = cQuestion;
		this.right_Answer = rightAnswer;
		this.a_Answer = aAnswer;
		this.b_Answer = bAnswer;
		this.c_Answer = cAnswer;
		this.d_Answer = dAnswer;
	}
	@Override
	public String toString() {
		return "ChoiceQuestion [cid=" + cid + ", cQuestion=" + c_Question + ", rightAnswer=" + right_Answer + ", aAnswer="
				+ a_Answer + ", bAnswer=" + b_Answer + ", cAnswer=" + c_Answer + ", dAnswer=" + d_Answer + ", mark=" + mark
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cid == null) ? 0 : cid.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChoiceQuestion other = (ChoiceQuestion) obj;
		if (cid == null) {
			if (other.cid != null)
				return false;
		} else if (!cid.equals(other.cid))
			return false;
		return true;
	}
	
	
}
