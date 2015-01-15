package com.creditease.eas.adminipurc.service.impl;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;

import com.creditease.eas.admin.bean.AdminUserPort;
import com.creditease.eas.admin.bean.User;
import com.creditease.eas.admin.bean.UserRole;
import com.creditease.eas.admin.dao.AdminUserPortMapper;
import com.creditease.eas.admin.dao.RoleMapper;
import com.creditease.eas.admin.dao.UserRoleMapper;
import com.creditease.eas.admin.service.UserAuthorityService;
import com.creditease.eas.admin.service.UserRoleService;
import com.creditease.eas.admin.service.UserService;
import com.creditease.eas.adminipurc.action.AdminiPurcAction;
import com.creditease.eas.adminipurc.dao.AdminContractInfoMapper;
import com.creditease.eas.adminipurc.dao.CommonPortinfoMapper;
import com.creditease.eas.adminipurc.service.IAdminiPurcPortService;
import com.creditease.eas.adminipurc.util.AuthorUtil;
import com.creditease.eas.adminipurc.util.ImportExcel;
import com.creditease.eas.adminipurc.util.ImportPortExcelValidate;
import com.creditease.eas.adminipurc.util.ImportPortInfoUtil;
import com.creditease.eas.adminipurc.vo.CommonPortinfoForExcelVo;
import com.creditease.eas.dictionary.dao.IDictionaryItemMapper;
import com.creditease.eas.dictionary.service.IDictionaryBaseService;
import com.creditease.eas.util.ExportExcel;
import com.creditease.eas.util.PageUtil;
import com.creditease.eas.util.Pagination;
import com.creditease.eas.util.UserUtil;
import com.creditease.eas.util.commbean.CommonPortinfo;
import com.creditease.eas.util.excel.ExcelTitleValidate;
import com.creditease.eas.util.port.ImportExecl;
import com.creditease.eas.warn.bean.WaringDetailView;
@Service
public class AdminiPurcPortServiceImpl implements IAdminiPurcPortService{
	/********************************保存系统日志信息***************************************/
	private static Logger logger = Logger.getLogger(AdminiPurcPortServiceImpl.class);
	@Resource
	private CommonPortinfoMapper commonPortinfoMapper;
	@Resource
	private IDictionaryBaseService dictionaryBaseService;
	/**用户service接口**/
	@Resource
	private UserAuthorityService userService;
	@Resource
	private UserRoleService userRoleService;
	@Resource
	private RoleMapper roleMapper;
	@Resource
	private AdminUserPortMapper adminUserPortMapper;
	@Resource
	private AdminContractInfoMapper adminContractInfoMapper;
	
	public AdminContractInfoMapper getAdminContractInfoMapper() {
		return adminContractInfoMapper;
	}

