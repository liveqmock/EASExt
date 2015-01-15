package com.creditease.eas.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CacheTest {
	private static void addHighPerson(Map<String,String> cachekey,List<Map<String,String>> listResponsePerson,List<Map<String,String>> maph){
		if(null != listResponsePerson && listResponsePerson.size()>0){
			Map<String,String> mhf = listResponsePerson.get(0);
			String personId = mhf.get("a").toString();
			Object value = cachekey.get(personId);
			if(value == null){
				maph.add(mhf);
				cachekey.put(personId,"a");
			}
		}
	}
	
	public static void main(String[] args)  throws Exception{
		Map<String,String> cachekey = new HashMap<String,String>();
		List<Map<String,String>> listResponsePerson  = new ArrayList<Map<String,String>>();
		List<Map<String,String>> maph  = new ArrayList<Map<String,String>>();
		Map<String,String> m1 = new HashMap<String,String>();
		m1.put("a", "b");
		listResponsePerson.add(m1);
		Map<String,String> m2 = new HashMap<String,String>();
		m2.put("a", "b");
		maph.add(m2);
		addHighPerson(cachekey,listResponsePerson,maph);
		
		List<Map<String,String>> listResponsePerson1  = new ArrayList<Map<String,String>>();
		List<Map<String,String>> maph1  = new ArrayList<Map<String,String>>();
		Map<String,String> m12 = new HashMap<String,String>();
		m12.put("a", "c");
		listResponsePerson1.add(m12);
		Map<String,String> m22 = new HashMap<String,String>();
		m22.put("a", "c");
		maph1.add(m22);
		addHighPerson(cachekey,listResponsePerson1,maph1);
		System.out.println(cachekey);
//		Map<String,String> m3 = new HashMap<String,String>();
//		m1.put("a", "b");
//		listResponsePerson.add(m3);
//		Map<String,String> m3 = new HashMap<String,String>();
//		m2.put("a", "b");
//		maph.add(m2);
//		addHighPerson(cachekey,listResponsePerson,maph);
		//CREDITEASEeas2013
	}
	
}
