package com.creditease.eas.roster.bean;

import java.util.Date;
/**
 * 投三花名册实体bean
 * @Roster.java
 * created at 2014-3-17 下午03:22:15 by zhangxin
 * 
 * @author zhangxin({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class Roster {
    private Integer fid;//id 主键
    private String fnumber;//5位员工编号
    private String longfnumber;//12位员工编号
    private String fname;//姓名
    private String fgender;//性别id
    private String fgendername;//性别value
    private String fage;//年龄
    private String fcardnum;//身份证号
    private String femail;//电子邮箱
    private String fpersonstatus;//人员状态id
    private String fpersonstatusname;//人员状态name
    private String fcity;//城市
    private String fcityname;//城市name
    private String fthreeunit;//三级部门id
    private String fthreeunitname;//三级部门name
    private String ffourunit;//四级部门id
    private String ffourunitname;//四级部门name
    private String foldfourunit;//原四级部门id
    private String foldfourunitname;//原四级部门name
    private String ffiveunit;//五级部门id
    private String ffiveunitname;//五级部门name
    private String fsixunit;//六级部门id
    private String fsixunitname;//六级部门name
    private String fteam;//团队
    private String fpositiontype;//职类id
    private String fpositiontypename;//职类name
    private String fposition;//岗位id
    private String fpositionname;//岗位name
    private String fpositionlevel;//职级id
    private String fpositionlevelname;//职级name
    private String fparentlevelname;//直接上级
    private Date fentrydate;//入职日期
    private Date fconfirmdate;//转正日期
    private Date fleavedate;//离职日期
    private Date fteamleaderdate;//大团队经理任职日期
    private Date fmobdate;//入司日期
    private String fishopeperson;//转签之前是否是hope人员成员id
    private String fishopepersonname;//转签之前是否是hope人员成员name
    private String frecruitmentchannels;//招聘渠道id
    private String frecruitmentchannelsname;//招聘渠道name
    private String frecommendname;//内部推荐人姓名
    private String fremarks;//备注
    private String fadminfid;//组织id
    private String datapower;//数据权限设置字段 根据原四级部门设置
    private String status;//状态  0-正常 1-删除
    private String ext1;  //手机号码
    private String ext2;
    private String ext3;
    private String ext4;
    private String ext5;
    private String ext6;
    private String ext7;
    private String ext8;
    private String ext9;
    private String ext10;
    private String parentteammanager;//上级团队经理
    private String parentbigteammanager;//上级大团队经理
    private String parentsalemanager;//上级营销部经理
    private String insurancecert;//保险从业资格证
    private String insurancecertname;//保险从业资格证name
    private String fundcert;//基金从业资格证
    private String fundcertname;//基金从业资格证name
    private String financialpalncert;//理财规划师持证
    private String financialpalncerttext;//理财规划师持证文本值
    
    
    public String getFinancialpalncerttext() {
		return financialpalncerttext;
	}
	public void setFinancialpalncerttext(String financialpalncerttext) {
		this.financialpalncerttext = financialpalncerttext;
	}
	public String getInsurancecertname() {
		return insurancecertname;
	}
	public void setInsurancecertname(String insurancecertname) {
		this.insurancecertname = insurancecertname;
	}
	public String getFundcertname() {
		return fundcertname;
	}
	public void setFundcertname(String fundcertname) {
		this.fundcertname = fundcertname;
	}
	public String getParentteammanager() {
		return parentteammanager;
	}
	public void setParentteammanager(String parentteammanager) {
		this.parentteammanager = parentteammanager;
	}
	public String getParentbigteammanager() {
		return parentbigteammanager;
	}
	public void setParentbigteammanager(String parentbigteammanager) {
		this.parentbigteammanager = parentbigteammanager;
	}
	public String getParentsalemanager() {
		return parentsalemanager;
	}
	public void setParentsalemanager(String parentsalemanager) {
		this.parentsalemanager = parentsalemanager;
	}
	public String getInsurancecert() {
		return insurancecert;
	}
	public void setInsurancecert(String insurancecert) {
		this.insurancecert = insurancecert;
	}
	public String getFundcert() {
		return fundcert;
	}
	public void setFundcert(String fundcert) {
		this.fundcert = fundcert;
	}
	public String getFinancialpalncert() {
		return financialpalncert;
	}
	public void setFinancialpalncert(String financialpalncert) {
		this.financialpalncert = financialpalncert;
	}
	public String getFage() {
		return fage;
	}
	public void setFage(String fage) {
		this.fage = fage;
	}
	public Integer getFid() {
        return fid;
    }
    public void setFid(Integer fid) {
        this.fid = fid;
    }
    public String getFnumber() {
        return fnumber;
    }
    public void setFnumber(String fnumber) {
        this.fnumber = fnumber == null ? null : fnumber.trim();
    }
    public String getLongfnumber() {
        return longfnumber;
    }
    public void setLongfnumber(String longfnumber) {
        this.longfnumber = longfnumber == null ? null : longfnumber.trim();
    }
    public String getFname() {
        return fname;
    }
    public void setFname(String fname) {
        this.fname = fname == null ? null : fname.trim();
    }
    public String getFgender() {
        return fgender;
    }
    public void setFgender(String fgender) {
        this.fgender = fgender == null ? null : fgender.trim();
    }
    public String getFcardnum() {
        return fcardnum;
    }
    public void setFcardnum(String fcardnum) {
        this.fcardnum = fcardnum == null ? null : fcardnum.trim();
    }
    public String getFemail() {
        return femail;
    }
    public void setFemail(String femail) {
        this.femail = femail == null ? null : femail.trim();
    }
    public String getFpersonstatus() {
        return fpersonstatus;
    }
    public void setFpersonstatus(String fpersonstatus) {
        this.fpersonstatus = fpersonstatus == null ? null : fpersonstatus.trim();
    }
    public String getFcity() {
        return fcity;
    }
    public void setFcity(String fcity) {
        this.fcity = fcity == null ? null : fcity.trim();
    }
    public String getFthreeunit() {
        return fthreeunit;
    }
    public void setFthreeunit(String fthreeunit) {
        this.fthreeunit = fthreeunit == null ? null : fthreeunit.trim();
    }
    public String getFfourunit() {
        return ffourunit;
    }
    public void setFfourunit(String ffourunit) {
        this.ffourunit = ffourunit == null ? null : ffourunit.trim();
    }
    public String getFoldfourunit() {
        return foldfourunit;
    }
    public void setFoldfourunit(String foldfourunit) {
        this.foldfourunit = foldfourunit == null ? null : foldfourunit.trim();
    }
    public String getFfiveunit() {
        return ffiveunit;
    }
    public void setFfiveunit(String ffiveunit) {
        this.ffiveunit = ffiveunit == null ? null : ffiveunit.trim();
    }
    public String getFsixunit() {
        return fsixunit;
    }
    public void setFsixunit(String fsixunit) {
        this.fsixunit = fsixunit == null ? null : fsixunit.trim();
    }
    public String getFteam() {
        return fteam;
    }
    public void setFteam(String fteam) {
        this.fteam = fteam == null ? null : fteam.trim();
    }
    public String getFpositiontype() {
        return fpositiontype;
    }
    public void setFpositiontype(String fpositiontype) {
        this.fpositiontype = fpositiontype == null ? null : fpositiontype.trim();
    }
    public String getFposition() {
        return fposition;
    }
    public void setFposition(String fposition) {
        this.fposition = fposition == null ? null : fposition.trim();
    }
    public String getFpositionlevel() {
        return fpositionlevel;
    }
    public void setFpositionlevel(String fpositionlevel) {
        this.fpositionlevel = fpositionlevel == null ? null : fpositionlevel.trim();
    }
    public String getFparentlevelname() {
        return fparentlevelname;
    }
    public void setFparentlevelname(String fparentlevelname) {
        this.fparentlevelname = fparentlevelname == null ? null : fparentlevelname.trim();
    }
    public Date getFentrydate() {
        return fentrydate;
    }
    public void setFentrydate(Date fentrydate) {
        this.fentrydate = fentrydate;
    }
    public Date getFconfirmdate() {
        return fconfirmdate;
    }
    public void setFconfirmdate(Date fconfirmdate) {
        this.fconfirmdate = fconfirmdate;
    }
    public Date getFleavedate() {
        return fleavedate;
    }
    public void setFleavedate(Date fleavedate) {
        this.fleavedate = fleavedate;
    }
    public Date getFteamleaderdate() {
        return fteamleaderdate;
    }
    public void setFteamleaderdate(Date fteamleaderdate) {
        this.fteamleaderdate = fteamleaderdate;
    }
    public Date getFmobdate() {
        return fmobdate;
    }
    public void setFmobdate(Date fmobdate) {
        this.fmobdate = fmobdate;
    }
    
    public String getFishopeperson() {
		return fishopeperson;
	}
	public void setFishopeperson(String fishopeperson) {
		this.fishopeperson = fishopeperson;
	}
	public String getFrecruitmentchannels() {
        return frecruitmentchannels;
    }
    public void setFrecruitmentchannels(String frecruitmentchannels) {
        this.frecruitmentchannels = frecruitmentchannels == null ? null : frecruitmentchannels.trim();
    }
    public String getFrecommendname() {
        return frecommendname;
    }
    public void setFrecommendname(String frecommendname) {
        this.frecommendname = frecommendname == null ? null : frecommendname.trim();
    }
    public String getFremarks() {
        return fremarks;
    }
    public void setFremarks(String fremarks) {
        this.fremarks = fremarks == null ? null : fremarks.trim();
    }
    public String getFadminfid() {
        return fadminfid;
    }
    public void setFadminfid(String fadminfid) {
        this.fadminfid = fadminfid == null ? null : fadminfid.trim();
    }
    public String getDatapower() {
        return datapower;
    }
    public void setDatapower(String datapower) {
        this.datapower = datapower == null ? null : datapower.trim();
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
    public String getExt1() {
        return ext1;
    }
    public void setExt1(String ext1) {
        this.ext1 = ext1 == null ? null : ext1.trim();
    }
    public String getExt2() {
        return ext2;
    }
    public void setExt2(String ext2) {
        this.ext2 = ext2 == null ? null : ext2.trim();
    }
    public String getExt3() {
        return ext3;
    }
    public void setExt3(String ext3) {
        this.ext3 = ext3 == null ? null : ext3.trim();
    }
    public String getExt4() {
        return ext4;
    }
    public void setExt4(String ext4) {
        this.ext4 = ext4 == null ? null : ext4.trim();
    }
    public String getExt5() {
        return ext5;
    }
    public void setExt5(String ext5) {
        this.ext5 = ext5 == null ? null : ext5.trim();
    }
    public String getExt6() {
        return ext6;
    }
    public void setExt6(String ext6) {
        this.ext6 = ext6 == null ? null : ext6.trim();
    }
    public String getExt7() {
        return ext7;
    }
    public void setExt7(String ext7) {
        this.ext7 = ext7 == null ? null : ext7.trim();
    }
    public String getExt8() {
        return ext8;
    }
    public void setExt8(String ext8) {
        this.ext8 = ext8 == null ? null : ext8.trim();
    }
    public String getExt9() {
        return ext9;
    }
    public void setExt9(String ext9) {
        this.ext9 = ext9 == null ? null : ext9.trim();
    }
    public String getExt10() {
        return ext10;
    }
    public void setExt10(String ext10) {
        this.ext10 = ext10 == null ? null : ext10.trim();
    }
	public String getFgendername() {
		return fgendername;
	}
	public void setFgendername(String fgendername) {
		this.fgendername = fgendername;
	}
	public String getFpersonstatusname() {
		return fpersonstatusname;
	}
	public void setFpersonstatusname(String fpersonstatusname) {
		this.fpersonstatusname = fpersonstatusname;
	}
	public String getFcityname() {
		return fcityname;
	}
	public void setFcityname(String fcityname) {
		this.fcityname = fcityname;
	}
	public String getFthreeunitname() {
		return fthreeunitname;
	}
	public void setFthreeunitname(String fthreeunitname) {
		this.fthreeunitname = fthreeunitname;
	}
	public String getFfourunitname() {
		return ffourunitname;
	}
	public void setFfourunitname(String ffourunitname) {
		this.ffourunitname = ffourunitname;
	}
	public String getFoldfourunitname() {
		return foldfourunitname;
	}
	public void setFoldfourunitname(String foldfourunitname) {
		this.foldfourunitname = foldfourunitname;
	}
	public String getFfiveunitname() {
		return ffiveunitname;
	}
	public void setFfiveunitname(String ffiveunitname) {
		this.ffiveunitname = ffiveunitname;
	}
	public String getFsixunitname() {
		return fsixunitname;
	}
	public void setFsixunitname(String fsixunitname) {
		this.fsixunitname = fsixunitname;
	}
	public String getFpositiontypename() {
		return fpositiontypename;
	}
	public void setFpositiontypename(String fpositiontypename) {
		this.fpositiontypename = fpositiontypename;
	}
	public String getFpositionname() {
		return fpositionname;
	}
	public void setFpositionname(String fpositionname) {
		this.fpositionname = fpositionname;
	}
	public String getFpositionlevelname() {
		return fpositionlevelname;
	}
	public void setFpositionlevelname(String fpositionlevelname) {
		this.fpositionlevelname = fpositionlevelname;
	}
	public String getFishopepersonname() {
		return fishopepersonname;
	}
	public void setFishopepersonname(String fishopepersonname) {
		this.fishopepersonname = fishopepersonname;
	}
	public String getFrecruitmentchannelsname() {
		return frecruitmentchannelsname;
	}
	public void setFrecruitmentchannelsname(String frecruitmentchannelsname) {
		this.frecruitmentchannelsname = frecruitmentchannelsname;
	}
    
    
    
}