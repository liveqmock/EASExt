package com.creditease.eas.warn.kingdee.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.creditease.eas.util.BaseMyBatisDao;
import com.creditease.eas.util.StringUtil;
import com.creditease.eas.warn.kingdee.dao.CommonPersonInfoSerachMapper;
/**
 * 公用的查询人员的直接上级，间接上级，部门负责人的方法
 * @CommonQuery.java
 * created at 2013-1-15 下午07:38:07 by ygq
 * 
 * @author ygq({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class CommonPersonInfoSerachQuery extends BaseMyBatisDao{
	/**
	 * 
	 * 描述：查询直接上级，间接上级，部门负责人(部门负责人只有一个)的方法
	 * 2013-1-18 下午07:21:17 by ygq
	 * @version
	 * @param cachekey
	 * @param listResponsePerson
	 * @param maph
	 */
	public static void addHighPerson(Map<String,String> cachekey,List<Map<String,Object>> listResponsePerson,List<Map<String,Object>> maph){
		if(null != listResponsePerson && listResponsePerson.size()>0){
			for(int i=0;i<listResponsePerson.size();i++){
				Map<String,Object> mhf = listResponsePerson.get(i);
				String personId = StringUtil.objToString(mhf.get("PERSONID"));
				if(personId == null){
					return;//没有人的时候不存
				}
				Object value = cachekey.get(personId);//根据现有的id,查询缓存中的值
				if(value==null){
					maph.add(mhf);
					cachekey.put(personId, personId);
				}
			}
		}
	}
	/**
	 * 
	 * 描述：查询所有的相关的需要查询的信息
	 * 2013-1-4 下午03:32:27 by ygq
	 * @version
	 * @throws Exception
	 */
	public static List<Map<String,Object>> queryHighPersonByPositionId(String positionId) throws Exception{
		/***************************遍历mapKey*******************************/
		List<Map<String,Object>> maph = new ArrayList<Map<String,Object>>();//存取人员
		SqlSession session = null;
		try {
			session = getSession();
			CommonPersonInfoSerachMapper mapper = session.getMapper(CommonPersonInfoSerachMapper.class); //调用公用的查询方法
			Map<String,String> cachekey = new HashMap<String,String>();
			//查询 直接上级，间接上级，部门负责人
			List<Map<String,Object>> listHighLevelPerson = mapper.selectHighLeverPersonByPositionId(positionId);//直接上级
			if(null != listHighLevelPerson && listHighLevelPerson.size()>0){
				for(int i=0;i<listHighLevelPerson.size();i++){//将所有的上级部门的人员都查询出来
					Map<String,Object> mh = listHighLevelPerson.get(i);
					//添加第一个人
					cachekey.put(mh.get("PERSONID").toString(),mh.get("PERSONID").toString());//存取人员的信息，并缓存下来
					//maph.addAll(listHighLevelPerson);//直接上级
					maph.add(mh);//添加直接上级
					String keyHighLevel = mh.get("POSITIONID").toString();
					System.out.println("直接上级的positionId\t" + keyHighLevel);
//					List<Map<String,Object>> listIndirectLevelPerson = mapper.selectHighLeverPersonByPositionId(keyHighLevel);//间接上级
//					addHighPerson(cachekey,listIndirectLevelPerson,maph);
				}
			}
			//部门负责人
//			List<Map<String,Object>> listResponsePerson =  mapper.selectResponsePersonInfo(positionId);
//			addHighPerson(cachekey,listResponsePerson,maph);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return maph;
	}
}
