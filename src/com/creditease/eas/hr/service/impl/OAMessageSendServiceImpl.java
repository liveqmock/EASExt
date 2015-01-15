package com.creditease.eas.hr.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebParam;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.creditease.eas.hr.bean.extoa.WSOAOrganization;
import com.creditease.eas.hr.bean.extoa.WSOAPerson;
import com.creditease.eas.hr.bean.extoa.WSOAPluralityPerson;
import com.creditease.eas.hr.bean.extoa.WSOAPosition;
import com.creditease.eas.hr.kingdee.query.OAMessageChangeQuery;
import com.creditease.eas.hr.service.IOAMessageSendService;
import com.creditease.eas.util.StringUtil;

@Service("oAMessageSendServiceImpl")
public class OAMessageSendServiceImpl implements IOAMessageSendService{
	private OAMessageChangeQuery oaMessageChange = new OAMessageChangeQuery();
	@Override
	public String queryOrganizationChangeFromHRToOA(int startRow,int endRow,String fnumber,String beginTime,String endTime,String fname,String fid) {
		Date dateBeginTime = StringUtil.strToDate(beginTime, "yyyy-MM-dd HH:mm:ss");
		Date dateEndTime = StringUtil.strToDate(endTime, "yyyy-MM-dd HH:mm:ss");
		Map<String,Object> map = new HashMap<String,Object>();
		//这个需要考虑最大页数的查询 
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		map.put("fnumber", fnumber);
		map.put("beginTime", dateBeginTime);
		map.put("endTime", dateEndTime);
		map.put("fname", StringUtil.addLikeOperBoth(fname));//名字支持模糊查询
		map.put("fid", fid);
		List<Map<String,Object>> listOrg = oaMessageChange.selectListChangeOrgInfo(map);
		return  StringUtil.convertListToGson(listOrg);
	}
	@Override
	public String queryCostcenterChangeInfo(int startRow,int endRow,String fnumber,String beginTime,String endTime,String fname,String fid) {
		Date dateBeginTime = StringUtil.strToDate(beginTime, "yyyy-MM-dd HH:mm:ss");
		Date dateEndTime = StringUtil.strToDate(endTime, "yyyy-MM-dd HH:mm:ss");
		Map<String,Object> map = new HashMap<String,Object>();
		//这个需要考虑最大页数的查询 
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		map.put("fnumber", fnumber);
		map.put("beginTime", dateBeginTime);
		map.put("endTime", dateEndTime);
		map.put("fname", StringUtil.addLikeOperBoth(fname));//名字支持模糊查询
		map.put("fid", fid);
		List<Map<String,Object>> listOrg = oaMessageChange.selectListChangeCostcenterInfo(map);
		return  StringUtil.convertListToGson(listOrg);
	}
	@Override
	public String queryResponPositionInfo(String beginTime, String endTime,
			String forgId,String fOrgnumber) {
		Date dateBeginTime = StringUtil.strToDate(beginTime, "yyyy-MM-dd HH:mm:ss");
		Date dateEndTime = StringUtil.strToDate(endTime, "yyyy-MM-dd HH:mm:ss");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("beginTime", dateBeginTime);
		map.put("endTime", dateEndTime);
		map.put("forgId",forgId);
		map.put("fOrgnumber",fOrgnumber);
		List<Map<String,Object>> list = oaMessageChange.queryResponPositionInfo(map);
		String str = StringUtil.convertListToGson(list);
		return str;
	}
	public String queryPositionInfoChangeFromHRToOA(int startRow,int endRow,String fnumber,String beginTime,String endTime,String fname,String fid) {
		Date dateBeginTime = StringUtil.strToDate(beginTime, "yyyy-MM-dd HH:mm:ss");
		Date dateEndTime = StringUtil.strToDate(endTime, "yyyy-MM-dd HH:mm:ss");
		Map<String,Object> map = new HashMap<String,Object>();
		//这个需要考虑最大页数的查询
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		map.put("fnumber", fnumber);
		map.put("beginTime", dateBeginTime);
		map.put("endTime", dateEndTime);
		map.put("fname", StringUtil.addLikeOperBoth(fname));//名字支持模糊查询
		map.put("fid", fid);
		List<Map<String,Object>> list = oaMessageChange.selectChangePositionInfo(map);
		String str = StringUtil.convertListToGson(list);
		return str;
	}

