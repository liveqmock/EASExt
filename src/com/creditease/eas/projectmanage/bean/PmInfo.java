package com.creditease.eas.projectmanage.bean;

import java.util.Date;

/**
 * @PmInfo.java created at 2014-03-13 下午03:28:26 by admin
 * 
 */
/**
 * @PmInfo.java
 * created at 2014-4-1 下午02:09:38 by sunxiaofeng
 * 
 * @author Administrator({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class PmInfo {
	private Integer id;
	private String pmName;// pm名称
	private Integer userId;// 用户id
	private String pmEmail;// 邮箱
	private Integer fCreateUserId;// 创建人
	private Date fCreateTime;// 创建时间
    private Integer lastupdaterId;//最后修改人id
    private Date lastupdateTime;//最后修改人时间
	// 扩展字段
	private String fext1;
	private String fext2;
	private String fext3;
	private String fext4;
	private String fext5;


	public PmInfo(Integer id, String pmName, Integer userId, String pmEmail,
			Integer fCreateUserId, Date fCreateTime, Integer lastupdaterId,
			Date lastupdateTime, String fext1, String fext2, String fext3,
			String fext4, String fext5) {
		super();
		this.id = id;
		this.pmName = pmName;
		this.userId = userId;
		this.pmEmail = pmEmail;
		this.fCreateUserId = fCreateUserId;
		this.fCreateTime = fCreateTime;
		this.lastupdaterId = lastupdaterId;
		this.lastupdateTime = lastupdateTime;
		this.fext1 = fext1;
		this.fext2 = fext2;
		this.fext3 = fext3;
		this.fext4 = fext4;
		this.fext5 = fext5;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPmName() {
		return pmName;
	}

	public void setPmName(String pmName) {
		this.pmName = pmName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getPmEmail() {
		return pmEmail;
	}

	public void setPmEmail(String pmEmail) {
		this.pmEmail = pmEmail;
	}

	public Integer getfCreateUserId() {
		return fCreateUserId;
	}

	public void setfCreateUserId(Integer fCreateUserId) {
		this.fCreateUserId = fCreateUserId;
	}

	public Date getfCreateTime() {
		return fCreateTime;
	}

	public void setfCreateTime(Date fCreateTime) {
		this.fCreateTime = fCreateTime;
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
    
	public Integer getLastupdaterId() {
		return lastupdaterId;
	}

	public void setLastupdaterId(Integer lastupdaterId) {
		this.lastupdaterId = lastupdaterId;
	}

	public Date getLastupdateTime() {
		return lastupdateTime;
	}

	public void setLastupdateTime(Date lastupdateTime) {
		this.lastupdateTime = lastupdateTime;
	}

	public PmInfo() {
		super();
	}

}
