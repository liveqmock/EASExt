package com.creditease.eas.institutional.service;

import java.util.Map;

import com.creditease.eas.institutional.bean.LogBaserecordIns;
/**
 * 向制度平台推送人员数据的服务
 * @author lining
 * @since 
 */

public interface WSPersonInfoService {

	/**
	 * 初始化制度平台人员数据推送
	 * @return
	 * @throws Exception
	 */
	LogBaserecordIns initPersonToRP(Map<String,Object> paramMap)throws Exception;
	/**
	 * 新增制度平台人员数据推送
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	LogBaserecordIns addPersonToRP(Map<String,Object> paramMap)throws Exception;
	/**
	 * 更新制度平台人员数据推送
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	LogBaserecordIns updatePersonToRP(Map<String,Object> paramMap)throws Exception;
	/**
	 * 删除制度平台中无效人员数据
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	LogBaserecordIns removePersonFromRP(Map<String,Object> paramMap)throws Exception;
//	/**
//	 * 向用户群发送加入平台的提示邮件
//	 * @param paramMap
//	 * @return
//	 * @throws Exception
//	 */
//	LogBaserecordIns sendMailToPerson(Map<String,Object> paramMap) throws Exception;
	/**
	 * 查询人员兼职数据
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	LogBaserecordIns addPersonAvocationToRP(Map<String,Object> paramMap) throws Exception;

}
