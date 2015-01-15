package com.creditease.eas.warn.kingdee.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.ibatis.session.SqlSession;

import com.creditease.eas.util.BaseMyBatisDao;
import com.creditease.eas.warn.kingdee.dao.ContractMapper;
import com.creditease.eas.warn.kingdee.dao.PersonMapper;
/**
 * 查询人员信息和上级部门的人员的相关信息的代码
 * @QualifyingMatureQuery001.java
 * created at 2013-1-4 下午02:07:43 by ygq
 * 
 * @author ygq({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class ContractQuery01 extends BaseMyBatisDao{
	/**
	 * 
	 * 描述：查询相关人员的信息
	 * 2013-1-4 下午04:27:47 by ygq
	 * @version
	 * @throws Exception
	 */
	public static void queryPersonsInfo() throws Exception{
		SqlSession session = null;
		/***************************遍历mapKey*******************************/
		try {
			session = getSession();
			ContractMapper mapper = session.getMapper(ContractMapper.class); 
			//需要转正的人的职位信息
			List<Map<String,Object>> list = mapper.selectPersons();
			System.out.println(list.size());
			//将信息放入到List中
			for(int i=0;i<list.size();i++){
				Map<String,Object> mp = list.get(i);
				String fid = (String)mp.get("FID");
				String personName = (String)mp.get("FNAME_L2");
				List<Map<String,Object>> listP = mapper.selectPositionByPersonId(fid);//根据人员的id，查询人员对应的职位的id
				if(null != listP){
					for(int j=0;j<listP.size();j++){
						Map<String,Object> mpp = listP.get(j);
						String positionid = mpp.get("POSITIONID")==null?"":mpp.get("POSITIONID").toString();
						if(null != positionid){
							Map<String,Object> aname = mapper.selectAdminInfo(positionid);//根据职位的id,查询成本中心的名称
							if(aname != null){
								System.out.println(personName + "\t" + aname.get("FNAME2"));
							}else{
								System.out.println(personName);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
	}
	public static void main(String[] args) throws Exception{
		queryPersonsInfo();
	}
}
