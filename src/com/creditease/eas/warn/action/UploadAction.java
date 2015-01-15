package com.creditease.eas.warn.action;

import java.io.File;
import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.creditease.eas.adminipurc.service.IAdminiPurcContractService;
import com.creditease.eas.adminipurc.util.ImportExcel;
import com.creditease.eas.adminipurc.vo.AdminContractInfoForExportExcelVo;
import com.creditease.eas.util.DownloadUtil;
import com.creditease.eas.util.ExportExcel;
import com.creditease.eas.warn.bean.WaringDetailView;
import com.creditease.eas.warn.service.UploadService;
import com.opensymphony.xwork2.ActionSupport;
@Controller
@Scope(value="prototype")
public class UploadAction extends ActionSupport {
	@Autowired
	private UploadService uploadService;
	@Resource
	private IAdminiPurcContractService iPurcContractService;//行政采购合同预警对应的service
	private String title;
	private File upload;
	private String uploadContentType;
	private String uploadFileName;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public IAdminiPurcContractService getiPurcContractService() {
		return iPurcContractService;
	}
	public void setiPurcContractService(
			IAdminiPurcContractService iPurcContractService) {
		this.iPurcContractService = iPurcContractService;
	}

	/***
	 *  2014年7月7日修改
	* @Title: exportExcel
	*created at 2014-7-7 下午02:51:43 by ygq  
	* @throws Exception
	* @return void
	 */
	public void exportExcel() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		// 创建响应输出流对象
		OutputStream out = DownloadUtil.getOutputStream(response,"预警明细");
		//导出文件需要的类
		ExportExcel<WaringDetailView> ex = new ExportExcel<WaringDetailView>();
		String[] headers = {"预警类别","预警方式","主题","发送时间", "电话","部门id", "部门名称","出生日期","入职日期","职位id","职位名称",
				  "收件人IDS", "收件人", "收件人邮箱", "抄送人IDS","抄送人","创建时间", "短信内容"
			  };
		List<WaringDetailView> dataset = uploadService.queryDatasetByParams();
		try {
			ex.exportExcel(headers, dataset, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.flushBuffer();
		System.out.println("excel导出成功！");
	}

	/***
	 * 导出行政采购合同预警的信息
	* @Title: exportAdminiPrucContractExcel
	*created at 2014-6-3 上午08:23:50 by ygq  
	* @throws Exception
	* @return void
	 */
	public void exportAdminiPrucContractExcel() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		// 创建响应输出流对象
		OutputStream out = DownloadUtil.getOutputStream(response,"行政采购合同预警");
		//导出文件需要的类
		ExportExcel<AdminContractInfoForExportExcelVo> ex = new ExportExcel<AdminContractInfoForExportExcelVo>();
		List<AdminContractInfoForExportExcelVo> dataset = iPurcContractService.queryDatasetByParams();
		try {
			ex.exportExcelForValidOrExport("行政采购合同预警",ImportExcel.CONTRACTINFOTITLESFOREXPORT, dataset, out,"yyyy-MM-dd");
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.flushBuffer();
		System.out.println("excel导出成功！");
	}
}
