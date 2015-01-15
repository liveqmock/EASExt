package com.creditease.eas.hr.util;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import oracle.sql.TIMESTAMP;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.creditease.eas.util.DateUtil;
/**
 * 这个是xml解析的util包
 * @XmlResolve.java
 * created at 2013-2-25 上午11:44:21 by ygq
 * 
 * @author ygq({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class XmlResolve {
	//日志
	//xml解析
	//添加日志记录
	/**
	 * 
	 * 描述：
	 * 2013-2-25 上午11:44:43 by ygq
	 * @version
	 * @param xmlString
	 */
	public static void readMessageBody(String xmlString) {
		try{
			Document doc = DocumentHelper.parseText(xmlString);
			Element root= doc.getRootElement();//获取根节点.      
			for ( Iterator iter = root.elementIterator(); iter.hasNext(); ) {
				Element element = (Element) iter.next();
				if(element.getName().equals("MessageBody")){
					//遍历MessageBody的所有子节点，即：row节点
					for(Iterator it=element.elementIterator();it.hasNext();){
						Element elemRow = (Element)it.next();
						if(elemRow.getName().equals("row")){
							//获取row节点下的所有子节点（code，name）
							for(Iterator iterInner = elemRow.elementIterator(); iterInner.hasNext(); ) {
								 Element elementInner = (Element) iterInner.next();
								 if(elementInner.getName().equals("code")){
									 String code = elementInner.getTextTrim();
									 System.out.println(code);
								 }else if(elementInner.getName().equals("name")){
									 String name = elementInner.getTextTrim();
									 System.out.println(name);
								 }
							}
						}
				}
			 }
		  }
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 描述：判断是否正常推送的数据
	 * 2013-2-25 上午11:56:30 by ygq
	 * @version
	 * @param xmlString
	 * @return
	 */
	public static String messageHeadValidate(String xmlString){
		String va = null;
		try{
			Document doc = DocumentHelper.parseText(xmlString);
			Element root= doc.getRootElement();//获取根节点.      
			for(Iterator iter = root.elementIterator(); iter.hasNext(); ) {
				Element element = (Element) iter.next();
				if(element.getName().equals("MessageHead")){
					//遍历MessageBody的所有子节点，即：row节点
					for(Iterator it=element.elementIterator();it.hasNext();){
						Element elemRow = (Element)it.next();
						System.out.println("node::::::::::" + elemRow.getName());
						if(elemRow.getName().equals("exCode")){
							String textTrim = elemRow.getTextTrim();
							return textTrim;//处理下数据
						}
				}
			 }
		  }
		}catch(Exception e){
			e.printStackTrace();
			va =  null;
		}
		return va;
	}
	/**
	 *模拟返回数据的过程 
	 */
	public static void test()  throws Exception{
		String xmlStr = "<Creditease>"+
						 "<MessageHead>"+
						      "<ID></ID>"+
						      "<tranCode></tranCode>"+
							"<tranType>0000</tranType>"+
						      "<tranTime>2013-02-22</tranTime>"+
						      "<channel>17</channel>"+
						      "<requestType></requestType>"+
						      "<exCode>00000</exCode>"+
							"<errStack></errStack>"+
							"<tranFlow></tranFlow>"+
							"<count></count>"+
							"<index></index>"+
						 "</MessageHead>"+
						 "<MessageBody>"+
						  "<row num='1'>"+
						    "<code>101</code>"+	
						    "<name>重庆</name>"+
						  "</row>"+
						"</MessageBody>"+
						"</Creditease>";
			//readMessageBody(xmlStr);
//			boolean va = messageHeadValidate(xmlStr);
//			System.out.println(va);
	}
	public static void main(String[] args) {
		try {
			test();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}