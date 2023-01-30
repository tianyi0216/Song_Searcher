// --== CS400 Project Two File Header ==--
// Name: Tianyi Xu
// CSL Username: tianyi
// Email: txu223@wisc.edu
// Lecture #: 002 
// Notes to Grader: test 10 was originally test 3 but since proposal changed, and FD also no longer
// have remove, will act as an extra test 

import java.io.FileNotFoundException;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Junit Testing class for the SongSearcherBackend class. Test if all methods works as expected 
 * from the backend of SongSearcher.
 * 
 * @author Tianyi Xu
 */
public class BackendDeveloperTests {

    SongSearcherBackend test;

    /**
     * create an instance of RBTree for testing purpose
     */
    @BeforeEach
    public void createBackend(){
        test = new SongSearcherBackend();
    }

    /**
     * Test the constructor and getNumberOfSongs() method of the backend. Test case include 
     * create a new instance of object to see if it is initialized correctly, add a song and
     * remove a song to see if getNumberOfSongs() returned correct numbers
     */
    @Test
    public void test1(){
        
        // case 1, testing if correctly create a rbtree without exception
        SongSearcherBackend test = new SongSearcherBackend();

        // test correctly create a backend object withou throwing exception and size 0;
        assertEquals(0, test.size);

        // test if correctly created two trees for title and artist
        // both would throw exception if not created and call size method. and size should give
        // 0
        assertEquals(0, test.titleTree.size());
        assertEquals(0, test.artistTree.size());
            
        // check if comparator is successfully set for both tree, should be null for title, not artist
        assertEquals(true, test.titleTree.comparator == null);
        assertFalse(test.artistTree.comparator == null);
            
        // case 2, hard code some songs to be added to the backend to test getNumberOfSongs()
        Song a = new Song("test", "test", "test", 123);
        Song b = new Song("test1", "test", "test", 123);
        Song c = new Song("test2", "test", "test", 123);
        Song d = new Song("test3", "test", "test", 123);

        // getNumberOfSong should return 0 when no songs were added
        assertEquals(0, test.getNumberOfSongs());

        // added first one, should be 1
        test.addSong(a);
        assertEquals(1, test.getNumberOfSongs());

        // add the rest of songs into the backend, test if size is increasing correctly
        test.addSong(b);
        test.addSong(c);
        test.addSong(d);
        assertEquals(4, test.getNumberOfSongs());
    }

    /**
     * Test the addSong method from the backend. Test cases include add 1 song, add null, 
     * add multiple songs and add duplicate songs. Should correctly add it into both
     * trees and increment size field.
     */
    @Test
    public void test2(){
        // case 1, test adding 1 song into the backend
        Song a = new Song("testH", "test1", "testH", 1234);
        test.addSong(a);

        // size should be 1 after add 1, check
        assertEquals(1, test.size);
             
        // check if root for both tree is equal to the song added
        // should be the same reference (can also use == operator)
        assertEquals(a, test.titleTree.root.data);
        assertEquals(a, test.artistTree.root.data);
            
            // case 2, add a null song, should throw NullPointerException
        Song b = null;
        NullPointerException n = assertThrows(NullPointerException.class, () -> 
            {test.addSong(b);});

        assertEquals("This RedBlackTree cannot store null references.", n.getMessage());

        // code more data to add to the tree
        Song c = new Song("testD", "test2", "testD", 2222);
        Song d = new Song("testS", "test3", "testS", 3333);
        Song e = new Song("testC", "test4", "testC", 1111);

        // case 3: add 3 more song into the backend, test to see if it is the 
        // correctly added and if added to each tree according to their comparator
        test.addSong(c);
        test.addSong(d);
        test.addSong(e);

        // test if size is 4 for the tree
        assertEquals(4, test.size);

        // test lamdba expression on the comparator works for two trees
        // test if title tree isnerted base on comparing title first by chekcing their location
        assertEquals(c, test.titleTree.root.leftChild.data);
        assertEquals(e, test.titleTree.root.leftChild.leftChild.data);
        assertEquals(d, test.titleTree.root.rightChild.data);

        // test if artist tree inserted base on compare artist first, same method of checking
        assertEquals(d, test.artistTree.root.rightChild.data);

        assertEquals(e, test.artistTree.root.rightChild.rightChild.data);

        assertEquals(c, test.artistTree.root.data);

        // case 4: add a song already exist (have same information), should not be added
        Song replicate = new Song("testH", "test1", "testH", 1234);
        test.addSong(replicate);

        assertEquals(4, test.size);
    }

