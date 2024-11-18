/**
 * A1 Part 1b By Ali Kirmani 30115539 and Ibrahim Ahmed 30125006
 */
import java.math.BigInteger;
import java.io.FileWriter;
import java.io.IOException;

public class p2 {
    public BigInteger Fib2(BigInteger n)           //Method for Fib2 algorithm
    {
        BigInteger i = BigInteger.ONE;
        BigInteger j = BigInteger.ZERO;
        for(BigInteger k = BigInteger.ONE; k.compareTo(n) <= 0; k = k.add(BigInteger.ONE))
        {
            j = i.add(j);
            i = j.subtract(i);
        }  
        return j;
    }

    static BigInteger[] f = new BigInteger[5001];

   public static BigInteger Fib3(int n)         //Method for Fib3 algorithm
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
    
    

    //Main Method
    public static void main(String[] args) throws IOException
    {
        p1 p1 = new p1();
        f[0] = BigInteger.ZERO;                         //f[0] = 0
        f[1] = f[2] = BigInteger.ONE;                   //f[1] = f[2] = 1

        FileWriter writer2 = new FileWriter("FibTwo5000Out.txt");   //Creates file writer for fib2
        BigInteger P = BigInteger.valueOf(5001);
        for(BigInteger n = BigInteger.ZERO; n.compareTo(P) < 0; n = n.add(BigInteger.ONE))
        {
            long start = System.nanoTime(); //Start clock
            BigInteger x = p1.Fib2(n);      //Find Fib(n)
            long end = System.nanoTime();   //End clock
            System.out.print(x);            //Print value
            long time = end - start;        //Find total value
            System.out.printf(" took %d nanoseconds\n", time);
            writer2.write("Fib2("+ n +") = " + x + " It took " + time + " nanoseconds\n");
        }
        writer2.close();                                                    //Close file writer


        FileWriter writer3 = new FileWriter("FibThree5000Out.txt"); //Creates file writer for fib3
        for(int n = 0; n < 5001; n++)
        {
            long start = System.nanoTime(); //Start clock
            BigInteger x = Fib3(n);
            long end = System.nanoTime();   //End clock
            System.out.print(x);            //Print value
            long time = end - start;        //Find total value
            System.out.printf(" took %d nanoseconds\n", time);
            writer3.write("Fib3("+ n +") = " + x + " It took " + time + " nanoseconds\n");  //Write to file

        }
        writer3.close();                                                    //Close file writer
    }

}