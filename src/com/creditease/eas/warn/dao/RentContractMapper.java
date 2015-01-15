package com.creditease.eas.warn.dao;

import java.util.Map;

import com.creditease.eas.util.BaseDAO;
import com.creditease.eas.warn.bean.RentContract;

/**
 * @RentContractMapper.java房租合同信息Dao
 * created at 2013-8-1 下午01:36:18 by caoyong
 * 
 * @author caoyong({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
@SuppressWarnings("deprecation")
public interface RentContractMapper extends BaseDAO<RentContract, Integer>{
   
	int deleteByPrimaryKey(Integer id);
    
    int insert(RentContract record);

    int insertSelective(RentContract record);

    RentContract selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RentContract record);

    int updateByPrimaryKey(RentContract record);
    
    public int selectRentByOfficeadd(String officeadd);
    
    @SuppressWarnings("unchecked")
	public int getTotalCounts(Map map);
}