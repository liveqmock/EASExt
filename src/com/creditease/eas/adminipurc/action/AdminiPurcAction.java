package com.creditease.eas.adminipurc.action;

import java.io.File;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.creditease.eas.adminipurc.service.IAdminiPurcContractService;
import com.creditease.eas.adminipurc.service.IAdminiPurcPortService;
import com.creditease.eas.adminipurc.util.ImportExcel;
import com.creditease.eas.adminipurc.vo.AdminContractInfoForExcelVo;
import com.creditease.eas.adminipurc.vo.CommonPortinfoForExcelVo;
import com.creditease.eas.util.BaseAction;
import com.creditease.eas.util.ExportExcel;

/****
 * 行政采购合同预警--导入基本的合同信息
 *AdminiPuocAction
 * @author gqy 2014-5-19
 */
@Controller
@Scope("prototype")
public class AdminiPurcAction extends BaseAction{
	public static String message = "";//定义一个静态变量，用于判断导入的信息为空时的提示内容
	//导入文件
	private File excelFile;
	//文件名称
	private String filePath;
	//返回json格式的字符串
	protected String json;
	@Resource
	private IAdminiPurcPortService iPurcPortinfoService;
	@Resource
	private IAdminiPurcContractService iPurcContractService;
	//接口人信息的vo，导出验证信息时用
	private static List<CommonPortinfoForExcelVo> datasetPortInfo;
	//合同信息的vo,导出验证信息时用
	private static List<AdminContractInfoForExcelVo> datasetContractInfo;
	
	public File getExcelFile() {
		return excelFile;
	}
	public void setExcelFile(File excelFile) {
		this.excelFile = excelFile;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public IAdminiPurcPortService getiPurcPortinfoService() {
		return iPurcPortinfoService;
	}
	public void setiPurcPortinfoService(IAdminiPurcPortService iPurcPortinfoService) {
		this.iPurcPortinfoService = iPurcPortinfoService;
	}
	public IAdminiPurcContractService getiPurcContractService() {
		return iPurcContractService;
	}

	public void setiPurcContractService(
			IAdminiPurcContractService iPurcContractService) {
		this.iPurcContractService = iPurcContractService;
	}
	/***
	 * 导入接口人信息
	 * @throws Exception
	 */
	public void importPortExcel() throws Exception{
		try {
			datasetPortInfo = iPurcPortinfoService.importPortExcel(excelFile,filePath);//验证有问题，则导出excel格式的提示
			//只要message有值，则导入的信息就有问题
			if(AdminiPurcAction.message != null && AdminiPurcAction.message != "" && !"".equals(AdminiPurcAction.message)){
				json = "{\"success\":\"false\", \"message\":\""+AdminiPurcAction.message +"\"}";
				AdminiPurcAction.message  = "";//清空message
			}else if(null == datasetPortInfo || datasetPortInfo.isEmpty() && (AdminiPurcAction.message == null||"".equals(AdminiPurcAction.message))){
				json = "{\"success\":\"true\", \"message\":\"导入信息成功\"}";
			}else{
				json = "{\"success\":\"false\", \"message\":\"有未验证通过的信息\"}";
			}
		}catch(Exception ex){
			json = "{\"success\":\"false\", \"message\":\"</br>导入信息异常\"}";
			ex.printStackTrace();
		}finally{
			response.setContentType("text/html;charset=UTF-8");
        	response.getWriter().print(json);
		}
	}
	/***
	 * 导出接口人验证信息
	* @Title: exportvalidInfo
	*created at 2014-6-8 上午09:06:22 by ygq  
	* @throws Exception
	* @return void
	 */
	public void exportvalidPortInfo()  throws Exception{
		response.setContentType("application/vnd.ms-excel");
		String fireFox = request.getParameter("fireFox");
		String fileName = "";
		//对字符进行编码
		if(null != fireFox && fireFox.equals("1")){
			//如果是火狐浏览器，则使用这种转码方式
			fileName = new String("行政采购合同接口人.xlsx".getBytes(), "iso8859-1");
		}else{
			//如果其他浏览器,则使用这种转码方式
			fileName = URLEncoder.encode("行政采购合同接口人.xlsx", "UTF-8");
		}
		String header = "attachment; filename=" + fileName;
		response.setHeader("Content-Disposition", header);
		// 创建响应输出流对象
		OutputStream out = response.getOutputStream();
		//导出文件需要的类
		try {
			 ExportExcel<CommonPortinfoForExcelVo> ex = new ExportExcel<CommonPortinfoForExcelVo>();
			 ex.exportExcelForValidOrExport("行政采购接口人信息验证",ImportExcel.PORTINFOTITLESForValid, datasetPortInfo, out,"yyyy-MM-dd");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			response.flushBuffer();
			datasetPortInfo = null;//清空dateset
		}
	}
	/***
	 * 导入合同信息
	 * @throws Exception
	 */
	public void importContractExcel() throws Exception{
		try {
			datasetContractInfo = iPurcContractService.importContractExcel(excelFile,filePath);//验证有问题，则导出excel格式的提示
			if(AdminiPurcAction.message != null && AdminiPurcAction.message != "" && !"".equals(AdminiPurcAction.message)){
				json = "{\"success\":\"false\", \"message\":\""+AdminiPurcAction.message +"\"}";
				AdminiPurcAction.message  = "";//清空message
			}else if(null == datasetContractInfo || datasetContractInfo.isEmpty() && (AdminiPurcAction.message == null||"".equals(AdminiPurcAction.message))){
				json = "{\"success\":\"true\", \"message\":\"导入信息成功\"}";
			}else{
				json = "{\"success\":\"false\", \"message\":\"有未验证通过的信息\"}";
			}
		}catch(Exception ex){
			json = "{\"success\":\"false\", \"message\":\"导入信息异常\"}";
			ex.printStackTrace();
		}finally{
			response.setContentType("text/html;charset=UTF-8");
        	response.getWriter().print(json);
		}
	}
	/***
	 * 导出合同验证信息
	* @Title: exportvalidInfo
	*created at 2014-6-8 上午09:06:22 by ygq  
	* @throws Exception
	* @return void 行政采购合同信息
	 */
	public void exportvalidContractInfo()  throws Exception{
		response.setContentType("application/vnd.ms-excel");
		String fireFox = request.getParameter("fireFox");
		String fileName = "";
		//对字符进行编码
		if(null != fireFox && fireFox.equals("1")){
			//如果是火狐浏览器，则使用这种转码方式
			fileName = new String("行政采购合同信息.xlsx".getBytes(), "iso8859-1");
		}else{
			//如果其他浏览器,则使用这种转码方式
			fileName = URLEncoder.encode("行政采购合同信息.xlsx", "UTF-8");
		}
		String header = "attachment; filename=" + fileName;
		response.setHeader("Content-Disposition", header);
		// 创建响应输出流对象
		OutputStream out = response.getOutputStream();
		//导出文件需要的类
		try {
			 ExportExcel<AdminContractInfoForExcelVo> ex = new ExportExcel<AdminContractInfoForExcelVo>();
			 ex.exportExcelForValidOrExport("行政采购合同信息验证",ImportExcel.CONTRACTINFOTITLESForValid, datasetContractInfo, out,"yyyy-MM-dd");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			response.flushBuffer();
			datasetContractInfo = null;//清空dateset
		}
	}
}