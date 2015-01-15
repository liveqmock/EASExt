package com.creditease.eas.hrnew.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
 
import com.creditease.eas.hrnew.kingdee.query.WSSMPPersonMapperQuery;
import com.creditease.eas.hrnew.service.IWSSMPPersonMapperHandService; 
import com.creditease.eas.util.StringUtil;
import com.creditease.smp.manager.EASWebservice;
import com.creditease.smp.manager.dto.EASEmployeeDTO;
import com.creditease.smp.manager.dto.EASOrganizeDTO;
import com.creditease.smp.manager.dto.EASPositionDTO;
import com.creditease.smp.manager.dto.EASTransferDTO;
@Service("wSSMPPersonMapperHandService")
public class WSSMPPersonMapperHandServiceImpl implements
		IWSSMPPersonMapperHandService {

	/**
	 * 手动推送异动信息 
	 */
	public Map<String, Object> queryFluctuation(String begin,String end) {
		Map<String,Object> result  = new HashMap<String,Object>(); 
//		得到日志时间
		Map<String, Object> map = getDate(begin,end);
		
		List<EASTransferDTO> fluctuation = WSSMPPersonMapperQuery.queryFluctuation(map);
		
		//如果没有更新数据，则不调用
		Integer tempint = 0;
		System.out.println("fluctuation____________"+fluctuation.size());
		if(null==fluctuation||fluctuation.size()==0){
			System.out.println("____没有数据更新，不调用接口");
			tempint = 1;
		}
		else{
			//改成服务器调用
			ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
			EASWebservice easWebservice = (EASWebservice) context.getBean("client3");
		    tempint = easWebservice.updateEASTransfers(fluctuation); 
			 
		}
		result.put("tempint",tempint);
		result.put("number", fluctuation.size()); 
		return result;
	}
	
	/**
	 * 手动推送组织信息 
	 */
	public Map<String, Object> orgAdminQuery(String begin,String end) {
		Map<String,Object> result  = new HashMap<String,Object>();
//		得到日志时间
		Map<String, Object> map = getDate(begin,end); 
		List<EASOrganizeDTO> organize = WSSMPPersonMapperQuery.orgAdminQuery(map);
		
		//如果没有更新数据，则不调用
		Integer tempint = 0;
		System.out.println("organize____________"+organize.size());
		if(organize.equals(null)||organize.size()==0){
			System.out.println("____没有数据更新，不调用接口");
			tempint = 1;
		}
		else{
			//改成服务器调用
			ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
			EASWebservice easWebservice = (EASWebservice) context.getBean("client3");
			
			tempint = easWebservice.updateEASOrganizes(organize);
		
		}
		result.put("tempint",tempint);
		result.put("number", organize.size()); 
		return result;
	}

	/**
	 * 手动推送职位信息 
	 */
	public Map<String, Object> orgPositionQuery(String begin,String end) {
		Map<String,Object> result  = new HashMap<String,Object>();
//		得到日志最大时间
		Map<String, Object> map = getDate(begin,end);
		List<EASPositionDTO> position = WSSMPPersonMapperQuery.orgPositionQuery(map);

		//如果没有更新数据，则不调用
		Integer tempint = 0;
		System.out.println("position____________"+position.size());
		if(position.equals(null)||position.size()==0){
			System.out.println("____没有数据更新，不调用接口");
			tempint = 1;
		}
		else{
			//改成服务器调用
			ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
			EASWebservice easWebservice = (EASWebservice) context.getBean("client3");
		
			tempint = easWebservice.updateEASPositions(position);
		}
		result.put("tempint",tempint);
		result.put("number", position.size()); 
		return result;
	}

	

	/**
	 * 手动推送员工信息 
	 */
	public Map<String, Object> queryPerson(String begin,String end) {
		Map<String,Object> result  = new HashMap<String,Object>();
//		得到日志最大时间
		Map<String, Object> map = getDate(begin,end);
		
		List<EASEmployeeDTO> employee = WSSMPPersonMapperQuery.queryPerson(map);

		//如果没有更新数据，则不调用
		Integer tempint = 0;
		System.out.println("employee____________"+employee.size());
		if(employee.equals(null)||employee.size()==0){
			System.out.println("____没有数据更新，不调用接口");
			tempint = 1;
		}
//		else{
			//改成服务器调用
			ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
			EASWebservice easWebservice = (EASWebservice) context.getBean("client3");
			
			tempint = easWebservice.updateEASEmployees(employee);
//		}
			result.put("tempint",tempint);
			result.put("number", employee.size()); 
			return result;
	}
	
	/**
	 *   得到日志时间
	 * @param typeid
	 * @return
	 */
	private Map<String, Object> getDate(String begin,String end) { 
		Date dt = StringUtil.strToDate(end,"yyyy-MM-dd HH:mm:ss");//结束日期
		Date beginTime = StringUtil.strToDate(begin,"yyyy-MM-dd HH:mm:ss");//开始日期
		Map<String,Object> map  = new HashMap<String,Object>(); 
		map.put("endTime",dt);
		map.put("beginTime", beginTime); 
		return map;
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		IWSSMPPersonMapperHandService im = (IWSSMPPersonMapperHandService)context.getBean("wSSMPPersonMapperHandService");
	
		//1.异动
//		Map temp = im.queryFluctuation("2003-2-25 11:05:03", "2013-2-26 11:05:03");
//		System.out.println("__"+temp);
		
		//2.组织信息
//		Integer temp = im.orgAdminQuery("2012-12-26 11:05:03", "2013-2-26 11:05:03");
//		Map temp = im.orgAdminQuery(null,null);
//		System.out.println("__"+temp.get("tempint"));
		
		//3.职位信息
//		Map temp = im.orgPositionQuery("2013-2-26 11:05:03", "2013-7-26 11:05:03");
//		System.out.println("__"+temp);
		
		//4.职员
//		Map temp = im.queryPerson("2012-11-20 12:00:00", "2012-11-28 12:00:00");
//		System.out.println("__"+temp);
	}
}
