package com.change.eas.partner.bean;

import java.util.Date;

import com.change.eas.util.LimitedpartnerUtil;

/**
 * 有限合伙公司
 * @author Administrator
 *
 */
public class Limitedpartner {

    public  Long id;
	
	/**有限合伙名称 */
	public String name;
	
	/**采集状态 */
	private Integer gatherstatus;
	
	/**法律文件制作状态 */
	private Integer lfmakestatus;
	
	/**工商变更状态 */
	private Integer gschangestatus;
	
	/**税务变更状态 */
	private Integer taxchangestatus;
	
	/**制表人 */
	private Long tabulator_id;
	
	/**审核人 */
	private Long auditor_id;
	
	/**单据状态  */
	private Integer orderstatus;
	
	/**暂存状态 */
	private Integer savestatus;
	
	/**提交状态 */
	private Integer submitstatus;

	/**审核状态 */
	private Integer checkstatus;
	
	/**是否法律文件生成 */
	private int isfileproducted;
	
	/**是否补充信息 */
	private int issupplement;
	
	/**普通合伙人（GP） */
	private String generalperson;
	
	/**原有限合伙人 （LP）*/
	private String formerlimitedpartner;
	
	/**代理人*/
	private String proxy;
	
	/**经营场所*/
	private String businessplace;
	
	/**出资时间*/
	private String investedtime;
	
	/**gp出资额*/
	private double gpcontribution;
	
	/**总出资额*/
	private double totalcontribution;
	
	/**gp出资比例*/
	private double gpcontributionpercent;
	
	/**gp出资比例（百分比且字符串形式）*/
	private String gpcontributionpercentstr;
	
	/**工商变更完成时间*/
	private Date gschangedate;
	
	/**税务变更完成时间*/
	private Date swchangedate;

	/**出资比例计算位数*/
	private int figure;
	
	/**出资比例计算方式*/
	private String calculationmethod;
	
	/**工商变更完成时间（字符串形式）*/
    private String gcchangedatestr;
	
    /**税务变更完成时间（字符串形式）*/
	private String shuiwuchnangedatestr;
	
	public String getGcchangedatestr() {
		return gcchangedatestr;
	}

	public void setGcchangedatestr(String gcchangedatestr) {
		this.gcchangedatestr = gcchangedatestr;
	}

	public String getShuiwuchnangedatestr() {
		return shuiwuchnangedatestr;
	}

	public void setShuiwuchnangedatestr(String shuiwuchnangedatestr) {
		this.shuiwuchnangedatestr = shuiwuchnangedatestr;
	}

	public int getFigure() {
		return figure;
	}

	public void setFigure(int figure) {
		this.figure = figure;
	}

	public String getCalculationmethod() {
		return calculationmethod;
	}

	public void setCalculationmethod(String calculationmethod) {
		this.calculationmethod = calculationmethod;
	}

	public Date getGschangedate() {
		return gschangedate;
	}

	public void setGschangedate(Date gschangedate) {
		this.gschangedate = gschangedate;
	}

	public Date getSwchangedate() {
		return swchangedate;
	}

	public void setSwchangedate(Date swchangedate) {
		this.swchangedate = swchangedate;
	}

	public String getGpcontributionpercentstr() {
		return gpcontributionpercentstr;
	}

	public void setGpcontributionpercentstr(String gpcontributionpercentstr) {
		this.gpcontributionpercentstr = gpcontributionpercentstr;
	}

	public String getGpcontributionpercentStr(){
		return gpcontributionpercent*100+"%";
	}
	
	public int getIsfileproducted() {
		return isfileproducted;
	}

	public void setIsfileproducted(int isfileproducted) {
		this.isfileproducted = isfileproducted;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getGatherstatus() {
		return gatherstatus;
	}

	public void setGatherstatus(Integer gatherstatus) {
		this.gatherstatus = gatherstatus;
	}

	public Integer getLfmakestatus() {
		return lfmakestatus;
	}

	public void setLfmakestatus(Integer lfmakestatus) {
		this.lfmakestatus = lfmakestatus;
	}

	public Integer getGschangestatus() {
		return gschangestatus;
	}

	public void setGschangestatus(Integer gschangestatus) {
		this.gschangestatus = gschangestatus;
	}

	public Integer getTaxchangestatus() {
		return taxchangestatus;
	}

	public void setTaxchangestatus(Integer taxchangestatus) {
		this.taxchangestatus = taxchangestatus;
	}

	public Long getTabulator_id() {
		return tabulator_id;
	}

	public void setTabulator_id(Long tabulatorId) {
		tabulator_id = tabulatorId;
	}

	public Long getAuditor_id() {
		return auditor_id;
	}

	public void setAuditor_id(Long auditorId) {
		auditor_id = auditorId;
	}

	public Integer getOrderstatus() {
		return orderstatus;
	}

	public void setOrderstatus(Integer orderstatus) {
		this.orderstatus = orderstatus;
	}

	public Integer getSavestatus() {
		return savestatus;
	}

	public void setSavestatus(Integer savestatus) {
		this.savestatus = savestatus;
	}

	public Integer getSubmitstatus() {
		return submitstatus;
	}

	public void setSubmitstatus(Integer submitstatus) {
		this.submitstatus = submitstatus;
	}

	public Integer getCheckstatus() {
		return checkstatus;
	}

	public void setCheckstatus(Integer checkstatus) {
		this.checkstatus = checkstatus;
	}

	public String getGeneralperson() {
		return generalperson;
	}

	public void setGeneralperson(String generalperson) {
		this.generalperson = generalperson;
	}

	public String getFormerlimitedpartner() {
		return formerlimitedpartner;
	}

	public void setFormerlimitedpartner(String formerlimitedpartner) {
		this.formerlimitedpartner = formerlimitedpartner;
	}

	public String getProxy() {
		return proxy;
	}

	public void setProxy(String proxy) {
		this.proxy = proxy;
	}

	public String getBusinessplace() {
		return businessplace;
	}

	public void setBusinessplace(String businessplace) {
		this.businessplace = businessplace;
	}

	public String getInvestedtime() {
		return investedtime;
	}

	public void setInvestedtime(String investedtime) {
		this.investedtime = investedtime;
	}

	public String getGpcontributionStr() {
		return LimitedpartnerUtil.getNumber(gpcontribution);
	}
	public double getGpcontribution() {
		return gpcontribution;
	}

	public void setGpcontribution(double gpcontribution) {
		this.gpcontribution = gpcontribution;
	}

	public String getTotalcontributionStr() {
		return LimitedpartnerUtil.getNumber(totalcontribution);
	}
	public double getTotalcontribution() {
		return totalcontribution;
	}

	public void setTotalcontribution(double totalcontribution) {
		this.totalcontribution = totalcontribution;
	}

	public double getGpcontributionpercent() {
		return gpcontributionpercent;
	}

	public void setGpcontributionpercent(double gpcontributionpercent) {
		this.gpcontributionpercent = gpcontributionpercent;
	}

	public int getIssupplement() {
		return issupplement;
	}

	public void setIssupplement(int issupplement) {
		this.issupplement = issupplement;
	}
	
}
