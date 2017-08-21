package com.jwxt.pojo;

import java.util.Date;

public class Notice {
    private Integer noticeId;

    private String noticeContact;

    private String noticeAuthor;

    private Date updatetime;

    public Notice(Integer noticeId, String noticeContact, String noticeAuthor, Date updatetime) {
        this.noticeId = noticeId;
        this.noticeContact = noticeContact;
        this.noticeAuthor = noticeAuthor;
        this.updatetime = updatetime;
    }

    public Notice() {
        super();
    }

    public Integer getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Integer noticeId) {
        this.noticeId = noticeId;
    }

    public String getNoticeContact() {
        return noticeContact;
    }

    public void setNoticeContact(String noticeContact) {
        this.noticeContact = noticeContact == null ? null : noticeContact.trim();
    }

    public String getNoticeAuthor() {
        return noticeAuthor;
    }

    public void setNoticeAuthor(String noticeAuthor) {
        this.noticeAuthor = noticeAuthor == null ? null : noticeAuthor.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}