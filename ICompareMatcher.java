// --== CS400 Project Two File Header ==--
// Name: Haozhe Wu
// CSL Username: haozhew
// Email: hwu435@wisc.edu
// Lecture #: 002 @1:00pm
// Notes to Grader: <any optional extra notes to your grader>

public interface ICompareMatcher<T> {
    /**
     * compare the given value by certain rules and return the value, it is similar
     * to compareTo:
     *
     * @param a the value to be compared by the rule
     * @return
     *         if return > 0, the current value is greater than the condition given <p>
     *         if return = 0, the current value matches the condition given <p>
     *         if return < 0, the current value is smaller than the condition given <p>
     */
    public int compare(T a);
}
