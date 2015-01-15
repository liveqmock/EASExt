package com.creditease.eas.accountr.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditease.eas.accountr.dao.GeneratFileMapper;
import com.creditease.eas.accountr.kingdee.query.NetQuery;
import com.creditease.eas.accountr.service.GeneratFileService;
import com.creditease.eas.admin.bean.User;
import com.creditease.eas.util.ExpExcelUtil;
import com.creditease.eas.util.PageUtil;
import com.creditease.eas.util.Pagination;
import com.creditease.eas.util.Utils;
import com.creditease.eas.util.mail.MailSenderInfo;
import com.creditease.eas.util.port.ImportXlsm;
import com.kingdee.eas.basedata.barcode.openURL.webservice.easlogin.EASLoginProxyServiceLocator;
import com.kingdee.eas.basedata.barcode.openURL.webservice.easlogin.WSContext;
import com.kingdee.eas.basedata.barcode.openURL.webservice.wswsvoucher.WSWSVoucher;
import com.kingdee.eas.basedata.barcode.openURL.webservice.wswsvoucher.WSWSVoucherSrvProxyServiceLocator;

@Service
public class GeneratFileServiceImpl implements GeneratFileService {
	
	@Autowired
	private GeneratFileMapper generatFileMapper;
	private String importMessage;
	private static final Logger logger = Logger
			.getLogger(GeneratFileServiceImpl.class);
	private NetQuery netQuery=new NetQuery();
	/**
	 * 生成网银信息
	 * @throws Exception 
	 * @throws Exception 
	 */
	@Override
	public String generationFile(File excelFile, String filePath) throws Exception{
		String jsonResult = "";
			String temp[] = filePath.replaceAll("\\\\", ",").split(",");
			String str = temp[temp.length - 1];
			String[] nameArray=str.split("\\.");
			String filename=nameArray[0]+"("+Utils.getNowcurrTime()+")."+nameArray[1];
			MailSenderInfo ms = new MailSenderInfo();
			Properties pr = ms.getProperties();
			String portfile = pr.getProperty("portfile");
			//portfile = "D:/";
			String directory = portfile+"SourceFile/";
			//String directory = "/upload/prot";
			//String targetDirectory = ServletActionContext.getServletContext()
			//		.getRealPath(directory);
			// 生成上传的文件对象
			File target = new File(directory, filename);
			// 如果文件已经存在，则删除原有文件
			if (target.exists()) {
				target.delete();
			}
			// 复制file对象，实现上传
			FileUtils.copyFile(excelFile, target);
			ImportXlsm poi = new ImportXlsm();
			List<Integer> callList=new ArrayList<Integer>();
			callList.add(4);//员工编码
			callList.add(168);//金额
			callList.add(10);//公司编码
			callList.add(25);//记账日期
			callList.add(26);//摘要
			callList.add(166);//科目
			callList.add(12);//成本中心编码
			callList.add(11);//部门编号
			callList.add(62);//费用类型
			// //房屋合同信息源数据
			List<List<String>> list = poi.read(target.getPath(),0,10,callList);//0表示是excel的第一页
			for(int i=0;i<list.size();i++){
				if(list.get(i).get(0).trim().indexOf(".")!=-1){
					list.get(i).set(0, list.get(i).get(0).trim().substring(0,list.get(i).get(0).trim().indexOf(".")));
				}
				if(list.get(i).get(5).trim().indexOf(".")!=-1){
					list.get(i).set(5, list.get(i).get(5).trim().substring(0,list.get(i).get(5).trim().indexOf(".")));
				}
			}
			this.importMessage = "";
			if (0 == list.size()){
				this.importMessage += "请检查报销信息汇总sheet格式或数据</br>";
			}
			 List<Map<String,String>> dataList= this.getPersonCard(list);//查询员工工资卡信息
			 boolean fag=this.importContracts(dataList,target.getPath(),filename);//生成网银信息
			 if(fag){
				this.importMessage="操作成功";
				try {
					this.callInterface(list);//生成凭证
				} catch (Exception e) {
					 this.importMessage ="凭证生成失败"+e.getMessage();
					 jsonResult = "{\"success\":\"false\", \"message\":\""
							+ this.importMessage + "\"}";
					 logger.info(importMessage);
					 return jsonResult;
				}
			 }else{
				 this.importMessage ="网银信息生成失败";
				 jsonResult = "{\"success\":\"false\", \"message\":\""
						+ this.importMessage + "\"}";
				 logger.info(importMessage);
				 return jsonResult;
			 }
			jsonResult = "{\"success\":\"true\", \"message\":\""
					+ this.importMessage + "\"}";
		return jsonResult;
	}
	/**
	 * 查询员工工资卡信息
	 * @param list
	 * @return
	 */
	private  List<Map<String,String>> getPersonCard(List<List<String>> list){
	    List<String> personNumber=new ArrayList<String>();//员工编号集合
	    for (int i = 0; i < list.size(); i++) {
              if(null != list.get(i).get(0)){
            	  if(list.get(i).get(0).length() == 12){
            		  personNumber.add(list.get(i).get(0).trim());
            	  }
              }
		}
	    //查询员工工资看信息
	   List<Map<String,String>> cardInfoList=netQuery.findCardInfo(personNumber);
	   for(int i=0;i<cardInfoList.size();i++){
		   Double money=0d;
		   for(int j=0;j<list.size();j++){
			 if(cardInfoList.get(i).get("FNUMBER").toString().equals(list.get(j).get(0))){
				 money=money+Double.parseDouble(list.get(j).get(1));
			 }
		   }
		   cardInfoList.get(i).put("MONEY", money.toString());
	   }
	   return cardInfoList;
	}
	/**
	 * 
	 * 描述;解析后缀为xlsm的excel
	 * 
	 * @version
	 */
	@SuppressWarnings("unchecked")
	private boolean importContracts(List<Map<String,String>> dataList,String SourcePath,String SourceName) throws Exception {
		String filestr=null;
		boolean fag=true;
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFCellStyle cellStyle = wb.createCellStyle();
			HSSFFont fontStyle = wb.createFont();
			HSSFFont fontStyleTo = wb.createFont();
			HSSFSheet sheet = wb.createSheet("网银信息");// 设置页的名称
			HSSFRow row = sheet.createRow(0);
			String[] title = { "账户", "姓名", "金额","开户行","开户地","注释"};
			String[] heads={"CFGZCARD","PERSONNAME","MONEY","CFGZBANKNAME","CITYNAME","NOTES"}; 
			for (int i = 0; i < title.length; i++) {
				sheet.setColumnWidth(i, 5000);// 设置单元格的宽度
				row.setHeight((short) 500);
				ExpExcelUtil.createTxtCell(wb, row, i, title[i], ExpExcelUtil
						.createColStyleTile(cellStyle, wb, fontStyle));// 创建表格第一行
			}
			if (null != dataList && !dataList.isEmpty()) {// 判断数据是否为空
				Object[] objs = dataList.toArray();// 转换为数组
				for (int i = 0; i < objs.length; i++) {
					HSSFRow row1 = sheet.createRow(i+1);// 创建行，设置从第二行开始
					row1.setHeight((short) 500);
					Map t = (Map) objs[i];
					for (int j = 0; j < title.length; j++) {
						if (heads[j] != "") {
							String getMethodName = heads[j];
							Object value = t.get(getMethodName);
							ExpExcelUtil.createTxtCell(wb,row1,j,value,ExpExcelUtil.createCollStyle(wb.createCellStyle(), wb,fontStyleTo));
						}
					}
				}
			}
			FileOutputStream fout;
			try {
				MailSenderInfo ms = new MailSenderInfo();
				Properties pr = ms.getProperties();
				String portfile = pr.getProperty("portfile");
				//portfile = "D:/";// 测试时用本地路径，上线时将此行注释掉
            	filestr = portfile + "Net/网银信息" + Utils.getNowcurrTime() + ".xls";
				fout = new FileOutputStream(filestr);
				wb.write(fout);
				fout.close();
				Map map=new HashMap();
				map.put("fileName", "网银信息_"+user.getUsername()+"("+ Utils.getNowcurrTime() + ").xls");
				map.put("filePath", filestr);
				map.put("createrId", user.getId().intValue());
				map.put("createName", user.getUsername());
				map.put("viewName", "网银信息_"+user.getUsername()+"("+ Utils.getNowcurrTime() + ").xls");
				map.put("SourcePath", SourcePath);
				map.put("SourceName", SourceName);
				generatFileMapper.insertFile(map);
			} catch (FileNotFoundException e) {
				fag=false;
				e.printStackTrace();
				logger.info("生成网银文件报错");
			}
		return fag;	
	}
	/**
	 * 查询文件信息
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Pagination queryPageFile(Pagination page,
			HttpServletRequest request) {
		int currentPage = PageUtil.strToPage(request.getParameter("page"));
		int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
		Map map =new HashMap();
		map.put("createname", request.getParameter("createname"));
		map.put("startDate", request.getParameter("startDate"));
		map.put("endDate", request.getParameter("endDate"));
		
		//查询总行数的方法
		int totalCounts = generatFileMapper.getFileCountsByParams(map);
		page = new Pagination(currentPage,pageSize,totalCounts); 
		//查询数据集的方法
		Map mapTo = PageUtil.getMap(page);
		mapTo.putAll(map);
		List<Map> list = generatFileMapper.queryPageByParamsFile(mapTo);
		page.setRows(list);
		return page;
	}
	/**
	 * 调用生成凭证接口
	 */
	public void callInterface(List<List<String>> list) throws Exception{
		List<List<String>> lists=getDeptName(list);
		WSWSVoucher[] voucherCols=this.makeVoucher(lists);
		System.out.println("+++++++++++++++++++++++++++++++++++++++开始++++++++++++++++++++++++");
		longii();
		 WSWSVoucherSrvProxyServiceLocator serviceLocator=new WSWSVoucherSrvProxyServiceLocator();
			String[][] result= serviceLocator.getWSWSVoucher().importVoucher(voucherCols,0,0);
			if(result.length>0){
				for(int i=0;i<result.length;i++){
					if(result[i][4].equals("0000")){
					}else{
						throw new Exception(result[i][5].toString());
					}
				}
			}
		System.out.println("+++++++++++++++++++++++++++++++++++++++结束++++++++++++++++++++++++");
	
	}
    public List<List<String>> getDeptName(List<List<String>> list){
    	List<String> accountNumber=new ArrayList<String>();
    	List<String> deptNumber=new ArrayList<String>();//部门编号集合
    	 List<String> person=new ArrayList<String>();//员工编号集合
   	     for (int i = 0; i < list.size(); i++) {
   	     deptNumber.add(list.get(i).get(7).trim());
               if(null != list.get(i).get(0)){
                  if(list.get(i).get(0).trim().indexOf(".")!=-1){
                	  String num=list.get(i).get(0).trim().substring(0,list.get(i).get(0).trim().indexOf("."));
                	  if(num.length() == 12){
                 	    	person.add(list.get(i).get(0).trim());
                 	   }
                  }else{
                	  String numTo=list.get(i).get(0).trim();
                	  if(numTo.length() == 12){
                 	    	person.add(list.get(i).get(0).trim());
                 	   } 
                  }	 
                }
               if(list.get(i).get(5).trim().indexOf(".")!=-1){
            	   accountNumber.add(list.get(i).get(5).trim().substring(0,list.get(i).get(5).trim().indexOf(".")));   
               }else{
            	   accountNumber.add(list.get(i).get(5).trim());
               }
      	     
   		 }
   	   //查询科目名称
   	  List<Map<String,String>> accountList=netQuery.findAccountInfo(accountNumber);
   	  //查询员工姓名
   	   List<Map<String,String>> personList=netQuery.findPersonInfo(person);
      	//查询部门名称
  	   List<Map<String,String>> deptInfoList=netQuery.findDeptInfo(deptNumber);
  	  for(int j=0;j<list.size();j++){
  		  for(int i=0;i<deptInfoList.size();i++){
  			if(null !=  deptInfoList.get(i).get("FNUMBER")){
  			  if(deptInfoList.get(i).get("FNUMBER").toString().equals(list.get(j).get(7))){
  				list.get(j).add(deptInfoList.get(i).get("DEPTNAME"));
			  }
  			 }
  		   }
  	   }
  	  for(int i=0;i<list.size();i++){
  		  boolean flag=true;
  		  if(personList.size()>0){
  			for (int j = 0; j < personList.size(); j++) {
  				if(null !=  personList.get(j).get("FNUMBER")){ 
  					if(list.get(i).get(0).trim().equals(personList.get(j).get("FNUMBER"))){
  						list.get(i).add(personList.get(j).get("PERSONNAME"));
  						flag=false;
  				    }
  				}
  			}
  			if(flag){
  				list.get(i).add("");
  			}
  		  }else{
  			list.get(i).add("");
  		  }
  		  
  	  }
  	 for(int i=0;i<list.size();i++){
  		  for(int j=0;j<accountList.size();j++){
  			  if(list.get(i).get(5).toString().equals(accountList.get(j).get("FNUMBER").toString())){
  				list.get(i).set(8, accountList.get(j).get("FNAME_L2").toString());
  			  }
  		  }
  	 }
	   return list;
    }
	//登录金蝶系统
	 private void longii(){
			try{
				EASLoginProxyServiceLocator locator = new EASLoginProxyServiceLocator();
				//WSContext ctx = locator.getEASLogin().login("BXCW", "bxcw123", "eas", "002", "L2", 1);
				WSContext ctx = locator.getEASLogin().login("user", "", "eas", "001", "L2", 1);
				System.out.println("SessionID = '"+ctx.getSessionId());	
			}catch (Exception e) {
				logger.info("登录金蝶系统报错");
			}
	    }
	/**
	 * 制作凭证
	 * @param list
    	callList.add(4);//员工编码
		callList.add(248);//原币金额
		callList.add(10);//公司编码
		callList.add(25);//记账日期
		callList.add(26);//摘要
		callList.add(166);//科目
		callList.add(12);//成本中心编码
	 */
	private WSWSVoucher[] makeVoucher(List<List<String>> list){
		List<List<List<String>>>  removeDuplicateList = removeDuplicateWithOrder(list);  
		List<WSWSVoucher> voucherList=new ArrayList<WSWSVoucher>();
		int entrySeq=1;//分录号
		for(int i=0;i<removeDuplicateList.size();i++){
			String sysDate=Long.toString( new Date().getTime());
			String bookedDate="";
			String bizDate="";
			Integer periodYear=0;
			Integer periodNumber=0;
			Double originalAmount=0d;
			Double creditAmount=0d;
		 for(int k=0;k<removeDuplicateList.get(i).size();k++){
				WSWSVoucher voucher = new WSWSVoucher();
				if(removeDuplicateList.get(i).get(k).get(2).trim().indexOf(".")!=-1){//公司编码
					voucher.setCompanyNumber("0"+removeDuplicateList.get(i).get(k).get(2).trim().substring(0,removeDuplicateList.get(i).get(k).get(2).trim().indexOf(".")));
				}else{
					voucher.setCompanyNumber("0"+removeDuplicateList.get(i).get(k).get(2).trim());
				}
				bookedDate=removeDuplicateList.get(i).get(k).get(3).trim();
				bizDate=removeDuplicateList.get(i).get(k).get(3).trim();
				String[] strDate=bookedDate.split("-");
				periodYear=Integer.parseInt(strDate[0]);
				periodNumber=Integer.parseInt(strDate[1]);
				originalAmount=Double.parseDouble(removeDuplicateList.get(i).get(k).get(1).trim());
				creditAmount+=Double.parseDouble(removeDuplicateList.get(i).get(k).get(1).trim());
				
				voucher.setBookedDate(bookedDate);//记账日期
				voucher.setBizDate(bizDate);//业务日期
				voucher.setPeriodYear(Integer.parseInt(strDate[0]));//会计期间-年
				voucher.setPeriodNumber(Integer.parseInt(strDate[1]));//会计期间-月
				voucher.setVoucherType("付");//凭证类型  默认为“付”
				voucher.setVoucherNumber(sysDate+""+i);//凭证号，保持唯一行
				voucher.setEntrySeq(entrySeq);//分录号
				voucher.setVoucherAbstract("北京"+removeDuplicateList.get(i).get(k).get(9).trim()+"付"+Utils.getNowDate()+""+removeDuplicateList.get(i).get(k).get(10).trim()+"个人报销-"+removeDuplicateList.get(i).get(k).get(8).trim()+"");//摘要
				String accountNumber="";
				if(removeDuplicateList.get(i).get(k).get(5).trim().indexOf(".")!=-1){//科目
					accountNumber=removeDuplicateList.get(i).get(k).get(5).trim().substring(0,removeDuplicateList.get(i).get(k).get(5).trim().indexOf("."));
				}else{
					accountNumber=removeDuplicateList.get(i).get(k).get(5).trim();
				}
				voucher.setAccountNumber(accountNumber);	
				voucher.setCurrencyNumber("BB01");//币种  默认的是"BB01"(人民币)
				voucher.setEntryDC(1);//方向  1表示是借方，-1表示是贷方
				voucher.setOriginalAmount(originalAmount);//原币金额
				voucher.setDebitAmount(originalAmount);//借方金额
				voucher.setCreator("梁佳");//制单人
				String asstActNumber="";//成本中心编码
				if(removeDuplicateList.get(i).get(k).get(6).trim().indexOf(".")!=-1){
					asstActNumber=removeDuplicateList.get(i).get(k).get(6).trim().substring(0,removeDuplicateList.get(i).get(k).get(6).trim().indexOf("."));//核算对象编码1
				}else{
					asstActNumber=removeDuplicateList.get(i).get(k).get(6).trim();//核算对象编码1
				}
			    String projectAccountNumber="6505020101,6505020102,6505020103";//成本中心+项目+辅助代码
			    String fuAccountNumber="65020301,65020302,65030101,65030102,65030201,65050202,6505020199,65100403";//成本中心+辅助代码
				if(projectAccountNumber.contains(accountNumber)){
					voucher.setAsstActType1("成本中心");//核算项目1
					voucher.setAsstActNumber1(asstActNumber);//核算对象编码1
					voucher.setAsstActName1("");//核算对象名称1
					voucher.setAsstActType2("项目");
					voucher.setAsstActNumber2("99.9999");
					voucher.setAsstActName2("初始化");
					voucher.setAsstActType3("辅助代码");
					voucher.setAsstActNumber3("001");
					voucher.setAsstActName3("Rc001"); 
				}else if(fuAccountNumber.contains(accountNumber)){
					voucher.setAsstActType1("成本中心");//核算项目1
				    voucher.setAsstActNumber1(asstActNumber);//核算对象编码1
					voucher.setAsstActName1("");//核算对象名称1
					voucher.setAsstActType2("辅助代码");
					voucher.setAsstActNumber2("001");
					voucher.setAsstActName2("Rc001");
				}else if(fuAccountNumber.equals("660305")){
				}else{
					voucher.setAsstActType1("职员");//核算项目1
					if(removeDuplicateList.get(i).get(k).get(0).trim().indexOf(".")!=-1){
						voucher.setAsstActNumber1(removeDuplicateList.get(i).get(k).get(0).trim().substring(0,removeDuplicateList.get(i).get(k).get(0).trim().indexOf(".")));//核算对象编码1);//核算对象编码1
					}else{
					    voucher.setAsstActNumber1(removeDuplicateList.get(i).get(k).get(0).trim());//核算对象编码1);//核算对象编码1
					}
					voucher.setAsstActName1("");//核算对象名称1
					voucher.setAsstActType2("成本中心");
					voucher.setAsstActNumber2(asstActNumber);
					voucher.setAsstActName2("");
					voucher.setAsstActType3("辅助代码");
					voucher.setAsstActNumber3("001");
					voucher.setAsstActName3("Rc001");
				}
			    entrySeq++;
			    voucherList.add(voucher);
			}
			WSWSVoucher voucherTo = new WSWSVoucher();
			voucherTo.setBookedDate(bookedDate);//记账日期
			voucherTo.setBizDate(bizDate);//业务日期
			voucherTo.setPeriodYear(periodYear);//会计期间-年
			voucherTo.setPeriodNumber(periodNumber);//会计期间-月
			voucherTo.setVoucherType("付");//凭证类型  默认为“付”
			voucherTo.setVoucherNumber(sysDate+""+i);//凭证号，保持唯一行
			voucherTo.setEntrySeq(entrySeq);//分录号
			voucherTo.setAccountNumber("100201"); 
			voucherTo.setCurrencyNumber("BB01");//币种  默认的是"BB01"(人民币)
			voucherTo.setEntryDC(-1);//方向  1表示是借方，-1表示是贷方
			voucherTo.setCreditAmount(creditAmount);//贷方金额
			voucherTo.setCreator("梁佳");//制单人
			voucherTo.setAsstActType1("银行账户");//核算项目1
			voucherTo.setAsstActNumber1("071001");//核算对象编码1
			voucherTo.setAsstActName1("普信恒业基本户0118");//核算对象名称1
			voucherTo.setVoucherAbstract("普信各部门付"+Utils.getNowDate()+"员工个人报销");//摘要
			voucherTo.setOriginalAmount(creditAmount);//原币金额
			voucherList.add(voucherTo);
		}
		WSWSVoucher[] voucherCols = new WSWSVoucher[voucherList.size()];
		int order = 0;
		for(WSWSVoucher voucherCol : voucherList){
			voucherCols[order] = voucherCol;
			order++;
		}
	  return voucherCols;
	}
	//把凭证分开
    private List<List<List<String>>>  removeDuplicateWithOrder(List<List<String>> list) {   
	       //定义Set :元素不重复   
	        Set set = new HashSet();   
	        //定义List 存放不重复的元素   
	       List newList = new ArrayList();   
	            //得到list的迭代器   
	       for (int i=0;i<list.size();i++) {   
	            //获得每一个元素   
	            Object element = list.get(i).get(3);   //取的记账日期
	            if (set.add(element))   
	              //添加元素   
	                newList.add(element);   
 
	        }   
	       List<List<List<String>>> objList=new ArrayList<List<List<String>>>();    
	         //遍历去掉重复元素的List 计算每个元素的个数   
	        for (int i = 0; i < newList.size(); i++) {   
	            //获得元素值   
	            String value = (String) newList.get(i);   
	            List<List<String>> sigList =  new ArrayList<List<String>> ();
	            for (int j = 0; j < list.size(); j++) {   
	                //如果元素值有和原来List中的值相等 count++   
	                if (value.equals(list.get(j).get(3))) {   
	                    sigList.add(list.get(j));
	                }   
	            }   
	            objList.add(sigList);
	        }   
	      return objList;   
   }   
	/**
	 * 删除文件信息
	 */
	public void deleteFile(int parseInt) {
		generatFileMapper.deleteFile(parseInt);
	};
	/** 
	 * 根据id查询文件信息
	 */
	@Override
	public Map findFileInfo(int fid) {
		return generatFileMapper.findFileInfo(fid);
	}

}
