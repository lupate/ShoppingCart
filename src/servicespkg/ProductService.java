package servicespkg;
import java.sql.*;
import exceptions.*;
import beanspkg.*;
import java.util.ArrayList;
public class ProductService {
	public Product[] selectAll() throws SQLException {
		Connection connection = null;
		Product[] arr = null;
		try {
			connection = new DbService().getConnection();
			ArrayList<Product> list = new ArrayList<Product>();
			Product item;
			Statement stmnt = connection.createStatement();
			ResultSet rs = stmnt.executeQuery("SELECT * FROM product");
			while(rs.next()){
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
		}

		finally {
			if(connection != null)connection.close();
			return arr;
		}

	}

	public Product selectOne(long prodId) throws SQLException {
		Connection connection = null;
		Product item = null;
		try {
			connection = new DbService().getConnection();
			Statement stmnt = connection.createStatement();
			ResultSet rs = stmnt.executeQuery("SELECT * FROM product WHERE prod_id = " + prodId);
			int count = 0;
			while(rs.next()){
				count++;
				if(count == 1){
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

	public int insert(Product item) throws SQLException {
		Connection connection = null;
		try {
			connection = new DbService().getConnection();
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

	public int update(Product item) throws SQLException {
		Connection connection = null;
		try {
			connection = new DbService().getConnection();
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
 + "', prod_color = '" + item.getProdColor() + " WHERE " + "prod_id = "+ item.getProdId();
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

	public int delete(long prodId) throws SQLException {
		Connection connection = null;
		try {
			connection = new DbService().getConnection();
			Statement stmnt = connection.createStatement();
			int rowsAffected = stmnt.executeUpdate("DELETE FROM product WHERE prod_id = " + prodId);
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

