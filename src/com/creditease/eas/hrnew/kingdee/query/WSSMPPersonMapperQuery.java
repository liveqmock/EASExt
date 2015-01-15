package com.creditease.eas.hrnew.kingdee.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.creditease.eas.hrnew.kingdee.dao.WSSMPPersonMapper;
import com.creditease.eas.util.BaseMyBatisDao;
import com.creditease.eas.util.StringUtil;
import com.creditease.smp.manager.dto.EASEmployeeDTO;
import com.creditease.smp.manager.dto.EASOrganizeDTO;
import com.creditease.smp.manager.dto.EASPositionDTO;
import com.creditease.smp.manager.dto.EASTransferDTO;

public class WSSMPPersonMapperQuery extends BaseMyBatisDao {
	
//	public static List<FluctuationBean> queryFluctuation(){
//		SqlSession session = getSession();  
//		WSSMPPersonMapper mapper = session.getMapper(WSSMPPersonMapper.class);
//		List<FluctuationBean> list2 = mapper.selectFluctuation();//.selectList("com.creditease.eas.hrnew.kingdee.dao.WSSMPPersonMapper.selectFluctuation");
//		System.out.println("list2\t" + list2.size());
//	}

	/**
	 * 异动
	 */
	public static List<EASTransferDTO> queryFluctuation(Map<String, Object> map){
		SqlSession session = null;
		List<EASTransferDTO> list = null;
		try { 
			session = getSession();
			WSSMPPersonMapper mapper = session.getMapper(WSSMPPersonMapper.class);
			list = mapper.selectFluctuation(map); 
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return list;
	}
	
	/**
	 * 职员
	 * @return
	 */
	public static List<EASEmployeeDTO> queryPerson(Map<String, Object> map){
		//职位整合
		List<EASEmployeeDTO> list2 = new ArrayList<EASEmployeeDTO>();
		
		SqlSession session = null;
		List<EASEmployeeDTO> list1 = null;
		try { 
			session = getSession();
			WSSMPPersonMapper mapper = session.getMapper(WSSMPPersonMapper.class);
			list1 = mapper.selectPerson(map);
			System.out.println("list1___"+list1.size());
			//职位整合 
			EASEmployeeDTO easemployee1;
			EASEmployeeDTO easemployee2;
			String usercode1,usercode2;
			for (int i = 0; i < list1.size(); i++) { 
				 easemployee1 = list1.get(i);
				 if(i==(list1.size()-1)){    //  如果运行到最后一个数据，直接添加
					 list2.add(easemployee1);
				 }
				 else{
					 easemployee2 = list1.get(i+1);
					 usercode1 = easemployee1.getUsercode();
					 usercode2 = easemployee2.getUsercode();  
					  if(usercode1.equals(usercode2)){  //如果相邻员工编码相等
						 
						  easemployee2.setHrpositioncode(easemployee1.getHrpositioncode()+"_"+easemployee2.getHrpositioncode());
						  easemployee2.setHrpositiondisplayname(easemployee1.getHrpositiondisplayname()+"_"+easemployee2.getHrpositiondisplayname());
						  easemployee2.setIsmainjob(easemployee1.getIsmainjob()+"_"+easemployee2.getIsmainjob());
						  list1.set(i+1, easemployee2);
					  }
					  else{
						  list2.add(easemployee1);
					  }
				 }
			}

			
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return list2;
	}
	
	
	/**
	 * 
	 * 描述：组织信息
	 * 2013-6-25 下午04:57:41 by Administrator
	 * @version
	 * @param map
	 * @return
	 */
	public static List<EASOrganizeDTO> orgAdminQuery(Map<String, Object> map){
		SqlSession session = null;
		List<EASOrganizeDTO> list = null;
		try {
			session = getSession();
			WSSMPPersonMapper mapper = session.getMapper(WSSMPPersonMapper.class);
			list = mapper.selectOrgAdminQuery(map);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return list;
	}
	/**
	 * 
	 * 描述：职位信息
	 * 2013-6-25 下午04:57:09 by Administrator
	 * @version
	 * @param map
	 * @return
	 */
	public static List<EASPositionDTO> orgPositionQuery(Map<String, Object> map){
		SqlSession session = null;
		List<EASPositionDTO> list = null;
		try {
			session = getSession();
			WSSMPPersonMapper mapper = session.getMapper(WSSMPPersonMapper.class);
			list = mapper.selectOrgPositionQuery(map);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return list;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Map<String, Object> map = new HashMap<String, Object>();
//		Date date = StringUtil.strToDate("2013-4-22 13:57:06", "yyyy-MM-dd HH:mm:ss");  //开始时间
//		Date endTime = StringUtil.strToDate("2013-1-1 ", "yyyy-MM-dd "); //结束时间
//		map.put("endTime", endTime);
		
		// TODO Auto-generated method stub
		System.out.println("hello world ");
		 List<EASEmployeeDTO> list = queryPerson(map);
		 for (int i = list.size()-3; i < list.size(); i++) {
			 EASEmployeeDTO temp = list.get(i);
			System.out.println(i+"___"+temp.getUsername()+"___"+temp.getHrpositioncode()+"————"+temp.getIsmainjob());
		}
		System.out.print("————————————————————————"+list.size());
		
//		System.out.println("hello world ");
//		 List<EASTransferDTO> list = queryFluctuation(map);
//		 for (int i = 0; i < 10; i++) {
//			 EASTransferDTO temp = list.get(i);
//			System.out.println(temp.getUsercode());
//		} 
//		System.out.print("————————————————————————"+list.size());
//		
////		
//		Map<String, Object> map = new HashMap<String, Object>();
//		Date date = StringUtil.strToDate("2013-4-22 13:57:06", "yyyy-MM-dd HH:mm:ss");  //开始时间
//		Date endTime = StringUtil.strToDate("2013-5-22 13:57:06", "yyyy-MM-dd HH:mm:ss"); //结束时间
//		map.put("beginTime", date);
//		map.put("endTime", endTime);
		//组织信息
//		List<EASOrganizeDTO> list = orgAdminQuery(map);
//		for(EASOrganizeDTO bean : list){
//			System.out.println(bean.getDisplayname()+":"+bean.getParentnumber()+":"+bean.getOrgtypestr());
//		}
		//职位信息
//		List<EASPositionDTO> list = orgPositionQuery(map);
//		for(EASPositionDTO bean : list){
//			System.out.println(bean.getPositionname()+":"+bean.getParentnumber()+":"+bean.getJobtypedisplayname());
//		}
//		System.out.println(list.size());

	}

}
