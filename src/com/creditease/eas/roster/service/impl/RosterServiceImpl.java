package com.creditease.eas.roster.service.impl;

import java.io.File;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.eclipse.jetty.server.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditease.eas.admin.bean.User;
import com.creditease.eas.admin.dao.ExtDataBaseMapper;
import com.creditease.eas.admin.dao.UserRoleMapper;
import com.creditease.eas.dictionary.dao.IDictionaryBaseMapper;
import com.creditease.eas.dictionary.dao.IDictionaryItemMapper;
import com.creditease.eas.dictionary.service.IDictionaryBaseService;
import com.creditease.eas.dictionary.service.IDictionaryItemService;
import com.creditease.eas.roster.action.RosterAction;
import com.creditease.eas.roster.bean.Roster;
import com.creditease.eas.roster.dao.IRosterMapper;
import com.creditease.eas.roster.service.IRosterService;
import com.creditease.eas.util.DownloadUtil;
import com.creditease.eas.util.ExpExcelUtil;
import com.creditease.eas.util.PageUtil;
import com.creditease.eas.util.Pagination;
import com.creditease.eas.util.Utils;
import com.creditease.eas.util.port.ImportExecl;
import com.hp.hpl.sparta.xpath.ThisNodeTest;
/**
 * 
 *投三花名册service层接口实现类
 * @RosterServiceImpl.java
 * created at 2014-3-17 下午05:25:17 by zhangxin
 * 
 * @author zhangxin({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
@SuppressWarnings("unused")
@Service
public class RosterServiceImpl implements IRosterService{
	
	
	@Autowired
	private IRosterMapper rosterMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;
	@Autowired
	private IDictionaryBaseMapper dictionaryBaseMapper;
	@Autowired
	private IDictionaryItemMapper dictionaryItemMapper;
	@Autowired
	private IDictionaryBaseService dictionaryBaseServiceImpl;
	@Autowired
	private IDictionaryItemService dictionaryItemServiceImpl;
	@SuppressWarnings("unchecked")
	public  List<Map> expDataList;//导出的数据
	private static final Logger logger = Logger
	.getLogger(RosterAction.class);
	/**导入excel返回消息**/
	private String importMessage;
	private String sign;//标识,导入时如果有错误行数,标识一下
	private String errorNum;//错误行号
	private String fids;//获取页面id值
	/**
	 * 查询页面具体信息
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Pagination queryPage(Pagination page) {
		HttpServletRequest request= ServletActionContext.getRequest();
		int currentPage = PageUtil.strToPage(request.getParameter("page"));
		int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
		Map mapInfo = new HashMap();
		String fnumberString = request.getParameter("fnumber");
		if(fnumberString!=null){
			fnumberString = fnumberString.replace(".00", "");
		}
		String longfnumberString = request.getParameter("longfnumber");
		if(longfnumberString!=null){
			longfnumberString = longfnumberString.replace(".00", "");
		}
		mapInfo.put("fnumber", fnumberString);
		mapInfo.put("longfnumber",longfnumberString);
		mapInfo.put("fname", request.getParameter("fname"));
		mapInfo.put("fentrydate", request.getParameter("fentrydate"));
		mapInfo.put("fentrydate2", request.getParameter("fentrydate2"));
		mapInfo.put("fpersonstatus", request.getParameter("fpersonstatus"));
		mapInfo.put("foldfourunitpage", request.getParameter("foldfourunitpage"));
		mapInfo.put("fourunitpage", request.getParameter("fourunitpage"));
		mapInfo.put("fteam", request.getParameter("fteam"));
		mapInfo.put("fposition", request.getParameter("fposition"));
		mapInfo.put("fleavedate", request.getParameter("fleavedate"));
		mapInfo.put("fleavedate2", request.getParameter("fleavedate2"));
		mapInfo.put("foldfourunit", this.getUserDatePower());
		mapInfo.put("datatype", request.getParameter("datatype"));//是否在职
		
		//查询总行数的方法
		int totalCounts = rosterMapper.getTotalCountsByParams(mapInfo);//查询总行数
		page = new Pagination(currentPage, pageSize, totalCounts);
		// 查询数据集的方法
		Map mapPageInfo = PageUtil.getMap(page);
		mapPageInfo.putAll(mapInfo);
		List list = rosterMapper.queryPageByParams(mapPageInfo);//带分页的数据集合，数据列表用
		
		page.setRows(list);
		return page;
	}
	
	/**
	 * 
	 * 描述：导出数据
	 * 2014-7-14 下午04:00:13 by zhangxin
	 * @version
	 * @param mapinfo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Map> expQuery() {
		HttpServletRequest request= ServletActionContext.getRequest();
		Map mapInfo = new HashMap();
		String fnumberString = request.getParameter("fnumber");
		if(fnumberString!=null){
			fnumberString = fnumberString.replace(".00", "");
		}
		String longfnumberString = request.getParameter("longfnumber");
		if(longfnumberString!=null){
			longfnumberString = longfnumberString.replace(".00", "");
		}
		mapInfo.put("fnumber", fnumberString);
		mapInfo.put("longfnumber",longfnumberString);
		mapInfo.put("fname", request.getParameter("fname"));
		mapInfo.put("fentrydate", request.getParameter("fentrydate"));
		mapInfo.put("fentrydate2", request.getParameter("fentrydate2"));
		mapInfo.put("fpersonstatus", request.getParameter("fpersonstatus"));
		mapInfo.put("foldfourunitpage", request.getParameter("foldfourunitpage"));
		mapInfo.put("fourunitpage", request.getParameter("fourunitpage"));
		mapInfo.put("fteam", request.getParameter("fteam"));
		mapInfo.put("fposition", request.getParameter("fposition"));
		mapInfo.put("fleavedate", request.getParameter("fleavedate"));
		mapInfo.put("fleavedate2", request.getParameter("fleavedate2"));
		mapInfo.put("foldfourunit", this.getUserDatePower());
		mapInfo.put("datatype", request.getParameter("datatype"));//是否在职
		expDataList = rosterMapper.expQuery(mapInfo);//不带分页的数据集合，导出时用
		return expDataList;
	}
	
	
	/**
	 * 查询批量修改数据信息
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Pagination queryManyEditInfoPage(Pagination page) {
		HttpServletRequest request= ServletActionContext.getRequest();
		int currentPage = PageUtil.strToPage(request.getParameter("page"));
		int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
		fids = request.getParameter("fids");
		Map mapInfo = new HashMap();
		mapInfo.put("fids", fids);//要修改的数据id集合
		mapInfo.put("foldfourunit", this.getUserDatePower());
		//查询总行数的方法
		int totalCounts = rosterMapper.getTotalManyEditInfo(mapInfo);//查询总行数
		page = new Pagination(currentPage, pageSize, totalCounts);
		// 查询数据集的方法
		Map mapPageInfo = PageUtil.getMap(page);
		mapPageInfo.putAll(mapInfo);
		List list = rosterMapper.queryPageManyEditInfo(mapPageInfo);//带分页的数据集合，数据列表用
		page.setRows(list);
		return page;
		
	}

	/**
	 * 
	 * 描述：根据typeid查询数据字典信息
	 * 2014-7-7 下午01:23:35 by zhangxin
	 * @version
	 * @param page
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Pagination queryDictionaryData(Pagination page) {
		HttpServletRequest request= ServletActionContext.getRequest();
		int currentPage = PageUtil.strToPage(request.getParameter("page"));
		int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
		String typeid = request.getParameter("typeid");//类型typeid字段
		Integer baseid = dictionaryBaseMapper.selectidbytypeid(typeid);//根据类型关键字获得主表id即从表baseid
		Map mapInfo = new HashMap();
		mapInfo.put("baseid", baseid);
		mapInfo.put("itemname", request.getParameter("itemname"));
		
		//查询总行数的方法
		int totalCounts = dictionaryItemMapper.getTotalCountsByParams(mapInfo);//查询总行数
		page = new Pagination(currentPage, pageSize, totalCounts);
		// 查询数据集的方法
		Map mapPageInfo = PageUtil.getMap(page);
		mapPageInfo.putAll(mapInfo);
		List list = dictionaryItemMapper.queryPageByParams(mapPageInfo);
		page.setRows(list);
		return page;
	}

	

	
	/**
	 * 查询某条数据的具体信息
	 */
	@Override
	public Roster selectByPrimaryKey(Integer id) {
		return rosterMapper.selectByPrimaryKey(id);
	}
	/**
	 * 删除某条花名册信息
	 */
	@Override
	public void deleteRoster(Integer id) {
		rosterMapper.deleteByPrimaryKey(id);
	}
	/**
	 * 插入花名册信息
	 */
	@Override
	public int insertRoster(Roster roster) {
		this.setChoserfinc(roster);//所勾选的理财规划师持证信息
		roster.setDatapower("99999999");
		return rosterMapper.insert(roster);
	}
	/**
	 * 更新花名册信息
	 */
	@Override
	public int updateRoster(Roster roster) {
        this.setChoserfinc(roster);//所勾选的理财规划师持证信息
		return rosterMapper.updateByPrimaryKey(roster);
	}
	/**
	 * 批量更新花名册信息
	 */
	@Override
	public int updateManyEditInfo() {
		HttpServletRequest request= ServletActionContext.getRequest();
		Map<String,Object> mapinfo = new HashMap<String, Object>();
		mapinfo.put("fids", fids);//批量修改id集合
		mapinfo.put("fpersonstatus", request.getParameter("fpersonstatus"));//人员状态
		mapinfo.put("fposition", request.getParameter("fposition"));//岗位
		mapinfo.put("fourunit", request.getParameter("fourunit"));//四级部门
		mapinfo.put("foldfourunitpage", request.getParameter("foldfourunitpage"));//原四级部门
		mapinfo.put("fiveunit", request.getParameter("fiveunit"));//五级部门
		
		return rosterMapper.updateManyEditData(mapinfo);
	}
	/**
	 * 
	 * 描述：新增、修改时保存已经勾选的理财规划师持证
	 * 2014-4-16 上午11:31:04 by zhangxin
	 * @version
	 */
	public void setChoserfinc(Roster roster){
		HttpServletRequest request= ServletActionContext.getRequest();
		String[] choserfincStrings = request.getParameterValues("chosefinaitemid");//已勾选的理财规划师持证
		 if(choserfincStrings == null){
	        	choserfincStrings = new String[1];
	        	choserfincStrings[0] = "559";//否
	        }
	        String fincString = "";
	        String fincText = "";
	        for(int j=0;j<choserfincStrings.length;j++){
	        	fincString = fincString + "," + choserfincStrings[j];
	        }
	        for(int k=0;k<choserfincStrings.length;k++){
	        	String tempvalue = dictionaryItemServiceImpl.findOneDictionaryItem(Integer.parseInt(choserfincStrings[k])).getItemname();
	        	fincText = fincText + "," + tempvalue ;
	        }
	        roster.setFinancialpalncert(fincString.substring(1));//所勾选的理财规划师持证key值
	        roster.setFinancialpalncerttext(fincText.substring(1));//所勾选的理财规划师持证文本值
	}
	
	
	
	/**
	 * 导出excel
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void expExcel()throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("GBK");
		response.setContentType("GBK");
		response.setContentType("application/vnd.ms-excel");
		String fileName = "投三人事花名册信息";// 设置excel文件的名字
		/*if (!fileName.equals("")) {
			String header = "attachment; filename=" + new String((fileName + ".xls").getBytes(), "iso8859-1");
			response.setHeader("Content-Disposition", header);
		}*/
		// 创建响应输出流对象
		OutputStream out = DownloadUtil.getOutputStreamlow(response, fileName);
		//获取角色id
		int roleid = this.getRoleid();
		String[] headers ;
		String[] title ;
		//589对应数据岗角色
		if(roleid == 589){
			 //导出文件需要的类,不包含敏感字段,邮箱、身份证号码、手机号码
			 headers = new String[]{"序号","5位员工编号", "12位员工编号", "姓名", "性别","三级部门", "四级部门","五级部门", "六级部门","工作城市"
					, "岗位","职级","职类","人员状态","大团队经理任职日期", "转签之前是否是HOPE人员成员","招聘渠道","内部推荐人姓名"
					,"上级团队经理","上级大团队经理","上级营业部经理","转正日期", "入职日期", "离职日期","备注","保险从业资格证",
					"基金从业资格证","理财规划师持证","原四级部门","入司日期"};
			 //导出字段的对应方法，不包含敏感字段,邮箱、身份证号码
			 title = new String[]{"","getFnumber","getLongfnumber","getFname","getFgendername",
		    		"getFthreeunitname","getFfourunitname",
		    		"getFfiveunitname","getFsixunitname","getFcityname","getFpositionname"
		    		,"getFpositionlevelname","getFpositiontypename","getFpersonstatusname","getFteamleaderdate",
		    		"getFishopepersonname","getFrecruitmentchannelsname","getFrecommendname","getParentteammanager","getParentbigteammanager","getParentsalemanager",
		    		"getFconfirmdate","getFentrydate","getFleavedate","getFremarks","getInsurancecertname","getFundcertname",
		    		"getFinancialpalncerttext","getFoldfourunitname","getFmobdate"};
		}else{
			 //导出文件需要的类,包含敏感字段
			 headers= new String[]{"序号","5位员工编号", "12位员工编号", "身份证号码", "姓名","手机号码", "性别","三级部门", "四级部门","五级部门", "六级部门","工作城市"
				, "岗位","职级","职类","人员状态","大团队经理任职日期", "转签之前是否是HOPE人员成员","招聘渠道","内部推荐人姓名"
				,"上级团队经理","上级大团队经理","上级营业部经理","转正日期", "入职日期", "离职日期","备注","保险从业资格证",
				"基金从业资格证","理财规划师持证","公司邮箱","原四级部门","入司日期"};
			 //导出字段的对应方法，包含敏感字段
			 title = new String[]{"","getFnumber","getLongfnumber","getFcardnum","getFname","getExt1","getFgendername",
	    		"getFthreeunitname","getFfourunitname",
	    		"getFfiveunitname","getFsixunitname","getFcityname","getFpositionname"
	    		,"getFpositionlevelname","getFpositiontypename","getFpersonstatusname","getFteamleaderdate",
	    		"getFishopepersonname","getFrecruitmentchannelsname","getFrecommendname","getParentteammanager","getParentbigteammanager","getParentsalemanager",
	    		"getFconfirmdate","getFentrydate","getFleavedate","getFremarks","getInsurancecertname","getFundcertname",
	    		"getFinancialpalncerttext","getFemail","getFoldfourunitname","getFmobdate"};
		}
		try {
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFCellStyle cellStyle = wb.createCellStyle();
			HSSFCellStyle cellStyleDate = wb.createCellStyle();
			HSSFSheet sheet = wb.createSheet("投三人事花名册信息");//设置页的名称
			HSSFRow row=sheet.createRow(0);
			row.setHeight((short)500);//设置行高
			HSSFFont fontStyle = wb.createFont();//创建字体
			for (int i = 0; i < headers.length; i++) {
				sheet.setColumnWidth(i, 10000);//设置单元格的宽度
				ExpExcelUtil.createTxtCell(wb, row, i, headers[i],ExpExcelUtil.createSimpleColStyl(cellStyle,wb));//创建表格第一行
			}
			expDataList = this.expQuery();
			if (expDataList != null && !expDataList.isEmpty()){//判断数据是否为空
				Field field=null;
				Object[] objs = expDataList.toArray();//转换为数组
				for (int i = 0; i < objs.length; i++) {
					HSSFRow row1=sheet.createRow(i+1);//创建行，设置从第二行开始
					row1.setHeight((short)300);
					Roster roster = (Roster)objs[i];//获取roster对象
					for (int j = 0; j < title.length; j++) {
						if(title[j]!=""){
						String getMethodName = title[j];
						Class tCls = roster.getClass();
						Method getMethod = tCls.getMethod(getMethodName,//利用反射获取值
								new Class[] {});
						Object value = getMethod.invoke(roster, new Object[] {});
							//设置单元格格式，如果是身份证、员工编号字段保留整数
							if(getMethodName.equals("getFcardnum")||getMethodName.equals("getFnumber")||getMethodName.equals("getLongfnumber")){
								if(value!=null){
									value = value.toString().replace(".00", "");
								}
								ExpExcelUtil.createTxtCell(wb, row1, j, value,ExpExcelUtil.createSimpleBgColStyle(cellStyle,wb,fontStyle));
							//设置单元格格式，如果是日期将单元格变为日期格式
							}else if(getMethodName.equals("getFteamleaderdate")||getMethodName.equals("getFconfirmdate")||getMethodName.equals("getFentrydate")
									||getMethodName.equals("getFleavedate")||getMethodName.equals("getFmobdate")){
								ExpExcelUtil.createTxtCell(wb, row1, j, value,ExpExcelUtil.createSimpleBgColStyle(ExpExcelUtil.createStyleToDate(cellStyleDate, wb),wb,fontStyle));
							//587-投三一般管理员 588-投三人事岗 这两个岗位导出时手机号码有值时显示“是” 没有值时显示“否”，不显示具体号码
							}else if(getMethodName.equals("getExt1")&&(roleid == 587 || roleid == 588)){
								if(value != null && !value.equals("")){
									value = "是";
								}else{
									value = "否";
								}
								ExpExcelUtil.createTxtCell(wb, row1, j, value,ExpExcelUtil.createSimpleBgColStyle(cellStyle,wb,fontStyle));
							}else{
								ExpExcelUtil.createTxtCell(wb, row1, j, value,ExpExcelUtil.createSimpleBgColStyle(cellStyle,wb,fontStyle));
							}
							
						}else{
							ExpExcelUtil.createTxtCell(wb, row1, j, i+1,ExpExcelUtil.createSimpleBgColStyle(cellStyle,wb,fontStyle));
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
		
	}
	
	/**
	 * 导出简单版excel
	 * 简版包含字段：5位员工编号、12位员工编号、姓名、原四级部门、五级部门、六级部门、岗位、职级、职类、人员状态、入职日期、离职日期
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void expExcelSimple()throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("GBK");
		response.setContentType("GBK");
		response.setContentType("application/vnd.ms-excel");
		String fileName = "投三人事花名册信息";// 设置excel文件的名字
		/*if (!fileName.equals("")) {
			String header = "attachment; filename=" + new String((fileName + ".xls").getBytes(), "iso8859-1");
			response.setHeader("Content-Disposition", header);
		}*/
		// 创建响应输出流对象
		OutputStream out = DownloadUtil.getOutputStreamlow(response, fileName);
		int roleid = this.getRoleid();
		String[] headers;
		String[] title;
		//589对应数据岗角色
		if(roleid == 589){
			//导出简版花名册文件需要的类,不包含敏感字段,看不到手机号码敏感字段
			 headers = new String[]{"序号","5位员工编号", "12位员工编号", "姓名","原四级部门","五级部门", "六级部门","岗位","职级","职类","人员状态", "入职日期", "离职日期"};
		     title = new String[]{"","getFnumber","getLongfnumber","getFname","getFoldfourunitname","getFfiveunitname","getFsixunitname","getFpositionname"
		    		 ,"getFpositionlevelname","getFpositiontypename","getFpersonstatusname","getFentrydate","getFleavedate"};
		}else{
			//导出简版花名册文件需要的类,包含敏感字段
			 headers = new String[]{"序号","5位员工编号", "12位员工编号", "姓名","手机号码","原四级部门","五级部门", "六级部门","岗位","职级","职类","人员状态", "入职日期", "离职日期"};
		     title = new String[]{"","getFnumber","getLongfnumber","getFname","getExt1","getFoldfourunitname","getFfiveunitname","getFsixunitname","getFpositionname"
		    		,"getFpositionlevelname","getFpositiontypename","getFpersonstatusname","getFentrydate","getFleavedate"};
		}
		try {
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFCellStyle cellStyle = wb.createCellStyle();
			HSSFCellStyle cellStyleDate = wb.createCellStyle();//设置日期格式时用
			HSSFCellStyle cellStyleNum = wb.createCellStyle();//设置数值格式时用
			HSSFSheet sheet = wb.createSheet("投三人事花名册信息");//设置页的名称
			HSSFRow row=sheet.createRow(0);
			row.setHeight((short)500);//设置行高
			HSSFFont fontStyle = wb.createFont();//创建字体
			for (int i = 0; i < headers.length; i++) {
				sheet.setColumnWidth(i, 10000);//设置单元格的宽度
				ExpExcelUtil.createTxtCell(wb, row, i, headers[i],ExpExcelUtil.createSimpleColStyl(cellStyle,wb));//创建表格第一行
			}
			expDataList = this.expQuery();
			if (expDataList != null && !expDataList.isEmpty()){//判断数据是否为空
				Field field=null;
				Object[] objs = expDataList.toArray();//转换为数组
				for (int i = 0; i < objs.length; i++) {
					HSSFRow row1=sheet.createRow(i+1);//创建行，设置从第二行开始
					row1.setHeight((short)300);
					Roster roster = (Roster)objs[i];//获取roster对象
					for (int j = 0; j < title.length; j++) {
						if(title[j]!=""){
						String getMethodName = title[j];
						Class tCls = roster.getClass();
						Method getMethod = tCls.getMethod(getMethodName,//利用反射获取值
								new Class[] {});
						Object value = getMethod.invoke(roster, new Object[] {});
							if(getMethodName.equals("getFnumber")||getMethodName.equals("getLongfnumber")){
								if(value!=null){
									value = value.toString().replace(".00", "");
								}
								ExpExcelUtil.createTxtCell(wb, row1, j, value,ExpExcelUtil.createSimpleBgColStyle(cellStyle,wb,fontStyle));
							
							//设置单元格格式，如果是日期将单元格变为日期格式
							}else if(getMethodName.equals("getFentrydate")||getMethodName.equals("getFleavedate")){
								ExpExcelUtil.createTxtCell(wb, row1, j, value,ExpExcelUtil.createSimpleBgColStyle( ExpExcelUtil.createStyleToDate(cellStyleDate, wb),wb,fontStyle));
							//587-投三一般管理员 588-投三人事岗 这两个岗位导出时手机号码有值时显示“是” 没有值时显示“否”，不显示具体号码
							}else if(getMethodName.equals("getExt1")&&(roleid == 587 || roleid == 588)){
								if(value != null && !value.equals("")){
									value = "是";
								}else{
									value = "否";
								}
								ExpExcelUtil.createTxtCell(wb, row1, j, value,ExpExcelUtil.createSimpleBgColStyle(cellStyle,wb,fontStyle));
							}else{
								ExpExcelUtil.createTxtCell(wb, row1, j, value,ExpExcelUtil.createSimpleBgColStyle(cellStyle,wb,fontStyle));
							}
						
						}else{
							ExpExcelUtil.createTxtCell(wb, row1, j, i+1,ExpExcelUtil.createSimpleBgColStyle(cellStyle,wb,fontStyle));
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
		
	}
	
	
	/**
	 * 导入人事花名册信息
	 */
	@Override
	public String importExcel(File excelFile, String filePath) throws Exception {
		String jsonResult = "";
		String temp[] = filePath.replaceAll("\\\\", ",").split(",");
		String str = temp[temp.length - 1];
		String directory = "/upload/port";
		String targetDirectory = ServletActionContext.getServletContext()
				.getRealPath(directory);
		// 生成上传的文件对象
		File target = new File(targetDirectory, str);
		// 如果文件已经存在，则删除原有文件
		if (target.exists()) {
			target.delete();
		}
		// 复制file对象，实现上传
		FileUtils.copyFile(excelFile, target);
		ImportExecl poi = new ImportExecl();
		List<List<String>> list = poi.read(target.getPath(), 0);//0表示是excel的第一页
		this.importMessage = "";
		if (0 == list.size())
			this.importMessage += "请检查花名册信息汇总sheet格式或数据</br>";
		this.importRentContracts(list);
		jsonResult = "{\"success\":\"true\", \"message\":\""
				+ this.importMessage + "\"}";
		return jsonResult;
	}
	/**
	 * 验证人事花名册信息是否正确
	 */
	@Override
	public String importExcelValidation(File excelFile, String filePath)
			throws Exception {
		String jsonResult = "";
		String temp[] = filePath.replaceAll("\\\\", ",").split(",");
		String str = temp[temp.length - 1];
		String directory = "/upload/prot";
		String targetDirectory = ServletActionContext.getServletContext()
				.getRealPath(directory);
		// 生成上传的文件对象
		File target = new File(targetDirectory, str);
		// 如果文件已经存在，则删除原有文件
		if (target.exists()) {
			target.delete();
		}
		// 复制file对象，实现上传
		FileUtils.copyFile(excelFile, target);
		ImportExecl poi = new ImportExecl();
		List<List<String>> list = poi.read(target.getPath(), 0);//0表示是excel的第一页
		String num="";
		 Pattern pattern = Pattern.compile("[0-9]*.[0-9]{0,2}"); //是否是数字
		 Pattern patternEmail = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@creditease.cn$");//邮箱格式是否正确
		for (int i = 1; i < list.size(); i++) {
			List<String> cellList = list.get(i);
				Matcher isEmail = patternEmail.matcher(cellList.get(29).trim());//邮箱
			//	Matcher isNum = pattern.matcher(cellList.get(5).trim());//年龄
				if ("".equals(cellList.get(2))//12位员工编号必填项，不能为空
						|| "".equals(cellList.get(4))//姓名必填项，不能为空
						|| !isEmail.matches()// 邮箱是否正确
						|| false == dictionaryBaseServiceImpl.isItemHave("gender", cellList.get(5).trim())//数据字典中 gender--性别
						|| false == dictionaryBaseServiceImpl.isItemHave("pstatus", cellList.get(14).trim())//数据字典中pstatus--人员状态
						|| false == dictionaryBaseServiceImpl.isItemHave("city", cellList.get(10).trim())// 数据字典中city--工作城市
						|| false == dictionaryBaseServiceImpl.isItemHave("threeunit", cellList.get(6).trim())//数据字典中 threeunit--三级部门
						|| false == dictionaryBaseServiceImpl.isItemHave("fourunit", cellList.get(7).trim())//数据字典中 fourunit--四级部门
						|| false == dictionaryBaseServiceImpl.isItemHave("fiveunit", cellList.get(8).trim())//数据字典中 fiveunit--五级部门
						|| false == dictionaryBaseServiceImpl.isItemHave("sixunit", cellList.get(9).trim())//数据字典中sixunit--六级部门
						|| false == dictionaryBaseServiceImpl.isItemHave("fpositiontype", cellList.get(13).trim())//数据字典中 fpositiontype--职类
						|| false == dictionaryBaseServiceImpl.isItemHave("fposition", cellList.get(11).trim())//数据字典中 fposition--岗位
						|| false == dictionaryBaseServiceImpl.isItemHave("fpositionlevel", cellList.get(12).trim())//数据字典中 fpositionlevel--职级
						|| false == dictionaryBaseServiceImpl.isItemHave("fishopeperson", cellList.get(16).trim())//数据字典中 fishopeperson--转签之前是否是hope人员成员
						|| false == dictionaryBaseServiceImpl.isItemHave("frecruitmentchannels", cellList.get(17).trim())//数据字典中 frecruitmentchannels--招聘渠道
						|| false == dictionaryBaseServiceImpl.isItemHave("insurancecert", cellList.get(26).trim())//数据字典中 insurancecert--保险从业资格证
						|| false == dictionaryBaseServiceImpl.isItemHave("fundcert", cellList.get(27).trim())//数据字典中 fundcert--基金从业资格证
						|| false == dictionaryBaseServiceImpl.isItemHave("oldfourunit", cellList.get(30).trim())//数据字典中 oldfourunit--原四级部门
						
						) {
					num+=i+",";
				}
			}
		this.importMessage = "";
		this.sign = "";
		if(!"".equals(num)){
			errorNum = num;
			this.importMessage="数据异常,请查看错误行号并进行修改。";
			this.sign = "1";
		}else{
			this.importMessage="是否继续导入花名册信息？";
		}
		if (0 == list.size())
			this.importMessage += "请检查花名册信息汇总sheet格式或数据</br>";
		jsonResult = "{\"success\":\"true\", \"message\":\""
				+ this.importMessage+ "\", \"sign\":\""+ this.sign + "\"}";
		return jsonResult;
	}
	
	/**
	 * 
	 * 描述：将导入数据存在数据库中
	 * 2014-3-31 下午04:53:40 by zhangxin
	 * @version
	 * @param list
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private void importRentContracts(List<List<String>> list) throws Exception {
		 Pattern pattern = Pattern.compile("[0-9]*.[0-9]{0,2}"); //是否是数字
		 Pattern patternEmail = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@creditease.cn$");//邮箱格式是否正确
		if (list != null) {
			if (list.size() > 1) {
				List<Roster> rosterList=new ArrayList<Roster>();
				List<String> titlesList = list.get(0);
				int[] titleNum = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};// 表头列对应的列number
				String num = "";
				for (int i = 1; i < list.size(); i++) {
					List<String> cellList = list.get(i);
				
                    	
						String longFnumber = cellList.get(titleNum[1]).trim();//12位员工编号
						if(longFnumber!=null){
							 longFnumber = longFnumber.replace(".00","");
						}
                    	Map tempMap = new HashMap();
                    	tempMap.put("longfnumber", longFnumber);
                    	//根据12位员工编号查询数据库中是否已经存在该条数据
                    	List<Map> rosterMapList = rosterMapper.selectDatabyParm(tempMap); 
                    	
						Roster roster = new Roster();
						String fnumberString = cellList.get(titleNum[0]).trim();
						if(fnumberString!=null){
							fnumberString = fnumberString.replace(".00","");
						}
						roster.setFnumber(fnumberString);//5位员工编号
						roster.setLongfnumber(longFnumber);//12位员工编号
						String cardString = cellList.get(titleNum[2]).trim();
						if(cardString!=null){
							cardString = cardString.replace(".00","");
						}
						roster.setFcardnum(cardString);//身份证号码
						roster.setFname(cellList.get(titleNum[3]).trim());//姓名
						Integer genderValue = dictionaryBaseServiceImpl.getItemKey("gender", cellList.get(titleNum[4]).trim());//数据字典中 gender--性别
						roster.setFgender(genderValue.toString());//性别
						Integer threeunitValue = dictionaryBaseServiceImpl.getItemKey("threeunit", cellList.get(titleNum[5]).trim());//数据字典中 threeunit--三级部门
						roster.setFthreeunit(threeunitValue.toString());//三级部门
						Integer fourunitValue = dictionaryBaseServiceImpl.getItemKey("fourunit", cellList.get(titleNum[6]).trim());//数据字典中 fourunit--四级部门
						roster.setFfourunit(fourunitValue.toString());//四级部门
						String fiveunitString =  cellList.get(titleNum[7]).trim();
						Integer fiveunitValue = dictionaryBaseServiceImpl.getItemKey("fiveunit",fiveunitString);//数据字典中 fiveunit--五级部门
						roster.setFfiveunit(fiveunitValue.toString());//五级部门
						Integer sixunitValue = dictionaryBaseServiceImpl.getItemKey("sixunit", cellList.get(titleNum[8]).trim());//数据字典中 sixunit--六级部门
						roster.setFsixunit(sixunitValue.toString());//六级部门
						Integer cityValue = dictionaryBaseServiceImpl.getItemKey("city", cellList.get(titleNum[9]).trim());// 数据字典中city--工作城市
						roster.setFcity(cityValue.toString());//工作城市
						Integer positionValue = dictionaryBaseServiceImpl.getItemKey("fposition", cellList.get(titleNum[10]).trim());//数据字典中 fposition--岗位
						roster.setFposition(positionValue.toString());//岗位
						Integer positionlevelValue = dictionaryBaseServiceImpl.getItemKey("fpositionlevel", cellList.get(titleNum[11]).trim());//数据字典中 fpositionlevel--职级
						roster.setFpositionlevel(positionlevelValue.toString());//职级
						Integer positiontypeValue = dictionaryBaseServiceImpl.getItemKey("fpositiontype", cellList.get(titleNum[12]).trim());//数据字典中 fpositiontype--职类
						roster.setFpositiontype(positiontypeValue.toString());//职类
						Integer perosonstatusValue = dictionaryBaseServiceImpl.getItemKey("pstatus", cellList.get(titleNum[13]).trim());// 数据字典中pstatus--人员状态
						roster.setFpersonstatus(perosonstatusValue.toString());//人员状态
						roster.setFteamleaderdate(Utils.toDate(cellList.get(titleNum[14]).trim()));//大团队经理任职日期
						Integer ishopepersonValue = dictionaryBaseServiceImpl.getItemKey("fishopeperson", cellList.get(titleNum[15]).trim());//数据字典中 fishopeperson--转签之前是否是hope人员成员
						roster.setFishopeperson(ishopepersonValue.toString());//转签之前是否是hope人员成员
						Integer recruValue = dictionaryBaseServiceImpl.getItemKey("frecruitmentchannels", cellList.get(titleNum[16]).trim());//数据字典中 frecruitmentchannels--招聘渠道
						roster.setFrecruitmentchannels(recruValue.toString());//招聘渠道
						roster.setFrecommendname(cellList.get(titleNum[17]).trim());//内部推荐人姓名
						roster.setParentteammanager(cellList.get(titleNum[18]).trim());//上级团队经理
						roster.setParentbigteammanager(cellList.get(titleNum[19]).trim());//上级大团队经理
						roster.setParentsalemanager(cellList.get(titleNum[20]).trim());//上级营销部经理
						roster.setFconfirmdate(Utils.toDate(cellList.get(titleNum[21]).trim()));//转正日期
						roster.setFentrydate(Utils.toDate(cellList.get(titleNum[22]).trim()));//入职日期
						roster.setFleavedate(Utils.toDate(cellList.get(titleNum[23]).trim()));//离职日期
						roster.setFremarks(cellList.get(titleNum[24]).trim());//备注
						Integer insurancecertValue = dictionaryBaseServiceImpl.getItemKey("insurancecert", cellList.get(titleNum[25]).trim());//数据字典中 insurancecert--保险从业资格证
						roster.setInsurancecert(insurancecertValue.toString());//保险从业资格证
						Integer fundcertValue = dictionaryBaseServiceImpl.getItemKey("fundcert", cellList.get(titleNum[26]).trim());//数据字典中 fundcert--基金从业资格证
						roster.setFundcert(fundcertValue.toString());//基金从业资格证
						String tempfinatext = cellList.get(titleNum[27]).trim();
						roster.setFinancialpalncerttext(tempfinatext);//理财规划师持证文本值,加这个字段方便导入、导出以及查看页面
						//理财规划师持证导入时导入的文本值，为了编辑页面打开时是勾选状态，查出value值保存起来
						String[] finatextString = tempfinatext.split(","); 
						String finatextvalue = "";
						for(int k=0;k<finatextString.length;k++){
							finatextvalue = finatextvalue + "," + dictionaryBaseServiceImpl.getItemKey("fundcert", finatextString[k]);
						}
						roster.setFinancialpalncert(finatextvalue.substring(1));//理财规划师持证value值
						roster.setFemail(cellList.get(titleNum[28]).trim());//电子邮箱
						roster.setDatapower("99999999");//数据权限  默认值 看不到任何信息
						String foldfourunitstring = cellList.get(titleNum[29]).trim();
						Integer foldfourunitValue = dictionaryBaseServiceImpl.getItemKey("oldfourunit",foldfourunitstring);//数据字典中 oldfourunit--原四级部门
						roster.setFoldfourunit(foldfourunitValue.toString());//原四级部门
						roster.setFmobdate(Utils.toDate(cellList.get(titleNum[30]).trim()));//入司日期
					
						//团队
						roster.setFteam(foldfourunitstring+"_"+fiveunitString);
						
							if(null != rosterMapList && rosterMapList.size()>0){
								Integer fid = Integer.parseInt(rosterMapList.get(0).get("FID").toString());
								roster.setFid(fid);
								rosterMapper.updateforimport(roster);//12位员工编号相同时，数据执行更新操作
							}else{
								rosterList.add(roster);//循环将数据存入到list中
							}
				}			
							if(null != rosterList && rosterList.size()>0 ){
								rosterMapper.InsertAll(rosterList);//将list中的数据批量导入到数据库中
							}
				 this.importMessage ="花名册信息全部导入</br>";
				}else{
					this.importMessage="没有可导入的花名册信息!";
				}
		}
	}
	/**
	 * 
	 * 描述：根据表头列名称获取列
	 * 
	 * @version
	 * @param list表头列集合
	 * @return result返回对应表头列的cellNumber
	 */
	private int[] getCellNumByName(List<String> list) {
		String[] titles = { "5位员工编号", "12位员工编号", "身份证号码", "姓名", "性别","三级部门", "四级部门","五级部门", "六级部门","工作城市"
				, "岗位","职级","职类","人员状态","大团队经理任职日期", "转签之前是否是HOPE人员成员","招聘渠道","内部推荐人姓名"
				,"上级团队经理","上级大团队经理","上级营业部经理","转正日期", "入职日期", "离职日期","备注","保险从业资格证",
				"基金从业资格证","理财规划师持证","公司邮箱","原四级部门","入司日期"};
		int[] result = new int[titles.length];
		for (int j = 0; j < titles.length; j++) {
			for (int i = 0; i < list.size(); i++) {
				if (titles[j].equals(list.get(i))) {
					result[j] = i;
					break;
				}
			}
		}
		return result;
	}
	
	/**
	 * 
	 * 描述：获得用户所负责的营业部信息，根据原四级部门字段进行划分
	 * 2014-4-7 下午12:36:31 by Administrator
	 * @version
	 * @return 所负责营业部字段信息
	 */
	private String getUserDatePower(){
		HttpServletRequest request= ServletActionContext.getRequest();
		//获得当前登录用户
		User user = (User)request.getSession().getAttribute("user");
		//获得当前登录用户名称
		String username = user.getUsername();
		//获得登录用户id值
		int userid = user.getId().intValue();
		//获得用户的角色
		int roleid = userRoleMapper.userroleid(userid);
		String userDataPower = "";
		//如果是投三超级管理员(568)，可以看到所有用户的信息
		if(roleid == 568){
			userDataPower = "";
		}else{//其他角色的用户根据所负责的营业部信息查看数据
		//根据登录名在投三数据中查找该登录用户信息
			Roster rosterInfo = rosterMapper.selectRosterByEmail(username);
			if(rosterInfo != null){
				//获得数据权限
				userDataPower = rosterInfo.getDatapower();
			}else{
				userDataPower = "-1";
			}
		}
		return userDataPower;
	}
	/**
	 * 
	 * 描述：返回角色id
	 * 2014-6-17 下午05:23:50 by zhangxin
	 * @version
	 * @return int数值
	 */
	private int getRoleid(){
		HttpServletRequest request= ServletActionContext.getRequest();
		//获得当前登录用户
		User user = (User)request.getSession().getAttribute("user");
		//获得当前登录用户名称
		String username = user.getUsername();
		//获得登录用户id值
		int userid = user.getId().intValue();
		//获得用户的角色
		int roleid = userRoleMapper.userroleid(userid);
		return roleid;
	}
	
	/**
	 * 
	 * 描述：查看导入数据时错误的数据行数
	 * 2014-4-11 下午02:10:37 by zhangxin
	 * @version
	 */
	@Override
	public void viewErrorData() {
		HttpServletRequest request= ServletActionContext.getRequest();
		request.setAttribute("errorNum", errorNum.substring(0,errorNum.length()-1));
	}

	/**
	 * 查询12位员工编号是否已经存在
	 * 
	 */
	@Override
	public boolean ifNumHasExists() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		return rosterMapper.findNumExists(Utils.setParams(
				"t_roster_info", "longfnumber", request.getParameter("columnValue"))) == 0 ? false : true;
	}

	/**
	 * 判断邮箱是否已经存在
	 */
	@Override
	public boolean ifEmailHasExists() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		return rosterMapper.findNumExists(Utils.setParams(
				"t_roster_info", "femail", request.getParameter("columnValue"))) == 0 ? false : true;
	}

}




