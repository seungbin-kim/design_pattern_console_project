package com.wisoft.managementsystem.dao;

import com.wisoft.managementsystem.dto.EnrolledStudentDTO;
import com.wisoft.managementsystem.dto.UserDTO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class FindEnrolledStudentsDAO extends DAOTemplate<EnrolledStudentDTO> {

    @Override
    protected boolean isSelect() {
        return true;
    }

    @Override
    protected void selectOperation() throws SQLException {
        ArrayList<EnrolledStudentDTO> dtos = new ArrayList<>();

        while (resultSet.next()) {
            String sno = resultSet.getString("과목번호");
            String sname = resultSet.getString("과목이름");
            String no = resultSet.getString("학번");
            String name = resultSet.getString("이름");

            EnrolledStudentDTO dto = new EnrolledStudentDTO();

            dto.setSno(sno);
            dto.setSname(sname);
            dto.setNo(no);
            dto.setName(name);

            dtos.add(dto);
        }
        resultDTO.setDtos(dtos);
        if (!dtos.isEmpty()) {
            resultDTO.setIsSuccess(true);
        }
    }

    @Override
    protected String setQuery() {
        return "SELECT c.sno AS 과목번호, s.sname AS 과목이름, u.no AS 학번, u.name AS 이름 "
                + "FROM course c, subject s, users u "
                + "WHERE c.sno = s.sno AND c.no = u.no AND s.pno = ? "
                + "ORDER BY c.sno ASC";
    }

    @Override
    protected void bindStatement(PreparedStatement pstmt, Object object) throws SQLException {
        UserDTO userDTO = (UserDTO) object;
        pstmt.setString(1, userDTO.getNo());
    }

}
