package com.creditease.eas.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
/***
 * 深度克隆的方法
 * @author ygq
 * @verion 1.0 13/12/18 23:14
 *
 */
public class DeepClone {
	/**
	 * 深度克隆的方法
	 * @param src 需要克隆的对象
	 * @return 返回克隆后的对象
	 */
	public static Object deepClone(Object  src) {
		Object o = null;
		try {
			if (src != null) {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(baos);
				oos.writeObject(src);
				oos.close();
				ByteArrayInputStream bais = new ByteArrayInputStream(
						baos.toByteArray());
				ObjectInputStream ois = new ObjectInputStream(bais);
				o = ois.readObject();
				ois.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return o;
	}
	/***
	 * 案例一：
	 * 给内网系统提供接口的时候，需要按照部门进行分组，拼成新的结果集
	 * 所以写了个公用方法，用于代码的转换
	 * @param args 基础的数据信息
	 * @return 返回经过转换之后的数据信息
	 */
	public static List putListToMap(List<Map<String,Object>> list){
		List listAll = new ArrayList();//总的list
		if(null == list || list.size() == 0){
			return null;
		}else{
			Map mapLast = new HashMap();//存放每个部门的数据信息
			List<Map<String,Object>>   listInfo = new LinkedList<Map<String,Object>>();
			Object  beforeDepartment = null;//之前的部门
			for(int i=0;i<list.size();i++){
				Map<String,Object> curInfo = list.get(i);//单条的数据信息
				Object curDept = curInfo.get("DEPARTMENT");//获取部门：需要比较下这两者有什么不同
				//有什么问题呢？
				if(i==0){//第一次取数据
				   beforeDepartment = curDept;//给之前的部门赋值
				   listInfo.add(curInfo);//其他情况下直接添加数据
			   }else if(!beforeDepartment.equals(curDept)){//数据发生变了
				   mapLast.put("department", beforeDepartment);//先将部门放到Map中
				   mapLast.put("datas", listInfo);
				   listAll.add(DeepClone.deepClone(mapLast));
				   mapLast.clear();//清空原来的map
				   listInfo.clear();//清空原来的List
				   
				  //新一轮的循环开始了
				   beforeDepartment = curDept;//给之前的部门赋值
				   listInfo.add(curInfo);//添加当前的数据信息（添加第一条记录）
			   }else{//直接赋值
				   listInfo.add(curInfo);//其他情况下直接添加数据
			   }
			   if(i== list.size()-1){//最后一条数据，特殊处理下
				   mapLast.put("department", beforeDepartment);//先将部门放到Map中
				   mapLast.put("datas", listInfo);
				   listAll.add(mapLast);
			   }
			}
		}
			return listAll;
	}
	/***
	 * 给内网系统提供接口的时候，需要按照部门进行分组，拼成新的结果集
	 * 所以写了个公用方法，用于代码的转换
	 * @param args 基础的数据信息
	 * @return 返回经过转换之后的数据信息
	 */
//	public static List putListToMap(List<Map<String,Object>> list){
//		List listAll = new ArrayList();//总的list
//		if(null == list || list.size() == 0){
//			return null;
//		}else{
//			Map mapLast = new HashMap();//存放每个部门的数据信息
//			List<Map<String,Object>> listInfo = null;
//			Object  beforeDepartment = null;//之前的部门
//			for(int i=0;i<list.size();i++){
//				Map<String,Object> curInfo = list.get(i);//单条的数据信息
//				Object curDept = curInfo.get("DEPARTMENT");//获取部门：需要比较下这两者有什么不同
//				//有什么问题呢？
//				if(i==0){//第一次取数据
//				   beforeDepartment = curDept;//给之前的部门赋值
//				   mapLast.put("department", curDept);//先将部门放到Map中
//				   listInfo = new LinkedList<Map<String,Object>>();
//				   listInfo.add(curInfo);//添加当前的数据信息（添加第一条记录）
//				   
//			   }else if(!beforeDepartment.equals(curDept)){//数据发生变了
//				   mapLast.put("datas", listInfo);
//				   listAll.add(mapLast);
//				  //新一轮的循环开始了
//				   listInfo = new LinkedList<Map<String,Object>>();//重新获取数据
//				   listInfo.add(curInfo);//添加当前的数据信息（添加第一条记录）
//				   beforeDepartment = curDept;//给之前的部门赋值
//				  // mapLast.put("department", curDept);//先将部门放到Map中(存放一个新的部门信息）
//				   
//			   }else{//直接赋值
//				   listInfo.add(curInfo);//其他情况下直接添加数据
//				   System.out.println("");
//			   }
//			   if(i== list.size()-1){//最后一条数据，特殊处理下
//				   mapLast.put("datas", listInfo);
//				   listAll.add(mapLast);
//			   }
//			}
//		}
//			return listAll;
//	}
}
