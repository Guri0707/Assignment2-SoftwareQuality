import java.util.ArrayList;

/**
 * A number of methods that will assist with the processing of integer data
 * sets. ArrayLists are used to hold the contents of the sets
 *
 * @author mark.yendt and karen.laurin
 *
 */
public final class SetLibrary {

    /**
     * Method will generate a set of integers between the minimum and maximum of
     * the requested size. If the uniqueElements is true the set will consist of
     * unique integer values
     *
     * @param size the number of elements for the array
     * @param minimum the lower value of the range of integers to generate
     * @param maximum the upper value of the range of integers to generate
     * @param uniqueElements flag for unique values
     * @return the newly created set with the given parameters
     */
    public static ArrayList<Integer> createSet(int size, int minimum, int maximum, boolean uniqueElements) {
        boolean filled = false;
        int newListSize = 0;
        ArrayList<Integer> arraySet = null;

        if(size < 0){
            throw new IllegalArgumentException("Size not a positive value!");
        }
        else if(minimum > maximum){
            throw new IllegalArgumentException("Maximum value is smaller than minimum value!");
        }
        else if(uniqueElements && maximum-minimum < size){
            throw new IllegalArgumentException("Range not large enough for a unique set!");
        }
        else {
            arraySet = new ArrayList<Integer>();
            boolean isUnique = false;
            while (!filled) {
                int randomInteger = (int) (Math.random() * (maximum - minimum)) + minimum;

                isUnique = true;
                for (int j = 0; j < newListSize && uniqueElements; j++) {
                    isUnique = randomInteger != arraySet.get(j);
                }

                if (isUnique || !uniqueElements) {
                    arraySet.add(randomInteger);
                    newListSize++;
                }

                filled = (newListSize == size);
            }
        }
        return arraySet;
    }

    /**
     * Method will determine if all elements in the input set are all unique
     *
     * @param arraySet the input array to check
     * @return true if there are no duplicates, false if duplicates exists
     * @throws IllegalArgumentException when one of setA or setB is null or
     * empty
     */

    public static boolean isSetUnique(ArrayList<Integer> arraySet) {
        if(arraySet == null || arraySet.size() == 0)
            throw new IllegalArgumentException("Set cannot be null or empty!");

        boolean isUniqueflag = true;

        for (int i = 0; i < arraySet.size() && isUniqueflag; i++) {
            for (int j = i + 1; j < arraySet.size() && isUniqueflag; j++) {
                isUniqueflag = arraySet.get(i) != arraySet.get(j);
            }
        }
        return isUniqueflag;
    }

    /**
     * Determines the common elements (intersection) between setA and setB
     * Does not allow duplicates in the intersection
     *
     * @param setA The first of the two Sets
     * @param setB The second of the two sets
     * @return a new set that consists of the common elements between A and B
     * @throws IllegalArgumentException when one of setA or setB is null or
     * empty
     */
    public static ArrayList<Integer> createIntersection(ArrayList<Integer> setA, ArrayList<Integer> setB)
            throws IllegalArgumentException {
        ArrayList<Integer> setCommon = new ArrayList<Integer>();

        if (setA == null || setB == null || setA.size() == 0 ) {
            throw new IllegalArgumentException("Arraylist arguments cannot be null or empty");
        }

        for (int i = 0; i < setA.size(); i++) {
            for (int j = 0; j < setB.size(); j++) {
                if (setA.get(i) == setB.get(j)) {
                    boolean canAddToCommon = true;
                    for (int k = 0; k < setCommon.size(); k++) {
                        if (setA.get(i) == setCommon.get(k)) {
                            canAddToCommon = false;
                            break;
                        }
                    }
                    if (canAddToCommon) {
                        setCommon.add(setA.get(i));
                    }
                }
            }
        }

        return setCommon;
    }

    /**
     * Determines if setB is a subSet of setA
     *
     * @param setA the full set for the comparison
     * @param setB the set to be tested as the sub set
     * @return true if setB is a subSet of setA
     * @throws IllegalArgumentException when one of setA or setB is null or
     * empty
     */
    public static boolean isSubSet(ArrayList<Integer> setA, ArrayList<Integer> setB) {

        if (setA == null || setB == null || setA.size() == 0 || setB.size() == 0) {
            throw new IllegalArgumentException("Arraylist arguments cannot be null or empty");
        }

        if(setB.size() > setA.size()){
            return false;
        }

        for (int i = 0; i < setA.size(); i++) {
            for (int j = 0; j < setB.size(); j++) {
                if(setA.get(i) == setB.get(j)) {
                    return true;
                }
            }
        }
        return false;
    }

}
