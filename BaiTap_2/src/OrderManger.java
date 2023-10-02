import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

public class OrderManger {
	private List<Order> orders;

	public OrderManger(List<Order> orders) {
		super();
		this.orders = orders;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Product maxProduct() {
		Product pd = orders.get(0).getItems().get(0).getItem();
		for (int i = 0; i < orders.size(); i++) {
			for (int j = 0; j < orders.get(i).getItems().size(); j++) {
				if (orders.get(i).getItems().get(j).getItem().getPrice() > pd.getPrice()) {
					pd = orders.get(i).getItems().get(j).getItem();
				}
			}
		}
		return pd;
	}

	public HashMap<String, Integer> productTypesStatistic() {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < orders.size(); i++) {
			for (int j = 0; j < orders.get(i).getItems().size(); j++) {
				String key = orders.get(i).getItems().get(j).getItem().getType();
				int value = orders.get(i).getItems().get(j).getAmount();
				if (map.get(key) == null) {
					map.put(key, value);
				} else {
					value += map.get(key);
					map.put(key, value);
				}
			}
		}
		return map;
	}

	public TreeSet<Order> orderByCost() {
		TreeSet<Order> treeset = new TreeSet<Order>(new Comparator<Order>() {
			@Override
			public int compare(Order o1, Order o2) {
				int a = 0;
				for (int i = 0; i < orders.get(i).getItems().size(); i++) {
					if (o1.getItems().get(i).getAmount() * o1.getItems().get(i).getItem().getPrice() == o1.getItems()
							.get(i).getAmount() * o1.getItems().get(i).getItem().getPrice()) {
						return a = o1.getDate().toString().compareTo(o2.getDate().toString());
					}
					return a = o2.getItems().get(i).getAmount() * o2.getItems().get(i).getItem().getPrice()
							- o2.getItems().get(i).getAmount() * o2.getItems().get(i).getItem().getPrice();
				}
				return a;
			}
		});
		for (Order order : this.orders) {
			treeset.add(order);
		}
		return treeset;
	}
}
