package com.creditease.eas.warn.action;
 

 
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.creditease.eas.util.BaseAction;
import com.creditease.eas.warn.bean.Homologous;
import com.creditease.eas.warn.bean.OrgManager;
import com.creditease.eas.warn.kingdee.query.OrgAdminMapperQuery;
import com.creditease.eas.warn.kingdee.query.OrgManagerQuery;
import com.creditease.eas.warn.service.OrgManagerService;

@Controller
@Scope("prototype")
public class OrgManagerAction extends BaseAction  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
 
	private List<Map<String,Object>> listorg  ;
	
	@Autowired  
	private OrgManagerService orgManagerServiceImpl;
	private OrgManager orgManager = new OrgManager();
	private String fnumber;
	private String orgname;
	public Map<String,Object> editCopypeoMap; //编辑抄送人信息集合
	public String fpersonname;
	public String fpersonemail;
	private String fid;

	
	

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	public String getFpersonname() {
		return fpersonname;
	}

	public void setFpersonname(String fpersonname) {
		this.fpersonname = fpersonname;
	}

	public String getFpersonemail() {
		return fpersonemail;
	}

	public void setFpersonemail(String fpersonemail) {
		this.fpersonemail = fpersonemail;
	}

	public Map<String, Object> getEditCopypeoMap() {
		return editCopypeoMap;
	}

	public void setEditCopypeoMap(Map<String, Object> editCopypeoMap) {
		this.editCopypeoMap = editCopypeoMap;
	}

	public String getFnumber() {
		return fnumber;
	}

	public void setFnumber(String fnumber) {
		this.fnumber = fnumber;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	private List<OrgManager> list = new ArrayList<OrgManager>();


	
	public OrgManager getOrgManager() {
		return orgManager;
	}

	public void setOrgManager(OrgManager orgManager) {
		this.orgManager = orgManager;
	}

	public List<OrgManager> getList() {
		return list;
	}

	public void setList(List<OrgManager> list) {
		this.list = list;
	}

	/**
	 * 
	 * 描述：查询所有
	 * 2013-4-26 下午03:11:47 by xjw
	 * @version
	 * @return
	 */
	public String allList(){
		list = orgManagerServiceImpl.allHomologousByFnumber();
		return "orglist";
	}
	
	/**
	 * 
	 * 描述：编辑
	 * 2013-4-26 下午03:27:24 by xjw
	 * @version
	 * @return
	 */
	public String edit(){
		fnumber = orgManager.getFnumber(); //获取金蝶库中部门编码
		orgname = OrgManagerQuery.getOrgNameByFnumber(fnumber);  //获取金蝶库中部门名称
		orgManager = orgManagerServiceImpl.getOrgManagerByFnumber(fnumber);
		return "edit";
	}
	
	public void save() throws Exception{
		try {
			fnumber = orgManager.getFnumber(); //获取金蝶库中部门编码
			orgname = OrgManagerQuery.getOrgNameByFnumber(fnumber);  //获取金蝶库中部门名称
			
			String managername = orgManager.getManagername().trim();
			String managermail = orgManager.getManagermail().trim();
			
			String hrbpname = orgManager.getHrbpname().trim();
			String hrbpmail = orgManager.getHrbpmail().trim();
			
			int i = orgManagerServiceImpl.selectCount(fnumber);
			if(i>0){  //该fnumber 编码存在于easext数据库中,更新
	//			if((managername!=null && !"".equals(managername)) || (managermail!=null && !"".equals(managermail))  ){
				if(managername!=null || managermail!=null ){
					int ftype = 2;
					Map<String , Object> obj = orgManagerServiceImpl.gethoms(fnumber);
					if(obj!=null && !obj.isEmpty()){
						int fid = Integer.parseInt(obj.get("FID").toString());
						Homologous homs1 = new Homologous();
						homs1.setFid(fid);
						homs1.setFnumber(fnumber);
						homs1.setFdepartmentname(orgname);
						homs1.setFpersonname(managername);
						homs1.setFpersonemail(managermail);
						homs1.setFtype(ftype);
						orgManagerServiceImpl.update(homs1);
					}
					
				}
				//if((hrbpname!=null && !"".equals(hrbpname)) || (hrbpmail!=null && !"".equals(hrbpmail))  ){
				if(hrbpname!=null || hrbpmail!=null){
					int ftype = 3;
					Map<String , Object> obj = orgManagerServiceImpl.gethomsBp(fnumber);
					if(obj!=null && !obj.isEmpty()){
						int fid = Integer.parseInt(obj.get("FID").toString());
						Homologous homs2 = new Homologous();
						homs2.setFid(fid);
						homs2.setFnumber(fnumber);
						homs2.setFdepartmentname(orgname);
						homs2.setFpersonname(hrbpname);
						homs2.setFpersonemail(hrbpmail);
						homs2.setFtype(ftype);
						orgManagerServiceImpl.update(homs2);
					}else{
	//					int fid = Integer.parseInt(obj.get("FID").toString());
						Homologous homs2 = new Homologous();
	//					homs2.setFid(fid);
						homs2.setFnumber(fnumber);
						homs2.setFdepartmentname(orgname);
						homs2.setFpersonname(hrbpname);
						homs2.setFpersonemail(hrbpmail);
						homs2.setFtype(ftype);
						orgManagerServiceImpl.insert(homs2);
					}
				}
			}else{ //不存在，新增
				if(managername!=null || managermail!=null ){
					Map<String , Object> obj = orgManagerServiceImpl.gethoms(fnumber);
					if(obj==null){
						int ftype = 2;
						Homologous homs1 = new Homologous();
						homs1.setFnumber(fnumber);
						homs1.setFdepartmentname(orgname);
						homs1.setFpersonname(managername);
						homs1.setFpersonemail(managermail);
						homs1.setFtype(ftype);
						orgManagerServiceImpl.insert(homs1);
					}
				}
				if(hrbpname!=null || hrbpmail!=null){
					Map<String , Object> obj = orgManagerServiceImpl.gethomsBp(fnumber);
					if(obj==null){
						int ftype = 3;
						Homologous homs2 = new Homologous();
						homs2.setFnumber(fnumber);
						homs2.setFdepartmentname(orgname);
						homs2.setFpersonname(hrbpname);
						homs2.setFpersonemail(hrbpmail);
						homs2.setFtype(ftype);
						orgManagerServiceImpl.insert(homs2);
					}
				}
			}
			this.json = "{\"success\":\"true\"}";
		} catch (Exception e) {
			e.printStackTrace();	
			this.json = "{\"success\":\"false\"}";
		}finally{
			this.writerJsonToClient(this.json);
		}
	}
	
	public String queryAll(){
		listorg = OrgAdminMapperQuery.selectAllOrgAdmin();
		request.setAttribute("listorg", listorg); 
		
		return "queryAll";
	}
	public String queryPageJson() throws Exception {
		this.pagination = orgManagerServiceImpl.queryPage(pagination);
	  // 将new出来的 分页对象 付给 Action的属性对象里
		return "queryPageJson";
	}
	/**
	 * 
	 * 描述：查询抄送人信息列表
	 * 2014-5-26 下午01:30:48 by zhangxin
	 * @version
	 * @return
	 * @throws Exception
	 */
	public String querycopypeopleJson() throws Exception{
		this.pagination = orgManagerServiceImpl.querycopypeoplePage(pagination);
	    return "querycopypeopleJson";
	}
	
	/**
	 * 
	 * 描述：删除抄送人信息
	 * 2014-5-26 下午02:56:37 by zhangxin
	 * @version
	 */
	public void deleteCopyPeo(){
		orgManagerServiceImpl.deleteCopyPeo();
	}
	/**
	 * 
	 * 描述：编辑抄送人信息
	 * 2014-5-26 下午04:02:25 by zhangxin
	 * @version
	 * @return
	 */
	public String editCopyPeo(){
		//获取fid
		String id = request.getParameter("fid");
		if(null != id){
			Integer fid =Integer.parseInt(id);
			editCopypeoMap = orgManagerServiceImpl.selectInfobyFid(fid);
		}
		return "editCopyPeo";
	}
	
	/**
	 * 
	 * 描述：保存修改抄送人信息
	 * 2014-5-26 下午05:40:51 by zhangxin
	 * @version
	 * @throws Exception
	 */
	public void updateCopyPeo() throws Exception{
		try {
			editCopypeoMap = new HashMap<String, Object>();
			editCopypeoMap.put("FID",Integer.parseInt(fid));
			editCopypeoMap.put("FPERSONNAME", fpersonname);
			editCopypeoMap.put("FPERSONEMAIL", fpersonemail);
			orgManagerServiceImpl.updateCopyPeo(editCopypeoMap);
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
	 * 描述：添加抄送人信息
	 * 2014-5-26 下午05:42:03 by zhangxin
	 * @version
	 * @throws Exception
	 */
	public void insertCopyPeo() throws Exception{
		try {
			editCopypeoMap = new HashMap<String, Object>();
			editCopypeoMap.put("FPERSONNAME", fpersonname);
			editCopypeoMap.put("FPERSONEMAIL", fpersonemail);
			orgManagerServiceImpl.insertCopyPeo(editCopypeoMap);
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
	 * 描述：判断抄送人邮箱是否重复
	 * 2014-5-27 下午05:08:17 by zhangxin
	 * @version
	 * @throws Exception
	 */
	public void ifEmailHasExists() throws Exception{
		boolean exist = false;
		PrintWriter pw = null;
		try {
			exist = orgManagerServiceImpl.ifEmailHasExists();
			pw = response.getWriter();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			pw.print(exist);
			pw.close();
		}
	}
	
	
}
