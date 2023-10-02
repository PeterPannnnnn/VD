
public class OrderItem {
	private Product item;
	private int amount;
	public OrderItem(Product item, int amount) {
		super();
		this.item = item;
		this.amount = amount;
	}
	public Product getItem() {
		return item;
	}
	public void setItem(Product item) {
		this.item = item;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
}
