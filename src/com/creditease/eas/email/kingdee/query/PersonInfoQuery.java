package com.creditease.eas.email.kingdee.query;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.creditease.eas.email.kingdee.dao.PersonInfoMapper;
import com.creditease.eas.util.BaseMyBatisDao;
/**
 * 
 * @PersonInfoQuery.java
 * created at 2013-12-16 下午02:26:50 by zhangxin
 * 查询新入职员工信息
 * @author zhangxin({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class PersonInfoQuery extends BaseMyBatisDao {
	/**
	 * 
	 * 描述：具体人员信息
	 * 2013-12-16 下午02:47:36 by zhangxin
	 * @version
	 * @param map 查询条件
	 * @return 返回信息
	 */
	@SuppressWarnings("unchecked")
	public List<Map> queryPersonInfo(Map map){
		SqlSession session = getSession();
		PersonInfoMapper mapper = session.getMapper(PersonInfoMapper.class);
		List<Map> list = mapper.queryPage(map);
		return list;
	}
	/**
	 * 
	 * 描述：数据条数
	 * 2013-12-27 上午10:30:10 by zhangxin
	 * @version
	 * @param map 模糊条件查询条件
	 * @return 整数类型
	 */
	@SuppressWarnings("unchecked")
	public int totalPerson(Map map){
		SqlSession session = getSession();
		PersonInfoMapper mapper = session.getMapper(PersonInfoMapper.class);
		return mapper.getTotalCountsByParams(map);
	}
	/**
	 * 
	 * 描述：导出数据查询方法
	 * 2013-12-27 上午10:32:39 by zhangxin
	 * @version
	 * @param map 模糊条件查询条件
	 * @return list类型
	 */
	@SuppressWarnings("unchecked")
	public List<Map> expQuery(Map map){
		SqlSession session = getSession();
		PersonInfoMapper mapper = session.getMapper(PersonInfoMapper.class);
		List<Map> list = mapper.expQuery(map);
		return list;
	}
	/**
	 * 
	 * 描述：根据人员编码查询人员信息
	 * 2013-12-27 上午10:44:50 by zhangxin
	 * @version
	 * @param fnumber
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map selectPerson(String fnumber){
		SqlSession session = getSession();
		PersonInfoMapper mapper = session.getMapper(PersonInfoMapper.class);
		Map map = mapper.selectPerson(fnumber);
		return map;
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		PersonInfoQuery query = new PersonInfoQuery();
		/*Map map = new HashMap<String, Object>();
		map.put("fname","");
		map.put("startRow", 1);
		map.put("endRow", 100);
		List<Map> list = query.queryPersonInfo(map);
		for(int i = 0;i < list.size();i++){
			System.out.println(list.get(i));
		}*/
		Map map = query.selectPerson("201312230004");
		System.out.println(map.get("FNAME")+" "+map.get("FNUMBER")+" "+map.get("EMAIL")+" "+map.get("FCELL"));
	}
}
