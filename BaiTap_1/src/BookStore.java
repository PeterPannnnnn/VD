import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class BookStore {
	private String name;
	private String address;
	private List<Book> listBook = new ArrayList<Book>();
	public BookStore(String name, String address, List<Book> listBook) {
		super();
		this.name = name;
		this.address = address;
		this.listBook = listBook;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<Book> getListBook() {
		return listBook;
	}
	public void setListBook(List<Book> listBook) {
		this.listBook = listBook;
	}
	public Map<Integer, ArrayList<Book>> statBookByYear(){
		Map<Integer, ArrayList<Book>> map = new HashMap<Integer, ArrayList<Book>>();
		for(Book book: listBook) {
			int key = book.getPubYear();
			if (map.get(key) == null) {
				map.put(key, new ArrayList<>());
			}
			map.get(key).add(book);
			}
		return map;
	}
	public Set<Book> getBook(String publisherName){
		Set<Book> set = new TreeSet<Book>(new Comparator<Book>() {

			@Override
			public int compare(Book o1, Book o2) {
				if (o1.getPubYear() == o2.getPubYear()) {
					return o1.getTitle().compareTo(o2.getTitle());
					
				}
				return o1.getPubYear()-o2.getPubYear();
			}
		});
								
			for (Book book : listBook) {
				if (book.getPublisher().equals(publisherName)) {
					set.add(book);
				}
			}
		return set;
	}
}
