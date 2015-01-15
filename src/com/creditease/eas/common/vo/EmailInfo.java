package com.creditease.eas.common.vo;
/**
 * 邮件缓存数据传输对象
 * @author lining
 * @since 2014-6-1
 */
public class EmailInfo {

	/**
	 * 邮件记录的主键属性
	 */
	private String fid;
	/**
	 * 生成邮件信息的业务标示
	 */
	private String businessId = "";
	/**
	 * 发送邮箱
	 */
	private String senderAddr = "";
	/**
	 * 发送邮箱密码密文
	 */
	private String senderPswd = "";
	/**
	 * 收件人展示名称
	 */
	private String receiverNames = "";
	/**
	 * 收件箱列表
	 */
	private String receiverAddrs = "";
	/**
	 * 抄送地址列表
	 */
	private String ccAddrs = "";
	/**
	 * 邮件主题
	 */
	private String theme = "";
	/**
	 * 邮件内容
	 */
	private String content = "";
	/**
	 * 邮件内容文件的保存路径
	 */
	private String contentPath = "";
	/**
	 * 信息类型
	 */
	private String infoType = "";
	/**
	 * 发送日期
	 */
	private String sendDate = "";
	/**
	 * 发送时间
	 */
	private String sendTime = "";
	/**
	 * 邮件信息创建人
	 */
	private String createUser = "";
	/**
	 * 邮件中的图片
	 */
	private String images = "";
	/**
	 * 邮件附件
	 */
	private String files = "";
	
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	public String getSenderAddr() {
		return senderAddr;
	}
	public void setSenderAddr(String senderAddr) {
		this.senderAddr = senderAddr;
	}
	public String getSenderPswd() {
		return senderPswd;
	}
	public void setSenderPswd(String senderPswd) {
		this.senderPswd = senderPswd;
	}
	public String getReceiverNames() {
		return receiverNames;
	}
	public void setReceiverNames(String receiverNames) {
		this.receiverNames = receiverNames;
	}
	public String getReceiverAddrs() {
		return receiverAddrs;
	}
	public void setReceiverAddrs(String receiverAddrs) {
		this.receiverAddrs = receiverAddrs;
	}
	public String getCcAddrs() {
		return ccAddrs;
	}
	public void setCcAddrs(String ccAddrs) {
		this.ccAddrs = ccAddrs;
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
	public String getContentPath() {
		return contentPath;
	}
	public void setContentPath(String contentPath) {
		this.contentPath = contentPath;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	public String getFiles() {
		return files;
	}
	public void setFiles(String files) {
		this.files = files;
	}
	public String getInfoType() {
		return infoType;
	}
	public void setInfoType(String infoType) {
		this.infoType = infoType;
	}
	public String getSendDate() {
		return sendDate;
	}
	public void setSendDate(String SendDate) {
		this.sendDate = SendDate;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	
	
}
