package com.staff.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class File {
    private String filename;

    private String username;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date date;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename == null ? null : filename.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Date getDate() {
        return date;
    }

    @Override
	public String toString() {
		return "File [filename=" + filename + ", username=" + username + ", date=" + date + "]";
	}

	public void setDate(Date date) {
        this.date = date;
    }
}