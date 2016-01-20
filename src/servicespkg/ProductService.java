package servicespkg;

import java.sql.*;
import exceptions.*;
import beanspkg.*;
import java.util.ArrayList;

public class ProductService {

    Connection connection = null;

    public Product[] selectAll() throws SQLException {
        Product[] arr = null;

        connection = DbService.getConnection();
        ArrayList<Product> list = new ArrayList<Product>();
        Product item;
        Statement stmnt = connection.createStatement();
        ResultSet rs = stmnt.executeQuery("SELECT * FROM product");
        while (rs.next()) {
            item = new Product();
            item.setProdId(rs.getLong(1));
            item.setProdSize(rs.getLong(2));
            item.setProdCondition(rs.getLong(3));
            item.setInStock(rs.getLong(4));
            item.setPhoto(rs.getBlob(5));
            item.setPrice(rs.getLong(6));
            item.setOffSale(rs.getLong(7));
            item.setUserId(rs.getLong(8));
            item.setCatId(rs.getLong(9));
            item.setProdName(rs.getString(10));
            item.setProdColor(rs.getString(11));
            list.add(item);
        }

        arr = new Product[list.size()];
        list.toArray(arr);
        return arr;

    }

    public ResultSet selectAllProduts() throws SQLException {
        connection = DbService.getConnection();
        Statement stmnt = connection.createStatement();
        ResultSet rs = stmnt.executeQuery("SELECT prod_id as ID , Prod_name as Product_Name , Full_name as Supplier_Name , cat_desc as Category , company as Company , Price , Off_sale as Offer FROM `scart`.`user` join `scart`.`category` join `scart`.`product` on user.user_type = 2 and user.user_id = product.user_id and category.cat_id = Product.cat_id");
        return rs;
    }

    //Select Products that have been searched for by Category
    public ResultSet selectProdutsSearchedByCategory(String str) throws SQLException {
        connection = DbService.getConnection();
        Statement stmnt = connection.createStatement();
        ResultSet rs = stmnt.executeQuery("SELECT prod_id as ID , Prod_name as Product_Name , Full_name as Supplier_Name , cat_desc as Category , company as Company , Price , Off_sale as Offer FROM `scart`.`user` join `scart`.`category` join `scart`.`product` on user.user_type = 2 and user.user_id = product.user_id and category.cat_id = Product.cat_id and cat_desc like '" + str + "%' ");
        return rs;
    }

    //Select Products that have been searched for by Supplier 
    public ResultSet selectProdutsSearchedBySupplier(String str) throws SQLException {
        connection = DbService.getConnection();
        Statement stmnt = connection.createStatement();
        ResultSet rs = stmnt.executeQuery("SELECT prod_id as ID , Prod_name as Product_Name , Full_name as Supplier_Name , cat_desc as Category , company as Company , Price , Off_sale as Offer FROM `scart`.`user` join `scart`.`category` join `scart`.`product` on user.user_type = 2 and user.user_id = product.user_id and category.cat_id = Product.cat_id and full_name LIKE '" + str + "%' ");
        return rs;
    }

    //Select Products that have been searched for by Name
    public ResultSet selectProdutsSearchedByName(String str) throws SQLException {
        connection = DbService.getConnection();
        Statement stmnt = connection.createStatement();
        ResultSet rs = stmnt.executeQuery("SELECT prod_id as ID , Prod_name as Product_Name , Full_name as Supplier_Name , cat_desc as Category , company as Company , Price , Off_sale as Offer FROM `scart`.`user` join `scart`.`category` join `scart`.`product` on user.user_type = 2 and user.user_id = product.user_id and category.cat_id = product.cat_id and Prod_name like '" + str + "%' ");
        return rs;
    }

    public ResultSet selectAllProdutNames() throws SQLException {
        connection = DbService.getConnection();
        Statement stmnt = connection.createStatement();
        ResultSet rs = stmnt.executeQuery("SELECT prod_name FROM product");
        return rs;
    }

