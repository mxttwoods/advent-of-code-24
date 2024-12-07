import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day1 {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Integer>[] columns = loadInputFile();
        ArrayList<Integer> column1 = columns[0];
        ArrayList<Integer> column2 = columns[1];

        // Part 1: Sum of absolute differences
        int sumDistances = 0;
        for (int i = 0; i < column1.size(); i++) {
            sumDistances += Math.abs(column1.get(i) - column2.get(i));
        }
        System.out.println("Sum of distances: " + sumDistances);

        // Part 2: Similarity Score
        Integer similarityScore = 0;
        for (int i = 0; i < column1.size(); i++) {
            Integer value1 = column1.get(i);
            Integer occurCount = 0;
            for (int j = 0; j < column2.size(); j++) {
                if (column2.get(j).equals(value1)) {
                    occurCount++;
                }
            }
            similarityScore += occurCount * value1;
        }
        System.out.println("Similarity Score: " + similarityScore);
    }

    private static ArrayList<Integer>[] loadInputFile() throws FileNotFoundException {
        File file = new File("input.txt");
        ArrayList<Integer> column1 = new ArrayList<>();
        ArrayList<Integer> column2 = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] split = line.split("  ");
                column1.add(Integer.parseInt(split[0].strip()));
                column2.add(Integer.parseInt(split[1].strip()));
            }
        }
        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] result = new ArrayList[] { column1, column2 };
        return result;
    }
}