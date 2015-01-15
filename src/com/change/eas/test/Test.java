package com.change.eas.test;

public class Test {

	public static void main(String args[]){
		
		double a=0;
		System.out.println(Math.round(a*10000.0)/10000.0);
		
		String date="2013-08-07";
//		System.out.println(IUtils.verificationOfDateIsCorrect(date));
		System.out.println(!true);
		
//		Date d=new Date();
//		System.out.println(d.toLocaleString());
//		
//		String orignal="41.72";
//		orignal=orignal.replaceAll("(0+)(\\d+)[.](\\d+)", "$2.$3");
//		System.out.println(orignal);
//		String str="001.67";
//		for(int i=0;i<str.length();i++){
//			char c=str.charAt(i);
//			if(c=='0')
//				str=str.substring(0,i);
////			else if(c=='.')
//				
//		}
//		
//		double a1=123.0;
//		double b1=223.0;
//		
//    
//		DecimalFormat df3  = new DecimalFormat("###.000"); //1 
//		
//		DecimalFormat df4  = new DecimalFormat("###.0000"); // 2
//		
//		DecimalFormat df5  = new DecimalFormat("###.00000"); //3 
//		
//		DecimalFormat df2  = new DecimalFormat("###.000000"); //4
//
//		System.out.println(df2.format(a1)); 
//		
//		System.out.println(df2.format(a1/b1));
//		System.out.println(df3.format(a1/b1));
//		System.out.println(df4.format(a1/b1));
//		System.out.println(df5.format(a1/b1));
//		String str=5516+"";
//		System.out.println(str.substring(((str.length()-2)),(str.length()))); // 后
//		System.out.println(str.substring(0,((str.length()-2)))); //前
//		System.out.println(str.substring(0,(str.length()+1-(str.length()-3)))+"."+str.substring((str.length()-3)));
//		System.out.println("4位"+((a1/b1)*1000000)/1000000.0);
//		System.out.println("4位"+(double)(Math.round((a1/b1)*1000000)/1000000.0));
//		System.out.println("3位"+(double)(Math.round((a1/b1)*100000)/100000.0));
//		System.out.println("2位"+(double)(Math.round((a1/b1)*10000)/10000.0));
//		System.out.println("1位"+(double)(Math.round((a1/b1)*1000)/1000.0));
		
	/*	double d =0.55157;
		String s=(d+"").substring(2);
		String s1=s.substring(s.length()-3);
		String s2=s.substring(s.length()-(s.length()-3));
		System.out.println(s2+"."+s1);
		System.out.println(s);
    	System.out.println(d-new Double(d));

		double a = 4.561235;

		String str = String.valueOf(a);

		char c = str.charAt(str.indexOf("."));

		System.out.println(c);

		LimitedpartnerUtil l=new LimitedpartnerUtil();
		float f=(float) (Math.round(0.333333*10000)/10000.0);
		System.out.println(f);
		System.out.println(System.getProperty("user.dir"));
		try {
			System.out.println(LimitedpartnerUtil.getStatusValue(3));
//			System.out.println(l.getStatus("未采集"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
}
