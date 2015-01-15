package com.creditease.eas.warn.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;


import com.creditease.eas.util.Pagination;
import com.creditease.eas.util.page.PageBean;
import com.creditease.eas.warn.bean.Jumbosmsv;
import com.creditease.eas.warn.bean.WaringDetail;
import com.creditease.eas.warn.vo.QueryData;
import com.creditease.eas.warn.vo.QueryParameters;
import com.creditease.service.ws.DetailsJaxb;
import com.creditease.service.ws.MessageReqJaxb;
public interface WaringdetailService{
	public PageBean queryPage(PageBean page);
	/**
	 * 
	 * 描述：带参数的查询方法
	 * 2012-12-29 下午04:22:35 by ygq
	 * @version
	 * @param page
	 * @param detail
	 * @return
	 */
	public PageBean queryPage(PageBean page,WaringDetail detail);
	
	public Pagination queryPage(Pagination page);
	/**
	 * 
	 * 描述：转换成json对象
	 * 2012-12-30 下午03:27:03 by ygq
	 * @version
	 * @param page
	 * @param detail
	 * @return
	 */
	public String queryPageJson(PageBean page,WaringDetail detail);
	
	public int insert() throws Exception;
    /**
     * 
     * 描述：查询所有的发送方式
     * 2012-12-28 下午05:00:52 by ygq
     * @version
     * @return
     */
	public String selectAllSendWays();
	/**
	 * 描述：查询所有的预警类型
	 * 2012-12-29 下午03:57:10 by ygq
	 * @version
	 * @return
	 */
	String selectAllWaringType();

	
	public List findAll();

	/**
	 * 查询明细信息通过id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public WaringDetail findWaringDetailById(Long id) throws Exception;
	public void sendCell(String content,MessageReqJaxb req,DetailsJaxb[] ds,DetailsJaxb d,String[] keywords,String theme,QueryData qd,Jumbosmsv jumb);
	
	public void sendCellTest(String content,MessageReqJaxb req,DetailsJaxb[] ds,DetailsJaxb d,String[] keywords,String theme,QueryData qd,Jumbosmsv jumb,String strs);
	
	public void updateWaringDetail(WaringDetail waringDetail);
	
	/**
	 * 描述：根据id删除预警明细
	 * 2013-3-7 上午11:11:25 by ygq
	 * @version
	 */
	public void deleteWaringDetailById(Long id);
	/**
	 * 根据预警类型和预警方式查询所有预警信息发送失败的记录
	 * @param param 预警类型和预警方式 
	 * @return 信息发送失败的记录ID
	 * @throws Exception
	 * @author lining
	 */
	public List<WaringDetail> queryAllByTypeIdAndWayIdOnIssend(QueryParameters paramMap) throws Exception;
}
