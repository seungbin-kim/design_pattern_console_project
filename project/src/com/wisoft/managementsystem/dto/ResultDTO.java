package com.wisoft.managementsystem.dto;

import java.util.ArrayList;

public class ResultDTO<T> {

  private ArrayList<T> dtos;
  private boolean isSuccess;
  private int completedNumber;

  public ResultDTO() {

  }

  public ArrayList<T> getDtos() {
    return dtos;
  }

  public void setDtos(ArrayList<T> dtos) {
    this.dtos = dtos;
  }

  public boolean getIsSuccess() {
    return isSuccess;
  }

  public void setIsSuccess(boolean success) {
    isSuccess = success;
  }

  public int getCompletedNumber() {
    return completedNumber;
  }

  public void setCompletedNumber(int completedNumber) {
    this.completedNumber = completedNumber;
    this.isSuccess = completedNumber >= 1;
  }

}
