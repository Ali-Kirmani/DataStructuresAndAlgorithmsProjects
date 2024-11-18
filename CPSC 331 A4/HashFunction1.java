
/**
 * Defines a hash function to be used for A4 Part 2
 *
 * Authors: Ali Kirmani 30115539 Ibrahim Ahmed 30125006
 * 
 */
public class HashFunction1
{
    /**
         * An example hash function; From : Mark Weiss, Data Structures and Algorithm Analysis Using Java, 3rd ed, Pearson
         *
         * @preconsidtion: none
         * @postcondition: returns a hash value for this string
         * 
         */
    public int hash(int element) 
    {
        element = ((element >>> 16) ^ element) * 0x45d9f3b;
        element = ((element >>> 16) ^ element) * 0x45d9f3b;
        element = ((element >>> 16) ^ element) * 0x45d9f3b;
        return Math.abs(element)%9973;
    }   
}
