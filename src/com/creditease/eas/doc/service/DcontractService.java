package com.creditease.eas.doc.service;

import java.io.File;

import com.creditease.eas.doc.bean.Dcontract;
import com.creditease.eas.util.Pagination;

public interface DcontractService {

	Pagination queryPage(Pagination pagination, String string);

	void insert(Dcontract dcontract);

	Dcontract getRentContractById(int id);

	Dcontract getContract(String fcontractnum);

	void update(Dcontract dcontract);

	void time();

	void deleteDcontract(String id);

	String importExcel(String nn, String username,File excelFile) throws Exception;
	/**
	 * 描述：查询合同序号是否已经存在
	 * 2013-12-5 下午01:48:10 by caoyong
	 * @version
	 * @return
	 * @throws Exception
	 */
	public boolean findContractNumExist() throws Exception;
}
