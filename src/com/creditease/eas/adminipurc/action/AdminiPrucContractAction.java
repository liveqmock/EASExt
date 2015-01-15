package com.creditease.eas.adminipurc.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.creditease.eas.adminipurc.bean.AdminContractInfo;
import com.creditease.eas.adminipurc.service.IAdminiPurcContractService;
import com.creditease.eas.dictionary.bean.DictionaryItem;
import com.creditease.eas.dictionary.service.IDictionaryItemService;
import com.creditease.eas.util.BaseAction;
import com.creditease.eas.util.UserUtil;
/***
 * 合同信息
 * @Title:AdminiPrucContractAction.java
 * @Package com.creditease.eas.adminipurc.action
 * created at 2014-5-25 下午08:52:17 by ygq
 * @author ygq
 * @version 1.0
 */
@Controller
@Scope("prototype")
public class AdminiPrucContractAction extends BaseAction{
	@Resource
	private IAdminiPurcContractService iPurcContractService;
	private AdminContractInfo contractInfo;
	@Autowired
	private IDictionaryItemService dictionaryItemServiceImpl;
	
	private List<DictionaryItem> listFcontractType;//合同类别
	private List<DictionaryItem>  listFpaytype;//支付方式
	private List<DictionaryItem>  listContractStatus;//到期跟进状态
	
	public IAdminiPurcContractService getiPurcContractService() {
		return iPurcContractService;
	}
	public void setiPurcContractService(
			IAdminiPurcContractService iPurcContractService) {
		this.iPurcContractService = iPurcContractService;
	}
	public AdminContractInfo getContractInfo() {
		return contractInfo;
	}
	public void setContractInfo(AdminContractInfo contractInfo) {
		this.contractInfo = contractInfo;
	}
	public List<DictionaryItem> getListFcontractType() {
		return listFcontractType;
	}
	public void setListFcontractType(List<DictionaryItem> listFcontractType) {
		this.listFcontractType = listFcontractType;
	}
	public List<DictionaryItem> getListFpaytype() {
		return listFpaytype;
	}
	public void setListFpaytype(List<DictionaryItem> listFpaytype) {
		this.listFpaytype = listFpaytype;
	}
	public List<DictionaryItem> getListContractStatus() {
		return listContractStatus;
	}
	public void setListContractStatus(List<DictionaryItem> listContractStatus) {
		this.listContractStatus = listContractStatus;
	}
	/***
	 *  合同信息列表
	* @Title: queryPageJson   
	* @return
	* @throws Exception
	* @return String
	 */
	public String queryPageJson() throws Exception {
		this.pagination = iPurcContractService.queryPage(pagination);
		listFcontractType = dictionaryItemServiceImpl.seleteItemData(247);//行政采购合同类别
		return "queryPageJson";
	}
	/***
	 * 查看合同信息
	* @Title: view
	*created at 2014-6-1 上午10:35:44 by ygq  
	* @return
	* @throws Exception
	* @return String
	 */
	public String view() throws Exception{
		contractInfo = iPurcContractService.findContractInfoById(contractInfo.getFid());
		listFcontractType = dictionaryItemServiceImpl.seleteItemData(247);//行政采购合同类别
		listFpaytype = dictionaryItemServiceImpl.seleteItemData(248);//支付方式
		listContractStatus = dictionaryItemServiceImpl.seleteItemData(266);//行政采购合同状态
		return "view";
	}

	/***
	 * 编辑
	* @Title: edit
	*created at 2014-6-1 上午11:15:28 by ygq  
	* @return
	* @throws Exception
	* @return String
	 */
	public String edit() throws Exception{
		contractInfo = iPurcContractService.findContractInfoById(contractInfo.getFid());
		return "edit";
	}
	/***
	 * 修改
	* @Title: update
	*created at 2014-6-1 上午11:15:36 by ygq  
	* @throws Exception
	* @return void
	 */
	public void update() throws Exception{
		try {
			contractInfo.setFlastupdater(UserUtil.getUser().getId());
			contractInfo.setFlastupdatetime(new Date());
			iPurcContractService.update(contractInfo);
			this.json = "{\"success\":\"true\"}";
		} catch (Exception e) {
			e.printStackTrace();	
			this.json = "{\"success\":\"false\"}";
		}finally{
			this.writerJsonToClient(this.json);
		}
	}
	/***
	 * 物理删除
	* @Title: delete
	*created at 2014-6-1 上午11:15:43 by ygq  
	* @return
	* @throws Exception
	* @return String
	 */
	public String delete() throws Exception{
		iPurcContractService.delete(contractInfo.getFid());
		return "queryPageJsonBack";
	}
	/**
	 * 导出合同信息Excel
	 * 
	 * @return
	 * @throws Exception
	 */
//	public void exportExcel() throws Exception {
//		HttpServletRequest request = ServletActionContext.getRequest();
//		HttpServletResponse response = ServletActionContext.getResponse();
//		response.setCharacterEncoding("GBK");
//		response.setContentType("GBK");
//		response.setContentType("application/vnd.ms-excel");
//		String fileName = "预警明细";// 设置excel文件的名字
//		if (!fileName.equals("")) {
//			String header = "attachment; filename=" + new String((fileName + ".xlsx").getBytes(), "iso8859-1");
//			response.setHeader("Content-Disposition", header);
//		}
//		// 创建响应输出流对象
//		OutputStream out = response.getOutputStream();
//		//导出文件需要的类
//		ExportExcel<WaringDetailView> ex = new ExportExcel<WaringDetailView>();
//		List<WaringDetailView> dataset = null;//iPurcContractService.queryDatasetByParams();
//		try {
////			ex.exportExcel("行政采购合同预警",ImportExcel.CONTRACTINFOTITLES, dataset, out);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		response.flushBuffer();
//		System.out.println("excel导出成功！");
//	}
}