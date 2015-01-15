package com.creditease.eas.admin.bean;

import java.io.Serializable;
import java.util.Date;
/***
 * 用户角色信息
 * 	6月8日，添加创建人，创建时间，最后修改人，最后修改时间
 * @Title:Role.java
 * @Package com.creditease.eas.admin.bean
 * created at 2014-6-8 下午06:21:59 by ygq
 * @author ygq
 * @version 1.0
 */
public class Role implements Serializable{
    private Integer id;
    private String rolename;
    //0:在用 1.不能用
    private Integer stutas;
    /**创建人**/
    private Long fcreator;
    /**最后修改人**/
    private Long flastupdator;
    private Date fcreatedate;
    private Date flastupdatetime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
    public String getRolename() {
        return rolename;
    }
 
    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
    }

    public Integer getStutas() {
        return stutas;
    }
    public void setStutas(Integer stutas) {
        this.stutas = stutas;
    }
    public Long getFcreator() {
		return fcreator;
	}

	public void setFcreator(Long fcreator) {
		this.fcreator = fcreator;
	}

	public Long getFlastupdator() {
		return flastupdator;
	}

	public void setFlastupdator(Long flastupdator) {
		this.flastupdator = flastupdator;
	}

	public Date getFcreatedate() {
		return fcreatedate;
	}

	public void setFcreatedate(Date fcreatedate) {
		this.fcreatedate = fcreatedate;
	}

	public Date getFlastupdatetime() {
		return flastupdatetime;
	}

	public void setFlastupdatetime(Date flastupdatetime) {
		this.flastupdatetime = flastupdatetime;
	}
}