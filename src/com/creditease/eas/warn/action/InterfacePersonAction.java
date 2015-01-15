/**
 * 
 */
package com.creditease.eas.warn.action;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.creditease.eas.util.BaseAction;
import com.creditease.eas.util.Dictionary;
import com.creditease.eas.util.DictionaryUtil;
import com.creditease.eas.warn.bean.InterfacePerson;
import com.creditease.eas.warn.service.InterfacePersonService;

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
public class InterfacePersonAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletResponse response = ServletActionContext.getResponse();
	private InterfacePerson interfacePerson;
	private File excelFile;
	private String nn;
	/**是否部门总接口人**/
	private List<Dictionary> isPorts;
	
	@Autowired
	private InterfacePersonService interfacePersonServiceImpl;

	public File getExcelFile() {
		return excelFile;
	}
	public void setExcelFile(File excelFile) {
		this.excelFile = excelFile;
	}
	public String getNn() {
		return nn;
	}
	public void setNn(String nn) {
		this.nn = nn;
	}
	
	public InterfacePerson getPortInfo() {
		return interfacePerson;
	}
	public void setPortInfo(InterfacePerson portInfo) {
		this.interfacePerson = portInfo;
	}
	
	/**
	 * 
	 * 描述：导入合同信息
	 * 2013-7-18 下午03:34:16 by Administrator
	 * @version
	 * @throws Exception 
	 * @throws Exception
	 */
	public void importExcel() throws Exception{
		try {
			json = interfacePersonServiceImpl.importExcel(excelFile,nn);
		} catch (Exception e) {
			json = "{\"success\":\"false\", \"message\":\"</br>导入异常\"}";
			e.printStackTrace();
		}finally{
			response.setContentType("text/html;charset=UTF-8");
        	response.getWriter().print(json);
		}
	}
	
	/**
	 * 
	 * 描述：缴费付款提醒
	 * 2013-7-30 下午03:25:49 by Administrator
	 * @version
	 * @throws Exception
	 */
	public void payfees() throws Exception {
		interfacePersonServiceImpl.payfees();
	}
	
	public String queryPageJson() throws Exception {
		this.pagination = interfacePersonServiceImpl.queryPage(pagination);
		return "queryPageJson";
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
		interfacePersonServiceImpl.insert(interfacePerson);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.print("<script type='text/javascript'>parent.test();</script>");
		pw.flush();
		pw.close();
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
		interfacePersonServiceImpl.delete(interfacePerson.getId());
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
		this.getAllDictionarys();
		interfacePerson = interfacePersonServiceImpl.getPortInfoById(interfacePerson.getId());
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
			interfacePerson.setLastupdater(this.findUser().getUsername());
			interfacePersonServiceImpl.update(interfacePerson);
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
		this.getAllDictionarys();
		interfacePerson = interfacePersonServiceImpl.getPortInfoById(interfacePerson.getId());
		return "view";
	}
	
	/**
	 * 
	 * 描述：查询所有的下拉列表集合（新增，查看，编辑页面）
	 * 2013-8-28 下午02:32:01 by caoyong
	 * @version
	 * @throws Exception
	 */
	private void getAllDictionarys() throws Exception{
		this.isPorts = DictionaryUtil.getDictionarys(DictionaryUtil.singleMap, DictionaryUtil.yesno);
	}
	/**
	 * @return the isPorts
	 */
	public List<Dictionary> getIsPorts() {
		return isPorts;
	}
	/**
	 * @param isPorts the isPorts to set
	 */
	public void setIsPorts(List<Dictionary> isPorts) {
		this.isPorts = isPorts;
	}
}
