/**
 * 
 */
package com.creditease.eas.test;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 * @ImageTest2.java
 * created at 2013-1-5 下午03:06:37 by xjw
 * 
 * @author xjw({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class ImageTest2 {

	public static void main(String[] args){
		  try {
			  	File f1 = new File("c:\\aaa.jpg");
		   		BufferedImage image = ImageIO.read(f1);
			   Graphics g = image.getGraphics();
			   g.setFont(new Font("华文行楷",Font.BOLD,25));
			   g.setColor(Color.blue);
			   g.drawString("王晓明", 100, 112);
			   File f2 = new File("c:\\yx_image\\copy.jpg");
			   ImageIO.write(image, "JPEG", f2);
		  } catch (IOException e) {
		   e.printStackTrace();
		}
	}
	
}
