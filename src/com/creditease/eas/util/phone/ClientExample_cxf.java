package com.creditease.eas.util.phone;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.creditease.eas.util.Utils;
import com.creditease.eas.warn.bean.Jumbosmsv;
import com.creditease.eas.warn.bean.WaringDetail;
import com.creditease.eas.warn.kingdee.query.WaringDetailQuery;
import com.creditease.eas.warn.service.JumbosmsvService;
import com.creditease.eas.warn.service.WaringdetailService;
import com.creditease.eas.warn.vo.QueryData;
import com.creditease.service.ws.DetailsJaxb;
import com.creditease.service.ws.MessageReqJaxb;
import com.creditease.service.ws.MessageResJaxb;
import com.creditease.service.ws.MessageService;
import com.creditease.service.ws.MessageServiceJaxbImplService;

//@Service("jumbosmsvServiceImpl")
//@Controller
@Scope("prototype")
public class ClientExample_cxf {
	
	@Autowired
	private JumbosmsvService jumbosmsvServiceImpl;
	
	@Autowired
	private JumbosmsvService waringdetailServiceImpl;
	public static MessageService getService() {
		MessageServiceJaxbImplService service = new MessageServiceJaxbImplService();
		return service.getMessageServicePort();
	}

	public static void main(String[] args) throws Exception {
		Scanner s = new Scanner(System.in);
		while (true) {
			init();
			String order = s.nextLine();
			if (order.trim().equals("0")) {				
				System.exit(1);
			}
			else if (order.trim().equals("1")) {
				testBatchOrderMsgSend();
			}
			else if (order.trim().equals("2")) {
				testBatchCustomMessageSend();
			}
			else if (order.trim().equals("3")) {
				testOrderMsgSend3();
			} else {
				System.err.println("指令不正确!");
				continue;
			}
		}
	}

	//多手机号不同短信内容批量发送
	public static void testBatchOrderMsgSend() {
		MessageReqJaxb req = new MessageReqJaxb(); // webservice参数
		DetailsJaxb[] ds = new DetailsJaxb[10]; // 短信内容数组
		
		for (int i = 0; i < ds.length; i++) {
			DetailsJaxb d =  new DetailsJaxb();
			String[] keywords = new String[3];// 模板关键字
			keywords[0] = "custName|xl";
			keywords[1] = "amount|1";
			keywords[2] = "day|2";
			d.setMobile("15210900847"); // 电话号码
			d.setKeywords(keywords);
			d.setPriority("5"); // 优先级
			ds[i] = d;
		}
		
		req.setOrgNo("1357");// 组织机构号
		req.setBatchId("000001");// 批次号
		req.setTypeNo("1001");// 模板号
		req.setVersion("2.00");// 接口版本
		req.setDetails(ds);
		
		MessageResJaxb res = getService().batchOrderMsgSendJaxb(req);
		System.err.println(res.getRetInfo());
	}

	//多手机号发送相同短信内容批量发送
	public static void testBatchCustomMessageSend() {
		MessageReqJaxb req = new MessageReqJaxb(); // webservice参数
		DetailsJaxb[] ds = new DetailsJaxb[1]; // 短信内容数组
		
		DetailsJaxb d =  new DetailsJaxb();
		d.setMobile("13699249740,13810003023,13501200917");// 电话号码
		d.setPriority("5"); // 优先级
		d.setContent("这是一条客户端测试短信");// 短信内容
//		d.setDetailType("000000");
		ds[0] = d;
		
		req.setOrgNo("2545");// 组织机构号
		req.setBatchId("000002");// 批次号
		req.setTypeNo("0003");// 模板号
		req.setVersion("2.00");// 接口版本
		req.setDetails(ds);
		
		MessageResJaxb res = getService().batchCustomMessageSendJaxb(req);
		System.err.println(res.getRetInfo());
	}
	
	public static void testOrderMsgSend3() throws Exception {
		MessageReqJaxb req = new MessageReqJaxb(); // webservice参数
		DetailsJaxb[] ds = new DetailsJaxb[1]; // 短信内容数组
		DetailsJaxb d =  new DetailsJaxb();
		String[] keywords = new String[1];// 模板关键字
		String strs="今天天气很不错。（测试）";
		
				
		keywords[0] = "custName|"+strs;
//		d.setMobile("18611198342"); // 电话号码
		d.setMobile("18610775451"); // 电话号码
		d.setKeywords(keywords);
		d.setPriority("2"); // 优先级
		ds[0] = d;
		
		req.setOrgNo("2267");// 组织机构号
		req.setBatchId("20120426");// 批次号
		req.setTypeNo("5360");// 模板号
		req.setVersion("2.00");// 接口版本
		req.setDetails(ds);
		
		MessageResJaxb res = getService().orderMsgSendJaxb(req);
		System.out.println("返回信息："+res.getRetInfo()+"  返回码："+res.getRetCode());
		
	}

