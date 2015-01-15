package com.creditease.eas.util.html;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.creditease.eas.util.consts.ConfigConst;

/**
 * 动态Table生成器测试类
 * @author lining
 * @since 2014-5-22
 */
public class HTMLTableDemo {

	public static void main(String[] args) {
		Map<String,Object> person = new HashMap<String,Object>();
		person.put("员工编号", "000000000000");
		person.put("姓名", "张三");
		person.put("一级部门", "销售创新中心");
		person.put("岗位", "总经理");
		person.put("入职日期", "2014-5-3");
		Map<String,Object> person1 = new HashMap<String,Object>();
		person1.put("员工编号", "111111111111");
		person1.put("姓名", "李四");
		person1.put("一级部门", "销售创新中心");
		person1.put("二级部门", "企业合作中心");
		person1.put("岗位", "总经理");
		person1.put("入职日期", "2014-5-3");
		Map<String,Object> person2 = new HashMap<String,Object>();
		person2.put("员工编号", "222222222222");
		person2.put("姓名", "王五");
		person2.put("一级部门", "销售创新中心");
		person2.put("二级部门", "企业合作中心");
		person2.put("三级部门", "个贷中心");
		person2.put("岗位", "总经理");
		person2.put("入职日期", "2014-5-3");
		List<Map<String,Object>> persons = new ArrayList<Map<String,Object>>();
		persons.add(person);
		persons.add(person1);
		List<Map<String,Object>> persons1 = new ArrayList<Map<String,Object>>();
		persons1.add(person2);
		List<List<Map<String,Object>>> datas = new ArrayList<List<Map<String,Object>>>();
		datas.add(persons);
		datas.add(persons1);
		Map<String,Object> tableDatas = new HashMap<String,Object>();
		tableDatas.put("datas", datas);
		tableDatas.put("contents", new String[]{"直接下级","间接下级"});
		System.out.println(HTMLTable.simpleHTMLTable(tableDatas,null));
	}
}
