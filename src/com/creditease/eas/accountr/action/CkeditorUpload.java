package com.creditease.eas.accountr.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.struts2.ServletActionContext;

import com.creditease.eas.util.BaseAction;

public class CkeditorUpload extends BaseAction {

	private File upload;
	private String uploadContentType;
	private String uploadFileName;

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {

		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String execute() throws Exception {
	//	HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("GBK");
		PrintWriter out = response.getWriter();

		// 对文件进行校验
		if (upload == null || uploadContentType == null
				|| uploadFileName == null) {
			out.print("<font color=\"red\" size=\"2\">*请选择上传文件</font>");
			return null;
		}

		if ((uploadContentType.equals("image/pjpeg") || uploadContentType
				.equals("image/jpeg"))
				&& uploadFileName.substring(uploadFileName.length() - 4)
						.toLowerCase().equals(".jpg")) {
			// IE6上传jpg图片的headimageContentType是image/pjpeg，而IE9以及火狐上传的jpg图片是image/jpeg
		} else if (uploadContentType.equals("image/png")
				&& uploadFileName.substring(uploadFileName.length() - 4)
						.toLowerCase().equals(".png")) {

		} else if (uploadContentType.equals("image/gif")
				&& uploadFileName.substring(uploadFileName.length() - 4)
						.toLowerCase().equals(".gif")) {

		} else if (uploadContentType.equals("image/bmp")
				&& uploadFileName.substring(uploadFileName.length() - 4)
						.toLowerCase().equals(".bmp")) {

		} else {
			out
					.print("<font color=\"red\" size=\"2\">*文件格式不正确（必须为.jpg/.gif/.bmp/.png文件）</font>");
			return null;
		}

		if (upload.length() > 600 * 1024) {
			out.print("<font color=\"red\" size=\"2\">*文件大小不得大于600k</font>");
			return null;
		}

		// 将文件保存到项目目录下
		InputStream is = new FileInputStream(upload);
		String uploadPath = ServletActionContext.getServletContext()
				.getRealPath("/upload/"); // 设置保存目录
		String fileName =new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "-" + RandomUtils.nextInt();//java.util.UUID.randomUUID(); // 采用UUID的方式随机命名
		fileName += uploadFileName.substring(uploadFileName.length() - 4);
		File toFile = new File(uploadPath, fileName);
		OutputStream os = new FileOutputStream(toFile);
		byte[] buffer = new byte[1024];
		int length = 0;
		while ((length = is.read(buffer)) > 0) {
			os.write(buffer, 0, length);
		}
		is.close();
		os.close();

		// 设置返回“图像”选项卡
		String callback = ServletActionContext.getRequest().getParameter(
				"CKEditorFuncNum");
		out.println("<script type=\"text/javascript\">");
		out.println("window.parent.CKEDITOR.tools.callFunction(" + callback
				+ ",'" + "/EASExt/upload/" + fileName + "','')");
		out.println("</script>");

		return null;
	}
}