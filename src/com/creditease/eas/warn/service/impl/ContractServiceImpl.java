/**
 * 
 */
package com.creditease.eas.warn.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.creditease.eas.util.FileReadUtil;
import com.creditease.eas.util.StringUtil;
import com.creditease.eas.util.TongJi;
import com.creditease.eas.util.mail.SendMailUtil;
import com.creditease.eas.warn.bean.WaringDetail;
import com.creditease.eas.warn.dao.WaringDetailMapper;
import com.creditease.eas.warn.kingdee.query.CommonPersonInfoSerachQuery;
import com.creditease.eas.warn.service.ContractService;
/**
 * created at 2012-12-26 下午04:01:37 by ygq
 * 
 * @author ygq({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
@Service
public class ContractServiceImpl implements ContractService{
	private static final Logger logger = Logger.getLogger(ContractServiceImpl.class);
	@Autowired
	private WaringDetailMapper waringdetailMapper;
	@Override
	public boolean queryPersonInfo() throws Exception{
		//List<String> listAll = sendMails();
//		System.out.println(listAll.size());
//		for(int i=0;i<listAll.size();i++){
//			System.out.print(listAll.get(i) + ";");
//		}
		return true;
	}
	/**
	 * 
	 * 描述：插入数据,并发送邮件
	 * 2013-1-3 下午12:35:30 by ygq
	 * @version
	 * @return
	 */
