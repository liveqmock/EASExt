/**
 * 
 */
package com.creditease.eas.util.excel;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 进行正则验证
 * @Title:RegularValid.java
 * @Package com.creditease.eas.adminipurc.util
 * created at 2014-6-6 下午07:33:11 by ygq
 * @author ygq
 * @version 1.0
 */
public class RegularValid {
	/***
	 * 进行数字验证
	* @Title: numberValid
	*created at 2014-6-6 下午07:35:13 by ygq  
	* @param textValue
	* @return
	* @return boolean
	 */
	public static boolean numberValid(String checkValue){
		boolean result = false;
		if(null != checkValue  && !"".equals(checkValue)){
			String type = "+";
			if("0".equals(checkValue)){
				result = true;
			}else if("0.00".equals(checkValue)){
				result = true;
			}else if(checkValue.indexOf("-")>0){
				type = "-";
			}else{
				type = "+";
			}
			if(!result){//如果为true则结束，如果为false,则继续查找
				
				result = checkNumber(checkValue,type);
			}
			if(!result){//如果为true则结束，如果为false,则继续查找
				result = checkFloat(checkValue,type);
			}
		}
		return result;
	}
	   /**  
     * 检查整数  
     * @param num  
     * @param type "0+":非负整数 "+":正整数 "-0":非正整数 "-":负整数 "":整数  
     * @return  
     */  
    public static boolean checkNumber(String num,String type){   
        String eL = "";   
        if(type.equals("0+"))eL = "^\\d+$";//非负整数   
        else if(type.equals("+"))eL = "^\\d*[1-9]\\d*$";//正整数   
        else if(type.equals("-0"))eL = "^((-\\d+)|(0+))$";//非正整数   
        else if(type.equals("-"))eL = "^-\\d*[1-9]\\d*$";//负整数   
        else eL = "^-?\\d+$";//整数   
        Pattern p = Pattern.compile(eL);   
        Matcher m = p.matcher(num);   
        boolean b = m.matches();   
        return b;   
    }   
    /**  
     * 检查浮点数  
     * @param num  
     * @param type "0+":非负浮点数 "+":正浮点数 "-0":非正浮点数 "-":负浮点数 "":浮点数  
     * @return  
     */  
    public static boolean checkFloat(String num,String type){   
        String eL = "";   
        if(type.equals("0+"))eL = "^\\d+(\\.\\d+)?$";//非负浮点数   
        else if(type.equals("+"))eL = "^((\\d+\\.\\d*[1-9]\\d*)|(\\d*[1-9]\\d*\\.\\d+)|(\\d*[1-9]\\d*))$";//正浮点数   
        else if(type.equals("-0"))eL = "^((-\\d+(\\.\\d+)?)|(0+(\\.0+)?))$";//非正浮点数   
        else if(type.equals("-"))eL = "^(-((\\d+\\.\\d*[1-9]\\d*)|(\\d*[1-9]\\d*\\.\\d+)|(\\d*[1-9]\\d*)))$";//负浮点数   
        else eL = "^(-?\\d+)(\\.\\d+)?$";//浮点数   
        Pattern p = Pattern.compile(eL);   
        Matcher m = p.matcher(num);   
        boolean b = m.matches();   
        return b;   
    }  
	/***
	 * 验证所有的日期格式是否正确
	* @Title: validAllDateFormat
	*created at 2014-6-7 下午02:43:20 by ygq  
	* @param checkValue
	* @return
	* @return boolean
	 */
	  public static boolean validAllDateFormat(String checkValue){
	         String eL = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-9]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";  
	         Pattern p = Pattern.compile(eL);   
	         Matcher m = p.matcher(checkValue);   
	         boolean result = m.matches();  
	         if(result){  
	             System.out.println("格式正确");  
	         }  
	         else{  
	             System.out.println("格式错误");  
	         }  
	         return result;
	  }
	  /***
	   * 验证符合条件的日期格式("-")
	  * @Title: validAllDateFormat
	  *created at 2014-6-7 下午02:43:49 by ygq  
	  * @param checkValue
	  * @return
	  * @return boolean
	   */
	  public static boolean validDateFormat(String checkValue,String split){
		  boolean result = false;
		  if(checkValue != null && !checkValue.equals("")) {  
	            if (checkValue.split(split).length > 1)  
	            {  
	                result = true;
	            }  
	        }
	      return result;
	  }
}
