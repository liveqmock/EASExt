package com.creditease.eas.warn.service.impl;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.creditease.eas.util.PageConst;
import com.creditease.eas.util.PageUtil;
import com.creditease.eas.util.Pagination;
import com.creditease.eas.util.StringUtil;
import com.creditease.eas.util.TongJi;
import com.creditease.eas.util.Utils;
import com.creditease.eas.util.page.PageBean;
import com.creditease.eas.warn.bean.Jumbosmsv;
import com.creditease.eas.warn.bean.SendWay;
import com.creditease.eas.warn.bean.WaringDetail;
import com.creditease.eas.warn.dao.JumbosmsvMapper;
import com.creditease.eas.warn.dao.SendWayMapper;
import com.creditease.eas.warn.dao.WaringDetailMapper;
import com.creditease.eas.warn.dao.WaringDetailViewMapper;
import com.creditease.eas.warn.dao.WaringTypeMapper;
import com.creditease.eas.warn.service.WaringdetailService;
import com.creditease.eas.warn.vo.QueryData;
import com.creditease.eas.warn.vo.QueryParameters;
import com.creditease.service.ws.DetailsJaxb;
import com.creditease.service.ws.MessageReqJaxb;
import com.creditease.service.ws.MessageResJaxb;
import com.creditease.service.ws.MessageService;
import com.creditease.service.ws.MessageServiceJaxbImplService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
@Service
public class WaringdetailServiceImpl implements WaringdetailService {
	private static final Logger logger = Logger.getLogger(WaringdetailServiceImpl.class);
	
	@Autowired
	private WaringDetailMapper waringdetailMapper;
	@Autowired
	private SendWayMapper sendWayMapper;
	@Autowired
	private WaringTypeMapper waringtypeMapper;
	@Autowired
	private WaringDetailViewMapper waringDetailViewMapper;
	@Autowired
	private JumbosmsvMapper jumbosmsvMapper;
	
	public static MessageService getService() {
		MessageServiceJaxbImplService service = new MessageServiceJaxbImplService();
		return service.getMessageServicePort();
	}
	
