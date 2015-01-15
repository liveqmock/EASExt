/**
 * 
 */
package com.creditease.eas.warn.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditease.eas.util.PageUtil;
import com.creditease.eas.util.Pagination;
import com.creditease.eas.warn.bean.ConfigInfo;
import com.creditease.eas.warn.dao.ConfigInfoMapper;
import com.creditease.eas.warn.service.ConfigService;

/**
 * @ConfigServiceImpl.java
 * created at 2013-3-28 上午09:37:01 by xjw
 * 
 * @author xjw({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
@Service
public class ConfigServiceImpl implements ConfigService {

	@Autowired
	private ConfigInfoMapper configInfoMapper;
	
	private ConfigInfo configInfo;
	
	
	public ConfigInfo getConfigInfo() {
		return configInfo;
	}

	public void setConfigInfo(ConfigInfo configInfo) {
		this.configInfo = configInfo;
	}

	/* (non-Javadoc)
	 * @see com.creditease.eas.warn.service.ConfigService#delete(java.lang.Integer)
	 */
	@Override
	public int delete(Integer id) throws Exception {
		int i = configInfoMapper.deleteByPrimaryKey(id);
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.creditease.eas.warn.service.ConfigService#getConfigInfoById(java.lang.Integer)
	 */
	@Override
	public ConfigInfo getConfigInfoById(Integer id) {
		configInfo = configInfoMapper.selectByPrimaryKey(id);
		return configInfo;
	}
	
	public int maxId(){
		return configInfoMapper.maxId();
	}

	/* (non-Javadoc)
	 * @see com.creditease.eas.warn.service.ConfigService#insert(com.creditease.eas.warn.bean.ConfigInfo)
	 */
	@Override
	public int insert(ConfigInfo configInfo) throws Exception {
		configInfoMapper.insert(configInfo);
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.creditease.eas.warn.service.ConfigService#queryPage(com.creditease.eas.util.Pagination)
	 */

	/* (non-Javadoc)
	 * @see com.creditease.eas.warn.service.ConfigService#totalCount()
	 */
	@Override
	public int totalCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.creditease.eas.warn.service.ConfigService#update(com.creditease.eas.warn.bean.ConfigInfo)
	 */
	@Override
	public int update(ConfigInfo configInfo) throws Exception {
		int i = configInfoMapper.updateByPrimaryKey(configInfo);
		return 0;
	}
	
	public Pagination queryPage(Pagination page) {
		HttpServletRequest request= ServletActionContext.getRequest();
		int currentPage = PageUtil.strToPage(request.getParameter("page"));
		int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
		//查询总行数的方法
		int totalCounts = configInfoMapper.getTotalCounts();
		page = new Pagination(currentPage,pageSize,totalCounts); 
		//查询数据集的方法
		Map map2 = PageUtil.getMap(page);
		List list = configInfoMapper.queryPage(map2);
		page.setRows(list);
		return page;
	}
	
	public ConfigInfo getConfigInfoByIsuse(Integer isuse) {
		configInfo = configInfoMapper.selectByIsuse(isuse);
		return configInfo;
	}

}
