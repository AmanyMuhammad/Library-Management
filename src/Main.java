import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        BooksFrame b=new BooksFrame();
        BorrowersFrame bb = new BorrowersFrame();
        LibraryFrame f=new LibraryFrame(b.pagePanel);

        BookTree t = new BookTree();

        t.recursiveInsert(new BookNode(120, "title 1", "Hatem", 6));
        t.recursiveInsert(new BookNode(150, "title 2", "Hatem", 6));
        t.recursiveInsert(new BookNode(12, "title 3", "Hatem", 6));
        t.recursiveInsert(new BookNode(14, "title 4", "Hatem", 6));
        t.recursiveInsert(new BookNode(11, "title 5", "Hatem", 6));
        t.recursiveInsert(new BookNode(16, "title 6", "Hatem", 6));


        t.display();

    }
}
