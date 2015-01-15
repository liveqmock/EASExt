 package com.creditease.eas.warn.action;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.creditease.eas.compliance.bean.Complain;
import com.creditease.eas.util.BaseAction;
import com.creditease.eas.util.TongJi;
import com.creditease.eas.util.Utils;
import com.creditease.eas.util.mail.ContractHtmlContent;
import com.creditease.eas.util.mail.MailSenderInfo;
import com.creditease.eas.util.mail.SendMailUtil;
import com.creditease.eas.util.mail.SendMailUtilNew;
import com.creditease.eas.warn.kingdee.query.FuseTubeQuery;
import com.creditease.eas.warn.kingdee.query.QualifyingMatureMergeQuery;
import com.creditease.eas.warn.kingdee.query.QualifyingMatureQuery;
import com.creditease.eas.warn.service.IFuseTubeService;
import com.creditease.eas.warn.service.OrgAdminChService;
import com.creditease.eas.warn.service.RegularService;
@Controller
@Scope("prototype")
public class RegularAction  extends BaseAction{
	private static final Logger logger = Logger.getLogger(RegularAction.class);
	@Autowired
	private RegularService regularService;
	@Autowired
	private OrgAdminChService orgAdminChServiceImpl;
	@Autowired
	private IFuseTubeService fuseTubeService;
	
