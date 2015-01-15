package com.creditease.eas.hr.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.creditease.eas.hr.service.IMicropayService;
import com.creditease.eas.util.BaseAction;

/**
 * 小微接口
 * @author hejw
 *
 */
@Controller
@Scope("prototype")
public class MicropayAction extends BaseAction{
	
	@Resource
	private IMicropayService micropayService;
	public void setMicropayService(IMicropayService micropayService) {
		this.micropayService = micropayService;
	}
	/**
	 * 小微传过来的员工编号
	 */
	private String fnumber;
	
	public String getFnumber() {
		return fnumber;
	}
	public void setFnumber(String fnumber) {
		this.fnumber = fnumber;
	}
	/***
	 * 提供员工信息
	 */
	public void queryEmployeeInfo(){
		try {
			String json = micropayService.queryEmployeeInfo(fnumber);
			writerJsonToClientEncode(json);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
