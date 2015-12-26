package jointservicespkg;
import java.sql.*;
import servicespkg.*;
import jointbeanspkg.*;
import java.util.ArrayList;
public class JointProductCategoryService {
	public JointProductCategory[] selectAll() throws SQLException {
		Connection connection = null;
		JointProductCategory[] arr = null;
		try {
			connection = new DbService().getConnection();
			ArrayList<JointProductCategory> list = new ArrayList<JointProductCategory>();
			JointProductCategory item;
			Statement stmnt = connection.createStatement();
			ResultSet rs = stmnt.executeQuery("SELECT * FROM product,category WHERE category.cat_id = product.cat_id");
			while(rs.next()){
				item = new JointProductCategory();
				item.setCatDesc(rs.getString("cat_desc"));
				item.setProdId(rs.getLong("prod_id"));
				item.setProdSize(rs.getLong("prod_size"));
				item.setProdCondition(rs.getLong("prod_condition"));
				item.setInStock(rs.getLong("in_stock"));
				item.setPhoto(rs.getBlob("photo"));
				item.setPrice(rs.getLong("price"));
				item.setOffSale(rs.getLong("off_sale"));
				item.setUserId(rs.getLong("user_id"));
				item.setProdName(rs.getString("prod_name"));
				item.setProdColor(rs.getString("prod_color"));
				list.add(item);
			}

		}

		finally {
			if(connection != null)connection.close();
			return arr;
		}

	}

}

