package com.creditease.eas.doc.dao;

import java.util.List;
import java.util.Map;

import com.creditease.eas.doc.bean.Dcontract;
import com.creditease.eas.util.BaseDAO;

public interface DcontractMapper extends BaseDAO<Dcontract, Integer> {

	public int getTotalCounts();

	@SuppressWarnings("unchecked")
	public List<Dcontract> queryPage(Map map2);

	public Dcontract getContract(String fcontractnum);

	public void insertDcontract(Dcontract dcontract);

	public Dcontract getContractById(int id);

	public void updateContract(Dcontract dcontract);

	public List<Map<String, Object>> dtime();

	public void deleteDcontract(String id);

	public  List<Dcontract> queryPageHByParams(Map map2);

	public int getTotalCountByParams(Map map);

	public List<Map<String, Object>> getDcontractList();


}
