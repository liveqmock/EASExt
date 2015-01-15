package com.creditease.eas.quartz;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.creditease.eas.test.Cn2Spell;

public class MainTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Test start.");
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext_quartz.xml");
        //如果配置文件中将startQuertz bean的lazy-init设置为false 则不用实例化
        //context.getBean("startQuertz");
        System.out.println("Test end..");
        
        String pyin = Cn2Spell.converterToSpell("张三");
        
        System.out.println(pyin);
        
        String pyin2 = Cn2Spell.converterToSpell("wangwu");
        
        System.out.println(pyin2);
	}

}