	// 单条发送
	public static void testOrderMsgSend2() throws Exception {
		MessageReqJaxb req = new MessageReqJaxb(); // webservice参数
		DetailsJaxb[] ds = new DetailsJaxb[1]; // 短信内容数组
		DetailsJaxb d =  new DetailsJaxb();
		String[] keywords = new String[1];// 模板关键字
		//String strs="今天天气很不错。";
		
		ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
		JumbosmsvService service = (JumbosmsvService)app.getBean("jumbosmsvServiceImpl");
		Jumbosmsv jumb = service.getJumbosmsvById(2);
		String content = null;
		
//		WaringdetailService wdservice = (WaringdetailService)app.getBean("waringdetailServiceImpl");
//		List list = wdservice.findAll();
//		List list = WaringDetailQuery.getWaringDetailList();
//		System.err.println("size: "+list.size());
//		for (int i = 0; i < list.size(); i++) {
//			content = jumb.getContent();
//			QueryData qd=(QueryData) list.get(i);
//			if(qd.getName()!=null){
//				content = content.replaceAll("\\$\\{name\\}", qd.getName());
//				content = content.replaceAll("\\$\\{birthday\\}",Utils.getDate(qd.getBirthday()));
//				System.err.println("content: " + content);
//				
//				keywords[0] = "custName|"+content;
//				d.setMobile("18611198342"); // 电话号码
//				d.setKeywords(keywords);
//				d.setPriority("2"); // 优先级
//				ds[0] = d;
//				
//				req.setOrgNo("2267");// 组织机构号
//				req.setBatchId("20120426");// 批次号
//				req.setTypeNo("5179");// 模板号
//				req.setVersion("2.00");// 接口版本
//				req.setDetails(ds);
//				
//				MessageResJaxb res = getService().orderMsgSendJaxb(req);
//				System.err.println(res.getRetInfo());
//				
//				
//			}
//		}
		
	}
	// 单条发送
	public static void testOrderMsgSend() throws Exception {
		MessageReqJaxb req = new MessageReqJaxb(); // webservice参数
		DetailsJaxb[] ds = new DetailsJaxb[1]; // 短信内容数组
		DetailsJaxb d =  new DetailsJaxb();
		String[] keywords = new String[1];// 模板关键字
		
		ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
		JumbosmsvService service = (JumbosmsvService)app.getBean("jumbosmsvServiceImpl");
		Jumbosmsv jumb = service.getJumbosmsvById(2);
		String content = null;
		
//		List list = WaringDetailQuery.getWaringDetailList();
//		System.err.println("size: "+list.size());
//		for (int i = 0; i < list.size(); i++) {
//			content = jumb.getContent();
//			QueryData qd=(QueryData) list.get(i);
//			if(qd.getName()!=null){
//				content = content.replaceAll("\\$\\{name\\}", qd.getName());
//				content = content.replaceAll("\\$\\{birthday\\}",Utils.getDate(qd.getBirthday()));
//				System.err.println("content: " + content);
//				
//				keywords[0] = "custName|"+content;
////				d.setMobile("13070189337"); // 电话号码
//				d.setMobile("18610775451"); // 电话号码
//				d.setKeywords(keywords);
//				d.setPriority("2"); // 优先级
//				ds[0] = d;
//				
//				req.setOrgNo("2267");// 组织机构号
//				req.setBatchId("20120426");// 批次号
////				req.setTypeNo("5179");// 模板号
//				req.setTypeNo("5360");// 模板号
//				req.setVersion("2.00");// 接口版本
//				req.setDetails(ds);
//				
//				MessageResJaxb res = getService().orderMsgSendJaxb(req);
//				System.out.println(res.getRetInfo()+"  返回码："+res.getRetCode());
//				
//				
//			}
//		}
		
	}

	private static void init() {
		System.out
				.println("请按指示操作:\n 0:退出\n 1:测试batchOrderMsgSend \n 2:测试batchCustomMessageSend \n 3:测试orderMsgSend");
	}

}
