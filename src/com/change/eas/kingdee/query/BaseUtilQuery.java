package com.change.eas.kingdee.query;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.change.eas.partner.bean.BaseUnit;
import com.creditease.eas.util.BaseMyBatisDao;

public class BaseUtilQuery extends BaseMyBatisDao {

	public List<BaseUnit> queryBaseUnit(Map map) {
		SqlSession session = null;
		session = getSession();
		List<BaseUnit> list = session.selectList("com.change.eas.kingdee.dao.BaseUnitMapper.queryBaseUnit", map);
		closeSession(session);
		return list;
	}

	public int getTotalBaseUnit(Map map) {
		SqlSession session = null;
		session = getSession();
		List<BaseUnit> list = session.selectList("com.change.eas.kingdee.dao.BaseUnitMapper.queryBaseUnit", map);
		closeSession(session);
		return list.size();
	}

	public List<BaseUnit> queryBaseUnitPage(Map map) {
		SqlSession session = null;
		session = getSession();
		List<BaseUnit> list = session.selectList("com.change.eas.kingdee.dao.BaseUnitMapper.queryBaseUnitPage",map);
		closeSession(session);
		return list;
	}
	
	public List<BaseUnit> queryBaseUnitPageJson(Map map) {
		SqlSession session = null;
		session = getSession();
		List<BaseUnit> list = session.selectList("com.change.eas.kingdee.dao.BaseUnitMapper.queryBaseUnitPageJson",map);
		closeSession(session);
		return list;

	}
}
