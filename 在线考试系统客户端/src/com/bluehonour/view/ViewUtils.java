package com.bluehonour.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.bluehonour.bean.ChoiceQuestion;
import com.bluehonour.bean.EssayQuestion;
import com.bluehonour.bean.User;

public class ViewUtils {

	private static Scanner input = new Scanner(System.in);

	/**
	 * @return 当应用打开时欢迎视图
	 */
	public static User welcomeView() {

		System.out.println("*******************************");
		System.out.println("\t欢迎进入在线考试系统");
		System.out.println("\t请根据提示进行操作");
		System.out.println("\t请输入您的账号");
		String userName = input.nextLine();

		System.out.println("\t请输入您的密码");
		String password = input.nextLine();
		System.out.println("*******************************");

		return new User(userName, password);
	}

	/**
	 * 用于弹出学员菜单
	 * 
	 * @return 学员菜单视图 <br>
	 *         0：退出 <br>
	 *         1：修改密码 <br>
	 *         2：开始考试 <br>
	 *         3：查询历史成绩 <br>
	 *         4：导出所有成绩
	 */
	public static int studentMenuView() {
		int type = -1;
		System.out.println("********************************");
		System.out.println("\t欢迎登陆学员系统");
		System.out.println("\t请根据提示进行操作");
		System.out.println("\t0：退出");
		System.out.println("\t1：修改密码");
		System.out.println("\t2：开始考试");
		System.out.println("\t3：查询历史成绩");
		System.out.println("\t4：导出所有成绩");
		try {
			type = Integer.parseInt(input.nextLine());
		} catch (NumberFormatException e) {

		}
		if (type < 0 || type > 4) {
			System.out.println("------------帅逼，请根据提示啊------------");
			return studentMenuView();
		}
		return type;
	}

	/**
	 * 用于弹出学员菜单
	 * 
	 * @return 返回的是管理员选择的菜单内容: <br>
	 *         0: 退出 <br>
	 *         1: 增加考试学员 <br>
	 *         2: 删除考试学员 <br>
	 *         3: 修改考试学员 <br>
	 *         4: 查询考试学员 <br>
	 *         5: 增加考试试题 <br>
	 *         6: 删除考试试题 <br>
	 *         7: 修改考试试题 <br>
	 *         8: 查询考试试题 <br>
	 *         9: 批量导入考试试题 <br>
	 *         10: 查询所有用户
	 */
	public static int managerMenuView() {
		System.out.println("****************************************************");
		System.out.println("******\t请根据系统提示进行操作:\t******");
		System.out.println("******\t0:	退出 \t\t******");
		System.out.println("******\t1:	增加考试学员  \t******");
		System.out.println("******\t2:	删除考试学员  \t******");
		System.out.println("******\t3:	修改考试学员  \t******");
		System.out.println("******\t4:	查询考试学员  \t******");
		System.out.println("******\t5:	增加选择题 \t\t******");
		System.out.println("******\t6:	增加简答题 \t\t******");
		System.out.println("******\t7:	修改选择题 \t\t******");
		System.out.println("******\t8:	修改简答题 \t\t******");
		System.out.println("******\t9:	删除选择题 \t\t******");
		System.out.println("******\t10:	删除简答题 \t\t******");
		System.out.println("******\t11:	查询选择题（id） \t******");
		System.out.println("******\t12:	 查询简答题（id）\t******");
		System.out.println("******\t13:	 批量导入考试试题（选择题）\t******");
		System.out.println("******\t14:	 批量导入考试试题（简答题）\t******");
		System.out.println("******\t15:	 查询所有用户\t******");
		System.out.println("******\t16:	 查询所有考题\t******");
		System.out.println("******\t17:	 随机生成一份试题进数据库\t******");
		System.out.println("******\t18:	 对学员成绩进行评分\t******");
		// 接收用户选择的菜单内容
		String text = input.nextLine();
		int type = -1;
		try {
			type = Integer.parseInt(text);
		} catch (NumberFormatException e) {
			// 当用户输入的内容, 不是数字, 则出现此异常
		}

		// 如果type依然为-1. 则表示用户可能输入了-1 或 输入了非数字
		if (type < 0 || type > 18) {
			// 不合理操作
			System.out.println("******\t帅逼, 你输入错了\t******");
			return managerMenuView();
		}
		return type;
	}

