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

        if(root.getId()>=borrower.getId())
            root.left=insertBorrower(root.left,borrower);

        else
            root.right=insertBorrower(root.right,borrower);

        return root;

    }

    public boolean exists(BorrowerNode root,int borrowerId){
            if(root==null)
                return false;

            if(borrowerId==root.getId())
                return true;

            else if(borrowerId<root.getId())
                return exists(root.left,borrowerId);

            else
                return exists(root.right,borrowerId);

    }

    public BorrowerNode find(BorrowerNode root,int borrowerId){
        if(root==null)
            return null;

        if(borrowerId==root.getId())
            return root;

        else if(borrowerId<root.getId())
            return find(root.left,borrowerId);

        else
            return find(root.right,borrowerId);

    }

    public int countCurrentBorrowsNum(BorrowerNode root,int studentID){
        if(root==null)
            return 0;

        int borrowsNum=0;
        if (root.getId() == studentID && root.getRecordStatus().equals("Active")) {
            borrowsNum = 1;
        }

        return borrowsNum+countCurrentBorrowsNum(root.left,studentID)+countCurrentBorrowsNum(root.right,studentID);
    }

    public void update(int studentID, String newName,LocalDate newBorrowDate){
        if(exists(this.root,studentID)){
            updateBorrower(this.root,studentID,newName,newBorrowDate);
        }
    }

    public void updateBorrower(BorrowerNode borrower, int studentID, String newName,LocalDate newBorrowDate){
        if(borrower==null)
            return;

        if (borrower.getId()==studentID){
            if (newName!=null && !newName.equals(borrower.getName())){
                borrower.setName(newName);
            }

            if (newBorrowDate!=null && !borrower.getBorrowDate().equals(newBorrowDate) && borrower.getRecordStatus().equals("Active")){
                borrower.setBorrowDate(newBorrowDate);
                borrower.setReturnDate(newBorrowDate.plusDays(20));
            }
        }

        updateBorrower(borrower.left,studentID,newName,newBorrowDate);
        updateBorrower(borrower.right,studentID,newName,newBorrowDate);

    }



}
