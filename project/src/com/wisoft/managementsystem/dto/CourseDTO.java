package com.wisoft.managementsystem.dto;

public class CourseDTO {

    private String no;
    private String sno;
    private String sname;
    private String grade;

    public CourseDTO() {
    }

    public CourseDTO(String no, String sno) {
        this.no = no;
        this.sno = sno;
        this.sname = sname;
    }

    public CourseDTO(String no, String sno, String grade) {
        this(no, sno);
        this.grade = grade;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

}
