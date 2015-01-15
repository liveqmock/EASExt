package com.change.eas.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

public class ZipFileUtil {

	private final static int BUFFER = 2048;

	/**
	 * ����Ŀ¼
	 * 
	 * @param path
	 *            Ŀ¼���·����
	 */
	static void createDir(String path) {
		File dir = new File(path);
		if (dir.exists() == false)
			dir.mkdir();
	}

	/**
	 * ȡ���ļ���,�����׺��
	 * 
	 * @param name
	 *            �����ļ���
	 * @return �ļ���(�����׺��)
	 */
	static String getSuffixName(String name) {
		return name.substring(0, name.lastIndexOf("."));
	}

	/**
	 * @author guzi
	 * @function ѹ������ļ�
	 * @process
	 * @param zipFileName
	 *            ѹ������ļ���
	 * @param inputFiles
	 *            ��ѹ�����ļ��б�
	 * @return
	 * @throws IOException
	 */
	public static File zipFile(String zipFileName, List<File> inputFiles)
			throws IOException {
		File temp = new File(Config.getKey("lawfile.temp"), zipFileName
				+ ".zip");
		deleteFile();
		try {
			BufferedInputStream origin = null;
			ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(
					new FileOutputStream(temp)));
			out.setEncoding("utf-8");
			byte data[] = new byte[BUFFER];
			for (File file : inputFiles) {
				FileInputStream fi = new FileInputStream(file);
				origin = new BufferedInputStream(fi, BUFFER);
				String name = file.getName();
				ZipEntry entry = new ZipEntry(name);
				out.putNextEntry(entry);
				int count;
				while ((count = origin.read(data, 0, BUFFER)) != -1) {
					out.write(data, 0, count);
				}
				origin.close();
				out.flush();
			}
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return temp;
	}

	private static void deleteFile() {
		File file = new File(Config.getKey("lawfile.temp"));
		File[] files = file.listFiles();
		for (File fi : files) {
			long l = fi.lastModified();
			long time = new Date().getTime();
			if ((time - l) > 24 * 60 * 60 * 1000) {
				fi.delete();
			}
		}
	}
}
