package com.wisoft.managementsystem.controller;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ProfessorInvocationHandler implements InvocationHandler {

  ManagementService managementService;

  public ProfessorInvocationHandler(ManagementService managementService) {
    this.managementService = managementService;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws IllegalAccessException {
    try {
      if (method.getName().equals("addSubject")) {
        return method.invoke(managementService, args);
      } else if (method.getName().equals("deleteSubject")) {
        return method.invoke(managementService, args);
      } else if (method.getName().equals("getMySubjects")) {
        return method.invoke(managementService, args);
      } else if (method.getName().equals("getMyStudents")) {
        return method.invoke(managementService, args);
      } else if (method.getName().equals("giveGrade")) {
        return method.invoke(managementService, args);
      } else {
        throw new IllegalAccessException();
      }
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    }
    return null;
  }

}
