/**
 * 
 */
package com.creditease.eas.warn.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.creditease.eas.util.BaseAction;
import com.creditease.eas.util.Dictionary;
import com.creditease.eas.util.DictionaryUtil;
import com.creditease.eas.warn.bean.RentContract;
import com.creditease.eas.warn.service.RentContractService;
import com.google.gson.GsonBuilder;
import com.hp.hpl.sparta.xpath.ThisNodeTest;

/**
 * @RentContractAction.java房租合同信息action
 * created at 2013-8-1 下午12:02:19 by caoyong
 * 
 * @author caoyong({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
@SuppressWarnings("deprecation")
@Controller
@Scope("prototype")
public class RentContractAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private RentContractService rentContractServiceImpl;

	/**房租合同bean**/
	private RentContract rentContract;
	/**付款方式**/
	private List<Dictionary> payTypes;
	/**合同状态**/
	private List<Dictionary> rentStatuses;
	
	public RentContract getRentContract() {
		return rentContract;
	}

	public void setRentContract(RentContract rentContract) {
		this.rentContract = rentContract;
	}
	
	public String queryPageJson() throws Exception {
		this.pagination = rentContractServiceImpl.queryPage(pagination);
		return "queryPageJson";
	}
	
	/**
	 * 查看
	 * 描述：
	 * 2012-12-27 下午06:16:18 by caoyong
	 * @version
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception{
		this.getAllDictionarys();
		rentContract = rentContractServiceImpl.getRentContractById(rentContract.getId());
		return "view";
	}
	
	/**
	 * 删除
	 * 描述：
	 * 2012-12-27 下午06:16:18 by caoyong
	 * @version
	 * @return
	 * @throws Exception 
	 * @throws Exception
	 */
	public String delete() throws Exception{
		rentContractServiceImpl.delete(rentContract.getId());
		return "queryPageJsonBack";
	}
	/**
	 * 编辑
	 * 描述：跳转到编辑页面
	 * 2012-12-27 下午06:16:18 by caoyong
	 * @version
	 * @return
	 * @throws Exception 
	 * @throws Exception
	 */
	public String edit() throws Exception{
		this.getAllDictionarys();
		if(rentContract!=null) rentContract = rentContractServiceImpl.getRentContractById(rentContract.getId());
		return "edit";
	}
	/**
	 * 
	 * 描述：查询所有下拉列表集合
	 * 2013-9-10 下午03:00:56 by caoyong
	 * @version
	 * @throws Exception
	 */
	public void getAllDictionarys() throws Exception{
		List<Dictionary> dictionaries = DictionaryUtil.getDictionarys(DictionaryUtil.singleMap, DictionaryUtil.payType);
		this.payTypes = new ArrayList<Dictionary>();
		Map<Integer, Dictionary> map = new HashMap<Integer, Dictionary>();
		for(Dictionary dictionary:dictionaries){map.put(DictionaryUtil.getKeyNum(dictionary.getKey())-1, dictionary);}
		for(int i=0;i<map.size();i++){this.payTypes.add(map.get(i));}
		this.rentStatuses = DictionaryUtil.getDictionarys(DictionaryUtil.singleMap, DictionaryUtil.rentStatus);
		response.getWriter().print(GsonBuilder.class.newInstance().create().toJson(this.payTypes));
	}
	/**修改
	 * 描述：
	 * 2012-12-27 下午06:16:18 by caoyong
	 * @version
	 * @return
	 * @throws Exception
	 */
	public void update() throws Exception{
		try {
			rentContract.setLastupdater(this.findUser().getUsername());
			rentContractServiceImpl.update(rentContract);
			this.json = "{\"success\":\"true\"}";
		} catch (Exception e) {
			e.printStackTrace();	
			this.json = "{\"success\":\"false\"}";
		}finally{
			this.writerJsonToClient(this.json);
		}
	}
	
	/**
	 * 
	 * 描述：新增房屋合同记录
	 * 2013-9-6 下午01:40:02 by caoyong
	 * @version
	 * @throws Exception
	 */
	public void insert() throws Exception{
		try {
			rentContract.setCreator(this.findUser().getUsername());
			rentContractServiceImpl.insert(rentContract);
			this.json = "{\"success\":\"true\"}";
		} catch (Exception e) {
			e.printStackTrace();	
			this.json = "{\"success\":\"false\"}";
		}finally{
			this.writerJsonToClient(this.json);
		}
	}
	
	/**
	 * @return the payTypes
	 */
	public List<Dictionary> getPayTypes() {
		return payTypes;
	}

	/**
	 * @param payTypes the payTypes to set
	 */
	public void setPayTypes(List<Dictionary> payTypes) {
		this.payTypes = payTypes;
	}

	/**
	 * @return the rentStatuses
	 */
	public List<Dictionary> getRentStatuses() {
		return rentStatuses;
	}

	/**
	 * @param rentStatuses the rentStatuses to set
	 */
	public void setRentStatuses(List<Dictionary> rentStatuses) {
		this.rentStatuses = rentStatuses;
	}
}
