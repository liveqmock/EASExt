package com.creditease.eas.warn.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.creditease.eas.util.BaseDAO;
import com.creditease.eas.warn.bean.SendPortInfo;

public interface SendPortInfoMapper extends BaseDAO<SendPortInfo, Integer>{
    int deleteByPrimaryKey(Integer id);
    int insert(SendPortInfo record);
    SendPortInfo selectByPrimaryKey(Integer id);
    int updateByPrimaryKey(SendPortInfo record);
    
    /**根据部门查询已经发送的房屋合同预警邮件：加@Param("别名")或者在Mapper映射文件的参数改成"_parameter" **/
    public List<SendPortInfo> allSendPortInfo(@Param("orgname")String orgname);
    
}