package com.creditease.eas.warn.vo;

import java.util.Date;

public class QueryData {
	private long id;
	private String name;
	private Date enterdate;
	private Date birthday;
	private String email;
	private String cell;
	private String positionid;//行政组织 id (职位id)
	private String orgid;//行政组织  （部门id）
	private String orgname;
	private String positionname;
	
	 private Integer typeid;

	   
	private Integer wayid;
	
	private Date sendtime;
	
	private String tpid;						//t_bd_person id
	private String personnumber;			//编码
	   
	private String cellcontent;
	
	private String theme;
	
	
	private String namess[];
	private String emailss[];
	
	private String hrname;
	private String ceoname;
	private String hrmail;
	private String ceomail;
	
	
	
	
	
	
	public String getHrname() {
		return hrname;
	}
	public void setHrname(String hrname) {
		this.hrname = hrname;
	}
	public String getCeoname() {
		return ceoname;
	}
	public void setCeoname(String ceoname) {
		this.ceoname = ceoname;
	}
	public String getHrmail() {
		return hrmail;
	}
	public void setHrmail(String hrmail) {
		this.hrmail = hrmail;
	}
	public String getCeomail() {
		return ceomail;
	}
	public void setCeomail(String ceomail) {
		this.ceomail = ceomail;
	}
	public String[] getNamess() {
		return namess;
	}
	public void setNamess(String[] namess) {
		this.namess = namess;
	}
	public String[] getEmailss() {
		return emailss;
	}
	public void setEmailss(String[] emailss) {
		this.emailss = emailss;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public String getCellcontent() {
		return cellcontent;
	}
	public void setCellcontent(String cellcontent) {
		this.cellcontent = cellcontent;
	}
	public String getTpid() {
		return tpid;
	}
	public void setTpid(String tpid) {
		this.tpid = tpid;
	}
	public String getPersonnumber() {
		return personnumber;
	}
	public void setPersonnumber(String personnumber) {
		this.personnumber = personnumber;
	}


	public Date getSendtime() {
		return sendtime;
	}
	public void setSendtime(Date sendtime) {
		this.sendtime = sendtime;
	}
	public Integer getTypeid() {
		return typeid;
	}
	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}
	public Integer getWayid() {
		return wayid;
	}
	public void setWayid(Integer wayid) {
		this.wayid = wayid;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public QueryData(){
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getEnterdate() {
		return enterdate;
	}
	public void setEnterdate(Date enterdate) {
		this.enterdate = enterdate;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCell() {
		return cell;
	}
	public void setCell(String cell) {
		this.cell = cell;
	}
	public String getPositionid() {
		return positionid;
	}
	public void setPositionid(String positionid) {
		this.positionid = positionid;
	}
	public String getOrgid() {
		return orgid;
	}
	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}
	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	public String getPositionname() {
		return positionname;
	}
	public void setPositionname(String positionname) {
		this.positionname = positionname;
	}

	

	
	
}