package com.example.uiuhostelmanagement.util;



import java.sql.*;

public class DatabaseConnection {
    private Connection connection;

    public DatabaseConnection() throws SQLException, ClassNotFoundException {
        this.connection = getConnection();
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(
                "jdbc:mysql://" + "localhost" + ":" + "3306" + "/" + "uiuhostel", "root", ""
        );

        return connection;
    }

    public void insideData(String sql) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(sql);
        connection.close();
    }

    public ResultSet queryData(String sql) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(sql);
    }

}
