package com.creditease.eas.hr.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.creditease.eas.hr.service.INeiWangService;
import com.creditease.eas.util.BaseAction;
import com.google.gson.Gson;

/***
 * 给内网提供的数据接口
 * @author ygq
 * @version 1.0 2013/12/16 16:15
 */
@Controller
@Scope("prototype")
public class NeiWangAction extends BaseAction{
	
	@Resource
	private INeiWangService iNeiWangService;
	public void setiNeiWangService(INeiWangService iNeiWangService) {
		this.iNeiWangService = iNeiWangService;
	}
	/**
	 * 获取内网传过来的参数
	 */
	private String date;
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	/***
	 * 提供生日的数据信息
	 */
	public void birthdayList(){
		try {
			String json = iNeiWangService.selectedBirthdayList(date);
			writerJsonToClientEncode("var arrBirth=" + json);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/***
	 * 提供司龄的数据信息
	 */
	public void workPersonList(){
		try {
			String json =  iNeiWangService.selectedWorkPerson(date);
			writerJsonToClientEncode("var arrWork=" + json);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
//	public static void main(String[] args) {
//		Map map = new HashMap();
//		map.put("a","中国");
//		Gson gson = new Gson();
//		String strJson = gson.toJson(map);
//		System.out.println(strJson);
//	}
}
