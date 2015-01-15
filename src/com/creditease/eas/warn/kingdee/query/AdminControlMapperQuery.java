package com.creditease.eas.warn.kingdee.query;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import com.creditease.eas.util.BaseMyBatisDao;
/**
 * 测试自由选择部门,用于解决部门自由选择的功能
 * 有可能要将信息添加到数据库中
 * @AdminControlMapperQuery.java
 * created at 2013-3-29 下午02:53:35 by ygq
 * @author ygq({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class AdminControlMapperQuery extends BaseMyBatisDao{
	/**
	 * 
	 * 描述：查询直接上级，间接上级，部门负责人(部门负责人只有一个)的方法
	 * 2013-1-18 下午07:21:17 by ygq
	 * @version
	 * @param cachekey
	 * @param listResponsePerson
	 * @param maph
	 */
	public static void test(){
		SqlSession session = getSession();
		List item = new ArrayList();
		item.add("11TEYT1400101");
		item.add("11TEYT1400105");
		List<Map<String,Object>> list2 = session.selectList("com.creditease.eas.warn.kingdee.dao.AdminControlMapper.selectOrgAdminByChoose",item);
		System.out.println("list2\t" + list2.size());
	}
	/**
	 * 
	 * 描述：查询直接上级，间接上级，部门负责人(部门负责人只有一个)的方法
	 * 2013-1-18 下午07:21:17 by ygq
	 * @version
	 * @param cachekey
	 * @param listResponsePerson
	 * @param maph
	 */
	public static void test2(){
		SqlSession session = getSession();
		List item = new ArrayList();
		item.add("01");
		List<Map<String,Object>> list2 = session.selectList("com.creditease.eas.warn.kingdee.dao.AdminControlMapper.selectOrgAdminByChooseTest2",item);
		System.out.println("list2\t" + list2.size());
	}
	/**
	 * 描述：
	 * 2013-3-29 下午03:03:58 by ygq
	 * @version
	 * @param args
	 */
	public static void main(String[] args) {
//		test();
		test2();
	}
}
