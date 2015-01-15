package com.creditease.eas.finance.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 汇率
 * @Test.java
 * created at 2013-5-2 下午10:01:46 by ygq
 * 
 * @author ygq({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class ExchangeRate implements Serializable{
	private String fnumber;//汇率编码
	public String getFnumber() {
		return fnumber;
	}
	public void setFnumber(String fnumber) {
		this.fnumber = fnumber;
	}
	private String tabfnumber;
	private String tabfname;
	private String srcCurfname;
	private String targetCurfname;
	private double fconvertRate;
	private Date favailTime;
	private Date finvalidTime;
	
	private String srcCurfnumber;
	private String targetCurfnumber;
	
	
	
	
	public String getSrcCurfnumber() {
		return srcCurfnumber;
	}
	public void setSrcCurfnumber(String srcCurfnumber) {
		this.srcCurfnumber = srcCurfnumber;
	}
	public String getTargetCurfnumber() {
		return targetCurfnumber;
	}
	public void setTargetCurfnumber(String targetCurfnumber) {
		this.targetCurfnumber = targetCurfnumber;
	}
	public Date getFavailTime() {
		return favailTime;
	}
	public void setFavailTime(Date favailTime) {
		this.favailTime = favailTime;
	}
	public Date getFinvalidTime() {
		return finvalidTime;
	}
	public void setFinvalidTime(Date finvalidTime) {
		this.finvalidTime = finvalidTime;
	}
	private String	ext1;//		扩展字段一
	private String	ext2;//		扩展字段二
	private String	ext3;//		扩展字段三
	private String	ext4;//		扩展字段四
	private String	ext5;//		扩展字段五
	private String	ext6;//		扩展字段六
	private String	ext7;//		扩展字段七
	private String	ext8;//		扩展字段八
	private String	ext9;//		扩展字段九
	private String	ext10;//扩展字段十
	public String getTabfnumber() {
		return tabfnumber;
	}
	public void setTabfnumber(String tabfnumber) {
		this.tabfnumber = tabfnumber;
	}
	public String getTabfname() {
		return tabfname;
	}
	public void setTabfname(String tabfname) {
		this.tabfname = tabfname;
	}
	public String getSrcCurfname() {
		return srcCurfname;
	}
	public void setSrcCurfname(String srcCurfname) {
		this.srcCurfname = srcCurfname;
	}
	public String getTargetCurfname() {
		return targetCurfname;
	}
	public void setTargetCurfname(String targetCurfname) {
		this.targetCurfname = targetCurfname;
	}
	public double getFconvertRate() {
		return fconvertRate;
	}
	public void setFconvertRate(double fconvertRate) {
		this.fconvertRate = fconvertRate;
	}
	public String getExt1() {
		return ext1;
	}
	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}
	public String getExt2() {
		return ext2;
	}
	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}
	public String getExt3() {
		return ext3;
	}
	public void setExt3(String ext3) {
		this.ext3 = ext3;
	}
	public String getExt4() {
		return ext4;
	}
	public void setExt4(String ext4) {
		this.ext4 = ext4;
	}
	public String getExt5() {
		return ext5;
	}
	public void setExt5(String ext5) {
		this.ext5 = ext5;
	}
	public String getExt6() {
		return ext6;
	}
	public void setExt6(String ext6) {
		this.ext6 = ext6;
	}
	public String getExt7() {
		return ext7;
	}
	public void setExt7(String ext7) {
		this.ext7 = ext7;
	}
	public String getExt8() {
		return ext8;
	}
	public void setExt8(String ext8) {
		this.ext8 = ext8;
	}
	public String getExt9() {
		return ext9;
	}
	public void setExt9(String ext9) {
		this.ext9 = ext9;
	}
	public String getExt10() {
		return ext10;
	}
	public void setExt10(String ext10) {
		this.ext10 = ext10;
	}
	private Date flastUpdateTime;
	public Date getFlastUpdateTime() {
		return flastUpdateTime;
	}
	public void setFlastUpdateTime(Date flastUpdateTime) {
		this.flastUpdateTime = flastUpdateTime;
	}
}
