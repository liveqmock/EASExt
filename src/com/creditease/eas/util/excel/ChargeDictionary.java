package com.creditease.eas.util.excel;

public class ChargeDictionary {
	
	/**
	 * 收费人员
	 * @param ffreceiveType
	 * @return
	 */
	public static String getFreceiveType(String ffreceiveType){
		String receiveType="";
		if(ffreceiveType !=null &&!"".equals(ffreceiveType)){
			if(ffreceiveType.equals("1")){
				receiveType="员工";
			}else if(ffreceiveType.equals("2")){
				receiveType="中介";
			}else if(ffreceiveType.equals("3")){
				receiveType="与同行串通";
			}
		}
		
		return receiveType;
	}
	
	/**
	 * 收费理由 
	 * @param fftype
	 * @return
	 */
	public static String getFtype(String fftype){
		String type="";
		if(fftype!=null&&!"".equals(fftype)){
			if(fftype.equals("1")){
				type="好处费";
			}else if(fftype.equals("2")){
				type="客户包装费";
			}else if(fftype.equals("3")){
				type="代客户还款";
			}else if(fftype.equals("4")){
				type="办理外部业务";
			}else if(fftype.equals("5")){
				type="协办征信、流水";
			}
		}
		return type;
	}
	
	/**
	 * 给付方式
	 * @param ffmode
	 * @return
	 */
	public static String getFmode(String ffmode){
		String mode="";
		if(ffmode!=null&&!"".equals(ffmode)){
			if(ffmode.equals("1")){
				mode="现金";
			}else if(ffmode.equals("2")){
				mode="转账";
			}else if(ffmode.equals("3")){
				mode="从客户账户中直接支取";
			}
		}
		return mode;
		
	}
}
