package com.wisoft.managementsystem.dao;

import com.wisoft.managementsystem.dto.SubjectDTO;
import com.wisoft.managementsystem.dto.UserDTO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class FindMySubjectsDAO extends DAOTemplate<SubjectDTO> {

    @Override
    protected boolean isSelect() {
        return true;
    }

    @Override
    protected void selectOperation() throws SQLException {
        ArrayList<SubjectDTO> subjectDTOS = new ArrayList<>();

        while (resultSet.next()) {
            String sno = resultSet.getString("sno");
            String sname = resultSet.getString("sname");

            SubjectDTO subjectDTO = new SubjectDTO();

            subjectDTO.setSno(sno);
            subjectDTO.setSname(sname);

            subjectDTOS.add(subjectDTO);
        }
        resultDTO.setDtos(subjectDTOS);
        if (!subjectDTOS.isEmpty()) {
            resultDTO.setIsSuccess(true);
        }
    }

    @Override
    protected String setQuery() {
        return "SELECT sno, sname FROM subject WHERE pno = ?";
    }

    @Override
    protected void bindStatement(PreparedStatement pstmt, Object object) throws SQLException {
        UserDTO userDTO = (UserDTO) object;
        pstmt.setString(1, userDTO.getNo());
    }

}
