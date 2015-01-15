package com.creditease.eas.compliance.action;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.cxf.wsdl.http.UrlEncoded;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.creditease.eas.compliance.bean.InvestFile;
import com.creditease.eas.compliance.service.InvestigationService;
import com.creditease.eas.util.BaseAction;
@Controller
@Scope("prototype")
public class DownloadFileAction extends BaseAction
{
	private String fileName;//要下载的文件名
	private String filePath;//要下载的文件所在的相对于项目的路径
	private String applicationTemplateName;
	@Autowired
	private InvestigationService investigationServiceImpl;
	public DownloadFileAction()
	{	
	}
	public InputStream getInputStream() 
	{
   InvestFile investfile= investigationServiceImpl.findFileName(Integer.parseInt(request.getParameter("id")));
	//	 file file = new file("e:\\book1.xls");
         filePath=investfile.getFilephysicspath();
		 File file = new File(filePath);
			 try {
				applicationTemplateName = URLEncoder.encode(investfile.getFilename(), "utf-8");
				InputStream is=new FileInputStream(file);
				 return is;
			} catch (Exception e) {
				e.printStackTrace();
			}
		return null;
	}
	
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getApplicationTemplateName() {
		return applicationTemplateName;
	}
	public void setApplicationTemplateName(String applicationTemplateName) {
		this.applicationTemplateName = applicationTemplateName;
	}

	
}