	/**
	 * 用于学员修改自身密码的操作
	 * 
	 * @param user
	 *            传递的用户对象, 包含了用户的帐号 密码为空 当方法执行完毕后, 旧的密码会存储在user对象中的pssword属性中
	 * @return 用户对象, 包含了新的密码
	 */
	public static User updateUserPassView_s(User user) {
		System.out.println("****************************************************");
		System.out.println("******\t修改密码操作\t******");
		System.out.println("******\t请输入你的原密码:\t******");
		String oldPass = input.nextLine();
		System.out.println("******\t请输入你的新密码:\t******");
		String newPass1 = input.nextLine();
		System.out.println("******\t请确认你的新密码:\t******");
		String newPass2 = input.nextLine();

		if (newPass1 != null && newPass1.equals(newPass2)) {
			// 密码不为空, 且两次输入相同
			user.setPassword(oldPass);
			return new User(null, newPass1);
		} else {
			// 密码为空 或 两次输入 不一致
			System.out.println("两次密码输入不一致, 请重新输入");
			return updateUserPassView_s(user);
		}
	}

	/**
	 * 管理员 添加学员的操作方法
	 * 
	 * @return 要添加的学员对象 , 包含了账号和密码
	 */
	public static User addUserView() {
		System.out.println("****************************************************");
		System.out.println("******\t添加学员操作\t******");
		System.out.println("******\t请输入新的学员帐号\t******");
		String username = input.nextLine();
		System.out.println("******\t请输入新的学员密码\t******");
		String password = input.nextLine();
		return new User(username, password);
	}

	/**
	 * 管理员 删除学员的操作方法
	 * 
	 * @return 要删除的学员帐号
	 */
	public static String deleteUserView() {
		System.out.println("****************************************************");
		System.out.println("******\t删除学员操作\t******");
		System.out.println("******\t请输入要删除的学员帐号\t******");
		String username = input.nextLine();
		return username;
	}

	/**
	 * 管理员 修改学员密码的操作方法
	 * 
	 * @return 用户对象, 包含了用户的帐号 以及 新的密码
	 */
	public static User updateUserView_m() {
		System.out.println("****************************************************");
		System.out.println("******\t修改学员密码操作\t******");
		System.out.println("******\t请输入学员的帐号\t******");
		String username = input.nextLine();
		System.out.println("******\t请输入学员的新密码\t******");
		String password = input.nextLine();
		return new User(username, password);
	}

	/**
	 * 通过帐号, 查询用户的操作方法
	 * 
	 * @return 要查询的学员帐号
	 */
	public static String findUserByName() {
		System.out.println("****************************************************");
		System.out.println("******\t查询学员操作\t******");
		System.out.println("******\t请输入要查询的学员帐号\t******");
		String username = input.nextLine();
		return username;
	}

	/**
	 * 增加选择题 格式：问题 &&&&& 答案 && 答案 a && 答案B && 答案c && 答案d && 分值（默认8）
	 * 
	 * @return
	 */
	public static ChoiceQuestion addChoiceQuestion() {
		System.out.println("***********************");
		System.out.println("*****\t增加选择题");
		System.out.println("输入格式如下：一行啊，中间没空格，别回车，会崩的");
		System.out.println("问题   && 答案（ABCD） && 答案a && 答案b 答案c && 答案d && 分值（建议8，也可以不输默认5，为什么，好凑齐一百分）");
		String line = input.nextLine();
		String[] str = line.split("&&");
		int len = str.length;
		ChoiceQuestion cq = new ChoiceQuestion(str[0], str[1], str[2], str[3], str[4], str[5]);
		if (len == 7)
			cq.setMark(Integer.parseInt(str[len - 1]));
		return cq;
	}

	/**
	 * 增加简答题
	 * 
	 * @return
	 */
	public static EssayQuestion addEssayQuestion() {
		System.out.println("**************************");
		System.out.println("*****\t增加简答题");
		System.out.println("请输入问题");
		String question = input.nextLine();
		System.out.println("请输入分值，建议20，为什么，好凑齐100");
		int mark = Integer.parseInt(input.nextLine());
		EssayQuestion eq = new EssayQuestion(question, mark);
		return eq;
	}

