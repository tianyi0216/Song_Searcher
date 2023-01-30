// --== CS400 File Header Information ==--
// Name: Haozhe Wu
// CSL Username: haozhew
// Email: hwu435@wisc.edu
// Lecture #: 002 @1:00pm
// Notes to Grader: <any optional extra notes to your grader>

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Junit Testing class for the RedBlackTree class.
 */
public class AlgorithmEngineerTests {

  RedBlackTree<SongAE> _Test;
  RedBlackTree<ISong> _newTest;

  /**
   * create an instance
   */
  @BeforeEach
  public void createTree() {
    _Test = new RedBlackTree<>();
    _newTest = new RedBlackTree<>();
  }

  /**
   * Tester to test build a basic RBTree and the insert method.
   */
  @Test
  public void test1() {
    // The default size should be 0
    assertEquals(0, _Test.size());

    assertTrue(_Test.isEmpty());

    // create 4 songs
    SongAE s1 = new SongAE(1, "test1");
    SongAE s2 = new SongAE(2, "test2");
    SongAE s3 = new SongAE(3, "test3");
    SongAE s4 = new SongAE(4, "test4");

    // insert one song
    _Test.insert(s1);

    assertFalse(_Test.isEmpty());

    // the size should be 1 noe
    assertEquals(1, _Test.size());

    _Test.insert(s2);
    _Test.insert(s3);
    _Test.insert(s4);

    // test should contain s1, s2, s3, s4
    assertTrue(_Test.contains(s1));
    assertTrue(_Test.contains(s2));
    assertTrue(_Test.contains(s3));
    assertTrue(_Test.contains(s4));


    // test if the RBTree is correct
    assertEquals(4, _Test.size());

    String answer = "level order: " + "[ 2: test2, 1: test1, 3: test3, 4: test4 ]" + "\nin order: "
        + "[ 1: test1, 2: test2, 3: test3, 4: test4 ]" + "\n";

    assertEquals(answer, _Test.toString());

  }

  /**
   * Tester to test the contains() method
   */
  @Test
  public void test2() {
    SongAE s1 = new SongAE(1, "test1");
    SongAE s2 = new SongAE(2, "test2");
    SongAE s3 = new SongAE(3, "test3");
    SongAE s4 = new SongAE(4, "test4");
    SongAE s5 = new SongAE(5, "test5");

    _Test.insert(s1);
    _Test.insert(s2);
    _Test.insert(s3);
    _Test.insert(s4);

    try {
      assertFalse(_Test.contains(null));
    } catch (NullPointerException e) {
    }

    assertTrue(_Test.contains(s1));
    assertTrue(_Test.contains(s2));
    assertTrue(_Test.contains(s3));
    assertTrue(_Test.contains(s4));
    assertFalse(_Test.contains(s5));

  }

  /**
   * Tester to test the get() method
   */
  @Test
  public void test3() {
    SongAE s1 = new SongAE(1, "test1");
    SongAE s2 = new SongAE(2, "test2");
    SongAE s3 = new SongAE(3, "test3");
    SongAE s4 = new SongAE(4, "test4");

    _Test.insert(s1);
    _Test.insert(s2);
    _Test.insert(s3);
    _Test.insert(s4);

    assertEquals("1: test1", (_Test.get(a -> a.number - 1)).toString());
    assertEquals("2: test2", (_Test.get(a -> a.number - 2)).toString());
    assertEquals("3: test3", (_Test.get(a -> a.number - 3)).toString());
    assertEquals("4: test4", (_Test.get(a -> a.number - 4)).toString());

  }

  /**
   * Tester to test the getAll() method
   */
  @Test
  public void test4() {
    SongAE s1 = new SongAE(1, "test1");
    SongAE s2 = new SongAE(2, "test2");
    SongAE s3 = new SongAE(3, "test3");
    SongAE s4 = new SongAE(4, "test4");
    SongAE s5 = new SongAE(1, "test5");
    SongAE s6 = new SongAE(1, "test6");

    _Test.insert(s1);
    _Test.insert(s2);
    _Test.insert(s3);
    _Test.insert(s4);
    _Test.insert(s5);
    _Test.insert(s6);

    assertEquals("[1: test5, 1: test6, 1: test1]", (_Test.getAll(a -> a.number - 1)).toString());

  }

  /**
   * Tester to test a non-default comparator
   */
  @Test
  public void test5() {
    SongAE s1 = new SongAE(1, "test4");
    SongAE s2 = new SongAE(2, "test3");
    SongAE s3 = new SongAE(3, "test2");
    SongAE s4 = new SongAE(4, "test1");

    _Test = new RedBlackTree<>((a, b) -> a.name.compareTo(b.name));

    _Test.insert(s4);
    _Test.insert(s3);
    _Test.insert(s2);
    _Test.insert(s1);


    String answer = "level order: " + "[ 3: test2, 4: test1, 2: test3, 1: test4 ]" + "\nin order: "
        + "[ 4: test1, 3: test2, 2: test3, 1: test4 ]" + "\n";


    assertEquals(answer, _Test.toString());

  }

