
/**
 * A tester class for the LinkedStack ADT
 *
 * @author Jalal Kawash
 */

public class LinkedStackTester
{
    public static void main(String[] args) 
    {
        System.out.println("Creating a new stack of integers");
        LinkedStack<Integer> s = new LinkedStack<Integer>();
        s.printStack();
        s.push(new Integer(4));
        s.printStack();
        System.out.println("The peek of the stack is: " + s.peek());
        s.push(new Integer(2));
        s.printStack();
        System.out.println("The peek of the stack is: " + s.peek());
        s.push(new Integer(5));
        s.printStack();
        System.out.println("The peek of the stack is: " + s.peek());
        s.push(new Integer(7));
        s.printStack();
        System.out.println("The peek of the stack is: " + s.peek());
        System.out.println("Popped: " + s.pop());
        System.out.println("The peek of the stack is: " + s.peek());
        
        System.out.println("Clearning the stack");
        while (!s.isEmpty()) s.pop();
        s.printStack();
        
        System.out.println("Attempting a pop on an empty stack");
        try {
            s.pop();
        } catch(UnderflowException e) {
            System.out.println(e);
        }
        
        
        System.out.println("Creating a another stack");
        for (int i = 0; i < 10; i++)
        {
            s.push(new Integer(9 - i));
            System.out.println("Pushed " + (9 - i) );
        }
        s.printStack();
        
        System.out.println("Pushing one more element");
        try {
            s.push(new Integer(10));
        } catch(OverflowException e) {
            System.out.println(e);
        }
        
        System.out.println("Pushed 10");
        s.printStack();
        
    }
}
