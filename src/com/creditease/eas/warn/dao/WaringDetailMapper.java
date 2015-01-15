package com.creditease.eas.warn.dao;

import java.util.List;
import java.util.Map;

import com.creditease.eas.util.BaseDAO;
import com.creditease.eas.warn.bean.WaringDetail;
import com.creditease.eas.warn.vo.QueryData;
import com.creditease.eas.warn.vo.QueryParameters;

public interface WaringDetailMapper extends BaseDAO<WaringDetail, Integer>{
	public int selectWaringdetail(Map map);
	public List waringDetaiList();
	public int insertWaringDetail(QueryData record);
	public List findAllWaring();
	public int deleteWaringDetailById(Long id);
	
	public List<Map<String,Object>> getAllPort();
	
	public String getPortByMail(String mail);
	
//	public Map<String,Map<String,Object>> getMapPort();
	
	public Map<String,Object> getMapPort();
	
	int updateByPrimaryKey(WaringDetail record);
	/**
	 * 根据预警类型和预警方式查询所有预警信息发送失败的记录
	 * @param param 预警类型和预警方式 
	 * @return 信息发送失败的记录ID
	 * @throws Exception
	 * @author lining
	 */
	public List<WaringDetail> selectAllByTypeIdAndWayIdOnIssend(QueryParameters param) throws Exception;
	
}