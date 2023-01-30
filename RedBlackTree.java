// --== CS400 File Header Information ==--
// Name: Haozhe Wu
// CSL Username: haozhew
// Email: hwu435@wisc.edu
// Lecture #: 002 @1:00pm
// Notes to Grader: <any optional extra notes to your grader>


import java.util.*;


/**
 * Red-Black Tree implementation with a Node inner class for representing the nodes of the tree.
 * Currently, this implements a Binary Search Tree that we will turn into a red black tree by
 * modifying the insert functionality. In this activity, we will start with implementing rotations
 * for the binary search tree insert algorithm. You can use this class' insert method to build a
 * regular binary search tree, and its toString method to display a level-order traversal of the
 * tree.
 */
public class RedBlackTree<T extends Comparable<T>> implements IRedBlackTree<T> {

  protected Node<T> root; // reference to root node of tree, null when empty
  protected int size = 0; // the number of values in the tree
  protected Comparator<T> comparator; // to be compared with

  /**
   * Default constructor, the tree would arrange the element by default T.compareTo()
   */
  public RedBlackTree() {}

  /**
   * a constructor that take one comparator as argument, the tree would arrange the element using
   * the comparator
   *
   * @param comparator given comparator
   */
  public RedBlackTree(Comparator<T> comparator) {
    this.comparator = comparator;
  }

  /**
   * find first element that matches the condition (return is 0) in the tree Note: the predicate
   * must use the same rule as the comparator EX: if the tree is sorted by T.getYear(), then the
   * predicate can only search for element by year and CANNOT search for anything else
   *
   * @param predicate the lambda expression for searching elements
   * @return all elements
   */
  @Override
  public T get(ICompareMatcher<T> predicate) {
    T answer = null;
    Node<T> rootCopy = root;

    // if current node is empty, return null
    if (this.isEmpty()) {
      return null;
    }

    // Binary Search
    while (predicate.compare(rootCopy.data) != 0) {
      if (predicate.compare(rootCopy.data) < 0) {
        if (rootCopy.rightChild == null) {
          return null;
        }
        rootCopy = rootCopy.rightChild;
      } else if (predicate.compare(rootCopy.data) > 0) {
        if (rootCopy.leftChild == null) {
          return null;
        }
        rootCopy = rootCopy.leftChild;
      }
    }

    answer = rootCopy.data;

    return answer;
  }

  /**
   * find all elements that match the condition (return is 0) in the tree Note: the predicate must
   * use the same rule as the comparator EX: if the tree is sorted by T.getYear(), then the
   * predicate can only search for element by year and CANNOT search for anything else
   *
   * @param predicate the lambda expression for searching elements
   * @return all elements
   */
  @Override
  public List<T> getAll(ICompareMatcher<T> predicate) {
    List<T> answer = new ArrayList<>();

    return getAllHelper(predicate, root, answer);
  }

  /**
   * Helper method of getAll()
   * 
   * @param predicate the lambda expression for searching elements
   * @param newRoot given new root
   * @param answer the returned list of nodes
   * @return the list of nodes we got
   */
  private List<T> getAllHelper(ICompareMatcher<T> predicate, Node<T> newRoot, List<T> answer) {

    if (predicate.compare(newRoot.data) == 0) {

      // if the current node is the one we want to get, add it to the answer
      answer.add(newRoot.data);

      // If the right child is not null, do recursion
      if (newRoot.rightChild != null) {
        getAllHelper(predicate, newRoot.rightChild, answer);
      }

      // If the left child is not null, do recursion
      if (newRoot.leftChild != null) {
        getAllHelper(predicate, newRoot.leftChild, answer);
      }

    } else if (predicate.compare(newRoot.data) < 0) { // search for the song we are looking for

      // If the right child is not null, do recursion
      if (newRoot.rightChild == null) {
        return answer;
      }
      getAllHelper(predicate, newRoot.rightChild, answer);

    } else { // search for the song we are looking for
      // If the left child is not null, do recursion
      if (newRoot.leftChild == null) {
        return answer;
      }
      getAllHelper(predicate, newRoot.leftChild, answer);
    }

    return answer;
  }

