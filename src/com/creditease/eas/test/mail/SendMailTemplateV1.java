package com.creditease.eas.test.mail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class SendMailTemplateV1 {
	/**
	 * 
	 * 描述：获取中心的部门名称
	 * 2013-1-10 下午02:45:08 by ygq
	 * @version
	 * @param fdisplayname
	 * @return
	 */
	public static String getDisplayMethod(Object fdisplayname){
		if(fdisplayname == null){
			return "";
		}
		String[] strs = fdisplayname.toString().split("_");
		if(strs.length>=3){
			return strs[2];
		}else if(strs.length==2){
			return strs[1];
		}else{
			return strs[0];
		}
	}
	public static String htmlContentForContract(List<Map<String,Object>> list){
		String htmlContent = "各位好！<br/>"+
		"根据《中华人民共和国劳动合同法》相关规定，用人单位与劳动者签订三年以上固定期限劳动合同，试用期不得超过六个月。<br/>"+
		"为避免公司用工风险、保护员工用工权益，特提醒部门务必于员工试用期到期日前15个工作日内、完成转正/解除劳动关系相关手续的办理。<br/>"+ 
		"以下为本团队即将转正人员名单，请查阅并参考邮件所附《员工转正审批流程》办理相关手续：<br/>";
		String html = "<div><table style='border-collapse: collapse;width:960px;' cellpadding='5' border='1'>" +
				"<tr bgcolor='#0066cc' align='center'><td height='20'><font color='white' size='2'>部门</font></td><td><font color='white' size='2'>员工姓名</font></td><td><font color='white' size='2'>员工编号</font></td><td><font color='white' size='2'>部门</font></td><td><font color='white' size='2'>城市</font></td><td><font color='white' size='2'>岗位</font></td><td><font color='white' size='2'>入职日期</font></td><td bgcolor='orange'><font color='white' size='2'>状态</font></td></tr>";
		for(int i =0;i<list.size();i++){
			Map<String,Object> mp = list.get(i);
			String personname = (String)mp.get("PERSONNAME");
			String personcode = (String)mp.get("PERSONCODE");
			String fdisplayname = getDisplayMethod(mp.get("FDISPLAYNAME"));
			String city = (mp.get("CITY")==null)?"":mp.get("CITY").toString();//地址
			String positionname = (String)mp.get("POSITIONNAME");
			//String fenterdate = DateUtil.formatTimestampToString((TIMESTAMP)mp.get("FENTERDATE"));
			String fenterdate = "2013-1-11";
			String deptname = (String)mp.get("DEPTNAME");
			String zhuanzheng = "";
			html += "<tr><td><font size='3'>" +fdisplayname+ "</font></td><td><font size='3'>" + personname + "</font></td><td><font size='3'>"+personcode+"</font></td><td><font size='3'>"+deptname+"</font></td><td><font size='3'>" +city+"</font></td>" +
					"<td><font size='3'>" +positionname + "</font></td><td><font size='3'>" +fenterdate+ "</font></td>" +
					"<td><font color='red'  size='3'>合同将到期</font></td></tr>";
		}
		html += "</table></div>";
		html += "附：员工转正审批流程：<br/>"+
			"1、各部门人事接口人收到提醒邮件后，立即梳理下月转正人员信息，提交本部门/团队负责人确认是否同意为员工办理转正手续；<br/>"+
			"2、由本部门/团队负责人根据员工实际工作情况进行评估、审批及办理后续手续：<br/>"+
			"▉  如部门同意员工转正：<br/>"+
			"&nbsp;&nbsp;&nbsp;▷员工直接主管安排与即将转正员工进行试用期面谈，双方达成一致后由员工本人填写《新员工转正评估表》并提交其上级予以审批；<br/>"+
			"&nbsp;&nbsp;&nbsp;▷ 员工直接主管、部门负责人完成签批手续后将附件直接提交人力资源与行政部（外埠地区将附件扫描件以邮件形式予以呈报）；<br/>"+
			"&nbsp;&nbsp;&nbsp;▷如该转正申请未通过公司级审批，HR将予以邮件反馈。<br/>"+
			"▉ 如部门不同意员工转正：请部门务必于试用期到期前与员工协商一致、并依据公司相关规定办理正式离职手续。<br/>"+
			"感谢您对人力资源与行政部工作的大力支持！ 谢谢。<br/>";
		htmlContent += html;
		return htmlContent;
	}
	public static String info(){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("PERSONNAME", "中华门");
		map.put("PERSONCODE", "12345");
		map.put("FDISPLAYNAME", "销售创新中心");
		map.put("CITY", "北京");
		map.put("POSITIONNAME", "技术部");
		map.put("FENTERDATE", "2013-1-1");
		map.put("DEPTNAME", "12345");
		list.add(map);
		String str = htmlContentForContract(list);
		return str;
	}
	public static void main(String[] args) {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("PERSONNAME", "中华门");
		map.put("PERSONCODE", "12345");
		map.put("FDISPLAYNAME", "销售创新中心");
		map.put("CITY", "北京");
		map.put("POSITIONNAME", "技术部");
		map.put("FENTERDATE", "2013-1-1");
		map.put("DEPTNAME", "12345");
		list.add(map);
		String str = htmlContentForContract(list);
		System.out.println(str);
	}
}
