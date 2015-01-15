package com.creditease.eas.hr.util;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oracle.sql.TIMESTAMP;

import com.creditease.eas.util.DateUtil;
import com.creditease.eas.util.consts.AddressConfig;
public class XmlConvert {
		/**
		 * 
		 * 描述：将List里面的数据转换成xml格式的字符串
		 * 2013-1-22 上午10:36:55 by ygq
		 * @version
		 * @param list
		 * @return
		 */
		public static String covertListToString(List<Map<String,Object>> list,String tranCode){
			StringBuffer str = new StringBuffer("<Creditease>\n");
			str.append(" <MessageHead>\n");
			str.append("      <ID></ID>\n");
			str.append("      <tranCode>" +tranCode+ "</tranCode>\n");
			str.append("	<tranType>0000</tranType>\n");
			String createTime = DateUtil.formatDateToString(new Date());//报文生成时间
			str.append("      <tranTime>" + createTime + "</tranTime>\n");
			str.append("      <channel>hr</channel>\n");
			str.append("      <requestType></requestType>\n");
			str.append("      <exCode>00000</exCode>\n");
			str.append("	<errStack></errStack>\n");
			str.append("	<tranFlow></tranFlow>\n");
			str.append("	<count></count>\n");
			str.append("	<index></index>\n");
			str.append(" </MessageHead>\n");
			str.append(" <MessageBody>\n");
			if(list != null){
				for(int i = 0; i < list.size(); i++){
					Map<String,Object> map = list.get(i);
					if(null != map){
						str.append("  <row num=\""+(i+1)+"\">\n    <code>" + map.get("CODE") + "</code>\t\n    " +
							"<name>"+map.get("NAME")+"</name>\n  </row>\n");
					}
				}
			}
			str.append("</MessageBody>\n");
			str.append("</Creditease>");
//			String strs = "";
//			try{
//				Document document = DocumentHelper.parseText(str.toString());
//				strs = document.asXML();
//			}catch(Exception ex){
//				ex.printStackTrace();
//			}
//		    return strs.toString();
			return str.toString();
		}
		/**
		 * 
		 * 描述：
		 * 2013-2-21 上午10:26:14 by ygq
		 * @version
		 * //			String strs = "";
//			try{
//				Document document = DocumentHelper.parseText(str.toString());
//				strs = document.asXML();
//			}catch(Exception ex){
//				ex.printStackTrace();
//			}
//		    return strs;
		 */
		public static String orgnationConvertXml(List<Map<String,Object>> list,Date dt) {
			StringBuffer str = new StringBuffer("<Creditease>\n");
			str.append(" <MessageHead>\n");
			str.append("      <ID></ID>\n");
			str.append("      <tranCode>002003</tranCode>\n");
			str.append("	  <tranType>0000</tranType>\n");
			String createTime = DateUtil.formatDateToString(new Date(),"yyyy-MM-dd HH:mm:ss");//报文生成时间
			str.append("      <tranTime>" + createTime + "</tranTime>\n");
			str.append("      <channel>hr</channel>\n");
			str.append("      <requestType></requestType>\n");
			str.append("      <exCode>00000</exCode>\n");
			str.append("	<errStack></errStack>\n");
			str.append("	<tranFlow></tranFlow>\n");
			str.append("	<count></count>\n");
			str.append("	<index></index>\n");
			str.append(" </MessageHead>\n");
			str.append(" <MessageBody>\n");
			if(list != null){
				for(int i = 0; i < list.size(); i++){
					Map<String,Object> map = list.get(i);
					str.append("  <row num=\""+(i+1)+"\">\n");
					if(AddressConfig.ORGCST==0){//测试异常的
						if(i==1||i==3){//制造假数据
							str.append("      <number></number>\n");
						}else{
							str.append("      <number>" + map.get("FNUMBER") + "</number>\n");
						}
					}else{//测试正常的
						str.append("      <number>" + map.get("FNUMBER") + "</number>\n");
//						str.append("      <number>p" + map.get("FNUMBER") + "1</number>\n");
					}
					str.append("      <principalID>" + map.get("FPRINCIPALID") + "</principalID>\n");
					str.append("      <index>" + map.get("FINDEX") + "</index>\n");
					str.append("      <parentNumber>" + map.get("PARENTNUMBER") + "</parentNumber>\n");
					//生效日期
					str.append("      <effectDate>" + DateUtil.formatTimestampToString2((TIMESTAMP)map.get("FEFFECTDATE"),"yyyy-MM-dd HH:mm:ss") + "</effectDate>\n");
					str.append("      <invalidDate>" + DateUtil.formatTimestampToString2((TIMESTAMP)map.get("FINVALIDDATE"),"yyyy-MM-dd HH:mm:ss") + "</invalidDate>\n");
					str.append("      <isFreeze>" + map.get("FISFREEZE") + "</isFreeze>\n");
					str.append("      <isStart>" + map.get("FISSTART") + "</isStart>\n");
					
					str.append("      <orgTypeStr>" + map.get("FORGTYPESTR") + "</orgTypeStr>\n");
					str.append("      <isLeaf>" + map.get("FISLEAF") + "</isLeaf>\n");
					str.append("      <level>" + map.get("FLEVEL") + "</level>\n");
					str.append("      <displayName>" + map.get("FDISPLAYNAME") + "</displayName>\n");
					str.append("      <description>" + map.get("FDESCRIPTION") + "</description>\n");
					str.append("      <simpleName>" + map.get("FSIMPLENAME") + "</simpleName>\n");
					str.append("      <isCostCenter>" + map.get("FISCOSTORGUNIT") + "</isCostCenter>\n");
					str.append("      <pushTime>" + DateUtil.formatDateToString(dt, "yyyy-MM-dd HH:mm") + "</pushTime>\n");
					str.append("  </row>\n");
				}
			}
			str.append(" </MessageBody>\n");
			str.append("</Creditease>");
			return str.toString();
		}
		/**
		 * 
		 * 描述：职位转换的代码
		 * 2013-2-21 上午10:26:14 by ygq
		 * @version
		 */
		public static String positionInfoConvertXml(List<Map<String,Object>> list,Date dt) {
			StringBuffer str = new StringBuffer("<Creditease>\n");
			str.append(" <MessageHead>\n");
			str.append("      <ID></ID>\n");
			str.append("      <tranCode>002002</tranCode>\n");
			str.append("	<tranType>0000</tranType>\n");
			String createTime = DateUtil.formatDateToString(new Date(),"yyyy-MM-dd HH:mm:ss");//报文生成时间
			str.append("      <tranTime>" + createTime + "</tranTime>\n");
//			str.append("      <tranTime>null</tranTime>\n");
			str.append("      <channel>hr</channel>\n");
			str.append("      <requestType></requestType>\n");
			str.append("      <exCode>00000</exCode>\n");
			str.append("	<errStack></errStack>\n");
			str.append("	<tranFlow></tranFlow>\n");
			str.append("	<count></count>\n");
			str.append("	<index></index>\n");
			str.append(" </MessageHead>\n");
			str.append(" <MessageBody>\n");
			if(list != null){
				for(int i = 0; i < list.size(); i++){
					Map<String,Object> map = list.get(i);
					str.append("  <row num=\""+(i+1)+"\">\n");
					if(AddressConfig.POSITIONCST==0){//测试异常的
						if(i==1||i==3){
							str.append("      <positionLevel></positionLevel>\n");
						}else{
							str.append("      <positionLevel>" + map.get("FPOSITIONLEVEL") + "</positionLevel>\n");
						}
					}else{
						str.append("      <positionLevel>" + map.get("FPOSITIONLEVEL") + "</positionLevel>\n");
					}
					str.append("      <positionLayer>" + map.get("FPOSITIONLAYER") + "</positionLayer>\n");
					str.append("      <jobType>" + map.get("FJOBTYPE") + "</jobType>\n");
					str.append("      <sortCode>" + map.get("FSORTCODE") + "</sortCode>\n");
					str.append("      <deletedStatus>" + map.get("FDELETEDSTATUS") + "</deletedStatus>\n");
					str.append("      <positionType>" + map.get("FPOSITIONTYPE") + "</positionType>\n");
					str.append("      <index>" + map.get("FINDEX") + "</index>\n");
					str.append("      <job>" + map.get("JOBNAME") + "</job>\n");
					str.append("      <effectDate>" + DateUtil.formatTimestampToString2((TIMESTAMP)map.get("FEFFECTDATE"),"yyyy-MM-dd HH:mm:ss") + "</effectDate>\n");
					str.append("      <invalidDate>" + DateUtil.formatTimestampToString2((TIMESTAMP)map.get("FINVALIDDATE"),"yyyy-MM-dd HH:mm:ss") + "</invalidDate>\n");
					str.append("      <simpleName>" + map.get("FSIMPLENAME") + "</simpleName>\n");
					str.append("      <description>" + map.get("FDESCRIPTION") + "</description>\n");
					str.append("      <number>" + map.get("FNUMBER") + "</number>\n");
					str.append("      <positionName>" + map.get("FPOSITIONNAME") + "</positionName>\n");
//					str.append("      <number>20130322abcdef</number>\n");
//					str.append("      <positionName>潘建平</positionName>\n");
					str.append("      <parentNumber>" + map.get("FPARENTNUMBER") + "</parentNumber>\n");
					str.append("      <deptNumber>" + map.get("FDEPTNUMBER") + "</deptNumber>\n");
					str.append("      <isMainJob>" + map.get("FISMAINJOB") + "</isMainJob>\n");
					str.append("      <pushTime>" + DateUtil.formatDateToString(dt, "yyyy-MM-dd HH:mm") + "</pushTime>\n");
					str.append("  </row>\n");
				}
			}
			str.append(" </MessageBody>\n");
			str.append("</Creditease>");
//			String strs = "";
//			try{
//				Document document = DocumentHelper.parseText(str.toString());
//				strs = document.asXML();
//			}catch(Exception ex){
//				ex.printStackTrace();
//			}
//		    return strs;
			return str.toString();
		}
		public static void test(List<Map<String,Object>> list){
			Map<String,Object> mapt = new HashMap<String,Object>();
			StringBuffer sb = new StringBuffer();
			int index = 0;
			for(int i=0;i<list.size();i++){
				Map<String,Object> mp = list.get(i);
				Object fnumber = mp.get("fnumber");
				//如果没有包含fnumber
				if(mapt.containsKey(fnumber)){//如果已经包含了该fnumber
					//如果还有，则在原来的基础上增加
					sb.append("<personInfo>\n");
					sb.append("<name>abc</name>\n");
					sb.append("<code>abc</code>\n");
					sb.append("</personInfo>\n");
					//放在最后增加
					index += 1;
				}else if(index ==0){//如果没有了，则清空，重新添加	
					//重新创建row
					sb.append("<row>\n");
					sb.append("<hello>122</hello>\n");
					sb.append("<personInfo>\n");
					sb.append("<name>abc</name>\n");
					sb.append("<code>abc</code>\n");
					sb.append("</personInfo>\n");
					mapt.put(fnumber.toString(), fnumber);//fnumber
					index = 1;
					if(i < list.size()-1){
						continue;
					}
				}
				//如果是1,则代表不存在一人多职的情况
				if(index >=1 || (i== list.size()-1)){
					index =  0;//初始化index
					sb.append("</row>\n");
				}
			}
			System.out.println("sb::::\n");
			System.out.println(sb);
		}
//		public static void main(String[] args) {
//			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
//			Map<String,Object> mp1 = new HashMap<String,Object>();
//			mp1.put("fnumber", "123");
//			mp1.put("name", "yang");
//			
//			Map<String,Object> mp2 = new HashMap<String,Object>();
//			mp2.put("fnumber", "123");
//			mp2.put("name", "gao");
//			
//			Map<String,Object> mp3 = new HashMap<String,Object>();
//			mp3.put("fnumber", "233");
//			mp3.put("name", "quan");
//			list.add(mp1);
//			list.add(mp2);
//			list.add(mp3);
//			test(list);
//		}
		/**
		 * 
		 * 描述：人员转换的代码
		 * loginName 如果不存在，则暂时创造出来的一个，叫loginName
		 * 2013-2-21 上午10:26:14 by ygq
		 * @version
		 */
		public static String personInfoConvertXml(List<Map<String,Object>> list,Date dt) {
			Map<String,Object> mapt = new HashMap<String,Object>();
			StringBuffer str = new StringBuffer("<Creditease>\n");
			str.append(" <MessageHead>\n");
			str.append("      <ID></ID>\n");
			str.append("      <tranCode>002001</tranCode>\n");
			str.append("	<tranType>0000</tranType>\n");
			String createTime = DateUtil.formatDateToString(new Date(),"yyyy-MM-dd HH:mm:ss");//报文生成时间
			str.append("      <tranTime>" + createTime + "</tranTime>\n");
			str.append("      <channel>hr</channel>\n");
			str.append("      <requestType></requestType>\n");
			str.append("      <exCode>00000</exCode>\n");
			str.append("	<errStack></errStack>\n");
			str.append("	<tranFlow></tranFlow>\n");
			str.append("	<count></count>\n");
			str.append("	<index></index>\n");
			str.append(" </MessageHead>\n");
			str.append(" <MessageBody>\n");
			if(list != null){
				int num = 1;//记录用的
				int index = 0;//初始化系统
				for(int i = 0; i < list.size(); i++){
					Map<String,Object> map = list.get(i);
					Object fnumber = map.get("FUSERCODE");
					if(index ==0){//如果没有了，则清空，重新添加	
						str.append("  <row num=\"" + num + "\">\n");
						if(AddressConfig.EMPCST==0){
							if(i==1||i==6||i==7){
								str.append("      <userName></userName>\n");
							}else{
								str.append("      <userName>" + map.get("FUSERNAME") + "</userName>\n");
							}
						}else{
							str.append("      <userName>" + map.get("FUSERNAME") + "</userName>\n");
						}
						String ab = (map.get("FLOGINNAME")==null)?"loginname":map.get("FLOGINNAME").toString();
						str.append("      <loginName>" + ab + "</loginName>\n");
						str.append("      <userEmail>" + map.get("FUSEREMAIL") + "</userEmail>\n");
						str.append("      <userActive>" + map.get("FUSERACTIVE") + "</userActive>\n");
						str.append("      <userOrderNo>" + map.get("FUSERORDERNO") + "</userOrderNo>\n");
						str.append("      <userStryle>" + map.get("FUSERSTRYLE") + "</userStryle>\n");
						str.append("      <userPassWord>" + map.get("FUSERPASSWORD") + "</userPassWord>\n");
						str.append("      <userCode>" + map.get("FUSERCODE") + "</userCode>\n");
						str.append("      <userSex>" + map.get("FUSERSEX") + "</userSex>\n");
						str.append("      <birthDay>" + DateUtil.formatTimestampToString2((TIMESTAMP)map.get("FBIRTHDAY"),"yyyy-MM-dd HH:mm:ss") + "</birthDay>\n");
						str.append("      <telPhone>" + map.get("FTELPHONE") + "</telPhone>\n");
						str.append("      <cellPhone>" + map.get("FCELLPHONE") + "</cellPhone>\n");
						str.append("      <telephoneHome>" + map.get("FTELEPHONEHOME") + "</telephoneHome>\n");
						str.append("      <certType>" + map.get("FCERTTYPE") + "</certType>\n");
						str.append("      <certNumber>" + map.get("FCERTNUMBER") + "</certNumber>\n");
						str.append("      <identityNo>" + map.get("FIDENTITYNO") + "</identityNo>\n");
						str.append("      <postCode>" + map.get("FPOSTCODE") + "</postCode>\n");
						str.append("      <address>" + map.get("FADDRESS") + "</address>\n");
						str.append("      <comments>" + map.get("FCOMMENTS") + "</comments>\n");
						str.append("      <userFax>" + map.get("FUSERFAX") + "</userFax>\n");
						str.append("      <registerTime>" + DateUtil.formatTimestampToString2((TIMESTAMP)map.get("FREGISTERTIME"),"yyyy-MM-dd  HH:mm:ss") + "</registerTime>\n");
						str.append("      <inService>" + map.get("FINSERVICE") + "</inService>\n");
						str.append("      <cityNumber>" + map.get("FCITYNUMBER") + "</cityNumber>\n");
						str.append("      <positionInfo>\n");//一个人可能对应多个PositiionInfo,这个需要考虑一下怎么搞
						str.append("      	  <positionCode>" + map.get("FPOSITIONCODE") + "</positionCode>\n");
						str.append("      	  <isMainJob>" + ((map.get("FISMAINJOB")==null||map.get("FISMAINJOB").toString().equals("0"))?"0":"1") + "</isMainJob>\n");
						str.append("      </positionInfo>\n");
						mapt.put(fnumber.toString(), fnumber);//fnumber
						index = 1;
					}else if(mapt.containsKey(fnumber) && index >=1){//如果已经包含了该fnumber
						//如果还有，则在原来的基础上增加
						str.append("      <positionInfo>\n");//一个人可能对应多个PositiionInfo,这个需要考虑一下怎么搞
						str.append("      	  <positionCode>" + map.get("FPOSITIONCODE") + "</positionCode>\n");
						str.append("      	  <isMainJob>" + ((map.get("FISMAINJOB")==null||map.get("FISMAINJOB").toString().equals("0"))?"0":"1") + "</isMainJob>\n");
						str.append("      </positionInfo>\n");
						//放在最后增加
						index ++;
					}else if(index >=1){
						str.append("      <directReports>" + map.get("FDIRECTREPORTS") + "</directReports>\n");
						//需要判断创建时间是否和最后修改时间是一致的
						str.append("      <operateSign>" + map.get("FOPERATESIGN") + "</operateSign>\n");
						//是否是真实的用户名
						str.append("      <operloginName>" + operloginNameVal(map.get("operloginName"),map.get("FUSEREMAIL")) + "</operloginName>\n");//这个该如何判断呢？
						str.append("      <pushTime>" + DateUtil.formatDateToString(dt, "yyyy-MM-dd HH:mm") + "</pushTime>\n");
						str.append("  </row>\n");
						//初始化一些变量
						index =  0;//初始化index
						num++;
						i--;//需要将i的值重新赋值下
					}
					//如果是1,则代表不存在一人多职的情况
					if(i==list.size()-1){//最后一个，需要增加这些控制的代码
						str.append("      <directReports>" + map.get("FDIRECTREPORTS") + "</directReports>\n");
						//需要判断创建时间是否和最后修改时间是一致的
						str.append("      <operateSign>" + map.get("FOPERATESIGN") + "</operateSign>\n");
						//是否是真实的用户名
						str.append("      <operloginName>" + operloginNameVal(map.get("operloginName"),map.get("FUSEREMAIL")) + "</operloginName>\n");//这个该如何判断呢？
						str.append("      <pushTime>" + DateUtil.formatDateToString(dt, "yyyy-MM-dd HH:mm") + "</pushTime>\n");
						str.append("  </row>\n");
					}
				}
			}
			str.append(" </MessageBody>\n");
			str.append("</Creditease>");
			return str.toString();
		}
		