    /**
     * Was testing remove in P2W3 individual code but do not have that function anymore.
     * New test3 will be testing a general run from beginning, simulating how a user would
     * use all function of backend from adding, searching etc.
     * Also testing using DW's song object instead of place holders
     **/
    @Test
    public void test3(){
        // backend should have 0 songs for now
        assertEquals(0, test.size);
        assertEquals(false, test.titleTree == null);
        assertEquals(false, test.artistTree == null);

        // make few Songs to use
        Song a = new Song("te st123", "testD", "test", 2025);
        Song b = new Song("tEST100", "testH", "test", 2013);
        Song c = new Song("te   St156", "testD", "test", 1998);
        Song d = new Song("te st123", "testJ", "test", 1900);
        Song e = new Song("te st123", "testD", "test", 2025);

        // user add songs into the backend
        test.addSong(a);
        test.addSong(b);
        test.addSong(c);
        test.addSong(d);
        test.addSong(e);

        // should have 5 songs, e should be not added due to duplicate
        assertEquals(4, test.size);
        assertEquals(4, test.getNumberOfSongs());
        
        // user try search by titleword as well as ability to ignore space when searching
        List<ISong> result = test.searchByTitleWord("test");
        assertEquals(4, result.size());
        assertEquals(true, result.contains(a));
        assertEquals(true, result.contains(b));
        assertEquals(true, result.contains(c));
        assertEquals(true, result.contains(d));
        assertEquals(false, result.contains(e));

        // use try search by artist
        result = test.searchByArtist("testJ");
        assertEquals(1, result.size());
        assertEquals(false, result.contains(a));
        assertEquals(false, result.contains(b));
        assertEquals(false, result.contains(c));
        assertEquals(true, result.contains(d));
        assertEquals(false, result.contains(e));
    }

    /**
     * Test the search for title word method. Cases include search when only 1 song
     * is in backend, multiple songs are in backend, search for title world with no matching
     * coditions etc. Should correctly return a list of all songs have matching starting
     * title world. Also test if the lambda predicate written by backend is working or not.
     */
    @Test
    public void test4(){
        Song a = new Song("test1", "test2", "album", 1033);
        test.addSong(a);

        // case 1, try search when 1 song is added, should get the song in the list
        // and list size should be 1
        List<ISong> result = test.searchByTitleWord("test");
        assertEquals(a, result.get(0));
        assertEquals(1, result.size());

        // code more songs to add to the backend for testing
        Song b = new Song("not test1", "test2", "album", 1033);
        Song c = new Song("not test3", "test2", "album", 1033);
        Song d = new Song("test2", "test2", "album", 1033);
        Song e = new Song("please", "test2", "album", 1033);
        test.addSong(b);
        test.addSong(c);
        test.addSong(d);
        test.addSong(e);

        // case 2, search for word when multiple songs are added
        result = test.searchByTitleWord("not");
        assertEquals(2, result.size());
            
        // should contains those two songs
        assertEquals(true, result.contains(c));
        assertEquals(true, result.contains(b));

        // try to see if search by starting world's lambda is working or not.
        result = test.searchByTitleWord("tes");
        assertEquals(2, result.size());
        assertEquals(true, result.contains(a));
        assertEquals(true, result.contains(d));
            
        // case 3, search for titleword not exsited, should return an empty lisrt
        result = test.searchByTitleWord("hahaha");
        assertEquals(0, result.size());
        assertEquals(true, result.isEmpty());
    }   

