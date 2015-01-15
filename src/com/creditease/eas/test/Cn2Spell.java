/**
 * 
 */
package com.creditease.eas.test;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * @Cn2Spell.java
 * created at 2013-3-14 下午05:44:48 by xjw
 * 
 * @author xjw({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
/** 
 * 汉字转换位汉语拼音，英文字符不变 
 * @author xuke 
 * 
 */  
public class Cn2Spell {  
   
    /** 
    * 汉字转换位汉语拼音首字母，英文字符不变 
    * @param chines 汉字 
    * @return 拼音 
    */  
    public static String converterToFirstSpell(String chines){         
        String pinyinName = "";  
        char[] nameChar = chines.toCharArray();  
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();  
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);  
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);  
        for (int i = 0; i < nameChar.length; i++) {  
            if (nameChar[i] > 128) {  
                try {  
                    pinyinName += PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat)[0].charAt(0);  
                } catch (BadHanyuPinyinOutputFormatCombination e) {  
                    e.printStackTrace();  
                }  
            }else{  
                pinyinName += nameChar[i];  
            }  
        }  
        return pinyinName;  
    }  
   
    /** 
    * 汉字转换位汉语拼音，英文字符不变 
    * @param chines 汉字 
    * @return 拼音 
    */  
    public static String converterToSpell(String chines){          
        String pinyinName = "";  
        char[] nameChar = chines.toCharArray();  
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();  
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);  
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);  
        for (int i = 0; i < nameChar.length; i++) {  
            if (nameChar[i] > 128) {  
                try {  
                    pinyinName += PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat)[0];  
                } catch (BadHanyuPinyinOutputFormatCombination e) {  
                    e.printStackTrace();  
                }  
            }else{  
                pinyinName += nameChar[i];  
            }  
        }  
        return pinyinName;  
    }  
      
    public static void main(String[] args) {  
    	String str = "欢迎来到最棒的Java中文社区 录 吕 旅 绿 陆 鋆 毓鋆 单 行";
    	str = converterToSpell(str);
    	str = str.replaceAll("lu:", "lv");
        System.out.println(str);  
    }  
}  
