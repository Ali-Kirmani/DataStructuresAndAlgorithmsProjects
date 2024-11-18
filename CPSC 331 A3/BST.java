
/**
 * Ali Kirmani 30115539 and Ibrahim Ahmed 30125006
 *BST class for part 1 questions 1 through 6
 * 
 * Implementation of a BST using linking
 * 
 * Implementation is by contract and by reference
 *
 * @author Jalal Kawash, based on the implementation of Dale, Joyce, and Weems (Object-Oriented Data Structures in Java)
 * 
 */
import java.util.Random;

public class BST<T extends Comparable> implements BSTInterface<T>
{   

    protected Node<T> root;
    
    public static final int INORDER = 0;
    public static final int PREORDER = 1;
    public static final int POSTORDER = 2;
    
    private LinkedQueue<T> inOrderQueue, preOrderQueue, postOrderQueue;

    public int tmpHeight, maxHeight;

    public Node<T> getRoot(){ // needed in TreePrinter
        return root;
    }

    /**
     * Constructor for objects of class BST
     */
    public BST()
    {
        root = null;
    }

    /**
     * Precondition: None
     * Postcondition: returns true if BST is empty
     */
    public boolean isEmpty()
    {
        return (root == null);
    }
    
    /**
     * Precondition: None
     * Postcondition: returns false
     */
    public boolean isFull()
    {
        return false;
    }
    
    /**
     * Precondition: None
     * Postcondition: returns the number of elements in the BST
     */
    public int size()
    {
        return recursiveSize(root);
    }
    
    int recursiveSize(Node<T> root) 
    {
        if (root == null) return 0;
        else return recursiveSize(root.getLeft()) + recursiveSize(root.getRight()) + 1;
    }
    
    /**
     * Precondition: None
     * Postcondition: deletes all the elements in the BST and resests it to the initial condition
     */
    public void clear()
    {
        root = null;
    }
    
    /**
     * Precondition: None
     * Postcondition: resets the current index to the begining of the BST
     */
    public void reset(int order) 
    {
        if (order == BST.INORDER)
        {
            inOrderQueue = new LinkedQueue<T>();
            inOrderTraversal(root);
        }
        
        if (order == BST.PREORDER)
        {
            preOrderQueue = new LinkedQueue<T>();
            preOrderTraversal(root);
        }
        
        if (order == BST.POSTORDER)
        {
            postOrderQueue = new LinkedQueue<T>();
            postOrderTraversal(root);
        }
    }
    
    void inOrderTraversal(Node<T> root) 
    {
        if (root != null) 
        {
            inOrderTraversal(root.getLeft());
            inOrderQueue.enqueue(root.getValue());
            inOrderTraversal(root.getRight());
        }
    }
    
    void preOrderTraversal(Node<T> root) 
    {
        if (root != null) 
        {
            preOrderQueue.enqueue(root.getValue());
            preOrderTraversal(root.getLeft());
            preOrderTraversal(root.getRight());
        }
    }
    
    void postOrderTraversal(Node<T> root) 
    {
        if (root != null) 
        {
            postOrderTraversal(root.getLeft());
            postOrderTraversal(root.getRight());
            postOrderQueue.enqueue(root.getValue());
        }
    }
    
    /**
     * Precondition: None
     * Postcondition: returns the next element in the list based on specified traversal order (inorder, preorder, postorder)
     */
    public T getNext(int order) 
    {
       if (order == BST.INORDER) return inOrderQueue.dequeue();
       if (order == BST.PREORDER) return preOrderQueue.dequeue();
       if (order == BST.POSTORDER) return postOrderQueue.dequeue();
       return null;
    }
    
    /**
     * Precondition: None
     * Postcondition: Adds a new element to the list
     */
    public void add(T item) 
    {
        root = recursiveAdd(item, root);
    }
    
    Node<T> recursiveAdd(T item, Node<T> root)
    {
        if (root == null) //insert here
        {
            root = new Node<T>();
            root.setValue(item);    
        }
        else if (item.compareTo(root.getValue()) < 0) // got to left subtree
            root.setLeft(recursiveAdd(item, root.getLeft()));
        else root.setRight(recursiveAdd(item, root.getRight())); // go right
        
        return root;
    }
    
    /**
     * Precondition: None
     * Postcondition: returns true if a given item is in the tree; otherwise returns false
     */
    public boolean contains(T item) 
    {
        return recursiveContains(item, root);
    }
    
    boolean recursiveContains(T item, Node<T> root)
    {
        if (root == null) return false;
        else if (item.compareTo(root.getValue()) < 0) return recursiveContains(item, root.getLeft());
        else if (item.compareTo(root.getValue()) > 0) return recursiveContains(item, root.getRight());
        else return true;
    }
    
    /**
     * Precondition: Item to be removed is in the tree
     * Postcondition: removes an item from the BST
     */
    public void remove(T item) 
    {
        root = recursiveRemove(item,root);
    }
    
    Node<T> recursiveRemove(T item, Node<T> root)
    {
        if (root == null) return null;
        else if (item.compareTo(root.getValue()) < 0) root.setLeft(recursiveRemove(item, root.getLeft()));
        else if (item.compareTo(root.getValue()) > 0) root.setRight(recursiveRemove(item, root.getRight()));
        else root = removeNode(root);
        return root;
    }
    
