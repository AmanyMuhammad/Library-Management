import javax.swing.*;

public class Main {

    public static BookTree books = new BookTree();


    public static void main(String[] args) {

        books.insert(new BookNode(123456789, "Title 1", "Author 1", 5));
        books.insert(new BookNode(233456789, "Title 2", "Author 2", 0));
        books.insert(new BookNode(123456649, "Title 3", "Author 3", 6));
        books.insert(new BookNode(1234569, "Title 4", "Author 4", 13));
        books.insert(new BookNode(1233789, "Title 5", "Author 5", 0));
        books.insert(new BookNode(12356789, "Title 6", "Author 6", 50));


        BooksFrame b = new BooksFrame();
        BorrowersFrame bb = new BorrowersFrame();
        LibraryFrame f = new LibraryFrame(b.pagePanel);
    }
}
