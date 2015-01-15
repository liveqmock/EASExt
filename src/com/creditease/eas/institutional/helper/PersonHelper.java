package com.creditease.eas.institutional.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.creditease.eas.institutional.bean.EASPerson;
import com.creditease.webservice.ArrayOfXsdAnyType;
import com.creditease.webservice.dto.UserInfoDTO;

/**
 * 处理集合的工具类
 * @author lining
 *
 */
public class PersonHelper {
	/**
	 * 合并查询到的重复人员数据
	 * @param paramList
	 * @return
	 * @throws Exception
	 */
	public static List<UserInfoDTO> personMerge(List<EASPerson> paramList)throws Exception{
		Map<String,Object> resultUsers = new HashMap<String,Object>();
		for(EASPerson user:paramList){
			String userId = user.getNewId();
			if(!resultUsers.containsKey(userId)){
				UserInfoDTO resultUser = new UserInfoDTO();
				resultUser.setNewId(user.getNewId());
				resultUser.setName(user.getName());
				resultUser.setEmail(user.getEmail());
				resultUser.setEnabled(user.getEnabled());
				resultUser.setGrade(macthConfidentiality(user.getGrade()));
				ArrayOfXsdAnyType newOrgs = new ArrayOfXsdAnyType();
				newOrgs.getItem().add(user.getLevelOneOrg());
				resultUser.setLevelOneOrg(newOrgs);
				ArrayOfXsdAnyType oldOrgs = new ArrayOfXsdAnyType();
				oldOrgs.getItem().add(user.getLevelOldOrgList());
				resultUser.setLevelOldOrgList(oldOrgs);
				
				resultUsers.put(userId, resultUser);
			}else{
				UserInfoDTO resultUser =  (UserInfoDTO)resultUsers.get(userId);
				if(null != user.getLevelOneOrg() && "" != user.getLevelOneOrg()){
					resultUser
						.getLevelOneOrg()
							.getItem()
								.add(user.getLevelOneOrg());
				}
				if(null != user.getLevelOldOrgList() && "" != user.getLevelOldOrgList()){
					resultUser
					.getLevelOldOrgList()
						.getItem()
							.add(user.getLevelOldOrgList());
	
				}
				if(1 == user.getIsPrimary()){
					resultUser
						.setGrade(macthConfidentiality(user.getGrade()));
				}
			}
		}
		List<UserInfoDTO> resultList = new ArrayList<UserInfoDTO>();
		Iterator<String> it = resultUsers.keySet().iterator();
		while(it.hasNext()){
			resultList.add((UserInfoDTO)resultUsers.get(it.next()));
		}
		return resultList;
	}
	/**
	 * 将原来的密级数据转化为新密级数据
	 * @param level 新密级数据
	 * @return 原密级数据
	 * @throws Exception
	 */
	private static String  macthConfidentiality(String level) throws Exception{
		String[] levels = {"","绝密","机密","秘密","公开"}; 
		if(null == level){
			return levels[0];
		}else{
			return levels[Integer.parseInt(level)];
		}
	}
}
