package com.creditease.eas.common.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditease.eas.common.bean.Email;
import com.creditease.eas.common.dao.impl.SendMailDaoImpl;
import com.creditease.eas.common.service.SendMailService;
import com.creditease.eas.common.vo.EmailInfo;
import com.creditease.eas.common.vo.MailInfo;
/**
 * 邮件换服务实现类
 * @author lining
 * @since 2014-6-1
 */
@Service("sendMailServiceImpl")
public class SendMailServiceImpl implements SendMailService{

	@Resource
	private SendMailDaoImpl sendMailDaoImpl;

	@Override
	public String createMail(List<EmailInfo> emailInfos) throws Exception {
		String EmailInfoId = null;
		if(null != emailInfos && 0 < emailInfos.size()){
			for(EmailInfo emailInfo:emailInfos){
				//逐条出入邮件记录并返回相应记录主键
				this.sendMailDaoImpl.insertMailForSend(emailInfo);
				EmailInfoId = emailInfo.getFid();
				System.out.println(emailInfo.getFid());
				List<Map<String,Object>> attachments = new ArrayList<Map<String,Object>>();
				//整合邮件内容中图片文件路径信息
				String imageList = emailInfo.getImages();
				if(null != imageList && !"".equals(imageList)){
					String[] images = imageList.split(";");
					for(String image:images){
						Map<String,Object> attachment = new HashMap<String,Object>();
						attachment.put("emailInfo", EmailInfoId);
						attachment.put("contentType", "i");
						attachment.put("content", image);
						attachment.put("FID", null);
						attachments.add(attachment);
					}
				}
				//整合邮件内附件文件路径信息
				String fileList = emailInfo.getFiles();
				if(null != fileList && !"".equals(fileList)){
					String[] files = fileList.split(";");
					for(String file:files){
						Map<String,Object> attachment = new HashMap<String,Object>();
						attachment.put("emailInfo", EmailInfoId);
						attachment.put("contentType", "f");
						attachment.put("content", file);
						attachment.put("FID", null);
						attachments.add(attachment);
					}
				}
				//批量存储邮件附件信息（包括邮件内容中的图片和附件中的文件）
				if(0 < attachments.size()){
					int temp = this.sendMailDaoImpl.insertAttachmentForSend(attachments);
					System.out.println(temp);
				}
			}
		}
		return EmailInfoId;
	}

	@Override
	public Map<String, Object> doSendMail() throws Exception {
		//获取MailInfo实例集合
		List<MailInfo> mailInfos = sendMailDaoImpl.selectMailForWillSend();
		Map<String,Object> sendReport = new HashMap<String,Object>();
		sendReport.put("sendReport:", "未获得邮件发送的相关信息！");
		if(null != mailInfos && 0 < mailInfos.size()){
			//调用Email的邮件发送方法发送邮件
			sendReport = new Email(mailInfos).sendAsOne();
			//获取邮件发送状态，修改预警信息状态
			int flag = sendMailDaoImpl.updateMailAfterSended(sendReport);
			System.out.println(flag);
		}
		return sendReport;
	}

	@Override
	public List<EmailInfo> findMail(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
