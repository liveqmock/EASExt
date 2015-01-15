package com.creditease.eas.util.sendcell;

import com.creditease.service.client.DetailsJaxb;
import com.creditease.service.client.MessageReqJaxb;
import com.creditease.service.client.MessageResJaxb;
import com.creditease.service.client.MessageService;
import com.creditease.service.client.MessageServiceJaxbImplService;
/***
 * 短信发送功能(内网用)
 * @Title:ClientSmsSend.java
 * @Package com.creditease.eas.util.sendcell
 * created at 2014-7-9 下午04:55:45 by ygq
 * @author ygq
 * @version 1.0
 */
public class ClientSmsSend {
    public static MessageService getService() {
        MessageServiceJaxbImplService service = new MessageServiceJaxbImplService();
        return service.getMessageServicePort();
    }
    /**单条发送短信
     *
     * @param phone 手机号
     * @param keywords 模板关键字
     * @param batchid 批号 最大32位,不重复即可
     * @param org_no 组织机构号
     * @param mod_type 模版号
     * @return
     */
    public  String oneMsgSend(String phone,String[] keywords,String batchid,String org_no,String mod_type) {
        MessageReqJaxb req = new MessageReqJaxb(); // webservice参数
        DetailsJaxb[] ds = new DetailsJaxb[1]; // 短信内容数组

        DetailsJaxb d =  new DetailsJaxb();
       /* String[] keywords = new String[1];// 模板关键字
        keywords[0] = "custName|测试单条";*/
        d.setMobile(phone); // 电话号码
        d.setKeywords(keywords);
        d.setPriority("5"); // 优先级
        ds[0] = d;
        req.setOrgNo(org_no);
        //req.setOrgNo(Constant.SMS_ORG_NO);// 组织机构号
        /*String batchid= "MidYear"+DateUtil.formatDate(new Date(),"yyyyMMddHHmmss");*/
        req.setBatchId(batchid);// 批次号4
        //req.setTypeNo(Constant.SMS_MID_YEAR_MOD_TYPE);// 模板号
        req.setTypeNo(mod_type);
        req.setVersion("2.00");// 接口版本
        req.setDetails(ds);

        MessageResJaxb res = getService().batchOrderMsgSendJaxb(req);
        StringBuffer sb=new StringBuffer();
        sb.append("{");
        sb.append("\"retInfo\":\"").append(res.getRetInfo()).append("\",");
        sb.append("\"batchId\":\"").append(res.getBatchId()).append("\",");
        sb.append("\"orgNo\":\"").append(res.getOrgNo()).append("\",");
        sb.append("\"retCode\":\"").append(res.getRetCode()).append("\",");
        sb.append("\"version\":\"").append(res.getVersion()).append("\"");
        sb.append("}");
        System.err.println(sb.toString());
        return sb.toString();
    }

    //多手机号发送相同短信内容批量发送
    //phone 逗号拼接
    public  String sameMessageManyPhoneSend(String phone,String[] keywords,String batchid,String org_no,String mod_type) {
        MessageReqJaxb req = new MessageReqJaxb(); // webservice参数
        DetailsJaxb[] ds = new DetailsJaxb[1]; // 短信内容数组
        DetailsJaxb d =  new DetailsJaxb();
        d.setMobile(phone);// 电话号码
        d.setPriority("5"); // 优先级
       /* String[] keywords = new String[1];// 模板关键字
        keywords[0] = "custName|调试人员1";*/
        d.setKeywords(keywords);
        ds[0] = d;
        req.setOrgNo(org_no);// 组织机构号
        // req.setOrgNo(Constant.SMS_ORG_NO);// 组织机构号
       /* String batchid= "MidYear"+DateUtil.formatDate(new Date(),"yyyyMMddHHmmss");*/
        req.setBatchId(batchid);// 批次号
        // req.setTypeNo(Constant.SMS_MID_YEAR_MOD_TYPE);// 模板号
        req.setTypeNo( mod_type);
        req.setVersion("2.00");// 接口版本
        req.setDetails(ds);

        MessageResJaxb res = getService().batchCustomMessageSendJaxb(req);
        StringBuffer sb=new StringBuffer();
        sb.append("{");
        sb.append("\"retInfo\":\"").append(res.getRetInfo()).append("\",");
        sb.append("\"batchId\":\"").append(res.getBatchId()).append("\",");
        sb.append("\"orgNo\":\"").append(res.getOrgNo()).append("\",");
        sb.append("\"retCode\":\"").append(res.getRetCode()).append("\",");
        sb.append("\"version\":\"").append(res.getVersion()).append("\"");
        sb.append("}");
        System.err.println(sb.toString());
        return sb.toString();
    }

    public static void main(String[] args) {
    	ClientSmsSend css = new ClientSmsSend();
        String keywords[]=new String[2];
        keywords[0]="custName|http://neiwang.creditease.corp/fsm_cms3/midyear/mobile/urlLogin/rGW0emgzlJ45250";
        //单条发送短信
        css.oneMsgSend("18710213152",keywords,"testbatchiii","2265","5184");
//      多手机号发送相同短信内容批量发送
//     sameMessageManyPhoneSend("18511456879,18710213152",keywords,"testbatchiii","2265","5184");
        System.out.println("ok");
    }

}
