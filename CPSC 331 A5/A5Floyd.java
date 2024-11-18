public class A5Floyd 
{
    int i;
    int j;
    int k;
    public static int size;
    public static int density;
    public static float D[][];
    //AdjMatrixGraph<T> adjMatrixGraph = new AdjMatrixGraph<>(i);
    RandomAdjMatrixGraph randMatrixGraph = new RandomAdjMatrixGraph(size, density);

    public A5Floyd () {}

    public float[][] Floyd(RandomAdjMatrixGraph G)
    {
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
