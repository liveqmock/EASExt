/**
 * 
 */
package com.creditease.eas.test;

/**
 * @PropeUtil.java
 * created at 2013-3-11 下午05:13:18 by xjw
 * 
 * @author xjw({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
/**
* 实现对Java设置 文件Properties的读取、写入与更新操纵
*/

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * 注:当前项目路径是

String filepath=System.getProperty("user.dir");

对下面的措施很有效 ...
 */
public class PropeUtil {
    //属性文件的路径   mailresources.properties
//    static String profilepath="mail.properties";
    static String profilepath="mailresources.properties";
    /**
    * 采用 静态行动
    */
    private static Properties props = new Properties();
    static {
        try {
            props.load(new FileInputStream(profilepath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        } catch (IOException e) {       
            System.exit(-1);
        }
    }

    /**
    * 读取属性文件中相应键的值
    * @param key
    *            主键
    * @return String
    */
    public static String getKeyValue(String key) {
        return props.getProperty(key);
    }

    /**
    * 按照 主键key读取主键的值value
    * @param filePath 属性文件路径
    * @param key 键名
    */
    public static String readValue(String filePath, String key) {
        Properties props = new Properties();
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(
                    filePath));
            props.load(in);
            String value = props.getProperty(key);
            System.out.println(key +"键的值是："+ value);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
   
    /**
    * 更新（或插入）一对properties信息(主键及其键值)
    * 假如 该主键已经存在，更新该主键的值；
    * 假如 该主键不存在，则插件一对键值。
    * @param keyname 键名
    * @param keyvalue 键值
    */
    public static void writeProperties(String keyname,String keyvalue) {       
        try {
            // 调用 Hashtable 的行动 put，应用 getProperty 行动 供给 并行性。
            // 逼迫 哀求 为属性的键和值应用 字符串。返回值是 Hashtable 调用 put 的结果。
            OutputStream fos = new FileOutputStream(profilepath);
            props.setProperty(keyname, keyvalue);
            // 以适宜 应用 load 行动 加载到 Properties 表中的技俩，
            // 将此 Properties 表中的属性列表（键和元素对）写入输出流
            props.store(fos, "Update '" + keyname + "' value");
        } catch (IOException e) {
            System.err.println("属性文件更新过错 ");
        }
    }

    /**
    * 更新properties文件的键值对
    * 假如 该主键已经存在，更新该主键的值；
    * 假如 该主键不存在，则插件一对键值。
    * @param keyname 键名
    * @param keyvalue 键值
    */
    public void updateProperties(String keyname,String keyvalue) {
        try {
            props.load(new FileInputStream(profilepath));
            // 调用 Hashtable 的行动 put，应用 getProperty 行动 供给 并行性。
            // 逼迫 哀求 为属性的键和值应用 字符串。返回值是 Hashtable 调用 put 的结果。
            OutputStream fos = new FileOutputStream(profilepath);           
            props.setProperty(keyname, keyvalue);
            // 以适宜 应用 load 行动 加载到 Properties 表中的技俩，
            // 将此 Properties 表中的属性列表（键和元素对）写入输出流
            props.store(fos, "Update '" + keyname + "' value");
        } catch (IOException e) {
            System.err.println("属性文件更新过错 ");
        }
    }
    //测试代码
    public static void main(String[] args) {
//        readValue("mail.properties", "MAIL_SERVER_PASSWORD");
//    	readValue("mailresources.properties", "MAIL_SERVER_PASSWORD");
//        writeProperties("MAIL_SERVER_INCOMING", "327@qq.com");       
        String filepath=System.getProperty("user.dir");
        System.out.println("操纵 完成   "+ filepath);
    }
}
