package com.pp.educational_management_system.domain;

public class Semester {
    private String semesterno;
    private String semestername;

    @Override
    public String toString() {
        return "Semester{" +
                "semesterno='" + semesterno + '\'' +
                ", semestername='" + semestername + '\'' +
                '}';
    }

    public void setSemesterno(String semesterno) {
        this.semesterno = semesterno;
    }

    public void setSemestername(String semestername) {
        this.semestername = semestername;
    }

    public String getSemesterno() {
        return semesterno;
    }

    public String getSemestername() {
        return semestername;
    }
}
