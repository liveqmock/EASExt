package com.creditease.eas.util;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ColorStyleUtil {
	public static final short SKY_BLUE = HSSFColor.SKY_BLUE.index;//style1 天空蓝
	public static final short LIGHT_YELLOW = HSSFColor.LIGHT_YELLOW.index;//style2浅黄色
	public static final short GREEN = HSSFColor.GREEN.index;//style3草绿色
	public static final short LIGHT_ORANGE = HSSFColor.LIGHT_ORANGE.index;//style4 浅色的oracle
	//样式1 天空蓝
	//样式2：浅黄色
	//样式3：草绿色
	//样式4：浅色的oracle
	public static void colorStyleSet(XSSFCell cell,String header,XSSFCellStyle style1,XSSFCellStyle style2,XSSFCellStyle style3, XSSFCellStyle style4){
		 if(header.equals("资产类别编码")||header.equals("原资产编码")||header.equals("资产名称")||header.equals("计量单位")||header.equals("实物入账日期")
				 ||header.equals("财务入账日期")||header.equals("来源方式")||header.equals("使用状态")||header.equals("存放地点")||header.equals("规格型号")
				 ||header.equals("管理部门")||header.equals("保管人编码")||header.equals("币种")||header.equals("汇率")||header.equals("原币金额")||header.equals("资产原值")
				  ||header.equals("具体存放地点")||header.equals("资产数量")){
	     	cell.setCellStyle(style3);
	     }else if(header.equals("资产编号")||header.equals("开始使用日期")||header.equals("预计使用年限")||header.equals("预计使用期间数")||header.equals("已折旧期间数")||header.equals("累计折旧")||
	    		 header.equals("净残值率")||header.equals("预计净残值")||header.equals("净值")||header.equals("本年累计折旧")||header.equals("净额")
	    		 ||header.equals("固定资产科目编码")||header.equals("折旧科目编码")||header.equals("减值准备科目编码")){
	     	cell.setCellStyle(style2);
	     }else{
	     	cell.setCellStyle(style4);
	     }
	}
	/***
	 * 
	 * 描述：设置样式的方法
	 * 2012-12-9 下午09:03:20 by ygq
	 * @version
	 * @param workbook
	 * @param colorIndex
	 * @return
	 */
	public static XSSFCellStyle styleSet(XSSFWorkbook workbook,short colorIndex){
		/******************************生成样式*******************************/
		// 生成一个样式:样式1 天空蓝
		XSSFCellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(colorIndex);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        return style;
	}
	/**
	 * 描述：设置对应的注释
	 * 2012-12-9 下午09:07:38 by ygq
	 * @version
	 * @param sheet
	 */
	public static void setComment(XSSFSheet sheet){
		XSSFDrawing patriarch = sheet.createDrawingPatriarch();// 声明一个画图的顶级管理器
		XSSFComment comment = patriarch.createCellComment(new XSSFClientAnchor(0, 0, 0,0, 5, 3, 6, 8));
		comment.setString(new XSSFRichTextString(CommentConst.FMEASUREUNITID));//计量单位编码
		//1
		comment = patriarch.createCellComment(new XSSFClientAnchor(0, 0, 0,0, 8, 3, 9, 8));
		comment.setString(new XSSFRichTextString(CommentConst.FORIGIN));//来源方式
		//2
		comment = patriarch.createCellComment(new XSSFClientAnchor(0, 0, 0,0, 9, 3, 10, 8));
		comment.setString(new XSSFRichTextString(CommentConst.FFAUSESTATUSID));//使用状态		
		//3
		comment = patriarch.createCellComment(new XSSFClientAnchor(0, 0, 0,0, 11, 3, 12, 8));
		comment.setString(new XSSFRichTextString(CommentConst.FSTORECITYID));//存放地点		
		//4
		comment = patriarch.createCellComment(new XSSFClientAnchor(0, 0, 0,0, 19, 3, 20, 8));
		comment.setString(new XSSFRichTextString(CommentConst.FMANAGEDEPARTMENTID));//管理部门编码		
		//5
		comment = patriarch.createCellComment(new XSSFClientAnchor(0, 0, 0,0, 22, 3, 23, 8));
		comment.setString(new XSSFRichTextString(CommentConst.FCURRENCYID));//币种编码		
	}
}
