package com.hooltech.reader.view.adapter;

public class worldBean {
    public int itemImageResid;
    public String itemTitleid;
    public Boolean checkboxStat;

    public worldBean(int itemImageResid,String itemTitleid,boolean checkboxStat){
        this.itemImageResid = itemImageResid;
        this.itemTitleid = itemTitleid;
        this.checkboxStat = checkboxStat;
    }
}
