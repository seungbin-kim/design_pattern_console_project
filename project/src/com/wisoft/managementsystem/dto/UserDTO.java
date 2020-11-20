package com.wisoft.managementsystem.dto;

public class UserDTO {

  private String no;
  private String id;
  private String pw;
  private String name;
  private String title;

  public UserDTO() {

  }

  public UserDTO(String no, String id, String pw, String name, String title) {
    this.no = no;
    this.id = id;
    this.pw = pw;
    this.name = name;
    this.title = title;
  }

  public String getNo() {
    return no;
  }

  public void setNo(String no) {
    this.no = no;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getPw() {
    return pw;
  }

  public void setPw(String pw) {
    this.pw = pw;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
