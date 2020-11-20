package com.wisoft.managementsystem.menu;

import com.wisoft.managementsystem.MainApplication;
import com.wisoft.managementsystem.dto.CourseDTO;
import com.wisoft.managementsystem.dto.EnrolledStudentDTO;
import com.wisoft.managementsystem.dto.SubjectDTO;
import com.wisoft.managementsystem.dto.UserDTO;
import com.wisoft.managementsystem.controller.ManagementService;
import com.wisoft.managementsystem.controller.ManagementSystem;
import com.wisoft.managementsystem.controller.ProfessorInvocationHandler;

import java.lang.reflect.Proxy;
import java.util.ArrayList;

public class ProfessorMenu implements MenuStrategy {

  private ManagementService managementService = getProfessorProxy(ManagementSystem.getInstance());

  @Override
  public void display() {
    while (true) {
      try {
        lineEnter(1);
        System.out.println("――――――――――――――――――――――――――――― 교수 메뉴 ――――――――――――――――――――――――――――");
        System.out.println("――――――――――― 1. 강의 등록 ㅣ 2. 강의 삭제 ㅣ 3. 나의 강의 ―――――――――――");
        System.out.println("―――――――――― 4. 점수 등록ㅣ 5. 로그아웃 ㅣ 6. 프로그램 종료 ――――――――――");
        System.out.println("――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――");
        System.out.print(">> ");
        String selectedNumber = scanner.nextLine();

        switch (selectedNumber) {
          case "1":
            displayAddSubject();
            break;
          case "2":
            displayDeleteSubject();
          case "3":
            displayMySubject();
            break;
          case "4":
            displayGivingGrade();
            break;
          case "5":
            MainApplication.logout();
            return;
          case "6":
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

  private void displayAddSubject() throws IllegalAccessException {
    lineEnter(1);
    SubjectDTO subjectDTO = null;

    System.out.print("등록할 과목번호 >> ");
    String sno = scanner.nextLine();
    System.out.print("등록할 과목이름 >> ");
    String sname = scanner.nextLine();
    String pno = MainApplication.getUser().getNo();

    System.out.println("――――――――――――――――――――――――――― 등록할까요? ―――――――――――――――――――――――――――");
    System.out.println("과목번호 : " + sno + "\n과목이름 : " + sname + "\n등록자 : " + pno);
    System.out.print("(y/n) >> ");
    String select = scanner.nextLine();

    switch (select) {
      case "y":
        subjectDTO = new SubjectDTO(sno, sname, pno);
        break;
      case "n":
        System.out.println("등록 취소됨");
        return;
      default:
        System.out.println("잘못된 입력입니다.");
        return;
    }

    if (managementService.addSubject(subjectDTO)) {
      System.out.println("등록 성공!");
    } else {
      System.out.println("등록 실패!");
    }
  }

  private void displayDeleteSubject() throws IllegalAccessException {
    lineEnter(1);
    SubjectDTO subjectDTO = null;

    System.out.print("삭제할 과목번호 >> ");
    String sno = scanner.nextLine();
    String pno = MainApplication.getUser().getNo();

    System.out.print("정말로 삭제할까요? (y/n) >> ");
    String select = scanner.nextLine();

    switch (select) {
      case "y":
        subjectDTO = new SubjectDTO(sno, "", pno);
        break;
      case "n":
        System.out.println("삭제 취소됨");
        return;
      default:
        System.out.println("잘못된 입력입니다.");
        return;
    }

    if (managementService.deleteSubject(subjectDTO)) {
      System.out.println("삭제 성공!");
    } else {
      System.out.println("삭제 실패!");
    }
  }

  private void displayMySubject() throws IllegalAccessException {
    lineEnter(1);
    UserDTO curUserDTO = MainApplication.getUser();
    ArrayList<SubjectDTO> subjectDTOS = managementService.getMySubjects(curUserDTO);

    System.out.println("―――――――――――――――――――――――――");
    System.out.println(" 강의번호ㅣ강의 이름");
    System.out.println("―――――――――――――――――――――――――");

    for (SubjectDTO dto : subjectDTOS) {
      String sno = dto.getSno();
      String sname = dto.getSname();
      System.out.println(sno + " ㅣ " + sname);
    }
  }

  private void displayGivingGrade() throws IllegalAccessException {
    lineEnter(1);
    viewStudents();

    CourseDTO courseDTO = null;

    System.out.print("성적부여 과목 >> ");
    String sno = scanner.nextLine();
    System.out.print("성적부여 학번 >> ");
    String no = scanner.nextLine();
    System.out.print("부여할 성적 >> ");
    String grade = scanner.nextLine();

    System.out.println("―――――――――――――――――――――― 아래의 내용대로 진행할까요? ――――――――――――――――――――――");
    System.out.println("과목번호 : " + sno);
    System.out.println("학번 : " + "[" + no + "]" + " 에게 " + "[" + grade + "]" + " 부여하기");
    System.out.print("(y/n) >> ");
    String select = scanner.nextLine();

    switch (select) {
      case "y":
        courseDTO = new CourseDTO(no, sno, grade);
        break;
      case "n":
        System.out.println("점수부여 취소");
        return;
      default:
        System.out.println("올바르지 않은 입력입니다.");
        return;
    }

    if (managementService.giveGrade(courseDTO)) {
      System.out.println("성공!");
    } else {
      System.out.println("실패!");
    }
  }

  private void viewStudents() throws IllegalAccessException {
    lineEnter(1);
    UserDTO curUserDTO = MainApplication.getUser();
    ArrayList<EnrolledStudentDTO> dtos = managementService.getMyStudents(curUserDTO);

    System.out.println("―――――――――――――――――――――――――――――――――――――――――――――――――――――");
    System.out.println(" 강의번호ㅣ     강의 이름     ㅣ 학생번호 ㅣ 학생이름");
    System.out.println("―――――――――――――――――――――――――――――――――――――――――――――――――――――");

    for (EnrolledStudentDTO dto : dtos) {
      String sno = dto.getSno();
      String sname = dto.getSname();
      String no = dto.getNo();
      String name = dto.getName();
      System.out.println(sno + " ㅣ " + sname + " ㅣ " + no + " ㅣ " + name);
    }
//    System.out.println(managementService.giveGrade(new Course())); // 보호프록시 테스트
  }

  private ManagementService getProfessorProxy(ManagementService managementService) {
    return (ManagementService) Proxy.newProxyInstance(
        managementService.getClass().getClassLoader(),
        managementService.getClass().getInterfaces(),
        new ProfessorInvocationHandler(managementService));
  }

}
