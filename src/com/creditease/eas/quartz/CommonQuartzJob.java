package com.creditease.eas.quartz;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.creditease.eas.util.TongJi;
import com.creditease.eas.warn.bean.ConfigInfo;
import com.creditease.eas.warn.bean.Jumbosmsv;
import com.creditease.eas.warn.dao.JumbosmsvMapper;
import com.creditease.eas.warn.kingdee.query.BirthdayQuery;
import com.creditease.eas.warn.kingdee.query.ContractRenewalQuery;
import com.creditease.eas.warn.kingdee.query.QualifyingMatureMergeQuery;
import com.creditease.eas.warn.kingdee.query.QualifyingMatureQuery;
import com.creditease.eas.warn.kingdee.query.WaringDetailQuery;
import com.creditease.eas.warn.kingdee.query.YearOfWorkQuery3;
import com.creditease.eas.warn.service.BirthdayService;
import com.creditease.eas.warn.service.ConfigService;
import com.creditease.eas.warn.service.ContractRenewalService;
import com.creditease.eas.warn.service.ContractService;
import com.creditease.eas.warn.service.IFuseTubeService;
import com.creditease.eas.warn.service.OrgAdminChService;
import com.creditease.eas.warn.service.RegularService;
import com.creditease.eas.warn.service.WaringdetailService;
import com.creditease.eas.warn.service.YearOfWorkService;
import com.creditease.eas.warn.vo.PersonData2;
import com.creditease.eas.warn.vo.QueryData;
import com.creditease.service.ws.DetailsJaxb;
import com.creditease.service.ws.MessageReqJaxb;

public class CommonQuartzJob {
	private static final Logger logger = Logger.getLogger(CommonQuartzJob.class);
	@Autowired
	private ContractService contractServiceImpl;
	
	@Autowired
	private YearOfWorkService yearOfWorkServiceImpl;

	@Autowired
	private RegularService regularServiceImpl;
	
	@Autowired
	private WaringdetailService waringdetailServiceImpl;
	
	@Autowired
	private ContractRenewalService contractRenewalServiceImpl;
	
	@Autowired
	private BirthdayService birthdayServiceImpl;
	
	@Autowired
	private JumbosmsvMapper jumbosmsvMapper;
	
	@Autowired
	private ConfigService configServiceImpl;
	
	@Autowired
	private OrgAdminChService orgAdminChServiceImpl;
	
