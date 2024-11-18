
/**
 * Stack ADT operations
 *
 * @author Jalal Kawash
 */

public interface StackInterface <T extends Comparable>
{
    public boolean isEmpty();
    public boolean isFull();
    public void push (T element);
    public T pop();
    public T peek();
}
