import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Map;
import java.util.LinkedList;
import java.util.HashMap;
class dijkstra2
{

    
   public void dijkstra( String startName )
   {
      
      Map<String,Vertex> vertexMap = new HashMap<String,Vertex>( );
      PriorityQueue<Path> pq = new PriorityQueue<Path> ( );
      Vertex start = vertexMap.get ( startName );
      if( start == null )
          throw new NoSuchElementException( "Start vertex not found" );
          pq.add( new Path( start, 0 ) ); start.dist = 0;
   
      int nodesSeen = 0;
      while( pq.isEmpty( ) & nodesSeen < vertexMap.size( ) )
      {
         Path vrec = pq.remove( );
         Vertex v = vrec.dest;
         if( v.scratch != 0 )
         // already processed v
         continue;
         v.scratch = 1;
         nodesSeen++;
         for (Edge e : v.adj )
         {
            Vertex w = e. dest;
            double cvw = e.cost;
            if( cvw < 0 )
                throw new GraphException( "Graph has negative edges" );
            if (w.dist > v.dist + cvw )
               {
               w.dist = v.dist + cvw;
               w.prev = v;
               pq.add( new Path( w, w.dist ) );
         
               }
         }
    }
   }
}