	@Resource
	private IFuseTubeService fuseTubeService;
	public void setFuseTubeService(IFuseTubeService fuseTubeService) {
		this.fuseTubeService = fuseTubeService;
	}
	/**
	 * 
	 * 描述：合同到期预警 邮件
	 * 2013-1-16 下午03:08:27 by xjw
	 * @version
	 * @throws Exception
	 */
	public void sendContractMail() throws Exception{
		
		ConfigInfo configInfo = configServiceImpl.getConfigInfoById(4); //获取期配置信息
		if(configInfo.getConfigvalue()==0){
			//发送给对应接口人
			Map<String, List<Map<String,Object>>> datamap = contractRenewalServiceImpl.getSectorInfo();
			for(Entry<String,List<Map<String,Object>>> it : datamap.entrySet()){
				System.out.println(it.getKey()+"  size: "+it.getValue().size());
				String email = it.getKey().trim();
				List<Map<String,Object>> listinfo = it.getValue();
				String secname = contractRenewalServiceImpl.getNameBymail(email);
				//测试，先将发送邮件的代码注释掉
				contractRenewalServiceImpl.sendSectorInfoQuartz(email,secname,listinfo);
			}
			
			//发送给部门负责人：
			//思路：汇总合同快到期的人员的信息给部门，按照部门进行区分，汇总到相应的部门负责人那
			List<String> numlist = orgAdminChServiceImpl.allOrgAdmin();//过滤预警的邮箱应该发给哪些部门
			System.out.println("部门编码个数："+numlist.size());
			Map<String, Set<Map<String,Object>>> deptmap = new HashMap<String, Set<Map<String,Object>>>();
			//查询合同要到期的人员的信息,按照选定的部门进行过滤
			 List<Map<String,Object>> list = ContractRenewalQuery.getDeptList(numlist);
			 if(list!=null && list.size()>0){
				 for (int i = 0; i < list.size(); i++) {
					 Map<String,Object> tmp = list.get(i);
					 Set<Map<String,Object>> personlist = new HashSet<Map<String,Object>>();
					 personlist.add(tmp);
					 //部门负责人信息总量
					 String positionid = tmp.get("POSITIONID").toString().trim();
					 Map<String,Object> org = YearOfWorkQuery3.getDeptPerson(positionid);
					 if(null==org){
						 continue;
					 }
					 System.out.println("name: "+org.get("PNAME").toString());
//					 String pnumber = org.get("PERSONNUMBER").toString();
					 String orgnumber = org.get("DEPTCODE").toString();
					 if(deptmap.containsKey(orgnumber)){
						 deptmap.get(orgnumber).add(tmp);
					 }else{
						 deptmap.put(orgnumber, personlist);
					 }
				 }
			 } 

			 
			 //前面的都是准备工作，汇总完信息后,发送给部门负责人Quartz
			 //测试，先将发送邮件的代码注释掉
				 if(!deptmap.isEmpty()){
					 Iterator iter = deptmap.entrySet().iterator();
					 while (iter.hasNext()) {
						 Entry entry = (Entry) iter.next();
						 String orgnumber = entry.getKey().toString();
						 Set<Map<String,Object>> set = (Set<Map<String,Object>>) entry.getValue();
						 contractRenewalServiceImpl.sendOrgTestQuartz(orgnumber,set);
					 }
				 }
		}else{
			System.out.println("关闭合同到期预警邮件发送功能");
		}
		
		/* 2014-7-13 lining 取消原来信管部门独立设置的合同预警发送*/
//		//信审部门 
//		FuseTubeQuery fq = new FuseTubeQuery();
//		List<Map<String,Object>> list = fq.selectContract("05XGXSF");
//		boolean va = fuseTubeService.sendMailInfoContractFuseTubeTest(list);
//		System.out.println("result\t" + va);
		logger.info("统计\t" + TongJi.tongJiCount + "\texception\t" + TongJi.tongJiExceptionCount);
		TongJi.initCount();//信息初始化
	}
	/**
	 * 
	 * 描述：司龄预警 邮件
	 * 2013-1-16 下午03:08:10 by xjw
	 * @version
	 * @throws Exception
	 */
	public void sendYearOfWorkMailTest() throws Exception{
		ConfigInfo configInfo = configServiceImpl.getConfigInfoById(2);//获取期配置信息
		if(configInfo.getConfigvalue()==0){
			List<String> numlist = orgAdminChServiceImpl.allOrgAdmin();
			System.out.println("部门编码个数："+numlist.size());
			Map<String, Set<PersonData2>> map = YearOfWorkQuery3.getAllByYear2(numlist);
			 //发送给直接上级、隔级上级
			 Iterator it = map.entrySet().iterator();
			 while (it.hasNext()) {
				 Entry entry = (Entry) it.next();
		           String pnumber = entry.getKey().toString();
		           // entry.getKey() 返回与此项对应的键
		           // entry.getValue() 返回与此项对应的值
		         Set<PersonData2> list = (Set<PersonData2>) entry.getValue();
				 yearOfWorkServiceImpl.sendHigher(pnumber,list);
			 }
			 
			 Map<String, Set<Map<String,Object>>> deptmap = new HashMap<String, Set<Map<String,Object>>>();
//			
			 List<Map<String,Object>> list = YearOfWorkQuery3.getYearPerson(numlist);
			 if(list!=null && list.size()>0){
				 for (int i = 0; i < list.size(); i++) {
					 Map<String,Object> tmp = list.get(i);
					//发送给本人
					 yearOfWorkServiceImpl.sendPersonQuartz(tmp); 
					 Set<Map<String,Object>> personlist = new HashSet<Map<String,Object>>();
					 personlist.add(tmp);
					 //部门负责人信息总量
					 String positionid = tmp.get("POSITIONID").toString().trim();
					 Map<String,Object> org = YearOfWorkQuery3.getDeptPerson(positionid);
					 if(null==org){
						 continue;
					 }
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
					 while (iter.hasNext()) {
						 Entry entry = (Entry) iter.next();
						 String orgnumber = entry.getKey().toString();
						 Set<Map<String,Object>> set = (Set<Map<String,Object>>) entry.getValue();
						 yearOfWorkServiceImpl.sendOrgTestQuartz(orgnumber,set);
					 } 
				 }
			 }
			logger.info(TongJi.tongJiCount + "\texception\t" + TongJi.tongJiExceptionCount);
			//初始化缓存变量
			TongJi.initCount();
		}else{
			System.out.println("关闭司龄预警邮件发送功能");
		}
	}
	/**
	 * 
	 * 描述：转正预警 邮件
	 * 2013-1-16 下午03:11:18 by xjw
	 * @version
	 * @throws Exception
	 */
	public void sendRegularMailTest() throws Exception{
		ConfigInfo configInfo = configServiceImpl.getConfigInfoById(3);//获取期配置信息
		if(configInfo.getConfigvalue()==0){
			//给直接上级和间接上级发送信息(这两块的信息是合并在一起发送的)
			List<String> numlist = orgAdminChServiceImpl.allOrgAdmin();
			System.out.println("部门编码个数："+numlist.size());
			Map<String,Map<String,List<Map<String,Object>>>> mpAllPersonInfos = QualifyingMatureMergeQuery.mergeAllPersonInfo(numlist);
			for(Entry<String,Map<String,List<Map<String,Object>>>> mp : mpAllPersonInfos.entrySet()){
				Map<String,List<Map<String,Object>>> mpInfo = mp.getValue();
				//测试，先将发送邮件的代码注释掉
				regularServiceImpl.sendMailInfosMerge(mpInfo);//处理需要转正的人员的信息
		    }
			//发送部门负责人的信息，需要处理BP的相关的信息 模拟给部门负责人发送邮件
			Map<String,List<Map<String,Object>>> mp = QualifyingMatureQuery.queryResponsePersonInfo(numlist);////查询部门负责人的信息
			//查询部门负责人的人员的信息
			Map<String,List<Map<String,Object>>> mpImmPersons = QualifyingMatureQuery.queryUnderResponsePersonInfo(numlist);//部门负责人的人员的信息
			if(mp != null && mpImmPersons != null){
				for(Entry<String,List<Map<String,Object>>> entry : mp.entrySet()){
					System.out.println("key::::" + entry.getKey());
					List<Map<String,Object>> mpPersons = entry.getValue();
					//这里面对应的是收件人的下属
					List<Map<String,Object>> mpImmPerson = mpImmPersons.get(entry.getKey());//根据key取值
					System.out.println(mpPersons + "\t" + mpImmPersons);
					//测试，先将发送邮件的代码注释掉
					if(mpPersons != null && (mpPersons.size()>0)&& mpImmPerson != null){//如果信息不为空
						//将这些信息对应的邮件整理出来
						regularServiceImpl.sendResponsePersonMailInfo(mpPersons,mpImmPerson);//处理需要转正的人员的信息
					}
				}
			}
			logger.info(TongJi.tongJiCount + "\texception\t" + TongJi.tongJiExceptionCount);
			//初始化缓存变量
			TongJi.initCount();
		}else{
			System.out.println("关闭转正预警邮件发送功能");
		}
		/* 2014-8-25 lining 取消原来信管部门独立设置的转正预警发送*/
//		FuseTubeQuery fq = new FuseTubeQuery();
//		List<Map<String,Object>> list = fq.selectRegularPersonInfos("05XGXSF");
//		boolean va = fuseTubeService.sendMailInfosFuseTubeTest(list);
//		System.out.println("result\t" + va);
		logger.info("统计\t" + TongJi.tongJiCount + "\texception\t" + TongJi.tongJiExceptionCount);
		TongJi.initCount();//信息初始化
	}
	/**
	 * 
	 * 描述：生日预警 短信
	 * 2013-1-16 下午03:27:00 by xjw
	 * @version
	 * @throws Exception
	 */
	
