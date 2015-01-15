/**
 * 
 */
package com.creditease.eas.warn.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.creditease.eas.util.BaseAction;
import com.creditease.eas.warn.bean.BlackListInfo;
import com.creditease.eas.warn.bean.Processmode;
import com.creditease.eas.warn.bean.WaringType;
import com.creditease.eas.warn.service.BlackListInfoService;

/**
 * @JumbosmsvAction.java
 * created at 2012-12-26 下午05:04:08 by xjw
 * 
 * @author xjw({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
@Controller
@Scope("prototype")
public class BlackListInfoAction extends BaseAction{
	private HttpServletResponse response = ServletActionContext.getResponse();
	private BlackListInfo blackListInfo;
	
	private List<Processmode> modeList = new ArrayList<Processmode>();
	private List<WaringType> typeList = new ArrayList<WaringType>();
	
	private Processmode promode;
	private WaringType warntype;
	
	
	public Processmode getPromode() {
		return promode;
	}

	public void setPromode(Processmode promode) {
		this.promode = promode;
	}

	public WaringType getWarntype() {
		return warntype;
	}

	public void setWarntype(WaringType warntype) {
		this.warntype = warntype;
	}

	public List<Processmode> getModeList() {
		return modeList;
	}

	public void setModeList(List<Processmode> modeList) {
		this.modeList = modeList;
	}

	public List<WaringType> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<WaringType> typeList) {
		this.typeList = typeList;
	}

	public BlackListInfo getBlackListInfo() {
		return blackListInfo;
	}

	public void setBlackListInfo(BlackListInfo blackListInfo) {
		this.blackListInfo = blackListInfo;
	}
	@Autowired
	private BlackListInfoService blackListInfoServiceImpl;
	
	public String queryPageJson() throws Exception {
		this.pagination = blackListInfoServiceImpl.queryPage(pagination);
	  // 将new出来的 分页对象 付给 Action的属性对象里
		return "queryPageJson";
	}
	
	public String queryPageJsonBack() throws Exception{
		return "queryPageJsonBack";
	}
	/**
	 * 插入
	 * 描述：
	 * 2012-12-27 下午06:17:08 by xjw
	 * @version
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		modeList = blackListInfoServiceImpl.modeList();
		typeList = blackListInfoServiceImpl.warntypeList();
		return "add";
	}
	/**
	 * 保存
	 * 描述：
	 * 2012-12-27 下午06:16:52 by xjw
	 * @version
	 * @return
	 * @throws Exception
	 */
	public void save() throws Exception{
		try {
			blackListInfoServiceImpl.insert(blackListInfo);
			this.json = "{\"success\":\"true\"}";
		} catch (Exception e) {
			e.printStackTrace();	
			this.json = "{\"success\":\"false\"}";
		}finally{
			this.writerJsonToClient(this.json);
		}
	}
	/**
	 * 删除
	 * 描述：
	 * 2012-12-27 下午06:16:44 by xjw
	 * @version
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception{
		blackListInfoServiceImpl.delete(blackListInfo.getPid());
		return "queryPageJsonBack";
	}
	/**
	 * 编辑
	 * 描述：
	 * 2012-12-27 下午06:15:50 by xjw
	 * @version
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception{
		modeList = blackListInfoServiceImpl.modeList();
		typeList = blackListInfoServiceImpl.warntypeList();
		if(blackListInfo!=null) blackListInfo = blackListInfoServiceImpl.getBlackListInfoById(blackListInfo.getPid());
		return "edit";
	}
	/**修改
	 * x
	 * 描述：
	 * 2012-12-27 下午06:15:59 by xjw
	 * @version
	 * @return
	 * @throws Exception
	 */
	public void update() throws Exception{
		try {
			blackListInfoServiceImpl.update(blackListInfo);
			this.json = "{\"success\":\"true\"}";
		} catch (Exception e) {
			e.printStackTrace();	
			this.json = "{\"success\":\"false\"}";
		}finally{
			this.writerJsonToClient(this.json);
		}
	}
	/**
	 * 查看
	 * 描述：
	 * 2012-12-27 下午06:16:18 by xjw
	 * @version
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception{
		blackListInfo = blackListInfoServiceImpl.getBlackListInfoById(blackListInfo.getPid());
		promode = blackListInfoServiceImpl.getProcessmode(blackListInfo.getModeid());
		warntype = blackListInfoServiceImpl.getWaringType(blackListInfo.getWarntype());
		return "view";
	}
	
}
