import java.time.LocalDate;
import java.util.ArrayList;

public class LibraryManagement {
    BorrowTree borrowers = new BorrowTree();
    BookTree books = new BookTree();
    WaitingList waitingList=new WaitingList(100);

    public boolean checkBorrowerExist(int studentID) {
        if (borrowers.exists(borrowers.getRoot(), studentID))
            return true;

        return false;
    }

    public String addBorrower(int studentID, String name, long bookISBN, LocalDate borrowDate, boolean isGraduate) {
        BorrowerNode borrower = null;

        BookNode book = books.search(books.getRoot(), bookISBN);

        //check book
        if (book==null) {
            return "The book is not exist!";//الكتاب غير موجود
        }

        //check if the book is available
        if (!book.isStatus() || book.getCopiesNum()<=0) {
            addWaitingRequest(studentID,name,bookISBN,isGraduate,borrowDate);
            return "The book is not available , you are added to the waiting list";
        }

        int currentBorrowsCount = borrowers.countCurrentBorrowsNum(borrowers.getRoot(), studentID);

        if (checkBorrowerExist(studentID)) {
            borrower = borrowers.find(borrowers.getRoot(), studentID);

            int remainingBorrows=borrower.getMaxBorrows()-currentBorrowsCount;
            if(remainingBorrows<=0){
                return "You are exceeded the maximum limit of borrows!";
            }
        }

        book.setCopiesNum(book.getCopiesNum() - 1);
        if(book.getCopiesNum()==0)
            book.setStatus(false);

        borrower = new BorrowerNode(studentID, name, bookISBN, borrowDate, isGraduate);
        borrower.setCurrentBorrowsCount(currentBorrowsCount + 1);

        BorrowerNode root = borrowers.getRoot();
        borrowers.insert(borrower);

        return "The borrow was successful!";
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

    public String returnBook(int studentID,long bookISBN){
        ArrayList<BorrowerNode> searchResults=new ArrayList<>();
        ArrayList<WaitingRequest> list=new ArrayList<>();
        boolean isReturnedSuccessfully = false;

        if(checkBorrowerExist(studentID)){
            fillSearchResults(searchResults,borrowers.getRoot(),studentID);

            for (BorrowerNode borrowerNode:searchResults){
                if(borrowerNode.getBookISBN()==bookISBN && borrowerNode.getRecordStatus().equals("Active")){
                    BookNode book=books.search(books.getRoot(),bookISBN);
                    isReturnedSuccessfully=true;
                    borrowerNode.setRecordStatus("Returned");
                    boolean foundRequest=false;

                    while (waitingList.currentSize>0){
                        WaitingRequest currentRequest=waitingList.extractMax();

                        if (currentRequest.getBookISBN()==bookISBN){
                            addBorrower(currentRequest.getStudentID(),currentRequest.getStudentName(),currentRequest.getBookISBN(),LocalDate.now(),currentRequest.isGraduate());
                            foundRequest=true;
                            break;

                        }

                        list.add(currentRequest);

                    }

                    if (!foundRequest) {
                        book.setCopiesNum(book.getCopiesNum() + 1);
                        book.setStatus(true);
                    }
                    break;
                }
            }

            for (WaitingRequest request:list){
                waitingList.insert(request);
            }

        }

        if (isReturnedSuccessfully) {
            return "The book returned successfully and processed waiting list!";
        }

        return "Error: No active borrow record found for this student and book!";

    }

    public String updateBorrowerInfo(int studentID, String newName,LocalDate newBorrowDate){
        if(borrowers.update(studentID,newName,newBorrowDate)){
            return "The borrower info updated successfully!";
        }

        return "The borrower not found!";

    }

    public void addWaitingRequest(int studentID, String studentName,long bookISBN,boolean isGraduate,LocalDate requestDate){
        WaitingRequest request=new WaitingRequest(studentID,studentName,bookISBN,isGraduate,requestDate);
        waitingList.insert(request);
    }

    public void deleteWaitingRequest(){
        waitingList.extractMax();
    }



}