    Node<T> removeNode(Node<T> root)
    {
        T tmp;
        if (root.getLeft() == null) return root.getRight();
        else if (root.getRight() == null) return root.getLeft();
        else
        {
            tmp = predecessor(root.getLeft());
            root.setValue(tmp);
            root.setLeft(recursiveRemove(tmp, root.getLeft()));
            return root;
        }
    }
    
    T predecessor(Node<T> root)
    {
        while (root.getRight() != null) root = root.getRight();
        return root.getValue();
    }
    
    
    /**
     * Precondition: None
     * Postcondition: prints the contents of the list based on the provided traversal order type
     */
    public void printBST(int order)
    {
        int s = size();
        if (order == INORDER) 
        {
            reset(INORDER);
            inOrderTraversal(root);
            System.out.println("Get items inorder: ");
            for (int i = 0; i < s; i++) System.out.print(getNext(BST.INORDER) + " ");
            System.out.println();
        }
        
        if (order == PREORDER) 
        {
            reset(PREORDER);
            preOrderTraversal(root);
            System.out.println("Get items preorder: ");
            for (int i = 0; i < s; i++) System.out.print(getNext(BST.PREORDER) + " ");
            System.out.println();
        }
        
        if (order == POSTORDER) 
        {
            reset(POSTORDER);
            postOrderTraversal(root);
            System.out.println("Get items postorder: ");
            for (int i = 0; i < s; i++) System.out.print(getNext(BST.POSTORDER) + " ");
            System.out.println();
        }
        
    }

    //Returns the height of the tree
    @Override
    public int height() {
        // TODO Auto-generated method stub
        return calcHeight(root);

    }
    
    private int calcHeight(Node<T> node) {
        if (node == null) {
            return -1;
        } else {
            int leftHeight = calcHeight(node.getLeft());
            int rightHeight = calcHeight(node.getRight());
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }
    


    //Returns the level of the value inputted
    @Override
    public int level(T value) {
        return calcLevel(root, value, 0);
    }
    
    private int calcLevel(Node<T> node, T value, int level) {
        if (node == null) {
            return -1; // Value not in the tree
        } else if (node.getValue().equals(value)) {
            return level; // Value found, return level
        }
    
        int leftLevel = calcLevel(node.getLeft(), value, level + 1);
        int rightLevel = calcLevel(node.getRight(), value, level + 1);
    
        // Check if the value is found in either the left or right subtree
        if (leftLevel != -1) {
            return leftLevel; // Value found in left
        } else if (rightLevel != -1) {
            return rightLevel; // Value found in right
        } else {
            return -1; // Value not in tree
        }
    }

    //Finds the parent of the value inputted
@Override
public Node<T> parent(T value) {
    // TODO Auto-generated method stub
    return calcParent(root, value);
}

private Node<T> calcParent(Node<T> node, T value) {
    if (node == null) 
    {
        return null; // Value not in tree
    } if ((node.getLeft() != null && node.getLeft().getValue().equals(value)) || (node.getRight() != null && node.getRight().getValue().equals(value)))
    {
        return node;
    }
    
    Node<T> parent = calcParent(node.getLeft(), value);
    if (parent != null) {
        return parent; // Parent found in the left
    }
    
    return calcParent(node.getRight(), value); // Parent found in the right subtree (or null if not found)
    
}

//Returns true or false depending on whether or not the tree is complete
public boolean isComplete() {
    return recursiveIsComplete(root, 0);
}

private boolean recursiveIsComplete(Node<T> root, int index) {
    if (root == null)return true;
    if (index >= size()) return false;
    
    boolean ans = recursiveIsComplete(root.getLeft(), 2 * index + 1)
    && recursiveIsComplete(root.getRight(), 2 * index + 2);
    return ans;
}

//Returns true or false depending on whether or not the tree is perfect
@Override
    public boolean isPerfect() {
        int height = calcHeight(root);
        return isPerfectRecursive(root, height, 0);
    }
    
    private boolean isPerfectRecursive(Node<T> node, int height, int level) {
        if (node == null) {
            return true;
        }
    
        // Check if current level is last level
        if (level == height) {
            // For last level, no child nodes
            return node.getLeft() == null && node.getRight() == null;
        }
    
        // Check if both left and right subtrees exist
        if (node.getLeft() != null && node.getRight() != null) {
            return isPerfectRecursive(node.getLeft(), height, level + 1)
                    && isPerfectRecursive(node.getRight(), height, level + 1);
        }
    
        return false;
    }

    //Returns true or false depending on whether or not the tree has duplicate values
    @Override
    public boolean hasDoubles() {
        return recursiveHasDoubles(root);
    }
    
    private boolean recursiveHasDoubles(Node<T> root) {
        if (root == null) {
            return false;
        }
        
        // Check if visited
        if (root.getVisited()) {
            return true; // Duplicate found
        }
        
        root.setVisited(true); // Mark as visited
        
        // Check for duplicates in left and right subtrees
        return recursiveHasDoubles(root.getLeft()) || recursiveHasDoubles(root.getRight());
    }
}

