import org.junit.Test;
import java.util.ArrayList;
import java.time.Duration;
import static org.junit.Assert.*;

/**
 * @author Gurpreet singh , 000938787
 */
public class SetLibraryTest {

    @Test
    public void testCreateSetValidUnique() {
        // Test for generating a valid set with unique elements
        ArrayList<Integer> result = SetLibrary.createSet(5, 1, 10, true);
        assertEquals(5, result.size());
        assertTrue(SetLibrary.isSetUnique(result));  // Check that all elements are unique
    }

    @Test
    public void testCreateSetValidNonUnique() {
        // Test for generating a set without unique element constraint
        ArrayList<Integer> result = SetLibrary.createSet(5, 1, 3, false);
        assertEquals(5, result.size());  // Ensure correct size
        assertFalse(SetLibrary.isSetUnique(result));  // Non-unique values are expected
    }

    @Test
    public void testCreateSetNegativeSize() {
        // Test if method throws exception on negative size
        try {
            SetLibrary.createSet(-1, 1, 10, true);
            fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Size not a positive value!", e.getMessage());
        }
    }

    @Test
    public void testCreateSetInvalidRange() {
        // Test if method throws exception when minimum is greater than maximum
        try {
            SetLibrary.createSet(5, 10, 1, true);
            fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Maximum value is smaller than minimum value!", e.getMessage());
        }
    }

    @Test
    public void testCreateSetInvalidUniqueRange() {
        // Test if method throws exception when range is smaller than required size for unique elements
        try {
            SetLibrary.createSet(5, 1, 3, true);
            fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Range not large enough for a unique set!", e.getMessage());
        }
    }

    @Test
    public void testCreateSetZeroSize() {
        // Test for size 0, which should return an empty set
        ArrayList<Integer> result = SetLibrary.createSet(0, 1, 10, true);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testCreateSetInfiniteLoop() {
        // Test if method does not handle impossible ranges leading to infinite loop
        try {
            SetLibrary.createSet(10, 1, 2, true);
            fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Range not large enough for a unique set!", e.getMessage());
        }
    }

    @Test
    public void testCreateSetNonUniqueInUniqueMode() {
        // Test that uniqueElements = true doesn't produce duplicate values
        ArrayList<Integer> result = SetLibrary.createSet(3, 1, 5, true);
        assertTrue(SetLibrary.isSetUnique(result));
    }

    @Test
    public void testUniqueSet() {
        // Test for a set with all unique elements
        ArrayList<Integer> uniqueSet = new ArrayList<>();
        uniqueSet.add(1);
        uniqueSet.add(2);
        uniqueSet.add(3);
        uniqueSet.add(4);
        uniqueSet.add(5);
        assertTrue(SetLibrary.isSetUnique(uniqueSet));
    }

    @Test
    public void testNonUniqueSet() {
        // Test for a set with duplicate elements
        ArrayList<Integer> nonUniqueSet = new ArrayList<>();
        nonUniqueSet.add(1);
        nonUniqueSet.add(2);
        nonUniqueSet.add(2);  // Duplicate
        nonUniqueSet.add(3);
        assertFalse(SetLibrary.isSetUnique(nonUniqueSet));
    }

    @Test
    public void testSingleElementSet() {
        // Test for a single element set (always unique)
        ArrayList<Integer> singleElementSet = new ArrayList<>();
        singleElementSet.add(1);
        assertTrue(SetLibrary.isSetUnique(singleElementSet));
    }

    @Test
    public void testLargeUniqueSet() {
        // Test for a large unique set
        ArrayList<Integer> largeUniqueSet = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            largeUniqueSet.add(i);
        }
        assertTrue(SetLibrary.isSetUnique(largeUniqueSet));
    }

    @Test
    public void testLargeNonUniqueSet() {
        // Test for a large non-unique set
        ArrayList<Integer> largeNonUniqueSet = new ArrayList<>();
        for (int i = 0; i < 999; i++) {
            largeNonUniqueSet.add(i);
        }
        largeNonUniqueSet.add(500);  // Duplicate
        assertFalse(SetLibrary.isSetUnique(largeNonUniqueSet));
    }

    @Test
    public void testValidIntersection() {
        // Test the intersection of two sets
        ArrayList<Integer> setA = new ArrayList<>();
        setA.add(1);
        setA.add(2);
        setA.add(3);
        ArrayList<Integer> setB = new ArrayList<>();
        setB.add(2);
        setB.add(3);
        setB.add(4);
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(2);
        expected.add(3);
        assertEquals(expected, SetLibrary.createIntersection(setA, setB));
    }

    @Test
    public void testEmptySetA() {
        // Test intersection with an empty set A
        ArrayList<Integer> setA = new ArrayList<>();
        ArrayList<Integer> setB = new ArrayList<>();
        setB.add(1);
        try {
            SetLibrary.createIntersection(setA, setB);
            fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Arraylist arguments cannot be null or empty", e.getMessage());
        }
    }

    @Test
    public void testEmptySetB() {
        // Test intersection with an empty set B
        ArrayList<Integer> setA = new ArrayList<>();
        setA.add(1);
        ArrayList<Integer> setB = new ArrayList<>();
        try {
            SetLibrary.createIntersection(setA, setB);
            fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Arraylist arguments cannot be null or empty", e.getMessage());
        }
    }

    @Test
    public void testNullSetA() {
        // Test intersection with null set A
        ArrayList<Integer> setB = new ArrayList<>();
        setB.add(1);
        try {
            SetLibrary.createIntersection(null, setB);
            fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Arraylist arguments cannot be null or empty", e.getMessage());
        }
    }

    @Test
    public void testNullSetB() {
        // Test intersection with null set B
        ArrayList<Integer> setA = new ArrayList<>();
        setA.add(1);
        try {
            SetLibrary.createIntersection(setA, null);
            fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Arraylist arguments cannot be null or empty", e.getMessage());
        }
    }

    @Test
    public void testIntersectionWithDuplicateValues() {
        // Test intersection with sets having duplicate values
        ArrayList<Integer> setA = new ArrayList<>();
        setA.add(1);
        setA.add(1);  // Duplicate
        setA.add(2);
        ArrayList<Integer> setB = new ArrayList<>();
        setB.add(1);
        setB.add(1);  // Duplicate
        setB.add(3);
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(1);
        assertEquals(expected, SetLibrary.createIntersection(setA, setB));
    }

    @Test
    public void testLargeSets() {
        // Test performance for large sets
        ArrayList<Integer> setA = new ArrayList<>();
        ArrayList<Integer> setB = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            setA.add(i);
            if (i % 2 == 0) {
                setB.add(i);  // Even numbers in set B
            }
        }
        ArrayList<Integer> expected = new ArrayList<>();
        for (int i = 0; i < 10000; i += 2) {
            expected.add(i);
        }
    }}