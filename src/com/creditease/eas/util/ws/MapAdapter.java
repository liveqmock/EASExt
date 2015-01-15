package com.creditease.eas.util.ws;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlAdapter;
/**
 * 实现WebService传递Map参数
 * @author ygq
 * @version 创建时间：2013-12-27 10:58:49
 * @DO Map转换器
 */
@XmlType(name = "MapConvertor")
@XmlAccessorType(XmlAccessType.FIELD)
public class MapAdapter extends XmlAdapter<MapConvertor, Map<String, Object>>{
	 public MapConvertor marshal(Map<String,Object> map) throws Exception {
		  MapConvertor convertor = new MapConvertor();
		  for(Map.Entry<String, Object> entry:map.entrySet()){
			  MapConvertor.MapEntry e = new MapConvertor.MapEntry(entry);
			  convertor.addEntry(e);
		  }
		  return convertor;
	 }
	 @Override
	 public Map<String, Object> unmarshal(MapConvertor map) throws Exception {
	  Map<String, Object> result = new HashMap<String,Object>();
	  for(MapConvertor.MapEntry e :map.getEntries()){
	   result.put(e.getKey(), e.getValue());
	  }
	  return result;
	 }
}
 
