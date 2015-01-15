package com.creditease.eas.projectmanage.service;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.creditease.eas.admin.bean.User;
import com.creditease.eas.compliance.bean.InvestFile;
import com.creditease.eas.projectmanage.bean.AgreementFile;
import com.creditease.eas.util.Pagination;

public interface IUploadFileService {
	 /**
     * 
     * 描述：上传文件
     * 2014-4-22 下午01:55:03 by sunxiaofeng
     * @version
     * @param excelFile
     * @param filePath
	 * @param projectfid 
	 * @param user 
     * @return  
     */
	public void upadFile(File fileName, String filePath, Integer projectfid, User user) throws Exception;
    /**
     * 
     * 描述：查询上传的文件
     * 2014-4-22 下午03:24:06 by sunxiaofeng
     * @version
     * @param pagination
     * @param request
     * @return
     */
	public Pagination queryUploadFile(Pagination pagination,
			HttpServletRequest request);
	/**
	 * 
	 * 描述：删除文件信息
	 * 2014-4-22 下午06:04:48 by sunxiaofeng
	 * @version
	 * @param parseInt
	 */
	public void deleteFile(int parseInt);
	/**
	 * 
	 * 描述：查询文件信息
	 * 2014-4-22 下午06:30:43 by sunxiaofeng
	 * @version
	 * @param parseInt
	 * @return
	 */
	public AgreementFile findAgreementFile(int fileFid);
}
