package com.wisoft.managementsystem.dao;

import com.wisoft.managementsystem.dto.UserDTO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteUserDAO extends DAOTemplate {

  @Override
  protected String setQuery() {
    return "DELETE FROM users WHERE no = ?";
  }

  @Override
  protected void bindStatement(PreparedStatement pstmt, Object object) throws SQLException {
    String no = ((UserDTO) object).getNo();
    pstmt.setString(1, no);
  }

}
