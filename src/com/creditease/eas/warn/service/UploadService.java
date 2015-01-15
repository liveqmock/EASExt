package com.creditease.eas.warn.service;
import java.util.List;

import com.creditease.eas.warn.bean.SendPortInfo;
import com.creditease.eas.warn.bean.WaringDetailView;
public interface UploadService {
	public List<WaringDetailView> queryDatasetByParams() throws Exception;
	
	public List<SendPortInfo> queryDataset(String orgname) throws Exception;
}
