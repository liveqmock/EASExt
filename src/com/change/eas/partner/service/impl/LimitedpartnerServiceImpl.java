package com.change.eas.partner.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.change.eas.kingdee.query.BaseUtilQuery;
import com.change.eas.partner.bean.BaseUnit;
import com.change.eas.partner.bean.DocumentFile;
import com.change.eas.partner.bean.LawFile;
import com.change.eas.partner.bean.Limitedpartner;
import com.change.eas.partner.bean.Partner;
import com.change.eas.partner.dao.LawTemplateMapper;
import com.change.eas.partner.dao.LimitedpartnerMapper;
import com.change.eas.partner.service.LimitedpartnerService;
import com.change.eas.util.Config;
import com.change.eas.util.LimitedpartnerUtil;
import com.change.eas.util.PageView;
import com.creditease.eas.admin.bean.User;
import com.creditease.eas.util.PageUtil;
import com.creditease.eas.util.Pagination;
import com.creditease.eas.util.StringUtil;

@Service
public class LimitedpartnerServiceImpl implements LimitedpartnerService {

	private BaseUtilQuery baseUtilQuery = new BaseUtilQuery();
	@Autowired
	private LimitedpartnerMapper limitedpartnerMapper;
	@Autowired
	private LawTemplateMapper lawTemplateMapper;
	private int orderstatus;

