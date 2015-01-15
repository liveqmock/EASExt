package com.creditease.eas.hr.kingdee.query;

import org.apache.ibatis.session.SqlSession;

import com.creditease.eas.hr.bean.BaseLogRecord;
import com.creditease.eas.hr.dao.BaseLogRecordMapper;
import com.creditease.eas.util.BaseMyBatisDao;
/**
 * 日志记录操作
 * @BaseLogRecordOperator.java
 * created at 2013-1-24 下午04:28:52 by ygq
 * 
 * @author ygq({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class BaseLogRecordOperator extends BaseMyBatisDao{
	/**
	 * 
	 * 描述：查询合同到期的人员的名单
	 * 2013-1-4 下午03:02:45 by ygq
	 * @version
	 */
	public static String insertLogRecord(BaseLogRecord baseLogRecord){
		String str = "";
		SqlSession session = null;
		try {
			session = getSession();
			BaseLogRecordMapper mapper = session.getMapper(BaseLogRecordMapper.class); 
			//需要转正的人的职位信息
			int num = mapper.insert(baseLogRecord);
			
			System.out.println(num);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return str;
	}
	public static void main(String[] args) throws Exception{
//		String str = selectTest(null);
	//	String str = selectPositionGradeByCode(null);
//		System.out.println(str);
		
	}
}
