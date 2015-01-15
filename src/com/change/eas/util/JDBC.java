package com.change.eas.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;

public class JDBC {
	String classString = "";
	String connectionString = "";
	String userName = "";
	String passWord = "";
	public Connection Conn;
	public Statement Stmt;

	public JDBC() {
		classString = "oracle.jdbc.driver.OracleDriver";
		connectionString = "jdbc:oracle:thin:@10.100.30.151:1521:cedb";
		userName = "easce0815";
		passWord = "t60GSawn";
	}
	public static void main(String[] args) throws Exception {
		JDBC jdbc = new JDBC();
		jdbc.OpenConnection();
		ArrayList<String> list = new ArrayList<String>();
		ResultSet result = null;
		try {
			String sql ="select fdisplayname_l2 from t_org_baseunit A where fdisplayname_l2 like '%投资理财%' and fdisplayname_l2 like '%分中心%' and fnumber not like '%.%' and A.fisousealup = 0";
			result = jdbc.Stmt.executeQuery(sql);
			while(result.next()){
				String string = result.getString("fdisplayname_l2");
				list.add(string);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			jdbc.CloseConnection();
		}
		HashSet<String> set = new HashSet<String>();
		for(String st : list){
			int end = st.indexOf("分中心");
			CharSequence seq = st.subSequence(0, end);
			int start = seq.toString().lastIndexOf("_");
			CharSequence sub = seq.toString().subSequence(0, start);
			start = sub.toString().lastIndexOf("_");
			CharSequence sub2 = st.subSequence(start+1, end);
			set.add(sub2.toString());
		}
		FullCity.fullCity(set);
	}
	public boolean OpenConnection() {
		boolean mResult = true;
		try {
			Class.forName(classString);
			if ((userName == null) && (passWord == null)) {
				Conn = DriverManager.getConnection(connectionString);
			} else {
				Conn = DriverManager.getConnection(connectionString, userName,
						passWord);
			}
			Stmt = Conn.createStatement();
			mResult = true;
		} catch (Exception e) {
			System.out.println(e.toString());
			mResult = false;
		}
		return (mResult);
	}

	public void CloseConnection() {
		try {
			Stmt.close();
			Conn.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
