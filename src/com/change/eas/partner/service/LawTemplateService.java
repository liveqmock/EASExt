package com.change.eas.partner.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.change.eas.partner.bean.City;
import com.change.eas.partner.bean.LawTemplate;

public interface LawTemplateService {
	public List<LawTemplate> listTemplate();

	public FileInputStream getFileInputStream(String ids)
			throws FileNotFoundException, IOException;

	public void setData(String city, Long pid,Long recordID, String user, String name );

	public  void saveDoumentFile(String str2, String listname, String user,
			String name, Long pid,Long RecordID);

	public String getCityTree();

	List<City> getDept();

	List<City> getCityList(int dept);

	String getDeptById(int dept);

	String getCityById(int city);

	void saveTemplate(LawTemplate temp);
}
