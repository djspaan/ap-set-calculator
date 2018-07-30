/**
 * @elements Set of Tokens
 * @structure linear
 * @domain All posible sets of tokens
 */
public interface SetCalculatorInterface {
    
    /**
     * @pre set may not be empty
     * @pre statement must be either an assignment, print statement or a comment
     * @post the input statement has been read
     */
    void statement(APScannerInterface in) throws APException;
}
