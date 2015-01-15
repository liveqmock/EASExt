package com.change.eas.partner.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.change.eas.partner.bean.City;
import com.change.eas.partner.bean.DocumentFile;
import com.change.eas.partner.bean.LawTemplate;
import com.change.eas.partner.bean.Limitedpartner;
import com.change.eas.partner.bean.Partner;
import com.change.eas.partner.dao.LawTemplateMapper;
import com.change.eas.partner.dao.LimitedpartnerMapper;
import com.change.eas.partner.service.LawTemplateService;
import com.change.eas.util.Config;
import com.change.eas.util.GetDate;
import com.change.eas.util.ZipFileUtil;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
@Service("lawTemplate")
public class LawTemplateServiceImpl implements LawTemplateService {
	@Autowired
	private LawTemplateMapper lawTemplateMapper;
	@Autowired
	private LimitedpartnerMapper limitedpartnerMapper;
	@Override
	public List<LawTemplate> listTemplate() {
		return lawTemplateMapper.listTemplate();
	}
	public LawTemplateMapper getLawTemplateMapper() {
		return lawTemplateMapper;
	}
	public void setLawTemplateMapper(LawTemplateMapper lawTemplateMapper) {
		this.lawTemplateMapper = lawTemplateMapper;
	}
	@Override
	public FileInputStream getFileInputStream(String ids) throws IOException {
		String[] arrIds = ids.split(";");
		List<String> list = new ArrayList<String>();
		for(String id : arrIds){
			if(id==null||"".equals(id)){
				continue;
			}
			list.add(lawTemplateMapper.getFilePathByID(Integer.parseInt(id)));
		}
		ArrayList<File> list2 = new ArrayList<File>();
		for(String st : list){
			st+=".doc";
			//System.out.println();
			File file = new File(st);
			if(!file.exists()){
				continue;
			}
			list2.add(file);
		}
		File file = ZipFileUtil.zipFile("lawFile"+new Date().getTime(), list2);
		return new FileInputStream(file);
	}
	
	

	 
	@SuppressWarnings("unused")
	@Override
	public void setData(String city, Long pid,Long RecordID,String user,String name)  {
	 	try {
	 		List<String> listname = null;
			// 用city查模版     将模版从数据库查询出来    保存到服务器上   查询出list集合
 	 		if( city.length() != 0){
 	 			  listname=getDoc(city);
 	 		 }else{
	 			 listname=getDoc2(RecordID);
 	 		 }
//			//从服务器 读取模版   将模版转换成xml文件
//	 		for (int i = 0; listname !=null && i < listname.size(); i++) {
//	 			DocToXMl DocToXMl=new DocToXMl();
//	 			 DocToXMl(listname.get(i));//  返回的是一个list 需要查询出名字  
//	 	    }
		   for (int i = 0; listname !=null && i < listname.size(); i++) {
               // marker(listname.get(i));//  参数      需要传入     文件名字   保存的文件路径    需要循环
                if(RecordID ==null){
		 	    	LawTemplate lawtemplate=new LawTemplate();
		 	    	lawtemplate.setCityID(Long.parseLong(city));
		 	    	lawtemplate.setFileName(listname.get(i));
		 	        String recordid=lawTemplateMapper.getRecordIDByCityAndFilename(lawtemplate);
		 	         marker(listname.get(i),Long.parseLong(recordid));
                }else{
                	 marker(listname.get(i),RecordID);
		 	    }
		    }
		 
		   Map<Object,Object> dataMap =getDataMap(pid); //查询出数据传到 getdata 方法中去 将数据进行封装
		
		 //数据填充模版
		    String str2=Config.getKey("lawfile.temp");
		     for (int j = 0; listname !=null && j < listname.size(); j++) {
	    	    data data = new data();
		 	    data.createDoc(dataMap,listname.get(j),user,name);  
		 	    if(RecordID ==null){
		 	    	LawTemplate lawtemplate=new LawTemplate();
		 	    	lawtemplate.setCityID(Long.parseLong(city));
		 	    	lawtemplate.setFileName(listname.get(j));
		 	        String recordid=lawTemplateMapper.getRecordIDByCityAndFilename(lawtemplate);
		 	        saveDoumentFile(str2, listname.get(j), user,name, pid,Long.parseLong(recordid));
		 	    }else{
		 	        saveDoumentFile(str2, listname.get(j), user,name, pid,RecordID);
		 	    }
		 	}
	 	  } catch (Exception e) {
	 	 e.printStackTrace();
		 }
	}
	///////////////////////////////////////////////////////////////////////////////
	public Map<Object,Object> getDataMap(Long pid) throws Exception{
		
		 Map<Object,Object> dataMap = new HashMap<Object, Object>(); //查询出数据传到 getdata 方法中去 将数据进行封装
		 Limitedpartner limitedpartner=limitedpartnerMapper.getData(pid);
		 List<Partner> partner=limitedpartnerMapper.getPartnerData(pid);
		 
		 dataMap.put("businessplace",limitedpartner.getBusinessplace());
		 dataMap.put("name",limitedpartner.getName());
		 dataMap.put("generalperson",limitedpartner.getGeneralperson());
		 dataMap.put("formerlimitedpartner",limitedpartner.getFormerlimitedpartner());
		 dataMap.put("proxy",limitedpartner.getProxy());
		 dataMap.put("investedtime",limitedpartner.getInvestedtime());
		 dataMap.put("gpcontribution",limitedpartner.getGpcontribution());
		 dataMap.put("totalcontribution",limitedpartner.getTotalcontribution());
		 dataMap.put("gpcontributionpercent",limitedpartner.getGpcontributionpercent());
		 dataMap.put("gpcontributionpercentstr",limitedpartner.getGpcontributionpercentstr());
		 dataMap.put("gschangedate",limitedpartner.getGschangedate());
		 dataMap.put("swchangedate",limitedpartner.getSwchangedate());
		 dataMap.put("figure",limitedpartner.getFigure());
		 
		
//			Map<String, String> map = getFieldValue(limitedpartner);
//				for (Map.Entry<String, String> en : map.entrySet()) {
//					System.out.println(en.getKey() + "====" + en.getValue());
//					dataMap.put(""+en.getKey()+"",en.getValue());
//				}
//				
		 
		 
		 
		  for (int i = 0; i < 5; i++) {
			 dataMap.put("partner"+i,partner);
		  }
		  dataMap.put("partner",partner);
		  dataMap.put("limitedpartner",limitedpartner);
		 
		  dataMap.put("year",GetDate.getDate().substring(0,4));
		  dataMap.put("month",GetDate.getDate().substring(5,7));
		  dataMap.put("day",GetDate.getDate().substring(8,10));
		 
		  //人数总计
		  int totalperson=limitedpartnerMapper.getTotalPerson(pid);
		  dataMap.put("totalperson",totalperson);
		  
		  //LP出资总额
		  Double lptotalamount=limitedpartnerMapper.getLPTotalAmount(pid);
		  dataMap.put("lptotalamount",lptotalamount);
		 return dataMap;
	}
	
	
	/////////////////////////////////////////////////////////////////////////
	private  List<String> getDoc2(Long recordID) {
		List<String>  listname=new ArrayList<String>();
		try {
		    LawTemplate  lawTemplate=lawTemplateMapper.getTemplateByRecordID(recordID);
			String filename=lawTemplate.getFileName();
			
			listname.add(filename); //得到所有的查询出来的模版名字
//			byte[] blob=lawTemplate.getFileBody();
//		    String path = "E:/word/";//需要 保存的路径
//		    String fileName = filename+lawTemplate.getFileType();
//		    //文件输出流
//		    File file =new File("E:/word/"+fileName);
//			if(file.exists()){
//				file.delete();
//			}
//		    FileOutputStream fileOutputStream = new FileOutputStream(new File(path, fileName));
//		    fileOutputStream.write(blob);
//		    fileOutputStream.flush();
//		    fileOutputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listname;
	}
	public List<String> getDoc(String city){
		List<String>  listname=new ArrayList<String>();
		try {
		List<LawTemplate> lawTemplateList=getTemplateByCity(city);// 读取所有的关于这个城市的模版
		for (int i = 0; lawTemplateList !=null  && i < lawTemplateList.size(); i++) {
			String filename=lawTemplateList.get(i).getFileName();
			listname.add(filename); //得到所有的查询出来的模版名字
//			byte[] blob=lawTemplateList.get(i).getFileBody();
//		    String path = "E:/word/";//需要 保存的路径
//		    String fileName = filename+lawTemplateList.get(i).getFileType();
//		    //文件输出流
//		    File file =new File("E:/word/"+fileName);
//			if(file.exists()){
//				file.delete();
//			}
//		    FileOutputStream fileOutputStream = new FileOutputStream(new File(path, fileName));
//		    fileOutputStream.write(blob);
//		    fileOutputStream.flush();
//		    fileOutputStream.close();
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listname;
	}

	
	//////////////////////////////////////////////////////////////////////////////////////////
	private List<LawTemplate> getTemplateByCity(String city) {
		List<LawTemplate> list=lawTemplateMapper.getTemplateByCity(city);
		return list;
	}
	//////////////////////////////////////////////////////////////////////////////////////////
	 String table="";
		
		public  Document getDocument(String listname,Long RecordID) throws Exception{
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
		    // String str=Config.getKey("lawfile.word");
			//File file = new File(str+listname+".xml");
			Document document = null ;
			String path = lawTemplateMapper.getTemplateFilePathByRecordID(RecordID.longValue());
			String key = Config.getKey("lawfile.word");
			if(path!=null){
				File file = new File(key,path);
				saxReader.getDocumentFactory().setXPathNamespaceURIs(map);
				document = saxReader.read(file);
			}
			return document;
		}
		
		//保存xml
		public void saveXMl(String text,String listname) throws Exception{
			//保存的临时文件
			//  FileWriter writer = new FileWriter(new File("E:/word/xml/"+listname+".xml"));// 获取该文件的输出流  
			     String str=Config.getKey("lawfile.xml");
				File file =new File(str+listname+".xml");
				if(file.exists()){
					file.delete();
				}
			  OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(str+listname+".xml", true),"UTF-8");
			  osw.write(text);
			  osw.flush();
			  osw.close();
//              writer.write(text);// 写内容  
//	          writer.flush();// 清空缓冲区，立即将输出流里的内容写到文件里  
//	          writer.close();// 关闭输出流，施放资源   
		}

		public void marker(String listname,Long RecordID) throws Exception{ 
			   List<String> listtable=new ArrayList<String>();
			   Document document=getDocument(listname,RecordID);
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
				    		 Element listss;
				    		if(table!=""  ){
		                     element.remove(titleElement);
                             if(!table.substring(0,6).equals("noloop")){
                            	 System.out.println("table--->"+table);
                            	 if(table.substring(0,7).equals("partner") ){
 					    		    listss = element.addElement("#list "+table+" as "+"partner");
 					    		    listss.add(titleElement);
 					    		  }else {
 					    		     listss = element.addElement("#list "+table+" as "+"limitedpartner");
 					    		    listss.add(titleElement);
 					    		  }
                            	   
                             }
                             else{
                            	 listss = element.addElement("#list "+table+" as "+"noloop");
					    		 listss.add(titleElement);
                             }
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
	    	 if("noloop".equals(listtable.get(i)) || "noloop1".equals(listtable.get(i) ) || "noloop2".equals(listtable.get(i) ) || "noloop3".equals(listtable.get(i) ) ){
	    		 String str1="<#list "+listtable.get(i)+" as "+"noloop"+">";
	    		 String str2="</#list "+listtable.get(i)+" as "+"noloop"+">";
		    	 text=text.replaceAll(str1, "");
		    	 text=text.replaceAll(str2, "");
	    	 }
	    	 String str;
	    	 if(!listtable.get(i).equals("noloop")){
		    	 if(listtable.get(i).substring(0, 7).equals("partner")){
		    		 str="</#list "+listtable.get(i)+" as "+"partner"+">"; 
		    	 }else{
		    		 str="</#list "+listtable.get(i)+" as "+"limitedpartner"+">";
		    	 }
		    	 text=text.replaceAll(str, "</#list>");
	    	 }
	    	 String str1="noloopno";
//	    	 String str2="noloopno1";
//	    	 String str3="noloopno2";
	    	 text=text.replaceAll(str1, "");
//	    	 text=text.replaceAll(str2, "");
//	    	 text=text.replaceAll(str3, "");
	    	 
	    	 for (int j = 0; j < 5; j++) {
				String str4="noloopno"+j;
				 text=text.replaceAll(str4, "");
			}
	    	 
	    	 
		  }
		return text;
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////
	@Test
	public   void  DocToXMl(String listname) throws Exception{
			System.err.println("doctoxml:------->");
			ComThread.InitSTA();
	        System.err.println("doctoxml2:------->");
	        ActiveXComponent   app  =   new   ActiveXComponent( "Word.Application");//启动word
	        String   inFile   =   "E:/word/"+listname+".doc";    //要转换的word文件
	        String   tpFile   =   "E:/word/"+listname+".htm";    //临时文件
	        
	        //保存的      xml  模版
	       // listname=new String(listname.getBytes(Config.getKey("charset.to")),Config.getKey("charset.from"));
	        String   otFile   =   "E:/word/"+listname+".xml";   //目标文件
	       
	        File file =new File("E:/word/"+listname+".xml");
//	        File file =new File(Config.getKey("lawfile.temp"));
			if(file.exists()){
				file.delete();
			}
			
	        System.err.println(inFile+":inFile");
	        boolean   flag   =   false;
	        try   {
	            app.setProperty( "Visible", new   Variant(false));//设置word不可见
	            Dispatch docs   =   app.getProperty( "Documents").toDispatch();
	            Dispatch doc   =   Dispatch.invoke(docs, "Open", Dispatch.Method, new Object[]{inFile,new Variant(false), new Variant(true)}, new int[1]).toDispatch();//打开word文件
	            Dispatch.invoke(doc, "SaveAs",   Dispatch.Method, new Object[]{otFile,new   Variant(11)},   new   int[1]);//作为html格式保存到临时文件
	            Variant   f   =   new   Variant(false);
	            Dispatch.call((Dispatch)doc,   "Close",   f);
	           
	            flag   =   true;
	        }catch   (Exception   e)   {
	            e.printStackTrace();
	        }finally   {
	            app.invoke( "Quit",   new   Variant[]   {});
	        }
	        ComThread.Release();
	}
	
	@Override
	public void saveDoumentFile(String str2, String listname, String user,String name,Long pid,Long RecordID) {
		
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		str2 = str2+"/"+format.format(date);
		
		DocumentFile doumentFile=new DocumentFile();
	
		doumentFile.setFilename(name+listname);
		doumentFile.setFilepath(str2);
		doumentFile.setMakeuser(user);
		doumentFile.setPid(pid);
		doumentFile.setTemplateid(RecordID);
		
		limitedpartnerMapper.saveDoumentFile(doumentFile);
	}
	public Map<String, String> getFieldValue(Limitedpartner limitedpartner)
			throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
	 Method[] methods = Limitedpartner.class.getMethods();
		HashMap<String, String> map = new HashMap<String, String>();
		for (Method me : methods) {
			boolean b = me.getName().startsWith("get");
			if (b) {
				String sub = me.getName().substring(3);
				String first = sub.substring(0, 1);
				char fr = sub.charAt(1);
				if (fr <= 122 && fr >= 97) {
					first = first.toLowerCase();
				}
				String end = sub.substring(1);
				String filed = first + end;
				if ("class".equals(filed)) {
					continue;
				}
				Object obj = me.invoke(limitedpartner);
				if (obj == null) {
					map.put(filed, "");
				} else {
					map.put(filed, obj.toString());
				}
			}
		}
		return map;
		}
	@Override
	// TODO add 08/19 18:08
	public String getCityTree() {
		List<City> list = lawTemplateMapper.getCityList();
		Map<String, String> hashMap = new LinkedHashMap<String, String>();
		for (City city : list) {
			hashMap.put(city.getPartherCode(), city.getPartherName());
		}
		Set<Entry<String, String>> set = hashMap.entrySet();
		StringBuffer sb = new StringBuffer();
		for (Entry<String, String> en : set) {
			sb.append("{id: " + en.getKey() + ",pId : 0,name:'" + en.getValue()
					+ "',value:'" + en.getKey() + "',t:'" + en.getValue()
					+ "'},");
			for (City city : list) {
				if (city.getPartherCode().equals(en.getKey())) {
					sb.append("{id: " + city.getId() + ",pId :" + en.getKey()
							+ ",name:'" + city.getCityName() + "',value:'"
							+ city.getId() + "',t:'" + city.getCityName()
							+ "'},");
					List<LawTemplate> tempList = new ArrayList<LawTemplate>();
					try {
						tempList = lawTemplateMapper.getTemplateByCityId(city.getId());
					} catch (Exception e) {
						e.printStackTrace();
					}
					int i = 0;
					for (LawTemplate lt : tempList) {
						sb.append("{ ");
						sb.append("id: 100" + i++ + ",");
						sb.append("pId: " + city.getId() + ",");
						sb.append("value: \"" + lt.getRecordID() + "\",");
						System.out.println(lt.getRecordID());
						sb.append("name: \"" + lt.getFileName() + "\",");
						sb.append("t: \"" + lt.getFileName() + "\"");
						sb.append("},");
					}
				}
			}
		}
		System.err.println(" sb.toString()------->"+sb.toString());
		return sb.toString();
	}

	@Override
	// TODO add by 2014-08-20 13:37
	public List<City> getDept() {
		List<City> list = lawTemplateMapper.getDept();
		return list;
	}

	@Override
	// TODO add by 0820
	public List<City> getCityList(int dept) {
		return lawTemplateMapper.getCity(dept);
	}

	@Override
	// TODO Add 0820
	public String getDeptById(int dept) {
		return lawTemplateMapper.getDeptById(dept);
	}

	@Override
	// TODO Add 0820
	public String getCityById(int city) {
		return lawTemplateMapper.getCityById(city);
	}

	@Override
	public void saveTemplate(LawTemplate temp) {
		lawTemplateMapper.saveTemplate(temp);
	}
}
