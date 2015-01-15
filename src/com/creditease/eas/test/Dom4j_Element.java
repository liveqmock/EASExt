package com.creditease.eas.test;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
public class Dom4j_Element {
	private String str2="<root><country location='country'>abc</country></root>";
	//根据一个符合Document格式的字符串来生成一个Document
	 /**将字符串转化为Document
	  * @param str  输入的字符串
	  * @return  生成的document
	  * @throws DocumentException
	  */
	 public Document parserStrtoDocument(String str) throws DocumentException{
	     Document document=DocumentHelper.parseText(str);
	     return document;
	 }
	 public static void main(String[] args) throws DocumentException
	 {
		 Dom4j_Element dom=new Dom4j_Element();
		 Document d=dom.parserStrtoDocument(dom.str2);
		 Element root = d.getRootElement();
		 Attribute type=root.element("country").attribute("location");
		 System.out.println(type.getText());
		 Element  info =(Element)d.selectSingleNode("//country[@location='country']");
		 System.out.println(info.getText());
	 }
}