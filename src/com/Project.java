package com;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Project {
    private int empNo;
    private int projectId;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private Integer duration;

    public Project(int empNo, int projectId, LocalDate dateFrom, String dateTo) {
        setEmpNo(empNo);
        setProjectId(projectId);
        setDateFrom(dateFrom);
        setDateTo(dateTo);
        this.duration = (int)(ChronoUnit.DAYS.between(dateFrom, this.dateTo));
    }

    @Override
    public String toString() {
        return "Project{" +
                "empNo=" + empNo +
                ", projectId=" + projectId +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", duration=" + duration +
                '}';
    }

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empId) {
        this.empNo = empId;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        if(dateTo.equals("NULL")){
            this.dateTo = LocalDate.now();
        }
        else{
            this.dateTo = LocalDate.parse(dateTo);
        }
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
