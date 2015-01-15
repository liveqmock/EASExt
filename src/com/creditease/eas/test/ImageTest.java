/**
 * 
 */
package com.creditease.eas.test;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
/**
 * @ImageTest.java
 * created at 2013-1-5 下午02:31:32 by xjw
 * 
 * @author xjw({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class ImageTest {

	/**
	  * 下载组织机构图
	  * */
	 @RequestMapping(value="lookImage")
	 public void lookImage(HttpServletRequest request,HttpServletResponse response) throws Exception{
	  
	    //设置响应方式为文件下载
	    response.setContentType("image/png; charset=GBK");
	    response.setHeader("content-type", "application/x-msdownload;");
	   
	    //处理中文文件名乱码
	    String realName ="组织机构";
	    String fileName = new String(realName.getBytes("GBK"), "ISO8859-1");
	    response.setHeader("Content-Disposition","attachment;filename="+fileName+".png");
	    
	    String path = request.getSession().getServletContext().getRealPath("/");//获取项目根路径，找到图片
	    File imageFile = new File(path+"/images/iproUnit.gif");
	    System.out.println("fileUrl--------------------------->"+imageFile);
	   
	    //先读取图片
	    BufferedImage image= ImageIO.read(imageFile);
	   
	    /*String str1 ="→测试";
	    String str2 ="→累";
	    String str3 ="→死";
	    String str4 ="→哥";
	    String str5 ="→了";
	    String str6 ="→啊";
	    String str7 ="→太不容易了";*/
	   
	    //顺序：图片从左到右，从上到下
	    String str1 ="→";
//	    String str2 ="→";
//	    String str3 ="→";
//	    String str4 ="→";
//	    String str5 ="→";
//	    String str6 ="→";
//	    String str7 ="→";
	   
	    //得到要写在图片上的数据
//	    Iprojects ipr = iprojectsManager.get(request.getParameter("id"));
//	    str1 += ipr.getpEachimunit();
//	    str2 += ipr.getpIperson();
//	    str3 += ipr.getpDirectPerosn();
//	    str4 += ipr.getpImunit();
//	    str5 += ipr.getpIounit();
//	    str6 += ipr.getpUnitop();
//	    str7 += ipr.getpTechPerson();
	   
	    //*绘图区******************************************************
	    Graphics g = image.getGraphics();
	    Font font = new Font("宋体",Font.LAYOUT_LEFT_TO_RIGHT,18);
	    g.setColor(Color.black);//设置当前颜色为black，(此处用来设置字体颜色)
	    g.setFont(font);//设置当前字体为 font
	    //向图片中写入文字
	    g.drawString(str1, 50, 400);//(“文字信息”,x坐标,y坐标)
//	    g.drawString(str2, 340, 75);
//	    g.drawString(str3, 340, 210);
//	    g.drawString(str4, 320, 345);
//	    g.drawString(str5, 320, 475);
//	    g.drawString(str6, 600, 270);
//	    g.drawString(str7, 600, 385);
	     //************************************************************
	    image.flush();
	    g.dispose();

	    ImageIO.write(image, "png", response.getOutputStream());//输出到浏览器，即下载
	    //ImageIO.write(image, "png", new File("C:/test.png"));
	 }
	 
	/**
	 * 描述：
	 * 2013-1-5 下午02:31:32 by xjw
	 * @version
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
