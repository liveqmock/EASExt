package com.creditease.eas.admin.dao;

import java.util.List;
import java.util.Map;

import com.creditease.eas.admin.bean.EmailConfig;

public interface EmailConfigMapper {

	int getTotalCounts();

	List queryPage(Map map);

	
	EmailConfig getEmailConfigByFid(Integer fid);

	int update(EmailConfig emailConfig);

	List<Map> test();

}
