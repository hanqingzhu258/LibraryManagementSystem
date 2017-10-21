package beans;

public class UB {

	private int id;
	private int userId;
	private int bookId;
	private String bookName;
	private int bookCount;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public int getBookCount() {
		return bookCount;
	}
	public void setBookCount(int bookCount) {
		this.bookCount = bookCount;
	}
	
	@Override
	public String toString() {
		return "UB [id=" + id + ", userId=" + userId + ", bookId=" + bookId
				+ ", bookName=" + bookName + ", bookCount=" + bookCount + "]";
	}
	
}
