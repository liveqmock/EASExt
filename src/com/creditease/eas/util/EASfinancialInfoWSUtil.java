package com.creditease.eas.util;

public class EASfinancialInfoWSUtil {
	public static int accountOfPage(int start,int limit){
		if(start<limit){
			return 1;
		}
		if(start%limit==0){
			return start/limit;
		}else{
			return start/limit + 1;
		}
	}
}
