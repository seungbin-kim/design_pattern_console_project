package com.wisoft.managementsystem;

import com.wisoft.managementsystem.dto.UserDTO;
import com.wisoft.managementsystem.menu.LoginMenu;
import com.wisoft.managementsystem.menu.MenuStrategy;

import java.util.Scanner;

public class MainApplication {

  static final private Scanner scanner = new Scanner(System.in);
  static private MenuStrategy menuStrategy;
  static private UserDTO userDTO;
  static private boolean exitFlag = false;

  public static void main(String... args) {
    do {
      LoginMenu loginMenu = new LoginMenu();
      loginMenu.login();
      menuStrategy.display();
    } while (!exitFlag);
  }

  public static Scanner getScanner() {
    return scanner;
  }

  public static void setMenuStrategy(MenuStrategy menuStrategy) {
    MainApplication.menuStrategy = menuStrategy;
  }

  public static UserDTO getUser() {
    return userDTO;
  }

  public static void setUser(UserDTO userDTO) {
    MainApplication.userDTO = userDTO;
  }

  public static void setExitFlag(boolean exitFlag) {
    MainApplication.exitFlag = exitFlag;
  }

  public static void logout() {
    MainApplication.setUser(null);
    MainApplication.setMenuStrategy(null);
  }

}
