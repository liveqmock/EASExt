//package com.creditease.eas.warn.action;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Map.Entry;
//
//import com.creditease.eas.util.TongJi;
//import com.creditease.eas.warn.kingdee.query.QualifyingMatureQuery;
//
//public class RegularControl {
//	/**
//	 * 
//	 * 描述：发送邮件
//	 * 2013-1-11 上午10:12:04 by ygq
//	 * @version
//	 * @return
//	 * @throws Exception
//	 */
//	public String sendRegularMailTest() throws Exception{
//		//模拟给直接上级发邮件
//		Map<String,List<Map<String,Object>>> mp = QualifyingMatureQuery.queryQualifyPersonsInfo(QualifyingMatureQuery.list);//查询间接上级的信息
//		//查询直接上级下的人员的信息
//		Map<String,List<Map<String,Object>>> mpImmPersons = QualifyingMatureQuery.queryImmediatePersonInfoInfo();//查询直接下属的信息
//		System.out.println("开始遍历了\t" + mpImmPersons);
//		if(mp != null && mpImmPersons != null){
//			for(Entry<String, List<Map<String, Object>>> entry : mp.entrySet()){
//				System.out.println("key::::" + entry.getKey());
//				List<Map<String,Object>> mpPersons = entry.getValue();
//				//这里面对应的是收件人的下属
//				List<Map<String,Object>> mpImmPerson = mpImmPersons.get(entry.getKey());//根据key取值
//				System.out.println(mpPersons + "\t" + mpImmPersons);
//				if(mpPersons != null && mpImmPerson != null){//如果信息不为空
//					//将这些信息对应的邮件整理出来
////					regularService.sendMailInfosV2Test(mpPersons,mpImmPerson);//处理需要转正的人员的信息
//					break;//先发送一个试试
//				}
//			}
//		}
//		//模拟给间接上级发邮件
////		Map<String,List<Map<String,Object>>> mp = QualifyingMatureQuery.queryQualifyPersonsInfo(QualifyingMatureQuery.listIndirect);//查询间接上级的信息
////		//查询直接上级下的人员的信息
////		Map<String,List<Map<String,Object>>> mpImmPersons = QualifyingMatureQuery.queryIndirectPersonInfoByParentPositionId();//获取间接上级下的人员的信息
////		System.out.println("开始遍历了\t" + mpImmPersons);
////		if(mp != null && mpImmPersons != null){
////			for(Entry<String, List<Map<String, Object>>> entry : mp.entrySet()){
////				System.out.println("key::::" + entry.getKey());
////				List<Map<String,Object>> mpPersons = entry.getValue();
////				//这里面对应的是收件人的下属
////				List<Map<String,Object>> mpImmPerson = mpImmPersons.get(entry.getKey());//根据key取值
////				System.out.println(mpPersons + "\t" + mpImmPersons);
////				if(mpPersons != null && mpImmPerson != null){//如果信息不为空
////					//将这些信息对应的邮件整理出来
////					regularService.sendMailInfosV2Test(mpPersons,mpImmPerson);//处理需要转正的人员的信息
////					break;//先发送一个试试
////				}
////			}
////		}
//		//模拟给部门负责人发送邮件
////		Map<String,List<Map<String,Object>>> mp = QualifyingMatureQuery.queryResponsePersonInfo();////查询部门负责人的信息
////		//查询部门负责人的人员的信息
////		Map<String,List<Map<String,Object>>> mpImmPersons = QualifyingMatureQuery.queryUnderResponsePersonInfo();//部门负责人的人员的信息
////		if(mp != null && mpImmPersons != null){
////			for(Entry<String,List<Map<String,Object>>> entry : mp.entrySet()){
////				System.out.println("key::::" + entry.getKey());
////				List<Map<String,Object>> mpPersons = entry.getValue();
////				//这里面对应的是收件人的下属
////				List<Map<String,Object>> mpImmPerson = mpImmPersons.get(entry.getKey());//根据key取值
////				System.out.println(mpPersons + "\t" + mpImmPersons);
////				if(mpPersons != null && mpImmPerson != null){//如果信息不为空
////					//将这些信息对应的邮件整理出来
////					regularService.sendMailInfosV2Test(mpPersons,mpImmPerson);//处理需要转正的人员的信息
////					break;//先发送一个试试
////				}
////			}
////		}
//		System.out.println("ok了");
////		logger.info("统计\t" + TongJi.tongJiCount + "\texception\t" + TongJi.tongJiExceptionCount);
//		return "sendMail_success";
////		{06GDXSF=[{POSITIONNAME=部门总监, DEPTCODE=06GDXSF, PNAME=王威, DEPTNAME=个贷营销中心, DEPTID=rlgAAAAADLPM567U, POSITIONID=TfHtg0PJQhymF5OIC3uU9HSuYS4=, CFMAIL=weiwang@creditease.cn, PERSONID=rlgAAAAAQaeA733t}], 
////			02FGGLF=[{POSITIONNAME=风险管理中心总监, DEPTCODE=02FGGLF, PNAME=王翠, DEPTNAME=风险管理中心, DEPTID=rlgAAAAADfjM567U, POSITIONID=34v1Bz9MSTq+a6iVS0eKXXSuYS4=, CFMAIL=cuiwang@creditease.cn, PERSONID=9Xdwh13ARq25w7CQQ/JvMIDvfe0=}], 
////			11LTXSF=[{POSITIONNAME=部门总监, DEPTCODE=11LTXSF, PNAME=吕骐, DEPTNAME=理财产品与投资策略部, DEPTID=rlgAAAAADALM567U, POSITIONID=f/z9MMf2RE+oV+vCO9SG0HSuYS4=, PERSONID=B1CH8ZiSR0aA8vPglEoAYYDvfe0=}]}
//		          //[{POSITIONNAME=部门总监, DEPTCODE=11LTXSF, PNAME=吕骐, DEPTNAME=理财产品与投资策略部, DEPTID=rlgAAAAADALM567U, POSITIONID=f/z9MMf2RE+oV+vCO9SG0HSuYS4=, PERSONID=B1CH8ZiSR0aA8vPglEoAYYDvfe0=}]
//	}
//	/**
//	 * 描述：模拟合并的过程
//	 * 2013-3-30 下午04:12:53 by ygq
//	 * @version
//	 * @return
//	 */
//	public static Map<String,Map<String,List<Map<String,Object>>>> imitateMerge(){
//		Map<String,Map<String,List<Map<String,Object>>>>  map = new HashMap<String,Map<String,List<Map<String,Object>>>>();
//		Map<String,List<Map<String,Object>>> mppDown = new HashMap<String,List<Map<String,Object>>>();//直接下级
//		//直接下级
//		List<Map<String,Object>> listImm = new ArrayList<Map<String,Object>>();
//		//第一个
//		Map<String,Object> mapp1 = new HashMap<String,Object>();
//		mapp1.put("PERSONID", "12345");
//		mapp1.put("PNAME", "张三");
//		listImm.add(mapp1);
//		//第二个人
//		Map<String,Object> mapp2 = new HashMap<String,Object>();
//		mapp2.put("PERSONID", "23456");
//		mapp2.put("PNAME", "李四");
//		listImm.add(mapp2);
//
//		mppDown.put("immPerson", listImm);
//		//间接下级
//		List<Map<String,Object>> listIndire = new ArrayList<Map<String,Object>>();
//		//第一个
//		Map<String,Object> mappp1 = new HashMap<String,Object>();
//		mappp1.put("PERSONID", "12345");
//		mappp1.put("PNAME", "张小三");
//		listIndire.add(mappp1);
//		//第二个人
//		Map<String,Object> mappp2 = new HashMap<String,Object>();
//		mappp2.put("PERSONID", "12345");
//		mappp2.put("PNAME", "李小四");
//		listIndire.add(mappp2);
//
//		mppDown.put("indirPerson",listIndire);
//		map.put("1012001458577", mppDown);//存取的人员的信息
//		return map;
//	}
//	public static void main(String[] args){
//		//收件人的信息
//		Map<String,Map<String,Object>> mapToAddress = new HashMap<String,Map<String,Object>>();
//		Map<String,Object> mapToAddPerson = new HashMap<String,Object>();//收件人
//		mapToAddPerson.put("CFMAIL", "gaoquanyang@creditease.cn");
//		mapToAddress.put("1012001458577", mapToAddPerson);
//		//收发邮件的人员的信息
//		Map<String,Map<String,List<Map<String,Object>>>> map = imitateMerge();
//		for(Entry<String,Map<String,List<Map<String,Object>>>> mp : map.entrySet()){
//			String str = mp.getKey();//对应的是收件人的编码
//			Map<String,Object> mpToPerson = mapToAddress.get(str);
//			Map<String,List<Map<String,Object>>> mpDown = mp.getValue();//对应的下级的人员名单
//			System.out.println("收件人的邮箱\t" + mpToPerson.get("CFMAIL"));
//			System.out.println("直接下属\t" + mpDown.get("immPerson"));
//			System.out.println("间接下属\t" + mpDown.get("indirPerson"));
//		}
//	}
//}
