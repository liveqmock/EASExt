/**
 * 
 */
package com.creditease.eas.admin.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @Menu.java 菜单bean，系统左侧导航栏动态加载的数据
 * created at 2013-8-8 下午03:18:27 by caoyong
 * 
 * @author caoyong({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class Menu implements Serializable{

	/**主键id**/
	private Integer id;
	/**是否叶子节点0否；1是；默认1**/
	private Integer leaf;
	/**菜单显示名称**/
	private String name;
	/**窗口位置**/
	private String target;
	/**链接显示title**/
	private String title;
	/**链接URL地址**/
	private String url;
	/**上级id**/
	private Integer parentId;
	/**同级节点顺序**/
	private Integer subSequence;
	/**创建时间**/
	private Date createTime;
	/**菜单级别**/
	private Integer level;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the target
	 */
	public String getTarget() {
		return target;
	}
	/**
	 * @param target the target to set
	 */
	public void setTarget(String target) {
		this.target = target;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the subSequence
	 */
	public Integer getSubSequence() {
		return subSequence;
	}
	/**
	 * @param subSequence the subSequence to set
	 */
	public void setSubSequence(Integer subSequence) {
		this.subSequence = subSequence;
	}
	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * @return the leaf
	 */
	public Integer getLeaf() {
		return leaf;
	}
	/**
	 * @param leaf the leaf to set
	 */
	public void setLeaf(Integer leaf) {
		this.leaf = leaf;
	}
	/**
	 * @return the parentId
	 */
	public Integer getParentId() {
		return parentId;
	}
	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	/**
	 * @return the level
	 */
	public Integer getLevel() {
		return level;
	}
	/**
	 * @param level the level to set
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}
}
