import javax.swing.*;
import java.time.LocalDate;

public class Main {

    public static LibraryManagement libraryManagement = new LibraryManagement();

    public static void main(String[] args) {

        libraryManagement.books.insert(new BookNode(978013468, "Effective Java", "Joshua Bloch", 5));
        libraryManagement.books.insert(new BookNode(978013235, "Clean Code", "Robert C. Martin", 0));
        libraryManagement.books.insert(new BookNode(978032135, "Design Patterns", "Erich Gamma", 3));
        libraryManagement.books.insert(new BookNode(978059600, "Head First Java", "Kathy Sierra", 12));
        libraryManagement.books.insert(new BookNode(978013449, "Core Java", "Cay S. Horstmann", 0));
        libraryManagement.books.insert(new BookNode(978013708, "The Clean Coder", "Robert C. Martin", 8));

        libraryManagement.addBorrower(2021101, "Ahmad Mansour", 978013468, LocalDate.parse("2026-06-01"), false);
        libraryManagement.addBorrower(2019302, "Sara Al-Omer", 978032135, LocalDate.parse("2026-06-05"), true);
        libraryManagement.addBorrower(2022105, "Nour Haddad", 978059600, LocalDate.parse("2026-06-12"), false);
        libraryManagement.addBorrower(2020404, "Khaled Masri", 978013468, LocalDate.parse("2026-06-15"), false);
        libraryManagement.addBorrower(2018209, "Reem Kassab", 978013708, LocalDate.parse("2026-06-20"), true);

        BooksFrame b = new BooksFrame();
        libraryManagement.borrowersFrame= new BorrowersFrame();
        libraryManagement.waitingListFrame = new WaitingListFrame();
        LibraryFrame f = new LibraryFrame(b.pagePanel);
    }
}
