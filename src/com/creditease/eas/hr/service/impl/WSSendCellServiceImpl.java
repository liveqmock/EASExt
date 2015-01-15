package com.creditease.eas.hr.service.impl;

import com.creditease.eas.hr.service.IWSSendCellService;
import com.creditease.eas.util.sendcell.ClientSmsSend;
import com.creditease.eas.util.sendcell.SendCellUtil;
import com.google.gson.Gson;

public class WSSendCellServiceImpl implements IWSSendCellService{
	//发送短信，内网平台需要
	private ClientSmsSend clientSmsSend = new ClientSmsSend();
	@Override
	public String sendCell(String fname,String fnumber,String fmail,String fkey,String fdescription,String fmobilePhone) {
		
		return SendCellUtil.sendCellUtil(fname, fnumber, fmail, fkey, fdescription, fmobilePhone);
	}

	/* (non-Javadoc)
	 * @see com.creditease.eas.hr.service.IWSSendCellService#oneMsgSend(java.lang.String, java.lang.String[], java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String oneMsgSend(String phone,String keyword1,String keyword2,String batchid,
			String orgNo, String modType) {
		String keywords[]=new String[2];
		if(null != keyword1 && !"".equals(keyword1)){
			keywords[0] = keyword1;
		}
		if(null != keyword2 && !"".equals(keyword2)){
			keywords[1] = keyword2;
		}
		return clientSmsSend.oneMsgSend(phone,keywords,batchid,orgNo,modType);
	}

	/* (non-Javadoc)
	 * @see com.creditease.eas.hr.service.IWSSendCellService#sameMessageManyPhoneSend(java.lang.String, java.lang.String[], java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String sameMessageManyPhoneSend(String phone, String keyword1,String keyword2,
			String batchid, String orgNo, String modType) {
		String keywords[]=new String[2];
		if(null != keyword1 && !"".equals(keyword1)){
			keywords[0] = keyword1;
		}
		if(null != keyword2 && !"".equals(keyword2)){
			keywords[1] = keyword2;
		}
		return clientSmsSend.sameMessageManyPhoneSend(phone,keywords,batchid,orgNo,modType);
	}

}