  /**
   * find the first element that matches the condition and remove it from the tree Note: the
   * predicate must use the same rule as the comparator EX: if the tree is sorted by T.getYear(),
   * then the predicate can only search for element by year and CANNOT search for anything else
   *
   * @param value the value for searching elements
   * @return true if the element removed successfully
   */
  @Override
  public boolean remove(T value) {
    Node<T> parent = getNode(value);
    return remove(parent);
  }

  /**
   * remove() helper method
   * 
   * @param removeNode Node that need to be removed
   * @return true if the element removed successfully
   */
  protected boolean remove(Node<T> removeNode) {
    if (removeNode == null)
      return false;

    // simplest case: 1 child
    if (removeNode.childCount() == 1) {
      Node<T> child = removeNode.rightChild == null ? removeNode.leftChild : removeNode.rightChild;
      rotate(child, removeNode);
      removeNode.disconnect();
      child.blackHeight = 1;

      size--;
      return true;
    }

    // case: 2 child
    if (removeNode.childCount() == 2) {
      Node<T> successorNode = removeNode.findSuccessor();
      removeNode.data = successorNode.data;
      return remove(successorNode);
    }

    // case: 0 child
    // EX+
    // node is black
    if (removeNode.blackHeight != 0) {
      removeNode.blackHeight = 2;
      enforceRBTreePropertiesAfterRemove(removeNode);
      // then node should have a fake blackheight of 1
    }
    removeNode.disconnect();
    // up to double black
    size--;
    return true;

  }

  /**
   * Resolve any red node with red parent property violations that are introduced by remove nodes
   * from a red-black tree.
   *
   * @param oldNode old red node
   */
  protected void enforceRBTreePropertiesAfterRemove(Node<T> oldNode) {
    // when the root is being removed
    if (oldNode.parent == null) {
      root = null;
      return;
    }
    Node<T> parent = oldNode.parent;
    Node<T> sibling = oldNode.isLeftChild() ? parent.rightChild : parent.leftChild;

    // case: sibling is red
    if (sibling.blackHeight == 0) {
      rotate(sibling, parent);
      sibling.blackHeight = 1;
      parent.blackHeight = 0;
      sibling = oldNode.isLeftChild() ? parent.rightChild : parent.leftChild;
    }

    // ALL CASE: sibling is black
    Node<T> leftNephew = sibling.leftChild;
    Node<T> rightNephew = sibling.rightChild;
    int leftNephewBlackHeight = leftNephew != null ? leftNephew.blackHeight : 1;
    int rightNephewBlackHeight = rightNephew != null ? rightNephew.blackHeight : 1;

    // case: all nephew are black
    if (leftNephewBlackHeight + rightNephewBlackHeight == 2) {
      oldNode.blackHeight--;
      sibling.blackHeight--;
      parent.blackHeight++;
      // if parent becomes a double black node, continue resolve
      if (parent.blackHeight == 2) {
        enforceRBTreePropertiesAfterRemove(parent);
      }
      return;
    }
    Node<T> redNephew = leftNephewBlackHeight == 0 ? leftNephew : rightNephew;
    boolean redNephewSameSide = oldNode.isLeftChild() == redNephew.isLeftChild();

    // case: red nephew is at the same side with removing node
    if (redNephewSameSide) {
      rotate(redNephew, sibling);
      redNephew.blackHeight = 1;
      sibling.blackHeight = 0;

      redNephew = sibling;
      sibling = redNephew.parent;
    }

    // case: red nephew is at the different side with removing node
    rotate(sibling, parent);
    sibling.blackHeight = parent.blackHeight;
    parent.blackHeight = 1;
    redNephew.blackHeight = 1;
    oldNode.blackHeight = 1;
  }

