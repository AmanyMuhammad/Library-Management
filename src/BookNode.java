import java.util.HashMap;

public class BookNode {
    private long ISBN;
    private String title;
    private String author;
    private int copiesNum;
    private boolean status;
    private int borrowCount=0;
    public BookNode left;
    public BookNode right;
    public int height = 0;

    public BookNode(long ISBN,String title,String author,int copiesNum){
        this.ISBN=ISBN;
        this.title=title;
        this.author=author;
        this.copiesNum=copiesNum;
        status = (copiesNum != 0);
        left = right = null;
    }

    public long getISBN() {
        return ISBN;
    }

    public void setISBN(long ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getCopiesNum() {
        return copiesNum;
    }

    public void setCopiesNum(int copiesNum) {
        this.copiesNum = copiesNum;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getStringStatus() {
        return (this.status) ? "AVAILABLE" : "UN AVAILABLE";
    }

    public int getBorrowCount() {
        return borrowCount;
    }

    public void setBorrowCount(int borrowCount) {
        this.borrowCount = borrowCount;
    }
}
