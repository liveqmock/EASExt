package com.creditease.eas.test.common.email;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.creditease.eas.common.bean.Email;
import com.creditease.eas.common.vo.MailInfo;
import com.creditease.eas.util.mail.RSAUtil;

public class EmailTest {

	private static RSAUtil t = new RSAUtil();
	/**
	 * 测试Eamil类
	 * @author lining
	 * @since 2014-4-23
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {

		List<MailInfo> mailInfos = new ArrayList<MailInfo>();
		MailInfo mailInfo = new MailInfo();
		mailInfo.setInfoId("12345");
		mailInfo.setTos(new String[]{"V-ningli@creditease.cn"});
		//mailInfo.setCc(new String[]{"gaoquanyang@creditease.cn","v-minggaoguo@creditease.cn"});
		//mailInfo.setBcc(new String[]{"v-xinzhang@creditease.cn","kekeyang@creditease.cn"});
		mailInfo.setFrom("hr@creditease.cn");
		mailInfo.setPassword(t.encodeSignautre("Yunying2013"));
		mailInfo.setTheme("员工生日提醒（此邮件为系统发送，请勿回复）测试");
		StringBuffer text = new StringBuffer();
		text.append("<html dir='ltr'><head><meta http-equiv='Content-Type' content='text/html; charset=gb2312'><style id='owaParaStyle'>P{margin-top:0;margin-bottom:0;}</style></head><body ocsi='0' fpstyle='1'><div style='direction: ltr;font-family: Tahoma;color: #000000;font-size: 10pt;'><div style='font-family: Times New Roman; color: #000000; font-size: 16px'><font size='3'>尊敬的领导：</font><br><div><div style='direction:ltr; font-family:Tahoma; color:#000000; font-size:10pt'><div style='font-family:Times New Roman; color:#000000; font-size:16px'><div><div style='direction:ltr; font-family:Tahoma; color:#000000; font-size:10pt'><div style='direction:ltr; font-family:Tahoma; color:#000000; font-size:10pt'><div style='font-family:Times New Roman; color:#000000; font-size:16px'><div><div style='direction:ltr; font-family:Tahoma; color:#000000; font-size:10pt'><div style='font-family:Times New Roman; color:#000000; font-size:16px'><div><div style='direction:ltr; font-family:Tahoma; color:#000000; font-size:10pt'><br><div style='font-family:Times New Roman; color:#000000; font-size:16px'><div><div style='direction:ltr; font-family:Tahoma; color:#000000; font-size:10pt'><p><font size='3'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 您好！所列员工的生日即将到来，公司将通过邮件及短信送上祝福，请您知悉</font></p><p><br></p><div style='font-family:Times New Roman; color:#000000; font-size:16px'><div><div style='font-family:Tahoma; direction:ltr; color:#000000; font-size:10pt'><div style='font-family:Times New Roman; color:#000000; font-size:16px'><div><div style='font-family:Tahoma; direction:ltr; color:#000000; font-size:10pt'><div><div style='font-family:Tahoma; font-size:13px'><table cellpadding='8' border='2' height='86' width='988'><tbody><tr align='center' bgcolor='#0066cc' height='20'><th><font color='white' size='3'>员工编号</font></th><th><font color='white' size='3'>姓名</font></th><th><font color='white' size='3'>一级部门</font></th><th><font color='white' size='3'>入职日期</font></th><th><font color='white' size='3'>出生日期</font></th></tr><tr align='center'><td><font>201210310366</font></td><td><font>李野</font></td><td><font>车贷综合管理部</font></td><td><font>2011-1-24</font></td><td><font>1982-4-30</font></td></tr></tbody></table><p><br></p><p><img src='cid:image_C4041CFA7DEB9E2214A8D53265F137C6.JPG'></p><p><span lang='EN-US'><font face='Calibri'><font size='3'>------------------------------------------------------------------</font></font></span><span lang='EN-US'><font face='Calibri' size='3'>&nbsp;</font></span></p><p><span lang='EN-US'></span><font size='3'><span style='font-family:宋体'>传真：</span><span lang='EN-US'><font face='Calibri'> 010- 5738 2188</font></span></font></p><p><font size='3'><span lang='EN-US'></span></font><font size='3'><span style='font-family:宋体'>网站：</span><span lang='EN-US'><font face='Calibri'><a href='http://www.CreditEase.cn' target='_blank'>www.CreditEase.cn</a></font></span></font></p><p><font size='3'><span lang='EN-US'></span></font><font size='3'><span style='font-family:宋体'>地址：</span><font face='Calibri'></font><span style='font-family:宋体'>北京市朝阳区建国路</span><span lang='EN-US'><font face='Calibri'>88</font></span><spn style='font-family:宋体'>号</span><span lang='EN-US'><font face='Calibri'>SOHO</font></span><span style='font-family:宋体'>现代城</span><span lang='EN-US'><font face='Calibri'>C</font></span><span style='font-family:宋体'>座</span><span lang='EN-US'><font face='Calibri'>16</font></span><span style='font-family:宋体'>层</span></font></p><p><span style='font-family:宋体; font-size:10.5pt'>邮编：</span><span style='font-family:'Calibri','sans-serif'; font-size:10.5pt' lang='EN-US'>&nbsp;&nbsp; 100022</span></p></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></div></body></html>");
		mailInfo.setContent(text.toString());
		mailInfo.setImages(new String[]{"F:\\image\\LOGO\\image_C4041CFA7DEB9E2214A8D53265F137C6.JPG"});
		mailInfo.setFiles(new String[]{"F:\\mail\\Outlook+2010用户使用手册.docx"});

		mailInfos.add(mailInfo);
		
		Email email = new Email(mailInfos);
		Map<String,Object> flag = email.sendAsOne(); 
		System.out.println(flag.get("hr@creditease.cn"));
	}

}
