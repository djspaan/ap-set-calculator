/**
 * @elements one natural number
 * @structure linear
 * @domain all possible natural numbers
 */
public interface NaturalNumberInterface {

    /**
     * @pre char may not be empty
     * @post character has been added to string
     */
    void add(char s);

    /**
     * @pre -
     * @post length of input is returned
     */
    int getLength();

    /**
     * @pre -
     * @post string representation of input is returned
     */
    String toString();
}