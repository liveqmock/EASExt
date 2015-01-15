package com.creditease.eas.adminipurc.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;

import com.creditease.eas.admin.bean.User;
import com.creditease.eas.admin.dao.RoleMapper;
import com.creditease.eas.adminipurc.action.AdminiPurcAction;
import com.creditease.eas.adminipurc.bean.AdminContractInfo;
import com.creditease.eas.adminipurc.dao.AdminContractInfoMapper;
import com.creditease.eas.adminipurc.dao.CommonPortinfoMapper;
import com.creditease.eas.adminipurc.service.IAdminiPurcContractService;
import com.creditease.eas.adminipurc.util.AuthorUtil;
import com.creditease.eas.adminipurc.util.ImportContractInfoUtil;
import com.creditease.eas.adminipurc.util.ImportContractInfoValidate;
import com.creditease.eas.adminipurc.util.ImportExcel;
import com.creditease.eas.adminipurc.vo.AdminContractInfoForExcelVo;
import com.creditease.eas.adminipurc.vo.AdminContractInfoForExportExcelVo;
import com.creditease.eas.adminipurc.vo.AdminContractInfoForValidVo;
import com.creditease.eas.dictionary.dao.IDictionaryItemMapper;
import com.creditease.eas.dictionary.service.IDictionaryBaseService;
import com.creditease.eas.util.PageUtil;
import com.creditease.eas.util.Pagination;
import com.creditease.eas.util.excel.ExcelTitleValidate;
import com.creditease.eas.util.port.ImportExecl;
@Service
public class AdminiPurcContractServiceImpl implements IAdminiPurcContractService{
	/********************************保存系统日志信息***************************************/
	private static Logger logger = Logger.getLogger(AdminiPurcContractServiceImpl.class);
	@Resource
	private AdminContractInfoMapper adminContractInfoMapper;
	@Resource
	private IDictionaryBaseService dictionaryBaseService;
	@Resource
	private CommonPortinfoMapper commonPortinfoMapper;
	@Resource
	private RoleMapper roleMapper;
//	private 
	public AdminContractInfoMapper getAdminContractInfoMapper() {
		return adminContractInfoMapper;
	}

	public void setAdminContractInfoMapper(
			AdminContractInfoMapper adminContractInfoMapper) {
		this.adminContractInfoMapper = adminContractInfoMapper;
	}
	public IDictionaryBaseService getDictionaryBaseService() {
		return dictionaryBaseService;
	}

	public void setDictionaryBaseService(
			IDictionaryBaseService dictionaryBaseService) {
		this.dictionaryBaseService = dictionaryBaseService;
	}

	public CommonPortinfoMapper getCommonPortinfoMapper() {
		return commonPortinfoMapper;
	}

	public void setCommonPortinfoMapper(CommonPortinfoMapper commonPortinfoMapper) {
		this.commonPortinfoMapper = commonPortinfoMapper;
	}

	public RoleMapper getRoleMapper() {
		return roleMapper;
	}

	public void setRoleMapper(RoleMapper roleMapper) {
		this.roleMapper = roleMapper;
	}

	@Override
	public List<AdminContractInfoForExcelVo> importContractExcel(File excelFile, String filePath)
			throws Exception {
		List<AdminContractInfoForExcelVo> listValid = null;
		try{
			String temp[] = filePath.replaceAll("\\\\",",").split(",");
			String str = temp[temp.length-1];
			String directory = "/upload/port";  
	        String targetDirectory = ServletActionContext.getServletContext().getRealPath(directory);  
	        //生成上传的文件对象  
	        File target = new File(targetDirectory,str);//创建文件时，增加时间参数
	       //如果文件已经存在，则删除原有文件  
	        if(target.exists()){  
	            target.delete();  
	        }  
	        //复制file对象，实现上传  
	    	FileUtils.copyFile(excelFile, target);  
	        ImportExecl poi = new ImportExecl();
	        //人员信息源数据
	        List<List<String>> list = poi.read(target.getPath(),0);
	        //
	        if(null == list || list.isEmpty() || list.size()<=1){ 
	        	AdminiPurcAction.message += "请检查合同信息汇总sheet格式或数据</br>";
	        }else if(list.size()>1){
	        	//验证表头
	        	boolean va = ExcelTitleValidate.titleValid(list.get(1), ImportExcel.CONTRACTINFOTITLES);
	        	if(va == false){
	        		AdminiPurcAction.message += "导入的合同信息格式不正确</br>";
	        	}else{
        		   //导入合同信息
    	        	ImportContractInfoValidate<AdminContractInfoForExcelVo> iev = new ImportContractInfoValidate<AdminContractInfoForExcelVo>(dictionaryBaseService);//加对数据字典的验证
    	        	listValid = iev.excelValidateIncludeSpecial(2,list,AdminContractInfoForValidVo.class,AdminContractInfoForExcelVo.class,ImportExcel.EXCLUDECONTRACTINFOTITLESFORVALID,ImportExcel.SPECIALCONTRACTINFOFORVALID);//查询有问题的数据信息
    	        	if(listValid == null||listValid.isEmpty()||listValid.size() == 0){//验证通过
    			        //导入接口人信息的的Util类
    			        ImportContractInfoUtil ipu = new ImportContractInfoUtil();
    			        //查询公司和部门对应的编码
    			        ipu.importContractInfos(list,adminContractInfoMapper,dictionaryBaseService,commonPortinfoMapper);
    			    	logger.info("导入合同信息成功");
    	        	}else{
    	        		 //房屋合同信息源数据
    	    	    	logger.info("数据验证失败");
    	        	}
	        	}
	        }
	        
		}catch(Exception ex){
			ex.printStackTrace();
			logger.info("导入合同信息失败" + ex.getMessage());
		}
		return listValid;
	}
	
