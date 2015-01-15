package com.creditease.eas.warn.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.creditease.eas.util.BaseDAO;
import com.creditease.eas.warn.bean.InterfacePerson;

public interface InterfacePersonMapper extends BaseDAO<InterfacePerson, Integer>{
    int deleteByPrimaryKey(Integer id);

    int insert(InterfacePerson record);

    int insertSelective(InterfacePerson record);

    InterfacePerson selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InterfacePerson record);

    int updateByPrimaryKey(InterfacePerson record);
    
    public List<Map<String, Object>> expire();
    
    public List<InterfacePerson> allorgmail();
    
    @SuppressWarnings("unchecked")
	public int selectPortByOfficeadd(Map map);
    
    @SuppressWarnings("unchecked")
	public int getTotalCounts(Map map);
    
    /**
     * 
     * 描述：根据部门获取收件人邮箱
     * 2013-9-2 下午03:40:07 by caoyong
     * @version
     * @param orgname
     * @return
     */
    public List<Map<String, String>> getEmailByOrgName(@Param("orgname")String orgname);
}