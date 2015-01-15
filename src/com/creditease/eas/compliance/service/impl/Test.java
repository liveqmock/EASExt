package com.creditease.eas.compliance.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;

import com.creditease.eas.compliance.bean.CaseDetailType;
import com.creditease.eas.compliance.bean.CaseType;
import com.creditease.eas.compliance.bean.Complain;
import com.creditease.eas.util.ExpExcelUtil;

public class Test {
	static void exportExcel(List<Complain> complains) throws Exception {
		OutputStream out = new FileOutputStream(new File("F:/test.xls"));

		String[] headers = { "案件编号", "案件初步违规分类", "详细分类（初）", "违规等级（初）",
				"Basel分类（初）" };

		try {
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFCellStyle cellStyle = wb.createCellStyle();
			HSSFSheet sheet = wb.createSheet("sheet1");// 设置页的名称

			HSSFRow row = sheet.createRow(0);
			row.setHeight((short) 500);// 设置行高
			for (int i = 0; i < headers.length; i++) {
				sheet.setColumnWidth(i, 5000);// 设置单元格的宽度
				ExpExcelUtil.createTxtCell(wb, row, i, headers[i], ExpExcelUtil
						.createCollStyle(cellStyle, wb));// 创建表格第一行
			}
			
			
			HSSFRow row1 = sheet.createRow(1);
			
			sheet.addMergedRegion(new CellRangeAddress(1, 2, 0, 2));
			
			Cell cell=row1.createCell(0);
			cell.setCellValue("测试");
			CellUtil.setAlignment(cell, wb, CellStyle.VERTICAL_CENTER);
			
			
			
			
			wb.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.close();// 关闭

	}
	
	public static void main(String[] args) {
		try {
			exportExcel(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
