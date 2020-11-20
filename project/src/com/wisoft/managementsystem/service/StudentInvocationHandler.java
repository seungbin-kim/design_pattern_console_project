package com.wisoft.managementsystem.service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class StudentInvocationHandler implements InvocationHandler {

  ManagementService managementService;

  public StudentInvocationHandler(ManagementService managementService) {
    this.managementService = managementService;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws IllegalAccessException {
    try {
      if (method.getName().equals("getAllSubjects")) {
        return method.invoke(managementService, args);
      } else if (method.getName().equals("getMyCourse")) {
        return method.invoke(managementService, args);
      } else if (method.getName().equals("registerCourse")) {
        return method.invoke(managementService, args);
      } else if (method.getName().equals("cancelCourse")) {
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
