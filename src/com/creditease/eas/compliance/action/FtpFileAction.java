package com.creditease.eas.compliance.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.SocketException;
import java.net.URLEncoder;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.creditease.eas.compliance.bean.FtpFile;
import com.creditease.eas.compliance.service.FtpFileService;
import com.creditease.eas.util.BaseAction;
import com.google.gson.Gson;
/**
 * 
 * @FtpFileAction.java
 * created at 2013-11-18 下午08:12:09 by sunxiaofeng
 * 描述：投诉管理模块中ftp文件下载模块
 * @version $Revision$</br>
 * update: $Date$
 */
@Controller
@Scope("prototype")
public class FtpFileAction extends BaseAction{
	
	@Autowired
    private FtpFileService ftpFileserviceImpl;
	private FtpFile ftpFile;
	private FTPClient ftpClient;
	private String filePath;//要下载的文件所在的相对于项目的路径
	private String applicationTemplateName;//要下载的文件名
	private static Logger logger=Logger.getLogger(FtpFileAction.class);
	/**
	 * 
	 * 描述：展示文件列表
	 * 2013-10-31 下午01:26:41 by sunxiaofeng
	 * @version
	 * @return  
	 */
	public String queryPageJson(){
		try {
			this.pagination = ftpFileserviceImpl.queryPage(pagination);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "queryPageJson";
	}
	
	/**
	 * 
	 * 描述：新增文件信息
	 * 2013-10-31 上午11:08:19 by sunxiaofeng
	 * @version
	 */
	public void addFtpFile(){
		ftpFile.setCreaterid(findUser().getId());
		ftpFile.setCreatername(findUser().getUsername());
		ftpFileserviceImpl.addFtpFile(ftpFile);
		this.json = "{\"success\":\"true\"}";
		try {
			this.writerJsonToClient(this.json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 描述：跳转到编辑页面
	 * 2013-9-16 上午11:31:32 by caoyong
	 * @version
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception{
			ftpFile = ftpFileserviceImpl.getFtpFileById(ftpFile.getFid());
		return "view";
	}
	public FtpFile getFtpFile() {
		return ftpFile;
	}
	public void setFtpFile(FtpFile ftpFile) {
		this.ftpFile = ftpFile;
	}
	/**
	 * 
	 * 描述：判断文件是否存在
	 * 2013-11-18 下午05:19:21 by sunxiaofeng
	 * @version
	 *//*
	public void isExistsFile() {
		ftpFile = ftpFileserviceImpl.getFtpFileById(ftpFile.getFid());
		InputStream is=null;
		try {
			// 连接ftp服务器
			connectServer("10.105.48.202", 21, "v-jiwuhe", "stouch123!",
					"");
			is = download(ftpFile.getFilepath());
		} catch (Exception e) {
			String jon = "false";
			try {
				Gson gson = new Gson();
				String strJson = gson.toJson(jon);
				writerJsonToClient(strJson);	
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	};
	*//**
	 * 
	 * 描述：文件下载
	 * 2013-11-4 下午02:58:08 by sunxiaofeng
	 * @version
	 * @return  返回 输入流（InputStream）
	 * @throws Exception
	 *//*
	public InputStream getInputStream() {
		InputStream is=null;
		try {
			ftpFile = ftpFileserviceImpl.getFtpFileById(ftpFile.getFid());
			applicationTemplateName = URLEncoder.encode(ftpFile.getFilename(),
					"UTF-8");
			//连接ftp服务器
			connectServer("10.105.48.202", 21, "v-jiwuhe", "stouch123!", "");
			 is = download(ftpFile.getFilepath());
			 logger.info("文件下载成功");
		} catch (Exception e) {
			logger.error("文件下载失败");
			e.printStackTrace();
		}
		return is;
	}*/
	
	/**
	 * 判断文件是否存在
	 */
	public void isExistsFile() {
		ftpFile = ftpFileserviceImpl.getFtpFileById(ftpFile.getFid());
		InputStream in = null;
		try {
			connectServer("10.105.48.202", 21, "v-jiwuhe", "stouch123!", "");
			in = ftpClient.retrieveFileStream(new String((ftpFile.getFilepath()).getBytes("GBK"),"ISO-8859-1"));
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
		
		if(in == null) {
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
	 * 下载文件，返回输入流
	 * @return
	 */
	public InputStream getInputStream() {
		InputStream in=null;
		try {
			ftpFile = ftpFileserviceImpl.getFtpFileById(ftpFile.getFid());
			connectServer("10.105.48.202", 21, "v-jiwuhe", "stouch123!", "");
			
			applicationTemplateName = URLEncoder.encode(ftpFile.getFilename(),"UTF-8");
			
			in = ftpClient.retrieveFileStream(new String((ftpFile.getFilepath()).getBytes("GBK"),"ISO-8859-1"));
			logger.info("文件下载成功");
		} catch (Exception e) {
			logger.error("文件下载失败");
			e.printStackTrace();
		}
		return in;
	}

    /**
	 * 
	 * 描述： 2013-11-18 下午08:21:23 by sunxiaofeng
	 * 
	 * @version
	 * @param remoteFile
	 *            ftp上文件的路径。
	 * @return 返回 输入流（InputStream）
	 * @throws IOException
	 */
	/*private TelnetInputStream download(String remoteFile) throws IOException {
//			System.out.println(remoteFile);
			logger.info("***************" + remoteFile + "****************");
			TelnetInputStream is = ftpClient.get(remoteFile);
			return is;
	}*/

	  /**
	     * @param ip 服务器ip
	     * @param port 端口
	     * @param user 用户名
	     * @param password 密码
	     * @param path 服务器上的路径
	     *//*
	private void connectServer(String ip, int port, String user,
			String password, String path) {
		try {
			ftpClient = new FtpClient();
			ftpClient.openServer(ip, port);//连接服务器
			ftpClient.login(user, password);
			if (path.length() != 0){
				ftpClient.cd(path);//切换目录
			}
			ftpClient.binary();
			logger.info("ftp服务器连接成功");
		} catch (IOException ex) {
			logger.error("ftp服务器连接失败");
			ex.printStackTrace();
		}
	}*/
	
	public void connectServer(String server, int port, String user,  
            String password, String path) throws SocketException, IOException {  
        ftpClient = new FTPClient();  
        ftpClient.connect(server, port);  
//        ftpClient.setControlEncoding("UTF-8");
//        ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
//        ftpClient.enterLocalPassiveMode();
        System.out.println("Connected to " + server + ".");  
        System.out.println(ftpClient.getReplyCode());  
        ftpClient.login(user, password);  
        // Path is the sub-path of the FTP path  
        if (path.length() != 0) {  
            ftpClient.changeWorkingDirectory(path);  
        }  
    }

	public FTPClient getFtpClient() {
		return ftpClient;
	}

	public void setFtpClient(FTPClient ftpClient) {
		this.ftpClient = ftpClient;
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
