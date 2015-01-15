package com.creditease.eas.hrnew.service;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.creditease.eas.hr.dao.BaseLogRecordMapper;
import com.creditease.eas.hr.util.MessageChangeDateUtil;
import com.creditease.eas.hrnew.dao.BaseLogRecordSMPMapper;
import com.creditease.eas.hrnew.kingdee.query.WSSMPPersonMapperQuery;
import com.creditease.eas.util.StringUtil;
import com.creditease.smp.manager.EASWebservice;
import com.creditease.smp.manager.dto.EASEmployeeDTO;
import com.creditease.smp.manager.dto.EASOrganizeDTO;
import com.creditease.smp.manager.dto.EASPositionDTO;
import com.creditease.smp.manager.dto.EASTransferDTO;

public class WSSMPPersonMapperClient {
	
	/*
	 * 异动
	 */
	public Integer queryFluctuation(){
		Map<String, Object> map = new HashMap();
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		EASWebservice easWebservice = (EASWebservice) context.getBean("client3");
		List<EASTransferDTO> fluctuation = WSSMPPersonMapperQuery.queryFluctuation(map);
		
		Integer tempint = easWebservice.updateEASTransfers(fluctuation);
		return tempint;
		
		
		

	}
	/*
	 * 职员
	 */
	public Integer queryPerson(){
		Map<String, Object> map = new HashMap();
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		EASWebservice easWebservice = (EASWebservice) context.getBean("client3");
		 List<EASEmployeeDTO> fluctuation = WSSMPPersonMapperQuery.queryPerson(map);
		
		Integer tempint = easWebservice.updateEASEmployees(fluctuation);
		return tempint;
		

	}
	/*
	 * 组织信息
	 */
	public Integer orgAdminQuery(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		EASWebservice easWebservice = (EASWebservice) context.getBean("client3");
		Map<String, Object> map = new HashMap();
		List<EASOrganizeDTO> fluctuation = WSSMPPersonMapperQuery.orgAdminQuery(map);
		
		Integer tempint = easWebservice.updateEASOrganizes(fluctuation);
		return tempint;
		

	}
	/*
	 * 职位信息
	 */
	public Integer orgPositionQuery(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		EASWebservice easWebservice = (EASWebservice) context.getBean("client3");
		Map<String, Object> map = new HashMap();
//		Date date = StringUtil.strToDate("2013-4-22 13:57:06", "yyyy-MM-dd HH:mm:ss");  //开始时间
//		Date endTime = StringUtil.strToDate("2013-5-22 13:57:06", "yyyy-MM-dd HH:mm:ss"); //结束时间
//		map.put("beginTime", date);
//		map.put("endTime", endTime);
		List<EASPositionDTO> fluctuation = WSSMPPersonMapperQuery.orgPositionQuery(map);
		
		Integer tempint = easWebservice.updateEASPositions(fluctuation);
		return tempint;
		

	}
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		WSSMPPersonMapperClient im = (WSSMPPersonMapperClient)context.getBean("empChangeSendService");
		
		WSSMPPersonMapperClient ws = new WSSMPPersonMapperClient();
		


		
		//1.异动
		Integer temp = ws.queryFluctuation();
		System.out.println("__"+temp);
		
		//2.组织信息
//		Integer temp = ws.orgAdminQuery();
//		System.out.println("__"+temp);
		
		//3.职位信息
//		Integer temp = ws.orgPositionQuery();
//		System.out.println("__"+temp);
		
		//4.职员
//		Integer temp = ws.queryPerson();
//		System.out.println("__"+temp);
	}
}
