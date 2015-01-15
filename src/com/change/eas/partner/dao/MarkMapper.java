package com.change.eas.partner.dao;

import java.util.List;
import java.util.Map;

import com.change.eas.partner.bean.Mark;

public interface MarkMapper {
	/**
	 * @param map
	 *            isLoop
	 * @return
	 */
	/*List<Mark> listMarkByBookmarkname(Map<String, Object> map);

	List<Mark> listMarkByIsloop(Map<String, Object> map);

	List<Mark> listMarkByIsloop2(Map<String, Object> map);

	List<Mark> listMarkByBookmarknameAndIsloop(Map<String, Object> map);

	List<Mark> listMarkByBookmarknameAndIsloop2(Map<String, Object> map);*/

	List<Mark> listMark(Map<String, Object> map);

	void updateMark(Mark mark);

	void addMark(Mark mark);

	void delMark(long markid);

	int getTitMark(Mark mark);
	
	int getTotalsMark(Map params);

	long getNewMarkID();

	List<Mark> findMarkByBookmarkname(String name);
	
	List<Mark> queryMarkPageJson(Map params);
}
