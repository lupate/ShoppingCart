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
			connection = new DbService().getConnection();
			ArrayList<Order> list = new ArrayList<Order>();
			Order item;
			Statement stmnt = connection.createStatement();
			ResultSet rs = stmnt.executeQuery("SELECT * FROM order");
			while(rs.next()){
				item = new Order();
				item.setOrderId(rs.getLong(1));
				item.setUserId(rs.getLong(2));
				item.setStatus(rs.getLong(3));
				item.setDeliveryDate(rs.getTimestamp(4));
				item.setAddress(rs.getString(5));
				list.add(item);
			}

			arr = new Order[list.size()];
			list.toArray(arr);
		}

		finally {
			if(connection != null)connection.close();
			return arr;
		}

	}

	public Order selectOne(long orderId) throws SQLException {
		Connection connection = null;
		Order item = null;
		try {
			connection = new DbService().getConnection();
			Statement stmnt = connection.createStatement();
			ResultSet rs = stmnt.executeQuery("SELECT * FROM order WHERE order_id = " + orderId);
			int count = 0;
			while(rs.next()){
				count++;
				if(count == 1){
					item = new Order();
					item.setOrderId(rs.getLong(1));
					item.setUserId(rs.getLong(2));
					item.setStatus(rs.getLong(3));
					item.setDeliveryDate(rs.getTimestamp(4));
					item.setAddress(rs.getString(5));
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

	public int insert(Order item) throws SQLException {
		Connection connection = null;
		try {
			connection = new DbService().getConnection();
			Statement stmnt = connection.createStatement();
			String insertQuery = "INSERT INTO order VALUES(" + item.getOrderId()
 + ", " + item.getUserId()
 + ", " + item.getStatus()
 + ", '" + item.getDeliveryDate()
 + "', '" + item.getAddress() + ")";
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

	public int update(Order item) throws SQLException {
		Connection connection = null;
		try {
			connection = new DbService().getConnection();
			Statement stmnt = connection.createStatement();
			String updateQuery = "UPDATE order SET user_id = " + item.getUserId()
 + ", status = " + item.getStatus()
 + ", delivery_date = '" + item.getDeliveryDate()
 + "', address = '" + item.getAddress() + " WHERE " + "order_id = "+ item.getOrderId();
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

	public int delete(long orderId) throws SQLException {
		Connection connection = null;
		try {
			connection = new DbService().getConnection();
			Statement stmnt = connection.createStatement();
			int rowsAffected = stmnt.executeUpdate("DELETE FROM order WHERE order_id = " + orderId);
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

