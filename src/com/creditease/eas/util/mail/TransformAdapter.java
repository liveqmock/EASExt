package com.creditease.eas.util.mail;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import oracle.sql.TIMESTAMP;

import com.creditease.eas.util.DateUtil;
import com.creditease.eas.util.Utils;
import com.creditease.eas.util.consts.ConfigConst;
import com.creditease.eas.util.html.HTMLTable;
import com.creditease.eas.warn.vo.PersonData2;

/**
 * 用于转换预警邮件信息内容的生成方式
 * @author lining
 * @since 2014-5-20
 */
public class TransformAdapter {

	/**
	 * 根据预警内容中的人员信息动态生成表头和表体HTML代码
	 * @author lining
	 * @since 2014-5-20
	 * @param list
	 * @return
	 */
	public  String meterStaticToDynamic(String[] contents,Object... list){
		List<List<Map<String,Object>>> datas = new ArrayList<List<Map<String,Object>>>();
		for(int i = 0; i < list.length;i++){
			//转化兼容多种数据结构
			List personDatas = null;
			if(list[i] instanceof List){
				personDatas = (List)list[i];
			}else if(list[i] instanceof Set){
				Set dataSet = (Set)list[i];
				personDatas = new ArrayList(dataSet);
			}
			List<Map<String,Object>> persons = new ArrayList<Map<String,Object>>();
			for(Object personData:personDatas){
				Map<String,Object> person = new HashMap<String,Object>();
				if(personData instanceof PersonData2){
					PersonData2 p = (PersonData2)personData;
					person.put("员工编号", (null == p.getPid())?"":p.getPid());
					person.put("姓名", (null == p.getName())?"":p.getName());
					person.put("入职日期", (null == p.getEnterdate())?"":p.getEnterdate());
					person.putAll(this.splitOrgAdminDisplayname((null == p.getDisname())?"":p.getDisname()));
					person.put("服务年限", Utils.getInt((null == p.getYears())?"":p.getYears())+1);
					person.put("岗位", (null == p.getPosition())?"":p.getPosition());
				}else if(personData instanceof Map){
					Map<String,Object> p = (HashMap<String,Object>)personData;
					//封装员工编号
					if(p.containsKey("PERSONNUMBER")){
						person.put("员工编号", (null == p.get("PERSONNUMBER"))?"":p.get("PERSONNUMBER").toString());
					}else if(p.containsKey("PERSONCODE")){
						person.put("员工编号", (null == p.get("PERSONCODE"))?"":p.get("PERSONCODE").toString());
					}
					//封装员工姓名
					if(p.containsKey("NAME")){
						person.put("姓名", (null == p.get("NAME"))?"":p.get("NAME").toString());
					}else if(p.containsKey("PNAME")){
						person.put("姓名", (null == p.get("PNAME"))?"":p.get("PNAME").toString());
					}else if(p.containsKey("PERSONNAME")){
						person.put("姓名", (null == p.get("PERSONNAME"))?"":p.get("PERSONNAME").toString());
					}
					//封装入职日期
					if(p.containsKey("ENTERDATE")){
						if(null == p.get("ENTERDATE")){
							person.put("入职日期", "");
						}else{
							person.put("入职日期", DateUtil.formatTimestampToString((TIMESTAMP)p.get("ENTERDATE")));
						}
					}else if(p.containsKey("FENTERDATE")){
						if(null == p.get("FENTERDATE")){
							person.put("入职日期", "");
						}else{
							person.put("入职日期", DateUtil.formatTimestampToString((TIMESTAMP)p.get("FENTERDATE")));
						}
					}
					//封装部门数据
					if(p.containsKey("DISNAME")){
						person.putAll(this.splitOrgAdminDisplayname((null == p.get("DISNAME"))?"":p.get("DISNAME").toString()));
					}else if(p.containsKey("FDISPLAYNAME")){
						person.putAll(this.splitOrgAdminDisplayname((null == p.get("FDISPLAYNAME"))?"":p.get("FDISPLAYNAME").toString()));
					}
					//封装每种预警特有的字段组合
					if(p.containsKey("YEARS")){
						person.put("服务年限", (null == p.get("YEARS"))?"":Utils.getInt(p.get("YEARS").toString())+1);
						person.put("岗位", (null == p.get("POSITION"))?"":p.get("POSITION"));
					}else if(p.containsKey("ENDDATE")){
						person.put("劳动合同到期日", (null == p.get("ENDDATE"))?"":DateUtil.formatTimestampToString((TIMESTAMP)p.get("ENDDATE")));
						person.put("岗位", (null == p.get("POSITION"))?"":p.get("POSITION").toString());
						person.put("城市", (null == p.get("NPLACE"))?"":p.get("NPLACE").toString());
					}else if(p.containsKey("PROBZZ")){
						person.put("试用期到期日", (null == p.get("PROBZZ"))?"":DateUtil.formatDateToString((Date)p.get("PROBZZ")));
						person.put("岗位", (null == p.get("POSITIONNAME"))?"":p.get("POSITIONNAME").toString());
						person.put("人员状态", (null == p.get("EMPTYPE"))?"":p.get("EMPTYPE").toString());
					}else if(p.containsKey("BIRTHDAY")){
						person.put("出生日期", (null == p.get("BIRTHDAY"))?"":DateUtil.formatTimestampToString((TIMESTAMP)p.get("BIRTHDAY")));
					}
				}
				persons.add(person);
			}
			datas.add(persons);
		}
		//封装数据
		Map<String,Object> tableDatas = new HashMap<String,Object>();
		tableDatas.put("datas", datas);
		tableDatas.put("contents", contents);
		//生成HTML代码
		return HTMLTable.simpleHTMLTable(tableDatas,ConfigConst.HRWARN_COLUMN_SORT);
	}
	/**
	 * 将行政组织displayname切割为各级组织并添加组织别名
	 * @author lining
	 * @since 2014-5-21
	 * @param displayname
	 * @return
	 */
	private Map<String,String> splitOrgAdminDisplayname(String displayname){
		Map<String,String> orgAdminNames = new HashMap<String,String>();
		if(null != displayname){
			String[] names = displayname.split("_");
			for(int i = 2; i < names.length; i++){
				orgAdminNames.put(ConfigConst.HRWARN_COLUMN_SORT[i], names[i]);
			}
		}
		return orgAdminNames;
	}
	/**
	 * 匹配替换掉数据集合中的特殊人员信息
	 * @author lining
	 * @since 2014-5-20
	 * @param list
	 * @return
	 */
//	public  List<Map<String,Object>> matchSpecialPerson(List<Map<String,Object>> list){
//		
//		return null;
//	}


}
