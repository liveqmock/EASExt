package com.creditease.eas.projectmanage.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.creditease.eas.projectmanage.bean.AgreementNumber;

/**
 * 
 * @IAgreementNumber.java协议数量接口
 * created at 2014-3-19 上午09:25:41 by sunxiaofeng
 * 
 * @author Administrator({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public interface IAgrNumberMapper {
  /**
   * 
   * 描述：根据出借方式查看所对应的协议
   * 2014-3-19 上午11:00:14 by sunxiaofeng
   * @version
   * @param loanWayId
   * @return  返还fid 集合
   */
    public List<Integer> selectAgreement(Integer loanWayId);
   /**
    * 
    * 描述：新增协议数量信息
    * 2014-3-19 上午11:11:36 by sunxiaofeng
    * @version
    * @param agrNumberList
    * @return
    */ 
    public int insertAgreementNumber(@Param("list")List<AgreementNumber> list);
    /**
     * 
     * 描述：根据贷款编号和出借方式查询数量
     * 2014-3-19 上午11:38:13 by sunxiaofeng
     * @version
     * @param map
     * @return 
     */
    public int selectAgrNumber(Map map);
    
	/**
	 * 
	 * 描述：查询协议数量
	 * 2014-3-19 下午04:35:00 by sunxiaofeng
	 * @version
	 * @param map
	 * @return
	 */
	public List<Map> queryAgrNumber(Map map);
	/**
	 * 
	 * 描述：根据fid查询合同数量信息
	 * 2014-3-19 下午05:20:12 by sunxiaofeng
	 * @version
	 * @param fid
	 * @return
	 */
	public Map editAgreement(Integer fid);
	/**
	 * 
	 * 描述：修改合同数量
	 * 2014-3-19 下午06:21:06 by sunxiaofeng
	 * @version
	 * @param agreementNumber
	 * @return
	 */
	public int updateAgrNumber(AgreementNumber agreementNumber);
	/**
	 * 
	 * 描述：删除合同数量信息
	 * 2014-4-21 上午11:17:22 by sunxiaofeng
	 * @version
	 * @param fid
	 * @return
	 */
	public int deleteAgrNumber(Integer fid);
}
