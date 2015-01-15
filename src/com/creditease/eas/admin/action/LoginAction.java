package com.creditease.eas.admin.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.creditease.eas.admin.bean.Menu;
import com.creditease.eas.admin.bean.User;
import com.creditease.eas.admin.service.LoginService;
import com.creditease.eas.admin.service.MenuService;
import com.creditease.eas.admin.service.RoleFunctionService;
import com.creditease.eas.admin.service.UserRoleService;
import com.creditease.eas.util.BaseAction;
import com.creditease.eas.util.ComparatorImpl;
import com.creditease.eas.util.StringUtil;
import com.creditease.eas.warn.bean.FinanceUser;
import com.creditease.eas.warn.dao.FinanceUserMapper;
import com.opensymphony.xwork2.ActionContext;

import edu.emory.mathcs.backport.java.util.Collections;
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class LoginAction extends BaseAction{
	private String username;
	private String password;
	private String verifycode;
	@Autowired
	private LoginService loginServiceImpl;
	@Autowired
	private MenuService menuServiceImpl;
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private RoleFunctionService roleFunctionService;
	@Autowired
	private FinanceUserMapper financeUserMapper;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getVerifycode() {
		return verifycode;
	}
	public void setVerifycode(String verifycode) {
		this.verifycode = verifycode;
	}
	@SuppressWarnings("unchecked")
	public String checkLogin() throws Exception{
		Map session = ActionContext.getContext().getSession();
		
		 HttpSession session2 = request.getSession(true);  
         String strs = StringUtil.objToString( session2.getAttribute("authCode"));  
         String mycode = StringUtil.objToString( request.getParameter("myCode"));
			User user = loginServiceImpl.findOneUser(username, password);
			if(user == null){
				request.setAttribute("msg", "<font color='red'>请检查用户名和密码是否输入正确!</font>");
				return "tologin";
				
			}else if(!strs.equalsIgnoreCase(mycode)){
				request.setAttribute("msg", "<font color='red'>请正确输入验证码!</font>");
				return "tologin";
				
			}else if (user!=null && strs.equalsIgnoreCase(mycode)) {
				//用户id 
				Integer userid = user.getId().intValue();
				
				//用户的角色集合
				List<Integer> rolelist = userRoleService.userroleidlist(userid);
				/*if(rolelist.contains(302)){//新入职员工信息中服务台特殊人员角色
					session.put("personrole", "302");
				}else{
					session.put("personrole", "");
				}*/
				
				session.put("personrole", rolelist);//将角色id集合存入session中
				
				if(rolelist.size()==0 || rolelist == null){
					request.setAttribute("msg", "<font color='red'>对不起你没有权限进入该系统!</font>");
					return "tologin";
					
								  }else{
									  
				//该用户角色对应的所有菜单id集合
				List<Integer> functlist = new ArrayList<Integer>();
				
				for (int i = 0; i < rolelist.size(); i++) {
					Integer roleid = rolelist.get(i);
					Map map = new HashMap();
					map.put("roleid", roleid);
					map.put("linktype", 0);
					map.put("stutas", 0);
					List<Integer> list = roleFunctionService.selerolefonctionid(map);
					functlist.addAll(list);
					
				}
				//为避免多角色之间有重叠的菜单权限，将list转成set
				Set<Integer> functiset = new HashSet<Integer>(functlist);
				
				Iterator<Integer> it = functiset.iterator();
				List<Menu> listmenu1 = new ArrayList<Menu>();
				List<Menu> listmenu2 = new ArrayList<Menu>();
				List<Menu> findbylist1 = new ArrayList<Menu>();
				List<Menu> findbylist2 = new ArrayList<Menu>();
				//根据菜单id查询菜单对象
				while (it.hasNext()) {
					
					Integer menuid = it.next();
					Map map = new HashMap();
					map.put("menuid", menuid);
					map.put("level", 1);
					listmenu1 = findByLevel(map);
					findbylist1.addAll(listmenu1);
					
					
					Map map2 = new HashMap();
					map2.put("menuid", menuid);
					map2.put("level", 2);
					listmenu2 = findByLevel(map2);
					findbylist2.addAll(listmenu2);
				}
				ComparatorImpl c1 = new ComparatorImpl();
				Collections.sort(findbylist1, c1);
				Collections.sort(findbylist2, c1);
				
				session.put("user", user);
				session.put("firstLelelMenus", findbylist1);
				session.put("secondLelelMenus", findbylist2);
				
				//登录用户在财务用户表中的数据
				session2.setAttribute("isgroupleader", 2);
				FinanceUser financeUser = financeUserMapper.selectByUserid(userid);
				if(financeUser!=null){
					int isgroupleader = financeUser.getIsGroupLeader();//是否是组长
					if(isgroupleader ==1 ||isgroupleader ==0){
						session2.setAttribute("isgroupleader", isgroupleader);
					}else{
						session2.setAttribute("isgroupleader", "");
					}
					
				}
				
				
				
				return "loginSuccess";
				
								  }}else{
									  
				System.out.println("LoginAction.checkLogin() verifyfail");
				request.setAttribute("msg", "<font color='red'>亲，登陆失败了!</font>");
				return "tologin";
			}
	}
	/**
	 * 
	 * 描述：退出系统
	 * 2013-1-16 下午06:12:31 by ygq
	 * @version
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String exitLogin() throws Exception{
		Map session = ActionContext.getContext().getSession();
		if (session instanceof org.apache.struts2.dispatcher.SessionMap) {
			((org.apache.struts2.dispatcher.SessionMap) session).invalidate();
		}
		ServletActionContext.getRequest().getSession().invalidate();
		return "tologin";
	}
	
    
    public void test(){
    	try {  
            int width = 50;  
            int height = 18;  
            // 取得一个4位随机字母数字字符串  
            String s = RandomStringUtils.random(4, true, true);  
            //System.err.println("字符："+s);
            HttpServletResponse response=ServletActionContext.getResponse();
            
            // 保存入session,用于与用户的输入进行比较.  
            // 注意比较完之后清除session.  
            HttpSession session = request.getSession(true);  
            session.setAttribute("authCode", s);  
  
            response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
	        response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
	        response.setHeader("Cache-Control", "no-cache");
	        response.setDateHeader("Expire", 0);
  
            ServletOutputStream out = response.getOutputStream();  
            BufferedImage image = new BufferedImage(width, height,  
                    BufferedImage.TYPE_INT_RGB);  
            Graphics g = image.getGraphics();  
            // 设定背景色  
            g.setColor(getRandColor(200, 250));  
            g.fillRect(0, 0, width, height);  
  
            // 设定字体  
            Font mFont = new Font("Times New Roman", Font.BOLD, 18);// 设置字体  
            g.setFont(mFont);  
  
            // 画边框  
            // g.setColor(Color.BLACK);  
            // g.drawRect(0, 0, width - 1, height - 1);  
  
            // 随机产生干扰线，使图象中的认证码不易被其它程序探测到  
            g.setColor(getRandColor(160, 200));  
            // 生成随机类  
            Random random = new Random();  
            for (int i = 0; i < 155; i++) {  
                int x2 = random.nextInt(width);  
                int y2 = random.nextInt(height);  
                int x3 = random.nextInt(12);  
                int y3 = random.nextInt(12);  
                g.drawLine(x2, y2, x2 + x3, y2 + y3);  
            }  
  
            // 将认证码显示到图象中  
            g.setColor(new Color(20 + random.nextInt(110), 20 + random  
                    .nextInt(110), 20 + random.nextInt(110)));  
  
            g.drawString(s, 2, 16);  
            
            // 图象生效  
            g.dispose();  
            // 输出图象到页面  
            ImageIO.write((BufferedImage) image, "JPEG", out);  
            out.close();  
            
           
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }
    
    private Color getRandColor(int fc, int bc) { // 给定范围获得随机颜色  
        Random random = new Random();  
        if (fc > 255)  
            fc = 255;  
        if (bc > 255)  
            bc = 255;  
        int r = fc + random.nextInt(bc - fc);  
        int g = fc + random.nextInt(bc - fc);  
        int b = fc + random.nextInt(bc - fc);  
        return new Color(r, g, b);  
    }  
    /**
     * 
     * 描述：根据级别查找菜单
     * 2013-8-13 上午11:23:09 by caoyong
     * @version
     * @param level级别
     * @return
     */
    private List<Menu> findByLevel(Map map){
    	List<Menu> menus = menuServiceImpl.findByLevel(map);
    	return menus;
    }
}
