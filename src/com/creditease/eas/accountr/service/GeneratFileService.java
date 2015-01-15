package com.creditease.eas.accountr.service;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.creditease.eas.util.Pagination;

public interface GeneratFileService {
    /**
     * 查看文件信息
     * @param pagination
     * @param request
     * @return
     */
	public Pagination queryPageFile(Pagination pagination, HttpServletRequest request);

	public String generationFile(File excelFile, String filePath)throws Exception;

	public void deleteFile(int parseInt);

	public Map findFileInfo(int parseInt);

}
