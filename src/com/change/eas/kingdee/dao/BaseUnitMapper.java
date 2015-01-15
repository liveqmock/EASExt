package com.change.eas.kingdee.dao;

import java.util.List;
import java.util.Map;

import com.change.eas.partner.bean.BaseUnit;

public interface BaseUnitMapper {

	public List<BaseUnit> queryBaseUnit(Map map);
	
	public List<BaseUnit> queryBaseUnitPage(Map map);
}
