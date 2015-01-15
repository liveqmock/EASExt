package com.creditease.eas.common.vo;
/**
 * 邮件信息数据传输类
 * @author lining
 * @since 2014-4-17
 */
public class MailInfo {
	
	/**
	 * 邮件信息的唯一标识
	 */
	private String infoId;
	/**
	 * 发件邮箱
	 */
	private String from;
	/**
	 * 发件箱密码
	 */
	private String password;
	/**
	 * 收件邮箱列表
	 */
	private String[] tos;
	/**
	 * 抄送邮箱地址
	 */
	private String[] cc;
	/**
	 * 密抄地址
	 */
	private String[] bcc;
	/**
	 * 邮件主题
	 */
	private String theme;
	/**
	 * 邮件文本信息
	 */
	private String content;
	/**
	 * 邮件图片的路径
	 */
	private String[] images;
	/**
	 * 邮件附件的路径
	 */
	private String[] files;
	
	public String getInfoId() {
		return infoId;
	}
	public void setInfoId(String infoId) {
		this.infoId = infoId;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String[] getTos() {
		return tos;
	}
	public void setTos(String[] tos) {
		this.tos = tos;
	}
	public String[] getCc() {
		return cc;
	}
	public void setCc(String[] cc) {
		this.cc = cc;
	}
	public String[] getBcc() {
		return bcc;
	}
	public void setBcc(String[] bcc) {
		this.bcc = bcc;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String[] getImages() {
		return images;
	}
	public void setImages(String[] images) {
		this.images = images;
	}
	public String[] getFiles() {
		return files;
	}
	public void setFiles(String[] files) {
		this.files = files;
	}
	
	
	

	
}
