package com.bluehonour.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.bluehonour.bean.ChoiceQuestion;
import com.bluehonour.bean.EssayQuestion;
import com.bluehonour.bean.ExamRecord;
import com.bluehonour.bean.ExaminationPaper;
import com.bluehonour.bean.Result;
import com.bluehonour.bean.Subject;
import com.bluehonour.dao.SubjectDao;
import com.bluehonour.utils.DBUtils;

public class SubjectDaoImpl implements SubjectDao {

	@Override
	public boolean insert(ChoiceQuestion cq) {
		QueryRunner qr = new QueryRunner();
		Connection conn = DBUtils.getConn();
		String sql = "insert into choice_question values( choice_question_cid_seq.nextval, '"+ 
				cq.getC_Question() +"', '" + cq.getRight_Answer()+"', '"+
				cq.getA_Answer() +"', '"+ cq.getB_Answer() +"', '"+cq.getC_Answer() +"', '"+
				cq.getD_Answer() +"', "+cq.getMark() +")";
		System.out.println(sql);
		boolean flag = false;
		try {
			int i = qr.update(conn, sql);
			if(i>0) {
				flag = true;
			}
		} catch (SQLException e) {
			System.out.println("试题内容重了哇，违反唯一约束了");
		}
		return flag;
	}

	@Override
	public boolean insert(EssayQuestion eq) {
		QueryRunner qr = new QueryRunner();
		Connection conn = DBUtils.getConn();
		int mark = eq.getMark()==null ? 20 : eq.getMark();
		String sql = "insert into essay_question values(essay_question_eid_seq.nextval, '"+
				eq.getE_Question() +"' ,"+mark+" )";
		System.out.println(sql);
		boolean flag = false;
		try {
			int i = qr.update(conn, sql);
			if(i>0) {
				flag = true;
			}
		} catch (SQLException e) {
			System.out.println("试题内容重了哇，违反唯一约束了");
		}
		return flag;
	}