//	private List<String> sendMails() throws Exception{
//		List<String> listAll = new ArrayList<String>();
//		Map<String,List<Map<String,Object>>> map = ContractQuery.queryQualifyPersonsInfo();//查询出来的是人员的信息
//		for(Entry<String,List<Map<String,Object>>> entry : map.entrySet()){
//			 String key = entry.getKey();//现在的key
//			/*********************处理需要转正的人员的信息******************************/
//			 List<Map<String,Object>> listPersonsInfo = entry.getValue();
//			if(listPersonsInfo !=null && listPersonsInfo.size()>0){
//				 String htmlContentForContract = SendMailUtil.htmlContentForContract(listPersonsInfo);//这块的信息是显示哪些人需要转正用的
//				 Map<String,Object> mp = listPersonsInfo.get(0);
//				 String deptName =  mp.get("DEPTNAME").toString();
//				 String reletivePath = FileReadUtil.writeFile(deptName,htmlContentForContract,"contract","合同即将到期人员名单");//取
//				 //根据key查询出来直接上级，间接上级，部门负责人的相关的信息 ：将信息插入到明细表中
//				 //发送邮件：直接负责人，间接负责人,部门负责人
//				 String title = deptName + "合同即将到期人员名单";
//				  String fromAddress = "gaoquanyang@creditease.cn";
//				  String toAddress = "gaoquanyang@creditease.cn";
//				  SendMailUtil.sendMail(fromAddress, toAddress, title, htmlContentForContract);
//				 //根据key，查询出来需要向你哪些人员发送信息
//					//查询出来直接上级，间接上级，部门负责人
//					//注意一点：
//					//如果邮箱信息查询不到，就查备用邮箱的信息
//					//这里需要判断一下
//					//将数据插入到waringDetail中
//				 //*********************处理发送给直接上级，间接上级，部门负责人的信息（部门负责人肯定不会为空）******************************/
//	//			 List<Map<String,Object>> mapHig = QualifyingMatureQuery001.queryHighPersonByPositionId(key);//查询他们的上级
//	//			 sendMailToHigh(mapHig,deptName,htmlContent,reletivePath);
//				 //插入数据的测试
//				 WaringDetail waringDetail = new WaringDetail();
//				 waringDetail.setTypeid(4);//合同到期预警
//				 waringDetail.setWayid(2);//发送方式:发送邮件
//				 waringDetail.setSendtime(new Date());//发送时间
//				 waringDetail.setDepartid("");//部门id
//				 waringDetail.setDepartname(deptName);//部门名称
//				 waringDetail.setPostid("");//职位id
//				 waringDetail.setPostname("");//职位名称 
//				 waringDetail.setReceiverids("");//收件人IDS
//				 waringDetail.setReceiver("王五");//接收人：张三
//				 waringDetail.setEmail("gaoquanyang@credit.cn");//收件人邮箱
//				 waringDetail.setCopyids("");
//				 waringDetail.setCopyperson("");//抄送人
//				 waringDetail.setTheme(title);//
//				 waringDetail.setContentaddress(reletivePath);//文件路径，
//				 waringDetail.setFilename(deptName + "转正人员名单.txt");//文件名称：先写死
//				 waringDetail.setIssend(1);//成功了
//				 waringDetail.setSendcount(1);//默认是一次
//				 waringDetail.setCreatime(new Date());//创建日期
//				 /*****************************************短信内容:*****************/
//				 //waringDetail.setCell("13070189337");//电话
//				 //waringDetail.setBirthday(new Date());//出生日期 ？：生日预警的时候用
//				 //waringDetail.setEnterdate(new Date());//入职日期?
//				 //waringDetail.setCellcontent(cellcontent);//
//				 waringdetailMapper.insert(waringDetail);
//			}
//		 }
//		 return listAll;
//	}
	@Override
	public void sendMailInfos(Entry<String,List<Map<String,Object>>> entry){
		try{
			 List<Map<String,Object>> listHig = CommonPersonInfoSerachQuery.queryHighPersonByPositionId(entry.getKey());//查询上级部门人员
			 if(listHig == null||listHig.isEmpty()){//如果直接上级，间接上级，部门负责人都是空,程序不再往下执行
					return;
			 }
			 //发送邮件：直接负责人，间接负责人,部门负责人
			 String emails = "";//收件人邮箱
			 String personIds = "";//收件人IDS
			 String personNames = "";//收件人名字
			 for(int i=0;i<listHig.size();i++){
				Map<String,Object> mpp =  listHig.get(i);
				Object cfmail = mpp.get("CFMAIL")==null?null:mpp.get("CFMAIL");//邮箱
				if(null != cfmail){
					emails += cfmail + ";";
					personIds += StringUtil.objToString(mpp.get("PERSONID")) + ";";//ids
					personNames += StringUtil.objToString(mpp.get("PNAME")) + ";";//接收人姓名
				}else{//取个人邮箱
					cfmail = mpp.get("EMAIL")==null?null:mpp.get("EMAIL");//邮箱
					if(null != cfmail){
						emails += cfmail + ";";
						personIds += StringUtil.objToString(mpp.get("PERSONID")) + ";";//ids
						personNames += StringUtil.objToString(mpp.get("PNAME")) + ";";//接收人姓名
					}
				}
			 }
			 if(emails.equals("")){//如果收件人一个邮件都没有，就放弃发送邮件,程序不再往下执行
				// System.out.println("放弃了");
				 return;
			 }
			 /**************************当上面的验证都通过的时候，程序继续往下执行*********************************************************/
			 TongJi.tongJiCount ++;
			 List<Map<String,Object>> listPersonsInfo = entry.getValue();
			 String htmlContentForContract = SendMailUtil.htmlContentForContract(listPersonsInfo);//这块的信息是显示哪些人需要转正用的
			 Map<String,Object> mp = listPersonsInfo.get(0);
			 String deptName =  StringUtil.objToString(mp.get("DEPTNAME"));
			 String reletivePath = FileReadUtil.writeFile(deptName,htmlContentForContract,"contract","合同即将到期人员名单");//取
			 //String title = deptName + "合同即将到期人员名单";
			 String title = "（预警测试）合同即将到期提醒通知(此邮件为系统发送，请勿回复)";
			 //String title = "重要！！合同即将到期提醒通知";
			 String fromAddress = "hr@mail.creditease.cn";
			 //String[] toAddress =  {"gaoquanyang@creditease.cn","xiaoquan328@163.com"};
			 //String[] toAddress = {"gaoquanyang@creditease.cn","nannanxu@creditease.cn"};
			 String[] toAddress = {"gaoquanyang@creditease.cn"};
			 //根据key查询出来直接上级，间接上级，部门负责人的相关的信息 ：将信息插入到明细表中
			 //插入数据的测试
			 //发送邮件给多个人:测试
//			 SendMailUtil.sendMailToManyPerson(fromAddress, toAddress, title, htmlContentForContract);//测试邮箱
			 WaringDetail waringDetail = new WaringDetail();
			 waringDetail.setTypeid(4);//合同到期预警
			 waringDetail.setWayid(2);//发送方式:发送邮件
			 waringDetail.setSendtime(new Timestamp(System.currentTimeMillis()));//发送时间:需要注意下
			 //waringDetail.setDepartid("");//部门id
			 //waringDetail.setDepartname(deptName);//部门名称
			 //waringDetail.setPostid("");//职位id
			 //waringDetail.setPostname("");//职位名称 
			 waringDetail.setReceiverids(personIds);//收件人IDS
			 waringDetail.setReceiver(personNames);//接收人：张三
			 waringDetail.setEmail(emails);//收件人邮箱
//			 waringDetail.setCopyids("");
//			 waringDetail.setCopyperson("");//抄送人
			 waringDetail.setTheme(title);//
			 waringDetail.setContentaddress(reletivePath);//文件路径，
			 waringDetail.setFilename(deptName + "合同到期人员名单.txt");//文件名称：先写死
			 waringDetail.setIssend(1);//成功了
			 waringDetail.setSendcount(1);//默认是一次
			 waringDetail.setCreatime(new Date());//创建日期
			 /*****************************************短信内容:*****************/
			 //waringDetail.setCell("13070189337");//电话
			 //waringDetail.setBirthday(new Date());//出生日期 ？：生日预警的时候用
			 //waringDetail.setEnterdate(new Date());//入职日期?
			 //waringDetail.setCellcontent(cellcontent);//
//			 waringdetailMapper.insert(waringDetail);
		}catch(Exception ex){
			TongJi.tongJiExceptionCount ++;
			logger.error(ex.getMessage());
		}
	}
