package com.creditease.eas.warn.kingdee.query;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import com.creditease.eas.util.BaseMyBatisDao;
import com.creditease.eas.util.StringUtil;
import com.creditease.eas.warn.kingdee.dao.ContractMapper;
import com.creditease.eas.warn.kingdee.dao.FuseTubeMapper;
/***
 *改文件用于解决信管中心需求：
 * 将合同和转正的邮件信息发送给指定的接口人
 * @author ygq
 *
 */
public class FuseTubeQuery extends BaseMyBatisDao{
	public List<Map<String,Object>> selectRegularPersonInfos(String deptCode){
		SqlSession session = null;
		List<Map<String,Object>> regularPersonInfos = null;
		try{
			session = getSession();
			FuseTubeMapper fuseTubeMapper = session.getMapper(FuseTubeMapper.class);
			regularPersonInfos =  fuseTubeMapper.selectRegularPersonInfos(StringUtil.addLikeOperBoth(deptCode));
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return regularPersonInfos;
	}
	/**
	 * 
	 * 描述：合同续签人员名单
	 * 2013-2-1 下午02:44:11 by xjw
	 * @version
	 * @throws Exception
	 */
	public  List<Map<String,Object>> selectContract(String deptCode) throws Exception{
			SqlSession session = null;
			session = getSession();
			FuseTubeMapper mapper = session.getMapper(FuseTubeMapper.class); 
			List<Map<String,Object>> list = mapper.selectRenewalList(StringUtil.addLikeOperBoth(deptCode));//添加%
			for (int i = 0; i < list.size(); i++) {
				Map<String,Object> mp = list.get(i);
				System.err.println(mp.get("PNAME").toString()+"  "+mp.get("LONGNUMBER").toString());
			}
			return list;
	}
	public static void main(String[] args)  throws Exception{
		FuseTubeQuery fq = new FuseTubeQuery();
		String deptCode = "05XGXSF";
		List<Map<String,Object>> list = fq.selectContract(deptCode);
//		for(int i=0;i<list.size();i++){
//			Map<String,Object> map = list.get(i);
//			System.out.println(map.get("PERSONNAME") + "\t" + map.get("PERSONCODE"));
//		}
	}
	
}
