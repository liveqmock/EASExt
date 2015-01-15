package com.creditease.eas.hr.webService.supplier.impl;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import com.creditease.eas.hr.bean.oa.ProvinceAndCityBean;
import com.creditease.eas.hr.bean.oa.WSSupplierBean;
import com.creditease.eas.hr.bean.oa.WSSupplierListBean;
import com.creditease.eas.hr.bean.oa.WrapperMap;
import com.creditease.eas.hr.kingdee.query.WSSupplierMapperQuery; 
import com.creditease.eas.hr.webService.supplier.ISupplierService;
@WebService(endpointInterface = "com.creditease.eas.hr.webService.supplier.ISupplierService",targetNamespace="supplierService",portName="supplierServiceImpl")
public class SupplierServiceImpl implements ISupplierService{
	@Override
	public  List<WSSupplierBean> selectSupplierInfoByNames(WrapperMap wra) {
		//		return (WSSupplierBean) WSSupplierMapperQuery.selectSupplierInfo(supplierId);
		 
		return  WSSupplierMapperQuery.selectSupplierInfo(wra); 
	}
	
	public static void main(String[] args) {
		
//		List<ProvinceAndCityBean> list = WSSupplierMapperQuery.selectProvinceInfo();
//		for (int i = 0; i < list.size(); i++) { 
//			 ProvinceAndCityBean temp = list.get(i);
//			System.out.println(temp.getProvinceName());
//		}
//		List<ProvinceAndCityBean> list = WSSupplierMapperQuery.selectListCityInfoByProvincenumber("03");
//		for (int i = 0; i < list.size(); i++) { 
//			 ProvinceAndCityBean temp = list.get(i);
//			System.out.println(temp.getCityName());
//		}
		
//		WSSupplierBean ws = (WSSupplierBean) WSSupplierMapperQuery.selectSupplierInfo("N0100001");
//		System.out.println("why\t" + ws.getSupplierNumber() + "\t" + ws.getCtiyName());
//		com.creditease.eas.hr.webService.supplier.impl.SupplierServiceImpl
		
//		BigDecimal max=BigDecimal.ZERO,min=BigDecimal.ZERO;
//		BigDecimal page = new BigDecimal(2),pagesize = new BigDecimal(3);
//		max = page.multiply(pagesize);
//		min = (page.subtract(BigDecimal.ONE)).multiply(pagesize).add(BigDecimal.ONE);
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("max", max);
//		map.put("min", min);
//		map.put("supplierName", "%");
		
		
//		WrapperMap wra = new WrapperMap();
//		wra.setPage(1);
//		wra.setPagesize(5);
//		wra.setSupplierName("丁");
//		List<WSSupplierBean> ws =  WSSupplierMapperQuery.selectSupplierInfo(wra);
//		for (int i = 0; i < ws.size(); i++) {
//			WSSupplierBean wsinfo = ws.get(i);
//			System.out.println(wsinfo.getSupplierName()+wsinfo.getProvinceName()+wsinfo.getBankAccount());
//		}
//		
		WrapperMap wra = new WrapperMap();
		wra.setPage(1);
		wra.setPagesize(5);
		wra.setSupplierName("丁");
		// TODO Auto-generated method stub
		WSSupplierListBean listbean = new WSSupplierListBean();
		
		List<WSSupplierBean> listinfo = WSSupplierMapperQuery.selectSupplierInfo(wra);
		Integer count = WSSupplierMapperQuery.selectSupplierCount(wra);
		listbean.setWssbean(listinfo);
		listbean.setCount(count);
		System.out.println(listinfo);
	}
	
	
	
	@Override
	public WSSupplierBean selectSupplierInfoById(String supplierId) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 供应商分页
	 */
	public WSSupplierListBean selectSupplierList(WrapperMap wra) {
		// TODO Auto-generated method stub
		WSSupplierListBean listbean = new WSSupplierListBean();
		
		List<WSSupplierBean> listinfo = WSSupplierMapperQuery.selectSupplierInfo(wra);
		Integer count = WSSupplierMapperQuery.selectSupplierCount(wra);
		listbean.setWssbean(listinfo);
		listbean.setCount(count);
		
		return listbean;
	}

	/**
	 * 根据省，得到市
	 */
	public List<ProvinceAndCityBean> selectCityList(String fnumber) {
		List<ProvinceAndCityBean> list = null;
		list = WSSupplierMapperQuery.selectListCityInfoByProvincenumber(fnumber);
		return list;
	}

	/**
	 * 省
	 */
	public List<ProvinceAndCityBean> selectProvinceList() {
		List<ProvinceAndCityBean> list = null;
		list = WSSupplierMapperQuery.selectProvinceInfo();
		return list;
	}


	 
	 
}
