package com.creditease.eas.admin.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.creditease.eas.admin.bean.Menu;
import com.creditease.eas.admin.service.MenuService;
import com.creditease.eas.util.BaseAction;
import com.creditease.eas.util.Dictionary;
import com.creditease.eas.util.DictionaryUtil;
/**
 * @MenuAction.java 菜单bean的Action
 * created at 2013-8-8 下午04:54:06 by caoyong
 * 
 * @author caoyong({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
@Controller
@Scope("prototype")
public class MenuAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**自动装配的service层接口实现类对象**/
	@Autowired
	private MenuService menuServiceImpl;
	/**菜单bean**/
	private Menu menu;
	/**叶子节点集合**/
	private List<Dictionary> leafNames;
	/**菜单级别集合**/
	private List<Dictionary> levelNames;
	/**打开位置集合**/
	private List<Dictionary> targetNames;
	/**上级菜单集合**/
	private List<Menu> parentMenus;
	/**
	 * @return the menu
	 */
	public Menu getMenu() {
		return menu;
	}
	/**
	 * @param menu the menu to set
	 */
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	/**
	 * 
	 * 描述：添加菜单
	 * 2013-8-8 下午05:29:45 by caoyong
	 * @version
	 * @throws Exception
	 */
	public void insert() throws Exception{
		try {
			menuServiceImpl.insert(menu);
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
	 * 描述：跳转到查看页面
	 * 2013-8-9 上午11:35:17 by caoyong
	 * @version
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception{
		this.getAllDictionarys();
		menu = menuServiceImpl.getMenuById(menu.getId());
		return "view";
	}
	/**
	 * 
	 * 描述：编辑 当menu为null时为新增
	 * 2012-12-27 下午06:15:50 by xjw
	 * @version
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception{
		this.getAllDictionarys();
		if(menu!=null) menu = menuServiceImpl.getMenuById(menu.getId());
		return "edit";
	}
	
	/**
	 * 
	 * 描述：查询所有的下拉列表集合（新增，查看，编辑页面）
	 * 2013-8-21 上午11:01:06 by caoyong
	 * @version
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private void getAllDictionarys() throws Exception{
		this.leafNames = DictionaryUtil.getDictionarys(DictionaryUtil.singleMap, DictionaryUtil.leaf);
		this.levelNames = DictionaryUtil.getDictionarys(DictionaryUtil.singleMap, DictionaryUtil.level);
		this.targetNames = DictionaryUtil.getDictionarys(DictionaryUtil.singleMap, DictionaryUtil.target);
		Map map = new HashMap();
		map.put("level", 1);
		this.parentMenus = menuServiceImpl.findByLevel(map);
	}
	/**
	 * 
	 * 描述：更新数据
	 * 2013-8-8 下午05:42:44 by caoyong
	 * @version
	 * @throws Exception
	 */
	public void update() throws Exception{
		try {
			menuServiceImpl.update(menu);
			this.json = "{\"success\":\"true\"}";
		} catch (Exception e) {
			e.printStackTrace();	
			this.json = "{\"success\":\"false\"}";
		}finally{
			this.writerJsonToClient(this.json);
		}
	}
	public void delete() throws Exception{
		menuServiceImpl.deleteById(menu.getId());
	}
	
	/**
	 * 
	 * 描述：获取同级菜单的节点顺序的最大值 
	 * 2013-9-5 下午03:47:53 by caoyong
	 * @version
	 * @throws Exception 
	 */
	public void getMaxSubSequence() throws Exception{
		String subSequence = menuServiceImpl.getMaxSubSequence(request.getParameter("parentId")) + "";
		response.getWriter().write(subSequence);
	}
	/**
	 * 
	 * 描述：返回分页数据后的列表数据
	 * 2012-12-30 下午08:20:21 by ygq
	 * @version
	 * @return
	 * @throws Exception
	 */
	public String queryPageJson() throws Exception {
		this.pagination = menuServiceImpl.queryPage(pagination);
		return "queryPageJson";
	}
	
	/**
	 * @return the leafNames
	 */
	public List<Dictionary> getLeafNames() {
		return leafNames;
	}
	/**
	 * @param leafNames the leafNames to set
	 */
	public void setLeafNames(List<Dictionary> leafNames) {
		this.leafNames = leafNames;
	}
	/**
	 * @return the levelNames
	 */
	public List<Dictionary> getLevelNames() {
		return levelNames;
	}
	/**
	 * @param levelNames the levelNames to set
	 */
	public void setLevelNames(List<Dictionary> levelNames) {
		this.levelNames = levelNames;
	}
	/**
	 * @return the targetNames
	 */
	public List<Dictionary> getTargetNames() {
		return targetNames;
	}
	/**
	 * @param targetNames the targetNames to set
	 */
	public void setTargetNames(List<Dictionary> targetNames) {
		this.targetNames = targetNames;
	}
	/**
	 * @return the parentMenus
	 */
	public List<Menu> getParentMenus() {
		return parentMenus;
	}
	/**
	 * @param parentMenus the parentMenus to set
	 */
	public void setParentMenus(List<Menu> parentMenus) {
		this.parentMenus = parentMenus;
	}
}
