package com.wisoft.managementsystem.dao;

import com.wisoft.managementsystem.dto.UserDTO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class FindAllUsersDAO extends DAOTemplate<UserDTO> {

    @Override
    protected boolean isSelect() {
        return true;
    }

    @Override
    protected void selectOperation() throws SQLException {
        ArrayList<UserDTO> userDTOS = new ArrayList<>();

        while (resultSet.next()) {
            String no = resultSet.getString("no");
            String id = resultSet.getString("id");
            String pw = resultSet.getString("pw");
            String name = resultSet.getString("name");
            String title = resultSet.getString("title");

            UserDTO userDTO = new UserDTO();

            userDTO.setNo(no);
            userDTO.setId(id);
            userDTO.setPw(pw);
            userDTO.setName(name);
            userDTO.setTitle(title);

            userDTOS.add(userDTO);
        }
        resultDTO.setDtos(userDTOS);
        if (!userDTOS.isEmpty()) {
            resultDTO.setIsSuccess(true);
        }
    }

    @Override
    protected String setQuery() {
        return "SELECT * FROM users";
    }

    @Override
    protected void bindStatement(PreparedStatement pstmt, Object object) throws SQLException {

    }

}
