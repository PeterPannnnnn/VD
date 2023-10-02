import java.util.ArrayList;
import java.util.List;

public class Book {
	private String id;
	private String title;
	private String publisher;
	private int price;
	private int pubYear;
	private List<Chapter> listChapter = new ArrayList<Chapter>();
	
	public int getPubYear() {
		return pubYear;
	}
	public void setPubYear(int pubYear) {
		this.pubYear = pubYear;
	}
	public Book(String id, String title, String publisher, int price, int pubYear, List<Chapter> listChapter) {
		super();
		this.id = id;
		this.title = title;
		this.publisher = publisher;
		this.price = price;
		this.pubYear = pubYear;
		this.listChapter = listChapter;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public List<Chapter> getListChapter() {
		return listChapter;
	}
	public void setListChapter(List<Chapter> listChapter) {
		this.listChapter = listChapter;
	}
	public Chapter maxPageChapter() {
		Chapter ct = listChapter.get(0);
		for (Chapter chapter : listChapter) {
			if (chapter.getPages() > ct.getPages()) {
				ct = chapter;
			}
		}
		return ct;
		
	}
}
