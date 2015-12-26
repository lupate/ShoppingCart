package servicespkg;
import java.sql.*;
import exceptions.*;
import beanspkg.*;
import java.util.ArrayList;
public class OnlineUsersService {
	public OnlineUsers[] selectAll() throws SQLException {
		Connection connection = null;
		OnlineUsers[] arr = null;
		try {
			connection = new DbService().getConnection();
			ArrayList<OnlineUsers> list = new ArrayList<OnlineUsers>();
			OnlineUsers item;
			Statement stmnt = connection.createStatement();
			ResultSet rs = stmnt.executeQuery("SELECT * FROM online_users");
			while(rs.next()){
				item = new OnlineUsers();
				item.setIdonlineUsers(rs.getLong(1));
				item.setUserId(rs.getLong(2));
				item.setLoginDate(rs.getTimestamp(3));
				list.add(item);
			}

			arr = new OnlineUsers[list.size()];
			list.toArray(arr);
		}

		finally {
			if(connection != null)connection.close();
			return arr;
		}

	}

	public OnlineUsers selectOne(long idonlineUsers) throws SQLException {
		Connection connection = null;
		OnlineUsers item = null;
		try {
			connection = new DbService().getConnection();
			Statement stmnt = connection.createStatement();
			ResultSet rs = stmnt.executeQuery("SELECT * FROM online_users WHERE idonline_users = " + idonlineUsers);
			int count = 0;
			while(rs.next()){
				count++;
				if(count == 1){
					item = new OnlineUsers();
					item.setIdonlineUsers(rs.getLong(1));
					item.setUserId(rs.getLong(2));
					item.setLoginDate(rs.getTimestamp(3));
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

	public int insert(OnlineUsers item) throws SQLException {
		Connection connection = null;
		try {
			connection = new DbService().getConnection();
			Statement stmnt = connection.createStatement();
			String insertQuery = "INSERT INTO online_users VALUES(" + item.getIdonlineUsers()
 + ", " + item.getUserId()
 + ", '" + item.getLoginDate() + ")";
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

	public int update(OnlineUsers item) throws SQLException {
		Connection connection = null;
		try {
			connection = new DbService().getConnection();
			Statement stmnt = connection.createStatement();
			String updateQuery = "UPDATE online_users SET user_id = " + item.getUserId()
 + ", login_date = '" + item.getLoginDate() + " WHERE " + "idonline_users = "+ item.getIdonlineUsers();
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

	public int delete(long idonlineUsers) throws SQLException {
		Connection connection = null;
		try {
			connection = new DbService().getConnection();
			Statement stmnt = connection.createStatement();
			int rowsAffected = stmnt.executeUpdate("DELETE FROM online_users WHERE idonline_users = " + idonlineUsers);
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

