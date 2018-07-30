import org.junit.Test;

import static org.junit.Assert.*;

public class SetTest {

    @Test
    public void testGetSize() {
        Set<Integer> set = new Set<>();
        assertEquals("A new add should have a size of 0", 0, set.getSize());

        set.addElement(1);
        assertEquals("A add with 1 element should have a size of 1", 1, set.getSize());

        set.addElement(2);
        assertEquals("A add with 2 elements should have a size of 2", 2, set.getSize());

        for (int i = 2; i <= 100; i++) {
            set.addElement(i);
        }
        assertEquals("A add with 100 elements should have a size of 100", 100, set.getSize());
    }

    @Test
    public void testAddElement() {
        Set<Integer> set = new Set<>();
        set.addElement(1);
        assertTrue("A add with 1 in it should contain 1", set.getFirstElement() == 1);
    }

    @Test
    public void testRemoveElement() {
        Set<Integer> set = new Set<>();
        set.addElement(1);
        set.removeElement(1);
        assertEquals("After removing the only element the add should have a size of 0", 0, set.getSize());

        set.addElement(1);
        set.addElement(2);
        set.removeElement(1);
        assertTrue("After removing the an element the add should still contain the other element", set.getFirstElement() == 2);
    }

    @Test
    public void testUnion() {
        Set<Integer> set1 = new Set<>();
        set1.addElement(1);
        Set<Integer> set2 = new Set<>();
        set2.addElement(2);
        Set result = set1.union(set2);
        assertEquals("After a union the new add should contain all the elements of the 2 sets", 2, result.getSize());


        Set<Integer> set3 = new Set<>();
        set3.addElement(1);
        set3.addElement(2);
        set3.addElement(3);
        set3.addElement(4);
        Set<Integer> set4 = new Set<>();
        set4.addElement(4);
        set4.addElement(5);
        set4.addElement(6);
        set4.addElement(7);
        Set result2 = set3.union(set4);
        assertEquals("After a union the new add should contain all the elements of the 2 sets", 7, result2.getSize());


        Set<Integer> set5 = new Set<>();
        Set<Integer> set6 = new Set<>();
        Set result3 = set5.union(set6);
        assertEquals("The union of two empty sets should be an empty add", 0, result3.getSize());
    }

    @Test
    public void testIntersection() {
        Set<Integer> set1 = new Set<>();
        set1.addElement(1);
        set1.addElement(2);
        Set<Integer> set2 = new Set<>();
        set2.addElement(2);
        set2.addElement(3);
        Set result = set1.intersection(set2);
        assertEquals("After an intersection the new add should contain the elements that only exist in both sets", 1, result.getSize());

        Set<Integer> set3 = new Set<>();
        Set<Integer> set4 = new Set<>();
        Set result2 = set3.intersection(set4);
        assertEquals("An intersection of two empty sets should be an empty add", 0, result2.getSize());

        Set<Integer> set5 = new Set<>();
        set5.addElement(1);
        set5.addElement(2);
        Set<Integer> set6 = new Set<>();
        Set result3 = set5.intersection(set6);
        assertEquals("An intersection of one filled and one empty add should be an empty add", 0, result3.getSize());

        Set<Integer> set7 = new Set<>();
        set7.addElement(1);
        set7.addElement(2);
        Set<Integer> set8 = new Set<>();
        set8.addElement(1);
        set8.addElement(2);
        Set result4 = set7.intersection(set8);
        assertEquals("An intersection of two equal sets should be equal to either sets", 2, result4.getSize());
    }

    @Test
    public void testComplement() {
        Set<Integer> set1 = new Set<>();
        set1.addElement(1);
        set1.addElement(2);
        Set<Integer> set2 = new Set<>();
        set2.addElement(2);
        set2.addElement(3);
        Set result = set1.complement(set2);
        assertEquals("The complement of the new add should contain the only the elements of the first add", 1, result.getFirstElement());

        Set<Integer> set3 = new Set<>();
        Set<Integer> set4 = new Set<>();
        Set result2 = set3.complement(set4);
        assertEquals("The complement of two empty sets should be an empty add", 0, result2.getSize());

        Set<Integer> set5 = new Set<>();
        set5.addElement(1);
        set5.addElement(2);
        Set<Integer> set6 = new Set<>();
        Set result3 = set5.complement(set6);
        assertEquals("The complement of one filled and one empty add should be equal to the first add", 2, result3.getSize());

        Set<Integer> set7 = new Set<>();
        set7.addElement(1);
        set7.addElement(2);
        Set<Integer> set8 = new Set<>();
        set8.addElement(1);
        set8.addElement(2);
        Set result4 = set7.complement(set8);
        assertEquals("The complement of two equal sets should be an empty add", 0, result4.getSize());
    }

    @Test
    public void testSymmetricDifference() {
        Set<Integer> set1 = new Set<>();
        set1.addElement(1);
        set1.addElement(2);
        Set<Integer> set2 = new Set<>();
        set2.addElement(2);
        set2.addElement(3);
        Set result = set1.symmetricDifference(set2);
        assertEquals("The symmetric difference of the new add should contain the only the elements that are not in both sets", 2, result.getSize());

        Set<Integer> set3 = new Set<>();
        Set<Integer> set4 = new Set<>();
        Set result2 = set3.symmetricDifference(set4);
        assertEquals("The symmetric difference of two empty sets should be an empty add", 0, result2.getSize());

        Set<Integer> set5 = new Set<>();
        set5.addElement(1);
        set5.addElement(2);
        Set<Integer> set6 = new Set<>();
        Set result3 = set5.symmetricDifference(set6);
        assertEquals("The symmetric difference of one filled and one empty add should be equal to the first add", 2, result3.getSize());

        Set<Integer> set7 = new Set<>();
        set7.addElement(1);
        set7.addElement(2);
        Set<Integer> set8 = new Set<>();
        set8.addElement(1);
        set8.addElement(2);
        Set result4 = set7.symmetricDifference(set8);
        assertEquals("The symmetric difference of two equal sets should be an empty add", 0, result4.getSize());
    }

    @Test
    public void testGetFirstElement() {
        Set<Integer> set1 = new Set<>();
        set1.addElement(1);
        assertTrue("The first element of a add containing only 1 should be 1", set1.getFirstElement() == 1);

        Set<Integer> set2 = new Set<>();
        set2.addElement(1);
        set2.addElement(2);
        assertTrue("The first element of a add containing 1 and 2 should be 2", set2.getFirstElement() == 1);

        Set<Integer> set3 = new Set<>();
        set3.addElement(1);
        set3.addElement(2);
        set3.addElement(3);
        assertTrue("The first element of a add containing 1 and 2 should be 2", set3.getFirstElement() == 1);
    }

    @Test
    public void testCopy() {
        Set<Integer> set1 = new Set<>();
        Set copy1 = set1.copy();
        assertEquals("The copy of an empty add should be an empty add", 0, copy1.getSize());

        Set<Integer> set2 = new Set<>();
        set2.addElement(1);
        Set copy2 = set2.copy();
        assertEquals("The copy of a add with one element should have one element", 1, copy2.getSize());

        Set<Integer> set3 = new Set<>();
        set3.addElement(1);
        set3.addElement(2);
        set3.addElement(3);
        Set copy3 = set3.copy();
        assertEquals("The copy of a add with three elements should have three elements", 3, copy3.getSize());

        Set<Integer> set4 = new Set<>();
        set4.addElement(1);
        Set copy4 = set4.copy();
        assertEquals("The copy of a add with a 1 should contain a 1", 1, copy4.getFirstElement());
    }
}