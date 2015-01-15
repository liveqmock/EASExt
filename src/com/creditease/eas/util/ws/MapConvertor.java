package com.creditease.eas.util.ws;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
/**
 * 实现WebService传递Map参数
 * @author ygq
 * @version 创建时间：2013-12-27 10:58:49
 * @DO Map转换器
 */
@XmlType(name = "MapConvertor")
@XmlAccessorType(XmlAccessType.FIELD)
public class MapConvertor{
	private List<MapEntry> entries = new ArrayList<MapEntry>();
	public void addEntry(MapEntry entry){
		entries.add(entry);
	}
	public List<MapEntry> getEntries(){
		return entries;
	}
	public static class MapEntry{
		public MapEntry(){
			super();
		}
		public MapEntry(Map.Entry<String, Object> entry){
			super();
			this.key = entry.getKey();
			this.value = entry.getValue();
		}
		public MapEntry(String key, Object value){
			super();
			this.key = key;
			this.value = value;
		}

		private String key;
		private Object value;
		public String getKey(){
			return key;
		}
		public void setKey(String key){
			this.key = key;
		}
		public Object getValue(){
			return value;
		}
		public void setValue(Object value){
			this.value = value;
		}
	}
}