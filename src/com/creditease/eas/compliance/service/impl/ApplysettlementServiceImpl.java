/**
 * 
 */
package com.creditease.eas.compliance.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditease.eas.compliance.bean.Applysettlement;
import com.creditease.eas.compliance.bean.RelInicasetype;
import com.creditease.eas.compliance.dao.ApplysettlementMapper;
import com.creditease.eas.compliance.dao.RelInicasetypeMapper;
import com.creditease.eas.compliance.service.ApplysettlementService;
import com.creditease.eas.util.PageUtil;
import com.creditease.eas.util.Pagination;
import com.creditease.eas.util.StringUtil;
/**
 * @ApplysettlementServiceImpl.java	合规（申请结案记录service实现类）
 * created at 2013-10-8 上午10:25:44 by caoyong
 * 
 * @author caoyong({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
@Service
public class ApplysettlementServiceImpl implements ApplysettlementService {
	/** 合规（申请结案记录DAO） **/
	@Autowired
	private ApplysettlementMapper applysettlementMapper;
	/** 关联的案件最终归类中间表（DAO） **/
	@Autowired
	private RelInicasetypeMapper relInicasetypeMapper;;
	/** 合规（申请结案记录Entity）**/
	private Applysettlement applysettlement;

	public int insert(Applysettlement applysettlement) throws Exception {
		return applysettlementMapper.insert(applysettlement);
	}

	public int delete(Integer id) throws Exception {
		return applysettlementMapper.deleteByPrimaryKey(id);
	}
	public int update(Applysettlement applysettlement) throws Exception {
		return applysettlementMapper.updateByPrimaryKey(applysettlement);
	}
	public Applysettlement getApplysettlementById(Integer id) throws Exception {
		applysettlement = applysettlementMapper.selectByPrimaryKey(id);
		return applysettlement;
	}
	@SuppressWarnings("unchecked")
	public Pagination queryPage(Pagination page) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		int currentPage = PageUtil.strToPage(request.getParameter("page"));
		int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
//		Map map = HashMap.class.newInstance();
//		map.put("orgName", request.getParameter("orgName"));
		//查询总行数的方法
		int totalCounts = applysettlementMapper.getTotalCounts();
		page = new Pagination(currentPage, pageSize, totalCounts);
		// 查询数据集的方法
		Map map1 = PageUtil.getMap(page);
//		map1.putAll(map);
		List list = applysettlementMapper.queryPage(map1);
		page.setRows(list);
		return page;
	}
	@SuppressWarnings("unchecked")
	public List<Map> getCfClassifyIds() {
		return applysettlementMapper.getCfClassifyIds();
	}

	@SuppressWarnings("unchecked")
	public List<Map> getOutofLineIds() {
		return applysettlementMapper.getOutofLineIds();
	}

	@SuppressWarnings("unchecked")
	public List<Map> getOutofLineLevelIds() {
		return applysettlementMapper.getOutofLineLevelIds();
	}
	public Applysettlement getByInvestigationId(Integer investigationId)
			throws Exception {
		return applysettlementMapper.getByInvestigationId(investigationId);
	}

	public void insertIntoRelInitype(int applysettlementId, String[] initypes)
			throws Exception {
		if(initypes!=null && initypes.length>0){
			for(String initypeId:initypes){
				RelInicasetype relInicasetype = new RelInicasetype();
				relInicasetype.setApplysettlementid(applysettlementId);
				relInicasetype.setInicasetypeid(Integer.parseInt(initypeId));
				relInicasetypeMapper.insert(relInicasetype);
			}
		}
	}
	public void deleteRelInitype(int applysettlementId) throws Exception {
		relInicasetypeMapper.deleteByApplysettlementId(applysettlementId);
	}
	@SuppressWarnings("unchecked")
	public List<Map> getRelInitypes(int applysettlementId) throws Exception {
		return applysettlementMapper.getRelInitypes(applysettlementId);
	}
	@SuppressWarnings("unchecked")
	public List<Map> getSeClasssifys() {
		return applysettlementMapper.getSeClasssifys();
	}
	
	public void insertIntoRelInitype(int applysettlementId,HttpServletRequest request,HttpSession session ) throws Exception{
		String[] finicasetypes = request.getParameterValues("finicasetype");
		if(null != finicasetypes){
			for(int i=0;i<finicasetypes.length;i++){
//				String fdetailCaseType = request.getParameter("fdetailCaseType" + (i+1));
				//关键的逻辑问题：详细分类的每个selected都会被提交
				//所以要根据初步分类来确定应该提交哪个详细的分类的信息
				String fdetailCaseType = request.getParameter("fdetailCaseType" + finicasetypes[i]);
				//String[] fdetailCaseType = request.getParameterValues("fdetailCaseType" + finicasetypes[i]);
				RelInicasetype relInicasetype = new RelInicasetype();
				relInicasetype.setApplysettlementid(applysettlementId);//案件信息
				relInicasetype.setInicasetypeid(StringUtil.strToInt(finicasetypes[i]));//添加案件初步分类信息
				relInicasetype.setDetailcasetypeid(StringUtil.strToInt(fdetailCaseType));//添加案件初步分类信息
				relInicasetypeMapper.insert(relInicasetype);
			}
		}
		String[] newfinicasetype= request.getParameterValues("newfinicasetype");
		if(newfinicasetype!=null){
			for (int i = 0; i < newfinicasetype.length; i++) {
				if(newfinicasetype[i].equals("27")){
					RelInicasetype relInicasetype = new RelInicasetype();
					relInicasetype.setApplysettlementid(applysettlementId);//案件信息
					relInicasetype.setInicasetypeid(StringUtil.strToInt(newfinicasetype[i]));//添加案件初步分类信息
					relInicasetypeMapper.insert(relInicasetype);
				}else{
					String[] newfinicasetypes= request.getParameterValues("fnewdetailtypeid"+newfinicasetype[i]);
					if(newfinicasetypes!=null&&newfinicasetypes.length>0){
						for (int j = 0; j < newfinicasetypes.length; j++) {
							RelInicasetype relInicasetype = new RelInicasetype();
							relInicasetype.setApplysettlementid(applysettlementId);//案件信息
							relInicasetype.setInicasetypeid(StringUtil.strToInt(newfinicasetype[i]));//添加案件初步分类信息
							relInicasetype.setDetailcasetypeid(StringUtil.strToInt(newfinicasetypes[j]));//添加案件初步分类信息
							relInicasetypeMapper.insert(relInicasetype);
						}
					}else{
						RelInicasetype relInicasetype = new RelInicasetype();
						relInicasetype.setApplysettlementid(applysettlementId);//案件信息
						relInicasetype.setInicasetypeid(StringUtil.strToInt(newfinicasetype[i]));//添加案件初步分类信息
						relInicasetype.setDetailcasetypeid(null);//添加案件初步分类信息
						relInicasetypeMapper.insert(relInicasetype);
					}
				}
				
			}
		}
	}

	@Override
	public List<RelInicasetype> getRelInicasetypesByApplysettlementId(Integer id) {
		return applysettlementMapper.getRelInicasetypesByApplysettlementId(id);
	}
}
