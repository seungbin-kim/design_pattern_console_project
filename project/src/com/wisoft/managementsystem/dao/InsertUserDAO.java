package com.wisoft.managementsystem.dao;

import com.wisoft.managementsystem.dto.UserDTO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertUserDAO extends DAOTemplate {

    @Override
    protected String setQuery() {
        return "INSERT INTO users (no, id, pw, name, title) VALUES (?, ?, ?, ?, ?)";
    }

    @Override
    protected void bindStatement(PreparedStatement pstmt, Object object) throws SQLException {
        UserDTO userDTO = (UserDTO) object;
        pstmt.setString(1, userDTO.getNo());
        pstmt.setString(2, userDTO.getId());
        pstmt.setString(3, userDTO.getPw());
        pstmt.setString(4, userDTO.getName());
        pstmt.setString(5, userDTO.getTitle());
    }

}
