package com.creditease.eas.hr.bean.extoa;

import java.io.Serializable;
import java.util.Date;

/**
 * 职位信息的接口
 * @WSOAPOsition.java
 * created at 2013-10-17 上午11:01:23 by ygq
 * 
 * @author ygq({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class WSOAPosition implements Serializable{
	  private static final long serialVersionUID = 1L;
	  private String fid;
	  private String fnumber;
	  private String fname;
	  private String fpositionLevelFnumber;//职位等级（职位等级字典表）
	  private String fpositionLevelName;//职位等级（中文名称）
	  private String fpositionlayerFnumber;//职层(编码)
	  private String fpositionLayerName;//职层（中文名称）
	  private String fpositionTypeFnumber;//职位类别（职位类别字典）
	  private String fpositionTypeName;//职位类别（中文名称）
	  private String fjobFNumber;//职务编码
	  private String fjobFName;//职务名称
	  private String fparentId;
	  private String fparentNumber;
	  private String fdeptID;//部门ID
	  private String fdeptNumber;//部门ID
	  private String fsimplename;//简称
	  private Integer fsortCode;//职位全集排序码
	  private Integer fdeletedStatus;
	  private String fdescription;
	  private Date fcreateTime;//创建时间
	  private Date flastUpdateTime;//创建时间
	  private Integer totalPositionCount;//职位总条数
	    private String	ext1;//		扩展字段一
		private String	ext2;//		扩展字段二
		public String getFpositionLevelFnumber() {
			return fpositionLevelFnumber;
		}
		public void setFpositionLevelFnumber(String fpositionLevelFnumber) {
			this.fpositionLevelFnumber = fpositionLevelFnumber;
		}
		public String getFpositionLevelName() {
			return fpositionLevelName;
		}
		public void setFpositionLevelName(String fpositionLevelName) {
			this.fpositionLevelName = fpositionLevelName;
		}
		public String getFpositionlayerFnumber() {
			return fpositionlayerFnumber;
		}
		public void setFpositionlayerFnumber(String fpositionlayerFnumber) {
			this.fpositionlayerFnumber = fpositionlayerFnumber;
		}
		public String getFpositionLayerName() {
			return fpositionLayerName;
		}
		public void setFpositionLayerName(String fpositionLayerName) {
			this.fpositionLayerName = fpositionLayerName;
		}
		public String getFpositionTypeFnumber() {
			return fpositionTypeFnumber;
		}
		public void setFpositionTypeFnumber(String fpositionTypeFnumber) {
			this.fpositionTypeFnumber = fpositionTypeFnumber;
		}
		public String getFpositionTypeName() {
			return fpositionTypeName;
		}
		public void setFpositionTypeName(String fpositionTypeName) {
			this.fpositionTypeName = fpositionTypeName;
		}
		public String getFjobFNumber() {
			return fjobFNumber;
		}
		public void setFjobFNumber(String fjobFNumber) {
			this.fjobFNumber = fjobFNumber;
		}
		public String getFjobFName() {
			return fjobFName;
		}
		public void setFjobFName(String fjobFName) {
			this.fjobFName = fjobFName;
		}
		public String getFparentNumber() {
			return fparentNumber;
		}
		public void setFparentNumber(String fparentNumber) {
			this.fparentNumber = fparentNumber;
		}
		public String getFdeptNumber() {
			return fdeptNumber;
		}
		public void setFdeptNumber(String fdeptNumber) {
			this.fdeptNumber = fdeptNumber;
		}
		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		private String	ext3;//		扩展字段三
		private String	ext4;//		扩展字段四
		private String	ext5;//		扩展字段五
		private String	ext6;//		扩展字段六
		private String	ext7;//		扩展字段七
		private String	ext8;//		扩展字段八
		private String	ext9;//		扩展字段九
		private String ext10;//扩展字段十
		
		public Integer getTotalPositionCount() {
			return totalPositionCount;
		}
		public void setTotalPositionCount(Integer totalPositionCount) {
			this.totalPositionCount = totalPositionCount;
		}
		public String getFid() {
			return fid;
		}
		public void setFid(String fid) {
			this.fid = fid;
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
		public String getFsimplename() {
			return fsimplename;
		}
		public void setFsimplename(String fsimplename) {
			this.fsimplename = fsimplename;
		}
		public Integer getFdeletedStatus() {
			return fdeletedStatus;
		}
		public void setFdeletedStatus(Integer fdeletedStatus) {
			this.fdeletedStatus = fdeletedStatus;
		}
		public String getFdescription() {
			return fdescription;
		}
		public void setFdescription(String fdescription) {
			this.fdescription = fdescription;
		}
		public String getFparentId() {
			return fparentId;
		}
		public void setFparentId(String fparentId) {
			this.fparentId = fparentId;
		}
		public Integer getFsortCode() {
			return fsortCode;
		}
		public void setFsortCode(Integer fsortCode) {
			this.fsortCode = fsortCode;
		}
		public Date getFcreateTime() {
			return fcreateTime;
		}
		public void setFcreateTime(Date fcreateTime) {
			this.fcreateTime = fcreateTime;
		}
		public Date getFlastUpdateTime() {
			return flastUpdateTime;
		}
		public void setFlastUpdateTime(Date flastUpdateTime) {
			this.flastUpdateTime = flastUpdateTime;
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
		public String getFdeptID() {
			return fdeptID;
		}
		public void setFdeptID(String fdeptID) {
			this.fdeptID = fdeptID;
		}
}