    /**
     * Test the search for artist method. Cases include search when only 1 song
     * is in backend, multiple songs are in backend, search for title world with no matching
     * coditions etc. Should correctly return a list of all songs have matching starting
     * title world. Also test if the lambda predicate written by backend is working or not.
     */
    @Test
    public void test5(){
        Song a = new Song("test", "test2", "album", 1033);
        test.addSong(a);

        // case 1, try search when 1 song is added
        List<ISong> result = test.searchByArtist("test2");
        assertEquals(a, result.get(0));
        assertEquals(1, result.size());

        // code more songs to add to the backend for testing
        Song b = new Song("test1", "test3", "album", 1033);
        Song c = new Song("test3", "test2", "album", 1033);
        Song d = new Song("test", "test1", "album", 1033);
        Song e = new Song("please", "test5", "album", 1033);
        test.addSong(b);
        test.addSong(c);
        test.addSong(d);
        test.addSong(e);

        // case 2, search for artist when multiple songs are added
        result = test.searchByArtist("test2");
        assertEquals(2, result.size());
        assertEquals(true, result.contains(c));
        assertEquals(true, result.contains(a));

        result = test.searchByArtist("test1");
        assertEquals(1, result.size());
        assertEquals(true, result.contains(d));

        // case 3, search for artist not exsited, should return an empty lisrt
        result = test.searchByArtist("hahaha");
        assertEquals(0, result.size());
        assertEquals(true, result.isEmpty());
    }

    /**
     * Tests Backend's working with the DW. Test title tree uses DW's compareTo method to sort
     * the songs into the RBTree.
     */
    @Test
    public void test6(){
        // should not have a comparator
        assertEquals(true, test.titleTree.comparator == null);
        assertEquals(0, test.size);

        // case 1, add songs and test if songs are sort in order for title tree
        Song a = new Song("test5", "testD", "test", 2025);
        Song b = new Song("test3", "testH", "test", 2013);
        Song c = new Song("test9", "testD", "test", 1998);
        Song d = new Song("test3", "testJ", "test", 1900);
        Song e = new Song("test7", "testD", "test", 2025);

        test.addSong(a);
        test.addSong(b);
        test.addSong(c);
        test.addSong(d);
        test.addSong(e);

        // test inserting correctly, add get number of Songs
        assertEquals(5, test.getNumberOfSongs());
        
        // testing order inserted by chekcing tree's position, should be inserted this way
        // according to rbtree property and song's compareTo
        assertEquals(a, test.titleTree.root.data);
        assertEquals(b, test.titleTree.root.leftChild.data);
        assertEquals(c, test.titleTree.root.rightChild.data);
        assertEquals(d, test.titleTree.root.leftChild.rightChild.data);
        assertEquals(e, test.titleTree.root.rightChild.leftChild.data);

        //test the order of the artist tree
        assertEquals(c, test.artistTree.root.data);
        assertEquals(a, test.artistTree.root.leftChild.data);
        assertEquals(b, test.artistTree.root.rightChild.data);
        assertEquals(e, test.artistTree.root.leftChild.rightChild.data);
        assertEquals(d, test.artistTree.root.rightChild.rightChild.data);
    }

    /**
     * Test BD's add function to add songs loaded by DW's song loader
     * @throws FileNotFoundException if file is not found
     */
    @Test
    public void test7() throws FileNotFoundException{
        SongLoader loader = new SongLoader();
        List<ISong> songs = loader.loadSongs("songs.xml");

        // should load 10000 songs from the file
        assertEquals(10000, songs.size());

        // case 1, add all into backend see if BD's code add 9984 songs
        // 16 will not be added due to exact duplicate values
        for(ISong song: songs) test.addSong(song);
        assertEquals(9984, test.size);
        assertEquals(9984, test.getNumberOfSongs());

        // case 2, test each tree's size, should also be 9984
        assertEquals(9984, test.titleTree.size);
        assertEquals(9984, test.artistTree.size);
    }

