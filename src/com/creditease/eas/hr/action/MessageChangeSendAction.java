package com.creditease.eas.hr.action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.creditease.eas.hr.webService.IEmpChangeSendService;
import com.creditease.eas.hr.webService.IOrganizationChangeSendService;
import com.creditease.eas.hr.webService.IPositionChangeSendService;
import com.creditease.eas.util.BaseAction;
import com.creditease.eas.util.StringUtil;
import com.creditease.eas.util.consts.AddressConfig;
@Controller
@Scope("prototype")
public class MessageChangeSendAction extends BaseAction{
	@Autowired
	private IOrganizationChangeSendService iorganizationChangeSendServiceImpl;
	@Autowired
	private IPositionChangeSendService positionChangeSendServiceImpl;
	@Autowired
	private IEmpChangeSendService impChangeSendServiceImpl;
	/**
	 * 描述：测试组织推送
	 * 2013-4-7 下午12:26:04 by ygq
	 * @version
	 */
	public String organizationChangeSend(){
		AddressConfig.ORGCST = StringUtil.objToInteger(request.getParameter("orgid"));
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");
		String fnumber = request.getParameter("fnumber");//编码
		iorganizationChangeSendServiceImpl.queryOrganizationChangeFromHRToOAHand(beginTime,endTime,fnumber);
		System.out.println("MessageChangeSendAction.organizationChangeSend()");
		return "organizationChange";
	}
	/**
	 * 描述：测试职位推送
	 * 2013-4-7 下午12:26:04 by ygq
	 * @version
	 */
	public String positionChangeSend(){
		AddressConfig.POSITIONCST = StringUtil.objToInteger(request.getParameter("position"));
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");
		String fnumber = request.getParameter("fnumber");//编码
		positionChangeSendServiceImpl.queryPositionInfoChangeFromHRToOAHand(beginTime,endTime,fnumber);
		System.out.println("MessageChangeSendAction.organizationChangeSend()");
		return "organizationChange";
	}
	/**
	 * 描述：测试人员推送
	 * 2013-4-7 下午12:26:04 by ygq
	 * @version
	 */
	public String empChangeSend(){
		AddressConfig.EMPCST = StringUtil.objToInteger(request.getParameter("emp"));
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");
		impChangeSendServiceImpl.queryEmpInfoChangeFromHRToSMPAndOAHand(beginTime,endTime);
		return "organizationChange";
	}
}