	public void setAdminContractInfoMapper(
			AdminContractInfoMapper adminContractInfoMapper) {
		this.adminContractInfoMapper = adminContractInfoMapper;
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
	public IDictionaryBaseService getDictionaryBaseService() {
		return dictionaryBaseService;
	}

	public void setDictionaryBaseService(
			IDictionaryBaseService dictionaryBaseService) {
		this.dictionaryBaseService = dictionaryBaseService;
	}
	public UserAuthorityService getUserService() {
		return userService;
	}
	public void setUserService(UserAuthorityService userService) {
		this.userService = userService;
	}

	public AdminUserPortMapper getAdminUserPortMapper() {
		return adminUserPortMapper;
	}
	public void setAdminUserPortMapper(AdminUserPortMapper adminUserPortMapper) {
		this.adminUserPortMapper = adminUserPortMapper;
	}
	@Override
	public List<CommonPortinfoForExcelVo> importPortExcel(File excelFile, String filePath) throws Exception {
		List<CommonPortinfoForExcelVo> listValid = null;
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
	        if(null == list || list.isEmpty() || list.size()<=1){ 
	        	AdminiPurcAction.message = "请检查接口人信息汇总sheet格式或数据！";//为空时的数据的验证
	        }else{
	        	//验证表头
	        	boolean va = ExcelTitleValidate.titleValid(list.get(1), ImportExcel.PORTINFOTITLES);
	        	if(va == false){
	        		AdminiPurcAction.message += "导入的接口人信息格式不正确";
	        	}else{//验证通过，导入接口人信息
	        		 
    	        	ImportPortExcelValidate<CommonPortinfoForExcelVo> iev = new ImportPortExcelValidate<CommonPortinfoForExcelVo>(dictionaryBaseService);
    	        	listValid = iev.excelValidateIncludeSpecial(2,list,CommonPortinfoForExcelVo.class,CommonPortinfoForExcelVo.class,null);//查询有问题的数据信息
    	        	if(listValid == null||listValid.isEmpty()||listValid.size() == 0){//验证通过
    		        	 //导入接口人信息的的Util类
    			        ImportPortInfoUtil ipu = new ImportPortInfoUtil(roleMapper,commonPortinfoMapper,adminUserPortMapper);
    			        //查询公司和部门对应的编码
    			        ipu.importPortInfosAndUsers(list,userService,userRoleService,dictionaryBaseService);
    			    	logger.info("导入接口人信息验证成功");
    	        	}
	        	}
	        }
	    
		}catch(Exception ex){
			ex.printStackTrace();
			logger.info("导入接口人信息验证失败" + ex.getMessage());
		}
		return listValid;
	}
//	@Override
//	public String importPortExcel(File excelFile, String filePath) throws Exception {
//		String jsonResult = "";
//		List<Map<String,String>> listValid = importPortExcelValid(excelFile,filePath);//导入接口人信息验证
//		if(listValid != null && listValid.size()>0){//如果验证数据有问题，则写入到excel中
//			System.out.println("导入的数据有问题");
//		}else{//导入接口人信息
//			//继续导入接口人信息
//		}
//		String importMessage = "";
//		return jsonResult;
//	}
	/* (non-Javadoc)
	 * @see com.creditease.eas.adminipurc.service.IAdminiProcPortService#queryPage(com.creditease.eas.util.Pagination)
	 */
	@Override
	public Pagination queryPage(Pagination page) throws Exception {
		try{
			HttpServletRequest request= ServletActionContext.getRequest();
			int currentPage = PageUtil.strToPage(request.getParameter("page"));
			int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
			Map map = HashMap.class.newInstance();
			
//			map.put("email",user.getEmail());
			//根据用户邮箱，查询人员相关的权限信息
			String condition = AuthorUtil.getAuthority(roleMapper,commonPortinfoMapper,"fcontractnumber",true);
			map.put("condition", condition);
			
			map.put("orgname",request.getParameter("orgname"));
			map.put("city",request.getParameter("city"));
			map.put("officeadd",request.getParameter("officeadd"));
			map.put("lastcostcenter",request.getParameter("lastcostcenter"));
			String isport =  request.getParameter("isport");
			if(null != isport &&!"".equals(isport)){
				map.put("isport",isport.equals("1")?"是":"否");
			}
			//查询总行数的方法
			int totalCounts = commonPortinfoMapper.getTotalCountsByParams(map);
			page = new Pagination(currentPage,pageSize,totalCounts); 
			//查询数据集的方法
			Map map2 = PageUtil.getMap(page);
			map2.putAll(map);
			
			List list = commonPortinfoMapper.queryPageByParams(map2);
			page.setRows(list);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return page;
	}
	/* (non-Javadoc)
	 * @see com.creditease.eas.adminipurc.service.IAdminiPurcPortService#findPortinfoById(java.lang.Long)
	 */
	@Override
	public CommonPortinfo findPortinfoById(Long fid) throws Exception {
		return commonPortinfoMapper.selectByPrimaryKey(fid);
	}
	/* (non-Javadoc)
	 * @see com.creditease.eas.adminipurc.service.IAdminiPurcPortService#update(com.creditease.eas.util.commbean.CommonPortinfo)
	 */
	@Override
	public int update(CommonPortinfo portInfo,String fportEmailValid) throws Exception {
		int result = 0;
		try{
			//更新接口人信息,用户原来的“接口人”的权限被更新到新的用户 
			result =  commonPortinfoMapper.update(portInfo);
			int roleId = 0;//初始化角色id
			long userid = 0;//初始化用户id
			if(fportEmailValid != portInfo.getFportEmail()){//如果修改用户邮箱，则需要重新创建用户
				String portEmail =  portInfo.getFportEmail();
				 /**
				  * 如果更新的是总接口人的信息
				  * 则需要批量更新下对应的接口人和合同的信息
			     * **/
				if(("是").equals(portInfo.getFisAllPort())){
					//批量更新接口人的扩展字段的信息
					Map map = new HashMap();
					map.put("forgName", portInfo.getForgName());
					map.put("fportOldEmail", fportEmailValid);
					map.put("fportNewEmail", portEmail);
					commonPortinfoMapper.updateFext1ByEmail(map);
					//批量更新合同的扩展字段的信息（统一使用邮箱去控制权限）
					adminContractInfoMapper.updateFext1ByEmail(map);
				}
				//查询新的用户信息
				int number = userService.selectedIfUserExists1(portInfo.getFportEmail());//判断用户是否存在，间接的判断是否修改了邮箱
				User sessionUser = UserUtil.getUser();
		    	//根据用户名，查询旧的用户信息
		    	User userCurrent = userService.selectOneUserByUsername(fportEmailValid);
				if(number==0){//如果用户不存在,创建用户相关的信息
			        User userinfo = User.class.newInstance();
			        userinfo.setUsername(portEmail);//用户名暂时为邮箱名字
			        userinfo.setPassword(portEmail);//密码暂时为邮箱名字
			        userinfo.setEmail(portEmail);//邮箱为邮箱名字
			        userinfo.setFcreator(sessionUser.getId());
			        userinfo.setCreatedate(new Date());//创建时间
			        userinfo.setFlastupdator(sessionUser.getId());//最后修改人
			        userinfo.setEditdate(new Date());//最后修改时间
			      
			        //设置上级的节点(旧的上级的id),也就是当前登录用户的id
			        userinfo.setParentid(userCurrent.getParentid());
			        /**扩展字段2:
				     * 2014年行政采购合同信息，查询接口人信息和合同信息时，需要根据创建人进行查询，
				     * 故保存接口人信息时需要保存最初的创建人的信息
				     * **/
			        if(("是").equals(portInfo.getFisAllPort())){
			        	userinfo.setFext2(portInfo.getFext1());
			        }
			        userService.insertUser(userinfo);//调用service方法插入数据
			        //给用户创建角色信息
			        UserRole userRole = new UserRole();
					userRole.setUserid(Integer.parseInt(userinfo.getId() +""));
					userRole.setStutas(0);
			        if(("是").equals(portInfo.getFisAllPort())){
			        	roleId = roleMapper.selectRoleIdByName("行政采购总接口人");
						//用户关联相应的角色
						userRole.setRoleid(roleId);
					}else{
						roleId = roleMapper.selectRoleIdByName("行政采购接口人");
						userRole.setRoleid(roleId);
					}
			    	userRoleService.inserUserRole(userRole);
			    	//获得用户id
			    	userid = userinfo.getId();
				}else{
					//如果用户存在
					List<Map<String,Object>> listUser = userService.selectedUserAndRoleInfoByUserName(portEmail);
					
					userid = getUserId(listUser);//获得用户的id
					
					//先判断用户有没有相应的权限
					UserRole userRole = new UserRole();
					userRole.setUserid((int)userid);
					userRole.setStutas(0);
					if(("是").equals(portInfo.getFisAllPort())){
						roleId = roleMapper.selectRoleIdByName("行政采购总接口人");
						boolean va = findIfUserRoleExist(listUser,roleId);
						if(!va){//用户关联相应的角色
							userRole.setRoleid(roleId);
						}
					}else{
					    roleId = roleMapper.selectRoleIdByName("行政采购接口人");
						boolean va = findIfUserRoleExist(listUser,roleId);
						if(!va){ //用户关联相应的角色
							userRole.setRoleid(roleId);
						}
					}
					userRoleService.inserUserRole(userRole);
				}
				 
		    	//更改用户角色，中间表的信息
		    	Map mapAuthority = new HashMap();
	      		mapAuthority.put("fuserid", userCurrent.getId());
	      		mapAuthority.put("fportid", portInfo.getFid());
	      		//根据当前人员的id，和接口人id，查询出对应的信息
		    	AdminUserPort adminUserAuthority = adminUserPortMapper.findAuthorityByUserIdAndPortId(mapAuthority);
		    	adminUserAuthority.setFuserid(userid);//将权限中间表的用户id更新为新创建的用户的id
		    	adminUserPortMapper.updateByPrimaryKey(adminUserAuthority);
		    	//如果该接口人，在系统中还有同等接口人的权限，则不删除
		    	Map map = new HashMap();
		    	map.put("fportemail", portInfo.getFportEmail());
		    	map.put("fisAllPort",portInfo.getFisAllPort());
		    	List<Map<String,Object>> list = commonPortinfoMapper.queryPortInfoByEmail(map);
		    	//如果该用户不再有相同的用户权限，则执行删除
		    	if(list == null){
			    	//删除掉  被修改用户  的行政采购权限的信息
			    	userRoleService.deleteUserRoleByUseridAndRoleId(userCurrent.getId(),roleId);
			    	//判断下，如果该人员已经没有权限了，则在系统中删除掉该人员
					List<Integer> rolelist = userRoleService.userroleidlist((int)userid);
					if(null == rolelist || rolelist.size()==0){
						//删除该用户:逻辑删除
						userService.updateStatus((int)userid);
					}
		    	}
			}
			logger.info("修改接口人信息成功");
		}catch(Exception e){
			e.printStackTrace();
			logger.info("修改接口人信息失败" + e.getMessage());
			result = 0;
		}
		return result;
	}
	
	/**
	 * 获得用户的id
	* @Title: getUserId
	*created at 2014-6-1 下午02:15:07 by ygq  
	* @param listUser
	* @return
	* @return int
	 */
	private int getUserId(List<Map<String,Object>> listUser){
		int userId = 0;
		if(null != listUser && listUser.size()>=1){
			Map map = listUser.get(0);
			userId = Integer.parseInt(map.get("ID")+"");
		}
		return userId;
	}
	public UserRoleService getUserRoleService() {
		return userRoleService;
	}

	public void setUserRoleService(UserRoleService userRoleService) {
		this.userRoleService = userRoleService;
	}

	/***
	 * 判断用户的角色是否已经存在
	* @Title: findIfUserRoleExist
	*created at 2014-6-1 下午02:25:29 by ygq  
	* @param listUser
	* @param roleId
	* @return
	* @return boolean
	 */
	private boolean findIfUserRoleExist(List<Map<String,Object>> listUser,int roleId){
		boolean va = false;
		if(null != listUser && listUser.size()>1){
			for(int i=0;i<listUser.size();i++){
				Map map = listUser.get(i);
				Object role_id = map.get("role_id");
			}
		}
		return va;
	}
	/* (non-Javadoc)
	 * @see com.creditease.eas.adminipurc.service.IAdminiPurcPortService#delete(java.lang.Long)
	 */
	@Override
	public int delete(Long fid) throws Exception {
		int result = 0;
		try{
			result = commonPortinfoMapper.physicalDeletion(fid);
			logger.info("删除行政采购接口人成功");
		}catch(Exception ex){
			ex.printStackTrace();
			logger.info("删除行政采购接口人失败");
		}
		return result;
	}
}
