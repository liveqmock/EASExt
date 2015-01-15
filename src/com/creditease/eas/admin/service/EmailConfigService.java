package com.creditease.eas.admin.service;

import java.util.List;
import java.util.Map;

import com.creditease.eas.admin.bean.EmailConfig;
import com.creditease.eas.util.Pagination;

public interface EmailConfigService {

	/**
	 * 查询
	 * @param page
	 * @return
	 */
	Pagination queryPage(Pagination page);

	/**
	 * 邮FID得到EmailConfig对象
	 * @param fid
	 * @return
	 */
	EmailConfig getEmailConfigByFid(Integer fid);

	
	int update(EmailConfig emailConfig);

	List<Map> test();

}
