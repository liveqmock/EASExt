package com.creditease.eas.util;

import java.util.Date;

import com.creditease.eas.compliance.bean.UpdateInfo;

public class UtilObjects {
	public static boolean notEqual(Object obj1,Object obj2){
		Object obj=null;
		if(obj1!=null){
			obj=obj1;
		}
		if(obj2!=null){
			obj=obj2;
		}
		if(obj!=null){
			Class<?> clazz=obj.getClass();
			if(clazz==String.class){
				if(obj1!=null && obj2!=null){
					String s1=obj1.toString();
					String s2=obj2.toString();
					if(!s1.equals(s2)){
						return true;
					}else{
						return false;
					}
				}else{
					if(obj1==null){
						if(obj2.equals("")){
							return false;
						}else{
							return true;
						}
					}
					if(obj2==null){
						if(obj1.equals("")){
							return false;
						}else{
							return true;
						}
					}
				}
			}
			
			//日期
			else if(clazz==Date.class){
				if(obj1!=null && obj2!=null){
					Date date1=(Date)obj1;
					Date date2=(Date)obj2;
					String s1=DateUtil.formatDateToString(date1);
					String s2=DateUtil.formatDateToString(date2);
					if(s1!=null && s2!=null){
						if(!s1.equals(s2)){
							return true;
						}else{
							return false;
						}
					}else{
						return true;
					}	
				}else{
					return true;
				}
			}
			
			
			else if(clazz==Integer.class){
				if(obj1!=null && obj2!=null){
					int i1=(Integer)obj1;
					int i2=(Integer)obj2;
					if(i1!=i2){
						return true;
					}else{
						return false;
					}
				}else{
					return true;
				}
			}
			
			else if(clazz==Long.class){
				if(obj1!=null && obj2!=null){
					long i1=(Long)obj1;
					long i2=(Long)obj2;
					if(i1!=i2){
						return true;
					}else{
						return false;
					}
				}else{
					return true;
				}
			}else {
				return false;
			}	
		}else{
			return false;
		}
		return false;
	}
	
	public static UpdateInfo setUpdateInfo(Object obj1,Object obj2){
		UpdateInfo uinfo=null;
		Object obj=null;
		if(obj1!=null){
			obj=obj1;
		}
		if(obj2!=null){
			obj=obj2;
		}
		if(obj!=null){
			Class<?> clazz=obj.getClass();
			if(clazz==String.class){
				if(obj1!=null && obj2!=null){
					String s1=obj1.toString();
					String s2=obj2.toString();
					if(!s1.equals(s2)){
						uinfo=new UpdateInfo();
						uinfo.setOldValue(s1);
						uinfo.setNewValue(s2);
					}
				}else{
					if(obj1==null){
						uinfo=new UpdateInfo();
						uinfo.setOldValue("");
						uinfo.setNewValue(obj2.toString());
					}
					if(obj2==null){
						uinfo=new UpdateInfo();
						uinfo.setOldValue(obj1.toString());
						uinfo.setNewValue("");
					}
				}
			}
			
			//日期
			else if(clazz==Date.class){
				if(obj1!=null && obj2!=null){
					Date date1=(Date)obj1;
					Date date2=(Date)obj2;
					String s1=DateUtil.formatDateToString(date1);
					String s2=DateUtil.formatDateToString(date2);
					if(s1!=null && s2!=null){
						if(!s1.equals(s2)){
							uinfo=new UpdateInfo();
							uinfo.setOldValue(s1);
							uinfo.setNewValue(s2);
						}
					}else{
						if(s1==null){
							uinfo=new UpdateInfo();
							uinfo.setOldValue("");
							uinfo.setNewValue(s2);
						}else if(s2==null){
							uinfo=new UpdateInfo();
							uinfo.setOldValue(s1);
							uinfo.setNewValue("");
						}
					}	
				}else{
					if(obj1==null){
						uinfo=new UpdateInfo();
						uinfo.setOldValue("");
						Date date2=(Date)obj2;
						uinfo.setNewValue(DateUtil.formatDateToString(date2));
					}else if(obj2==null){
						uinfo=new UpdateInfo();
						Date date1=(Date)obj1;
						uinfo.setOldValue(DateUtil.formatDateToString(date1));
						uinfo.setNewValue("");
					}
				}
			}
			
			
			else if(clazz==Integer.class){
				if(obj1!=null && obj2!=null){
					int i1=(Integer)obj1;
					int i2=(Integer)obj2;
					if(i1!=i2){
						uinfo=new UpdateInfo();
						uinfo.setOldValue(String.valueOf(i1));
						uinfo.setNewValue(String.valueOf(i2));
					}
				}else{
					if(obj1==null){
						uinfo=new UpdateInfo();
						int i2=(Integer)obj2;
						uinfo.setOldValue("");
						uinfo.setNewValue(String.valueOf(i2));
					}else if(obj2==null){
						uinfo=new UpdateInfo();
						int i1=(Integer)obj1;
						uinfo.setOldValue(String.valueOf(i1));
						uinfo.setNewValue("");
					}
				}
			}
			
			else if(clazz==Long.class){
				if(obj1!=null && obj2!=null){
					long i1=(Long)obj1;
					long i2=(Long)obj2;
					if(i1!=i2){
						uinfo=new UpdateInfo();
						uinfo.setOldValue(String.valueOf(i1));
						uinfo.setNewValue(String.valueOf(i2));
					}
				}else{
					if(obj1==null){
						uinfo=new UpdateInfo();
						long i2=(Long)obj2;
						uinfo.setOldValue("");
						uinfo.setNewValue(String.valueOf(i2));
					}else if(obj2==null){
						uinfo=new UpdateInfo();
						long i1=(Long)obj1;
						uinfo.setOldValue(String.valueOf(i1));
						uinfo.setNewValue("");
					}
				}
			}
		}
		
		return uinfo;
	}
	

	
	public static void main(String[] args) {
		/*String s1=null;
		String s2="1";
		System.out.println(notEqual(s1,s2));*/
		/*Date date1=new Date();
		Date date2=new Date();
		System.out.println(notEqual(date1,date2));*/
		
		long i=1;
		long j=1;
		System.out.println(notEqual(i,j));
		
	}
}
