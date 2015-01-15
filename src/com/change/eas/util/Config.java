package com.change.eas.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class Config {
	static Properties pro = new Properties();
	static HashMap<Long, String> map;
	static {
		try {
			pro.load(Config.class.getClassLoader().getResourceAsStream(
					"config.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		map = new HashMap<Long, String>();
		map.put(1l, "投资理财一部");
		map.put(11l, "上海");
		map.put(12l, "成都");
		map.put(13l, "九江/共青城");
		map.put(2l, "投资理财二部");
		map.put(21l, "青岛");
		map.put(22l, "烟台");
		map.put(23l, "长春");
		map.put(3l, "投资理财三部");
		map.put(31l, "无锡");
		map.put(32l, "南京");
		map.put(33l, "苏州");
		map.put(34l, "芜湖");
		map.put(35l, "宁波");
	}

	public static String getKey(String key) {
		return pro.getProperty(key);
	}

	public static String getCityMap(Long key) {
		return map.get(key);
	}
	
	public static String getDepartment(Long key) {
		if (key > 10 && key < 21)
			return map.get(1l);
		else if (key > 20 && key < 31)
			return map.get(2l);
		else
			return map.get(3l);
	}
	
	public static String getPercent(String DD){
		try {
			String[] split = DD.split("\\.");
			String nn = split[1];
			if(nn.length()<2){
				throw new RuntimeException("数字不合法："+DD);
			}
			CharSequence sub1 = nn.subSequence(0, 2);
			CharSequence sub2 = nn.subSequence(2, nn.length());
			if(sub2.toString().equals("")){
				return sub1.toString()+"%";
			}
			return sub1.toString()+"."+sub2.toString()+"%";
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("数字不合法："+DD);
		}
	}

	public static File[] getCityTemplate(String cityName) {
		String path = getKey("lawfile.word");
		File file = new File(path,cityName);
		if(!file.isDirectory()){
			file.mkdirs();
		}
		File[] files = file.listFiles();
		return files;
	}
}
