import java.time.LocalDate;

public class BorrowerNode {
    private int id;
    private String name;
    private String borrowedBook;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private int maxBorrows=3;
    private boolean isGraduate;

    public BorrowerNode(String name,String borrowedBook,LocalDate borrowDate,boolean isGraduate){
        this.name=name;
        this.borrowedBook=borrowedBook;
        this.borrowDate=borrowDate;
        this.returnDate=borrowDate.plusDays(20);
        this.isGraduate=isGraduate;
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

    public String getBorrowedBook() {
        return borrowedBook;
    }

    public void setBorrowedBook(String borrowedBook) {
        this.borrowedBook = borrowedBook;
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

    public void setMaxBorrows(int maxBorrows) {
        this.maxBorrows = maxBorrows;
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
}