	@Override
	public PageBean queryPage(PageBean page) {
		int totalCounts = waringdetailMapper.getTotalCounts();
		page = new PageBean(PageConst.PAGESIZE,page.getCurPage(),totalCounts);
		Map map = PageUtil.getMap(page);
		List<WaringDetail> list = waringdetailMapper.queryPage(map);
		page.setResult(list);
		return page;
	}
	@SuppressWarnings("unchecked")
	@Override
	public PageBean queryPage(PageBean page,WaringDetail detail) {
		Map map = new HashMap();
		map.put("typeid",detail.getTypeid());
		map.put("wayid",detail.getWayid());
		int totalCounts = waringDetailViewMapper.getTotalCountsByParams(map);
		page = new PageBean(page.getPageSize(),page.getCurPage(),totalCounts);
		map = PageUtil.getMap(page);
		map.put("typeid",detail.getTypeid());
		map.put("wayid",detail.getWayid());
		List<WaringDetail> list = waringDetailViewMapper.queryPageByParams(map);
		System.out.println("list:::::" + list.size());
		page.setResult(list);
		return page;
	}
	/**
	 * 
	 * 描述：设置公共的参数
	 * 2012-12-31 下午06:08:52 by ygq
	 * @version
	 * @param request
	 * @param map
	 * @return
	 */
	private Map setMapValue(HttpServletRequest request,Map map){
		Date begin = StringUtil.strToDate(request.getParameter("begin"));
		Date end = StringUtil.strToDate(request.getParameter("end"));
		String receiver = StringUtil.addLikeOperBoth(request.getParameter("receiver"));//添加
		String departname =  StringUtil.addLikeOperBoth(request.getParameter("departname"));
		String postname = request.getParameter("postname");
		String theme = StringUtil.addLikeOperBoth(request.getParameter("theme"));
		String typeid = request.getParameter("typeid");
		String wayid = request.getParameter("wayid");
		String issend = request.getParameter("issend");
		map.put("begin", begin);
		map.put("end", end);
		map.put("receiver", receiver);
		map.put("departname", departname);
		map.put("postname", postname);
		map.put("theme", theme);
		map.put("typeid",typeid);
		map.put("wayid",wayid);
		map.put("issend",issend);
		return map;
	}
	@SuppressWarnings("unchecked")
	@Override
	public Pagination queryPage(Pagination page) {
		HttpServletRequest request= ServletActionContext.getRequest();
		int currentPage = PageUtil.strToPage(request.getParameter("page"));
		int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
		//查询总行数的方法
		Map map = new HashMap();
		map = setMapValue(request, map);
		int totalCounts = waringDetailViewMapper.getTotalCountsByParams(map);
		page = new Pagination(currentPage,pageSize,totalCounts); 
		//查询数据集的方法
		Map map2 = PageUtil.getMap(page);
		map2 = setMapValue(request,map2);
		List list = waringDetailViewMapper.queryPageByParams(map2);
		page.setRows(list);
		return page;
	}
	/**
	 * 现在的查询
	 */
	public String queryPageJson(PageBean page,WaringDetail detail) {
		Map map = new HashMap();
		map.put("typeid",detail.getTypeid());
		map.put("wayid",detail.getWayid());
		int totalCounts = waringdetailMapper.getTotalCountsByParams(map);
		page = new PageBean(page.getPageSize(),page.getCurPage(),totalCounts);
		map = PageUtil.getMap(page);
		map.put("typeid",detail.getTypeid());
		map.put("wayid",detail.getWayid());
		List<WaringDetail> list = waringdetailMapper.queryPageByParams(map);
		Gson g = new GsonBuilder().create();
		String json = g.toJson(list);
		return json;
	}
	@Override
	public int insert() throws Exception {
		SendWay sendway = new SendWay();
		sendway.setWaycode("yang");
		sendway.setWaycode("1232");
		sendway.setCreatime(new Date());
		sendWayMapper.insert(sendway);
		return 1;
	}
	/**
	 * 
	 * 描述：发送短信 邮件
	 * 2013-1-16 下午03:20:37 by xjw
	 * @version
	 * @param list
	 */
	public void sendCell(String content,MessageReqJaxb req,DetailsJaxb[] ds,DetailsJaxb d,String[] keywords,String theme,QueryData qd,Jumbosmsv jumb){
		try{
//			QueryData qd=(QueryData) list.get(i);
			theme = "祝"+qd.getName()+"生日快乐";
			qd.setWayid(1);
			qd.setTypeid(1);
			qd.setTheme(theme);
			qd.setSendtime(new Timestamp(System.currentTimeMillis()));
			if(jumb!=null && jumb.getContent()!=null){
				content = jumb.getContent();
				if(qd.getName()!=null){
					content = content.replaceAll("\\$\\{name\\}", qd.getName());
					content = content.replaceAll("\\$\\{birthday\\}",Utils.getDate(qd.getBirthday()));
					int years = Utils.getTime().getYear() - qd.getBirthday().getYear();
					content = content.replaceAll("\\$\\{age\\}",Integer.toString(years));
				}
			}else{
				content = "烛光、蛋糕、美酒；祝福平安歌，笑语生日贺；母亲养育恩，阖家享欢乐！${name}，宜信全体同仁祝您生日快乐，家人安康、幸福！";
				content = content.replaceAll("\\$\\{name\\}", qd.getName());
			}
			qd.setCellcontent(content);
			keywords[0] = "custName|"+content;
//					d.setMobile("13070189337"); // 电话号码
//					d.setMobile("18611111111"); // 电话号码
//					d.setMobile("13439853524"); // 电话号码
			d.setMobile(Utils.strTrim(qd.getCell())); // 电话号码
			d.setKeywords(keywords);
			d.setPriority("2"); // 优先级
			ds[0] = d;
			req.setOrgNo("2267");// 组织机构号
			req.setBatchId("20120426");// 批次号
//					req.setTypeNo("5179");// 模板号
			req.setTypeNo("5360");// 模板号
			req.setVersion("2.00");// 接口版本
			req.setDetails(ds);
			MessageResJaxb res = getService().orderMsgSendJaxb(req);
			System.err.println(res.getRetInfo());	
			waringdetailMapper.insertWaringDetail(qd);
			TongJi.tongJiCount ++;
		}catch (Exception ex) {
			ex.printStackTrace();
			TongJi.tongJiExceptionCount ++;
			logger.error(ex.getMessage());
		}
	}
	
