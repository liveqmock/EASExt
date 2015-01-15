package com.creditease.eas.hr.kingdee.query;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;

import com.creditease.eas.hr.util.MessageChangeDateUtil;
import com.creditease.eas.hr.util.XmlConvert;
import com.creditease.eas.util.BaseMyBatisDao;
import com.creditease.eas.util.DateUtil;
import com.creditease.eas.util.StringUtil;
/**
 * 组织，机构，人员变动信息查询（信息推送）
 * @QualifyingMatureQuery001.java
 * created at 2013-1-4 下午02:07:43 by ygq
 * 
 * @author ygq({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class MessageChangeQuery extends BaseMyBatisDao{
	/**
	 * 
	 * 描述：组织变更的sql
	 * 2013-2-21 上午11:54:16 by ygq
	 * @version
	 * @param beginTime
	 * @return
	 */
	public  List<Map<String,Object>> selectListChangeOrgInfo(Date beginTime){
		String str = "";
		SqlSession session = null;
		try {
			session = getSession();
			Map<String,Object> map = new HashMap<String,Object>();
			//这个需要从最初的日志表中查询
			//写到一个文件里，用的时候去那个文件里读取行吗？
			map.put("beginTime",beginTime);
			Date dt = MessageChangeDateUtil.completeDate(new Date());
			//获取当前时间
			map.put("endTime",dt);
			List<Map<String,Object>> list = session.selectList("com.creditease.eas.hr.kingdee.dao.MessageSendMapper.queryChangeOrgInfo",map);
			//需要转正的人的职位信息
			//将信息放入到List中
			return list;
		}catch (Exception e) {
			return null;
		}finally{
			closeSession(session);
		}
	}
	/**
	 * 
	 * 描述：组织变更的sql
	 * 2013-2-21 上午11:54:16 by ygq
	 * @version
	 * @param beginTime
	 * @return
	 */
	public  String selectChangeOrgInfo(Date beginTime){
		String str = "";
		SqlSession session = null;
		try {
			session = getSession();
			Map<String,Object> map = new HashMap<String,Object>();
			//这个需要从最初的日志表中查询
			//写到一个文件里，用的时候去那个文件里读取行吗？
			map.put("beginTime",beginTime);
			Date dt = MessageChangeDateUtil.completeDate(new Date());
			//获取当前时间
			map.put("endTime",dt);
			List<Map<String,Object>> list = session.selectList("com.creditease.eas.hr.kingdee.dao.MessageSendMapper.queryChangeOrgInfo",map);
			//需要转正的人的职位信息
			//将信息放入到List中
			str = XmlConvert.orgnationConvertXml(list,dt);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return str;
	}
	/**
	 * 
	 * 描述：职位变更的sql
	 * 2013-2-21 上午11:54:16 by ygq
	 * @version
	 * @param beginTime
	 * @return
	 */
	public  String selectChangePositionInfo(Date beginTime){
		String str = "";
		SqlSession session = null;
		try {
			session = getSession();
			Map<String,Object> map = new HashMap<String,Object>();
			//这个需要从最初的日志表中查询,写到一个文件里，用的时候去那个文件里读取行吗？
			map.put("beginTime",beginTime);
			Date dt = MessageChangeDateUtil.completeDate(new Date());
			//获取当前时间
			map.put("endTime",dt);
			List<Map<String,Object>> list = session.selectList("com.creditease.eas.hr.kingdee.dao.MessageSendMapper.queryChangePositionInfo",map);
			//需要转正的人的职位信息
			//将信息放入到List中
			str = XmlConvert.positionInfoConvertXml(list,dt);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return str;
	}
	/**
	 * 
	 * 描述：人员变更的sql
	 * 2013-2-21 上午11:54:16 by ygq
	 * @version
	 * @param beginTime
	 * @return
	 */
	public  String selectChangePersonInfo(Date beginTime){
		String str = "";
		SqlSession session = null;
		try {
			session = getSession();
			Map<String,Object> map = new HashMap<String,Object>();
			Date dt = MessageChangeDateUtil.completeDate(new Date());
			//获取当前时间
			//写到一个文件里，用的时候去那个文件里读取行吗？
			map.put("beginTime",beginTime);
			map.put("endTime",dt);
			List<Map<String,Object>> list = session.selectList("com.creditease.eas.hr.kingdee.dao.MessageSendMapper.queryChangePersonInfo",map);
//			for(int i=0;i<list.size();i++){
//				Map<String,Object> ma = list.get(i);
//				System.out.println(ma.get("FUSERCODE") + "\t" + ma.get("FUSERNAME") + "\t" + ma.get("FISMAINJOB"));
//			}
//			System.out.println("list:::::::::" +list.size());
			//需要转正的人的职位信息
			//将信息放入到List中
			str = XmlConvert.personInfoConvertXml(list,dt);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return str;
	}
	/******************************具体调用的方法************************************************/
//	public static void orgQuery(){
//		String strTime = "2013-1-30 11:05:03";
//		Date beginTime = StringUtil.strToDate(strTime);
//		String str = selectChangeOrgInfo(beginTime);
//		System.out.println("why:::::\n" + str);
//		//1.开始时间，取的是日志表里面的最大的日志记录时间
//		//2.结束时间，取的是
//	}
//	
//	public static void positionQuery(){
//		String strTime = "2013-2-26 11:05:03";
//		Date beginTime = StringUtil.strToDate(strTime);
//		String str = selectChangePositionInfo(beginTime);
//		System.out.println("why:::::\n" + str);
//		//1.开始时间，取的是日志表里面的最大的日志记录时间
//		//2.结束时间，取的是
//	}
//	
//	public static void personInfoQuery(){
//		String strTime = "2013-2-26 11:05:03";
//		Date beginTime = StringUtil.strToDate(strTime);
//		String str = selectChangePersonInfo(beginTime);
//		System.out.println("why:::::\n" + str);
//		//1.开始时间，取的是日志表里面的最大的日志记录时间
//		//2.结束时间，取的是
//	}
	public static void main(String[] args) throws Exception{
		//orgQuery();
//		positionQuery();
//		personInfoQuery();
	}
}


