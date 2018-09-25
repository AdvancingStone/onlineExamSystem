package com.bluehonour.client;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.bluehonour.bean.ChoiceQuestion;
import com.bluehonour.bean.EssayQuestion;
import com.bluehonour.bean.ExamRecord;
import com.bluehonour.bean.ExaminationPaper;
import com.bluehonour.bean.Result;
import com.bluehonour.bean.Subject;
import com.bluehonour.bean.User;
import com.bluehonour.view.ViewUtils;

public class ClientMain {

	private static Socket socket;
	/**
	 * 用于从服务器接收数据的流
	 */
	private static ObjectInputStream ois;
	/**
	 * 用于向服务器发送数据的流
	 */
	private static ObjectOutputStream oos;
	/**
	 * 用于向服务器发送的数据容器
	 */
	private static HashMap<String, Object> toData;
	/**
	 * 用于从服务器接收数据的数据容器
	 */
	private static HashMap<String, Object> fromData;

	/**
	 * 用户名
	 */
	private static String username;
	/**
	 * 学员id
	 */
	private static int id;
	private static int erid;

	public static void main(String[] args) {
		try {
			socket = new Socket("localhost", 10086);
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());

			// 提示输出账号密码
			User user = ViewUtils.welcomeView();
			username = user.getUsername();

			// 发消息给服务器
			toMessage(10001, user);
			// 从服务器接收消息
			fromMessage();

			Integer type = (Integer) fromData.get("type");
			switch (type) {
			case 20001:
				// 管理员登录成功
				managerClient();
				break;
			case 20002:
				// 学员登录成功
				id = (int) fromData.get("data");
				studentClient();
				break;
			case 20003:
				// 登录失败
				System.out.println("很遗憾, 输入错误 ,程序已关闭");
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 学员的客户端
	 */
	private static void studentClient() {
		System.out.println("******\t欢迎登录学员系统\t******");
		while (true) {
			int menu = ViewUtils.studentMenuView();
			switch (menu) {
			case 0:// 退出
				System.out.println(username+"已退出");
				System.exit(0);
				break;
			case 1:// 修改密码
				User oldUser = new User(username, null);
				User newUser = ViewUtils.updateUserPassView_s(oldUser);
				toMessage(10002, new User[] { oldUser, newUser });
				break;
			case 2:// 开始考试，随机生成一套试题
				toMessage(10019,null);
				break;
			case 3:// 查询所有考试成绩(按时间降序排列)
				Result rs = new Result();
				rs.setId(id);
				toMessage(10020,rs);
				break;
			case 4:// 导出所有成绩
				rs = new Result();
				rs.setId(id);
				toMessage(10021,rs);
				break;
			}
			fromMessage();
			service();
		}
	}

	@SuppressWarnings("unchecked")
	private static void service() {
		Integer type = (Integer) fromData.get("type");
		switch (type) {
		case 20004:
			// 学员修改自身密码成功
			System.out.println("恭喜你, 密码修改成功了 ! ");
			break;
		case 20005:
			// 学员修改自身密码失败
			System.out.println("很遗憾, 密码修改失败了, 原密码输入错误");
			break;
		case 20006:
			// 管理员增加考试学员成功
			System.out.println("恭喜你, 学员增加成功 ! ");
			break;
		case 20007:
			// 管理员增加考试学员失败
			System.out.println("很遗憾, 学员增加失败");
			break;
		case 20008:
			// 管理员删除考试学员成功
			System.out.println("恭喜你, 学员删除成功 ! ");
			break;
		case 20009:
			// 管理员删除考试学员失败
			System.out.println("很遗憾, 学员删除失败");
			break;
		case 20010:
			// 管理员修改考试学员成功
			System.out.println("恭喜你, 学员密码修改成功 ! ");
			break;
		case 20011:
			// 管理员修改考试学员失败
			System.out.println("很遗憾, 学员密码修改失败");
			break;
		case 20012:
			// 管理员修改考试学员成功
			User user = (User) fromData.get("data");
			System.out.println("查询成功 ,信息如下:");
			System.out.println(user);
			break;
		case 20013:
			// 查询所有
			System.out.println("很遗憾, 学员不存在 !");
			break;
		case 20014:
			// 查询所有用户成功
			System.out.println("查询成功 ,信息如下:");
			List<User> userList = (List<User>) fromData.get("data");
			for (User u : userList) {
				System.out.println(u);
			}
			System.out.println();
			break;
		case 20015:
			// 查询所有用户失败
			System.out.println("很遗憾, 查询出错了 !");
			break;
		
		case 20016:
			System.out.println("恭喜您，增加选择题成功");
			break;
			
		case 20017:
			System.out.println("很遗憾，增加选择题失败");
			break;
			
		case 20018:
			System.out.println("恭喜你，修改选择题成功");
			break;
			
		case 20019:
			System.out.println("很遗憾，修改选择题失败");
			System.out.println("很大可能是违反完整性约束，有外键约束");
			break;
			
		case 20020:
			System.out.println("恭喜你，删除选择题成功");
			break;
			
		case 20021:
			System.out.println("很遗憾，删除选择题失败");
			System.out.println("很可能是违反完整性约束，外键约束");
			break;
			
		case 20022:
			System.out.println("恭喜您，查询选择题成功，信息如下：");
			ChoiceQuestion cq = (ChoiceQuestion)fromData.get("data");
			System.out.println(cq);
			break;
			
		case 20023:
			System.out.println("很遗憾，查询失败");
			break;
			
		case 20024:
			System.out.println("考题（选择+简答）查询成功，信息如下");
			ExaminationPaper examPaper = (ExaminationPaper)fromData.get("data");
			List<ChoiceQuestion> cqList = examPaper.getCqList();
			List<EssayQuestion> eqList = examPaper.getEqList();
			for(ChoiceQuestion cqa : cqList) {
				System.out.println(cqa);
			}
			for(EssayQuestion eqa : eqList) {
				System.out.println(eqa);
			}
			break;
			
		case 20025:
			System.out.println("很遗憾，查询失败");
			break;
			
		case 20026:
			int i = (int) fromData.get("data");
			System.out.println("恭喜您，批量导入选择题成功"+i+"条");
			break;
			
		case 20027:
			System.out.println("很遗憾，批量导入失败，进行事务回滚操作");
			break;
			
		case 20028:
			i = (int) fromData.get("data");
			System.out.println("恭喜您，批量导入简答题成功"+i+"条");
			break;
			
		case 20029:
			System.out.println("很遗憾，批量导入失败，进行事务回滚操作");
			break;
			
		case 20030:
			System.out.println("添加简答题成功");
			break;
			
		case 20031:
			System.out.println("添加简答题失败");
			break;
			
		case 20032:
			System.out.println("修改简答题成功");
			break;
			
		case 20033:
			System.out.println("修改简答题失败");
			System.out.println("很可能是违反完整性约束，外键约束");
			break;
			
		case 20034:
			System.out.println("删除简答题成功");
			break;
			
		case 20035:
			System.out.println("删除简答题失败");
			System.out.println("很可能是违反完整性约束，外键约束");
			break;
			
		case 20036:
			System.out.println("查询简答题结果如下：");
			EssayQuestion eq = (EssayQuestion)fromData.get("data");
			System.out.println(eq);
			break;
			
		case 20037:
			System.out.println("查询简答题失败");
			break;
			
		case 20038:
			System.out.println("生成试卷入库成功");
			break;
		case 20039:
			System.out.println("生成试卷入库失败");
			break;
			
		/////////////////////
			/////////////////////////
			///////////////
			///////////
		/*
		case 30040:
			System.out.println("生成试卷成功");
			//获得随机生成一套试卷
			ExaminationPaper ep =  (ExaminationPaper) fromData.get("data");
			//选择题
			List<ChoiceQuestion> cqlist = ep.getCqList();
			//简答题
			List<EssayQuestion> eqlist = ep.getEqList();
		//////////////////
			int sid=ep.getSid();
//			String c_a_1="",c_a_2="",c_a_3="",c_a_4="",c_a_5="",c_a_6="",c_a_7="",c_a_8="",e_a_1="",e_a_2="",e_a_3="";
			Scanner s = new Scanner(System.in);
			
			//通过map解决变量名的动态变化问题，真的是好用啊
			Map<Integer,String> map = new HashMap<>();
			for(i=0;i<cqlist.size();i++) {
				System.out.println("*********\t第"+(i+1)+"个选择题\t*************");
				System.out.println(cqlist.get(i).getC_Question());
				System.out.println("A:	"+cqlist.get(i).getA_Answer());
				System.out.println("B:	"+cqlist.get(i).getB_Answer());
				System.out.println("C:	"+cqlist.get(i).getC_Answer());
				System.out.println("D:	"+cqlist.get(i).getD_Answer());
				System.out.println("请选择 A-B-C-D");
				String answer = s.nextLine();
				map.put(i, answer);
			}
			for(i=0;i<eqlist.size();i++) {
				System.out.println("*********\t第"+(i+1)+"个简答题\t*************");
				System.out.println(cqlist.get(i).getC_Question());
				System.out.println("请输入您的答案");
				String answer = s.nextLine();
				map.put(i+8, answer);
			}
			java.util.Date date = new java.util.Date();
			
			ExamRecord er = new ExamRecord(id,new Date(date.getTime()),sid,map.get(0),map.get(1),map.get(2)
					,map.get(3),map.get(4),map.get(5),map.get(6),map.get(7),map.get(8)
					,map.get(9),map.get(10));
//			ExamRecord er = new ExamRecord(id,new Date(date.getTime()),sid,c_a_1,c_a_2,c_a_3,c_a_4,c_a_5,
//					c_a_6,c_a_7,c_a_8,e_a_1,e_a_2,e_a_3);
			break;
			
			*/
		//////////////////////////////////////////////////////
			/////////////////////////////////////////////
			
		case 20040:
			System.out.println("生成试卷成功");
			//获得随机生成一套试卷
			ExaminationPaper ep =  (ExaminationPaper) fromData.get("data");
			//选择题
			List<ChoiceQuestion> cqlist = ep.getCqList();
			//简答题
			List<EssayQuestion> eqlist = ep.getEqList();
			
			int sid=ep.getSid();
//			System.out.println(sid);
//			String c_a_1="",c_a_2="",c_a_3="",c_a_4="",c_a_5="",c_a_6="",c_a_7="",c_a_8="",e_a_1="",e_a_2="",e_a_3="";
			Scanner s = new Scanner(System.in);
			
			//通过map解决变量名的动态变化问题，真的是好用啊
			Map<Integer,String> map = new HashMap<>();
			for(i=0;i<cqlist.size();i++) {
				System.out.println("*********第"+(i+1)+"个选择题*************");
				System.out.println(cqlist.get(i).getC_Question());
				System.out.println("A:	"+cqlist.get(i).getA_Answer());
				System.out.println("B:	"+cqlist.get(i).getB_Answer());
				System.out.println("C:	"+cqlist.get(i).getC_Answer());
				System.out.println("D:	"+cqlist.get(i).getD_Answer());
				System.out.println("请选择 A-B-C-D");
				String answer = s.nextLine();
				map.put(i, answer);
			}
			for(i=0;i<eqlist.size();i++) {
				System.out.println("*********\t第"+(i+1)+"个简答题\t*************");
				System.out.println(cqlist.get(i).getC_Question());
				System.out.println("请输入您的答案");
				String answer = s.nextLine();
				map.put(i+8, answer);
			}
			java.util.Date date = new java.util.Date();
			
			ExamRecord er = new ExamRecord(id,new Date(date.getTime()),sid,map.get(0),map.get(1),map.get(2)
					,map.get(3),map.get(4),map.get(5),map.get(6),map.get(7),map.get(8)
					,map.get(9),map.get(10));
			toMessage(10023, er);
			fromMessage();
			service();
			break;
			
		case 20041:
			System.out.println("生成试卷失败");
			break;
			
		case 20042:
			System.out.println("查询所有成绩成功，按时间降序");
			List<Result> listGrade = (List<Result>) fromData.get("data");
			for(Result r : listGrade) {
				System.out.println(r.getRecord());
			}
			break;
			
		case 20043:
			System.out.println("查询所有成绩失败");
			break;
			
		case 20044:
			listGrade = (List<Result>) fromData.get("data");
			System.out.println("请输入要导出的文件目录");
			String filePath = new Scanner(System.in).nextLine();
			try {
				PrintStream ps = new PrintStream(filePath,"utf-8");
				for(Result r : listGrade) {
//					System.out.println(r.getRecord());
					ps.println(r.getRecord());
				}
				ps.close();
			} catch (IOException e) {
				// 出错后删除该文件，并打印导出成绩失败信息
				File file = new File(filePath);
				if(file.exists()) {
					file.delete();
				}
				System.out.println("导出所有考试成绩失败");
				e.printStackTrace();
			}
			
			System.out.println("导出所有考试成绩成功");
			
			break;
			
		case 20045:
			System.out.println("导出所有考试成绩失败");
			break;
		case 20046:
			System.out.println("在数据库记录该学生成绩答案成功");
			break;
		case 20047:
			System.out.println("在数据库记录该学生成绩答案失败");
			break;
			
		case 20048:
			System.out.println("查询出该考生的考题纪录成功");
			ExamRecord record = (ExamRecord) fromData.get("data");
			System.out.println(record);
			
			Result result = new Result();
			System.out.println("请输入分数");
			int mark = new Scanner(System.in).nextInt();
			result.setRecord(mark);
			result.setId(record.getId());
			result.setErid(record.getErid());
			toMessage(10025,result);
			fromMessage();
			service();
			break;
		case 20049:
			System.out.println("查询出该考生的考题纪录失败");
			break;
		case 20050:
			System.out.println("评分成功");
			break;
		case 20051:
			System.out.println("评分失败");
			break;
			
		default:
			System.out.println("未知原因出错了。。。");
			break;
		}
	}

	/**
	 * 狗管理的客户端
	 */
	private static void managerClient() {
		System.out.println("******\t欢迎大佬回来\t******");
		while (true) {
			int menu = ViewUtils.managerMenuView();
			switch (menu) {
			case 0:// 退出
				System.exit(0);
				break;
			case 1:// 增加考试学员
				User user = ViewUtils.addUserView();
				toMessage(10003, user);
				break;
			case 2:// 删除考试学员
				String username = ViewUtils.deleteUserView();
				toMessage(10004, username);
				break;
			case 3:// 修改考试学员
				user = ViewUtils.updateUserView_m();
				toMessage(10005, user);
				break;
			case 4:// 查询考试学员
				username = ViewUtils.findUserByName();
				toMessage(10006, username);
				break;
			case 5:// 增加选择题
				ChoiceQuestion cq = ViewUtils.addChoiceQuestion();
				toMessage(10007, cq);
				break;
			case 6:// 增加简答题
				EssayQuestion eq = ViewUtils.addEssayQuestion();
				toMessage(10015, eq);
				break;
			case 7:// 修改选择题
				cq = ViewUtils.updateChoiceQuestion();
				toMessage(10008, cq);
				break;
			case 8:// 修改简答题
				eq = ViewUtils.updateEssayQuestion();
				toMessage(10016, eq);
				break;
			case 9:// 删除选择题
				int cid = ViewUtils.deleteChoiceQuesation();
				toMessage(10009, cid);
				break;
			case 10:// 删除简答题
				int eid = ViewUtils.deleteEssayQuesation();
				toMessage(10017, eid);
				break;
			case 11:// 查询选择题（id）
				cid  = ViewUtils.findChoidceQuestionById();
				toMessage(10010, cid);
				break;
			case 12:// 查询简答题（id）
				eid = ViewUtils.findEssayQuesationById();
				toMessage(10018, eid);
				break;
			case 13:// 批量导入考试试题（选择题）
				List<ChoiceQuestion> choiceQuestionsList = ViewUtils.batchChoiceQuestions();
				toMessage(10013, choiceQuestionsList);
				break;
			case 14:// 批量导入考试试题（简答题）
				List<EssayQuestion> essayQuestionsList = ViewUtils.batchEssayQuestions();
				toMessage(10014, essayQuestionsList);
				break;
			case 15:// 查询所有用户
				toMessage(10012, null);
				break;
			case 16:// 查询所有考题
				toMessage(10011, null);
				break;
			case 17: //随机生成一份试题进数据库
				toMessage(10022,null);
				break;
			case 18:	//根据erid查询考生的考试记录
				System.out.println("请输入考题记录的编号");
				erid = new Scanner(System.in).nextInt();
				toMessage(10024,erid);
				break;
			}
			fromMessage();
			service();
		}

	}

	/**
	 * 从服务器接收消息
	 */
	@SuppressWarnings("unchecked")
	private static void fromMessage() {
		try {
			fromData = (HashMap<String, Object>) ois.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 发送消息个服务器
	 * 
	 * @param type
	 * @param user
	 */
	private static void toMessage(int type, Object data) {
		toData = new HashMap<>();
		toData.put("type", type);
		toData.put("data", data);
		try {
			oos.writeObject(toData);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
