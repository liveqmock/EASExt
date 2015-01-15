package com.creditease.eas.email.action;

import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.creditease.eas.email.service.IPersonInfoService;
import com.creditease.eas.email.service.Impl.PersonInfoServiceImpl;
import com.creditease.eas.util.BaseAction;
import com.creditease.eas.util.ExpExcelUtil;
import com.creditease.eas.util.sendcell.SendCellUtil;
import com.creditease.eas.warn.bean.FinanceRentContract;
import com.creditease.eas.warn.service.impl.FinanceRentContractServiceImpl;
import com.sun.mail.iap.Response;
/**
 * 
 * @PersonInfoAction.java
 * created at 2013-12-16 下午04:56:13 by zhangxin
 * 查询新入职员工信息
 * @author zhangxin({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class PersonInfoAction extends BaseAction{
	@SuppressWarnings("unused")
	@Autowired
	private IPersonInfoService personInfoService;
	private List personInfoList;
	private static final Logger logger = Logger
	.getLogger(PersonInfoAction.class);
	
	
	public List getPersonInfoList() {
		return personInfoList;
	}
	public void setPersonInfoList(List personInfoList) {
		this.personInfoList = personInfoList;
	}
	public String queryPageJson() throws Exception {
		this.pagination = personInfoService.queryPage(pagination);
		personInfoList = pagination.getRows();
		return "queryPageJson";
	}
	/**
	 * 
	 * 描述：导出新入职员工信息
	 * 2013-12-25 下午04:29:22 by zhangxin
	 * @version
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void expExcel() throws Exception{	
	@SuppressWarnings("unused")
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	response.setCharacterEncoding("GBK");
	response.setContentType("GBK");
	response.setContentType("application/vnd.ms-excel");
	
	String fileName = "员工入职信息";// 设置excel文件的名字
	if (!fileName.equals("")) {
		String header = "attachment; filename=" + new String((fileName + ".xls").getBytes(), "iso8859-1");
		response.setHeader("Content-Disposition", header);
	}
	// 创建响应输出流对象
	OutputStream out = response.getOutputStream();
	//导出文件需要的类
	String[] headers={"序号","员工编号","姓名","职位","组织","邮箱","创建日期"};
    String[] title={"","FNUMBER","FNAME","POSITION","FDISPLAYNAME","EMAIL","CREATTIME"};
   
	List<Map> datalist = personInfoService.expQuery();
	try {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFCellStyle cellStyle = wb.createCellStyle();
		HSSFSheet sheet = wb.createSheet("员工入职信息");//设置页的名称
		HSSFRow row=sheet.createRow(0);
		row.setHeight((short)500);//设置行高
		HSSFFont fontStyle = wb.createFont();//创建字体
		for (int i = 0; i < headers.length; i++) {
			sheet.setColumnWidth(i, 10000);//设置单元格的宽度
			ExpExcelUtil.createTxtCell(wb, row, i, headers[i],ExpExcelUtil.createBgColStyleutil(cellStyle,wb));//创建表格第一行
		}
		if (datalist != null && !datalist.isEmpty()){//判断数据是否为空
			@SuppressWarnings("unused")
			Field field=null;
			Object[] objs = datalist.toArray();//转换为数组
			for (int i = 0; i < objs.length; i++) {
				HSSFRow row1=sheet.createRow(i+1);//创建行，设置从第二行开始
				row1.setHeight((short)300);
				Map personMap = (Map) objs[i];
				for (int j = 0; j < title.length; j++) {
					if(title[j]!=""){
					String getMethodName = title[j];
					
					 ExpExcelUtil.createTxtCell(wb, row1, j, personMap.get(getMethodName),ExpExcelUtil.createBgColStyle(cellStyle,wb,fontStyle));
					}else{
						ExpExcelUtil.createTxtCell(wb, row1, j, i+1,ExpExcelUtil.createBgColStyle(cellStyle,wb,fontStyle));
						 }
					}
			}
		}
		wb.write(out);
	} catch (Exception e) {
		logger.error(e.getMessage());
		e.printStackTrace();
	}
	out.close();//关闭
	response.flushBuffer();
}
	/**
	 * 
	 * 描述：发送短信
	 * 2013-12-26 下午04:37:59 by zhangxin
	 * @version
	 */
	public void sendMessage(){
	String number = request.getParameter("FNUMBER");
	Map map = personInfoService.selectPerson(number);
	String fname = (String) map.get("FNAME");
	String fnumber = (String) map.get("FNUMBER");
	String fmail = (String) map.get("EMAIL");
	String fmobilePhone = (String) map.get("FCELL");
	String fkey = "year|yixin@2013";
	String fdescription = " ";
	if(null != fmobilePhone && ""!= fmobilePhone){
		SendCellUtil su = new SendCellUtil();//发送短信
		su.sendCellUtil(fname, fnumber, fmail, fkey, fdescription, fmobilePhone);
	}
	}

}
