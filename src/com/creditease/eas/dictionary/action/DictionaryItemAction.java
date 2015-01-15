package com.creditease.eas.dictionary.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.creditease.eas.dictionary.bean.DictionaryBase;
import com.creditease.eas.dictionary.bean.DictionaryItem;
import com.creditease.eas.dictionary.service.IDictionaryBaseService;
import com.creditease.eas.dictionary.service.IDictionaryItemService;
import com.creditease.eas.util.BaseAction;
/**
 * 数据字典子项Action
 * @DictionaryItemAction.java
 * created at 2014-3-12 下午04:05:13 by zhangxin
 * 
 * @author zhangxin({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
@SuppressWarnings({ "unused", "serial" })
@Controller
@Scope("prototype")
public class DictionaryItemAction extends BaseAction{
	
	private DictionaryItem dictionaryitem;//数据字典子项bean
	private DictionaryBase dictionaryBase;//数据字典父类bean
	@Autowired
	private IDictionaryItemService dictionaryItemServiceImpl;
	@Autowired
	private IDictionaryBaseService dictionaryBaseServiceImpl;
	private static final Logger logger = Logger
	.getLogger(DictionaryItemAction.class);
	public List<DictionaryItem> dictionaryItemList;
	
	public List<DictionaryItem> getDictionaryItemList() {
		return dictionaryItemList;
	}
	public void setDictionaryItemList(List<DictionaryItem> dictionaryItemList) {
		this.dictionaryItemList = dictionaryItemList;
	}
	public DictionaryItem getDictionaryitem() {
		return dictionaryitem;
	}
	public void setDictionaryitem(DictionaryItem dictionaryitem) {
		this.dictionaryitem = dictionaryitem;
	}
	public DictionaryBase getDictionaryBase() {
		return dictionaryBase;
	}
	public void setDictionaryBase(DictionaryBase dictionaryBase) {
		this.dictionaryBase = dictionaryBase;
	}
	/**
	 * 
	 * 描述：查询数据字典子项信息
	 * 2014-3-12 下午04:13:07 by zhangxin
	 * @version
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String queryPageJson(){
		this.pagination = dictionaryItemServiceImpl.queryPage(pagination);
		dictionaryItemList = pagination.getRows();
		return "queryPageJson";
	}

	/**
	 * 
	 * 描述：添加数据字典子项
	 * 2014-3-11 下午02:09:14 by zhangxin
	 * @version
	 * @throws Exception
	 */
	public void insert() throws Exception{
		try {
			dictionaryItemServiceImpl.insertDictionaryItem(dictionaryitem);
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
	 * 描述：查询要修改的字典子项信息
	 * 2014-3-11 下午02:45:56 by zhangxin
	 * @version
	 * @return "edit"
	 */
	public String edit(){
		if(null != dictionaryitem){
			Integer id = dictionaryitem.getId();//子项id
			dictionaryitem = dictionaryItemServiceImpl.findOneDictionaryItem(id);
			Integer bid = dictionaryitem.getBaseid();
			dictionaryBase = dictionaryBaseServiceImpl.findOneDictionaryBase(bid);
			//删除缓存中的数据
			dictionaryBaseServiceImpl.changeCacheDataMap(dictionaryBase.getTypeid());
		}
		
		return "edit";
	}
	/**
	 * 
	 * 描述：根据数据类型父id查询具体数据信息
	 * 2014-3-14 下午03:25:05 by zhangxin
	 * @version
	 * @return
	 */
	public String add(){
		if(null != dictionaryitem){
			Integer baseid = dictionaryitem.getBaseid();
			dictionaryBase = dictionaryBaseServiceImpl.findOneDictionaryBase(baseid);
			//删除缓存中的数据
			dictionaryBaseServiceImpl.changeCacheDataMap(dictionaryBase.getTypeid());
		}
		return "add";
	}
	
	/**
	 * 
	 * 描述：保存要修改的字典子项信息
	 * 2014-3-11 下午02:46:54 by zhangxin
	 * @version
	 * @throws Exception 
	 */
	public void update() throws Exception{
		try {
			dictionaryItemServiceImpl.updateDictionaryItem(dictionaryitem);
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
	 * 描述：删除数据字典子项
	 * 2014-3-11 下午03:22:15 by zhangxin
	 * @version
	 */
	public void delete(){
		dictionaryitem = dictionaryItemServiceImpl.findOneDictionaryItem(dictionaryitem.getId());//查询子项对象
		Integer baseid = dictionaryitem.getBaseid();
		dictionaryBase = dictionaryBaseServiceImpl.findOneDictionaryBase(baseid);
		dictionaryBaseServiceImpl.changeCacheDataMap(dictionaryBase.getTypeid());//删除缓存中的数据
		dictionaryItemServiceImpl.deleteDictionaryItem(dictionaryitem.getId());//删除字典子项信息
		
	}
	
	/**
	 * 
	 * 描述：判断数据关键字是否已经存在
	 * 2014-3-14 下午04:40:48 by zhangxin
	 * @version
	 */
	public void ifDataExists(){
		boolean exist = false;
		PrintWriter pWriter = null;
		String id = request.getParameter("id");
		Integer baseid = Integer.parseInt(id);//父类id即子类Baseid
		try {
			exist = dictionaryItemServiceImpl.itemidIfExists(baseid);
			pWriter = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			pWriter.print(exist);
			pWriter.close();
		}
	}
	
	/**
	 * 
	 * 描述：判断子项数据值是否已经存在
	 * 2014-3-14 下午04:40:48 by zhangxin
	 * @version
	 */
	public void ifItemNameDataExists(){
		boolean exist = false;
		PrintWriter pWriter = null;
		String id = request.getParameter("id");
		Integer bid = Integer.parseInt(id);//父类
		Integer baseid = dictionaryBaseServiceImpl.findOneDictionaryBase(bid).getId();//父类id即子类baseid
		try {
			exist = dictionaryItemServiceImpl.itemnameIfExists(baseid);
			pWriter = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			pWriter.print(exist);
			pWriter.close();
		}
	}
	
	/**
	 * 
	 * 描述：判断子项数据关键字是否已经存在
	 * 2014-5-6 下午04:40:48 by zhangxin
	 * @version
	 */
	public void ifItemIDDataExists(){
		boolean exist = false;
		PrintWriter pWriter = null;
		String id = request.getParameter("id");
		Integer bid = Integer.parseInt(id);//父类
		Integer baseid = dictionaryBaseServiceImpl.findOneDictionaryBase(bid).getId();//父类id即子类baseid
		try {
			exist = dictionaryItemServiceImpl.itemidIfExists(baseid);
			pWriter = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			pWriter.print(exist);
			pWriter.close();
		}
	}
	
	
	
}


