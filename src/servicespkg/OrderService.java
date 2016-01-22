package servicespkg;

import java.sql.*;
import exceptions.*;
import beanspkg.*;
import java.util.ArrayList;

public class OrderService {

    public Order[] selectAll() throws SQLException {
        Connection connection = null;
        Order[] arr = null;
        try {
            connection = DbService.getConnection();
            ArrayList<Order> list = new ArrayList<Order>();
            Order item;
            Statement stmnt = connection.createStatement();
            ResultSet rs = stmnt.executeQuery("SELECT * FROM order");
            while (rs.next()) {
                item = new Order();
                item.setOrderId(rs.getLong(1));
                item.setUserId(rs.getInt(2));
                item.setStatus(rs.getLong(3));
                item.setDeliveryDate(rs.getTimestamp(4));
                item.setAddress(rs.getString(5));
                list.add(item);
            }

            arr = new Order[list.size()];
            list.toArray(arr);
        } finally {
            if (connection != null) {
                connection.close();
            }
            return arr;
        }
    }
    
    //new select
    public ResultSet selectDataAsRSAdmin() throws SQLException {
        Connection connection = null;
        Order[] arr = null;

        connection = DbService.getConnection();
        ArrayList<Order> list = new ArrayList<Order>();
        Order item;
        Statement stmnt = connection.createStatement();
        ResultSet rs = stmnt.executeQuery("SELECT order_id as ID , Full_name as Customer_name , delivery_date as Date,status as Status ,order.address as Address FROM `scart`.`user` join `scart`.`order` on user_type = 3 and order.user_id=user.user_id"); //and user.user_id=order.user_id   and product.user_id=user.user_id  order by user.user_id

        return rs;
    }
//////////////
     //new select * products for admin
    public ResultSet selectProductsAsRSAdmin() throws SQLException {
        Connection connection = null;
        connection = DbService.getConnection();
        Statement stmnt = connection.createStatement();
        ResultSet rs = stmnt.executeQuery("SELECT product.prod_id as ID , Prod_name as Product_Name , Full_name as Supplier_Name , cat_desc as Category , company as Company , Price FROM `scart`.`user` join `scart`.`category` join `scart`.`product` join `scart`.`user_rate_product` on user.user_id = product.user_id and category.cat_id = Product.cat_id and product.prod_id = user_rate_product.prod_id order by like_count desc ");
        return rs;
    }
//////////////

    //make select return result set
    public ResultSet selectDataAsRS() throws SQLException {
        Connection connection = null;
        connection = DbService.getConnection();
        Statement stmnt = connection.createStatement();
        ResultSet rs = stmnt.executeQuery("SELECT order_id as ID , Full_name as CustomerName , delivery_date as DeliveryDate, order.address as Address FROM `scart`.`user` join `scart`.`order` on user_type = 3 and user.user_id=order.user_id order by user.user_id");
        return rs;
    }

    public ResultSet selectDataWithConditionAsRS() throws SQLException {
        Connection connection = null;
        connection = DbService.getConnection();
        Statement stmnt = connection.createStatement();
        ResultSet rs = stmnt.executeQuery("SELECT order_id as ID , Full_name as CustomerName , delivery_date as DeliveryDate, order.address as Address FROM `scart`.`user` join `scart`.`order` on user_type = 3 and user.user_id=order.user_id and status = 0 order by user.user_id");
        return rs;
    }

    public Order selectOne(Long orderId) throws SQLException {
        Connection connection = null;
        Order item = null;
        connection = DbService.getConnection();
        Statement stmnt = connection.createStatement();
        ResultSet rs = stmnt.executeQuery("SELECT * FROM `scart`.`order` WHERE order_id = " + orderId);
        int count = 0;
        while (rs.next()) {
            count++;
            if (count == 1) {
                item = new Order();
                item.setOrderId(rs.getLong(1));
                item.setUserId(rs.getInt(2));
                item.setStatus(rs.getLong(3));
                item.setDeliveryDate(rs.getTimestamp(4));
                item.setAddress(rs.getString(5));
            } else {
                throw new MoreThanOneItemException();
            }
        }
        return item;
    }

    public int insert(Order item) throws SQLException {
        Connection connection = null;
            connection = DbService.getConnection();
            Statement stmnt = connection.createStatement();
            String insertQuery = "INSERT INTO `scart`.`order` VALUES(" +item.getOrderId() 
                    + ", "+ item.getUserId()
                    + ", " + item.getStatus() 
                    +", '" +item.getDeliveryDate()
                    + "', '" + item.getAddress() + "')";
            insertQuery = insertQuery.replace("'null'", "null");
            int rowsAffected = stmnt.executeUpdate(insertQuery);
            if (rowsAffected != 0) {
                return rowsAffected;
            } else {
                return -1;
            }
    }

    public int update(Order item) throws SQLException {
        Connection connection = null;
        connection = DbService.getConnection();
        Statement stmnt = connection.createStatement();
        String updateQuery = "UPDATE `scart`.`order` SET status = " + item.getStatus()
                + ", delivery_date = '" + item.getDeliveryDate()
                + "', address = '" + item.getAddress() + "' WHERE" + " order_id = " + item.getOrderId();
        updateQuery = updateQuery.replace("'null'", "null");
        int rowsAffected = stmnt.executeUpdate(updateQuery);
        if (rowsAffected != 0) {
            return rowsAffected;
        } else {
            return -1;
        }
    }

  
    
    public int delete(long orderId) throws SQLException {
        Connection connection = null;
        try {
            connection = DbService.getConnection();
            Statement stmnt = connection.createStatement();
            int rowsAffected = stmnt.executeUpdate("DELETE FROM order WHERE order_id = " + orderId);
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
