package com.creditease.eas.dictionary.action;

import java.io.PrintWriter;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.creditease.eas.dictionary.bean.DictionaryBase;
import com.creditease.eas.dictionary.bean.DictionaryItem;
import com.creditease.eas.dictionary.service.IDictionaryBaseService;
import com.creditease.eas.util.BaseAction;
import com.creditease.eas.util.StringUtil;
/**
 * 数据字典基项Action
 * 提供了增删改查的功能
 * @DictionaryBaseAction.java
 * created at 2014-3-10 下午02:45:51 by zhangxin
 * 
 * @author zhangxin({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
@SuppressWarnings({ "unused", "serial" })
@Controller
@Scope("prototype")
public class DictionaryBaseAction extends BaseAction{
	private DictionaryBase dictionarybase;//数据字典项实体bean
	@Autowired
	private IDictionaryBaseService dictionaryBaseServiceImpl;
	private static final Logger logger = Logger
	.getLogger(DictionaryBaseAction.class);
	public List<DictionaryBase> dictionaryBaseList;//数据字典基项集合
	
	public List<DictionaryBase> getDictionaryBaseList() {
		return dictionaryBaseList;
	}
	public void setDictionaryBaseList(List<DictionaryBase> dictionaryBaseList) {
		this.dictionaryBaseList = dictionaryBaseList;
	}
	public DictionaryBase getDictionarybase() {
		return dictionarybase;
	}
	public void setDictionarybase(DictionaryBase dictionarybase) {
		this.dictionarybase = dictionarybase;
	}
	/**
	 * 
	 * 描述：查询所有的数据字典基项
	 * 2014-3-10 下午03:12:56 by zhangxin
	 * @version
	 * @return 返回“queryPageJson”
	 */
	@SuppressWarnings("unchecked")
	public String queryPageJson(){
		this.pagination = dictionaryBaseServiceImpl.queryPage(pagination);
		dictionaryBaseList = pagination.getRows();
		return "queryPageJson";
	}
	
	/**
	 * 
	 * 描述：添加数据字典项
	 * 2014-3-11 下午02:09:14 by zhangxin
	 * @version
	 * @throws Exception
	 */
	public void insert() throws Exception{
		try {
			dictionaryBaseServiceImpl.insertDictionaryBase(dictionarybase);
			this.json = "{\"success\":\"true\"}";
		} catch (Exception e) {
			e.printStackTrace();
			this.json = "{\"success\":\"false\"}";
		}finally{
			this.writerJsonToClient(this.json);
		}
	}
	/**
	 * 
	 * 描述：查询要修改的字典信息
	 * 2014-3-11 下午02:45:56 by zhangxin
	 * @version
	 * @return "edit"
	 */
	public String edit(){
		if(null != dictionarybase){
			Integer id = dictionarybase.getId();
			dictionarybase = dictionaryBaseServiceImpl.findOneDictionaryBase(id);
			dictionaryBaseServiceImpl.changeCacheDataMap(dictionarybase.getTypeid());//删除缓存中的数据
		}
		
		return "edit";
	}
	
	/**
	 * 
	 * 描述：保存要修改的字典项信息
	 * 2014-3-11 下午02:46:54 by zhangxin
	 * @version
	 * @throws Exception 
	 */
	public void update() throws Exception{
		try {
			dictionaryBaseServiceImpl.updateDictionaryBase(dictionarybase);
			this.json = "{\"success\":\"true\"}";
		} catch (Exception e) {
			e.printStackTrace();
			this.json = "{\"success\":\"false\"}";
		}finally{
			this.writerJsonToClient(this.json);
		}
	}
	/**
	 * 
	 * 描述：查询数据字典,返回json数据
	 * 2014-3-31 上午10:26:53 by zhangxin
	 * @version
	 */
	public void findDictionary(){
		try {
			String typeid = request.getParameter("id");//类型关键字 t_dictionary_base表中typeid
			List<DictionaryItem> list = dictionaryBaseServiceImpl.cacheDictionaryMap(typeid);
			String json = StringUtil.convertListToGson(list);
			writerJsonToClient(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	/**
	 * 
	 * 描述：删除某条数据,并级联删除子表中的信息
	 * 2014-3-11 下午03:22:15 by zhangxin
	 * @version
	 */
	public void delete(){
		Integer baseid = dictionarybase.getId();
		dictionarybase = dictionaryBaseServiceImpl.findOneDictionaryBase(baseid);
		dictionaryBaseServiceImpl.deleteDictionaryBase(baseid);
		dictionaryBaseServiceImpl.deleteItemInfo(baseid);
		dictionaryBaseServiceImpl.changeCacheDataMap(dictionarybase.getTypeid());//删除缓存中的数据
	}
	/**
	 * 
	 * 描述：删除缓存中字典数据
	 * 2014-4-23 上午10:59:20 by zhangxin
	 * @version
	 */
	public void deleteCacheData(){
		dictionaryBaseServiceImpl.delectCacheData();
		
	}
	
	
	
	/**
	 * 
	 * 描述：判断类型关键字是否重复
	 * 2014-3-26 下午03:42:57 by zhangxin
	 * @version
	 * @throws Exception
	 */
	public void selectedIfTypeExists() throws Exception{
		boolean exist = false;
		PrintWriter pw = null;
		try {
			exist = dictionaryBaseServiceImpl.selectedIfTypeExists();
			pw = response.getWriter();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			pw.print(exist);
			pw.close();
		}
	}
	
	/**
	 * 
	 * 描述：判断类型关键字是否重复
	 * 2014-5-6 下午04:47:33 by zhangxin
	 * @version
	 * @throws Exception
	 */
	public void typeidIfExists() throws Exception{
		boolean exist = false;
		PrintWriter pw = null;
		try {
			exist = dictionaryBaseServiceImpl.typeidIfExists();
			pw = response.getWriter();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			pw.print(exist);
			pw.close();
		}
	}
	
	
}