	@SuppressWarnings("unchecked")
	@Override
	public Pagination queryPage(Pagination page) {
		HttpServletRequest request = ServletActionContext.getRequest();
		int currentPage = PageUtil.strToPage(request.getParameter("page"));
		int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
		// 查询总行数的方法
		Map map = new HashMap();
		try {
			map = setMapValue(request, map);
			int totalCounts = limitedpartnerMapper.getTotalCountsByParams(map);
			page = new Pagination(currentPage, pageSize, totalCounts);
			// 查询数据集的方法
			Map map2 = PageUtil.getMap(page);
			map2 = setMapValue(request, map2);
			List list = limitedpartnerMapper.queryPageByParams(map2);
			page.setRows(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}

	@Override
	public Pagination queryPage2(Pagination page, String roleName) {
		HttpServletRequest request = ServletActionContext.getRequest();
		int currentPage = PageUtil.strToPage(request.getParameter("page"));
		int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
		// 查询总行数的方法
		Map map = new HashMap();
		try {
			map = setMapValue(request, map);
			int totalCounts = 0;
			if ("合规部".equals(roleName))
				totalCounts = limitedpartnerMapper.getTotalCountsByParams2(map);
			else
				totalCounts = limitedpartnerMapper.getTotalCountsByParams(map);
			page = new Pagination(currentPage, pageSize, totalCounts);
			// 查询数据集的方法
			Map map2 = PageUtil.getMap(page);
			map2 = setMapValue(request, map2);
			List list = null;
			if ("合规部".equals(roleName))
				list = limitedpartnerMapper.queryPageByParams2(map2);
			else
				list = limitedpartnerMapper.queryPageByParams(map2);
			page.setRows(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}

	public Pagination queryLawFilePage(Pagination page) {
		HttpServletRequest request = ServletActionContext.getRequest();
		int currentPage = PageUtil.strToPage(request.getParameter("page"));
		int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
		// 查询总行数的方法
		Map map = new HashMap();
		try {
			map = setLawFileMapValue(request, map);
			int totalCounts = limitedpartnerMapper.getLawFileTotalCountsByParams(map);
			page = new Pagination(currentPage, pageSize, totalCounts);
			// 查询数据集的方法
			Map map2 = PageUtil.getMap(page);
			map2 = setLawFileMapValue(request, map2);
			List list = limitedpartnerMapper.queryLawFilePageByParams(map2);
			page.setRows(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}

	@Override
	public Pagination queryPagedeatail(Pagination page, Long pid) {
		HttpServletRequest request = ServletActionContext.getRequest();
		int currentPage = PageUtil.strToPage(request.getParameter("page"));
		int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
		try {
			int totalCounts = limitedpartnerMapper.getTotalCountsByPid(pid);
			page = new Pagination(currentPage, pageSize, totalCounts);
			Map map = PageUtil.getMap(page);
			map.put("pid", pid);
			List list = limitedpartnerMapper.queryPartnerPageByParams(map);
			page.setRows(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return page;
	}

	@Override
	public Pagination queryPageDocumentFile(Pagination page, Long pid) {
		HttpServletRequest request = ServletActionContext.getRequest();
		int currentPage = PageUtil.strToPage(request.getParameter("page"));
		int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
		try {
			int totalCounts = limitedpartnerMapper.getDocumentFileTotalCountsByPid(pid);
			page = new Pagination(currentPage, pageSize, totalCounts);
			Map map = PageUtil.getMap(page);
			map.put("pid", pid);
			List list = limitedpartnerMapper.queryDocumentFilePageByParams(map);
			page.setRows(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return page;
	}

	@Override
	public Pagination listLimitedPartnerPageJson(Pagination page, Map params) {
		HttpServletRequest request = ServletActionContext.getRequest();
		int currentPage = PageUtil.strToPage(request.getParameter("page"));
		int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
		try {
			int totalCounts = baseUtilQuery.getTotalBaseUnit(params);
			page = new Pagination(currentPage, pageSize, totalCounts);
			Map map = PageUtil.getMap(page);
			map.put("fname_l2", params.get("fname_l2"));
			List list = baseUtilQuery.queryBaseUnitPageJson(map);
			page.setRows(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return page;
	}

	@Override
	public Pagination addLimitedPartnerPage(Pagination page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Long id) {
		try {
			limitedpartnerMapper.deleteByPrimaryKey(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Partner> selectPartnerListByPID(Long pid) {
		return limitedpartnerMapper.selectPartnerListByPID(pid);
	}

	@Override
	public void deletePartnerById(Long id) {
		limitedpartnerMapper.deletePartnerById(id);

	}

	@Override
	public void insertPartner(Partner partner) {
		limitedpartnerMapper.insertPartner(partner);
	}

	@Override
	public Limitedpartner findLimitedpartnerById(Long id) {
		return limitedpartnerMapper.findLimitedpartnerById(id);
	}

	@Override
	public int getTotalCountsByPid(Long pid) {
		return limitedpartnerMapper.getTotalCountsByPid(pid);
	}

	@Override
	public double getSumAmountByPid(Long pid) {
		return limitedpartnerMapper.getSumAmountByPid(pid);
	}

	public int getOrderstatus() {
		return orderstatus;
	}

	public void setOrderstatus(int orderstatus) {
		this.orderstatus = orderstatus;
	}

	@Override
	public void updateGatherstatus(Long id, String operation, Long userid) {
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		try {
			if (LimitedpartnerUtil.CONST_STATUS_SAVE.equals(operation)) {
				orderstatus = LimitedpartnerUtil.getStatus(LimitedpartnerUtil.CONST_UNDONE);
				map.put("savestatus", LimitedpartnerUtil.getStatus("已暂存"));
				map.put("tabulator_id", userid);
				limitedpartnerMapper.updateSavestatus(map);
			} else if (LimitedpartnerUtil.CONST_STATUS_SUBMIT.equals(operation)) {
				orderstatus = LimitedpartnerUtil.getStatus(LimitedpartnerUtil.CONST_DONE);
				map.put("submitstatus", LimitedpartnerUtil.getStatus("已提交"));
				map.put("tabulator_id", userid);
				limitedpartnerMapper.updateSubmitstatus(map);
			} else if (LimitedpartnerUtil.CONST_STATUS_CHECK.equals(operation)) {
				orderstatus = LimitedpartnerUtil.getStatus(LimitedpartnerUtil.CONST_CHECK);
				map.put("checkstatus", LimitedpartnerUtil.getStatus("已审核通过"));
				map.put("auditor_id", userid);
				limitedpartnerMapper.updateCheckstatus(map);
			} else if (LimitedpartnerUtil.CONST_STATUS_FANCHECK.equals(operation)) {
				orderstatus = LimitedpartnerUtil.getStatus(LimitedpartnerUtil.CONST_DONE);// 反审核使单据状态变为完成
				map.put("checkstatus", LimitedpartnerUtil.getStatus("未审核"));
				map.put("auditor_id", 0);
				limitedpartnerMapper.updateCheckstatus(map);
			}
			params.put("id", id);
			params.put("orderstatus", orderstatus);
		} catch (Exception e) {
			e.printStackTrace();
		}
		limitedpartnerMapper.updateGatherstatus(params);
	}

	@Override
	public User findUserById(Long id) {
		return limitedpartnerMapper.findUserById(id);
	}

	@Override
	public void updateTime(Long pid, String change) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pid", pid);
		params.put("change", change);
		limitedpartnerMapper.updateTime(params);
	}

	@Override
	public List<LawFile> lawfilelist(Map params) {
		// TODO Auto-generated method stub
		return limitedpartnerMapper.lawfilelist(params);
	}

	@Override
	public List<BaseUnit> queryBaseUnit(Map map) {
		return baseUtilQuery.queryBaseUnit(map);
	}

	@Override
	public void insertLimitedpartner(Limitedpartner limitedpartner) {
		// TODO Auto-generated method stub
		limitedpartnerMapper.insertLimitedpartner(limitedpartner);
	}

	@Override
	public Partner selectPartnerById(Long id) {
		// TODO Auto-generated method stub
		return limitedpartnerMapper.selectPartnerById(id);
	}

	@Override
	public void updatePartner(Map params) {
		// TODO Auto-generated method stub
		limitedpartnerMapper.updatePartner(params);
	}

	@Override
	public void updateLimitedPartnerBySupplement(Map params) {
		// TODO Auto-generated method stub
		limitedpartnerMapper.updateLimitedPartnerBySupplement(params);

	}

	@Override
	public void updatePartnerpercentById(Map params) {
		// TODO Auto-generated method stub
		limitedpartnerMapper.updatePartnerpercentById(params);
	}

	@Override
	public List<DocumentFile> queryDocumentFile(PageView view, Long pid) {
		int tit = limitedpartnerMapper.getTitDocumentFile(pid);
		view.setAllRows(tit);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", view.getBeginRow());
		map.put("end", view.getEndRow());
		map.put("pid", pid);
		return limitedpartnerMapper.queryDocumentFile(map);
	}

	@Override
	public void deleteDocumentFile(Map params) {
		// TODO Auto-generated method stub
		limitedpartnerMapper.deleteDocumentFile(params);
	}

	@Override
	public void deleteLawTemplate(Long RecordID) {
		// TODO Auto-generated method stub
		String path = lawTemplateMapper.getTemplateFilePathByID(RecordID.longValue());
		String key = Config.getKey("lawfile.word");
		if (path != null) {
			File file = new File(key, path);

			if (file.isFile() && file.exists()) {
				file.delete();
			}
		}
		limitedpartnerMapper.deleteLawTemplate(RecordID);
	}

	@Override
	public void updateLawFileStatus(Map params) {
		// TODO Auto-generated method stub
		limitedpartnerMapper.updateLawFileStatus(params);
	}

	@Override
	public int getRepeatLimitedpartnerName(Map params) {
		// TODO Auto-generated method stub
		return limitedpartnerMapper.getRepeatLimitedpartnerName(params);
	}

	@Override
	public void updateChangeTime(Map params) {
		limitedpartnerMapper.updateChangeTime(params);
	}

	@SuppressWarnings("unchecked")
	private Map setMapValue(HttpServletRequest request, Map map) throws Exception {
		String name = StringUtil.addLikeOperBoth(request.getParameter("name"));
		int gatherstatus = LimitedpartnerUtil.getStatus(request.getParameter("gatherstatusValue"));
		int lfmakestatus = LimitedpartnerUtil.getStatus(request.getParameter("lfmakestatusValue"));
		int gschangestatus = LimitedpartnerUtil.getStatus(request.getParameter("gschangestatusValue"));
		int taxchangestatus = LimitedpartnerUtil.getStatus(request.getParameter("taxchangestatusValue"));
		map.put("name", name);
		map.put("gatherstatus", gatherstatus);
		map.put("lfmakestatus", lfmakestatus);
		map.put("gschangestatus", gschangestatus);
		map.put("taxchangestatus", taxchangestatus);
		return map;
	}

	@SuppressWarnings("unchecked")
	private Map setLawFileMapValue(HttpServletRequest request, Map map) throws Exception {
		String limitedpartnername = StringUtil.addLikeOperBoth(request.getParameter("limitedpartnername"));
		String department = request.getParameter("department");
		String city = request.getParameter("city");
		map.put("limitedpartnername", limitedpartnername);
		map.put("department", department);
		map.put("city", city);
		return map;
	}

	@Override
	public List<Partner> selectPartnerListByFenyePID(PageView view, Long id) {
		int tit = limitedpartnerMapper.getTitCountByPid(id);
		view.setAllRows(tit);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", view.getBeginRow());
		map.put("end", view.getEndRow());
		map.put("pid", id);
		return limitedpartnerMapper.getPartnerByPid(map);
	}

	@Override
	public int getisexists(Long pid) {
		// TODO Auto-generated method stub
		return limitedpartnerMapper.getisexists(pid);
	}

	@Override
	public void updateLimitedpartnerstatus(Long pid) {
		// TODO Auto-generated method stub
		limitedpartnerMapper.updateLimitedpartnerstatus(pid);
	}

	@Override
	public List<Limitedpartner> selectLimitedpartnerList() {
		// TODO Auto-generated method stub
		return limitedpartnerMapper.selectLimitedpartnerList();
	}

	@Override
	public List<LawFile> lawfilePageList(PageView view, Map<String, Object> params) {
		int tit = limitedpartnerMapper.getTitLawfile(params);
		view.setAllRows(tit);
		params.put("start", view.getBeginRow());
		params.put("end", view.getEndRow());
		return limitedpartnerMapper.getLawfileList(params);
	}

	@Override
	public List<BaseUnit> queryBaseUnitPage(PageView view, Map params) {
		// TODO Auto-generated method stub
		int total = baseUtilQuery.getTotalBaseUnit(params);
		view.setAllRows(total);
		params.put("start", view.getBeginRow());
		params.put("end", view.getEndRow());
		return baseUtilQuery.queryBaseUnitPage(params);
	}

	public String getCityNameByTemplateRecordID(Long recordID) {
		return limitedpartnerMapper.getCityNameByTemplateRecordID(recordID);
	}

	@Override
	public List<Partner> getPartnerAllIDCard(Long pid) {
		// TODO Auto-generated method stub
		return limitedpartnerMapper.getPartnerAllIDCard(pid);
	}

	@Override
	public void insertDocumentFileByCon(DocumentFile file) {
		// TODO Auto-generated method stub
		limitedpartnerMapper.insertDocumentFileByCon(file);
	}

}
