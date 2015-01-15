package com.creditease.eas.util;

import java.util.Comparator;

import com.creditease.eas.admin.bean.Menu;

public class ComparatorImpl implements Comparator<Menu>{
	
	@Override
	public int compare(Menu o1, Menu o2) {
		if(o1.getSubSequence()>o2.getSubSequence())return 1;
		else if(o1.getSubSequence()<o2.getSubSequence())return -1;
		else if(o1.getSubSequence()==o2.getSubSequence())return 0;
		return 0;
	}

}
