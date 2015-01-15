package com.creditease.eas.quartz;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.creditease.eas.util.JdbcConn;
import com.creditease.eas.util.Utils;
import com.creditease.eas.warn.bean.Person;

public class PersonUtils {

	static String m = "1";
	static List list = new ArrayList();
	
	public static List getBirthday() {
		
		String months=Utils.getNowMonth();
		String days=Utils.getNowDay();
		String strs=months+days;
		String sql = "SELECT FNAME_L2,FBIRTHDAY,FCELL FROM T_BD_Person where to_char(trunc(FBIRTHDAY),'mmdd') = '"+strs+"'";
		 System.out.println(sql);
		
	
		ResultSet rs1 = JdbcConn.queryResult(JdbcConn.EAS02, sql);
		try {
			while (rs1.next()) {
				Person p=new Person();
				p.setFid(m);
				p.setFname(rs1.getString("FNAME_L2"));
				p.setFbirthday(rs1.getString("FBIRTHDAY"));
				list.add(p);
				//m++;
			}
			
			if(list!=null){
				for (int i = 0; i < list.size(); i++) {
					Person p=(Person) list.get(i);
					System.err.println("序号："+p.getFid()+" 姓名："+p.getFname()+" 生日："+p.getFbirthday());
				}
				System.out.println("---------------------------------------------");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcConn.colseConn();
		}
		return list;

	}
	
	public static List getBirthdayByStrs(String strs){
		//List list=getBirthday(strs);
		if(list!=null){
			for (int i = 0; i < list.size(); i++) {
				Person p=(Person) list.get(i);
				System.err.println("序号："+p.getFid()+" 姓名："+p.getFname()+" 生日："+p.getFbirthday());
			}
			System.out.println("---------------------------------------------");
		}
		return list;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stubsss
		String months=Utils.getNowMonth();
		String days=Utils.getNowDay();
		String strs=months+days;
		
		getBirthday();
		//getBirthdayByStrs(strs);
	}

}
