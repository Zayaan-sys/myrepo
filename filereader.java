import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class filereader {
    public static void main( String [ ] args )
    {
        
        Graph g = new Graph( );
        	
            try (BufferedReader graphFile = new BufferedReader(new FileReader("graph.txt"))) {
                // Read the edges and insert
                String line;
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
                        }
                        catch( NumberFormatException e )
                          { System.err.println( "Skipping ill-formatted line " + line ); }
                     }
            }
            
         
         catch( IOException e )
           { System.err.println( e ); }

    

         
    } 
}