	public String queryEmpInfoChangeFromHRToOA(int startRow,int endRow,String fnumber,
			String beginTime, String endTime,String fname,String fid) {
		Date dateBeginTime = StringUtil.strToDate(beginTime, "yyyy-MM-dd HH:mm:ss");
		Date dateEndTime = StringUtil.strToDate(endTime, "yyyy-MM-dd HH:mm:ss");
		Map<String,Object> map = new HashMap<String,Object>();
		//这个需要考虑最大页数的查询
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		map.put("fnumber", fnumber);
		map.put("beginTime", dateBeginTime);
		map.put("endTime", dateEndTime);
		map.put("fname", StringUtil.addLikeOperBoth(fname));//名字支持模糊查询
		map.put("fid", fid);
		List<Map<String,Object>> list = oaMessageChange.selectChangePersonInfo(map);
		String str = StringUtil.convertListToGson(list);
		return str;
	}
	@Override
	public String queryChangeContractInfo(int startRow, int endRow,
			String beginTime, String endTime, String fpersoncode, String fpersonid) {
		Date dateBeginTime = StringUtil.strToDate(beginTime, "yyyy-MM-dd HH:mm:ss");
		Date dateEndTime = StringUtil.strToDate(endTime, "yyyy-MM-dd HH:mm:ss");
		Map<String,Object> map = new HashMap<String,Object>();
		//这个需要考虑最大页数的查询
		map.put("min", startRow);
		map.put("max", endRow);
		map.put("beginTime", dateBeginTime);
		map.put("endTime", dateEndTime);
		map.put("fpersoncode", fpersoncode);
		map.put("fpersonid", fpersonid);
		List<Map<String,Object>> list = oaMessageChange.selectContractInfo(map);
		String str = StringUtil.convertListToGson(list);
		return str;
	}
	@Override
	public String queryPersonPositionTempInfo(int startRow, int endRow,
			String beginTime, String endTime, String fpersoncode,
			String fpersonid, String fpositioncode, String fpositionid) {
		Date dateBeginTime = StringUtil.strToDate(beginTime, "yyyy-MM-dd HH:mm:ss");
		Date dateEndTime = StringUtil.strToDate(endTime, "yyyy-MM-dd HH:mm:ss");
		Map<String,Object> map = new HashMap<String,Object>();
		//这个需要考虑最大页数的查询
		map.put("min", startRow);
		map.put("max", endRow);
		map.put("beginTime", dateBeginTime);
		map.put("endTime", dateEndTime);
		map.put("fpersoncode", fpersoncode);
		map.put("fpersonid", fpersonid);
		map.put("fpositioncode", fpositioncode);
		map.put("fpositionid", fpositionid);
		List<Map<String,Object>> list = oaMessageChange.queryPersonPositionTempInfo(map);
		String str = StringUtil.convertListToGson(list);
		return str;
	}
	/* (non-Javadoc)
	 * @see com.creditease.eas.hr.service.IOAMessageSendService#queryWorkExpCur(int, int)
	 */
	@Override
	public String queryWorkExpCur(int startRow, int endRow,String beginTime,String endTime,String fpersoncode) {
		Date dateBeginTime = StringUtil.strToDate(beginTime, "yyyy-MM-dd HH:mm:ss");
		Date dateEndTime = StringUtil.strToDate(endTime, "yyyy-MM-dd HH:mm:ss");
		Map<String,Object> map = new HashMap<String,Object>();
		//这个需要考虑最大页数的查询
		map.put("min", startRow);
		map.put("max", endRow);
		map.put("beginTime", dateBeginTime);
		map.put("endTime", dateEndTime);
		map.put("fpersoncode", fpersoncode);
		List<Map<String,Object>> list = oaMessageChange.queryWorkExpCur(map);
		String str = StringUtil.convertListToGson(list);
		return str;
	}
}
