package com.creditease.eas.accountr.kingdee.dao;

import java.util.List;
import java.util.Map;

public interface NetMapper {
    /**
     * 查询员工工资卡信息
     * @param listNumber
     * @return
     */
	List<Map<String, String>> selectCardInfo(List<String> listNumber);
    /**
     * 查询部门名称
     * @param deptNumber
     * @return
     */
	List<Map<String, String>> selectDeptInfo(List<String> deptNumber);
	/**
	 * 查询员工信息
	 * @param person
	 * @return
	 */
	List<Map<String, String>> selectPersonInfo(List<String> person);
	/**
	 * 查看科目名称
	 * @param accountNumber
	 * @return
	 */
	List<Map<String, String>> selectAccountInfo(List<String> accountNumber);

}
