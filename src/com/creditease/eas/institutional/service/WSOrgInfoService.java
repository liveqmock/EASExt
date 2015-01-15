package com.creditease.eas.institutional.service;

import java.util.Map;

import com.creditease.eas.institutional.bean.LogBaserecordIns;

/**
 * 向制度平台推送组织信息服务
 * @author lining
 *
 */
public interface WSOrgInfoService {

	/**
	 * 初始化制度平台组织数据推送
	 * @return
	 * @throws Exception
	 */
	LogBaserecordIns initOrgToRP(Map<String,Object> paramMap)throws Exception;
	/**
	 * 新增制度平台组织数据推送
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	LogBaserecordIns addOrgToRP(Map<String,Object> paramMap)throws Exception;
	/**
	 * 更新制度平台组织数据推送
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	LogBaserecordIns updateOrgToRP(Map<String,Object> paramMap)throws Exception;
	/**
	 * 删除制度平台中无效组织数据
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	LogBaserecordIns removeOrgFromRP(Map<String,Object> paramMap)throws Exception;

}
