package com.wisoft.managementsystem.dao;

import com.wisoft.managementsystem.dto.SubjectDTO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteSubjectDAO extends DAOTemplate {

  @Override
  protected String setQuery() {
    return "DELETE FROM subject WHERE sno = ? AND pno = ?";
  }

  @Override
  protected void bindStatement(PreparedStatement pstmt, Object object) throws SQLException {
    SubjectDTO subjectDTO = (SubjectDTO) object;
    pstmt.setString(1, subjectDTO.getSno());
    pstmt.setString(2, subjectDTO.getPno());
  }

}
