import javax.swing.*;
import java.time.LocalDate;

public class Main {
    public static LibraryManagement libraryManagement = new LibraryManagement();

    public static void main(String[] args) {

        BookNode testBook = new BookNode(9781234567890L, "Java Programming", "Author Name", 5);
        BookNode testBook1 = new BookNode(9780132350884L, "Java Programming", "Author Name", 5);
        libraryManagement.books.insert(testBook);
        libraryManagement.books.insert(testBook1);

        libraryManagement.addBorrower(101, "Ahmad", 9781234567890L, LocalDate.parse("2026-06-01"), false);
        libraryManagement.addBorrower(102, "Sara", 9780132350884L, LocalDate.parse("2026-06-10"), true);

        BooksFrame b = new BooksFrame();
        libraryManagement.borrowersFrame = new BorrowersFrame();

        LibraryFrame f = new LibraryFrame(b.pagePanel, libraryManagement.borrowersFrame.pagePanel);

    }
}