    /**
     * Code Review for AE
     * Test AE's RBTree's general purpose
     */
    @Test
    public void test8(){
        // create a new rbtree of integer for testing purpose
        RedBlackTree<Integer> rbtree = new RedBlackTree<Integer>();

        // test basic attribute of tree
        assertEquals(0, rbtree.size);
        assertEquals(null, rbtree.comparator);
        
        // case 1: try insert and see if size increases correclty
        rbtree.insert(1);
        rbtree.insert(2);
        rbtree.insert(3);
        rbtree.insert(4);
        rbtree.insert(5);
        assertEquals(5, rbtree.size);   
        
        // case 2: test contains
        assertEquals(true, rbtree.contains(1));
        assertEquals(true, rbtree.contains(2));
        assertEquals(true, rbtree.contains(3));
        assertEquals(true, rbtree.contains(4));
        assertEquals(true, rbtree.contains(5));
        assertEquals(false, rbtree.contains(9));

        // case 3: test get method and predicate compareMatcher for the rbtree
        assertEquals(5, rbtree.get((Integer a) -> a - 5));
        assertEquals(1, rbtree.get((Integer a) -> a - 1));
        assertEquals(3, rbtree.get((Integer a) -> a - 3));
        assertEquals(null, rbtree.get((Integer a) -> a - 7));

        // case 4: test getAll from AE to see if it is able to get all expected integer
        List<Integer> result = rbtree.getAll( 
            (Integer a) -> {
                if (a <= 3) return 0;
                else
                    return a - 3;
        });

        assertEquals(3, result.size());
        assertEquals(true, result.contains(1));
        assertEquals(true, result.contains(2));
        assertEquals(true, result.contains(3));

        // try to get all integer higher than 3
        result = rbtree.getAll( 
            (Integer a) -> {
                if (a > 3) return 0;
                else
                    return a - 4;
        });
        assertEquals(2, result.size());
        assertEquals(true, result.contains(4));
        assertEquals(true, result.contains(5));
    }

    /**
     * Code Review for AE
     * Test backend's integration with AE's red black tree, testing cases including check the 
     * addsong function, and search function as well get the number of songs
     */
    @Test
    public void test9(){
        // two trees should not be null, and size be 0
        assertEquals(false, test.titleTree == null);
        assertEquals(false, test.artistTree == null);
        assertEquals(0, test.titleTree.size);
        assertEquals(0, test.artistTree.size);

        // comparator should be null for title, not for artist
        assertEquals(true, test.titleTree.comparator == null);
        assertEquals(false, test.artistTree.comparator == null);
        
        //case 1, test getnumber of song and search when there's no song, should be 0 and null
        assertEquals(0, test.getNumberOfSongs());
        List<ISong> result = test.searchByArtist("artist");
        assertEquals(0, result.size());
        assertEquals(true, result.isEmpty());

        result = test.searchByTitleWord("title");
        assertEquals(0, result.size());
        assertEquals(true, result.isEmpty());

        // test add a null song into AE's tree, should throw exception
        Song empty = null;
        NullPointerException n = assertThrows(NullPointerException.class, () -> 
            {test.addSong(empty);});

        assertEquals("This RedBlackTree cannot store null references.", n.getMessage());
        assertEquals(0, test.getNumberOfSongs());

        // add several songs into it for testing searches
        Song a = new Song("testing", "test5", "album", 1092);
        Song b = new Song("not test", "test1", "album", 1033);
        Song c = new Song("not test 2", "test3", "album", 1033);
        Song d = new Song("test1", "test2", "album", 1033);
        Song e = new Song("test", "test5", "album", 1033);

        test.addSong(a);
        test.addSong(b);
        test.addSong(c);
        test.addSong(d);
        test.addSong(e);

        // case 2, search for songs and test if it works with AE's search
        result = test.searchByTitleWord("test");
        assertEquals(3, result.size());
        assertEquals(true, result.contains(a) && result.contains(d) && result.contains(e));
        assertEquals(false, result.contains(b) && result.contains(c));

        // case 3, test ae's tree working with search by artist
        result = test.searchByArtist("test5");
        assertEquals(2, result.size());
        assertEquals(true, result.contains(a) && result.contains(e));
        assertEquals(false, result.contains(b) && result.contains(c) && result.contains(d));

        // case 4, try ignoring spaces and case feature for search by beginning of title
        Song f = new Song("tE st   ios", "test1", "album", 1023);
        Song g = new Song("t est 5", "test", "album", 1200);
        Song h = new Song("tEST12", "not test this time", "album still", 1234);
        test.addSong(f);
        test.addSong(g);
        test.addSong(h);

        result = test.searchByTitleWord("test");
        // should now have 6 songs and those 3 extreme cases song
        assertEquals(6, result.size());
        assertEquals(true, result.contains(f) && result.contains(g) && result.contains(h));
    }