  /**
   * Get a Node
   * 
   * @param data the data value to look for
   * @return true of the value is in the subtree, false if not
   */
  public Node<T> getNode(T data) {
    return getNodeHelper(data, root);
  }

  /**
   * Recursive helper method that recurse through the tree and looks for the value *data*.
   *
   * @param data the data value to look for
   * @param subtree the subtree to search through
   * @return true of the value is in the subtree, false if not
   */
  private Node<T> getNodeHelper(T data, Node<T> subtree) {
    if (subtree == null) {
      // we are at a null child, value is not in tree
      return null;
    } else {
      int compare = data.compareTo(subtree.data);
      if (compare < 0) {
        // go left in the tree
        return getNodeHelper(data, subtree.leftChild);
      } else if (compare > 0) {
        // go right in the tree
        return getNodeHelper(data, subtree.rightChild);
      } else {
        // we found it :)
        return subtree;
      }
    }
  }

  /**
   * This class represents a node holding a single value within a binary tree the parent, left, and
   * right child references are always maintained.
   */
  protected static class Node<T> {
    public T data;
    public Node<T> parent; // null for root node
    public Node<T> leftChild;
    public Node<T> rightChild;
    public int blackHeight;

    public Node(T data) {
      this.data = data;
    }

    /**
     * @return true when this node has a parent and is the left child of that parent, otherwise
     *         return false
     */
    public boolean isLeftChild() {
      return parent != null && parent.leftChild == this;
    }

    /**
     * @return the number of children of current node
     */
    public int childCount() {
      return (leftChild == null ? 0 : 1) + (rightChild == null ? 0 : 1);
    }

    public void disconnect() {
      // it is not connected to a parent anyway...
      if (parent == null) {
        return;
      }

      // if it is connect to a parent
      if (isLeftChild()) {
        parent.leftChild = null;
      } else {
        parent.rightChild = null;
      }
      this.parent = null;
    }

    /**
     * find the successor
     * 
     * @return the successor
     */
    public Node<T> findSuccessor() {
      Node<T> current = rightChild;
      if (current == null)
        return null;
      while (current.leftChild != null) {
        current = current.leftChild;
      }
      return current;
    }

    /**
     * find the predecessor
     * 
     * @return the predecessor
     */
    public Node<T> findPredecessor() {
      Node<T> current = leftChild;
      if (current == null)
        return null;
      while (current.rightChild != null) {
        current = current.rightChild;
      }
      return current;
    }
  }

  /**
   * Resolve any red node with red parent property violations that are introduced by inserting new
   * nodes into a red-black tree.
   *
   * @param newInsert newly added red node
   */
  protected void enforceRBTreePropertiesAfterInsert(Node<T> newInsert) {
    // if it's a new tree, then newInsert.blackHeight = 1
    if (newInsert.parent == null) {
      newInsert.blackHeight = 1;
      return;
    }
    if (newInsert.parent.blackHeight != 0) {
      return;
    }
    if (newInsert.parent.parent == null) {
      return;
    }

    Node<T> parent = newInsert.parent;
    Node<T> grandParent = newInsert.parent.parent;
    Node<T> uncle;
    boolean isSameSide;

    if (newInsert.parent.isLeftChild()) {
      uncle = newInsert.parent.parent.rightChild;
      isSameSide = newInsert.isLeftChild();
    } else {
      uncle = newInsert.parent.parent.leftChild;
      isSameSide = !newInsert.isLeftChild();
    }

    // case 3
    if (newInsert.parent.blackHeight == 0 && grandParent.blackHeight != 0 && uncle != null
        && uncle.blackHeight == 0) {
      newInsert.parent.blackHeight = 1;
      grandParent.blackHeight = 0;
      uncle.blackHeight = 1;
      enforceRBTreePropertiesAfterInsert(grandParent);
      return;
    }

    // case 1 to case 2
    if (!isSameSide) {
      rotate(newInsert, newInsert.parent);
      newInsert = parent;
      parent = newInsert.parent;

    }

    // case 2
    if (newInsert.parent.blackHeight == 0 && grandParent.blackHeight != 0) {
      rotate(newInsert.parent, grandParent);
      grandParent.blackHeight = 0;
      newInsert.parent.blackHeight = 1;
    }

  }

