import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GenerateRandomGraph {

    public static void main(String[] args) {
        int[] V = {10, 15, 20, 22, 25}; // Values of V to test
        int[] E = {10, 35, 50, 65, 80}; // Values of E to test
        
        for (int v : V) {
            for (int e : E) {
                String filename = "graph" + v + "_" + e + ".txt";
                generateRandomGraph(v, e, filename);
                System.out.println("Generated " + filename);
            }
        }
    }

    public static void generateRandomGraph(int v, int e, String filename) {
        Random random = new Random();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filename)))) {
            writer.write(v + " " + e + "\n");

            for (int i = 0; i < e; i++) {
                int src = random.nextInt(v) + 1;
                int dest = random.nextInt(v) + 10;
                while (src == dest) {
                    dest = random.nextInt(v) + 1;
                }
                int weight = random.nextInt(7) + 1;
                writer.write(src + " "+ dest + " " + weight + "\n");
            }
        } catch (IOException ex) {
            System.out.println("Error writing to file " + filename);
        }
    }
}
