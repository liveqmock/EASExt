package com.change.eas.partner.dao;

import java.util.List;
import java.util.Map;

import com.change.eas.partner.bean.City;
import com.change.eas.partner.bean.LawTemplate;

public interface LawTemplateMapper {
	List<LawTemplate> listTemplate();

	String getFilePathByID(int id);

	List<LawTemplate> getTemplateByCity(String city);

	LawTemplate getTemplateByRecordID(Long recordID);

	String getRecordIDByCityAndFilename(LawTemplate lawtemplate);

	List<City> getCityList();

	List<LawTemplate> getTemplateByCityId(long id);

	List<City> getCity(int dept);

	String getDeptById(int dept);

	String getCityById(int city);

	void saveTemplate(LawTemplate temp);

	List<City> getDept();

	String getTemplateFilePathByID(long recordID);

	String getTemplateFilePathByRecordID(long recordID);
}
