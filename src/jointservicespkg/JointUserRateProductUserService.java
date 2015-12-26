package jointservicespkg;
import java.sql.*;
import servicespkg.*;
import jointbeanspkg.*;
import java.util.ArrayList;
public class JointUserRateProductUserService {
	public JointUserRateProductUser[] selectAll() throws SQLException {
		Connection connection = null;
		JointUserRateProductUser[] arr = null;
		try {
			connection = DbService.getConnection();
			ArrayList<JointUserRateProductUser> list = new ArrayList<JointUserRateProductUser>();
			JointUserRateProductUser item;
			Statement stmnt = connection.createStatement();
			ResultSet rs = stmnt.executeQuery("SELECT * FROM user_rate_product,user WHERE user.user_id = user_rate_product.user_id");
			while(rs.next()){
				item = new JointUserRateProductUser();
				item.setFullName(rs.getString("full_name"));
				item.setAddress(rs.getString("address"));
				item.setCity(rs.getLong("city"));
				item.setEmail(rs.getString("email"));
				item.setPassword(rs.getString("password"));
				item.setPhone(rs.getLong("phone"));
				item.setPhoto(rs.getBlob("photo"));
				item.setUserType(rs.getLong("user_type"));
				item.setCompany(rs.getString("company"));
				item.setProdId(rs.getLong("prod_id"));
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

