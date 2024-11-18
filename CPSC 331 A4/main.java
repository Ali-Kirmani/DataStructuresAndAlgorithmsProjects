/*
 * Author: Ali Kirmani 30115539 Ibrahim Ahmed 30125006
 * Code for Part 1 and 2 of Assignment 4
 * Code for methods Partition, Swap, and quickSort are from GeeksForGeeks https://www.geeksforgeeks.org/quick-sort/
 */

import java.util.Arrays;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.Math;
import java.io.*;

class main
{
//Sequential Search **TA SAID MAYBE MAKE THIS A SEPARATE CLASS FOR EARCH SEARCH METHOD**
    public static int seqSearch(int element, int search[]) 
    {
        int i = 0;
        while(i < search.length)
        {
            if(element == search[i]) 
            {
                //System.out.println("Element " + element + " found at the " + i + " index");
                //writer1.write("Element " + element + " found at the " + i + " index");

                return i;
            }
            i++;
        }
        //System.out.println("Element " + element + " not found in the array");
        //writer1.write("Element " + element + " not found in the array");

        return -1;
    }

    public static int binSearch(int element, int search[], int low, int high)
    {
        if(low>high) return -1;
        int mid = (int)Math.floor((low + high)/2);
	    if (element == search[mid]) 
        {
            //System.out.println("Element " + element + " is at " + mid + "index");
            return mid;
        }
        else if (element < search[mid]) return binSearch(element, search, low, mid - 1);
        else return binSearch(element, search, mid + 1, high);
    }

    // A utility function to swap two elements
    static void swap(int[] arr, int i, int j)
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
 
    // This function takes last element as pivot,
    // places the pivot element at its correct position
    // in sorted array, and places all smaller to left
    // of pivot and all greater elements to right of pivot
    static int partition(int[] arr, int low, int high)
    {
        // Choosing the pivot
        int pivot = arr[high];
 
        // Index of smaller element and indicates
        // the right position of pivot found so far
        int i = (low - 1);
 
        for (int j = low; j <= high - 1; j++) {
 
            // If current element is smaller than the pivot
            if (arr[j] < pivot) {
 
                // Increment index of smaller element
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return (i + 1);
    }
 
    // The main function that implements QuickSort
    // arr[] --> Array to be sorted,
    // low --> Starting index,
    // high --> Ending index
    static void quickSort(int[] arr, int low, int high)
    {
        if (low < high) {
 
            // pi is partitioning index, arr[p]
            // is now at right place
            int pi = partition(arr, low, high);
 
            // Separately sort elements before
            // partition and after partition
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }
    // To print sorted array
    public static void printArr(int[] arr)
    {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " \n");
        }
    }

    

    public static void main(String[] args) throws IOException 
    {
        //Make search arrays of sizes 1000-1mil
        //With random values 0-5000
        
        //For rand values make them from 0-5000 and
        //store them in array of size 100
        //Make 5001 the 100th cell in the array

        //elements array is generated once and is
        //fixed for all search methods
        int n;
        int i;
        //int search[];
        int elements[] = new int[100];
        elements[99] = 5001;

        Random rand = new Random();
        FileWriter writer1 = new FileWriter("p1Out.txt");

        HashFunction1 hashFunction1 = new HashFunction1();
        HashTableSC<Integer> hashTableSC = new HashTableSC<Integer>(hashFunction1, 9973);


        //Generating elements array
        for(i = 0; i<99; i++)
        {
            int temp = rand.nextInt(5000);
            //Ensures elements are distinct
            if(!Arrays.asList(elements).contains(temp))
            {
                elements[i] = temp;
            }
            else i--;
            //System.out.println(elements[i]);
        }
        //System.out.println(elements[99]);

        //Looping through arrays of size n
        for(n = 1000; n<=1001000; n = n + 10000)
        //for(n = 1000; n<=2000; n = n + 100)
        //for(n = 10; n<100; n = n + 10)
        {
            //Making array of size n **MAKE A SEPARATE FUNCTION**
            int search[] = new int[n];
            for(i = 0; i<n; i++)
            {
                int temp = rand.nextInt(5000);
                search[i] = temp;
                //System.out.println(search[i]);
            }

            //Calling seq search on element i
            long start = System.nanoTime(); //Start clock
            for(i = 0; i<elements.length; i++)
            {
                seqSearch(elements[i], search);
            }
            long end = System.nanoTime();   //End clock
            long time = end - start;        //Find total value
            //writer1.write("Linear: Array of size " + n + " Took " + time + " time\t");
            writer1.write(n + ", " + time + "\t");
            //System.out.printf(" took %d nanoseconds\n", time);

            for(i = 0; i<n; i++)
            {
                hashTableSC.add(search[i]);
            }

            quickSort(search, 0, n - 1);
            //System.out.println("Sorted array:");
            //printArr(search);
            
            //Binary Search
            start = System.nanoTime(); //Start clock
            for(i = 0; i<elements.length; i++)
            {
                binSearch(elements[i], search, 0, n-1);
            }
            end = System.nanoTime();   //End clock
            time = end - start;        //Find total value
            writer1.write(n + ", " + time + "\t");
            //writer1.write("Binary: Array of size " + n + " Took " + time + " time\t");

            start = System.nanoTime(); //Start clock
            for(i = 0; i<elements.length; i++)
            {
                hashTableSC.contains(elements[i]);
            }
            end = System.nanoTime();   //End clock
            time = end - start;        //Find total value
            writer1.write(n + ", " + time + "\n");
            //writer1.write("Hash: Array of size " + n + " Took " + time + " time\t");
            //hashTableSC.printTable();
            search = null;
        }
        writer1.close();
    }
}