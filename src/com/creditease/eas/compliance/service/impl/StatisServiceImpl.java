package com.creditease.eas.compliance.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditease.eas.compliance.dao.StatisMapper;
import com.creditease.eas.compliance.service.StatisService;
import com.creditease.eas.util.PageUtil;
import com.creditease.eas.util.Pagination;
/**
 * 违规统计实现类
 * @StatisServiceImpl.java
 * created at 2013-11-5 下午03:25:30 by zhangxin
 * 
 * @author zhangxin({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
@Service
public class StatisServiceImpl implements StatisService{
	
	@Autowired
	private StatisMapper statisMapper;
	
	@SuppressWarnings("unchecked")
	@Override
	public Pagination queryPage(Pagination page) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		int currentPage = PageUtil.strToPage(request.getParameter("page"));
		int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
		Map map = HashMap.class.newInstance();
		//页面复选框的类型集合
		List<String> typeList = new ArrayList<String>();
		typeList.add("qizhaCount>0");//欺诈伪冒类
		typeList.add("weichengxinCount>0");//违背诚信类
		typeList.add("huiluCount>0");//商业贿赂类
		typeList.add("baomiCount>0");//信息保密类
		typeList.add("fuwuCount>0");//服务类
		typeList.add("otherCount>0");//其他
		
		//获得页面复选框的值
		String checkValue = request.getParameter("checkValue");
		if(checkValue != null && checkValue !=""){
			//将页面选择数据用逗号分隔
			String[] checkValue2 = checkValue.split(",");
			List<String> changeList = Arrays.asList(checkValue2);
			//页面未选择的类型
			for(String str:changeList){
				if(typeList.contains(str)){
					typeList.remove(str);
				}
			}
			//将未选择的案件类型>换为=号
			List nochecktypeList = new ArrayList();
			for(int j=0;j<typeList.size();j++){
				nochecktypeList.add(typeList.get(j).toString().replace(">", "="));
			}
			String manytypecount="";
			//拼接页面选择案件类型
			for(int i=0;i<checkValue2.length;i++){
				manytypecount = checkValue2[i]+" and "+manytypecount;
			}
			//拼接页面未选择的案件类型
			for(int n=0;n<nochecktypeList.size();n++){
				manytypecount = nochecktypeList.get(n)+" and "+manytypecount;
			}
			//最终拼接案件类型字段
			manytypecount = manytypecount.substring(0,manytypecount.length()-4);
			map.put("manytypecount",manytypecount);
		}
		map.put("fcityname", request.getParameter("fcityname"));
		map.put("fdeptname", request.getParameter("fdeptname"));
		//查询总行数的方法
		int totalCounts = statisMapper.getTotalCountsByParams(map);
		page = new Pagination(currentPage, pageSize, totalCounts);
		// 查询数据集的方法
		Map map1 = PageUtil.getMap(page);
		map1.putAll(map);
		List list = statisMapper.queryPage(map1);
		page.setRows(list);
		return page;
		
	}


}
