package com.wisoft.managementsystem.dao;

import com.wisoft.managementsystem.dto.SubjectDTO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class FindAllSubjectsDAO extends DAOTemplate<SubjectDTO> {

  @Override
  protected boolean isSelect() {
    return true;
  }

  @Override
  protected void selectOperation() throws SQLException {
    ArrayList<SubjectDTO> userDTOs = new ArrayList<>();

    while (resultSet.next()) {
      String sno = resultSet.getString("과목번호");
      String sname = resultSet.getString("과목이름");
      String pname = resultSet.getString("담당교수");

      SubjectDTO subjectDTO = new SubjectDTO();

      subjectDTO.setSno(sno);
      subjectDTO.setSname(sname);
      subjectDTO.setPname(pname);

      userDTOs.add(subjectDTO);
    }
    resultDTO.setDtos(userDTOs);
    if (!userDTOs.isEmpty()) {
      resultDTO.setIsSuccess(true);
    }
  }

  @Override
  protected String setQuery() {
    return "SELECT s.sno AS 과목번호, s.sname AS 과목이름, u.name AS 담당교수 "
        + "FROM subject s, users u WHERE s.pno = u.no";
  }

  @Override
  protected void bindStatement(PreparedStatement pstmt, Object object) throws SQLException {

  }

}
