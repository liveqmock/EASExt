/**
 * 
 */
package com.creditease.eas.warn.service.impl;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.creditease.eas.admin.bean.EmailConfig;
import com.creditease.eas.admin.bean.User;
import com.creditease.eas.admin.dao.EmailConfigMapper;
import com.creditease.eas.admin.service.UserService;
import com.creditease.eas.util.DateUtil;
import com.creditease.eas.util.DictionaryUtil;
import com.creditease.eas.util.Holiday;
import com.creditease.eas.util.PageUtil;
import com.creditease.eas.util.Pagination;
import com.creditease.eas.util.SendExcel;
import com.creditease.eas.util.Utils;
import com.creditease.eas.util.mail.ContractHtmlContent;
import com.creditease.eas.util.mail.MailSenderInfo;
import com.creditease.eas.util.mail.SendMailUtilNew;
import com.creditease.eas.util.port.ImportExecl;
import com.creditease.eas.warn.bean.InterfacePerson;
import com.creditease.eas.warn.bean.RentContract;
import com.creditease.eas.warn.bean.SendPortInfo;
import com.creditease.eas.warn.dao.InterfacePersonMapper;
import com.creditease.eas.warn.dao.SendPortInfoMapper;
import com.creditease.eas.warn.service.InterfacePersonService;
import com.creditease.eas.warn.service.RentContractService;
import com.creditease.eas.warn.service.UploadService;

@SuppressWarnings("deprecation")
@Service
public class InterfacePersonServiceImpl implements InterfacePersonService{
	@Autowired
	private InterfacePersonMapper interfacePersonMapper;
	@Autowired
	private SendPortInfoMapper sendPortInfoMapper;
	@Autowired
	private UploadService uploadService;
	@Autowired
	private RentContractService rentContractServiceImpl;
	/**用户service接口**/
	@Autowired
	private UserService userServiceImpl;
	/**接口人entity**/
	private InterfacePerson interfacePerson;
	/**导入excel返回消息**/
	private String importMessage;
	/** 日志**/
	private static final Logger logger = Logger.getLogger(InterfacePersonServiceImpl.class);
	
	@Override
	public int insert(InterfacePerson portInfo) throws Exception {
		// TODO Auto-generated method stub
		interfacePersonMapper.insert(portInfo);
		return 0;
	}

	public int delete(Integer id) throws Exception {
		int i = interfacePersonMapper.deleteByPrimaryKey(id);
		return i;
	}
	
	public int update(InterfacePerson portInfo) throws Exception {
		int i = interfacePersonMapper.updateByPrimaryKey(portInfo);
		return i;
	}
	
	public InterfacePerson getPortInfoById(Integer id) throws Exception {
		interfacePerson = interfacePersonMapper.selectByPrimaryKey(id);
		return interfacePerson;
	}