  /**
   * Tester to test remove()
   */
  @Test
  public void test6() {
    RedBlackTree<Integer> _TreeInt = new RedBlackTree<>();

    for (int i = 1; i < 16; i++) {
      _TreeInt.insert(i);
    }

    assertEquals(_TreeInt.toLevelOrderString(),
        "[ 4, 2, 8, 1, 3, 6, 10, 5, 7, 9, 12, 11, 14, 13, 15 ]");
    _TreeInt.remove(7);
    assertEquals(_TreeInt.toLevelOrderString(),
        "[ 4, 2, 10, 1, 3, 8, 12, 6, 9, 11, 14, 5, 13, 15 ]");
  }

  /**
   * combination test class for DW's Song.java and AE's RedBlackTree.java
   */
  @Test
  public void test7() {
    Song s1 = new Song("title1", "artist1", "album1", 2001);
    Song s2 = new Song("title2", "artist2", "album2", 2002);
    Song s3 = new Song("title3", "artist3", "album3", 2003);
    Song s4 = new Song("title4", "artist4", "album4", 2004);


    _newTest.insert(s1);
    _newTest.insert(s2);
    _newTest.insert(s3);
    _newTest.insert(s4);

    // test should contain s1, s2, s3, s4
    assertTrue(_newTest.contains(s1));
    assertTrue(_newTest.contains(s2));
    assertTrue(_newTest.contains(s3));
    assertTrue(_newTest.contains(s4));

    // the _newTest tree should not be empty now
    assertFalse(_newTest.isEmpty());

    // test if the RBTree is correct
    assertEquals(4, _newTest.size());

  }

  /**
   * combination test class for DW's Song.java and AE's RedBlackTree.java
   */
  @Test
  public void test8() {
    Song s1 = new Song("title1", "artist1", "album1", 2001);
    Song s2 = new Song("title1", "artist2", "album2", 2002);
    Song s3 = new Song("title3", "artist3", "album3", 2003);
    Song s4 = new Song("title4", "artist4", "album4", 2004);
    Song s5 = new Song("title5", "artist5", "album5", 2004);
    Song s6 = new Song("title6", "artist6", "album6", 2004);


    _newTest.insert(s1);
    _newTest.insert(s2);
    _newTest.insert(s3);
    _newTest.insert(s4);
    _newTest.insert(s5);
    _newTest.insert(s6);

    // test the getAll() method
    List<ISong> get = _newTest.getAll(a -> a.getYear() - 2004);

    assertTrue(get.contains(s4));
    assertTrue(get.contains(s5));
    assertTrue(get.contains(s6));

  }

  /**
   * code review for BD
   */
  @Test
  public void test9() {
    SongSearcherBackend test = new SongSearcherBackend();

    // all the size should be 0 at the beginning
    assertEquals(0, test.size);
    assertEquals(0, test.titleTree.size());
    assertEquals(0, test.artistTree.size());

    Song s1 = new Song("title1", "artist1", "album1", 2001);
    Song s2 = new Song("title2", "artist2", "album2", 2002);
    Song s3 = new Song("title3", "artist3", "album3", 2003);
    Song s4 = new Song("title4", "artist4", "album4", 2004);

    // add all the songs
    test.addSong(s1);
    test.addSong(s2);
    test.addSong(s3);
    test.addSong(s4);

    // all the size should be 4 now
    assertEquals(4, test.size);
    assertEquals(4, test.titleTree.size());
    assertEquals(4, test.artistTree.size());

  }

  /**
   * code review for BD
   */
  @Test
  public void test10() {
    SongSearcherBackend test = new SongSearcherBackend();

    Song s1 = new Song("title1", "artist4", "album1", 2001);
    Song s2 = new Song("title2", "artist3", "album2", 2002);
    Song s3 = new Song("title3", "artist2", "album3", 2003);
    Song s4 = new Song("title4", "artist1", "album4", 2004);

    test.addSong(s1);
    test.addSong(s2);
    test.addSong(s3);
    test.addSong(s4);

    // the root of titleTree should be s2
    assertEquals(s2, test.titleTree.root.data);
    // the titleTree root's left tree is s1
    assertEquals(s1, test.titleTree.root.leftChild.data);
    // the titleTree root's right tree is s3
    assertEquals(s3, test.titleTree.root.rightChild.data);
    // s3's right child should be s4
    assertEquals(s4, test.titleTree.root.rightChild.rightChild.data);


    // the root of artistTree should be s2
    assertEquals(s2, test.artistTree.root.data);
    // the artistTree root's left tree is s3
    assertEquals(s3, test.artistTree.root.leftChild.data);
    // the artistTree root's right tree is s1
    assertEquals(s1, test.artistTree.root.rightChild.data);
    // s3's left child should be s4
    assertEquals(s4, test.artistTree.root.leftChild.leftChild.data);

  }

  /**
   * placeholder class for AE
   */
  public class SongAE implements Comparable<SongAE> {
    public int number;
    public String name;

    public SongAE() {
      this.name = "";
      this.number = -100;
    }

    public SongAE(int number, String name) {
      this.number = number;
      this.name = name;
    }

    public int getNumber() {
      return this.number;
    }

    public String getName() {
      return this.name;
    }

    public String toString() {
      return number + ": " + name;
    }

    @Override
    public int compareTo(SongAE s2) {
      if (this.number - s2.number != 0)
        return this.number - s2.number;

      else {
        return this.name.compareTo(s2.name);
      }
    }
  }

}
