package com.creditease.eas.util.mail;

import java.util.List;
import java.util.Map;

/**
 * 生日预警模板
 * @author lining
 * @since 2014-5-26
 */
public class HtmlBirthdayTemplate {

	/**
	 * 发送个上级的生日预警模板
	 * @author lining
	 * @since 2014-5-26
	 * @param list
	 * @return
	 */
	public static String makeEmailForHigher(
			Map<String, List<Map<String, Object>>> list) {
		List<Map<String,Object>> immPerson = list.get("immDownPerson");//直接下属
		List<Map<String,Object>> indirPerson = list.get("indirDownPerson");//间接下属
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
		htmlContent.append("<div style='margin-left:21.0pt'><p class=MsoNormal><span style='font-size:10.0pt'>您好！所列员工的生日即将到来，公司将通过邮件及短信送上祝福，请您知悉<span lang=EN-US><o:p></o:p></span></span></p></div>");
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
		
		htmlContent.append("<div>");
		htmlContent.append("<p class=MsoNormal><span lang=EN-US><o:p>&nbsp;</o:p></span></p>");
		
		htmlContent.append("<br>");
		htmlContent.append("<p class=MsoNormal><span lang=EN-US><img width=251 height=55 src='cid:logo.png'><br>------------------------------------------------------------------<o:p></o:p></span></p>");
		htmlContent.append("<div><p class=MsoNormal><span style='font-size:10.5pt'>传真：<span lang=EN-US> 010- 5738 2188<o:p></o:p></span></span></p></div>");
		htmlContent.append("<div><p class=MsoNormal><span style='font-size:10.5pt'>网站： <span lang=EN-US><a href='http://www.CreditEase.cn'>www.CreditEase.cn</a><o:p></o:p></span></span></p></div>");
		htmlContent.append("<div><p class=MsoNormal><span style='font-size:10.5pt'>地址： 北京市朝阳区建国路<span lang=EN-US>88</span>号<span lang=EN-US>SOHO</span>现代城<span lang=EN-US>C</span>座<span lang=EN-US>16</span>层<span lang=EN-US><o:p></o:p></span></span></p></div>");
		htmlContent.append("<div><p class=MsoNormal><span style='font-size:10.5pt'>邮编：<span lang=EN-US> 100022<o:p></o:p></span></span></p></div>");
		htmlContent.append("</div></div></body></html>");
		return htmlContent.toString();
	}

	/**
	 * 发送给部门负责人（抄送综合管理部和BP）的生日预警模板
	 * @author lining
	 * @since 2014-5-26
	 * @param list
	 * @return
	 */
	public static String makeEmailForResponse(
			List<Map<String, Object>> list) {
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
		htmlContent.append("<div style='margin-left:21.0pt'><p class=MsoNormal><span style='font-size:10.0pt'>您好！所列员工的生日即将到来，公司将通过邮件及短信送上祝福，请您知悉<span lang=EN-US><o:p></o:p></span></span></p></div>");
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
		
		htmlContent.append("<div>");
		htmlContent.append("<p class=MsoNormal><span lang=EN-US><o:p>&nbsp;</o:p></span></p>");
		
		htmlContent.append("<p class=MsoNormal><span lang=EN-US><img width=251 height=55 src='cid:logo.png'><br>------------------------------------------------------------------<o:p></o:p></span></p>");
		htmlContent.append("<div><p class=MsoNormal><span style='font-size:10.5pt'>传真：<span lang=EN-US> 010- 5738 2188<o:p></o:p></span></span></p></div>");
		htmlContent.append("<div><p class=MsoNormal><span style='font-size:10.5pt'>网站： <span lang=EN-US><a href='http://www.CreditEase.cn'>www.CreditEase.cn</a><o:p></o:p></span></span></p></div>");
		htmlContent.append("<div><p class=MsoNormal><span style='font-size:10.5pt'>地址： 北京市朝阳区建国路<span lang=EN-US>88</span>号<span lang=EN-US>SOHO</span>现代城<span lang=EN-US>C</span>座<span lang=EN-US>16</span>层<span lang=EN-US><o:p></o:p></span></span></p></div>");
		htmlContent.append("<div><p class=MsoNormal><span style='font-size:10.5pt'>邮编：<span lang=EN-US> 100022<o:p></o:p></span></span></p></div>");
		htmlContent.append("</div></div></body></html>");
		return htmlContent.toString();
	}

	
}
