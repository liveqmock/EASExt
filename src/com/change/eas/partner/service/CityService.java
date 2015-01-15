package com.change.eas.partner.service;

import java.util.List;

import com.change.eas.partner.bean.City;

public interface CityService {
	void addCity(City city) throws Exception;
	public List<City> listCity() throws Exception;
	List<City> getParthserList() throws Exception;
}
