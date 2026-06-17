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

    public boolean update(int ISBN, String newTitle, String newAuthor, int newCopiesNum) {
        BookNode target = search(root, ISBN);

        // If No Input => Keep the old data
        if (newTitle == null || newTitle.trim().isEmpty()) {
            newTitle = target.getTitle();
        }
        if (newAuthor == null || newAuthor.trim().isEmpty()) {
            newAuthor = target.getAuthor();
        }
        if (newCopiesNum < 0) {
            newCopiesNum = 0;
        }

        // Author name changed --> Migrate his read counts
        if (!target.getAuthor().equals(newAuthor)) {
            int readCount = authorReadCounts.getOrDefault(target.getAuthor(), 0);
            authorReadCounts.remove(target.getAuthor());
            authorReadCounts.put(newAuthor, readCount);
        }

        target.setTitle(newTitle);
        target.setAuthor(newAuthor);
        target.setCopiesNum(newCopiesNum);

        target.setStatus(newCopiesNum > 0);

        return true;
    }

    private BookNode search(BookNode node, int ISBN) {
        if (node == null || ISBN == node.getISBN())
            return node;

        if (ISBN < node.getISBN())
            return search(node.left, ISBN);

        return search(node.right, ISBN);
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
