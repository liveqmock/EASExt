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

import com.creditease.eas.util.BaseAction;
import com.creditease.eas.warn.kingdee.query.ContractRenewalQuery;
import com.creditease.eas.warn.kingdee.query.YearOfWorkQuery3;
import com.creditease.eas.warn.service.ContractRenewalService;
import com.creditease.eas.warn.service.ContractService;
import com.creditease.eas.warn.service.OrgAdminChService;
@Controller
@Scope("prototype")
public class ContractAction  extends BaseAction{
	private static final Logger logger = Logger.getLogger(ContractAction.class);
	@Autowired
	private ContractService contractServiceImpl;
	
	@Autowired
	private ContractRenewalService contractRenewalServiceImpl;
	
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
//	public String sendMail() throws Exception{
//		boolean va = contractServiceImpl.queryPersonInfo();
//		System.out.println("合同到期预警发送邮件成功!!!" + va);
//		return "sendMail_success";
//	}
	/**
	 * 
	 * 描述：发送邮件
	 * 2013-1-11 上午10:12:04 by ygq
	 * @version
	 * @return
	 * @throws Exception
	 */
	public String sendContractMailTest() throws Exception{
		
		//发送给对应接口人
		Map<String, List<Map<String,Object>>> datamap = contractRenewalServiceImpl.getSectorInfo();
		int count = 0;
		for(Entry<String,List<Map<String,Object>>> it : datamap.entrySet()){
			System.out.println(it.getKey()+"  size: "+it.getValue().size());
			String email = it.getKey().trim();
			List<Map<String,Object>> listinfo = it.getValue();
			String secname = contractRenewalServiceImpl.getNameBymail(email);
			//以下判断用于有选择的获取测试邮件
			if(count < 10){
				contractRenewalServiceImpl.sendSectorInfo(email,secname,listinfo);
			}
			count++;
		}
		
		Map<String, Set<Map<String,Object>>> deptmap = new HashMap<String, Set<Map<String,Object>>>();
		List<String> numlist = orgAdminChServiceImpl.allOrgAdmin();
		System.out.println("部门编码个数："+numlist.size());
		 List<Map<String,Object>> list = ContractRenewalQuery.getDeptList(numlist);
		 if(list!=null && list.size()>0){
			 for (int i = 0; i < list.size(); i++) {
				 Map<String,Object> tmp = list.get(i);
				 Set<Map<String,Object>> personlist = new HashSet<Map<String,Object>>();
				 personlist.add(tmp);
				 //部门负责人信息总量
				 String positionid = tmp.get("POSITIONID").toString().trim();
				 Map<String,Object> org = YearOfWorkQuery3.getDeptPerson(positionid);
				 if( null!=org){
					 String orgnumber = org.get("DEPTCODE").toString();
//					 System.out.println("name: "+org.get("PNAME").toString()+" : "+orgnumber);
					 if(deptmap.containsKey(orgnumber)){
						 deptmap.get(orgnumber).add(tmp);
					 }else{
						 deptmap.put(orgnumber, personlist);
					 }
				 }
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
					 contractRenewalServiceImpl.sendOrgTest(orgnumber,set);
				 }
				 count++;
		 	}
			 
			 
		System.out.println("合同到期预警发送邮件成功 !" );
//		logger.info("统计\t" + TongJi.tongJiCount + "\texception\t" + TongJi.tongJiExceptionCount);
		//初始化缓存变量
//		TongJi.initCount();
		return "sendMail_success";
	}
}
