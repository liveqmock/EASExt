/**
 * 
 */
package com.creditease.eas.warn.service;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import _208._203._207._111.ormrpc.services.WSTransferDataFacade.WSTransferDataFacadeSrvProxy;

import com.creditease.eas.warn.bean.personContactMethod;
import com.creditease.eas.warn.kingdee.dao.PersonDataMapper;

/**
 * @SetEmailService.java
 * created at 2013-3-22 下午02:37:02 by xjw
 * 
 * @author xjw({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public interface SetEmailService {

	public void setAllEmailTest2(personContactMethod pcm,Map<String,Integer> map,PersonDataMapper mapper,Map<String, Object> obj,String[] ss,SqlSession session);
	
	public void setAllEmailTest();
	
	public void setEmpty();
	
	public void setEmpty(String fnumber,WSTransferDataFacadeSrvProxy sercice);
	
	public void trimName();
}
