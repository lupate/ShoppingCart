package beanspkg;
import java.sql.*;
public class Product {
	private long prodId;
	public long getProdId() {
		return prodId;
	}

	public void setProdId(long prodId) {
		this.prodId = prodId;
	}

	private long prodSize;
	public long getProdSize() {
		return prodSize;
	}

	public void setProdSize(long prodSize) {
		this.prodSize = prodSize;
	}

	private long prodCondition;
	public long getProdCondition() {
		return prodCondition;
	}

	public void setProdCondition(long prodCondition) {
		this.prodCondition = prodCondition;
	}

	private long inStock;
	public long getInStock() {
		return inStock;
	}

	public void setInStock(long inStock) {
		this.inStock = inStock;
	}

	private Blob photo;
	public Blob getPhoto() {
		return photo;
	}

	public void setPhoto(Blob photo) {
		this.photo = photo;
	}

	private long price;
	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	private long offSale;
	public long getOffSale() {
		return offSale;
	}

	public void setOffSale(long offSale) {
		this.offSale = offSale;
	}

	private long userId;
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	private long catId;
	public long getCatId() {
		return catId;
	}

	public void setCatId(long catId) {
		this.catId = catId;
	}

	private String prodName;
	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	private String prodColor;
	public String getProdColor() {
		return prodColor;
	}

	public void setProdColor(String prodColor) {
		this.prodColor = prodColor;
	}

}

