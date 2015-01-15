package com.creditease.eas.projectmanage.bean;

import java.util.Date;

public class AgreementFile {
	/**自增id*/
   private Integer fid;
   /**文件名称  */
   private String fileName;
   /**页面展示文件名*/
   private String viewName;
   /** 文件路径*/
   private String filePath;
   /** 项目信息fid*/
   private Integer projectFid;
   /** 创建id*/
   private Integer createrId;
   /**创建人名称*/
   private String createName;
   /** 创建时间*/
   private Date createTime;
	/*扩展字段1-10*/
	private String fext1;
	private String fext2;
	private String fext3;
	private String fext4;
	private String fext5;
	private String fext6;
	private String fext7;
	private String fext8;
	private String fext9;
	private String fext10;
	public Integer getFid() {
		return fid;
	}
	public void setFid(Integer fid) {
		this.fid = fid;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Integer getProjectFid() {
		return projectFid;
	}
	public void setProjectFid(Integer projectFid) {
		this.projectFid = projectFid;
	}
	public Integer getCreaterId() {
		return createrId;
	}
	public void setCreaterId(Integer createrId) {
		this.createrId = createrId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
	
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	public AgreementFile() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getViewName() {
		return viewName;
	}
	public void setViewName(String viewName) {
		this.viewName = viewName;
	}
	public AgreementFile(String viewName,String fileName, String filePath,
			Integer projectFid, Integer createrId,String createName){
		super();
		this.viewName=viewName;
		this.fileName = fileName;
		this.filePath = filePath;
		this.projectFid = projectFid;
		this.createrId = createrId;
		this.createName = createName;
	} 
	
	
}
