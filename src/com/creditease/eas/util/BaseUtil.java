package com.creditease.eas.util;

import java.util.List;

/**
 * 公共包
 * @BaseUtil.java
 * created at 2012-12-25 上午10:22:02 by ygq
 * 
 * @author ygq({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class BaseUtil {
	/**
	 * 
	 * 描述：添加所有的List的数据
	 * 2012-12-25 上午10:20:39 by ygq
	 * @version
	 * @param list
	 * @param baseDao
	 */
	
	public static void insertAll(List list,BaseDAO baseDao){
		if(list!=null){
			for (int i = 0; i < list.size(); i++) {
				Object o = list.get(i);
				if(null!=o){
					baseDao.insert(o);
				}
			}
		}
	}
}
