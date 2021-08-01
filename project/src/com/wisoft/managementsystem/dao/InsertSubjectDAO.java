package com.wisoft.managementsystem.dao;

import com.wisoft.managementsystem.dto.SubjectDTO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertSubjectDAO extends DAOTemplate {

    @Override
    protected String setQuery() {
        return "INSERT INTO subject(sno, sname, pno) VALUES(?, ?, ?)";
    }

    @Override
    protected void bindStatement(PreparedStatement pstmt, Object object) throws SQLException {
        SubjectDTO subjectDTO = (SubjectDTO) object;
        pstmt.setString(1, subjectDTO.getSno());
        pstmt.setString(2, subjectDTO.getSname());
        pstmt.setString(3, subjectDTO.getPno());
    }

}
