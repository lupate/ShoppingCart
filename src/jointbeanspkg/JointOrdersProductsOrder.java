package jointbeanspkg;

import java.sql.*;

public class JointOrdersProductsOrder {

    private long idordersProducts;

    public long getIdordersProducts() {
        return idordersProducts;
    }

    public void setIdordersProducts(long idordersProducts) {
        this.idordersProducts = idordersProducts;
    }

    private long ordersProductsUserId;

    public long getOrdersProductsUserId() {
        return ordersProductsUserId;
    }

    public void setOrdersProductsUserId(long ordersProductsUserId) {
        this.ordersProductsUserId = ordersProductsUserId;
    }

    private long amount;

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    private long orderUserId;

    public long getOrderUserId() {
        return orderUserId;
    }

    public void setOrderUserId(long orderUserId) {
        this.orderUserId = orderUserId;
    }

    private long status;

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    private Timestamp deliveryDate;

    public Timestamp getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Timestamp deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
