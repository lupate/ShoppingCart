package beanspkg;

import java.sql.*;

public class User {

    private long userId;

    public User(String fullName, String email, int city, String address, String password, String company, long phone, long userTypes) {
        this.fullName = fullName;
        this.email = email;
        this.city = city;
        this.address = address;
        this.password = password;
        this.company = company;
        this.phone = phone;
        this.userTypes= userTypes;

    }

    public User() {

    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
//	private int userId2;
//	public int getUserId2() {
//		return userId2;
//	}
//
//	public void setUserId2(int userId) {
//		this.userId2 = userId;
//	}

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

    //photo as bytes[]
    private byte[] photo2;

    public byte[] getPhoto2() {
        return photo2;
    }

    public void setPhoto2(byte[] photo) {
        this.photo2 = photo2;
    }
    ///////////end//////////

    private int userType;

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    private long userTypes;

    public long getUserTypes() {
        return userTypes;
    }

    public long setUserTypes(long userTypes) {
        this.userTypes = userTypes;
        return userTypes;
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
