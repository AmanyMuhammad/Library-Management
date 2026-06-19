import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        BooksFrame b=new BooksFrame();
        BorrowersFrame bb = new BorrowersFrame();
        LibraryFrame f=new LibraryFrame(b.pagePanel);
    }
}
