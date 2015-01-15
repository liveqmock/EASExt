package com.creditease.eas.test;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Test2{
	public  String getExp(){
		String vartitle = "<pre>在职员工列表\t\t\t\t\t\t\t\t\t\t"
			+"<a href='<%=basePath %>upload/upload!exportExcel.action?begin=${param.begin}"
			+"&end=${param.end}&receiver=${param.receiver}&departname=${param.departname}"
			+"&postname=${param.postname}&theme=${param.theme}&typeid=${param.typeid}&wayid=${param.wayid}'>"
			+"生成excel文件</a></pre>";
		System.out.println("vartitle:::::::" + vartitle);
		return vartitle;
	}
 
 public static void main(String[] args){
  try {
	  	File f1 = new File("d:\\backup.jpg");
   		BufferedImage image = ImageIO.read(f1);
	   Graphics g = image.getGraphics();
	   g.setFont(new Font("Serif",Font.BOLD,20));
	   g.setColor(Color.red);
	   g.drawString("happy new year!", 10, 20);
	   File f2 = new File("d:\\copy.jpg");
	   ImageIO.write(image, "JPEG", f2);
  } catch (IOException e) {
   e.printStackTrace();
  }
 }
}