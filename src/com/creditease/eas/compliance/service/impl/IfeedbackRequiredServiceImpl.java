/**
 * 
 */
package com.creditease.eas.compliance.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditease.eas.admin.bean.User;
import com.creditease.eas.admin.dao.UserMapper;
import com.creditease.eas.compliance.bean.IFeedbackRequired;
import com.creditease.eas.compliance.dao.IFeedbackRequiredMapper;
import com.creditease.eas.compliance.service.IFeedbackRequiredService;
import com.creditease.eas.util.PageUtil;
import com.creditease.eas.util.Pagination;
import com.creditease.eas.util.Utils;
/**
 * @IfeedbackServiceImpl.java	合规（初步调查——被投诉部门调查处理反馈要求service实现类）
 * created at 2013-10-8 下午02:56:01 by caoyong
 * 
 * @author caoyong({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
@Service
public class IfeedbackRequiredServiceImpl implements IFeedbackRequiredService {
	/** 合规（初步调查——被投诉部门调查处理反馈要求DAO） **/
	@Autowired
	private IFeedbackRequiredMapper feedbackRequiredMapper;
	/**用户service接口**/
	@Autowired
	private UserMapper userMapper;
	/** 合规（初步调查——被投诉部门调查处理反馈要求Entity）**/
	private IFeedbackRequired feedbackRequired;
	/** 日志**/
	private static final Logger logger = Logger.getLogger(IfeedbackRequiredServiceImpl.class);
	
	public int insert(IFeedbackRequired feedbackRequired) throws Exception {
		return feedbackRequiredMapper.insert(feedbackRequired);
	}

	public int delete(Integer id) throws Exception {
		return feedbackRequiredMapper.deleteByPrimaryKey(id);
	}
	public int update(IFeedbackRequired feedbackRequired) throws Exception {
		return feedbackRequiredMapper.updateByPrimaryKey(feedbackRequired);//修改
	}
	public IFeedbackRequired getFeedbackRequiredById(Integer id) throws Exception {
		feedbackRequired = feedbackRequiredMapper.selectByPrimaryKey(id);
		return feedbackRequired;
	}
	@SuppressWarnings("unchecked")
	public Pagination queryPage(Pagination page) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		int currentPage = PageUtil.strToPage(request.getParameter("page"));
		int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
//		Map map = HashMap.class.newInstance();
//		map.put("orgName", request.getParameter("orgName"));
		//查询总行数的方法
		int totalCounts = feedbackRequiredMapper.getTotalCounts();
		page = new Pagination(currentPage, pageSize, totalCounts);
		// 查询数据集的方法
		Map map1 = PageUtil.getMap(page);
//		map1.putAll(map);
		List list = feedbackRequiredMapper.queryPage(map1);
		page.setRows(list);
		return page;
	}
	/**
	 * 用户不存在时根据邮箱创建用户
	 */
	public void insertUserByEmail(String email,String createUserName) throws Exception {
		if(userMapper.selectedIfUserExists(email.trim())==0){//用户不存在时根据邮箱创建用户
			User user = User.class.newInstance();
			user.setUsername(email.trim());//用户名暂时为邮箱名字
			user.setPassword(email.trim());//密码暂时为邮箱名字
			user.setEmail(email.trim());//邮箱为邮箱名字
			user.setTypeid(0l);//用户类型
			user.setFisdelete(0);//是否删除
			user.setStutas(0);
			user.setFforbidden(0);//0：正在使用，1：已经禁用
			user.setCreateusername(createUserName);//用户名
			user.setCreatedate(Utils.getLongDate());
			userMapper.insertUser(user);
		}
	}
	public IFeedbackRequired getFeedbackRequiredByInvestigationId(
			Integer investigationId) throws Exception {
		return feedbackRequiredMapper.selectIfeedbackRequireByVisitigation(investigationId);
	}

	

	
	
}
