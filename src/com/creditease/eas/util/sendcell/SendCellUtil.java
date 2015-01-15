package com.creditease.eas.util.sendcell;


import com.creditease.service.ws.DetailsJaxb;
import com.creditease.service.ws.MessageReqJaxb;
import com.creditease.service.ws.MessageResJaxb;
import com.creditease.service.ws.MessageService;
import com.creditease.service.ws.MessageServiceJaxbImplService;
/***
 * 发送短信
 * @author ygq
 * @version 1.0 2013/12/6
 */
public class SendCellUtil {
	/**
	 * 2013-12
	 * @param name 姓名
	 * @param number 员工编码
	 * @param mail 邮箱
	 * @param key 密码
	 * @param description 描述
	 * @param mobilePhone 手机号码
	 */
	public static String sendCellUtil(String fname,String fnumber,String fmail,String fkey,String fdescription,String fmobilePhone){
		MessageServiceJaxbImplService service = new MessageServiceJaxbImplService();
		MessageService port =  service.getMessageServicePort();
		String retInfo = "";//返回值
		try{
			MessageReqJaxb req = new MessageReqJaxb(); // webservice参数
			DetailsJaxb[] ds = new DetailsJaxb[1]; // 短信内容数组
			String[] keywords = new String[5];// 模板关键字
			keywords[0] = "custName|" + fname;
			keywords[1] = "accountNo|" + fnumber;
			keywords[2] = "docNo|" + fmail;
			keywords[3] = "year|yixin@2014";// + fkey;//密码写死，为yixin@2013
			
			keywords[4] = "month|,";//该参数未起作用
			DetailsJaxb d = new DetailsJaxb();
			d.setMobile(fmobilePhone); // 电话号码
			d.setKeywords(keywords);
			d.setPriority("5"); // 优先级
			ds[0] = d;
			//
			req.setOrgNo("2265");// 组织机构号
			req.setBatchId("000001");// 批次号
			req.setTypeNo("6001");// 模板号
			req.setVersion("2.00");// 接口版本
			req.setDetails(ds);
			MessageResJaxb res = port.orderMsgSendJaxb(req);
			retInfo =  res.getRetInfo();	
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return retInfo;
	}

//	public static void main(String[] args) {
//		SendCellUtil su = new SendCellUtil();
//		String name ="张三";
//		String number = "20131012";
//		String mail = "sanzhang@creditease.cn";
//		String key ="123";
//		String description="good";
//		String mobilePhone = "15011303479";
//		su.sendCellUtil(name,number,mail,key,description,mobilePhone);
//	}

}
