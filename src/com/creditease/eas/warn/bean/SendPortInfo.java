package com.creditease.eas.warn.bean;

import java.util.Date;

/**
 * @SendPortInfo.java	已发送房租合同邮件明细bean
 * created at 2013-8-2 下午01:35:58 by caoyong
 * 
 * @author caoyong({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class SendPortInfo {
	/**主键id**/
    private Integer id;
    /**序号**/
    private String rentnumber;
    /**内容**/
    private String content;
    /**附件**/
    private String contentfile;
    /**是否发送**/
    private Integer issend;
    /**序号**/
    private String orgnumber;
    /**部门**/
    private String orgname;
    /**城市**/
    private String city;
    /**办公室地址**/
    private String officeadd;
    /**末级成本中心**/
    private String lastcostcenter;
    /**收件人邮箱**/
    private String portmail;
    /**是否为接口人*/
    private String isport;
    /**创建人*/
    private String creator;
    /**创建时间*/
    private Date creatortime;
    /**最后更新人姓名*/
    private String lastupdater;
    /**最后更新时间*/
    private Date lastupdatetime;
    /**是否发送成功*/
    private String ext1;
    /**备用字段2(标识财富端（2），普惠端（1）记录)*/
    private String ext2;
    /**备用字段3*/
    private String ext3;
    /**备用字段4*/
    private String ext4;
    /**备用字段5*/
    private String ext5;
    /**备用字段6*/
    private String ext6;
    /**备用字段7*/
    private String ext7;
    /**备用字段8*/
    private String ext8;
    /**备用字段9*/
    private String ext9;
    /**备用字段10*/
    private String ext10;
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the rentnumber
	 */
	public String getRentnumber() {
		return rentnumber;
	}
	/**
	 * @param rentnumber the rentnumber to set
	 */
	public void setRentnumber(String rentnumber) {
		this.rentnumber = rentnumber;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the contentfile
	 */
	public String getContentfile() {
		return contentfile;
	}
	/**
	 * @param contentfile the contentfile to set
	 */
	public void setContentfile(String contentfile) {
		this.contentfile = contentfile;
	}
	/**
	 * @return the issend
	 */
	public Integer getIssend() {
		return issend;
	}
	/**
	 * @param issend the issend to set
	 */
	public void setIssend(Integer issend) {
		this.issend = issend;
	}
	/**
	 * @return the orgnumber
	 */
	public String getOrgnumber() {
		return orgnumber;
	}
	/**
	 * @param orgnumber the orgnumber to set
	 */
	public void setOrgnumber(String orgnumber) {
		this.orgnumber = orgnumber;
	}
	/**
	 * @return the orgname
	 */
	public String getOrgname() {
		return orgname;
	}
	/**
	 * @param orgname the orgname to set
	 */
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the officeadd
	 */
	public String getOfficeadd() {
		return officeadd;
	}
	/**
	 * @param officeadd the officeadd to set
	 */
	public void setOfficeadd(String officeadd) {
		this.officeadd = officeadd;
	}
	/**
	 * @return the lastcostcenter
	 */
	public String getLastcostcenter() {
		return lastcostcenter;
	}
	/**
	 * @param lastcostcenter the lastcostcenter to set
	 */
	public void setLastcostcenter(String lastcostcenter) {
		this.lastcostcenter = lastcostcenter;
	}
	/**
	 * @return the portmail
	 */
	public String getPortmail() {
		return portmail;
	}
	/**
	 * @param portmail the portmail to set
	 */
	public void setPortmail(String portmail) {
		this.portmail = portmail;
	}
	/**
	 * @return the isport
	 */
	public String getIsport() {
		return isport;
	}
	/**
	 * @param isport the isport to set
	 */
	public void setIsport(String isport) {
		this.isport = isport;
	}
	/**
	 * @return the creator
	 */
	public String getCreator() {
		return creator;
	}
	/**
	 * @param creator the creator to set
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}
	/**
	 * @return the creatortime
	 */
	public Date getCreatortime() {
		return creatortime;
	}
	/**
	 * @param creatortime the creatortime to set
	 */
	public void setCreatortime(Date creatortime) {
		this.creatortime = creatortime;
	}
	/**
	 * @return the lastupdatetime
	 */
	public Date getLastupdatetime() {
		return lastupdatetime;
	}
	/**
	 * @return the lastupdater
	 */
	public String getLastupdater() {
		return lastupdater;
	}
	/**
	 * @param lastupdater the lastupdater to set
	 */
	public void setLastupdater(String lastupdater) {
		this.lastupdater = lastupdater;
	}
	/**
	 * @param lastupdatetime the lastupdatetime to set
	 */
	public void setLastupdatetime(Date lastupdatetime) {
		this.lastupdatetime = lastupdatetime;
	}
	/**
	 * @return the ext1
	 */
	public String getExt1() {
		return ext1;
	}
	/**
	 * @param ext1 the ext1 to set
	 */
	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}
	/**
	 * @return the ext2
	 */
	public String getExt2() {
		return ext2;
	}
	/**
	 * @param ext2 the ext2 to set
	 */
	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}
	/**
	 * @return the ext3
	 */
	public String getExt3() {
		return ext3;
	}
	/**
	 * @param ext3 the ext3 to set
	 */
	public void setExt3(String ext3) {
		this.ext3 = ext3;
	}
	/**
	 * @return the ext4
	 */
	public String getExt4() {
		return ext4;
	}
	/**
	 * @param ext4 the ext4 to set
	 */
	public void setExt4(String ext4) {
		this.ext4 = ext4;
	}
	/**
	 * @return the ext5
	 */
	public String getExt5() {
		return ext5;
	}
	/**
	 * @param ext5 the ext5 to set
	 */
	public void setExt5(String ext5) {
		this.ext5 = ext5;
	}
	/**
	 * @return the ext6
	 */
	public String getExt6() {
		return ext6;
	}
	/**
	 * @param ext6 the ext6 to set
	 */
	public void setExt6(String ext6) {
		this.ext6 = ext6;
	}
	/**
	 * @return the ext7
	 */
	public String getExt7() {
		return ext7;
	}
	/**
	 * @param ext7 the ext7 to set
	 */
	public void setExt7(String ext7) {
		this.ext7 = ext7;
	}
	/**
	 * @return the ext8
	 */
	public String getExt8() {
		return ext8;
	}
	/**
	 * @param ext8 the ext8 to set
	 */
	public void setExt8(String ext8) {
		this.ext8 = ext8;
	}
	/**
	 * @return the ext9
	 */
	public String getExt9() {
		return ext9;
	}
	/**
	 * @param ext9 the ext9 to set
	 */
	public void setExt9(String ext9) {
		this.ext9 = ext9;
	}
	/**
	 * @return the ext10
	 */
	public String getExt10() {
		return ext10;
	}
	/**
	 * @param ext10 the ext10 to set
	 */
	public void setExt10(String ext10) {
		this.ext10 = ext10;
	}
}