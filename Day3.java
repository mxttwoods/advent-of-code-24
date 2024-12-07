import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

class Day3 {
    public static void main(String[] args) throws FileNotFoundException {
        String inputString = loadInputFile();
        // System.out.println(inputString);
        Pattern pattern = Pattern.compile("(do\\(\\)|don't\\(\\)|mul\\((\\d+,\\d+)\\))");
        Matcher matcher = pattern.matcher(inputString);
        int sum = 0;
        boolean mulEnabled = true;

        while (matcher.find()) {
            String match = matcher.group(1);
            if (match.equals("do()")) {
                mulEnabled = true;
            } else if (match.equals("don't()")) {
                mulEnabled = false;
            } else if (mulEnabled) {
                String[] numbers = matcher.group(2).split(",");
                int num1 = Integer.parseInt(numbers[0]);
                int num2 = Integer.parseInt(numbers[1]);
                sum += num1 * num2;
            }
        }
        System.out.println("Sum of enabled multiplications: " + sum);
    }

    private static String loadInputFile() throws FileNotFoundException {
        File file = new File("day3-input.txt");
        String lines = "";
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine())
                lines += scanner.nextLine();

        }
        return lines;
    }
}