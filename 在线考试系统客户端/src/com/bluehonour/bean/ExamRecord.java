package com.bluehonour.bean;

import java.io.Serializable;
import java.sql.Date;
/**
 * 考试记录
 * @author alin
 *
 */
public class ExamRecord implements Serializable{

	private int erid;
	private int id;
	private Date time;
	private int sid;
	private String c_a_1;
	private String c_a_2;
	private String c_a_3;
	private String c_a_4;
	private String c_a_5;
	private String c_a_6;
	private String c_a_7;
	private String c_a_8;
	
	private String e_a_1;
	private String e_a_2;
	private String e_a_3;
	public int getErid() {
		return erid;
	}
	public void setErid(int erid) {
		this.erid = erid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getC_a_1() {
		return c_a_1;
	}
	public void setC_a_1(String c_a_1) {
		this.c_a_1 = c_a_1;
	}
	public String getC_a_2() {
		return c_a_2;
	}
	public void setC_a_2(String c_a_2) {
		this.c_a_2 = c_a_2;
	}
	public String getC_a_3() {
		return c_a_3;
	}
	public void setC_a_3(String c_a_3) {
		this.c_a_3 = c_a_3;
	}
	public String getC_a_4() {
		return c_a_4;
	}
	public void setC_a_4(String c_a_4) {
		this.c_a_4 = c_a_4;
	}
	public String getC_a_5() {
		return c_a_5;
	}
	public void setC_a_5(String c_a_5) {
		this.c_a_5 = c_a_5;
	}
	public String getC_a_6() {
		return c_a_6;
	}
	public void setC_a_6(String c_a_6) {
		this.c_a_6 = c_a_6;
	}
	public String getC_a_7() {
		return c_a_7;
	}
	public void setC_a_7(String c_a_7) {
		this.c_a_7 = c_a_7;
	}
	public String getC_a_8() {
		return c_a_8;
	}
	public void setC_a_8(String c_a_8) {
		this.c_a_8 = c_a_8;
	}
	public String getE_a_1() {
		return e_a_1;
	}
	public void setE_a_1(String e_a_1) {
		this.e_a_1 = e_a_1;
	}
	public String getE_a_2() {
		return e_a_2;
	}
	public void setE_a_2(String e_a_2) {
		this.e_a_2 = e_a_2;
	}
	public String getE_a_3() {
		return e_a_3;
	}
	public void setE_a_3(String e_a_3) {
		this.e_a_3 = e_a_3;
	}
	public ExamRecord(int erid, int id, Date time, int sid, String c_a_1, String c_a_2, String c_a_3, String c_a_4,
			String c_a_5, String c_a_6, String c_a_7, String c_a_8, String e_a_1, String e_a_2, String e_a_3) {
		super();
		this.erid = erid;
		this.id = id;
		this.time = time;
		this.sid = sid;
		this.c_a_1 = c_a_1;
		this.c_a_2 = c_a_2;
		this.c_a_3 = c_a_3;
		this.c_a_4 = c_a_4;
		this.c_a_5 = c_a_5;
		this.c_a_6 = c_a_6;
		this.c_a_7 = c_a_7;
		this.c_a_8 = c_a_8;
		this.e_a_1 = e_a_1;
		this.e_a_2 = e_a_2;
		this.e_a_3 = e_a_3;
	}
	
	public ExamRecord(int id, Date time, int sid, String c_a_1, String c_a_2, String c_a_3, String c_a_4, String c_a_5,
			String c_a_6, String c_a_7, String c_a_8, String e_a_1, String e_a_2, String e_a_3) {
		super();
		this.id = id;
		this.time = time;
		this.sid = sid;
		this.c_a_1 = c_a_1;
		this.c_a_2 = c_a_2;
		this.c_a_3 = c_a_3;
		this.c_a_4 = c_a_4;
		this.c_a_5 = c_a_5;
		this.c_a_6 = c_a_6;
		this.c_a_7 = c_a_7;
		this.c_a_8 = c_a_8;
		this.e_a_1 = e_a_1;
		this.e_a_2 = e_a_2;
		this.e_a_3 = e_a_3;
	}
	public ExamRecord() {
		super();
	}
	@Override
	public String toString() {
		return "ExamRecord [erid=" + erid + ", id=" + id + ", time=" + time + ", sid=" + sid + ", c_a_1=" + c_a_1
				+ ", c_a_2=" + c_a_2 + ", c_a_3=" + c_a_3 + ", c_a_4=" + c_a_4 + ", c_a_5=" + c_a_5 + ", c_a_6=" + c_a_6
				+ ", c_a_7=" + c_a_7 + ", c_a_8=" + c_a_8 + ", e_a_1=" + e_a_1 + ", e_a_2=" + e_a_2 + ", e_a_3=" + e_a_3
				+ "]";
	}
	
	
}
