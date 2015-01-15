package com.creditease.eas.hr.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.creditease.eas.hr.bean.Wsorganization;
import com.creditease.eas.hr.bean.Wspersoninfo;
import com.creditease.eas.hr.bean.Wspositioninfo;
import com.creditease.eas.util.StringUtil;
/**
 * 这个类暂时主要处理，推送数据的时候，数据之间的一些转换的方法
 * @CommonUtil.java
 * created at 2013-2-25 下午06:55:28 by ygq
 * 
 * @author ygq({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class CommonUtil {
	/**
	 * 描述：解析组织相关的xmlString的信息
	 * 这个地方需要分两种情况去处理
	 * (并且转换起来应该是比较复杂)
	 * 是不是可以考虑一下使用反射进行处理呢？？？？？？
	 * 2013-2-25 下午07:05:58 by ygq
	 * @version
	 * @param xmlString
	 * @param baseLogId
	 * @return
	 */
	public static List<Wsorganization> xmlStringToWsorganization(String xmlString,Long baseLogId){
		List<Wsorganization> list = new ArrayList<Wsorganization>();
		try{
			Document doc = DocumentHelper.parseText(xmlString);
			Element root= doc.getRootElement();//获取根节点.      
			for ( Iterator iter = root.elementIterator(); iter.hasNext(); ) {
				Element element = (Element) iter.next();
				if(element.getName().equals("MessageBody")){
					//遍历MessageBody的所有子节点，即：row节点
					for(Iterator it=element.elementIterator();it.hasNext();){
						Element elemRow = (Element)it.next();
						if(elemRow.getName().equals("row")){
							Wsorganization wsorganization = new Wsorganization();
							//获取row节点下的所有子节点（code，name）
							for(Iterator iterInner = elemRow.elementIterator(); iterInner.hasNext(); ) {
								 Element elementInner = (Element) iterInner.next();
								 //添加组织相关的信息
								if(elementInner.getName().equals("number")){
									 String fnumber = elementInner.getText();
									 wsorganization.setFnumber(fnumber);
								}else if(elementInner.getName().equals("principalID")){
									 String principalID = elementInner.getText();
									 wsorganization.setPrincipalid(principalID);
								}else if(elementInner.getName().equals("index")){
									 String findex = elementInner.getText();
									 wsorganization.setFindex(StringUtil.objToLong(findex));
								}else if(elementInner.getName().equals("parentNumber")){
									 String parentNumber = elementInner.getText();
									 wsorganization.setParentnumber(parentNumber);
								}else if(elementInner.getName().equals("effectDate")){
									 String effectDate = elementInner.getText();
									 wsorganization.setFeffectdate(StringUtil.strToDate(effectDate,"yyyy-MM-dd HH:mm:ss"));
								}else if(elementInner.getName().equals("invalidDate")){
									 String invalidDate = elementInner.getText();
									 wsorganization.setFinvaliddate(StringUtil.strToDate(invalidDate,"yyyy-MM-dd HH:mm:ss"));
								}else if(elementInner.getName().equals("isFreeze")){
									 String isFreeze = elementInner.getText();
									 wsorganization.setIsfreeze(isFreeze);
								}else if(elementInner.getName().equals("isStart")){
									 String isStart = elementInner.getText();
									 wsorganization.setFisstart(isStart);
								}else if(elementInner.getName().equals("orgTypeStr")){
									 String orgTypeStr = elementInner.getText();
									 wsorganization.setForgtypestr(orgTypeStr);
								}else if(elementInner.getName().equals("isLeaf")){
									 String isLeaf = elementInner.getText();
									 wsorganization.setIsleaf(isLeaf);
								}else if(elementInner.getName().equals("level")){
									 String level = elementInner.getText();
									 wsorganization.setFlevel(StringUtil.objToInteger(level));
								}else if(elementInner.getName().equals("displayName")){
									 String displayName = elementInner.getText();
									 wsorganization.setDisplayname(displayName);
								}else if(elementInner.getName().equals("description")){
									 String description = elementInner.getText();
									 wsorganization.setDescription(description);
								}else if(elementInner.getName().equals("simpleName")){
									 String simpleName = elementInner.getText();
									 wsorganization.setSimplename(simpleName);
								}else if(elementInner.getName().equals("isCostCenter")){
									 String isCostCenter = elementInner.getText();
									 wsorganization.setIscostcenter(isCostCenter);
								}else if(elementInner.getName().equals("pushTime")){
									 String pushTime = elementInner.getText();
									 wsorganization.setPushtime(StringUtil.strToDate(pushTime,"yyyy-MM-dd HH:mm"));//推送时间
								}
							}
							wsorganization.setRecdate(new Date());
							wsorganization.settLogBaseid(baseLogId);
							list.add(wsorganization);
						}
				}
			}
		  }
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return list;
	}
	/**
	 * 描述：解析组织相关的xmlString的信息(周一的时候验证下)
	 * 这个地方需要分两种情况去处理
	 * (并且转换起来应该是比较复杂)
	 * 是不是可以考虑一下使用反射进行处理呢？？？？？？
	 * 2013-2-25 下午07:05:58 by ygq
	 * @version
	 * @param xmlString
	 * @param baseLogId
	 * @return
	 */
	public static List<Wsorganization> xmlStringToWsorganization(String xmlStringOld,String xmlString,Long baseLogId){
		//解析xmlString
		List<Wsorganization> list = new ArrayList<Wsorganization>();
		try{
			Document doc = DocumentHelper.parseText(xmlString);
			Document d = DocumentHelper.parseText(xmlStringOld);
			Element root= doc.getRootElement();//获取根节点.      
			for ( Iterator iter = root.elementIterator(); iter.hasNext(); ) {
				Element element = (Element) iter.next();
				if(element.getName().equals("MessageBody")){
					//遍历MessageBody的所有子节点，即：row节点
					for(Iterator it=element.elementIterator();it.hasNext();){
						Element elemRow = (Element)it.next();
						if(elemRow.getName().equals("row")){
							//找到对应的row之后,获得里面的值
							String text = elemRow.getText();//获得第几行
							Element  info =(Element)d.selectSingleNode("//row[@num='" +text+"']");//这里面就会有对应的信息了
							Wsorganization wsorganization = new Wsorganization();
							//获取row节点下的所有子节点（code，name）
							if(info == null){
								continue;
							}
							for(Iterator iterInner = info.elementIterator(); iterInner.hasNext(); ) {
								 Element elementInner = (Element) iterInner.next();
								 //添加组织相关的信息
								if(elementInner.getName().equals("number")){
									 String fnumber = elementInner.getText();
									 wsorganization.setFnumber(fnumber);
								}else if(elementInner.getName().equals("principalID")){
									 String principalID = elementInner.getText();
									 wsorganization.setPrincipalid(principalID);
								}else if(elementInner.getName().equals("index")){
									 String findex = elementInner.getText();
									 wsorganization.setFindex(StringUtil.objToLong(findex));
								}else if(elementInner.getName().equals("parentNumber")){
									 String parentNumber = elementInner.getText();
									 wsorganization.setParentnumber(parentNumber);
								}else if(elementInner.getName().equals("effectDate")){
									 String effectDate = elementInner.getText();
									 wsorganization.setFeffectdate(StringUtil.strToDate(effectDate,"yyyy-MM-dd HH:mm:ss"));
								}else if(elementInner.getName().equals("invalidDate")){
									 String invalidDate = elementInner.getText();
									 wsorganization.setFinvaliddate(StringUtil.strToDate(invalidDate,"yyyy-MM-dd HH:mm:ss"));
								}else if(elementInner.getName().equals("isFreeze")){
									 String isFreeze = elementInner.getText();
									 wsorganization.setIsfreeze(isFreeze);
								}else if(elementInner.getName().equals("isStart")){
									 String isStart = elementInner.getText();
									 wsorganization.setFisstart(isStart);
								}else if(elementInner.getName().equals("orgTypeStr")){
									 String orgTypeStr = elementInner.getText();
									 wsorganization.setForgtypestr(orgTypeStr);
								}else if(elementInner.getName().equals("isLeaf")){
									 String isLeaf = elementInner.getText();
									 wsorganization.setIsleaf(isLeaf);
								}else if(elementInner.getName().equals("level")){
									 String level = elementInner.getText();
									 wsorganization.setFlevel(StringUtil.objToInteger(level));
								}else if(elementInner.getName().equals("displayName")){
									 String displayName = elementInner.getText();
									 wsorganization.setDisplayname(displayName);
								}else if(elementInner.getName().equals("description")){
									 String description = elementInner.getText();
									 wsorganization.setDescription(description);
								}else if(elementInner.getName().equals("simpleName")){
									 String simpleName = elementInner.getText();
									 wsorganization.setSimplename(simpleName);
								}else if(elementInner.getName().equals("isCostCenter")){
									 String isCostCenter = elementInner.getText();
									 wsorganization.setIscostcenter(isCostCenter);
								}else if(elementInner.getName().equals("pushTime")){
									 String pushTime = elementInner.getText();
									 wsorganization.setPushtime(StringUtil.strToDate(pushTime,"yyyy-MM-dd HH:mm"));//推送时间
								}
							}
							wsorganization.setRecdate(new Date());
							wsorganization.settLogBaseid(baseLogId);
							list.add(wsorganization);
						}
				}
			}
		  }
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return list;
	}
	/**
	 * 描述：解析人员相关的xmlString的信息--->如果说第二次推送还是失败，就进入异常信息块了
	 * 这个地方需要分两种情况去处理
	 * (并且转换起来应该是比较复杂)
	 * 2013-2-25 下午07:05:58 by ygq
	 * @version
	 * @param xmlString
	 * @param baseLogId
	 * @return
	 */
	public static List<Wspersoninfo> xmlStringToWspersoninfo(String xmlString,Long baseLogId){
		List<Wspersoninfo> list = new ArrayList<Wspersoninfo>();
		try{
			Document doc = DocumentHelper.parseText(xmlString);
			
			Element root= doc.getRootElement();//获取根节点.      
			for(Iterator iter = root.elementIterator(); iter.hasNext();) {
				Element element = (Element) iter.next();
				if(element.getName().equals("MessageBody")){
					//遍历MessageBody的所有子节点，即：row节点
					for(Iterator it=element.elementIterator();it.hasNext();){
						Element elemRow = (Element)it.next();
						if(elemRow.getName().equals("row")){
							Wspersoninfo wspersoninfo = new Wspersoninfo();
							//获取row节点下的所有子节点（code，name）
							for(Iterator iterInner = elemRow.elementIterator(); iterInner.hasNext(); ) {
								Element elementInner = (Element) iterInner.next();
								 //添加组织相关的信息
								if(elementInner.getName().equals("userName")){
									 String userName = elementInner.getText();
									 wspersoninfo.setUsername(userName);
								}else if(elementInner.getName().equals("loginName")){
									 String loginName = elementInner.getText();
									 wspersoninfo.setLoginname(loginName);
								}else if(elementInner.getName().equals("userEmail")){
									 String userEmail = elementInner.getText();
									 wspersoninfo.setUseremail(userEmail);
								}else if(elementInner.getName().equals("userActive")){
									 String userActive = elementInner.getText();
									 wspersoninfo.setUseractive(userActive);
								}else if(elementInner.getName().equals("userOrderNo")){
									 String userOrderNo = elementInner.getText();
									 wspersoninfo.setUserorderno(userOrderNo);
								}else if(elementInner.getName().equals("userStryle")){
									 String userStryle = elementInner.getText();
									 wspersoninfo.setUserstryle(userStryle);
								}else if(elementInner.getName().equals("userPassWord")){
									 String userPassWord = elementInner.getText();
									 wspersoninfo.setUserpassword(userPassWord);
								}else if(elementInner.getName().equals("userCode")){
									 String userCode = elementInner.getText();
									 wspersoninfo.setUsercode(userCode);
								}else if(elementInner.getName().equals("userSex")){
									 String userSex = elementInner.getText();
									 wspersoninfo.setUsersex(userSex);
								}else if(elementInner.getName().equals("birthDay")){
									 String birthDay = elementInner.getText();
									 wspersoninfo.setBirthday(birthDay);
								}else if(elementInner.getName().equals("telPhone")){
									 String telPhone = elementInner.getText();
									 wspersoninfo.setTelphone(telPhone);
								}else if(elementInner.getName().equals("cellPhone")){
									 String cellPhone = elementInner.getText();
									 wspersoninfo.setCellphone(cellPhone);
								}else if(elementInner.getName().equals("telephoneHome")){
									 String telephoneHome = elementInner.getText();
									 wspersoninfo.setTelephonehome(telephoneHome);
								}else if(elementInner.getName().equals("certType")){
									 String certType = elementInner.getText();
									 wspersoninfo.setCerttype(certType);
								}else if(elementInner.getName().equals("certNumber")){
									 String certNumber = elementInner.getText();
									 wspersoninfo.setCertnumber(certNumber);
								}else if(elementInner.getName().equals("identityNo")){
									 String identityNo = elementInner.getText();
									 wspersoninfo.setIdentityno(identityNo);
								}else if(elementInner.getName().equals("postCode")){
									 String postCode = elementInner.getText();
									 wspersoninfo.setPostcode(postCode);
								}else if(elementInner.getName().equals("address")){
									 String address = elementInner.getText();
									 wspersoninfo.setAddress(address);
								}else if(elementInner.getName().equals("comments")){
									 String comments = elementInner.getText();
									 wspersoninfo.setComments(comments);
								}else if(elementInner.getName().equals("userFax")){
									 String userFax = elementInner.getText();
									 wspersoninfo.setUserfax(userFax);
								}else if(elementInner.getName().equals("registerTime")){
									 String registerTime = elementInner.getText();
									 wspersoninfo.setRegistertime(registerTime);
								}else if(elementInner.getName().equals("cityNumber")){
									 String cityNumber = elementInner.getText();
									 wspersoninfo.setCitynumber(cityNumber);
								}else if(elementInner.getName().equals("directReports")){
									 String directReports = elementInner.getText();
									 wspersoninfo.setDirectreports(directReports);
								}else if(elementInner.getName().equals("operateSign")){
									 String operateSign = elementInner.getText();
									 wspersoninfo.setOperatesign(operateSign);
								}else if(elementInner.getName().equals("operloginName")){
									 String operloginName = elementInner.getText();
									 wspersoninfo.setOperloginname(operloginName);
								}else if(elementInner.getName().equals("pushTime")){
									 String pushTime = elementInner.getText();
									 wspersoninfo.setPushtime(StringUtil.strToDate(pushTime,"yyyy-MM-dd HH:mm"));//推送时间
								}
								if(elementInner.getName().equals("positionInfo")){
									for (Iterator iterIn = elementInner.elementIterator(); iterIn.hasNext(); ) {
										Element elemRowPo = (Element)iterIn.next();
										if(elemRowPo.getName().equals("positionCode")){
											 String positionCode = elementInner.getText();
											 wspersoninfo.setPostcode(positionCode);
										}else if(elemRowPo.getName().equals("isMainJob")){
											 String isMainJob = elementInner.getText();
											 wspersoninfo.setIsmainjob(isMainJob);//推送时间
										}
									}
									wspersoninfo.setCreatedate(new Date());
									wspersoninfo.settLogBaseid(baseLogId);
									list.add(wspersoninfo);
								}
							}
							
					}
				}
			}
		  }
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return list;
	}
	/**
	 * 描述：解析人员相关的xmlString的信息
	 * 这个地方需要分两种情况去处理
	 * (并且转换起来应该是比较复杂)
	 * 2013-2-25 下午07:05:58 by ygq
	 * @version
	 * @param xmlString
	 * @param baseLogId
	 * @return
	 */
	public static List<Wspersoninfo> xmlStringToWspersoninfo(String xmlStringOld,String xmlString,Long baseLogId){
		List<Wspersoninfo> list = new ArrayList<Wspersoninfo>();
		try{
			Document doc = DocumentHelper.parseText(xmlString);//返回的字符串
			Document d = DocumentHelper.parseText(xmlStringOld);//最初的字符串
			
			Element root= doc.getRootElement();//获取根节点. 

			for(Iterator iter = root.elementIterator(); iter.hasNext();) {
				Element element = (Element) iter.next();
				if(element.getName().equals("MessageBody")){
					//遍历MessageBody的所有子节点，即：row节点
					for(Iterator it=element.elementIterator();it.hasNext();){
						Element elemRow = (Element)it.next();
						if(elemRow.getName().equals("row")){
							//找到对应的row之后,获得里面的值
							String text = elemRow.getText();//获得第几行
							Element  info =(Element)d.selectSingleNode("//row[@num='" +text+"']");//这里面就会有对应的信息了
							Wspersoninfo wspersoninfo = new Wspersoninfo();
							//获取row节点下的所有子节点（code，name）
							for(Iterator iterInner = info.elementIterator(); iterInner.hasNext(); ) {
								Element elementInner = (Element) iterInner.next();
								 //添加组织相关的信息
								if(elementInner.getName().equals("userName")){
									 String userName = elementInner.getText();
									 wspersoninfo.setUsername(userName);
								}else if(elementInner.getName().equals("loginName")){
									 String loginName = elementInner.getText();
									 wspersoninfo.setLoginname(loginName);
								}else if(elementInner.getName().equals("userEmail")){
									 String userEmail = elementInner.getText();
									 wspersoninfo.setUseremail(userEmail);
								}else if(elementInner.getName().equals("userActive")){
									 String userActive = elementInner.getText();
									 wspersoninfo.setUseractive(userActive);
								}else if(elementInner.getName().equals("userOrderNo")){
									 String userOrderNo = elementInner.getText();
									 wspersoninfo.setUserorderno(userOrderNo);
								}else if(elementInner.getName().equals("userStryle")){
									 String userStryle = elementInner.getText();
									 wspersoninfo.setUserstryle(userStryle);
								}else if(elementInner.getName().equals("userPassWord")){
									 String userPassWord = elementInner.getText();
									 wspersoninfo.setUserpassword(userPassWord);
								}else if(elementInner.getName().equals("userCode")){
									 String userCode = elementInner.getText();
									 wspersoninfo.setUsercode(userCode);
								}else if(elementInner.getName().equals("userSex")){
									 String userSex = elementInner.getText();
									 wspersoninfo.setUsersex(userSex);
								}else if(elementInner.getName().equals("birthDay")){
									 String birthDay = elementInner.getText();
									 wspersoninfo.setBirthday(birthDay);
								}else if(elementInner.getName().equals("telPhone")){
									 String telPhone = elementInner.getText();
									 wspersoninfo.setTelphone(telPhone);
								}else if(elementInner.getName().equals("cellPhone")){
									 String cellPhone = elementInner.getText();
									 wspersoninfo.setCellphone(cellPhone);
								}else if(elementInner.getName().equals("telephoneHome")){
									 String telephoneHome = elementInner.getText();
									 wspersoninfo.setTelephonehome(telephoneHome);
								}else if(elementInner.getName().equals("certType")){
									 String certType = elementInner.getText();
									 wspersoninfo.setCerttype(certType);
								}else if(elementInner.getName().equals("certNumber")){
									 String certNumber = elementInner.getText();
									 wspersoninfo.setCertnumber(certNumber);
								}else if(elementInner.getName().equals("identityNo")){
									 String identityNo = elementInner.getText();
									 wspersoninfo.setIdentityno(identityNo);
								}else if(elementInner.getName().equals("postCode")){
									 String postCode = elementInner.getText();
									 wspersoninfo.setPostcode(postCode);
								}else if(elementInner.getName().equals("address")){
									 String address = elementInner.getText();
									 wspersoninfo.setAddress(address);
								}else if(elementInner.getName().equals("comments")){
									 String comments = elementInner.getText();
									 wspersoninfo.setComments(comments);
								}else if(elementInner.getName().equals("userFax")){
									 String userFax = elementInner.getText();
									 wspersoninfo.setUserfax(userFax);
								}else if(elementInner.getName().equals("registerTime")){
									 String registerTime = elementInner.getText();
									 wspersoninfo.setRegistertime(registerTime);
								}else if(elementInner.getName().equals("cityNumber")){
									 String cityNumber = elementInner.getText();
									 wspersoninfo.setCitynumber(cityNumber);
								}else if(elementInner.getName().equals("directReports")){
									 String directReports = elementInner.getText();
									 wspersoninfo.setDirectreports(directReports);
								}else if(elementInner.getName().equals("operateSign")){
									 String operateSign = elementInner.getText();
									 wspersoninfo.setOperatesign(operateSign);
								}else if(elementInner.getName().equals("operloginName")){
									 String operloginName = elementInner.getText();
									 wspersoninfo.setOperloginname(operloginName);
								}else if(elementInner.getName().equals("pushTime")){
									 String pushTime = elementInner.getText();
									 wspersoninfo.setPushtime(StringUtil.strToDate(pushTime,"yyyy-MM-dd HH:mm"));//推送时间
								}
								if(elementInner.getName().equals("positionInfo")){
									for (Iterator iterIn = elementInner.elementIterator(); iterIn.hasNext(); ) {
										Element elemRowPo = (Element)iterIn.next();
										if(elemRowPo.getName().equals("positionCode")){
											 String positionCode = elementInner.getText();
											 wspersoninfo.setPostcode(positionCode);
										}else if(elemRowPo.getName().equals("isMainJob")){
											 String isMainJob = elementInner.getText();
											 wspersoninfo.setIsmainjob(isMainJob);//推送时间
										}
									}
									wspersoninfo.setCreatedate(new Date());
									wspersoninfo.settLogBaseid(baseLogId);
									list.add(wspersoninfo);
								}
							}
							
					}
				}
			}
		  }
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return list;
	}
	/**
	 * 描述：解析职位相关的xmlString的信息
	 * 这个地方需要分两种情况去处理
	 * (并且转换起来应该是比较复杂)
	 * 2013-2-25 下午07:05:58 by ygq
	 * @version
	 * @param xmlString
	 * @param baseLogId
	 * @return
	 */
	public static List<Wspositioninfo> xmlStringToWspositioninfo(String xmlString,Long baseLogId){
		List<Wspositioninfo> list = new ArrayList<Wspositioninfo>();
		try{
			Document doc = DocumentHelper.parseText(xmlString);
			Element root= doc.getRootElement();//获取根节点.      
			for(Iterator iter = root.elementIterator(); iter.hasNext();) {
				Element element = (Element) iter.next();
				if(element.getName().equals("MessageBody")){
					//遍历MessageBody的所有子节点，即：row节点
					for(Iterator it=element.elementIterator();it.hasNext();){
						Element elemRow = (Element)it.next();
						if(elemRow.getName().equals("row")){
							Wspositioninfo wspersoninfo = new Wspositioninfo();
							//获取row节点下的所有子节点（code，name）
							for(Iterator iterInner = elemRow.elementIterator(); iterInner.hasNext(); ) {
								Element elementInner = (Element) iterInner.next();
								 //添加职位相关的信息
								if(elementInner.getName().equals("positionLevel")){
									 String positionlevel = elementInner.getText();
									 wspersoninfo.setPositionlevel(positionlevel);
								}else if(elementInner.getName().equals("positionlayer")){
									 String positionlayer = elementInner.getText();
									 wspersoninfo.setPositionlayer(positionlayer);
								}else if(elementInner.getName().equals("jobType")){
									 String jobType = elementInner.getText();
									 wspersoninfo.setJobtype(jobType);
								}else if(elementInner.getName().equals("sortCode")){
									 String sortCode = elementInner.getText();
									 wspersoninfo.setSortcode((StringUtil.objToInteger(sortCode)));
								}else if(elementInner.getName().equals("deletedStatus")){
									 String deletedStatus = elementInner.getText();
									 wspersoninfo.setDeletedstatus(StringUtil.objToInteger(deletedStatus));
								}else if(elementInner.getName().equals("positionType")){
									 String positionType = elementInner.getText();
									 wspersoninfo.setPositiontype(positionType);
								}else if(elementInner.getName().equals("index")){
									 String index = elementInner.getText();
									 wspersoninfo.setFindex(StringUtil.objToLong(index));
								}else if(elementInner.getName().equals("job")){
									 String job = elementInner.getText();
									 wspersoninfo.setJob(job);
								}else if(elementInner.getName().equals("FEffectDate")){
									 String FEffectDate = elementInner.getText();
									 wspersoninfo.setFinvaliddate(StringUtil.strToDate(FEffectDate,"yyyy-MM-dd HH:mm"));
								}else if(elementInner.getName().equals("FInvalidDate")){
									 String FInvalidDate = elementInner.getText();
									 wspersoninfo.setFinvaliddate(StringUtil.strToDate(FInvalidDate,"yyyy-MM-dd HH:mm"));
								}else if(elementInner.getName().equals("simpleName")){
									 String simpleName = elementInner.getText();
									 wspersoninfo.setSimplename(simpleName);
								}else if(elementInner.getName().equals("description")){
									 String description = elementInner.getText();
									 wspersoninfo.setFdescription(description);
								}else if(elementInner.getName().equals("number")){
									 String number = elementInner.getText();
									 wspersoninfo.setFnumber(number);
								}else if(elementInner.getName().equals("positionName")){
									 String positionName = elementInner.getText();
									 wspersoninfo.setPositionname(positionName);
								}else if(elementInner.getName().equals("parentNumber")){
									 String parentNumber = elementInner.getText();
									 wspersoninfo.setParentnumber(parentNumber);
								}else if(elementInner.getName().equals("deptnumber")){
									 String deptnumber = elementInner.getText();
									 wspersoninfo.setDeptnumber(deptnumber);
								}else if(elementInner.getName().equals("isMainJob")){
									 String isMainJob = elementInner.getText();
									 wspersoninfo.setIsmainjob(isMainJob);
								}else if(elementInner.getName().equals("pushTime")){
									 String pushTime = elementInner.getText();
									 wspersoninfo.setPushtime(StringUtil.strToDate(pushTime,"yyyy-MM-dd HH:mm"));//推送时间
								}
							}
								list.add(wspersoninfo);
					}
				}
			}
		  }
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return list;
	}
	/**
	 * 描述：解析职位相关的xmlString的信息
	 * 这个地方需要分两种情况去处理
	 * (并且转换起来应该是比较复杂)
	 * 2013-2-25 下午07:05:58 by ygq
	 * @version
	 * @param xmlString
	 * @param baseLogId
	 * @return
	 */
	public static List<Wspositioninfo> xmlStringToWspositioninfo(String xmlStringOld,String xmlString,Long baseLogId){
		List<Wspositioninfo> list = new ArrayList<Wspositioninfo>();
		try{
			Document doc = DocumentHelper.parseText(xmlString);
			Element root= doc.getRootElement();//获取根节点.  
			Document d = DocumentHelper.parseText(xmlStringOld);
			for(Iterator iter = root.elementIterator(); iter.hasNext();) {
				Element element = (Element) iter.next();
				if(element.getName().equals("MessageBody")){
					//遍历MessageBody的所有子节点，即：row节点
					for(Iterator it=element.elementIterator();it.hasNext();){
						Element elemRow = (Element)it.next();
						if(elemRow.getName().equals("row")){
							//找到对应的row之后,获得里面的值
							String text = elemRow.getText();//获得第几行
							Element  info =(Element)d.selectSingleNode("//row[@num='" +text+"']");//这里面就会有对应的信息了
							if(info == null){
								continue;
							}
							Wspositioninfo wspersoninfo = new Wspositioninfo();
							//获取row节点下的所有子节点（code，name）
							for(Iterator iterInner = info.elementIterator(); iterInner.hasNext();) {
								Element elementInner = (Element) iterInner.next();
								 //添加职位相关的信息
								if(elementInner.getName().equals("positionLevel")){
									 String positionlevel = elementInner.getText();
									 wspersoninfo.setPositionlevel(positionlevel);
								}else if(elementInner.getName().equals("positionlayer")){
									 String positionlayer = elementInner.getText();
									 wspersoninfo.setPositionlayer(positionlayer);
								}else if(elementInner.getName().equals("jobType")){
									 String jobType = elementInner.getText();
									 wspersoninfo.setJobtype(jobType);
								}else if(elementInner.getName().equals("sortCode")){
									 String sortCode = elementInner.getText();
									 wspersoninfo.setSortcode(StringUtil.objToInteger(sortCode));
								}else if(elementInner.getName().equals("deletedStatus")){
									 String deletedStatus = elementInner.getText();
									 wspersoninfo.setDeletedstatus(StringUtil.objToInteger(deletedStatus));
								}else if(elementInner.getName().equals("positionType")){
									 String positionType = elementInner.getText();
									 wspersoninfo.setPositiontype(positionType);
								}else if(elementInner.getName().equals("index")){
									 String index = elementInner.getText();
									 wspersoninfo.setFindex(StringUtil.objToLong(index));
								}else if(elementInner.getName().equals("job")){
									 String job = elementInner.getText();
									 wspersoninfo.setJob(job);
								}else if(elementInner.getName().equals("FEffectDate")){
									 String FEffectDate = elementInner.getText();
									 wspersoninfo.setFinvaliddate(StringUtil.strToDate(FEffectDate,"yyyy-MM-dd HH:mm"));
								}else if(elementInner.getName().equals("FInvalidDate")){
									 String FInvalidDate = elementInner.getText();
									 wspersoninfo.setFinvaliddate(StringUtil.strToDate(FInvalidDate,"yyyy-MM-dd HH:mm"));
								}else if(elementInner.getName().equals("simpleName")){
									 String simpleName = elementInner.getText();
									 wspersoninfo.setSimplename(simpleName);
								}else if(elementInner.getName().equals("description")){
									 String description = elementInner.getText();
									 wspersoninfo.setFdescription(description);
								}else if(elementInner.getName().equals("number")){
									 String number = elementInner.getText();
									 wspersoninfo.setFnumber(number);
								}else if(elementInner.getName().equals("positionName")){
									 String positionName = elementInner.getText();
									 wspersoninfo.setPositionname(positionName);
								}else if(elementInner.getName().equals("parentNumber")){
									 String parentNumber = elementInner.getText();
									 wspersoninfo.setParentnumber(parentNumber);
								}else if(elementInner.getName().equals("deptnumber")){
									 String deptnumber = elementInner.getText();
									 wspersoninfo.setDeptnumber(deptnumber);
								}else if(elementInner.getName().equals("isMainJob")){
									 String isMainJob = elementInner.getText();
									 wspersoninfo.setIsmainjob(isMainJob);
								}else if(elementInner.getName().equals("pushTime")){
									 String pushTime = elementInner.getText();
									 wspersoninfo.setPushtime(StringUtil.strToDate(pushTime,"yyyy-MM-dd HH:mm"));//推送时间
								}
							}
						   list.add(wspersoninfo);
					}
				}
			}
		  }
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return list;
	}
}
