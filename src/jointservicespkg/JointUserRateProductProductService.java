package jointservicespkg;
import java.sql.*;
import servicespkg.*;
import jointbeanspkg.*;
import java.util.ArrayList;
public class JointUserRateProductProductService {
	public JointUserRateProductProduct[] selectAll() throws SQLException {
		Connection connection = null;
		JointUserRateProductProduct[] arr = null;
		try {
			connection = DbService.getConnection();
			ArrayList<JointUserRateProductProduct> list = new ArrayList<JointUserRateProductProduct>();
			JointUserRateProductProduct item;
			Statement stmnt = connection.createStatement();
			ResultSet rs = stmnt.executeQuery("SELECT * FROM user_rate_product,product WHERE product.prod_id = user_rate_product.prod_id");
			while(rs.next()){
				item = new JointUserRateProductProduct();
				item.setProdSize(rs.getLong("prod_size"));
				item.setProdCondition(rs.getLong("prod_condition"));
				item.setInStock(rs.getLong("in_stock"));
				item.setPhoto(rs.getBlob("photo"));
				item.setPrice(rs.getLong("price"));
				item.setOffSale(rs.getLong("off_sale"));
				item.setProductUserId(rs.getLong("user_id"));
				item.setCatId(rs.getLong("cat_id"));
				item.setProdName(rs.getString("prod_name"));
				item.setProdColor(rs.getString("prod_color"));
				item.setUserRateProductUserId(rs.getLong("user_id"));
				item.setLikeCount(rs.getString("like_count"));
				list.add(item);
			}

		}

		finally {
			if(connection != null)connection.close();
			return arr;
		}

	}

}

