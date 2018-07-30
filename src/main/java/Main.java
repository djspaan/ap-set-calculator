import java.util.Scanner;

public class Main {
    private void run() {
        Scanner scanner = new Scanner(System.in);
        SetCalculator calculator = new SetCalculator();

        while (scanner.hasNextLine()) {
            APScanner lineScanner = new APScanner(scanner.nextLine());

            try {
                calculator.statement(lineScanner);
            } catch (APException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] argv) {
        new Main().run();
    }
}