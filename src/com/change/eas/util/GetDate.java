package com.change.eas.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class GetDate {
	
	
	@Test
   public static String  getDate(){
	   Date dt = new Date();
	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	   String  dt1= format.format(dt);
//	   System.out.println(dt1);
//	   System.out.println(dt1.subSequence(0, 4));
//	   System.out.println(dt1.subSequence(5, 7));
//	   System.out.println(dt1.subSequence(8, 10));
	   return dt1;
   }
}
