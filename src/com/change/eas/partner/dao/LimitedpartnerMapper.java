package com.change.eas.partner.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.change.eas.partner.bean.DocumentFile;
import com.change.eas.partner.bean.LawFile;
import com.change.eas.partner.bean.Limitedpartner;
import com.change.eas.partner.bean.Partner;
import com.creditease.eas.admin.bean.User;
import com.creditease.eas.util.BaseDAO;

public interface LimitedpartnerMapper extends BaseDAO<Limitedpartner, Integer>{
 
   List<Limitedpartner> selectLimitedpartnerList();

   int deleteByPrimaryKey(Long id);
   
   int deletePartnerById(Long id);
   
   List<Partner> selectPartnerListByPID(Long pid);
   
   void insertPartner(Partner partner);
   
   Limitedpartner findLimitedpartnerById(Long id);
   
   int getTotalCountsByPid(Long pid);
   
   int getDocumentFileTotalCountsByPid(Long pid);
   
   double getSumAmountByPid(Long pid);
   
   void updateGatherstatus(Map<String, Object> map);
   
   void updateSavestatus(Map<String, Object> map);
   
   void updateSubmitstatus(Map<String, Object> map);
   
   void updateCheckstatus(Map<String, Object> map);
   
   User findUserById(Long id);
   
   int getTotalCountsByParams2(Map map);
   
   int getDetailTotalCountsByParams(Long pid);
   
   List<Limitedpartner> queryPageByParams2(Map params);
   
   void updateTime(Map params);
   
   List<LawFile> lawfilelist(Map params);
   
   void insertLimitedpartner(Limitedpartner limitedpartner);
   
   Partner selectPartnerById(Long id);
   
   void updatePartner(Map params);
   
   void updateLimitedPartnerBySupplement(Map params);
   
   void updatePartnerpercentById(Map params);
   
   List<DocumentFile> queryDocumentFile(HashMap<String, Object> map);
   
   void deleteDocumentFile(Map params);
   
   void deleteLawTemplate(Long recordid);
   
   void updateLawFileStatus(Map params);
  
   int getTitCountByPid(Long id);

	List<Partner> getPartnerByPid(HashMap<String, Object> map);

	int getTitDocumentFile(Long pid);

	int getTitLawfile(Map<String, Object> params);
	
	List<LawFile> getLawfileList(Map<String, Object> params);
	
	int getRepeatLimitedpartnerName(Map params);
	
	int getisexists(Long pid);
	
	void updateLimitedpartnerstatus(Long pid);
	
	int getLawFileTotalCountsByParams(Map params);
	
	List<LawFile> queryLawFilePageByParams(Map params);
	
	String getCityNameByTemplateRecordID(Long recordID);

	void updateChangeTime(Map params);
	
	List<Partner> queryPartnerPageByParams(Map params);
	
	List<DocumentFile> queryDocumentFilePageByParams(Map params);
	
	List<Partner> getPartnerAllIDCard(Long pid);

	Limitedpartner getData(Long pid);

	List<Partner> getPartnerData(Long pid);

	

	void saveDoumentFile(DocumentFile doumentFile);
	
	void insertDocumentFileByCon(DocumentFile file);

	int getTotalPerson(Long pid);

	Double getLPTotalAmount(Long pid);
}
