package com.bluehonour.bean;

import java.io.Serializable;

/**
 * 考题
 * @author alin
 *
 */
public class Subject implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer sid;
	private String c_q_1;//选择题   choice_question
	private String c_q_2;//选择题
	private String c_q_3;//选择题
	private String c_q_4;//选择题
	private String c_q_5;//选择题
	private String c_q_6;//选择题
	private String c_q_7;//选择题
	private String c_q_8;//选择题
	
	private String e_q_1;//简答题  essay_question
	private String e_q_2;//简答题
	private String e_q_3;//简答题
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public String getC_q_1() {
		return c_q_1;
	}
	public void setC_q_1(String c_q_1) {
		this.c_q_1 = c_q_1;
	}
	public String getC_q_2() {
		return c_q_2;
	}
	public void setC_q_2(String c_q_2) {
		this.c_q_2 = c_q_2;
	}
	public String getC_q_3() {
		return c_q_3;
	}
	public void setC_q_3(String c_q_3) {
		this.c_q_3 = c_q_3;
	}
	public String getC_q_4() {
		return c_q_4;
	}
	public void setC_q_4(String c_q_4) {
		this.c_q_4 = c_q_4;
	}
	public String getC_q_5() {
		return c_q_5;
	}
	public void setC_q_5(String c_q_5) {
		this.c_q_5 = c_q_5;
	}
	public String getC_q_6() {
		return c_q_6;
	}
	public void setC_q_6(String c_q_6) {
		this.c_q_6 = c_q_6;
	}
	public String getC_q_7() {
		return c_q_7;
	}
	public void setC_q_7(String c_q_7) {
		this.c_q_7 = c_q_7;
	}
	public String getC_q_8() {
		return c_q_8;
	}
	public void setC_q_8(String c_q_8) {
		this.c_q_8 = c_q_8;
	}
	public String getE_q_1() {
		return e_q_1;
	}
	public void setE_q_1(String e_q_1) {
		this.e_q_1 = e_q_1;
	}
	public String getE_q_2() {
		return e_q_2;
	}
	public void setE_q_2(String e_q_2) {
		this.e_q_2 = e_q_2;
	}
	public String getE_q_3() {
		return e_q_3;
	}
	public void setE_q_3(String e_q_3) {
		this.e_q_3 = e_q_3;
	}
	public Subject(Integer sid, String c_q_1, String c_q_2, String c_q_3, String c_q_4, String c_q_5, String c_q_6,
			String c_q_7, String c_q_8, String e_q_1, String e_q_2, String e_q_3) {
		super();
		this.sid = sid;
		this.c_q_1 = c_q_1;
		this.c_q_2 = c_q_2;
		this.c_q_3 = c_q_3;
		this.c_q_4 = c_q_4;
		this.c_q_5 = c_q_5;
		this.c_q_6 = c_q_6;
		this.c_q_7 = c_q_7;
		this.c_q_8 = c_q_8;
		this.e_q_1 = e_q_1;
		this.e_q_2 = e_q_2;
		this.e_q_3 = e_q_3;
	}
	public Subject() {
		super();
	}
	@Override
	public String toString() {
		return "Subject [sid=" + sid + ", c_q_1=" + c_q_1 + ", c_q_2=" + c_q_2 + ", c_q_3=" + c_q_3 + ", c_q_4=" + c_q_4
				+ ", c_q_5=" + c_q_5 + ", c_q_6=" + c_q_6 + ", c_q_7=" + c_q_7 + ", c_q_8=" + c_q_8 + ", e_q_1=" + e_q_1
				+ ", e_q_2=" + e_q_2 + ", e_q_3=" + e_q_3 + "]";
	}
	
	
	
}
