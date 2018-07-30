public class Set<E extends Comparable> implements SetInterface<E> {

    private List<E> list;

    Set() {
        list = new List<>();
    }

    private List getList() {
        return list;
    }

    private void setList(List<E> newList) {
        list = newList;
    }

    public Set<E> addElement(E e) {
        if (list.find(e)) {
            return this;
        }
        list.insert(e);
        return this;
    }

    public Set<E> removeElement(E e) {
        if (list.find(e)) {
            list.remove();
        }
        return this;
    }

    public Set<E> union(Set<E> s) {
        Set<E> res = new Set<E>();

        if (getSize() > 0) {
            list.goToFirst();
            do {
                // If the current element is not in the result add, add it to the result
                if (res.getList().isEmpty() || !res.getList().find(list.retrieve())) {
                    res.addElement(list.retrieve());
                }
            } while (list.goToNext());
        }

        if (s.getSize() > 0) {
            s.getList().goToFirst();
            do {
                // If the current element is not in the result add, add it to the result
                if (res.getList().isEmpty() || !res.getList().find(s.getList().retrieve())) {
                    res.addElement(s.list.retrieve());
                }
            } while (s.list.goToNext());
        }

        return res;
    }

    public Set<E> intersection(Set<E> s) {
        Set<E> res = new Set<E>();

        if (getSize() == 0 || s.getSize() == 0) {
            return res;
        }

        list.goToFirst();
        do {
            // If the current element is in add s, add it to the result
            if (s.getList().find(list.retrieve())) {
                res.addElement(list.retrieve());
            }
        } while (list.goToNext());

        return res;
    }

    public Set<E> complement(Set<E> s) {
        Set<E> res = new Set<E>();

        if (getSize() > 0) {
            list.goToFirst();
            do {
                // If the current element is not in add s, add it to the result
                if (!s.getList().find(list.retrieve())) {
                    res.addElement(list.retrieve());
                }
            } while (list.goToNext());
        }

        return res;
    }

    public Set<E> symmetricDifference(Set<E> s) {
        Set<E> unionSet = union(s);
        Set<E> intersectionSet = intersection(s);
        return unionSet.complement(intersectionSet);
    }

    public int getSize() {
        return list.size();
    }

    public E getFirstElement() {
        list.goToFirst();
        return list.retrieve();
    }

    Set<E> copy() {
        Set<E> copy = new Set<E>();
        copy.setList(list.copy());
        return copy;
    }

    public String toString() {
        Set copy = copy();
        StringBuilder result = new StringBuilder();
        while (copy.getSize() > 0) {
            E number = (E) copy.getFirstElement();
            copy.removeElement(number);
            result.append(number.toString());
           result.append(" ");
        }
        return result.toString();
    }
}