	/**
	 * 查询分布列表记录
	 */
	@SuppressWarnings("unchecked")
	public Pagination queryPage(Pagination page) throws Exception {
		HttpServletRequest request= ServletActionContext.getRequest();
		int currentPage = PageUtil.strToPage(request.getParameter("page"));
		int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
		
		User user = (User) request.getSession().getAttribute("user");
		Map map = HashMap.class.newInstance();
		map.put("typeid",user.getTypeid());
		map.put("email",user.getEmail());
		
		map.put("orgname",request.getParameter("orgname"));
		map.put("city",request.getParameter("city"));
		map.put("officeadd",request.getParameter("officeadd"));
		map.put("lastcostcenter",request.getParameter("lastcostcenter"));
		map.put("isport",request.getParameter("isport"));
		//查询总行数的方法
		int totalCounts = interfacePersonMapper.getTotalCounts(map);
		
		page = new Pagination(currentPage,pageSize,totalCounts); 
		//查询数据集的方法
		Map map2 = PageUtil.getMap(page);
		map2.putAll(map);
		List list = interfacePersonMapper.queryPage(map2);
//		list = Utils.convertTimeOfList2String(list, "yyyy-MM-dd HH:mm:ss");
		page.setRows(list);
		return page;
	}
	/**
	 * 到期提醒:合同到期提前四个月提醒，四个月中提醒四次（即每个月的月初提醒一次），月初的1号到5号之间
	 */
	public void expire(){
		
		List<Map<String, Object>> list = interfacePersonMapper.expire();
		for(Map<String, Object> map : list){
			if(map.get("ORGNAME")==null || "".equals(map.get("ORGNAME"))
					|| map.get("CITY")==null || map.get("OFFICEADD")==null || "".equals(map.get("OFFICEADD"))
					|| map.get("LASTCOSTCENTER")==null || map.get("RENTENDTIME")==null || "".equals(map.get("RENTENDTIME"))) continue;
			String orgname = map.get("ORGNAME").toString();//部门
			MailSenderInfo ms = new MailSenderInfo();//邮件发送消息bean
			Properties pr = ms.getProperties();
			String[] toAddress =this.getEmailArr(interfacePersonMapper.getEmailByOrgName(orgname));//收件人
			if(pr.getProperty("to_local_Address") != null && "yes".equals(pr.getProperty("to_local_Address"))){
				//toAddress = pr.getProperty("toAddress").split(",");//代码正式提交时邮件接收人为接口人邮箱，此行注释掉
			}
			if(toAddress.length == 0) continue;
			String city = map.get("CITY").toString();//城市
			String officeadd = map.get("OFFICEADD").toString();//办公地址
			String lastcostcenter = map.get("LASTCOSTCENTER").toString();//末级成本中心
			
			String str = map.get("RENTENDTIME").toString();//合同到期日期
			String str1=null ,str2=null ,str3=null ,str4=null;
			try {
				str = Utils.checkDateStr(str);
				str1 = Utils.lastmonth(str,0);//到期前一个月的第一天
				str2 = Utils.lastmonth(str,-1);//到期前两个月的第一天
				str3 = Utils.lastmonth(str,-2);//到期前三个月的第一天
				str4 = Utils.lastmonth(str,-3);//到期前四个月的第一天
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("部门："+orgname+"，城市："+city+"，办公地址："+officeadd+"，末级成本中心："
						+lastcostcenter+"，合同到期日期格式错误！("+str+")，"+e.getMessage());
				continue;
			}
			
			String fromAddress = pr.getProperty("PORT_USERNAME");//邮件发送人
			String password = pr.getProperty("PORT_PASSWORD");
			String logoPath = pr.getProperty("logoPath");//邮件图片logo路径
			String htmlContent = ContractHtmlContent.htmlContentPort(officeadd, Utils.getCnTimeStr(str));//邮件内容
			String[] ccs = null;
			boolean va = false;//发送邮件成功与否
			String content = "";
			if(str4.equals(Utils.getCurrentDate()) || str3.equals(Utils.getCurrentDate())
					|| str2.equals(Utils.getCurrentDate()) || str1.equals(Utils.getCurrentDate())){
				va = SendMailUtilNew.sendMailToManyPersonAddLogo(fromAddress,password, toAddress, "房屋合同到期提醒", htmlContent, logoPath,ccs);
				content = "您好，地址:"+officeadd+"，房租将于   "+Utils.getCnTimeStr(str)+" 到期，请及时确认本职场是否需要续租。";	
			}
			this.insertSendPortInfo(content, va, orgname, city, lastcostcenter, officeadd, this.getEmailStr(toAddress),null);
		}
	}
	/**
	 * 系统自动发出提醒：节假日期间提前2-3天由系统向所有接口人发出提醒信息，是否需要提前申请房租付款。
	 */
	public void sendPort(){
		List<Map<String, Object>> list = interfacePersonMapper.expire();
		for(Map<String, Object> map : list){
			if(map.get("ORGNAME")==null || "".equals(map.get("ORGNAME"))
					|| map.get("CITY")==null || map.get("OFFICEADD")==null || "".equals(map.get("OFFICEADD"))
					|| map.get("LASTCOSTCENTER")==null) continue;
			String orgname = map.get("ORGNAME").toString();//部门
			MailSenderInfo ms = new MailSenderInfo();
			Properties pr = ms.getProperties();
			//接口人邮件（发送预警邮件时邮件接收人地址）
			String[] toAddress = this.getEmailArr(interfacePersonMapper.getEmailByOrgName(orgname));
			if(pr.getProperty("to_local_Address") != null && "yes".equals(pr.getProperty("to_local_Address"))){
				//toAddress = pr.getProperty("toAddress").split(",");//代码正式提交时邮件接收人为接口人邮箱，此行注释掉
			}
			if(toAddress.length==0) continue;
			String city = map.get("CITY").toString();//城市
			String officeadd = map.get("OFFICEADD").toString();//办公地址
			String lastcostcenter = map.get("LASTCOSTCENTER").toString();//末级成本中心
			
			String fromAddress = pr.getProperty("PORT_USERNAME");//邮件发送人
			String password = pr.getProperty("PORT_PASSWORD");
			String logoPath = pr.getProperty("logoPath");//邮件图片logo路径
			String htmlContent = ContractHtmlContent.htmlContentSendPort(officeadd);//邮件内容
			String[] ccs = null;
			boolean va = false;//发送邮件成功与否
			String content = "";
			Date warningDate = null;//预警日期
			//节假日提前三天（或者两天）和当前日期比较，相等则发送邮件提醒
			for(int i=0;i<Holiday.HOLIDAYS_STARTDAY_2013.length;i++){
				warningDate = Utils.getBeforeDate(Utils.toDate(Holiday.HOLIDAYS_STARTDAY_2013[i]), 3);
				if(Utils.diffdates(warningDate, Utils.getDate())==0){
					va=SendMailUtilNew.sendMailToManyPersonAddLogo(fromAddress,password, toAddress, "房屋合同系统发出提醒内容", htmlContent, logoPath,ccs);
					content = "您好：假期临近，地址:"+officeadd+"，请确认是否需要提前申请房租付款申请？如需提前申请付款的请与相关人员申请；";	
					this.insertSendPortInfo(content, va, orgname, city, lastcostcenter, officeadd, this.getEmailStr(toAddress),null);
				}
			}
		}
	}
	/**
	 * 缴费付款提醒:所有房租付款日提前15个自然日发出提醒信息
	 */
	public void payfees(){
		MailSenderInfo ms = new MailSenderInfo();
		Properties pr = ms.getProperties();
		String fromAddress = pr.getProperty("PORT_USERNAME");//邮件发送人
		String password = pr.getProperty("PORT_PASSWORD");
		String logoPath = pr.getProperty("logoPath");
		String[] ccs = null;
		boolean va = false;//是否发送成功
		String htmlContent = null;
		List<Map<String, Object>> list = interfacePersonMapper.expire();
		for(Map<String, Object> map : list){
			if(map.get("ORGNAME")==null || "".equals(map.get("ORGNAME"))
					|| map.get("CITY")==null || map.get("OFFICEADD")==null || "".equals(map.get("OFFICEADD"))
					|| map.get("LASTCOSTCENTER")==null || map.get("PAYMENTCYCLE")==null || "".equals(map.get("PAYMENTCYCLE"))
					|| map.get("PAYCOUNT")==null || "".equals(map.get("PAYCOUNT"))
					|| map.get("RENTSTARTTIME")==null || "".equals(map.get("RENTSTARTTIME"))) continue;
			String orgname = map.get("ORGNAME").toString();
			String[] toAddress = this.getEmailArr(interfacePersonMapper.getEmailByOrgName(orgname));//接口人邮件（发送预警邮件时邮件接收人地址）
			if(pr.getProperty("to_local_Address") != null && "yes".equals(pr.getProperty("to_local_Address"))){
				//toAddress = pr.getProperty("toAddress").split(",");//代码正式提交时邮件接收人为接口人邮箱，此行注释掉
			}
			if(toAddress.length==0) continue;
			String city = map.get("CITY").toString();
			String officeadd = map.get("OFFICEADD").toString();
			String lastcostcenter = map.get("LASTCOSTCENTER").toString();
			
			String paymentcycle = map.get("PAYMENTCYCLE").toString();//付款周期
			int paycount = Utils.getInt(map.get("PAYCOUNT"));//付款次数
			String rentstarttime = map.get("RENTSTARTTIME").toString();//房租起始日期
			Date paydate = null;
			try {
				rentstarttime = Utils.checkDateStr(rentstarttime);
				paydate = Utils.toDate(rentstarttime);//付款时间
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("部门："+orgname+"，城市："+city+"，办公地址："+officeadd+"，末级成本中心："
						+lastcostcenter+"，房租起始日期格式错误！("+rentstarttime+")，"+e.getMessage());
				continue;
			}
			Date warndate;//提醒时间
			String content = "";
			for(String key:DictionaryUtil.payType_map.keySet()){
				int step = DictionaryUtil.getKeyNum(key);
				if(key.equals(paymentcycle)){
					for(int i = 1;i<=paycount;i++){
						paydate = DateUtil.getDateAfterMonth(paydate, step);//加上setp个月
						warndate = Utils.getBeforeDate(paydate, 15);
						if(Utils.diffdates(warndate, Utils.getDate())==0){//提醒时间等于当前时间，发送邮件
							htmlContent = ContractHtmlContent.htmlContentPayMoney(officeadd, Utils.getCnTimeFormat(paydate));
							va = SendMailUtilNew.sendMailToManyPersonAddLogo(fromAddress,password, toAddress, "房屋合同缴费付款提醒", htmlContent, logoPath,ccs);
							content = "您好，地址:"+officeadd+"，房租请于 "+Utils.getCnTimeFormat(paydate)+" 之前，完成付款。";	
						}
					}
				}
			}
			this.insertSendPortInfo(content, va, orgname, city, lastcostcenter, officeadd, this.getEmailStr(toAddress),null);
		}
	}
	/**
	 * 
	 * 描述：获取接收人邮箱数组
	 * 2013-11-18 下午04:24:20 by caoyong
	 * @version
	 * @param list接口人邮箱结果集合
	 * @return 接收人邮箱数组
	 */
	private String[] getEmailArr(List<Map<String, String>> list){
		String[] emailArr = new String[0];
		if(list!=null && list.size()>0){
			emailArr = new String [list.size()];
			for(int i=0;i<list.size();i++){
				emailArr[i] = list.get(i).get("PORTMAIL").toString();
			}
		}
		return emailArr;
	} 
	/**
	 * 
	 * 描述：根据邮箱数组返回邮箱字符串，以","隔开
	 * 2013-11-18 下午04:58:16 by caoyong
	 * @version
	 * @param emailArr邮箱数组
	 * @return	邮箱字符串，以","隔开
	 */
	private String getEmailStr(String[] emailArr){
		String emailStr = "";
		if(emailArr!=null && emailArr.length>0){
			for(int i=0;i<emailArr.length;i++){
				emailStr += emailArr[i]+",";
			}
			emailStr = emailStr.substring(0, emailStr.lastIndexOf(","));
		}
		return emailStr;
	}
	/**
	 * 
	 * 描述：发送定时任务邮件时记录保存到表
	 * 2013-9-6 下午05:58:11 by caoyong
	 * @version
	 * @param content
	 * @param flag
	 * @param orgname
	 * @param city
	 * @param lastcostcenter
	 * @param officeadd
	 * @param receieveEmail
	 */
	private void insertSendPortInfo(String content,boolean flag,String orgname,String city,
			String lastcostcenter,String officeadd,String receieveEmail,String filePath){
		if(!"".equals(content)){
			SendPortInfo sendPortInfo = new SendPortInfo();
			sendPortInfo.setOrgname(orgname);
			sendPortInfo.setCity(city);
			sendPortInfo.setLastcostcenter(lastcostcenter);
			sendPortInfo.setOfficeadd(officeadd);
			sendPortInfo.setPortmail(receieveEmail);
			sendPortInfo.setContent(content.equals("sendorgmail")?null:content);
			sendPortInfo.setCreatortime(new Date());
			sendPortInfo.setContentfile(filePath);
			if(flag){
				sendPortInfo.setIssend(1);//成功了
				sendPortInfo.setExt1("成功");
			}else{
				sendPortInfo.setIssend(0);//失败了
				sendPortInfo.setExt1("失败");
			}
			sendPortInfoMapper.insert(sendPortInfo);
		}
	}
	
