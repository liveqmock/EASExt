package com.creditease.eas.warn.action;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.creditease.eas.util.BaseAction;
import com.creditease.eas.util.FileReadUtil;
import com.creditease.eas.util.TongJi;
import com.creditease.eas.util.Utils;
import com.creditease.eas.util.mail.MailSenderInfo;
import com.creditease.eas.util.mail.SendMailUtil;
import com.creditease.eas.warn.bean.Jumbosmsv;
import com.creditease.eas.warn.bean.WaringDetail;
import com.creditease.eas.warn.dao.JumbosmsvMapper;
import com.creditease.eas.warn.kingdee.query.WaringDetailQuery;
import com.creditease.eas.warn.service.OrgAdminChService;
import com.creditease.eas.warn.service.WaringdetailService;
import com.creditease.eas.warn.vo.QueryData;
import com.creditease.eas.warn.vo.QueryParameters;
import com.creditease.service.ws.DetailsJaxb;
import com.creditease.service.ws.MessageReqJaxb;
@Controller
@Scope("prototype")
public class WaringDetailAction  extends BaseAction{
	private static final Logger logger = Logger.getLogger(WaringDetailAction.class);
	
	@Autowired
	private WaringdetailService waringdetailServiceImpl;
	private WaringDetail waringDetail;
	private List<WaringDetail> waringDetails;
	private QueryParameters queryParam;
	@Autowired
	private JumbosmsvMapper jumbosmsvMapper;
	
	@Autowired
	private OrgAdminChService orgAdminChServiceImpl;
	
	
	public List<WaringDetail> getWaringDetails() {
		return waringDetails;
	}
	public void setWaringDetails(List<WaringDetail> waringDetails) {
		this.waringDetails = waringDetails;
	}
	public WaringDetail getWaringDetail() {
		return waringDetail;
	}
	public void setWaringDetail(WaringDetail waringDetail) {
		this.waringDetail = waringDetail;
	}
	
	public QueryParameters getQueryParam() {
		return queryParam;
	}
	public void setQueryParam(QueryParameters queryParam) {
		this.queryParam = queryParam;
	}
	/**
	 * 
	 * 描述：返回Json格式的List
	 * 2012-12-30 下午08:20:21 by ygq
	 * @version
	 * @return
	 * @throws Exception
	 */
	public String queryPageJson() throws Exception {
		this.pagination = waringdetailServiceImpl.queryPage(pagination);
	  // 将new出来的 分页对象 付给 Action的属性对象里
		return "queryPageJson";
	}
	/**
	 * 
	 * 描述：查询数据，并且回显数据:只是跳转和重新加载信息的时候用
	 * 2012-12-31 上午11:19:13 by ygq
	 * @version
	 * @return
	 * @throws Exception
	 */
	public String queryPageJsonBack() throws Exception{
		return "queryPageJsonBack";
	}
	
