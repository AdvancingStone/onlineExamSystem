package com.bluehonour.service.impl;

import java.util.List;

import com.bluehonour.bean.ChoiceQuestion;
import com.bluehonour.bean.EssayQuestion;
import com.bluehonour.bean.ExamRecord;
import com.bluehonour.bean.ExaminationPaper;
import com.bluehonour.bean.Result;
import com.bluehonour.dao.SubjectDao;
import com.bluehonour.dao.impl.SubjectDaoImpl;
import com.bluehonour.service.SubjectService;

public class SubjectServiceImpl implements SubjectService{

	private SubjectDao subjectDao= new SubjectDaoImpl();
	
	@Override
	public boolean insert(ChoiceQuestion cq) {
		return subjectDao.insert(cq);
	}

	@Override
	public boolean insert(EssayQuestion eq) {
		return subjectDao.insert(eq);
	}

	@Override
	public boolean update(ChoiceQuestion cq) {
		return subjectDao.update(cq);
	}

	@Override
	public boolean update(EssayQuestion eq) {
		return subjectDao.update(eq);
	}

	@Override
	public boolean deleteChoice(int id) {
		return subjectDao.deleteChoice(id);
	}

	@Override
	public boolean deleteEssay(int id) {
		return subjectDao.deleteEssay(id);
	}

	@Override
	public ChoiceQuestion selectChoiceQuestionById(int id) {
		return subjectDao.selectChoiceQuestionById(id);
	}

	@Override
	public EssayQuestion selectEssayQuestionById(int id) {
		return subjectDao.selectEssayQuestionById(id);
	}

	@Override
	public ExaminationPaper selectAll() {
		return subjectDao.selectAll();
	}

	@Override
	public ExaminationPaper generateExamPaper() {
		return subjectDao.generateExamPaper();
	}

	@Override
	public boolean putStorage() {
		return subjectDao.putStorage();
	}

	@Override
	public boolean examRecord(ExamRecord er) {
		return subjectDao.examRecord(er);
	}

	@Override
	public int batchImportChoiceQuestion(ChoiceQuestion[] cq) {
		return subjectDao.batchImportChoiceQuestion(cq);
	}

	@Override
	public int batchImportEssayQuestion(EssayQuestion[] eq) {
		return subjectDao.batchImportEssayQuestion(eq);
	}

	@Override
	public List<Result> findAllResult(Result r) {
		return subjectDao.findAllResult(r);
	}

	@Override
	public ExaminationPaper findExaminationPaper() {
		return subjectDao.findExaminationPaper();
	}

	@Override
	public ExamRecord findExamRecordById(int erid) {
		return subjectDao.findExamRecordById(erid);
	}

	@Override
	public boolean mark(Result result) {
		return subjectDao.mark(result);
	}

}
