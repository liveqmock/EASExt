package com.creditease.eas.util;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.jetty.util.ajax.JSONObjectConvertor;

import com.creditease.eas.compliance.bean.BaseType;
import com.creditease.eas.compliance.bean.Complain;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

public class JsonUtil<T> {
	public String fromObjToJson(T t) {
		Gson g = new GsonBuilder().create();
		String json = g.toJson(t);
		return json;
	}

	public T fromJsonToObj(String json,Type type) {
		//Type type = new TypeToken<Complain>() {}.getType();
		Gson g = new GsonBuilder().create();
		T t = g.fromJson(json, type);
		return t;
	}
	
	public Map fromJsonToMap(String json) throws Exception{
		if(json!=null&&!"".equals(json)){
			Gson g = new GsonBuilder().create();
			Type t=new TypeToken<Map>(){}.getType();	
			Map map= g.fromJson(json, t);
			return map;
		}
		return null;
	}
	
	
	

	public static void main(String[] args) {
		/*List<BaseType> list = new ArrayList<BaseType>();
		BaseType b = new BaseType();
		b.setBasetypeId(1);
		b.setBasetypeName("name1");
		b.setDetailId("11");
		b.setDetailName("detailName1");

		BaseType b1 = new BaseType();
		b1.setBasetypeId(2);
		b1.setBasetypeName("name2");
		b1.setDetailId("22");
		b1.setDetailName("detailName2");

		list.add(b);
		list.add(b1);

		JsonUtil<List<BaseType>> jsonUtil = new JsonUtil<List<BaseType>>();
		String json = jsonUtil.fromObjToJson(list);
		System.out.println(json);

		List<BaseType> lll = jsonUtil.fromJsonToObj(json);
		System.out.println(lll.size());*/

		Complain f=new Complain();
		f.setFcompchannelid(1);
		f.setFcompcontactinfo("0000000000000");
		
		String json1 = "{'fcomplainanter':'YYYYYYYYYYY','fiscustomer':'1','fidcard':'','fcusName':'YYYYYYYYYYY','fmobile':'','fofficephone':'','fqq':'','femail':'','fcussourceid':0,'fservicetypeid':0,'financialplan':'','fstartorendtime':'','fsalesmanname':'','fservicename':'','ffirsttrial':'','flasttrial':'','fdeniedloans':'','fcontractnumber':'','floanbread':'','freimbstrattime':'','freimbendtime':'','famount':'','fdeadline':'','fisviolate':'0','fcusstatusid':0,'fremarks':'','fcusrelation':'','fcompcontactinfo':'','fcomplainidcard':'','fviolateCountHistory':'','fext2':'','fext3':'','fext4':'','fext5':''}";
		JsonUtil<Complain> j = new JsonUtil<Complain>();
		//Complain cccc=j.fromJsonToObj(json1);
		
		
		
		
		String ssssss=j.fromObjToJson(f);
		System.out.println(ssssss);
		
		
		
		Gson g = new GsonBuilder().create();
		
		Type type = new TypeToken<Complain>() {}.getType();
		
		//Complain t= g.fromJson(json1, type);
		
		Complain t = j.fromJsonToObj(json1,type);
		System.out.println(t);
		//Complain ccccc=j.fromJsonToObj(ssssss);
		//System.out.println(ccccc);
		
		
	}
}
