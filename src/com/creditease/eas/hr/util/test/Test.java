package com.creditease.eas.hr.util.test;
import java.io.StringReader;
import java.util.Iterator;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import com.creditease.eas.hr.webService.IOrganizationChangeSendService;
import com.creditease.eas.hr.webService.impl.OrganizationChangeSendServiceImpl;
public class Test {
	//日志
	//xml解析
	//添加日志记录
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
								 }
								 else if(elementInner.getName().equals("name")){
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
			readMessageBody(xmlStr);
	}
	public static void main(String[] args) {
		IOrganizationChangeSendService im = new OrganizationChangeSendServiceImpl();
		String xmlString = "";
//		im.queryOrganizationChangeFromHRToOA(xmlString);
		try {
			test();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
