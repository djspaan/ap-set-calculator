/**
 * @elements Scanner
 * @structure linear
 * @domain all content being read by Scanner
 */
public interface APScannerInterface {
    
    /**
     * @pre -
     * @post existence of a new to read character is returned
     */
    boolean hasNext();

    /**
     * @pre -
     * @post equality of next character to the input is returned
     */
    boolean nextCharIs(char c);

    /**
     * @pre -
     * @post if next character is a digit is returned
     */
    boolean nextCharIsDigit();

    /**
     * @pre -
     * @post if next char is a letter is returned
     */
    boolean nextCharIsLetter();

    /**
     * @pre -
     * @post next character is returned
     */
    char nextChar();

    /**
     * @pre -
     * @post whitespaces are skipped by scanner until next non whitespace
     */
    void removeWhitespaces();
}
