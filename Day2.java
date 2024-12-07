import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Day2 {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<ArrayList<Integer>> table = loadInputFile();
        int safeCount = 0;
        for (int i = 0; i < table.size(); i++) {
            boolean safe = true;
            ArrayList<Integer> row = table.get(i);

            // Check if decreasing
            boolean isDecreasing = row.get(0) > row.get(1);

            for (int j = 1; j < row.size(); j++) {
                int diff = Math.abs(row.get(j) - row.get(j - 1));

                // Check if difference is between 1 and 3
                if (diff < 1 || diff > 3) {
                    safe = false;
                    break;
                }

                // Check if consistently increasing or decreasing
                if (isDecreasing && row.get(j - 1) <= row.get(j)) {
                    safe = false;
                    break;
                }
                if (!isDecreasing && row.get(j - 1) >= row.get(j)) {
                    safe = false;
                    break;
                }
            }

            safeCount += safe ? 1 : 0;
        }
        System.out.println("Safe Count: " + safeCount);
    }

    private static ArrayList<ArrayList<Integer>> loadInputFile() throws FileNotFoundException {
        File file = new File("day2-input.txt");
        ArrayList<ArrayList<Integer>> table = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] split = line.split(" ");
                ArrayList<Integer> row = new ArrayList<>();
                for (int i = 0; i < split.length; i++) {
                    row.add(Integer.parseInt(split[i]));
                }
                table.add(row);
            }
        }
        return table;
    }
}