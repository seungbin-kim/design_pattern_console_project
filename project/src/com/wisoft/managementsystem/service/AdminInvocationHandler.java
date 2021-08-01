package com.wisoft.managementsystem.service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AdminInvocationHandler implements InvocationHandler {

    ManagementService managementService;

    public AdminInvocationHandler(ManagementService managementService) {
        this.managementService = managementService;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws IllegalAccessException {
        try {
            if (method.getName().equals("addUser")) {
                return method.invoke(managementService, args);
            } else if (method.getName().equals("deleteUser")) {
                return method.invoke(managementService, args);
            } else if (method.getName().equals("getAllUsers")) {
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
