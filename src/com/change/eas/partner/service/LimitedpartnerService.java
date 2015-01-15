package com.change.eas.partner.service;

import java.util.List;
import java.util.Map;

import com.change.eas.partner.bean.BaseUnit;
import com.change.eas.partner.bean.DocumentFile;
import com.change.eas.partner.bean.LawFile;
import com.change.eas.partner.bean.Limitedpartner;
import com.change.eas.partner.bean.Partner;
import com.change.eas.util.PageView;
import com.creditease.eas.admin.bean.User;
import com.creditease.eas.util.Pagination;

public interface LimitedpartnerService {
	
	Pagination queryPage(Pagination page);
	
	Pagination queryPage2(Pagination page,String roleName);
	
	Pagination queryLawFilePage(Pagination page);
	
	Pagination addLimitedPartnerPage(Pagination page);
	
	Pagination listLimitedPartnerPageJson(Pagination page,Map params);
	
	// easyui查询 
	Pagination queryPagedeatail(Pagination page,Long pid);
	
	Pagination queryPageDocumentFile(Pagination page,Long pid);
	
	void deleteById(Long id);
	
	void deletePartnerById(Long id);
	
	List<Partner> selectPartnerListByPID(Long pid);
	
	void insertPartner(Partner partner);
	
	Limitedpartner findLimitedpartnerById(Long id);
	
	int getTotalCountsByPid(Long pid);
	
	double getSumAmountByPid(Long pid);
	
	void updateGatherstatus(Long pid,String operation,Long userid);
	
	User findUserById(Long id);
	
	void updateTime(Long pid,String change);
	
	void updateChangeTime(Map params);
	
	List<LawFile> lawfilelist(Map params);
	
	List<BaseUnit> queryBaseUnit(Map params);
	
	void insertLimitedpartner(Limitedpartner limitedpartner);
	
	Partner selectPartnerById(Long id);
	
	void updatePartner(Map params);
	
	void updateLimitedPartnerBySupplement(Map params);
	
	void updatePartnerpercentById(Map params);
	
	List<DocumentFile> queryDocumentFile(PageView view, Long pid);
	
	void deleteDocumentFile(Map params);
	
	void deleteLawTemplate(Long RecordID);
	
	void updateLawFileStatus(Map params);
	
	int getRepeatLimitedpartnerName(Map params);

	List<Partner> selectPartnerListByFenyePID(PageView view, Long id);

	List<LawFile> lawfilePageList(PageView view, Map<String, Object> params);
	
	List<BaseUnit> queryBaseUnitPage(PageView view,Map params);
	
	int getisexists(Long pid);
	
	void updateLimitedpartnerstatus(Long pid);
	
	List<Limitedpartner> selectLimitedpartnerList();
	
	String getCityNameByTemplateRecordID(Long recordID);
	
	List<Partner> getPartnerAllIDCard(Long pid);
	
	void insertDocumentFileByCon(DocumentFile file);

}