	//使用假数据模拟信息发送的过程
	public String sendRegularMailTest() throws Exception{
		//给直接上级和间接上级发送信息(这两块的信息是合并在一起发送的)
		//发送部门负责人的信息，需要处理BP的相关的信息 模拟给部门负责人发送邮件
		System.out.println("开始发送给直接上级.........");
		List<String> numlist = orgAdminChServiceImpl.allOrgAdmin();
		System.out.println("部门编码个数："+numlist.size());
		Map<String,Map<String,List<Map<String,Object>>>> mpAllPersonInfos = QualifyingMatureMergeQuery.mergeAllPersonInfo(numlist);
		int count = 0;
		for(Entry<String,Map<String,List<Map<String,Object>>>> mp : mpAllPersonInfos.entrySet()){
			Map<String,List<Map<String,Object>>> mpInfo = mp.getValue();
			if(count < 10){
				regularService.sendMailInfosMergeTest(mpInfo);//处理需要转正的人员的信息
			}
			count++;
	    }
		System.out.println("开始发送给部门负责人.........");
		Map<String,List<Map<String,Object>>> mp = QualifyingMatureQuery.queryResponsePersonInfo(numlist);////查询部门负责人的信息
		System.out.println("mp\t" + mp.size());
		//查询部门负责人的人员的信息
		Map<String,List<Map<String,Object>>> mpImmPersons = QualifyingMatureQuery.queryUnderResponsePersonInfo(numlist);//部门负责人的人员的信息
		if(mp != null && mpImmPersons != null){
			count = 0;
			for(Entry<String,List<Map<String,Object>>> entry : mp.entrySet()){
				System.out.println("key::::" + entry.getKey());
				List<Map<String,Object>> mpPersons = entry.getValue();
				//这里面对应的是收件人的下属
				List<Map<String,Object>> mpImmPerson = mpImmPersons.get(entry.getKey());//根据key取值
//				System.out.println(mpPersons + "\t" + mpImmPersons);
				if(mpPersons != null && (mpPersons.size()>0)&& mpImmPerson != null && count < 10){//如果信息不为空
					//将这些信息对应的邮件整理出来
					regularService.sendResponsePersonMailInfoTest(mpPersons,mpImmPerson);//处理需要转正的人员的信息
				}
				count++;
			}
		}
		System.out.println("ok了");
		logger.info("统计\t" + TongJi.tongJiCount + "\texception\t" + TongJi.tongJiExceptionCount);
		TongJi.initCount();//信息初始化
		return "sendMail_success";
	}
	/***
	 * 测试信管中心的需求 ----转正预警
	 * 
	 * @return
	 * @throws Exception
	 */
	public String sendFuseTubeRegularMailTest() throws Exception{
		FuseTubeQuery fq = new FuseTubeQuery();
		List<Map<String,Object>> list = fq.selectRegularPersonInfos("05XGXSF");
		boolean va = fuseTubeService.sendMailInfosFuseTubeTest(list);
		System.out.println("result\t" + va);
		logger.info("统计\t" + TongJi.tongJiCount + "\texception\t" + TongJi.tongJiExceptionCount);
		TongJi.initCount();//信息初始化
		return "sendMail_success";
	}
	/***
	 * 测试信管中心的需求 ----合同到期
	 * @return
	 * @throws Exception
	 */
	public String sendFuseTubeContractMailTest() throws Exception{
		FuseTubeQuery fq = new FuseTubeQuery();
		List<Map<String,Object>> list = fq.selectContract("05XGXSF");
		boolean va = fuseTubeService.sendMailInfoContractFuseTubeTest(list);
		System.out.println("result\t" + va);
		logger.info("统计\t" + TongJi.tongJiCount + "\texception\t" + TongJi.tongJiExceptionCount);
		TongJi.initCount();//信息初始化
		return "sendMail_success";
	}
	/**
	 * 描述：行政房屋合同测试
	 * 2014-1-20 下午04:26:17 by caoyong
	 * @version
	 * @throws Exception
	 */
	public void recontactMailTest() throws Exception{
		boolean flag = false;//发送邮件成功与否
		try {
			MailSenderInfo ms = new MailSenderInfo();//邮件发送消息bean
			Properties pr = ms.getProperties();
			String[] toAddress = {request.getParameter("toAddress")};//代码正式提交时邮件接收人为接口人邮箱，此行注释掉
//			toAddress = pr.getProperty("toAddress").split(",");//代码正式提交时邮件接收人为接口人邮箱，此行注释掉
//			String fromAddress = pr.getProperty("PORT_USERNAME");//邮件发送人
			String fromAddress = request.getParameter("fromAddress");//邮件发送人
			String password = pr.getProperty("PORT_PASSWORD");
			String logoPath = pr.getProperty("logoPath");//邮件图片logo路径
			String htmlContent = ContractHtmlContent.htmlContentPort("测试地址", Utils.getCnTimeStr("2014-01-20"));//邮件内容
			String[] ccs = null;
			flag = SendMailUtilNew.sendMailToManyPersonAddLogo(fromAddress,password, toAddress, "房屋合同到期提醒", htmlContent, logoPath,ccs);
		} catch (Exception e) {
			e.printStackTrace();	
		}finally{
			this.json = "{\"success\":\""+flag+"\"}";
			this.writerJsonToClient(this.json);
		}
	}
	/**
	 * 描述：财务房屋合同测试
	 * 2014-1-20 下午04:26:17 by caoyong
	 * @version
	 * @throws Exception
	 */
	public void financeMailTest() throws Exception{
		boolean flag = false;//发送邮件成功与否
		try {
			MailSenderInfo ms = new MailSenderInfo();//邮件发送消息bean
			Properties pr = ms.getProperties();
			String[] toAddress = {request.getParameter("toAddress")};//代码正式提交时邮件接收人为接口人邮箱，此行注释掉
//			toAddress = pr.getProperty("toAddress").split(",");//代码正式提交时邮件接收人为接口人邮箱，此行注释掉
//			String fromAddress = pr.getProperty("PORT_USERNAME");//邮件发送人
			String fromAddress = request.getParameter("fromAddress");//邮件发送人
			String password = pr.getProperty("PORT_PASSWORD");
			String logoPath = pr.getProperty("logoPath");//邮件图片logo路径
			String htmlContent = ContractHtmlContent.htmlContentPort("测试地址", Utils.getCnTimeStr("2014-01-20"));//邮件内容
			String[] ccs = null;
			flag = SendMailUtilNew.sendMailToManyPersonAddLogo(fromAddress,password, toAddress, "房屋合同到期提醒", htmlContent, logoPath,ccs);
		} catch (Exception e) {
			e.printStackTrace();	
		}finally{
			this.json = "{\"success\":\""+flag+"\"}";
			this.writerJsonToClient(this.json);
		}
	}
	/**
	 * 描述：文档管理邮件测试
	 * 2014-1-20 下午04:26:17 by caoyong
	 * @version
	 * @throws Exception
	 */
	public void documentMailTest() throws Exception{
		boolean flag = false;//发送邮件成功与否
		try {
			MailSenderInfo ms = new MailSenderInfo();//邮件发送消息bean
			Properties pr = ms.getProperties();
			String[] toAddress = {request.getParameter("toAddress")};//代码正式提交时邮件接收人为接口人邮箱，此行注释掉
//			toAddress = pr.getProperty("toAddress").split(",");//代码正式提交时邮件接收人为接口人邮箱，此行注释掉
//			String fromAddress = pr.getProperty("PORT_USERNAME");//邮件发送人
			String fromAddress = request.getParameter("fromAddress");//邮件发送人
			String password = pr.getProperty("DOC_PASSWORD");
			flag = SendMailUtilNew
			.sendMailToManyPerson(fromAddress,password,toAddress,"合同到期提醒","请注意！ 编号：&nbsp;&nbsp;&nbsp;&nbsp;<<>>&nbsp;&nbsp;&nbsp;&nbsp; 距离到期只有30天，请及时处理！请重视，谢谢！");
		} catch (Exception e) {
			e.printStackTrace();	
		}finally{
			this.json = "{\"success\":\""+flag+"\"}";
			this.writerJsonToClient(this.json);
		}
	}
	/**
	 * 描述：合规邮件测试
	 * 2014-1-20 下午04:26:17 by caoyong
	 * @version
	 * @throws Exception
	 */
	public void complianceMailTest() throws Exception{
		boolean flag = false;//发送邮件成功与否
		try {
			MailSenderInfo ms = new MailSenderInfo();//邮件发送消息bean
			Properties pr = ms.getProperties();
			String[] toAddress = {request.getParameter("toAddress")};//代码正式提交时邮件接收人为接口人邮箱，此行注释掉
//			toAddress = pr.getProperty("toAddress").split(",");//代码正式提交时邮件接收人为接口人邮箱，此行注释掉
//			String fromAddress = pr.getProperty("PORT_USERNAME");//邮件发送人
			String fromAddress = request.getParameter("fromAddress");//邮件发送人
			String logoPath = pr.getProperty("logoPath");//邮件图片logo路径
			String htmlContent = ContractHtmlContent.htmlContent2CaseChargeMan(new Complain(),4,null,null,null);
			String[] ccs = null;
			flag = SendMailUtil.sendComplianceMail(fromAddress, toAddress,"案件进度跟进", htmlContent, logoPath,ccs);
//			flag = SendMailUtil.sendComplianceMail(fromAddress, toAddress, "test", "xxxxxx", pr.getProperty("logoPath"), null);
		} catch (Exception e) {
			e.printStackTrace();	
		}finally{
			this.json = "{\"success\":\""+flag+"\"}";
			this.writerJsonToClient(this.json);
		}
	}
}
