package com.creditease.eas.adminipurc.bean;

import java.util.Date;
/***
 * 行政采购合同预警对应的合同信息
 * @Title:AdminContractInfo.java
 * @Package com.creditease.eas.adminipurc.bean
 * created at 2014-5-25 下午04:32:40 by ygq
 * @author ygq
 * @version 1.0
 */
public class AdminContractInfo {
   //主键
    private Long fid;
    //组织名称
    private String forgname;
    //城市
    private String fcity;
    //末级成本中心
    private String flastcostcenter;
    //办公室坐落地点
    private String fofficeaddr;
    //合同编号
    private String fnumber;
    //合同编号的后三位流水号
    private Integer fserialNumber;
    //合同类别
    private Integer fcontracttypeid;
    //供应商名称
    private String fsuppliername;
   //供应商编码
    private String fsupplierfnumber;
    //合同额
    private String fcontractamount;
    //开始日期
    private Date fbegindate;
    //结束日期
    private Date fenddate;
    //无特殊情况自动续签
    private String fisrenewal;
    //合同期限
    private Long fdurtime;
    //月费用
    private String fmonthcost;
    //打印设备押金
    private String fprintdeposit;
    //付款方式
    private Integer fpaytypeid;

    //合同状态：1.在用2.删除
    private String fdeletestatus;
    //合同到期跟进:1.生效 2.已续签、3.续签申请中、4.不续签
    private Integer fcontractstatus;
    //备注
    private String fremark;
    /**创建人**/
    private Long fcreator;
    /**创建时间**/
    private Date fcreatetime;
    /**最后更新人姓名**/
    private Long flastupdater;
    /**最后更新时间**/
    private Date flastupdatetime;

    /**扩展字段1:
     * 保存下接口人的邮箱
     * */
    private String fext1;
    /**扩展字段2：保存接口人的id**/
    private String fext2;
    private String fext3;
    /**扩展字段4**/
    private String fext4;
    /**扩展字段5**/
    private String fext5;
    /**扩展字段6**/
    private String fext6;
    /**扩展字段7**/
    private String fext7;
    /**扩展字段8**/
    private String fext8;
    /**扩展字段9**/
    private String fext9;
    /**扩展字段10**/
    private String fext10;
    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

   
    public String getForgname() {
		return forgname;
	}

	public void setForgname(String forgname) {
		this.forgname = forgname;
	}

	public String getFcity() {
		return fcity;
	}

