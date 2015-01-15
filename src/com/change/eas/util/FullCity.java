package com.change.eas.util;

import java.util.HashMap;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.change.eas.partner.bean.City;
import com.change.eas.partner.service.CityService;

public class FullCity {
	static CityService cityService;
	static{
		ApplicationContext con = new ClassPathXmlApplicationContext("applicationContext.xml");
		cityService = (CityService) con.getBean("cityService");
	}
	public static void fullCity(Set<String> set) throws Exception{
		long id = 0l;
		int pcode = 100;
		int cCode = 100;
		HashMap<String,String> map = new HashMap<String,String>();
		for(String srt : set){
			String[] split = srt.split("_");
			map.put(split[0],pcode++ +"");
		}
		for(String srt : set){
			String[] split = srt.split("_");
			City city = new City();
			city.setId(id++);
			city.setCityCode(cCode++ +"");
			city.setCityName(split[1]);
			String v = map.get(split[0]);
			city.setPartherCode(v);
			city.setPartherName(split[0]);
			System.out.println(city);
			cityService.addCity(city);
		}
	}
}
