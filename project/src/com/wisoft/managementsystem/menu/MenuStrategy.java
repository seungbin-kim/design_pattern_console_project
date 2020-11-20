package com.wisoft.managementsystem.menu;

import com.wisoft.managementsystem.MainApplication;

import java.util.Scanner;

public interface MenuStrategy {

  Scanner scanner = MainApplication.getScanner();

  void display();

  default void lineEnter(int n) {
    for (int i = 0; i < n; i++) {
      System.out.println();
    }
  }

}
