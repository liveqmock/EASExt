package com.change.eas.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013-6-19
 * </p>
 * <p>
 * Company: ���ν���
 * </p>
 * 
 * @author guzi
 * @version 1.0.0
 */
public class Excel {
	private Workbook book;
	/**
	 * ����Excel�ļ�/����Excel�ļ�
	 */
	private String operate;
	/**
	 * �ļ�·��
	 */
	private String path;
	/**
	 * ����Excel�ļ�
	 */
	public static final String ANALYSIS = "00";
	/**
	 * ����Excel�ļ�
	 */
	public static final String CREATE = "01";
	/**
	 * �����õ���ֵMap
	 */
	public Map<Integer, Map<Integer, String>> valueMap;
	
	public static boolean isExcel2003 = true;
	
	private Excel(Workbook book) {
		this.book = book;
	}
	
	public static boolean checkIsExcel2003(String filename){
		isExcel2003=filename.matches("^.+\\.(?i)(xls)$");
		return isExcel2003;
	}

	public static Excel getExcelInstance(InputStream in) {
		Workbook book;
		try {
			if (isExcel2003)
				book = new HSSFWorkbook(in);
			else
				book = new XSSFWorkbook(in);
		} catch (IOException e) {
			return null;
		}
		Excel excel = new Excel(book);
		excel.setOperate(Excel.ANALYSIS);
		return excel;
	}

	/**
	 * @author guzi
	 * @function �õ�����Excel��ʵ��
	 * @process
	 * @param path
	 *            Ҫ������excel �ļ�·��
	 * @param flag
	 *            �������:����Excel�ļ�/����Excel�ļ�
	 * @return
	 */
	public static Excel getExcelInstance(String path, String flag) {
		HSSFWorkbook book;
		File file = new File(path);
		if (!file.getName().endsWith("xls")) {
			return null;
		}
		if (Excel.ANALYSIS.equalsIgnoreCase(flag)) {
			try {
				if (!file.isFile()) {
					return null;
				}
				book = new HSSFWorkbook(new FileInputStream(file));
			} catch (IOException e) {
				return null;
			}
		} else if (Excel.CREATE.equalsIgnoreCase(flag)) {
			if (!file.exists()) {
				new File(file.getParent()).mkdirs();
				try {
					file.createNewFile();
				} catch (IOException e) {
					return null;
				}
			}
			book = new HSSFWorkbook();
		} else {
			return null;
		}
		Excel excel = new Excel(book);
		excel.setPath(path);
		excel.setOperate(flag);
		return excel;
	}

