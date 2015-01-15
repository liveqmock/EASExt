package com.creditease.eas.util.mail;

import java.util.Date;
import java.util.List;
import java.util.Map;

import oracle.sql.TIMESTAMP;

import com.creditease.eas.util.DateUtil;
import com.creditease.eas.util.StringUtil;

/**
 * 预警公用的方法:这是最初的一个版本，在这个版本中存在一下的问题：
 * 1.发送的邮件中不能使用样式（因为只是一个String格式的字符串，而不是html)
 * 2.字符串的拼接使用的是String，而不是StringBuffer,效率会比较的低
 * @WarnCommonMethod.java
 * created at 2013-2-1 下午01:52:41 by ygq
 * 
 * @author ygq({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class HtmlContentCommonMethodV1 {
	/**
	 * 
	 * 描述：设置displayname
	 * 2013-2-1 上午10:12:49 by ygq
	 * @version
	 * @param strs
	 */
	public static String setDisplayName(Object fdisplayname){
		if(fdisplayname == null){
			return null;
		}
		String[] strs = fdisplayname.toString().split("_");
		StringBuffer sb = new StringBuffer();
		//为空的时候
		if(strs == null || strs.length == 0){
			for(int i=0;i<4;i++){
				sb.append("<td><font size='2'></font></td>");
			}
			return sb.toString();
		}
		//其他的情况:判断是否大于5级部门了
		int end = strs.length>6?6:strs.length;
		for(int i=1;i<end;i++){
			sb.append("<td><font size='2'>" +strs[i]+ "</font></td>");
		}
		//如果不到6级，就赋空值
		if(strs.length<6){
			for(int i=0;i<6-strs.length;i++){
				sb.append("<td><font size='2'></font></td>");
			}
		}
		return sb.toString();
	}
	/**
	 * 
	 * 描述：获得试用期到期人员的html信息(&nabla;向下的符号)
	 *<font style="font-family: 宋体;size=5">
	 * 2013-1-3 下午12:18:35 by ygq
	 * @version
	 * @param list
	 * @return
	 */
	public static String htmlContent(List<Map<String,Object>> list){
		String htmlContent = "<div style='line-height: 150%;font-family: 宋体;size=5;'>各位好！<br/>"+
		"根据《中华人民共和国劳动合同法》相关规定，用人单位与劳动者签订三年以上固定期限劳动合同，试用期不得超过六个月。<br/>"+
		"为避免公司用工风险、保护员工用工权益，特提醒部门务必于员工试用期到期日前15个工作日内、完成转正/解除劳动关系相关手续的办理。<br/>"+ 
		"以下为本团队即将转正人员名单，请查阅并参考邮件所附《员工转正审批流程》办理相关手续：<br/>";
		String html =  "<table style='border-collapse: collapse;width:960px;line-height:150%;font-family: 宋体;size=5;' cellpadding='5' border='1'>" +
				"<table style='border-collapse: collapse;width:960px;line-height:150%;font-family: 宋体;size=5;' cellpadding='5' border='1'>" +
				"<tr bgcolor='#0066cc' align='center'>" +
				"<td nowrap><font color='white' size='2'>员工编号</font></td>" +
				"<td nowrap><font color='white' size='2'>身份证姓名</font></td>" +
				"<td nowrap height='20'><font color='white' size='2'>一&nbsp;&nbsp;&nbsp;&nbsp;级&nbsp;&nbsp;&nbsp;&nbsp;部&nbsp;&nbsp;&nbsp;&nbsp;门</font></td>" +
				"<td nowrap height='20'><font color='white' size='2'>二&nbsp;&nbsp;级&nbsp;&nbsp;部&nbsp;&nbsp;门</font></td>" +
				"<td nowrap height='20'><font color='white' size='2'>三&nbsp;&nbsp;级&nbsp;&nbsp;部&nbsp;&nbsp;门</font></td>" +
				"<td nowrap height='20'><font color='white' size='2'>四 &nbsp;&nbsp;级&nbsp;&nbsp;部&nbsp;&nbsp;门</font></td>" +
				"<td nowrap height='20'><font color='white' size='2'>五&nbsp;&nbsp;级&nbsp;&nbsp;部&nbsp;&nbsp;门</font></td>" +
				"<td nowrap><font color='white' size='2'>城市</font></td>" +
				"<td nowrap><font color='white' size='2'>岗&nbsp;&nbsp;&nbsp;位</font></td>" +
				"<td nowrap><font color='white' size='2'>任职资格</font></td>" +
				"<td nowrap><font color='white' size='2'>人员状态</font></td>" +
				"<td nowrap><font color='white' size='2'>入&nbsp;职&nbsp;日&nbsp;期</font></td>" +
				"<td nowrap><font color='white' size='2'>预计转正日期</font></td>" +
				"<td nowrap bgcolor='orange'><font color='white' size='2'>预警类型</font></td>" +
				"</tr>";
		for(int i =0;i<list.size();i++){
			Map<String,Object> mp = list.get(i);
			String personcode = StringUtil.objToString(mp.get("PERSONCODE"));
			String personname = StringUtil.objToString(mp.get("PERSONNAME"));
			String city =StringUtil.objToString(mp.get("CITY"));//城市
			String positionname = StringUtil.objToString(mp.get("POSITIONNAME"));//岗位
			String pqfname = StringUtil.objToString(mp.get("PQFNAME"));//任职资格
			String emptype = StringUtil.objToString(mp.get("EMPTYPE"));//人员状态
			String fenterdate = DateUtil.formatTimestampToString((TIMESTAMP)mp.get("FENTERDATE"));//入职日期
			String probzz = DateUtil.formatDateToString((Date)mp.get("PROBZZ"));//预计转正日期
			//int entmonth = DateUtil.monthsBetween(fenterdate);
			html += "<tr>" +
			"<td><font size='2'>"+personcode+"</font></td>" +
			"<td><font size='2'>" + personname + "</font></td>";
			//设置displayName
			html += setDisplayName(mp.get("FDISPLAYNAME"));
			html +=	"<td><font size='2'>" +city+"</font></td>" +
					"<td><font size='2'>" +positionname + "</font></td>" +
					"<td><font size='2'>" +pqfname + "</font></td>" +
					"<td><font size='2'>"+emptype + "</font></td>"+
					"<td><font size='2'>" +fenterdate+ "</font></td>"+
					"<td><font size='2'>" +probzz+ "</font></td>"+
					"<td><font size='2'>转正预警</font></td>";
		}
		html += "</tr></table>";
		html += "附：员工转正审批流程：<br/>"+ 
		"&nbsp;&nbsp;&nbsp;&nbsp;1、 主管级（含）以下员工<br/>"+ 
		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;由部门根据员工日常工作表现进行评估、审批，并根据审批结果进行相关手续办理：<br/>"+ 
		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;▉  如部门同意员工转正：<br/>"+ 
		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;a.员工直接上级与即将转正员工进行试用期面谈，双方达成一致后由员工本人填写《新员工转正评估表》并提交部门审批；<br/>"+ 
		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;b.部门完成审批手续后将《新员工转正评估表》提交总部人力资源与行政部备案（外埠地区将表单以扫描件形式予以呈报）。<br/><br/>"+ 
		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;▉  如部门不同意员工转正<br/>"+ 
		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;a.请部门务必于试用期到期前与员工协商一致、依据公司相关规定办理正常离职手续。<br/><br/>"+ 
		"&nbsp;&nbsp;&nbsp;&nbsp;2、 经理级以上员工<br/>"+ 
		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;由部门根据员工实际工作表现进行评估、审核，并根据审核结果进行相关手续办理：<br/>"+ 
		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;▉  如部门同意员工转正：<br/>"+ 
		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;a.员工直接上级与即将转正员工进行试用期面谈，双方达成一致后由员工本人填写《新员工转正评估表》并提交部门审核；<br/>"+ 
		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;b.部门完成审核手续后将《新员工转正评估表》提交总部人力资源与行政部审批（外埠地区将表单以扫描件形式予以呈报）；<br/>"+ 
		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;c.如该转正申请未通过审批，总部人力资源与行政部将予以邮件反馈。<br/><br/>"+ 
		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;▉  如部门不同意员工转正<br/>"+ 
		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;a.部门将不同意员工转正的意见及相关材料提交总部人力资源与行政部审批（外埠地区将表单以扫描件形式予以呈报）；<br/>"+ 
		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;b.根据员工实际情况进行相关手续办理。<br/><br/>"+ 
		"感谢您对人力资源与行政部工作的大力支持！谢谢。";
		htmlContent += html;
		htmlContent += "</div>";
		return htmlContent;
	}
}