	public void setFcity(String fcity) {
		this.fcity = fcity;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_ADMINI_CONTRACTINFO.FLASTCOSTCENTER
     *
     * @return the value of T_ADMINI_CONTRACTINFO.FLASTCOSTCENTER
     *
     * @mbggenerated Sun May 25 16:30:10 CST 2014
     */
    public String getFlastcostcenter() {
        return flastcostcenter;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_ADMINI_CONTRACTINFO.FLASTCOSTCENTER
     *
     * @param flastcostcenter the value for T_ADMINI_CONTRACTINFO.FLASTCOSTCENTER
     *
     * @mbggenerated Sun May 25 16:30:10 CST 2014
     */
    public void setFlastcostcenter(String flastcostcenter) {
        this.flastcostcenter = flastcostcenter == null ? null : flastcostcenter.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_ADMINI_CONTRACTINFO.FOFFICEADDR
     *
     * @return the value of T_ADMINI_CONTRACTINFO.FOFFICEADDR
     *
     * @mbggenerated Sun May 25 16:30:10 CST 2014
     */
    public String getFofficeaddr() {
        return fofficeaddr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_ADMINI_CONTRACTINFO.FOFFICEADDR
     *
     * @param fofficeaddr the value for T_ADMINI_CONTRACTINFO.FOFFICEADDR
     *
     * @mbggenerated Sun May 25 16:30:10 CST 2014
     */
    public void setFofficeaddr(String fofficeaddr) {
        this.fofficeaddr = fofficeaddr == null ? null : fofficeaddr.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_ADMINI_CONTRACTINFO.FNUMBER
     *
     * @return the value of T_ADMINI_CONTRACTINFO.FNUMBER
     *
     * @mbggenerated Sun May 25 16:30:10 CST 2014
     */
    public String getFnumber() {
        return fnumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_ADMINI_CONTRACTINFO.FNUMBER
     *
     * @param fnumber the value for T_ADMINI_CONTRACTINFO.FNUMBER
     *
     * @mbggenerated Sun May 25 16:30:10 CST 2014
     */
    public void setFnumber(String fnumber) {
        this.fnumber = fnumber == null ? null : fnumber.trim();
    }


    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_ADMINI_CONTRACTINFO.FSUPPLIERNAME
     *
     * @return the value of T_ADMINI_CONTRACTINFO.FSUPPLIERNAME
     *
     * @mbggenerated Sun May 25 16:30:10 CST 2014
     */
    public String getFsuppliername() {
        return fsuppliername;
    }

    public Integer getFserialNumber() {
		return fserialNumber;
	}

	public void setFserialNumber(Integer fserialNumber) {
		this.fserialNumber = fserialNumber;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_ADMINI_CONTRACTINFO.FSUPPLIERNAME
     *
     * @param fsuppliername the value for T_ADMINI_CONTRACTINFO.FSUPPLIERNAME
     *
     * @mbggenerated Sun May 25 16:30:10 CST 2014
     */
    public void setFsuppliername(String fsuppliername) {
        this.fsuppliername = fsuppliername == null ? null : fsuppliername.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_ADMINI_CONTRACTINFO.FSUPPLIERFNUMBER
     *
     * @return the value of T_ADMINI_CONTRACTINFO.FSUPPLIERFNUMBER
     *
     * @mbggenerated Sun May 25 16:30:10 CST 2014
     */
    public String getFsupplierfnumber() {
        return fsupplierfnumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_ADMINI_CONTRACTINFO.FSUPPLIERFNUMBER
     *
     * @param fsupplierfnumber the value for T_ADMINI_CONTRACTINFO.FSUPPLIERFNUMBER
     *
     * @mbggenerated Sun May 25 16:30:10 CST 2014
     */
    public void setFsupplierfnumber(String fsupplierfnumber) {
        this.fsupplierfnumber = fsupplierfnumber == null ? null : fsupplierfnumber.trim();
    }


    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_ADMINI_CONTRACTINFO.FBEGINDATE
     *
     * @return the value of T_ADMINI_CONTRACTINFO.FBEGINDATE
     *
     * @mbggenerated Sun May 25 16:30:10 CST 2014
     */
    public Date getFbegindate() {
        return fbegindate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_ADMINI_CONTRACTINFO.FBEGINDATE
     *
     * @param fbegindate the value for T_ADMINI_CONTRACTINFO.FBEGINDATE
     *
     * @mbggenerated Sun May 25 16:30:10 CST 2014
     */
    public void setFbegindate(Date fbegindate) {
        this.fbegindate = fbegindate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_ADMINI_CONTRACTINFO.FENDDATE
     *
     * @return the value of T_ADMINI_CONTRACTINFO.FENDDATE
     *
     * @mbggenerated Sun May 25 16:30:10 CST 2014
     */
    public Date getFenddate() {
        return fenddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_ADMINI_CONTRACTINFO.FENDDATE
     *
     * @param fenddate the value for T_ADMINI_CONTRACTINFO.FENDDATE
     *
     * @mbggenerated Sun May 25 16:30:10 CST 2014
     */
    public void setFenddate(Date fenddate) {
        this.fenddate = fenddate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_ADMINI_CONTRACTINFO.FISRENEWAL
     *
     * @return the value of T_ADMINI_CONTRACTINFO.FISRENEWAL
     *
     * @mbggenerated Sun May 25 16:30:10 CST 2014
     */
    public String getFisrenewal() {
        return fisrenewal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_ADMINI_CONTRACTINFO.FISRENEWAL
     *
     * @param fisrenewal the value for T_ADMINI_CONTRACTINFO.FISRENEWAL
     *
     * @mbggenerated Sun May 25 16:30:10 CST 2014
     */
    public void setFisrenewal(String fisrenewal) {
        this.fisrenewal = fisrenewal == null ? null : fisrenewal.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_ADMINI_CONTRACTINFO.FDURTIME
     *
     * @return the value of T_ADMINI_CONTRACTINFO.FDURTIME
     *
     * @mbggenerated Sun May 25 16:30:10 CST 2014
     */
    public Long getFdurtime() {
        return fdurtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_ADMINI_CONTRACTINFO.FDURTIME
     *
     * @param fdurtime the value for T_ADMINI_CONTRACTINFO.FDURTIME
     *
     * @mbggenerated Sun May 25 16:30:10 CST 2014
     */
    public void setFdurtime(Long fdurtime) {
        this.fdurtime = fdurtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_ADMINI_CONTRACTINFO.FMONTHCOST
     *
     * @return the value of T_ADMINI_CONTRACTINFO.FMONTHCOST
     *
     * @mbggenerated Sun May 25 16:30:10 CST 2014
     */
    public String getFmonthcost() {
        return fmonthcost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_ADMINI_CONTRACTINFO.FMONTHCOST
     *
     * @param fmonthcost the value for T_ADMINI_CONTRACTINFO.FMONTHCOST
     *
     * @mbggenerated Sun May 25 16:30:10 CST 2014
     */
    public void setFmonthcost(String fmonthcost) {
        this.fmonthcost = fmonthcost == null ? null : fmonthcost.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_ADMINI_CONTRACTINFO.FPRINTDEPOSIT
     *
     * @return the value of T_ADMINI_CONTRACTINFO.FPRINTDEPOSIT
     *
     * @mbggenerated Sun May 25 16:30:10 CST 2014
     */
    public String getFprintdeposit() {
        return fprintdeposit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_ADMINI_CONTRACTINFO.FPRINTDEPOSIT
     *
     * @param fprintdeposit the value for T_ADMINI_CONTRACTINFO.FPRINTDEPOSIT
     *
     * @mbggenerated Sun May 25 16:30:10 CST 2014
     */
    public void setFprintdeposit(String fprintdeposit) {
        this.fprintdeposit = fprintdeposit == null ? null : fprintdeposit.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_ADMINI_CONTRACTINFO.FDELETESTATUS
     *
     * @return the value of T_ADMINI_CONTRACTINFO.FDELETESTATUS
     *
     * @mbggenerated Sun May 25 16:30:10 CST 2014
     */
    public String getFdeletestatus() {
        return fdeletestatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_ADMINI_CONTRACTINFO.FDELETESTATUS
     *
     * @param fdeletestatus the value for T_ADMINI_CONTRACTINFO.FDELETESTATUS
     *
     * @mbggenerated Sun May 25 16:30:10 CST 2014
     */
    public void setFdeletestatus(String fdeletestatus) {
        this.fdeletestatus = fdeletestatus == null ? null : fdeletestatus.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_ADMINI_CONTRACTINFO.FCONTRACTSTATUS
     *
     * @return the value of T_ADMINI_CONTRACTINFO.FCONTRACTSTATUS
     *
     * @mbggenerated Sun May 25 16:30:10 CST 2014
     */
    public Integer getFcontractstatus() {
        return fcontractstatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_ADMINI_CONTRACTINFO.FCONTRACTSTATUS
     *
     * @param fcontractstatus the value for T_ADMINI_CONTRACTINFO.FCONTRACTSTATUS
     *
     * @mbggenerated Sun May 25 16:30:10 CST 2014
     */
    public void setFcontractstatus(Integer fcontractstatus) {
        this.fcontractstatus = fcontractstatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_ADMINI_CONTRACTINFO.FREMARK
     *
     * @return the value of T_ADMINI_CONTRACTINFO.FREMARK
     *
     * @mbggenerated Sun May 25 16:30:10 CST 2014
     */
    public String getFremark() {
        return fremark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_ADMINI_CONTRACTINFO.FREMARK
     *
     * @param fremark the value for T_ADMINI_CONTRACTINFO.FREMARK
     *
     * @mbggenerated Sun May 25 16:30:10 CST 2014
     */
    public void setFremark(String fremark) {
        this.fremark = fremark == null ? null : fremark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_ADMINI_CONTRACTINFO.FCREATOR
     *
     * @return the value of T_ADMINI_CONTRACTINFO.FCREATOR
     *
     * @mbggenerated Sun May 25 16:30:10 CST 2014
     */
    public Long getFcreator() {
        return fcreator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_ADMINI_CONTRACTINFO.FCREATOR
     *
     * @param fcreator the value for T_ADMINI_CONTRACTINFO.FCREATOR
     *
     * @mbggenerated Sun May 25 16:30:10 CST 2014
     */
    public void setFcreator(Long fcreator) {
        this.fcreator = fcreator;
    }

    public Date getFcreatetime() {
		return fcreatetime;
	}

	public void setFcreatetime(Date fcreatetime) {
		this.fcreatetime = fcreatetime;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_ADMINI_CONTRACTINFO.FLASTUPDATER
     *
     * @return the value of T_ADMINI_CONTRACTINFO.FLASTUPDATER
     *
     * @mbggenerated Sun May 25 16:30:10 CST 2014
     */
    public Long getFlastupdater() {
        return flastupdater;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_ADMINI_CONTRACTINFO.FLASTUPDATER
     *
     * @param flastupdater the value for T_ADMINI_CONTRACTINFO.FLASTUPDATER
     *
     * @mbggenerated Sun May 25 16:30:10 CST 2014
     */
    public void setFlastupdater(Long flastupdater) {
        this.flastupdater = flastupdater;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_ADMINI_CONTRACTINFO.FLASTUPDATETIME
     *
     * @return the value of T_ADMINI_CONTRACTINFO.FLASTUPDATETIME
     *
     * @mbggenerated Sun May 25 16:30:10 CST 2014
     */
    public Date getFlastupdatetime() {
        return flastupdatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_ADMINI_CONTRACTINFO.FLASTUPDATETIME
     *
     * @param flastupdatetime the value for T_ADMINI_CONTRACTINFO.FLASTUPDATETIME
     *
     * @mbggenerated Sun May 25 16:30:10 CST 2014
     */
    public void setFlastupdatetime(Date flastupdatetime) {
        this.flastupdatetime = flastupdatetime;
    }

    public Integer getFcontracttypeid() {
		return fcontracttypeid;
	}

	public void setFcontracttypeid(Integer fcontracttypeid) {
		this.fcontracttypeid = fcontracttypeid;
	}

	public String getFcontractamount() {
		return fcontractamount;
	}

	public void setFcontractamount(String fcontractamount) {
		this.fcontractamount = fcontractamount;
	}

	public Integer getFpaytypeid() {
		return fpaytypeid;
	}

	public void setFpaytypeid(Integer fpaytypeid) {
		this.fpaytypeid = fpaytypeid;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_ADMINI_CONTRACTINFO.FEXT1
     *
     * @return the value of T_ADMINI_CONTRACTINFO.FEXT1
     *
     * @mbggenerated Sun May 25 16:30:10 CST 2014
     */
    public String getFext1() {
        return fext1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_ADMINI_CONTRACTINFO.FEXT1
     *
     * @param fext1 the value for T_ADMINI_CONTRACTINFO.FEXT1
     *
     * @mbggenerated Sun May 25 16:30:10 CST 2014
     */
    public void setFext1(String fext1) {
        this.fext1 = fext1 == null ? null : fext1.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_ADMINI_CONTRACTINFO.FEXT2
     *
     * @return the value of T_ADMINI_CONTRACTINFO.FEXT2
     *
     * @mbggenerated Sun May 25 16:30:10 CST 2014
     */
    public String getFext2() {
        return fext2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_ADMINI_CONTRACTINFO.FEXT2
     *
     * @param fext2 the value for T_ADMINI_CONTRACTINFO.FEXT2
     *
     * @mbggenerated Sun May 25 16:30:10 CST 2014
     */
    public void setFext2(String fext2) {
        this.fext2 = fext2 == null ? null : fext2.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_ADMINI_CONTRACTINFO.FEXT3
     *
     * @return the value of T_ADMINI_CONTRACTINFO.FEXT3
     *
     * @mbggenerated Sun May 25 16:30:10 CST 2014
     */
    public String getFext3() {
        return fext3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_ADMINI_CONTRACTINFO.FEXT3
     *
     * @param fext3 the value for T_ADMINI_CONTRACTINFO.FEXT3
     *
     * @mbggenerated Sun May 25 16:30:10 CST 2014
     */
    public void setFext3(String fext3) {
        this.fext3 = fext3 == null ? null : fext3.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_ADMINI_CONTRACTINFO.FEXT4
     *
     * @return the value of T_ADMINI_CONTRACTINFO.FEXT4
     *
     * @mbggenerated Sun May 25 16:30:10 CST 2014
     */
    public String getFext4() {
        return fext4;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_ADMINI_CONTRACTINFO.FEXT4
     *
     * @param fext4 the value for T_ADMINI_CONTRACTINFO.FEXT4
     *
     * @mbggenerated Sun May 25 16:30:10 CST 2014
     */
    public void setFext4(String fext4) {
        this.fext4 = fext4 == null ? null : fext4.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_ADMINI_CONTRACTINFO.FEXT7
     *
     * @return the value of T_ADMINI_CONTRACTINFO.FEXT7
     *
     * @mbggenerated Sun May 25 16:30:10 CST 2014
     */
    public String getFext7() {
        return fext7;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_ADMINI_CONTRACTINFO.FEXT7
     *
     * @param fext7 the value for T_ADMINI_CONTRACTINFO.FEXT7
     *
     * @mbggenerated Sun May 25 16:30:10 CST 2014
     */
    public void setFext7(String fext7) {
        this.fext7 = fext7 == null ? null : fext7.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_ADMINI_CONTRACTINFO.FEXT8
     *
     * @return the value of T_ADMINI_CONTRACTINFO.FEXT8
     *
     * @mbggenerated Sun May 25 16:30:10 CST 2014
     */
    public String getFext8() {
        return fext8;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_ADMINI_CONTRACTINFO.FEXT8
     *
     * @param fext8 the value for T_ADMINI_CONTRACTINFO.FEXT8
     *
     * @mbggenerated Sun May 25 16:30:10 CST 2014
     */
    public void setFext8(String fext8) {
        this.fext8 = fext8 == null ? null : fext8.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_ADMINI_CONTRACTINFO.FEXT6
     *
     * @return the value of T_ADMINI_CONTRACTINFO.FEXT6
     *
     * @mbggenerated Sun May 25 16:30:10 CST 2014
     */
    public String getFext6() {
        return fext6;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_ADMINI_CONTRACTINFO.FEXT6
     *
     * @param fext6 the value for T_ADMINI_CONTRACTINFO.FEXT6
     *
     * @mbggenerated Sun May 25 16:30:10 CST 2014
     */
    public void setFext6(String fext6) {
        this.fext6 = fext6 == null ? null : fext6.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_ADMINI_CONTRACTINFO.FEXT5
     *
     * @return the value of T_ADMINI_CONTRACTINFO.FEXT5
     *
     * @mbggenerated Sun May 25 16:30:10 CST 2014
     */
    public String getFext5() {
        return fext5;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_ADMINI_CONTRACTINFO.FEXT5
     *
     * @param fext5 the value for T_ADMINI_CONTRACTINFO.FEXT5
     *
     * @mbggenerated Sun May 25 16:30:10 CST 2014
     */
    public void setFext5(String fext5) {
        this.fext5 = fext5 == null ? null : fext5.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_ADMINI_CONTRACTINFO.FEXT9
     *
     * @return the value of T_ADMINI_CONTRACTINFO.FEXT9
     *
     * @mbggenerated Sun May 25 16:30:10 CST 2014
     */
    public String getFext9() {
        return fext9;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_ADMINI_CONTRACTINFO.FEXT9
     *
     * @param fext9 the value for T_ADMINI_CONTRACTINFO.FEXT9
     *
     * @mbggenerated Sun May 25 16:30:10 CST 2014
     */
    public void setFext9(String fext9) {
        this.fext9 = fext9 == null ? null : fext9.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column T_ADMINI_CONTRACTINFO.FEXT10
     *
     * @return the value of T_ADMINI_CONTRACTINFO.FEXT10
     *
     * @mbggenerated Sun May 25 16:30:10 CST 2014
     */
    public String getFext10() {
        return fext10;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column T_ADMINI_CONTRACTINFO.FEXT10
     *
     * @param fext10 the value for T_ADMINI_CONTRACTINFO.FEXT10
     *
     * @mbggenerated Sun May 25 16:30:10 CST 2014
     */
    public void setFext10(String fext10) {
        this.fext10 = fext10 == null ? null : fext10.trim();
    }
}