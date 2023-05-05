// Represents an edge in the graph.
class Edge
{
    public Vertex     dest;   // Second vertex in Edge
    public double     cost;   // Edge cost
    public String startname;
    
    public Edge( Vertex d, double c )
    {
        dest = d;
        cost = c;
    }
}
