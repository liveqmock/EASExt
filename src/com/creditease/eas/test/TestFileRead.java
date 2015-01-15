/**
 * 
 */
package com.creditease.eas.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @TestFileRead.java
 * created at 2013-5-30 下午07:02:25 by Administrator
 * 
 * @author Administrator({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class TestFileRead {
    public static String test1(String filePath) throws Exception{  
        FileChannel fci =  new FileInputStream(filePath).getChannel();  
        ByteBuffer bf = ByteBuffer.allocate(2048);  
        StringBuffer sb  = new StringBuffer();
        while(fci.read(bf) != -1){  
            bf.flip();  
            sb.append(bf.toString());
            bf.clear();  
        }  
        fci.close();  
        return sb.toString();
    }   
    
    public static String test2(String path) {  
//        String path = "d:\\testfile.txt";  
        File file = new File(path);  
        String str = ""; 
        try {  
            BufferedReader rd = new BufferedReader(new FileReader(file));  
            String s = rd.readLine();  
            while (null != s) {  
                str += s + "<br/>";  
                s = rd.readLine();  
            }  
            System.out.println(str);  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } 
        return str;
    }   
	public static void main(String[] args)throws Exception {
//		String filePath = "D:\\upload\\合同预警\\boliu4\\2013-02-20\\1361337860583合同即将到期人员名单.txt";
		String filePath = "D:\\upload\\合同预警\\boliu4\\2013-02-20\\1361337860583合同即将到期人员名单.txt";
		String str = test2(filePath);
		System.out.println(str);
	}
}
