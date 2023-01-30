// --== CS400 File Header Information ==--
// Name: William Zhu
// Email: wlzhu@wisc.edu
// Team: BL
// TA: Sujitha
// Lecturer: Dahl
// Notes to Grader: Made a mistake when pushing, making this interface not available until after 
//                  the deadline. The code was done before the deadline (though I made minor edits
//                  for issues I later found.)

import java.util.List;
/**
 * Instances of classes that implement this interface can be used to drive a
 * console-based text user interface for the SongSearcher App.
 */
public interface ISongSearcherFrontend {

    // constructor args (ISongSearcherBackend) reads input from System.in
    // constructor args (String, ISongSearcherBackend) reads input from String
    /**
     * This method drives the entire read, eval, print loop (repl) for the
     * Song Search App.  This loop will continue to run until the user 
     * explicitly enters the quit command. 
     */
    void runCommandLoop();

    // to help make it easier to test the functionality of this program, 
    // the following helper methods will help support runCommandLoop():
    public void displayCommandMenu(); // prints command options to System.out

    public void displaySongs(List<ISong> song); // displays a list of songs

    public void titleSearch(); // reads word from System.in, displays results

    public void artistSearch(); // reads year from System.in, displays results

    public void insertSong(); //Adds a song to the tree by reading song components from System.in.

    //public void removeSong(); //Removes a specific existing song from the tree by reading user input
			      // from the tree. 
}
