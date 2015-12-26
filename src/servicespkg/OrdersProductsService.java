package servicespkg;
import java.sql.*;
import exceptions.*;
import beanspkg.*;
import java.util.ArrayList;
public class OrdersProductsService {
	public OrdersProducts[] selectAll() throws SQLException {
		Connection connection = null;
		OrdersProducts[] arr = null;
		try {
			connection = DbService.getConnection();
			ArrayList<OrdersProducts> list = new ArrayList<OrdersProducts>();
			OrdersProducts item;
			Statement stmnt = connection.createStatement();
			ResultSet rs = stmnt.executeQuery("SELECT * FROM orders_products");
			while(rs.next()){
				item = new OrdersProducts();
				item.setIdordersProducts(rs.getLong(1));
				item.setOrderId(rs.getLong(2));
				item.setUserId(rs.getLong(3));
				item.setAmount(rs.getLong(4));
				list.add(item);
			}

			arr = new OrdersProducts[list.size()];
			list.toArray(arr);
		}

		finally {
			if(connection != null)connection.close();
			return arr;
		}

	}

	public OrdersProducts selectOne(long idordersProducts) throws SQLException {
		Connection connection = null;
		OrdersProducts item = null;
		try {
			connection = DbService.getConnection();
			Statement stmnt = connection.createStatement();
			ResultSet rs = stmnt.executeQuery("SELECT * FROM orders_products WHERE idorders_products = " + idordersProducts);
			int count = 0;
			while(rs.next()){
				count++;
				if(count == 1){
					item = new OrdersProducts();
					item.setIdordersProducts(rs.getLong(1));
					item.setOrderId(rs.getLong(2));
					item.setUserId(rs.getLong(3));
					item.setAmount(rs.getLong(4));
				}

				else{
					throw new MoreThanOneItemException();
				}

			}

			rs.close();
			stmnt.close();
		}

		finally {
			if(connection != null)connection.close();
			return item;
		}

	}

	public int insert(OrdersProducts item) throws SQLException {
		Connection connection = null;
		try {
			connection = DbService.getConnection();
			Statement stmnt = connection.createStatement();
			String insertQuery = "INSERT INTO orders_products VALUES(" + item.getIdordersProducts()
 + ", " + item.getOrderId()
 + ", " + item.getUserId()
 + ", " + item.getAmount() + ")";
			insertQuery = insertQuery.replace("'null'", "null");
			int rowsAffected = stmnt.executeUpdate(insertQuery);
			stmnt.close();
			if(rowsAffected != 0){
				return rowsAffected;
			}

			else{
				return -1;
			}

		}

		finally {
			if(connection != null)connection.close();
		}

	}

	public int update(OrdersProducts item) throws SQLException {
		Connection connection = null;
		try {
			connection = DbService.getConnection();
			Statement stmnt = connection.createStatement();
			String updateQuery = "UPDATE orders_products SET order_id = " + item.getOrderId()
 + ", user_id = " + item.getUserId()
 + ", amount = " + item.getAmount() + " WHERE " + "idorders_products = "+ item.getIdordersProducts();
			updateQuery = updateQuery.replace("'null'", "null");
			int rowsAffected = stmnt.executeUpdate(updateQuery);
			stmnt.close();
			if(rowsAffected != 0){
				return rowsAffected;
			}

			else{
				return -1;
			}

		}

		finally {
			if(connection != null)connection.close();
		}

	}

	public int delete(long idordersProducts) throws SQLException {
		Connection connection = null;
		try {
			connection = DbService.getConnection();
			Statement stmnt = connection.createStatement();
			int rowsAffected = stmnt.executeUpdate("DELETE FROM orders_products WHERE idorders_products = " + idordersProducts);
			stmnt.close();
			if(rowsAffected != 0){
				return rowsAffected;
			}

			else{
				return -1;
			}

		}

		finally {
			if(connection != null)connection.close();
		}

	}

}

