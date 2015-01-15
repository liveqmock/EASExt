package com.creditease.eas.warn.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.creditease.eas.util.BaseAction;
import com.creditease.eas.util.page.PageBean;
import com.creditease.eas.warn.bean.Jumbosmsv;
import com.creditease.eas.warn.bean.Person;
import com.creditease.eas.warn.service.PersonService;
@Controller
@Scope("prototype")
public class PersonAction extends BaseAction {
	@Autowired
	private PersonService personService;
	private Person person;
	private Jumbosmsv jumbosmsv;
	public Jumbosmsv getJumbosmsv() {
		return jumbosmsv;
	}
	public void setJumbosmsv(Jumbosmsv jumbosmsv) {
		this.jumbosmsv = jumbosmsv;
	}
	private PageBean page = new PageBean();
	public PageBean getPage() {
		return page;
	}
	public void setPage(PageBean page) {
		this.page = page;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public String toAdd() {
		return "add";
	}
	// 添加
	public String add() {
		//service.save(per);
		System.out.println("jump:" + jumbosmsv.getId() + "\t" + jumbosmsv.getAlterman() + "\t" + jumbosmsv.getName());
		return "success";
	}
//	// 转到修页面
//	public String toUpdate() {
//		per = service.findById(per.getFid());
//		return "update";
//	}
//	// 更新信息
//	public String update() {
//		Person temp = service.findById(per.getFid());
//		temp.setFid(per.getFid());
//		temp.setFname(per.getFname());
//		temp.setFgender(per.getFgender());
//		temp.setFbirthday(per.getFbirthday());
//		temp.setFcell(per.getFcell());
//		temp.setFaddress(per.getFaddress());
//		service.update(temp);
//		return "success";
//	}
	// 查询列表
	public String query() {
		//page = personService.queryPage(page);
		return "query";
	}
	// 删除信息
//	public String delete() {
//		service.delete(per.getFid());
//		return "success";
//	}
}
