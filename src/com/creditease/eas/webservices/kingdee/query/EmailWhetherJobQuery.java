package com.creditease.eas.webservices.kingdee.query;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.creditease.eas.util.BaseMyBatisDao;
import com.creditease.eas.webservices.kingdee.dao.EmailWhetherJobMapper;
/**
 * 
 * <p>Title: EmailWhetherJobQuery</p>
 * <p>Description: 根据宜信邮箱判断此邮箱账户对应人员是否正式在职员工，反馈结果TRUE OR FALSE</p>
 * <p>date : 2014-7-21 下午02:08:29 </p>
 * @author gaoliya_thinking
 * @version 1.0
 */
public class EmailWhetherJobQuery extends BaseMyBatisDao{
	public List emailWorkingConditions(String cfmail){
		SqlSession session=null;
		List cemail=null;
		try {
			session=getSession();
			EmailWhetherJobMapper emailmapper=session.getMapper(EmailWhetherJobMapper.class);
			cemail=emailmapper.emailWorkingConditions(cfmail);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
		return cemail;
		
	}

}
