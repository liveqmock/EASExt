package com.change.eas.partner.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.junit.Test;

import com.change.eas.util.Config;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;


public class data {
	
	
	private Configuration configuration = null;
	public data() {
		configuration = new Configuration();
		configuration.setDefaultEncoding("utf-8");
	}
	
	
    @Test
	public void createDoc(Map<Object, Object> dataMap,String listname,String user,String name)  {
		//Map<Object,Object> dataMap = new HashMap<Object, Object>();
		//getData(dataMap);
		//configuration.setClassForTemplateLoading(this.getClass(),"/com/wuyue/template");
		//configuration.setServletContextForTemplateLoading(Object servletContext, String path);
		try {
			
			String str=Config.getKey("lawfile.xml");
			configuration.setDirectoryForTemplateLoading(new File(str));
			Template t = configuration.getTemplate(listname+".xml");
			t.setEncoding("utf-8");
			
			String str2=Config.getKey("lawfile.temp"); 
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			str2 = str2+"/"+format.format(date)+"/";
			System.out.println(str2);
			File  file=new File(str2);
			if(!file.exists()){
				file.mkdir();
			}
			
			File outFile = new File(str2+name+listname+".doc");
//			if(outFile.exists()){
//				outFile.delete();
//			}
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"));
			t.process(dataMap, out);
			out.close();
			//saveDoumentFile(str2,listname,user,name,pid);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
//    public void saveDoumentFile(String str2, String listname,String user,String name,Long pid){
////    	LawTemplateService LawTemplateServiceimpl=new LawTemplateServiceImpl();
//    	//LawTemplateService.saveDoumentFile(str2,listname,user,name,pid);
//    	System.err.println("str2"+str2);
//    	System.err.println("listname"+listname);
//    	System.err.println("user"+user);
//    	System.err.println("name"+name);
//    	System.err.println("pid"+pid);
//	}
}