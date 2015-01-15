package com.creditease.eas.doc.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.creditease.eas.doc.bean.Dcontract;
import com.creditease.eas.doc.service.DcontractService;
import com.creditease.eas.util.BaseAction;

/**
 * 文档管理模块
 * 
 * @DocumentAction.java created at 2013-9-10 上午11:25:02 by admin
 * 
 * @author xiaofeng({@link authorEmail})
 * @version $Revision$</br> update: $Date$
 */
@Controller
@Scope("prototype")
public class DcontractAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	@Autowired
	private DcontractService dcontractServiceImpl;
	private List<Dcontract> DcontractList;
	private Dcontract dcontract;
	private File excelFile;
	private String nn;
    private String status;
    private static Logger logger=Logger.getLogger(DcontractAction.class);
	/**
	 * 查询所有合同信息 描述： 2013-9-10 下午05:54:12 by admin
	 * 
	 * @version
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String queryPageJson() {
		this.pagination = dcontractServiceImpl.queryPage(pagination,request.getParameter("status"));
		DcontractList = pagination.getRows();
		return "queryPageJson";
	}
	/**
	 * 导入合同信息 描述： 2013-9-11 上午09:43:18 by sunxiaofeng
	 * 
	 * @version
	 * @throws Exception
	 */
	public void importExcel()  {
		String json="";
		try {
			json = dcontractServiceImpl.importExcel(nn, findUser()
					.getUsername(),excelFile);
			logger.info("导入合同信息成功");
	  } catch (Exception e) {
		json = "{\"success\":\"false\", \"message\":\"</br>导入异常\"}";
		e.printStackTrace();
	 } finally {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		try {
			response.getWriter().print(json);
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	   }
	}
	/**
	 * 合同信息编辑 描述： 2013-9-11 下午02:26:58 by admin
	 * 
	 * @version
	 * @return
	 * @throws Exception
	 */
	public String edit(){
		// this.getAllDictionarys();
		if (dcontract != null)
			dcontract = dcontractServiceImpl.getRentContractById(dcontract
					.getId());
		return "edit";
	}
/**
 * 验证该合同信息是否存在
 * 描述：
 * 2013-9-17 下午12:16:28 by admin
 * @version
 * @throws IOException
 */
	public void getFcontractnum() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
	//	System.out.println(request.getParameter("fcontractnum"));
		Dcontract dcontract1 = dcontractServiceImpl.getContract(request
				.getParameter("fcontractnum"));
			try {
				if (dcontract1 != null) {
					PrintWriter out = response.getWriter();
				   out.write("1");
				 }
			} catch (IOException e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
		
	}

	/**
	 * 新增合同记录 描述： 2013-9-11 下午03:25:33 by admin
	 * 
	 * @version
	 * @throws Exception
	 */
	public void insert(){
		try {
			Dcontract dcontract1 = dcontractServiceImpl.getContract(dcontract
					.getFcontractnum());
			if (dcontract1 == null) {
				dcontract.setCreator(findUser().getUsername());
				dcontract.setStatus("1");
				dcontractServiceImpl.insert(dcontract);
			}
			this.json = "{\"success\":\"true\"}";
			logger.info("合同信息新增成功");
		} catch (Exception e) {
			e.printStackTrace();	
			this.json = "{\"success\":\"false\"}";
			logger.info("合同信息新增失败");
		}finally{
			try {
				this.writerJsonToClient(this.json);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 修改合同信息 描述： 2013-9-12 下午01:53:29 by admin
	 * 
	 * @version
	 * @throws Exception
	 */
	public void update(){
		try {
			dcontract.setLastupdater(findUser().getUsername());
			dcontractServiceImpl.update(dcontract);
			this.json = "{\"success\":\"true\"}";
			logger.info("合同信息修改成功");
		} catch (Exception e) {
			e.printStackTrace();	
			this.json = "{\"success\":\"false\"}";
			logger.info("合同信息修改失败");
		}finally{
			try {
				this.writerJsonToClient(this.json);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 删除合同信息（假删除，状态控制） 描述： 2013-9-16 下午05:49:33 by admin
	 * 
	 * @version
	 * @throws IOException
	 */
	public void deleteDcontract() {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			System.out.println();
			String id = request.getParameter("id");
			dcontractServiceImpl.deleteDcontract(id);
			PrintWriter out = response.getWriter();
			out.write("1");
			logger.info("删除合同信息操作成功");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		
	}
	/**
	 * 描述：查找合同序号是否已经存在
	 * 2013-12-5 下午01:46:30 by caoyong
	 * @version
	 */
	public void findContractNumExist(){
		boolean exist = false;
		PrintWriter pw = null;
		try {
			exist = dcontractServiceImpl.findContractNumExist();
			pw = response.getWriter();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			pw.print(exist);
			pw.close();
		}
	}
	public File getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(File excelFile) {
		this.excelFile = excelFile;
	}

	public String getNn() {
		return nn;
	}

	public void setNn(String nn) {
		this.nn = nn;
	}

	public Dcontract getDcontract() {
		return dcontract;
	}

	public void setDcontract(Dcontract dcontract) {
		this.dcontract = dcontract;
	}

	public List<Dcontract> getDcontractList() {
		return DcontractList;
	}

	public void setDcontractList(List<Dcontract> dcontractList) {
		DcontractList = dcontractList;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
