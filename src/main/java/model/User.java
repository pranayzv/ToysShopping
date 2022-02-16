package model;

public class User {
	private String fullname;
	private String username;
	private String password;
	private String address;
	private String gender;
	private long mob;
	
	public User(String fullname, String username, String password, String address, String gender, long mob) {
		super();
		this.fullname = fullname;
		this.username = username;
		this.password = password;
		this.address = address;
		this.gender = gender;
		this.mob = mob;
	}
	
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public long getMob() {
		return mob;
	}
	public void setMob(long mob) {
		this.mob = mob;
	}
}
