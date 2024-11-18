import java.util.ArrayList;
import java.util.HashSet;
import java.io.FileWriter;
import java.io.IOException;

public class A5 {

    public static void main(String[] args) throws IOException {
        FileWriter writer1 = new FileWriter("p1Out.txt");   
        //part 1
        for(int size=500; size<1001; size++) {
        //    for(int size=5; size<11; size++) {
            RandomAdjMatrixGraph graph = new RandomAdjMatrixGraph(size, 5);
            long start = System.nanoTime();
            for(int s=0; s<size; s++)
                Dijkstra(graph, s);
            long finish = System.nanoTime();
            long time = finish - start;
            writer1.write("Dijkstra Size:"+ size +" It took " + time + " nanoseconds");  //Write this to file
            System.out.println("Dijkstra Size:"+ size +" It took " + time + " nanoseconds");

            start = System.nanoTime();
            Floyd(graph);
            finish = System.nanoTime();
            time = finish - start;
            writer1.write("\tFloyd Size:"+ size +" It took " + time + " nanoseconds\n");  //Write this to file
            System.out.println("\tFloyd Size:"+ size +" It took " + time + " nanoseconds\n");
        }
        writer1.close();

        //part 2
        FileWriter writer2 = new FileWriter("p2Out.txt"); 
        for(int d=1; d<=9; d++) {
            
            RandomAdjMatrixGraph graph = new RandomAdjMatrixGraph(5000, d);
            //RandomAdjMatrixGraph graph = new RandomAdjMatrixGraph(10, d);

            long start = System.nanoTime();
            for(int s=0; s<graph.size; s++)
                Dijkstra(graph, s);
            long finish = System.nanoTime();
            long time = finish - start;
            writer2.write("Dijkstra Density:"+ d +" It took " + time + " nanoseconds");  //Write this to file
            System.out.println("Dijkstra Density:"+ d +" It took " + time + " nanoseconds");


            start = System.nanoTime();
            Floyd(graph);
            finish = System.nanoTime();
            time = finish - start;
            writer2.write("\tFloyd Density:"+ d +" It took " + time + " nanoseconds\n");  //Write this to file
            System.out.println("\tFloyd Density:"+ d +" It took " + time + " nanoseconds\n");


        }
        writer2.close();

        // System.out.print("D: ");
        //DELEETE THISSSSSSSSSS
        // for(int t=0; t<f.length; t++)
        //     if(f[t] == Integer.MAX_VALUE)
        //         System.out.print("INF, ");
        //     else
        //         System.out.printf("%.2f, ", f[t]);

        // System.out.println("");

     }

    public static float[] Dijkstra(RandomAdjMatrixGraph g, int s){
        ArrayList<Integer> S = new ArrayList<>();
        S.add(s);

        float[] D = new float[g.size];

        for(int i = 0; i < g.size; i++){
            if(g.getNode(s,i) != null)
                D[i] = g.getNode(s,i).getWeight();
            else
                D[i] = Integer.MAX_VALUE;
        }

        D[s] = 0;
        for(int j = 0; j < g.size; j++){
            if(j!=s)
            {
                float min = Integer.MAX_VALUE;
                int minK = 0;

                for(int k = 0; k < D.length; k++){
                    if(D[k] < min && !S.contains(k)){
                        min = D[k];
                        minK = k;
                    }
                }
                S.add(minK);

                for(int v = 0; v<g.size; v++) {
                    if(g.getNode(minK,v) != null && !S.contains(v))
                        D[v] = Math.min(D[v], D[minK] + g.getNode(minK,v).getWeight());
                }
            
            }
        }

        return D;
    }

    public static float[][] Floyd(RandomAdjMatrixGraph G)
    {
        int i;
        int j;
        int k;

        float D[][];
        D = new float[G.size][G.size];
        for(i = 0; i < G.size; i++)
        {
            for(j = 0; j < G.size; j++)
            {
                if(G.getNode(i, j) != null) D[i][j] = G.getNode(i,j).getWeight();
            }
        }

        for(i = 0; i < G.size; i++)
        {
            D[i][i] = 0;
        }

        for(k = 0; k < G.size; k++)
        {
            for(i = 0; i < G.size; i++)
            {
                for(j = 0; j < G.size; j++)
                {
                    if(D[i][k] + D[k][j] < D[i][j])
                    {
                        D[i][j] = D[i][k] + D[k][j];
                    }
                }
            }
        
        }
        return D;
    }
}
