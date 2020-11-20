package com.wisoft.managementsystem.dao;

import com.wisoft.managementsystem.dto.CourseDTO;
import com.wisoft.managementsystem.dto.UserDTO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class FindMyCoursesDAO extends DAOTemplate<CourseDTO> {

  @Override
  protected boolean isSelect() {
    return true;
  }

  @Override
  protected void selectOperation() throws SQLException {
    ArrayList<CourseDTO> dtos = new ArrayList<>();

    while (resultSet.next()) {
      String sno = resultSet.getString("과목번호");
      String sname = resultSet.getString("과목이름");
      String grade = resultSet.getString("성적");

      CourseDTO courseDTO = new CourseDTO();

      courseDTO.setSno(sno);
      courseDTO.setSname(sname);
      courseDTO.setGrade((grade == null) ? "미입력" : grade);

      dtos.add(courseDTO);
    }
    resultDTO.setDtos(dtos);
    if (!dtos.isEmpty()) {
      resultDTO.setIsSuccess(true);
    }
  }

  @Override
  protected String setQuery() {
    return "SELECT c.sno AS 과목번호, s.sname AS 과목이름, c.grade AS 성적 "
        + "FROM course c, subject s WHERE c.sno = s.sno AND no = ?";
  }

  @Override
  protected void bindStatement(PreparedStatement pstmt, Object object) throws SQLException {
    UserDTO userDTO = (UserDTO) object;
    pstmt.setString(1, userDTO.getNo());
  }

}
