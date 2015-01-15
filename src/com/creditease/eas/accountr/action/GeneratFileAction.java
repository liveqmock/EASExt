package com.creditease.eas.accountr.action;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.creditease.eas.accountr.service.GeneratFileService;
import com.creditease.eas.accountr.service.IAccountrService;
import com.creditease.eas.util.BaseAction;
import com.google.gson.Gson;

/**
 * 
 * @UploadFile.java合同文件上传 created at 2014-4-21 下午02:37:28 by sunxiaofeng
 * 
 * @author Administrator({@link authorEmail})
 * @version $Revision$</br> update: $Date$
 */
@Controller
@Scope("prototype")
public class GeneratFileAction extends BaseAction{
	@Autowired
	private GeneratFileService generatFileService;
	private static Logger logger=Logger.getLogger(GeneratFileAction.class);
	/**要上传源数据**/
	private File excelFile;
	/**要上传源数据路径**/
	private String filePath;
	private String applicationTemplateName;
	/**
	 * 
	 * 描述：查询文件信息
	 * 2014-5-27 下午04:58:11 by sunxiaofeng
	 * @version
	 */
	public String queryFileInfo(){
		this.pagination = generatFileService.queryPageFile(pagination,request);
		return "queryPageJson";
	}

	/**
	 * 
	 * 描述：生成网银信息及凭证
	 * 2014-5-23 上午10:01:16 by sunxiaofeng
	 * @version
	 * @throws IOException
	 */
	public void generationFile() throws Exception{
		try {
			json=generatFileService.generationFile(excelFile,filePath);
			//json = "{\"success\":\"true\", \"message\":\"</br>文件上传成功\"}";
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
	 * 描述：删除文件信息
	 * 2014-5-27 下午07:17:06 by sunxiaofeng
	 * @version
	 */
    public void deleteFile(){
    	generatFileService.deleteFile(Integer.parseInt(request.getParameter("fileFid")));
    }
    /**
     * 
     * 描述：判断文件是否存在
     * 2014-4-23 上午10:19:32 by sunxiaofeng
     * @version
     */
    public void isExistsFile() {
    	   Map map= generatFileService.findFileInfo(Integer.parseInt(request.getParameter("fileFid")));
    		//	 file file = new file("e:\\book1.xls");
    	         filePath=map.get("FILEPATH").toString();
    			 File file = new File(filePath);
    				 try {
    					applicationTemplateName = URLEncoder.encode(map.get("FILENAME").toString(), "utf-8");
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
	 * 下载源文件
	 */
	public void downLoadSourceFile(){
		try {
			Map map= generatFileService.findFileInfo(Integer.parseInt(request.getParameter("fileFid")));
			response.setCharacterEncoding("GBK");
			response.setContentType("application/vnd.ms-excel");  
			String header = "attachment; filename="
				+ new String(URLDecoder.decode(map.get("SOURCENAME").toString(), "UTF-8")
						.getBytes(), "iso8859-1");
	    	response.setHeader("Content-Disposition", header);
	    	ServletOutputStream out = response.getOutputStream();
	    	File file = new File(map.get("SOURCEPATH").toString());
			if (file.exists() && file.isFile()) {
				FileInputStream in = new FileInputStream(file);
				byte[] by = new byte[in.available()];
				int length = 0;
				while ((length = in.read(by)) != -1) {
					out.write(by, 0, length);
				}
				out.close();
				in.close();
			} 
		} catch (Exception e) {
			e.printStackTrace();
			//throw new RuntimeException(e);
		}
	}
	/**
	 * 判断源文件是否存在
	 */
	public void isExistsSourceFile(){
		Map map= generatFileService.findFileInfo(Integer.parseInt(request.getParameter("fileFid")));
		File file = new File(map.get("SOURCEPATH").toString());
		if (file.exists() && file.isFile()) {
		}else{
			String jon = "false";
			try {
				Gson gson = new Gson();
				String strJson = gson.toJson(jon);
				writerJsonToClient(strJson);
			} catch (Exception e1) {
				e1.printStackTrace();
	      }
		}
	}

    /**
     * 
     * 描述：下载文件
     * 2014-4-23 上午10:19:17 by sunxiaofeng
     * @version
     * @return
     */
    public InputStream getInputStream() 
	{
    	Map map= generatFileService.findFileInfo(Integer.parseInt(request.getParameter("fileFid")));
	//	 file file = new file("e:\\book1.xls");
    	  filePath=map.get("FILEPATH").toString();
		 File file = new File(filePath);
			 try {
				applicationTemplateName = URLEncoder.encode(map.get("FILENAME").toString(), "utf-8");
				InputStream is=new FileInputStream(file);
				 return is;
			} catch (Exception e) {
				e.printStackTrace();
			}
		return null;
	}

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
	public String getApplicationTemplateName() {
		return applicationTemplateName;
	}
	public void setApplicationTemplateName(String applicationTemplateName) {
		this.applicationTemplateName = applicationTemplateName;
	}
    
}
