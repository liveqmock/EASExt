package com.creditease.eas.hr.kingdee.query;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import com.creditease.eas.hr.kingdee.dao.MessageSendMapper;
import com.creditease.eas.hr.util.XmlConvert;
import com.creditease.eas.util.BaseMyBatisDao;
/**
 * 码表信息查询
 * @QualifyingMatureQuery001.java
 * created at 2013-1-4 下午02:07:43 by ygq
 * 
 * @author ygq({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class MessageSendQuery extends BaseMyBatisDao{
	/**
	 * 
	 * 描述：城市信息码表
	 * 2013-1-4 下午03:02:45 by ygq
	 * @version
	 */
	public static String selectCityByCode(String code,String tranCode){
		String str = "";
		SqlSession session = null;
		try {
			session = getSession();
			MessageSendMapper mapper = session.getMapper(MessageSendMapper.class); 
			//需要转正的人的职位信息
			List<Map<String,Object>> list = mapper.queryCityByCode(code);
			System.out.println("list::::" + list);
			//将信息放入到List中
			str = XmlConvert.covertListToString(list,tranCode);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return str;
	}
//	public static void main(String[] args) {
//		selectCityByCode(null);
//	}
	/**
	 * 描述：查询职位等级的信息 
	 * 2013-1-4 下午03:02:45 by ygq
	 * @version
	 */
	public static String selectPositionGradeByCode(String code,String tranCode){
		String str = "";
		SqlSession session = null;
		try {
			session = getSession();
			MessageSendMapper mapper = session.getMapper(MessageSendMapper.class); 
			//需要转正的人的职位信息
			List<Map<String,Object>> list = mapper.queryPositionGradeByCode(code);
			//将信息放入到List中
			str = XmlConvert.covertListToString(list,tranCode);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return str;
	}
	/**
	 * 描述：查询职位类别的信息
	 * 2013-1-4 下午03:02:45 by ygq
	 * @version
	 */
	public static String selectPositionTypeByCode(String code,String tranCode){
		String str = "";
		SqlSession session = null;
		try {
			session = getSession();
			MessageSendMapper mapper = session.getMapper(MessageSendMapper.class); 
			//需要转正的人的职位信息
			List<Map<String,Object>> list = mapper.queryPositionTypeByCode(code);
			//将信息放入到List中
			str = XmlConvert.covertListToString(list,tranCode);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return str;
	}
	/**
	 * 
	 * 描述：员工状态
	 * 2013-1-21 下午01:44:35 by ygq
	 * @version
	 * @param code
	 * @return
	 */
	public static String selectEmployeeStatusByCode(String code,String tranCode){
		String str = "";
		SqlSession session = null;
		try {
			session = getSession();
			MessageSendMapper mapper = session.getMapper(MessageSendMapper.class); 
			//需要转正的人的职位信息
			List<Map<String,Object>> list = mapper.queryEmployeeStatusByCode(code);
			//将信息放入到List中
			str = XmlConvert.covertListToString(list,tranCode);
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return str;
	}
}
