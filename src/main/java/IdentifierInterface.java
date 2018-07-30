/**
 * @elements character
 * @structure linear
 * @domain All posible sets of tokens
 */
public interface IdentifierInterface {

    /**
     * @pre character may not be empty
     * @post object is returned
     */
	IdentifierInterface addChar(char c);

    /**
     * @pre -
     * @post equality between input and identifier string is returned
     */
	boolean equals(IdentifierInterface i);
}