	/**
	 * 导出excel（生成汇总附件）：根据部门汇总已发送邮件信息
	 * @param orgname （部门名称） 部门名称为空时查询所有的部门已发送的邮件信息，用于发送给谢允儿/于静的基础数据
	 * @return 生成的excel文件的路径
	 */
	public String createxcel(String orgname,List<SendPortInfo> list) throws Exception{
		SendExcel<SendPortInfo> ex = new SendExcel<SendPortInfo>();
		String filestr = ex.createmail(list,orgname);
		return filestr;
	}
	/**
	 * 给部门负责人发送房屋合同续租excel：将部门已发送的预警邮件进行汇总发送给总接口人
	 */
	public void sendorgmail() throws Exception{
		//查询部门负责人
		List<InterfacePerson> list = interfacePersonMapper.allorgmail();//获取部门总接口人信息
		MailSenderInfo ms = new MailSenderInfo();
		Properties pr = ms.getProperties();
		String fromAddress = pr.getProperty("PORT_USERNAME");//邮件发送人
		String password = pr.getProperty("PORT_PASSWORD");
		
		//根据部门（orgname）汇总邮件信息
		for(InterfacePerson bean:list){
			String city = bean.getCity();
			String officeadd = bean.getOfficeadd();
			String lastcostcenter = bean.getLastcostcenter();
			String portmail = bean.getPortmail();
			String[] toAddress = {portmail};
			if(pr.getProperty("to_local_Address") != null && "yes".equals(pr.getProperty("to_local_Address"))){
				//toAddress = pr.getProperty("toAddress").split(",");//代码正式提交时邮件接收人为接口人邮箱，此行注释掉
			}
			String orgname = bean.getOrgname();
			//导出文件需要的类
			List<SendPortInfo> sendPortInfos = uploadService.queryDataset(orgname);
			if(sendPortInfos.size()<=0) continue;//如果没有该部门没有已经发送的预警邮件则不发送汇总邮件
			String filestr = createxcel(orgname,sendPortInfos);//文件路径（生成汇总附件）
			String title = bean.getOrgname()+"部门发送的房屋合同预警邮件汇总";
			boolean va = SendMailUtilNew.sendFileMail(fromAddress,password, toAddress, title, filestr);
			this.insertSendPortInfo("sendorgmail", va, orgname, city, lastcostcenter, officeadd, portmail,filestr);
		}
		//邮件汇总发送给谢允儿/于静（给于静的汇总查询部门like'%投%'，给谢允我的部门not like '投'）
		sendEmailToYuAndXie("xieyuner", ms);
		sendEmailToYuAndXie("yujing", ms);
	}
	
