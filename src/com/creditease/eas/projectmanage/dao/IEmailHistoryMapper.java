package com.creditease.eas.projectmanage.dao;

import java.util.List;
import java.util.Map;

import com.creditease.eas.projectmanage.bean.EmailHistory;
import com.creditease.eas.util.BaseDAO;

public interface IEmailHistoryMapper extends BaseDAO<EmailHistory, Integer> {

	public int getTotalCounts();

	@SuppressWarnings("unchecked")
	public int getEmailHistoryTotalCounts(Map map);
	
	@SuppressWarnings("unchecked")
	public List<Map>  queryAllEmailHistoryList(Map map);
    /**
     * 
     * 描述：添加邮件发送记录
     * 2014-5-26 下午05:07:10 by sunxiaofeng
     * @version
     * @param emailHistory
     */
	public void insertEmailHistory(EmailHistory emailHistory);
    


}
