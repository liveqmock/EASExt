package com.creditease.eas.accountr.kingdee.query;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.creditease.eas.accountr.kingdee.dao.NetMapper;
import com.creditease.eas.email.kingdee.dao.PersonInfoMapper;
import com.creditease.eas.util.BaseMyBatisDao;

public class NetQuery extends BaseMyBatisDao{
	/**
	 * 查询员工工资卡号信息
	 * @param map
	 * @return
	 */
	public static List<Map<String,String>> findCardInfo(List<String> personNumber){
		SqlSession session = getSession();
		NetMapper mapper = session.getMapper(NetMapper.class);
		return mapper.selectCardInfo(personNumber);
	}
    /**
     * 查看部门名称
     * @param deptNumber
     * @return
     */
	public List<Map<String, String>> findDeptInfo(List<String> deptNumber) {
		SqlSession session = getSession();
		NetMapper mapper = session.getMapper(NetMapper.class);
		return mapper.selectDeptInfo(deptNumber);
	}
	/**
	 * 查看员工信息
	 * @param person
	 * @return
	 */
	public List<Map<String, String>> findPersonInfo(List<String> person) {
		SqlSession session = getSession();
		NetMapper mapper = session.getMapper(NetMapper.class);
		return mapper.selectPersonInfo(person);
	}
	/**
	 * 查看科目名称
	 * @param accountNumber
	 * @return
	 */
	public List<Map<String, String>> findAccountInfo(List<String> accountNumber) {
		SqlSession session = getSession();
		NetMapper mapper = session.getMapper(NetMapper.class);
		return mapper.selectAccountInfo(accountNumber);
	}
}
