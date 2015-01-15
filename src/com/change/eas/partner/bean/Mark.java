package com.change.eas.partner.bean;

import java.util.HashMap;
import java.util.Map;

public class Mark {
	private long bookmarkid;// number(15) not null,
	private String bookmarkname = "";// varchar2(50),
	private String bookmarkdesc = "";// varchar2(100),
	private String bookmarktext = "";// varchar2(200),
	private String tablename = "";// varchar2(50),
	private String tablefield = "";// varchar2(50),
	private int isloop = -1;// number(15)
	private String loopstring;


	public Mark(){
	  super();
	  getLoopStringByisLoop();
	}
	
	public String getLoopStringByisLoop(){
		if (this.isloop == 1000) {
			this.loopstring="普通标签";
//		} else if (this.isloop == 100) {
//			this.loopstring="表格定位标签";
//		} else if (this.isloop == 101) {
//			this.loopstring="表格循环标签";
//		} else if (this.isloop == 102) {
//			this.loopstring="表格普通标签";
//		} else if (this.isloop == 103) {
//			this.loopstring="表格表头标签";
		} else if (this.isloop == 1001) {
			this.loopstring = "循环标签";
		} else if (this.isloop == 1002) {
			this.loopstring = "不循环标记";
		} else if (this.isloop == 1003) {
			this.loopstring ="时间标签";
		} else if (this.isloop == 1004) {
			this.loopstring ="循环显示字段标签";
		} else if (this.isloop == 1005) {
			this.loopstring ="特殊标签";
		}
		return this.loopstring;
	}
	
	

	public String getUrlPar() {
		String url = "&mark.bookmarkname=" + this.getBookmarkname();
		url += "&mark.isloop=" + this.getIsloop();
		return url;
	}

	public String getLoopstring() {
		return loopstring;
	}

	public void setLoopstring(String loopstring) {
		this.loopstring = loopstring;
	}

	public Map<String, Object> getMap() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("bookmarkname", this.getBookmarkname());
		map.put("isloop", this.getIsloop());
		return map;
	}

	// '0:普通标签，0-100特殊标签（要特殊处理），100表格定位标签，101循环标签，102表格里面的普通标签，103表头标签';
	public String isLoopString() {
		if (isloop == 1000) {
			return "普通标签";
		} else if (isloop == 1001) {
			return "循环标签";
		} else if (isloop == 1002) {
			return "不循环标记";
		} else if (isloop == 1003) {
			return "时间标签";
		} else if (isloop == 1004) {
			return "循环显示字段标签";
//		} else if (isloop == 100) {
//			return "表格定位标签";
//		} else if (isloop == 101) {
//			return "表格循环标签";
//		} else if (isloop == 102) {
//			return "表格普通标签";
//		} else if (isloop == 103) {
//			return "表格表头标签";
		} else if (isloop < 100 && isloop > 0) {
			return "特殊标签";
		} else {
			return "未定义的类型";
		}
	}

	public static Map<String, String> getTypeList() {
		Map<String, String> list = new HashMap<String, String>();
		//list.put("0", "普通标签");
		//list.put("100", "表格定位标签");
		//list.put("101", "表格循环标签");
		//list.put("102", "表格普通标签");
		//list.put("103", "表格表头标签");
		//list.put("2", "特殊标签");
		//list.put("1000", "普通标签");
		list.put("2", "特殊标签");
		list.put("1001", "循环标签");
		list.put("1002", "不循环标记");
		list.put("1003", "时间标签");
		list.put("1004", "循环显示字段标签");
		 
		return list;
	}

	public long getBookmarkid() {
		return bookmarkid;
	}

	public void setBookmarkid(long bookmarkid) {
		this.bookmarkid = bookmarkid;
	}

	public String getBookmarkname() {
		return bookmarkname;
	}

	public void setBookmarkname(String bookmarkname) {
		this.bookmarkname = bookmarkname;
	}

	public String getBookmarkdesc() {
		return bookmarkdesc;
	}

	public void setBookmarkdesc(String bookmarkdesc) {
		this.bookmarkdesc = bookmarkdesc;
	}

	public String getBookmarktext() {
		return bookmarktext;
	}

	public void setBookmarktext(String bookmarktext) {
		this.bookmarktext = bookmarktext;
	}

	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public String getTablefield() {
		return tablefield;
	}

	public void setTablefield(String tablefield) {
		this.tablefield = tablefield;
	}

	public int getIsloop() {
		return isloop;
	}

	public void setIsloop(int isloop) {
		this.isloop = isloop;
	}
}
