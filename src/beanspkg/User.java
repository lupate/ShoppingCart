package beanspkg;
import java.sql.*;
public class User {
	private long userId;
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	private String fullName;
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	private String address;
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	private long city;
	public long getCity() {
		return city;
	}

	public void setCity(long city) {
		this.city = city;
	}

	private String email;
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	private String password;
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private long phone;
	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	private Blob photo;
	public Blob getPhoto() {
		return photo;
	}

	public void setPhoto(Blob photo) {
		this.photo = photo;
	}

	private long userType;
	public long getUserType() {
		return userType;
	}

	public void setUserType(long userType) {
		this.userType = userType;
	}

	private String company;
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

        
}
