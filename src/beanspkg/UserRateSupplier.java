package beanspkg;
import java.sql.*;
public class UserRateSupplier {
	private long userId;
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	private long supId;
	public long getSupId() {
		return supId;
	}

	public void setSupId(long supId) {
		this.supId = supId;
	}

	private long likeCount;
	public long getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(long likeCount) {
		this.likeCount = likeCount;
	}

}

