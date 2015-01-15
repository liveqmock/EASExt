package com.creditease.eas.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import com.creditease.eas.util.mail.MailSenderInfo;

public class FileReadUtil {
	/**
	 * 
	 * 描述：
	 * 2013-1-10 下午03:50:59 by ygq
	 * @version
	 * @param key
	 * @param htmlContent
	 * @param waringTypeName：预警类型
	 * @param title:标题
	 * @return
	 * @throws Exception
	 */
	public static String writeFile(String key,String htmlContent,String waringTypeName,String title) throws Exception{
			if(htmlContent == null || "".equals(htmlContent)){
		 		System.out.println("html is null");
		 		return null;
		 	}
			byte[] buff=new byte[]{};  
		    FileOutputStream out = null;  
		    String reletivePath = "";
		    try{  
	            buff = htmlContent.getBytes();  
	            String date = DateUtil.formatDateToString(new Date());
	            long time = System.currentTimeMillis();
	            //String filePath =ServletActionContext.getRequest().getRealPath("/");
	            MailSenderInfo mailInfo = new MailSenderInfo();
	            String filePath = mailInfo.getProperties().getProperty("filepath");
	            reletivePath  = "upload/"+waringTypeName+"/"+key+"/" + date + "/";//相对路径
	            filePath +=  "upload/"+waringTypeName+"/"+key+"/" + date + "/";
	            File file = new File(filePath);
	            if(!file.exists()){
	            	file.mkdirs();
	            }
	            reletivePath += time + title + ".txt";//相对路径
	            filePath +=  time + title + ".txt";
	            out = new FileOutputStream(filePath);
	            out.write(buff,0,buff.length);  
	        }catch (Exception e){  
	        	System.out.println(key + "\texception");
	        	//System.out.println(htmlContent);
	        }finally{
	        	if(null != out){
	        		out.close();
	        	}
	        }
	        return reletivePath;//相对路径
	}
	
	/**
	 * 
	 * 描述：模拟写文件的过程
	 * 2013-1-10 下午03:50:59 by ygq
	 * @version
	 * @param key
	 * @param htmlContent
	 * @param waringTypeName：预警类型
	 * @param title:标题
	 * @return
	 * @throws Exception
	 */
	public static String writeFileImitate(String htmlContent,String waringTypeName,String title) throws Exception{
		    String key = "稍后处理";
			if(htmlContent == null || "".equals(htmlContent)){
		 		System.out.println("html is null");
		 		return null;
		 	}
			byte[] buff=new byte[]{};  
		    FileOutputStream out = null;  
		    String reletivePath = "";
		    try{  
	            buff = htmlContent.getBytes();  
	            String date = DateUtil.formatDateToString(new Date());
	            long time = System.currentTimeMillis();
	            //String filePath =ServletActionContext.getRequest().getRealPath("/");
	            MailSenderInfo mailInfo = new MailSenderInfo();
	            String filePath = mailInfo.getProperties().getProperty("filepath");
	            reletivePath  = "upload/"+waringTypeName+"/"+key+"/" + date + "/";//相对路径
	            filePath +=  "upload/"+waringTypeName+"/"+key+"/" + date + "/";
	            File file = new File(filePath);
	            if(!file.exists()){
	            	file.mkdirs();
	            }
	            reletivePath += time + title + ".txt";//相对路径
	            filePath +=  time + title + ".txt";
	            out = new FileOutputStream(filePath);
	            out.write(buff,0,buff.length);  
	        }catch (Exception e){  
	        	System.out.println(key + "\texception");
	        	//System.out.println(htmlContent);
	        }finally{
	        	if(null != out){
	        		out.close();
	        	}
	        }
	        return reletivePath;//相对路径
	}
	
	 public static String readFile(String path) {  
//       String path = "d:\\testfile.txt";  
       File file = new File(path);  
       String str = ""; 
       try {  
           BufferedReader rd = new BufferedReader(new FileReader(file));  
           String s = rd.readLine();  
           while (null != s) {  
               str += s + "<br/>";  
               s = rd.readLine();  
           }  
           System.out.println(str);  
       } catch (FileNotFoundException e) {  
           e.printStackTrace();  
       } catch (IOException e) {  
           e.printStackTrace();  
       } 
       return str;
   }  
	/**
	 * 读文件
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	 public static String  getUserFile(String filePath) throws IOException{
	    	File file = new File(filePath);
	    	  FileInputStream fis = new FileInputStream(file);
	    	  BufferedReader br = new BufferedReader(new InputStreamReader(fis));
	    	  StringBuffer sb = new StringBuffer();
	    	  String line=null;  
	    	  while((line=br.readLine())!=null)  
	    	  {  
	    		  sb.append(line);
	    	  }  
	    	  return sb.toString();
	 }
}
