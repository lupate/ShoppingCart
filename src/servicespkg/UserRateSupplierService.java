package servicespkg;
import java.sql.*;
import exceptions.*;
import beanspkg.*;
import java.util.ArrayList;
public class UserRateSupplierService {
	public UserRateSupplier[] selectAll() throws SQLException {
		Connection connection = null;
		UserRateSupplier[] arr = null;
		try {
			connection = DbService.getConnection();
			ArrayList<UserRateSupplier> list = new ArrayList<UserRateSupplier>();
			UserRateSupplier item;
			Statement stmnt = connection.createStatement();
			ResultSet rs = stmnt.executeQuery("SELECT * FROM user_rate_supplier");
			while(rs.next()){
				item = new UserRateSupplier();
				item.setUserId(rs.getLong(1));
				item.setSupId(rs.getLong(2));
				item.setLikeCount(rs.getLong(3));
				list.add(item);
			}

			arr = new UserRateSupplier[list.size()];
			list.toArray(arr);
		}

		finally {
			if(connection != null)connection.close();
			return arr;
		}

	}

	public UserRateSupplier selectOne(long supId, long userId) throws SQLException {
		Connection connection = null;
		UserRateSupplier item = null;
		try {
			connection = DbService.getConnection();
			Statement stmnt = connection.createStatement();
			ResultSet rs = stmnt.executeQuery("SELECT * FROM user_rate_supplier WHERE sup_id = " + supId + " and user_id = " + userId);
			int count = 0;
			while(rs.next()){
				count++;
				if(count == 1){
					item = new UserRateSupplier();
					item.setUserId(rs.getLong(1));
					item.setSupId(rs.getLong(2));
					item.setLikeCount(rs.getLong(3));
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

	public int insert(UserRateSupplier item) throws SQLException {
		Connection connection = null;
		try {
			connection = DbService.getConnection();
			Statement stmnt = connection.createStatement();
			String insertQuery = "INSERT INTO user_rate_supplier VALUES(" + item.getUserId()
 + ", " + item.getSupId()
 + ", " + item.getLikeCount() + ")";
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

	public int update(UserRateSupplier item) throws SQLException {
		Connection connection = null;
		try {
			connection = DbService.getConnection();
			Statement stmnt = connection.createStatement();
			String updateQuery = "UPDATE user_rate_supplier SET user_id = " + item.getUserId()
 + ", like_count = " + item.getLikeCount() + " WHERE " + "sup_id = "+ item.getSupId();
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

	public int delete(long supId, long userId) throws SQLException {
		Connection connection = null;
		try {
			connection = DbService.getConnection();
			Statement stmnt = connection.createStatement();
			int rowsAffected = stmnt.executeUpdate("DELETE FROM user_rate_supplier WHERE sup_id = " + supId + " and user_id = " + userId);
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

