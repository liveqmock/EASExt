package com.creditease.eas.util.excel;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFRegionUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;

public class POIUtil {
	/**
	 * 得到当前行 
	 * @param sheet
	 * @param rowIndex
	 * @return
	 */
	public static Row createRow(HSSFSheet sheet,int rowIndex){
		Row r=sheet.getRow(rowIndex);
		if(r==null){
			r=sheet.createRow(rowIndex);
		}
		return r;
	}
	
	public static final HSSFCellStyle createCollStyle(HSSFWorkbook wb) {
		HSSFCellStyle cellStyle=wb.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
		//cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cellStyle.setWrapText(true);
		HSSFFont fontStyle = wb.createFont();
		fontStyle.setFontName("宋体");
		fontStyle.setFontHeightInPoints((short) 10);
		cellStyle.setFont(fontStyle);
		return cellStyle;
	}
	
	public static HSSFCellStyle createCellStyle(HSSFWorkbook wb) {
		HSSFCellStyle cellStyle=wb.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
		//cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cellStyle.setWrapText(true);
		HSSFFont fontStyle = wb.createFont();
		fontStyle.setFontName("宋体");
		fontStyle.setFontHeightInPoints((short) 11);
		cellStyle.setFont(fontStyle);
		return cellStyle;
	}

	/**
	 * 创建合并单元格样式
	 */
	public static void createRangeCell(HSSFWorkbook wb, HSSFSheet sheet,
			CellRangeAddress region) {
		HSSFRegionUtil.setBorderTop(CellStyle.BORDER_THIN, region, sheet, wb);
		HSSFRegionUtil.setBorderLeft(CellStyle.BORDER_THIN, region, sheet, wb);
		HSSFRegionUtil.setBorderBottom(CellStyle.BORDER_THIN, region, sheet, wb);
		HSSFRegionUtil.setBorderRight(CellStyle.BORDER_THIN, region, sheet, wb);
	}
	
	public static void setMergerBorder(HSSFSheet sheet,HSSFCellStyle cellStyle,CellRangeAddress region){
		int startRow=region.getFirstRow();
		int endRow=region.getLastRow();
		int startColumn=region.getFirstColumn();
		int endColumn=region.getLastColumn();
		for (int i = startRow; i <= endRow; i++) {
			Row r=createRow(sheet, i);
			//Row r=sheet.getRow(i);
			for (int j = startColumn; j <= endColumn; j++) {
				//Cell c=r.getCell(j);
				Cell c=createCell(r,j);
				c.setCellStyle(cellStyle);
			}
		}
		
	}
	
	private static Cell createCell(Row r,int i){
		Cell cell=r.getCell(i);
		if(cell==null){
			cell=r.createCell(i);
		}
		return cell;
	}
	
	
	public static HSSFCellStyle createMergCellStyle(HSSFWorkbook wb){
		HSSFCellStyle cellStyle=wb.createCellStyle();
		cellStyle.setBorderTop(CellStyle.BORDER_THIN);
		cellStyle.setBorderRight(CellStyle.BORDER_THIN);
		cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
		return cellStyle;
	}
	
	/**
	 * 主标题
	 */
	public static HSSFCellStyle createMainTitleFont(HSSFWorkbook wb){
		HSSFCellStyle cellStyle=wb.createCellStyle();
		
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		
		cellStyle.setWrapText(true);
		
		HSSFFont fontStyle = wb.createFont();
		fontStyle.setFontName("宋体");
		fontStyle.setFontHeightInPoints((short) 13 );
		cellStyle.setFont(fontStyle);
		return cellStyle;
		
	}
	
	/**
	 * 副标题
	 */
	public static HSSFCellStyle createSubjectTitleFont(HSSFWorkbook wb){
		HSSFCellStyle cellStyle=wb.createCellStyle();
		
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		
		cellStyle.setWrapText(true);
		
		HSSFFont fontStyle = wb.createFont();
		fontStyle.setFontName("宋体");
		fontStyle.setFontHeightInPoints((short) 12 );
		cellStyle.setFont(fontStyle);
		return cellStyle;
	}
	
	/**
	 * 正文
	 */
	public static HSSFCellStyle createTextCell(HSSFWorkbook wb){
		HSSFCellStyle cellStyle=wb.createCellStyle();
		
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		
		cellStyle.setWrapText(true);
		
		HSSFFont fontStyle = wb.createFont();
		fontStyle.setFontName("宋体");
		fontStyle.setFontHeightInPoints((short) 11 );
		cellStyle.setFont(fontStyle);
		return cellStyle;
	}
}
