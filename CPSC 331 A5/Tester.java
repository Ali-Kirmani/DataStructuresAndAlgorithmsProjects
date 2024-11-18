
public class Tester
{
    public static final int SIZE = 5000;
    public static void main(String[] args)
    {
        RandomAdjMatrixGraph rg;
        //rg.printGraph();
        
        for (int i = 0; i < 11; i++)
        {
            rg = new RandomAdjMatrixGraph(SIZE, i);
            System.out.printf("G has %d edges with density %d\n", rg.getNumEdges(), i);
            if (rg.isStronglyConnected()) System.out.println("The graph is strongly connected");
            else System.out.println("The graph is NOT strongly connected");
        
        }
        
        /**
        System.out.println("DFS walks from each vertix");
        for (int i = 0; i < SIZE; i++) {
            LinkedQueue q = rg.oneSourceDFS(i);
            System.out.printf("[%d]", i);
            q.printQueue();
        }
        
        System.out.println("BFS walks from each vertix");
        for (int i = 0; i < SIZE; i++) {
            LinkedQueue q = rg.oneSourceBFS(i);
            System.out.printf("[%d]", i);
            q.printQueue();
        }
        
        System.out.println("Reachability:");
        boolean[][] rm = rg.getReachMatrix();
        for (int i = 0; i < SIZE; i++)
        {
            System.out.printf("[%d]",i);
            for (int j = 0; j < SIZE; j++) 
            {
                if (rm[i][j]) 
                {
                    System.out.print(j);
                    if (j < SIZE-1) System.out.print(" ");
                }
            }
            System.out.println();
        }
        */
        
        
        /**
        System.out.println();
        System.out.println("Repeating for Ajacency List implementation");
        RandomAdjListGraph rlg = new RandomAdjListGraph(SIZE, 6);
        //rlg.printGraph();
        */
        /**
        System.out.println("DFS walks from each vertix");
        for (int i = 0; i < SIZE; i++) {
            LinkedQueue q = rlg.oneSourceDFS(i);
            System.out.printf("[%d]", i);
            q.printQueue();
        }
        
        System.out.println("BFS walks from each vertix");
        for (int i = 0; i < SIZE; i++) {
            LinkedQueue q = rlg.oneSourceBFS(i);
            System.out.printf("[%d]", i);
            q.printQueue();
        }
        
        System.out.println("Reachability:");
        rm = rlg.getReachMatrix();
        for (int i = 0; i < SIZE; i++)
        {
            System.out.printf("[%d]",i);
            for (int j = 0; j < SIZE; j++) 
            {
                if (rm[i][j]) 
                {
                    System.out.print(j);
                    if (j < SIZE-1) System.out.print(" ");
                }
            }
            System.out.println();
        }
        
        
        if (rlg.isStronglyConnected()) System.out.println("The graph is strongly connected");
        else System.out.println("The graph is NOT strongly connected");    
        */
        
    }
}
