package com.change.eas.partner.service.impl;

import org.junit.Test;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class DocToXMl {
	@Test
	public   void  DocToXMl(String listname){
			System.err.println("doctoxml:------->");
			ActiveXComponent   app = null ;
		 try   {
			ComThread.InitSTA();
	        System.err.println("doctoxml2:------->");
	        app   =   new   ActiveXComponent( "Word.Application");//启动word
	        String   inFile   =   "E:/word/"+listname+".doc";    //要转换的word文件
	        String   tpFile   =   "E:/word/"+listname+".htm";    //临时文件
	        String   otFile   =   "E:/word/"+listname+".xml";   //目标文件
	        System.err.println(inFile+":inFile");
	        boolean   flag   =   false;
	            app.setProperty( "Visible", new   Variant(false));//设置word不可见
	            Dispatch docs   =   app.getProperty( "Documents").toDispatch();
	            Dispatch doc   =   Dispatch.invoke(docs, "Open", Dispatch.Method, new Object[]{inFile,new Variant(false), new Variant(true)}, new int[1]).toDispatch();//打开word文件
	            Dispatch.invoke(doc, "SaveAs",   Dispatch.Method, new Object[]{otFile,new   Variant(11)},   new   int[1]);//作为html格式保存到临时文件
	            Variant   f   =   new   Variant(true);
	            Dispatch.call((Dispatch)doc,   "Close",   f);
	            flag   =   true;
	        }catch   (Exception   e)   {
	            e.printStackTrace();
	        }finally   {
	            app.invoke( "Quit",   new   Variant[]   {});
	        }
	        ComThread.Release();
   }
}