  /**
   * Performs a naive insertion into a binary search tree: adding the input data value to a new node
   * in a leaf position within the tree. After this insertion, no attempt is made to restructure or
   * balance the tree. This tree will not hold null references, nor duplicate data values.
   *
   * @param data to be added into this binary search tree
   * @return true if the value was inserted, false if not
   * @throws NullPointerException when the provided data argument is null
   * @throws IllegalArgumentException when the newNode and subtree contain equal data references
   */
  @Override
  public boolean insert(T data) throws NullPointerException, IllegalArgumentException {
    // null references cannot be stored within this tree
    if (data == null)
      throw new NullPointerException("This RedBlackTree cannot store null references.");

    Node<T> newNode = new Node<>(data);
    if (root == null) {
      root = newNode;
      size++;

      // set the root node of the red black tree to be black
      root.blackHeight = 1;

      return true;
    } // add first node to an empty tree
    else {
      boolean returnValue = insertHelper(newNode, root); // recursively insert into subtree
      if (returnValue)
        size++;
      else
        throw new IllegalArgumentException("This RedBlackTree already contains that value.");

      // set the root node of the red black tree to be black
      root.blackHeight = 1;

      return returnValue;
    }
  }

  /**
   * Recursive helper method to find the subtree with a null reference in the position that the
   * newNode should be inserted, and then extend this tree by the newNode in that position.
   *
   * @param newNode is the new node that is being added to this tree
   * @param subtree is the reference to a node within this tree which the newNode should be inserted
   *        as a descenedent beneath
   * @return true is the value was inserted in subtree, false if not
   */
  private boolean insertHelper(Node<T> newNode, Node<T> subtree) {
    int compare;

    if (comparator == null) {
      compare = newNode.data.compareTo(subtree.data);
    } else {
      compare = comparator.compare(newNode.data, subtree.data);
    }

    // do not allow duplicate values to be stored within this tree
    if (compare == 0)
      return false;

    // store newNode within left subtree of subtree
    else if (compare < 0) {
      if (subtree.leftChild == null) { // left subtree empty, add here
        subtree.leftChild = newNode;
        newNode.parent = subtree;

        enforceRBTreePropertiesAfterInsert(newNode);

        return true;
        // otherwise continue recursive search for location to insert
      } else
        return insertHelper(newNode, subtree.leftChild);
    }

    // store newNode within the right subtree of subtree
    else {
      if (subtree.rightChild == null) { // right subtree empty, add here
        subtree.rightChild = newNode;
        newNode.parent = subtree;

        enforceRBTreePropertiesAfterInsert(newNode);

        return true;
        // otherwise continue recursive search for location to insert
      } else
        return insertHelper(newNode, subtree.rightChild);
    }
  }

