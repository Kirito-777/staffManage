package com.staff.bean;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Notice {
    private Integer nid;

    private String username;

    private String title;

    private String nId;
    
    
    private String content;
    
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date time;
    

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getnId() {
        return nId;
    }

    public void setnId(String nId) {
        this.nId = nId == null ? null : nId.trim();
    }

    public String getContent() {
//    	System.out.println(content+"内容");
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

	@Override
	public String toString() {
		return "Notice [nid=" + nid + ", username=" + username + ", title=" + title + ", nId=" + nId + ", content="
				+ content + ", time=" + time + "]";
	}
    
    
}