	public void sendCellTest(String content,MessageReqJaxb req,DetailsJaxb[] ds,DetailsJaxb d,String[] keywords,String theme,QueryData qd,Jumbosmsv jumb,String strs){
		try{
			
//			QueryData qd=(QueryData) list.get(i);
			theme = "祝"+qd.getName()+"生日快乐";
			qd.setWayid(1);
			qd.setTypeid(1);
			qd.setTheme(theme);
			qd.setSendtime(new Timestamp(System.currentTimeMillis()));
			if(jumb!=null && jumb.getContent()!=null){
				content = jumb.getContent();
				if(qd.getName()!=null){
					content = content.replaceAll("\\$\\{name\\}", qd.getName());
					content = content.replaceAll("\\$\\{birthday\\}",Utils.getDate(qd.getBirthday()));
					int years = Utils.getTime().getYear() - qd.getBirthday().getYear();
					content = content.replaceAll("\\$\\{age\\}",Integer.toString(years));
				}
			}else{
				content = "烛光、蛋糕、美酒；祝福平安歌，笑语生日贺；母亲养育恩，阖家享欢乐！${name}，宜信全体同仁祝您生日快乐，家人安康、幸福！";
				content = content.replaceAll("\\$\\{name\\}", qd.getName());
			}
			
			qd.setCellcontent(content);
			keywords[0] = "custName|"+content;
//					d.setMobile("13070189337"); // 电话号码
//					d.setMobile("18611111111"); // 电话号码
//					d.setMobile("13439853524"); // 电话号码
			
			
			d.setMobile("18610775451"); // 电话号码
//			d.setMobile(strs); // 电话号码
			d.setKeywords(keywords);
			d.setPriority("2"); // 优先级
			ds[0] = d;
			req.setOrgNo("2267");// 组织机构号
			req.setBatchId("20120426");// 批次号
//					req.setTypeNo("5179");// 模板号
			req.setTypeNo("5360");// 模板号
			req.setVersion("2.00");// 接口版本
			req.setDetails(ds);
			MessageResJaxb res = getService().orderMsgSendJaxb(req);
//			if("000000".equals(res.getRetCode())){ //发送成功
//				
//			}else{
//				
//			}
			System.err.println(res.getRetInfo());	
			waringdetailMapper.insertWaringDetail(qd);
			TongJi.tongJiCount ++;
		}catch (Exception ex) {
			ex.printStackTrace();
			TongJi.tongJiExceptionCount ++;
			logger.error(ex.getMessage());
		}
	}
	
	@Override
	public String selectAllSendWays() {
		String json = "";
		List<Map<String,Object>> list = sendWayMapper.selectAllSendWays();
		Gson g = new GsonBuilder().create();
		json = g.toJson(list);
		return json;
	}
	@Override
	public String selectAllWaringType() {
		String json = "";
		Gson g = new GsonBuilder().create();
		List<Map<String,Object>> list = waringtypeMapper.selectAllWaringType();
		json = g.toJson(list);
		return json;
	}
	@Override
	public WaringDetail findWaringDetailById(Long id) throws Exception{
		return waringdetailMapper.selectByPrimaryKey(id);
	}
	public List findAll(){
		List list=null;
		list = waringdetailMapper.findAllWaring();
//		for (int i = 0; i < list.size(); i++) {
//			WaringDetail wd = (WaringDetail) list.get(i);
//			System.err.println(wd.getId()+" : "+wd.getDepartname());
//		}
		return list;
	}
	
//	public void sendCell() throws Exception{
//		ClientExample_cxf.testOrderMsgSend();
//	}
	
	public void updateWaringDetail(WaringDetail waringDetail) {
		waringdetailMapper.updateByPrimaryKey(waringDetail);
	}
	
	public static void main(String[] args)  throws Exception{
		ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
		WaringdetailService service = (WaringdetailService)app.getBean("waringdetailServiceImpl");
		long i = 39169;
		WaringDetail waringDetail = service.findWaringDetailById(i);
		System.out.println(waringDetail.getReceiver()+" : "+waringDetail.getEmail());
		waringDetail.setExt3("xxxxxx");
		service.updateWaringDetail(waringDetail);
		
//		service.insertCell();
		//service.findAll();
		
//		WaringdetailServiceImpl ws = new WaringdetailServiceImpl();
//		ws.sendCell();
	}

	@Override
	public void deleteWaringDetailById(Long id) {
		waringdetailMapper.deleteWaringDetailById(id);
	}

	@Override
	public List<WaringDetail> queryAllByTypeIdAndWayIdOnIssend(
			QueryParameters param) throws Exception {
		return waringdetailMapper.selectAllByTypeIdAndWayIdOnIssend(param);
	}
}
