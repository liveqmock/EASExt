package com.creditease.eas.warn.kingdee.dao;

import java.util.List;
import java.util.Map;
/***
 * 改文件用于解决信管中心需求：
 * 将合同和转正的邮件信息发送给指定的接口人
 * @author ygq
 *
 */
public interface FuseTubeMapper {
	/**
	 * 查询要转正的人员的信息
	 * @return
	 */
	public List<Map<String,Object>> selectRegularPersonInfos(String deptCode);
	/**
	 * 查询合同快到期的人员的信息
	 * @return
	 */
	public List<Map<String,Object>> selectRenewalList(String deptCode);
}
