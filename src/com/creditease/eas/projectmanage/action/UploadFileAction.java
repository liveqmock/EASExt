package com.creditease.eas.projectmanage.action;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

import com.creditease.eas.admin.bean.User;
import com.creditease.eas.compliance.bean.InvestFile;
import com.creditease.eas.compliance.service.InvestFileService;
import com.creditease.eas.projectmanage.bean.AgreementFile;
import com.creditease.eas.projectmanage.bean.PmInfo;
import com.creditease.eas.projectmanage.service.IUploadFileService;
import com.creditease.eas.projectmanage.service.PmInfoService;
import com.creditease.eas.util.BaseAction;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpServer;

/**
 * 
 * @UploadFile.java合同文件上传 created at 2014-4-21 下午02:37:28 by sunxiaofeng
 * 
 * @author Administrator({@link authorEmail})
 * @version $Revision$</br> update: $Date$
 */
@Controller
@Scope("prototype")
public class UploadFileAction extends BaseAction{
	@Autowired
	private IUploadFileService uploadFileServiceImpl;
	private static Logger logger=Logger.getLogger(UploadFileAction.class);
	/**要上传源数据**/
	private File fileName;
	/**要上传源数据路径**/
	private String filePath;
	private String applicationTemplateName;
	/**
	 * 
	 * 描述：上传文件
	 * 2014-4-22 下午06:02:21 by sunxiaofeng
	 * @version
	 * @throws Exception
	 */
    public void upadFile() throws Exception{
    	try {
    		uploadFileServiceImpl.upadFile(fileName,filePath,Integer.parseInt(request.getParameter("projectfid")),findUser());
			json = "{\"success\":\"true\", \"message\":\"</br>文件上传成功\"}";
		} catch (Exception e) {
			json = "{\"success\":\"false\", \"message\":\"</br>文件上传失败\"}";
			e.printStackTrace();
			logger.error(e.getMessage());
		}finally{
			response.setContentType("text/html;charset=UTF-8");
        	response.getWriter().print(json);
		}
    }
    /**
     * 
     * 描述：查询文件信息
     * 2014-4-22 下午06:02:37 by sunxiaofeng
     * @version
     * @return
     * @throws Exception
     */
	public String queryUploadFile() throws Exception {
		this.pagination = uploadFileServiceImpl.queryUploadFile(pagination,request);
		return "queryPageJson";
	}
	/**
	 * 
	 * 描述：删除文件信息
	 * 2014-4-22 下午06:11:56 by sunxiaofeng
	 * @version
	 */
    public void deleteFile(){
    	uploadFileServiceImpl.deleteFile(Integer.parseInt(request.getParameter("fileFid")));
    }
    /**
     * 
     * 描述：判断文件是否存在
     * 2014-4-23 上午10:19:32 by sunxiaofeng
     * @version
     */
    public void isExistsFile() {
    	   AgreementFile agreementFile= uploadFileServiceImpl.findAgreementFile(Integer.parseInt(request.getParameter("fileFid")));
    		//	 file file = new file("e:\\book1.xls");
    	         filePath=agreementFile.getFilePath();
    			 File file = new File(filePath);
    				 try {
    					applicationTemplateName = URLEncoder.encode(agreementFile.getFileName(), "utf-8");
    					InputStream is=new FileInputStream(file);
    				} catch (Exception e) {
    					String jon = "false";
    					try {
    						Gson gson = new Gson();
    						String strJson = gson.toJson(jon);
    						writerJsonToClient(strJson);
    					} catch (Exception e1) {
    						e1.printStackTrace();
    			 }
    	}

	};
    /**
     * 
     * 描述：下载文件
     * 2014-4-23 上午10:19:17 by sunxiaofeng
     * @version
     * @return
     */
    public InputStream getInputStream() 
	{
       AgreementFile agreementFile= uploadFileServiceImpl.findAgreementFile(Integer.parseInt(request.getParameter("fileFid")));
	//	 file file = new file("e:\\book1.xls");
         filePath=agreementFile.getFilePath();
		 File file = new File(filePath);
			 try {
				applicationTemplateName = URLEncoder.encode(agreementFile.getFileName(), "utf-8");
				InputStream is=new FileInputStream(file);
				 return is;
			} catch (Exception e) {
				e.printStackTrace();
			}
		return null;
	}
	public File getFileName() {
		return fileName;
	}
	public void setFileName(File fileName) {
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
