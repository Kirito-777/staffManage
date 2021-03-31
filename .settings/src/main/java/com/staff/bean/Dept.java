package com.staff.bean;

public class Dept {
    private Integer did;

    private String dname;

    private String fId;
    
    private String manage;
    
    public String getManage() {
		return manage;
	}

	public void setManage(String manage) {
		this.manage = manage;
	}

//	private Employee employee;
//
//    public Employee getEmployee() {
//		return employee;
//	}
//
//	public void setEmployee(Employee employee) {
//		this.employee = employee;
//	}

	public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname == null ? null : dname.trim();
    }

    public String getfId() {
        return fId;
    }

    public void setfId(String fId) {
        this.fId = fId == null ? null : fId.trim();
    }
}