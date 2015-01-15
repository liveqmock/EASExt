package com.creditease.eas.hr.kingdee.query;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.creditease.eas.hr.util.MessageChangeDateUtil;
import com.creditease.eas.hr.util.XmlConvert;
import com.creditease.eas.util.BaseMyBatisDao;

public class MessageSendHandlerQuery extends BaseMyBatisDao{
	/**
	 * 
	 * 描述：职位变更的sql
	 * 2013-2-21 上午11:54:16 by ygq
	 * @version
	 * @param beginTime
	 * @return
	 */
	public static String selectChangePositionInfoHandler(Date beginTime,Date dt,String fnumber){
		String str = "";
		SqlSession session = null;
		try {
			session = getSession();
			Map<String,Object> map = new HashMap<String,Object>();
			//这个需要从最初的日志表中查询,写到一个文件里，用的时候去那个文件里读取行吗？
			map.put("beginTime",beginTime);
			//获取当前时间
			map.put("endTime",dt);
			map.put("fnumber", fnumber);
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
	 * 描述：组织变更的sql
	 * 2013-2-21 上午11:54:16 by ygq
	 * @version
	 * @param beginTime
	 * @return
	 */
	public static String selectChangeOrgInfoHandler(Date beginTime,Date dt,String fnumber){
		String str = "";
		SqlSession session = null;
		try {
			session = getSession();
			Map<String,Object> map = new HashMap<String,Object>();
			//这个需要从最初的日志表中查询
			//写到一个文件里，用的时候去那个文件里读取行吗？
			map.put("beginTime",beginTime);
//			//获取当前时间
			map.put("endTime",dt);
			map.put("fnumber", fnumber);
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
	 * 描述：人员变更的sql
	 * 2013-2-21 上午11:54:16 by ygq
	 * @version
	 * @param beginTime
	 * @return
	 */
	public static String selectChangePersonInfoHandler(Date beginTime,Date dt){
		String str = "";
		SqlSession session = null;
		try {
			session = getSession();
			Map<String,Object> map = new HashMap<String,Object>();
			//获取当前时间
			//写到一个文件里，用的时候去那个文件里读取行吗？
			map.put("beginTime",beginTime);
			map.put("endTime",dt);
			List<Map<String,Object>> list = session.selectList("com.creditease.eas.hr.kingdee.dao.MessageSendMapper.queryChangePersonInfo",map);
			for(int i=0;i<list.size();i++){
				Map<String,Object> ma = list.get(i);
				System.out.println(ma.get("FUSERCODE") + "\t" + ma.get("FUSERNAME") + "\t" + ma.get("FISMAINJOB"));
			}
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
}
