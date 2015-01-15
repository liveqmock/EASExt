package com.creditease.eas.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 检查xml文件beanId是否重复
 * @author hejw
 *
 */
public class CheckDuplicateBeanIdUtil {
	
	public void CheckDuplicate(String url) throws Exception {
		List<File> xmlFiles = new ArrayList<File>();
		
		//遍历目录
		File dir = new File(url);
		if(dir.isDirectory()) {
			
			File[] files = dir.listFiles();
			for(int i=0;i<files.length;i++) {
				
				//查找xml文件
				if(files[i].isFile()) {
					String fileName = files[i].getName();
					if("xml".equals(fileName.substring(fileName.lastIndexOf(".")+1,fileName.length()))) {
						xmlFiles.add(files[i]);
					}
				}
			}
			
			
		}
		
		//得到所有xml文件bean的id
		Map<String,String> beanIds = new HashMap<String,String>();
		for(File xmlFile:xmlFiles) {

			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(xmlFile);
			Element root = document.getRootElement();
//			List<Attribute> nodes = document.selectNodes("/beans//bean/@id");
			List<Element> nodes = root.elements("bean");
			System.out.println("----------------" + xmlFile.getName() + "----------------");
			for(Element node:nodes) {
				if(node.attribute("id") != null) {
					System.out.println(node.attribute("id").getText());
					//检查beanId是否重复
					if(beanIds.containsKey(node.attribute("id").getText())) {
						System.out.println("beanId:" + node.attribute("id").getText() + "重复!所在文件名称为:" + xmlFile.getName() + "和" + beanIds.get(node.attribute("id").getText()) + "!请检查配置文件!");
					} else {
						beanIds.put(node.attribute("id").getText(), xmlFile.getName());
					}
				}
			}
				
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		//得到配置文件所在目录
		File file = new File(".");
		String directory = file.getCanonicalPath() + "\\cfg";
		CheckDuplicateBeanIdUtil check = new CheckDuplicateBeanIdUtil();
		check.CheckDuplicate(directory);
	}

}
