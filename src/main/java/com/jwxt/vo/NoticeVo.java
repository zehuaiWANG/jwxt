package com.jwxt.vo;

public class NoticeVo {
    private Integer noticeId;

    private String noticeContact;

    private String noticeAuthor;

    private String updatetime;

    public NoticeVo() {
    }

    public NoticeVo(Integer noticeId, String noticeContact, String noticeAuthor, String updatetime) {
        this.noticeId = noticeId;
        this.noticeContact = noticeContact;
        this.noticeAuthor = noticeAuthor;
        this.updatetime = updatetime;
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
        this.noticeContact = noticeContact;
    }

    public String getNoticeAuthor() {
        return noticeAuthor;
    }

    public void setNoticeAuthor(String noticeAuthor) {
        this.noticeAuthor = noticeAuthor;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }
}
