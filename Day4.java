import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Day4 {
    public static void main(String[] args) throws FileNotFoundException {
        String inputString = loadInputFile();
        String[] lines = inputString.split("\n");
        int size = lines.length;

        char[][] grid = new char[size][size];
        for (int i = 0; i < size; i++) {
            grid[i] = lines[i].toCharArray();
        }

        int count = 0;
        // Check each position as a potential 'A' center
        for (int i = 1; i < size - 1; i++) {
            for (int j = 1; j < size - 1; j++) {
                if (grid[i][j] != 'A')
                    continue;

                // Check all possible combinations of diagonal patterns
                if (hasValidXPattern(grid, i, j)) {
                    count++;
                }
            }
        }
        System.out.println("Found X-MAS patterns: " + count);
    }

    private static boolean hasValidXPattern(char[][] grid, int i, int j) {
        // Check diagonal \ pattern
        boolean diagDown = checkDiagonalDown(grid, i, j);

        // Check diagonal / pattern
        boolean diagUp = checkDiagonalUp(grid, i, j);

        return diagDown || diagUp;
    }

    private static boolean checkDiagonalDown(char[][] grid, int i, int j) {
        // Check for MAS or SAM pattern in diagonal \ direction
        boolean masPattern = (grid[i - 1][j - 1] == 'M' && grid[i + 1][j + 1] == 'S') ||
                (grid[i - 1][j - 1] == 'S' && grid[i + 1][j + 1] == 'M');

        // Check for MAS or SAM pattern in diagonal / direction
        boolean masPattern2 = (grid[i - 1][j + 1] == 'M' && grid[i + 1][j - 1] == 'S') ||
                (grid[i - 1][j + 1] == 'S' && grid[i + 1][j - 1] == 'M');

        return masPattern && masPattern2;
    }

    private static boolean checkDiagonalUp(char[][] grid, int i, int j) {
        // Check for MAS pattern in one direction and SAM in other
        boolean pattern1 = (grid[i - 1][j - 1] == 'M' && grid[i + 1][j + 1] == 'S' &&
                grid[i - 1][j + 1] == 'S' && grid[i + 1][j - 1] == 'M');

        boolean pattern2 = (grid[i - 1][j - 1] == 'S' && grid[i + 1][j + 1] == 'M' &&
                grid[i - 1][j + 1] == 'M' && grid[i + 1][j - 1] == 'S');

        return pattern1 || pattern2;
    }

    private static String loadInputFile() throws FileNotFoundException {
        File file = new File("day4-input.txt");
        StringBuilder lines = new StringBuilder();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                lines.append(scanner.nextLine()).append("\n");
            }
        }
        return lines.toString();
    }
}