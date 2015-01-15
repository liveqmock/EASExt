package com.change.eas.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class LimitedpartnerUtil {
	public static void main(String[] args) {
		double i = 123;
		String num = getNumber(i);
		System.out.println(num);
		System.out.println(i);
	}
	public static String getNumber(double i){
		String num = i+"";
		String[] sp = num.split("[.]");
		if(sp.length==1){
			return sp[0];
		}
		if(sp[1]==null||"".equals(sp[1])){
			return sp[0];
		}
		int x = Integer.parseInt(sp[1]);
		if(x==0){
			return sp[0];
		}else{
			return num;
		}
	}
	
	public final static String CONST_DONE_GS="工商变更完成";
	
	public final static String CONST_DONE_TAX="税务变更完成";
	
	public final static String CONST_STATUS_SAVE="暂存";
	
	public final static String CONST_STATUS_SUBMIT="提交";
	
	public final static String CONST_STATUS_CHECK="审核";
	
	public final static String CONST_STATUS_FANCHECK="反审核";
	
    public final static String CONST_UNGATHER="未采集";
	
	public final static String CONST_UNDONE="未完成";
	
	public final static String CONST_DONE="已完成";
	
    public final static String CONST_CHECK="已审核";
	
	public final static String CONST_UNMAKE="未制作";

	public static String getStatusValue(int status) throws Exception {
		String Statusvalue = "";
		if ((Integer) status != null) {
			Statusvalue = status == 0 ? "未采集"
					: status == 1 ? "未完成 "
							: status == 2 ? "已完成 "
									: status == 3 ? "未制作 "
											: status == 4 ? "已审核 "
													: status == 5 ? "未暂存 "
															: status == 6 ? "已暂存 "
																	: status == 7 ? "未提交 "
																			: status == 8 ? "已提交 "
																					: status == 9 ? "未审核 "
																							: status == 10 ? "已审核通过 "
																									: "wrong";
															
		} else
			throw new Exception("无法识别的状态值！！！");
		if ("wrong".equals(Statusvalue))
			throw new Exception("错误的状态值，请审查！！！");
		return Statusvalue;
	}

	public static int getStatus(String Statusvalue) throws Exception {
		int status = -1;
		if (!"".equals(Statusvalue) && Statusvalue != null) {
			status = "未采集".equals(Statusvalue) ? 0
					: "未完成".equals(Statusvalue) ? 1
							: "已完成".equals(Statusvalue) ? 2 : "未制作"
									.equals(Statusvalue) ? 3 : "已审核"
									.equals(Statusvalue) ? 4 : "未暂存"
									.equals(Statusvalue) ? 5 : "已暂存"
									.equals(Statusvalue) ? 6 : "未提交"
									.equals(Statusvalue) ? 7 : "已提交"
									.equals(Statusvalue) ? 8 : "未审核"
									.equals(Statusvalue) ? 9 : "已审核通过"
									.equals(Statusvalue) ? 10 : 99999;
		}
		if (status == 99999)
			throw new Exception("不存在此状态值！！！");
		return status;
	}
	
	public static double getPercent(double account,double sumaccount,int figure){
		double result=0;
		switch(figure){
		case 1:
			result=(Math.round((account/sumaccount)*1000)/1000.0);
			break;
		case 2:
			result=(Math.round((account/sumaccount)*10000)/10000.00);
			break;
		case 3:
			result=(Math.round((account/sumaccount)*100000)/100000.000);
			break;
		case 4:
			result=(Math.round((account/sumaccount)*1000000)/1000000.0000);
			break;
		}
		return result;
	}
	

	public static String getPar3(String par2) {
		for (int i = 0; i < par2.length(); i++) {
			if (par2.startsWith("0")) {
				par2 = par2.substring(1);
			}
			if (par2.startsWith(".")) {
				par2 = "0" + par2;
			}
		}
		return par2;
	}
	
	public static String getPercent_new(double account,double sumaccount,int figure){
		String result="";
        DecimalFormat df1  = new DecimalFormat("###.000"); //1 
		DecimalFormat df2  = new DecimalFormat("###.0000"); // 2
		DecimalFormat df3  = new DecimalFormat("###.00000"); //3 
		DecimalFormat df4  = new DecimalFormat("###.000000"); //4
		BigDecimal d = new BigDecimal(0);
		switch(figure){
		case 1:
			result=df1.format(account/sumaccount);
			break;
		case 2:
			result=df2.format(account/sumaccount);
			break;
		case 3:
			result=df3.format(account/sumaccount);
			break;
		case 4:
			result=df4.format(account/sumaccount);
			break;
		}
		
		return result;
	}
	
	public static double getdouble(double value) {
		if(value==0)
			return 0;
		BigDecimal b = new BigDecimal(value);
		double number = b.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
		return number;
	}
	
	public static String getStr(String str, int figure) {
		if (str.length() != 0)
			str = str.substring(1);
		for (int i = 0; i < str.length(); i++) {
			if (str.startsWith("0"))
				str = str.substring(1);
			else
				break;
		}
		if (figure == 1) { // 123
			if (str.length() == 0) {
				str = "0.0%";
			} else if (str.length() == 1) {
				str = "0." + str + "%";
			} else {
				str = str.substring(0, str.length() - 1) + "."
						+ str.substring(str.length() - 1, str.length()) + "%";
			}
		} else if (figure == 2) { // 1234
			if (str.length() == 0) {
				str = "0.00%";
			} else if (str.length() == 1) {
				str = "0.0" + str + "%";
			} else if (str.length() == 2) {
				str = "0." + str + "%";
			} else {
				str = str.substring(0, str.length() - 2) + "."
						+ str.substring(str.length() - 2, str.length()) + "%";
			}
		} else if (figure == 3) { // 12345
			if (str.length() == 0) {
				str = "0.000%";
			} else if (str.length() == 1) {
				str = "0.00" + str + "%";
			} else if (str.length() == 2) {
				str = "0.0" + str + "%";
			} else if (str.length() == 3) {
				str = "0." + str + "%";
			} else {
				str = str.substring(0, str.length() - 3) + "."
						+ str.substring(str.length() - 3, str.length()) + "%";
			}
		} else if (figure == 4) {
			if (str.length() == 0) {
				str = "0.0000%";
			} else if (str.length() == 1) {
				str = "0.000" + str + "%";
			} else if (str.length() == 2) {
				str = "0.00" + str + "%";
			} else if (str.length() == 3) {
				str = "0.0" + str + "%";
			} else if (str.length() == 4) {
				str = "0." + str + "%";
			} else {
				str = str.substring(0, str.length() - 4) + "."
						+ str.substring(str.length() - 4, str.length()) + "%";
			}
		}
		return str;
	}
	
	public static double getAdd2(String str){
		str=str.substring(0, str.length()-1);
		str=str.replace(".", "");
		str="0."+str;
		double a=Double.parseDouble(str);
		return a;
	}
	
	public static double getAdd(String str){
		str=str.substring(0, str.length()-1);
		String strArray[]=str.split("\\.");
		str=str.replace(".", "");
	    if(strArray[0].length() == 1 &&("0").equals(strArray[0]))
			str = "0.00" + str;
		else if(strArray[0].length() == 1)
			str = "0.0" + str;
		else
			str = "0." + str;
		double a=Double.parseDouble(str);
		return a;
	}

	public static String get(String str,int figure){
		String result="";
		if(str.startsWith("."))
			str=str.substring(1);
		str=str.substring(0, str.length()-figure)+"."+str.substring(str.length()-figure, str.length());
		result=str;
		return result;
	}

	public static String getPar(double num, double length) {
		String tit = num + "";
		String[] sp = tit.split("\\.");
		String res = "0.";
		for (int i = 0; i < length; i++) {
			if (sp[1].length() > i) {
				res += sp[1].charAt(i);
			} else {
				res += "0";
			}
		}
		return res;
	}

	public static String getPar2(String num) {
		String[] sp = num.split("\\.");
		String str1 = sp[1].substring(0, 2);
		String str2 = sp[1].substring(2);
		return str1 + "." + str2 + "%";
	}
	public static String getPar4(String num) {
		String[] sp = num.split("\\.");
		String str1 = sp[1].substring(0, 2);
		String str2 = sp[1].substring(2);
		return str1 + "." + str2;
	}
}
