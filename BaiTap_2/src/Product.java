import java.util.Date;

public class Product {
	private String name, type;
	private int price;
	private Date expiredDate;
	public Product(String name, String type, int price, Date expiredDate) {
		super();
		this.name = name;
		this.type = type;
		this.price = price;
		this.expiredDate = expiredDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Date getExpiredDate() {
		return expiredDate;
	}
	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}
	
}
