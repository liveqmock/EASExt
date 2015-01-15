/**
 * 
 */
package com.creditease.eas.warn.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

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

import com.creditease.eas.admin.bean.User;
import com.creditease.eas.admin.dao.ExtDataBaseMapper;
import com.creditease.eas.admin.dao.UserRoleMapper;
import com.creditease.eas.util.DateUtil;
import com.creditease.eas.util.DictionaryUtil;
import com.creditease.eas.util.ExpExcelUtil;
import com.creditease.eas.util.Holiday;
import com.creditease.eas.util.PageUtil;
import com.creditease.eas.util.Pagination;
import com.creditease.eas.util.StringUtil;
import com.creditease.eas.util.Utils;
import com.creditease.eas.util.mail.MailSenderInfo;
import com.creditease.eas.util.mail.SendMailUtilNew;
import com.creditease.eas.util.port.ImportExecl;
import com.creditease.eas.warn.bean.FinanceOperation;
import com.creditease.eas.warn.bean.FinanceRentContract;
import com.creditease.eas.warn.bean.FinanceUser;
import com.creditease.eas.warn.bean.OrgCostcenter;
import com.creditease.eas.warn.dao.FinanceOperationMapper;
import com.creditease.eas.warn.dao.FinanceRentContractMapper;
import com.creditease.eas.warn.dao.FinanceUserMapper;
import com.creditease.eas.warn.kingdee.query.OrgCostcenterQuery;
import com.creditease.eas.warn.service.FinanceRentContractService;
import com.sun.org.apache.commons.beanutils.PropertyUtils;

/**
 * @FinanceRentContractServiceImpl.java 财务房租合同信息service实现类 created at 2013-9-16
 *                                      上午11:08:34 by caoyong
 * 
 * @author caoyong({@link authorEmail})
 * @version $Revision$</br> update: $Date$
 */
@Service
public class FinanceRentContractServiceImpl implements FinanceRentContractService {
	@Autowired
	private FinanceRentContractMapper financeRentContractMapper;
	private FinanceRentContract financeRentContract;
	@Autowired
	private FinanceOperationMapper financeOperationMapper;
	/** 导入excel返回消息 **/
	@Autowired
	private FinanceUserMapper financeUserMapper;
	@Autowired
	private ExtDataBaseMapper extDataBaseMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;

	/**导入excel返回消息**/
	private String importMessage;
	private static final Logger logger = Logger
			.getLogger(FinanceRentContractServiceImpl.class);