  /**
   * Performs the rotation operation on the provided nodes within this tree. When the provided child
   * is a leftChild of the provided parent, this method will perform a right rotation. When the
   * provided child is a rightChild of the provided parent, this method will perform a left
   * rotation. When the provided nodes are not related in one of these ways, this method will throw
   * an IllegalArgumentException.
   *
   * @param child is the node being rotated from child to parent position (between these two node
   *        arguments)
   * @param parent is the node being rotated from parent to child position (between these two node
   *        arguments)
   * @throws IllegalArgumentException when the provided child and parent node references are not
   *         initially (pre-rotation) related that way
   */
  private void rotate(Node<T> child, Node<T> parent) throws IllegalArgumentException {
    // When the provided nodes are not related in one of these ways, this method will throw an
    // IllegalArgumentException.
    if (parent.leftChild != child && parent.rightChild != child) {
      throw new IllegalArgumentException("The provided nodes are not related");
    }

    // If the parent is root
    if (parent == root) {

      // If child is left Child, do right rotation
      if (child.isLeftChild()) {
        Node<T> copy = child.rightChild;
        root = child;
        child.rightChild = parent;
        parent.leftChild = copy;
        parent.parent = child;

        if (parent.leftChild != null) {
          parent.leftChild.parent = parent;
        }
      } else { // If child is right Child, do left rotation
        Node<T> copy = child.leftChild;
        root = child;
        child.leftChild = parent;
        parent.rightChild = copy;
        parent.parent = child;

        if (parent.rightChild != null) {
          parent.rightChild.parent = parent;
        }
      }
    } else { // If the parent is not the root node
      if (parent.isLeftChild()) { // parent is a left child
        if (parent.leftChild == child) { // If child is left Child, do right rotation
          Node<T> copy = child.rightChild;
          child.parent = parent.parent;
          parent.parent.leftChild = child;
          child.rightChild = parent;
          parent.leftChild = copy;
          parent.parent = child;

          if (parent.leftChild != null) {
            parent.leftChild.parent = parent;
          }
        } else { // If child is right Child, do left rotation
          Node<T> copy = child.leftChild;
          child.parent = parent.parent;
          parent.parent.leftChild = child;
          child.leftChild = parent;
          parent.rightChild = copy;
          parent.parent = child;

          if (parent.rightChild != null) {
            parent.rightChild.parent = parent;
          }
        }
      } else { // parent is a right child
        if (parent.leftChild == child) { // If child is left Child, do right rotation
          Node<T> copy = child.rightChild;
          child.parent = parent.parent;
          parent.parent.rightChild = child;
          child.rightChild = parent;
          parent.leftChild = copy;
          parent.parent = child;

          if (parent.leftChild != null) {
            parent.leftChild.parent = parent;
          }
        } else { // If child is right Child, do left rotation
          Node<T> copy = child.leftChild;
          child.parent = parent.parent;
          parent.parent.rightChild = child;
          child.leftChild = parent;
          parent.rightChild = copy;
          parent.parent = child;

          if (parent.rightChild != null) {
            parent.rightChild.parent = parent;
          }
        }
      }
    }
  }

  /**
   * Get the size of the tree (its number of nodes).
   *
   * @return the number of nodes in the tree
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * Method to check if the tree is empty (does not contain any node).
   *
   * @return true of this.size() return 0, false if this.size() > 0
   */
  @Override
  public boolean isEmpty() {
    return this.size() == 0;
  }

  /**
   * Checks whether the tree contains the value *data*.
   *
   * @param data the data value to test for
   * @return true if *data* is in the tree, false if it is not in the tree
   */
  @Override
  public boolean contains(T data) {
    // null references will not be stored within this tree
    if (data == null)
      throw new NullPointerException("This RedBlackTree cannot store null references.");
    return this.containsHelper(data, root);
  }

  /**
   * Recursive helper method that recurses through the tree and looks for the value *data*.
   *
   * @param data the data value to look for
   * @param subtree the subtree to search through
   * @return true of the value is in the subtree, false if not
   */
  private boolean containsHelper(T data, Node<T> subtree) {
    if (subtree == null) {
      // we are at a null child, value is not in tree
      return false;
    } else {

      int compare;

      if (comparator == null) {
        compare = data.compareTo(subtree.data);
      } else {
        compare = comparator.compare(data, subtree.data);
      }

      if (compare < 0) {
        // go left in the tree
        return containsHelper(data, subtree.leftChild);
      } else if (compare > 0) {
        // go right in the tree
        return containsHelper(data, subtree.rightChild);
      } else {
        // we found it :)
        return true;
      }
    }
  }

