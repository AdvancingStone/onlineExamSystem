package com.bluehonour.dao;

import java.util.List;

import com.bluehonour.bean.ChoiceQuestion;
import com.bluehonour.bean.EssayQuestion;
import com.bluehonour.bean.ExamRecord;
import com.bluehonour.bean.ExaminationPaper;
import com.bluehonour.bean.Result;

/**
 * 管理员对题库进行CRUD
 * @author alin
 *
 */
public interface SubjectDao {
	
	/**
	 * 增加选择题
	 * @param cq
	 * @return boolean
	 */
	boolean insert(ChoiceQuestion cq);
	
	/**
	 * 增加简答题
	 * @param eq
	 * @return boolean
	 */
	boolean insert(EssayQuestion eq);
	
	/**
	 * 修改选择题
	 * @param cq
	 * @return boolean
	 */
	boolean update(ChoiceQuestion cq);
	
	/**
	 * 修改简答题
	 * @param eq
	 * @return boolean
	 */
	boolean update(EssayQuestion eq);
	
	/**
	 * 删除选择题
	 * @param id
	 * @return boolean
	 */
	boolean deleteChoice(int id);
	
	/**
	 * 删除简答题
	 * @param  id
	 * @return boolean
	 */
	boolean deleteEssay(int id);
	
	/**
	 * 根据id查询选择题
	 * @param id
	 * @return
	 */
	ChoiceQuestion selectChoiceQuestionById(int id);
	
	/**
	 * 根据id查询简答题
	 * @param id
	 * @return
	 */
	EssayQuestion selectEssayQuestionById(int id);
	
	/**
	 * 查询所有试题，选择+简答
	 * @return ExaminationPaper
	 */
	ExaminationPaper selectAll();
	
	/**
	 * 生成试卷
	 * @return
	 */
	ExaminationPaper generateExamPaper();
	
	/**
	 * 将随机生成的考题存进数据库哇
	 */
	boolean putStorage();
	
	/**
	 * 考试记录，答案,人等
	 */
	boolean examRecord(ExamRecord er);
	
	/**
	 * 批量导入选择题
	 * @param cq
	 * @return 导入成功的试题数
	 */
	int batchImportChoiceQuestion(ChoiceQuestion[] cq);
	
	/**
	 * 批量导入简答题
	 * @param eq
	 * @return
	 */
	int batchImportEssayQuestion(EssayQuestion[] eq);

	/**
	 * 查询所有考试成绩
	 * @return
	 */
	List<Result> findAllResult(Result ep);

	/**
	 * 从数据库随机选择一套试题
	 * @return
	 */
	ExaminationPaper findExaminationPaper();

	/**
	 *  查询该学员的考试记录
	 * @return
	 */
	ExamRecord findExamRecordById(int erid);

	/**
	 * 对学员考试成绩进行评分,
	 * @param result
	 * @return
	 */
	boolean mark(Result result);
}
