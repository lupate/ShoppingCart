/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Login;

/**
 *
 * @author Muhammad Lupate
 */
public class LoginService {

    static String driver = "com.mysql.jdbc.Driver";
    static String url = "jdbc:mysql://localhost:3306/scart";
    String unicode = "?useUnicode=yes&characterEncoding=UTF-8";
    static String username = "root";
    static String password = "root";

    private Connection connection;

    public int checkUserExistence(Login logindata) {
        try {

            Class.forName(driver);
            connection = DriverManager.getConnection(url + unicode, username, password);

            Statement stmnt = connection.createStatement();
            String query = "select user_id from user where email ='" + logindata.getName() + "'and password = '" + logindata.getPassword() + "'";
            ResultSet rs = stmnt.executeQuery(query);
            if (rs.next()) {
                return rs.getInt("user_id");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex);
            return 0;
        }
        return 0;
    }

}