	@Autowired
	private EmailConfigMapper emailConfigMapper;
	public void setEmailConfigMapper(EmailConfigMapper emailConfigMapper) {
		this.emailConfigMapper = emailConfigMapper;
	}
	
	private String[] getEmailByYuAndXie(int fid){
		EmailConfig emailConfig=emailConfigMapper.getEmailConfigByFid(fid);
		String toAddressEmail=emailConfig.getFemail();
		String[] toAddress={};
		if(toAddressEmail!=null &&!"".equals(toAddressEmail)){
			toAddress=toAddressEmail.split(";");
		}
		return toAddress;
	}

	/**
	 * 
	 * 描述：邮件汇总发送给谢允儿（yunerxie@creditease.cn）/于静（jingyu2@creditease.cn）
	 * 		 同时发送给财务人员乔艳霞（yanxiaqiao@creditease.cn）；
	 * 		 于静的邮件同时发送给徐广宁（guangningxu@creditease.cn）；谢允儿的邮件同时发送给曹莹（yingcao@creditease.cn）；
	 * 2013-8-7 下午05:44:43 by caoyong
	 * @version
	 * @param name 邮件接收人（谢允儿/于静）
	 * @param ms   要发送的邮件
	 * @throws Exception
	 */
	public void sendEmailToYuAndXie(String name,MailSenderInfo ms) throws Exception{
		List<SendPortInfo> list = uploadService.queryDataset(name);
		if(list!=null && list.size()>0){
			String orgname = "普惠职能端";String receiverMail="yunerxie@creditease.cn";String str = "yingcao@creditease.cn";
			boolean sendSuccess = false;String filePath = "";String title = "";
			if(name.equals("yujing")){
				orgname="财富端";receiverMail="jingyu2@creditease.cn";str = "guangningxu@creditease.cn";
				String[] toAddress = {receiverMail,str,"yanxiaqiao@creditease.cn","qixiao@creditease.cn"};
				
				toAddress=getEmailByYuAndXie(2);
				
				//toAddress = {receiverMail,str,"yanxiaqiao@creditease.cn","qixiao@creditease.cn"};
				if(ms.getProperties().getProperty("to_local_Address") != null && "yes".equals(ms.getProperties().getProperty("to_local_Address"))){
					//toAddress = ms.getProperties().getProperty("toAddress").split(",");//上线后删除本行代码
				}
				title = orgname+"所有部门发送的房屋合同预警邮件汇总";
				filePath = createxcel(orgname,list);//文件路径（生成汇总附件）
				sendSuccess = SendMailUtilNew.sendFileMail(ms.getProperties().getProperty("PORT_USERNAME"),ms.getProperties().getProperty("PORT_PASSWORD"), toAddress, title, filePath);
			}else if(name.equals("xieyuner")){
				String[] toAddress = {receiverMail,str,"yanxiaqiao@creditease.cn"};
				
				toAddress=getEmailByYuAndXie(1);
				
				if(ms.getProperties().getProperty("to_local_Address") != null && "yes".equals(ms.getProperties().getProperty("to_local_Address"))){
					//toAddress = ms.getProperties().getProperty("toAddress").split(",");//上线后删除本行代码
				}
				title = orgname+"所有部门发送的房屋合同预警邮件汇总";
				filePath = createxcel(orgname,list);//文件路径（生成汇总附件）
				sendSuccess = SendMailUtilNew.sendFileMail(ms.getProperties().getProperty("PORT_USERNAME"),ms.getProperties().getProperty("PORT_PASSWORD"), toAddress, title, filePath);
			}
			this .insertSendPortInfo("sendorgmail", sendSuccess, orgname, null, null, null, receiverMail, filePath);
		}
		
	}
	public String importExcel(File excelFile,String filePath) throws Exception{
		String jsonResult = "";
		String temp[] = filePath.replaceAll("\\\\",",").split(",");
		String str = temp[temp.length-1];
		String directory = "/upload/port";  
        String targetDirectory = ServletActionContext.getServletContext().getRealPath(directory);  
        //生成上传的文件对象  
        File target = new File(targetDirectory,str);  
        //如果文件已经存在，则删除原有文件  
        if(target.exists()){  
            target.delete();  
        }  
        //复制file对象，实现上传  
    	FileUtils.copyFile(excelFile, target);  
        ImportExecl poi = new ImportExecl();
        //人员信息源数据
        List<List<String>> list = poi.read(target.getPath(),0);
        this.importMessage = "";
        if(list.size()==0) this.importMessage += "请检查接口人员信息汇总sheet格式或数据</br>";
        this.importPortInfosAndUsers(list);
//	        //房屋合同信息源数据
        List<List<String>> list2 = poi.read(target.getPath(),1);  
        if(list2.size()==0) this.importMessage += "请检查房屋合同信息汇总sheet格式或数据</br>";
		this.importRentContracts(list2);
		jsonResult = "{\"success\":\"true\", \"message\":\"" + this.importMessage + "\"}";
		return jsonResult;
	}
	/**
	 * 
	 * 描述：导入接口人信息和用户信息（数据表头列可以调整顺序）
	 * 
	 * 表头字段说明：
	 * =====行政可维护字段：
	 * 1、成本中心2、使用部门3、地区4、办公室座落地点5、面积（平方米）6、开始日期7、结束日期8、租赁期限（月）9、付款次数10、付款方式
	 * =====财务可维护字段：
	 * 1、纸质合同编号2、成本中心编号3、付款部门4、汇入帐户（业主）5、首付日期6、房屋押金7、物业押金8、免租期(月)9、年租金（元）10、 合同月租金 
	 * =====三期备用字段
	 * 1、状态2、备注
	 * 
	 * 2013-8-13 下午06:08:56 by caoyong
	 * @version
	 * @param list数据源（从excel获取的数据源）
	 * @throws Exception
	 */
	private void importPortInfosAndUsers(List<List<String>> list) throws Exception{
		//人员信息
        if (list != null){  
        	int portInfoCount=0,userCount=0;
        	//处理表关title信息（i==1）
            for (int i = 2; i < list.size(); i++){  
            	List<String> cellList = list.get(i); 
            	String officeadd = cellList.get(2).trim();
            	String mail = cellList.get(4).trim();
            	String orgname = cellList.get(0).trim();
            	int officecount = this.selectPortByOfficeadd(mail,officeadd);//重复插入判断
            	if(officecount > 0){
            		continue;
            	}else if(mail==null || "".equals(mail) || mail.equals("yanxiaqiao@creditease.cn")){//乔燕霞数据不导入/email数据为空不导入
                	continue;
                }
            	interfacePerson = new InterfacePerson();
            	interfacePerson.setOrgname(orgname);
            	interfacePerson.setCity(cellList.get(1).trim());
            	interfacePerson.setOfficeadd(officeadd);
            	interfacePerson.setLastcostcenter(cellList.get(3).trim());
            	interfacePerson.setPortmail(cellList.get(4).trim());
            	interfacePerson.setIsport(DictionaryUtil.getKeyByValue(DictionaryUtil.yesno_map, cellList.get(5).trim()));
            	interfacePerson.setCreator(this.findUser().getUsername());//加入系统后设置成userName
            	interfacePerson.setCreatortime(Utils.getLongDate());
            	interfacePerson.setExt1(this.isCaiFuDuanRecord(orgname)?"2":"1");//财富端数据2，普惠职能端数据1
                this.insert(interfacePerson); 
                portInfoCount ++;
                
                //对应每一行接口人信息都插入一条记录到用户表(已经存在的记录不进行新增用户操作)
                if(userServiceImpl.selectedIfUserExists1(cellList.get(4).trim())!=0) continue;
                User user = User.class.newInstance();
                user.setUsername(cellList.get(4).trim());//用户名暂时为邮箱名字
                user.setPassword(cellList.get(4).trim());//密码暂时为邮箱名字
                user.setEmail(cellList.get(4).trim());//邮箱为邮箱名字
                
                // 人员类型：0默认是普通的用户；1管理员；2普惠职能端部门总接口人；3普惠职能端普通部门接口人；
            	// 4普惠职能端总接口人负责人(谢允儿)；5普惠职能端行政部门负责人（曹莹）；6财富端部门总接口人；
                // 7财富端普通部门接口人；8财富端总接口人负责人(于静)；9财富端行政部门负责人（徐广宁）；
                // 部门包含“投一或者投二等”时为财富端，否则导入的人员信息为普惠端人员(导入时只涉及到2/3/6/7)
                if(cellList.get(5).trim().equals("是")){
                	if(orgname.contains("投")){
                		user.setTypeid(6l);//6财富端部门总接口人
                	}else{
                		user.setTypeid(2l);//2普惠职能端部门总接口人
                	}
                }else if(cellList.get(5).trim().equals("否")){
                	if(orgname.contains("投")){
                		user.setTypeid(7l);//7财富端普通部门接口人
                	}else{
                		user.setTypeid(3l);//3普惠职能端普通部门接口人
                	}
                }
                user.setFforbidden(0);//0：正在使用，1：已经禁用
                user.setCreateusername(this.findUser().getUsername());
                user.setCreatedate(Utils.getLongDate());
                userServiceImpl.insertUser(user);//调用service方法插入数据
                userCount ++;
            } 
            this.importMessage += portInfoCount==0?"无接口人信息导入</br>":("导入了"+portInfoCount+"条接口人信息</br>");
            this.importMessage += userCount==0?"无用户信息导入</br>":("导入了"+userCount+"条用户信息</br>");
        }
	}
	
