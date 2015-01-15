package com.creditease.eas.dictionary.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditease.eas.admin.bean.User;
import com.creditease.eas.admin.dao.ExtDataBaseMapper;
import com.creditease.eas.dictionary.bean.DictionaryBase;
import com.creditease.eas.dictionary.bean.DictionaryItem;
import com.creditease.eas.dictionary.dao.IDictionaryBaseMapper;
import com.creditease.eas.dictionary.dao.IDictionaryItemMapper;
import com.creditease.eas.dictionary.service.IDictionaryBaseService;
import com.creditease.eas.util.PageUtil;
import com.creditease.eas.util.Pagination;
import com.creditease.eas.util.Utils;
import com.sun.org.apache.bcel.internal.generic.NEW;
/**
 * 数据字典基项实现类
 * @DictionaryBaseServiceImpl.java
 * created at 2014-3-10 下午03:38:10 by zhangxin
 * 
 * @author zhangxin({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
@SuppressWarnings("unused")
@Service
public class DictionaryBaseServiceImpl implements IDictionaryBaseService{
	
	@Autowired
	private IDictionaryBaseMapper dictionaryBaseMapper;
	@Autowired
	private IDictionaryItemMapper dictionaryItemMapper;
	@Autowired
	private ExtDataBaseMapper extDataBaseMapper;
	//存放显示过的数据字典数据,key为关键字id,即t_dictionary_base中的id,value为字典数据值集合
	private static Map<String,List<DictionaryItem>> cacheDataMap = new HashMap<String,List<DictionaryItem>>();
	
	
	
	/**
	 * 查询数据字典信息
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Pagination queryPage(Pagination page) {
		HttpServletRequest request= ServletActionContext.getRequest();
		int currentPage = PageUtil.strToPage(request.getParameter("page"));
		int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
		Map mapInfo = new HashMap();
		mapInfo.put("typeid", request.getParameter("typeid"));
		mapInfo.put("typename", request.getParameter("typename"));
		//查询总行数的方法
		int totalCounts = dictionaryBaseMapper.getTotalCountsByParams(mapInfo);//查询总行数
		page = new Pagination(currentPage, pageSize, totalCounts);
		// 查询数据集的方法
		Map mapPageInfo = PageUtil.getMap(page);
		mapPageInfo.putAll(mapInfo);
		List list = dictionaryBaseMapper.queryPageByParams(mapPageInfo);
		page.setRows(list);
		return page;
	}
	/**
	 * 添加数据字典项
	 */
	@Override
	public int insertDictionaryBase(DictionaryBase dictionaryBase) {
		return dictionaryBaseMapper.insertBase(dictionaryBase);
	}
	/**
	 * 根据id查询具体字典项
	 */
	@Override
	public DictionaryBase findOneDictionaryBase(Integer id) {
		return dictionaryBaseMapper.selectByPrimaryKey(id);
	}
	/**
	 * 保存数据字典项信息
	 */
	@Override
	public void updateDictionaryBase(DictionaryBase dictionaryBase) {
		dictionaryBaseMapper.updateByPrimaryKey(dictionaryBase);
	}
	/**
	 * 删除某条字典项数据
	 */
	@Override
	public void deleteDictionaryBase(Integer id) {
		dictionaryBaseMapper.deleteByPrimaryKey(id);
		
	}
	/**
	 * 根据baseid删除子表中的详细数据信息
	 */
	@Override
	public void deleteItemInfo(Integer baseid) {
		dictionaryItemMapper.deleteDataInfo(baseid);
	}
	/**
	 * 数据类型是否重复
	 */
	@Override
	public boolean selectedIfTypeExists() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		return extDataBaseMapper.findExists(Utils.setParams(
				"t_dictionary_base", "id", request.getParameter("columnValue"))) == 0 ? false : true;
	}
	/**
	 * 
	 * 描述：将已显示过的数据字典放在缓存中
	 * 2014-3-28 下午04:56:40 by zhangxin
	 * @version
	 * @return dataDictionaryList 缓存中value值
	 */
	@Override
	public List<DictionaryItem> cacheDictionaryMap(String typeid) {
		List<DictionaryItem> dataDictionaryList;    
		Set<String> keyset = cacheDataMap.keySet();//获取缓存的keyset
		if(keyset.contains(typeid)){//id如果在缓存中
			dataDictionaryList = cacheDataMap.get(typeid);//获得value值
		}else{
			Integer baseid = dictionaryBaseMapper.selectidbytypeid(typeid);//根据类型关键字获得主表id即从表baseid
			dataDictionaryList = dictionaryItemMapper.seleteItemData(baseid);//根据baseid 获得字典子类对象集合 
			cacheDataMap.put(typeid, dataDictionaryList);//将数据放在缓存中
		}
		return dataDictionaryList;
	}
	
	/**
	 * 
	 * 描述：编辑、添加或者删除字典项时，将该字典项从数据字典缓存中删除
	 * 2014-3-28 下午04:56:40 by zhangxin
	 * @version
	 * @param typeid 要变动字典项的类型关键字typeid
	 * @return cacheDataMap 缓存数据
	 */
	@Override
	public Map<String,List<DictionaryItem>>  changeCacheDataMap(String typeid){
		Set<String> keySet = cacheDataMap.keySet();//获取缓存keyset
		if(keySet.contains(typeid)){
			cacheDataMap.remove(typeid);//将该字典项移除缓存
		}
		return cacheDataMap;
	}
	
	/**
	 *描述：获得数据值子项Item的key值
	 * 2014-4-1 下午04:00:17 by zhangxin
	 * @version
	 * @param typeid 类型关键字
	 * @param value  下拉框显示的具体值
	 * @return
	 */
	@Override
	public Integer getItemKey(String typeid, String value) {
		Integer itemId = null;
		List<DictionaryItem> dictionaryItemList;
		if(cacheDataMap.keySet().contains(typeid)){//如果缓存中有
			dictionaryItemList = cacheDataMap.get(typeid);
		}else{
			Integer baseid = dictionaryBaseMapper.selectidbytypeid(typeid);//根据类型关键字获得主表id即从表baseid
			dictionaryItemList = dictionaryItemMapper.seleteItemData(baseid);
			cacheDataMap.put(typeid, dictionaryItemList);//放入缓存中
		}
		for(int i=0;i<dictionaryItemList.size();i++){
			if(value.equals(dictionaryItemList.get(i).getItemname().toString())){//如果value与字典值相同，获取字典值的key值
				itemId = dictionaryItemList.get(i).getId();
				break;
			}
		}
		return itemId;
	}
	/**
	 * 数据字典中是否存在传入的值
	 * @param typeid
	 * @param value
	 * @return boolean true:存在  false:不存在
	 */
	@Override
	public boolean isItemHave(String typeid, String value) {
		Integer tempValue = this.getItemKey(typeid, value);
		if(tempValue != null){
			return true;
		}else {
			return false;
		}
	}
	/**
	 * 删除缓存中的字典数据
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void delectCacheData() {
		Set<String> keySet =  cacheDataMap.keySet();
		System.out.println("删除之前,字典条数："+keySet.size());
		cacheDataMap.clear();
		System.out.println("删除之后,字典条数："+keySet.size());
		
		
	}
	/**
	 * 判断类型关键字是否重复
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean typeidIfExists() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		Map map = new HashMap();
		map.put("typeid", request.getParameter("columnValue"));
		return dictionaryBaseMapper.typeidIfExists(map) == 0 ? false : true;
	}
	/* (non-Javadoc)
	 * @see com.creditease.eas.dictionary.service.IDictionaryBaseService#getItemId(java.lang.String, java.lang.String)
	 */
	@Override
	public String getItemId(String typeid, String value) {
		String itemId = null;
		List<DictionaryItem> dictionaryItemList;
		if(cacheDataMap.keySet().contains(typeid)){//如果缓存中有
			dictionaryItemList = cacheDataMap.get(typeid);
		}else{
			Integer baseid = dictionaryBaseMapper.selectidbytypeid(typeid);//根据类型关键字获得主表id即从表baseid
			dictionaryItemList = dictionaryItemMapper.seleteItemData(baseid);
			cacheDataMap.put(typeid, dictionaryItemList);//放入缓存中
		}
		for(int i=0;i<dictionaryItemList.size();i++){
			if(value.equals(dictionaryItemList.get(i).getItemname().toString())){//如果value与字典值相同，获取字典值的key值
				itemId = dictionaryItemList.get(i).getItemid();
				break;
			}
		}
		return itemId;
	}
}
