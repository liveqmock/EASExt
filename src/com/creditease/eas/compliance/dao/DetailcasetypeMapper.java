package com.creditease.eas.compliance.dao;

import com.creditease.eas.compliance.bean.Detailcasetype;

public interface DetailcasetypeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_COM_DETAILCASETYPE
     *
     * @mbggenerated Sun Sep 29 19:10:18 CST 2013
     */
    int deleteByPrimaryKey(Long fid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_COM_DETAILCASETYPE
     *
     * @mbggenerated Sun Sep 29 19:10:18 CST 2013
     */
    int insert(Detailcasetype record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_COM_DETAILCASETYPE
     *
     * @mbggenerated Sun Sep 29 19:10:18 CST 2013
     */
    int insertSelective(Detailcasetype record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_COM_DETAILCASETYPE
     *
     * @mbggenerated Sun Sep 29 19:10:18 CST 2013
     */
    Detailcasetype selectByPrimaryKey(Long fid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_COM_DETAILCASETYPE
     *
     * @mbggenerated Sun Sep 29 19:10:18 CST 2013
     */
    int updateByPrimaryKeySelective(Detailcasetype record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_COM_DETAILCASETYPE
     *
     * @mbggenerated Sun Sep 29 19:10:18 CST 2013
     */
    int updateByPrimaryKey(Detailcasetype record);
}