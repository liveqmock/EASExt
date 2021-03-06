package com.creditease.eas.warn.dao;

import com.creditease.eas.util.BaseDAO;
import com.creditease.eas.warn.bean.Jumbosmsv;


public interface JumbosmsvMapper extends BaseDAO<Jumbosmsv, Integer>{
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_JUMBOSMSV
     *
     * @mbggenerated Mon Dec 24 10:15:10 CST 2012
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_JUMBOSMSV
     *
     * @mbggenerated Mon Dec 24 10:15:10 CST 2012
     */
    int insertJumbosmsv(Jumbosmsv record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_JUMBOSMSV
     *
     * @mbggenerated Mon Dec 24 10:15:10 CST 2012
     */
    int insertSelective(Jumbosmsv record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_JUMBOSMSV
     *
     * @mbggenerated Mon Dec 24 10:15:10 CST 2012
     */
    Jumbosmsv selectByPrimaryKey(Integer id);

    Jumbosmsv selectByIsuse(Integer isuse);
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_JUMBOSMSV
     *
     * @mbggenerated Mon Dec 24 10:15:10 CST 2012
     */
    int updateByPrimaryKeySelective(Jumbosmsv record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table T_JUMBOSMSV
     *
     * @mbggenerated Mon Dec 24 10:15:10 CST 2012
     */
    int updateByPrimaryKey(Jumbosmsv record);
}