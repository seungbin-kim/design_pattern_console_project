package com.wisoft.managementsystem.menu;

import com.wisoft.managementsystem.MainApplication;
import com.wisoft.managementsystem.dto.CourseDTO;
import com.wisoft.managementsystem.dto.SubjectDTO;
import com.wisoft.managementsystem.dto.UserDTO;
import com.wisoft.managementsystem.service.ManagementService;
import com.wisoft.managementsystem.service.ManagementSystem;
import com.wisoft.managementsystem.service.StudentInvocationHandler;

import java.lang.reflect.Proxy;
import java.util.ArrayList;

public class StudentMenu implements MenuStrategy {

    ManagementService managementService = getStudentProxy(ManagementSystem.getInstance());

    @Override
    public void display() {
        while (true) {
            try {
                lineEnter(1);
                System.out.println("――――――――――――――――――――――――――――― 학생 메뉴 ――――――――――――――――――――――――――――");
                System.out.println("―――――――――――― 1. 수강신청 ㅣ 2. 수강취소 ㅣ 3. 나의 강의 ――――――――――――");
                System.out.println("―――――――――――――――――― 4. 로그아웃 ㅣ 5. 프로그램 종료 ―――――――――――――――――");
                System.out.println("――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――");
                System.out.print(">> ");
                String seletedNumber = scanner.nextLine();

                switch (seletedNumber) {
                    case "1":
                        displayCourseEnrolment();
                        break;
                    case "2":
                        displayCourseCancel();
                        break;
                    case "3":
                        displayMyCourse();
                        break;
                    case "4":
                        MainApplication.logout();
                        return;
                    case "5":
                        MainApplication.setExitFlag(true);
                        return;
                    default:
                        System.out.println("올바르지 않은 입력입니다.");
                }
            } catch (IllegalAccessException e) {
                System.out.println("잘못된 접근입니다.");
            }
        }
    }

    private void displayCourseEnrolment() throws IllegalAccessException {
        lineEnter(1);
        viewAllSubjects();

        CourseDTO courseDTO = null;

        String no = MainApplication.getUser().getNo();
        System.out.print("신청할 과목 번호 >> ");
        String sno = scanner.nextLine();

        System.out.println("―――――――――――――――신청할까요?―――――――――――――――");
        System.out.println("과목번호 : " + sno + "\n신청인 : " + no);
        System.out.print("(y/n) >> ");
        String select = scanner.nextLine();

        switch (select) {
            case "y":
                courseDTO = new CourseDTO(no, sno);
                break;
            case "n":
                System.out.println("신청 취소됨");
                return;
            default:
                System.out.println("잘못된 입력입니다.");
                return;
        }

        if (managementService.registerCourse(courseDTO)) {
            System.out.println("신청 성공!");
        } else {
            System.out.println("신청 실패!");
        }
    }

    private void displayCourseCancel() throws IllegalAccessException {
        lineEnter(1);
        displayMyCourse();

        CourseDTO courseDTO = null;

        String no = MainApplication.getUser().getNo();
        System.out.print("삭제할 과목 번호 >> ");
        String sno = scanner.nextLine();

        System.out.println("과목번호 " + "[" + sno + "]" + " 을 삭제할까요?");
        System.out.print("(y/n) >> ");
        String select = scanner.nextLine();

        switch (select) {
            case "y":
                courseDTO = new CourseDTO(no, sno);
                break;
            case "n":
                System.out.println("삭제 취소됨");
                return;
            default:
                System.out.println("올바르지 않은 입력입니다.");
                return;
        }

        if (managementService.cancelCourse(courseDTO)) {
            System.out.println("삭제 성공!");
        } else {
            System.out.println("삭제 실패!");
        }
    }

    private void displayMyCourse() throws IllegalAccessException {
        lineEnter(1);
        UserDTO curUserDTO = MainApplication.getUser();
        ArrayList<CourseDTO> cours = managementService.getMyCourse(curUserDTO);

        System.out.println("―――――――――――――――――――――――――――――――――――――――――――");
        System.out.println(" 과목번호 ㅣ     강의 이름     ㅣ 성적");
        System.out.println("―――――――――――――――――――――――――――――――――――――――――――");

        for (CourseDTO dto : cours) {
            String sno = dto.getSno();
            String sname = dto.getSname();
            String grade = dto.getGrade();
            System.out.println(sno + " ㅣ " + sname + " ㅣ " + grade);
        }
    }

    private void viewAllSubjects() throws IllegalAccessException {
        lineEnter(1);
        ArrayList<SubjectDTO> dtos = managementService.getAllSubjects();

        System.out.println("―――――――――――――――――――――――――――――――――――――――――――");
        System.out.println(" 강의번호 ㅣ     강의 이름     ㅣ 교수 이름");
        System.out.println("―――――――――――――――――――――――――――――――――――――――――――");

        for (SubjectDTO dto : dtos) {
            String sno = dto.getSno();
            String sname = dto.getSname();
            String pname = dto.getPname();
            System.out.println(sno + " ㅣ " + sname + " ㅣ " + pname);
        }
//    System.out.println(managementService.giveGrade(new CourseDTO())); // 보호프록시 테스트
    }

    private ManagementService getStudentProxy(ManagementService managementService) {
        return (ManagementService) Proxy.newProxyInstance(
                managementService.getClass().getClassLoader(),
                managementService.getClass().getInterfaces(),
                new StudentInvocationHandler(managementService));
    }

}
