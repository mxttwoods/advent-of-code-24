import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Day2 {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<ArrayList<Integer>> table = loadInputFile();
        int safeCount = 0;
        for (int i = 0; i < table.size(); i++) {
            ArrayList<Integer> row = table.get(i);

            // First check if safe without dampening
            if (isSequenceSafe(row)) {
                safeCount++;
                continue;
            }

            // Try removing each number one at a time
            for (int skip = 0; skip < row.size(); skip++) {
                ArrayList<Integer> dampened = new ArrayList<>();
                for (int j = 0; j < row.size(); j++) {
                    if (j != skip) {
                        dampened.add(row.get(j));
                    }
                }

                if (isSequenceSafe(dampened)) {
                    safeCount++;
                    break;
                }
            }
        }
        System.out.println("Safe Count: " + safeCount);
    }

    private static boolean isSequenceSafe(ArrayList<Integer> sequence) {
        if (sequence.size() < 2)
            return true;
        boolean isDecreasing = sequence.get(0) > sequence.get(1);

        for (int i = 1; i < sequence.size(); i++) {
            int diff = Math.abs(sequence.get(i) - sequence.get(i - 1));

            if (diff < 1 || diff > 3)
                return false;

            if (isDecreasing && sequence.get(i - 1) <= sequence.get(i))
                return false;
            if (!isDecreasing && sequence.get(i - 1) >= sequence.get(i))
                return false;
        }
        return true;
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