
/*
 * Authors: Main author is Jalal but edited to work for A4 by Ali Kirmani 30115539 Ibrahim Ahmed 30125006
 * File: HashTableSC.java 
 * This file contains code mainly for part 2 of Assignment 4
 * 
 *
 * A hash table implementation using separate chaining
 *
 * @author Jalal Kawash
 * 
 */
import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.ArrayList;

public class HashTableSC<T> implements HashTableInterface<T>
{
    private ArrayList<Integer>[] hashTable;
    private HashFunction1 f;

    /**
     * Constructor for objects of class HashTableSC
     */
    public HashTableSC(HashFunction1 f, int maxSize)
    {
        hashTable = new ArrayList[maxSize];
        this.f = f;
    }

    /**
     * clears the hashtable
     *
     * @precondition: none
     * @postcondition: hash table becomes empty
     * 
     */
    public void clear()
    {
        for (int i = 0; i < hashTable.length; i++) hashTable[i] = null;
    }
    
    /**
     * adds an item to the hashtable
     *
     * @preconsidtion: none
     * @postcondition: item is added to the appropriat chain
     * 
     */
    public void add(int item)
    {
        int i = f.hash(item);
        if (hashTable[i] == null) hashTable[i] = new ArrayList<Integer>();
        hashTable[i].add(item);
    }
    
    /**
     * removes an item to the hashtable
     *
     * @preconsidtion: none
     * @postcondition: item is deleted from the hashtable
     * 
     */
    public void remove(int item)
    {
        hashTable[f.hash(item)].remove(item);
    }
    
    /**
     * looks for an item in a hash table 
     *
     * @preconsidtion: none
     * @postcondition: returns true if item is in the table; false otherwise
     * 
     */
    public boolean contains(int item)
    {
        int i = f.hash(item);
        if (hashTable[i] == null) return false;
        else return hashTable[i].contains(item);
    }
    
    public void printStats() 
    {
        int chains = 0;
        int items = 0;
        int chainLength;
        int totChainLength = 0;
        for (int i = 0; i < hashTable.length; i++)
        {
            if (hashTable[i] != null) {
                chains++;
                chainLength = 0;
                for (int j = 0; j < hashTable[i].size(); j++) 
                {
                    items++; 
                    chainLength++;
                }
                totChainLength += chainLength;
            }
        }
        //System.out.println("Statistics");
        System.out.println("--------------------------------------------");
        System.out.printf("Items hashed: %d, with load factor = %.2f\n", items, (float) items/hashTable.length);
        System.out.printf("Chains used: %d out of %d, chain usage: %.2f%%\n", chains, hashTable.length, ((float) chains/hashTable.length)*100);
        System.out.printf("Average chain length: %.2f (%d/%d)\n", (float) totChainLength/chains, totChainLength, chains);
        System.out.println("--------------------------------------------");
    }
    
    public void printTable() // only suitable for small hash tables
    {
        int chains = 0;
        int items = 0;
        int chainLength;
        int totChainLength = 0;
        System.out.println("Hash Table");
        for (int i = 0; i < hashTable.length; i++)
        {
            if (hashTable[i] != null) {
                chains++;
                chainLength = 0;
                System.out.print(i + " : ");
                for (int j = 0; j < hashTable[i].size(); j++) 
                {
                    items++; 
                    chainLength++;
                    System.out.print(hashTable[i].get(j));
                    if (j != hashTable[i].size() - 1) System.out.print(", ");
                }
                totChainLength += chainLength;
                System.out.println();
            }
        }
        System.out.println("----");
        System.out.printf("Items hashed: %d, with load factor = %.2f\n", items, (float) items/hashTable.length);
        System.out.printf("Chains used: %d out of %d, chain usage: %.2f\n", chains, hashTable.length, ((float) chains/hashTable.length)*100);
        System.out.printf("Average chain length: %.2f (%d/%d)\n", (float) totChainLength/chains, totChainLength, chains);
    }
}
