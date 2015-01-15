package com.creditease.eas.compliance.webservice.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.creditease.eas.compliance.dao.ComplainCommonMapper;
import com.creditease.eas.compliance.dao.ComplainMapper;
import com.creditease.eas.compliance.dao.InvestigationMapper;
import com.creditease.eas.compliance.webservice.ComplainForAntiFraudService;
import com.creditease.eas.util.JsonUtil;
import com.creditease.eas.util.StringUtil;

public class ComplainForAntiFraudServiceImpl implements
		ComplainForAntiFraudService {

	@Autowired
	private ComplainMapper complainMapper;

	public void setComplainMapper(ComplainMapper complainMapper) {
		this.complainMapper = complainMapper;
	}

	@Resource
	private ComplainCommonMapper commonMapper;

	public void setCommonMapper(ComplainCommonMapper commonMapper) {
		this.commonMapper = commonMapper;
	}

	@Resource
	private InvestigationMapper investigationMapper;

	public void setInvestigationMapper(InvestigationMapper investigationMapper) {
		this.investigationMapper = investigationMapper;
	}

	@Override
	public String hello(String name) {
		System.out.println("name:" + name);
		return "hello " + name;
	}

	@Override
	public String saveComplainToCompliance(String complainJson) {
		Integer fnumber = 0;
		boolean result = true;
		String errorCode = "";

		if (complainJson != null && !"".equals(complainJson)) {
			try {
				JsonUtil jsonUtil = new JsonUtil();
				Map map = jsonUtil.fromJsonToMap(complainJson);

				if (map != null) {
					// 判断进件编号
					String transportId = StringUtil.objToString(map
							.get("transportId"));
					if (transportId != null && !"".equals(transportId)) {
						int caseCount = complainMapper
								.getComplainCountByComeIntoCount(transportId);
						// 进件编号不存在
						if (caseCount < 1) {
							fnumber = commonMapper.findMaxComplain();// 取得案件编号
							map.put("fnumber", fnumber);

							complainMapper.saveComplainToCompliance(map);

							// 给子表加外键
							List<Map> mapList = (List<Map>) map.get("antis");
							if (mapList != null) {
								int complainId = StringUtil.objToInteger(map
										.get("fid"));
								for (int i = 0; i < mapList.size(); i++) {
									Map m = mapList.get(i);
									m.put("complainId", complainId);
								}
								complainMapper.batchSaveLoanAnti(mapList);
							}

						} else {
							result = false;
							errorCode = "进件编号已存在";
						}
					}
				}
			} catch (Exception e) {
				result = false;
				errorCode = e.getMessage();
			}
		}
		return "{fnumber:'" + fnumber + "',result:'" + result + "',errorCode:'"
				+ errorCode + "'}";
	}

	@Override
	public String saveInvestigationToCompliance(String investigationJson) {
		boolean result = true;
		String errorCode = "";

		if (investigationJson != null && !"".equals(investigationJson)) {
			try {
				JsonUtil jsonUtil = new JsonUtil();
				Map map = jsonUtil.fromJsonToMap(investigationJson);
				if (map != null) {
					String transportId = StringUtil.objToString(map
							.get("transportId"));
					if (transportId != null && !"".equals(transportId)) {
						int caseCount = complainMapper
								.getComplainCountByComeIntoCount(transportId);
						if (caseCount > 1) {
							result = false;
							errorCode = "进件编号存在多个";
						} else if (caseCount < 1) {
							result = false;
							errorCode = "进件编号不存在";
						} else {
							int investigationCount = investigationMapper
									.getInvestigationCountByComeIntoCode(transportId);
							if (investigationCount > 1) {
								result = false;
								errorCode = "一个案子存在多个调查";
							} else if (investigationCount < 1) {
								result = false;
								errorCode = "该案子还没有开始调查";
							} else {
								int parentId = investigationMapper
										.getInvestigationIdByComeIntoCode(transportId);
								map.put("parentid", parentId);
								int iii=investigationMapper
										.SaveInvestigationLoanAnti(map);
							}
						}
					}

				}

			} catch (Exception e) {
				result = false;
				errorCode = e.getMessage();
			}

		}

		return "{result:'" + result + "',errorCode:'"
		+ errorCode + "'}";
	}
}