//	private List sendMailToHigh(List<Map<String,Object>> mapHig,String deptName,String htmlContent,String reletivePath){
//		String toAddress = "gaoquanyang@creditease.cn";
//		if(null != mapHig && mapHig.size()>0){
//			for(int i=0;i<mapHig.size();i++){
//				Map<String,Object> mph = mapHig.get(i);
//				//邮箱的有效性的验证
//				Object mail = (mph.get("CFMAIL")==null)?mph.get("EMAIL"):mph.get("CFMAIL");
//				List<String> list = new ArrayList<String>();
//				if(mail != null && !"".equals(mail)){
//					list.add(toAddress);
//					String pname = StringUtil.objToString(mph.get("PNAME"));
//					String title = deptName + "转正人员名单";
//					//根据key，查询出来需要向你哪些人员发送信息
//					//查询出来直接上级，间接上级，部门负责人
//					//注意一点：
//					//如果邮箱信息查询不到，就查备用邮箱的信息
//					//这里需要判断一下
//					//将数据插入到waringDetail中
//					 WaringDetail waringDetail = new WaringDetail();
//					 waringDetail.setTypeid(2);//预警类别
//					 waringDetail.setWayid(2);//发送方式
//					 waringDetail.setSendtime(new Date());//发送时间
//					 waringDetail.setDepartid("");//部门id
//					 waringDetail.setDepartname(deptName);//部门名称
//					 waringDetail.setPostid("");//职位id
//					 waringDetail.setPostname("");//职位名称 
//					 waringDetail.setReceiverids("");//收件人IDS
//					 waringDetail.setReceiver(pname);//接收人：张三
//					 waringDetail.setEmail(toAddress);//收件人邮箱
//					 waringDetail.setCopyids("");
//					 waringDetail.setCopyperson("");//抄送人
//					 waringDetail.setTheme(title);//
//					 waringDetail.setContentaddress(reletivePath);//文件路径，
//					 waringDetail.setFilename(deptName + "转正人员名单.txt");//文件名称：先写死
//					 waringDetail.setIssend(1);//成功了
//					 waringDetail.setSendcount(1);//默认是一次
//					 waringDetail.setCreatime(new Date());//创建日期
//					 /*****************************************短信内容:*****************/
//					 //waringDetail.setCell("13070189337");//电话
//					 //waringDetail.setBirthday(new Date());//出生日期 ？：生日预警的时候用
//					 //waringDetail.setEnterdate(new Date());//入职日期?
//					 //waringDetail.setCellcontent(cellcontent);//
//					 waringdetailMapper.insert(waringDetail);
//				}
//			}
//		}
//		return null;
//	}
	public static void main(String[] args) throws Exception {
		ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
		ContractService regularService = (ContractService)app.getBean("contractServiceImpl");
		boolean va = regularService.queryPersonInfo();
		System.out.println("ok" + va);
	}
}
