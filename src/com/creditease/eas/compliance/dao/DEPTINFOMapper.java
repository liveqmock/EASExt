package com.creditease.eas.compliance.dao;

import java.util.List;

import com.creditease.eas.compliance.bean.DEPTINFO;

public interface DEPTINFOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_COM_DEPTINFO
     *
     * @mbggenerated Fri Nov 01 09:24:42 CST 2013
     */
    int insert(DEPTINFO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_COM_DEPTINFO
     *
     * @mbggenerated Fri Nov 01 09:24:42 CST 2013
     */
    int insertSelective(DEPTINFO record);

	List<DEPTINFO> selectDeptInfoByComplainId(int complainid);

	public void deleteDeptByComplainId(Integer complainid);
}