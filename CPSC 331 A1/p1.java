/*
 * A1 Part 1a By Ali Kirmani 30115539 and Ibrahim Ahmed 30125006
 */
import java.math.BigInteger;
import java.io.FileWriter;
import java.io.IOException;

public class p1 {

    public static BigInteger Fib1(BigInteger n)             //Method for Fib1 algorithm
    {                                   

        if(n.compareTo(BigInteger.valueOf(2)) < 0) {
            return n;
        }
        else {
            BigInteger n1 = Fib1(n.subtract(BigInteger.ONE));
            BigInteger n2 = Fib1(n.subtract(BigInteger.TWO));
    
            return n1.add(n2);
        }
    }

    public static BigInteger Fib2(BigInteger n)                    //Method for Fib2 algorithm
    {
        BigInteger i = BigInteger.ONE;                      //i = 1
        BigInteger j = BigInteger.ZERO;                     //j = 0
        for(BigInteger k = BigInteger.ONE; k.compareTo(n) <= 0; k = k.add(BigInteger.ONE))
        {
            j = i.add(j);
            i = j.subtract(i);
        }  
        return j;
    }

    static BigInteger[] f = new BigInteger[55];


   //Method to compute algorithm 3 
   public static BigInteger Fib3(int n)
    {
        int k;
        if(n < 3) return f[n];
        if(f[n] != null) return f[n];
        if((n % 2) != 0) k = (n+1)/2;
        else k = n/2;
        if((n % 2) != 0) f[n] = ((Fib3(k)).pow(2)).add((Fib3(k-1)).pow(2));
        else f[n] = ((Fib3(k-1)).multiply(BigInteger.TWO).add(Fib3(k))).multiply(Fib3(k));

        return f[n];
    }
    
    

    public static void main(String[] args) throws IOException 
    {
        //p1 p1 = new p1();                         
        BigInteger P = BigInteger.valueOf(54);//Variable where for loop limit is stored

        f[0] = BigInteger.ZERO;           //Setting f[0] to 0
        f[1] = f[2] = BigInteger.ONE;     //Setting f[1] and f[2] to 1

        FileWriter writer1 = new FileWriter("Fib1Out.txt");                        //For loop from 0 to 54
        for(BigInteger n = BigInteger.ZERO; n.compareTo(P) < 0; n = n.add(BigInteger.ONE))
        {
            long start = System.nanoTime(); //Start clock
            BigInteger x = Fib1(n);      //Find Fib(n)
            long end = System.nanoTime();   //End clock
            System.out.print(x);            //Print value
            long time = end - start;        //Find total value
            System.out.printf(" took %d nanoseconds\n", time);
            writer1.write("Fib1("+ n +") = " + x + " It took " + time + " nanoseconds\n");  //Write this to file
        }
        writer1.close();

        FileWriter writer2 = new FileWriter("Fib2Out.txt");
        for(BigInteger n = BigInteger.ZERO; n.compareTo(P) < 0; n = n.add(BigInteger.ONE))  //For loop from 0 to 54
        {
            long start = System.nanoTime(); //Start clock
            BigInteger x = Fib2(n);      //Find Fib(n)
            long end = System.nanoTime();   //End clock
            //System.out.print(x);            //Print value
            long time = end - start;        //Find total value
            //System.out.printf(" took %d nanoseconds\n", time);
            writer2.write("Fib2("+ n +") = " + x + " It took " + time + " nanoseconds\n");  //Write this to file
        }
        writer2.close();                    //Close file

        FileWriter writer3 = new FileWriter("Fib3Out.txt");                        //New file
        for(int n = 0; n < 54; n++)                                                         //For loop from 0 to 54
        {
            long start = System.nanoTime(); //Start clock
            BigInteger x = Fib3(n);      //Store value of fib3(n) in x
            long end = System.nanoTime();   //End clock
            //System.out.print(x);            //Print value
            long time = end - start;        //Find total value
            //System.out.printf(" took %d nanoseconds\n", time);          
            writer3.write("Fib3("+ n +") = " + x + " It took " + time + " nanoseconds\n");  //Write this to file
        }
        writer3.close();                    //Close file
    }

}