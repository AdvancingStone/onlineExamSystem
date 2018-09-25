package com.bluehonour.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;

import com.bluehonour.bean.ChoiceQuestion;
import com.bluehonour.bean.EssayQuestion;
import com.bluehonour.bean.ExamRecord;
import com.bluehonour.bean.ExaminationPaper;
import com.bluehonour.bean.Result;
import com.bluehonour.bean.Subject;
import com.bluehonour.bean.User;
import com.bluehonour.service.SubjectService;
import com.bluehonour.service.UserService;
import com.bluehonour.service.impl.SubjectServiceImpl;
import com.bluehonour.service.impl.UserServiceImpl;

public class ServerThread implements Runnable {
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	/**
	 * 用于向服务器发送的数据容器
	 */
	private HashMap<String,Object> toData;
	/**
	 * 用于从服务器接收数据的数据容器
	 */
	private HashMap<String,Object> fromData;
	/**
	 * 线程标识符
	 */
	private boolean threadFlag = true;

	public ServerThread(Socket socket) {
		super();
		try {
			ois = new ObjectInputStream(socket.getInputStream());
			oos = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		System.out.println("客户端连接完毕");
		while (threadFlag) {
			try {
				fromMessage();//接受客户端消息
				service();//处理客户端消息
				toMessage();//发送消息给客户端
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				threadFlag = false;
			} catch (IOException e) {
				e.printStackTrace();
				threadFlag = false;
			}
		}
	}

	/**
	 * 接收客户端的消息
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	private void fromMessage() throws ClassNotFoundException, IOException {
		fromData = (HashMap<String,Object>) ois.readObject();
	}

	/**
	 * 对客户端消息进行处理
	 */
	private void service() {
		Integer type = (Integer)fromData.get("type");
		UserService userService = new UserServiceImpl();
		SubjectService subjectService = new SubjectServiceImpl();
		
		switch (type) {
		//10001	:	登录操作
		case 10001:
			User user = (User)fromData.get("data");
			int i = userService.login(user);
			int id = userService.findId(user);
			if(i==1) {
				sendToMessageDate(20001, null);//管理员登录成功, 不携带任何数据
			}else if(i==2){
				sendToMessageDate(20002, id);//学员登录成功 , 将学号传回
			}else if(i==-1) {
				sendToMessageDate(20003, null);//登录失败 , 不携带任何数据
			}
			break;
		
		//10002	:	学员修改自身密码操作
		case 10002:
			User[] userArr = (User[])fromData.get("data");
			boolean updateFlag = userService.studentUpdate(userArr[0], userArr[1]);
			if(updateFlag) {
				
				sendToMessageDate(20004, null);//学员修改自身密码成功
			}else {
				sendToMessageDate(20005, null);//学员修改自身密码失败
			}
			break;
			
		//10003:	管理员增加学员
		case 10003:
			User user3 = (User) fromData.get("data");
			boolean insertFlag = userService.insert(user3);
			if(insertFlag) {
				sendToMessageDate(20006, null);//学员增加成功
			}else {
				sendToMessageDate(20007, null);//学员增加失败
			}
			break;
		//10004	:	管理员删除考试学员
		case 10004:
			String username = (String)fromData.get("data");
			boolean deleteFlag = userService.delete(username);
			if(deleteFlag) {
				sendToMessageDate(20008, null);//学员删除成功
			}else {
				sendToMessageDate(20009, null);//学员删除失败
			}
			break;
			
		//10005	:	管理员修改学员密码
		case 10005:
			User user5 = (User) fromData.get("data");
			boolean updateMFlag = userService.managerUpdate(user5);
			if(updateMFlag) {
				sendToMessageDate(20010, null);//学员修改成功
			}else {
				sendToMessageDate(20011, null);//学员修改失败
			}
			break;
		//10006	:	管理员查询学员信息
		case 10006:
			String userName = (String) fromData.get("data");
			User user6 = userService.findUserByName(userName);
			if(user6!=null) {
				sendToMessageDate(20012, user6);//学员查询成功
			}else {
				sendToMessageDate(20013, null);//学员查询失败
			}
			break;
		
		case 10007: //增加考题(选择题)
			ChoiceQuestion cq = (ChoiceQuestion)fromData.get("data");
			insertFlag = subjectService.insert(cq);
			if(insertFlag) {
				sendToMessageDate(20016, null);//选择题增加成功
			}else {
				sendToMessageDate(20017, null);//选择题增加失败
			}
			break;
			
		case 10008:  //修改考题(选择题)
			cq = (ChoiceQuestion)fromData.get("data");
			updateFlag = subjectService.update(cq);
			if(updateFlag) {
				sendToMessageDate(20018, null);// 选择题修改成功
			}else {
				sendToMessageDate(20019, null);//选择题修改失败
			}
			break;
		case 10009:	//删除考题(选择题)
			int cid = (int)fromData.get("data");
			deleteFlag = subjectService.deleteChoice(cid);
			if(deleteFlag) {
				sendToMessageDate(20020, null);// 选择题删除成功
			}else {
				sendToMessageDate(20021, null);//选择题删除失败
			}
			break;
		case 10010: //查询考题（按id)
			cid = (int)fromData.get("data");
			ChoiceQuestion c = subjectService.selectChoiceQuestionById(cid);
			if(c!=null) {
				sendToMessageDate(20022, c);// 选择题查询成功
			}else {
				sendToMessageDate(20023, null);//选择题查询失败
			}
			break;
		case 10011:	//查询所有考题
			ExaminationPaper selectAll = subjectService.selectAll();
			if(selectAll!=null) {
				sendToMessageDate(20024, selectAll);// 选择题删除成功
			}else {
				sendToMessageDate(20025, null);//选择题删除失败
			}
			break;

		//10012	:	管理员查询所有用户
		case 10012:
			List<User> userList = userService.findAll();
			if(!userList.isEmpty()) {
				sendToMessageDate(20014, userList);//所有用户查询成功
			}else {
				sendToMessageDate(20015, null);//所有用户查询失败
			}
			break;
		
		case 10013:	//批量导入考题（选择题）
			 @SuppressWarnings("unchecked") 
			 List<ChoiceQuestion> cqList = (List<ChoiceQuestion>) fromData.get("data");
			 ChoiceQuestion[] cqArr = new ChoiceQuestion[cqList.size()];
			 for(i=0;i<cqList.size();i++) {
				 cqArr[i] = cqList.get(i);
			 }
			i = subjectService.batchImportChoiceQuestion(cqArr);
			if(i>0) {
				sendToMessageDate(20026, i);//批量导入考题（选择题）成功
			}else {
				sendToMessageDate(20027, null);//批量导入考题（选择题）失败
			}
			break;
		case 10014:	//批量导入考题（简答题）
			@SuppressWarnings("unchecked") 
			List<EssayQuestion> eqList =  (List<EssayQuestion>) fromData.get("data");
			EssayQuestion[] eqArr = new EssayQuestion[eqList.size()];
			for(i=0;i<eqList.size();i++) {
				eqArr[i] = eqList.get(i);
			}
			i = subjectService.batchImportEssayQuestion(eqArr);
			if(i>0) {
				sendToMessageDate(20028, i);//批量导入考题（简答题）成功
			}else {
				sendToMessageDate(20029, null);//批量导入考题（简答题）失败
			}
			break;
			
		case 10015:	//增加简答题
			EssayQuestion eq = (EssayQuestion)fromData.get("data");
			insertFlag = subjectService.insert(eq);
			if(insertFlag) {
				sendToMessageDate(20030, null);//简答题增加成功
			}else {
				sendToMessageDate(20031, null);//简答题增加失败
			}
			break;
			
		case 10016:	//修改简答题
			eq = (EssayQuestion)fromData.get("data");
			updateFlag = subjectService.update(eq);
			if(updateFlag) {
				sendToMessageDate(20032, null);// 简答题修改成功
			}else {
				sendToMessageDate(20033, null);//简答题修改失败
			}
			break;
			
		case 10017:	//删除简答题
			int eid = (int)fromData.get("data");
			deleteFlag = subjectService.deleteEssay(eid);
			if(deleteFlag) {
				sendToMessageDate(20034, null);// 简答题删除成功
			}else {
				sendToMessageDate(20035, null);//简答题删除失败
			}
			break;
			
		case 10018:	//查询简答题id
			eid = (int)fromData.get("data");
			EssayQuestion e = subjectService.selectEssayQuestionById(eid);
			if(e!=null) {
				sendToMessageDate(20036, e);// 简答题查询成功
			}else {
				sendToMessageDate(20037, null);//简答题查询失败
			}
			break;
			
		case 10019://开始考试，从数据库随机选择一套试题
			
//			ExaminationPaper examPaper = subjectService.generateExamPaper();
			ExaminationPaper subject = subjectService.findExaminationPaper();
			if(subject!=null) {
				sendToMessageDate(20040, subject);
			}else {
				sendToMessageDate(20041, null);
			}
			break;
			
		case 10020: //查询所有考试成绩(按时间降序排列)
//			id = (int )fromData.get("data");
			Result r  = (Result) fromData.get("data");
			List<Result> listResult = subjectService.findAllResult(r);
			if(listResult!=null) {
				sendToMessageDate(20042,listResult);
			}else {
				sendToMessageDate(20043,null);
			}
			break;
			
		case 10021: //导出所有成绩
//			id = (int )fromData.get("data");
			Result re  = (Result) fromData.get("data");
			listResult = subjectService.findAllResult(re);
			if(listResult!=null) {
				sendToMessageDate(20044,listResult);
			}else {
				sendToMessageDate(20045,null);
			}
			break;
			
		case 10022: //	随机生成一份试题进数据库
			boolean b = subjectService.putStorage();
			if(b) {
				sendToMessageDate(20038,null);//成功
			}else {
				sendToMessageDate(20039,null);//失败
			}
			break;
		case 10023: //	保存学员的考试记录
			ExamRecord er = (ExamRecord) fromData.get("data");
			boolean examRecord = false;
			try {
				examRecord = subjectService.examRecord(er);
			} catch (Exception e1) {
				sendToMessageDate(20047,null);//失败
				e1.printStackTrace();
			}
			if(examRecord) {
				sendToMessageDate(20046,null);//成功
			}else {
				sendToMessageDate(20047,null);//失败
			}
			break;
		case 10024:	//根据erid查询学生的考试记录
			int erid = (int)fromData.get("data");
			ExamRecord record = subjectService.findExamRecordById(erid);
			if(record!=null) {
				sendToMessageDate(20048,record);//查询出该考生的考题纪录成功
			}else {
				sendToMessageDate(20049,null);//失败
			}
			break;
		case 10025:
			Result result = (Result) fromData.get("data");
			boolean bb = subjectService.mark(result);
			if(bb) {
				sendToMessageDate(20050,null);//评分成功
			}else {
				sendToMessageDate(20051,null);//失败
			}
			break;
		}
	}

	/**
	 * 将处理的消息封装到map中
	 * @param type
	 * @param data
	 */
	private void sendToMessageDate(int type, Object data) {
		toData = new HashMap<>();
		toData.put("type", type);
		toData.put("data", data);
	}
	
	/**
	 * 给客户端发送消息
	 */
	private void toMessage() {
		try {
			oos.writeObject(toData);
			oos.flush();//刷新缓冲区
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
