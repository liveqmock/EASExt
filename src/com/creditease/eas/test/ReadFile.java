package com.creditease.eas.test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

/**
*
* @author 烟过留声
*/
public class ReadFile {

    public static void readFileByChars(String fileName) {
        File file = new File(fileName);
        Reader reader = null;
        try {
            System.out.println("以字符为单位读取文件内容，一次读多个字节：");
            //一次读多个字符
            char[] tempchars = new char[500];
            int charread = 0;
            reader = new InputStreamReader(new FileInputStream(fileName));
            //读入多个字符到字符数组中，charread为一次读取字符数
            while ((charread = reader.read(tempchars)) != -1) {
                //屏蔽掉\r不显示
//                if ((charread == tempchars.length) && (tempchars[tempchars.length - 1] != '\r')) {
//                    System.out.print(tempchars);
//                } else {
                    for (int i = 0; i < charread; i++) {

                        if (tempchars[i] == '\r') {
                            continue;
                        } else {
                            System.out.print(tempchars[i]);
                        }
                    }
//                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    public static String selfReadFile(String strFileName) {
        StringBuffer buf = null;//the   intermediary,   mutable   buffer
        BufferedReader breader = null;//reader   for   the   template   files
        try {
            breader = new BufferedReader(new InputStreamReader(new FileInputStream((strFileName)), Charset.forName("utf-8")));
            while (breader.ready()) {
                buf.append((char) breader.read());
            }
            breader.close();
        }//try
        catch (Exception e) {
            e.printStackTrace();
        }//catch
        return buf.toString();
    }
    public static String  getUserFile(String filePath) throws IOException{
    	File file = new File(filePath);
    	  FileInputStream fis = new FileInputStream(file);
    	  BufferedReader br = new BufferedReader(new InputStreamReader(fis));
    	  StringBuffer sb = new StringBuffer();
    	  String line=null;  
    	  while((line=br.readLine())!=null)  
    	  {  
    		  sb.append(line);
    	  }  
    	  return sb.toString();
    }
    public static void main(String[] args) throws Exception {
    	//1.El表达式读取内容
    	//2.自定义函数，取值
        System.out.println(ReadFile.getUserFile("D:\\2013-01-031357199961399技术部转正人员名单.txt"));
    }
}
