package com.creditease.eas.test.cxf;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Test {
	public static void main(String[] args) {
		Set<Map<String,Object>> set = new HashSet<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("zhangsan", "1");
		set.add(map);
		set.add(map);
		System.out.println(set.size());
	}
}
