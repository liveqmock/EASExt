package com.creditease.eas.adminipurc.dao;

import java.util.List;
import java.util.Map;
import com.creditease.eas.adminipurc.bean.AdminContractInfo;
import com.creditease.eas.adminipurc.vo.AdminContractInfoForExportExcelVo;
import com.creditease.eas.util.BaseDAO;
public interface AdminContractInfoMapper  extends BaseDAO<AdminContractInfo, Long>{
	/***
	 * 判断是否已经添加了重复的合同信息
	* @Title: findIfContractExists
	*created at 2014-5-25 下午05:21:17 by ygq  
	* @param map
	* @return
	* @return AdminContractInfo
	 */
	public AdminContractInfo findIfContractExists(Map map);
	/***
	 * 查询生成合同编号的流水号
	* @Title: findContractSerialNumber
	*created at 2014-5-25 下午05:33:10 by ygq  
	* @param contractFnumberPre
	* @return String
	 */
	public Integer findContractSerialNumber(String contractFnumberPre);
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_ADMINI_CONTRACTINFO
     *
     * @mbggenerated Sun May 25 16:30:10 CST 2014
     */
    int insertSelective(AdminContractInfo record);
    
    /**
	 *返回值为Map
	 * 2012-12-29 下午04:21:13 by ygq
	 * @version
	 * @param params
	 * @param entity
	 * @return
	 */
	public List<Map<String,Object>> queryPageMapByParams(Map params);
	
	/***
	 * 导出行政采购合同预警的相关信息
	* @Title: queryDatasetByParams
	*created at 2014-6-3 上午08:31:42 by ygq  
	* @param params
	* @return
	* @return List<AdminContractInfo>
	 */
	public List<AdminContractInfoForExportExcelVo> queryDatasetByParams(Map params);
	/**
	 * 
	 * 描述：查询所有合同信息
	 * 2014-5-28 下午04:54:20 by zhangxin
	 * @version
	 * @return
	 */
	public List<Map<String, Object>> queryInfoToMail();
	/***
	 * 将旧的接口人的fext1的信息更新为新的fext1的信息
	 * @param map
	 * @return
	 */
	public int updateFext1ByEmail(Map map);
}