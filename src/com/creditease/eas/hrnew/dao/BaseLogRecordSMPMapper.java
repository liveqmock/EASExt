package com.creditease.eas.hrnew.dao;

import java.util.Map;

import com.creditease.eas.hrnew.bean.BaseLogRecordSMP;



public interface BaseLogRecordSMPMapper {
	
	/**
	 * 描述：查询上次最大的记录时间
	 * @param map
	 * @return
	 */
	 Map<String,Object> selectLastMaxRecordTime(Map map);
	
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_LOG_BASERECORD_SMP
     *
     * @mbggenerated Wed Jul 03 16:06:54 CST 2013
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_LOG_BASERECORD_SMP
     *
     * @mbggenerated Wed Jul 03 16:06:54 CST 2013
     */
    int insert(BaseLogRecordSMP record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_LOG_BASERECORD_SMP
     *
     * @mbggenerated Wed Jul 03 16:06:54 CST 2013
     */
    int insertSelective(BaseLogRecordSMP record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_LOG_BASERECORD_SMP
     *
     * @mbggenerated Wed Jul 03 16:06:54 CST 2013
     */
    BaseLogRecordSMP selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_LOG_BASERECORD_SMP
     *
     * @mbggenerated Wed Jul 03 16:06:54 CST 2013
     */
    int updateByPrimaryKeySelective(BaseLogRecordSMP record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_LOG_BASERECORD_SMP
     *
     * @mbggenerated Wed Jul 03 16:06:54 CST 2013
     */
    int updateByPrimaryKey(BaseLogRecordSMP record);
}