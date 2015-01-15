package com.creditease.eas.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileSplit {
	/**
	 * 把一个txt分成几等分
	 * @param count需要分成几等分
	 * D:/data.txt
	 */
	public static void splitTxt(String filePath,String tartPath,int count) {
		try {
			FileReader read = new FileReader(filePath);
			BufferedReader br = new BufferedReader(read);
			String row;
			List<FileWriter> flist = new ArrayList<FileWriter>();
			for (int i = 0; i < count; i++) {
				flist.add(new FileWriter(tartPath + i + ".txt"));
			}
			int rownum = 1;// 计数器
			while ((row = br.readLine()) != null) {
				flist.get(rownum % count).append(row + "\r\n");
				rownum++;
			}
			for (int i = 0; i < flist.size(); i++) {
				flist.get(i).close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		splitTxt("E:\\tomcat-log\\catalina.out","e:/tomcat-log/a",4);
		System.out.println("成功了...............");
//		com.mysql.jdbc.Driver
	}
}
