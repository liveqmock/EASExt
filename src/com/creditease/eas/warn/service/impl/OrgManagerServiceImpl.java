/**
 * 
 */
package com.creditease.eas.warn.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.creditease.eas.util.PageUtil;
import com.creditease.eas.util.Pagination;
import com.creditease.eas.util.Utils;
import com.creditease.eas.warn.bean.Homologous;
import com.creditease.eas.warn.bean.OrgManager;
import com.creditease.eas.warn.dao.OrgManagerMapper;
import com.creditease.eas.warn.kingdee.query.OrgManagerQuery;
import com.creditease.eas.warn.service.OrgManagerService;

/**
 * 
 * @OrgAdminChServiceImpl.java
 * created at 2013-4-8 下午04:11:30 by guominggao
 * 
 * @author guominggao({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
@Service("orgManagerService")
public class OrgManagerServiceImpl implements OrgManagerService{
	@Autowired
	private OrgManagerMapper orgManagerMapper;

	private OrgManager orgManager;
	//编码
	public  static String fnumber;
	
	
//	@Autowired //(spring 方式配置mybatis可以)
//	private OrgAdminMapper orgAdminMapper;
	
	public Pagination queryPage(Pagination page) {
		HttpServletRequest request= ServletActionContext.getRequest();
		int currentPage = PageUtil.strToPage(request.getParameter("page"));
		int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
		//查询总行数的方法
		int totalCounts = orgManagerMapper.getTotalCounts();
		page = new Pagination(currentPage,pageSize,totalCounts); 
		//查询数据集的方法
		Map map2 = PageUtil.getMap(page);
		List list = orgManagerMapper.queryPage(map2);
		page.setRows(list);
		return page;
	}
	/**
	 * 
	 * 描述：查询抄送人信息列表页面
	 * 2014-5-26 下午01:42:17 by zhangxin
	 * @version
	 * @param page
	 * @return
	 */
	@SuppressWarnings({ "unused", "unchecked" })
	public Pagination querycopypeoplePage(Pagination page){
		HttpServletRequest request= ServletActionContext.getRequest();
		
		int currentPage = PageUtil.strToPage(request.getParameter("page"));
		int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
		Map mapInfo = new HashMap();
		fnumber = request.getParameter("fnumber");
		mapInfo.put("fnumber", fnumber);
		mapInfo.put("copypeoplename", request.getParameter("copypeoplename"));
		mapInfo.put("copypeoplemail", request.getParameter("copypeoplemail"));
		
		//查询抄送人总行数
		int totalCounts = orgManagerMapper.selectCopyPeoCounts(mapInfo);
		page = new Pagination(currentPage,pageSize,totalCounts);
		//查询抄送人数据集
		Map mapPageInfo = PageUtil.getMap(page);
		mapPageInfo.putAll(mapInfo);
		List list = orgManagerMapper.queryCopyPeoPage(mapPageInfo);
		page.setRows(list);
		return page;
	}
	/**
	 * 
	 * 描述：删除抄送人信息
	 * 2014-5-26 下午01:42:17 by zhangxin
	 * @version
	 * @param page
	 * @return
	 */
	@Override
	public void deleteCopyPeo() {
		HttpServletRequest request= ServletActionContext.getRequest();
		int fid = Integer.parseInt(request.getParameter("fid"));
		orgManagerMapper.deleteByPrimaryKey(fid);
	}
	/**
	 * 
	 * 描述：根据fid查询抄送人信息
	 * 2014-5-26 下午04:58:54 by zhangxin
	 * @version
	 * @param fid
	 * @return 信息集合
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> selectInfobyFid(int fid) {
		return orgManagerMapper.selectInfobyFid(fid);
	}
	/**
	 * 
	 * 描述：插入抄送人信息
	 * 2014-5-26 下午05:47:46 by zhangxin
	 * @version
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public int insertCopyPeo(Map map) {
		int fid = orgManagerMapper.nextSeq();
		map.put("FID", fid);
		map.put("FNUMBER", fnumber);
		map.put("FTYPE", "5");//5-类型为抄送人
		return orgManagerMapper.insertCopyPeo(map);
	}
	/**
	 * 
	 * 描述：保存修改的抄送人信息
	 * 2014-5-26 下午05:44:31 by zhangxin
	 * @version
	 * @param map
	 */
	@Override
	public int updateCopyPeo(Map map) {
		return orgManagerMapper.updateCopyPeo(map);
	}
	
	/**
	 * 
	 * 描述：查询抄送人邮箱是否存在
	 * 2014-5-27 上午10:38:00 by zhangxin
	 * @version
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean ifEmailHasExists() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("tableName", "T_HR_HOMOLOGOUS");
		map.put("columnName", "FPERSONEMAIL");
		map.put("columnValue", request.getParameter("columnValue"));
		map.put("fnumber", fnumber);
		return orgManagerMapper.findEmailExists(map) == 0 ? false : true;
	}
	
	
	/**
	 * 根据部门编码判断信息是否存在
	 */
	public int selectCount(String fnumber){
		
		return orgManagerMapper.selectCount(fnumber);
	}
	
	/**
	 * 获取所有
	 */
	public List<OrgManager> allHomologousByFnumber(){
		List<OrgManager> list = new ArrayList<OrgManager>();
		Map<String,String> cemap = OrgManagerQuery.getAllOrgManager();
		for(Entry<String, String> it:cemap.entrySet()){
//			System.out.println(it.getKey()+" : " +it.getValue());
			String fnumber = it.getKey();
			Map<String, Object> map = orgManagerMapper.getHomologousByFnumber(fnumber);
			OrgManager org = new OrgManager();
//			org.setFnumber(map.get("FNUMBER").toString());
//			org.setOrgname(map.get("ORGNAME").toString());    
			org.setFnumber(it.getKey());
			org.setOrgname(it.getValue());
			
			if(null != map && !map.isEmpty()){
				org.setManagername((map.get("MANAGERNAME")==null)?"":map.get("MANAGERNAME").toString());
				org.setManagermail((map.get("MANAGERMAIL")==null)?"":map.get("MANAGERMAIL").toString());
				org.setHrbpname((map.get("HRBPNAME")==null)?"":map.get("HRBPNAME").toString());
				org.setHrbpmail((map.get("HRBPMAIL")==null)?"":map.get("HRBPMAIL").toString());
			}else{
				org.setManagername("");
				org.setManagermail("");
				org.setHrbpname("");
				org.setHrbpmail("");
			}
			list.add(org);
		}
		return list;
	}
	
	public OrgManager getOrgManagerByFnumber(String fnumber){
		Map<String, Object> map = orgManagerMapper.getHomologousByFnumber(fnumber);
		OrgManager org = new OrgManager();
		org.setFnumber(fnumber);
//		org.setOrgname(map.get("ORGNAME").toString());
		if(null != map && !map.isEmpty()){
			org.setManagername((map.get("MANAGERNAME")==null)?"":map.get("MANAGERNAME").toString());
			org.setManagermail((map.get("MANAGERMAIL")==null)?"":map.get("MANAGERMAIL").toString());
			org.setHrbpname((map.get("HRBPNAME")==null)?"":map.get("HRBPNAME").toString());
			org.setHrbpmail((map.get("HRBPMAIL")==null)?"":map.get("HRBPMAIL").toString());
		}else{
			org.setManagername("");
			org.setManagermail("");
			org.setHrbpname("");
			org.setHrbpmail("");
		}
		return org;
	}
	/**
	 * 获取homs表中fid
	 */
	public Map<String, Object> gethoms(String fnumber){
		return orgManagerMapper.getHoms(fnumber);
	}
	
	public Map<String, Object> gethomsBp(String fnumber){
		return orgManagerMapper.getHomsBp(fnumber);
	}
	
	public void insert(Homologous homs){
		orgManagerMapper.insertHoms(homs);
	}
	
	public void update(Homologous homs){
		orgManagerMapper.updateHoms(homs);
	}
	
	public static void main(String[] args) throws Exception {
		ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
		OrgManagerService service = (OrgManagerService) app.getBean("orgManagerService");
		List<OrgManager> list = service.allHomologousByFnumber();
		for(OrgManager org : list){
			System.out.println(org.getFnumber()+":"+org.getOrgname()+":"+org.getManagername()+":"+org.getHrbpname());
		}
	}
	
	
 
}
