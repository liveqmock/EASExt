package com.creditease.eas.warn.action;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.creditease.eas.util.TongJi;
import com.creditease.eas.warn.kingdee.query.BirthdayQuery;
import com.creditease.eas.warn.kingdee.query.QualifyingMatureQuery;
import com.creditease.eas.warn.kingdee.query.WaringDetailQuery;
import com.creditease.eas.warn.service.BirthdayService;
import com.creditease.eas.warn.service.OrgAdminChService;
/**
 * 用于测试发送生日预警的Action
 * @author lining
 * @since 2014-5-28
 */
@Controller
@Scope("prototype")
public class BirthdayAction  {
	private static final Logger logger = Logger.getLogger(YearOfWorkAction.class);
	@Autowired
	private BirthdayService birthdayServiceImpl;
	
	@Autowired
	private OrgAdminChService orgAdminChServiceImpl;
	/**
	 * 发送生日测试邮件
	 * @author lining
	 * @since 2014-5-28
	 * @return
	 * @throws Exception
	 */
	public String sendBirthdayEmailTest() throws Exception{
		//向个人发送生日祝福
		cellSendImage();
		//获取所有部门编号
		List<String> numlist = orgAdminChServiceImpl.allOrgAdmin();
		System.out.println("部门编码个数："+numlist.size());
		//获取各部门
		Map<String,Map<String,List<Map<String,Object>>>> mpAllPersonInfos = BirthdayQuery.mergeAllPersonInfo(numlist);
		int count = 0;
		for(Entry<String,Map<String,List<Map<String,Object>>>> mp : mpAllPersonInfos.entrySet()){
			Map<String,List<Map<String,Object>>> mpInfo = mp.getValue();
			//测试，先将发送邮件的代码注释掉
			if(10 < count){
				birthdayServiceImpl.sendHigherMailInfoTest(mpInfo);
			}
			count++;
	    }
		//发送部门负责人的信息，需要处理BP的相关的信息 模拟给部门负责人发送邮件
		Map<String,List<Map<String,Object>>> mp = QualifyingMatureQuery.queryResponsePersonInfo(numlist);
		//查询部门负责人对应的过生日的员工信息
		Map<String,List<Map<String,Object>>> mpImmPersons = BirthdayQuery.queryUnderResponsePersonInfo(numlist);
		if(mp != null && mpImmPersons != null){
			count = 0;
			for(Entry<String,List<Map<String,Object>>> entry : mp.entrySet()){
				System.out.println("key::::" + entry.getKey());
				List<Map<String,Object>> mpPersons = entry.getValue();
				//这里面对应的是收件人的下属
				List<Map<String,Object>> mpImmPerson = mpImmPersons.get(entry.getKey());
				System.out.println(mpPersons + "\t" + mpImmPersons);
				//测试，先将发送邮件的代码注释掉
				if(mpPersons != null && 0 < mpPersons.size() && mpImmPerson != null && 0 < mpImmPerson.size()){
					//将这些信息对应的邮件整理出来
					if(10 < count){
						birthdayServiceImpl.sendResponsePersonMailInfoTest(mpPersons,mpImmPerson);
					}
				}
				count++;
			}
		}
		 	System.out.println("生日预警发送邮件成功!!!" );
			System.out.println("统计\t" + TongJi.tongJiCount + "\texception\t" + TongJi.tongJiExceptionCount);
			logger.info("统计\t" + TongJi.tongJiCount + "\texception\t" + TongJi.tongJiExceptionCount);
//			初始化缓存变量
			TongJi.initCount();
		 return "sendMail_success";
	}
	
	/**
	 * 向个人发送生日祝福邮件
	 * @author lining
	 * @since 2014-5-28
	 * @return
	 * @throws Exception
	 */
	private void cellSendImage() throws Exception{
		
		List<String> numlist = orgAdminChServiceImpl.allOrgAdmin();
		System.out.println("部门编码个数："+numlist.size());
		List<Map<String,Object>> list = WaringDetailQuery.sendImage(numlist);
//		List<Map<String,Object>> list = WaringDetailQuery.sendImage();
		if(list!=null && list.size()>0){
			int count = 0;
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map= list.get(i);
				if(count < 10){
					birthdayServiceImpl.sendImagesTest(map);
				}
				count++;
			}
		}
		System.out.println("生日祝福发送邮件成功!!!" );
		logger.info("统计\t" + TongJi.tongJiCount + "\texception\t" + TongJi.tongJiExceptionCount);
		//初始化缓存变量
		TongJi.initCount();
	}
}