	public String insert() throws Exception{
		waringdetailServiceImpl.insert();
		return "queryWarn";
	}
	/**
	 * 
	 * 描述：返回json格式的字符串
	 * 2012-12-28 下午05:07:23 by ygq
	 * @version
	 * @return
	 */
	public void selectAllSendWays() throws Exception{
		json = waringdetailServiceImpl.selectAllSendWays();
		writerJsonToClient(json);
	}
	/**
	 * 描述：查询所有的预警类型
	 * 2012-12-29 下午03:59:38 by ygq
	 * @version
	 * @throws Exception
	 */
	public void selectAllWaringType() throws Exception{
		json = waringdetailServiceImpl.selectAllWaringType();
		writerJsonToClient(json);
	}
	/**
	 * 查询明细信息
	 * @return
	 * @throws Exception
	 */
	public String queryDetail() throws Exception{
			waringDetail = waringdetailServiceImpl.findWaringDetailById(waringDetail.getId());
			if(waringDetail.getContentaddress().contains("txt")){
				//String filePath = request.getRealPath("/");
				MailSenderInfo mailInfo = new MailSenderInfo();
				String filePath = mailInfo.getProperties().getProperty("filepath");
				filePath +=  waringDetail.getContentaddress();
				//System.out.println("filePath:::" + filePath);
				String htmlContent = FileReadUtil.getUserFile(filePath);
				String[] html = htmlContent.split("<img width=251 height=55 src='cid:logo.png'>");
				request.setAttribute("htmlContent", html);
				return "detail";
			}else{
				return "imagecell";
			}
	}
	/**
	 * 
	 * 描述：短信明细信息
	 * 2013-1-6 下午05:42:16 by xjw
	 * @version
	 * @return
	 * @throws Exception
	 */
	public String queryDetailCell() throws Exception{
		waringDetail = waringdetailServiceImpl.findWaringDetailById(waringDetail.getId());
//		if(waringDetail.getWayid()==1){
//			return "celldetail";	
//		}else{
//			return "imagecell";
//		}
		return "celldetail";	
	}
	/**
	 * 
	 * 描述：发送短信的action
	 * 2013-1-6 下午06:05:43 by ygq
	 * @version
	 * @return
	 * @throws Exception
	 */
	public String cellSend() throws Exception{
//		waringdetailServiceImpl.insertCellTest();
		String content = null;
		MessageReqJaxb req = new MessageReqJaxb(); // webservice参数
		DetailsJaxb[] ds = new DetailsJaxb[1]; // 短信内容数组
		DetailsJaxb d =  new DetailsJaxb();
		String[] keywords = new String[1];// 模板关键字
		Jumbosmsv jumb = jumbosmsvMapper.selectByIsuse(1);
		String theme=null;
		List<String> numlist = orgAdminChServiceImpl.allOrgAdmin();
		System.out.println("部门编码个数："+numlist.size());
		List list = WaringDetailQuery.getWaringDetailList(numlist);
		if(list!=null){
			for (int i = 0; i < list.size(); i++) {
				QueryData qd=(QueryData) list.get(i);
				String strs=null;
				if(i<5){
					strs = "18610775451";
				}else if(i>=5 && i<=9){
					strs = "18610775451aa";
				}
				else if(i>9 && i<=19){
					strs = "13070189337   ";
				}else{
					strs = "1307018933723";
				}
				strs=Utils.toTrim(strs);
				
				waringdetailServiceImpl.sendCellTest(content, req, ds, d, keywords, theme, qd, jumb,strs);
			}
		}
		System.out.println("生日祝福发送短信成功!!!" );
		logger.info("统计\t" + TongJi.tongJiCount + "\texception\t" + TongJi.tongJiExceptionCount);
		//初始化缓存变量
		TongJi.initCount();
		return "sendCellsuccess";
	}
	/**
	 * 
	 * 描述：查看司龄明细
	 * 2013-1-15 上午10:32:26 by xjw
	 * @version
	 * @return
	 * @throws Exception
	 */
	public String yearOfWorkDetail() throws Exception{
		waringDetail = waringdetailServiceImpl.findWaringDetailById(waringDetail.getId());
//		if(waringDetail.getCopyids()!=null && !"".equals(waringDetail.getCopyids())){
		if(waringDetail.getContentaddress().contains("txt")){
			MailSenderInfo mailInfo = new MailSenderInfo();
		    String filePath = mailInfo.getProperties().getProperty("filepath");
			filePath +=  waringDetail.getContentaddress();
			//System.out.println("filePath:::" + filePath);
			String htmlContent = FileReadUtil.getUserFile(filePath);
			String[] html = htmlContent.split("<img width=251 height=55 src='cid:logo.png'>");
			request.setAttribute("htmlContent", html);
			return "detail";
		}else{
			return "yjdetail";
		}
		
	}
	/***
	 * 
	 * 描述：处理图片输出
	 * 2013-1-15 上午10:32:52 by xjw
	 * @version.
	 */
	public void test(){
	  HttpServletResponse response=ServletActionContext.getResponse();
	  response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        try {
        	getRandcode(request, response,waringDetail.getId());//输出图片方法
        } catch (Exception e) {
	        e.printStackTrace();
        }
	}
	
	
	