	/**
	 * 
	 * 描述：导入房屋合同信息
	 * 2013-8-13 下午06:13:13 by caoyong
	 * @version
	 * @param list数据（从excel获取的房屋合同信息源数据）
	 * @throws Exception
	 */
	private void importRentContracts(List<List<String>> list) throws Exception{
		if (list != null){  
			if(list.size()>2){
				List<String> titlesList = list.get(1);
				int[] titleNum = this.getCellNumByName(titlesList);//表头列对应的列number
				int rentContractCount = 0;
	            for (int i = 2; i < list.size(); i++){  
	            	List<String> cellList = list.get(i); 
	            	String officeadd = cellList.get(titleNum[6]).trim();//办公室座落地点
	            	String orgname = cellList.get(titleNum[3]).trim();//使用部门
	            	int officecount = rentContractServiceImpl.selectRentByOfficeadd(officeadd);//已经存在的数据不插入（办公室座落地点去重）
	            	if(officecount > 0) continue;
	                RentContract rentContract = new RentContract();
	                //行政端数据
	                rentContract.setLastcostcenter(cellList.get(titleNum[2]).trim());//成本中心
	                rentContract.setOrgname(orgname);//使用部门
	                rentContract.setCity(cellList.get(titleNum[5]).trim());//地区
	                rentContract.setOfficeadd(officeadd);//办公室座落地点
	                rentContract.setAreaSqm(cellList.get(titleNum[8]).trim());//面积（平方米）
	                rentContract.setRentyear(cellList.get(titleNum[12]).trim());//租赁期限（月）
	                rentContract.setRentstarttime(cellList.get(titleNum[9]).trim());//开始日期
	                rentContract.setRentendtime(cellList.get(titleNum[11]).trim());//结束日期
	                rentContract.setPaycount(Utils.fToInt(cellList.get(titleNum[13]).trim()));//付款次数
	                rentContract.setPaymentcycle(
	                		DictionaryUtil.getKeyByValue(DictionaryUtil.payType_map,cellList.get(titleNum[14]).trim()));//付款方式（付款周期处理）
	                rentContract.setSignatory(cellList.get(titleNum[22]).trim());//签署合同公司
	                //财务端数据
	                rentContract.setContractNum(cellList.get(titleNum[0]).trim());//纸质合同编号
	                rentContract.setCostcenterNum(cellList.get(titleNum[1]).trim());//成本中心编号
	                rentContract.setPayorgName(cellList.get(titleNum[4]).trim());//付款部门
	                rentContract.setToAccount(cellList.get(titleNum[7]).trim());//汇入帐户（业主）
	                rentContract.setHousingDeposit(cellList.get(titleNum[15]).trim());//房屋押金
	                rentContract.setPayfirsttime(cellList.get(titleNum[10]).trim());//首付日期
	                rentContract.setPropertyDeposit(cellList.get(titleNum[16]).trim());//物业押金
	                rentContract.setRentFreePeriod(cellList.get(titleNum[17]).trim());//免租期(月)
	                rentContract.setAnnualRent(cellList.get(titleNum[18]).trim());//年租金（元）
	                rentContract.setMonthmoney(cellList.get(titleNum[19]).trim());//合同月租金
	                
	                //其他数据
	                rentContract.setStatus(
	                		DictionaryUtil.getKeyByValue(DictionaryUtil.rentStatus_map, cellList.get(titleNum[20]).trim()));//状态
	                rentContract.setRemark(cellList.get(titleNum[21]).trim());//备注
	                rentContract.setExt1(this.isCaiFuDuanRecord(orgname)?"2":"1");//财富端数据2，普惠职能端数据1
	                rentContract.setCreator(this.findUser().getUsername());//创建人
	                rentContract.setCreatortime(Utils.getLongDate());//创建时间
	                //插入数据
	                rentContractServiceImpl.insert(rentContract);
	                rentContractCount ++ ;
	            }
	            String spaceString = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	            this.importMessage += rentContractCount==0?(spaceString+"无房屋合同信息导入</br>")
	            		:(spaceString+"导入了"+rentContractCount+"条房屋合同信息</br>");
			}
        } 
	}
	
