package com.creditease.eas.warn.action;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.creditease.eas.util.BaseAction;
import com.creditease.eas.util.Dictionary;
import com.creditease.eas.util.DictionaryUtil;
import com.creditease.eas.util.ExpExcelUtil;
import com.creditease.eas.warn.bean.FinanceRentContract;
import com.creditease.eas.warn.bean.OrgCostcenter;
import com.creditease.eas.warn.service.FinanceRentContractService;
import com.google.gson.GsonBuilder;
 /**
 * @FinanceRentContractAction.java 财务房租合同信息
 * created at 2013-9-16 上午09:50:22 by caoyong
 * 
 * @author caoyong({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
@Controller
@Scope("prototype")
public class FinanceRentContractAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;
	/**要自动装配的财务房屋合同接口**/
	@Autowired
	private FinanceRentContractService financeRentContractServiceImpl;
	

	/**财务房租合同bean**/
	private FinanceRentContract financeRentContract;
	/**付款方式**/
	private List<Dictionary> payTypes;
	/**合同状态**/
	private List<Dictionary> rentStatuses;
	/**要导入的excel源数据**/
	private File excelFile;
	/**要导入的excel源数据路径**/
	private String filePath;
	private String title;
	private File upload;
	private String uploadContentType;
	private String uploadFileName;
	
	private static Logger logger = Logger.getLogger(FinanceRentContractAction.class);
	/**
	 * 
	 * 描述：查询列表页数据
	 * 2013-9-16 上午11:02:39 by caoyong
	 * @version
	 * @return
	 * @throws Exception
	 */
	public String queryPageJson() throws Exception {
		this.pagination = financeRentContractServiceImpl.queryPage(pagination);
		return "queryPageJson";
	}
	
	
	/**
	 * 
	 * 描述：查看Entity详细信息
	 * 2013-9-16 上午11:28:30 by caoyong
	 * @version
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception{
		this.getAllDictionarys();
		financeRentContract = financeRentContractServiceImpl.getFinanceRentContractById(financeRentContract.getId());
		return "view";
	}
	/**
	 * 
	 * 描述：删除Entity
	 * 2013-9-16 上午11:28:58 by caoyong
	 * @version
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception{
		financeRentContractServiceImpl.delete(financeRentContract.getId());
		return "delete";
	}
	/**
	 * 
	 * 描述：跳转到编辑页面
	 * 2013-9-16 上午11:31:32 by caoyong
	 * @version
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception{
		this.getAllDictionarys();
		if(financeRentContract!=null) 
		financeRentContract = financeRentContractServiceImpl.getFinanceRentContractById(financeRentContract.getId());
		return "edit";
	}
	
	/**
	 * 
	 * 描述：查询所有下拉列表集合
	 * 2013-9-16 上午11:32:21 by caoyong
	 * @version
	 * @throws Exception
	 */
	public void getAllDictionarys() throws Exception{
		List<Dictionary> dictionaries = DictionaryUtil.getDictionarys(DictionaryUtil.singleMap, DictionaryUtil.payType);
		this.payTypes = new ArrayList<Dictionary>();
		Map<Integer, Dictionary> map = new HashMap<Integer, Dictionary>();
		for(Dictionary dictionary:dictionaries){map.put(DictionaryUtil.getKeyNum(dictionary.getKey())-1, dictionary);}
		for(int i=0;i<map.size();i++){this.payTypes.add(map.get(i));}
		this.rentStatuses = DictionaryUtil.getDictionarys(DictionaryUtil.singleMap, DictionaryUtil.rentStatus);
		response.getWriter().print(GsonBuilder.class.newInstance().create().toJson(this.payTypes));
	}
	/**
	 * 
	 * 描述：修改
	 * 2013-9-16 上午11:32:41 by caoyong
	 * @version
	 * @throws Exception
	 */
	public void update() throws Exception{
		try {
			financeRentContract.setLastUpdater(this.findUser().getUsername());
			boolean success=financeRentContractServiceImpl.update(financeRentContract);
			if(success) 
				logger.info("更新合同发送邮件提醒给负责人成功！");
			else
				logger.info("更新合同发送邮件提醒给负责人失败！");
			this.json = "{\"success\":\"true\"}";
		} catch (Exception e) {
			e.printStackTrace();	
			this.json = "{\"success\":\"false\"}";
		}finally{
			this.writerJsonToClient(this.json);
		}
		
	}
	/**
	 * 
	 * 描述：新增财务房屋合同记录
	 * 2013-9-16 上午11:33:59 by caoyong
	 * @version
	 * @throws Exception
	 */
	public void insert() throws Exception{
		try {
			financeRentContract.setCreator(this.findUser().getUsername());
			financeRentContractServiceImpl.insert(financeRentContract);
			this.json = "{\"success\":\"true\"}";
		} catch (Exception e) {
			e.printStackTrace();	
			this.json = "{\"success\":\"false\"}";
		}finally{
			this.writerJsonToClient(this.json);
		}
	}
	
	
	
	/**
	 * 
	 * 描述：导入数据
	 * 2013-9-16 下午01:51:02 by caoyong
	 * @version
	 * @throws Exception
	 */
	public void importExcel() throws Exception{
		try {
			json = financeRentContractServiceImpl.importExcel(excelFile,filePath);
		} catch (Exception e) {
			json = "{\"success\":\"false\", \"message\":\"</br>导入异常\"}";
			e.printStackTrace();
			logger.error(e.getMessage());
		}finally{
			response.setContentType("text/html;charset=UTF-8");
        	response.getWriter().print(json);
		}
	}
	/**
	 * 导出合同信息
	 * 描述：
	 * 2013-9-25 下午01:36:36 by admin
	 * @version
	 * @throws IOException
	 */
	public void expExcel() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("GBK");
		response.setContentType("GBK");
		response.setContentType("application/vnd.ms-excel");
		String fileName = "房屋合同信息";// 设置excel文件的名字
		if (!fileName.equals("")) {
			String header = "attachment; filename=" + new String((fileName + ".xls").getBytes(), "iso8859-1");
			response.setHeader("Content-Disposition", header);
		}
		// 创建响应输出流对象
		OutputStream out = response.getOutputStream();
		//导出文件需要的类
		String[] headers={"序号","纸质合同编号","成本中心编号","成本中心","使用部门",
					"付款部门","地区","办公室坐落地点","汇入账号(业主)","面积(平方米)","开始日期","首付日期"
					,"结束日期","租凭期限(月)","付款次数","付款方式","房屋押金","物业押金","免租期(月)",
					"年租金(元)","合同月租金","状态","备注","签署合同公司"};
	    String[] title={"","getContractNum","getCostCenterNum","getLastCostCenter","getOrgName","getPayOrgName","getCity",
	    		"getOfficeAdd","getToAccount","getAreaSqm","getRentStartTime","getRentEndTime","getRentEndTime",
	    		"getRentYear","getPayCount","getPaymentCycle","getHousingDeposit",
	    		"getPropertyDeposit","getRentFreePeriod","getAnnualRent","getMonthMoney","getStatus",
	    		"getRemark","getSignatory"};
	   
		List<FinanceRentContract> dataset = financeRentContractServiceImpl.expqueryPage(pagination);//查询合同信息
		try {
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFCellStyle cellStyle = wb.createCellStyle();
			HSSFSheet sheet = wb.createSheet("房屋合同信息");//设置页的名称
			HSSFRow row=sheet.createRow(0);
			row.setHeight((short)500);//设置行高
			for (int i = 0; i < headers.length; i++) {
				sheet.setColumnWidth(i, 10000);//设置单元格的宽度
				ExpExcelUtil.createTxtCell(wb, row, i, headers[i],ExpExcelUtil.createBgColStyleutil(cellStyle,wb));//创建表格第一行
			}
			if (dataset != null && !dataset.isEmpty()){//判断数据是否为空
				Field field=null;
				Object[] objs = dataset.toArray();//转换为数组
				for (int i = 0; i < objs.length; i++) {
					HSSFRow row1=sheet.createRow(i+1);//创建行，设置从第二行开始
					row1.setHeight((short)300);
					FinanceRentContract t = (FinanceRentContract) objs[i];
					for (int j = 0; j < title.length; j++) {
						if(title[j]!=""){
						String getMethodName = title[j];
						Class tCls = t.getClass();
						Method getMethod = tCls.getMethod(getMethodName,//利用反射获取值
								new Class[] {});
						Object value = getMethod.invoke(t, new Object[] {});
						 ExpExcelUtil.createTxtCell(wb, row1, j, value,ExpExcelUtil.createBgColStyle(cellStyle,wb));
						}else{
							ExpExcelUtil.createTxtCell(wb, row1, j, i+1,ExpExcelUtil.createBgColStyle(cellStyle,wb));
							 }
						}
				}
			}
			wb.write(out);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		out.close();//关闭
		response.flushBuffer();
		//System.out.println("excel导出成功！");
	}
	
	/**
	 * 
	 * 描述：查找合同编号是否已经存在
	 * 2013-9-27 下午02:09:06 by caoyong
	 * @version
	 */
	public void findContractNumExist(){
		boolean exist = false;
		PrintWriter pw = null;
		try {
			exist = financeRentContractServiceImpl.findContractNumExist();
			pw = response.getWriter();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			pw.print(exist);
			pw.close();
		}
	}
	/**
	 * 
	 * 描述：查找办公室地址是否已经存在
	 * 2013-9-27 下午02:09:06 by caoyong
	 * @version
	 */
	public void findOfficeAddExist(){
		boolean exist = false;
		PrintWriter pw = null;
		try {
			exist = financeRentContractServiceImpl.findOfficeAddExist();
			pw = response.getWriter();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			pw.print(exist);
			pw.close();
		}
	}
	/**
	    * 
	    * 描述：根据成本中心从eas中查找成本中心编号
	    * 2013-10-8 下午05:00:45 by sunxiaofeng
	    * @version
	    * @param costcenter
	 * @throws Exception 
	    */
	public String selectCostcenter(){
		try {
			this.pagination = financeRentContractServiceImpl.selectCostcenter(pagination);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "queryPageJson";
	}
		/**
	 * @return the payTypes
	 */
	public List<Dictionary> getPayTypes() {
		return payTypes;
	}

	/**
	 * @param payTypes the payTypes to set
	 */
	public void setPayTypes(List<Dictionary> payTypes) {
		this.payTypes = payTypes;
	}

	/**
	 * @return the rentStatuses
	 */
	public List<Dictionary> getRentStatuses() {
		return rentStatuses;
	}

	/**
	 * @param rentStatuses the rentStatuses to set
	 */
	public void setRentStatuses(List<Dictionary> rentStatuses) {
		this.rentStatuses = rentStatuses;
	}

	/**
	 * @return the financeRentContract
	 */
	public FinanceRentContract getFinanceRentContract() {
		return financeRentContract;
	}

	/**
	 * @param financeRentContract the financeRentContract to set
	 */
	public void setFinanceRentContract(FinanceRentContract financeRentContract) {
		this.financeRentContract = financeRentContract;
	}

	/**
	 * @return the excelFile
	 */
	public File getExcelFile() {
		return excelFile;
	}

	/**
	 * @param excelFile the excelFile to set
	 */
	public void setExcelFile(File excelFile) {
		this.excelFile = excelFile;
	}

	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}


	

}
