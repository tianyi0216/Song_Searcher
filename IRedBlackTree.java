// --== CS400 Project Two File Header ==--
// Name: Haozhe Wu
// CSL Username: haozhew
// Email: hwu435@wisc.edu
// Lecture #: 002 @1:00pm
// Notes to Grader: <any optional extra notes to your grader>

import java.util.List;

public interface IRedBlackTree<T extends Comparable<T>> extends SortedCollectionInterface<T> {

    /*
      constructor : this(), // a default constructor, the tree would arrange the
      element by default T.compareTo()
      this(Comparator<T> comparator) // a constructor that take one comparator as
      argument, the tree would arrange the element using the comparator
     */

    /**
     * find first element that matches the condition (return is 0) in the tree
     * Note: the predicate must use the same rule as the comparator
     * EX: if the tree is sorted by T.getYear(), then the predicate can only search
     * for element by year and CANNOT search for anything else
     *
     * @param predicate the lambda expression for searching elements
     * @return all elements
     */
    public T get(ICompareMatcher<T> predicate);

    /**
     * find all elements that match the condition (return is 0) in the tree
     * Note: the predicate must use the same rule as the comparator
     * EX: if the tree is sorted by T.getYear(), then the predicate can only search
     * for element by year and CANNOT search for anything else
     *
     * @param predicate the lambda expression for searching elements
     * @return all elements
     */
    public List<T> getAll(ICompareMatcher<T> predicate);

    /**
     * find the first element that matches the condition and remove it from the tree
     * Note: the predicate must use the same rule as the comparator
     * EX: if the tree is sorted by T.getYear(), then the predicate can only search
     * for element by year and CANNOT search for anything else
     *
     * @param value the value for searching elements
     * @return true if the element removed successfully
     */
    public boolean remove(T value);

}
