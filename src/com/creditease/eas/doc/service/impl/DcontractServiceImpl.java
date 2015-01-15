package com.creditease.eas.doc.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditease.eas.admin.dao.ExtDataBaseMapper;
import com.creditease.eas.doc.bean.Dcontract;
import com.creditease.eas.doc.dao.DcontractMapper;
import com.creditease.eas.doc.service.DcontractService;
import com.creditease.eas.util.DateUtil;
import com.creditease.eas.util.PageUtil;
import com.creditease.eas.util.Pagination;
import com.creditease.eas.util.StringUtil;
import com.creditease.eas.util.Utils;
import com.creditease.eas.util.mail.MailSenderInfo;
import com.creditease.eas.util.mail.SendMailUtil;
import com.creditease.eas.util.mail.SendMailUtilNew;
import com.creditease.eas.util.port.ImportExecl;

@Service
public class DcontractServiceImpl implements DcontractService {

	private static final long serialVersionUID = 1L;
	@Autowired
	private DcontractMapper IdcontractMapper;
	@Autowired
	private ExtDataBaseMapper extDataBaseMapper;
	@SuppressWarnings("unchecked")
	@Override
	public Pagination queryPage(Pagination page,String status) {
		HttpServletRequest request = ServletActionContext.getRequest();
		int currentPage = PageUtil.strToPage(request.getParameter("page"));
		int pageSize = PageUtil.strToPageSize(request.getParameter("rows"));
		// 查询总行数的方法
		Map map = new HashMap();
		map = setMapValue(request, map);
		int totalCounts=0;
		if("1".equals(status)){
			totalCounts=IdcontractMapper.getTotalCountByParams(map);
		}else{
		 totalCounts = IdcontractMapper.getTotalCountsByParams(map);
		}
		page = new Pagination(currentPage, pageSize, totalCounts);
		// 查询数据集的方法
		Map map2 = PageUtil.getMap(page);
		map2 = setMapValue(request, map2);
		List list=null;
        if("1".equals(status)){
        list=IdcontractMapper.queryPageHByParams(map2);
		}else{
		 list = IdcontractMapper.queryPageByParams(map2);
		}
		page.setRows(list);
		return page;
	}
	@SuppressWarnings("unchecked")
	private Map setMapValue(HttpServletRequest request, Map map) {
		Date begin = StringUtil.strToDate(request.getParameter("begin"),"yyyy-MM-dd");
		Date end = StringUtil.strToDate(request.getParameter("end"),"yyyy-MM-dd");
		String fcontractname = StringUtil.addLikeOperBoth(request
				.getParameter("fcontractname"));// 添加
		map.put("begin", begin);
		map.put("end", end);
		map.put("fcontractname", fcontractname);
		return map;
	}
	/**
	 * 新增合同信息
	 */
	@Override
	public void insert(Dcontract dcontract) {
		IdcontractMapper.insertDcontract(dcontract);
	}

	/**
	 * 合同信息
	 */
	@Override
	public Dcontract getRentContractById(int id) {
		Dcontract dcontract = IdcontractMapper.getContractById(id);
		return dcontract;
	}

	/**
	 * 根据合同序号查询信息
	 */
	@Override
	public Dcontract getContract(String fcontractnum) {
		Dcontract dcontract = IdcontractMapper.getContract(fcontractnum);
		return dcontract;
	}

	/**
	 * 修改合同信息
	 */
	@Override
	public void update(Dcontract dcontract) {
		IdcontractMapper.updateContract(dcontract);

	}

	// 定时合同到期前一个月10:00,14:00,17:00发送邮件
	public void time() {
		List<Map<String, Object>> list = IdcontractMapper.dtime();
		for (Map<String, Object> map : list) {
			String str = map.get("FCONTRACTDATE").toString();// 合同到期日期
			String fcontractnum = map.get("FCONTRACTNUM").toString();
			
			if(fcontractnum.indexOf(".")!=-1)
				fcontractnum=fcontractnum.substring(0,fcontractnum.indexOf("."))+";";//截取.0
			
			SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
			String str1 = simple.format(DateUtil.getDateAfterMonth(Utils
					.toDate(str), -1));// 提前一个月
			String fcontractname = map.get("FCONTRACTNAME").toString();// 合同名称
			MailSenderInfo ms = new MailSenderInfo();// 邮件发送消息bean
			Properties pr = ms.getProperties();
			String fromAddress = pr.getProperty("DOC_USERNAME");// 发邮件人
			String password=pr.getProperty("DOC_PASSWORD");
			boolean va = false;
			String[] toAddress = map.get("FMAILBOX").toString().split(";");
			if(pr.getProperty("to_local_Address") != null && "yes".equals(pr.getProperty("to_local_Address"))){
				toAddress = pr.getProperty("toAddress").split(",");//代码正式提交时邮件接收人为接口人邮箱，此行注释掉
			}
			if (str1.equals(Utils.getCurrentDate())) {
				va = SendMailUtilNew
						.sendMailToManyPerson(
								fromAddress,
								password,
								toAddress,
								"合同到期提醒",
								"请注意！ 编号： "
										+ fcontractnum
										+ "&nbsp;&nbsp;&nbsp;&nbsp;<<"
										+ fcontractname
										+ ">>&nbsp;&nbsp;&nbsp;&nbsp; 距离到期只有30天，请及时处理！请重视，谢谢！");
			}
		}
	}

