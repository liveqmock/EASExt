package com.creditease.eas.hr.util;

import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
/**
 * 这个是最初的转换xml的方法，不符合Esb服务总线的报文规范
 * @XmlConvertV1.java
 * created at 2013-2-20 下午12:01:36 by ygq
 * 
 * @author ygq({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class XmlConvertV1 {
		/**
		 * 
		 * 描述：将List里面的数据转换成xml格式的字符串
		 * 2013-1-22 上午10:36:55 by ygq
		 * @version
		 * @param list
		 * @return
		 */
		public static String covertListToString(List<Map<String,Object>> list){
			StringBuffer str = new StringBuffer("<Creditease>\n");
			if(list != null){
				for(int i = 0; i < list.size(); i++){
					Map<String,Object> map = list.get(i);
					str.append("  <row num=\""+(i+1)+"\">\n    <code>" + map.get("CODE") + "</code>\t\n    <name>"+map.get("NAME")+"</name>\n  </row>\n");
				}
			}
			str.append("</Creditease>");
			String strs = "";
			try{
				Document document = DocumentHelper.parseText(str.toString());
				strs = document.asXML();
			}catch(Exception ex){
				ex.printStackTrace();
			}
		    return strs;
		}
}