    /**
     * Test the remove song backend method to see if it correctly removes a song. Test cases
     * include remove when there's 1 song, multiple song, remove a song not in the backend, remove
     * with same name different artist and vice versa. 
     * 
     * this is an extra test for remove, which is no longer part of proposal but since both AE
     * and BD implemented this before it the amendement was approved, it will be used as both code review
     * and for testing only.
     * Was test 3 originally for last week. 
     */
    @Test
    public void test10(){
        // case 1, test removing when there is only 1 song (root)
        Song a = new Song("testA", "testA", "testA", 1234);
        test.addSong(a);

        assertEquals(1, test.getNumberOfSongs());
            
        ISong removed = test.removeSong("testA", "testA");
            
        // size should be 0,  and song removed need to be the song just added
        assertEquals(0, test.getNumberOfSongs());
        assertEquals(a, removed);
            
        // check if removed from both tree
        assertFalse(test.titleTree.root != null);
        assertFalse(test.artistTree.root != null);

        // case 2, remove a song not in the tree, should not remove and keep size unchanged
        Song b = new Song("testH", "test3", "test", 1234);
        test.addSong(b);

        removed = test.removeSong("notgoingtofind", "none");
        assertEquals(1, test.getNumberOfSongs());
            
        // songs returned should be null
        assertEquals(null, removed);

        // case 3, add more songs and test remove
        // remove is not the actual remove, it remove the rightmost node with no children
        // for testing purpose, will remove the rightmost song only so it still
        // works with actual AE's implementation
        Song c = new Song("testO", "test5", "test", 2222);
        Song d = new Song("testC", "test1", "test", 2212);
            
        test.addSong(c);
        test.addSong(d);
            
        // remove testO and test 5, getNumberOfSongs should be 2 and c should be removed
        removed = test.removeSong("testO", "test5");
        assertEquals(2, test.getNumberOfSongs());
        assertEquals(c, removed);

        // add more data for testing 
        Song e = new Song("testO", "test9", "test", 2222);
        Song f = new Song("testK", "test7", "test", 1920);
        Song g = new Song("testN", "test5", "test", 1021);
        test.addSong(e);
        test.addSong(f);
        test.addSong(g);
        
        assertEquals(5, test.getNumberOfSongs());
            
        // try remove same artist, different title, should not remove or change size
        removed = test.removeSong("DNE", "test7");
        assertEquals(null, removed);
        assertEquals(5, test.getNumberOfSongs());

        // try remove same title, different artist, should not remove or change size
        removed = test.removeSong("testO", "DNE");
        assertEquals(null, removed);
        assertEquals(5, test.getNumberOfSongs());

        // tests removing when there are multiple items in tree
        removed = test.removeSong("testO", "test9");
        assertEquals(e, removed);
        assertEquals(4, test.getNumberOfSongs());
    }
}


