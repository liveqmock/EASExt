/**
 * 
 */
package com.creditease.eas.util.consts;

/**
 * @ConfigConst.java
 * created at 2013-1-16 下午05:11:46 by xjw
 * 
 * @author xjw({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class ConfigConst {
public static final String CONFIGCONST = "/app/tomcat-eas/easfile/mailresources.properties";
/**
 * 用于控制现有四种预警动态表头所需字段生成和排序依据
 */
public static final String[] HRWARN_COLUMN_SORT = {"员工编号","姓名","一级部门","二级部门","三级部门","四级部门","五级部门","六级部门","七级部门","八级部门","九级部门","城市","岗位","人员状态","入职日期","劳动合同到期日","试用期到期日","服务年限","出生日期"};
//行政采购合同预警邮件动态表头所需字段生成和排序依据
public static final String[] ADMINI_PURC = {"部门","办公室地址","成本中心","合同类别","供应商名称","合同到期日"};
}


