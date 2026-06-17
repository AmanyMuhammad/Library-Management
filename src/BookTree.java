import java.util.HashMap;

public class BookTree {
    private BookNode root;
    private HashMap<String,Integer> authorReadCounts=new HashMap<>();

    public void recursiveInsert(BookNode newNode) {
        root = insert(root, newNode);
    }

    private BookNode insert(BookNode root, BookNode newNode) {

        int data = newNode.getISBN();

        if (root == null) {
            root = newNode;
            return root;
        }
        else if (data < root.getISBN()) {
            root.left = insert(root.left, newNode);
        }
        else {
            root.right = insert(root.right, newNode);
        }

        return root;
    }










    // For testing
    public void display() {
        displayHelper(root);
    }
    // For testing
    private void displayHelper(BookNode root) {

        if (root != null) {
            displayHelper(root.left);
            System.out.print(root.getISBN() + " ");
            displayHelper(root.right);
        }
    }

}





/*

    Delay
    public void searchBST(){

    }


    public void editBST(){

    }

    public void deleteBST(){

    }

    public void addAVL(){

    }

    ???
    public void editAVL(){

    }

    public void deleteAVL(){

    }


    Done:
    - insert BST

 */