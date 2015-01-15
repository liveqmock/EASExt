package  com.creditease.eas.util.phone;


import java.util.Scanner;

import com.creditease.service.client.DetailsJaxb;
import com.creditease.service.client.MessageReqJaxb;
import com.creditease.service.client.MessageResJaxb;
import com.creditease.service.client.MessageService;
import com.creditease.service.client.MessageServiceJaxbImplService;

public class ClientExample_jaxws {
	public static MessageService getService() {
		MessageServiceJaxbImplService service = new MessageServiceJaxbImplService();
		return service.getMessageServicePort();
	}

	public static void main(String[] args) {
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
				testOrderMsgSend();
			} else {
				System.err.println("指令不正确!");
				continue;
			}
		}
	}

	//多手机号不同短信内容批量发送
	public static void testBatchOrderMsgSend() {
		MessageReqJaxb req = new MessageReqJaxb(); // webservice参数
		DetailsJaxb[] ds = new DetailsJaxb[1]; // 短信内容数组
		
		DetailsJaxb d =  new DetailsJaxb();
		String[] keywords = new String[1];// 模板关键字
		keywords[0] = "key|value";
		d.setMobile("15210900847"); // 电话号码
		d.setKeywords(keywords);
		d.setPriority("5"); // 优先级
		ds[0] = d;
		
		req.setOrgNo("1354");// 组织机构号
		req.setBatchId("000001");// 批次号
		req.setTypeNo("5066");// 模板号
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
		ds[0] = d;
		
		req.setOrgNo("1357");// 组织机构号
		req.setBatchId("000002");// 批次号
		req.setTypeNo("5002");// 模板号
		req.setVersion("2.00");// 接口版本
		req.setDetails(ds);
		
		MessageResJaxb res = getService().batchCustomMessageSendJaxb(req);
		System.err.println(res.getRetInfo());
	}

	// 单条发送
	public static void testOrderMsgSend() {
		MessageReqJaxb req = new MessageReqJaxb(); // webservice参数
		DetailsJaxb[] ds = new DetailsJaxb[1]; // 短信内容数组
		
		DetailsJaxb d =  new DetailsJaxb();
		String[] keywords = new String[1];// 模板关键字
		keywords[0] = "key|value";
		d.setMobile("18610775451"); // 电话号码
		d.setKeywords(keywords);
		d.setPriority("5"); // 优先级
		ds[0] = d;
		
		req.setOrgNo("1354");// 组织机构号
		req.setBatchId("000001");// 批次号
		req.setTypeNo("5286");// 模板号
		req.setVersion("2.00");// 接口版本
		req.setDetails(ds);
		
		MessageResJaxb res = getService().batchOrderMsgSendJaxb(req);
		System.err.println(res.getRetInfo());
	}

	private static void init() {
		System.out.println("请按指示操作:\n 0:退出\n 1:测试batchOrderMsgSend \n 2:测试batchCustomMessageSend \n 3:测试orderMsgSend");
	}

}
