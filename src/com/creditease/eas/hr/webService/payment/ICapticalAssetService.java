package com.creditease.eas.hr.webService.payment;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.creditease.eas.hr.bean.CapticalAssetsBean;

/**
 * 固定资产的
 * @IEmpChangeService.java
 * created at 2013-3-4 上午10:26:38 by ygq
 * 
 * @author ygq({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
@WebService(targetNamespace="capticalAsset")
public interface ICapticalAssetService {
	public List<CapticalAssetsBean> queryAssetsClassNameAndCodeByFistClass(String first_class);
	/**
	 * 描述：固定资产，查询计量单位
	 * 2013-3-18 下午12:24:57 by ygq
	 * @version
	 * @return
	 */
	public  List<CapticalAssetsBean> queryMeasureUnitInfo(@WebParam(name="fnumber")String catFnumber);
}
