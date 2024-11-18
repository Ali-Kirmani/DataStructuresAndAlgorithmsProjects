
/**
 * Implementation of an unbounded stack ADT using linking (edited to work as doubly linked stack)
 * 
 * Implementation uses exception throwing and by reference
 * 
 *
 * @author Jalal Kawash but edited by Ali Kirmani and Ibrahim Ahmed
 */


public class LinkedStack<T extends Comparable> implements StackInterface<T>
{
    private class Node<T extends Comparable>
    {
        private T value;
        private Node<T> next, prev;
    }
    private Node<T> topIndex;
    
    /**
     * Constructor for objects of class LinkedStack
     */
    public LinkedStack()
    {
        topIndex = null;
    }

    /**
     * Precondition: None
     * Postcondition: returns true if stack is empty
     */
    public boolean isEmpty()
    {
        return (topIndex == null);
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
     * Postcondition: Adds a new element to the stack
     */
    public void push(T item) 
    {
        Node<T> newNode = new Node<T>();
        newNode.value = item;
        
        if(topIndex != null) {
        newNode.next = topIndex;
        topIndex.prev = newNode;
        }
        
        
        topIndex = newNode;
    }
    
        
    /**
     * Precondition: None
     * Postcondition: removes the top item from the stack; throws ane exception if stack is empty
     */
    public T pop() throws UnderflowException
    {
        if (!isEmpty())
        {
            T tmp = topIndex.value;
            topIndex = topIndex.next;
            if (topIndex != null) {
            topIndex.prev = null;
            }
            return tmp;
        } 
        else throw new UnderflowException("Cannot pop from an empty stack");
                
    }
    
    /**
     * Precondition: None
     * Postcondition: peeks at the top of the stack
     */
    public T peek() throws UnderflowException
    {
        if (!isEmpty())
            return topIndex.value;
        else throw new UnderflowException("Cannot peek to an empty stack");
                
    }
    
    /**
     * Precondition: None
     * Postcondition: prints the contents of the stack
     */
    public void printStack()
    {
       System.out.print("top-> ");
       Node<T> tmp = topIndex;
       
       while (tmp != null)
       {
            System.out.print(tmp.value);
            if (tmp.next != null) System.out.print(", ");
            tmp = tmp.next;
       }
       System.out.println();
    }
}
