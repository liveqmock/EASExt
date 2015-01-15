/**
 * 
 */
package com.creditease.eas.util.port;

/**
 *  @描述：工具类 
 * @WDWUtil.java
 * created at 2013-7-17 下午07:03:54 by Administrator
 * 
 * @author Administrator({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class WDWUtil {

	   /** 
     *  
     * @描述：是否是2003的excel，返回true是2003 
     *  
     * @参数：@param filePath　文件完整路径 
     *  
     * @参数：@return 
     *  
     * @返回值：boolean 
     */  
  
    public static boolean isExcel2003(String filePath)  
    {  
        return filePath.matches("^.+\\.(?i)(xls)$");  
    }  
  
    /** 
     *  
     * @描述：是否是2007的excel，返回true是2007 
     *  
     * @参数：@param filePath　文件完整路径 
     *  
     * @参数：@return 
     *  
     * @返回值：boolean 
     */  
  
    public static boolean isExcel2007(String filePath)  
    {  
        return filePath.matches("^.+\\.(?i)(xlsx)$");  
    }  
    /** 
     *  
     * @描述：判断是否有宏代码   
     *  
     * @参数：@param filePath　文件完整路径 
     *  
     * @参数：@return 
     *  
     * @返回值：boolean 
     */  
  
    public static boolean isExcelXlsm(String filePath)  
    {  
        return filePath.matches("^.+\\.(?i)(xlsm)$");  
    }  
}
