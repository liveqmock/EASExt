package com.creditease.eas.projectmanage.service.imp;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditease.eas.admin.bean.User;
import com.creditease.eas.projectmanage.bean.AgreementFile;
import com.creditease.eas.projectmanage.dao.IProjectMapper;
import com.creditease.eas.projectmanage.service.IUploadFileService;
import com.creditease.eas.util.PageUtil;
import com.creditease.eas.util.Pagination;
@Service
public class UploadFileServiceImpl implements IUploadFileService{
	@Autowired
	private IProjectMapper projectMapper;
	private static final String PATH_INFO="/app/tomcat-eas/easfile/upload/agreemenFile";
	//private static final String PATH_INFO="/home/easext/app/tomcat-eas/easfile/upload/agreemenFile";
	/**
	 * 上传文件
	 * @throws IOException 
	 */
	@Override 
	public void upadFile(File fileName, String filePath,Integer projectfid,User user) throws Exception {
		String temp[] = filePath.replaceAll("\\\\", ",").split(",");
		String str = temp[temp.length - 1];
		String name = str.substring(0,str.indexOf("."));
		String ext = str.substring(str.lastIndexOf(".") + 1);
		String targetDirectory = PATH_INFO;
		String prefix = new java.text.SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String saveFileName = name+"-"+prefix+"." + ext;
	//	String savePath=targetDirectory+str;
		// 生成上传的文件对象
		File target = new File(targetDirectory, saveFileName);
		//System.out.println(target.getPath());
		// 复制file对象，实现上传
	    FileUtils.copyFile(fileName, target);
	    AgreementFile agreementFile = new AgreementFile(str,saveFileName, target.getPath(), projectfid, user.getId().intValue(),user.getUsername());
	    projectMapper.insertFile(agreementFile);
    }
	/**
	 * 查询上传的文件
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Pagination queryUploadFile(Pagination pagination,
			HttpServletRequest request) {
		int currentPage = PageUtil.strToPage(request.getParameter("page"));
		int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
		Map map = new HashMap();
		map.put("fid", request.getParameter("projectfid"));
		map.put("viewName", request.getParameter("viewName"));
		map.put("endDate", request.getParameter("endDate"));
		map.put("startDate", request.getParameter("startDate"));
		int totalCounts = projectMapper.queryFileNumber(map);// 查询总行数
		pagination = new Pagination(currentPage, pageSize, totalCounts);
		// 查询数据集的方法
		Map mapTo = PageUtil.getMap(pagination);
		mapTo.putAll(map);//复制map中的查询条件
		pagination.setRows(projectMapper.queryFileList(mapTo));
		return pagination;
	}
	/**
	 * 删除文件信息
	 */
	@Override
	public void deleteFile(int fileFid) {
		projectMapper.deleteFile(fileFid);
	}
	/**
	 * 查询文件信息
	 */
	@Override
	public AgreementFile findAgreementFile(int fileFid) {
		return projectMapper.findAgreementFile(fileFid);
	}
}
