package com.creditease.eas.adminipurc.util;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Controller;

import com.creditease.eas.admin.bean.AdminUserPort;
import com.creditease.eas.admin.bean.User;
import com.creditease.eas.admin.bean.UserRole;
import com.creditease.eas.admin.dao.AdminAuthorityMapper;
import com.creditease.eas.admin.dao.AdminUserPortMapper;
import com.creditease.eas.admin.dao.RoleMapper;
import com.creditease.eas.admin.dao.UserRoleMapper;
import com.creditease.eas.admin.service.UserAuthorityService;
import com.creditease.eas.admin.service.UserRoleService;
import com.creditease.eas.admin.service.UserService;
import com.creditease.eas.adminipurc.dao.CommonPortinfoMapper;
import com.creditease.eas.dictionary.bean.DictionaryItem;
import com.creditease.eas.dictionary.dao.IDictionaryItemMapper;
import com.creditease.eas.dictionary.service.IDictionaryBaseService;
import com.creditease.eas.util.UserUtil;
import com.creditease.eas.util.commbean.CommonPortinfo;
/****
 * 导入接口人信息的公用包
 * @Title:ImportPortInfoUtil.java
 * @Package com.creditease.eas.adminipurc.util
 * created at 2014-5-24 上午10:52:32 by ygq
 * @Description: TODO
 * @author ygq
 * @version 1.0
 */
@Controller
public class ImportPortInfoUtil {
	private RoleMapper roleMapper;//角色对应的映射
	private CommonPortinfoMapper portinfoMapper;//接口人对应的映射