  /**
   * Returns an iterator over the values in in-order (sorted) order.
   *
   * @return iterator object that traverses the tree in in-order sequence
   */
  @Override
  public Iterator<T> iterator() {
    // use an anonymous class here that implements the Iterator interface
    // we create a new on-off object of this class everytime the iterator
    // method is called
    return new Iterator<T>() {
      // a stack and current reference store the progress of the traversal
      // so that we can return one value at a time with the Iterator
      Stack<Node<T>> stack = null;
      Node<T> current = root;

      /**
       * The next method is called for each value in the traversal sequence. It returns one value at
       * a time.
       * 
       * @return next value in the sequence of the traversal
       * @throws NoSuchElementException if there is no more elements in the sequence
       */
      public T next() {
        // if stack == null, we need to initialize the stack and current element
        if (stack == null) {
          stack = new Stack<Node<T>>();
          current = root;
        }
        // go left as far as possible in the sub tree we are in un8til we hit a null
        // leaf (current is null), pushing all the nodes we fund on our way onto the
        // stack to process later
        while (current != null) {
          stack.push(current);
          current = current.leftChild;
        }
        // as long as the stack is not empty, we haven't finished the traversal yet;
        // take the next element from the stack and return it, then start to step down
        // its right subtree (set its right sub tree to current)
        if (!stack.isEmpty()) {
          Node<T> processedNode = stack.pop();
          current = processedNode.rightChild;
          return processedNode.data;
        } else {
          // if the stack is empty, we are done with our traversal
          throw new NoSuchElementException("There are no more elements in the tree");
        }

      }

      /**
       * Returns a boolean that indicates if the iterator has more elements (true), or if the
       * traversal has finished (false)
       * 
       * @return boolean indicating whether there are more elements / steps for the traversal
       */
      public boolean hasNext() {
        // return true if we either still have a current reference, or the stack
        // is not empty yet
        return !(current == null && (stack == null || stack.isEmpty()));
      }

    };
  }

  /**
   * This method performs an inorder traversal of the tree. The string representations of each data
   * value within this tree are assembled into a comma separated string within brackets (similar to
   * many implementations of java.util.Collection, like java.util.ArrayList, LinkedList, etc). Note
   * that this RedBlackTree class implementation of toString generates an inorder traversal. The
   * toString of the Node class class above produces a level order traversal of the nodes / values
   * of the tree.
   *
   * @return string containing the ordered values of this tree (in-order traversal)
   */
  public String toInOrderString() {
    // use the inorder Iterator that we get by calling the iterator method above
    // to generate a string of all values of the tree in (ordered) in-order
    // traversal sequence
    Iterator<T> treeNodeIterator = this.iterator();
    StringBuffer sb = new StringBuffer();
    sb.append("[ ");
    if (treeNodeIterator.hasNext())
      sb.append(treeNodeIterator.next());
    while (treeNodeIterator.hasNext()) {
      T data = treeNodeIterator.next();
      sb.append(", ");
      sb.append(data.toString());
    }
    sb.append(" ]");
    return sb.toString();
  }

  /**
   * This method performs a level order traversal of the tree rooted at the current node. The string
   * representations of each data value within this tree are assembled into a comma separated string
   * within brackets (similar to many implementations of java.util.Collection). Note that the Node's
   * implementation of toString generates a level order traversal. The toString of the RedBlackTree
   * class below produces an inorder traversal of the nodes / values of the tree. This method will
   * be helpful as a helper for the debugging and testing of your rotation implementation.
   *
   * @return string containing the values of this tree in level order
   */
  public String toLevelOrderString() {
    String output = "[ ";
    LinkedList<Node<T>> q = new LinkedList<>();
    q.add(this.root);
    while (!q.isEmpty()) {
      Node<T> next = q.removeFirst();
      if (next.leftChild != null)
        q.add(next.leftChild);
      if (next.rightChild != null)
        q.add(next.rightChild);
      output += next.data.toString();
      if (!q.isEmpty())
        output += ", ";
    }
    return output + " ]";
  }

  @Override
  public String toString() {
    return "level order: " + this.toLevelOrderString() + "\nin order: " + this.toInOrderString()
        + "\n";
  }

}
