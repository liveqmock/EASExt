/**
 * 
 */
package com.creditease.eas.warn.service.impl;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditease.eas.util.FileReadUtil;
import com.creditease.eas.util.StringUtil;
import com.creditease.eas.util.TongJi;
import com.creditease.eas.util.mail.ContractHtmlContent;
import com.creditease.eas.util.mail.HtmlContentCommonFuseTubeServiceMethod;
import com.creditease.eas.util.mail.HtmlContentCommonMethod;
import com.creditease.eas.util.mail.MailSenderInfo;
import com.creditease.eas.util.mail.SendMailUtil;
import com.creditease.eas.warn.bean.BlackListInfo;
import com.creditease.eas.warn.bean.WaringDetail;
import com.creditease.eas.warn.dao.WaringDetailMapper;
import com.creditease.eas.warn.kingdee.query.OrgAdminMapperQuery;
import com.creditease.eas.warn.service.BlackListInfoService;
import com.creditease.eas.warn.service.IFuseTubeService;
import com.creditease.eas.warn.service.OrgAdminChService;
import com.creditease.eas.warn.service.RegularService;
/**
 * @VariablesServiceImpl.java
 * created at 2012-12-26 下午04:01:37 by xjw
 * @author xjw({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
@Service
public class FuseTubeServiceImpl implements IFuseTubeService{
	private static final Logger logger = Logger.getLogger(FuseTubeServiceImpl.class);
	@Autowired
	private WaringDetailMapper waringdetailMapper;
	@Override
	public boolean sendMailInfosFuseTubeTest(List<Map<String, Object>> mpPerson){
		boolean va = false;
		try{
			 //现在使用新版本里面的方法
			 String htmlContent = HtmlContentCommonFuseTubeServiceMethod.htmlContent(mpPerson);
			 String reletivePath = FileReadUtil.writeFileImitate(htmlContent,"regular","重要！！员工试用期转正提醒通知");//相对路径，保存日志记录的时候用
			 String title = "员工试用期转正提醒通知(此邮件为系统发送，请勿回复)";
			 MailSenderInfo ms = new MailSenderInfo();
			 Properties pr = ms.getProperties();
			 String logoPath = pr.getProperty("logoPath");
			 String fromAddress = pr.getProperty("fromAddress");//发件人
			 //测试用代码
//			 String emails = "gaoquanyang@creditease.cn";//假的收件人
//			 String[] toAddress = {emails};////收件人
//			 va = SendMailUtil.sendMailToManyPersonAddLogo(fromAddress,toAddress , title, htmlContent, logoPath);//添加抄送人
			 //上线用代码
			 String[] toAddress = {"fanrongliu@creditease.cn","lichen@creditease.cn"};////收件人
 			 String[] ccs = {"honglangpeng@creditease.cn"};//
			 va = SendMailUtil.sendMailToManyPersonAddLogo(fromAddress,toAddress , title, htmlContent, logoPath,ccs);//添加抄送人
			 //收件人
			 WaringDetail waringDetail = new WaringDetail();
			 waringDetail.setTypeid(2);//预警类别
			 waringDetail.setWayid(2);//发送方式
			 waringDetail.setSendtime(new Date());//发送时间
			 waringDetail.setReceiver("刘凡榕;陈丽");//接收人：张三
			 waringDetail.setEmail("fanrongliu@creditease.cn;lichen@creditease.cn");//收件人邮箱
			 waringDetail.setCopyperson("彭红浪");//抄送人姓名
			 waringDetail.setExt2("honglangpeng@creditease.cn");//抄送人邮箱
			 waringDetail.setTheme(title);//
			 waringDetail.setContentaddress(reletivePath);//文件路径，
			 waringDetail.setFilename("转正人员名单.txt");//文件名称：先写死
			 if(va){
				waringDetail.setIssend(1);//成功了
				waringDetail.setExt1("成功");
			 }else{
				waringDetail.setIssend(0);//失败了
				waringDetail.setExt1("失败");
			 }
			 waringDetail.setSendcount(1);//默认是一次
			 waringDetail.setCreatime(new Date());//创建日期
			 /*****************************************短信内容:*****************/
			 waringdetailMapper.insert(waringDetail);
		}catch(Exception ex){
			ex.printStackTrace();
			TongJi.tongJiExceptionCount ++;
			logger.error(ex.getMessage());
		}
		return va;
	}
	@Override
	public boolean sendMailInfoContractFuseTubeTest(
			List<Map<String, Object>> mpPerson) {
		 String title = "合同到期提醒通知(此邮件为系统发送，请勿回复)";
		 MailSenderInfo ms = new MailSenderInfo();
		 Properties pr = ms.getProperties();
		 String logoPath = pr.getProperty("logoPath");
		 String fromAddress = pr.getProperty("fromAddress");
		 String htmlContentForContract = ContractHtmlContent.htmlContent(mpPerson);
		 String reletivePath = null;
		try {
			reletivePath = FileReadUtil.writeFile("shuoliu",htmlContentForContract,"合同预警","合同即将到期人员名单");
		} catch (Exception e) {
			TongJi.tongJiExceptionCount ++;
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	     //测试用代码
//	 	 String emails = "gaoquanyang@creditease.cn";//假的收件人
//		 String[] toAddress = {emails};////收件人
//		 boolean va = SendMailUtil.sendMailToManyPersonAddLogo(fromAddress, toAddress, title, htmlContentForContract,logoPath);//测试邮箱
		 //上线用代码
		 String[] toAddress = {"fanrongliu@creditease.cn","lichen@creditease.cn"};////收件人
		 String[] ccs = {"honglangpeng@creditease.cn"};//
		 boolean va = SendMailUtil.sendMailToManyPersonAddLogo(fromAddress,toAddress , title, htmlContentForContract, logoPath,ccs);//添加抄送人
		 WaringDetail waringDetail = new WaringDetail();
		 waringDetail.setTypeid(4);//合同到期预警
		 waringDetail.setWayid(2);//发送方式:发送邮件
		 waringDetail.setSendtime(new Timestamp(System.currentTimeMillis()));//发送时间:需要注意下
		 waringDetail.setTheme(title);//
		 waringDetail.setContentaddress(reletivePath);//文件路径，
		 waringDetail.setFilename("合同到期人员名单.txt");//文件名称：先写死
		 waringDetail.setReceiver("刘凡榕;陈丽");//接收人：张三
		 waringDetail.setEmail("fanrongliu@creditease.cn;lichen@creditease.cn");//收件人邮箱
		 waringDetail.setCopyperson("彭红浪");//抄送人姓名
		 waringDetail.setExt2("honglangpeng@creditease.cn");//抄送人邮箱
		 if(va){
			 waringDetail.setIssend(1);//成功了
			 waringDetail.setExt1("成功");
		 }else{
			 waringDetail.setIssend(0);//失败了
			 waringDetail.setExt1("失败");
		 }
		 waringDetail.setSendcount(1);//默认是一次
		 waringDetail.setCreatime(new Date());//创建日期
		 waringdetailMapper.insert(waringDetail);	
		 return va;
	}
}