		private static int operloginNameVal(Object operloginName,Object userEmail){
			if(operloginName != null){
				return 0;
			}
			if(userEmail != null){
				return 1;
			}
			return 2;
		}
		/**
		 * 
		 * 描述：模仿组织返回xml内容
		 * 2013-1-22 上午10:36:55 by ygq
		 * @version
		 * @param list
		 * @return
		 */
		public static String xmlInfoReturn(String xmlContent){
			StringBuffer str = new StringBuffer("<Creditease>\n");
			str.append(" <MessageHead>\n");
			str.append("      <ID></ID>\n");
			str.append("      <tranCode></tranCode>\n");
			str.append("	<tranType>0000</tranType>\n");
			String createTime = DateUtil.formatDateToString(new Date());//报文生成时间
			str.append("      <tranTime>" + createTime + "</tranTime>\n");
			str.append("      <channel>hr</channel>\n");
			str.append("      <requestType></requestType>\n");
//			str.append("      <exCode>00000</exCode>\n");//成功/失败 状态来回改变
			str.append("      <exCode>00001</exCode>\n");//失败 状态来回改变
			str.append("	<errStack></errStack>\n");
			str.append("	<tranFlow></tranFlow>\n");
			str.append("	<count></count>\n");
			str.append("	<index></index>\n");
			str.append(" </MessageHead>\n");
			str.append(" <MessageBody>\n");
			str.append("<row>" + xmlContent + "</row>");
			str.append("</MessageBody>\n");
			str.append("</Creditease>");
		    return str.toString();
		}

}