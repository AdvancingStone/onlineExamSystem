package com.bluehonour.test;

import java.sql.Date;
import java.util.List;

import org.junit.Test;

import com.bluehonour.bean.ChoiceQuestion;
import com.bluehonour.bean.EssayQuestion;
import com.bluehonour.bean.ExamRecord;
import com.bluehonour.bean.ExaminationPaper;
import com.bluehonour.dao.SubjectDao;
import com.bluehonour.dao.impl.SubjectDaoImpl;

public class SubjectDaoImplTest {
	SubjectDao subjectDao = new SubjectDaoImpl();
	
	@Test
	public void insert_choiceQuestionTest() {
		ChoiceQuestion cq = new ChoiceQuestion() ;
		cq.setC_Question("你喜欢什ffdf么动物");
		cq.setRight_Answer("A");
		cq.setA_Answer("猫");
		cq.setB_Answer("狗");
		cq.setC_Answer("虎");
		cq.setD_Answer("狼");
		cq.setMark(5);
		boolean b = subjectDao.insert(cq);
		System.out.println(b);
	}
	@Test
	public void insert_essayQuestionTest() {
		EssayQuestion eq = new EssayQuestion("请介绍一hffhh下你自己？");
		boolean b = subjectDao.insert(eq);
		System.out.println(b);
	}
	
	@Test
	public void update_choiceQuestionTest() {
		ChoiceQuestion cq = new ChoiceQuestion(6, "1撒哈哈assdfs3", "d", "ffgasdf", "ffsff", "fffasda", "ffcvxf", 23) ;
		boolean b = subjectDao.update(cq);
		System.out.println(b);
	}
	
	@Test
	public void update_essayQuestionTest() {
		EssayQuestion eq = new EssayQuestion(11,"请介sds绍一hffhh下你自己？",45);
		boolean b = subjectDao.update(eq);
		System.out.println(b);
	}
	@Test
	public void deleteChoiceTest() {
		boolean b = subjectDao.deleteChoice(7);
		System.out.println(b);
	}
	@Test
	public void deleteEssayTest() {
		boolean b = subjectDao.deleteEssay(12);
		System.out.println(b);
	}
	
	@Test
	public void selectChoiceQuestionByIdTest() {
		ChoiceQuestion cq = subjectDao.selectChoiceQuestionById(1);
		System.out.println(cq);
	}
	
	@Test
	public void selectEssayQuestionByIdTest() {
		EssayQuestion eq = subjectDao.selectEssayQuestionById(2);
		System.out.println(eq);
	}
	
	@Test
	public void generateExamPaperTest() {
		ExaminationPaper examPaper = subjectDao.generateExamPaper();
		List<ChoiceQuestion> cqList = examPaper.getCqList();
		List<EssayQuestion> eqList = examPaper.getEqList();
		for(ChoiceQuestion cq : cqList) {
			System.out.println(cq);
		}
		for(EssayQuestion eq : eqList) {
			System.out.println(eq);
		}
	}
	@Test
	public void selectAllTest() {
		ExaminationPaper examPaper = subjectDao.selectAll();
		List<ChoiceQuestion> cqList = examPaper.getCqList();
		List<EssayQuestion> eqList = examPaper.getEqList();
		for(ChoiceQuestion cq : cqList) {
			System.out.println(cq);
		}
		for(EssayQuestion eq : eqList) {
			System.out.println(eq);
		}
	}
	
	@Test
	public void putStorageTest() {
		boolean putStorage = subjectDao.putStorage();
		
	}
	@Test
	public void examRecordTest() {
		//int id, Date time, int sid, String c_a_1, String c_a_2, String c_a_3, String c_a_4, String c_a_5,
		//String c_a_6, String c_a_7, String c_a_8, String e_a_1, String e_a_2, String e_a_3
		java.util.Date date = new java.util.Date();
		ExamRecord er = new ExamRecord(41,new Date(date.getTime()),32,"1 ","2","3","","","","","","","","");
		boolean b = subjectDao.examRecord(er);
		System.out.println(b);
	}
	
	@Test
	public void batchImportChoiceQuestionTest() {
		ChoiceQuestion[] cq = new ChoiceQuestion[] {new ChoiceQuestion("4","a","1","1","1","1",5),
				new ChoiceQuestion("6","a","1","1","1","1",5),new ChoiceQuestion("5","a","1","1","1","1",5)};
		int i = subjectDao.batchImportChoiceQuestion(cq);
		System.out.println(i);
	}
	@Test
	public void batchImportEssayQuestionTest() {
		EssayQuestion[] cq = new EssayQuestion[] {new EssayQuestion("ddfgdfggd",2),new EssayQuestion("ddddffg2",2)};
		int i = subjectDao.batchImportEssayQuestion(cq);
		System.out.println(i);
	}
}
