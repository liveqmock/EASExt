package com.creditease.eas.util.commbean;

import java.util.Date;

import com.creditease.eas.common.importexcel.bean.ImportCommonBean;
/**
 * @CommonPortinfo.java
 * created at 2014-5-19 下午03:04:24 by gqy
 * 
 * @author gqy({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class CommonPortinfo{
	/**主键id**/
	private Long fid;
    /**使用部门**/
    private String forgName;
    /**城市**/
    private String fcity;
    /**办公室座落地点**/
    private String fofficeAddr;
    /**成本中心**/
    private String flastCostcenter;
	/**接口人邮箱**/
    private String fportEmail;
    /**接口人名字**/
    private String fportName;
    /**是否部门总接口人**/
    private String fisAllPort;
    /***********权限类别:
     * 需求中已经将该字段删除掉了，为了减少改动量
     * 故暂时只是标记该字段，不在代码中进行删除
     * ********/
    @Deprecated
    private String fauthorityType;
    /****
     * 预警类别：从数据字典中取数据
     */
    private Integer fportType;
    /***
     * 1.在用2.删除
     */
    private String fdeleteStatus;
    /*******合同编号前缀*********/
    private String fcontractNumber;
    /**创建人**/
    private Long fcreator;
    /**创建时间**/
    private Date fcreatetime;
    /**最后更新人姓名**/
    private Long flastupdater;
    /**最后更新时间**/
    private Date flastupdatetime;
    /**扩展字段1:用于保存最初的创建人id**/
    private String fext1;
    /**扩展字段2**/
    private String fext2;
    /**扩展字段3**/
    private String fext3;
    /**扩展字段4**/
    private String fext4;
    /**扩展字段5**/
    private String fext5;
    /**扩展字段6**/
    private String fext6;
    /**扩展字段7**/
    private String fext7;
    /**扩展字段8**/
    private String fext8;
    /**扩展字段9**/
    private String fext9;
    /**扩展字段10**/
    private String fext10;
    public Long getFid() {
		return fid;
	}
	public void setFid(Long fid) {
		this.fid = fid;
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
	public String getFauthorityType() {
		return fauthorityType;
	}
	public void setFauthorityType(String fauthorityType) {
		this.fauthorityType = fauthorityType;
	}
	public Integer getFportType() {
		return fportType;
	}
	public void setFportType(Integer fportType) {
		this.fportType = fportType;
	}
	public String getFdeleteStatus() {
		return fdeleteStatus;
	}
	public void setFdeleteStatus(String fdeleteStatus) {
		this.fdeleteStatus = fdeleteStatus;
	}
	public Date getFcreatetime() {
		return fcreatetime;
	}
	public void setFcreatetime(Date fcreatetime) {
		this.fcreatetime = fcreatetime;
	}
	public Long getFcreator() {
		return fcreator;
	}
	public void setFcreator(Long fcreator) {
		this.fcreator = fcreator;
	}
	public Long getFlastupdater() {
		return flastupdater;
	}
	public Date getFlastupdatetime() {
		return flastupdatetime;
	}
	public void setFlastupdatetime(Date flastupdatetime) {
		this.flastupdatetime = flastupdatetime;
	}
	public void setFlastupdater(Long flastupdater) {
		this.flastupdater = flastupdater;
	}
	public String getFcontractNumber() {
		return fcontractNumber;
	}
	public void setFcontractNumber(String fcontractNumber) {
		this.fcontractNumber = fcontractNumber;
	}
	public String getFext1() {
		return fext1;
	}
	public void setFext1(String fext1) {
		this.fext1 = fext1;
	}
	public String getFext2() {
		return fext2;
	}
	public void setFext2(String fext2) {
		this.fext2 = fext2;
	}
	public String getFext3() {
		return fext3;
	}
	public void setFext3(String fext3) {
		this.fext3 = fext3;
	}
	public String getFext4() {
		return fext4;
	}
	public void setFext4(String fext4) {
		this.fext4 = fext4;
	}
	public String getFext5() {
		return fext5;
	}
	public void setFext5(String fext5) {
		this.fext5 = fext5;
	}
	public String getFext6() {
		return fext6;
	}
	public void setFext6(String fext6) {
		this.fext6 = fext6;
	}
	public String getFext7() {
		return fext7;
	}
	public void setFext7(String fext7) {
		this.fext7 = fext7;
	}
	public String getFext8() {
		return fext8;
	}
	public void setFext8(String fext8) {
		this.fext8 = fext8;
	}
	public String getFext9() {
		return fext9;
	}
	public void setFext9(String fext9) {
		this.fext9 = fext9;
	}
	public String getFext10() {
		return fext10;
	}
	public void setFext10(String fext10) {
		this.fext10 = fext10;
	}
}