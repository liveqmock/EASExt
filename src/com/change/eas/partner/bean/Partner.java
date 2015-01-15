package com.change.eas.partner.bean;

import com.change.eas.util.LimitedpartnerUtil;

/**
 * 有限合伙人
 * @author Administrator
 *
 */
public class Partner {

	private Long id;
	
	/**有限合伙公司id*/
	private Long pid;
	
	/**有限合伙人姓名*/
	private String partner_name;
	
	/**有限合伙人性别*/
	private String partner_sex;
	
	/**有限合伙人民族*/
	private String partner_nation;
	
	/**有限合伙人户籍地址*/
	private String partner_addr;
	
	/**有限合伙人证件号码*/
	private String partner_IDCard;
	
	/**有限合伙人国籍*/
	private String partner_country;
	
	/**有限合伙人出资额*/
	private double partner_amount;
	
	/**有限合伙人出资比例*/
	private double partner_percent;
	
	private String partner_percentstr;
	
	
	
	private String partner_date;
	
	private String partner_note;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getPartner_name() {
		return partner_name;
	}

	public void setPartner_name(String partnerName) {
		partner_name = partnerName;
	}

	public String getPartner_sex() {
		return partner_sex;
	}

	public void setPartner_sex(String partnerSex) {
		partner_sex = partnerSex;
	}

	public String getPartner_nation() {
		return partner_nation;
	}

	public void setPartner_nation(String partnerNation) {
		partner_nation = partnerNation;
	}

	public String getPartner_addr() {
		return partner_addr;
	}

	public void setPartner_addr(String partnerAddr) {
		partner_addr = partnerAddr;
	}

	public String getPartner_IDCard() {
		return partner_IDCard;
	}

	public void setPartner_IDCard(String partnerIDCard) {
		partner_IDCard = partnerIDCard;
	}

	public String getPartner_country() {
		return partner_country;
	}

	public void setPartner_country(String partnerCountry) {
		partner_country = partnerCountry;
	}

	public String getPartner_amountStr() {
		return LimitedpartnerUtil.getNumber(partner_amount);
	}
	public double getPartner_amount() {
		return partner_amount;
	}

	public void setPartner_amount(double partnerAmount) {
		partner_amount = partnerAmount;
	}

	public double getPartner_percent() {
		return partner_percent;
	}

	public void setPartner_percent(double partnerPercent) {
		partner_percent = partnerPercent;
	}

	public String getPartner_date() {
		return partner_date;
	}

	public void setPartner_date(String partnerDate) {
		partner_date = partnerDate;
	}

	public String getPartner_note() {
		return partner_note;
	}

	public void setPartner_note(String partnerNote) {
		partner_note = partnerNote;
	}
	public String getPartner_percentstr() {
		return partner_percentstr;
	}

	public void setPartner_percentstr(String partnerPercentstr) {
		partner_percentstr = partnerPercentstr;
	}

	public String getPartnerPercent(){
		
		return 100*partner_percent+"%";
	}

}
