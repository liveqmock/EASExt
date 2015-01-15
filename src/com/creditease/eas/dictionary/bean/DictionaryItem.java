package com.creditease.eas.dictionary.bean;
/**
 * 数据字典子项
 * @DictionaryItem.java
 * created at 2014-3-10 下午02:17:48 by zhangxin
 * 
 * @author zhangxin({@link authorEmail})
 * @version $Revision$</br>
 * update: $Date$
 */
public class DictionaryItem {
    private Integer id;   //id
    private Integer baseid; //父类id
    private String itemname;//子项名称
    private String status;//状态值 0-正常 1-删除
    private String itemid;//子项关键字
    private String ext1;
    private String ext2;
    private String ext3;
    private String ext4;
    private String ext5;
    private String ext6;
    private String ext7;
    private String ext8;
    private String ext9;
    private String ext10;
   
    
    public String getItemid() {
		return itemid;
	}
	public void setItemid(String itemid) {
		this.itemid = itemid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getBaseid() {
        return baseid;
    }
    public void setBaseid(Integer baseid) {
        this.baseid = baseid;
    }

    public String getItemname() {
        return itemname;
    }
    public void setItemname(String itemname) {
        this.itemname = itemname == null ? null : itemname.trim();
    }
    public String getExt1() {
        return ext1;
    }
    public void setExt1(String ext1) {
        this.ext1 = ext1 == null ? null : ext1.trim();
    }
    public String getExt2() {
        return ext2;
    }
    public void setExt2(String ext2) {
        this.ext2 = ext2 == null ? null : ext2.trim();
    }
    public String getExt3() {
        return ext3;
    }
    public void setExt3(String ext3) {
        this.ext3 = ext3 == null ? null : ext3.trim();
    }
    public String getExt4() {
        return ext4;
    }
    public void setExt4(String ext4) {
        this.ext4 = ext4 == null ? null : ext4.trim();
    }
    public String getExt5() {
        return ext5;
    }
    public void setExt5(String ext5) {
        this.ext5 = ext5 == null ? null : ext5.trim();
    }
    public String getExt6() {
        return ext6;
    }
    public void setExt6(String ext6) {
        this.ext6 = ext6 == null ? null : ext6.trim();
    }
    public String getExt7() {
        return ext7;
    }
    public void setExt7(String ext7) {
        this.ext7 = ext7 == null ? null : ext7.trim();
    }
    public String getExt8() {
        return ext8;
    }
    public void setExt8(String ext8) {
        this.ext8 = ext8 == null ? null : ext8.trim();
    }
    public String getExt9() {
        return ext9;
    }
    public void setExt9(String ext9) {
        this.ext9 = ext9 == null ? null : ext9.trim();
    }
    public String getExt10() {
        return ext10;
    }
    public void setExt10(String ext10) {
        this.ext10 = ext10 == null ? null : ext10.trim();
    }
}