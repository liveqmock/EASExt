/**
 * 
 */
package com.creditease.eas.warn.bean;

/**
 * @BlackListInfo.java
 * created at 2013-7-1 下午03:50:03 by Administrator
 * 
 * @author Administrator({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class BlackListInfo {
	private Integer pid;//编号
	private String pname;//人员名称
	private String pnumber;//人员编码
	private String pmail;//人员邮箱
	private String orgname;//部门名称
	private String orgnumber;//部门编码
	private Integer warntype;//预警类型
	private String raplacename;//替代人员
	private String raplacenumber;//替人员代编码
	private String raplacemail;//替代人员邮箱
	private String processmode;//处理方式（部门负责人代收、间下人员不收、都不收）:经查，这个字段没用了
	private String ext1;
	private String ext2;
	private String ext3;
	private String ext4;
	private String ext5;
	private String typename;
	private Integer modeid;//处理方式（部门负责人代收、间下人员不收、都不收）
	
	
	public Integer getModeid() {
		return modeid;
	}



	public void setModeid(Integer modeid) {
		this.modeid = modeid;
	}



	public String getTypename() {
		return typename;
	}



	public void setTypename(String typename) {
		this.typename = typename;
	}



	public BlackListInfo(){
		
	}
	
	

	public String getPmail() {
		return pmail;
	}



	public void setPmail(String pmail) {
		this.pmail = pmail;
	}



	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPnumber() {
		return pnumber;
	}

	public void setPnumber(String pnumber) {
		this.pnumber = pnumber;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public String getOrgnumber() {
		return orgnumber;
	}

	public void setOrgnumber(String orgnumber) {
		this.orgnumber = orgnumber;
	}

	public Integer getWarntype() {
		return warntype;
	}

	public void setWarntype(Integer warntype) {
		this.warntype = warntype;
	}

	public String getRaplacename() {
		return raplacename;
	}

	public void setRaplacename(String raplacename) {
		this.raplacename = raplacename;
	}

	public String getRaplacenumber() {
		return raplacenumber;
	}

	public void setRaplacenumber(String raplacenumber) {
		this.raplacenumber = raplacenumber;
	}

	public String getRaplacemail() {
		return raplacemail;
	}

	public void setRaplacemail(String raplacemail) {
		this.raplacemail = raplacemail;
	}

	public String getProcessmode() {
		return processmode;
	}

	public void setProcessmode(String processmode) {
		this.processmode = processmode;
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
	
	
}
