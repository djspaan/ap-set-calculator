class NaturalNumberFactory {
    static NaturalNumber createFromScanner(APScannerInterface in) throws APException {
        NaturalNumber number = new NaturalNumber();

        if (!in.nextCharIsDigit()) {
            throw new APException("Character is not a digit.");
        }

        if (in.nextCharIs('0')) {
            number.add(zero(in));
            return number;
        }

        while (in.nextCharIsDigit()) {
            number.add(in.nextChar());
        }

        return number;
    }

    private static char zero(APScannerInterface in) throws APException {
        in.removeWhitespaces();

        char result = in.nextChar();

        in.removeWhitespaces();

        if (in.nextCharIsDigit()) {
            throw new APException("The digit 0 cannot precede another digit.");
        }

        return result;
    }
}