	/**
	 * 
	 * 描述：根据表头列名称获取列的number
	 * 2013-8-16 上午10:32:56 by caoyong
	 * @version
	 * @param list表头列集合
	 * @return result返回对应表头列的cellNumber
	 */
	private int[] getCellNumByName(List<String> list){
		String[] titles = {"纸质合同编号","成本中心编号","成本中心","使用部门","付款部门",
				"地区","办公室座落地点","汇入帐户（业主）","面积（平方米）","开始日期",
				"首付日期","结束日期","租赁期限（月）","付款次数","付款方式",
				"房屋押金","物业押金","免租期(月)","年租金（元）","合同月租金","状态","备注","签署合同公司"};
		int[] result = new int[titles.length];
		for(int j=0;j<titles.length;j++){
			for(int i=0;i<list.size();i++){
				if(titles[j].equals(list.get(i))){
					result[j] = i;break;
				}
			}
		}
		return result;
	}
	
	/**
	 * 
	 * 描述：判断记录是财富端(true)，普惠职能端(false)
	 * 2013-9-2 下午02:41:57 by caoyong
	 * @version
	 * @param orgname
	 * @return
	 */
	private boolean isCaiFuDuanRecord(String orgname){
		boolean result = false;
		if(orgname != null && !"".equals(orgname) &&
			("投一".equals(orgname) || "投二".equals(orgname) || "投三".equals(orgname) ||
					"理财产品与投资策略".equals(orgname) || "保险团队".equals(orgname) || "基金团队".equals(orgname)))
			result = true;
		return result;
	}
	/**
	 * 
	 * 描述：获取系统登陆用户
	 * 2013-8-28 下午03:06:10 by caoyong
	 * @version
	 * @return
	 */
	private  User findUser() {
		User user = (User)ServletActionContext.getRequest().getSession().getAttribute("user");
		return user;
	}
	/**
	 * 
	 * 描述：根据邮箱和办公室内地址查询已经存在的记录数据
	 * 2013-9-24 下午04:53:58 by caoyong
	 * @version
	 * @param mail
	 * @param officeadd
	 * @return
	 */
	private int selectPortByOfficeadd(String mail,String officeadd){
		Map<String, String> map = new HashMap<String, String>();
		map.put("mail", mail);
		map.put("officeadd", officeadd);
		return interfacePersonMapper.selectPortByOfficeadd(map);
	}
	public static void main(String[] args) throws Exception {
		ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
		InterfacePersonService service = (InterfacePersonService) app.getBean("interfacePersonServiceImpl");
//		List<Map<String, Object>> list = service.expire();
		
//		float x = 2.56f;
//		System.out.println(Utils.fToInt(x));
//		service.expire();
//		service.payfees();
//		service.sendorgmail();
//		service.sendPort();
		
//		service.sendEmailToYuAndXie("xieyuner", new MailSenderInfo());
//		service.sendEmailToYuAndXie("yujing", new MailSenderInfo());
//		service.sendorgmail();
	}
}