package com.creditease.eas.adminipurc.vo;

import java.util.Date;
/***
 * 行政采购合同预警对应的vo，用于导出
 * @Title:AdminContractInfo.java
 * @Package com.creditease.eas.adminipurc.bean
 * created at 2014-5-25 下午04:32:40 by ygq
 * @author ygq
 * @version 1.0
 */
public class AdminContractInfoForExportExcelVo {
	private String fnumber;//合同编号
	//组织名称
    private String forgname;
    //城市
    private String fcity;
    //末级成本中心
    private String flastcostcenter;
    //办公室坐落地点
    private String fofficeaddr;
    //合同类别
    private String fcontracttypeid;
    //供应商名称
    private String fsuppliername;
    //合同额
    private String fcontractamount;
    //开始日期
    private String fbegindate;
    //结束日期
    private String fenddate;
    //无特殊情况自动续签
    private String fisrenewal;
    //合同期限
    private String fdurtime;
    //月费用
    private String fmonthcost;
    //打印设备押金
    private String fprintdeposit;
    //付款方式
    private String fpaytypeid;
    //合同到期跟进状态
    private String fcontractstatus;
    //备注
    private String fremark;
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
     * This method returns the value of the database column T_ADMINI_CONTRACTINFO.FSUPPLIERNAME
     *
     * @return the value of T_ADMINI_CONTRACTINFO.FSUPPLIERNAME
     *
     * @mbggenerated Sun May 25 16:30:10 CST 2014
     */
    public String getFsuppliername() {
        return fsuppliername;
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
     * This method returns the value of the database column T_ADMINI_CONTRACTINFO.FBEGINDATE
     *
     * @return the value of T_ADMINI_CONTRACTINFO.FBEGINDATE
     *
     * @mbggenerated Sun May 25 16:30:10 CST 2014
     */
    public String getFbegindate() {
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
    public void setFbegindate(String fbegindate) {
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
    public String getFenddate() {
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
    public void setFenddate(String fenddate) {
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
    public String getFdurtime() {
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
    public void setFdurtime(String fdurtime) {
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
    public String getFcontracttypeid() {
		return fcontracttypeid;
	}

	public void setFcontracttypeid(String fcontracttypeid) {
		this.fcontracttypeid = fcontracttypeid;
	}

	public String getFcontractamount() {
		return fcontractamount;
	}

	public void setFcontractamount(String fcontractamount) {
		this.fcontractamount = fcontractamount;
	}

	public String getFpaytypeid() {
		return fpaytypeid;
	}

	public void setFpaytypeid(String fpaytypeid) {
		this.fpaytypeid = fpaytypeid;
	}
	public String getFnumber() {
		return fnumber;
	}

	public void setFnumber(String fnumber) {
		this.fnumber = fnumber;
	}

	public String getFcontractstatus() {
		return fcontractstatus;
	}

	public void setFcontractstatus(String fcontractstatus) {
		this.fcontractstatus = fcontractstatus;
	}
}