package com.creditease.eas.warn.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditease.eas.util.StringUtil;
import com.creditease.eas.warn.bean.SendPortInfo;
import com.creditease.eas.warn.bean.WaringDetailView;
import com.creditease.eas.warn.dao.SendPortInfoMapper;
import com.creditease.eas.warn.dao.WaringDetailViewMapper;
import com.creditease.eas.warn.service.UploadService;
@Service
public class UploadServiceImpl implements UploadService{
	@Autowired
	private WaringDetailViewMapper waringDetailViewMapper;
	
	@Autowired
	private SendPortInfoMapper sendPortInfoMapper;
	
	/**
	 * 
	 * 描述：设置公共的参数
	 * 2012-12-31 下午06:08:52 by ygq
	 * @version
	 * @param request
	 * @param map
	 * @return
	 */
	private Map setMapValue() throws Exception{
		HttpServletRequest request= ServletActionContext.getRequest();
		Date begin = StringUtil.strToDate(request.getParameter("begin"));
		Date end = StringUtil.strToDate(request.getParameter("end"));
		String receiver = "";//添加
		String str = request.getParameter("receiver");
		if(null != str && !"".equals(str)){
			receiver =  StringUtil.addLikeOperBoth(str);
		}
		String departname =  StringUtil.addLikeOperBoth(request.getParameter("departname"));
		String postname = request.getParameter("postname");
		String theme = StringUtil.addLikeOperBoth(request.getParameter("theme"));
		String typeid = request.getParameter("typeid");
		String wayid = request.getParameter("wayid");
		Map map = new HashMap();
		map.put("begin", begin);
		map.put("end", end);
		map.put("receiver", receiver);
		map.put("departname", departname);
		map.put("postname", postname);
		map.put("theme", theme);
		map.put("typeid",typeid);
		map.put("wayid",wayid);
		return map;
	}
	@Override
	public List<WaringDetailView> queryDatasetByParams() throws Exception{
		//查询数据集的方法
		Map map = setMapValue();
		List list = waringDetailViewMapper.queryByParams(map);
		return list;
	}
	
	@Override
	public List<SendPortInfo> queryDataset(String orgname) throws Exception{
		List<SendPortInfo> list = sendPortInfoMapper.allSendPortInfo(orgname);
		return list;
	}
}
