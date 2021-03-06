package servicespkg;
import java.sql.*;
import exceptions.*;
import beanspkg.*;
import java.util.ArrayList;
public class CategoryService {
	public Category[] selectAll() throws SQLException {
		Connection connection = null;
		Category[] arr = null;
			connection = DbService.getConnection();
			ArrayList<Category> list = new ArrayList<Category>();
			Category item;
			Statement stmnt = connection.createStatement();
			ResultSet rs = stmnt.executeQuery("SELECT * FROM category");
			while(rs.next()){
				item = new Category();
				item.setCatId(rs.getLong(1));
				item.setCatDesc(rs.getString(2));
				list.add(item);
			}

			arr = new Category[list.size()];
			list.toArray(arr);
		return arr;
	}
      
        public ResultSet selectAllCategories() throws SQLException {
		Connection connection = null;
			connection = DbService.getConnection();
			Category item;
			Statement stmnt = connection.createStatement();
			ResultSet rs = stmnt.executeQuery("SELECT cat_desc FROM category");
			
		return rs;
	}

	public Category selectOne(long catId) throws SQLException {
		Connection connection = null;
		Category item = null;
		try {
			connection = DbService.getConnection();
			Statement stmnt = connection.createStatement();
			ResultSet rs = stmnt.executeQuery("SELECT * FROM category WHERE cat_id = " + catId);
			int count = 0;
			while(rs.next()){
				count++;
				if(count == 1){
					item = new Category();
					item.setCatId(rs.getLong(1));
					item.setCatDesc(rs.getString(2));
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
                    public CategoryBean selectCat (String catName) throws SQLException {
		Connection connection = null;
		CategoryBean item = null;
		try {
			connection = DbService.getConnection();
			Statement stmnt = connection.createStatement();
                                                                System.out.println(catName);
			ResultSet rs = stmnt.executeQuery("SELECT * FROM category WHERE cat_desc like'" + catName + "%'");
                                                                
			int count = 0;
			while(rs.next()){
				count++;
				if(count == 1){
					item = new CategoryBean();
					item.setCatId(rs.getInt(1));
					item.setCatDesc(rs.getString(2));
				}

				else{
					throw new MoreThanOneItemException();
				}

			}

			rs.close();
			stmnt.close();
		}

		finally {
			return item;
		}}

	public int insert(CategoryBean item) throws SQLException {
		Connection connection = null;
		try {
			connection = DbService.getConnection();
			Statement stmnt = connection.createStatement();
			String insertQuery = "INSERT INTO category VALUES(" + item.getCatId()+ ", '" + item.getCatDesc() + ")";
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

	public int update(CategoryBean item) throws SQLException {
		Connection connection = null;
		try {
			connection = DbService.getConnection();
			Statement stmnt = connection.createStatement();
			String updateQuery = "UPDATE category SET cat_desc = '" + item.getCatDesc() + " WHERE " + "cat_id = "+ item.getCatId();
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

	public int delete(long catId) throws SQLException {
		Connection connection = null;
		try {
			connection = DbService.getConnection();
			Statement stmnt = connection.createStatement();
			int rowsAffected = stmnt.executeUpdate("DELETE FROM category WHERE cat_id = " + catId);
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

