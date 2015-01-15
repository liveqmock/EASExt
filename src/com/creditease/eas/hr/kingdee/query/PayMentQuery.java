package com.creditease.eas.hr.kingdee.query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;

import com.creditease.eas.hr.bean.CapticalAssetsBean;
import com.creditease.eas.hr.kingdee.dao.MessageSendMapper;
import com.creditease.eas.hr.kingdee.dao.PayMentMapper;
import com.creditease.eas.hr.util.XmlConvert;
import com.creditease.eas.util.BaseMyBatisDao;
/**
 * 码表信息查询
 * @QualifyingMatureQuery001.java
 * created at 2013-1-4 下午02:07:43 by ygq
 * 
 * @author ygq({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class PayMentQuery extends BaseMyBatisDao{
	/**
	 * 
	 * 描述：固定资产类别
	 * 2013-1-4 下午03:02:45 by ygq
	 * @version
	 */
	public static List<CapticalAssetsBean> queryAssetsClassNameAndCodeByFistClass(Map<String,String> map){
		SqlSession session = null;
		List<CapticalAssetsBean> list = null;
		try {
			session = getSession();
			list =  session.selectList("com.creditease.eas.hr.kingdee.dao.PayMentMapper.selectAssetsClassNameAndCodeByFistClass",map);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return list;
	}
	
	/**
	 * 
	 * 描述：固定资产类别  以逗号分割，传多个值
	 * 2014-7-17 
	 * @version
	 */
	public static List<CapticalAssetsBean> queryAssetsClassNameAndCodeByFistClassS(Map<String,String[]> map){
		SqlSession session = null;
		List<CapticalAssetsBean> list = null;
		try {
			session = getSession();
			list =  session.selectList("com.creditease.eas.hr.kingdee.dao.PayMentMapper.selectAssetsClassNameAndCodeByFistClass",map);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return list;
	}
	
	/**
	 * 
	 * 描述：供应商
	 * 2013-1-4 下午03:02:45 by ygq
	 * @version
	 */
	public static List<Map<String,Object>> querySupplierInfo(){
		SqlSession session = null;
		List<Map<String,Object>> list = null;
		try {
			session = getSession();
			list =  session.selectList("com.creditease.eas.hr.kingdee.dao.PayMentMapper.selectSupplierInfo");
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return list;
	}
	/**
	 * 
	 * 描述：省，市
	 * 2013-1-4 下午03:02:45 by ygq
	 * @version
	 */
//	public static List<Map<String,Object>> queryCityInfo(){
//		SqlSession session = null;
//		List<Map<String,Object>> list = null;
//		try {
//			session = getSession();
//			list =  session.selectList("com.creditease.eas.hr.kingdee.dao.PayMentMapper.selectCityInfo");
//		}catch (Exception e) {
//			e.printStackTrace();
//		}finally{
//			closeSession(session);
//		}
//		return list;
//	}
	/**
	 * 
	 * 描述：查询计量单位的信息
	 * 2013-1-4 下午03:02:45 by ygq
	 * @version
	 */
	public static List<CapticalAssetsBean> queryMeasureUnitInfo(Map map){
		SqlSession session = null;
		List<CapticalAssetsBean> list = null;
		try {
			session = getSession();
			list =  session.selectList("com.creditease.eas.hr.kingdee.dao.PayMentMapper.selectMeasureUnitInfo",map);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return list;
	}
	public static void main(String[] args) {
//		List<CapticalAssetsBean> list = queryMeasureUnitInfo();
//		List<CapticalAssetsBean> list = queryAssetsClassNameAndCodeByFistClass("01%");
		Map<String,String> mp = new HashMap<String,String>();
//		mp.put("firstClass", "01___%");
//		mp.put("companyId", "rlgAAAAADfXM567U");
		
		
		String firstClass = "0101";
		String[] parameters = firstClass.split("_");
		Map<String,String> map = new HashMap<String,String>();
		parameters[0] = firstClass+"%";
		if(1==firstClass.length()){   
			map.put("companyId", parameters[0]);
		}else{
			map.put("firstClass",parameters[0]);
		} 
		
//		mp.put("firstClass", "0101__");
//		mp.put("companyId", "G%");
		List<CapticalAssetsBean> list = queryAssetsClassNameAndCodeByFistClass(map);
		System.out.println(list.size());
		for(int i=0;i<list.size();i++){
			CapticalAssetsBean ca = list.get(i);
			System.out.println(ca.getName() + "\t" + ca.getCode());
		}
//		queryCityInfo();
	}
}
