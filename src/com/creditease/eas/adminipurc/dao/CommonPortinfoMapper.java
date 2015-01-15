package com.creditease.eas.adminipurc.dao;
import java.util.List;
import java.util.Map;

import com.creditease.eas.util.BaseDAO;
import com.creditease.eas.util.commbean.CommonPortinfo;
/***
 * 接口人信息
 *CommonPortinfoMapper
 * @author admin 2014-5-22
 */
public interface CommonPortinfoMapper extends BaseDAO<CommonPortinfo, Long>{
	/**
	 * 查询是否已经添加了重复的接口人信息
	* @Title: findIfPortExists   
	* @param fportName
	* @param fportEmail
	* @return
	* @return CommonPortinfo
	 */
	public CommonPortinfo findIfPortExists(Map map);
	/***
	 * 批量添加接口人信息
	 * 由于需要在导入的时候对信息进行重复性验证，所以该方法暂时废弃掉
	 * @param list
	 * @return
	 */
//	public int insertAllPort(List<CommonPortinfo> list);
	/***
	 * 根据登陆用户的用户名，查询相关的权限信息
	* @Title: queryAuthorityByUsername
	*created at 2014-5-30 下午04:39:53 by ygq  
	* @param username
	* @return
	* @return List<Map<String,Object>>
	 */
	public List<Map<String,String>> queryAuthorityByUsername(String username);
	/***
	 * 查询采购组相关人员的权限
	* @Title: queryCaiGouAuthorityByUsername
	*created at 2014-6-1 下午04:57:31 by ygq  
	* @param username
	* @return
	* @return List<Map<String,String>>
	 */
	public List<Map<String,String>> queryCaiGouAuthorityByUserid(Long userid);
	
	/**
	 * 
	 * 描述：根据使用部门、末级成本中心、办公室坐落地址查询接口人邮箱
	 * 2014-5-29 下午06:16:14 by zhangxin
	 * @version
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> portemailToSend(Map map);

	/***
	 * 根据邮箱名,合同的部门编码，查询用户的id
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> queryPortInfoByEmail(Map map);
	/***
	 * 根据创建人id和登录人的邮箱查询对应的接口人信息
	 * @param map
	 * @return
	 */
	public String queryCreateidinfoByCreatorAndEmail(Map map);
	/***
	 * 将旧的接口人的fext1的信息更新为新的fext1的信息
	 * @param map
	 * @return
	 */
	public int updateFext1ByEmail(Map map);
	
	/**
	 * 
	 * 描述：查询行政采购房屋合同模块总接口人信息
	 * 2014-8-18 下午02:25:07 by zhangxin
	 * @version
	 * @return list
	 */
	public List<Map<String, Object>> allPortEmailToSend();

}
