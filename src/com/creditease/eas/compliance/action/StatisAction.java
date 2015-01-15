package com.creditease.eas.compliance.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.creditease.eas.compliance.service.StatisService;
import com.creditease.eas.util.BaseAction;
@Controller
@Scope("prototype")
public class StatisAction extends BaseAction{
	//自动装配违规统计service接口
	@Autowired
	private StatisService statisServiceImpl;
	
	
	/**
	 * 
	 * 描述：查询违规数据列表页
	 * 2013-11-6 下午01:48:27 by zhangxin
	 * @version
	 * @return
	 */
	public String queryPageJson(){
		try {
			this.pagination = statisServiceImpl.queryPage(pagination);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "queryPageJson";
	}
	
}
