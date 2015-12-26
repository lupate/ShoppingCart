package servicespkg;
import java.sql.*;
import exceptions.*;
import beanspkg.*;
import java.util.ArrayList;
public class UserRateProductService {
	public UserRateProduct[] selectAll() throws SQLException {
		Connection connection = null;
		UserRateProduct[] arr = null;
		try {
			connection = DbService.getConnection();
			ArrayList<UserRateProduct> list = new ArrayList<UserRateProduct>();
			UserRateProduct item;
			Statement stmnt = connection.createStatement();
			ResultSet rs = stmnt.executeQuery("SELECT * FROM user_rate_product");
			while(rs.next()){
				item = new UserRateProduct();
				item.setProdId(rs.getLong(1));
				item.setUserId(rs.getLong(2));
				item.setLikeCount(rs.getString(3));
				list.add(item);
			}

			arr = new UserRateProduct[list.size()];
			list.toArray(arr);
		}

		finally {
			if(connection != null)connection.close();
			return arr;
		}

	}

	public UserRateProduct selectOne(long prodId, long userId) throws SQLException {
		Connection connection = null;
		UserRateProduct item = null;
		try {
			connection = DbService.getConnection();
			Statement stmnt = connection.createStatement();
			ResultSet rs = stmnt.executeQuery("SELECT * FROM user_rate_product WHERE prod_id = " + prodId + " and user_id = " + userId);
			int count = 0;
			while(rs.next()){
				count++;
				if(count == 1){
					item = new UserRateProduct();
					item.setProdId(rs.getLong(1));
					item.setUserId(rs.getLong(2));
					item.setLikeCount(rs.getString(3));
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

	public int insert(UserRateProduct item) throws SQLException {
		Connection connection = null;
		try {
			connection = DbService.getConnection();
			Statement stmnt = connection.createStatement();
			String insertQuery = "INSERT INTO user_rate_product VALUES(" + item.getProdId()
 + ", " + item.getUserId()
 + ", '" + item.getLikeCount() + ")";
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

	public int update(UserRateProduct item) throws SQLException {
		Connection connection = null;
		try {
			connection = DbService.getConnection();
			Statement stmnt = connection.createStatement();
			String updateQuery = "UPDATE user_rate_product SET user_id = " + item.getUserId()
 + ", like_count = '" + item.getLikeCount() + " WHERE " + "prod_id = "+ item.getProdId();
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

	public int delete(long prodId, long userId) throws SQLException {
		Connection connection = null;
		try {
			connection = DbService.getConnection();
			Statement stmnt = connection.createStatement();
			int rowsAffected = stmnt.executeUpdate("DELETE FROM user_rate_product WHERE prod_id = " + prodId + " and user_id = " + userId);
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

