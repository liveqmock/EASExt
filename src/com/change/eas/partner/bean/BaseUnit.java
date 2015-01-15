package com.change.eas.partner.bean;

/**
 * 金蝶系统中有限合伙公司
 * @author Administrator
 *
 */
public class BaseUnit {
	
	/**编码 */
	private String fcode;
	
	/**助记码 */
	private String fnumber;
	
	/**有限合伙公司名称 */
	private String fname_l2;

	public String getFcode() {
		return fcode;
	}

	public void setFcode(String fcode) {
		this.fcode = fcode;
	}

	public String getFnumber() {
		return fnumber;
	}

	public void setFnumber(String fnumber) {
		this.fnumber = fnumber;
	}

	public String getFname_l2() {
		return fname_l2;
	}

	public void setFname_l2(String fnameL2) {
		fname_l2 = fnameL2;
	}
	
}
