package com.change.eas.partner.service.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;
import org.xml.sax.InputSource;

public class Dom4jXMlMaker2 {
	 String table="";
	
	 
		public  Document getDocument(String listname) throws Exception{
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("c","http://schemas.microsoft.com/aml/2001/core");
			map.put("w","http://schemas.microsoft.com/office/word/2003/wordml");
			map.put("o","urn:schemas-microsoft-com:office:office");
			map.put("wx","http://schemas.microsoft.com/office/word/2003/auxHint");
			map.put("aml","http://schemas.microsoft.com/aml/2001/core");
			map.put("wpc","http://schemas.microsoft.com/office/word/2010/wordprocessingCanvas");
			map.put("wsp","http://schemas.microsoft.com/office/word/2003/wordml/sp2");
			map.put("sl","http://schemas.microsoft.com/schemaLibrary/2003/core");
			   
			// 读取需要解析的 xml 文件
			SAXReader saxReader = new SAXReader();
			File file = new File("E:/word/"+listname+".xml");
			saxReader.getDocumentFactory().setXPathNamespaceURIs(map);
			Document document = saxReader.read(file);
			return document;
		}
		
		//保存xml
		public void saveXMl(String text,String listname) throws Exception{
			  FileWriter writer = new FileWriter(new File("E:/word/xml/"+listname+".xml"));// 获取该文件的输出流  
	          writer.write(text);// 写内容  
	          writer.flush();// 清空缓冲区，立即将输出流里的内容写到文件里  
	          writer.close();// 关闭输出流，施放资源   
		}
	
	public void marker(String listname) throws Exception{ 
		System.err.println("marker:---->");
		   List<String> listtable=new ArrayList<String>();
		   Document document=getDocument(listname);
		   List<?> list= document.selectNodes("//w:tbl");
		   @SuppressWarnings("unchecked")
		   Iterator<Element> iter = (Iterator<Element>) list.iterator();
		   int m=0;
		   while(iter.hasNext()){
			  Element element = (Element)iter.next();
			  Iterator<?> iterator = element.elementIterator("tr"); //elementIterator 获取子标签
			  int i=0;
			  while(iterator.hasNext()){
			    Element titleElement=(Element)iterator.next();
			    if("tr".equals(titleElement.getName())){
			    	Iterator<?> annotationiterator=getannotationiterator(titleElement);
			    	if(annotationiterator.hasNext()){//获取需要  循环的标记  表名
			    	   Element annotationElement=(Element)annotationiterator.next();// 得到循环标签的的属性值
			    	   table=  annotationElement.attributeValue("name");
			    	   if(table!=""){
			    	      listtable.add(table);
			    	   }
			    	   deleteElement(annotationElement.getParent());
                    m++;
			    	}
			    	if(i>=1){
			    		if(table!="" ){
                     element.remove(titleElement);
			    		Element listss = element.addElement("#list "+table+" as "+table);
			    		listss.add(titleElement);
				    }}
			    	i++;
	 			}
			    }
			    i=0;
			    table="";
			}
		    String  text= replacetext(document,listtable,m);
		    saveXMl(text,listname);
}
	
	public  void  cell(Element titleElement){
		 Iterator<?> tciterator=titleElement.elementIterator("tc");
		 List<Element> l = new ArrayList<Element>();//转成list  删除最后一个R标签
		   for (; tciterator.hasNext();) {
			  Element tc = (Element) tciterator.next();
		      l.add(tc);
		   }
		   titleElement.remove(l.get(0));
		   titleElement.getParent().add(l.get(0));
	}
	
	public Iterator<?> getannotationiterator(Element titleElement){
		Iterator<?> tciterator=titleElement.elementIterator("tc");//得到 标记的标签
 	Element tcElement=(Element)tciterator.next();
 	Iterator<?> piterator=tcElement.elementIterator("p");
 	Element pElement=(Element)piterator.next();
 	
 	Iterator<?> annotationiterator=pElement.elementIterator("annotation");
 	return annotationiterator;
	}
	public  void deleteElement(Element pElement){
		 Iterator<?> it=pElement.elementIterator("r");
		   List<Element> l = new ArrayList<Element>();//转成list  删除最后一个R标签
		   for (; it.hasNext();) {
			  Element e = (Element) it.next();
		      l.add(e);
		   }
		 if(l.size()>=2){
		  pElement.remove(l.get(l.size()-1));// 删除 doc中显示的值最后一个r
		 }
		
	}
	//替换 结束标签
	public String replacetext(Document document,List<String> listtable,int m){
		 String text = document.asXML();  
	     //循环替换 list 结束标签
	     for (int i = 0; i < m; i++) {
	    	 if("noloop".equals(listtable.get(i) )){
	    		 String str1="<#list "+listtable.get(i)+" as "+listtable.get(i)+">";
	    		 String str2="</#list "+listtable.get(i)+" as "+listtable.get(i)+">";
		    	 text=text.replaceAll(str1, "");
		    	 text=text.replaceAll(str2, "");
	    	 }
	    	 
	    	 String str="</#list "+listtable.get(i)+" as "+listtable.get(i)+">";
	    	 text=text.replaceAll(str, "</#list>");
	    	 String str1="noloopno";
	    	 text=text.replaceAll(str1, "");
		  }
		return text;
	}
}

