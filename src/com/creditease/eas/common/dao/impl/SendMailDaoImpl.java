package com.creditease.eas.common.dao.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.creditease.eas.common.dao.SendMailDao;
import com.creditease.eas.common.vo.EmailInfo;
import com.creditease.eas.common.vo.MailInfo;
/**
 * 邮件发送数据访问对象实现类
 * @author lining
 * @since 2014-6-7
 */
@Repository("sendMailDaoImpl")
public class SendMailDaoImpl {

	@Resource
	private SendMailDao sendMailDao;
	/**
	 * 插入邮件记录关联的附件
	 * @author lining
	 * @since 2014-6-9
	 * @param attachments
	 * @return 成功插入记录条数
	 */
	public int insertAttachmentForSend(List<Map<String,Object>> attachments) {
		return this.sendMailDao.insertAttachmentForSend(attachments);
	}

	/**
	 * 插入邮件记录
	 * @author lining
	 * @since 2014-6-9
	 * @param emailInfos
	 * @return 成功插入记录条数
	 * @throws Exception
	 */
	public int insertMailForSend(EmailInfo emailInfos) throws Exception {
		return this.sendMailDao.insertMailForSend(emailInfos);
	}

	/**
	 * 查询待发送的邮件
	 * @author lining
	 * @since 2014-6-9
	 * @return
	 * @throws Exception
	 */
	public List<MailInfo> selectMailForWillSend() throws Exception {
		//获取当前一小时内要发送的预警邮件信息
		List<Map<String,Object>> warnMailInfos = sendMailDao.selectMailForWillSend();
		//整合封装邮件信息集合
		List<MailInfo> mailInfos = new ArrayList<MailInfo>();
		if(null != warnMailInfos && 0 < warnMailInfos.size()){
			for(Map<String,Object> warnMailInfo:warnMailInfos){
//				MailInfo mailInfo = (MailInfo)BeanUtil.dataToBean(warnMailInfo, "com.creditease.eas.common.vo.MailInfo");暂时封装工具类不可用
				MailInfo mailInfo = new MailInfo();
				String infoId = warnMailInfo.get("INFOID").toString();
				mailInfo.setInfoId((null == infoId)?"":infoId);
				mailInfo.setFrom((null == warnMailInfo.get("SENDER"))?"":warnMailInfo.get("SENDER").toString());
				mailInfo.setPassword((null == warnMailInfo.get("PWD"))?"":warnMailInfo.get("PWD").toString());
				mailInfo.setTos((null == warnMailInfo.get("TOS"))?null: warnMailInfo.get("TOS").toString().split(";"));
				mailInfo.setCc((null == warnMailInfo.get("CC"))?null: warnMailInfo.get("CC").toString().split(";"));
				mailInfo.setTheme((null == warnMailInfo.get("THEME"))?"":warnMailInfo.get("THEME").toString());
				StringBuffer bf = new StringBuffer();
				File file = new File((null == warnMailInfo.get("CONTENT"))?"":warnMailInfo.get("CONTENT").toString());
				//根据邮件内容路径获取获取邮件内容字符串
				if(file.exists()){
					FileInputStream fi = null;
					InputStreamReader isr = null;
					BufferedReader br = null;
					try {
						fi = new FileInputStream(file);
						isr = new InputStreamReader(fi,"UTF-8");
						br = new BufferedReader(isr);
						String str = "test";
						while ((str = br.readLine()) != null) {
							bf.append(str);
						}
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}finally{
						if(null != br){
							try {
								br.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						if(null != isr){
							try {
								isr.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						if(null != fi){
							try {
								fi.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
				mailInfo.setContent(bf.toString());
				//根据邮件记录id获取邮件记录对应的附件
				List<Map<String,Object>> attachments = sendMailDao.selectAttachmentByInfoId(infoId);
				if(null != attachments && 0 < attachments.size()){
					List<String> images = new ArrayList<String>(); 
					List<String> files = new ArrayList<String>(); 
					for(Map<String,Object> attachment:attachments){
						if("i".equals(attachment.get("CONTENTTYPE").toString())){
							//添加图片
							images.add(attachment.get("CONTENT").toString());
						}else if("f".equals(attachment.get("CONTENTTYPE").toString())){
							//添加附件
							files.add(attachment.get("CONTENT").toString());
						}
					}
					if(0 < images.size()){
						mailInfo.setImages(images.toArray(new String[images.size()]));
					}
					if(0 < files.size()){
						mailInfo.setFiles(files.toArray(new String[files.size()]));
					}
				}
				mailInfos.add(mailInfo);
			}
		}
		return mailInfos;
	}

	/**
	 * 更新邮件发送后对应邮件记录数据
	 * @author lining
	 * @since 2014-6-9
	 * @param sendReport
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public int updateMailAfterSended(Map<String, Object> sendReport)
			throws Exception {
		//从邮件信息集合中提取邮件发送信息
		Map<String,Object> totaleReport = new HashMap<String,Object>();
		for(String user:sendReport.keySet()){
			Map<String,Object> mailMessage = (Map<String, Object>) sendReport.get(user);
			List<Map<String,Object>> mails = (List<Map<String, Object>>) mailMessage.get("mails");
			for(Map<String,Object> mail:mails){
				String mailInfoId = (String) mail.get("mailInfoId");
				Integer state = (Integer) mail.get("state");
				if(1 == state){
					if(!totaleReport.containsKey(mailInfoId)){
						totaleReport.put(mailInfoId, state);
					}
				}else if(0 == state){
					totaleReport.put(mailInfoId, state);
				}
			}
		}
		//每1000条数据整合为一个邮件状态批量更新集合，并调用更新邮件记录信息方法，不足1000条则最后去不更新集合中数据
		int flag = 0;
		if(0 < totaleReport.size()){
			Map<String,Object> successReports = new HashMap<String,Object>();
			successReports.put("infoState", "sendsuccess");
			Map<String,Object> failReports = new HashMap<String,Object>();
			failReports.put("infoState", "sendfalse");
			List<Object> successList = new ArrayList<Object>();
			List<Object> failList = new ArrayList<Object>();
			Iterator it = totaleReport.keySet().iterator();
			for(String key ;it.hasNext();){
				key = (String) it.next();
				Integer value = (Integer) totaleReport.get(key);
				if(0 == value){
					if(1000 <= failList.size()){
						failReports.put("stateList", failList);
						flag += sendMailDao.updateMailAfterSended(failReports);
						failList.clear();
						failList.add(key);
					}else{
						failList.add(key);
					}
				}else if(1 == value){
					if(1000 <= successList.size()){
						successReports.put("stateList", successList);
						flag += sendMailDao.updateMailAfterSended(successReports);
						successList.clear();
						successList.add(key);
					}else{
						successList.add(key);
					}
				}
			}
			if(null != failList && 0 < failList.size()){
				failReports.put("stateList", failList);
				flag += sendMailDao.updateMailAfterSended(failReports);
			}
			if(null != successList && 0 < successList.size()){
				successReports.put("stateList", successList);
				flag += sendMailDao.updateMailAfterSended(successReports);
			}
		}
		//更新成功条数
		return flag;
	}

}
