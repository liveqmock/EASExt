//package com.creditease.eas.quartz;
//
//import java.util.List;
//import java.util.Map;
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.creditease.eas.util.StringUtil;
//import com.creditease.eas.util.TongJi;
//import com.creditease.eas.warn.bean.ConfigInfo;
//import com.creditease.eas.warn.bean.Jumbosmsv;
//import com.creditease.eas.warn.dao.JumbosmsvMapper;
//import com.creditease.eas.warn.kingdee.query.WaringDetailQuery;
//import com.creditease.eas.warn.kingdee.query.YearOfWorkQuery;
//import com.creditease.eas.warn.service.BirthdayService;
//import com.creditease.eas.warn.service.ConfigService;
//import com.creditease.eas.warn.service.ContractRenewalService;
//import com.creditease.eas.warn.service.ContractService;
//import com.creditease.eas.warn.service.RegularService;
//import com.creditease.eas.warn.service.WaringdetailService;
//import com.creditease.eas.warn.service.YearOfWorkService;
//import com.creditease.eas.warn.vo.QueryData;
//import com.creditease.service.ws.DetailsJaxb;
//import com.creditease.service.ws.MessageReqJaxb;
///**
// * 这是邮件信息合并之后的最后一个版本
// * @CommonQuartzJobv1.java
// * created at 2013-4-10 下午09:43:55 by ygq
// * 
// * @author ygq({@link authorEmail})
// * @version $Revision$</br>
// * update: $Date$
// */
//public class CommonQuartzJobv1 {
//	private static final Logger logger = Logger.getLogger(CommonQuartzJobv1.class);
//	@Autowired
//	private ContractService contractServiceImpl;
//	
//	@Autowired
//	private YearOfWorkService yearOfWorkServiceImpl;
//
//	@Autowired
//	private RegularService regularServiceImpl;
//	
//	@Autowired
//	private WaringdetailService waringdetailServiceImpl;
//	
//	@Autowired
//	private ContractRenewalService contractRenewalServiceImpl;
//	
//	@Autowired
//	private BirthdayService birthdayServiceImpl;
//	
//	@Autowired
//	private JumbosmsvMapper jumbosmsvMapper;
//	
//	@Autowired
//	private ConfigService configServiceImpl;
//	/**
//	 * 
//	 * 描述：合同到期预警 邮件
//	 * 2013-1-16 下午03:08:27 by xjw
//	 * @version
//	 * @throws Exception
//	 */
//	public void sendContractMail() throws Exception{
//		
//		ConfigInfo configInfo = configServiceImpl.getConfigInfoById(4); //获取期配置信息
//		if(configInfo.getConfigvalue()==0){
//			contractRenewalServiceImpl.sendMailInfos();
//			logger.info(TongJi.a + "\texception\t" + TongJi.tongJiExceptionCount);
//			//初始化缓存变量
//			TongJi.initCount();
//			System.out.println("合同到期预警发送邮件成功!!!" );
//		}else{
//			System.out.println("关闭合同到期预警邮件发送功能");
//		}
//		
//	}
//	/**
//	 * 
//	 * 描述：司龄预警 邮件
//	 * 2013-1-16 下午03:08:10 by xjw
//	 * @version
//	 * @throws Exception
//	 */
//	public void sendYearOfWorkMailTest() throws Exception{
//		ConfigInfo configInfo = configServiceImpl.getConfigInfoById(2);//获取期配置信息
//		if(configInfo.getConfigvalue()==0){
//			List<Map<String,Object>> listPersonsInfo = YearOfWorkQuery.getAllByYear();
//			 if(listPersonsInfo!=null && listPersonsInfo.size()>0){
//				 for(int i=0;i<listPersonsInfo.size();i++){
//					Map<String, Object> map= listPersonsInfo.get(i);
//					yearOfWorkServiceImpl.sendLowMails(map);
//				 }
//			 }
//			logger.info(TongJi.tongJiCount + "\texception\t" + TongJi.tongJiExceptionCount);
//			//初始化缓存变量
//			TongJi.initCount();
//		}else{
//			System.out.println("关闭司龄预警邮件发送功能");
//		}
//	}
//	/**
//	 * 
//	 * 描述：转正预警 邮件
//	 * 2013-1-16 下午03:11:18 by xjw
//	 * @version
//	 * @throws Exception
//	 */
//	public void sendRegularMailTest() throws Exception{
////		ConfigInfo configInfo = configServiceImpl.getConfigInfoById(3);//获取期配置信息
////		if(configInfo.getConfigvalue()==0){
////			Map<String,List<Map<String,Object>>> map =  QualifyingMatureQuery001.queryQualifyPersonsInfo();//查询出来的是人员的信息
////			for(Entry<String,List<Map<String,Object>>> entry : map.entrySet()){
////				regularServiceImpl.sendMailInfos(entry);//处理需要转正的人员的信息
////			}
////			logger.info(TongJi.tongJiCount + "\texception\t" + TongJi.tongJiExceptionCount);
//////			System.out.println("转正预警发送邮件成功!!!" );
////			//初始化缓存变量
////			TongJi.initCount();
////		}else{
////			System.out.println("关闭转正预警邮件发送功能");
////		}
//	}
//	/**
//	 * 
//	 * 描述：生日预警 短信
//	 * 2013-1-16 下午03:27:00 by xjw
//	 * @version
//	 * @throws Exception
//	 */
//	
//	public void sendCell() throws Exception{
//		ConfigInfo configInfo = configServiceImpl.getConfigInfoById(1);//获取期配置信息
//		if(configInfo.getConfigvalue()==0){
//			String content = null;
//			MessageReqJaxb req = new MessageReqJaxb(); // webservice参数
//			DetailsJaxb[] ds = new DetailsJaxb[1]; // 短信内容数组
//			DetailsJaxb d =  new DetailsJaxb();
//			String[] keywords = new String[1];// 模板关键字
//			Jumbosmsv jumb = jumbosmsvMapper.selectByIsuse(1);
//			String theme=null;
//			List list = WaringDetailQuery.getWaringDetailList();
//			if(list!=null){
//				for (int i = 0; i < list.size(); i++) {
//					QueryData qd=(QueryData) list.get(i);
//					waringdetailServiceImpl.sendCell(content, req, ds, d, keywords, theme, qd, jumb);
//				}
//			}
//			logger.info(TongJi.tongJiCount + "\texception\t" + TongJi.tongJiExceptionCount);
//			//初始化缓存变量
//			TongJi.initCount();		
//		}else{
//			System.out.println("关闭生日预警短信发送功能");
//		}
//	}
//	/**
//	 * 
//	 * 描述：生日预警 邮件
//	 * 2013-2-22 上午10:46:59 by xjw
//	 * @version
//	 */
//	public void sendBirthdayImage() throws Exception{
//		ConfigInfo configInfo = configServiceImpl.getConfigInfoById(5);//获取期配置信息
//		if(configInfo.getConfigvalue()==0){
////			System.out.println("启动生日预警邮件发送功能");
//			List<Map<String,Object>> list = WaringDetailQuery.sendImage();
//			if(list!=null && list.size()>0){
//				for (int i = 0; i < list.size(); i++) {
//					Map<String, Object> map= list.get(i);
//					birthdayServiceImpl.sendImages(map);
//				}
//			}
//			logger.info(TongJi.tongJiCount + "\texception\t" + TongJi.tongJiExceptionCount);
//			//初始化缓存变量
//			TongJi.initCount();
//			System.err.println("生日祝福邮件发送成功！！！");
//		}else{
//			System.out.println("关闭生日预警邮件发送功能");
//		}
//		
//	}
//}
