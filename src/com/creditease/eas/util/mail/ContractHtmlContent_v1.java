package com.creditease.eas.util.mail;

import java.util.Date;
import java.util.List;
import java.util.Map;

import oracle.sql.TIMESTAMP;

import com.creditease.eas.util.DateUtil;
import com.creditease.eas.util.StringUtil;

/**
 * 预警公用的方法
 * @WarnCommonMethod.java
 * created at 2013-2-1 下午01:52:41 by ygq
 * 
 * @author ygq({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class ContractHtmlContent_v1 {

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
		htmlContent.append("<div style='line-height: 150%;font-family: 宋体;font-size: 14px;'>各位好！</div>");
		htmlContent.append("<div class='div28'>根据《中华人民共和国劳动合同法》相关规定，用人单位与劳动者签订三年以上固定期限劳动合同，试用期不得超过六个月。</div>");
		htmlContent.append("<div class='div28'>为避免公司用工风险、保护员工用工权益，特提醒部门务必于员工试用期到期日前15个工作日内、完成转正/解除劳动关系相关手续的办理。</div>"); 
		htmlContent.append("<div class='div28'>以下为本团队即将转正人员名单，请查阅并参考邮件所附《员工转正审批流程》办理相关手续：</div>");
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
			html.append("</tr>");
		}
		 html.append("</table><br/>");
		 html.append("<div>附：员工劳动合同续签流程：</div>");
		 html.append("<div class='div28'>1. 各部门人事接口人收到提醒邮件后，立即与本部门/团队负责人确认是否同意为员工办理续签手续；</div>");
		 html.append("<div class='div28'>2. 由本部门/团队负责人根据员工实际工作情况进行评估、审批及办理后续操作：</div>"); 
		 html.append("<div class='div50'>&nbsp;&nbsp;1. 如部门内部协商一致、同意与员工续签劳动合同，则：	</div>");
		 html.append("<div class='div50'>&nbsp;&nbsp;2. 员工直接主管安排与劳动合同续签人员进行续签面谈，双方达成一致后由员工至总部HR/部门接口人处签订《劳动合同续签协议书》；</div>"); 
		 html.append("<div class='div50'>&nbsp;&nbsp;3. 如该转正申请未通过公司级审批，HR将予以邮件反馈。</div><br/>"); 
		 html.append("<div class='div28'>1. 如部门不同意与员工续签劳动合同：请部门务必于劳动合同到期前30个工作日前与员工协商一致、并依据公司相关规定办理正式离职手续。</div>"); 
		
		//添加hmtl
		htmlContent.append(html);
//		htmlContent.append("</div><br/><img src='cid:xx.jpg'><br/>");
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
//		htmlContent.append("</div><br/><img src='cid:xx.jpg'><br/>");
		htmlContent.append("------------------------------------------------------------------<br/>");
		htmlContent.append("<div>传真： 010- 5738 2188</div>");
		htmlContent.append("<div>网站： www.CreditEase.cn</div>");
		htmlContent.append("<div>地址： 北京市朝阳区建国路88号SOHO现代城C座16层</div>");
		htmlContent.append("<div>邮编： 100022</div>");
		htmlContent.append("</body>");
		htmlContent.append("</html>");
		return htmlContent.toString();
	}
	
}
