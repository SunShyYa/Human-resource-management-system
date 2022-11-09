package org.javaboy.vhr.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Employeeremove {
    private Integer id;

    private Integer eid;

    private Integer beforedepid;

    private Integer afterdepid;

    private Integer beforejobid;

    private Integer afterjobid;

    private String beforedepname;

    private String afterdepname;

    private String beforejobname;

    private String afterjobname;


    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private Date removedate;

    private String reason;

    private String remark;

    private Employee employee;


    public String getBeforedepname() {
        return beforedepname;
    }

    public void setBeforedepname(String beforedepname) {
        this.beforedepname = beforedepname;
    }

    public String getAfterdepname() {
        return afterdepname;
    }

    public void setAfterdepname(String afterdepname) {
        this.afterdepname = afterdepname;
    }

    public String getBeforejobname() {
        return beforejobname;
    }

    public void setBeforejobname(String beforejobname) {
        this.beforejobname = beforejobname;
    }

    public String getAfterjobname() {
        return afterjobname;
    }

    public void setAfterjobname(String afterjobname) {
        this.afterjobname = afterjobname;
    }

    private JobLevel jobLevel;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }



    public JobLevel getJobLevel() {
        return jobLevel;
    }

    public void setJobLevel(JobLevel jobLevel) {
        this.jobLevel = jobLevel;
    }

    public Integer getBeforedepid() {
        return beforedepid;
    }

    public void setBeforedepid(Integer beforedepid) {
        this.beforedepid = beforedepid;
    }

    public Integer getBeforejobid() {
        return beforejobid;
    }

    public void setBeforejobid(Integer beforejobid) {
        this.beforejobid = beforejobid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public Integer getAfterdepid() {
        return afterdepid;
    }

    public void setAfterdepid(Integer afterdepid) {
        this.afterdepid = afterdepid;
    }

    public Integer getAfterjobid() {
        return afterjobid;
    }

    public void setAfterjobid(Integer afterjobid) {
        this.afterjobid = afterjobid;
    }

    public Date getRemovedate() {
        return removedate;
    }

    public void setRemovedate(Date removedate) {
        this.removedate = removedate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public String getRemark() {
        return remark;
    }

    @Override
    public String toString() {
        return "Employeeremove{" +
                "id=" + id +
                ", eid=" + eid +
                ", beforedepid=" + beforedepid +
                ", afterdepid=" + afterdepid +
                ", beforejobid=" + beforejobid +
                ", afterjobid=" + afterjobid +
                ", beforedepname='" + beforedepname + '\'' +
                ", afterdepname='" + afterdepname + '\'' +
                ", beforejobname='" + beforejobname + '\'' +
                ", afterjobname='" + afterjobname + '\'' +
                ", removedate=" + removedate +
                ", reason='" + reason + '\'' +
                ", remark='" + remark + '\'' +
                ", employee=" + employee +
                ", jobLevel=" + jobLevel +
                '}';
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}