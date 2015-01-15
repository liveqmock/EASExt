package com.creditease.eas.hr.kingdee.query;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.creditease.eas.hr.util.XmlConvert;
import com.creditease.eas.util.BaseMyBatisDao;

/***
 * 给内网提供的数据:
 * @author ygq
 * @version 1.0 2013/12/16 18:42
 *
 */
public class NeiWangQuery extends BaseMyBatisDao{
	/***
	 * 获得司龄满周年的员工名单
	 * @param beginTime
	 * @param dt
	 * @param fnumber
	 * @return
	 */
	public static List<Map<String,Object>> selectWorkPerson(Date beginTime){
		SqlSession session = null;
		List<Map<String,Object>> list = null;
		try {
			session = getSession();
			Map<String,Object> map = new HashMap<String,Object>();
			//这个需要从最初的日志表中查询,写到一个文件里，用的时候去那个文件里读取行吗？
			map.put("beginTime",beginTime==null?new Date():beginTime);
			//获取当前时间
			list = session.selectList("com.creditease.eas.hr.kingdee.dao.NeiWangMapper.getWorkPerson",map);
			//需要转正的人的职位信息
			//将信息放入到List中
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return list;
	}
	/***
	 * 获得每天过生日的的员工名单
	 * @param beginTime
	 * @param dt
	 * @param fnumber
	 * @return
	 */
	public static List<Map<String,Object>> selectBirthDayList(Date beginTime){
		SqlSession session = null;
		List<Map<String,Object>> list = null;
		try {
			session = getSession();
			Map<String,Object> map = new HashMap<String,Object>();
			//这个需要从最初的日志表中查询,写到一个文件里，用的时候去那个文件里读取行吗？
			map.put("beginTime",beginTime==null?new Date():beginTime);
			//获取当前时间
			list = session.selectList("com.creditease.eas.hr.kingdee.dao.NeiWangMapper.birthDayList",map);
			//需要转正的人的职位信息
			//将信息放入到List中
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return list;
	}
	public static void main(String[] args) {
//		List<Map<String,Object>> list = selectedWorkPerson(null);
		List<Map<String,Object>> list = selectBirthDayList(null);
		System.out.println("list\t" + list);
	}
}
