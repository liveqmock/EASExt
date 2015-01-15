package com.creditease.eas.hr.kingdee.query;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;

import com.creditease.eas.hr.bean.oa.ProvinceAndCityBean;
import com.creditease.eas.hr.bean.oa.WSBankBean;
import com.creditease.eas.hr.bean.oa.WSSupplierBean;
import com.creditease.eas.hr.bean.oa.WrapperMap; 
import com.creditease.eas.util.BaseMyBatisDao;
import com.creditease.eas.util.StringUtil;
/**
 * 码表信息查询
 * @QualifyingMatureQuery001.java
 * created at 2013-1-4 下午02:07:43 by ygq
 * 
 * @author ygq({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class WSSupplierMapperQuery extends BaseMyBatisDao{
	/**
	 * 描述：查询供应商的信息
	 * 2013-3-17 下午10:29:19 by ygq
	 * @version
	 * @return
	 */
	public static List<WSSupplierBean>  selectSupplierInfo(WrapperMap wra){
		 
		
		SqlSession session = null;
		WSSupplierBean ws = null;
		/* 需求更改，分页查询供应商
		try {
			session = getSession();
			List<Map<String,Object>> list = session.selectList("com.creditease.eas.hr.kingdee.dao.WSSupplierMapper.selectSupplierInfo",fnumber);
			ws = new WSSupplierBean();
			List<WSBankBean> listWSBankBean = new ArrayList<WSBankBean>();
			if(null != list && list.size()>0){//一般情况下只有一个供应商，
				String supplierNumber = null;
				for(int i=0;i<list.size();i++){
				   Map<String,Object> ms = list.get(i);
				   System.out.println(ms.get("SUPPLIERNAME"));
					//需要将信息进行转化
				   if(supplierNumber == null){
					   supplierNumber = StringUtil.objToString2(ms.get("SUPPLIERNUMBER"));
					   String supplierName = StringUtil.objToString2(ms.get("SUPPLIERNAME"));
					   String provinceName = StringUtil.objToString2(ms.get("PROVINCENAME"));
					   String provinceNumber = StringUtil.objToString2(ms.get("PROVINCENUMBER"));
					   String ctiyNumber = StringUtil.objToString2(ms.get("CTIYNUMBER"));
					   String ctiyName = StringUtil.objToString2(ms.get("CTIYNAME"));
					   ws.setSupplierNumber(supplierNumber);
					   ws.setSupplierName(supplierName);
					   ws.setCtiyNumber(ctiyNumber);
					   ws.setCtiyName(ctiyName);
					   ws.setProvinceName(provinceName);
					   ws.setProvinceNumber(provinceNumber);
					   //设置银行卡的信息
					   String bankName = StringUtil.objToString2(ms.get("BANKNAME"));
					   String bankAccount = StringUtil.objToString2(ms.get("BANKACCOUNT"));
					   String bankAddress = StringUtil.objToString2(ms.get("BANKADDRESS"));
					   WSBankBean wb = new WSBankBean();
					   wb.setBankName(bankName);
					   wb.setBankAccount(bankAccount);
					   wb.setBankAddress(bankAddress);
					   listWSBankBean.add(wb);
				   }else{//查询出来 的肯定是该供应商的其他的信息
					   String bankName = StringUtil.objToString2(ms.get("BANKNAME"));
					   String bankAccount = StringUtil.objToString2(ms.get("BANKACCOUNT"));
					   String bankAddress = StringUtil.objToString2(ms.get("BANKADDRESS"));
					   WSBankBean wb = new WSBankBean();
					   wb.setBankName(bankName);
					   wb.setBankAccount(bankAccount);
					   wb.setBankAddress(bankAddress);
					   listWSBankBean.add(wb);   
				   }
				}
			//	ws.setList(listWSBankBean);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		*/
		List<WSSupplierBean> list = null;
//		BigDecimal max=BigDecimal.ZERO,min=BigDecimal.ZERO; 
//		max = page.multiply(pagesize);
//		min = (page.subtract(BigDecimal.ONE)).multiply(pagesize).add(BigDecimal.ONE);
		try{
			BigDecimal max=BigDecimal.ZERO,min=BigDecimal.ZERO;
			BigDecimal page = new BigDecimal(wra.getPage());
			BigDecimal pagesize = new BigDecimal(wra.getPagesize());
			max = page.multiply(pagesize);
			min = (page.subtract(BigDecimal.ONE)).multiply(pagesize).add(BigDecimal.ONE);
			wra.setMax(max);
			wra.setMin(min);
			
			String supName = null;
			if(null==wra.getSupplierName()||"".equals(wra.getSupplierName())){
				supName ="%";
			}else{
				supName = StringUtil.addLikeOperBoth(wra.getSupplierName()); 
			}
			wra.setSupplierName(supName);
			
			session = getSession();    
			list = session.selectList("com.creditease.eas.hr.kingdee.dao.WSSupplierMapper.selectWSSupplierInfo",wra); 
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return list;
	}
	
	public static Integer selectSupplierCount(WrapperMap wra){
		SqlSession session = null;
		Integer count = null ;
		try{ 
			String supName = null;
			if(null==wra.getSupplierName()||"".equals(wra.getSupplierName())){
				supName ="%";
			}else{
				supName = StringUtil.addLikeOperBoth(wra.getSupplierName()); 
			}
			wra.setSupplierName(supName);
			
			session = getSession();
			count =   (Integer) session.selectOne("com.creditease.eas.hr.kingdee.dao.WSSupplierMapper.selectWSSuppliercount",wra);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return count;
	}
	/**
	 * 
	 * 描述：省
	 * 2013-4-16 上午10:38:12 by guominggao
	 * @version
	 * @return
	 */
	public static List<ProvinceAndCityBean> selectProvinceInfo(){
		SqlSession session = null;
		List<ProvinceAndCityBean> list = null;
		try{
			session = getSession();
			list = session.selectList("com.creditease.eas.hr.kingdee.dao.WSSupplierMapper.selectProvinceInfo");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return list;
	}
	/**
	 * 
	 * 描述： 根据省，得到市
	 * 2013-4-16 上午10:38:46 by guominggao
	 * @version
	 * @return
	 */
	public static List<ProvinceAndCityBean> selectListCityInfoByProvincenumber(String fnumber){
		SqlSession session = null;
		List<ProvinceAndCityBean> list = null;
		try{
			session = getSession();
			list = session.selectList("com.creditease.eas.hr.kingdee.dao.WSSupplierMapper.selectCityInfoByProvincenumber",fnumber);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeSession(session);
		}
		return list;
	}
	
	
//	public static void main(String[] args) {
//		selectSupplierInfo("N0100001");
//		System.out.println("end");
//		System.out.println("dd");
//	}
	public static void main(String[] args)  {
		
		List<ProvinceAndCityBean> list = selectListCityInfoByProvincenumber("03");
		for (int i = 0; i < list.size(); i++) { 
			 ProvinceAndCityBean temp = list.get(i);
			System.out.println(temp.getCityName());
		}
		
//		List<Map<String,Object>> list = selectProvinceInfo();
//		for (int i = 0; i < list.size(); i++) {
//			System.out.println(list.get(i));
//		}
		
//		WrapperMap  wra = new WrapperMap(); 
//		wra.setSupplierName("");
//		
//		  Integer count = selectSupplierCount(wra);
//		System.out.println("总数为："+count);
		
//		BigDecimal page = new BigDecimal(2),pagesize = new BigDecimal(5);
//		
//		
//		BigDecimal max=BigDecimal.ZERO,min=BigDecimal.ZERO; 
//		max = page.multiply(pagesize);
//		min = (page.subtract(BigDecimal.ONE)).multiply(pagesize).add(BigDecimal.ONE);

		//		Map<String,Object> mp = new HashMap<String,Object>();
//		mp.put("max", max);
//		mp.put("min", min);
//		mp.put("supplierName", "%");
//		List<CapticalAssetsBean> list = queryAssetsClassNameAndCodeByFistClass(mp);

//		 List<WSSupplierBean> temp = selectSupplierInfo(mp);
		
//		WrapperMap  wra = new WrapperMap();
//		wra.setPage(1);
//		wra.setPagesize(10000000);
//		wra.setSupplierName("丁");
//		List<WSSupplierBean> temp = selectSupplierInfo(wra);
//		 for (int i = 0; i < temp.size(); i++) {
//			 WSSupplierBean wsinfo = temp.get(i);
//			System.out.println(wsinfo.getProvinceName()+wsinfo.getBankAccount());
//		}
		
		
	}
}
