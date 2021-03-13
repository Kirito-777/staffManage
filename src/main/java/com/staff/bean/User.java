package com.staff.bean;

public class User {
    private String id;

    private String username;

    private String realname;

    private String password;

    private String sex;
    
    private String power;

    public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", realname=" + realname + ", password=" + password
				+ ", sex=" + sex + ", power=" + power + "]";
	}
    
}