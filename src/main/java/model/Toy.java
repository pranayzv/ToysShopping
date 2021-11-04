package model;

public class Toy {

	private String name;
	private long price;
	private String seller;
	private String typeof;
	private String image;
	public Toy(String name, long price, String seller, String typeof, String image) {
		super();
		this.name = name;
		this.price = price;
		this.seller = seller;
		this.typeof = typeof;
		this.image = image;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public String getSeller() {
		return seller;
	}
	public void setSeller(String seller) {
		this.seller = seller;
	}
	public String getTypeof() {
		return typeof;
	}
	public void setTypeof(String typeof) {
		this.typeof = typeof;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	
	
}
