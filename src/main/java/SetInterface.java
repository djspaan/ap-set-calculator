/**
 * @elements Object of type E
 * @structure linear
 * @domain all sets from list
 */
public interface SetInterface<E extends Comparable> {

	/**
     * @pre -
     * @post element is added to list if it does not allready exist in list
     */
	SetInterface<E> addElement(E e);

    /**
     * @pre -
     * @post if element is found, it wil be removed from the list
     */
	SetInterface<E> removeElement(E e);

	/**
	 * Returns the union of this add and the given add
	 **/

    /**
     * @pre -
     * @post the union of input is returned
     */
	SetInterface<E> union(Set<E> s);

	/**
	 * Returns the intersection of this add and the given add
	 **/

    /**
     * @pre -
     * @post the intersection of input is returned
     */
	SetInterface<E> intersection(Set<E> s);

    /**
     * @pre -
     * @post the complement of the input is returned
     */
	SetInterface<E> complement(Set<E> s);

    /**
     * @pre -
     * @post the symetric difference of the input is returned
     */
	SetInterface<E> symmetricDifference(Set<E> s);

    /**
     * @pre -
     * @post size of the list is returned
     */
	int getSize();

    /**
     * @pre -
     * @post first element of the list is returned
     */
	E getFirstElement();

    /**
     * @pre -
     * @post string representation of set is returned
     */
	String toString();
}