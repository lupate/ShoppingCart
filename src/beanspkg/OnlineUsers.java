package beanspkg;
import java.sql.*;
public class OnlineUsers {
	private long idonlineUsers;
	public long getIdonlineUsers() {
		return idonlineUsers;
	}

	public void setIdonlineUsers(long idonlineUsers) {
		this.idonlineUsers = idonlineUsers;
	}

	private long userId;
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	private Timestamp loginDate;
	public Timestamp getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Timestamp loginDate) {
		this.loginDate = loginDate;
	}

}

