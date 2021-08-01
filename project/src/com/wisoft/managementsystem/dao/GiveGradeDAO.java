package com.wisoft.managementsystem.dao;

import com.wisoft.managementsystem.dto.CourseDTO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GiveGradeDAO extends DAOTemplate {

    @Override
    protected String setQuery() {
        return "UPDATE course SET grade = ? WHERE no = ? AND sno = ?";
    }

    @Override
    protected void bindStatement(PreparedStatement pstmt, Object object) throws SQLException {
        CourseDTO courseDTO = (CourseDTO) object;
        pstmt.setString(1, courseDTO.getGrade());
        pstmt.setString(2, courseDTO.getNo());
        pstmt.setString(3, courseDTO.getSno());
    }

}