	private AdminUserPortMapper adminUserPortMapper;//用户接口人中间表
	public ImportPortInfoUtil(){
		
	}
	/***
	 * 初始化对应的参数信息
	 * 构造函数
	 * @param roleMapper
	 * @param portinfoMapper
	 * @param dictionaryItemMapper
	 * @param userRoleMapper
	 */
	public ImportPortInfoUtil(RoleMapper roleMapper,CommonPortinfoMapper portinfoMapper,
			AdminUserPortMapper adminUserPortMapper){
		this.roleMapper = roleMapper;
		this.portinfoMapper = portinfoMapper;
		this.adminUserPortMapper = adminUserPortMapper;
	}
	/***
	 * 导入接口人信息和用户信息
	* @Title: importPortInfosAndUsers   
	* @param @param list
	* @param @param portinfoMapper
	* @param dictionaryItemMapper
	* @param @throws Exception
	* @return void
	* @throws
	 */
	public void importPortInfosAndUsers(List<List<String>> list,UserAuthorityService userService,UserRoleService userRoleService,IDictionaryBaseService dictionaryBaseService) throws Exception{
//		List<DictionaryItem> listContractWarnType = dictionaryItemMapper.seleteItemData(246);//合同预警类别
//		List<DictionaryItem> listComadmin = dictionaryItemMapper.seleteItemData(249);//公司部门编码
		int roleId = roleMapper.selectRoleIdByName("行政采购接口人");//查询行政采购接口人对应的角色id
		//部门总接口人的角色，是采购组的同事创建的，如果没创建，则不能导入
		//这个可以在验证的时候验证下
//		int roleAllId = roleMapper.selectRoleIdByName("行政采购总接口人");//查询行政采购总接口人对应的角色id
		User user = UserUtil.getUser();//获得登陆用户的基本信息
      	Long userId = user.getId();//用户id(创建人)     
//      	String username = user.getUsername();//登陆名
      	Date createTime = new Date();//创建时间
      	Long lastupdater = user.getId();//修改人
      	Date lastupdatetime =  new Date();//修改时间
		//查询公司和部门编码信息,便利并导入接口人信息s
	     for (int i = 2; i < list.size(); i++){  
	    	 //动态的title
	    	 List<String> titlesList = list.get(1);
			int[] titleNum = ImportExcel.getCellNumByName(titlesList,ImportExcel.PORTINFOTITLES);// 表头列对应的列numberz
	      	List<String> cellList = list.get(i); 
	      	String forgName = cellList.get(titleNum[0]).trim();//使用部门
	     	String fcity = cellList.get(titleNum[1]).trim();//城市
	     	String flastCostcenter = cellList.get(titleNum[2]).trim();//末级成本中心
	      	String fofficeAdd = cellList.get(titleNum[3]).trim();//办公司地址
	     	String fportName = cellList.get(titleNum[4]).trim();//接口人姓名
	      	String fportEmail = cellList.get(titleNum[5]).trim();//接口人邮箱
	      	String fisAllPort = cellList.get(titleNum[6]).trim();//是否为部门总接口人
	    	//生成合同编号前缀
	    	String fcontractNumber = dictionaryBaseService.getItemId("xzcgcomadmin",forgName);
	    	//获得预警类别
	    	Integer fportType = dictionaryBaseService.getItemKey("contractwarntype","行政采购房屋合同预警");
	    	if((forgName == null || forgName.equals("")) &&(fcity == null || fcity.equals("")) && (flastCostcenter == null || flastCostcenter.equals(""))
	    	  && (fofficeAdd == null || fofficeAdd.equals(""))&& (fportName == null || fportName.equals(""))&& (fportEmail == null || fportEmail.equals(""))){
	    		break;
	    	}
	      	//对接口人进行重复性判断:根据接口人姓名和邮箱进行判断
	      	Map map = new HashMap();
	      	map.put("forgName", forgName);
	    	map.put("fcity", fcity);
	    	map.put("flastCostcenter", flastCostcenter);
	    	map.put("fofficeAdd", fofficeAdd);
	    	map.put("fportEmail", fportEmail);
	    	//重复插入判断
	      	CommonPortinfo commonPortinfo = portinfoMapper.findIfPortExists(map);
	      	if(null == commonPortinfo){//不存在则直接添加接口人信息
		    	//获得人员信息
		    	CommonPortinfo portinfo = new CommonPortinfo();
		    	portinfo.setFdeleteStatus("1");//设置接口人状态
		      	portinfo.setForgName(forgName);
		      	portinfo.setFcity(fcity);
		      	portinfo.setFlastCostcenter(flastCostcenter);
		      	portinfo.setFofficeAddr(fofficeAdd);
		      	portinfo.setFportName(fportName);
		      	portinfo.setFportEmail(fportEmail);
		      	portinfo.setFisAllPort(fisAllPort);
		      	portinfo.setFcontractNumber(fcontractNumber);
		      	portinfo.setFportType(fportType);//设置预警类别
		      	if(null != user){
			      	portinfo.setFcreator(userId);
			      	portinfo.setFcreatetime(createTime);
			      	portinfo.setFlastupdater(lastupdater);
			      	portinfo.setFlastupdatetime(lastupdatetime);
			      	/**:
				     * 2014年行政采购合同信息，查询总接口人信息和合同信息时，需要根据接口人的邮箱查询，
				     * 故保存一个uuid，区分是哪个接口人存储的信息
				     * **/
			      	portinfo.setFext1(user.getUsername());
		      	}
	      		//添加接口人的信息
	      		portinfoMapper.insert(portinfo);
	      		int re = userService.selectedIfUserExists1(fportEmail);
		      	//导入总接口人的信息
	      		//&& username.equals(fportEmail) 不用判断是不是和当前登录人的邮箱相等
		      	if((null!=fisAllPort && "是".equals(fisAllPort.trim())) && re != 0){//必须已经给总接口人创建了角色
		      		//根据当前人员的用户名，查询用户信息
		      		//根据用户名，查询用户信息
			    	User userCurrent = userService.selectOneUserByUsername(fportEmail);
			    	
		      		//判断权限是否已经存在,不存在再进行添加
		      		Map mapAuthority = new HashMap();
		      		if(null != userCurrent){
		      			mapAuthority.put("fuserid", userCurrent.getId());
		      		}
		      		//查询接口人id
		      		mapAuthority.put("fportid", portinfo.getFid());
		      		int num = adminUserPortMapper.findIfAuthorityExists(mapAuthority);
		      		//判断该接口人是否已经拥有了该权限
		      		if(num == 0){
			      		//添加用户和权限中间表的数据
			            AdminUserPort adminUserAuthority = new AdminUserPort();
			            adminUserAuthority.setPortid(portinfo.getFid());//数据权限表
			            adminUserAuthority.setFuserid(userCurrent.getId());//当前用户的id
			            adminUserPortMapper.insert(adminUserAuthority);
		      		}
		            //总接口人的角色信息已经创建出来了
		            //如果角色为空，则帮助创建角色(正常情况下不用创建角色)
		      	}else if(null!=fisAllPort && "否".equals(fisAllPort.trim())){//导入一般接口人的信息
		      		//判断用户是否存在,不存在再进行添加
		      		int result = userService.selectedIfUserExists1(fportEmail);
		      		if(result == 0){
			      		//创建用户相关的信息
			            User userinfo = User.class.newInstance();
			            userinfo.setUsername(fportEmail);//用户名暂时为邮箱名字
			            userinfo.setPassword(fportEmail);//密码暂时为邮箱名字
			            userinfo.setEmail(fportEmail);//邮箱为邮箱名字
			            userinfo.setFcreator(user.getId());
			            userinfo.setCreatedate(new Date());//创建时间
			            userinfo.setFlastupdator(user.getId());//最后修改人
			            userinfo.setEditdate(new Date());//最后修改时间
			            userService.insertUser(userinfo);//调用service方法插入数据
			            userId = userinfo.getId();//获得当前用户的id
			            //用户关联相应的角色
			        	UserRole userRole = new UserRole();
						userRole.setUserid(Integer.parseInt(userinfo.getId() +""));
						userRole.setRoleid(roleId);
						userRole.setStutas(0);
						userRoleService.inserUserRole(userRole);
		      		}
		      		else{
//		      			userService.selectedIfUserExists1(fportEmail);
		      			User userInfo = userService.selectOneUserByUsername(fportEmail);
		      			if(null != userInfo){
		      				userId = userInfo.getId();
		      			}
		      		}
		      		//判断权限是否已经存在,不存在再进行添加:添加用户和权限中间表的数据
		      		Map mapAuthority = new HashMap();
		      		mapAuthority.put("fuserid",userId);
		      		mapAuthority.put("fportid", portinfo.getFid());
		      		int num = adminUserPortMapper.findIfAuthorityExists(mapAuthority);
		      		if(num == 0){
			            AdminUserPort adminUserAuthority = new AdminUserPort();
			            adminUserAuthority.setPortid(portinfo.getFid());//数据权限表
			            adminUserAuthority.setFuserid(userId);
			            adminUserPortMapper.insert(adminUserAuthority);
		      		}
		      	}
	      	}
	      	//经过考虑，接口人的权限信息不能在导入的时候更改，只能手动的更改
//	      	else{
//	      		//先缓存下权限信息，用于判断权限是否需要更新
//	      		String fisAllPortTemp = commonPortinfo.getFisAllPort();//是否是总接口人
//	      		String fauthorityTypeTemp = commonPortinfo.getFauthorityType();//权限类别
//	      		
//	      		//如果接口人信息已经存在，则更新接口人信息
//	      		//可能需要更新信息和权限
//	      		//commonPortinfo.setFdeleteStatus("1");//设置接口人状态
//	      		commonPortinfo.setForgName(forgName);
//	      		commonPortinfo.setFcity(fcity);
//	      		commonPortinfo.setFlastCostcenter(flastCostcenter);
//	      		commonPortinfo.setFofficeAddr(fofficeAdd);
//	      		commonPortinfo.setFportName(fportName);
//	      		commonPortinfo.setFportEmail(fportEmail);
//	      		commonPortinfo.setFisAllPort(fisAllPort);
//	      		commonPortinfo.setFauthorityType(fauthorityType);
//	      		commonPortinfo.setFcontractNumber(fcontractNumber);
//	      		commonPortinfo.setFportType(fportType);//设置预警类别
//	      		//更新接口人的信息
//	      		updatePortInfo(commonPortinfo,fisAllPortTemp,fauthorityTypeTemp,roleId,roleAllId);
//	      	}
	    }
	}
	/***
	 * 如果接口人信息发生变化，则更新接口人的信息
	 * 只有两种权限变化：A-->变为B
	 * B--》变成A ---》并且总接口人不能修改自己的权限信息
	* @Title: updatePortInfo
	*created at 2014-6-22 下午03:44:19 by ygq  
	* @return void
	 */
//	private void updatePortInfo(CommonPortinfo commonPortinfo,String fisAllPortTemp,String fauthorityTypeTemp,int roleId,int roleAllId){
//		int result = 0;
//  		//查询对应的权限
//  		//根据邮箱名，查询用户的id
//  		//更改用户角色，中间表的信息
//  		//查询出该用户的角色,用户中间表的信息，进行数据的更新
//  		//如果权限发生变化，则更新接口人的权限
//		//
//		try{
//			//更新接口人信息,用户原来的“接口人”的权限被更新到新的用户 
//			result =  portinfoMapper.update(commonPortinfo);
//				//如果用户存在
////				List<Map<String,Object>> listUser = userService.selectedUserAndRoleInfoByUserName(portEmail);
////				
////				userid = getUserId(listUser);//获得用户的id
//		}catch(Exception e){
//			e.printStackTrace();
//			result = 0;
//		}
//	}
}
