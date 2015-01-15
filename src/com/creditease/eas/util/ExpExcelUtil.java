package com.creditease.eas.util;

import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.GroupLayout.Alignment;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;

public class ExpExcelUtil {
	/**
	 * 导出excel 注意：该类的方法还需改进 描述： 2013-9-25 下午01:41:29 
	 * 创建文本单元格
	 * @version
	 * @param wb
	 * @param row
	 * @param column
	 * @param value
	 * @param hcs
	 */
	public static void createTxtCell(HSSFWorkbook wb, HSSFRow row, int column,
			Object value, HSSFCellStyle hcs) {
		HSSFCell cell = row.createCell((short) column);
		cell.setCellStyle(hcs);
		String textValue = null;
		if (value != null && !"".equals(value)) {
			if (value instanceof java.util.Date) {
				Date date = (Date) value;
				textValue = StringUtil.dateToString(date);
			} else if (value instanceof java.lang.Double) {
				double d = Double.parseDouble(value.toString());
				if (d != 0d) {
					textValue = StringUtil.doubleToNumber2(d) + "";
					String tx = textValue.substring(textValue.indexOf('.'));
					if (tx.length() == 2) {
						textValue += "0";
					}
				}
			} else {
				textValue = value.toString();
			}
			if (textValue != null) {
				Pattern p = Pattern.compile("^//d+(//.//d+)?{1}");
				Matcher matcher = p.matcher(textValue);
				if (matcher.matches()) {
					// 是数字当作double处理
					cell.setCellValue(Double.parseDouble(textValue));
				} else {
					cell.setCellValue(textValue);
				}
			}

		}

	}
	/**
	 * 样式设置 无背景色(Yellow)，字体色(BLACK) 左右居中，上下边框加粗，左右为细
	 * @param wb
	 * @return
	 */
	public static final HSSFCellStyle createBgColStyle(HSSFCellStyle cellStyle,HSSFWorkbook wb) {
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN); // 设置单元个边框
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
		cellStyle.setBorderBottom((short) 2);
		// 左右居中
		cellStyle.setWrapText(true);// 文本区域随内容多少自动调整
		HSSFFont fontStyle = wb.createFont();
		fontStyle.setFontName("宋体");
		fontStyle.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		fontStyle.setColor(HSSFColor.BLACK.index); // 设置字体色
		fontStyle.setFontHeightInPoints((short) 13);
		cellStyle.setFont(fontStyle);
		return cellStyle;
	}
	/**
	 * 
	 * 描述：将HSSFFont作为参数传入，避免导出数据量大时后台字体样式实例太多而报错
	 * 2013-12-26 下午03:34:41 by zhangxin
	 * @version
	 * @param cellStyle
	 * @param wb
	 * @param fontStyle
	 * @return
	 */
	public static final HSSFCellStyle createBgColStyle(HSSFCellStyle cellStyle,HSSFWorkbook wb,HSSFFont fontStyle) {
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN); // 设置单元个边框
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
		cellStyle.setBorderBottom((short) 2);
		// 左右居中
		cellStyle.setWrapText(true);// 文本区域随内容多少自动调整
		fontStyle.setFontName("宋体");
		fontStyle.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		fontStyle.setColor(HSSFColor.BLACK.index); // 设置字体色
		fontStyle.setFontHeightInPoints((short) 13);
		cellStyle.setFont(fontStyle);
		return cellStyle;
	}
	
	/**
	 * 
	 * 描述：将HSSFFont作为参数传入,无边框样式
	 * 2013-12-26 下午03:34:41 by zhangxin
	 * @version
	 * @param cellStyle
	 * @param wb
	 * @param fontStyle
	 * @return
	 */
	public static final HSSFCellStyle createSimpleBgColStyle(HSSFCellStyle cellStyle,HSSFWorkbook wb,HSSFFont fontStyle) {
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
		// 左右居中
		cellStyle.setWrapText(true);// 文本区域随内容多少自动调整
		fontStyle.setFontName("宋体");
		fontStyle.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		fontStyle.setColor(HSSFColor.BLACK.index); // 设置字体色
		fontStyle.setFontHeightInPoints((short) 13);
		cellStyle.setFont(fontStyle);
		return cellStyle;
	}
	/**
	 * 
	 * 描述：将单元格设置为日期格式
	 * 2014-6-18 下午04:49:24 by zhangxin
	 * @version
	 * @param cellStyle
	 * @param wb
	 * @return
	 */
	public static final HSSFCellStyle createStyleToDate(HSSFCellStyle cellStyle,HSSFWorkbook wb) {
			HSSFDataFormat format = wb.createDataFormat();   
			cellStyle.setDataFormat(format.getFormat("yyyy-m-d")); 
			return cellStyle;
		}
	/**
	 * 
	 * 描述：将单元格设置为数值格式
	 * 2014-6-18 下午06:22:25 by zhangxin
	 * @version
	 * @param cellStyle
	 * @param wb
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static final HSSFCellStyle createStyleToNum(HSSFCellStyle cellStyle,HSSFWorkbook wb) {
		HSSFDataFormat format = wb.createDataFormat();   
		cellStyle.setDataFormat(format.getFormat("#,##0.00"));
		return cellStyle;
	}
	
	/**  
	 * 样式设置 无背景色，没有边框 ，字号13 字体
	 * @param wb
	 * @return
	 */
	public static final HSSFCellStyle createSimpleColStyl(HSSFCellStyle cellStyle,HSSFWorkbook wb) {
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
		HSSFFont fontStyle = wb.createFont();
		fontStyle.setFontName("宋体");
		fontStyle.setFontHeightInPoints((short) 13);
		cellStyle.setFont(fontStyle);
		return cellStyle;
	}
	
	/**  
	 * 样式设置 背景色， 居左， 左、右、上、下细边框 ，字号20 字体
	 * @param wb
	 * @return
	 */
	public static final HSSFCellStyle createBgColStyleutil(HSSFCellStyle cellStyle,HSSFWorkbook wb) {
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cellStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		HSSFFont fontStyle = wb.createFont();
		fontStyle.setFontName("宋体");
		fontStyle.setFontHeightInPoints((short) 20);
		cellStyle.setFont(fontStyle);
		return cellStyle;
	}
	/**  
	 * 样式设置 无背景色， 居左， 左、右、上、下细边框 ，字号13 字体
	 * @param wb
	 * @return
	 */
	public static final HSSFCellStyle createColStyl(HSSFCellStyle cellStyle,HSSFWorkbook wb) {
		//HSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
		HSSFFont fontStyle = wb.createFont();
		fontStyle.setFontName("宋体");
		fontStyle.setFontHeightInPoints((short) 13);
		cellStyle.setFont(fontStyle);
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		return cellStyle;
	}

	/**
	 * 创建单元格 合并单元格
	 * 
	 * @param sheet
	 * @param column
	 *            创建单元格的列
	 * @param oroww
	 *            创建单元格行
	 * @param fillName
	 *            填充值
	 * @param srow
	 *            合并开始行
	 * @param scol
	 *            合并开始列
	 * @param erow
	 *            合并结束行
	 * @param ecol
	 *            合并结束列
	 * @param fontCol
	 *            字体颜色
	 * @return
	 */
	public static HSSFCell createMergingCells(HSSFWorkbook wb, HSSFSheet sheet,
			HSSFRow row, int column, String fillValue, int srow, int scol,
			int erow, int ecol, HSSFCellStyle hcs) {
		HSSFCell cell = row.createCell((short) column);
		// cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		// 设置每一个单元格个样式（否则只填充合并单元格的一个样式）
		for (int i = 0; i <= (erow - srow); i++) {
			for (int j = 0; j <= (ecol - scol); j++) {
				HSSFCell cell1 = sheet.createRow(srow + i).createCell(
						(short) (scol + j));
				cell1.setCellStyle(hcs);
			}
		}
		cell.setCellValue(fillValue);
		sheet
				.addMergedRegion(new Region(srow, (short) scol, erow,
						(short) ecol));
		cell.setCellStyle(hcs);
		return cell;
	}
	/**  
	 * 样式设置 无背景色， 居左， 左、右、上、下细边框 ，字号13 字体
	 * @param wb
	 * @return
	 */
	public static final HSSFCellStyle createCollStyleCenter(HSSFCellStyle cellStyle,HSSFWorkbook wb) {
	
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
		//cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		//cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		HSSFFont fontStyle = wb.createFont();
		fontStyle.setFontName("宋体");
		fontStyle.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		fontStyle.setFontHeightInPoints((short) 13);
		cellStyle.setFont(fontStyle);
		return cellStyle;
	}
	//cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
	//cellStyle.setBorderBottom((short) 2);
	// 左右居中
	//HSSFFont fontStyle = wb.createFont();
	//fontStyle.setColor(HSSFColor.BLACK.index); // 设置字体色
	/**  
	 * 样式设置 无背景色， 居左， 左、右、上、下细边框 ，字号11 字体
	 * @param wb
	 * @return
	 */
	public static final HSSFCellStyle createCollStyle(HSSFCellStyle cellStyle,HSSFWorkbook wb) {
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
		//cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		//cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cellStyle.setWrapText(true);
		HSSFFont fontStyle = wb.createFont();
		fontStyle.setFontName("宋体");
		fontStyle.setFontHeightInPoints((short) 11);
		cellStyle.setFont(fontStyle);
		return cellStyle;
	}
	/**  
	 * 样式设置 无背景色， 居左， 左、右、上、下细边框 ，字号11 字体 
	 * @param wb  sunxiaofeng 2014-7-23
	 * @return
	 */
	public static final HSSFCellStyle createCollStyle(HSSFCellStyle cellStyle,HSSFWorkbook wb,HSSFFont fontStyle) {
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
		//cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		//cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cellStyle.setWrapText(true);
		fontStyle.setFontName("宋体");
		fontStyle.setFontHeightInPoints((short) 10);
		cellStyle.setFont(fontStyle);
		return cellStyle;
	}
	/**
	 * sunxiaofeng 2014-7-23
	 * @param cellStyle
	 * @param wb
	 * @param fontStyle
	 * @return
	 */
	public static final HSSFCellStyle createColStyleTile(HSSFCellStyle cellStyle,HSSFWorkbook wb,HSSFFont fontStyle) {
		//HSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
		fontStyle.setFontName("Arial");
		fontStyle.setFontHeightInPoints((short) 13);
		cellStyle.setFont(fontStyle);
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		return cellStyle;
	}
}
