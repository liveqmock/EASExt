package com.creditease.eas.hr.service.impl;




import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.creditease.eas.hr.bean.PersonInfo;
import com.creditease.eas.hr.kingdee.query.SeparationProcessesQuery;
import com.creditease.eas.hr.service.ISeparationProcessesService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
@Service("separservice")
public class SeparationProcessesServiceImpl  implements ISeparationProcessesService {
	
	SeparationProcessesQuery se = new SeparationProcessesQuery();
	
	@Override
	public String queryFuumber(String fnumber) {
		
		PersonInfo person = se.getFnumber(fnumber);
		Map<String,Object> map = new HashMap<String,Object>();
		String json="";
		if (person!=null && null != person.getFnumber() && !"".equals(person.getFnumber())) {
			map.put("userCode", person.getFnumber());
			Map<String, Object> byj = se.getByj(fnumber);
			if (byj!=null) {
				map.put("balance", byj.get("COSTCENTERAMOUNT"));
			}else{
				map.put("balance", "0");
			}
			if(person.getCfisbyj()==null || "".equals(person.getCfisbyj()) || "false".equals(person.getCfisbyj())){
				map.put("isimprest", false);
			}else{
				map.put("isimprest", true);
			}
			Gson g = new GsonBuilder().create();
			json = g.toJson(map);
		}
		return json;
	}

}
