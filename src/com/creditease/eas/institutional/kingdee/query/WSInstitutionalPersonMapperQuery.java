package com.creditease.eas.institutional.kingdee.query;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.creditease.eas.institutional.kingdee.dao.WSInstitutionalPersonMapper;
import com.creditease.eas.util.BaseMyBatisDao;
import com.creditease.eas.util.StringUtil;
import com.creditease.webservice.dto.UserInfoDTO;



public class WSInstitutionalPersonMapperQuery extends BaseMyBatisDao {
	
//	public static List<FluctuationBean> queryFluctuation(){
//		SqlSession session = getSession();  
//		WSSMPPersonMapper mapper = session.getMapper(WSSMPPersonMapper.class);
//		List<FluctuationBean> list2 = mapper.selectFluctuation();//.selectList("com.creditease.eas.hrnew.kingdee.dao.WSSMPPersonMapper.selectFluctuation");
//		System.out.println("list2\t" + list2.size());
//	}
 
	/**
	 * 人员信息
	 */
	public static List<UserInfoDTO> queryPerson(Map<String, Object> map){
		 
		SqlSession session = null; 
		List<UserInfoDTO> list = null;
		try { 
			session = getSession();
			WSInstitutionalPersonMapper mapper = session.getMapper(WSInstitutionalPersonMapper.class);  
			list = mapper.selectPerson(map);
			
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
		Date date = StringUtil.strToDate("2013-10-22 13:57:06", "yyyy-MM-dd HH:mm:ss");  //开始时间
//		Date endTime = StringUtil.strToDate("2013-1-1 ", "yyyy-MM-dd "); //结束时间 
		map.put("beginTime", date);
//		map.put("endTime", endTime);
		// TODO Auto-generated method stub
		System.out.println("hello world ");
		 List<UserInfoDTO> list = queryPerson(map);
		 for (int i = 0; i < 10; i++) {
			 UserInfoDTO temp = list.get(i);
			System.out.println(i+"___"+temp.getOldId()+"___"+temp.getNewId()+"————"+temp.getEnabled()
					+temp.getLevelOneOrg()+"___"+temp.getGrade()+"————"+temp.getEmail()+"————"+temp.getName());
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
