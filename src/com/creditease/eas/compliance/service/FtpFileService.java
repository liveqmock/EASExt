package com.creditease.eas.compliance.service;

import com.creditease.eas.compliance.bean.FtpFile;
import com.creditease.eas.util.Pagination;

public interface FtpFileService {
   /**
    * 
    * 描述：添加文件信息
    * 2013-11-18 下午08:36:03 by sunxiaofeng
    * @version
    * @param ftpFile
    */
	public void addFtpFile(FtpFile ftpFile);
    /**
     * 
     * 描述：文件信息列表
     * 2013-11-18 下午08:36:27 by sunxiaofeng
     * @version
     * @param pagination  
     * @return  分页对象
     * @throws Exception
     */
	public Pagination queryPage(Pagination pagination) throws Exception ;
    /**
     * 
     * 描述：
     * 2013-11-18 下午08:37:14 by sunxiaofeng
     * @version  
     * @param fid  文件id
     * @return  文件对象
     */
	public FtpFile getFtpFileById(Integer fid);


}
