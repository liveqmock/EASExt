package com.change.eas.partner.bean;

/**
 * 法律文件完成数量统计
 * @author Administrator
 *
 */
public class LawFile {

	private Long id;
	
	/**投资理财部门 */
	private String department;
	
	/**有限合伙公司名称 */
	private String limitedpartnername;
	
	/**有限合伙公司id*/
	private Long limitedpartnerid;
	
	/**城市 */
	private String city;
	
	private Long cityid;
	
	/**制作人 */
	private String makeuser;
	
	/**已完成数量 */
	private int done;
	
	/**未完成数量 */
	private int undone;

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

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Long getCityid() {
		return cityid;
	}

	public void setCityid(Long cityid) {
		this.cityid = cityid;
	}

	public String getMakeuser() {
		return makeuser;
	}

	public void setMakeuser(String makeuser) {
		this.makeuser = makeuser;
	}

	public int getDone() {
		return done;
	}

	public void setDone(int done) {
		this.done = done;
	}

	public int getUndone() {
		return undone;
	}

	public void setUndone(int undone) {
		this.undone = undone;
	}

	public String getLimitedpartnername() {
		return limitedpartnername;
	}

	public void setLimitedpartnername(String limitedpartnername) {
		this.limitedpartnername = limitedpartnername;
	}
}
