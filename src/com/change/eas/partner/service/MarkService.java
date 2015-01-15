package com.change.eas.partner.service;

import java.util.List;
import com.change.eas.partner.bean.Mark;
import com.change.eas.util.PageView;
import com.creditease.eas.util.Pagination;

public interface MarkService {

	List<Mark> listMark(Mark mark, PageView view);

	long getNewMarkID();

	boolean checkMarkName(String name);

	void addMark(Mark mark);

	void delMark(long bookmarkid);

	Pagination queryMarkPageJson(Pagination page);
}
