package com.wisoft.managementsystem.menu;

import com.wisoft.managementsystem.MainApplication;
import com.wisoft.managementsystem.dao.LoginDAO;
import com.wisoft.managementsystem.dto.UserDTO;

import java.util.Scanner;

public class LoginMenu {

    private LoginDAO loginDAO = new LoginDAO();

    public void login() {
        Scanner scanner = MainApplication.getScanner();

        do {
            System.out.println("――――――――――――――[ 심플 학사관리 시스템 ]――――――――――――――");
            System.out.println("――――――――――――――――로그인이 필요합니다.――――――――――――――――");
            System.out.print("ID >> ");
            String id = scanner.nextLine();
            System.out.print("PW >> ");
            String pw = scanner.nextLine();

            UserDTO userDTO = new UserDTO("", id, pw, "", "");
            if (loginDAO.access(userDTO).getIsSuccess()) {
                System.out.println("로그인 성공");
                return;
            } else {
                System.out.println("로그인 실패");
            }

        } while (true);
    }

}
