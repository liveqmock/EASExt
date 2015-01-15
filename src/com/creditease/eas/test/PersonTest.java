package com.creditease.eas.test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.jms.Connection;

import com.creditease.eas.util.DataTest;
import com.creditease.eas.util.JdbcConn;
import com.creditease.eas.util.Utils;

public class PersonTest {

	static int m = 100;
	static List list = new ArrayList();
	
	public static List getBirthday() {
		
		String months=Utils.getNowMonth();
		String days=Utils.getNowDay();
		String strs=months+days;
		//String sql = "SELECT FNAME_L2,FBIRTHDAY,FCELL FROM T_BD_Person where to_char(trunc(FBIRTHDAY),'mmdd') = '"+strs+"'";
		String sql = "select U.fnumber as loginName, " + 
			"  P.FNAME_L2 as name, " + 
       "  P.fid              as id, " + 
       "  P.Fnumber as personNumber, " + 
       "   PP.FEnterDate as enterDate, " + 
       "  P.Fgender as gender, " + 
       "  P.Fbirthday as birthday, " + 
       "  Pcm.Cfmail as email, " + 
       "  Pcm.Faddress as address, " + 
       "  Pcm.Fhomephone as homephone, " + 
       "  Pcm.Fmobile as cell, " + 
       "  P.FIDCardNO as idCardNO, " + 
       "  P.FPassportNO as passportNO, " + 
       " P.Fnationalityid as nativePlaceID, " + 
       "  P.FEmployeeTypeID as FEmployeeTypeID, " + 
       "  P.FEmployeeClassifyID as FEmployeeClassifyID, " + 
       "  P.FIndex as FIndex, " + 
       "  P.FFullNamePingYin as FFullNamePingYin, " + 
       "   P.CFWorkplaceID as CFWorkplaceID, " + 
       "   P.CFZlID as CFZlID, " + 
       "  P.CFZcID as CFZcID, " + 
       "  P.CFRzzgID as CFRzzgID, " + 
       "  P.CFGangweishuomingI as CFGangweishuomingID, " + 
       "  P.CFShuomingshu as CFShuomingshu, " + 
       "  Po.fid             as positionID, " + 
       "  Po.Fnumber as positionNumber, " + 
       " Po.Fadminorgunitid as orgID, " + 
       " A.Fnumber as orgNumber, " + 
       "  a.fname_l2 as orgname, " + 
       " po.fname_l2 as positionname " + 
       " from t_bd_person P " + 
  " left join t_pm_user U  on U.FPersonId = P.Fid " + 
  " left join T_ORG_PositionMember Pm  on P.fid = Pm.FPersonID " + 
  " left join T_ORG_Position Po  on Po.fid = Pm.FPositionID " + 
  " left join t_Org_Admin A on A.Fid = Po.Fadminorgunitid " + 
  " left join T_HR_PersonContactMethod Pcm on Pcm.Fpersonid = P.Fid " + 
  " left join T_HR_PersonPosition PP on PP.Fpersonid = P.Fid "+
  
 " where P.FDeletedStatus = 1 and to_char(trunc(FBIRTHDAY),'mmdd') ='1221'";
		 System.out.println(sql);
		
		try {
			ResultSet rs1 = JdbcConn.queryResult(JdbcConn.EAS02, sql);
			while (rs1.next()) {
				DataTest dt=new DataTest();
				if(m<10)
					dt.setId(strs+"0"+m);
				else
					dt.setId(strs+m);
				dt.setName(rs1.getString("name"));
				dt.setBirthday(rs1.getString("birthday").substring(0, 10));
				dt.setCell(rs1.getString("cell"));
				dt.setEmail(rs1.getString("email"));
				dt.setPositionid(rs1.getString("positionid"));
				dt.setPositionname(rs1.getString("positionname"));
				dt.setOrgid(rs1.getString("orgid"));
				dt.setOrgname(rs1.getString("orgname"));
				list.add(dt);
				m++;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcConn.colseConn();
		}
		return list;

	}
	
	public static void insertWaringDetail(){
		String sql=null;
		PreparedStatement pstm=null;
		list=getBirthday(); 
		String tmptime=Utils.getTimeStr();
			try {
				if(list!=null){
					System.err.println(list.size());
					for (int i = 0; i < list.size(); i++) {
						DataTest dt=(DataTest) list.get(i);
						System.err.println("id："+dt.getId()+" 用户名："+dt.getName()+" 生日："+dt.getBirthday());
						sql = "insert into T_WaringDetail (id,typeid,wayid,sendtime,cell,departid,departname,postid,postname) values(?,?,?,?,?,?,?,?,?)";
						//sql = "insert into T_WaringDetail (id,typeid,wayid,sendtime,cell) values(?,?,?,?,?)";
						
						pstm=JdbcConn.getPreparedStatement(sql);
						pstm.setInt(1,Utils.getInt(dt.getId()));
						pstm.setInt(2, 1);
						pstm.setInt(3, 1);
						pstm.setString(4, tmptime);
						pstm.setString(5, dt.getCell());
						
						pstm.setString(6, dt.getOrgid());
						pstm.setString(7, dt.getOrgname());
						pstm.setString(8, dt.getPositionid());
						pstm.setString(9, dt.getPositionname());
						pstm.executeUpdate();
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
				
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		insertWaringDetail();
	}

}
