package com.creditease.eas.warn.dao;

import com.creditease.eas.util.BaseDAO;
import com.creditease.eas.warn.bean.FinanceOperation;

public interface FinanceOperationMapper extends BaseDAO<FinanceOperation, Integer> {

		/**
	    * xxsss
	    * 描述：插入合同操作信息记录
	    * 2013-9-29 上午11:33:52 by zhangxin
	    * @version
	    * @param financeOperation
	    * @return
	    */
	    int insert(FinanceOperation financeOperation2);
}
