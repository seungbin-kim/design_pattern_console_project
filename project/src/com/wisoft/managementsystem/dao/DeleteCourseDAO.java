package com.wisoft.managementsystem.dao;

import com.wisoft.managementsystem.dto.CourseDTO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteCourseDAO extends DAOTemplate {

  @Override
  protected String setQuery() {
    return "DELETE FROM course WHERE no = ? AND sno = ?";
  }

  @Override
  protected void bindStatement(PreparedStatement pstmt, Object object) throws SQLException {
    CourseDTO courseDTO = (CourseDTO) object;
    pstmt.setString(1, courseDTO.getNo());
    pstmt.setString(2, courseDTO.getSno());
  }

}
