package com.creditease.eas.hr.bean.extoa;

import java.io.Serializable;
import java.util.Date;

/***
 * 组织信息的接口
 * @WSOAOrganization.java
 * created at 2013-10-17 上午10:32:18 by ygq
 * 
 * @author ygq({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class WSOAOrganization implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fid;//ID
	private String fname;//名称
	private String fnumber;//组织的编码
	private Integer findex;//序号
	private String fparentID;//上级行政组织ID
	private String fparentNumber;//上级组织编码
	private String fisOUSealUp;//是否封存
	private String forgTypeStr;//组织类型信息
	private String fisLeaf;//是否叶子节点
	private Integer flevel;//级次
	private String fdisplayName;//显示名称
	private String fisCostCenter;//是否为成本中心
	private Date fcreateTime;//创建时间
	private Date flastUpdateTime;//创建时间
	private Integer totalorgcount;//总条数
	private String fmanagernumber;//组织负责人对应的人员的编码
	private String	ext1;//		扩展字段一
	private String	ext2;//		扩展字段二
	private String	ext3;//		扩展字段三
	private String	ext4;//		扩展字段四
	private String	ext5;//		扩展字段五
	private String	ext6;//		扩展字段六
	private String	ext7;//		扩展字段七
	private String	ext8;//		扩展字段八
	private String	ext9;//		扩展字段九
	private String ext10;
	public String getFmanagernumber() {
		return fmanagernumber;
	}
	public void setFmanagernumber(String fmanagernumber) {
		this.fmanagernumber = fmanagernumber;
	}
	public Integer getTotalorgcount() {
		return totalorgcount;
	}
	public void setTotalorgcount(Integer totalorgcount) {
		this.totalorgcount = totalorgcount;
	}
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getFnumber() {
		return fnumber;
	}
	public void setFnumber(String fnumber) {
		this.fnumber = fnumber;
	}
	public Integer getFindex() {
		return findex;
	}
	public void setFindex(Integer findex) {
		this.findex = findex;
	}
	public String getFparentID() {
		return fparentID;
	}
	public void setFparentID(String fparentID) {
		this.fparentID = fparentID;
	}
	public String getFisOUSealUp() {
		return fisOUSealUp;
	}
	public void setFisOUSealUp(String fisOUSealUp) {
		this.fisOUSealUp = fisOUSealUp;
	}
	public String getForgTypeStr() {
		return forgTypeStr;
	}
	public void setForgTypeStr(String forgTypeStr) {
		this.forgTypeStr = forgTypeStr;
	}
	public String getFisLeaf() {
		return fisLeaf;
	}
	public void setFisLeaf(String fisLeaf) {
		this.fisLeaf = fisLeaf;
	}
	public Integer getFlevel() {
		return flevel;
	}
	public void setFlevel(Integer flevel) {
		this.flevel = flevel;
	}
	public String getFdisplayName() {
		return fdisplayName;
	}
	public void setFdisplayName(String fdisplayName) {
		this.fdisplayName = fdisplayName;
	}
	public String getFisCostCenter() {
		return fisCostCenter;
	}
	public void setFisCostCenter(String fisCostCenter) {
		this.fisCostCenter = fisCostCenter;
	}
	public Date getFcreateTime() {
		return fcreateTime;
	}
	public void setFcreateTime(Date fcreateTime) {
		this.fcreateTime = fcreateTime;
	}
	public Date getFlastUpdateTime() {
		return flastUpdateTime;
	}
	public void setFlastUpdateTime(Date flastUpdateTime) {
		this.flastUpdateTime = flastUpdateTime;
	}
	public String getExt1() {
		return ext1;
	}
	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}
	public String getExt2() {
		return ext2;
	}
	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}
	public String getExt3() {
		return ext3;
	}
	public void setExt3(String ext3) {
		this.ext3 = ext3;
	}
	public String getExt4() {
		return ext4;
	}
	public void setExt4(String ext4) {
		this.ext4 = ext4;
	}
	public String getExt5() {
		return ext5;
	}
	public void setExt5(String ext5) {
		this.ext5 = ext5;
	}
	public String getExt6() {
		return ext6;
	}
	public void setExt6(String ext6) {
		this.ext6 = ext6;
	}
	public String getExt7() {
		return ext7;
	}
	public void setExt7(String ext7) {
		this.ext7 = ext7;
	}
	public String getExt8() {
		return ext8;
	}
	public void setExt8(String ext8) {
		this.ext8 = ext8;
	}
	public String getExt9() {
		return ext9;
	}
	public void setExt9(String ext9) {
		this.ext9 = ext9;
	}
	public String getFparentNumber() {
		return fparentNumber;
	}
	public void setFparentNumber(String fparentNumber) {
		this.fparentNumber = fparentNumber;
	}
	public String getExt10() {
		return ext10;
	}
	public void setExt10(String ext10) {
		this.ext10 = ext10;
	}

}