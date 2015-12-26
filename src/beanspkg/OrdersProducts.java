package beanspkg;
import java.sql.*;
public class OrdersProducts {
	private long idordersProducts;
	public long getIdordersProducts() {
		return idordersProducts;
	}

	public void setIdordersProducts(long idordersProducts) {
		this.idordersProducts = idordersProducts;
	}

	private long orderId;
	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	private long userId;
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	private long amount;
	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

}

