package com.creditease.eas.util.excel;

import java.util.Map;
import com.creditease.eas.util.DictionaryUtil;

public class LoanBreadDictionary {
	public static String getLoanBread(String key) {
		if(key==null || "".equals(key)){
			return "";
		}
		Map<String,String> loanBreadMap=DictionaryUtil.singleMap.get(DictionaryUtil.loanBread);
		String value=loanBreadMap.get(key);
		return value;
	}
}
