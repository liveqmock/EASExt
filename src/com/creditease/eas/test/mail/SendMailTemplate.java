package com.creditease.eas.test.mail;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.creditease.eas.util.DateUtil;
public class SendMailTemplate {
	/**
	 * 
	 * 描述：获取中心的部门名称
	 * 2013-1-10 下午02:45:08 by ygq
	 * @version
	 * @param fdisplayname
	 * @return
	 */
//	public static String[] getDisplayMethod(Object fdisplayname){
//		if(fdisplayname == null){
//			return null;
//		}
//		String[] strs = fdisplayname.toString().split("_");
//		return strs;
//	}
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
//	/**
//	 * 
//	 * 描述：获得试用期到期人员的html信息(&nabla;向下的符号)
//	 * 2013-1-3 下午12:18:35 by ygq
//	 * @version
//	 * @param list
//	 * @return
//	 */
//	public static String htmlContent(List<Map<String,Object>> list){
//		String htmlContent = "各位好！<br/>"+
//		"根据《中华人民共和国劳动合同法》相关规定，用人单位与劳动者签订三年以上固定期限劳动合同，试用期不得超过六个月。<br/>"+
//		"为避免公司用工风险、保护员工用工权益，特提醒部门务必于员工试用期到期日前15个工作日内、完成转正/解除劳动关系相关手续的办理。<br/>"+ 
//		"以下为本团队即将转正人员名单，请查阅并参考邮件所附《员工转正审批流程》办理相关手续：<br/>";
//		String html = "<div style='line-height: 500%;font-family: 宋体;size=5;'>" +
//				"<table style='border-collapse: collapse;width:960px;line-height:150%;font-family: 宋体;size=5;' cellpadding='5' border='1'>" +
//				"<tr bgcolor='#0066cc' align='center'>" +
//				"<td><font color='white' size='2'>员工编号</font></td>" +
//				"<td><font color='white' size='2'>身份证姓名</font></td>" +
//				"<td height='20'><font color='white' size='2'>一&nbsp;&nbsp;&nbsp;&nbsp;级&nbsp;&nbsp;&nbsp;&nbsp;部&nbsp;&nbsp;&nbsp;&nbsp;门</font></td>" +
//				"<td height='20'><font color='white' size='2'>二&nbsp;&nbsp;级&nbsp;&nbsp;部&nbsp;&nbsp;门</font></td>" +
//				"<td height='20'><font color='white' size='2'>三&nbsp;&nbsp;级&nbsp;&nbsp;部&nbsp;&nbsp;门</font></td>" +
//				"<td height='20'><font color='white' size='2'>四 &nbsp;&nbsp;级&nbsp;&nbsp;部&nbsp;&nbsp;门</font></td>" +
//				"<td height='20'><font color='white' size='2'>五&nbsp;&nbsp;级&nbsp;&nbsp;部&nbsp;&nbsp;门</font></td>" +
//				"<td><font color='white' size='2'>城市</font></td>" +
//				"<td><font color='white' size='2'>岗&nbsp;&nbsp;&nbsp;位</font></td>" +
//				"<td><font color='white' size='2'>任职资格</font></td>" +
//				"<td><font color='white' size='2'>人员状态</font></td>" +
//				"<td><font color='white' size='2'>入&nbsp;职&nbsp;日&nbsp;期</font></td>" +
//				"<td><font color='white' size='2'>预计转正日期</font></td>" +
//				"<td bgcolor='orange'><font color='white' size='2'>预警类型</font></td>" +
//				"</tr>";
//		//<font style="font-family: 宋体;size=5">
//		for(int i =0;i<list.size();i++){
//			Map<String,Object> mp = list.get(i);
//			String personcode = (String)mp.get("PERSONCODE");
//			String personname = (String)mp.get("PERSONNAME");
//			String city = (mp.get("CITY")==null)?"":mp.get("CITY").toString();//城市
//			String positionname = (String)mp.get("POSITIONNAME");//岗位
//			String pqfname = (String)mp.get("PQFNAME");//任职资格
//			String fenterdate = mp.get("FENTERDATE").toString();
//			String probzz = mp.get("PROBZZ").toString();
//			String stat = mp.get("STATUS").toString();
////			String fenterdate = DateUtil.formatTimestampToString((TIMESTAMP)mp.get("FENTERDATE"));//入职日期
////			String PROBZZ = DateUtil.formatTimestampToString((TIMESTAMP)mp.get("PROBZZ"));//预计转正日期
//			int entmonth = DateUtil.monthsBetween(fenterdate);
//			html += "<tr>" +
//			"<td><font size='2'>"+personcode+"</font></td>" +
//			"<td><font size='2'>" + personname + "</font></td>";
//			//设置displayName
//			html += setDisplayName(mp.get("FDISPLAYNAME"));
//			html +=	"<td><font size='2'>" +city+"</font></td>" +
//					"<td><font size='2'>" +positionname + "</font></td>" +
//					"<td><font size='2'>" +pqfname + "</font></td>" +
//					"<td><font size='2'>实习</font></td>"+
//					"<td><font size='2'>" +fenterdate+ "</font></td>"+
//					"<td><font size='2'>" +probzz+ "</font></td>"+
//					"<td><font size='2'>转正预警</font></td>";
//		}
//		html += "</tr></table></div>";
//		html += "附：员工转正审批流程：<br/>"+
//			"1、各部门人事接口人收到提醒邮件后，立即梳理下月转正人员信息，提交本部门/团队负责人确认是否同意为员工办理转正手续；<br/>"+
//			"2、由本部门/团队负责人根据员工实际工作情况进行评估、审批及办理后续手续：<br/>"+
//			"▉  如部门同意员工转正：<br/>"+
//			"&nbsp;&nbsp;&nbsp;a.员工直接主管安排与即将转正员工进行试用期面谈，双方达成一致后由员工本人填写《新员工转正评估表》并提交其上级予以审批；<br/>"+
//			"&nbsp;&nbsp;&nbsp;b.员工直接主管、部门负责人完成签批手续后将附件直接提交人力资源与行政部（外埠地区将附件扫描件以邮件形式予以呈报）；<br/>"+
//			"&nbsp;&nbsp;&nbsp;c.如该转正申请未通过公司级审批，HR将予以邮件反馈。<br/>"+
//			"▉ 如部门不同意员工转正：请部门务必于试用期到期前与员工协商一致、并依据公司相关规定办理正式离职手续。<br/>"+
//			"感谢您对人力资源与行政部工作的大力支持！ 谢谢。<br/>";
//		htmlContent += html;
//		return htmlContent;
//	}
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
		html.append("<td><font color='white' size='2'>人员状态</font></td>");
		html.append("<td><font color='white' size='2'>入职日期</font></td>");
		html.append("<td><font color='white' size='2'>预计转正日期</font></td>");
		html.append("<td bgcolor='orange'><font color='white' size='2'>预警类型</font></td>");
		html.append("</tr>");
		for(int i =0;i<list.size();i++){
			Map<String,Object> mp = list.get(i);
			String personcode = (String)mp.get("PERSONCODE");
			String personname = (String)mp.get("PERSONNAME");
			String city = (mp.get("CITY")==null)?"":mp.get("CITY").toString();//城市
			String positionname = (String)mp.get("POSITIONNAME");//岗位
			String pqfname = (String)mp.get("PQFNAME");//任职资格
			String fenterdate = mp.get("FENTERDATE").toString();
			String probzz = mp.get("PROBZZ").toString();
			String status = mp.get("STATUS").toString();
//			String fenterdate = DateUtil.formatTimestampToString((TIMESTAMP)mp.get("FENTERDATE"));//入职日期
//			String PROBZZ = DateUtil.formatTimestampToString((TIMESTAMP)mp.get("PROBZZ"));//预计转正日期
			int entmonth = DateUtil.monthsBetween(fenterdate);
			html.append("<tr align='center'>");
			html.append("<td><font size='2'>"+personcode+"</font></td>");
			html.append("<td><font size='2'>" + personname + "</font></td>");
			//设置displayName
		     html.append(setDisplayName(mp.get("FDISPLAYNAME")));
		     html.append("<td><font size='2'>" +city+"</font></td>");
    		 html.append("<td><font size='2'>" +positionname + "</font></td>");
			 html.append("<td><font size='2'>" +pqfname + "</font></td>");
			 html.append("<td><font size='2'>" +status+ "</font></td>");
			 html.append("<td><font size='2'>" +fenterdate+ "</font></td>");
			 html.append("<td><font size='2'>" +probzz+ "</font></td>");
			 html.append("<td><font size='2'>"+((entmonth <6)?"试用期将到期":"试用期已到期") +"</font></td>");
		}
		 html.append("</tr></table><br/>");
		 html.append("<div>附：员工转正审批流程：</div>");
		 html.append("<div class='div28'>1、主管级（含）以下员工</div>");
		 html.append("<div class='div50'>由部门根据员工日常工作表现进行评估、审批，并根据审批结果进行相关手续办理：</div>"); 
		 html.append("<div class='div50'>▉如部门同意员工转正：</div>");
		 html.append("<div class='div72'>a.员工直接上级与即将转正员工进行试用期面谈，双方达成一致后由员工本人填写《新员工转正评估表》并提交部门审批；</div>"); 
		 html.append("<div class='div72'>b.部门完成审批手续后将《新员工转正评估表》提交总部人力资源与行政部备案（外埠地区将表单以扫描件形式予以呈报）。</div><br/>"); 
		 html.append("<div class='div50'>▉如部门不同意员工转正</div>"); 
		 html.append("<div class='div72'>a.请部门务必于试用期到期前与员工协商一致、依据公司相关规定办理正常离职手续。</div><br/>"); 
		 html.append("<div class='div28'>2、经理级以上员工</div>");
		 html.append("<div class='div50'>由部门根据员工实际工作表现进行评估、审核，并根据审核结果进行相关手续办理：</div>"); 
		 html.append("<div class='div50'>▉如部门同意员工转正：</div>");
		 html.append("<div class='div72'>a.员工直接上级与即将转正员工进行试用期面谈，双方达成一致后由员工本人填写《新员工转正评估表》并提交部门审核；</div>");
		 html.append("<div class='div72'>b.部门完成审核手续后将《新员工转正评估表》提交总部人力资源与行政部审批（外埠地区将表单以扫描件形式予以呈报）；</div>"); 
		 html.append("<div class='div72'>c.如该转正申请未通过审批，总部人力资源与行政部将予以邮件反馈。</div><br/>");
		 html.append("<div class='div50'>▉如部门不同意员工转正</div>");
		 html.append("<div class='div72'>a.部门将不同意员工转正的意见及相关材料提交总部人力资源与行政部审批（外埠地区将表单以扫描件形式予以呈报）；</div>"); 
		 html.append("<div class='div72'>b.根据员工实际情况进行相关手续办理。</div><br/>");
		 html.append("<div>感谢您对人力资源与行政部工作的大力支持！谢谢。</div>");
		//添加hmtl
		htmlContent.append(html);
		htmlContent.append("</div><br/><img src='cid:xx.jpg'><br/>");
		htmlContent.append("------------------------------------------------------------------<br/>");
		htmlContent.append("<div>传真： 010- 5738 2188</div>");
		htmlContent.append("<div>网站： www.CreditEase.cn</div>");
		htmlContent.append("<div>地址： 北京市朝阳区建国路88号SOHO现代城C座16层</div>");
		htmlContent.append("<div>邮编： 100022</div>");
		htmlContent.append("</body>");
		return htmlContent.toString();
	}
	/**
	 * 
	 * 描述：
	 * 2013-2-1 上午11:04:30 by ygq
	 * @version
	 * @return
	 */
	public static String info(){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("PERSONCODE", "12345");//员工编号
		map.put("PERSONNAME", "张三");//身份证姓名
		//各级部门
		map.put("FDISPLAYNAME", "宜信_宜信卓越财富投资管理（北京）有限公司_投资理财一部_武汉分中心_江汉区浦发财富管理中心 _ClustetC_CCA团队_CCA TeamC1");
		map.put("CITY", "北京");//城市
		map.put("POSITIONNAME", "软件工程师");//岗位
		//map.put("DEPTNAME", "12345");
		map.put("PQFNAME", "");//任职资格
		map.put("STATUS", "试用");//人员状态：这个暂时不确定:要是实习什么的，就需要再连一张表查询下
		map.put("FENTERDATE", "2012-9-1");//入职日期
		map.put("PROBZZ", "2013-3-1");//预计转正日期
		map.put("WARNTYPE", "转正预警");// 预警类型
		list.add(map);
		Map<String,Object> map2 = new HashMap<String,Object>();
		map2.put("PERSONCODE", "12345");//员工编号
		map2.put("PERSONNAME", "李四");//身份证姓名
		//各级部门
		map2.put("FDISPLAYNAME", "宜信_宜信卓越财富投资管理（北京）有限公司_投资理财三部_南京分中心_鼓楼区紫峰大厦财富管理一中心_T1");
		map2.put("CITY", "北京");//城市
		map2.put("POSITIONNAME", "软件工程师");//岗位
		//map.put("DEPTNAME", "12345");
		map2.put("PQFNAME", "");//任职资格
		map2.put("STATUS", "实习");//人员状态：这个暂时不确定:要是实习什么的，就需要再连一张表查询下
		map2.put("FENTERDATE", "2012-9-1");//入职日期
		map2.put("PROBZZ", "2013-3-1");//预计转正日期
		map2.put("WARNTYPE", "转正预警");// 预警类型
		list.add(map2);
		String str = htmlContent(list);
		return str;
	}
	/**
	 * 读文件
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
//	 public static String  getUserFile(String filePath) throws IOException{
//	    	File file = new File(filePath);
//	    	  FileInputStream fis = new FileInputStream(file);
//	    	  BufferedReader br = new BufferedReader(new InputStreamReader(fis));
//	    	  StringBuffer sb = new StringBuffer();
//	    	  String line=null;  
//	    	  while((line=br.readLine())!=null)  
//	    	  {  
//	    		  sb.append(line);
//	    	  }  
//	    	  return sb.toString();
//	 }
	//"626264481@qq.com"
//	public static  void sendMail(){
//		 boolean flag = false;
//		 String fromAddress = "HR@creditease.cn";
//			// 邮件内容
//		 try{
//			String htmlContent = SendMailTemplate.info();
//			 MailSenderInfo ms = new MailSenderInfo();
//			 Properties pr = ms.getProperties();
//			 String[] toAddress = pr.getProperty("mailInfos").split(",");
//			flag = SendMailUtil.sendMailToManyPerson(fromAddress, toAddress, "转正预警测试", htmlContent);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		if(flag){
//			System.out.println("发送成功！");
//		}else{
//			System.out.println("发送失败！");
//		}
//	} 
//	public static void main(String[] args) {
//		sendMail();
//	}
}
