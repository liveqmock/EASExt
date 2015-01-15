package com.creditease.eas.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.creditease.eas.util.mail.MailSenderInfo;
import com.creditease.eas.warn.bean.SendPortInfo;
import com.creditease.eas.warn.bean.WaringDetail;

/**
 * 利用开源组件POI3.0.2动态导出EXCEL文档 转载时请保留以下信息，注明出处！
 * 
 * @author leno
 * @version v1.0
 * @param<T> 应用泛型，代表任意一个符合javabean风格的类
 *           注意这里为了简单起见，boolean型的属性xxx的get器方式为getXxx(),而不是isXxx()
 *           byte[]表jpg格式的图片数据
 */
public class SendExcel<T> {
	/**
	 * 描述：需要用到的导出方法 2012-12-8 下午12:01:13 by Administrator
	 * 
	 * @version
	 * @param headers
	 * @param dataset
	 * @param out
	 */
	public void exportExcel(String[] headers, Collection<T> dataset,
			OutputStream out) {
		exportExcel("房屋合同到期缴费提醒", headers, dataset, out, "yyyy-MM-dd");
	}

	/**
	 * 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上
	 * 
	 * @param title
	 *            表格标题名
	 * @param headers
	 *            表格属性列名数组
	 * @param dataset
	 *            需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的
	 *            javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据)
	 * @param out
	 *            与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
	 * @param pattern
	 *            如果有时间数据，设定输出格式。默认为"yyy-MM-dd"
	 */
	@SuppressWarnings("unchecked")
	public void exportExcel(String title, String[] headers,
			Collection<T> dataset, OutputStream out, String pattern) {
		// 声明一个工作薄
		XSSFWorkbook workbook = new XSSFWorkbook();
		try {
			createSheet(title, headers, dataset, out, pattern, workbook);
			workbook.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * 描述：创建excel工作簿的sheet单元
	 * 2013-8-7 下午02:29:07 by caoyong
	 * @version
	 * @param title
	 * @param headers
	 * @param dataset
	 * @param out
	 * @param pattern
	 * @param workbook
	 */
	private void createSheet(String title, String[] headers,
			Collection<T> dataset, OutputStream out, String pattern,
			XSSFWorkbook workbook) {
		// 生成一个表格
		XSSFSheet sheet = workbook.createSheet(title);
		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth((short) 25);
		/****************************** 生成样式 *******************************/
//		// 生成一个样式:样式1 天空蓝
//		XSSFCellStyle style = ColorStyleUtil.styleSet(workbook,
//				ColorStyleUtil.SKY_BLUE);
//		// 生成并设置另一个样式 样式2：浅黄色
//		XSSFCellStyle style2 = ColorStyleUtil.styleSet(workbook,
//				ColorStyleUtil.LIGHT_YELLOW);
//		// 生成并设置另一个样式 草绿色
//		XSSFCellStyle style3 = ColorStyleUtil.styleSet(workbook,
//				ColorStyleUtil.GREEN);
//		// 生成并设置另一个样式 浅色的oracle
//		XSSFCellStyle style4 = ColorStyleUtil.styleSet(workbook,
//				ColorStyleUtil.LIGHT_ORANGE);
//		/****************************** 生成字体 *******************************/
//		XSSFFont font = workbook.createFont();
//		font.setColor(HSSFColor.VIOLET.index);
//		font.setFontHeightInPoints((short) 12);
//		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//		// 把字体应用到当前的样式
//		style.setFont(font);
//		// 生成另一种字体
//		XSSFFont font2 = workbook.createFont();
//		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
//		// 把字体应用到当前的样式
//		style2.setFont(font2);
		/************************************ 设置行表头********************************************/
//		HeaderSet.setHeadeInit(sheet, style);
		/****************************** 产生表格标题行 *************/
		XSSFRow row = sheet.createRow(1);
		for (int i = 0; i < headers.length; i++) {
			XSSFCell cell = row.createCell(i);
			// 设置标题头的样式
//			ColorStyleUtil.colorStyleSet(cell, headers[i], style, style2,
//					style3, style4);
			XSSFRichTextString text = new XSSFRichTextString(headers[i]);
			cell.setCellValue(text);
		}
		/****************** 遍历集合数据，产生数据行 *************/
		int index = 1;
		if (dataset != null && !dataset.isEmpty()) {// 非空判断
			Object[] objs = dataset.toArray();//这段代码可能需要回去重新弄一下
			for (int i = 0; i < objs.length; i++) {
					index++;
					row = sheet.createRow(index);
					T t = (T) objs[i];
					/****************************** 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值 *********************/
					Field[] fields = t.getClass().getDeclaredFields();
					for (int j = 1; j < 16; j++) {
						XSSFCell cell = row.createCell(j - 1);// 列的索引不能排除
						Field field = fields[j];// 第一列是id，需要排除掉
						String fieldName = field.getName();
						String getMethodName = "get"
								+ fieldName.substring(0, 1).toUpperCase()
								+ fieldName.substring(1);
						try {
							Class tCls = t.getClass();
							Method getMethod = tCls.getMethod(getMethodName,new Class[] {});
							Object value = getMethod.invoke(t, new Object[] {});
							String textValue = null;
							if (value != null && !"".equals(value)) {
								if (field.getType().getName().equals(
										"java.util.Date")) {
									Date date = (Date) value;
									textValue = StringUtil.dateToString(date);
								} else if (field.getType().getName().equals(
										"double")) {
									double d = Double.parseDouble(value
											.toString());
									if (d != 0d) {
										textValue = StringUtil
												.doubleToNumber2(d)
												+ "";
										String tx = textValue
												.substring(textValue
														.indexOf('.'));
										if (tx.length() == 2) {
											textValue += "0";
										}
									}
								} else {
									textValue = value.toString();
								}
							}
							// 利用正则表达式判断textValue是否全部由数字组成
							if (textValue != null) {
								Pattern p = Pattern
										.compile("^//d+(//.//d+)?{1}");
								Matcher matcher = p.matcher(textValue);
								if (matcher.matches()) {
									// 是数字当作double处理
									cell.setCellValue(Double.parseDouble(textValue));
								} else {
									XSSFRichTextString richString = new XSSFRichTextString(
											textValue);
									XSSFFont font3 = workbook.createFont();
									font3.setColor(HSSFColor.BLACK.index);//黑色
									//richString.applyFont(font3);
									cell.setCellValue(richString);
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							// 清理资源
						}
					}
			}
		}
	}
	
	/**
	 * 
	 * 描述：根据已发送的房屋合同预警邮件记录和部门名称生成excel汇总文件
	 * 2013-8-7 下午02:24:21 by caoyong
	 * @version
	 * @param list已发送的房屋合同预警邮件记录集合
	 * @param orgfname部门名称
	 * @return 生成的excel文件保存路径
	 * @throws IOException
	 */
	public String createmail(List<SendPortInfo> list,String orgfname) throws IOException{
		String filestr = null;
		String [] columns=new String[]{"部门", "城市", "办公室地址", "末级成本中心","接口人邮箱", "内容"};
		
		XSSFWorkbook wb = new XSSFWorkbook();//建立新HSSFWorkbook对象
		 // 设置字体   
		XSSFFont headfont = wb.createFont();   
		headfont.setFontName("黑体");   
		headfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗  
		 // 样式   
		XSSFCellStyle headstyle = wb.createCellStyle();   
		headstyle.setFont(headfont);   

		XSSFSheet sheet = wb.createSheet("房屋合同到期缴费提醒");//建立新的sheet对象
		// 设置表格默认列宽度为15个字节
//		sheet.setDefaultColumnWidth((short) 20);
		sheet.setColumnWidth(2, (short)5000);
		sheet.setColumnWidth(3, (short)3500);
		sheet.setColumnWidth(4, (short)3000);
		sheet.setColumnWidth(5, (short)12000);
		XSSFRow rowcolumn = sheet.createRow(0);
		for(int i=0;i<columns.length;i++){
			XSSFCell cell = rowcolumn.createCell((short) i);
			cell.setCellValue(new XSSFRichTextString(columns[i]));
			cell.setCellStyle(headstyle);
		}
		for(int i=0;i< list.size();i++){
			XSSFRow row = sheet.createRow(i+1);//建立新行  第一行是列头
			SendPortInfo sp = list.get(i);
			String orgname = sp.getOrgname();
			String city = sp.getCity();
			String officeadd = sp.getOfficeadd();
			String lastcostcenter = sp.getLastcostcenter();
			String portmail = sp.getPortmail();
			String content = sp.getContent();
			row.createCell((short) 0).setCellValue(new XSSFRichTextString(orgname));
			row.createCell((short) 1).setCellValue(new XSSFRichTextString(city));
			row.createCell((short) 2).setCellValue(new XSSFRichTextString(officeadd));
			row.createCell((short) 3).setCellValue(new XSSFRichTextString(lastcostcenter));
			row.createCell((short) 4).setCellValue(new XSSFRichTextString(portmail));
			row.createCell((short) 5).setCellValue(new XSSFRichTextString(content));
		}
		FileOutputStream fout;
		try {
			MailSenderInfo ms = new MailSenderInfo();
			Properties pr = ms.getProperties();
			String portfile = pr.getProperty("portfile");
//			portfile = "D:/";//测试时用本地路径，上线时将此行注释掉
			filestr = portfile + orgfname + Utils.getNowcurrTime()+".xlsx";
			fout = new FileOutputStream(filestr);
			wb.write(fout);
			fout.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filestr;
	}
	

	public static void main(String[] args) {
		// 测试学生
		SendExcel<WaringDetail> ex = new SendExcel<WaringDetail>();
		String[] headers = {"预警类别", "发送方式", "发送时间", "电话","部门id", "部门名称","出生日期","入职日期","职位id","职位名称",
			  "收件人IDS", "收件人", "收件人邮箱", "抄送人IDS","抄送人", "主题","正文内容地址","是否发送成功",
			  "发送次数", "创建时间", "短信内容","文件名称"
		  };
		List<WaringDetail> dataset = new ArrayList<WaringDetail>();
		WaringDetail w1 = new WaringDetail();
		w1.setId(1L);
		w1.setTypeid(1);
		w1.setWayid(1);
		w1.setSendtime(new Date());
		w1.setCell("13070189337");
		w1.setDepartid("1");
		w1.setDepartname("中华部");
		dataset.add(w1);
//		assets1.setId(1);
//		dataset.add(assets1);
//		Assets assets2 = new Assets();
//		assets2.setId(2); vcc nbb 
//		dataset.add(assets2);
//		Assets assets3 = new Assets();
//		assets3.setId(3);
//		dataset.add(assets3);
		try {
			OutputStream out = new FileOutputStream("c:/show.xlsx");
			ex.exportExcel(headers, dataset, out);
			JOptionPane.showMessageDialog(null, "导出成功!");
			System.out.println("excel导出成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}