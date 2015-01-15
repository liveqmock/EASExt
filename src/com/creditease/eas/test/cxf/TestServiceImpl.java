package com.creditease.eas.test.cxf;

public class TestServiceImpl implements ITestService{
	public String helloWord(String testStr) {
		System.out.println("WebService-----------");
		return "helloWord\t"+testStr;
	}

}
