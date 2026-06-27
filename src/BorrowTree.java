import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;

public class BorrowTree {
    private BorrowerNode root;

    public BorrowerNode getRoot(){
        return this.root;
    }

    public void insert(BorrowerNode borrower){
        this.root=insertBorrower(this.root,borrower);
    }

    public BorrowerNode insertBorrower(BorrowerNode root, BorrowerNode borrower){
        if(root==null)
            return borrower;

        if(root.getStudentID()>=borrower.getStudentID())
            root.left=insertBorrower(root.left,borrower);

        else
            root.right=insertBorrower(root.right,borrower);

        return root;
    }

    public boolean exists(BorrowerNode root,int borrowerId){
        if(root==null)
            return false;

        if(borrowerId==root.getStudentID())
            return true;

        else if(borrowerId<root.getStudentID())
            return exists(root.left,borrowerId);

        else
            return exists(root.right,borrowerId);

    }

    public BorrowerNode find(BorrowerNode root,int borrowerId){
        if(root==null)
            return null;

        if(borrowerId==root.getStudentID())
            return root;

        else if(borrowerId<root.getStudentID())
            return find(root.left,borrowerId);

        else
            return find(root.right,borrowerId);

    }

    public BorrowerNode findBorrowRecord(BorrowerNode root, int studentID, long isbn, LocalDate borrowDate){
        if(root==null)
            return null;

        if(root.getStudentID()==studentID && root.getBookISBN()==isbn && root.getBorrowDate().equals(borrowDate)){
            return root;
        }

        BorrowerNode found=findBorrowRecord(root.left,studentID,isbn,borrowDate);

        if(found!=null)
            return found;

        return findBorrowRecord(root.right,studentID,isbn,borrowDate);
    }

    public int countCurrentBorrowsNum(BorrowerNode root,int studentID){
        if(root==null)
            return 0;

        int borrowsNum=0;
        if (root.getStudentID() == studentID && root.getRecordStatus().equalsIgnoreCase("ACTIVE")) {
            borrowsNum = 1;
        }

        return borrowsNum+countCurrentBorrowsNum(root.left,studentID)+countCurrentBorrowsNum(root.right,studentID);
    }

    public boolean update(int id, String newName,LocalDate newBorrowDate){
        return updateBorrower(this.root,id,newName,newBorrowDate);
    }

    public boolean updateBorrower(BorrowerNode borrower, int studentID, String newName, LocalDate newBorrowDate) {
        if (borrower == null)
            return false;

        if (studentID < borrower.getStudentID())
            return updateBorrower(borrower.left, studentID, newName, newBorrowDate);

        if (studentID > borrower.getStudentID())
            return updateBorrower(borrower.right, studentID, newName, newBorrowDate);

        if (studentID==borrower.getStudentID()){
            if (newName!=null && !newName.equals(borrower.getName()))
                borrower.setName(newName);
        }

        if (newBorrowDate!=null && !borrower.getBorrowDate().equals(newBorrowDate) && borrower.getRecordStatus().equalsIgnoreCase("ACTIVE")) {
            borrower.setBorrowDate(newBorrowDate);
            borrower.setReturnDate(newBorrowDate.plusDays(20));
        }

        return true;
    }

    public void fillTableFromTree(BorrowerNode root, DefaultTableModel tableModel){
        if(root==null)
            return;

        fillTableFromTree(root.left,tableModel);

        tableModel.addRow(new Object[]{
                root.getStudentID(),
                root.getName(),
                root.getBookISBN(),
                root.getBorrowDate(),
                root.getReturnDate(),
                (root.isGraduate()?"Graduate":"Student"),
                root.getRecordStatus()
        });

        fillTableFromTree(root.right,tableModel);
    }

}
