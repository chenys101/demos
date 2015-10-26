package demo.mybatis.entity;

public class Log {
	private int id;
	private String action;
	private int userId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder();
		buf.append("User{")
		.append("id").append(":").append(id).append(", ")
		.append("action").append(":").append(action).append(", ")
		.append("userId").append(":").append(userId)
		.append("}");
		return buf.toString();
	}
}
