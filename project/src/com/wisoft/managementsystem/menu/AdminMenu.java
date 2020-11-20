package com.wisoft.managementsystem.menu;

import com.wisoft.managementsystem.MainApplication;
import com.wisoft.managementsystem.dto.UserDTO;
import com.wisoft.managementsystem.service.AdminInvocationHandler;
import com.wisoft.managementsystem.service.ManagementService;
import com.wisoft.managementsystem.service.ManagementSystem;

import java.lang.reflect.Proxy;
import java.util.ArrayList;

public class AdminMenu implements MenuStrategy {

  private ManagementService managementService = getAdminProxy(ManagementSystem.getInstance());

  @Override
  public void display() {
    while (true) {
      try {
        lineEnter(1);
        System.out.println("――――――――――――――――――――――――――――― 관리자 메뉴 ――――――――――――――――――――――――――――");
        System.out.println("―――――――― 1. 유저 등록하기 ㅣ 2. 유저 삭제하기 ㅣ 3. 유저 목록 ――――――――");
        System.out.println("――――――――――――――――――― 4. 로그아웃 ㅣ 5. 프로그램 종료 ――――――――――――――――――");
        System.out.println("――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――");
        System.out.print(">> ");
        String selectedNumber = scanner.nextLine();

        switch (selectedNumber) {
          case "1":
            displayAddUser();
            break;
          case "2":
            displayDeleteUser();
            break;
          case "3":
            displayAllUsers();
            break;
          case "4":
            MainApplication.logout();
            return;
          case "5":
            MainApplication.setExitFlag(true);
            return;
          default:
            System.out.println("올바른 입력이 아닙니다.");
        }
      } catch (IllegalAccessException e) {
        System.out.println("잘못된 접근입니다.");
      }
    }
  }

  private void displayAddUser() throws IllegalAccessException{
    lineEnter(1);
    System.out.print("등록할 번호(학번) >> ");
    String no = scanner.nextLine();
    System.out.print("등록할 ID >> ");
    String id = scanner.nextLine();
    System.out.print("등록할 PW >> ");
    String pw = scanner.nextLine();
    System.out.print("등록할 이름 >> ");
    String name = scanner.nextLine();

    System.out.println("―――――――――――――――――――유저 유형―――――――――――――――――――");
    System.out.println("――――――――1. 관리자 ㅣ 2. 교수 ㅣ 3. 학생――――――――");
    System.out.print(">> ");
    String selectedNumber = scanner.nextLine();

    String title = "";
    switch (selectedNumber) {
      case "1":
        title = "관리자";
        break;
      case "2":
        title = "교수";
        break;
      case "3":
        title = "학생";
        break;
      default:
        System.out.println("올바른 입력이 아닙니다.");
        return;
    }

    UserDTO userDTO = new UserDTO(no, id, pw, name, title);

    if (managementService.addUser(userDTO)) {
      System.out.println("등록 성공!");
    } else {
      System.out.println("등록 실패!");
    }
  }

  private void displayDeleteUser() throws IllegalAccessException {
    lineEnter(1);
    UserDTO userDTO = null;

    System.out.print("삭제할 번호(학번) >> ");
    String no = scanner.nextLine();

    System.out.println("정말로 삭제할까요?(y/n) >> ");
    String answer = scanner.nextLine();


    switch (answer) {
      case "y":
        userDTO = new UserDTO(no, "", "", "", "");
        break;
      case "n":
        System.out.println("삭제 취소됨");
        return;
      default:
        System.out.println("잘못된 입력입니다.");
        return;
    }

    if (managementService.deleteUser(userDTO)) {
      System.out.println("삭제 성공!");
    } else {
      System.out.println("삭제 실패!");
    }
  }

  private void displayAllUsers() throws IllegalAccessException {
    lineEnter(1);
    ArrayList<UserDTO> userDTOS = managementService.getAllUsers();

    System.out.println("――――――――――――――――――――――――――――――――――――――――――――――――――――――");
    System.out.println("   번호   ㅣ   ID   ㅣ   PW   ㅣ   이름   ㅣ   직책");
    System.out.println("――――――――――――――――――――――――――――――――――――――――――――――――――――――");

    for (UserDTO dto : userDTOS) {
      String no = dto.getNo();
      String id = dto.getId();
      String pw = dto.getPw();
      String name = dto.getName();
      String title = dto.getTitle();
      System.out.println("ㅣ" + no + "ㅣ" + id + "ㅣ" + pw + "ㅣ" + name + "ㅣ" + title + "ㅣ");
    }
//    System.out.println(managementService.giveGrade(new CourseDTO())); // 보호프록시 테스트
  }

  private ManagementService getAdminProxy(ManagementService managementService) {
    return (ManagementService) Proxy.newProxyInstance(
        managementService.getClass().getClassLoader(),
        managementService.getClass().getInterfaces(),
        new AdminInvocationHandler(managementService));
  }

}
