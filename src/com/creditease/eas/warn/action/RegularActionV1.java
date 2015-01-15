 package com.creditease.eas.warn.action;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.creditease.eas.util.BaseAction;
import com.creditease.eas.warn.service.RegularService;
/**
 * 这个是需求变更之前的最终版本
 * @RegularActionV1.java
 * created at 2013-3-20 下午01:49:57 by ygq
 * 
 * @author ygq({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
@Controller
@Scope("prototype")
public class RegularActionV1  extends BaseAction{
	private static final Logger logger = Logger.getLogger(RegularActionV1.class);
	@Autowired
	private RegularService regularService;
	/**
	 * 
	 * 描述：转正预警发送邮件功能
	 * 2013-1-3 上午11:18:45 by ygq
	 * @version
	 * @return
	 * @throws Exception
	 */
//	public String sendMail() throws Exception{
//		boolean va = regularService.queryPersonInfo();
//		System.out.println("va" + va);
//		return "sendMail_success";
//	}
//	/**
//	 * 
//	 * 描述：发送邮件：版本1
//	 * 2013-1-11 上午10:12:04 by ygq
//	 * @version
//	 * @return
//	 * @throws Exception
//	 */
//	public String sendRegularMailTestV1() throws Exception{
//		//Map<String,List<Map<String,Object>>> map =  QualifyingMatureQuery001.queryQualifyPersonsInfo();//查询出来的是人员的信息
//		for(Entry<String,List<Map<String,Object>>> entry : map.entrySet()){
//			System.out.println(entry);
//			//regularService.sendMailInfosTest(entry);//处理需要转正的人员的信息
//		}
//		System.out.println("转正预警发送邮件成功!!!" );
//		logger.info("统计\t" + TongJi.tongJiCount + "\texception\t" + TongJi.tongJiExceptionCount);
//		//初始化缓存变量
//		TongJi.initCount();
//		return "sendMail_success";
//	}
	
//	public void test1(){
//		//初始化缓存变量
//		TongJi.initCount();
//		List<Map<String,Object>> list = QualifyingMatureQuery001.personInfoTest();
//		Map<String,List<Map<String,Object>>> map = new HashMap<String,List<Map<String,Object>>>();
//		map.put("bAKwEzgESvScT4yOzQemIHSuYS4=", list);
//		for(Entry<String,List<Map<String,Object>>> entry : map.entrySet()){
//			regularService.sendMailInfosTest(entry);//处理需要转正的人员的信息
//			System.out.println(entry);
//			break;
//		}
//	}
	/**
	 * 
	 * 描述：发送邮件
	 * 2013-1-11 上午10:12:04 by ygq
	 * @version
	 * @return
	 * @throws Exception
	 */
	public String sendRegularMailTest() throws Exception{
//		Map<String,List<Map<String,Object>>> map =  QualifyingMatureQuery.queryQualifyPersonsInfo();//查询出来的是人员的信息
//		//经过验证，break能够起到跳出循环的作用;
//		for(Entry<String,List<Map<String,Object>>> entry : map.entrySet()){
//			regularService.sendMailInfosTest(entry);//处理需要转正的人员的信息
//			System.out.println(entry);
////			break;
//		}
//		System.out.println("转正预警发送邮件成功!!!");
//		logger.info("统计\t" + TongJi.tongJiCount + "\texception\t" + TongJi.tongJiExceptionCount);
//		List<Map<String,Object>> list = QualifyingMatureQuery.personInfoTest();
//		Map<String,List<Map<String,Object>>> map = new HashMap<String,List<Map<String,Object>>>();
////		map.put("bAKwEzgESvScT4yOzQemIHSuYS4=", list);
//		map.put("XkEMi5pDSlS7Phqo4RplvXSuYS4=", list);
//		for(Entry<String,List<Map<String,Object>>> entry : map.entrySet()){
////			regularService.sendMailInfosTest(entry);//处理需要转正的人员的信息
//			System.out.println(entry);
//		}
		return "sendMail_success";
	}
	/**
	 * 
	 * 描述：邮件发送给一个人
	 * 2013-2-4 下午08:22:04 by ygq
	 * @version
	 * @return
	 * @throws Exception
	 */
//	public String sendOneMailTest() throws Exception{
//		String fromAddress = "HR@creditease.cn";
//		boolean va = false;
//		 // 邮件内容
//		try{
//			String htmlContent = SendMailTemplate.info();
//			MailSenderInfo ms = new MailSenderInfo();
//			Properties pr = ms.getProperties();
//			String[] toAddress = pr.getProperty("mailInfos").split(",");
//			String logoPath = pr.getProperty("logoPath");
//			va = SendMailUtil.sendMailToManyPersonAddLogo(fromAddress, toAddress, "转正预警测试", htmlContent,logoPath);
//		}catch(Exception e){
//			e.printStackTrace();
//			logger.info(e.getMessage());
//		}
//		if(va){
//		System.out.println("发送成功！");
//		}else{
//			System.out.println("发送失败！");
//		}
//		return "sendMail_success";
//	}
}
