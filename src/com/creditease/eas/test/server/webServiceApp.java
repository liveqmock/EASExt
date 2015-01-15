package com.creditease.eas.test.server;
import javax.xml.ws.Endpoint;

import com.creditease.eas.test.cxf.TestServiceImpl;
/***
 * 服务端
 * @webServiceApp.java
 * created at 2013-1-20 下午12:39:08 by ygq
 * 
 * @author ygq({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class webServiceApp {
	  public static void main(String[] args) {
          System.out.println("web service start");
          TestServiceImpl implementor= new TestServiceImpl();
          String address="http://localhost:8080/PWebService";
          Endpoint.publish(address, implementor);
          System.out.println("web service started");
	  }	
}
