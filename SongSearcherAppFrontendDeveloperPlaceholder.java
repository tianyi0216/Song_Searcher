// --== CS400 Project Two File Header ==--
// Name: Bill Zhu
// CSL Username: bzhu
// Email: wlzhu@wisc.edu
// Lecture #: 002 @1:00pm
// Notes to Grader:

import java.util.List;

/**
 * This class serves as a placeholder for the SongSearcherAoo class to facilitate testing of the
 * SongSearcherFrontend class.
 * 
 * @author williamzhu
 *
 */
public class SongSearcherAppFrontendDeveloperPlaceholder {
  
  /**
   * Starts the application.
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    SongSearcherBackendFrontendDeveloperPlaceholder backend =
        new SongSearcherBackendFrontendDeveloperPlaceholder();
    SongSearcherFrontend frontend = new SongSearcherFrontend(backend);
    frontend.runCommandLoop();
  }
}
