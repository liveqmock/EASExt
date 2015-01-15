package com.creditease.eas.adminipurc.service;
/**
 * 行政采购合同预警业务处理层接口
 * @IAdminiPurcMailService.java
 * created at 2014-5-20 下午04:25:11 by zhangxin
 * 
 * @author zhangxin({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public interface IAdminiPurcMailService {

	/**
	 * 
	 * 描述：将邮件内容写入文件中
	 * 2014-6-7 下午02:34:56 by zhangxin
	 * @version
	 * @param mailinfo
	 * @return 返回文件路径
	 */
	public String writeMailInfo(String mailinfo);
	/**
	 * 
	 * 描述：提前28天给接口人发送合同到期邮件提醒
	 * 2014-5-22 上午11:41:11 by zhangxin
	 * @version
	 * @return String 内容字符串
	 */
	public void purcPortMailInfo();
	/**
	 * 
	 * 描述：28天提醒后再次提醒为到期前每周一发送邮件提醒
	 * 2014-7-17 下午01:28:38 by zhangxin
	 * @version
	 */
	public void purcMondayMailInfo();
	/**
	 * 
	 * 描述：每周四汇总本周的提醒邮件发送给采购组
	 * 2014-6-7 下午01:25:01 by zhangxin
	 * @version
	 */
	public void totalPurcPortMailInfo();
	/**
	 * 
	 * 描述：每周四总接口人收到本部门的邮件提醒汇总邮件
	 * 2014-8-18 下午02:13:50 by zhangxin
	 * @version
	 */
	public void orgTotalMailToPort();
}
