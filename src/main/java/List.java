public class List<E extends Comparable> implements ListInterface<E> {

    class Node {
        E data;
        Node prior;
        Node next;

        Node(E data) {
            this(data, null, null);
        }

        Node(E data, Node prior, Node next) {
            this.data = data;
            this.prior = prior;
            this.next = next;
        }
    }

    private Node current;

    List() {
        init();
    }

    public boolean isEmpty() {
        return current == null;
    }

    public List<E> init() {
        current = null;
        return this;
    }

    public int size() {
        if (isEmpty()) {
            return 0;
        }

        goToFirst();

        int count = 0;
        do {
            count++;
        } while (goToNext());

        return count;
    }

    public List<E> insert(E d) {
        if (isEmpty()) {
            current = new Node(d);
            return this;
        }

        goToFirst();

        do {
            // The current element equals or is greater than the given element, so add it prior to the current element
            if (current.data.compareTo(d) >= 0) {
                current = new Node(d, current.prior, current);
                current.next.prior = current;
                if (!isFirst()) {
                    // Only add the previous element if this is not the first element
                    current.prior.next = current;
                }
                return this;
            }
        } while (goToNext());

        // The given element is larger than the current element, so add it to the end of the list.
        current = new Node(d, current, null);
        current.prior.next = current;
        return this;
    }

    public E retrieve() {
        return current.data;
    }

    public List<E> remove() {
        if (isFirst() && isLast()) {
            current = null;
            return this;
        } else if (isFirst()) {
            current.next.prior = null;
            current = current.next;
            return this;
        } else if (isLast()) {
            current.prior.next = null;
            current = current.prior;
            return this;
        } else {
            current.next.prior = current.prior;
            current.prior.next = current.next;
            current = current.next;
            return this;
        }
    }

    public boolean find(E d) {
        if (isEmpty()) {
            return false;
        }

        goToFirst();

        do {
            // If the data is the same we found the element!
            if (current.data.compareTo(d) == 0) {
                return true;
                // If the current data is larger than the data we're searching for, the element is not in the list and we go to the previous element
            } else if (current.data.compareTo(d) > 0) {
                goToPrevious();
                return false;
            }
        } while (goToNext());

        return false;
    }

    public boolean goToFirst() {
        if (isEmpty()) {
            return false;
        }

        do {
            if (isFirst()) {
                return true;
            }
        } while (goToPrevious());

        return false;
    }

    public boolean goToLast() {
        if (isEmpty()) {
            return false;
        }

        do {
            if (isLast()) {
                return true;
            }
        } while (goToNext());

        return false;
    }

    public boolean goToNext() {
        if (!isEmpty() && !isLast()) {
            current = current.next;
            return true;
        }
        return false;
    }

    public boolean goToPrevious() {
        if (!isEmpty() && !isFirst()) {
            current = current.prior;
            return true;
        }
        return false;
    }

    public List<E> copy() {
        List<E> res = new List<E>();

        if (!isEmpty()) {
            goToFirst();

            do {
                res.insert(current.data);
            } while (goToNext());
        }
        return res;
    }

    private boolean isFirst() {
        return current.prior == null;
    }

    private boolean isLast() {
        return current.next == null;
    }
}
