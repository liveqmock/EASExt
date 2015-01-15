package com.creditease.eas.util;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
/***
 * 设置行表头，这个貌似没什么用吧
 * @Title:HeaderSet.java
 * @Package com.creditease.eas.util
 * created at 2014-6-3 上午09:51:10 by ygq
 * @author ygq
 * @version 1.0
 */
public class HeaderSet {
	//设置第三行
	public static void setHeadeInit(XSSFSheet sheet,XSSFCellStyle style){
		String[] header1 = {"typename","wayname","sendtime","cell","departid","departname","birthday","enterdate","postid","postname",
				"receiverids","receiver","email","copyids","copyperson","theme","creatime","cellcontent"
				};
		XSSFRow row = sheet.createRow(0);
		for (int i = 0; i < header1.length; i++) {
            XSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
            XSSFRichTextString text = new XSSFRichTextString(header1[i]);
            cell.setCellValue(text);
        }
	}
}
