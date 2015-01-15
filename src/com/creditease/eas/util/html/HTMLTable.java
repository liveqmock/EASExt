package com.creditease.eas.util.html;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import edu.emory.mathcs.backport.java.util.Arrays;

/**
 * 处理HTML文档的工具类
 * @author lining
 * @since 2014-3-24
 */
@SuppressWarnings("unchecked")
public class HTMLTable {
	
	private List<String> COLUMNS = new ArrayList<String>();
	private List<String> heads = new ArrayList<String>();
	private List<List<Map<String,Object>>> datas; //可包含多个内容数据集（例如直接下级数据List<Map<String,Object>>集合、间接下级数据List<Map<String,Object>>集合）
	private String[] contents;
	
	 private HTMLTable(Map<String,Object> tableDatas,String[] columns){
		 if(null != columns && 0 < columns.length){
			 this.COLUMNS = Arrays.asList(columns);
		 }
		 if(null != tableDatas){
			 if(null != tableDatas.get("datas")){
				 datas = (List<List<Map<String,Object>>>) tableDatas.get("datas");
				 if(0 < datas.size()){
					 List<Integer> headIndex = new ArrayList<Integer>();
					 for(List<Map<String,Object>> data:datas){
						 if(null != data && 0 < data.size()){
							 for(Map<String,Object> one:data){
								 if(null != one && 0 < one.size()){
									 for(String key:one.keySet()){
										 //获得一个有序的表头
										 if(COLUMNS.contains(key)){
											 if(!headIndex.contains(COLUMNS.indexOf(key))){
												 headIndex.add(COLUMNS.indexOf(key));
											 }
										 }else{
											 if(!heads.contains(key)){
												 heads.add(key);
											 }
										 }
									 }
								 }
							 }
						 }
					 }
					 if(0 < headIndex.size()){
						 Collections.sort(headIndex);
						 for(int index:headIndex){
								 heads.add(COLUMNS.get(index));
						 }
					 }
				 }
			 }
			 if(null != tableDatas.get("contents")){
				 contents = (String[]) tableDatas.get("contents");
			 }
		 }
	 }
	/**
	 * 根据数据动态生成一个固定格式的HTML表格
	 * @param table 生成表格的相关数据
	 * @return HTML字符串
	 */
	public static String simpleHTMLTable(Map<String,Object> tableDatas,String[] columns){
		HTMLTable table = new HTMLTable(tableDatas,columns);
		StringBuffer htmlTable = new StringBuffer();
		htmlTable.append("<table class=MsoNormalTable border=1 cellspacing=0 cellpadding=0 style='width:720.0pt;border-collapse:collapse'>");
		if(null != table.heads){
			htmlTable.append(table.simpleHTMLTableHead(table.heads));
			if(null != table.contents && 0 < table.contents.length){
				for(int i = 0;i < table.contents.length; i++){
					htmlTable.append(table.singleHTMLTableLine(table.contents[i]));
					if(null != table.datas && 0 < table.datas.size()){
						htmlTable.append(table.simpleHTMLTableBody(table.datas.get(i)));
					}
				}
			}else{
				if(null != table.datas && 0 < table.datas.size()){
					if(null != table.datas.get(0) && 0 < table.datas.get(0).size())
						htmlTable.append(table.simpleHTMLTableBody(table.datas.get(0)));
				}
			}
		}
		htmlTable.append("</table>");
		return htmlTable.toString();
	}
	/**
	 * 根据数据内容动态生成固定格式HTML表头
	 * @param datas 包含列名的数据集合
	 * @return HTML字符串
	 */
	private String simpleHTMLTableHead(List<String> heads){
		StringBuffer html = new StringBuffer();
		html.append("<tr style='height:15.0pt'>");
			for(String head: heads){
				html.append("<td nowrap style='background:#0066CC;padding:3.75pt 3.75pt 3.75pt 3.75pt'><p class=MsoNormal align=center style='text-align:center;line-height:150%'><span style='font-size:10.0pt;line-height:150%;color:white;font-weight:bold'>"+head+"</span><span lang=EN-US><o:p></o:p></span></p></td>");
			}
		html.append("</tr>");
		return html.toString();
	}
	/**
	 * 根据数据内容动态生成HTML表体
	 * @param datas 包含表体数据及其对应列名的数据集合
	 * @return HTML字符串
	 */
	private String simpleHTMLTableBody(List<Map<String,Object>> datas){
		StringBuffer html = new StringBuffer();
		if(null != datas && 0 < datas.size()){
			for(Map<String,Object> data:datas){
				html.append("<tr>");
				for(String column:heads){
					html.append("<td nowrap style='padding:3.75pt 3.75pt 3.75pt 3.75pt'><p class=MsoNormal align=center style='text-align:center;line-height:150%'><span style='font-size:10.0pt;line-height:150%'>"+((null == data.get(column))?"":data.get(column))+"</span><span lang=EN-US><o:p></o:p></span></p></td>");
				}
				html.append("</tr>");
			}
		}
		return html.toString();
	}
	/**
	 * 提供一个粗体左对齐HTML表格行
	 * @param content 行中的内容
	 * @return
	 */
	private String singleHTMLTableLine(String content){
		return "<td nowrap style='padding:3.75pt 3.75pt 3.75pt 3.75pt' colspan='"+heads.size()+"'><p class=MsoNormal  align=center style='text-align:left;line-height:150%'><span style='font-size:10.0pt;line-height:150%;font-weight:bold'>"+content+"</span><span lang=EN-US><o:p></o:p></span></p></td>";
	}
}
