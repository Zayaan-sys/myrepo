import java.util.*;

public class randomgenerator {
    public static void main(String[] args) {
        // Create two sets of values
        Set<Integer> set1 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        Set<Integer> set2 = new HashSet<>(Arrays.asList(6, 7, 8, 9, 10));
        
        // Create a new set to store the random values
        Set<Integer> randomSet = new HashSet<>();
        
        // Set the number of random values to generate
        int numRandomValues = 5;
        
        // Loop through both sets to generate random values
        Random rand = new Random();
        while (randomSet.size() < numRandomValues) {
            int randIndex1 = rand.nextInt(set1.size());
            int randIndex2 = rand.nextInt(set2.size());
            int randomValue1 = (int) set1.toArray()[randIndex1];
            int randomValue2 = (int) set2.toArray()[randIndex2];
            randomSet.add(randomValue1 + randomValue2);
        }
        
        // Print the random set of values
        System.out.println("Random Set: " + randomSet);
    }
}