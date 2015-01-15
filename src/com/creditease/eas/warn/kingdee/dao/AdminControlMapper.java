package com.creditease.eas.warn.kingdee.dao;

import java.util.List;
import java.util.Map;
/**
 * 测试自由选择部门的代码
 * @AdminControlMapper.java
 * created at 2013-3-29 下午02:47:15 by ygq
 * 
 * @author ygq({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public interface AdminControlMapper {
	public List<Map<String,Object>> selectOrgAdminByChoose(Map map);
	//测试2
	public List<Map<String,Object>> selectOrgAdminByChooseTest2(Map map);
}