	@Override
	public boolean update(ChoiceQuestion cq) {
		QueryRunner qr = new QueryRunner(true);
		Connection conn = DBUtils.getConn();
		String sql = "update choice_question set c_question=?, right_answer=?, a_answer=?, b_answer=?,"
				+ "c_answer=?, d_answer=?, mark=? where cid=?";
		System.out.println(sql);
		boolean flag = false;
		try {
			Object[] params = new Object[] {cq.getC_Question(),cq.getRight_Answer(),cq.getA_Answer(),cq.getB_Answer(),
					cq.getC_Answer(),cq.getD_Answer(),cq.getMark(),cq.getCid()};
			int i = qr.update(conn, sql, params);
			if(i>0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("试题已存在  或   请全部输入哇，我可没有回显功能，为什么？ '懒-_-'");
		}
		return flag;
	}

	@Override
	public boolean update(EssayQuestion eq) {
		QueryRunner qr = new QueryRunner();
		Connection conn = DBUtils.getConn();
		String sql = "update essay_question set e_question=?,mark=? where eid=?";
		System.out.println(sql);
		boolean flag = false;
		try {
			int i = qr.update(conn, sql,eq.getE_Question(),eq.getMark(),eq.getEid());
			if(i>0) {
				flag = true;
			}
		} catch (SQLException e) {
			System.out.println("试题内容重了哇，违反唯一约束了");
		}
		return flag;
	}

	@Override
	public boolean deleteChoice(int id) {
		QueryRunner qr = new QueryRunner();
		Connection conn = DBUtils.getConn();
		String sql = "delete from choice_question where cid = ?";
		System.out.println(sql);
		try {
			int i = qr.update(conn,sql,id);
			if(i>0) return true;
		} catch (SQLException e) {
			System.out.println("id号不存在");
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean deleteEssay(int id) {
		QueryRunner qr = new QueryRunner();
		Connection conn = DBUtils.getConn();
		String sql = "delete from essay_question where eid = ?";
		System.out.println(sql);
		try {
			int i = qr.update(conn,sql,id);
			if(i>0) return true;
		} catch (SQLException e) {
			System.out.println("id号不存在");
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public ChoiceQuestion selectChoiceQuestionById(int id) {
		QueryRunner qr = new QueryRunner();
		Connection conn = DBUtils.getConn();
		String sql = "select * from choice_question where cid = " + id;
		System.out.println(sql);
		try {
			ChoiceQuestion cq = qr.query(conn, sql, new BeanHandler<>(ChoiceQuestion.class));
			return cq;
		} catch (SQLException e) {
			System.out.println("id号不存在");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public EssayQuestion selectEssayQuestionById(int id) {
		QueryRunner qr = new QueryRunner();
		Connection conn = DBUtils.getConn();
		String sql = "select eid,e_question,mark from essay_question where eid = " + id;
		System.out.println(sql);
		try {
			EssayQuestion eq = qr.query(conn, sql, new BeanHandler<>(EssayQuestion.class));
			return eq;
		} catch (SQLException e) {
			System.out.println("id号不存在");
			e.printStackTrace();
		}
		return null;
	}



	/**
	 * 查询所有试题
	 */
	@Override
	public ExaminationPaper selectAll() {
		QueryRunner qr = new QueryRunner();
		Connection conn = DBUtils.getConn();
		String sql1 = "select * from choice_question";
		String sql2 = "select * from essay_question";
		try {
			List<ChoiceQuestion> cqList = qr.query(conn, sql1, new BeanListHandler<>(ChoiceQuestion.class));
			List<EssayQuestion> eqList = qr.query(conn, sql2, new BeanListHandler<>(EssayQuestion.class));
			ExaminationPaper ep = new ExaminationPaper(cqList,eqList);
			return ep;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}


	/**
	 * 生成试卷
	 */
	@Override
	public ExaminationPaper generateExamPaper() {
		QueryRunner qr = new QueryRunner();
		Connection conn = DBUtils.getConn();
		String sql1 = "select * from (select * from choice_question order by dbms_random.value) where rownum <=8 ";
		String sql2 = "select * from (select * from essay_question order by dbms_random.value) where rownum <=3";
		try {
			List<ChoiceQuestion> cqList = qr.query(conn, sql1, new BeanListHandler<>(ChoiceQuestion.class));
			List<EssayQuestion> eqList = qr.query(conn, sql2, new BeanListHandler<>(EssayQuestion.class));
			ExaminationPaper ep = new ExaminationPaper(cqList,eqList);
			return ep;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将随机生成的题存入数据库
	 */
	@Override
	public boolean putStorage() {
		boolean flag = true;
		QueryRunner qr = new QueryRunner();
		Connection conn = DBUtils.getConn();
		//生成的题库
		ExaminationPaper examPaper = generateExamPaper();
		//遍历记录
		List<ChoiceQuestion> cqList = examPaper.getCqList();
		List<EssayQuestion> eqList = examPaper.getEqList();
		
		int index = -1;
		//先查最大的sid
		String sql = "select subject_sid_seq.nextval from dual";
		ResultSet rs = null;
		PreparedStatement statement = null;
		try {
			statement = conn.prepareStatement(sql);
			rs = statement.executeQuery();
			rs.next();
			index = rs.getInt(1);
			sql = "insert into subject(sid) values("+index+")";
//			System.out.println(sql);
			statement.executeUpdate(sql);
		} catch (SQLException e1) {
			flag = false;
			e1.printStackTrace();
		} finally {
			DBUtils.close(statement, rs);
		}
		
		if(index==-1) {
			System.out.println("导入失败");
			return false;
		}
		//将选择题 简答题题库存入数据库subject，真是哔了狗，写的我头疼发麻
		//没法通过set方法动态的变，哎，只能这样牺牲性能
		Subject subject = new Subject();
		for(int i=0; i<cqList.size(); i++) {
			sql = "update subject set c_q_"+(i+1)+" = '"+cqList.get(i).getC_Question()+"' where sid = "+index;
//			System.out.println(sql);
			try {
				qr.update(conn,sql);
			} catch (SQLException e) {
				flag = false;
				e.printStackTrace();
			}
		}
		for(int i=0; i<eqList.size(); i++) {
			sql = "update subject set e_q_"+(i+1)+" = '"+eqList.get(i).getE_Question()+"' where sid = "+index;
//			System.out.println(sql);
			try {
				qr.update(conn,sql);
			} catch (SQLException e) {
				flag = false;
				e.printStackTrace();
			}
		}
		return flag;
	}

	/**
	 * 记录考试的信息，人，题库，答案
	 */
	@Override
	public boolean examRecord(ExamRecord er) {
		QueryRunner qr = new QueryRunner();
		Connection conn = DBUtils.getConn();
		String sql = "insert into exam_record values(exam_record_erid_seq.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		System.out.println(sql);
		try {
			Object[] parames = new Object[] {er.getId(),er.getTime(),er.getSid(),er.getC_a_1(),er.getC_a_2(),
					er.getC_a_3(),er.getC_a_4(),er.getC_a_5(),er.getC_a_6(),er.getC_a_7(),er.getC_a_8(),
					er.getE_a_1(),er.getE_a_2(),er.getE_a_3()};
			int i = qr.update(conn,sql,parames );
			if(i>0) return true;
		} catch (SQLException e) {
			System.out.println("很遗憾，导入记录失败");
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 批量导入选择题
	 * 试题内容，正确选项（abcd），a答案，b答案，c答案，d答案，分值
	 */
	@Override
	public int batchImportChoiceQuestion(ChoiceQuestion[] cq) {
		int num = 0;
		QueryRunner qr = new QueryRunner();
		Connection conn = DBUtils.getConn();
		String sql = "insert into choice_question values(choice_question_cid_seq.nextval,?,?,?,?,?,?,? )";
//		System.out.println(sql);
		try {
			conn.setAutoCommit(false);
			boolean flag = false;
			Object[][] params = new Object[cq.length][7];
			for(int i=0;i<cq.length;i++) {
				params[i][0] = cq[i].getC_Question();
				params[i][1] = cq[i].getRight_Answer();
				params[i][2] = cq[i].getA_Answer();
				params[i][3] = cq[i].getB_Answer();
				params[i][4] = cq[i].getC_Answer();
				params[i][5] = cq[i].getD_Answer();
				params[i][6] = cq[i].getMark();
			}
			qr.batch(conn, sql, params);
			conn.commit();
			num = cq.length;
		} catch (SQLException e) {
			System.out.println("反正我不知道原因，就是出错啦，回滚，我就不告诉你原因，你能奈我何");
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return num;
	}

	/**
	 * 批量导入简答题
	 * 试题内容，分值
	 */
	@Override
	public int batchImportEssayQuestion(EssayQuestion[] eq) {
		int num = 0;
		QueryRunner qr = new QueryRunner();
		Connection conn = DBUtils.getConn();
		String sql = "insert into essay_question values(essay_question_eid_seq.nextval,?,?)";
		System.out.println(sql);
		boolean flag = false;
		Object[][] params = new Object[eq.length][2];
		try {
			conn.setAutoCommit(false);
			for(int i=0;i<eq.length;i++) {
				params[i][0] = eq[i].getE_Question();
				params[i][1] = eq[i].getMark();
			}
			qr.batch(conn, sql, params);
			conn.commit();
			num = eq.length;
		} catch (SQLException e) {
			System.out.println("反正我不知道原因，就是出错啦，回滚，我就不告诉你原因，你能奈我何");
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return num;
	}

	@Override
	public List<Result> findAllResult(Result r) {
		QueryRunner qr = new QueryRunner();
		Connection conn = DBUtils.getConn();
		int id = r.getId();
		String sql = "select r.record  from result r left join exam_record e  " + 
				"on r.id = e.id and r.erid=e.erid " + 
				"where r.id =  " + id +
				" order by e.time desc " ;
		System.out.println(sql);
		List<Result> list = null;
		try {
			list = qr.query(conn, sql,new BeanListHandler<>(Result.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public ExaminationPaper findExaminationPaper() {
		
		QueryRunner qr = new QueryRunner();
		Connection conn = DBUtils.getConn();
		//select * from (select * from choice_question order by dbms_random.value) where rownum <=8
		String sql = "select * from (select * from subject order by dbms_random.value) where rownum <= 1";
		System.out.println(sql);
		Subject subject = new Subject();
		int sid = 0;
		ExaminationPaper ep = new ExaminationPaper();
		try {
			subject = qr.query(conn, sql, new BeanHandler<>(Subject.class));
			sid = subject.getSid();
			List<ChoiceQuestion>cq = new ArrayList<>();
			List<EssayQuestion>eq = new ArrayList<>();
			sql = "select * from choice_question where c_question = '" + subject.getC_q_1()+"'";
			cq.add(qr.query(conn, sql,new BeanHandler<>(ChoiceQuestion.class)));
			sql = "select * from choice_question where c_question = '" + subject.getC_q_2()+"'";
			cq.add(qr.query(conn, sql,new BeanHandler<>(ChoiceQuestion.class)));
			sql = "select * from choice_question where c_question = '" + subject.getC_q_3()+"'";
			cq.add(qr.query(conn, sql,new BeanHandler<>(ChoiceQuestion.class)));
			sql = "select * from choice_question where c_question = '" + subject.getC_q_4()+"'";
			cq.add(qr.query(conn, sql,new BeanHandler<>(ChoiceQuestion.class)));
			sql = "select * from choice_question where c_question = '" + subject.getC_q_5()+"'";
			cq.add(qr.query(conn, sql,new BeanHandler<>(ChoiceQuestion.class)));
			sql = "select * from choice_question where c_question = '" + subject.getC_q_6()+"'";
			cq.add(qr.query(conn, sql,new BeanHandler<>(ChoiceQuestion.class)));
			sql = "select * from choice_question where c_question = '" + subject.getC_q_7()+"'";
			cq.add(qr.query(conn, sql,new BeanHandler<>(ChoiceQuestion.class)));
			sql = "select * from choice_question where c_question = '" + subject.getC_q_8()+"'";
			cq.add(qr.query(conn, sql,new BeanHandler<>(ChoiceQuestion.class)));
			
			sql = "select * from essay_question where e_question = '" + subject.getE_q_1()+"'";
			eq.add(qr.query(conn, sql,new BeanHandler<>(EssayQuestion.class)));
			sql = "select * from essay_question where e_question = '" + subject.getE_q_2()+"'";
			eq.add(qr.query(conn, sql,new BeanHandler<>(EssayQuestion.class)));
			sql = "select * from essay_question where e_question = '" + subject.getE_q_3()+"'";
			eq.add(qr.query(conn, sql,new BeanHandler<>(EssayQuestion.class)));
			
			ep.setCqList(cq);
			ep.setEqList(eq);
			ep.setSid(sid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ep;
	}

	@Override
	public ExamRecord findExamRecordById(int erid) {
		QueryRunner qr = new QueryRunner();
		Connection conn = DBUtils.getConn();
		String sql = "select  * from exam_record where erid = " + erid;
		ExamRecord record = null;
		try {
			record = qr.query(conn, sql,new BeanHandler<>(ExamRecord.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return record;
	}

	@Override
	public boolean mark(Result result) {
		QueryRunner qr = new QueryRunner();
		Connection conn = DBUtils.getConn();
		String sql = "insert into result values(result_rid_seq.nextval,?,?,?) ";
		try {
			Object[] params = new Object[] {result.getErid(),result.getId(),result.getRecord()};
			int i = qr.update(conn,sql,params);
			if(i>0) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}


}
