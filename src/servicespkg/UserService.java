package servicespkg;

import java.sql.*;
import exceptions.*;
import beanspkg.*;
import java.util.ArrayList;
import dto.Login;

public class UserService {

    public User[] selectAll() throws SQLException {
        Connection connection = null;
        User[] arr = null;
        try {
            connection = DbService.getConnection();
            ArrayList<User> list = new ArrayList<User>();
            User item;
            Statement stmnt = connection.createStatement();
            ResultSet rs = stmnt.executeQuery("SELECT * FROM user");
            while (rs.next()) {
                item = new User();
                item.setUserId(rs.getLong(1));
                item.setFullName(rs.getString(2));
                item.setAddress(rs.getString(3));
                item.setCity(rs.getLong(4));
                item.setEmail(rs.getString(5));
                item.setPassword(rs.getString(6));
                item.setPhone(rs.getLong(7));
                item.setPhoto(rs.getBlob(8));
                item.setUserType(rs.getLong(9));
                item.setCompany(rs.getString(10));
                list.add(item);
            }

            arr = new User[list.size()];
            list.toArray(arr);
        } finally {
            if (connection != null) {
                connection.close();
            }
            return arr;
        }
    }

    public User selectOne(long userId) throws SQLException {
        Connection connection = null;
        User item = null;
        try {
            connection = DbService.getConnection();
            Statement stmnt = connection.createStatement();
            ResultSet rs = stmnt.executeQuery("SELECT * FROM user WHERE user_id = " + userId);
            int count = 0;
            while (rs.next()) {
                count++;
                if (count == 1) {
                    item = new User();
                    item.setUserId(rs.getLong(1));
                    item.setFullName(rs.getString(2));
                    item.setAddress(rs.getString(3));
                    item.setCity(rs.getLong(4));
                    item.setEmail(rs.getString(5));
                    item.setPassword(rs.getString(6));
                    item.setPhone(rs.getLong(7));
                    item.setPhoto(rs.getBlob(8));
                    item.setUserType(rs.getLong(9));
                    item.setCompany(rs.getString(10));
                } else {
                    throw new MoreThanOneItemException();
                }

            }

            rs.close();
            stmnt.close();
        } finally {
            if (connection != null) {
                connection.close();
            }
            return item;
        }

    }

    public User login(Login logindata) throws SQLException {
        Connection connection = null;
        User item = null;
        try {
            connection = DbService.getConnection(); // Singleton design pattern
            Statement stmnt = connection.createStatement();
            ResultSet rs = stmnt.executeQuery("select * from user where email ='" + logindata.getName() + "'and password = '" + logindata.getPassword() + "'");
            int count = 0;
            while (rs.next()) {
                count++;
                if (count == 1) {
                    item = new beanspkg.User();
                    item.setUserId(rs.getLong(1));
                    item.setFullName(rs.getString(2));
                    item.setAddress(rs.getString(3));
                    item.setCity(rs.getLong(4));
                    item.setEmail(rs.getString(5));
                    item.setPassword(rs.getString(6));
                    item.setPhone(rs.getLong(7));
                    item.setPhoto(rs.getBlob(8));
                    item.setUserType(rs.getLong(9));
                    item.setCompany(rs.getString(10));
                } else {
                    throw new MoreThanOneItemException();
                }
            }

            rs.close();
            stmnt.close();
        } finally {
            if (connection != null) {
                connection.close();
            }
            return item;
        }
    }

    public int insert(User item) throws SQLException {
        Connection connection = null;
        try {
            connection = DbService.getConnection();
            Statement stmnt = connection.createStatement();
            String insertQuery = "INSERT INTO user VALUES(" + item.getUserId()
                    + ", '" + item.getFullName()
                    + "', '" + item.getAddress()
                    + "', " + item.getCity()
                    + ", '" + item.getEmail()
                    + "', '" + item.getPassword()
                    + "', " + item.getPhone()
                    + ", " + item.getPhoto()
                    + ", " + item.getUserType()
                    + ", '" + item.getCompany() + "')";
            insertQuery = insertQuery.replace("'null'", "null");
            int rowsAffected = stmnt.executeUpdate(insertQuery);
            stmnt.close();
            if (rowsAffected != 0) {
                return rowsAffected;
            } else {
                return -1;
            }

        } finally {
            if (connection != null) {
                connection.close();
            }
        }

    }

    public int update(User item) throws SQLException {
        Connection connection = null;
        try {
            connection = DbService.getConnection();
            Statement stmnt = connection.createStatement();
            String updateQuery = "UPDATE user SET full_name = '" + item.getFullName()
                    + "', address = '" + item.getAddress()
                    + "', city = " + item.getCity()
                    + ", email = '" + item.getEmail()
                    + "', password = '" + item.getPassword()
                    + "', phone = " + item.getPhone()
                    + ", photo = " + item.getPhoto()
                    + ", user_type = " + item.getUserType()
                    + ", company = '" + item.getCompany() + "' WHERE " + "user_id = " + item.getUserId();
            updateQuery = updateQuery.replace("'null'", "null");
            int rowsAffected = stmnt.executeUpdate(updateQuery);
            stmnt.close();
            if (rowsAffected != 0) {
                return rowsAffected;
            } else {
                return -1;
            }

        } finally {
            if (connection != null) {
                connection.close();
            }
        }

    }

    public int delete(long userId) throws SQLException {
        Connection connection = null;
        try {
            connection = DbService.getConnection();
            Statement stmnt = connection.createStatement();
            int rowsAffected = stmnt.executeUpdate("DELETE FROM user WHERE user_id = " + userId);
            stmnt.close();
            if (rowsAffected != 0) {
                return rowsAffected;
            } else {
                return -1;
            }

        } finally {
            if (connection != null) {
                connection.close();
            }
        }

    }

}