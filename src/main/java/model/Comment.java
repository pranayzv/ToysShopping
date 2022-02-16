package model;

public class Comment {
	private String user;
	private String prod;
	private String comment;
	public Comment(String user, String prod, String comment) {
		super();
		this.user = user;
		this.prod = prod;
		this.comment = comment;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getProd() {
		return prod;
	}
	public void setProd(String prod) {
		this.prod = prod;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
}