	/**
	 * 删除合同信息
	 */
	@Override
	public void deleteDcontract(String id) {
		IdcontractMapper.deleteDcontract(id);
	}

	/**
	 * 导入合同信息 描述： 2013-9-11 上午09:43:18 by admin
	 * 
	 * @version
	 * @throws Exception 
	 * @throws Exception
	 */
	@Override
	public String importExcel(String nn, String username,File excelFile) throws Exception {
		String message = "";
		String json = "";
		String temp[] = nn.replaceAll("\\\\",",").split(",");
		String str = temp[temp.length-1];
		String directory = "/upload/port";  
        String targetDirectory = ServletActionContext.getServletContext().getRealPath(directory);  
        //生成上传的文件对象  
        File target = new File(targetDirectory,str);  
        //如果文件已经存在，则删除原有文件  
        if(target.exists()){  
            target.delete();  
        }  
	
			  //复制file对象，实现上传  
		    FileUtils.copyFile(excelFile, target);  
		   
			ImportExecl poi = new ImportExecl();
			// //房屋合同信息源数据
			List<List<String>> list2 = poi.read(target.getPath(),0);
			if (list2.size() == 0)
				message += "请检查房屋合同信息汇总sheet格式或数据</br>";
			int status = 0;
			String string = "";
			String fcontractnum = "";
			List<Map<String, Object>> list = IdcontractMapper.getDcontractList();
			for (Map<String, Object> map : list) {
				  fcontractnum += map.get("FCONTRACTNUM").toString() + ";";//把数据库中所有的合同编号拼成一个字符串
			}
			for (int i = 1; i < list2.size(); i++) {
				int count = 0;
				List<String> cellList = list2.get(i);
				for (int j = 0; j < cellList.size(); j++) {//判断合同序号 ,合同/协议名称  合同到期日期  ,接口人邮箱是否为空
					
					if (cellList.get(0).equals("")
							|| cellList.get(1).equals("")
							|| cellList.get(5).equals("")
							|| cellList.get(6).equals("")) {
						status = 1;
					}
				}
				if(cellList.get(0)!=""){
				  if (fcontractnum.indexOf(cellList.get(0)) == -1) {//判断该是否有重复值
				   	fcontractnum += cellList.get(0) + ";";
					 count = 1;
				  }

				  if (count != 1) {
					 if(cellList.get(0).indexOf(".")!=-1)
				    	string+=cellList.get(0).substring(0,cellList.get(0).indexOf("."))+";";//截取.0
				     else
					string += cellList.get(0) + ";";
					status = 2;
				  }
				}
			 }
			System.out.println(string);
			if (status == 1) {
				message = "合同序号 ,合同/协议名称  <br/>"
						+ "合同到期日期  ,接口人邮箱<br/>"
						+ " &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 不能为空";
			} else if (status == 2) {
				message = "合同序号有重复 &nbsp;&nbsp;重复合同序号为:" + string;
			} else {
				message = this.importRentContracts(list2, username);
			}
			json = "{\"success\":\"true\", \"message\":\"" + message + "\"}";
		return json;
	}

	private String importRentContracts(List<List<String>> list, String username)
			throws Exception {
		String message = "";
		if (list != null) {
			if (list.size() > 1) {
				int rentContractCount = 0;
				for (int i = 1; i < list.size(); i++) {
					List<String> cellList = list.get(i);
					String fcontractnum = cellList.get(0).trim();// 合同序号
					// String dcontractname =
					// cellList.get(titleNum[3]).trim();//合同/协议名称
					Dcontract dcontract = new Dcontract();
					// 合同数据
					dcontract.setFcontractnum(cellList.get(0).trim());
					dcontract.setFcontractname(cellList.get(1).trim());
					dcontract.setFfirstparty(cellList.get(2).trim());
					dcontract.setFclient(cellList.get(3).trim());
					dcontract.setFtransact(cellList.get(4).trim());
					dcontract.setFcontractdate(Utils.toDate(cellList.get(5)));
					dcontract.setFmailbox(cellList.get(6).trim());

					dcontract.setCreator(username);
					dcontract.setStatus("1");
					IdcontractMapper.insertDcontract(dcontract); // 插入数据

					rentContractCount++;
				}
				String spaceString = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				message += rentContractCount == 0 ? (spaceString + "合同信息导入</br>")
						: (spaceString + "导入了" + rentContractCount + "条合同信息</br>");
			}
		}
		return message;
	}
	public boolean findContractNumExist() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		return extDataBaseMapper.findExists(Utils.setParams(
				"T_DOCUMENT", "FCONTRACTNUM", request.getParameter("columnValue"))) == 0 ? false : true;
	}
}
