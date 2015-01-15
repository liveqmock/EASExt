package com.creditease.eas.hr.kingdee.dao;

import java.util.HashMap;
import java.util.List;

public interface SpecialWorkerMapper {
/*
 * 特殊人员接口根据员工编码返回职位编码
 */
	List<HashMap<String, Object>> getSpecialWorkerServiceBycode(String fnumber);

}
