import java.util.HashMap;

class SetCalculator implements SetCalculatorInterface {
    private HashMap<String, Set> map;

    SetCalculator() {
        map = new HashMap<>();
    }

    @Override
    public void statement(APScannerInterface in) throws APException {
        in.removeWhitespaces();

        if (!in.hasNext()) {
            throw new APException("Statement is empty.");
        } else if (in.nextCharIsLetter()) {
            assignment(in);
        } else if (in.nextCharIs('?')) {
            printStatement(in);
        } else if (in.nextCharIs('/')) {
            return;
        } else {
            throw new APException("Statement must be either an assignment, print statement or a comment. None of them detected.");
        }
    }

    private void assignment(APScannerInterface in) throws APException {
        in.removeWhitespaces();

        Identifier identifier = identifier(in);

        in.removeWhitespaces();

        character(in, '=');

        Set set = expression(in);

        eoln(in);

        map.put(identifier.toString(), set);
    }

    private void printStatement(APScannerInterface in) throws APException {
        character(in, '?');

        in.removeWhitespaces();

        Set set = expression(in);

        in.removeWhitespaces();

        eoln(in);

        printSet(set);

        System.out.println();
    }

    private Set expression(APScannerInterface in) throws APException {
        in.removeWhitespaces();

        if (!in.hasNext()) {
            throw new APException("expression is empty");
        }

        Set set = term(in);

        while (nextCharIsAdditiveOperator(in)) {
            if (in.nextCharIs('+')) {
                character(in, '+');
                in.removeWhitespaces();
                set = set.union(term(in));
            } else if (in.nextCharIs('-')) {
                character(in, '-');
                in.removeWhitespaces();
                set = set.complement(term(in));
            } else if (in.nextCharIs('|')) {
                character(in, '|');
                in.removeWhitespaces();
                set = set.symmetricDifference(term(in));
            }
            in.removeWhitespaces();
        }

        return set;
    }

    private boolean nextCharIsAdditiveOperator(APScannerInterface in) {
        in.removeWhitespaces();

        if (in.nextCharIs('+')) {
            return true;
        }

        if (in.nextCharIs('-')) {
            return true;
        }

        return in.nextCharIs('|');
    }

    private void eoln(APScannerInterface in) throws APException {
        if (in.hasNext()) {
            throw new APException("End-of-line expected.");
        }
    }

    private void character(APScannerInterface in, char c) throws APException {
        in.removeWhitespaces();

        if (!in.nextCharIs(c)) {
            throw new APException("Unexpected character.");
        }

        in.nextChar();
    }

    private Identifier identifier(APScannerInterface in) throws APException {
        Identifier identifier = new Identifier();

        identifier.addChar(letter(in));

        assertWhitespaceEndsWith(in, '=');

        while (in.nextCharIsLetter() || in.nextCharIsDigit()) {
            identifier.addChar(in.nextChar());
        }

        return identifier;
    }

    private void assertWhitespaceEndsWith(APScannerInterface in, char c) throws APException {
        while (in.nextCharIs(' ')) {
            in.removeWhitespaces();
            if (!in.nextCharIs('=')) {
                throw new APException("Unexpected character.");
            }
        }
    }

    private char letter(APScannerInterface in) throws APException {
        if (!in.nextCharIsLetter()) {
            throw new APException("Expected character to be a letter.");
        }

        return in.nextChar();
    }

    private Set term(APScannerInterface in) throws APException {
        in.removeWhitespaces();

        if (!in.hasNext()) {
            throw new APException("Term is empty.");
        }

        Set set = factor(in);

        while (multiplicativeOperator(in)) {
            character(in, '*');
            in.removeWhitespaces();
            set = set.intersection(factor(in));
        }

        return set;
    }

    private Set factor(APScannerInterface in) throws APException {
        in.removeWhitespaces();

        if (!in.hasNext()) {
            throw new APException("Factor is empty.");
        }

        if (in.nextCharIsLetter()) {
            Identifier identifier = identifier(in);

            Set set = map.get(identifier.toString());

            if (set == null) {
                throw new APException("Identifier not found. Set could not be retrieved.");
            }

            return set;
        }

        if (in.nextCharIs('(')) {
            return complexFactor(in);
        }

        if (in.nextCharIs('{')) {
            return set(in);
        }

        throw new APException("Factor must be either an Identifier, complex factor or a add. None of them detected.");
    }

    private boolean multiplicativeOperator(APScannerInterface in) {
        in.removeWhitespaces();

        return in.nextCharIs('*');
    }

    private Set complexFactor(APScannerInterface in) throws APException {
        in.removeWhitespaces();

        character(in, '(');

        Set set = expression(in);

        character(in, ')');

        return set;
    }

    private Set set(APScannerInterface in) throws APException {
        in.removeWhitespaces();

        character(in, '{');

        Set set = createSetWithNaturalNumbers(in);

        in.removeWhitespaces();

        character(in, '}');

        return set;
    }

    private Set createSetWithNaturalNumbers(APScannerInterface in) throws APException {
        in.removeWhitespaces();

        Set<NaturalNumber> set = new Set<>();

        if (!in.nextCharIsDigit()) {
            return set;
        }

        set.addElement(naturalNumber(in));

        in.removeWhitespaces();

        while (in.nextCharIs(',')) {

            character(in, ',');

            in.removeWhitespaces();

            if (!in.nextCharIsDigit()) {
                throw new APException("Expected digit.");
            }

            NaturalNumber nn = naturalNumber(in);

            set.addElement(nn);
        }

        return set;
    }

    private void printSet(Set set) {
        System.out.print(set.toString());
        System.out.print(" ");
    }

    private NaturalNumber naturalNumber(APScannerInterface in) throws APException {
        return NaturalNumberFactory.createFromScanner(in);
    }
}
