// --== CS400 Project Two File Header ==--
// Name: Wendell Cai
// CSL Username: wendell
// Email: wcai54@wisc.edu
// Lecture #: 002 @1:00pm
// Notes to Grader: Na

import java.util.List;
import java.io.FileNotFoundException;


public interface ISongLoader {
    
    /**
     * This method loads the list of songs described within a XML file.
     * @param filepath is relative to executable's working directory
     * @return a list of song objects that were read from specified file
     */
    List<ISong> loadSongs(String filepath) throws FileNotFoundException;
}
