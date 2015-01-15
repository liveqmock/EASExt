package com.creditease.eas.hr.dao;

import java.util.List;
import java.util.Map;

import oracle.sql.TIMESTAMP;

import com.creditease.eas.hr.bean.BaseLogRecord;
import com.creditease.eas.util.BaseDAO;
import com.creditease.eas.warn.bean.Person;
public interface BaseLogRecordMapper{
	
	int insert(BaseLogRecord baseLogRecord);
	//更新日志信息
	int updateByPrimaryKeySelective(BaseLogRecord baseLogRecord);
    //
    String proHello(Map map);
    /**
     * 
     * 描述：查询上次最大的记录时间
     * 2013-1-24 下午10:06:29 by ygq
     * @version
     * @return
     */
    Map<String,Object> selectLastMaxRecordTime(Map map);
    /**
     * 
     * 描述：
     * 2013-1-24 下午10:06:29 by ygq
     * @version
     * @return
     */
    List<Map<String,Object>> selectLogBase(Map map);
//    /**
//     * 
//     * 描述：
//     * 2013-1-24 下午10:06:29 by ygq
//     * @version
//     * @return
//     */
//    List<Map<String,Object>> insertOrganization(Map map);
//    /**
//     * 
//     * 描述：
//     * 2013-1-24 下午10:06:29 by ygq
//     * @version
//     * @return
//     */
//    List<Map<String,Object>> insertPositionLogInfo(List<Map<String,Object>> map);
//    /**
//     * 
//     * 描述：
//     * 2013-1-24 下午10:06:29 by ygq
//     * @version
//     * @return
//     */
//    List<Map<String,Object>> insertPersonLogInfo(List<Map<String,Object>> map);
//    
}