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
        Pattern pattern = Pattern.compile("mul\\((\\d+,\\d+)\\)");
        Matcher matcher = pattern.matcher(inputString);

        ArrayList<Integer> ns = new ArrayList<>();
        while (matcher.find()) {
            String[] numbers = matcher.group(1).split(",");
            int num1 = Integer.parseInt(numbers[0]);
            int num2 = Integer.parseInt(numbers[1]);
            System.out.println(num1 + " " + num2);
            ns.add(num1 * num2);
        }
        int sum = 0;
        for (int i = 0; i < ns.size(); i++) {
            sum += ns.get(i);
        }
        System.out.println(sum);
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