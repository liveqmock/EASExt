package com.creditease.eas.util.mail;

import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.creditease.eas.compliance.bean.NextCommunicateTimeContent;
import com.creditease.eas.compliance.bean.NextCommunicateTimeForEmail;
import com.creditease.eas.util.StringUtil;

public class InvestigatonEmailUtil {

	private static Logger logger = Logger
			.getLogger(InvestigatonEmailUtil.class);

	/**
	 * 拼接邮件内容
	 * 
	 * @param list
	 * @return
	 */
	public static String getHtmlContent(List<NextCommunicateTimeContent> list) {
		StringBuffer htmlContent = new StringBuffer();
		htmlContent.append("<html><head>");
		htmlContent.append("</head>");
		htmlContent.append("<body>");

		htmlContent
				.append("<div style='line-height: 150%;font-family: 宋体;font-size: 14px;font-weight: bold;' align='center'>"
						+ StringUtil.getTomorrowTime() + "需要沟通案件如下");

		htmlContent
				.append(
						"<div style='font-family: 宋体; font-size: 14px;font-weight: normal'>")
				.append("<table border='2'>").append("<tr>").append(
						"<th>序号</th>").append("<th>案件编号</th>").append(
						"<th>案件详细描述</th>").append("<th>案件负责人</th>").append(
						"<th>最近一次沟通时间</th>").append("<th>操作</th>").append(
						"</tr>");

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				NextCommunicateTimeContent next = list.get(i);
				htmlContent
						.append("<tr>")

						.append("<td>"+(i + 1)+"</td>")

						.append("<td>"+next.getFnumber()+"</td>")

						.append("<td>"+((next.getFdetaildescrip()==null || "".equals(next.getFdetaildescrip()))?"":next.getFdetaildescrip())+"</td>")

						.append("<td>"+next.getFresponsiblename()+"</td>")

						.append("<td>"+next.getLastcommunicatetime()+"</td>")

						.append("<td>")

						.append(
								"<a href='http://localhost:8080/compliance/investigation!view?investigation.id="
										+ next.getInvestigatonid()
										+ "'>"
										+ "查看" + "</a>")

						.append("</td>")

						.append("</tr>");
			}
		}

		htmlContent.append("</table>").append("</div>");

		htmlContent
				.append("<div style='line-height: 150%;font-family: 宋体;font-size: 14px;' align='left'>"
						+ "<font color='grey'>本邮件为系统邮件，请勿回复</font></div>");
		htmlContent.append("<img src='cid:xx.jpg'><br/>");
		htmlContent
				.append("------------------------------------------------------------------<br/>");
		htmlContent.append("<div align='left'>传真： 010- 5738 2188</div>");
		htmlContent.append("<div align='left'>网站： www.CreditEase.cn</div>");
		htmlContent.append("<div align='left'>地址： 北京市朝阳区建国路88号SOHO现代城C座16层</div>");
		htmlContent.append("<div align='left'>邮编： 100022</div>");
		htmlContent.append("</body>");
		htmlContent.append("</html>");
		return htmlContent.toString();
	}

	/**
	 * 发送邮件
	 * 
	 * @param list
	 */
	public static void sendEmail(List<NextCommunicateTimeForEmail> list) {
		if (list != null && list.size() > 0) {
			MailSenderInfo ms = new MailSenderInfo();
			Properties pr = ms.getProperties();
			String fromAddress = pr.getProperty("HEGUI_USERNAME");
			String logoPath = pr.getProperty("logoPath");
			String[] ccs = null;

			for (int i = 0; i < list.size(); i++) {
				NextCommunicateTimeForEmail nextCommunicateTimeForEmail = list
						.get(i);
				String[] toAddress = { nextCommunicateTimeForEmail.getToEmail() };

				if (nextCommunicateTimeForEmail.getContent() != null
						&& nextCommunicateTimeForEmail.getContent().size() > 0) {
					String htmlContent = getHtmlContent(nextCommunicateTimeForEmail
							.getContent());
					boolean sendSuccess = SendMailUtil.sendComplianceMail(
							fromAddress, toAddress, "明天需要沟通内容统计", htmlContent,
							logoPath, ccs);
					logger.info(sendSuccess ? "明天需沟通内容发送邮件成功！"
							: "明天需沟通内容发送邮件失败！");
				}
			}
		}

	}

	public static void main(String[] args) {
		MailSenderInfo ms = new MailSenderInfo();
		Properties pr = ms.getProperties();
		String fromAddress = pr.getProperty("USERNAME");
		String[] toAddress = { "v-ruyili@creditease.cn" };
		// toAddress =
		// pr.getProperty("toAddress").split(",");//代码正式提交时邮件接收人为接口人邮箱，此行注释掉
		String logoPath = pr.getProperty("logoPath");
		String[] ccs = null;
		/*
		 * String htmlContent = getHtmlContent(new
		 * ArrayList<NextCommunicateTimeForEmail>());
		 * 
		 * boolean sendSuccess = SendMailUtil.sendComplianceMail(fromAddress,
		 * toAddress, "案件进度跟进", htmlContent, logoPath, ccs);
		 * 
		 * System.out.println(sendSuccess);
		 */
	}
}
