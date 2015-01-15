package com.creditease.eas.finance.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 成本中心
 * @CostCenter.java
 * created at 2013-5-2 下午10:20:01 by ygq
 * 
 * @author ygq({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class CostCenter implements Serializable{
		private String fnumber;//编码
		private String fname;//名称
		private String		fparentNumber;//上级组织单元（对应的是上级number)
		private String		fdisplayName;//显示名称
		private String		fdescription;//描述
		private Integer		flevel;//级次(最多到8级)
		private Integer		fisLeaf;//是否是叶子节点（o:不是,1:是)
		private String		fisAssistantOrg;//是否副账簿组织（o:不是，1：是）
		private String		forgTypeStr;//组织类型信息
//		private Integer		fisOUSealUp;//是否封存：（o:未封存，1：封存）
		
		private Integer		status;//是否封存：状态 1：可用、0：不可用
		
		
	
		public Integer getStatus() {
			return status;
		}
		public void setStatus(Integer status) {
			this.status = status;
		}
		public String getFparentNumber() {
			return fparentNumber;
		}
		public void setFparentNumber(String fparentNumber) {
			this.fparentNumber = fparentNumber;
		}
		public String getFdisplayName() {
			return fdisplayName;
		}
		public void setFdisplayName(String fdisplayName) {
			this.fdisplayName = fdisplayName;
		}
		public String getFdescription() {
			return fdescription;
		}
		public void setFdescription(String fdescription) {
			this.fdescription = fdescription;
		}
		public Integer getFlevel() {
			return flevel;
		}
		public void setFlevel(Integer flevel) {
			this.flevel = flevel;
		}
		public Integer getFisLeaf() {
			return fisLeaf;
		}
		public void setFisLeaf(Integer fisLeaf) {
			this.fisLeaf = fisLeaf;
		}
		public String getFisAssistantOrg() {
			return fisAssistantOrg;
		}
		public void setFisAssistantOrg(String fisAssistantOrg) {
			this.fisAssistantOrg = fisAssistantOrg;
		}
		public String getForgTypeStr() {
			return forgTypeStr;
		}
		public void setForgTypeStr(String forgTypeStr) {
			this.forgTypeStr = forgTypeStr;
		}
		public String getFnumber() {
			return fnumber;
		}
		public void setFnumber(String fnumber) {
			this.fnumber = fnumber;
		}
		public String getFname() {
			return fname;
		}
		public void setFname(String fname) {
			this.fname = fname;
		}
		private String	errCode;//异常编码（0000：正常，0001：异常)
		private String	errMessage;//异常信息
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
		public String getErrCode() {
			return errCode;
		}
		public void setErrCode(String errCode) {
			this.errCode = errCode;
		}
		public String getErrMessage() {
			return errMessage;
		}
		public void setErrMessage(String errMessage) {
			this.errMessage = errMessage;
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