	@Override
	public int insert(FinanceRentContract financeRentContract) throws Exception {
		//将新增的合同信息插入到数据库
		financeRentContractMapper.insert(financeRentContract);
		
		//将新增的合同信息维护到T_FINANCE_OPERATION表中
		String remark = this.apendInfo(financeRentContract);
		insertFinacerentcontractToOperation(remark);
		
		return 0;
	}
	public int delete(Integer id) throws Exception {
		int i = financeRentContractMapper.deleteByPrimaryKey(id);
		return i;
	}
/**
 * 修改合同信息，并发送邮件
 */
	public boolean update(FinanceRentContract financeRentContract) throws Exception {
		HttpServletRequest request= ServletActionContext.getRequest();
		//获得当前登录用户
		User user = (User)request.getSession().getAttribute("user");
		//根据id获取数据库中的数据(修改前的数据)
		FinanceRentContract financeRentContractOld=financeRentContractMapper.findFinanceKeID(financeRentContract.getId());
		OrgCostcenter orgCostcenterQuery=OrgCostcenterQuery.selectByFname(financeRentContractOld.getExt1());
		if(orgCostcenterQuery!=null){
		  financeRentContractOld.setLastCostCenter(orgCostcenterQuery.getFname_l2());
		}
		List<FinanceUser> FinanceUserList=financeUserMapper.findFinUser();//获得所有组用户信息

		List<FinanceUser> financeuserlist=new ArrayList<FinanceUser>();
		
		for (int j = 0; j < FinanceUserList.size(); j++) {
			if(FinanceUserList.get(j).getChargeCompanies().indexOf(",")==-1&&FinanceUserList.get(j).getChargeCompanies()!=null){//判断给用户是否负责该公司     注：用户可能负责多个公司
				if(FinanceUserList.get(j).getChargeCompanies().equals(financeRentContractOld.getSignatory())){
					financeuserlist.add(FinanceUserList.get(j));
				}
			}else{
				String[] strCompanies=FinanceUserList.get(j).getChargeCompanies().split(",");
				for (int k = 0; k < strCompanies.length; k++) {
					
					if(strCompanies[k].trim().equals(financeRentContractOld.getSignatory()+"")){
						financeuserlist.add(FinanceUserList.get(j));
					}
				}
			}
		}
	   
		//利用Java反射判断用户修改了那些字段
		Field[]  fields=financeRentContract.getClass().getDeclaredFields();
		String FieldUpdate="";
		String fieldinfo="";
		Object contractNum = PropertyUtils.getProperty(financeRentContractOld, "contractNum");//合同编号
		for (Field field : fields) {
			String fieldName=field.getName();//获得属性名
			Object valueNew=PropertyUtils.getProperty(financeRentContract, fieldName);//获得值
			Object valueold=PropertyUtils.getProperty(financeRentContractOld, fieldName);
			if(valueNew==null)
			  valueNew="";
			if(valueold==null)
			  valueold="";
			if(fieldName.equals("lastUpdater")||fieldName.equals("lastUpdateTime")||fieldName.equals("creator")||fieldName.equals("creatorTime")||fieldName.equals("ext1")){
			
			}else{
				if(!valueNew.equals(valueold)){
					if(fieldName.equals("rentStartTime")||fieldName.equals("rentEndTime")||fieldName.equals("payFirstTime")){
						fieldinfo+=",修改字段："+getMapKe().get(fieldName)+" 内容:"+Utils.toDate2(valueold)+" 修改为:"+Utils.toDate2(valueNew);
					}else{
						fieldinfo+=",修改字段："+getMapKe().get(fieldName)+" 内容:"+valueold+" 修改为:"+valueNew; 
					}
						
				}
			}
		}
		boolean flag=false;
		if(fieldinfo!=""){
			int i = financeRentContractMapper.updateByPrimaryKey(financeRentContract);//修改
			FieldUpdate = "系统在试运行阶段，如有问题请及时反馈。      用户："+user.getUsername()+"修改了纸质合同编号"+contractNum+"合同"+fieldinfo;
			//发送邮件
			flag=this.sendEmailToChargeMen(FieldUpdate,financeuserlist);
			//将修改的合同信息维护到T_FINANCE_OPERATION表中
			editFinacerentcontractToOperation(FieldUpdate);
		}
		
		
		
	  return flag;
	}
	@SuppressWarnings("unchecked")
	private  Map getMapKe() throws Exception{
		
		Map map = HashMap.class.newInstance();
		map.put("orgNumber","使用部门编号 ");
		map.put("orgName", "使用部门 ");
		map.put("city","地区 ");
		map.put("officeAdd", "办公室座落地点 ");
		map.put("lastCostCenter", "成本中心 ");
		map.put("signatory","签署合同公司 ");
		
		map.put("payMoney","合同金额 ");
		map.put("monthMoney", "合同月租金");
		map.put("paymentCycle","付款方式 ");
		map.put("payCount", "付款次数 ");
		map.put("rentStartTime", "开始日期 ");
		map.put("rentEndTime","结束日期 ");
		map.put("payFirstTime","首付日期 ");
		
		map.put("rentYear","租赁期限 ");
		map.put("contractNum", "纸质合同编号");
		map.put("costCenterNum","成本中心编号 ");
		map.put("payOrgName", "付款部门 ");
		map.put("toAccount", "汇入帐户（业主） ");
		map.put("areaSqm","面积（平方米） ");
		
		map.put("housingDeposit","房屋押金 ");
		map.put("propertyDeposit", "物业押金");
		map.put("rentFreePeriod","免租期(月) ");
		map.put("annualRent", "年租金（元） ");
		map.put("status", "状态 ");
		map.put("remark","备注 ");
		map.put("ext1","fid ");
	return map;
	}
	/**
	 * 修改数据后发送邮件
	 * 描述：
	 * 2013-9-29 下午03:34:35 by admin
	 * @version
	 * @param content
	 * @param financeuserlist
	 * @return
	 */
     private boolean sendEmailToChargeMen(String content,List<FinanceUser> financeuserlist){
    	  MailSenderInfo ms = new MailSenderInfo();
    	  Properties pr = ms.getProperties();
    	  String fromAddress = pr.getProperty("FINANCE_USERNAME");//获得发邮件人 的邮箱
    	  String password=pr.getProperty("FINANCE_PASSWORD");
    	  String[] toAddress=null;//接受人的邮箱
    		  String email="";
    		  for (int i = 0; i <financeuserlist.size(); i++) {
    		        if(financeuserlist.get(i).getUserEmail()!=null||!financeuserlist.get(i).getUserEmail().equals(""))
    		        	email+=financeuserlist.get(i).getUserEmail()+",";
    		  }
   		  if(email!=""){
    			  String userEmail[]=email.split(",");
    			  toAddress=new String[userEmail.length];
    			 for (int j = 0; j <userEmail.length; j++) {
    				 toAddress[j]=userEmail[j];
    			 }
    		  }
   		if(pr.getProperty("to_local_Address") != null && "yes".equals(pr.getProperty("to_local_Address"))){
			toAddress = pr.getProperty("toAddress").split(",");//代码正式提交时邮件接收人为接口人邮箱，此行注释掉
		}
   		  //发送邮件
    		boolean success = SendMailUtilNew.sendMailToManyPerson(fromAddress,password,toAddress,"更新合同提醒",content);	
    		return success;
     }
     /**
      *编辑
      */
	public FinanceRentContract getFinanceRentContractById(Integer id)
			throws Exception {
		financeRentContract = financeRentContractMapper.selectByPrimaryKey(id);
		//设置成本中心名称，注意：是根据id到eas数据库中查到的。
		OrgCostcenter orgCostcenter=OrgCostcenterQuery.selectByFname(financeRentContract.getExt1());
		if(orgCostcenter!=null){
			financeRentContract.setLastCostCenter(orgCostcenter.getFname_l2());
			financeRentContract.setCostCenterNum(orgCostcenter.getFnumber());
		}else{
			financeRentContract.setLastCostCenter("");
			financeRentContract.setCostCenterNum("");
		}
		return financeRentContract;
	}
	@SuppressWarnings("unchecked")
	public Pagination queryPage(Pagination page) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		int currentPage = PageUtil.strToPage(request.getParameter("page"));
		int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
		Map map = HashMap.class.newInstance();
		map.put("orgName", request.getParameter("orgName"));
		map.put("startDate", request.getParameter("startDate"));
		map.put("paymentCycle", request.getParameter("paymentCycle"));
		//map.put("lastCostCenter", request.getParameter("lastCostCenter"));
		map.put("endDate", request.getParameter("endDate"));
		map.put("monthMoney", request.getParameter("monthMoney"));
		map.put("city", request.getParameter("city"));
		map.put("contractNum", request.getParameter("contractNum"));
		//map.put("signatorys",this.getSignatorys());
		map.put("status", request.getParameter("status"));
		if(request.getParameter("signatory2")==""||request.getParameter("signatory2")==null){
			map.put("signatorys",this.getSignatorys());	
		}else{
			map.put("signatory2",request.getParameter("signatory2"));
			
		}	
		List<String>  fnamelist=null;
		if(request.getParameter("lastCostCenter")!=null&&!"".equals(request.getParameter("lastCostCenter"))){
			fnamelist=OrgCostcenterQuery.selectIdByFname(StringUtil.addLikeOperBoth(request.getParameter("lastCostCenter")));
		}
		map.put("list", fnamelist);
		//查询总行数的方法
		int totalCounts = financeRentContractMapper.getTotalCounts(map);
		page = new Pagination(currentPage, pageSize, totalCounts);
		// 查询数据集的方法
		Map map1 = PageUtil.getMap(page);
		map1.putAll(map);
		List<FinanceRentContract> list = financeRentContractMapper.queryPage(map1);
		List<String> fidlist=new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getExt1()!=null)
			fidlist.add(list.get(i).getExt1());
		}
		List<OrgCostcenter> orgCostcenterList=OrgCostcenterQuery.selectByFname(fidlist);
		
			for (int i = 0; i <list.size(); i++) {
				if(orgCostcenterList!=null){
				for (int j = 0; j<orgCostcenterList.size(); j++) {
					if(orgCostcenterList.get(j).getFid().equals(list.get(i).getExt1())){
						list.get(i).setLastCostCenter(orgCostcenterList.get(j).getFname_l2());
						break;
					}else{
						list.get(i).setLastCostCenter("");
					}
				}
			}else{
				list.get(i).setLastCostCenter("");
			}	
		}
		page.setRows(list);
		return page;
	}

	@Override
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
		List<List<String>> list = poi.read(target.getPath(), 0);
		this.importMessage = "";
		if (list.size() == 0)
			this.importMessage += "请检查房屋合同信息汇总sheet格式或数据</br>";
		this.importRentContracts(list);
		jsonResult = "{\"success\":\"true\", \"message\":\""
				+ this.importMessage + "\"}";
		return jsonResult;
	}

	/**
	 * 
	 * 描述：导入房屋合同记录（重复的记录执行更新操作） 2013-9-16 下午03:08:56 by caoyong
	 * 
	 * @version
	 */
	@SuppressWarnings("unchecked")
	private void importRentContracts(List<List<String>> list) throws Exception {
		if (list != null) {
			if (list.size() > 2) {
				List<FinanceRentContract> financeList=new ArrayList<FinanceRentContract>();
				List<String> fnumberlist=new ArrayList<String>();
				List<String> titlesList = list.get(1);
				int[] titleNum = this.getCellNumByName(titlesList);// 表头列对应的列numberz
				int insertNum = 0, updateNum = 0;
				for (int i = 2; i < list.size(); i++) {
					List<String> cellList = list.get(i);
					String officeAdd = cellList.get(titleNum[6]).trim();// 办公室座落地点
					String orgName = cellList.get(titleNum[3]).trim();// 使用部门
					String contractNum = cellList.get(titleNum[0]).trim();// 纸质合同编号

					Map Params = HashMap.class.newInstance();
					Params.put("contractNum", contractNum);
					Params.put("officeAdd", officeAdd);
					List<Map> existedRecords = financeRentContractMapper
							.selectRentByParams(Params);// 重复的记录执行更新操作
					FinanceRentContract financeRentContract = FinanceRentContract.class
							.newInstance();
					// 行政端数据
					financeRentContract.setLastCostCenter(cellList.get(
							titleNum[2]).trim());// 成本中心
					financeRentContract.setOrgName(orgName);// 使用部门
					financeRentContract.setCity(cellList.get(titleNum[5])
							.trim());// 地区
					financeRentContract.setOfficeAdd(officeAdd);// 办公室座落地点
					financeRentContract.setAreaSqm(cellList.get(titleNum[8])
							.trim());// 面积（平方米）
					financeRentContract.setRentYear(cellList.get(titleNum[12])
							.trim());// 租赁期限（月）
					financeRentContract.setRentStartTime(Utils.toDate(cellList
							.get(titleNum[9]).trim()));// 开始日期
					financeRentContract.setRentEndTime(Utils.toDate(cellList
							.get(titleNum[11]).trim()));// 结束日期
					financeRentContract.setPayCount(Utils.fToInt(cellList.get(
							titleNum[13]).trim()));// 付款次数
					financeRentContract.setPaymentCycle(DictionaryUtil
							.getKeyByValue(DictionaryUtil.payType_map, cellList
									.get(titleNum[14]).trim()));// 付款方式（付款周期处理）
					financeRentContract.setSignatory(cellList.get(titleNum[22])
							.trim());// 签署合同公司
					// 财务端数据
					financeRentContract.setContractNum(cellList
							.get(titleNum[0]).trim());// 纸质合同编号
					financeRentContract.setCostCenterNum(cellList.get(
							titleNum[1]).trim());// 成本中心编号
					financeRentContract.setPayOrgName(cellList.get(titleNum[4])
							.trim());// 付款部门
					financeRentContract.setToAccount(cellList.get(titleNum[7])
							.trim());// 汇入帐户（业主）
					financeRentContract.setHousingDeposit(cellList.get(
							titleNum[15]).trim());// 房屋押金
					financeRentContract.setPayFirstTime(Utils.toDate(cellList
							.get(titleNum[10]).trim()));// 首付日期
					financeRentContract.setPropertyDeposit(cellList.get(
							titleNum[16]).trim());// 物业押金
					financeRentContract.setRentFreePeriod(cellList.get(
							titleNum[17]).trim());// 免租期(月)
					financeRentContract.setAnnualRent(cellList
							.get(titleNum[18]).trim());// 年租金（元）
					financeRentContract.setMonthMoney(cellList
							.get(titleNum[19]).trim());// 合同月租金

					// 其他数据
					financeRentContract.setStatus(Integer
							.parseInt(DictionaryUtil.getKeyByValue(
									DictionaryUtil.rentStatus_map, cellList
											.get(titleNum[20]).trim())));// 状态
					financeRentContract.setRemark(cellList.get(titleNum[21])
							.trim());// 备注
					if (existedRecords != null && existedRecords.size() > 0) {
						
						Integer id = Integer.parseInt(existedRecords.get(0).get("ID").toString());
						financeRentContract.setLastUpdater(this.findUser()
								.getUsername());
						financeRentContract.setId(id);
						String fid=OrgCostcenterQuery.selectById(cellList.get(titleNum[1]).trim());
						financeRentContract.setExt1(fid);
						this.update(financeRentContract);// 更新数据
						updateNum++;
						
						//根据id获取数据库中的数据(更新前的数据)
						//FinanceRentContract financeRentContractOld= financeRentContractMapper.findFinanceKeID(id);
						//利用Java反射判断用户修改了那些字段
						/*Field[]  fields=financeRentContract.getClass().getDeclaredFields();
						Object contractNum2 = PropertyUtils.getProperty(financeRentContractOld, "contractNum");//合同编号
						String FieldUpdate="";
						String fieldinfo="";
						for (Field field : fields) {
							String fieldName=field.getName();//获得属性名
							Object valueNew=PropertyUtils.getProperty(financeRentContract, fieldName);//获得值
							Object valueold=PropertyUtils.getProperty(financeRentContractOld, fieldName);
							if(valueNew==null)
							  valueNew="";
							if(valueold==null)
							  valueold="";
							if(fieldName.equals("lastUpdater")||fieldName.equals("lastUpdateTime")||fieldName.equals("creator")||fieldName.equals("creatorTime")){
							}else{
								if(!valueNew.equals(valueold)){
									fieldinfo+="合同,更新字段："+getMapKe().get(fieldName)+" 内容:"+valueold+" 更新为:"+valueNew; 
								}
							}
						}
						if(fieldinfo!=""){
							FieldUpdate="系统在试运行阶段，如有问题请及时反馈。  用户："+this.findUser().getUsername()+"更新了纸质合同编号"+contractNum2;
							//将更新的合同信息维护到T_FINANCE_OPERATION表中
							editFinacerentcontractToOperation(FieldUpdate);
						}*/
					} else {
						financeRentContract.setCreator(this.findUser()
								.getUsername());// 创建人
						financeRentContract.setCreatorTime(Utils.getLongDate());// 创建时间
						//String fid=OrgCostcenterQuery.selectById(cellList.get(titleNum[1]).trim());
						//financeRentContract.setExt1(fid);
						fnumberlist.add(cellList.get(titleNum[1]).trim());
						financeList.add(financeRentContract);
						//this.insert(financeRentContract);// 插入数据
						insertNum++;
						//将导入的合同信息维护到T_FINANCE_OPERATION表中
						String remark = this.apendInfo(financeRentContract);
						insertFinacerentcontractToOperation(remark);
					}
				}
			if(fnumberlist.size()!=0){
				//根据成本中心编号去EAS数据库查询数据
				List<OrgCostcenter> orgCostcenterlist=OrgCostcenterQuery.selectByIds(fnumberlist);
				for (int i = 0; i < financeList.size(); i++) {
					for (int j = 0; j < orgCostcenterlist.size(); j++) {
						if(orgCostcenterlist.get(j).getFnumber().equals(financeList.get(i).getCostCenterNum())){
							financeList.get(i).setExt1(orgCostcenterlist.get(j).getFid());//设置fid
						}
					}
				}
				//批量新增
				financeRentContractMapper.InsertAll(financeList);
			}
			
				String spaceString = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				this.importMessage += insertNum == 0 ? (spaceString + "无新增房屋合同信息记录</br>")
						: (spaceString + "新增了" + insertNum + "条房屋合同信息</br>");
				this.importMessage += updateNum == 0 ? (spaceString + "无重复房屋合同信息记录</br>")
						: (spaceString + "更新了" + updateNum + "条房屋合同信息</br>");
			}
		}
	}

	/**
	 * 
	 * 描述：根据表头列名称获取列的number 2013-8-16 上午10:32:56 by caoyong
	 * 
	 * @version
	 * @param list表头列集合
	 * @return result返回对应表头列的cellNumber
	 */
	private int[] getCellNumByName(List<String> list) {
		String[] titles = { "纸质合同编号", "成本中心编号", "成本中心", "使用部门", "付款部门", "地区",
				"办公室座落地点", "汇入帐户（业主）", "面积（平方米）", "开始日期", "首付日期", "结束日期",
				"租赁期限（月）", "付款次数", "付款方式", "房屋押金", "物业押金", "免租期(月)", "年租金（元）",
				"合同月租金", "状态", "备注", "签署合同公司" };
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
	 * 
	 * 描述：获取系统登陆用户 2013-8-28 下午03:06:10 by caoyong
	 * 
	 * @version
	 * @return
	 */
	private User findUser() {
		User user = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("user");
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FinanceRentContract> expqueryPage(Pagination page) throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		String lastCostCenter=request.getParameter("lastCostCenter");
		String signatorys2=request.getParameter("signatory2");
		Map map = HashMap.class.newInstance();
		map.put("orgName", request.getParameter("orgName"));
		map.put("startDate", request.getParameter("startDate"));
		map.put("paymentCycle", request.getParameter("paymentCycle"));
		map.put("endDate", request.getParameter("endDate"));
		map.put("monthMoney", request.getParameter("monthMoney"));
		map.put("city",request.getParameter("city"));
		map.put("contractNum", request.getParameter("contractNum"));
		map.put("status", request.getParameter("status"));
		if(signatorys2==""||signatorys2==null){
			map.put("signatorys",this.getSignatorys());	
		}else{
			map.put("signatory2",signatorys2);
			
		}	
		List<String>  fnamelist=null;
		if(lastCostCenter!=null&&!"".equals(lastCostCenter)){
			fnamelist=OrgCostcenterQuery.selectIdByFname(StringUtil.addLikeOperBoth(lastCostCenter));
		}
		map.put("list", fnamelist);
		//查询总行数的方法
		int totalCounts = financeRentContractMapper.getTotalCounts(map);
		// 查询数据集的方法
		Map map1 = PageUtil.getMap(page);
		map1.putAll(map);
		List<FinanceRentContract> list = financeRentContractMapper.expAllqueryPage(map);
		List<String> fidlist=new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getExt1()!=null)
			fidlist.add(list.get(i).getExt1());
		}
		List<OrgCostcenter> orgCostcenterList=OrgCostcenterQuery.selectByFname(fidlist);
		if(orgCostcenterList!=null){
		for (int i = 0; i <list.size(); i++) {
			for (int j = 0; j < orgCostcenterList.size(); j++) {
				if(orgCostcenterList.get(j).getFid().equals(list.get(i).getExt1())){
					list.get(i).setLastCostCenter(orgCostcenterList.get(j).getFname_l2());
					break;
				}else{
					list.get(i).setLastCostCenter("");
				}
			}
		}
		}
		page.setRows(list);
		return list;
	}

	/**
	 * 
	 * 描述：给王祺发送邮件的定时任务（汇总创建合同时、合同变更时、合同到期时合同数据
	 * (remark字段包括合同的创建时间、创建人、修改时间以及修改人这些信息)，生成excel，以附件形式发送， 提醒周期为每周一次，每周一上午10:00）
	 * 2013-9-24 上午10:38:44 by caoyong
	 * 
	 * @version
	 */
	public void sendEmailToWang() {
		try {
			List<List<Map<String, Object>>> finlist = new ArrayList<List<Map<String, Object>>>();
			String str1 = Utils.lastDay(new Date(), -7);
			Map map = HashMap.class.newInstance();
			map.put("beginDate", str1);
			map.put("endDate", Utils.getNowDate());
			List<Map<String, Object>> dataListUpdate = financeRentContractMapper
					.selectFinanceOptionUpdate(map);
			List<Map<String, Object>> dataListCreate = financeRentContractMapper
					.selectFinanceOptionCreate(map);
			finlist.add(dataListCreate);
			finlist.add(dataListUpdate);
			String filestr = this.exportExcelToWang(finlist);
			String title = "合同汇总";
			MailSenderInfo ms = new MailSenderInfo();
			Properties pr = ms.getProperties();
			String fromAddress = pr.getProperty("FINANCE_USERNAME");
			String password=pr.getProperty("FINANCE_PASSWORD");
			// String [] toAddress = {"529620495@qq.com"};
			 String [] toAddress = {"qiwang@creditease.cn"};if(pr.getProperty("to_local_Address") != null && "yes".equals(pr.getProperty("to_local_Address"))){
				toAddress = pr.getProperty("toAddress").split(",");//代码正式提交时邮件接收人为接口人邮箱，此行注释掉
			 }
			boolean success = SendMailUtilNew.sendFileMail(fromAddress,password,toAddress,
					title, filestr);
			if (success)
				logger.info("给王祺定时发送邮件任务成功！");
			else
				logger.info("给王祺定时发送邮件任务失败！");
		} catch (Exception e) {
			e.printStackTrace();
			try {
				throw new Exception("给王祺定时发送邮件任务异常！");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * 描述：给乔燕霞发送邮件的定时任务（汇总三种类型需要提醒的合同数据，生成excel，以附件形式发送） 2013-9-24 上午10:38:44 by
	 * caoyong
	 * 
	 * @version
	 */
	public void sendEmailToJiao() {
		try {
			List<FinanceRentContract> list = financeRentContractMapper
					.selectAllRents();
			List<List<String>> dataList = new ArrayList<List<String>>();
			dataList.add(this.getNeededPayData(list));
			dataList.add(this.getExpiredData(list));
			dataList.add(this.getHolidayData(list));
			String filestr = this.exportExcelToQiao(dataList);
			String title = "合同汇总";
			MailSenderInfo ms = new MailSenderInfo();
			Properties pr = ms.getProperties();
			String fromAddress = pr.getProperty("FINANCE_USERNAME");
			String password=pr.getProperty("FINANCE_PASSWORD");
			// String [] toAddress = {"sxfmxsh@163.com"};
			 String [] toAddress = {"yanxiaqiao@creditease.cn"};
			 if(pr.getProperty("to_local_Address") != null && "yes".equals(pr.getProperty("to_local_Address"))){
				toAddress = pr.getProperty("toAddress").split(",");//代码正式提交时邮件接收人为接口人邮箱，此行注释掉
			 }
			boolean success = SendMailUtilNew.sendFileMail(fromAddress,password, toAddress,
					title, filestr);
			if (success)
				logger.info("给乔燕霞定时发送邮件任务成功");
			else
				logger.info("给乔燕霞定时发送邮件任务失败");
		} catch (Exception e) {
			e.printStackTrace();
			try {
				throw new Exception("给乔燕霞定时发送邮件任务异常！");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * 描述：获取需要提前10个自然日发出提醒信息合同数据 2013-9-24 上午10:34:02 by caoyong
	 * 
	 * @version
	 * @param list所有的合同数据
	 * @return
	 */
	private List<String> getNeededPayData(List<FinanceRentContract> list) {
		List<String> result = new ArrayList<String>();
		for (int j = 0; j < list.size(); j++) {
			String paymentcycle = list.get(j).getPaymentCycle();// 付款周期
			int paycount = Utils.getInt(list.get(j).getPayCount());// 付款次数
			Date paydate = list.get(j).getRentStartTime();// 起始日期
			Date warndate;// 提醒时间
			String content = "";

			if (paydate != null) {
				if (DictionaryUtil.payType_key_4.equals(paymentcycle)) {// 一年
					for (int i = 1; i <= paycount; i++) {
						paydate = DateUtil.getDateAfterMonth(paydate, 12);// 加上12个月
						warndate = Utils.getBeforeDate(paydate, 10);
						if (Utils.diffdates(warndate, Utils.getDate()) == 0) {// 提醒时间等于当前时间
							String string = "你好：成本中心:"
									+ list.get(j).getLastCostCenter() + ";办公室座落地点："
									+ list.get(j).getOfficeAdd() + ";签署公司："
									+ list.get(j).getSignatory() + ";汇入账户："
									+ list.get(j).getToAccount() + ";付款部门为："
									+ list.get(j).getOrgName() + ";付款方式：一年;请重视";
							result.add(string);
						}
					}
				} else if (DictionaryUtil.payType_key_3.equals(paymentcycle)) {// 半年
					for (int i = 1; i <= paycount; i++) {
						paydate = DateUtil.getDateAfterMonth(paydate, 6);// 加上6个月
						warndate = Utils.getBeforeDate(paydate, 10);
						if (Utils.diffdates(warndate, Utils.getDate()) == 0) {// 提醒时间等于当前时间
							String string = "你好：成本中心："
									+ list.get(j).getLastCostCenter() + ";办公室座落地点："
									+ list.get(j).getOfficeAdd() + ";签署公司："
									+ list.get(j).getSignatory() + ";汇入账户："
									+ list.get(j).getToAccount() + ";付款部门为："
									+ list.get(j).getOrgName() + ";付款方式：半年;请重视";
							result.add(string);
						}
					}
				} else if (DictionaryUtil.payType_key_2.equals(paymentcycle)) {// 季度
					for (int i = 1; i <= paycount; i++) {
						paydate = DateUtil.getDateAfterMonth(paydate, 3);// 加上3个月
						warndate = Utils.getBeforeDate(paydate, 10);
						if (Utils.diffdates(warndate, Utils.getDate()) == 0) {// 提醒时间等于当前时间
							String string = "你好：成本中心："
									+ list.get(j).getLastCostCenter() + ";办公室座落地点："
									+ list.get(j).getOfficeAdd() + " ;签署公司："
									+ list.get(j).getSignatory() + ";汇入账户："
									+ list.get(j).getToAccount() + " ;付款部门为："
									+ list.get(j).getOrgName() + ";付款方式：季度;请重视";
							result.add(string);
						}
					}
				} else if (DictionaryUtil.payType_key_1.equals(paymentcycle)) {// 两月
					for (int i = 1; i <= paycount; i++) {
						paydate = DateUtil.getDateAfterMonth(paydate, 2);// 加上2个月
						warndate = Utils.getBeforeDate(paydate, 10);
						if (Utils.diffdates(warndate, Utils.getDate()) == 0) {// 提醒时间等于当前时间
							String string = "你好：成本中心："
									+ list.get(j).getLastCostCenter() + ";办公室座落地点："
									+ list.get(j).getOfficeAdd() + ";签署公司："
									+ list.get(j).getSignatory() + ";汇入账户："
									+ list.get(j).getToAccount() + ";付款部门为："
									+ list.get(j).getOrgName() + ";付款方式：两月;请重视";
							result.add(string);
						}
					}
				} else if (DictionaryUtil.payType_key_0.equals(paymentcycle)) {// 每月
					for (int i = 1; i <= paycount; i++) {
						paydate = DateUtil.getDateAfterMonth(paydate, 1);// 加上1个月
						warndate = Utils.getBeforeDate(paydate, 10);
						if (Utils.diffdates(warndate, Utils.getDate()) == 0) {// 提醒时间等于当前时间
							String string = "你好：成本中心："
									+ list.get(j).getLastCostCenter() + ";办公室座落地点："
									+ list.get(j).getOfficeAdd() + " ;签署公司："
									+ list.get(j).getSignatory() + ";汇入账户："
									+ list.get(j).getToAccount() + ";付款部门为："
									+ list.get(j).getOrgName() + ";付款方式：每月;请重视";
							result.add(string);
						}
					}
				}
			}
		}
		return result;
	}

	/**
	 * 
	 * 描述：获取需要到期提前10个自然日提醒合同数据 2013-9-24 上午10:35:20 by caoyong
	 * 
	 * @version
	 * @param list所有的合同数据
	 * @return
	 */
	private List<String> getExpiredData(List<FinanceRentContract> list) {
		List<String> result = new ArrayList<String>();
		for (int j = 0; j < list.size(); j++) {
			Date str = list.get(j).getRentEndTime();// 合同到期日期
			if (str != null && !str.equals("")) {
				String str1 = Utils.lastDay(str, -10);// 到期前10天
				String value = "";
				if ("month".equals(list.get(j).getPaymentCycle()))
					value = "每月";
				else if ("twoMonths".equals(list.get(j).getPaymentCycle()))
					value = "两月";
				else if ("quarter".equals(list.get(j).getPaymentCycle()))
					value = "季度";
				else if ("halfYear".equals(list.get(j).getPaymentCycle()))
					value = "半年";
				else if ("year".equals(list.get(j).getPaymentCycle()))
					value = "一年";
				if (str1.equals(Utils.getCurrentDate())) {
					String string = "你好：成本中心："
							+ list.get(j).getLastCostCenter() + ";办公室座落地点："
							+ list.get(j).getOfficeAdd() + " ;签署公司："
							+ list.get(j).getSignatory() + ";汇入账户："
							+ list.get(j).getToAccount() + ";付款部门为："
							+ list.get(j).getOrgName() + ";付款方式：" + value
							+ ";请重视";
					result.add(string);
				}
			}
		}
		return result;
	}

	/**
	 * 
	 * 描述：获取需要系统自动发出提醒合同数据(节假日期间提前2-3天由系统向乔艳霞发出提醒信息) 2013-9-24 上午10:36:06 by
	 * caoyong
	 * 
	 * @version
	 * @param list所有的合同数据
	 * @return
	 */
	private List<String> getHolidayData(List<FinanceRentContract> list) {
		List<String> result = new ArrayList<String>();
		for (int j = 0; j < list.size(); j++) {
			Date warningDate = null;// 预警日期
			// 节假日提前三天（或者两天）和当前日期比较
			for (int i = 0; i < Holiday.HOLIDAYS_STARTDAY_2013.length; i++) {
				warningDate = Utils.getBeforeDate(Utils
						.toDate(Holiday.HOLIDAYS_STARTDAY_2013[i]), 3);
				if (Utils.diffdates(warningDate, Utils.getDate()) == 0) {
					result.add("您好：系统在试运行阶段，如有问题请及时反馈。       纸质合同编号为:"+list.get(j).getContractNum()+"  假期临近请确认是否需要提前申请房租付款申请？如需提前申请付款的请与相关人员申请");
				}
			}
		}
		return result;
	}

	/**
	 * 
	 * 描述：生成给乔燕霞发送定时任务的excel附件 2013-9-24 上午10:37:00 by caoyong
	 * 
	 * @version
	 * @param dataList
	 *            数据源（需要三种提醒的数据）
	 * @return
	 * @throws IOException
	 */
	private String exportExcelToQiao(List<List<String>> dataList)
			throws IOException {
		String filestr = null;
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFCellStyle cellStyle = wb.createCellStyle();
		HSSFSheet sheet = wb.createSheet("合同信息汇总");// 设置页的名称
		HSSFRow row = sheet.createRow(0);
		String[] title = { "付款提醒", "合同到期提醒", "节假日提醒" };
		ExpExcelUtil exp = new ExpExcelUtil();
		if (dataList != null && !dataList.isEmpty()) {// 判断数据是否为空
			for (int i = 0; i < dataList.size(); i++) {
				exp.createMergingCells(wb, sheet, sheet.createRow(sheet
						.getLastRowNum() + 1), 0, title[i], sheet
						.getLastRowNum(), 0, sheet.getLastRowNum() + 2, 20, exp
						.createBgColStyleutil(cellStyle,wb));
				System.out.println(sheet.getLastRowNum());
				if (dataList.get(i) != null && !dataList.get(i).isEmpty()) {
					for (int j = 0; j < dataList.get(i).size(); j++) {
						exp.createMergingCells(wb, sheet, sheet
										.createRow(sheet.getLastRowNum() + 1),
										0, dataList.get(i).get(j), sheet
												.getLastRowNum(), 0, sheet
												.getLastRowNum() + 1, 20, exp
												.createColStyl(cellStyle,wb));
					}
				} else {
					exp.createMergingCells(wb, sheet, sheet.createRow(sheet
							.getLastRowNum() + 1), 0, "无", sheet
							.getLastRowNum(), 0, sheet.getLastRowNum() + 1, 20,
							exp.createColStyl(cellStyle,wb));
				}
			}
		}
		FileOutputStream fout;
		try {
			MailSenderInfo ms = new MailSenderInfo();
			Properties pr = ms.getProperties();
			String portfile = pr.getProperty("portfile");
			//portfile = "D:/";// 测试时用本地路径，上线时将此行注释掉
			filestr = portfile + "邮件提醒" + Utils.getNowcurrTime() + ".xls";
			fout = new FileOutputStream(filestr);
			wb.write(fout);
			fout.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return filestr;
	}

	/**
	 * 
	 * 描述：生成给王祺发送定时任务的excel附件 2013-9-24 上午10:37:00 by caoyong
	 * 
	 * @version
	 * @param dataList
	 *            数据源（需要三种提醒的数据）
	 * @return
	 * @throws IOException
	 */
	private String exportExcelToWang(List<List<Map<String, Object>>> finlist)
			throws IOException {
		String filestr = null;
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFCellStyle cellStyle = wb.createCellStyle();
		HSSFSheet sheet = wb.createSheet("合同信息变更汇总");// 设置页的名称
		HSSFRow row = sheet.createRow(0);
		String[] title = { "创建", "修改" };
		ExpExcelUtil exp = new ExpExcelUtil();
		for (int i = 0; i < finlist.size(); i++) {
			exp.createMergingCells(wb, sheet, sheet.createRow(sheet
					.getLastRowNum() + 1), 0, title[i], sheet.getLastRowNum(),
					0, sheet.getLastRowNum() + 2, 20, exp
							.createBgColStyleutil(cellStyle,wb));
			if (finlist.get(i) != null && !finlist.get(i).isEmpty()) {
				for (Map<String, Object> map : finlist.get(i)) {
			 if(map.get("REMARK")!=null && !map.get("REMARK").equals("")){
					exp.createMergingCells(wb, sheet, sheet.createRow(sheet
							.getLastRowNum() + 1), 0, map.get("REMARK")
							.toString(), sheet.getLastRowNum(), 0, sheet
							.getLastRowNum() + 1, 20, exp.createColStyl(cellStyle,wb));
				     }
				}
			} else {
				exp
						.createMergingCells(wb, sheet, sheet.createRow(sheet
								.getLastRowNum() + 1), 0, "无", sheet
								.getLastRowNum(), 0, sheet.getLastRowNum() + 1,
								20, exp.createColStyl(cellStyle,wb));
			}
		}
		FileOutputStream fout;
		try {
			MailSenderInfo ms = new MailSenderInfo();
			Properties pr = ms.getProperties();
			String portfile = pr.getProperty("portfile");
			//portfile = "D:/";// 测试时用本地路径，上线时将此行注释掉
			filestr = portfile + "合同信息变更" + Utils.getNowcurrTime() + ".xls";
			fout = new FileOutputStream(filestr);
			wb.write(fout);
			fout.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return filestr;
	}
	/**
	 * 
	 * 描述：获得财务用户所负责的公司，根据用户表用户所负责的公司字段与合同信息表进行匹配
	 * 2013-9-26 下午08:31:14 by zhangxin
	 * @version
	 * @return
	 */
	private String getSignatorys(){
		HttpServletRequest request= ServletActionContext.getRequest();
		//获得当前登录用户
		User user = (User)request.getSession().getAttribute("user");
		//获得登录用户id值
		int userid = user.getId().intValue();
		int roleid = userRoleMapper.userroleid(userid);
		String chargecompanies = "";
		//登录用户是乔艳霞,管理员
		if(roleid == 122){//财务管理员
			//获取所有合同信息所负责公司拼接的字段  chargecompanies	
			List<FinanceRentContract> allrentslist = financeRentContractMapper.selectAllRents();
			for(int j=0;j<allrentslist.size();j++){
				String alltempcompany = allrentslist.get(j).getSignatory();
				chargecompanies=chargecompanies+","+alltempcompany;
			}
			
		}else{
			//登录用户在财务用户表中的数据
			FinanceUser financeUser = financeUserMapper.selectByUserid(userid);
			if(financeUser!=null){
			int isgroupleader = financeUser.getIsGroupLeader();//是否是组长
		
			int financegroupid = financeUser.getFinanceGroupId();//所在组id
			//如果是组长  查询所有与组长组id相同的chargecompanies字段
		
				if(isgroupleader == 1){ //如果是组长
					List<FinanceUser> financeuserlist = financeUserMapper.selectByGroupid(financegroupid);
					for(int i=0 ;i <financeuserlist.size();i++){
						String tempcompany = financeuserlist.get(i).getChargeCompanies();
						chargecompanies=chargecompanies+","+tempcompany;//将组长与组员的信息拼接起来
				}}else{
						chargecompanies = financeUser.getChargeCompanies();//组员所负责的公司
				}
			}
		}
				String[] companies1 = chargecompanies.split(",");//将信息分开
		
				String companies="";
				for(int j=0;j<companies1.length;j++){
					companies=companies+"'"+companies1[j].trim()+"',";
				}
				companies = companies.substring(0,companies.length()-1); 
		
				return companies;
		
	}
	/**
	    * 
	    * 描述：根据成本中心从eas中查找成本中心编号
	    * 2013-10-8 下午05:00:45 by sunxiaofeng
	    * @version
	    * @param costcenter
	 * @throws Exception 
	    */
	@Override
	public Pagination selectCostcenter(Pagination page) throws Exception {
		return OrgCostcenterQuery.selectCostcenter(page);
	}
	/**
	 * 
	 * 描述：插入合同信息时拼接数据，用于t_finance_operation表保存历史记录
	 * 2013-10-7 下午05:41:35 by zhangxin
	 * @version
	 * @param financeRentContract
	 * @return
	 */
	private String apendInfo(FinanceRentContract financeRentContract){
		String remark="系统在试运行阶段，如有问题请及时反馈。          新增合同,详细信息为,使用部门："+financeRentContract.getOrgName()+",地区："
		+financeRentContract.getCity()+",办公室坐落地点："+financeRentContract.getOfficeAdd()+",成本中心："+financeRentContract.getLastCostCenter()
		+",签署合同公司："+financeRentContract.getSignatory()+",合同月租金："+financeRentContract.getMonthMoney()
		+",付款方式："+financeRentContract.getPaymentCycle()+",付款次数："+financeRentContract.getPayCount()+",开始日期:"+DateUtil.formatDateToString(financeRentContract.getRentStartTime())
		+",结束日期："+DateUtil.formatDateToString(financeRentContract.getRentEndTime())+",租赁期限(月)："+financeRentContract.getRentYear()+",首付日期："+DateUtil.formatDateToString(financeRentContract.getPayFirstTime())
		+",纸质合同编号："+financeRentContract.getContractNum()
		+",成本中心编号："+financeRentContract.getCostCenterNum()+",付款部门："+financeRentContract.getPayOrgName()+",汇入账户(业主)："+financeRentContract.getToAccount()
		+",面积(平方米)："+financeRentContract.getAreaSqm()+",房屋押金："+financeRentContract.getHousingDeposit()+",物业押金："+financeRentContract.getPropertyDeposit()
		+",免租期(月)："+financeRentContract.getRentFreePeriod()+",年租金(元)："+financeRentContract.getAnnualRent()+",状态（在用0、停租1、转租2、变更3）："
		+financeRentContract.getStatus()+",备注："+financeRentContract.getRemark();
		
		return remark;
	}
	/**
	 * 
	 * 描述：编辑/更新合同信息时将变动数据保存到t_finance_operation表
	 * 2013-10-7 下午05:41:05 by zhangxin
	 * @version
	 * @param remark
	 */
	private void editFinacerentcontractToOperation(String remark){
		FinanceOperation financeOperation = new FinanceOperation();
		financeOperation.setRemark(remark);
		financeOperation.setLastupdater(this.findUser().getUsername());
		financeOperation.setLastupdatetime(new Date());
		financeOperation.setType(1);
		financeOperationMapper.insert(financeOperation);
	}
	/**
	 * 
	 * 描述：导入/插入合同信息时将新增数据保存到t_finance_operation表
	 * 2013-10-7 下午05:50:59 by zhangxin
	 * @version
	 * @param remark
	 */
	private void insertFinacerentcontractToOperation(String remark){
		FinanceOperation financeOperation = new FinanceOperation();
		financeOperation.setRemark(remark);
		financeOperation.setCreator(this.findUser().getUsername());
		financeOperation.setCreatortime(new Date());
		financeOperation.setType(0);
		financeOperationMapper.insert(financeOperation);
	}
	
	public boolean findContractNumExist() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		return extDataBaseMapper.findExists(Utils.setParams(
				"T_FINANCE_RENTCONTRACT", "CONTRACTNUM", request.getParameter("columnValue"))) == 0 ? false : true;
	}
	public boolean findOfficeAddExist() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		return extDataBaseMapper.findExists(Utils.setParams(
				"T_FINANCE_RENTCONTRACT", "OFFICEADD", request.getParameter("columnValue"))) == 0 ? false : true;
	}
}