	/**
	 * @author guzi
	 * @function
	 * @process
	 * @param cell
	 * @return
	 */
	public boolean updateExcelCell(Cell cell) {
		Sheet sheet = book.getSheetAt(0);
		Row row = sheet.getRow(cell.getRowIndex());
		Cell ce = row.getCell(cell.getColumnIndex());
		ce.setCellValue(cell.getStringCellValue());
		try {
			book.write(new FileOutputStream(new File(path)));
		} catch (FileNotFoundException e) {
			return false;
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	/**
	 * @author guzi
	 * @function ���µ�Ԫ���ֵ
	 * @process
	 * @param x
	 *            �к�
	 * @param y
	 *            �к�
	 * @param v
	 *            ��Ԫ���ֵ
	 * @return �Ƿ���³ɹ�
	 */
	public boolean updateExcelCell(int x, int y, Object v) {
		Sheet sheet = book.getSheetAt(0);
		Row row = sheet.getRow(x);
		Cell cell = row.getCell(y);
		cell.setCellValue(v.toString());
		try {
			book.write(new FileOutputStream(new File(path)));
		} catch (FileNotFoundException e) {
			return false;
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	/**
	 * @author guzi
	 * @function ����Excel���õ������ϵ�ֵ
	 * @process
	 * @param x
	 *            �к�
	 * @param y
	 *            �к�
	 * @return �����ϵ�ֵ
	 */
	public String getValue(int x, int y) {
		if (valueMap != null)
			return valueMap.get(x).get(y);
		return null;
	}

	/**
	 * @author guzi
	 * @function �ڲ�����Excel�������µõ�������ϵ�ֵ
	 * @process
	 * @param x
	 *            �к�
	 * @param y
	 *            �к�
	 * @return
	 */
	public String getExcelValue(int x, int y) {
		Sheet sheet = book.getSheetAt(0);
		Row row = sheet.getRow(x);
		Cell cell = row.getCell(y);
		return getCellString(cell);
	}

	/**
	 * @author guzi
	 * @function ����Excel�ļ�
	 * @process ��������һ��Sheet
	 */
	public void analysisExcel() {
		analysisExcel("");
	}

	public Cell getExcelCell(int x, int y) {
		Sheet sheet = book.getSheetAt(0);
		Row row = sheet.getRow(x);
		return row.getCell(y);
	}

	/**
	 * @author guzi
	 * @function ����Excel�ļ�
	 * @param sheetName
	 *            ����
	 * @process ��������һ��Sheet
	 */
	public void analysisExcel(String sheetName) {
		if (operate.equalsIgnoreCase(CREATE)) {
			return;
		}
		valueMap = new HashMap<Integer, Map<Integer, String>>();
		Sheet sheet = book.getSheet(sheetName);
		if (sheet == null) {
			sheet = book.getSheetAt(0);
		}
		int rowNum = sheet.getLastRowNum();
		int count = 0;
		xx:for (int i = 0; i <= rowNum; i++) {
			Row row = sheet.getRow(i);
			if (row == null) {
					break;
			}
			short cellNum = row.getLastCellNum();
			HashMap<Integer, String> rowMap = new HashMap<Integer, String>();
			for (int j = 0; j < cellNum; j++) {
				Cell cell = row.getCell(j);
				if(j==0&&cell==null){
					break xx;
				}
				rowMap.put(j, getCellString(cell));
			}
			valueMap.put(i, rowMap);
		}
	}

	/**
	 * @function �õ�Cell��ֵ
	 * @process
	 * @param cell
	 * @return
	 */
	private String getCellString(Cell cell) {
		Object result = null;
		if (cell != null) {
			// ��Ԫ�����ͣ�Numeric:0,String:1,Formula:2,Blank:3,Boolean:4,Error:5
			int cellType = cell.getCellType();
			switch (cellType) {
			case Cell.CELL_TYPE_STRING:
				result = cell.getRichStringCellValue().getString();
				break;
			case Cell.CELL_TYPE_NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					Date d = cell.getDateCellValue();
					DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
					result = formater.format(d);
				} else {
					result = cell.getNumericCellValue();
				}
				break;
			case Cell.CELL_TYPE_FORMULA:
				result = cell.getNumericCellValue();
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				result = cell.getBooleanCellValue();
				break;
			case Cell.CELL_TYPE_BLANK:
				result = null;
				break;
			case Cell.CELL_TYPE_ERROR:
				result = null;
				break;
			default:
				System.out.println("ö������������");
				break;
			}
		}
		return result == null ? "" : result.toString();
	}

	/**
	 * @author guzi
	 * @function �õ�����Excel��õ���ֵMap
	 * @process
	 * @return ����Excel��õ���ֵMap
	 */
	public Map<Integer, Map<Integer, String>> getValueMap() {
		return valueMap;
	}

	/**
	 * @author guzi
	 * @function ����Excel�ļ�
	 * @process
	 * @param table
	 *            ���к�(��0��ʼ)ΪKey��map,ֵ
	 *            ���Ե�Ԫ���(��0��ʼ)ΪKey�ĵ�Map,ֵ��String(������չ�ɶ���)
	 * @return �޸ĺ��Excel�ļ�·��
	 */
	public String createExcel(Map<Integer, Map<Integer, String>> table) {
		if (operate.equalsIgnoreCase(ANALYSIS)) {
			return null;
		}
		Sheet sheet = book.createSheet();
		Set<Entry<Integer, Map<Integer, String>>> set = table.entrySet();
		for (Entry<Integer, Map<Integer, String>> entry : set) {
			Map<Integer, String> rowValue = entry.getValue();
			Row row = sheet.createRow(entry.getKey());
			Set<Entry<Integer, String>> entryCell = rowValue.entrySet();
			for (Entry<Integer, String> ent : entryCell) {
				String cellValue = ent.getValue();
				Cell cell = row.createCell(ent.getKey());
				cell.setCellValue(cellValue);
			}
		}
		try {
			book.write(new FileOutputStream(new File(path)));
		} catch (FileNotFoundException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
		return path;
	}

	public Workbook getBook() {
		return book;
	}

	public void setBook(Workbook book) {
		this.book = book;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
