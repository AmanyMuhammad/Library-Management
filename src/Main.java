import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        BooksFrame b=new BooksFrame();
        LibraryFrame f=new LibraryFrame(b.pagePanel);
    }
}
