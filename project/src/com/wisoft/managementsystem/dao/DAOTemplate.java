package com.wisoft.managementsystem.dao;

import com.wisoft.managementsystem.dto.ResultDTO;

import java.sql.*;

public abstract class DAOTemplate<T> {

    protected ResultSet resultSet;

    protected ResultDTO<T> resultDTO = new ResultDTO<>();

    protected boolean isSelect() {
        return false;
    }

    protected void selectOperation() throws SQLException {
    }

    protected abstract String setQuery();

    protected abstract void bindStatement(PreparedStatement pstmt, Object object) throws SQLException;

    final public ResultDTO<T> access(Object object) {

        String query = setQuery();

        try (Connection con = connDB();
             PreparedStatement pstmt = (con != null) ? con.prepareStatement(query) : null) {

            if (pstmt == null) return resultDTO;

            bindStatement(pstmt, object);

            if (isSelect()) {
                resultSet = pstmt.executeQuery();
                selectOperation();
            } else {
                resultDTO.setCompletedNumber(pstmt.executeUpdate());
            }

        } catch (SQLException sqex) {
            System.out.println("SQLException: " + sqex.getMessage());
            System.out.println("SQLState: " + sqex.getSQLState());
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return resultDTO;
    }

    private Connection connDB() {
        String url = "jdbc:postgresql://localhost:25432/scott"; // 원래포트는 5432
        String user = "scott";
        String pwd = "tiger";

        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(url, user, pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
