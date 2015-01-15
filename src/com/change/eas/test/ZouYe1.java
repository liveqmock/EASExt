package com.change.eas.test;
/*
 * 需求分析：
 * 			需要一个Student类：
 * 		私有属性：
 * 			1  学生名属性；
 * 			2  学生学号属性；
 * 			
 * 		需要提供getter和setter方法；
 *   	需要重写equals方法：
 *   	有string和object类不同方法不同
 *   	
 *   	equals属于object类 
 *     需要重写toString 方法
 * 
 * 
 * */
public class ZouYe1 {
		
		String name;
		
		String stuno;
		public ZouYe1(){
			
		}
		public ZouYe1(String name,String stuno)
		{
			this.name=name;
			this.stuno=stuno;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getStuno() {
			return stuno;
		}

		public void setStuno(String stuno) {
			this.stuno = stuno;
		}
		// 此equals方法是所有的类都具有的方法，判断传进来的参数ob是否和当前类（这里是ZouYe1）的对象相等，这里是重写这个方法
		@Override
		public boolean equals(Object ob)
		{
			if(this==ob){
				//this 代表的事此类中的所有属性？还可以代表其方法么？
				// this代表的不是此类中的所有属性，也不是代表其方法。
				//this代表的是当前这个类（ZouYe1）的对象，即new ZouYe1()
				// 格式：this.xxx。xxx代表当前类的变量和方法，例：this.stuno,this.toString();
				return true; 
			}
			if(ob instanceof ZouYe1){  //ob是否是该类
				ZouYe1 st=(ZouYe1) ob;//向下转型 ？转为此类对象 
				                      // 是的，向下转型，将父类对象转为子类对象（Object对象是所有java类对象的父类）
				
			
			if(stuno.equals(st.getStuno()))//比较输入号是否与学生号相等 if语句里的布尔表达式？
				return true;//st是对象? st为何可以调用getStuno属性？st已经是此类对象了！
			                // st是对象，已经是此类对象,在前面已经定义且初始化了（ZouYe1 st=(ZouYe1) ob），当然可以调用它本身的方法getStuno()
			
			
			}
			return false;
		}
		@Override
		public String toString()
		{
			return "姓名："+name+"\t学号："+stuno;
		}
		
		public static  void main(String args[]){
			ZouYe1 z=new ZouYe1();
		}
}
