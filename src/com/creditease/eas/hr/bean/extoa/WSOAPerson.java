package com.creditease.eas.hr.bean.extoa;

import java.io.Serializable;
import java.util.Date;

/***
 * 人员信息的接口
 * @WSOAOrganization.java
 * created at 2013-10-17 上午10:32:18 by ygq
 * 
 * @author ygq({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class WSOAPerson implements Serializable{
	 private String fpersonid;//员工id
	 private String fusercode;//员工编码
	 private String fusername;//员工姓名
	 private String floginname;//登录名
	 private String fuseremail;//邮箱
	 private String fuseractiveFnumber;//员工状态编码
	 private String fuseractiveFname;//员工状态名称
	 private String finservice;//员工状态
	 private String fuserstryle;//籍贯
	 private String fusersex;//员工性别
	 private Date fbirthday;//员工生日
	 private String fpostcode;//邮政编码
	 private String faddress;//通信地址
	 private Date fregistertime;//入职日期
	 private String fcityNumber;//城市编码
	 private String fcityName;//城市名称
	 
	 private String fcell;//手机号码
	 private String fofficephone;//办公室电话
	 private String fidcardno;//身份证号码
	 private String fisbyj;//是否备用金负责人
	 private Date flastupdatetime;//最后修改时间
	 private String fbackupEMail;//私人邮箱
	 private String fbackupMobile;//私人手机号码
	 private String fqq;//qq
	private Integer totalPersonCount;//总条数
 
  	private String	ext1;//		扩展字段一
	private String	ext2;//		扩展字段二
	private String	ext3;//		扩展字段三
	private String	ext4;//		扩展字段四
	private String	ext5;//		扩展字段五
	private String	ext6;//		扩展字段六
	private String	ext7;//		扩展字段七
	private String	ext8;//		扩展字段八
	private String	ext9;//		扩展字段九
	private String ext10;//扩展字段十
	public String getFpersonid() {
		return fpersonid;
	}
	public void setFpersonid(String fpersonid) {
		this.fpersonid = fpersonid;
	}
	public String getFusercode() {
		return fusercode;
	}
	public void setFusercode(String fusercode) {
		this.fusercode = fusercode;
	}
	public String getFusername() {
		return fusername;
	}
	public void setFusername(String fusername) {
		this.fusername = fusername;
	}
	public String getFloginname() {
		return floginname;
	}
	public void setFloginname(String floginname) {
		this.floginname = floginname;
	}
	public String getFuseremail() {
		return fuseremail;
	}
	public void setFuseremail(String fuseremail) {
		this.fuseremail = fuseremail;
	}
	public String getFuseractiveFnumber() {
		return fuseractiveFnumber;
	}
	public void setFuseractiveFnumber(String fuseractiveFnumber) {
		this.fuseractiveFnumber = fuseractiveFnumber;
	}
	public String getFuseractiveFname() {
		return fuseractiveFname;
	}
	public void setFuseractiveFname(String fuseractiveFname) {
		this.fuseractiveFname = fuseractiveFname;
	}
	public String getFinservice() {
		return finservice;
	}
	public void setFinservice(String finservice) {
		this.finservice = finservice;
	}
	public String getFuserstryle() {
		return fuserstryle;
	}
	public void setFuserstryle(String fuserstryle) {
		this.fuserstryle = fuserstryle;
	}
	public String getFusersex() {
		return fusersex;
	}
	public void setFusersex(String fusersex) {
		this.fusersex = fusersex;
	}
	public Date getFbirthday() {
		return fbirthday;
	}
	public void setFbirthday(Date fbirthday) {
		this.fbirthday = fbirthday;
	}
	public String getFpostcode() {
		return fpostcode;
	}
	public void setFpostcode(String fpostcode) {
		this.fpostcode = fpostcode;
	}
	public String getFaddress() {
		return faddress;
	}
	public void setFaddress(String faddress) {
		this.faddress = faddress;
	}
	public Date getFregistertime() {
		return fregistertime;
	}
	public void setFregistertime(Date fregistertime) {
		this.fregistertime = fregistertime;
	}
	public String getFcityNumber() {
		return fcityNumber;
	}
	public void setFcityNumber(String fcityNumber) {
		this.fcityNumber = fcityNumber;
	}
	public String getFcityName() {
		return fcityName;
	}
	public void setFcityName(String fcityName) {
		this.fcityName = fcityName;
	}
	public String getFcell() {
		return fcell;
	}
	public void setFcell(String fcell) {
		this.fcell = fcell;
	}
	public String getFofficephone() {
		return fofficephone;
	}
	public void setFofficephone(String fofficephone) {
		this.fofficephone = fofficephone;
	}
	public String getFidcardno() {
		return fidcardno;
	}
	public void setFidcardno(String fidcardno) {
		this.fidcardno = fidcardno;
	}
	public String getFisbyj() {
		return fisbyj;
	}
	public void setFisbyj(String fisbyj) {
		this.fisbyj = fisbyj;
	}
	public Date getFlastupdatetime() {
		return flastupdatetime;
	}
	public void setFlastupdatetime(Date flastupdatetime) {
		this.flastupdatetime = flastupdatetime;
	}
	public Integer getTotalPersonCount() {
		return totalPersonCount;
	}
	public void setTotalPersonCount(Integer totalPersonCount) {
		this.totalPersonCount = totalPersonCount;
	}
	 public String getFbackupEMail() {
		return fbackupEMail;
	}
	public void setFbackupEMail(String fbackupEMail) {
		this.fbackupEMail = fbackupEMail;
	}
	public String getFbackupMobile() {
		return fbackupMobile;
	}
	public void setFbackupMobile(String fbackupMobile) {
		this.fbackupMobile = fbackupMobile;
	}
	public String getFqq() {
		return fqq;
	}
	public void setFqq(String fqq) {
		this.fqq = fqq;
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
}