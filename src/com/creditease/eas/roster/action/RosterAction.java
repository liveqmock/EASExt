package com.creditease.eas.roster.action;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.creditease.eas.admin.bean.User;
import com.creditease.eas.admin.dao.UserRoleMapper;
import com.creditease.eas.dictionary.bean.DictionaryItem;
import com.creditease.eas.dictionary.service.IDictionaryBaseService;
import com.creditease.eas.dictionary.service.IDictionaryItemService;
import com.creditease.eas.roster.bean.Roster;
import com.creditease.eas.roster.service.IRosterService;
import com.creditease.eas.util.BaseAction;
import com.creditease.eas.util.ExpExcelUtil;

/**
 * 投三花名册Action层调用方法
 * @RosterAction.java
 * created at 2014-3-18 下午04:57:53 by zhangxin
 * 
 * @author zhangxin({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
@SuppressWarnings({ "unused", "serial" })
@Controller
@Scope("prototype")
public class RosterAction extends BaseAction{

	public Roster roster;//花名册实体类
	@Autowired
	private IRosterService rosterServiceImpl;
	@Autowired
	private IDictionaryBaseService dictionaryBaseServiceImpl;
	@Autowired
	private IDictionaryItemService dictionaryItemServiceImpl;
	@Autowired
	private UserRoleMapper userRoleMapper;
	private static final Logger logger = Logger
	.getLogger(RosterAction.class);
	/**要导入的excel源数据**/
	private File excelFile;
	/**要导入的excel源数据路径**/
	private String filePath;
	private List<DictionaryItem> oldfourunitlist;//负责营业部集合
	private List<DictionaryItem> financlist;//理财规划师持证集合
	private String[] choseoldfourunitlist; //人员已选择负责的营业部集合
	private String[] chosefinanclist;//已勾选的理财规划师持证集合
	private String persondatapower;//人员数据权限
	
	public File getExcelFile() {
		return excelFile;
	}
	public void setExcelFile(File excelFile) {
		this.excelFile = excelFile;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public List<DictionaryItem> getFinanclist() {
		return financlist;
	}
	public void setFinanclist(List<DictionaryItem> financlist) {
		this.financlist = financlist;
	}
	public String[] getChosefinanclist() {
		return chosefinanclist;
	}
	public void setChosefinanclist(String[] chosefinanclist) {
		this.chosefinanclist = chosefinanclist;
	}
	public List<DictionaryItem> getOldfourunitlist() {
		return oldfourunitlist;
	}
	public void setOldfourunitlist(List<DictionaryItem> oldfourunitlist) {
		this.oldfourunitlist = oldfourunitlist;
	}
	public String[] getChoseoldfourunitlist() {
		return choseoldfourunitlist;
	}
	public void setChoseoldfourunitlist(String[] choseoldfourunitlist) {
		this.choseoldfourunitlist = choseoldfourunitlist;
	}
	public Roster getRoster() {
		return roster;
	}
	public void setRoster(Roster roster) {
		this.roster = roster;
	}
	/**
	 * 
	 * 描述：查询所有花名册信息
	 * 2014-3-10 下午03:12:56 by zhangxin
	 * @version
	 * @return 返回“queryPageJson”
	 */
	@SuppressWarnings("unchecked")
	public String queryPageJson(){
		this.pagination = rosterServiceImpl.queryPage(pagination);
		return "queryPageJson";
	}
	/**
	 * 
	 * 描述：批量修改数据信息页面
	 * 2014-6-26 上午11:24:37 by zhangxin
	 * @version
	 * @return
	 */
	public String queryManyEditJson(){
		
		this.pagination = rosterServiceImpl.queryManyEditInfoPage(pagination);
		return "queryManyEditJson";
	}
	/**
	 * 
	 * 描述：根据typeid查询数据字典信息
	 * 2014-7-7 下午12:31:36 by zhangxin
	 * @version
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String queryDictionaryData(){
		this.pagination = rosterServiceImpl.queryDictionaryData(pagination);
		return "queryDictionaryData";
	}
	
	/**
	 * 
	 * 描述：更新批量修改的数据信息
	 * 2014-6-26 下午03:17:34 by zhangxin
	 * @version
	 * @throws Exception 
	 */
	public void updateManyEditInfo() throws Exception{
		try {
			rosterServiceImpl.updateManyEditInfo();
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
	 * 描述：查询具体某条数据的详细信息
	 * 2014-3-18 下午02:15:38 by zhangxin
	 * @version
	 * @return "view"
	 */
	public String viewRoster(){
		Integer id = roster.getFid();
		roster = rosterServiceImpl.selectByPrimaryKey(id);
		String tempstring = roster.getDatapower();
		String datapowervalue = "";
		String tempvalue = "";
		if(null != tempstring){
			String[] datapowerkeys = tempstring.split(",");
			for(int i=0;i<datapowerkeys.length;i++){
				//获得数据字典
				DictionaryItem tempitem = dictionaryItemServiceImpl.findOneDictionaryItem(Integer.parseInt(datapowerkeys[i]));
				if(tempitem == null || tempitem.equals("")){
					tempvalue = " ";
				}else{
					tempvalue = tempitem.getItemname();//如果在数据字典中存在获取其数据值
				}
				datapowervalue = datapowervalue + "," +tempvalue;
			}
			datapowervalue = datapowervalue.substring(1);
		}
		
		request.setAttribute("datapowervalue",datapowervalue);
		return "view";
	}
	/**
	 * 
	 * 描述：删除某条数据的信息
	 * 2014-3-18 下午02:32:49 by zhangxin
	 * @version
	 */
	public void delete(){
		rosterServiceImpl.deleteRoster(roster.getFid());
	}
	/**
	 * 
	 * 描述：编辑一条花名册信息时
	 * 2014-3-19 下午02:14:51 by zhangxin
	 * @version
	 * @return "edit"
	 */
	public String edit(){
		financlist = dictionaryBaseServiceImpl.cacheDictionaryMap("financialpalncert");//理财规划师持证集合
		if(null != roster){
			roster = rosterServiceImpl.selectByPrimaryKey(roster.getFid());
			oldfourunitlist = dictionaryBaseServiceImpl.cacheDictionaryMap("oldfourunit");//原四级部门数据集合
			if(roster.getDatapower()!=null && roster.getDatapower()!=""){
				choseoldfourunitlist = roster.getDatapower().split(",");//已选择的复选框集合
			}else{
				choseoldfourunitlist = new String[1];
				choseoldfourunitlist[0] = "1";  //为了防止空指针，在数组里放一个不存在的值
			}
			
			if(roster.getFinancialpalncert() != null && roster.getFinancialpalncert()!=""){
				chosefinanclist = roster.getFinancialpalncert().split(",");//已选择的理财规划师持证
			}else{
				chosefinanclist = new String[1];
				chosefinanclist[0] = "1";
			}
			
		}
		return "edit";
	}
	/**
	 * 
	 * 描述：点批量修改按钮时跳转到manyeditinfo.jsp页面
	 * 2014-6-25 下午05:34:30 by zhangxin
	 * @version
	 * @return “manyeditinfo”
	 */
	public String manyeditinfo(){
		String fids =  request.getParameter("fids");//获取页面要修改数据的id
		
		if(fids.length()>0){
		String subfids = fids.substring(0,fids.length()-1);//截取掉最后一个“,”
		request.setAttribute("manyfids", subfids);
		}
		return "manyeditinfo";
	}
	
	/**
	 * 
	 * 描述：查看导入数据时错误的数据行号
	 * 2014-4-11 下午02:05:10 by zhangxin
	 * @version
	 * @return
	 */
	public String viewErrorData(){
		rosterServiceImpl.viewErrorData();
		return "viewErrorData";
	}
	
	
	/**
	 * 
	 * 描述：插入花名册信息
	 * 2014-3-19 下午04:01:35 by zhangxin
	 * @version
	 * @throws Exception 
	 */
	public void insert() throws Exception{
		try {
			rosterServiceImpl.insertRoster(roster);
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
	 * 描述：更新花名册信息
	 * 2014-3-19 下午04:14:02 by zhangxin
	 * @version
	 * @throws Exception 
	 */
	public void update() throws Exception{
		try {
			
			//获得当前登录用户
			User user = (User)request.getSession().getAttribute("user");
			//获得登录用户id值
			int userid = user.getId().intValue();
			int roleid = userRoleMapper.userroleid(userid);
			/**
			 * 投三超级管理员(568)和一般管理员(587)在编辑页面可以授数据权限，如果这两个角色的编辑数据保存页面数据权限信息,
			 * 其他角色在编辑页面时保存原本的数据权限。
			 */
			if(roleid != 587 && roleid!=568 ){
				roster.setDatapower(request.getParameter("persondatapower"));
			}else{
				 String[] tempdata = request.getParameterValues("choseunititemid");//已勾选的负责营业部
			     if(tempdata == null){  
			       tempdata = new String[1];
			       tempdata[0] = "99999999";//无权限
			        }	
			     String datapower="";
			     for(int i=0;i<tempdata.length;i++){
			       datapower = datapower + "," + tempdata[i];
			        }
			     roster.setDatapower(datapower.substring(1));//所负责营业部的信息
			}
			
			rosterServiceImpl.updateRoster(roster);
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
	 * 描述：导出花名册信息
	 * 2014-3-20 下午01:45:56 by zhangxin
	 * @version
	 * @throws Exception
	 */
	public void expExcel() throws Exception{
		rosterServiceImpl.expExcel();
	}
	
	/**
	 * 
	 * 描述：导出简版花名册信息
	 * 2014-3-20 下午01:45:56 by zhangxin
	 * @version
	 * @throws Exception
	 */
	public void expExcelSimple() throws Exception{
		rosterServiceImpl.expExcelSimple();
	}
	
	/**
	 * 
	 * 描述：导入excel，校验错误行
	 * 2014-3-31 下午04:46:02 by zhangxin
	 * @version
	 * @throws Exception
	 */
	public void importExcelValidation() throws Exception{
    	try {
			json = rosterServiceImpl.importExcelValidation(excelFile,filePath);
		} catch (Exception e) {
			json = "{\"success\":\"false\", \"message\":\"</br>数据异常\"}";
			e.printStackTrace();
			logger.error(e.getMessage());
		}finally{
			response.setContentType("text/html;charset=UTF-8");
        	response.getWriter().print(json);
		}
    }
   
	/**
    * 
    * 描述：数据导入功能
    * 2014-3-31 下午04:46:40 by zhangxin
    * @version
    * @throws Exception
    */
	public void importExcel() throws Exception{
		try {
			json = rosterServiceImpl.importExcel(excelFile,filePath);
		} catch (Exception e) {
			json = "{\"success\":\"false\", \"message\":\"</br>导入异常\"}";
			e.printStackTrace();
			logger.error(e.getMessage());
		}finally{
			response.setContentType("text/html;charset=UTF-8");
        	response.getWriter().print(json);
		}
	}
	/**
	 * 
	 * 描述：判断12位员工编号是否重复
	 * 2014-4-24 上午10:31:29 by zhangxin
	 * @version
	 * @throws Exception
	 */
	public void ifNumHasExists() throws Exception{
		boolean exist = false;
		PrintWriter pw = null;
		try {
			exist = rosterServiceImpl.ifNumHasExists();
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
	 * 描述：判断邮箱是否重复
	 * 2014-4-24 上午10:31:29 by zhangxin
	 * @version
	 * @throws Exception
	 */
	public void ifEmailHasExists() throws Exception{
		boolean exist = false;
		PrintWriter pw = null;
		try {
			exist = rosterServiceImpl.ifEmailHasExists();
			pw = response.getWriter();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			pw.print(exist);
			pw.close();
		}
	}
	
	
	
}