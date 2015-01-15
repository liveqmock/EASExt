package com.creditease.eas.roster.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditease.eas.roster.dao.IRosterOrgAdminMapper;
import com.creditease.eas.roster.service.IRosterOrgAdminService;
import com.sun.org.apache.bcel.internal.generic.NEW;
/**
 * 投资理财三部组织结构service层实现类
 * @RosterOrgAdminServiceImpl.java
 * created at 2014-3-24 下午05:16:10 by zhangxin
 * 
 * @author zhangxin({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
@SuppressWarnings("unused")
@Service
public class RosterOrgAdminServiceImpl implements IRosterOrgAdminService{

	@Autowired
	private IRosterOrgAdminMapper orgadminMapper;
	private static List<Map<String, Object>> cacheOrgAdminlist = new ArrayList<Map<String, Object>>();//组织架构缓存
	
	/**
	 * 缓存中有组织架构数据时取缓存中的数据，没有重新查找
	 * 组织架构树只是为了方便带出三级、四级、五级、六级部门信息，不会变动
	 * 
	 */
	@Override
	public List<Map<String, Object>> selectAllOrgAdmin() {
		if(null != cacheOrgAdminlist && cacheOrgAdminlist.size()>0){
			return cacheOrgAdminlist;
		}else{
			cacheOrgAdminlist = orgadminMapper.selectAllOrgAdmin();
			return cacheOrgAdminlist;
		}
	}
	
	
	
	
	

}
