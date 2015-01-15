/**
 * 
 */
package com.creditease.eas.warn.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.creditease.eas.util.PageUtil;
import com.creditease.eas.util.Pagination;
import com.creditease.eas.warn.bean.BlackListInfo;
import com.creditease.eas.warn.bean.Processmode;
import com.creditease.eas.warn.bean.WaringType;
import com.creditease.eas.warn.dao.BlackListInfoMapper;
import com.creditease.eas.warn.service.BlackListInfoService;

/**
 * @JumbosmsvServiceImpl.java
 * created at 2012-12-26 下午04:01:37 by xjw
 * 
 * @author xjw({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
//@Service("blackListInfoServiceImpl")
@Service
public class BlackListInfoServiceImpl implements BlackListInfoService{
	@Autowired
	private BlackListInfoMapper blackListInfoMapper;

	private BlackListInfo blackListInfo;
	
	public Processmode getProcessmode(Integer mid){
		return blackListInfoMapper.getProcessmode(mid);
	}
	
	public WaringType getWaringType(Integer wid){
		return blackListInfoMapper.getWaringType(wid);
	}
	
	public List<Processmode> modeList()
	{
		return blackListInfoMapper.modeList();
	}
	
	public List<WaringType> warntypeList()
	{
		return blackListInfoMapper.warntypeList();
	}

	@Override
	public int insert(BlackListInfo blackListInfo) throws Exception {
		// TODO Auto-generated method stub
		blackListInfoMapper.insertBlackListInfo(blackListInfo);
		return 0;
	}

	public int delete(Integer id) throws Exception {
		int i = blackListInfoMapper.deleteByPrimaryKey(id);
		return i;
	}
	
	public int update(BlackListInfo blackListInfo) throws Exception {
		int i = blackListInfoMapper.updateByPrimaryKey(blackListInfo);
		return i;
	}
	
	public BlackListInfo getBlackListInfoById(Integer id) throws Exception {
		blackListInfo = blackListInfoMapper.selectByPrimaryKey(id);
		return blackListInfo;
	}
	/**
	 * 
	 * 描述：根据人员编码查找人员信息
	 * 2013-7-4 下午01:22:46 by Administrator
	 * @version
	 * @param pnumber
	 * @return
	 * @throws Exception
	 */
	public BlackListInfo selectBlackListInfoByPnumber(String pnumber,int warntype,int modetype) throws Exception {
		Map map = new HashMap<String, Object>();
		map.put("pnumber", pnumber);
		map.put("warntype", warntype);
		map.put("modetype", modetype);
		blackListInfo = blackListInfoMapper.selectByFnumber(map);
		return blackListInfo;
	}
	
	public Pagination queryPage(Pagination page) {
		HttpServletRequest request= ServletActionContext.getRequest();
		int currentPage = PageUtil.strToPage(request.getParameter("page"));
		int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
		//查询总行数的方法
		int totalCounts = blackListInfoMapper.getTotalCounts();
		page = new Pagination(currentPage,pageSize,totalCounts); 
		//查询数据集的方法
		Map map2 = PageUtil.getMap(page);
		List list = blackListInfoMapper.queryPage(map2);
		page.setRows(list);
		return page;
	}


	public static void main(String[] args) throws Exception {
		ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
		BlackListInfoService service = (BlackListInfoService) app.getBean("blackListInfoServiceImpl");
		BlackListInfo b1 = new BlackListInfo();
		b1.setPname("liubei2");
		b1.setPnumber("p00000010");
		b1.setOrgname("综合管理部");
//		service.insert(b1);
//		service.delete(3);
//		System.err.println(service.warntypeList().size());
//		System.err.println(service.modeList().size());
//		System.err.println(service.getProcessmode(1).getProcessmode());
//		System.err.println(service.getWaringType(1).getTypename());
//		System.err.println(service.selectBlackListInfoByPnumber("201210290296",3,1).getPname());
		System.err.println(service.selectBlackListInfoByPnumber("201210290296",1,3).getPname());
	}
}