	/***
	 * 
	 * 描述：生成图片 
	 * 2013-1-26 下午01:14:58 by xjw
	 * @version
	 * @param request
	 * @param response
	 * @param id
	 * @throws Exception
	 */
    public void getRandcode(HttpServletRequest request,HttpServletResponse response,Long id) throws Exception{
        waringDetail = waringdetailServiceImpl.findWaringDetailById(id);
        File files = new File(waringDetail.getContentaddress());
        BufferedImage image = ImageIO.read(files);
        ImageIO.write(image, "JPEG", response.getOutputStream());//将内存中的图片通过流动形式输出到客户端
    }
    
    
	public String imageCellDetail() throws Exception{
		waringDetail = waringdetailServiceImpl.findWaringDetailById(waringDetail.getId());
		return "imagecell";
	}
	
    /**
	 * 
	 * 描述：读取短信邮件图片
	 * 2013-2-25 上午11:41:34 by xjw
	 * @version
	 */
	public void imageCell(){
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        try {
        	MailSenderInfo ms = new MailSenderInfo();
			Properties pr = ms.getProperties();
			String birthdayImage = pr.getProperty("birthdayImage");
			File files = new File(birthdayImage);
			BufferedImage image = ImageIO.read(files);
		        ImageIO.write(image, "JPEG", response.getOutputStream());//将内存中的图片通过流动形式输出到客户端
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	/**
	 * 
	 * 描述：查询数据，并且回显数据:只是跳转和重新加载信息的时候用
	 * 2012-12-31 上午11:19:13 by ygq
	 * @version
	 * @return
	 * @throws Exception
	 */
	public String queryPageJsonBackDelete() throws Exception{
		return "queryPageJsonBackDelete";
	}
	/***
	 * 描述：单条删除预警明细
	 * 2013-3-7 上午11:08:54 by ygq
	 * @version
	 * @return
	 * @throws Exception
	 */
	public String deleteWaringDetailById() throws Exception{
		waringdetailServiceImpl.deleteWaringDetailById(waringDetail.getId());
		return null;
	}
	/***
	 * 描述：批量删除预警明细
	 * 2013-3-7 上午11:08:54 by ygq
	 * @version
	 * @return
	 * @throws Exception
	 */
	public String deleteWaringDetailAll() throws Exception{
		String ids = request.getParameter("ids");
		if(ids != null || "".equals(ids)){
			String[] sts = ids.split(",");
			for(int i=0;i<sts.length;i++){
				Long id = Long.parseLong(sts[i]);
				waringdetailServiceImpl.deleteWaringDetailById(id);
			}
		}
		return null;
	}
	/**
	 * 
	 * 描述：重发邮件
	 * 2013-5-28 下午06:24:24 by Administrator
	 * @version
	 * @return
	 * @throws Exception 
	 */
	public String retryMail() throws Exception{
		waringDetail = waringdetailServiceImpl.findWaringDetailById(waringDetail.getId());
		waringDetail.setSendcount(waringDetail.getSendcount()+1);
		waringDetail.setSendtime(new Timestamp(System.currentTimeMillis()));
		MailSenderInfo ms = new MailSenderInfo();
		Properties pr = ms.getProperties();
		String fromAddress = pr.getProperty("fromAddress");
		if(waringDetail.getContentaddress().contains("jpg")){
			String toAddress = waringDetail.getEmail();
//			toAddress = "tracy_0201@163.com";
			String title = waringDetail.getTheme() + "(此邮件为系统发送，请勿回复)补发";
			String birthdayImage = waringDetail.getContentaddress();
			boolean va = SendMailUtil.sendImageMail(fromAddress,toAddress,title,birthdayImage);
			if(va){
				waringDetail.setIssend(1);//成功了
				waringDetail.setExt1("成功");
			}else{
				waringDetail.setIssend(0);//失败了
				waringDetail.setExt1("失败");
			}
			waringdetailServiceImpl.updateWaringDetail(waringDetail);
		}else{
			String[] toAddress = waringDetail.getEmail().split(",");
//			toAddress = pr.getProperty("toAddress").split(",");
			String title = waringDetail.getTheme() + "(此邮件为系统发送，请勿回复)补发";
			String htmlContent = pr.getProperty("filepath")+waringDetail.getContentaddress();//文件路径
			
			String myContent = FileReadUtil.readFile(htmlContent);//读文件
			System.out.println("myContent\t" + myContent);
			String logoPath = pr.getProperty("logoPath");
			String tmp = waringDetail.getCopyids();
			System.out.println("抄送人:"+tmp);
			String[] addcopys = null;
			if(null!=tmp && !tmp.contains("@")){
				tmp = waringDetail.getCopyperson();
				addcopys =  tmp.split(",");
			}
//			addcopys = waringDetail.getCopyids().split(",");
			if(null != waringDetail.getCopyids() && !"".equals(waringDetail.getCopyids())){
				addcopys = waringDetail.getCopyids().split(",");
			}
//			addcopys = pr.getProperty("ccs").split(",");
			boolean va = SendMailUtil.sendMailToManyPersonAddLogo(fromAddress, toAddress, title, myContent, logoPath,addcopys);
			if(va){
				waringDetail.setIssend(1);//成功了
				waringDetail.setExt1("成功");
			}else{
				waringDetail.setIssend(0);//失败了
				waringDetail.setExt1("失败");
			}
			waringdetailServiceImpl.updateWaringDetail(waringDetail);
		}
		return "queryPageJsonBack";
	}
	
	
	public String retryMailTest() throws Exception{
		waringDetail = waringdetailServiceImpl.findWaringDetailById(waringDetail.getId());
		waringDetail.setSendcount(waringDetail.getSendcount()+1);
		waringDetail.setSendtime(new Timestamp(System.currentTimeMillis()));
		MailSenderInfo ms = new MailSenderInfo();
		Properties pr = ms.getProperties();
		String fromAddress = pr.getProperty("fromAddress");
		if(waringDetail.getContentaddress().contains("jpg")){
			String toAddress = waringDetail.getEmail();
			toAddress = "tracy_0201@163.com";
			String title = waringDetail.getTheme();
			String birthdayImage = waringDetail.getContentaddress();
			boolean va = SendMailUtil.sendImageMail(fromAddress,toAddress,title,birthdayImage);
			if(va){
				waringDetail.setIssend(1);//成功了
				waringDetail.setExt1("成功");
			}else{
				waringDetail.setIssend(0);//失败了
				waringDetail.setExt1("失败");
			}
			waringdetailServiceImpl.updateWaringDetail(waringDetail);
		}else{
			String[] toAddress = waringDetail.getEmail().split(",");
			toAddress = pr.getProperty("toAddress").split(",");
			String title = waringDetail.getTheme();
			String htmlContent = pr.getProperty("filepath")+waringDetail.getContentaddress();//文件路径
			
			String myContent = FileReadUtil.readFile(htmlContent);//读文件
			System.out.println("myContent\t" + myContent);
			String logoPath = pr.getProperty("logoPath");
			String tmp = waringDetail.getCopyids();
			System.out.println("抄送人:"+tmp);
			String[] addcopys = null;
			if(null!=tmp && !tmp.contains("@")){
				tmp = waringDetail.getCopyperson();
				addcopys =  tmp.split(",");
			}
			addcopys = waringDetail.getCopyids().split(",");
			addcopys = pr.getProperty("ccs").split(",");
			boolean va = SendMailUtil.sendMailToManyPersonAddLogo(fromAddress, toAddress, title, myContent, logoPath,addcopys);
			if(va){
				waringDetail.setIssend(1);//成功了
				waringDetail.setExt1("成功");
			}else{
				waringDetail.setIssend(0);//失败了
				waringDetail.setExt1("失败");
			}
			waringdetailServiceImpl.updateWaringDetail(waringDetail);
		}
		return "queryPageJsonBack";
	}
	/**
	 * 异步查询预警失败重发信息数量
	 * @author lining
	 */
	public void countResendMsgByAjax(){
		try {
			int count = 0;
			waringDetails = this.waringdetailServiceImpl.queryAllByTypeIdAndWayIdOnIssend(queryParam);
			count = waringDetails.size();
			System.out.println("{count:\""+count+"\"}");
			this.writerJsonToClient("{count:\""+count+"\"}");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 批量重发预警失败信息
	 * @author lining
	 */
//	public void resendMsgsForFail(){
//		System.out.println("开始重发。。。");
//		try {
//			waringDetails = this.waringdetailServiceImpl.queryAllByTypeIdAndWayIdOnIssend(queryParam);
////			for(WaringDetail detail:waringDetails){
////				System.out.println(detail.getCell() + "\t" + detail.getSendtime());
////			}
////			System.out.println("size\t" + waringDetails.size());
//			for(WaringDetail detail:waringDetails){
//				retryMailMethod(detail);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println("成功了。。。。。");
//	}
	
	private void retryMailMethod(WaringDetail waringDetail)throws Exception{ 
		waringDetail = waringdetailServiceImpl.findWaringDetailById(waringDetail.getId());
		waringDetail.setSendcount(waringDetail.getSendcount()+1);
		waringDetail.setSendtime(new Timestamp(System.currentTimeMillis()));
		MailSenderInfo ms = new MailSenderInfo();
		Properties pr = ms.getProperties();
		String fromAddress = pr.getProperty("fromAddress");
		if(waringDetail.getContentaddress().contains("jpg")){
			String toAddress = waringDetail.getEmail();
//			toAddress = "tracy_0201@163.com";
			String title = waringDetail.getTheme() + "(此邮件为系统发送，请勿回复)补发";
			String birthdayImage = waringDetail.getContentaddress();
			boolean va = SendMailUtil.sendImageMail(fromAddress,toAddress,title,birthdayImage);
			if(va){
				waringDetail.setIssend(1);//成功了
				waringDetail.setExt1("成功");
			}else{
				waringDetail.setIssend(0);//失败了
				waringDetail.setExt1("失败");
			}
			waringdetailServiceImpl.updateWaringDetail(waringDetail);
		}else{
			String[] toAddress = waringDetail.getEmail().split(",");
//			toAddress = pr.getProperty("toAddress").split(",");
			String title = waringDetail.getTheme() + "(此邮件为系统发送，请勿回复)补发";
			String htmlContent = pr.getProperty("filepath")+waringDetail.getContentaddress();//文件路径
			
			String myContent = FileReadUtil.readFile(htmlContent);//读文件
			System.out.println("myContent\t" + myContent);
			String logoPath = pr.getProperty("logoPath");
			String tmp = waringDetail.getCopyids();
			System.out.println("抄送人:"+tmp);
			String[] addcopys = null;
			if(null!=tmp && !tmp.contains("@")){
				tmp = waringDetail.getCopyperson();
				addcopys =  tmp.split(",");
			}
			if(null != waringDetail.getCopyids() && !"".equals(waringDetail.getCopyids())){
				addcopys = waringDetail.getCopyids().split(",");
			}
//			addcopys = pr.getProperty("ccs").split(",");
			boolean va = SendMailUtil.sendMailToManyPersonAddLogo(fromAddress, toAddress, title, myContent, logoPath,addcopys);
			if(va){
				waringDetail.setIssend(1);//成功了
				waringDetail.setExt1("成功");
			}else{
				waringDetail.setIssend(0);//失败了
				waringDetail.setExt1("失败");
			}
			waringdetailServiceImpl.updateWaringDetail(waringDetail);
		}
	}
	
//	public static void main(String[] args) {
//		try{
//			ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//			WaringDetailAction waringDetailAction = (WaringDetailAction)context.getBean("waringDetailAction");
//			QueryParameters parameter = new QueryParameters();
//			parameter.setBeginTime("2013-01-01");
//			parameter.setTypeId(1);
//			parameter.setWayId(2);
//			waringDetailAction.setQueryParam(parameter);
//			waringDetailAction.countResendMsgByAjax();
//		}catch(Exception ex){
//			ex.printStackTrace();
//		}
//	}
}
