package com.wisoft.managementsystem.dao;

import com.wisoft.managementsystem.MainApplication;
import com.wisoft.managementsystem.dto.UserDTO;
import com.wisoft.managementsystem.menu.AdminMenu;
import com.wisoft.managementsystem.menu.ProfessorMenu;
import com.wisoft.managementsystem.menu.StudentMenu;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoginDAO extends DAOTemplate {

  @Override
  protected void selectOperation() throws SQLException {
    if (resultSet.next()) {
      String no = resultSet.getString("no");
      String id = resultSet.getString("id");
      String pw = resultSet.getString("pw");
      String name = resultSet.getString("name");
      String title = resultSet.getString("title");

      UserDTO userDTO = new UserDTO();

      switch (title) {
        case "관리자":
          MainApplication.setMenuStrategy(new AdminMenu());
          break;
        case "교수":
          MainApplication.setMenuStrategy(new ProfessorMenu());
          break;
        case "학생":
          MainApplication.setMenuStrategy(new StudentMenu());
          break;
      }

      userDTO.setNo(no);
      userDTO.setId(id);
      userDTO.setPw(pw);
      userDTO.setName(name);
      MainApplication.setUser(userDTO);
      resultDTO.setIsSuccess(true);
      return;
    }
    resultDTO.setIsSuccess(false);
  }

  @Override
  protected void bindStatement(PreparedStatement pstmt, Object object) throws SQLException {
    UserDTO userDTO = (UserDTO) object;
    String id = userDTO.getId();
    String pw = userDTO.getPw();
    pstmt.setString(1, id);
    pstmt.setString(2, pw);
  }

  @Override
  protected String setQuery() {
    return "SELECT * FROM users WHERE id = ? AND pw = ?";
  }

  @Override
  protected boolean isSelect() {
    return true;
  }


}
