package com.creditease.eas.hr.kingdee.query;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.creditease.eas.hr.kingdee.dao.SalesManagerMapper;
import com.creditease.eas.util.BaseMyBatisDao;

/***
 * 销管系统相关的接口的Query
 * 
 * @author ygq
 * @version 1.0 2013/12/27 10:23
 */
public class SalesManagerQuery extends BaseMyBatisDao {
	/**
	 * 查询转正的人员信息
	 * @param paramMap
	 *            查询条件
	 * @return 转正人员信息列表
	 * @author ln 2014/01/13 23:00
	 */
	public List<Map<String, Object>> personTransformQuery(
			Map<String, Object> paramMap) {
		List<Map<String, Object>> formPersonList = null;
		SqlSession session = null;
		try {
			session = getSession();
			SalesManagerMapper mapper = session
					.getMapper(SalesManagerMapper.class);
			formPersonList = mapper.personTransformQuery(paramMap);
		} catch (Exception e) {
			return null;
		} finally {
			closeSession(session);
		}
		return formPersonList;
	}

	/**
	 * 查询异动的人员信息
	 * 
	 * @param paramMap
	 *            查询条件
	 * @return 异动人员信息列表
	 * @author ln 2014/01/13 23:00
	 */
	public List<Map<String, Object>> personUnusualActionQuery(
			Map<String, Object> paramMap) {
		List<Map<String, Object>> unususlPersonList = null;
		SqlSession session = null;
		try {
			session = getSession();
			SalesManagerMapper mapper = session
					.getMapper(SalesManagerMapper.class);
			unususlPersonList = mapper.personUnusualActionQuery(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			closeSession(session);
		}
		return unususlPersonList;
	}

	/**
	 * 查询离职的人员信息
	 * 
	 * @param paramMap
	 *            查询条件
	 * @return 离职人员信息列表
	 * @author ln 2014/01/13 23:00
	 */
	public List<Map<String, Object>> personLeaveQuery(
			Map<String, Object> paramMap) {
		List<Map<String, Object>> leavePersonList = null;
		SqlSession session = null;
		try {
			session = getSession();
			SalesManagerMapper mapper = session
					.getMapper(SalesManagerMapper.class);
			leavePersonList = mapper.personLeaveQuery(paramMap);
		} catch (Exception e) {
			return null;
		} finally {
			closeSession(session);
		}
		return leavePersonList;
	}

	/***
	 * 1.9查询新增的组织信息
	 * 
	 * @param map
	 *            封装的组织信息
	 * @return 组织信息列表
	 */
	public List<Map<String, Object>> orgAddQuery(Map map) {
		List<Map<String, Object>> listOrg = null;
		SqlSession session = null;
		try {
			session = getSession();
			SalesManagerMapper mapper = session
					.getMapper(SalesManagerMapper.class);
			listOrg = mapper.orgAddQuery(map);
		} catch (Exception e) {
			return null;
		} finally {
			closeSession(session);
		}
		return listOrg;
	}

	/***
	 * 1.10查询更新的组织信息
	 * 
	 * @param map
	 *            封装的组织信息
	 * @return 组织信息列表
	 */
	public List<Map<String, Object>> orgUpdateQuery(Map map) {
		List<Map<String, Object>> listOrg = null;
		SqlSession session = null;
		try {
			session = getSession();
			SalesManagerMapper mapper = session
					.getMapper(SalesManagerMapper.class);
			listOrg = mapper.orgUpdateQuery(map);
		} catch (Exception e) {
			return null;
		} finally {
			closeSession(session);
		}
		return listOrg;
	}

	/**
	 * 查询组织的职位信息
	 * 
	 * @param paramMap
	 *            封装的查询条件
	 * @return 组织职位信息列表
	 * @author ln 2014/01/16 16:21
	 */
	public List<Map<String, Object>> rankQuery(Map<String, Object> paramMap) {
		List<Map<String, Object>> orgRankList = null;
		SqlSession session = null;
		try {
			session = getSession();
			SalesManagerMapper mapper = session
					.getMapper(SalesManagerMapper.class);
			orgRankList = mapper.rankQuery(paramMap);
		} catch (Exception e) {
			return null;
		} finally {
			closeSession(session);
		}
		return orgRankList;
	}

	/**
	 * 查询新增的人员信息
	 * 
	 * @param paramMap
	 *            查询条件
	 * @return 新增人员信息列表
	 * @author ln 2014/01/06 13:37
	 */
	public List<Map<String, Object>> personAddQuery(Map<String, Object> paramMap) {
		List<Map<String, Object>> listAddPerson = null;
		SqlSession session = null;
		try {
			session = getSession();
			SalesManagerMapper mapper = session
					.getMapper(SalesManagerMapper.class);
			listAddPerson = mapper.personAddQuery(paramMap);
		} catch (Exception e) {
			return null;
		} finally {
			closeSession(session);
		}
		return listAddPerson;
	}

	/**
	 * 查询新增人员的职位信息
	 * 
	 * @param paramMap
	 *            查询条件
	 * @return 新增人员信息列表
	 * @author ln 2014/01/06 13:37
	 */
	public List<Map<String, Object>> personAddPositionQuery(
			Map<String, Object> paramMap) {
		List<Map<String, Object>> listAddPerson = null;
		SqlSession session = null;
		try {
			session = getSession();
			SalesManagerMapper mapper = session
					.getMapper(SalesManagerMapper.class);
			listAddPerson = mapper.personAddPositionQuery(paramMap);
		} catch (Exception e) {
			return null;
		} finally {
			closeSession(session);
		}
		return listAddPerson;
	}

	/**
	 * 查询新增人员的合同信息
	 * 
	 * @param paramMap
	 *            查询条件
	 * @return 新增人员信息列表
	 * @author ln 2014/01/06 13:37
	 */
	public List<Map<String, Object>> personAddContractQuery(
			Map<String, Object> paramMap) {
		List<Map<String, Object>> listAddPerson = null;
		SqlSession session = null;
		try {
			session = getSession();
			SalesManagerMapper mapper = session
					.getMapper(SalesManagerMapper.class);
			listAddPerson = mapper.personAddContractQuery(paramMap);
		} catch (Exception e) {
			return null;
		} finally {
			closeSession(session);
		}
		return listAddPerson;
	}

	/**
	 * 查询新增人员的学历信息
	 * 
	 * @param paramMap
	 *            查询条件
	 * @return 新增人员信息列表
	 * @author ln 2014/01/06 13:37
	 */
	public List<Map<String, Object>> personAddDegreeQuery(
			Map<String, Object> paramMap) {
		List<Map<String, Object>> listAddPerson = null;
		SqlSession session = null;
		try {
			session = getSession();
			SalesManagerMapper mapper = session
					.getMapper(SalesManagerMapper.class);
			listAddPerson = mapper.personAddDegreeQuery(paramMap);
		} catch (Exception e) {
			return null;
		} finally {
			closeSession(session);
		}
		return listAddPerson;
	}

	/**
	 * 查询更新的人员信息
	 * 
	 * @param paramMap
	 *            查询条件
	 * @return 更新人员信息列表
	 * @author ln 2014/01/06 13:37
	 */
	public List<Map<String, Object>> personUpdateQuery(
			Map<String, Object> paramMap) {
		List<Map<String, Object>> listUpdatePerson = null;
		SqlSession session = null;
		try {
			session = getSession();
			SalesManagerMapper mapper = session
					.getMapper(SalesManagerMapper.class);
			listUpdatePerson = mapper.personUpdateQuery(paramMap);
		} catch (Exception e) {
			return null;
		} finally {
			closeSession(session);
		}
		return listUpdatePerson;
	}

	/**
	 * 查询更新人员的职位信息
	 * 
	 * @param paramMap
	 *            查询条件
	 * @return 更新人员信息列表
	 * @author ln 2014/01/06 13:37
	 */
	public List<Map<String, Object>> personUpdatePositionQuery(
			Map<String, Object> paramMap) {
		List<Map<String, Object>> listUpdatePerson = null;
		SqlSession session = null;
		try {
			session = getSession();
			SalesManagerMapper mapper = session
					.getMapper(SalesManagerMapper.class);
			listUpdatePerson = mapper.personUpdatePositionQuery(paramMap);
		} catch (Exception e) {
			return null;
		} finally {
			closeSession(session);
		}
		return listUpdatePerson;
	}

	/**
	 * 查询更新人员的合同信息
	 * 
	 * @param paramMap
	 *            查询条件
	 * @return 更新人员信息列表
	 * @author ln 2014/01/06 13:37
	 */
	public List<Map<String, Object>> personUpdateContractQuery(
			Map<String, Object> paramMap) {
		List<Map<String, Object>> listUpdatePerson = null;
		SqlSession session = null;
		try {
			session = getSession();
			SalesManagerMapper mapper = session
					.getMapper(SalesManagerMapper.class);
			listUpdatePerson = mapper.personUpdateContractQuery(paramMap);
		} catch (Exception e) {
			return null;
		} finally {
			closeSession(session);
		}
		return listUpdatePerson;
	}

	/**
	 * 查询更新人员的学历信息
	 * 
	 * @param paramMap
	 *            查询条件
	 * @return 更新人员信息列表
	 * @author ln 2014/01/06 13:37
	 */
	public List<Map<String, Object>> personUpdateDegreeQuery(
			Map<String, Object> paramMap) {
		List<Map<String, Object>> listUpdatePerson = null;
		SqlSession session = null;
		try {
			session = getSession();
			SalesManagerMapper mapper = session
					.getMapper(SalesManagerMapper.class);
			listUpdatePerson = mapper.personUpdateDegreeQuery(paramMap);
		} catch (Exception e) {
			return null;
		} finally {
			closeSession(session);
		}
		return listUpdatePerson;
	}
//	/**
//	 * 查询计薪人员信息
//	 * @param paramMap
//	 *            查询条件
//	 * @return 计薪人员信息列表
//	 * @author ln 2014/04/04 13:37
//	 */
//	public List<Map<String, Object>> salaryPersonQuery(Map<String, Object> paramMap) {
//		List<Map<String, Object>> listSalaryPerson = null;
//		SqlSession session = null;
//		// 获取所有的薪酬发放表
//		List<String> tempTableList = this.getSalaryTables();
//		if (null != tempTableList && 0 < tempTableList.size()) {
//			try {
//				session = getSession();
//				SalesManagerMapper mapper = session
//						.getMapper(SalesManagerMapper.class);
//				listSalaryPerson = new ArrayList<Map<String, Object>>();
//				for (String tempTable : tempTableList) {
//					if (null != tempTable && "" != tempTable) {
//						try{
//							paramMap.put("tempTable", tempTable);
//							listSalaryPerson.addAll(mapper.salaryPersonQuery(paramMap));
//						}catch(Exception e){
//							e.printStackTrace();
//							System.out.println(tempTable+"表中没有符合要求的数据。");
//						}
//					}
//				}
//			} catch (Exception e) {
//				return null;
//			} finally {
//				closeSession(session);
//			}
//		}
//		return listSalaryPerson;
//	}
//	/**
//	 * 查询计薪人员薪酬更新信息
//	 * @param paramMap
//	 *            查询条件
//	 * @return 计薪人员薪酬更新信息列表
//	 * @author ln 2014/04/04 13:37
//	 */
//	public List<Map<String, Object>> salaryUpdateQuery(Map<String, Object> paramMap) {
//		List<Map<String, Object>> salaryUpdateList = null;
//		SqlSession session = null;
//		try {
//			session = getSession();
//			SalesManagerMapper mapper = session
//					.getMapper(SalesManagerMapper.class);
//			salaryUpdateList = mapper.salaryUpdateQuery(paramMap);
//		} catch (Exception e) {
//			return null;
//		} finally {
//			closeSession(session);
//		}
//		return salaryUpdateList;
//	}
//	/**
//	 * 查询计薪人员信息
//	 * 
//	 * @param jsonParam
//	 *            查询条件
//	 * @return 计薪人员信息列表
//	 * @author ln 2014/04/04 13:37
//	 */
//	public List<Map<String, Object>> salaryQuery(Map<String, Object> paramMap) {
//		List<Map<String, Object>> listSalaryPerson = null;
//		SqlSession session = null;
//		// 获取所有的薪酬发放表
//		List<String> tempTableList = this.getSalaryTables();
//		if (null != tempTableList && 0 < tempTableList.size()) {
//			try {
//				session = getSession();
//				SalesManagerMapper mapper = session
//						.getMapper(SalesManagerMapper.class);
//				listSalaryPerson = new ArrayList<Map<String, Object>>();
//				for (String tempTable : tempTableList) {
//					if (null != tempTable && "" != tempTable) {
//						try{
//							listSalaryPerson.addAll(mapper.salaryQuery(tempTable));
//						}catch(Exception e){
//							e.printStackTrace();
//							System.out.println(tempTable+"表中没有符合要求的数据。");
//						}
//					}
//				}
//			} catch (Exception e) {
//				return null;
//			} finally {
//				closeSession(session);
//			}
//		}
//		return listSalaryPerson;
//	}
	/**
	 * 查询计薪人员定级定薪表
	 * @return 计薪人员定级定薪表
	 * @author ln 2014/04/04 13:37
	 */
	private List<String> getSalaryTables() {
		List<String> tempTableList = null;
		SqlSession session = null;
		try {
			session = getSession();
			SalesManagerMapper mapper = session
					.getMapper(SalesManagerMapper.class);
			tempTableList = mapper.salaryTableQuery();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			closeSession(session);
		}
		return tempTableList;
	}

	/**
	 * 计薪人员查询
	 * @param paramMap
	 * @return
	 */
	public List<Map<String, Object>> paidStaffQuery(Map<String, Object> paramMap) {
		List<Map<String, Object>> paidStaffList = null;
		SqlSession session = null;
		try {
			session = getSession();
			SalesManagerMapper mapper = session
					.getMapper(SalesManagerMapper.class);
			paidStaffList = mapper.paidStaffQuery(paramMap);
		} catch (Exception e) {
			return null;
		} finally {
			closeSession(session);
		}
		return paidStaffList;
	}

	/**
	 * 员工薪酬查询
	 * @return
	 */
	public List<Map<String, Object>> paymentQuery() {
		List<Map<String, Object>> paymentList = null;
		SqlSession session = null;
		// 获取所有的薪酬发放表
		List<String> tempTableList = this.getSalaryTables();
		if (null != tempTableList && 0 < tempTableList.size()) {
			try {
				session = getSession();
				SalesManagerMapper mapper = session
						.getMapper(SalesManagerMapper.class);
				paymentList = new ArrayList<Map<String, Object>>();
				for (String tempTable : tempTableList) {
					if (null != tempTable && "" != tempTable) {
						try{
							paymentList.addAll(mapper.paymentQuery(tempTable));
						}catch(Exception e){
							e.printStackTrace();
							System.out.println(tempTable+"表中没有符合要求的数据。");
						}
					}
				}
			} catch (Exception e) {
				return null;
			} finally {
				closeSession(session);
			}
		}
		return paymentList;
	}


	// public static void main(String[] args) {
	// Map map = null;
	// List<Map<String,Object>> list = personAddQuery(map);
	// for(int i=0;i<list.size();i++){
	// Map<String,Object> mapInfo = list.get(i);
	// System.out.println("mapInfo\t" + mapInfo);
	// }
	// Map<String,Object> map = new HashMap<String,Object>();
	// map.put("fnumber", null);
	// map.put("beginTime",null);
	// map.put("endTime", null);
	// SalesManagerQuery sm = new SalesManagerQuery();
	// List<Map<String,Object>> list = sm.personTransformQuery(map);
	// System.out.println(Arrays.toString(list.toArray()));
	// }
}
