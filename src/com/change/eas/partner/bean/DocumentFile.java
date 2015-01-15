package com.change.eas.partner.bean;

/**
 * 法律文件
 * @author Administrator
 *
 */
public class DocumentFile {

	private Long id;
	
	/**有限合伙公司id*/
	private Long limitedpartnerid;
	
	/**有限合伙公司名称*/
	private String limitedpartnername;
	
	/**法律文件名称*/
	private String filename;
	
	/**制作人*/
	private String makeuser;
	
	private String filepath;
	
	private Long pid;
	
	private Long templateid;




	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getLimitedpartnerid() {
		return limitedpartnerid;
	}

	public void setLimitedpartnerid(Long limitedpartnerid) {
		this.limitedpartnerid = limitedpartnerid;
	}

	public String getLimitedpartnername() {
		return limitedpartnername;
	}

	public void setLimitedpartnername(String limitedpartnername) {
		this.limitedpartnername = limitedpartnername;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getMakeuser() {
		return makeuser;
	}

	public void setMakeuser(String makeuser) {
		this.makeuser = makeuser;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public Long getTemplateid() {
		return templateid;
	}

	public void setTemplateid(Long templateid) {
		this.templateid = templateid;
	}
	
}
