package jointservicespkg;
import java.sql.*;
import servicespkg.*;
import jointbeanspkg.*;
import java.util.ArrayList;
public class JointUserRateSupplierUserService {
	public JointUserRateSupplierUser[] selectAll() throws SQLException {
		Connection connection = null;
		JointUserRateSupplierUser[] arr = null;
		try {
			connection = new DbService().getConnection();
			ArrayList<JointUserRateSupplierUser> list = new ArrayList<JointUserRateSupplierUser>();
			JointUserRateSupplierUser item;
			Statement stmnt = connection.createStatement();
			ResultSet rs = stmnt.executeQuery("SELECT * FROM user_rate_supplier,user WHERE user.user_id = user_rate_supplier.sup_id");
			while(rs.next()){
				item = new JointUserRateSupplierUser();
				item.setFullName(rs.getString("full_name"));
				item.setAddress(rs.getString("address"));
				item.setCity(rs.getLong("city"));
				item.setEmail(rs.getString("email"));
				item.setPassword(rs.getString("password"));
				item.setPhone(rs.getLong("phone"));
				item.setPhoto(rs.getBlob("photo"));
				item.setUserType(rs.getLong("user_type"));
				item.setCompany(rs.getString("company"));
				item.setUserRateSupplierUserId(rs.getLong("user_id"));
				item.setLikeCount(rs.getLong("like_count"));
				list.add(item);
			}

		}

		finally {
			if(connection != null)connection.close();
			return arr;
		}

	}

}

