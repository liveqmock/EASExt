package com.creditease.eas.recruitment.service;

import java.util.List;
import java.util.Map;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.creditease.eas.recruitment.bean.RecOrgAdmin;
import com.creditease.eas.recruitment.bean.RecOrgPosition;
import com.creditease.eas.recruitment.bean.RecPersonInfo;
@WebService(targetNamespace="http://com/creditease/eas/ws",serviceName="IRecruitmentWSService")
@SOAPBinding(style = Style.DOCUMENT)
public interface IRecruitmentWSService {
	/**
	 * 描述：1.组织
	 * 2013-5-9 下午08:34:20 by ygq
	 * @version
	 * @return
	 */
	List<RecOrgAdmin> findOrgAdmin(@WebParam(name="page")int page, @WebParam(name="size")int size);
	/***
	 * 描述：查询职位的信息
	 * 2013-8-7 下午11:01:32 by ygq
	 * @version
	 * @param map
	 * @return
	 */
	public List<RecOrgPosition> findOrgPositionInfo(@WebParam(name="page")int page, @WebParam(name="size")int size);
	
	/***
	 * 描述：查询人员的信息
	 * 2013-8-7 下午11:01:32 by ygq
	 * @version
	 * @param map
	 * @return
	 */
	public List<RecPersonInfo> findPersonInfo(@WebParam(name="page")int page, @WebParam(name="size")int size);
}
