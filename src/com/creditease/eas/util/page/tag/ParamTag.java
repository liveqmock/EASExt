package com.creditease.eas.util.page.tag;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

public class ParamTag extends TagSupport {
	private String name;
	private String value = "";
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int doEndTag() throws JspTagException {
		Tag t = findAncestorWithClass(this, PageTag.class);
		PageTag parent = (PageTag) t;
		parent.addParam(name,value);
		return EVAL_PAGE;
	}
}
