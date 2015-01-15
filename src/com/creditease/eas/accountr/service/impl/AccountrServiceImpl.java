package com.creditease.eas.accountr.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditease.eas.accountr.bean.Accountr;
import com.creditease.eas.accountr.bean.AccountrRemarks;
import com.creditease.eas.accountr.bean.AccountrUser;
import com.creditease.eas.accountr.dao.IAccountrMapper;
import com.creditease.eas.accountr.dao.IAccountrUserMapper;
import com.creditease.eas.accountr.service.IAccountrService;
import com.creditease.eas.admin.bean.User;
import com.creditease.eas.util.ExpExcelUtil;
import com.creditease.eas.util.PageUtil;
import com.creditease.eas.util.Pagination;
import com.creditease.eas.util.StringUtil;
import com.creditease.eas.util.Utils;
import com.creditease.eas.util.mail.MailSenderInfo;
import com.creditease.eas.util.mail.SendMailUtilNew;
import com.creditease.eas.util.port.ImportExecl;
/**
 * 
 * @AccountrServiceImpl.java
 * created at 2014-1-6 下午03:52:06 by sunxiaofeng
 * 财务报销提醒实现类
 * @author Administrator({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
@Service
public class AccountrServiceImpl implements IAccountrService {
	
	@Autowired
	private IAccountrMapper accountrMapper;
	@Autowired
	private IAccountrUserMapper accountrUserMapper;
	
	/**导入excel返回消息**/
	private String importMessage;
	private static final Logger logger = Logger
			.getLogger(AccountrServiceImpl.class);
   /**
    * 查询所有汇总邮件的收件人
    */
	public String findAccountrUser() {
		List<AccountrUser> accountrUserList=accountrUserMapper.findAccountrUser();
		return StringUtil.convertListToGson(accountrUserList);
	}
	/**
	 * 添加汇总邮件的收件人
	 */
	public void addAccountrUser(AccountrUser accountrUser) {
		accountrUserMapper.addAccountrUser(accountrUser);
	}
	/**
	 * 删除汇总邮件的收件人
	 */
	public void deleteAccountrUser(String fid) {
	   String[] idItem=fid.split(",");
		accountrUserMapper.deleteAccountrUser(idItem);
	}
	/**
	 * 查询公共备注
	 */
	public AccountrRemarks findAccountrRemarks() {
		List<AccountrRemarks> accountrRemarks = accountrUserMapper.findAccountrRemarks();
		return accountrRemarks.get(0);
	}
	/*public String findAccountrRemarks() {
		List<AccountrRemarks> accountrRemarks = accountrUserMapper.findAccountrRemarks();
		return StringUtil.convertListToGson(accountrRemarks);
	}*/
	/**
	 * 保存选中汇总邮件收件人及公共备注
	 */
	@SuppressWarnings("unused")
	public void updateAccountrUserRemarks(String[] fid,
			AccountrRemarks accountrRemarks) {
		int m=accountrUserMapper.updateAccountrUserNot(fid);
		int i=accountrUserMapper.updateAccountrUser(fid);
		int n=accountrUserMapper.updateAccountrRemarks(accountrRemarks);
	}
	/**
	 * 查询报销列表信息
	 */
	@SuppressWarnings("unchecked")
	public Pagination queryPage(Pagination page,
			HttpServletRequest request) {
		int currentPage = PageUtil.strToPage(request.getParameter("page"));
		int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
		Map map =new HashMap();
		map.put("accountrNum", request.getParameter("accountrNum"));
		map.put("startDate", request.getParameter("startDate"));
		map.put("endDate", request.getParameter("endDate"));
		map.put("employeesNum", request.getParameter("employeesNum"));
		map.put("startMoney", request.getParameter("startMoney"));
		map.put("endMoney", request.getParameter("endMoney"));
		map.put("company", request.getParameter("company"));
		map.put("department", request.getParameter("department"));
		map.put("email", request.getParameter("email"));
		map.put("personalRemark", request.getParameter("personalRemark"));
		map.put("employeesName", request.getParameter("employeesName"));
		map.put("fcreateuserName", request.getParameter("fcreateuserName"));
		map.put("status", request.getParameter("status"));
		//查询总行数的方法
		int totalCounts = accountrMapper.getTotalCountsByParams(map);
		page = new Pagination(currentPage,pageSize,totalCounts); 
		//查询数据集的方法
		Map mapTo = PageUtil.getMap(page);
		mapTo.putAll(map);
		List<Accountr> list = accountrMapper.queryPageByParams(mapTo);
		page.setRows(list);
		return page;
	}
	/**
	 * 根据id查询报销信息
	 */
	public Accountr findAccountr(Integer fid) {
		return accountrMapper.findAccountr(fid);
	}
	/**
	 * 新增报销信息
	 */
	public void insert(Accountr accountr) {
		accountr.setAccountrNum(GetNum(accountr.getAccountrDate()));
		accountrMapper.insert(accountr);	
	}
	/**
	 * 
	 * 描述：流水号格式
	 * 2014-1-13 下午02:20:16 by sunxiaofeng
	 * @version
	 * @param number
	 * @return
	 */
	 private  String zero(String number){
		    String [] z={"0000","000","00","0",""};
		    return z[number.length()] + (Integer.parseInt(number)+1);
	 }
	 /**
		 * 
		 * 描述：流水号
		 * 2014-1-13 上午09:50:00 by sunxiaofeng
		 * @version
		 * @param number
		 * @return
		 */
     private String GetNum(Date date){
    	 String accountrNum=accountrMapper.getAccountrNumByDate(Utils.getDate(date));
 		String num="";
 		if(!"".equals(accountrNum) && null != accountrNum){
 			 num=Utils.getDate(date).replace("-", "")+zero(Integer.parseInt(accountrNum.substring(8, accountrNum.length()))+"");
 		}else{
 			num=Utils.getDate(date).replace("-", "")+zero("0");
 		}
 		return num;
	 }
	/**
	 * 修改报销信息
	 */
	public void update(Accountr accountr) {
		accountrMapper.update(accountr);		
	}
	/**
	 * 删除报销信息
	 */
	public void deleteAccountr(Integer fid) {
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		Accountr accountr =accountrMapper.findAccountr(fid);
		accountr.setFcreateuserId(Integer.parseInt(user.getId().toString()));
		accountrMapper.insertAccountrLog(accountr);//把要删除的信息报存
		accountrMapper.deleteAccountr(fid);//删除信息
	}
	public String importExcelValidation(File excelFile, String filePath) throws Exception{
		String jsonResult = "";
		String temp[] = filePath.replaceAll("\\\\", ",").split(",");
		String str = temp[temp.length - 1];
		String directory = "/upload/prot";
		String targetDirectory = ServletActionContext.getServletContext()
				.getRealPath(directory);
		// 生成上传的文件对象
		File target = new File(targetDirectory, str);
		// 如果文件已经存在，则删除原有文件
		if (target.exists()) {
			target.delete();
		}
		// 复制file对象，实现上传
		FileUtils.copyFile(excelFile, target);
		ImportExecl poi = new ImportExecl();
		// //房屋合同信息源数据
		List<List<String>> list = poi.read(target.getPath(), 0);//0表示是excel的第一页
		String num="";
		 Pattern pattern = Pattern.compile("[0-9]*.[0-9]{0,2}");
		 Pattern patternEmail = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@creditease.cn$");
		for (int i = 1; i < list.size(); i++) {
			List<String> cellList = list.get(i);
				Matcher isNum = pattern.matcher(cellList.get(3));
				Matcher isEmail = patternEmail.matcher(cellList.get(6));
				if ("".equals(cellList.get(0))
						|| "".equals(cellList.get(1))
						|| "".equals(cellList.get(2))
						|| "".equals(cellList.get(3))
						|| "".equals(cellList.get(4))
						|| "".equals(cellList.get(5))
						|| "".equals(cellList.get(6))
						|| !isNum.matches()
						|| !isEmail.matches()) {
					num+=i+",";
				}
			}
		this.importMessage = "";
		if(!"".equals(num)){
			this.importMessage="报销信息"+num.substring(0,num.length()-1)+"行数据有问题，是否继续导入？";
		}else{
			this.importMessage="是否继续导入报销信息？";
		}
		if (0 == list.size())
			this.importMessage += "请检查报销信息汇总sheet格式或数据</br>";
		jsonResult = "{\"success\":\"true\", \"message\":\""
				+ this.importMessage + "\"}";
		return jsonResult;
	}
	/**
	 * 导入报销信息
	 */
	public String importExcel(File excelFile, String filePath) throws Exception {
		String jsonResult = "";
		String temp[] = filePath.replaceAll("\\\\", ",").split(",");
		String str = temp[temp.length - 1];
		String directory = "/upload/port";
		String targetDirectory = ServletActionContext.getServletContext()
				.getRealPath(directory);
		// 生成上传的文件对象
		File target = new File(targetDirectory, str);
		// 如果文件已经存在，则删除原有文件
		if (target.exists()) {
			target.delete();
		}
		// 复制file对象，实现上传
		FileUtils.copyFile(excelFile, target);
		ImportExecl poi = new ImportExecl();
		// //房屋合同信息源数据
		List<List<String>> list = poi.read(target.getPath(), 0);//0表示是excel的第一页
		this.importMessage = "";
		if (0 == list.size())
			this.importMessage += "请检查报销信息汇总sheet格式或数据</br>";
		this.importRentContracts(list);
		jsonResult = "{\"success\":\"true\", \"message\":\""
				+ this.importMessage + "\"}";
		return jsonResult;
	}

	/**
	 * 
	 * 描述：导入财务报销信息（处理）
	 * 
	 * @version
	 */
	@SuppressWarnings("unchecked")
	private void importRentContracts(List<List<String>> list) throws Exception {
		 Pattern pattern = Pattern.compile("[0-9]*.[0-9]{0,2}");
		 Pattern patternEmail = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@creditease.cn$");
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if (list != null) {
			if (list.size() > 1) {
				List<Accountr> accountrList=new ArrayList<Accountr>();
				List<String> titlesList = list.get(0);
				int[] titleNum = this.getCellNumByName(titlesList);// 表头列对应的列number
				String num = "";
				for (int i = 1; i < list.size(); i++) {
					List<String> cellList = list.get(i);
					 Matcher isNum = pattern.matcher( cellList.get(titleNum[3]).trim());
					 Matcher isEmail = patternEmail.matcher( cellList.get(titleNum[6]).trim());
                    if("" != cellList.get(titleNum[0]).trim() 
                    	&& "" != cellList.get(titleNum[1]).trim()
                    	&& "" != cellList.get(titleNum[2]).trim() 
                    	&& "" != cellList.get(titleNum[3]).trim() 
                    	&& "" != cellList.get(titleNum[4]).trim()
                    	&& "" != cellList.get(titleNum[5]).trim() 
                    	&& "" != cellList.get(titleNum[6]).trim()
                    	&& isNum.matches()
                    	&& isEmail.matches()){//判断 报销日期,员工工号, 姓名, 报销金额, 公司,部门,
                    	                       //邮箱 是否为空以及报销金额是否是数字， 邮箱是否正确
						Accountr accountr =new  Accountr();
						accountr.setAccountrDate(Utils.toDate(cellList.get(titleNum[0]).trim()));//报销时间
						if(cellList.get(titleNum[1]).trim().indexOf(".")!=-1){
						  accountr.setEmployeesNum(cellList.get(titleNum[1]).trim().substring(0,cellList.get(titleNum[1]).trim().indexOf(".")));//员工工号
						}else{
						  accountr.setEmployeesNum(cellList.get(titleNum[1]).trim());//员工工号
						}
						accountr.setEmployeesName(cellList.get(titleNum[2]).trim());//姓名
						accountr.setAccountrMoney(cellList.get(titleNum[3]).trim());//报销金额
						accountr.setCompany(cellList.get(titleNum[4]).trim());//公司
						accountr.setDepartment(cellList.get(titleNum[5]).trim());//部门
						accountr.setEmail(cellList.get(titleNum[6]).trim());//邮箱
						accountr.setPersonalRemark(cellList.get(titleNum[7]).trim());//个人备注
						
						accountr.setFcreateuserId(Integer.parseInt(user.getId().toString()));//创建人
						accountr.setFcreateTime(Utils.getLongDate());//创建时间
						accountr.setFcreateuserName(user.getUsername());//创建人
						accountr.setAccountrNum(GetNum(Utils.toDate(cellList.get(titleNum[0]).trim())));//报销信息编号
						accountr.setStatus(0);//发送状态
						//accountrList.add(accountr);
						accountrMapper.insert(accountr);
                    }else{
                    	num+=i+",";
                    }
				}
				//批量新增
				//if(accountrList.size()!=0){
				// accountrMapper.InsertAll(accountrList);
				 this.importMessage += num == "" ? ("报销信息全部导入</br>")
							: ("报销信息" + num + "行没有导入</br>");
				}else{
					this.importMessage="没有可导入的报销信息!";
				}
				
			//}
		}
	}
	/**
	 * 
	 * 描述：根据表头列名称获取列的
	 * 
	 * @version
	 * @param list表头列集合
	 * @return result返回对应表头列的cellNumber
	 */
	private int[] getCellNumByName(List<String> list) {
		String[] titles = { "报销日期", "员工工号", "姓名", "报销金额", "公司", "部门",
				"邮箱", "备注"};
		int[] result = new int[titles.length];
		for (int j = 0; j < titles.length; j++) {
			for (int i = 0; i < list.size(); i++) {
				if (titles[j].equals(list.get(i))) {
					result[j] = i;
					break;
				}
			}
		}
		return result;
	}
	/**
 	* 补发发送邮件
	 * @throws Exception 
 	*/
	public void reissueEmail(String fids) throws Exception {
	  String[] fid=fids.split(",");
	  List<Accountr> accountrList = accountrMapper.getAccountrById(fid);
	  for (int i = 0; i <accountrList.size(); i++) {
		  sendEmail(accountrList.get(i));
	  }
	 // boolean  success=sendEmailExcel(accountrList);
	}
	/**
	 * 批量发送邮件及汇总邮件
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public void batchEmail(String fids,HttpServletRequest request) throws Exception {
		String[] fid=fids.split(",");
		Map map=new HashMap();
		map.put("accountrNum", request.getParameter("accountrNum"));
		map.put("startDate", request.getParameter("startDate"));
		map.put("endDate", request.getParameter("endDate"));
		map.put("employeesNum", request.getParameter("employeesNum"));
		map.put("startMoney", request.getParameter("startMoney"));
		map.put("endMoney", request.getParameter("endMoney"));
		map.put("company", request.getParameter("company"));
		map.put("department", request.getParameter("department"));
		map.put("email", request.getParameter("email"));
		map.put("personalRemark", request.getParameter("personalRemark"));
		map.put("fcreateuserName", request.getParameter("fcreateuserName"));
		map.put("employeesName", request.getParameter("employeesName"));
		map.put("status", request.getParameter("status"));
		if(!"".equals(fid[0])){//判断是否有不发送邮件的报销信息
			map.put("fid",fid);
		}
		  List<Accountr> accountrList = accountrMapper.getAccountrByIdBatch(map);
		  for (int i = 0; i <accountrList.size(); i++) {
			  sendEmail(accountrList.get(i));
		  }
		  boolean  success=sendEmailExcel(accountrList);
	}
	
	/**
	 * 
	 * 描述：给报销人发邮件
	 * 2014-1-15 下午03:01:42 by sunxiaofeng
	 * @version
	 * @param accountr
	 * @return
	 */
	private void sendEmail(Accountr accountr){
		List<AccountrRemarks> accountrRemarks =accountrUserMapper.findAccountrRemarks();
		String str=accountrRemarks.get(0).getFremarks();
		StringBuffer buffer=new StringBuffer();
		buffer.append("<br/><font size='4'>"+accountr.getEmployeesName()+":你好!<br/>");
		buffer.append(Utils.getDate(accountr.getAccountrDate())+"日已将您的报销款"+accountr.getAccountrMoney()+"元汇入您的个人招商银/工商银行,请注意查收。</font>");
		if(!"".equals(accountr.getPersonalRemark())&&null!=accountr.getPersonalRemark()){
			buffer.append("<font size='4' style='background-color: #ffff00'>备注："+accountr.getPersonalRemark()+"</font>");
		}
		buffer.append("<br/>");
		buffer.append("<font size='4'>若收到金额与报销金额不符者，请于10个工作日内联系财务。<br/>");
		buffer.append(str);
		MailSenderInfo ms = new MailSenderInfo();
		Properties pr = ms.getProperties();
		String fromAddress=pr.getProperty("ACCOUNTR_USERNAME");
		String password=pr.getProperty("ACCOUNTR_PASSWORD");
		String[] toAddress={accountr.getEmail()};
		String title=Utils.getDate(accountr.getAccountrDate())+""+accountr.getCompany()+"日常报销邮件";
		String htmlContent=buffer.toString();
		if(pr.getProperty("to_local_Address") != null && "yes".equals(pr.getProperty("to_local_Address"))){
			toAddress = pr.getProperty("toAddress").split(",");//代码正式提交时邮件接收人为接口人邮箱，此行注释掉
		}
		Boolean success=SendMailUtilNew.sendMailToManyPerson(fromAddress, password, toAddress, title, htmlContent);
		if(true==success){
			logger.info("邮件发送成功");
			accountr.setStatus(1);//1表示发送成功
			accountrMapper.updateById(accountr);
		}else{
			accountr.setStatus(2);//2表示发送失败
			accountrMapper.updateById(accountr);
		 logger.info("邮件发送失败");
		}
	}
	/**
	 * 
	 * 描述：发送汇总文件
	 * 2014-1-15 下午06:45:12 by sunxiaofeng
	 * @version
	 * @param dataset
	 * @return
	 * @throws Exception
	 */
	private boolean sendEmailExcel(List<Accountr> dataset) throws Exception{
		//查询设置好的收件人
		List<AccountrUser> accountrUserList =accountrUserMapper.getSelectUser();
		String [] toAddress=new String[accountrUserList.size()];
		List<Accountr> accountList=new ArrayList<Accountr>();
		MailSenderInfo ms = new MailSenderInfo();
		Properties pr = ms.getProperties();
		for (int i = 0; i < accountrUserList.size(); i++) {
			toAddress[i]=accountrUserList.get(i).getFuseremail();
		}
		if(pr.getProperty("to_local_Address") != null && "yes".equals(pr.getProperty("to_local_Address"))){
			toAddress = pr.getProperty("toAddress").split(",");//代码正式提交时邮件接收人为接口人邮箱，此行注释掉
		}
		if(toAddress.length!=0){
		for (int i = 0; i < dataset.size(); i++) {
			if(null != dataset.get(i).getPersonalRemark() && !"".equals(dataset.get(i).getPersonalRemark())){
				accountList.add(dataset.get(i));
			}
		}
		String filestr = this.exportExcel(accountList);
		String title = "报销信息汇总";
		String fromAddress=pr.getProperty("ACCOUNTR_USERNAME");
		String password=pr.getProperty("ACCOUNTR_PASSWORD");
		
		boolean success = SendMailUtilNew.sendFileMail(fromAddress,password, toAddress,
				title, filestr);
		if(true==success){
			logger.info("发送报销信息附件信息成功");
		}else{
			logger.error("发送报销信息附件信息失败");
		}
		return success;
		}
	 return false;
	}
	/**
	 * 
	 * 描述：汇总excel发送给指定人员
	 * 2014-1-15 下午04:31:40 by sunxiaofeng
	 * @version
	 * @param dataList
	 * @return
	 * @throws IOException
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 */
	private String exportExcel(List<Accountr> dataset)throws Exception {
		String[] headers={ "报销日期", "员工工号", "姓名", "报销金额", "公司", "部门",
				"邮箱", "备注"};
	    String[] title={"getAccountrDate","getEmployeesNum","getEmployeesName","getAccountrMoney",
	    		"getCompany","getDepartment","getEmail","getPersonalRemark"};
		String filestr = null;
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFCellStyle cellStyle = wb.createCellStyle();
		HSSFSheet sheet = wb.createSheet("报销信息汇总");//设置页的名称
		HSSFRow row=sheet.createRow(0);
		row.setHeight((short)500);//设置行高
		for (int i = 0; i < headers.length; i++) {
			sheet.setColumnWidth(i, 5000);//设置单元格的宽度
			ExpExcelUtil.createTxtCell(wb, row, i, headers[i],ExpExcelUtil.createCollStyleCenter(cellStyle,wb));//创建表格第一行
		}
		if (null != dataset && !dataset.isEmpty()){//判断数据是否为空
			Object[] objs = dataset.toArray();//转换为数组
			for (int i = 0; i < objs.length; i++) {
				HSSFRow rowTo=sheet.createRow(i+1);//创建行，设置从第二行开始
				rowTo.setHeight((short)500);
				Accountr t = (Accountr) objs[i];
				for (int j = 0; j < title.length; j++) {
					if(title[j]!=""){
						String getMethodName = title[j];
						Class tCls = t.getClass();
						Method getMethod = tCls.getMethod(getMethodName,//利用反射获取值
							new Class[] {});
						Object value = getMethod.invoke(t, new Object[] {});
							ExpExcelUtil.createTxtCell(wb, rowTo, j, value,ExpExcelUtil.createCollStyle(cellStyle,wb));
					}
				}
			}
		}
		FileOutputStream fout;
		try {
			MailSenderInfo ms = new MailSenderInfo();
			Properties pr = ms.getProperties();
			String portfile = pr.getProperty("portfile");
		   // portfile = "D:/";// 测试时用本地路径，上线时将此行注释掉
			filestr = portfile + "报销信息汇总" + Utils.getNowcurrTime() + ".xls";
			fout = new FileOutputStream(filestr);
			wb.write(fout);
			fout.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return filestr;
	}
}
