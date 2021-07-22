package com.department.client.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Department {

    @Expose
    @SerializedName("DepartmentId") private int DepartmentId;

    @Expose
    @SerializedName("DepartmentName") private String DepartmentName;

    @Expose
    @SerializedName("DepartmentDesk") private String DepartmentDesk;

    @Expose
    @SerializedName("success") private Boolean success;

    @Expose
    @SerializedName("message") private String message;

    public int getDepartmentId() {
        return DepartmentId;
    }

    public void setDepartmentId(int departmentId) {
        DepartmentId = departmentId;
    }

    public String getDepartmentName() {
        return DepartmentName;
    }

    public void setDepartmentName(String departmentName) {
        DepartmentName = departmentName;
    }

    public String getDepartmentDesk() {
        return DepartmentDesk;
    }

    public void setDepartmentDesk(String departmentDesk) {
        DepartmentDesk = departmentDesk;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