    public Product selectOne(long prodId) throws SQLException {
        Product item = null;
        connection = DbService.getConnection();
        Statement stmnt = connection.createStatement();
        ResultSet rs = stmnt.executeQuery("SELECT * FROM product WHERE prod_id = " + prodId);
        int count = 0;
        while (rs.next()) {
            count++;
            if (count == 1) {
                item = new Product();
                item.setProdId(rs.getLong(1));
                item.setProdSize(rs.getLong(2));
                item.setProdCondition(rs.getLong(3));
                item.setInStock(rs.getLong(4));
                item.setPhoto(rs.getBlob(5));
                item.setPrice(rs.getLong(6));
                item.setOffSale(rs.getLong(7));
                item.setUserId(rs.getLong(8));
                item.setCatId(rs.getLong(9));
                item.setProdName(rs.getString(10));
                item.setProdColor(rs.getString(11));
            } else {
                throw new MoreThanOneItemException();
            }

        }
        return item;
    }

    public ResultSet selectOneProduct(long prodId) throws SQLException {
        try {
            connection = DbService.getConnection();
            Statement stmnt = connection.createStatement();
            ResultSet rs = stmnt.executeQuery("SELECT * FROM product WHERE prod_id = " + prodId);
            
            return rs;

        } catch (Exception e) {
            System.out.println("Error... in service");
            return null;
        }
    }

    public ResultSet selectOneProductWithCondition(long prodId) throws SQLException {
        try {
            connection = DbService.getConnection();
            Statement stmnt = connection.createStatement();
            ResultSet rs = stmnt.executeQuery("SELECT prod_name as Product_Name , Price , Off_sale FROM product WHERE prod_id = " + prodId);
            return rs;

        } catch (Exception e) {
            return null;
        }
    }

    public int insert(Product item) throws SQLException {
        connection = DbService.getConnection();
        Statement stmnt = connection.createStatement();
        String insertQuery = "INSERT INTO product VALUES(" + item.getProdId()
                + ", " + item.getProdSize()
                + ", " + item.getProdCondition()
                + ", " + item.getInStock()
                + ", " + item.getPhoto()
                + ", " + item.getPrice()
                + ", " + item.getOffSale()
                + ", " + item.getUserId()
                + ", " + item.getCatId()
                + ", '" + item.getProdName()
                + "', '" + item.getProdColor() + ")";
        insertQuery = insertQuery.replace("'null'", "null");
        int rowsAffected = stmnt.executeUpdate(insertQuery);
        stmnt.close();
        if (rowsAffected != 0) {
            return rowsAffected;
        } else {
            return -1;
        }

    }

    public int update(Product item) throws SQLException {
        connection = DbService.getConnection();
        Statement stmnt = connection.createStatement();
        String updateQuery = "UPDATE product SET prod_size = " + item.getProdSize()
                + ", prod_condition = " + item.getProdCondition()
                + ", in_stock = " + item.getInStock()
                + ", photo = " + item.getPhoto()
                + ", price = " + item.getPrice()
                + ", off_sale = " + item.getOffSale()
                + ", user_id = " + item.getUserId()
                + ", cat_id = " + item.getCatId()
                + ", prod_name = '" + item.getProdName()
                + "', prod_color = '" + item.getProdColor() + " WHERE " + "prod_id = " + item.getProdId();
        updateQuery = updateQuery.replace("'null'", "null");
        int rowsAffected = stmnt.executeUpdate(updateQuery);
        stmnt.close();
        if (rowsAffected != 0) {
            return rowsAffected;
        } else {
            return -1;
        }

    }

    public int delete(long prodId) throws SQLException {
        connection = DbService.getConnection();
        Statement stmnt = connection.createStatement();
        int rowsAffected = stmnt.executeUpdate("DELETE FROM product WHERE prod_id = " + prodId);
        stmnt.close();
        if (rowsAffected != 0) {
            return rowsAffected;
        } else {
            return -1;
        }

    }

}
