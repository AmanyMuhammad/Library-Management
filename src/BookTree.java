import java.util.HashMap;

public class BookTree {
    private BookNode root;
    private HashMap<String,Integer> authorReadCounts=new HashMap<>();

    public BookNode getRoot() {
        return root;
    }

    private void recursiveInsertBST(BookNode newNode) {
        root = insertBST(root, newNode);
    }

    private BookNode insertBST(BookNode root, BookNode newNode) {

        long data = newNode.getISBN();

        if (root == null) {
            root = newNode;
            return root;
        }
        else if (data < root.getISBN()) {
            root.left = insertBST(root.left, newNode);
        }
        else {
            root.right = insertBST(root.right, newNode);
        }

        return root;
    }

    public boolean update(long ISBN, String newTitle, String newAuthor, int newCopiesNum) {
        BookNode target = search(root, ISBN);

        // If No Input => Keep the old data
        if (newTitle.isEmpty() || newTitle.trim().isEmpty() || newTitle.equalsIgnoreCase("Title")) {
            newTitle = target.getTitle();
        }
        if (newAuthor.isEmpty() || newAuthor.trim().isEmpty() || newAuthor.equalsIgnoreCase("Author")) {
            newAuthor = target.getAuthor();
        }
        if (newCopiesNum < 0) {
            newCopiesNum = target.getCopiesNum();
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

    public BookNode search(BookNode node, long ISBN) {
        if (node == null || ISBN == node.getISBN())
            return node;

        if (ISBN < node.getISBN())
            return search(node.left, ISBN);

        return search(node.right, ISBN);
    }

    private int height(BookNode node) {
        return (node == null) ? -1 : node.height;
    }

    private void updateHeight(BookNode node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    private int balanceFactor(BookNode node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    private BookNode rotateRight(BookNode n) {
        BookNode y = n.left;
        BookNode x = y.right;

        y.right = n;
        n.left = x;

        updateHeight(n); // n is now lower, update first
        updateHeight(y);
        return y;
    }

    private BookNode rotateLeft(BookNode x) {
        BookNode y = x.right;
        BookNode z = y.left;

        y.left = x;
        x.right = z;

        updateHeight(x);
        updateHeight(y);
        return y;
    }

    private BookNode rebalance(BookNode node) {
        updateHeight(node);
        int bf = balanceFactor(node);

        // Left-Left
        if (bf > 1 && balanceFactor(node.left) >= 0)
            return rotateRight(node);

        // Left-Right
        if (bf > 1 && balanceFactor(node.left) < 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        // Right-Right
        if (bf < -1 && balanceFactor(node.right) <= 0)
            return rotateLeft(node);

        // Right-Left
        if (bf < -1 && balanceFactor(node.right) > 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        // no need to rebalance (already rebalanced)
        return node;
    }

    public void insert(BookNode newNode) {
        root = insertAVL(root, newNode);
    }

    private BookNode insertAVL(BookNode root, BookNode newNode) {
        if (root == null)
            return newNode;

        if (newNode.getISBN() < root.getISBN())
            root.left = insertAVL(root.left, newNode);
        else if (newNode.getISBN() > root.getISBN())
            root.right = insertAVL(root.right, newNode);

        return rebalance(root);
    }

    public boolean delete(long ISBN) {
        root = deleteAVL(root, ISBN);
        return true;
    }

    private BookNode deleteAVL(BookNode node, long ISBN) {
        if (node == null)
            return null;

        int cmp = Long.compare(ISBN, node.getISBN());

        if (ISBN < node.getISBN()) {
            node.left = deleteAVL(node.left, ISBN);
        } else if (ISBN > node.getISBN()) {
            node.right = deleteAVL(node.right, ISBN);

        } else {    // Node Found

            // Case 1 & 2: zero or one child
            if (node.left == null || node.right == null) {
                node = (node.left != null) ? node.left : node.right;
            } else {
                // Case 3: two children — replace with in-order successor
                BookNode successor = findMin(node.right);

                // Copy successor's data into current node (ISBN stays as key)
                node.setTitle(successor.getTitle());
                node.setAuthor(successor.getAuthor());
                node.setCopiesNum(successor.getCopiesNum());
                node.setStatus(successor.isStatus());
                node.setBorrowCount(successor.getBorrowCount());
                node.setISBN(successor.getISBN());

                // Delete the successor from the right subtree
                node.right = deleteAVL(node.right, successor.getISBN());
            }
        }

        // ── 2. Rebalance (node can be null if it was a leaf) ──
        if (node == null)
            return null;

        return rebalance(node);
    }

    private BookNode findMin(BookNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public int getAvailableBooksCount() {
        return countAvailable(root);
    }

    private int countAvailable(BookNode node) {
        if (node == null)
            return 0;
        int count = (node.isStatus()) ? 1 : 0;
        return count + countAvailable(node.left) + countAvailable(node.right);
    }

    public BookNode getMostReadBook() {
        return findMostReadBook(root, null);
    }

    private BookNode findMostReadBook(BookNode node, BookNode maxNode) {
        if (node == null)
            return maxNode;

        if (maxNode == null || node.getBorrowCount() > maxNode.getBorrowCount())
            maxNode = node;

        maxNode = findMostReadBook(node.left, maxNode);
        maxNode = findMostReadBook(node.right, maxNode);
        return maxNode;
    }

    public String getMostReadAuthor() {
        if (authorReadCounts.isEmpty())
            return "No Data";

        return authorReadCounts.entrySet()
                .stream()
                .max(HashMap.Entry.comparingByValue())
                .get()
                .getKey();
    }

    // For testing
    public void display() {
        displayHelper(root);
    }
    // For testing
    private void displayHelper(BookNode root) {

        if (root != null) {
            displayHelper(root.left);
            displayHelper(root.right);
        }
    }

    public void recordBorrow(long ISBN) {
        BookNode book = search(root, ISBN);
        if (book == null) return;

        book.setBorrowCount(book.getBorrowCount() + 1);

        String author = book.getAuthor();
        authorReadCounts.put(author, authorReadCounts.getOrDefault(author, 0) + 1);
    }

}


/*
    Done:
    - Insert BST
    - Update BST
    - Search BST
    - Delete AVL
    - Insert AVL

 */
