package beans;

public class Book {
	
	private int bookId;
	private String bookNo;
	private String bookName;
	private String bookAuthor;
	private String bookPrice;
	private String bookInfo;
	private int bookTotalCount;
	private int bookBorrowedCount;
	private int bookRest;
	
	public int getBookRest() {
		return bookTotalCount-bookBorrowedCount;
	}
	public void setBookRest(int bookRest) {
		this.bookRest = bookRest;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getBookNo() {
		return bookNo;
	}
	public void setBookNo(String bookNo) {
		this.bookNo = bookNo;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookAuthor() {
		return bookAuthor;
	}
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	public String getBookPrice() {
		return bookPrice;
	}
	public void setBookPrice(String bookPrice) {
		this.bookPrice = bookPrice;
	}
	public String getBookInfo() {
		return bookInfo;
	}
	public void setBookInfo(String bookInfo) {
		this.bookInfo = bookInfo;
	}
	public int getBookTotalCount() {
		return bookTotalCount;
	}
	public void setBookTotalCount(int bookTotalCount) {
		this.bookTotalCount = bookTotalCount;
	}
	public int getBookBorrowedCount() {
		return bookBorrowedCount;
	}
	public void setBookBorrowedCount(int bookBorrowedCount) {
		this.bookBorrowedCount = bookBorrowedCount;
	}
	
	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", bookNo=" + bookNo + ", bookName="
				+ bookName + ", bookAuthor=" + bookAuthor + ", bookPrice="
				+ bookPrice + ", bookInfo=" + bookInfo + ", bookTotalCount="
				+ bookTotalCount + ", bookBorrowedCount=" + bookBorrowedCount
				+ ", bookRest=" + bookRest + "]";
	}
	
}
