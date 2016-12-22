package com.anooc.android.md.model.entity;


import com.anooc.android.md.util.FormatUtils;

public class Md {
    private int id;
    private String vid;
    private String title;
    private String Cover;
    private int commentnum;
    private int uid;
    private int newslist_tpl;
    private String ctime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() { // 修复头像地址的历史遗留问题
        return FormatUtils.getCompatAvatarUrl(Cover);
    }

    public void setCover(String Cover) {
        this.Cover = Cover;
    }


    public int getCommentnum() {
        return commentnum;
    }

    public void setCommentnum(int commentnum) {
        this.commentnum = commentnum;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getNewslist_tpl() {
        return newslist_tpl;
    }

    public void setNewslist_tpl(int newslist_tpl) {
        this.newslist_tpl = newslist_tpl;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }
}
