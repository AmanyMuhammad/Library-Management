import java.time.LocalDate;
import java.util.ArrayList;

public class LibraryManagement {
    BorrowTree borrowers = new BorrowTree();
    BookTree books = new BookTree();

    public boolean checkBorrowerExist(int studentID) {
        if (borrowers.exists(borrowers.getRoot(), studentID))
            return true;

        return false;
    }

    public void addBorrower(int studentID, String name, long bookISBN, LocalDate borrowDate, boolean isGraduate) {
        BorrowerNode borrower = null;

        //check book
        if (!books.searchAVL(books.getRoot(), bookISBN)) {
            return;//الكتاب غير موجود
        }

        BookNode book = books.find(books.getRoot(), bookISBN);

        //check if the book is available
        if (!book.isStatus()) {
            return;
        }

        int currentBorrowsCount = borrowers.countCurrentBorrowsNum(borrowers.getRoot(), studentID);

        if (checkBorrowerExist(studentID)) {
            borrower = borrowers.find(borrowers.getRoot(), studentID);

            int remainingBorrows=borrower.getMaxBorrows()-currentBorrowsCount;
            if(remainingBorrows<=0){
                return;
            }
        }

        book.setCopiesNum(book.getCopiesNum() - 1);
        if(book.getCopiesNum()==0)
            book.setStatus(false);

        borrower = new BorrowerNode(studentID, name, bookISBN, borrowDate, isGraduate);
        borrower.setCurrentBorrowsCount(currentBorrowsCount + 1);

        BorrowerNode root = borrowers.getRoot();
        borrowers.insert(borrower);
    }

    public void fillSearchResults(ArrayList<BorrowerNode> searchResults, BorrowerNode root,int studentID){
        if(root==null)
            return ;

        if(root.getId()==studentID){
            searchResults.add(root);
        }

        fillSearchResults(searchResults,root.left,studentID);
        fillSearchResults(searchResults,root.right,studentID);

    }

    public ArrayList<BorrowerNode> borrowerSearch(int studentID){

        ArrayList<BorrowerNode> searchResults=new ArrayList<>();
        fillSearchResults(searchResults,borrowers.getRoot(),studentID);

        return searchResults;
    }

    public void returnBook(int studentID,long bookISBN){
        ArrayList<BorrowerNode> searchResults=new ArrayList<>();

        if(checkBorrowerExist(studentID)){
            fillSearchResults(searchResults,borrowers.getRoot(),studentID);

            for (BorrowerNode borrowerNode:searchResults){
                if(borrowerNode.getBookISBN()==bookISBN && borrowerNode.getRecordStatus().equals("Active")){
                    BookNode book=books.find(books.getRoot(),bookISBN);
                    book.setCopiesNum(book.getCopiesNum()+1);
                    book.setStatus(true);
                    borrowerNode.setRecordStatus("Returned");
                    break;
                }
            }

        }
    }

    public void updateBorrowerInfo(int studentID, String newName,LocalDate newBorrowDate){
        borrowers.update(studentID,newName,newBorrowDate);

    }

}
