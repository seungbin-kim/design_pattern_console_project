package com.wisoft.managementsystem.dto;

public class SubjectDTO {

  private String sno;
  private String sname;
  private String pno;
  private String pname;

  public SubjectDTO() {

  }

  public SubjectDTO(String sno, String sname, String pno) {
    this.sno = sno;
    this.sname = sname;
    this.pno = pno;
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

  public String getPno() {
    return pno;
  }

  public void setPno(String pno) {
    this.pno = pno;
  }

  public String getPname() {
    return pname;
  }

  public void setPname(String pname) {
    this.pname = pname;
  }

}
