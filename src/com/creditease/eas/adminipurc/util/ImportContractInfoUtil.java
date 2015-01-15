package com.creditease.eas.adminipurc.util;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.creditease.eas.admin.bean.User;
import com.creditease.eas.adminipurc.bean.AdminContractInfo;
import com.creditease.eas.adminipurc.dao.AdminContractInfoMapper;
import com.creditease.eas.adminipurc.dao.CommonPortinfoMapper;
import com.creditease.eas.dictionary.bean.DictionaryItem;
import com.creditease.eas.dictionary.dao.IDictionaryItemMapper;
import com.creditease.eas.dictionary.service.IDictionaryBaseService;
import com.creditease.eas.util.DateUtil;
import com.creditease.eas.util.StringUtil;
import com.creditease.eas.util.UserUtil;
/***
 * 导入合同信息的公用包
 * @Title:ImportContractInfoUtil.java
 * @Package com.creditease.eas.adminipurc.util
 * created at 2014-5-25 下午04:23:21 by ygq
 * @author ygq
 * @version 1.0
 */
public class ImportContractInfoUtil {
	/***
	 * 导入合同信息
	* @Title: importContractInfos
	*created at 2014-6-22 下午04:33:39 by ygq  
	* @param list
	* @param adminContractInfoMapper
	* @param dictionaryItemMapper
	* @throws Exception
	* @return void
	 */
	public void importContractInfos(List<List<String>> list,AdminContractInfoMapper adminContractInfoMapper,IDictionaryBaseService dictionaryBaseService,CommonPortinfoMapper commonPortinfoMapper) throws Exception{
		//查询公司和部门编码信息,便利并导入合同信息
		if (list != null && list.size() > 2) {
		     for (int i = 2; i < list.size(); i++){  
		    	//获得表头
		    	List<String> titlesList = list.get(1);
				int[] titleNum = ImportExcel.getCellNumByName(titlesList,ImportExcel.CONTRACTINFOTITLES);// 表头列对应的列numberz
				List<String> cellList = list.get(i); 
		      	String forgName = cellList.get(titleNum[0]).trim();//使用部门
		     	String fcity = cellList.get(titleNum[1]).trim();//城市
		     	String flastCostcenter = cellList.get(titleNum[2]).trim();//末级成本中心
		      	String fofficeAdd = cellList.get(titleNum[3]).trim();//办公司地址
		    	if((forgName == null || forgName.equals("")) &&(fcity == null || fcity.equals("")) && (flastCostcenter == null || flastCostcenter.equals(""))
		    	  && (fofficeAdd == null || fofficeAdd.equals(""))){
		    		break;
		    	}
		    	String fstrcontracttype = cellList.get(titleNum[4]).trim();
		    	//合同类别
		    	int fcontracttypeid = dictionaryBaseService.getItemKey("xzcgct", fstrcontracttype);
		    	
		    	String fsuppliername = cellList.get(titleNum[5]).trim();//供应商名称
		    	String fcontractAmount = cellList.get(titleNum[6]).trim();//合同额（元）
		    	//6、快递合同、总部框架协议、酒店合同额的合同额不准为空，如果是空，默认是0。其他类别不能为0.
		    	if("快递合同".equals(fstrcontracttype)||"总部框架协议".equals(fstrcontracttype)||"酒店协议".equals(fstrcontracttype)
		    		||"通讯（电话/宽带）协议".equals(fstrcontracttype)){
		    		if("".equals(fcontractAmount)||null == fcontractAmount){
		    			fcontractAmount = "0";
		    		}
		    	}
		    	Date fbegindate = StringUtil.strToDate(cellList.get(titleNum[7]).trim(),"yyyy-MM-dd");//开始日期
		    	Date fenddate = StringUtil.strToDate(cellList.get(titleNum[8]).trim(),"yyyy-MM-dd");//结束日期
		    	
		    	String fisRenewal = cellList.get(titleNum[9]).trim();//无特殊情况自动续签
		    	
		    	String fmonthCost = cellList.get(titleNum[10]).trim();//月费用
		    	String fprintDeposit = cellList.get(titleNum[11]).trim();//打印设备押金
		    	
		    	int fpaytype = dictionaryBaseService.getItemKey("xzcgpw", cellList.get(titleNum[12]).trim());
		    	String fremark = cellList.get(titleNum[13]).trim();//备注
		    	
		    	
		    	String contractFnumber = dictionaryBaseService.getItemId("xzcgcomadmin",forgName);//获得合同编号前缀
		    	
		    	String contractType = dictionaryBaseService.getItemId("xzcgct",fstrcontracttype);//行政采购合同类别
		    	
		    	String fnumberPre = contractFnumber + "-" + contractType + StringUtil.getCurrentYear();
		    	Integer fserialNumber = adminContractInfoMapper.findContractSerialNumber(fnumberPre);//序列号
		    	//4、	合同编码自动生成：生成规则为公司部门编码+—+合同类别编码+当前年份+3位流水
		    	String fnumber = fnumberPre + ImportExcel.getfserialNumber(fserialNumber);//获得合同编号前缀
//		    	int fontractstatus = ImportExcel.getDictionaryId("生效",listContractStatus);//合同到期跟进状态
		    	//保存合同信息
		    	AdminContractInfo adminContractInfo = new AdminContractInfo();
		    	adminContractInfo.setFdeletestatus("1");//设置合同状态
		    	//正常导入时，合同到期跟进状态设置为空
//		    	adminContractInfo.setFcontractstatus(fontractstatus);//1.生效 2.已续签、3.续签申请中、4.不续签
		    	adminContractInfo.setFnumber(fnumber);//设置合同编号
		    	adminContractInfo.setFserialNumber(fserialNumber);
		    	adminContractInfo.setForgname(forgName);
		    	adminContractInfo.setFcity(fcity);
		    	adminContractInfo.setFlastcostcenter(flastCostcenter);
		    	adminContractInfo.setFofficeaddr(fofficeAdd);
		    	adminContractInfo.setFcontracttypeid(fcontracttypeid);//合同类别
		    	adminContractInfo.setFsuppliername(fsuppliername);
//		    	adminContractInfo.setFsupplierfnumber(fsupplierfnumber);//供应商编码暂时不设置
		    	adminContractInfo.setFcontractamount(fcontractAmount);
		    	
		    	adminContractInfo.setFbegindate(fbegindate);//开始日期
		    	adminContractInfo.setFenddate(fenddate);//结束日期
		    	adminContractInfo.setFisrenewal(fisRenewal);
		    	
		    	long fdurtime = DateUtil.monthsBetweenIncludeDays(fbegindate, fenddate);
		    	adminContractInfo.setFdurtime(fdurtime);
		    	adminContractInfo.setFmonthcost(fmonthCost);
		    	adminContractInfo.setFprintdeposit(fprintDeposit);
		    	adminContractInfo.setFpaytypeid(fpaytype);
		    	adminContractInfo.setFremark(fremark);
		      	User user = UserUtil.getUser();//获得登陆用户的基本信息
		      	
		      	//保存合同信息时，保存下接口人的id
		      	Map mapCommon = new HashMap();
		      	mapCommon.put("fportemail", user.getUsername());
		      	mapCommon.put("forgname", forgName);//根据总接口人所在的部门查
		      	List<Map<String,Object>> listInfo = commonPortinfoMapper.queryPortInfoByEmail(mapCommon);
		      	if(null != listInfo && !listInfo.isEmpty()){
		      		Map<String,Object> map = listInfo.get(0);
		      		String fid = map.get("FID") + "";
		      		adminContractInfo.setFext2(fid);
		      	}
		      	//
		      	if(null != user){
		      		adminContractInfo.setFcreator(user.getId());
		      		adminContractInfo.setFcreatetime(new Date());
		      		adminContractInfo.setFlastupdater(user.getId());
		      		adminContractInfo.setFlastupdatetime(new Date());
		        	/**:
				     * 2014年行政采购合同信息，查询总接口人信息和合同信息时，需要根据接口人的邮箱查询，
				     * 故保存下接口人的邮箱，区分是哪个接口人存储的信息
				     * **/
		      		adminContractInfo.setFext1(user.getUsername());
		      	}
		      //对合同进行重复性判断:根据合同姓名和邮箱进行判断
		      	Map map = new HashMap();
		      	map.put("forgName", forgName);
		    	map.put("fcity", fcity);
		    	map.put("flastCostcenter", flastCostcenter);
		    	map.put("fofficeAdd", fofficeAdd);
		    	map.put("fcontracttypeid",fcontracttypeid);
		    	map.put("fbegindate", fbegindate);
		    	map.put("fenddate", fenddate);
		    	AdminContractInfo  adminContractInfoExists = adminContractInfoMapper.findIfContractExists(map);//重复插入判断
		      	if(null == adminContractInfoExists){//不存在则直接添加合同信息
		      		adminContractInfoMapper.insert(adminContractInfo);
		      	}else{//存在则更新合同信息
		      		adminContractInfo.setFid(adminContractInfoExists.getFid());
		      		adminContractInfo.setFnumber(adminContractInfoExists.getFnumber());//还使用原来的合同编号
		      		adminContractInfo.setFserialNumber(adminContractInfoExists.getFserialNumber());
		      		adminContractInfoMapper.update(adminContractInfo);
		      	}
			}
		}
	}
}
