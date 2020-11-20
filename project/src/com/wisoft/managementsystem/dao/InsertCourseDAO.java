package com.wisoft.managementsystem.dao;

import com.wisoft.managementsystem.dto.CourseDTO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertCourseDAO extends DAOTemplate {

  @Override
  protected String setQuery() {
    return "INSERT INTO course(no, sno) VALUES(?, ?)";
  }

  @Override
  protected void bindStatement(PreparedStatement pstmt, Object object) throws SQLException {
    CourseDTO courseDTO = (CourseDTO) object;
    pstmt.setString(1, courseDTO.getNo());
    pstmt.setString(2, courseDTO.getSno());
  }

}