	@Override
	public Pagination queryPage(Pagination page) throws Exception {
		try{
			HttpServletRequest request= ServletActionContext.getRequest();
			int currentPage = PageUtil.strToPage(request.getParameter("page"));
			int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
			
			//设置Map的参数值
			Map map = setMapValue();
			//查询总行数的方法
			int totalCounts = adminContractInfoMapper.getTotalCountsByParams(map);
			page = new Pagination(currentPage,pageSize,totalCounts); 
			//查询数据集的方法
			map.put("startRow", page.getStartPageIndex());
			map.put("endRow", page.getEndPageIndex());
			//调用的是返回Map值的方法
			List list = adminContractInfoMapper.queryPageMapByParams(map);
			page.setRows(list);
		}catch(Exception ex){
			ex.printStackTrace();
			logger.info("查询合同信息失败：" +ex.getMessage());
		}
		return page;
	}

	/* (non-Javadoc)
	 * @see com.creditease.eas.adminipurc.service.IAdminiPurcContractService#delete(java.lang.Long)
	 */
	@Override
	public int delete(Long fid) throws Exception {
		int result = 0;
		try{
			result = adminContractInfoMapper.physicalDeletion(fid);
			logger.info("删除行政采购接口人成功");
		}catch(Exception ex){
			ex.printStackTrace();
			logger.info("删除行政采购接口人失败");
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.creditease.eas.adminipurc.service.IAdminiPurcContractService#findContractInfoById(java.lang.Long)
	 */
	@Override
	public AdminContractInfo findContractInfoById(Long fid) throws Exception {
		// TODO Auto-generated method stub
		return adminContractInfoMapper.selectByPrimaryKey(fid);
	}

	/* (non-Javadoc)
	 * @see com.creditease.eas.adminipurc.service.IAdminiPurcContractService#update(com.creditease.eas.adminipurc.bean.AdminContractInfo, java.lang.String)
	 */
	@Override
	public int update(AdminContractInfo adminContractInfo)
			throws Exception {
		// TODO Auto-generated method stub
		return adminContractInfoMapper.update(adminContractInfo);
	}
	/**
	 * 
	 * 描述：设置公共的参数
	 * 2012-12-31 下午06:08:52 by ygq
	 * @version
	 * @param request
	 * @param map
	 * @return
	 */
	private Map setMapValue() throws Exception{
		//根据用户邮箱，查询人员相关的权限信息
		String condition = AuthorUtil.getAuthority(roleMapper,commonPortinfoMapper,"fnumber",false);
		HttpServletRequest request= ServletActionContext.getRequest();
		Map map = HashMap.class.newInstance();
		map.put("condition", condition);
		map.put("fnumber", request.getParameter("fnumber"));
		map.put("forgname",request.getParameter("forgname"));
		
		map.put("fcontracttypeid",request.getParameter("fcontracttypeid"));//合同类别
		//开始日期和结束日期
//		Date fbegindate = StringUtil.strToDate(request.getParameter("fbegindate"));
		map.put("fbegindate",request.getParameter("fbegindate"));
//		Date fenddate = StringUtil.strToDate(request.getParameter("fenddate"));
		map.put("fenddate",request.getParameter("fenddate"));
		
		map.put("fcity",request.getParameter("fcity"));
		map.put("fcontractStatus", request.getParameter("fcontractStatus"));
		map.put("flastcostcenter",request.getParameter("flastcostcenter"));
		String fStatus = request.getParameter("fStatus");
		map.put("fStatus",fStatus);
		return map;
	}
	/**
	 * 描述：设置公共的参数
	* @Title: setMapValueForExport
	*created at 2014-6-4 下午06:33:19 by ygq  
	* @return
	* @throws Exception
	* @return Map
	 */
	private Map setMapValueForExport() throws Exception{
		HttpServletRequest request= ServletActionContext.getRequest();
		Map map = HashMap.class.newInstance();
		User user = (User) request.getSession().getAttribute("user");
		map.put("email",user.getEmail());
		//根据用户邮箱，查询人员相关的权限信息
		String condition = AuthorUtil.getAuthority(roleMapper,commonPortinfoMapper,"fnumber",false);
		map.put("condition", condition);
		map.put("fnumber", request.getParameter("fnumber"));
		map.put("forgname",request.getParameter("forgname"));
		
		map.put("fcontracttypeid",request.getParameter("fcontracttypeid"));//合同类别
		//开始日期和结束日期
//		Date fbegindate = StringUtil.strToDate(request.getParameter("fbegindate"));
		map.put("fbegindate",request.getParameter("fbegindate"));
//		Date fenddate = StringUtil.strToDate(request.getParameter("fenddate"));
		map.put("fenddate",request.getParameter("fenddate"));
		
		map.put("fcity",request.getParameter("fcity"));
		map.put("fcontractStatus", request.getParameter("fcontractStatus"));
		map.put("flastcostcenter",request.getParameter("flastcostcenter"));
		return map;
	}
	@Override
	public List<AdminContractInfoForExportExcelVo> queryDatasetByParams() throws Exception{
		List<AdminContractInfoForExportExcelVo> list = null;
		try{
			//查询数据集的方法
			Map map = setMapValueForExport();
			list = adminContractInfoMapper.queryDatasetByParams(map);
		}catch(Exception ex){
			ex.printStackTrace();
			logger.info("导出excel时，查询合同信息失败：" +ex.getMessage());
		}
		return list;
	}
}
