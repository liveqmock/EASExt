package com.creditease.eas.util.mail;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.creditease.eas.admin.bean.EmailConfig;
import com.creditease.eas.admin.dao.EmailConfigMapper;
import com.creditease.eas.compliance.bean.Complain;
import com.creditease.eas.util.Constant;
import com.creditease.eas.util.consts.ConfigConst;
import com.creditease.eas.util.html.HTMLTable;
import com.creditease.eas.warn.vo.PersonData2;

/**
 * 预警公用的方法
 * @WarnCommonMethod.java
 * created at 2013-2-1 下午01:52:41 by ygq
 * 
 * @author ygq({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class ContractHtmlContent{

	/**
	 * 
	 * 描述：设置DisplayName
	 * 2013-2-19 上午11:25:12 by xjw
	 * @version
	 * @param fdisplayname
	 * @return
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
	 * 描述：获取发送内容
	 * 2013-2-19 上午11:24:58 by xjw
	 * @version
	 * @param list
	 * @return
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
		htmlContent.append("<div><p class=MsoNormal style='line-height:150%'><span style='font-size:10.0pt;line-height:150%'>亲爱的同事：<span lang=EN-US><o:p></o:p></span></span></p></div>");
		htmlContent.append("<div style='margin-left:21.0pt'><p class=MsoNormal><span style='font-size:10.0pt'>您好！所列员工的劳动合同即将期满，请查阅并参考邮件所附《员工劳动合同续签流程》办理相关手续<span lang=EN-US><o:p></o:p></span></span></p></div>");
		htmlContent.append("<div style='margin-left:21.0pt'><p class=MsoNormal><span style='font-size:10.0pt'>为避免公司用工风险、维护员工劳动权益，特提醒部门务必于收到本邮件<span lang=EN-US>10</span>日内，完成续签或解除劳动合同相关流程<span lang=EN-US><o:p></o:p></span></span></p></div>");
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
		/**
		 * 原静态Table生成
		StringBuffer html = new StringBuffer();
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
		html.append("<td><font color='white' size='2'>职类</font></td>");
		html.append("<td><font color='white' size='2'>人员状态</font></td>");
		html.append("<td><font color='white' size='2'>入职日期</font></td>");
		html.append("<td><font color='white' size='2'>性别</font></td>");
		html.append("<td><font color='white' size='2'>年龄</font></td>");
		html.append("<td><font color='white' size='2'>政治面貌</font></td>");
		html.append("<td><font color='white' size='2'>毕业院校</font></td>");
		html.append("<td><font color='white' size='2'>专业</font></td>");
		html.append("<td><font color='white' size='2'>学历</font></td>");
		html.append("<td bgcolor='orange'><font color='white' size='2'>状态</font></td>");
		html.append("</tr>");
		for(int i =0;i<list.size();i++){
			Map<String,Object> mp = list.get(i);
			String pid = mp.get("PERSONNUMBER").toString();
			String name = mp.get("PNAME").toString();
			String city = (mp.get("NPLACE")==null)?"":mp.get("NPLACE").toString(); //城市
			String position = mp.get("POSITION").toString();
			String posiqual = (mp.get("POSIQUAL")==null)?"":mp.get("POSIQUAL").toString(); //任职资格
			String pstatus = (mp.get("PSTATUS")==null)?"":mp.get("PSTATUS").toString();  //人员状态
			String potype = (mp.get("POTYPE")==null)?"":mp.get("POTYPE").toString();   //职类
			String politics = (mp.get("POLITICS")==null)?"":mp.get("POLITICS").toString();  //政治面貌
//			String enterdate = (mp.get("ENTERDATE")==null)?"":mp.get("ENTERDATE").toString(); 
			String enterdate = DateUtil.formatTimestampToString((TIMESTAMP)mp.get("ENTERDATE"));
			String sex="";
			Integer gender = Integer.parseInt(mp.get("GENDER").toString());
			if(gender==1)
				sex="男";
			else
				sex="女";
			String age= mp.get("AGE").toString();
			String school = (mp.get("SCHOOL")==null)?"":mp.get("SCHOOL").toString();  //毕业院校
			String specialty = (mp.get("SPECIALTY")==null)?"":mp.get("SPECIALTY").toString(); //专业
			String degreed = (mp.get("DEGREED")==null)?"":mp.get("DEGREED").toString(); //学历
			
			html.append("<tr align='center'>");
			html.append("<td><font size='2'>"+pid+"</font></td>");
			html.append("<td><font size='2'>" + name + "</font></td>");
			//设置displayName
		    html.append(setDisplayName(mp.get("DISNAME")));
		    html.append("<td><font size='2'>" +city+"</font></td>");
		    html.append("<td><font size='2'>" +position + "</font></td>");
		    html.append("<td><font size='2'>" +posiqual + "</font></td>");
		    
		    html.append("<td><font size='2'>" +potype+ "</font></td>");
		    html.append("<td><font size='2'>" +pstatus+ "</font></td>");
		    html.append("<td><font size='2'>" +enterdate + "</font></td>");
		  
		    html.append("<td><font size='2'>" +sex + "</font></td>");
		    html.append("<td><font size='2'>" +age+ "</font></td>");
		    html.append("<td><font size='2'>" +politics+ "</font></td>"); 
		    html.append("<td><font size='2'>" +school+ "</font></td>");
		    html.append("<td><font size='2'>" +specialty+ "</font></td>"); 
		    html.append("<td><font size='2'>" +degreed+ "</font></td>"); 
		    html.append("<td><font color='red' size='2'>合同将到期</font></td>"); 
			html.append("</tr>");
		}
		html.append("</table><br/>");
		 */
		htmlContent.append("<div>");
		htmlContent.append("<p class=MsoNormal><span lang=EN-US><o:p>&nbsp;</o:p></span></p>");
		
		htmlContent.append("<div><p class=MsoNormal><span style='font-size:10.0pt'>附：员工劳动合同续签流程：<span lang=EN-US><o:p></o:p></span></span></p></div>");
		htmlContent.append("<div style='margin-left:21.0pt'><p class=MsoNormal><span lang=EN-US style='font-size:10.0pt'>1. </span><span style='font-size:10.0pt'>收到提醒邮件后，请立即联系部门接口人<span lang=EN-US>/</span>人事工作负责人跟进部门内部续签审批工作，如员工已符合签订无固定期劳动合同的条件，请务必及时通知部门总监<span lang=EN-US>/</span>部门人事工作负责人<span lang=EN-US><o:p></o:p></span></span></p></div>"); 
		htmlContent.append("<div style='margin-left:21.0pt'><p class=MsoNormal><span lang=EN-US style='font-size:10.0pt'>2. </span><span style='font-size:10.0pt'>请部门根据员工实际工作表现进行评估、审批，确认是否同意与员工续签劳动合同<span lang=EN-US><o:p></o:p></span></span></p></div>");
		htmlContent.append("<div style='margin-left:37.5pt'><p class=MsoNormal><span lang=EN-US style='font-size:10.0pt'>&nbsp; a. </span><span style='font-size:10.0pt'>如部门内部协商一致，同意与员工续签劳动合同：员工直接主管安排与续签人员进行续签面谈，双方达成一致后由员工至总部HR<span lang=EN-US>/</span>部门接口人处签订《劳动合同续订书》办理续签手续；<span lang=EN-US><o:p></o:p></span></span></p></div>");
		htmlContent.append("<div style='margin-left:37.5pt'><p class=MsoNormal><span lang=EN-US style='font-size:10.0pt'>&nbsp; b. </span><span style='font-size:10.0pt'>如部门不同意与员工续签劳动合同：请部门务必于劳动合同期满前一个月（<span lang=EN-US>30</span>日）与员工协商解决、达成一致，并依据公司相关规定办理离职手续。<span lang=EN-US><o:p></o:p></span></span></p></div>");
		htmlContent.append("<p class=MsoNormal><span lang=EN-US><o:p>&nbsp;</o:p></span></p><div><p class=MsoNormal><span style='font-size:10.0pt'>感谢您对人力资源与行政部工作的支持与配合。谢谢！<span lang=EN-US><o:p></o:p></span></span></p></div><p class=MsoNormal><span lang=EN-US><o:p>&nbsp;</o:p></span></p>");
		htmlContent.append("<p class=MsoNormal><span lang=EN-US><img width=251 height=55 src='cid:logo.png'><br>------------------------------------------------------------------<o:p></o:p></span></p>");
		htmlContent.append("<div><p class=MsoNormal><span style='font-size:10.5pt'>传真：<span lang=EN-US> 010- 5738 2188<o:p></o:p></span></span></p></div>");
		htmlContent.append("<div><p class=MsoNormal><span style='font-size:10.5pt'>网站： <span lang=EN-US><a href='http://www.CreditEase.cn'>www.CreditEase.cn</a><o:p></o:p></span></span></p></div>");
		htmlContent.append("<div><p class=MsoNormal><span style='font-size:10.5pt'>地址： 北京市朝阳区建国路<span lang=EN-US>88</span>号<span lang=EN-US>SOHO</span>现代城<span lang=EN-US>C</span>座<span lang=EN-US>16</span>层<span lang=EN-US><o:p></o:p></span></span></p></div>");
		htmlContent.append("<div><p class=MsoNormal><span style='font-size:10.5pt'>邮编：<span lang=EN-US> 100022<o:p></o:p></span></span></p></div>");
		htmlContent.append("</div></div></body></html>");
		return htmlContent.toString();
	}
	
	public static String htmlContent22(Set<PersonData2> list){
		StringBuffer htmlContent = new StringBuffer();
		htmlContent.append("<html><head>");
		htmlContent.append("<style type='text/css'>");
		htmlContent.append("div {");
		htmlContent.append("line-height: 150%;");
		htmlContent.append("font-family: 宋体;");
		htmlContent.append("font-size: 14px;");
		htmlContent.append("}");
		htmlContent.append(".div28{");
		htmlContent.append("margin-left:28px;");
		htmlContent.append("line-height: 150%;");
		htmlContent.append("font-family: 宋体;");
		htmlContent.append("font-size: 14px;");
		htmlContent.append("}");
		htmlContent.append(".div50{");
		htmlContent.append("margin-left:50px;");
		htmlContent.append("line-height: 150%;");
		htmlContent.append("font-family: 宋体;");
		htmlContent.append("font-size: 14px;");
		htmlContent.append("}");
		htmlContent.append(".div72{");
		htmlContent.append("margin-left:72px;");
		htmlContent.append("line-height: 150%;");
		htmlContent.append("font-family: 宋体;");
		htmlContent.append("font-size: 14px;");
		htmlContent.append("}");
		htmlContent.append("td{");
		htmlContent.append("white-space: nowrap");
		htmlContent.append("}");
		htmlContent.append("</style>");
		htmlContent.append("</head>");
		htmlContent.append("<body>");


		StringBuffer html = new StringBuffer();
		html.append("<table style='border-collapse: collapse;width:960px;line-height:150%;font-family: 宋体;size=5;' cellpadding='5' border='1'>");
		html.append("<tr bgcolor='#0066cc' align='center'>");
		html.append("<td><font color='white' size='2'>员工编号</font></td>");
		html.append("<td><font color='white' size='2'>身份证姓名</font></td>");
		html.append("<td height='20'><font color='white' size='2'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;一级部门&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font></td>");
		html.append("<td height='20'><font color='white' size='2'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;二级部门&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font></td>");
		html.append("<td height='20'><font color='white' size='2'>&nbsp;&nbsp;&nbsp;三级部门&nbsp;&nbsp;&nbsp;</font></td>");
		html.append("<td height='20'><font color='white' size='2'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;四级部门&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font></td>");
		html.append("<td height='20'><font color='white' size='2'>&nbsp;&nbsp;&nbsp;五级部门&nbsp;&nbsp;&nbsp;</font></td>");
		html.append("<td><font color='white' size='2'>岗位</font></td>");
		html.append("<td><font color='white' size='2'>任职资格</font></td>");
		html.append("<td><font color='white' size='2'>人员状态</font></td>");
		html.append("<td><font color='white' size='2'>入职日期</font></td>");
		html.append("<td><font color='white' size='2'>服务年限</font></td>");
		html.append("<td><font color='white' size='2'>上下级关系</font></td>");
		html.append("</tr>");
//		for(int i =0;i<list.size();i++){
//		Map<String,Object> mp = list.get(i);
		
		for(PersonData2 mp : list){
//			String pid = mp.get("PERSONNUMBER").toString();
//			String name = mp.get("NAME").toString();
//			String position = mp.get("POSITION").toString();
//			String posiqual = (mp.get("POSIQUAL")==null)?"":mp.get("POSIQUAL").toString(); //任职资格
//			String pstatus = (mp.get("PSTATUS")==null)?"":mp.get("PSTATUS").toString();  //人员状态
//			String enterdate = DateUtil.formatTimestampToString((TIMESTAMP)mp.get("ENTERDATE"));//入职日期
//			String years = mp.get("YEARS").toString();   //服务年限
//			String tmp = "";
//			if(poid.equals(mp.get("POSITIONID").toString())){
//				tmp = "直接下属";
//			}else{
//				tmp = "间接下属";
//			}
			
			String pid = mp.getPid();
			String name = mp.getName();
			String position = mp.getPosition();
			String posiqual = mp.getPosiqual(); //任职资格
			String pstatus = mp.getPstatus();  //人员状态
			String enterdate = mp.getEnterdate();//入职日期
			String years = mp.getYears();   //服务年限
			String tmp = "";
			if("zj".equals(mp.getTmp())){
				tmp = "直接下属";
			}else{
				tmp = "间接下属";
			}
			
			html.append("<tr align='center'>");
			html.append("<td><font size='2'>"+pid+"</font></td>");
			html.append("<td><font size='2'>" + name + "</font></td>");
			//设置displayName
		    html.append(setDisplayName(mp.getDisname()));
		    html.append("<td><font size='2'>" +position + "</font></td>");
		    html.append("<td><font size='2'>" +posiqual + "</font></td>");
		    
		    html.append("<td><font size='2'>" +pstatus+ "</font></td>");
		    html.append("<td><font size='2'>" +enterdate + "</font></td>");
		  
		    html.append("<td><font size='2'>" +years+ "</font></td>");
//		    html.append("<td><font size='2'>" +tmp+ "</font></td>");
			html.append("</tr>");
		}
		 html.append("</table><br/>");
		
		//添加hmtl
		htmlContent.append(html);
		htmlContent.append("<img src='cid:xx.jpg'><br/>");
		htmlContent.append("------------------------------------------------------------------<br/>");
		htmlContent.append("<div>传真： 010- 5738 2188</div>");
		htmlContent.append("<div>网站： www.CreditEase.cn</div>");
		htmlContent.append("<div>地址： 北京市朝阳区建国路88号SOHO现代城C座16层</div>");
		htmlContent.append("<div>邮编： 100022</div>");
		htmlContent.append("</body>");
		htmlContent.append("</html>");
		return htmlContent.toString();
	}
	
	/**
	 * 
	 * 描述：司龄发送给直接|隔级上级
	 * 2013-4-18 下午03:24:13 by xjw
	 * @version
	 * @param list
	 * @param list2
	 * @return
	 */
	public static String htmlContent33(List<PersonData2> list,List<PersonData2> list2){
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
		htmlContent.append("<div style='margin-left:21.0pt'><p class=MsoNormal><span style='font-size:10.0pt'>您好！所列员工入职周年纪念日即将到来，公司将通过邮件送上祝福，请您知悉<span lang=EN-US><o:p></o:p></span></span></p></div>");
		htmlContent.append("<p class=MsoNormal><span lang=EN-US><o:p>&nbsp;</o:p></span></p>");
		htmlContent.append("<div style='margin-left:21.0pt'>");
		
		/*
		 * 新生成动态Table
		 */
		TransformAdapter adapter = new TransformAdapter();
		String htmlTable = "";
		if(null != list && 0 < list.size() && null != list2 && 0 < list2.size()){
			htmlTable = adapter.meterStaticToDynamic(new String[]{"直接下级","间接下级"},list,list2);
		}
		if(null != list && 0 < list.size() && (null == list2 || 0 >= list2.size())){
			htmlTable = adapter.meterStaticToDynamic(new String[]{"直接下级"},list);
		}
		if(null != list2 && 0 < list2.size() && (null == list || 0 >= list.size())){
			htmlTable = adapter.meterStaticToDynamic(new String[]{"间接下级"},list2);
		}
		htmlContent.append(htmlTable);
		
		/*
		 * 原静态表头
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
		html.append("<td><font color='white' size='2'>岗位</font></td>");
		html.append("<td><font color='white' size='2'>任职资格</font></td>");
		html.append("<td><font color='white' size='2'>人员状态</font></td>");
		html.append("<td><font color='white' size='2'>入职日期</font></td>");
		html.append("<td><font color='white' size='2'>服务年限</font></td>");
		html.append("</tr>");
		if(list!=null && list.size()>0){
			html.append("<tr align='center'>");
//			html.append("<td  colspan='12'><font color='red' size='2'>直接下属</font></td>");
		    html.append("<tr><td colspan='12'><b>直接下属:</b></td></tr>");
			html.append("</tr>");
			for(PersonData2 mp : list){
				String pid = mp.getPid();
				String name = mp.getName();
				String position = mp.getPosition();
				String posiqual = mp.getPosiqual(); //任职资格
				String pstatus = mp.getPstatus();  //人员状态
				String enterdate = mp.getEnterdate();//入职日期
				String years = mp.getYears();   //服务年限
				int yy = Utils.getInt(years)+1;
				html.append("<tr align='center'>");
				html.append("<td><font size='2'>"+pid+"</font></td>");
				html.append("<td><font size='2'>" + name + "</font></td>");
				//设置displayName
			    html.append(setDisplayName(mp.getDisname()));
			    html.append("<td><font size='2'>" +position + "</font></td>");
			    html.append("<td><font size='2'>" +posiqual + "</font></td>");
			    
			    html.append("<td><font size='2'>" +pstatus+ "</font></td>");
			    html.append("<td><font size='2'>" +enterdate + "</font></td>");
			  
			    html.append("<td><font size='2'>" +yy+ "</font></td>");
//			    html.append("<td><font size='2'>" +tmp+ "</font></td>");
				html.append("</tr>");
			}
		}else if(list2!=null && list2.size()>0){
			html.append("<tr align='center'>");
//			html.append("<td colspan='12'><font color='red' size='2'>间接下属</font></td>");
			  html.append("<tr><td colspan='12'><b>间接下属:</b></td></tr>");
			html.append("</tr>");
			for(PersonData2 mp : list2){
				String pid = mp.getPid();
				String name = mp.getName();
				String position = mp.getPosition();
				String posiqual = mp.getPosiqual(); //任职资格
				String pstatus = mp.getPstatus();  //人员状态
				String enterdate = mp.getEnterdate();//入职日期
				String years = mp.getYears();   //服务年限
				int yy = Utils.getInt(years)+1;
				html.append("<tr align='center'>");
				html.append("<td><font size='2'>"+pid+"</font></td>");
				html.append("<td><font size='2'>" + name + "</font></td>");
				//设置displayName
			    html.append(setDisplayName(mp.getDisname()));
			    html.append("<td><font size='2'>" +position + "</font></td>");
			    html.append("<td><font size='2'>" +posiqual + "</font></td>");
			    
			    html.append("<td><font size='2'>" +pstatus+ "</font></td>");
			    html.append("<td><font size='2'>" +enterdate + "</font></td>");
			  
			    html.append("<td><font size='2'>" +yy+ "</font></td>");
//			    html.append("<td><font size='2'>" +tmp+ "</font></td>");
				html.append("</tr>");
			}
		}
		html.append("</table><br/>");
		//添加hmtl
		htmlContent.append(html);
		 */
		
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
	 * 
	 * 描述：合同发送给部门负责人
	 * 2013-4-18 下午03:24:54 by xjw
	 * @version
	 * @param list
	 * @return
	 */
	public static String htmlContent44(Set<Map<String,Object>> list){
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
		htmlContent.append("<div style='margin-left:21.0pt'><p class=MsoNormal><span style='font-size:10.0pt'>您好！所列员工的劳动合同即将期满，请查阅并参考邮件所附《员工劳动合同续签流程》办理相关手续<span lang=EN-US><o:p></o:p></span></span></p></div>");
		htmlContent.append("<div style='margin-left:21.0pt'><p class=MsoNormal><span style='font-size:10.0pt'>为避免公司用工风险、维护员工劳动权益，特提醒部门务必于收到本邮件<span lang=EN-US>10</span>日内，完成续签或解除劳动合同相关流程<span lang=EN-US><o:p></o:p></span></span></p></div>");
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
		html.append("<td><font color='white' size='2'>岗位</font></td>");
		html.append("<td><font color='white' size='2'>任职资格</font></td>");
		html.append("<td><font color='white' size='2'>人员状态</font></td>");
		html.append("<td><font color='white' size='2'>入职日期</font></td>");
		html.append("<td><font color='white' size='2'>服务年限</font></td>");
		html.append("</tr>");
		for(Map<String,Object> mp : list){
			String pid = mp.get("PERSONNUMBER").toString();
			String name = mp.get("NAME").toString();
			String position = mp.get("POSITION").toString();
			String posiqual = (mp.get("POSIQUAL")==null)?"":mp.get("POSIQUAL").toString(); //任职资格
			String pstatus = (mp.get("PSTATUS")==null)?"":mp.get("PSTATUS").toString();  //人员状态
			String enterdate = DateUtil.formatTimestampToString((TIMESTAMP)mp.get("ENTERDATE"));//入职日期
			String years = mp.get("YEARS").toString();   //服务年限
			
			html.append("<tr align='center'>");
			html.append("<td><font size='2'>"+pid+"</font></td>");
			html.append("<td><font size='2'>" + name + "</font></td>");
			//设置displayName
		    html.append(setDisplayName(mp.get("DISNAME")));
		    html.append("<td><font size='2'>" +position + "</font></td>");
		    html.append("<td><font size='2'>" +posiqual + "</font></td>");
		    
		    html.append("<td><font size='2'>" +pstatus+ "</font></td>");
		    html.append("<td><font size='2'>" +enterdate + "</font></td>");
		  
		    html.append("<td><font size='2'>" +years+ "</font></td>");
			html.append("</tr>");
		}
		html.append("</table><br/>");
		 */
		htmlContent.append("<div>");
		htmlContent.append("<p class=MsoNormal><span lang=EN-US><o:p>&nbsp;</o:p></span></p>");
		 
		htmlContent.append("<div><p class=MsoNormal><span style='font-size:10.0pt'>附：员工劳动合同续签流程：<span lang=EN-US><o:p></o:p></span></span></p></div>");
		htmlContent.append("<div style='margin-left:21.0pt'><p class=MsoNormal><span style='font-size:10.0pt'>收到提醒邮件后，请部门根据员工实际工作表现进行评估、审批，确认是否同意与员工续签劳动合同<span lang=EN-US><o:p></o:p></span></span></p></div>"); 
		htmlContent.append("<div style='margin-left:37.5pt'><p class=MsoNormal><span lang=EN-US style='font-size:10.0pt'>&nbsp; a. </span><span style='font-size:10.0pt'>如部门内部协商一致，同意与员工续签劳动合同：员工直接主管安排与续签人员进行续签面谈，双方达成一致后由员工至总部HR<span lang=EN-US>/</span>部门接口人处签订《劳动合同续订书》办理续签手续；<span lang=EN-US><o:p></o:p></span></span></p></div>");
		htmlContent.append("<div style='margin-left:37.5pt'><p class=MsoNormal><span lang=EN-US style='font-size:10.0pt'>&nbsp; b. </span><span style='font-size:10.0pt'>如部门不同意与员工续签劳动合同：请部门务必于劳动合同期满前一个月（<span lang=EN-US>30</span>日）与员工协商解决、达成一致，并依据公司相关规定办理离职手续；<span lang=EN-US><o:p></o:p></span></span></p></div>"); 
		htmlContent.append("<div style='margin-left:37.5pt'><p class=MsoNormal><span lang=EN-US style='font-size:10.0pt'>&nbsp; c. </span><span style='font-size:10.0pt'>如员工在<span lang=EN-US>2008</span>年<span lang=EN-US>1</span>月<span lang=EN-US>1</span>日后，已连续两次签订固定期限劳动合同，则第三次签订时需签署无固定期限劳动合同。签署无固定期限劳动合同前，请与总部HR进行沟通确认。<span lang=EN-US><o:p></o:p></span></span></p></div>"); 
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
	 * 描述：司龄发送给部门负责人
	 * 2013-4-18 下午03:25:16 by xjw
	 * @version
	 * @param list
	 * @return
	 */
	public static String htmlContentYearOfWork(Set<Map<String,Object>> list){
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
		htmlContent.append("<div style='margin-left:21.0pt'><p class=MsoNormal><span style='font-size:10.0pt'>您好！所列员工入职周年纪念日即将到来，公司将通过邮件送上祝福，请您知悉<span lang=EN-US><o:p></o:p></span></span></p></div>");
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
		html.append("<td><font color='white' size='2'>岗位</font></td>");
		html.append("<td><font color='white' size='2'>任职资格</font></td>");
		html.append("<td><font color='white' size='2'>人员状态</font></td>");
		html.append("<td><font color='white' size='2'>入职日期</font></td>");
		html.append("<td><font color='white' size='2'>服务年限</font></td>");
		html.append("</tr>");
		for(Map<String,Object> mp : list){
			String pid = mp.get("PERSONNUMBER").toString();
			String name = mp.get("NAME").toString();
			String position = mp.get("POSITION").toString();
			String posiqual = (mp.get("POSIQUAL")==null)?"":mp.get("POSIQUAL").toString(); //任职资格
			String pstatus = (mp.get("PSTATUS")==null)?"":mp.get("PSTATUS").toString();  //人员状态
			String enterdate = DateUtil.formatTimestampToString((TIMESTAMP)mp.get("ENTERDATE"));//入职日期
			String years = mp.get("YEARS").toString();   //服务年限
			int yy = Utils.getInt(years)+1;
			html.append("<tr align='center'>");
			html.append("<td><font size='2'>"+pid+"</font></td>");
			html.append("<td><font size='2'>" + name + "</font></td>");
			//设置displayName
		    html.append(setDisplayName(mp.get("DISNAME")));
		    html.append("<td><font size='2'>" +position + "</font></td>");
		    html.append("<td><font size='2'>" +posiqual + "</font></td>");
		    
		    html.append("<td><font size='2'>" +pstatus+ "</font></td>");
		    html.append("<td><font size='2'>" +enterdate + "</font></td>");
		  
		    html.append("<td><font size='2'>" +yy+ "</font></td>");
			html.append("</tr>");
		}
		html.append("</table><br/>");
		//添加hmtl
		htmlContent.append(html);
		 */
		
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
	 * 
	 * 描述：获取发送内容
	 * 2013-2-19 上午11:24:58 by xjw
	 * @version
	 * @param list
	 * @return
	 */
	public static String htmlContent(Map<String,Object> mp){
		StringBuffer htmlContent = new StringBuffer();
		htmlContent.append("<html><head>");
		htmlContent.append("<style type='text/css'>");
		htmlContent.append("div {");
		htmlContent.append("line-height: 150%;");
		htmlContent.append("font-family: 宋体;");
		htmlContent.append("font-size: 14px;");
		htmlContent.append("}");
		htmlContent.append(".div28{");
		htmlContent.append("margin-left:28px;");
		htmlContent.append("line-height: 150%;");
		htmlContent.append("font-family: 宋体;");
		htmlContent.append("font-size: 14px;");
		htmlContent.append("}");
		htmlContent.append(".div50{");
		htmlContent.append("margin-left:50px;");
		htmlContent.append("line-height: 150%;");
		htmlContent.append("font-family: 宋体;");
		htmlContent.append("font-size: 14px;");
		htmlContent.append("}");
		htmlContent.append(".div72{");
		htmlContent.append("margin-left:72px;");
		htmlContent.append("line-height: 150%;");
		htmlContent.append("font-family: 宋体;");
		htmlContent.append("font-size: 14px;");
		htmlContent.append("}");
		htmlContent.append("td{");
		htmlContent.append("white-space: nowrap");
		htmlContent.append("}");
		htmlContent.append("</style>");
		htmlContent.append("</head>");
		htmlContent.append("<body>");
		htmlContent.append("<div style='line-height: 150%;font-family: 宋体;font-size: 14px;'>各位好！下面是合同即将到期的人员的名单:</div>");
		htmlContent.append("<div class='div28'>合同到期人员名单</div>");
		StringBuffer html = new StringBuffer();
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
		html.append("<td><font color='white' size='2'>职类</font></td>");
		html.append("<td><font color='white' size='2'>人员状态</font></td>");
		html.append("<td><font color='white' size='2'>入职日期</font></td>");
		
		html.append("<td><font color='white' size='2'>性别</font></td>");
		html.append("<td><font color='white' size='2'>年龄</font></td>");
		html.append("<td><font color='white' size='2'>政治面貌</font></td>");
		html.append("<td><font color='white' size='2'>毕业院校</font></td>");
		html.append("<td><font color='white' size='2'>专业</font></td>");
		html.append("<td><font color='white' size='2'>学历</font></td>");
		html.append("<td bgcolor='orange'><font color='white' size='2'>状态</font></td>");
		html.append("</tr>");
//		for(int i =0;i<list.size();i++){
//			Map<String,Object> mp = list.get(i);
			String pid = mp.get("PERSONNUMBER").toString();
			String name = mp.get("PNAME").toString();
			String city = (mp.get("NPLACE")==null)?"":mp.get("NPLACE").toString(); //城市
			String position = mp.get("POSITION").toString();
			String posiqual = (mp.get("POSIQUAL")==null)?"":mp.get("POSIQUAL").toString(); //任职资格
			String pstatus = (mp.get("PSTATUS")==null)?"":mp.get("PSTATUS").toString();  //人员状态
			String potype = (mp.get("POTYPE")==null)?"":mp.get("POTYPE").toString();   //职类
			String politics = (mp.get("POLITICS")==null)?"":mp.get("POLITICS").toString();  //政治面貌
			String enterdate = (mp.get("ENTERDATE")==null)?"":mp.get("ENTERDATE").toString(); 
			String sex="";
			Integer gender = Integer.parseInt(mp.get("GENDER").toString());
			if(gender==1)
				sex="男";
			else
				sex="女";
			String age= mp.get("AGE").toString();
			String school = (mp.get("SCHOOL")==null)?"":mp.get("SCHOOL").toString();  //毕业院校
			String specialty = (mp.get("SPECIALTY")==null)?"":mp.get("SPECIALTY").toString(); //专业
			String degreed = (mp.get("DEGREED")==null)?"":mp.get("DEGREED").toString(); //学历
			
			html.append("<tr align='center'>");
			html.append("<td><font size='2'>"+pid+"</font></td>");
			html.append("<td><font size='2'>" + name + "</font></td>");
			//设置displayName
		    html.append(setDisplayName(mp.get("DISNAME")));
		    html.append("<td><font size='2'>" +city+"</font></td>");
		    html.append("<td><font size='2'>" +position + "</font></td>");
		    html.append("<td><font size='2'>" +posiqual + "</font></td>");
		    
		    html.append("<td><font size='2'>" +potype+ "</font></td>");
		    html.append("<td><font size='2'>" +pstatus+ "</font></td>");
		    html.append("<td><font size='2'>" +enterdate + "</font></td>");
		  
		    html.append("<td><font size='2'>" +sex + "</font></td>");
		    html.append("<td><font size='2'>" +age+ "</font></td>");
		    html.append("<td><font size='2'>" +politics+ "</font></td>"); 
		    html.append("<td><font size='2'>" +school+ "</font></td>");
		    html.append("<td><font size='2'>" +specialty+ "</font></td>"); 
		    html.append("<td><font size='2'>" +degreed+ "</font></td>"); 
		    html.append("<td><font color='red' size='2'>合同将到期</font></td>"); 
//		}
		 html.append("</tr></table><br/>");
		//添加hmtl
		htmlContent.append(html);
//		htmlContent.append("<br/><img src='cid:xx.jpg'><br/>");
		htmlContent.append("------------------------------------------------------------------<br/>");
		htmlContent.append("<div>传真： 010- 5738 2188</div>");
		htmlContent.append("<div>网站： www.CreditEase.cn</div>");
		htmlContent.append("<div>地址： 北京市朝阳区建国路88号SOHO现代城C座16层</div>");
		htmlContent.append("<div>邮编： 100022</div>");
		htmlContent.append("</body>");
		htmlContent.append("</html>");
		return htmlContent.toString();
	}
	
	
	
	public static String htmlContentPort(String addrs,String strdate){
		StringBuffer htmlContent = new StringBuffer();
		htmlContent.append("<html><head>");
		htmlContent.append("</head>");
		htmlContent.append("<body>");
		htmlContent.append("<div style='line-height: 150%;font-family: 宋体;font-size: 14px;'>您好，地址:"+addrs+"，房租将于  "+strdate+" 到期，请及时确认本职场是否需要续租。</div>");
		htmlContent.append("<br/><img src='cid:xx.jpg'><br/>");
		htmlContent.append("------------------------------------------------------------------<br/>");
		htmlContent.append("<div>传真： 010- 5738 2188</div>");
		htmlContent.append("<div>网站： www.CreditEase.cn</div>");
		htmlContent.append("<div>地址： 北京市朝阳区建国路88号SOHO现代城C座16层</div>");
		htmlContent.append("<div>邮编： 100022</div>");
		htmlContent.append("</body>");
		htmlContent.append("</html>");
		return htmlContent.toString();
	}
	
	public static String htmlContentPayMoney(String addrs,String strdate){
		StringBuffer htmlContent = new StringBuffer();
		htmlContent.append("<html><head>");
		htmlContent.append("</head>");
		htmlContent.append("<body>");
		htmlContent.append("<div style='line-height: 150%;font-family: 宋体;font-size: 14px;'>您好，地址:"+addrs+"，房租请于  "+strdate+" 之前，完成付款。</div>");
		htmlContent.append("<br/><img src='cid:xx.jpg'><br/>");
		htmlContent.append("------------------------------------------------------------------<br/>");
		htmlContent.append("<div>传真： 010- 5738 2188</div>");
		htmlContent.append("<div>网站： www.CreditEase.cn</div>");
		htmlContent.append("<div>地址： 北京市朝阳区建国路88号SOHO现代城C座16层</div>");
		htmlContent.append("<div>邮编： 100022</div>");
		htmlContent.append("</body>");
		htmlContent.append("</html>");
		return htmlContent.toString();
	}
	
	public static String htmlContentSendPort(String addrs){
		StringBuffer htmlContent = new StringBuffer();
		htmlContent.append("<html><head>");
		htmlContent.append("</head>");
		htmlContent.append("<body>");
		htmlContent.append("<div style='line-height: 150%;font-family: 宋体;font-size: 14px;'>您好，地址:"+addrs+"；假期临近请确认是否需要提前申请房租付款申请？如需提前申请付款的请与相关人员申请。</div>");
		htmlContent.append("<br/><img src='cid:xx.jpg'><br/>");
		htmlContent.append("------------------------------------------------------------------<br/>");
		htmlContent.append("<div>传真： 010- 5738 2188</div>");
		htmlContent.append("<div>网站： www.CreditEase.cn</div>");
		htmlContent.append("<div>地址： 北京市朝阳区建国路88号SOHO现代城C座16层</div>");
		htmlContent.append("<div>邮编： 100022</div>");
		htmlContent.append("</body>");
		htmlContent.append("</html>");
		return htmlContent.toString();
	}
	/**
	 * 
	 * 描述：投诉管理的案件进度跟进的邮件内容
	 * 2013-10-23 下午06:42:04 by caoyong
	 * @version
	 * @param complain
	 * @param statusId
	 * @return
	 */
	public static String htmlContent2CaseChargeMan(Complain complain,int statusId,String first,String second,String three){
		StringBuffer htmlContent = new StringBuffer();
		htmlContent.append("<html><head>");
		htmlContent.append("</head>");
		htmlContent.append("<body>");
		if(statusId==3 || statusId==8 || statusId==13 || statusId==15 || statusId==16){
			if(complain.getFservicetypeid()==null){
				complain.setFservicetypeid(0);
			}
			htmlContent.append("<div style='line-height: 150%;font-family: 宋体;font-size: 14px;'>您好，"
					+(statusId==15?first:((2==complain.getFservicetypeid()||3==complain.getFservicetypeid())?
							second:three))
					+"！案件编号为\""+complain.getFnumber()+"\"的案件，投诉人\""
					+complain.getFcomplainanter()+"\"，当前状态为：\""
					+getContent(statusId)+"\"，需要您进行审批。要查看详情，请登陆系统，" 
					+"网址：<a href=\"http://easext.creditease.corp\">http://easext.creditease.corp</a></div>");
		}else{
			htmlContent.append("<div style='line-height: 150%;font-family: 宋体;font-size: 14px;'>您好，"
					+complain.getFresponsibleName()+"！案件编号为\""+complain.getFnumber()+"\"的案件，投诉人\""
					+complain.getFcomplainanter()+"\",当前状态为：\""
					+getContent(statusId)+"\"，"+(statusId==17?"请知悉":"请断续跟进")+"。要查看详情，请登陆系统，" 
					+"网址：<a href=\"http://easext.creditease.corp\">http://easext.creditease.corp</a></div>");
		}
		htmlContent.append("<br/><div style='line-height: 150%;font-family: 宋体;font-size: 14px;'>" +
		"<font color='grey'>本邮件为系统邮件，请勿回复</font></div>");
		htmlContent.append("<br/><img src='cid:xx.jpg'><br/>");
		htmlContent.append("------------------------------------------------------------------<br/>");
		htmlContent.append("<div>传真： 010- 5738 2188</div>");
		htmlContent.append("<div>网站： www.CreditEase.cn</div>");
		htmlContent.append("<div>地址： 北京市朝阳区建国路88号SOHO现代城C座16层</div>");
		htmlContent.append("<div>邮编： 100022</div>");
		htmlContent.append("</body>");
		htmlContent.append("</html>");
		return htmlContent.toString();
	}
	/**
	 * 
	 * 描述：投诉管理的案件要求反馈的邮件内容
	 * 2013-10-23 下午06:42:04 by caoyong
	 * @version
	 * @param complain
	 * @param statusId
	 * @return
	 */
	public static String htmlContent2FeedbackPerson(Complain complain,int statusId,String email){
		StringBuffer htmlContent = new StringBuffer();
		htmlContent.append("<html><head>");
		htmlContent.append("</head>");
		htmlContent.append("<body>");
		htmlContent.append("<div style='line-height: 150%;font-family: 宋体;font-size: 14px;'>您好，"
				+email+"！案件编号为\""+complain.getFnumber()+"\"的案件，当前状态为：\""
				+getContent(statusId)+"\"，需要您及时进行反馈。要查看详情，请登陆系统，用户名和密码一致\""+email 
				+"\"网址：<a href=\"http://easext.creditease.cn\">http://easext.creditease.cn</a></div>");
		htmlContent.append("<br/><div style='line-height: 150%;font-family: 宋体;font-size: 14px;'>" +
				"<font color='grey'>本邮件为系统邮件，请勿回复</font></div>");
		htmlContent.append("<br/><img src='cid:xx.jpg'><br/>");
		htmlContent.append("------------------------------------------------------------------<br/>");
		htmlContent.append("<div>传真： 010- 5738 2188</div>");
		htmlContent.append("<div>网站： www.CreditEase.cn</div>");
		htmlContent.append("<div>地址： 北京市朝阳区建国路88号SOHO现代城C座16层</div>");
		htmlContent.append("<div>邮编： 100022</div>");
		htmlContent.append("</body>");
		htmlContent.append("</html>");
		return htmlContent.toString();
	}
	private static String getContent(int statusId){
		String result = "";
		if(statusId==2){
			result = "继续调查";
		}else if(statusId==3){
			result = "被投诉部门调查处理待审批";
		}else if(statusId==4){
			result = "被投诉部门调查处理审批未通过";
		}else if(statusId==5){
			result = "被投诉部门调查处理审批已通过";
		}else if(statusId==6){
			result = "被投诉部门调查处理待反馈";
		}else if(statusId==7){
			result = "被投诉部门调查处理已反馈";
		}else if(statusId==8){
			result = "本部门实地调查待审批";
		}else if(statusId==9){
			result = "本部门实地调查审批已通过";
		}else if(statusId==10){
			result = "本部门实地调查审批未通过";
		}else if(statusId==11){
			result = "再次协助落实待反馈";
		}else if(statusId==12){
			result = "再次协助落实已反馈";
		}else if(statusId==13){
			result = "待结案初审";
		}else if(statusId==14){
			result = "结案初审未通过";
		}else if(statusId==15){
			result = "待结案终审";
		}else if(statusId==16){
			result = "结案终审未通过";
		}else if(statusId==17){
			result = "已结案";
		}
		return result;
	}
	
	/**
	 * 
	 * 描述：行政采购合同邮件内容
	 * 2014-6-6 下午04:19:44 by zhangxin
	 * @version
	 * @param addrs
	 * @param strdate
	 * @return
	 */
	public static String htmlAdminiPort(Map map){
		StringBuffer htmlContent = new StringBuffer();
		htmlContent.append("<html><head>");
		htmlContent.append("</head>");
		htmlContent.append("<body>");
		htmlContent.append("<div style='line-height: 150%;font-family: 宋体;font-size: 14px;'>您好，<br/>&nbsp;&nbsp;&nbsp;&nbsp;以下合同即将到期。请收到此邮件后登陆行政采购合同预警平台完成" +
				"【合同到期跟进】，如选择已续签系统不会再次发送提醒邮件。</div>");
		String info = HTMLTable.simpleHTMLTable(map,ConfigConst.ADMINI_PURC);
		htmlContent.append("<br/>"+info+"<br/>");
		htmlContent.append("<div style='line-height: 150%;font-family: 宋体;font-size: 14px;'>温馨提示：请使用公司统一的采购/保洁服务/绿植租赁/办公设备租赁" +
				"的合同模板，附合同、供应商营业执照副本扫描件、相关服务考核表进行申请。</div>");
		htmlContent.append("<br/><img src='cid:logo.png'><br/>");
		htmlContent.append("</body>");
		htmlContent.append("</html>");
		return htmlContent.toString();
	}
	
	
	
	
	
}
