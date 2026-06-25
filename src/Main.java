import javax.swing.*;
import java.time.LocalDate;

public class Main {
    public static LibraryManagement libraryManagement = new LibraryManagement();
    public static BookTree books = new BookTree();


    public static void main(String[] args) {

        books.insert(new BookNode(123456789, "Title 1", "Author 1", 5));
        books.insert(new BookNode(233456789, "Title 2", "Author 2", 0));
        books.insert(new BookNode(123456649, "Title 3", "Author 3", 6));
        books.insert(new BookNode(1234569, "Title 4", "Author 4", 13));
        books.insert(new BookNode(1233789, "Title 5", "Author 5", 0));
        books.insert(new BookNode(12356789, "Title 6", "Author 6", 50));

        libraryManagement.addBorrower(101, "Ahmad", 123456789L, LocalDate.parse("2026-06-01"), false);
        libraryManagement.addBorrower(102, "Sara", 1233789L, LocalDate.parse("2026-06-10"), true);

        BooksFrame b = new BooksFrame();
        BorrowersFrame bb = new BorrowersFrame();
        libraryManagement.waitingListFrame = new WaitingListFrame();
        LibraryFrame f = new LibraryFrame(b.pagePanel);
    }
}
