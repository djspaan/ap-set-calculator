import java.util.Scanner;
import java.util.regex.Pattern;

class APScanner implements APScannerInterface {
    private Scanner scanner;

    APScanner(String line) {
        scanner = new Scanner(line);
        scanner.useDelimiter("");
    }

    public boolean hasNext() {
        return scanner.hasNext();
    }

    public boolean nextCharIs(char c) {
        return scanner.hasNext(Pattern.quote(c + ""));
    }

    public boolean nextCharIsDigit() {
        return scanner.hasNext("[0-9]");
    }

    public boolean nextCharIsLetter() {
        return scanner.hasNext("[a-zA-Z]");
    }

    public char nextChar() {
        return scanner.next().charAt(0);
    }

    public void removeWhitespaces() {
        while (nextCharIs(' ')) {
            scanner.next();
        }
    }

}
