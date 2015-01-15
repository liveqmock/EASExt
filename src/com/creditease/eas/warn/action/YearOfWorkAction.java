package com.creditease.eas.warn.action;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.creditease.eas.util.TongJi;
import com.creditease.eas.util.Utils;
import com.creditease.eas.warn.kingdee.query.YearOfWorkQuery3;
import com.creditease.eas.warn.service.OrgAdminChService;
import com.creditease.eas.warn.service.YearOfWorkService;
import com.creditease.eas.warn.vo.PersonData2;

@Controller
@Scope("prototype")
public class YearOfWorkAction  {
	private static final Logger logger = Logger.getLogger(YearOfWorkAction.class);
	@Autowired
	private YearOfWorkService yearOfWorkServiceImpl;
	
	@Autowired
	private OrgAdminChService orgAdminChServiceImpl;
	/**
	 * 
	 * 描述：测试用
	 * 2013-1-3 上午11:18:45 by ygq
	 * @version
	 * @return
	 * @throws Exception
	 */
	public String sendMail() throws Exception{
		boolean va = yearOfWorkServiceImpl.queryPersonInfo();
		System.out.println("va" + va);
		return "sendMail_success";
	}
	
	/**
	 * 
	 * 描述：发送司龄邮件
	 * 2013-1-16 上午09:33:00 by xjw
	 * @version
	 * @return
	 * @throws Exception
	 */
	public String sendYearOfWorkMailTest() throws Exception{
		System.out.println("开始时间: "+Utils.getTimeStr());
		List<String> numlist = orgAdminChServiceImpl.allOrgAdmin();
		System.out.println("部门编码个数："+numlist.size());
		Map<String, Set<PersonData2>> map = YearOfWorkQuery3.getAllByYear2(numlist);
		 //发送给直接上级、隔级上级
		 Iterator it = map.entrySet().iterator();
		int count = 0;
		 while (it.hasNext() && count < 10) {
			 Entry entry = (Entry) it.next();
	           String pnumber = entry.getKey().toString();
	         Set<PersonData2> list = (Set<PersonData2>) entry.getValue();
			 yearOfWorkServiceImpl.sendHigherTest(pnumber,list);
			 count++;
		 }
		 
		 Map<String, Set<Map<String,Object>>> deptmap = new HashMap<String, Set<Map<String,Object>>>();
//		
		 List<Map<String,Object>> list = YearOfWorkQuery3.getYearPerson(numlist);
		 if(list!=null && list.size()>0){
			 count = 0;
			 for (int i = 0; i < list.size(); i++) {
				 Map<String,Object> tmp = list.get(i);
				//发送给本人
				 if(10 > count){
					 yearOfWorkServiceImpl.sendPerson(tmp); 
				 }
				 count++;
				 Set<Map<String,Object>> personlist = new HashSet<Map<String,Object>>();
				 personlist.add(tmp);
				 //部门负责人信息总量
				 String positionid = tmp.get("POSITIONID").toString().trim();
				 Map<String,Object> org = YearOfWorkQuery3.getDeptPerson(positionid);//
				 if(null==org){
					 continue;
				 }
//				 String orgNumber = org.get("PERSONNUMBER").toString();
				 String orgnumber = org.get("DEPTCODE").toString();
				 if(deptmap.containsKey(orgnumber)){
					 deptmap.get(orgnumber).add(tmp);
				 }else{
					 deptmap.put(orgnumber, personlist);
				 }
			 }
			 if(!deptmap.isEmpty()){
				 //发送给部门负责人
				 Iterator iter = deptmap.entrySet().iterator();
				 count = 0;
				 while (iter.hasNext() && count < 10) {
					 Entry entry = (Entry) iter.next();
					 String orgnumber = entry.getKey().toString();
					 Set<Map<String,Object>> set = (Set<Map<String,Object>>) entry.getValue();
					 yearOfWorkServiceImpl.sendOrgTest(orgnumber,set);
					 count++;
				 }
			 }
			 
			 System.out.println("结束时间: "+Utils.getTimeStr());
		 }
		 	System.out.println("司龄祝福发送邮件成功!!!" );
			System.out.println("统计\t" + TongJi.tongJiCount + "\texception\t" + TongJi.tongJiExceptionCount);
			logger.info("统计\t" + TongJi.tongJiCount + "\texception\t" + TongJi.tongJiExceptionCount);
//			初始化缓存变量
			TongJi.initCount();
		 return "sendMail_success";
	}
    
}
