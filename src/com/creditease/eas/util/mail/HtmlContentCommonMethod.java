package com.creditease.eas.util.mail;

import java.util.List;
import java.util.Map;

/**
 * 预警公用的方法
 * @WarnCommonMethod.java
 * created at 2013-2-1 下午01:52:41 by ygq
 * 
 * @author ygq({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class HtmlContentCommonMethod {
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
	 * 2013-1-3 下午12:18:35 by ygq
	 * @version
	 * @param list
	 * @return
	 * style="line-height: 500%;font-family: 宋体;font-size: 五号;"
	 */
	public static String htmlContent(List<Map<String,Object>> list){
		StringBuffer htmlContent = new StringBuffer();
		htmlContent.append("<html xmlns:v='urn:schemas-microsoft-com:vml' xmlns:o='urn:schemas-microsoft-com:office:office' xmlns:w='urn:schemas-microsoft-com:office:word'xmlns:m='http://schemas.microsoft.com/office/2004/12/omml' xmlns='http://www.w3.org/TR/REC-html40'>");
		htmlContent.append("<head>");
		htmlContent.append("<meta http-equiv=Content-Type content='text/html; charset=utf-8'><meta name=Generator content='Microsoft Word 14 (filtered medium)'>");
		htmlContent.append("<style>");
		htmlContent.append("<!-- /* Font Definitions */ @font-face {font-family:宋体;panose-1:2 1 6 0 3 1 1 1 1 1;} @font-face {font-family:宋体;panose-1:2 1 6 0 3 1 1 1 1 1;} @font-face {font-family:Calibri;panose-1:2 15 5 2 2 2 4 3 2 4;} @font-face {font-family:'\\@宋体';panose-1:2 1 6 0 3 1 1 1 1 1;}");
		htmlContent.append("/* Style Definitions */ p.MsoNormal, li.MsoNormal, div.MsoNormal {margin:0cm;margin-bottom:.0001pt;font-size:12.0pt;font-family:宋体;} a:link, span.MsoHyperlink {mso-style-priority:99;color:blue;text-decoration:underline;} a:visited, span.MsoHyperlinkFollowed {mso-style-priority:99;color:purple;text-decoration:underline;} p.MsoAcetate, li.MsoAcetate, div.MsoAcetate {mso-style-priority:99;mso-style-link:'批注框文本 Char';margin:0cm;margin-bottom:.0001pt;font-size:9.0pt;font-family:宋体;}");
		htmlContent.append("p.div28, li.div28, div.div28 {mso-style-name:div28;mso-margin-top-alt:auto;margin-right:0cm;mso-margin-bottom-alt:auto;	margin-left:21.0pt;font-size:10.5pt;font-family:宋体;} p.div50, li.div50, div.div50 {mso-style-name:div50;mso-margin-top-alt:auto;margin-right:0cm;mso-margin-bottom-alt:auto;margin-left:37.5pt;font-size:10.5pt;font-family:宋体;} p.div72, li.div72, div.div72 {mso-style-name:div72;mso-margin-top-alt:auto;margin-right:0cm;mso-margin-bottom-alt:auto;margin-left:54.0pt;font-size:10.5pt;font-family:宋体;}");
		htmlContent.append("span.Char {mso-style-name:'批注框文本 Char';mso-style-priority:99;mso-style-link:批注框文本;font-family:宋体;} span.EmailStyle22 {mso-style-type:personal-reply;font-family:'Calibri','sans-serif';color:#1F497D;} .MsoChpDefault{mso-style-type:export-only;font-family:'Calibri','sans-serif';} @page WordSection1 {size:612.0pt 792.0pt;margin:72.0pt 90.0pt 72.0pt 90.0pt;} div.WordSection1 {page:WordSection1;}-->");
		htmlContent.append("</style>");
		htmlContent.append("</head>");
		htmlContent.append("<body lang=ZH-CN link=blue vlink=purple><div class=WordSection1><div>");
		htmlContent.append("<div><p class=MsoNormal style='line-height:150%'><span style='font-size:10.0pt;line-height:150%'>尊敬的领导：<span lang=EN-US><o:p></o:p></span></span></p></div>");
		htmlContent.append("<div style='margin-left:21.0pt'><p class=MsoNormal><span style='font-size:10.0pt'>您好！所列员工的试用期即将期满，请查阅并参考邮件所附《员工转正审批流程》办理相关手续<span lang=EN-US><o:p></o:p></span></span></p></div>");
		htmlContent.append("<div style='margin-left:21.0pt'><p class=MsoNormal><span style='font-size:10.0pt'>根据《中华人民共和国劳动合同法》相关规定，用人单位与劳动者签订三年以上固定期限劳动合同的，试用期不得超过六个月<span lang=EN-US><o:p></o:p></span></span></p></div>");
		htmlContent.append("<div style='margin-left:21.0pt'><p class=MsoNormal><span style='font-size:10.0pt'>为避免公司用工风险、维护员工劳动权益，特提醒部门务必于员工试用期到期日前<span lang=EN-US>15</span>日内，完成转正或解除劳动关系相关手续的办理<span lang=EN-US><o:p></o:p></span></span></p></div>");
		htmlContent.append("<p class=MsoNormal><span lang=EN-US><o:p>&nbsp;</o:p></span></p>");
		htmlContent.append("<div style='margin-left:21.0pt'>");
		/*
		 * 新生成动态Table
		 */
		TransformAdapter adapter = new TransformAdapter();
		String htmlTable = "";
		if(null != list && 0 < list.size()){
			htmlTable = adapter.meterStaticToDynamic(null,list);
		}
		htmlContent.append(htmlTable);
		
		
		/*
		 * 原静态Table
		 * StringBuffer html = new StringBuffer();
		html.append("<table style='border-collapse: collapse;width:960px;line-height:150%;font-family: 宋体;size=5;' cellpadding='5' border='1'>");
		html.append("<tr bgcolor='#0066cc' align='center'>");
		html.append("<td><font color='white' size='2'>员工编号</font></td>");
		html.append("<td><font color='white' size='2'>身份证姓名</font></td>");
		html.append("<td height='20'><font color='white' size='2'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;一级部门&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font></td>");
		html.append("<td height='20'><font color='white' size='2'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;二级部门&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font></td>");
		html.append("<td height='20'><font color='white' size='2'>&nbsp;&nbsp;&nbsp;三级部门&nbsp;&nbsp;&nbsp;</font></td>");
		html.append("<td height='20'><font color='white' size='2'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;四级部门&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font></td>");
		html.append("<td height='20'><font color='white' size='2'>&nbsp;&nbsp;&nbsp;五级部门&nbsp;&nbsp;&nbsp;</font></td>");
		html.append("<td><font color='white' size='2'>城市</font></td>");
		html.append("<td><font color='white' size='2'>岗位</font></td>");
		html.append("<td><font color='white' size='2'>任职资格</font></td>");
		html.append("<td><font color='white' size='2'>人员状态</font></td>");
		html.append("<td><font color='white' size='2'>入职日期</font></td>");
		html.append("<td><font color='white' size='2'>预计转正日期</font></td>");
		html.append("<td bgcolor='orange'><font color='white' size='2'>预警类型</font></td>");
		html.append("</tr>");
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
			int entmonth = DateUtil.monthsBetween(fenterdate);
			html.append("<tr align='center'>");
			html.append("<td><font size='2'>"+personcode+"</font></td>");
			html.append("<td><font size='2'>" + personname + "</font></td>");
			//设置displayName
		    html.append(setDisplayName(mp.get("FDISPLAYNAME")));
		    html.append("<td><font size='2'>" +city+"</font></td>");
		    html.append("<td><font size='2'>" +positionname + "</font></td>");
		    html.append("<td><font size='2'>" +pqfname + "</font></td>");
		    html.append("<td><font size='2'>" +emptype+ "</font></td>");
		    html.append("<td><font size='2'>" +fenterdate+ "</font></td>");
		    html.append("<td><font size='2'>" +probzz+ "</font></td>");
		    html.append("<td><font size='2'>"+((entmonth <6)?"试用期将到期":"试用期已到期") +"</font></td>");
		}
		 html.append("</tr></table>");
		 html.append("</tr></table><br/>");
		 //添加hmtl
		htmlContent.append(html);
		 */
		
		htmlContent.append("<div>");
		htmlContent.append("<p class=MsoNormal><span lang=EN-US><o:p>&nbsp;</o:p></span></p>");
		
		
		htmlContent.append("<div><p class=MsoNormal><span style='font-size:10.0pt'>附：员工转正审批流程：<span lang=EN-US><o:p></o:p></span></span></p></div>");
		htmlContent.append("<div style='margin-left:21.0pt'><p class=MsoNormal><span lang=EN-US style='font-size:10.0pt'>1. </span><span style='font-size:10.0pt'>主管级（含）以下员工<span lang=EN-US><o:p></o:p></span></span></p></div>"); 
		htmlContent.append("<div style='margin-left:37.5pt'><p class=MsoNormal><span style='font-size:10.0pt'>由部门根据员工日常工作表现进行评估、审批，并根据审批结果进行相关手续的办理：<span lang=EN-US><o:p></o:p></span></span></p></div>");
		htmlContent.append("<div style='margin-left:37.5pt'><p class=MsoNormal><span style='font-size:10.0pt'>▉如部门同意员工转正： <span lang=EN-US><o:p></o:p></span></span></p></div>");
		htmlContent.append("<div style='margin-left:37.5pt'><p class=MsoNormal><span lang=EN-US style='font-size:10.0pt'>&nbsp;a. </span><span style='font-size:10.0pt'>员工直接上级与即将转正员工进行试用期面谈，双方达成一致后由员工本人填写《新员工转正评估表》并提交部门审批；<span lang=EN-US><o:p></o:p></span></span></p></div>");
		htmlContent.append("<div style='margin-left:37.5pt'><p class=MsoNormal><span lang=EN-US style='font-size:10.0pt'>&nbsp;b. </span><span style='font-size:10.0pt'>部门完成审批手续后将《新员工转正评估表》提交总部人力资源与行政部备案（外埠地区表单以扫描件形式邮件呈报）。<span lang=EN-US><o:p></o:p></span></span></p></div>");
		htmlContent.append("<div style='margin-left:37.5pt'><p class=MsoNormal><span style='font-size:10.0pt'>▉如部门不同意员工转正：<span lang=EN-US><o:p></o:p></span></span></p></div>");
		htmlContent.append("<div style='margin-left:37.5pt'><p class=MsoNormal><span lang=EN-US style='font-size:10.0pt'>&nbsp;a. </span><span style='font-size:10.0pt'>请部门务必于试用期期满前与员工协商一致，依据公司相关规定办理离职手续。<span lang=EN-US><o:p></o:p></span></span></p></div>");
		htmlContent.append("<div style='margin-left:21.0pt'><p class=MsoNormal><span lang=EN-US style='font-size:10.0pt'>2. </span><span style='font-size:10.0pt'>经理级（含）以上员工<span lang=EN-US><o:p></o:p></span></span></p></div>");
		htmlContent.append("<div style='margin-left:37.5pt'><p class=MsoNormal><span style='font-size:10.0pt'>由部门根据员工实际工作表现进行评估、审核，并根据审核结果进行相关手续的办理：<span lang=EN-US><o:p></o:p></span></span></p></div>");
		htmlContent.append("<div style='margin-left:37.5pt'><p class=MsoNormal><span style='font-size:10.0pt'>▉如部门同意员工转正：<span lang=EN-US><o:p></o:p></span></span></p></div>");
		htmlContent.append("<div style='margin-left:37.5pt'><p class=MsoNormal><span lang=EN-US style='font-size:10.0pt'>&nbsp;a. </span><span style='font-size:10.0pt'>员工直接上级与即将转正员工进行试用期面谈，双方达成一致后由员工本人填写《新员工转正评估表》并提交部门审核；<span lang=EN-US><o:p></o:p></span></span></p></div>");
		htmlContent.append("<div style='margin-left:37.5pt'><p class=MsoNormal><span lang=EN-US style='font-size:10.0pt'>&nbsp;b. </span><span style='font-size:10.0pt'>部门完成审核手续后将《新员工转正评估表》提交总部人力资源与行政部进行公司审批（外埠地区表单以扫描件形式邮件呈报）；<span lang=EN-US><o:p></o:p></span></span></p></div>");
		htmlContent.append("<div style='margin-left:37.5pt'><p class=MsoNormal><span lang=EN-US style='font-size:10.0pt'>&nbsp;c. </span><span style='font-size:10.0pt'>如该转正申请未通过公司审批，总部人力资源与行政部将予以邮件反馈。<span lang=EN-US><o:p></o:p></span></span></p></div>");
		htmlContent.append("<div style='margin-left:37.5pt'><p class=MsoNormal><span style='font-size:10.0pt'>▉如部门不同意员工转正：<span lang=EN-US><o:p></o:p></span></span></p></div>");
		htmlContent.append("<div style='margin-left:37.5pt'><p class=MsoNormal><span lang=EN-US style='font-size:10.0pt'>&nbsp;a. </span><span style='font-size:10.0pt'>部门将不同意员工转正的意见及相关材料提交总部人力资源与行政部审批（外埠地区表单以扫描件形式邮件呈报）；<span lang=EN-US><o:p></o:p></span></span></p></div>");
		htmlContent.append("<div style='margin-left:37.5pt'><p class=MsoNormal><span lang=EN-US style='font-size:10.0pt'>&nbsp;b. </span><span style='font-size:10.0pt'>根据员工实际情况进行离职手续的办理。<span lang=EN-US><o:p></o:p></span></span></p></div>");
		htmlContent.append("<p class=MsoNormal><span lang=EN-US><o:p>&nbsp;</o:p></span></p><div><p class=MsoNormal><span style='font-size:10.0pt'>感谢您对人力资源与行政部工作的支持与配合。谢谢！<span lang=EN-US><o:p></o:p></span></span></p></div><p class=MsoNormal><span lang=EN-US><o:p>&nbsp;</o:p></span></p>");
		htmlContent.append("<p class=MsoNormal><span lang=EN-US><img width=251 height=55 src='cid:logo.png'><br>------------------------------------------------------------------<o:p></o:p></span></p>");
		htmlContent.append("<div><p class=MsoNormal><span style='font-size:10.5pt'>传真：<span lang=EN-US> 010- 5738 2188<o:p></o:p></span></span></p></div>");
		htmlContent.append("<div><p class=MsoNormal><span style='font-size:10.5pt'>网站： <span lang=EN-US><a href='http://www.CreditEase.cn'>www.CreditEase.cn</a><o:p></o:p></span></span></p></div>");
		htmlContent.append("<div><p class=MsoNormal><span style='font-size:10.5pt'>地址： 北京市朝阳区建国路<span lang=EN-US>88</span>号<span lang=EN-US>SOHO</span>现代城<span lang=EN-US>C</span>座<span lang=EN-US>16</span>层<span lang=EN-US><o:p></o:p></span></span></p></div>");
		htmlContent.append("<div><p class=MsoNormal><span style='font-size:10.5pt'>邮编：<span lang=EN-US> 100022<o:p></o:p></span></span></p></div>");
		htmlContent.append("</div></div></body></html>");
		return htmlContent.toString();
	}
	/**
	 * 
	 * 描述：获得试用期到期人员的html信息(&nabla;向下的符号)
	 * 2013-1-3 下午12:18:35 by ygq
	 * @version
	 * @param list
	 * @return
	 * style="line-height: 500%;font-family: 宋体;font-size: 五号;"
	 */
	public static String htmlContentImitate(Map<String,List<Map<String,Object>>> mapDown){
		List<Map<String,Object>> immPerson = mapDown.get("immDownPerson");//直接下属
		List<Map<String,Object>> indirPerson = mapDown.get("indirDownPerson");//间接下属
		StringBuffer htmlContent = new StringBuffer();
		htmlContent.append("<html xmlns:v='urn:schemas-microsoft-com:vml' xmlns:o='urn:schemas-microsoft-com:office:office' xmlns:w='urn:schemas-microsoft-com:office:word'xmlns:m='http://schemas.microsoft.com/office/2004/12/omml' xmlns='http://www.w3.org/TR/REC-html40'>");
		htmlContent.append("<head>");
		htmlContent.append("<meta http-equiv=Content-Type content='text/html; charset=utf-8'><meta name=Generator content='Microsoft Word 14 (filtered medium)'>");
		htmlContent.append("<style>");
		htmlContent.append("<!-- /* Font Definitions */ @font-face {font-family:宋体;panose-1:2 1 6 0 3 1 1 1 1 1;} @font-face {font-family:宋体;panose-1:2 1 6 0 3 1 1 1 1 1;} @font-face {font-family:Calibri;panose-1:2 15 5 2 2 2 4 3 2 4;} @font-face {font-family:'\\@宋体';panose-1:2 1 6 0 3 1 1 1 1 1;}");
		htmlContent.append("/* Style Definitions */ p.MsoNormal, li.MsoNormal, div.MsoNormal {margin:0cm;margin-bottom:.0001pt;font-size:12.0pt;font-family:宋体;} a:link, span.MsoHyperlink {mso-style-priority:99;color:blue;text-decoration:underline;} a:visited, span.MsoHyperlinkFollowed {mso-style-priority:99;color:purple;text-decoration:underline;} p.MsoAcetate, li.MsoAcetate, div.MsoAcetate {mso-style-priority:99;mso-style-link:'批注框文本 Char';margin:0cm;margin-bottom:.0001pt;font-size:9.0pt;font-family:宋体;}");
		htmlContent.append("p.div28, li.div28, div.div28 {mso-style-name:div28;mso-margin-top-alt:auto;margin-right:0cm;mso-margin-bottom-alt:auto;	margin-left:21.0pt;font-size:10.5pt;font-family:宋体;} p.div50, li.div50, div.div50 {mso-style-name:div50;mso-margin-top-alt:auto;margin-right:0cm;mso-margin-bottom-alt:auto;margin-left:37.5pt;font-size:10.5pt;font-family:宋体;} p.div72, li.div72, div.div72 {mso-style-name:div72;mso-margin-top-alt:auto;margin-right:0cm;mso-margin-bottom-alt:auto;margin-left:54.0pt;font-size:10.5pt;font-family:宋体;}");
		htmlContent.append("span.Char {mso-style-name:'批注框文本 Char';mso-style-priority:99;mso-style-link:批注框文本;font-family:宋体;} span.EmailStyle22 {mso-style-type:personal-reply;font-family:'Calibri','sans-serif';color:#1F497D;} .MsoChpDefault{mso-style-type:export-only;font-family:'Calibri','sans-serif';} @page WordSection1 {size:612.0pt 792.0pt;margin:72.0pt 90.0pt 72.0pt 90.0pt;} div.WordSection1 {page:WordSection1;}-->");
		htmlContent.append("</style>");
		htmlContent.append("</head>");
		htmlContent.append("<body lang=ZH-CN link=blue vlink=purple><div class=WordSection1><div>");
		htmlContent.append("<div><p class=MsoNormal style='line-height:150%'><span style='font-size:10.0pt;line-height:150%'>尊敬的领导：<span lang=EN-US><o:p></o:p></span></span></p></div>");
		htmlContent.append("<div style='margin-left:21.0pt'><p class=MsoNormal><span style='font-size:10.0pt'>您好！所列员工的试用期即将期满，请查阅并参考邮件所附《员工转正审批流程》办理相关手续<span lang=EN-US><o:p></o:p></span></span></p></div>");
		htmlContent.append("<div style='margin-left:21.0pt'><p class=MsoNormal><span style='font-size:10.0pt'>根据《中华人民共和国劳动合同法》相关规定，用人单位与劳动者签订三年以上固定期限劳动合同的，试用期不得超过六个月<span lang=EN-US><o:p></o:p></span></span></p></div>");
		htmlContent.append("<div style='margin-left:21.0pt'><p class=MsoNormal><span style='font-size:10.0pt'>为避免公司用工风险、维护员工劳动权益，特提醒部门务必于员工试用期到期日前<span lang=EN-US>15</span>日内，完成转正或解除劳动关系相关手续的办理<span lang=EN-US><o:p></o:p></span></span></p></div>");
		htmlContent.append("<p class=MsoNormal><span lang=EN-US><o:p>&nbsp;</o:p></span></p>");
		htmlContent.append("<div style='margin-left:21.0pt'>");
		/*
		 * 新生成动态Table
		 */
		TransformAdapter adapter = new TransformAdapter();
		String htmlTable = "";
		if(null != immPerson && 0 < immPerson.size() && null != indirPerson && 0 < indirPerson.size()){
			htmlTable = adapter.meterStaticToDynamic(new String[]{"直接下级","间接下级"},immPerson,indirPerson);
		}
		if(null != immPerson && 0 < immPerson.size() && (null == indirPerson || 0 >= indirPerson.size())){
			htmlTable = adapter.meterStaticToDynamic(new String[]{"直接下级"},immPerson);
		}
		if(null != indirPerson && 0 < indirPerson.size() && (null == immPerson || 0 >= immPerson.size())){
			htmlTable = adapter.meterStaticToDynamic(new String[]{"间接下级"},indirPerson);
		}
		htmlContent.append(htmlTable);
		
		/*
		 * 原静态Table
		 * StringBuffer html = new StringBuffer();
		html.append("<table style='border-collapse: collapse;width:960px;line-height:150%;font-family: 宋体;size=5;' cellpadding='5' border='1'>");
		html.append("<tr bgcolor='#0066cc' align='center'>");
		html.append("<td><font color='white' size='2'>员工编号</font></td>");
		html.append("<td><font color='white' size='2'>身份证姓名</font></td>");
		html.append("<td height='20'><font color='white' size='2'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;一级部门&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font></td>");
		html.append("<td height='20'><font color='white' size='2'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;二级部门&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font></td>");
		html.append("<td height='20'><font color='white' size='2'>&nbsp;&nbsp;&nbsp;三级部门&nbsp;&nbsp;&nbsp;</font></td>");
		html.append("<td height='20'><font color='white' size='2'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;四级部门&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font></td>");
		html.append("<td height='20'><font color='white' size='2'>&nbsp;&nbsp;&nbsp;五级部门&nbsp;&nbsp;&nbsp;</font></td>");
		html.append("<td><font color='white' size='2'>城市</font></td>");
		html.append("<td><font color='white' size='2'>岗位</font></td>");
		html.append("<td><font color='white' size='2'>任职资格</font></td>");
		html.append("<td><font color='white' size='2'>人员状态</font></td>");
		html.append("<td><font color='white' size='2'>入职日期</font></td>");
		html.append("<td><font color='white' size='2'>预计转正日期</font></td>");
		html.append("<td bgcolor='orange'><font color='white' size='2'>预警类型</font></td>");
		html.append("</tr>");
		if(null != immPerson && immPerson.size()>0){
	       html.append("<tr><td colspan='14'><b>直接下属:</b></td></tr>");
		   for(int i =0;i<immPerson.size();i++){
			Map<String,Object> mp = immPerson.get(i);
			String personcode = StringUtil.objToString(mp.get("PERSONCODE"));
			String personname = StringUtil.objToString(mp.get("PERSONNAME"));
			String city =StringUtil.objToString(mp.get("CITY"));//城市
			String positionname = StringUtil.objToString(mp.get("POSITIONNAME"));//岗位
			String pqfname = StringUtil.objToString(mp.get("PQFNAME"));//任职资格
			String emptype = StringUtil.objToString(mp.get("EMPTYPE"));//人员状态
			String fenterdate = DateUtil.formatTimestampToString((TIMESTAMP)mp.get("FENTERDATE"));//入职日期
			String probzz = DateUtil.formatDateToString((Date)mp.get("PROBZZ"));//预计转正日期
			int entmonth = DateUtil.monthsBetween(fenterdate);
			html.append("<tr align='center'>");
			html.append("<td><font size='2'>"+personcode+"</font></td>");
			html.append("<td><font size='2'>" + personname + "</font></td>");
			//设置displayName
		    html.append(setDisplayName(mp.get("FDISPLAYNAME")));
		    html.append("<td><font size='2'>" +city+"</font></td>");
		    html.append("<td><font size='2'>" +positionname + "</font></td>");
		    html.append("<td><font size='2'>" +pqfname + "</font></td>");
		    html.append("<td><font size='2'>" +emptype+ "</font></td>");
		    html.append("<td><font size='2'>" +fenterdate+ "</font></td>");
		    html.append("<td><font size='2'>" +probzz+ "</font></td>");
		    html.append("<td><font size='2'>"+((entmonth <6)?"试用期将到期":"试用期已到期") +"</font></td>");
		 }
		}
		if(null != indirPerson && indirPerson.size()>0){
		       html.append("<tr><td colspan='14'><b>间接下属:</b></td></tr>");
			   for(int i =0;i<indirPerson.size();i++){
				Map<String,Object> mp = indirPerson.get(i);
				String personcode = StringUtil.objToString(mp.get("PERSONCODE"));
				String personname = StringUtil.objToString(mp.get("PERSONNAME"));
				String city =StringUtil.objToString(mp.get("CITY"));//城市
				String positionname = StringUtil.objToString(mp.get("POSITIONNAME"));//岗位
				String pqfname = StringUtil.objToString(mp.get("PQFNAME"));//任职资格
				String emptype = StringUtil.objToString(mp.get("EMPTYPE"));//人员状态
				String fenterdate = DateUtil.formatTimestampToString((TIMESTAMP)mp.get("FENTERDATE"));//入职日期
				String probzz = DateUtil.formatDateToString((Date)mp.get("PROBZZ"));//预计转正日期
				int entmonth = DateUtil.monthsBetween(fenterdate);
				html.append("<tr align='center'>");
				html.append("<td><font size='2'>"+personcode+"</font></td>");
				html.append("<td><font size='2'>" + personname + "</font></td>");
				//设置displayName
			    html.append(setDisplayName(mp.get("FDISPLAYNAME")));
			    html.append("<td><font size='2'>" +city+"</font></td>");
			    html.append("<td><font size='2'>" +positionname + "</font></td>");
			    html.append("<td><font size='2'>" +pqfname + "</font></td>");
			    html.append("<td><font size='2'>" +emptype+ "</font></td>");
			    html.append("<td><font size='2'>" +fenterdate+ "</font></td>");
			    html.append("<td><font size='2'>" +probzz+ "</font></td>");
			    html.append("<td><font size='2'>"+((entmonth <6)?"试用期将到期":"试用期已到期") +"</font></td>");
			 }
		 }
		 html.append("</tr></table>");
		 html.append("</tr></table><br/>");
		 //添加hmtl
		htmlContent.append(html);
		 */
		 
		htmlContent.append("<div>");
		htmlContent.append("<p class=MsoNormal><span lang=EN-US><o:p>&nbsp;</o:p></span></p>");
		
		htmlContent.append("<div><p class=MsoNormal><span style='font-size:10.0pt'>附：员工转正审批流程：<span lang=EN-US><o:p></o:p></span></span></p></div>");
		htmlContent.append("<div style='margin-left:21.0pt'><p class=MsoNormal><span lang=EN-US style='font-size:10.0pt'>1. </span><span style='font-size:10.0pt'>主管级（含）以下员工<span lang=EN-US><o:p></o:p></span></span></p></div>"); 
		htmlContent.append("<div style='margin-left:37.5pt'><p class=MsoNormal><span style='font-size:10.0pt'>由部门根据员工日常工作表现进行评估、审批，并根据审批结果进行相关手续的办理：<span lang=EN-US><o:p></o:p></span></span></p></div>");
		htmlContent.append("<div style='margin-left:37.5pt'><p class=MsoNormal><span style='font-size:10.0pt'>▉如部门同意员工转正： <span lang=EN-US><o:p></o:p></span></span></p></div>");
		htmlContent.append("<div style='margin-left:37.5pt'><p class=MsoNormal><span lang=EN-US style='font-size:10.0pt'>&nbsp;a. </span><span style='font-size:10.0pt'>员工直接上级与即将转正员工进行试用期面谈，双方达成一致后由员工本人填写《新员工转正评估表》并提交部门审批；<span lang=EN-US><o:p></o:p></span></span></p></div>");
		htmlContent.append("<div style='margin-left:37.5pt'><p class=MsoNormal><span lang=EN-US style='font-size:10.0pt'>&nbsp;b. </span><span style='font-size:10.0pt'>部门完成审批手续后将《新员工转正评估表》提交总部人力资源与行政部备案（外埠地区表单以扫描件形式邮件呈报）。<span lang=EN-US><o:p></o:p></span></span></p></div>");
		htmlContent.append("<div style='margin-left:37.5pt'><p class=MsoNormal><span style='font-size:10.0pt'>▉如部门不同意员工转正：<span lang=EN-US><o:p></o:p></span></span></p></div>");
		htmlContent.append("<div style='margin-left:37.5pt'><p class=MsoNormal><span lang=EN-US style='font-size:10.0pt'>&nbsp;a. </span><span style='font-size:10.0pt'>请部门务必于试用期期满前与员工协商一致，依据公司相关规定办理离职手续。<span lang=EN-US><o:p></o:p></span></span></p></div>");
		htmlContent.append("<div style='margin-left:21.0pt'><p class=MsoNormal><span lang=EN-US style='font-size:10.0pt'>2. </span><span style='font-size:10.0pt'>经理级（含）以上员工<span lang=EN-US><o:p></o:p></span></span></p></div>");
		htmlContent.append("<div style='margin-left:37.5pt'><p class=MsoNormal><span style='font-size:10.0pt'>由部门根据员工实际工作表现进行评估、审核，并根据审核结果进行相关手续的办理：<span lang=EN-US><o:p></o:p></span></span></p></div>");
		htmlContent.append("<div style='margin-left:37.5pt'><p class=MsoNormal><span style='font-size:10.0pt'>▉如部门同意员工转正：<span lang=EN-US><o:p></o:p></span></span></p></div>");
		htmlContent.append("<div style='margin-left:37.5pt'><p class=MsoNormal><span lang=EN-US style='font-size:10.0pt'>&nbsp;a. </span><span style='font-size:10.0pt'>员工直接上级与即将转正员工进行试用期面谈，双方达成一致后由员工本人填写《新员工转正评估表》并提交部门审核；<span lang=EN-US><o:p></o:p></span></span></p></div>");
		htmlContent.append("<div style='margin-left:37.5pt'><p class=MsoNormal><span lang=EN-US style='font-size:10.0pt'>&nbsp;b. </span><span style='font-size:10.0pt'>部门完成审核手续后将《新员工转正评估表》提交总部人力资源与行政部进行公司审批（外埠地区表单以扫描件形式邮件呈报）；<span lang=EN-US><o:p></o:p></span></span></p></div>");
		htmlContent.append("<div style='margin-left:37.5pt'><p class=MsoNormal><span lang=EN-US style='font-size:10.0pt'>&nbsp;c. </span><span style='font-size:10.0pt'>如该转正申请未通过公司审批，总部人力资源与行政部将予以邮件反馈。<span lang=EN-US><o:p></o:p></span></span></p></div>");
		htmlContent.append("<div style='margin-left:37.5pt'><p class=MsoNormal><span style='font-size:10.0pt'>▉如部门不同意员工转正：<span lang=EN-US><o:p></o:p></span></span></p></div>");
		htmlContent.append("<div style='margin-left:37.5pt'><p class=MsoNormal><span lang=EN-US style='font-size:10.0pt'>&nbsp;a. </span><span style='font-size:10.0pt'>部门将不同意员工转正的意见及相关材料提交总部人力资源与行政部审批（外埠地区表单以扫描件形式邮件呈报）；<span lang=EN-US><o:p></o:p></span></span></p></div>");
		htmlContent.append("<div style='margin-left:37.5pt'><p class=MsoNormal><span lang=EN-US style='font-size:10.0pt'>&nbsp;b. </span><span style='font-size:10.0pt'>根据员工实际情况进行离职手续的办理。<span lang=EN-US><o:p></o:p></span></span></p></div>");
		htmlContent.append("<p class=MsoNormal><span lang=EN-US><o:p>&nbsp;</o:p></span></p><div><p class=MsoNormal><span style='font-size:10.0pt'>感谢您对人力资源与行政部工作的支持与配合。谢谢！<span lang=EN-US><o:p></o:p></span></span></p></div><p class=MsoNormal><span lang=EN-US><o:p>&nbsp;</o:p></span></p>");
		htmlContent.append("<p class=MsoNormal><span lang=EN-US><img width=251 height=55 src='cid:logo.png'><br>------------------------------------------------------------------<o:p></o:p></span></p>");
		htmlContent.append("<div><p class=MsoNormal><span style='font-size:10.5pt'>传真：<span lang=EN-US> 010- 5738 2188<o:p></o:p></span></span></p></div>");
		htmlContent.append("<div><p class=MsoNormal><span style='font-size:10.5pt'>网站： <span lang=EN-US><a href='http://www.CreditEase.cn'>www.CreditEase.cn</a><o:p></o:p></span></span></p></div>");
		htmlContent.append("<div><p class=MsoNormal><span style='font-size:10.5pt'>地址： 北京市朝阳区建国路<span lang=EN-US>88</span>号<span lang=EN-US>SOHO</span>现代城<span lang=EN-US>C</span>座<span lang=EN-US>16</span>层<span lang=EN-US><o:p></o:p></span></span></p></div>");
		htmlContent.append("<div><p class=MsoNormal><span style='font-size:10.5pt'>邮编：<span lang=EN-US> 100022<o:p></o:p></span></span></p></div>");
		htmlContent.append("</div></div></body></html>");
		return htmlContent.toString();
	}
}
