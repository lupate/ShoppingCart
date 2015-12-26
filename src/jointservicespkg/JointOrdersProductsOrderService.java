package jointservicespkg;

import java.sql.*;
import servicespkg.*;
import jointbeanspkg.*;
import java.util.ArrayList;

public class JointOrdersProductsOrderService {

    public JointOrdersProductsOrder[] selectAll() throws SQLException {
        Connection connection = null;
        JointOrdersProductsOrder[] arr = null;
        try {
            connection = new DbService().getConnection();
            ArrayList<JointOrdersProductsOrder> list = new ArrayList<JointOrdersProductsOrder>();
            JointOrdersProductsOrder item;
            Statement stmnt = connection.createStatement();
            ResultSet rs = stmnt.executeQuery("SELECT * FROM orders_products,order WHERE order.order_id = orders_products.order_id");
            while (rs.next()) {
                item = new JointOrdersProductsOrder();
                item.setOrderUserId(rs.getLong("user_id"));
                item.setStatus(rs.getLong("status"));
                item.setDeliveryDate(rs.getTimestamp("delivery_date"));
                item.setAddress(rs.getString("address"));
                item.setIdordersProducts(rs.getLong("idorders_products"));
                item.setOrdersProductsUserId(rs.getLong("user_id"));
                item.setAmount(rs.getLong("amount"));
                list.add(item);
            }

        } finally {
            if (connection != null) {
                connection.close();
            }
            return arr;
        }

    }

}
