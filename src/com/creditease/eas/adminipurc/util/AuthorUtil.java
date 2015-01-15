/**
 * 
 */
package com.creditease.eas.adminipurc.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.creditease.eas.admin.bean.User;
import com.creditease.eas.admin.dao.RoleMapper;
import com.creditease.eas.adminipurc.dao.CommonPortinfoMapper;
import com.creditease.eas.util.StringUtil;
import com.creditease.eas.util.UserUtil;

/**
 * 权限的util包
 * @Title:AuthorUtil.java
 * @Package com.creditease.eas.adminipurc.util
 * created at 2014-6-1 下午05:22:27 by ygq
 * @author ygq
 * @version 1.0
 */
public class AuthorUtil {
	/***
	 * 使用sql注入的方式，解决权限查询的问题(接口人信息权限/查询合同相关的信息)
	* @Title: getAuthority
	*created at 2014-5-30 下午05:25:49 by ygq  
	* @param commonPortinfoMapper
	* @param contractName:查询参数的名称 ----->接口人调用时，传递fcontractnumber,合同信息调用时，传递fnumber
	* @return
	* @return String
	 */
	public static String getAuthority(RoleMapper roleMapper,CommonPortinfoMapper commonPortinfoMapper,String contractName,boolean isPort){
		User user = UserUtil.getUser();
		//查询采购组负责人的角色
		int roleId = roleMapper.selectRoleIdByName("行政采购合同预警采购组人员");
		boolean va = UserUtil.findIfSomeRole(roleId);//判断是否是采购组负责人的角色
		
		List<Map<String,String>> list = null;
		//如果是采购组负责人，则查询它下面所有人的信息
		if(va){
			list = commonPortinfoMapper.queryCaiGouAuthorityByUserid(user.getId());//用户名
			
		}else{
			list = commonPortinfoMapper.queryAuthorityByUsername(user.getUsername());//用户名
		}
		//当时采购组负责人时
		//当没有关联的数据信息时
		if(list==null ||list.isEmpty()){
			return "and fid is null";
		}
		String condition = "and (";
		for(int i=0;i<list.size();i++){
			Map<String,String> map = list.get(i);
			//暂时只考虑部门总接口人和接口人看到信息的情况，采购组负责人的情况根据用户角色去判断处理
			String fisallport = map.get("FISALLPORT");
			String fcontractnumber = map.get("FCONTRACTNUMBER");
			if(i==0){//i==0时，不能加or
				if(null != fisallport && fisallport.equals("是")){
					//这段代码需要根据成本中心控制 
					condition += "(" + contractName + " like '" + StringUtil.addLikeOperBoth(fcontractnumber) + "'";//与合同信息权限有差异的地方
					  /**扩展字段1:
				     * 2014年行政采购合同信息，查询接口人信息和合同信息时，需要根据创建人进行查询，
				     * 故保存接口人信息时需要保存最初的创建人的信息
				     * **/
					condition += "and fext1 ='" + map.get("FPORTEMAIL") + "')";
				}else if (null != fisallport && fisallport.equals("否")){
					condition += "(forgname = '" + map.get("FORGNAME") + "' and fcity='"+  map.get("FCITY")+ 
					"' and flastcostcenter='"+  map.get("FLASTCOSTCENTER");
					//如果是查询的接口人的，则加根据youx
					if(isPort){
						condition += "' and fportemail ='"+  map.get("FPORTEMAIL");
					}
					condition += "' and fofficeaddr='"+  map.get("FOFFICEADDR")+"')";
				}
			}else{//i 不等于0时，需要加or
				if(null != fisallport && fisallport.equals("是")){
					condition += "or (" +contractName+ " like '" + StringUtil.addLikeOperBoth(fcontractnumber) + "'";
					condition += "and fext1 ='" + map.get("FPORTEMAIL") + "')";
				}else if (null != fisallport && fisallport.equals("否")){
					condition += "or (forgname = '" + map.get("FORGNAME") + "' and fcity='"+  map.get("FCITY")+ 
					"' and flastcostcenter='"+  map.get("FLASTCOSTCENTER");
					//如果是查询的接口人的，则加根据youx
					if(isPort){
						condition += "' and fportemail ='"+  map.get("FPORTEMAIL");
					}
					condition += "' and fofficeaddr='"+  map.get("FOFFICEADDR")+"')";
				}
			}
		}
		condition += ")";
		return condition;
	}
}
