package com.creditease.eas.hr.service;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.creditease.eas.hr.bean.extoa.WSOAOrganization;
import com.creditease.eas.hr.bean.extoa.WSOAPerson;
import com.creditease.eas.hr.bean.extoa.WSOAPluralityPerson;
import com.creditease.eas.hr.bean.extoa.WSOAPosition;
@WebService(targetNamespace="http://com/creditease/eas/ws",serviceName="IOAMessageSendService")
@SOAPBinding(style = Style.DOCUMENT)
public interface IOAMessageSendService {
	/**********************************************************************OA 组织，职位，人员的拉取接口**********************************************************************************/
	/**
	 * 
	 * 描述：查询组织变动信息(需要有一个开始时间，结束时间)
	 * 2014-1-21 下午01:39:59 by ygq
	 * @version
	 * @return		 
	 */
	public String queryOrganizationChangeFromHRToOA(@WebParam(name="startRow")int startRow,@WebParam(name="endRow")int endRow,@WebParam(name="fnumber")String fnumber,@WebParam(name="beginTime")String beginTime,
			@WebParam(name="endTime")String endTime,@WebParam(name="fname")String fname,@WebParam(name="fid")String fid);
	/***
	 * 查询成本中心变更的sql
	* @Title: queryCostcenterChangeInfo
	*created at 2014-6-18 下午10:09:37 by ygq  
	* @param startRow
	* @param endRow
	* @param fnumber
	* @param beginTime
	* @param endTime
	* @param fname
	* @param fid
	* @return
	* @return String
	 */
	public String queryCostcenterChangeInfo(@WebParam(name="startRow")int startRow,@WebParam(name="endRow")int endRow,@WebParam(name="fnumber")String fnumber,@WebParam(name="beginTime")String beginTime,
			@WebParam(name="endTime")String endTime,@WebParam(name="fname")String fname,@WebParam(name="fid")String fid);
	/***
	 * 查询组织对应的负责人的信息
	 * @param beginTime
	 * @param endTime
	 * @param fOrgnumber
	 * @return
	 */
	public String queryResponPositionInfo(@WebParam(name="beginTime")String beginTime,@WebParam(name="endTime")String endTime,@WebParam(name="forgId")String forgId,@WebParam(name="fOrgnumber")String fOrgnumber);
	
	/**
	 * 
	 * 描述：查询职位变动信息
	 * 2013-1-21 下午01:39:59 by ygq
	 * @version
	 * @return
	 */ 
	public String queryPositionInfoChangeFromHRToOA(@WebParam(name="startRow")int startRow,@WebParam(name="endRow")int endRow,@WebParam(name="fnumber")String fnumber,@WebParam(name="beginTime")String beginTime,@WebParam(name="endTime")String endTime,@WebParam(name="fname")String fname,@WebParam(name="fid")String fid);
	/**
	 * 
	 * 描述：查询人员变动信息
	 * 2014-1-21 下午01:39:59 by ygq
	 * @version
	 * @return
	 */ 
	public String queryEmpInfoChangeFromHRToOA(@WebParam(name="startRow")int startRow,@WebParam(name="endRow")int endRow,@WebParam(name="fnumber")String fnumber,@WebParam(name="beginTime")String beginTime,@WebParam(name="endTime")String endTime,@WebParam(name="fname")String fname,@WebParam(name="fid")String fid);
	/**
	 * 
	 * 描述：人员职位中间表的信息
	 * 2015-5-13下午01:39:59 by ygq
	 * @version
	 * @return
	 */ 
	public String queryPersonPositionTempInfo(@WebParam(name="startRow")int startRow,@WebParam(name="endRow")int endRow,@WebParam(name="beginTime")String beginTime,@WebParam(name="endTime")String endTime,@WebParam(name="fpersoncode")String fpersoncode,@WebParam(name="fpersonid")String fpersonid,@WebParam(name="fpositioncode")String fpositioncode,@WebParam(name="fpositionid")String fpositionid);
	/***
	 * 查询人员对应的合同签订公司的信息
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public String queryChangeContractInfo(@WebParam(name="startRow")int startRow,@WebParam(name="endRow")int endRow,@WebParam(name="beginTime")String beginTime,@WebParam(name="endTime")String endTime,@WebParam(name="fpersoncode")String fpersoncode,@WebParam(name="fpersonid")String fpersonid);
	/***
	 * 查询人员任职历史记录信息
	* @Title: queryWorkExpCur
	*created at 2014-6-12 下午07:44:02 by ygq  
	* @param startRow
	* @param endRow
	* @return
	* @return String
	 */
	public String queryWorkExpCur(@WebParam(name="startRow")int startRow,@WebParam(name="endRow")int endRow,@WebParam(name="beginTime")String beginTime,@WebParam(name="endTime")String endTime,@WebParam(name="fpersoncode")String fpersoncode);
}
