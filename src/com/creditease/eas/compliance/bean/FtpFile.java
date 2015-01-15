package com.creditease.eas.compliance.bean;

import java.util.Date;

public class FtpFile {
  private Integer fid;//主键fid
  private String filename;//文件名称
  private String filepath;//文件路径
  private long createrid;//创建人的id
  private String creatername;//创建人名称
  private Date creatertime;//创建时间
  private String filetype;//文件类型
  private Integer lastupdaterid;//最后修改人id
  private Date fastupdatetime;//最后修改时间
  //扩展字段
  private String ext1;
  private String ext2;
  private String ext3;
  private String ext4;
  private String ext5;
  private String ext6;
  private String ext7;
  private String ext8;
  private String ext9;
  private String ext10;
public Integer getFid() {
	return fid;
}
public void setFid(Integer fid) {
	this.fid = fid;
}
public String getFilename() {
	return filename;
}
public void setFilename(String filename) {
	this.filename = filename;
}
public String getFilepath() {
	return filepath;
}
public void setFilepath(String filepath) {
	this.filepath = filepath;
}
public long getCreaterid() {
	return createrid;
}
public void setCreaterid(long createrid) {
	this.createrid = createrid;
}
public String getCreatername() {
	return creatername;
}
public void setCreatername(String creatername) {
	this.creatername = creatername;
}
public Date getCreatertime() {
	return creatertime;
}
public void setCreatertime(Date creatertime) {
	this.creatertime = creatertime;
}
public String getFiletype() {
	return filetype;
}
public void setFiletype(String filetype) {
	this.filetype = filetype;
}
public Integer getLastupdaterid() {
	return lastupdaterid;
}
public void setLastupdaterid(Integer lastupdaterid) {
	this.lastupdaterid = lastupdaterid;
}
public Date getFastupdatetime() {
	return fastupdatetime;
}
public void setFastupdatetime(Date fastupdatetime) {
	this.fastupdatetime = fastupdatetime;
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
public String getExt10() {
	return ext10;
}
public void setExt10(String ext10) {
	this.ext10 = ext10;
}
public FtpFile(Integer fid, String filename, String filepath,
		long createrid, String creatername, Date creatertime,
		String filetype, Integer lastupdaterid, Date fastupdatetime,
		String ext1, String ext2, String ext3, String ext4, String ext5,
		String ext6, String ext7, String ext8, String ext9, String ext10) {
	super();
	this.fid = fid;
	this.filename = filename;
	this.filepath = filepath;
	this.createrid = createrid;
	this.creatername = creatername;
	this.creatertime = creatertime;
	this.filetype = filetype;
	this.lastupdaterid = lastupdaterid;
	this.fastupdatetime = fastupdatetime;
	this.ext1 = ext1;
	this.ext2 = ext2;
	this.ext3 = ext3;
	this.ext4 = ext4;
	this.ext5 = ext5;
	this.ext6 = ext6;
	this.ext7 = ext7;
	this.ext8 = ext8;
	this.ext9 = ext9;
	this.ext10 = ext10;
}
public FtpFile() {
	super();
}
  
}
