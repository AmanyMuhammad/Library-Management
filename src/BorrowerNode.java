import java.time.LocalDate;

public class BorrowerNode {
    private int id;
    private String name;
    private long BookISBN;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private final int maxBorrows=3;
    private int currentBorrowsCount=0;
    private boolean isGraduate;
    private String recordStatus;

    BorrowerNode left;
    BorrowerNode right;

    public BorrowerNode(int id, String name, long BookISBN, LocalDate borrowDate, boolean isGraduate){
        this.id=id;
        this.name=name;
        this.BookISBN = BookISBN;
        this.borrowDate=borrowDate;
        this.returnDate=borrowDate.plusDays(20);
        this.isGraduate=isGraduate;
        this.recordStatus="Active";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getBookISBN() {
        return BookISBN;
    }

    public void setBookISBN(long bookISBN) {
        this.BookISBN = bookISBN;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public int getMaxBorrows() {
        return maxBorrows;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isGraduate() {
        return isGraduate;
    }

    public void setGraduate(boolean graduate) {
        isGraduate = graduate;
    }

    public int getCurrentBorrowsCount() {
        return currentBorrowsCount;
    }

    public void setCurrentBorrowsCount(int currentBorrowsCount) {
        this.currentBorrowsCount = currentBorrowsCount;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }
}
