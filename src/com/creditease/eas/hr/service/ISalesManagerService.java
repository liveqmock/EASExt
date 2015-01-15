package com.creditease.eas.hr.service;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
/***
 * 销管系统相关的接口的service
 * @author ygq
 * @version 1.0 2013/12/27 10:23
 */
@WebService(targetNamespace="http://com/creditease/eas/ws",serviceName="ISalesManagerService")
@SOAPBinding(style = Style.RPC)
public interface ISalesManagerService {
	/**
	 * 查询添加的组织信息,销管系统那边传递json格式的字符串给MIS服务，
	 * MIS服务进行解析，然后再将查询出的Json格式的数据给到销管系统
	 * 中间需要处理json到Map的转换及Map到json的转换
	 * @param json 销管系统那边传递的json格式的参数
	 * @return 查询的符合条件的信息
	 */
	public String orgAddQuery(String jsonParam);
	/**
	 * 查询更新的组织信息
	 * @param jsonParam 查询条件
	 * @return 更新组织信息列表
	 * @author ln 2014/01/13 23:00
	 */
	public String orgUpdateQuery(String jsonParam);	
	/**
	 * 查询组织职位信息
	 * @param jsonParam 查询条件
	 * @return 组织职位信息列表
	 * @author ln 2014/01/16 16:22
	 */
	public String rankQuery(String jsonParam);	
	/**
	 * 查询转正的人员信息
	 * @param jsonParam 查询条件
	 * @return 转正人员信息列表
	 * @author ln 2014/01/13 23:00
	 */
	public String personTransformQuery(String jsonParam);
	/**
	 * 查询异动的人员信息
	 * @param jsonParam 查询条件
	 * @return 异动人员信息列表
	 * @author ln 2014/01/13 23:00
	 */
	public String personUnusualActionQuery(String jsonParam);
	/**
	 * 查询离职的人员信息
	 * @param jsonParam 查询条件
	 * @return 离职人员信息列表
	 * @author ln 2014/01/13 23:00
	 */
	public String personLeaveQuery(String jsonParam);
	/**
	 * 查询新增的人员信息
	 * @param jsonParam 查询条件
	 * @return 新增人员信息列表
	 * @author ln 2014/01/06 13:37
	 */
	public String personAddQuery(String jsonParam);
	/**
	 * 查询新增人员的职位信息
	 * @param jsonParam 查询条件
	 * @return 新增人员信息列表
	 * @author ln 2014/01/06 13:37
	 */
	public String personAddPositionQuery(String jsonParam);
	/**
	 * 查询新增人员的合同信息
	 * @param jsonParam 查询条件
	 * @return 新增人员信息列表
	 * @author ln 2014/01/06 13:37
	 */
	public String personAddContractQuery(String jsonParam);
	/**
	 * 查询新增人员的学历信息
	 * @param jsonParam 查询条件
	 * @return 新增人员信息列表
	 * @author ln 2014/01/06 13:37
	 */
	public String personAddDegreeQuery(String jsonParam);
	/**
	 * 查询更新的人员信息
	 * @param jsonParam 查询条件
	 * @return 更新人员信息列表
	 * @author ln 2014/01/06 13:37
	 */
	public String personUpdateQuery(String jsonParam);
	/**
	 * 查询更新人员的职位信息
	 * @param jsonParam 查询条件
	 * @return 更新人员信息列表
	 * @author ln 2014/01/06 13:37
	 */
	public String personUpdatePositionQuery(String jsonParam);
	/**
	 * 查询更新人员的合同信息
	 * @param jsonParam 查询条件
	 * @return 更新人员信息列表
	 * @author ln 2014/01/06 13:37
	 */
	public String personUpdateContractQuery(String jsonParam);
	/**
	 * 查询更新人员的学历信息
	 * @param jsonParam 查询条件
	 * @return 更新人员信息列表
	 * @author ln 2014/01/06 13:37
	 */
	public String personUpdateDegreeQuery(String jsonParam);
//	/**
//	 * 查询计薪人员信息
//	 * @param jsonParam 查询条件
//	 * @return 计薪人员信息列表
//	 * @author ln 2014/04/04 13:37
//	 */
//	public String salaryPersonQuery(String jsonParam);
//	/**
//	 * 查询计薪人员薪酬项目更新信息
//	 * @param jsonParam 查询条件
//	 * @return 人员薪酬项目更新信息列表
//	 * @author ln 2014/04/04 13:37
//	 */
//	public String salaryUpdateQuery(String jsonParam);
	/**
	 * 查询员工定级定薪数据
	 * @return 全体定级定薪表薪酬数据
	 */
	public String paymentQuery();
	/**
	 * 查询计薪人员
	 * @param jsonParam 查询期间
	 * @return 计薪人员列表
	 */
	public String paidStaffQuery(String jsonParam);

}
