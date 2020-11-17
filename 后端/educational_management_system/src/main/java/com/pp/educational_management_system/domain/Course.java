package com.pp.educational_management_system.domain;

public class Course {
    private String coursecode;
    private String coursename;
    private String coursetype;
    private String begingrade;
    private String beginacademy;
    private String semester;
    private String execourses;
    private String tline;
    private String timeflag;
    private String spotflag;
    private Integer attendexpect;
    private Integer atusum;
    private String creadit;
    private String period;
    private String weeknum;

    @Override
    public String toString() {
        return "Course{" +
                "coursecode='" + coursecode + '\'' +
                ", coursename='" + coursename + '\'' +
                ", coursetype='" + coursetype + '\'' +
                ", begingrade='" + begingrade + '\'' +
                ", beginacademy='" + beginacademy + '\'' +
                ", semester='" + semester + '\'' +
                ", execourses='" + execourses + '\'' +
                ", tline='" + tline + '\'' +
                ", timeflag='" + timeflag + '\'' +
                ", spotflag='" + spotflag + '\'' +
                ", attendexpect=" + attendexpect +
                ", atusum=" + atusum +
                ", creadit='" + creadit + '\'' +
                ", period='" + period + '\'' +
                ", weeknum='" + weeknum + '\'' +
                '}';
    }

    public void setAttendexpect(Integer attendexpect) {
        this.attendexpect = attendexpect;
    }

    public void setAtusum(Integer atusum) {
        this.atusum = atusum;
    }

    public Integer getAttendexpect() {
        return attendexpect;
    }

    public Integer getAtusum() {
        return atusum;
    }

    public void setWeeknum(String weeknum) {
        this.weeknum = weeknum;
    }

    public String getWeeknum() {
        return weeknum;
    }

    public String getCoursecode() {
        return coursecode;
    }

    public String getCoursename() {
        return coursename;
    }

    public String getCoursetype() {
        return coursetype;
    }

    public String getBegingrade() {
        return begingrade;
    }

    public String getBeginacademy() {
        return beginacademy;
    }

    public String getSemester() {
        return semester;
    }

    public String getExecourses() {
        return execourses;
    }

    public String getTline() {
        return tline;
    }



    public String getTimeflag() {
        return timeflag;
    }



    public String getSpotflag() {
        return spotflag;
    }



    public String getCreadit() {
        return creadit;
    }

    public String getPeriod() {
        return period;
    }

    public void setCoursecode(String coursecode) {
        this.coursecode = coursecode;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public void setCoursetype(String coursetype) {
        this.coursetype = coursetype;
    }

    public void setBegingrade(String begingrade) {
        this.begingrade = begingrade;
    }

    public void setBeginacademy(String beginacademy) {
        this.beginacademy = beginacademy;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public void setExecourses(String execourses) {
        this.execourses = execourses;
    }

    public void setTline(String tline) {
        this.tline = tline;
    }



    public void setTimeflag(String timeflag) {
        this.timeflag = timeflag;
    }



    public void setSpotflag(String spotflag) {
        this.spotflag = spotflag;
    }


    public void setCreadit(String creadit) {
        this.creadit = creadit;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
}
