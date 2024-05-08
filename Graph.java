/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


// Graph class: evaluate shortest paths.
//
// CONSTRUCTION: with no parameters.
//
// ******************PUBLIC OPERATIONS**********************
// void addEdge( String v, String w, double cvw )
//                              --> Add additional edge
// void printPath( String w )   --> Print path after alg is run
// void dijkstra( String s )    --> Single-source weighted
// ******************ERRORS*********************************
// Some error checking is performed to make sure graph is ok,
// and to make sure graph satisfies properties needed by each
// algorithm.  Exceptions are thrown if errors are detected.

public class Graph
{
    public static final double INFINITY = Double.MAX_VALUE;
    static Map<String,Vertex> vertexMap = new HashMap<String,Vertex>( );
    static int opcount_v ;
    static int opcount_e;
    static int opcount_pq; 
    /**
     * Add a new edge to the graph.
     */

    
    
    

    public void addEdge( String sourceName, String destName, double cost )
    {
        Vertex v = getVertex( sourceName );
        Vertex w = getVertex( destName );
        v.adj.add( new Edge( w, cost ) );
    }

    /**
     * Driver routine to handle unreachables and print total cost.
     * It calls recursive routine to print shortest path to
     * destNode after a shortest path algorithm has run.
     */
    

    /**
     * If vertexName is not present, add it to vertexMap.
     * In either case, return the Vertex.
     */
    private Vertex getVertex( String vertexName )
    {
        Vertex v = vertexMap.get( vertexName );
        //System.out.println(v);
        if( v == null )
        {
            v = new Vertex( vertexName );
            vertexMap.put( vertexName, v );
        }
        else 
        {vertexMap.put( vertexName, v );}
        return(v);
        //return v;
    }

    /**
     * Recursive routine to print shortest path to dest
     * after running shortest path algorithm. The path
     * is known to exist.
     */
    public String getPath( String destName )
{
    Vertex w = vertexMap.get( destName );
    if( w == null )
        throw new NoSuchElementException( "Destination vertex not found" );
    else if( w.dist == INFINITY )
        return destName + " is unreachable";
    else
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Cost is: ").append(w.dist).append(" from ");
        getPath(w, sb);
        return sb.toString();
    }
}

private void getPath(Vertex dest, StringBuilder sb) {
    if( dest.prev != null )
    {
        getPath( dest.prev, sb );
        sb.append(" to ");
    }
    sb.append(dest.name);
}
public void printPath(String destName) {
    String path = getPath(destName);
    System.out.println(path);
}

    
    /**
     * Initializes the vertex output info prior to running
     * any shortest path algorithm.
     */
    private static void clearAll( )
    {
        for( Vertex v : vertexMap.values( ) )
            v.reset( );
    }

    /**
     * Single-source weighted shortest-path algorithm. (Dijkstra) 
     * using priority queues based on the binary heap
     */
    public static void dijkstra( String startName )
    {
        PriorityQueue<Path> pq = new PriorityQueue<Path>( );
        
        Vertex start = vertexMap.get( startName );
        if( start == null )
            throw new NoSuchElementException( "Start vertex not found" );

        clearAll( );
        pq.add( new Path( start, 0 ) ); start.dist = 0;
        
        int nodesSeen = 0;
        while( !pq.isEmpty( ) && nodesSeen < vertexMap.size( ) )
        {
            opcount_pq+=(int)(Math.log(pq.size())/Math.log(2));
            Path vrec = pq.remove( );
            Vertex v = vrec.dest;
            if( v.scratch != 0 )  // already processed v
                continue;
            
            opcount_v++;   
            v.scratch = 1;
            nodesSeen++;

            for( Edge e : v.adj )
            {
                Vertex w = e.dest;
                double cvw = e.cost;
                                 
                if( cvw < 0 )
                    throw new GraphException( "Graph has negative edges" );
                
                opcount_e++;  
                if( w.dist > v.dist + cvw )
                {
                    w.dist = v.dist +cvw;
                    w.prev = v;
                    pq.add( new Path( w, w.dist ) );
                    opcount_pq+=(int)(Math.log(pq.size())/Math.log(2));
                
                }

                
            }
            
        }
            

        }
       
    

    /**
     * A main routine that:
     * 1. Reads a file containing edges (supplied as a command-line parameter);
     * 2. Forms the graph;
     * 3. Repeatedly prompts for two vertices and
     *    runs the shortest path algorithm.
     * The data file is a sequence of lines of the format
     *    source destination cost
     */
    public static void main( String [ ] args )
    {
        
        Graph g = new Graph( );
        String fileName = "graph40_80.txt";
        String line;    
        try (BufferedReader graphFile = new BufferedReader(new FileReader(fileName))){
                // Read the edges and insert
                System.out.println("V " +"E "+"Cost "+"OpV "+"OpE "+"PQ");
                System.out.println("-------------------------------------");
                while ((line = graphFile.readLine()) != null) {
                    StringTokenizer st = new StringTokenizer(line);
                    try {
                        
                        if (st.countTokens() != 3) {
                            System.err.println("Skipping ill-formatted line " + line);
                            continue;
                            }
                            String source  = st.nextToken( );
                            String dest    = st.nextToken( );
                            int    cost    = Integer.parseInt( st.nextToken( ) );
                            g.addEdge( source, dest, cost );
                            
                            Graph.dijkstra(source);
                            System.out.println(source +","+dest +"," + cost +"," + Graph.opcount_v +","+ Graph.opcount_e +","+ Graph.opcount_pq);
                            
                        }
                        catch( NumberFormatException e )
                          { System.err.println( "Skipping ill-formatted line " + line ); }
                     }
            }
            
         
         catch( IOException e )
           { System.err.println( e ); }

           
        

       
    } 

    
    }

