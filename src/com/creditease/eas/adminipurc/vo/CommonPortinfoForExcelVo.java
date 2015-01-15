package com.creditease.eas.adminipurc.vo;

import java.util.Date;

import com.creditease.eas.common.importexcel.bean.ImportCommonBean;
/**
 * 接口人信息对应的vo，用于验证
 * @CommonPortinfo.java
 * created at 2014-5-19 下午03:04:24 by gqy
 * 
 * @author gqy({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class CommonPortinfoForExcelVo{
	private Integer rowNum;//用于保存行数
	/**使用部门**/
    private String forgName;
    /**城市**/
    private String fcity;
    /**办公室座落地点**/
    private String fofficeAddr;
    /**成本中心**/
    private String flastCostcenter;
    /**接口人名字**/
    private String fportName;
	/**接口人邮箱**/
    private String fportEmail;
	/**是否部门总接口人**/
    private String fisAllPort;
    public Integer getRowNum() {
		return rowNum;
	}
	public void setRowNum(Integer rowNum) {
		this.rowNum = rowNum;
	}
    public String getForgName() {
		return forgName;
	}
	public void setForgName(String forgName) {
		this.forgName = forgName;
	}
	public String getFcity() {
		return fcity;
	}
	public void setFcity(String fcity) {
		this.fcity = fcity;
	}
	public String getFofficeAddr() {
		return fofficeAddr;
	}
	public void setFofficeAddr(String fofficeAddr) {
		this.fofficeAddr = fofficeAddr;
	}
	public String getFlastCostcenter() {
		return flastCostcenter;
	}
	public void setFlastCostcenter(String flastCostcenter) {
		this.flastCostcenter = flastCostcenter;
	}
	public String getFportEmail() {
		return fportEmail;
	}
	public void setFportEmail(String fportEmail) {
		this.fportEmail = fportEmail;
	}
	public String getFportName() {
		return fportName;
	}
	public void setFportName(String fportName) {
		this.fportName = fportName;
	}
	public String getFisAllPort() {
		return fisAllPort;
	}
	public void setFisAllPort(String fisAllPort) {
		this.fisAllPort = fisAllPort;
	}
}