	/**
	 * 修改选择题
	 * 
	 * @return
	 */
	public static ChoiceQuestion updateChoiceQuestion() {
		System.out.println("************************");
		System.out.println("**********\t修改选择题");
		System.out.println("请输入选择题的id号");
		int cid = Integer.parseInt(input.nextLine());
		System.out.println("请输入问题");
		String question = input.nextLine();
		
		System.out.println("请输入答案");
		String RightAnswer = input.nextLine();
		
		System.out.println("请输入A选项");
		String aAnswer = input.nextLine();
		System.out.println("请输入B选项");
		String bAnswer = input.nextLine();
		System.out.println("请输入C选项");
		String cAnswer = input.nextLine();
		System.out.println("请输入D选项");
		String dAnswer = input.nextLine();
		System.out.println("请输入分值");
		int mark = input.nextInt();
		ChoiceQuestion cq = new ChoiceQuestion(cid, question, RightAnswer, aAnswer, bAnswer, cAnswer, dAnswer, mark);
		return cq;
	}

	/**
	 * 修改简答题
	 * 
	 * @return
	 */
	public static EssayQuestion updateEssayQuestion() {
		System.out.println("************************");
		System.out.println("**********\t修改简答题");
		System.out.println("请输入简答题的id号");
		int eid = Integer.parseInt(input.nextLine());
		System.out.println("请输入问题");
		String question = input.nextLine();
		System.out.println("请输入分值");
		int mark = input.nextInt();
		EssayQuestion eq = new EssayQuestion(eid, question, mark);
		return eq;
	}

	/**
	 * 删除选择题，根据编号
	 * 
	 * @return
	 */
	public static int deleteChoiceQuesation() {
		System.out.println("**************************");
		System.out.println("********\t删除选择题");
		System.out.println("请输入选择题的编号");
		int cid = input.nextInt();
		return cid;
	}

	/**
	 * 删除简答题，根据题号
	 * 
	 * @return
	 */
	public static int deleteEssayQuesation() {
		System.out.println("**************************");
		System.out.println("********\t删除简答题");
		System.out.println("请输入简答题的编号");
		int eid = input.nextInt();
		return eid;
	}

	/**
	 * 根据id查询选择题
	 * 
	 * @return
	 */
	public static int findChoidceQuestionById() {
		System.out.println("***************************");
		System.out.println("*********\t根据id查询选择题");
		System.out.println("请输入题号");
		int cid = input.nextInt();
		return cid;
	}

	/**
	 * 根据id查询简答题
	 * 
	 * @return
	 */
	public static int findEssayQuesationById() {
		System.out.println("**************************");
		System.out.println("********\t根据id查询简答题");
		System.out.println("请输入简答题的编号");
		int eid = input.nextInt();
		return eid;
	}

	/**
	 * 批量导入选择题
	 */
	@SuppressWarnings("null")
	public static List<ChoiceQuestion> batchChoiceQuestions() {
		System.out.println("***********************");
		System.out.println("*******\t请输入要导入文件的具体路径");
		String filePath = input.nextLine();
		List<ChoiceQuestion> listCq = new ArrayList<>();
		try {
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
				String line = null;
				while((line=br.readLine())!=null) {
//					System.out.println(line);
					String[] params = line.split("&&");
					String cQuestion = params[0];
					String rightAnswer = params[1];
					String aAnswer = params[2];
					String bAnswer = params[3];;
					String cAnswer = params[4];
					String dAnswer = params[5];
					Integer mark = Integer.parseInt(params[6]);
					ChoiceQuestion cq = new ChoiceQuestion(cQuestion, rightAnswer, aAnswer, bAnswer, cAnswer, 
							dAnswer,mark);
//					System.out.println(cq);
					listCq.add(cq);
				}
			} else {
				System.out.println("文件路径错误或者文件不存在");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listCq;
	}

	/**
	 * 批量导入简答题
	 */
	@SuppressWarnings("null")
	public static List<EssayQuestion> batchEssayQuestions() {
		System.out.println("***********************");
		System.out.println("*******\t请输入要导入文件的具体路径");
		String filePath = input.nextLine();
		List<EssayQuestion> listEq = new ArrayList<>();
		try {
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
				String line = null;
				while((line=br.readLine())!=null) {
					String[] params = line.split("&&");
					String question = params[0];
					Integer mark = Integer.parseInt(params[1]);
					EssayQuestion eq = new EssayQuestion(question,mark);
					listEq.add(eq);
				}
			} else {
				System.out.println("文件路径错误或者文件不存在");
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listEq;
	}

}