	public void sendCell() throws Exception{
		ConfigInfo configInfo = configServiceImpl.getConfigInfoById(1);//获取期配置信息
		if(configInfo.getConfigvalue()==0){
			String content = null;
			MessageReqJaxb req = new MessageReqJaxb(); // webservice参数
			DetailsJaxb[] ds = new DetailsJaxb[1]; // 短信内容数组
			DetailsJaxb d =  new DetailsJaxb();
			String[] keywords = new String[1];// 模板关键字
			Jumbosmsv jumb = jumbosmsvMapper.selectByIsuse(1);
			String theme=null;
			List<String> numlist = orgAdminChServiceImpl.allOrgAdmin();
			System.out.println("部门编码个数："+numlist.size());
			List list = WaringDetailQuery.getWaringDetailList(numlist);
//			List list = WaringDetailQuery.getWaringDetailList();
			if(list!=null){
				for (int i = 0; i < list.size(); i++) {
					QueryData qd=(QueryData) list.get(i);
					waringdetailServiceImpl.sendCell(content, req, ds, d, keywords, theme, qd, jumb);
				}
			}
			logger.info(TongJi.tongJiCount + "\texception\t" + TongJi.tongJiExceptionCount);
			//初始化缓存变量
			TongJi.initCount();		
		}else{
			System.out.println("关闭生日预警短信发送功能");
		}
	}
	/**
	 * 
	 * 描述：生日预警 邮件
	 * 2013-2-22 上午10:46:59 by xjw
	 * @version
	 */
	public void sendBirthdayImage() throws Exception{
		ConfigInfo configInfo = configServiceImpl.getConfigInfoById(5);//获取期配置信息
		if(configInfo.getConfigvalue()==0){
//			System.out.println("启动生日预警邮件发送功能");
			List<String> numlist = orgAdminChServiceImpl.allOrgAdmin();
			System.out.println("部门编码个数："+numlist.size());
			List<Map<String,Object>> list = WaringDetailQuery.sendImage(numlist);
//			List<Map<String,Object>> list = WaringDetailQuery.sendImage();
			if(list!=null && list.size()>0){
				for (int i = 0; i < list.size(); i++) {
					Map<String, Object> map= list.get(i);
					birthdayServiceImpl.sendImages(map);
				}
			}
			logger.info(TongJi.tongJiCount + "\texception\t" + TongJi.tongJiExceptionCount);
			//初始化缓存变量
			TongJi.initCount();
			System.err.println("生日祝福邮件发送成功！！！");
		}else{
			System.out.println("关闭生日预警邮件发送功能");
		}
	}
	/**
	 * 将人员的生日预警信息发送给上级（直接、隔级）和部门负责人（抄送综合管理和BP人员）
	 * @author lining
	 * @since 2014-5-26
	 * @throws Exception
	 */
	public void sendBirthdayWarn() throws Exception{
//		System.out.println("test");
		ConfigInfo configInfo = configServiceImpl.getConfigInfoById(1);//获取生日预警配置信息
		if(configInfo.getConfigvalue()==0){
			//获取所有部门编号
			List<String> numlist = orgAdminChServiceImpl.allOrgAdmin();
			System.out.println("部门编码个数："+numlist.size());
			//获取各部门
			Map<String,Map<String,List<Map<String,Object>>>> mpAllPersonInfos = BirthdayQuery.mergeAllPersonInfo(numlist);
			for(Entry<String,Map<String,List<Map<String,Object>>>> mp : mpAllPersonInfos.entrySet()){
				Map<String,List<Map<String,Object>>> mpInfo = mp.getValue();
				//测试，先将发送邮件的代码注释掉
				birthdayServiceImpl.sendHigherMailInfo(mpInfo);
		    }
			//发送部门负责人的信息，需要处理BP的相关的信息 模拟给部门负责人发送邮件
			Map<String,List<Map<String,Object>>> mp = QualifyingMatureQuery.queryResponsePersonInfo(numlist);
			//查询部门负责人对应的过生日的员工信息
			Map<String,List<Map<String,Object>>> mpImmPersons = BirthdayQuery.queryUnderResponsePersonInfo(numlist);
			if(mp != null && mpImmPersons != null){
				for(Entry<String,List<Map<String,Object>>> entry : mp.entrySet()){
					System.out.println("key::::" + entry.getKey());
					List<Map<String,Object>> mpPersons = entry.getValue();
					//这里面对应的是收件人的下属
					List<Map<String,Object>> mpImmPerson = mpImmPersons.get(entry.getKey());
					System.out.println(mpPersons + "\t" + mpImmPersons);
					//测试，先将发送邮件的代码注释掉
					if(mpPersons != null && (mpPersons.size()>0)&& mpImmPerson != null){
						//将这些信息对应的邮件整理出来
						birthdayServiceImpl.sendResponsePersonMailInfo(mpPersons,mpImmPerson);
					}
				}
			}
			logger.info(TongJi.tongJiCount + "\texception\t" + TongJi.tongJiExceptionCount);
			//初始化缓存变量
			TongJi.initCount();
		}else{
			System.out.println("关闭生日预警邮件发送功能");
		}
	}
}
