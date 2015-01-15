package com.creditease.eas.compliance.bean;

import java.util.List;

public class NextCommunicateTimeForEmail {
	private String toEmail;
	private List<NextCommunicateTimeContent> content;

	public String getToEmail() {
		return toEmail;
	}

	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}

	public List<NextCommunicateTimeContent> getContent() {
		return content;
	}

	public void setContent(List<NextCommunicateTimeContent> content) {
		this.content = content;
	}

}
