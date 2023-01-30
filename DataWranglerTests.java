// --== CS400 Project Two File Header ==--
// Name: Wendell Cai 
// CSL Username: wendell
// Email: wcai54@wisc.edu
// Lecture #: 002 @1:00pm
// Notes to Grader: ?

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tester for Song, SongLoader, and SongSearcherFrontEnd (review only)
 */
public class DataWranglerTests {

    /**
     * Tester for Song class
     *
     * 
     */
    @Test
    public void test1() {

        /**
         * <Title>Soul Deep</Title>
         * <ArtistName>The Box Tops</ArtistName>
         * <Album>Dimensions</Album>
         * <Year>1969</Year>
         */
        Song test1 = new Song("Soul Deep", "The Box Tops", "Dimensions", 1969);

        assertEquals(test1.getSongTitle(), "Soul Deep");
        // Artist: The Box Tops
        assertEquals(test1.getArtist(), "The Box Tops");
        // Album: 1
        assertEquals(test1.getAlbum(), "Dimensions");
        // Year: 2001
        assertEquals(test1.getYear(), 1969);
    }

    /**
     * Tester for compareTo()
     *
     * 
     */
    @Test
    public void test2() {

        Song test1 = new Song("test1", "artist", "album", 1);
        Song test2 = new Song("test2", "artist", "album", 1);

        // Test1 is bigger than Test2
        assertFalse(test1.compareTo(test2) >= 0);
    }

    /**
     * Tester for load a csv file
     *
     * 
     */
    @Test
    public void test3() {
        try {
            List<ISong> file = new SongLoader().loadSongs("songs.xml");

            assertEquals(file.size(), 10000);
            assertThrows(FileNotFoundException.class, () -> {
                new SongLoader().loadSongs("Not a file");
            });

            assertThrows(FileNotFoundException.class, () -> {
                new SongLoader().loadSongs("No file.xml");
            });

        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Tester to test the first song
     *
     * 
     */
    @Test
    public void test4() {
        try {
            List<ISong> file = new SongLoader().loadSongs("songs.xml");

            // The correct first song
            /*
             * <Title>I Didn't Mean To</Title>
             * <ArtistName>Casual</ArtistName>
             * <Album>Fear Itself</Album>
             * <Year>0</Year>
             */
            Song test1 = new Song("I Didn't Mean To", "Casual", "Fear Itself", 0);

            ISong first = file.get(0);
            // Title: Breaking Bad
            assertEquals(first.getSongTitle(), test1.getSongTitle());

            // Year
            assertEquals(first.getYear(), test1.getYear());
            // Artist
            assertEquals(first.getArtist(), test1.getArtist());

            // Album
            assertEquals(first.getAlbum(), test1.getAlbum());

            assertEquals(first.compareTo(test1), 0);

        } catch (Exception e) {
            fail();
        }
    }

    /**
     * Tester method to test some songs other than the first one
     *
     * 
     */
    @Test
    public void test5() {
        try {
            List<ISong> file = new SongLoader().loadSongs("songs.xml");

            // The correct last song
            /*
             * <Title>Shattered Life</Title>
             * <ArtistName>Seventh Day Slumber</ArtistName>
             * <Album>Once Upon A Shattered Life</Album>
             * <Year>2005</Year>
             */
            Song testLast = new Song("Shattered Life", "Seventh Day Slumber", "Once Upon A Shattered Life", 2005);
            ISong last = file.get(file.size() - 1);

            // Year: 2005
            assertEquals(last.getYear(), testLast.getYear());

            // Title: Fearless Adventures with Jack Randall
            assertEquals(last.getSongTitle(), testLast.getSongTitle());

            // Artist:
            assertEquals(last.getArtist(), testLast.getArtist());

            // Rating: 10
            assertEquals(last.getAlbum(), testLast.getAlbum());

            /*
             * <Title>Face the Ashes</Title>
             * <ArtistName>Gob</ArtistName>
             * <Album>Muertos Vivos</Album>
             * <Year>2007</Year>
             */
            // The correct 5th song
            Song testFifth = new Song("Face the Ashes", "Gob", "Muertos Vivos", 2007);
            ISong fifth = file.get(4);

            assertEquals(fifth.getYear(), testFifth.getYear());

            // Title: Fearless Adventures with Jack Randall
            assertEquals(fifth.getSongTitle(), testFifth.getSongTitle());

            // Artist:
            assertEquals(fifth.getArtist(), testFifth.getArtist());

            // Rating: 10
            assertEquals(fifth.getAlbum(), testFifth.getAlbum());

        } catch (Exception e) {
            fail();
        }
    }

    /**
     * tester for checking whether frontend search right when there are no songs
     */
    @Test
    public void testFrontEnd1() {
        SongSearcherBackend backend = new SongSearcherBackend();
        SongSearcherFrontend frontend = new SongSearcherFrontend(backend);
        TextUITester tester = new TextUITester("T\ng");
        try {
            frontend.runCommandLoop();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String result = tester.checkOutput();
        assertEquals(result.replace("\r", "").split("\n")[9], "Found 0 out of 0 matches.");
    }

    /**
     * tester for checking whether frontend insert right when there are no songs
     */
    @Test
    public void testFrontEnd2() {
        SongSearcherBackend backend = new SongSearcherBackend();
        SongSearcherFrontend frontend = new SongSearcherFrontend(backend);
        TextUITester tester = new TextUITester("I\nA\nB\nC\n2022\nT\nA");
        try {
            frontend.runCommandLoop();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String result = tester.checkOutput();

        // assertEquals(result, "");
        assertEquals(result.replace("\r", "").split("\n")[19], "Found 1 out of 1 matches.");
        assertEquals(result.replace("\r", "").split("\n")[20], "1. A");
        assertEquals(result.replace("\r", "").split("\n")[21], "\tC (2022), B");
    }

    /**
     * tester for checking whether frontend insert right when adding same song
     */
    @Test
    public void testFrontEnd3() {
        SongSearcherBackend backend = new SongSearcherBackend();
        SongSearcherFrontend frontend = new SongSearcherFrontend(backend);
        TextUITester tester = new TextUITester("I\nA\nB\nC\n2022\nI\nA\nB\nC\n2022\nT\nA");
        try {
            frontend.runCommandLoop();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String result = tester.checkOutput();

        // assertEquals(result, "");
        assertEquals(result.replace("\r", "").split("\n")[29], "Found 1 out of 1 matches.");
        assertEquals(result.replace("\r", "").split("\n")[30], "1. A");
        assertEquals(result.replace("\r", "").split("\n")[31], "\tC (2022), B");
    }

}
