package com.creditease.eas.compliance.dao;

import java.util.List;
import java.util.Map;

import com.creditease.eas.compliance.bean.FtpFile;

public interface FtpFileMapper {

	public int addFtpFile(FtpFile ftpFile);

	public int getTotalCounts(Map map);

	public List queryPage(Map map1);

	public FtpFile getFtpFileById(Integer fid);


}
