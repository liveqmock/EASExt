package com.creditease.eas.warn.action;
 

 
import java.util.List;
import java.util.Map;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.creditease.eas.util.BaseAction; 
import com.creditease.eas.warn.bean.OrgAdmin;
import com.creditease.eas.warn.bean.OrgAdminCh;
import com.creditease.eas.warn.kingdee.query.OrgAdminMapperQuery;
import com.creditease.eas.warn.service.OrgAdminChService;
import com.creditease.eas.warn.service.OrgAdminService;

@Controller
@Scope("prototype")
public class OrgAdminAction extends BaseAction  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
 
	@Autowired  
	private OrgAdminChService orgadminchServiceImpl;
	private OrgAdminCh orgadminch = new OrgAdminCh();
	
	@Autowired
	private OrgAdminService orgAdminServiceImpl;
	
	private OrgAdmin orgAdmin = new OrgAdmin();
	
	
	
	public OrgAdminService getOrgAdminServiceImpl() {
		return orgAdminServiceImpl;
	}
	public void setOrgAdminServiceImpl(OrgAdminService orgAdminServiceImpl) {
		this.orgAdminServiceImpl = orgAdminServiceImpl;
	}
	public OrgAdmin getOrgAdmin() {
		return orgAdmin;
	}
	public void setOrgAdmin(OrgAdmin orgAdmin) {
		this.orgAdmin = orgAdmin;
	}
	public OrgAdminCh getOrgadminch() {
		return orgadminch;
	}
	public void setOrgadminch(OrgAdminCh orgadminch) {
		this.orgadminch = orgadminch;
	}
	private List<Map<String,Object>> listorg  ;
	
	/**
	 * 
	 * 描述：查询所有行政组织
	 * 2013-4-3 下午05:50:39 by guominggao
	 * @version
	 * @return
	 */
	public String queryAllOrgadmin (){ 
		listorg = OrgAdminMapperQuery.selectAllOrgAdmin();
		request.setAttribute("listorg", listorg); 
		return "chooseorg";
		
	}
	public String add() throws Exception{  
		int result = 0 ;
		
		String number = request.getParameter("listadminnumber");
		String name = request.getParameter("listadminname");
		String ext1 = request.getParameter("listadminfid");
		if(null==number||"".equals(number)){
//			return "chooseorg";
			orgadminchServiceImpl.deleteAll();
			return "result";
		}
		if(null==name||"".equals(name)){
//			return "chooseorg";
			orgadminchServiceImpl.deleteAll();
			return "result";
		}
//		number = number.substring(0, number.length()-1);
//		name = name.substring(0, name.length()-1);
//		ext1 = ext1.substring(0, ext1.length()-1);

		String[] numbers = number.split(",");
		String[] names = name.split(",");
		String[] ext1s = ext1.split(",");
		orgadminchServiceImpl.deleteAll();
		for (int i = 0; i < numbers.length; i++) {
			orgadminch.setFnumber(numbers[i]);
			orgadminch.setFname(names[i]);
			orgadminch.setExt1(ext1s[i]);
			result = orgadminchServiceImpl.insert(orgadminch);
		}  
	    
		request.setAttribute("result", result);
		
		return "result";
	}
	
	public String queryPageJson() throws Exception {
		int counts = orgadminchServiceImpl.queryCount();
		if(counts==0){
			this.pagination = orgAdminServiceImpl.queryPage(pagination);
			System.out.println("ccccccccccccccccnnnnnnnnnn");
		}else{
			this.pagination = orgadminchServiceImpl.queryPage(pagination);
		}
	  // 将new出来的 分页对象 付给 Action的属性对象里
		return "queryPageJson";
	}
	
	public String queryPageJsonBack() throws Exception{
		return "queryPageJsonBack";
	}
	
}
