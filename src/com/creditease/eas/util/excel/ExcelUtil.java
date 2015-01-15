package com.creditease.eas.util.excel;

import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFCellUtil;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * 描述：Excel写操作帮助类
 * @author	zhaiwei
 * @since	2014-03-22
 * @version	1.0v
 */
public class ExcelUtil {
	private static final Logger log=Logger.getLogger(ExcelUtil.class);
	/**
	 * 功能：将HSSFWorkbook写入Excel文件
	 * @param 	wb		HSSFWorkbook
	 * @param 	response	
	 */
	public static void writeWorkbook(HSSFWorkbook wb,HttpServletResponse response){
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			wb.write(out);
			response.flushBuffer();
		}  catch (IOException e) {
			log.error(new StringBuffer("[").append(e.getMessage()).append("]").append(e.getCause()));
		} finally{
			try {
				if(out!=null){
					out.close();
				}
			} catch (IOException e) {
				log.error(new StringBuffer("[").append(e.getMessage()).append("]").append(e.getCause()));
			}
		}
	}
	/**
	 * 功能：创建HSSFSheet工作簿
	 * @param 	wb	HSSFWorkbook
	 * @param 	sheetName	String
	 * @return	HSSFSheet
	 */
	public static HSSFSheet createSheet(HSSFWorkbook wb,String sheetName){
		HSSFSheet sheet=wb.createSheet(sheetName);
		sheet.setDefaultColumnWidth(12);
		sheet.setGridsPrinted(false);
		sheet.setDisplayGridlines(false);
		return sheet;
	}
	/**
	 * 功能：创建HSSFRow
	 * @param 	sheet	HSSFSheet
	 * @param 	rowNum	int
	 * @param 	height	int
	 * @return	HSSFRow
	 */
	public static HSSFRow createRow(HSSFSheet sheet,int rowNum,int height){
		HSSFRow row=sheet.createRow(rowNum);
		row.setHeight((short)height);
		return row;
	}
	/**
	 * 功能：创建CellStyle样式
	 * @param 	wb				HSSFWorkbook	
	 * @param 	backgroundColor	背景色	
	 * @param 	foregroundColor	前置色
	 * @param	font			字体
	 * @return	CellStyle
	 */
	public static CellStyle createCellStyle(HSSFWorkbook wb,short backgroundColor,short foregroundColor,short halign,Font font){
		CellStyle cs=wb.createCellStyle();
		cs.setAlignment(halign);
		cs.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		cs.setFillBackgroundColor(backgroundColor);
		cs.setFillForegroundColor(foregroundColor);
		cs.setFillPattern(CellStyle.SOLID_FOREGROUND);
		cs.setFont(font);
		return cs;
	}
	/**
	 * 功能：创建带边框的CellStyle样式
	 * @param 	wb				HSSFWorkbook	
	 * @param 	backgroundColor	背景色	
	 * @param 	foregroundColor	前置色
	 * @param	font			字体
	 * @return	CellStyle
	 */
	public static CellStyle createBorderCellStyle(HSSFWorkbook wb,short backgroundColor,short foregroundColor,short halign,Font font){
		CellStyle cs=wb.createCellStyle();
		cs.setAlignment(halign);
		cs.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		cs.setFillBackgroundColor(backgroundColor);
		cs.setFillForegroundColor(foregroundColor);
		cs.setFillPattern(CellStyle.SOLID_FOREGROUND);
		cs.setFont(font);
		cs.setBorderLeft(CellStyle.BORDER_DASHED);
		cs.setBorderRight(CellStyle.BORDER_DASHED);
		cs.setBorderTop(CellStyle.BORDER_DASHED);
		cs.setBorderBottom(CellStyle.BORDER_DASHED);  
		return cs;
	}
	
	
	/**
	 * 功能：创建CELL
	 * @param 	row		HSSFRow	
	 * @param 	cellNum	int
	 * @param 	style	HSSFStyle
	 * @return	HSSFCell
	 */
	public static HSSFCell createCell(HSSFRow row,int cellNum,CellStyle style){
		HSSFCell cell=row.createCell(cellNum);
		cell.setCellStyle(style);
		return cell;
	}
	
	/**
	 * 描述：创建表头样式
	 * @param cellStyle
	 * @param wb
	 * @return
	 */
	public static final HSSFCellStyle createHeaderStyle(HSSFCellStyle cellStyle,HSSFWorkbook wb) {
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		HSSFFont fontStyle = wb.createFont();
		fontStyle.setFontName("宋体");
		fontStyle.setFontHeightInPoints((short) 13);
		fontStyle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//字体加粗
		cellStyle.setFont(fontStyle);
		return cellStyle;
	}
	
	/**
	 * 创建数据样式
	 * @param cellStyle
	 * @param wb
	 * @return
	 */
	public static final HSSFCellStyle createTextStyle(HSSFCellStyle cellStyle,HSSFWorkbook wb) {
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		HSSFFont fontStyle = wb.createFont();
		fontStyle.setFontName("宋体");
		fontStyle.setFontHeightInPoints((short) 13);
		cellStyle.setFont(fontStyle);
		return cellStyle;
	}
	
	/**
	 * 功能：合并单元格
	 * @param 	sheet		HSSFSheet
	 * @param 	firstRow	int
	 * @param 	lastRow		int
	 * @param 	firstColumn	int
	 * @param 	lastColumn	int
	 * @return	int			合并区域号码
	 */
	public static int mergeCell(HSSFSheet sheet,int firstRow,int lastRow,int firstColumn,int lastColumn){
		return sheet.addMergedRegion(new CellRangeAddress(firstRow,lastRow,firstColumn,lastColumn));	
	}
	/**
	 * 功能：创建字体
	 * @param 	wb			HSSFWorkbook	
	 * @param 	boldweight	short
	 * @param 	color		short
	 * @return	Font	
	 */
	public static Font createFont(HSSFWorkbook wb,short boldweight,short color,short size){
		Font font=wb.createFont();
		font.setBoldweight(boldweight);
		font.setColor(color);
		font.setFontHeightInPoints(size);
		return font;
	}
	/**
	 * 设置合并单元格的边框样式
	 * @param	sheet	HSSFSheet	
	 * @param 	ca		CellRangAddress
	 * @param 	style	CellStyle
	 */
	public static void setRegionStyle(HSSFSheet sheet, CellRangeAddress ca,CellStyle style) {  
	    for (int i = ca.getFirstRow(); i <= ca.getLastRow(); i++) {  
	        HSSFRow row = HSSFCellUtil.getRow(i, sheet);  
	        for (int j = ca.getFirstColumn(); j <= ca.getLastColumn(); j++) {  
	            HSSFCell cell = HSSFCellUtil.getCell(row, j);  
	            cell.setCellStyle(style);  
	        }  
	    }  
	}  
}