// --== CS400 Project Two File Header ==--
// Name: Bill Zhu
// CSL Username: bzhu
// Email: wlzhu@wisc.edu
// Lecture #: 002 @1:00pm
// Notes to Grader:

import java.util.ArrayList;
import java.util.List;

/**
 * This class serves as a placeholder for the SongSearcherBackend class to facilitate testing of the
 * SongSearcherFrontend class. This class is hard coded.
 * 
 * @author williamzhu
 *
 */
public class SongSearcherBackendFrontendDeveloperPlaceholder implements ISongSearcherBackend {

  /**
   * Hard coded. Prints out song details.
   * 
   * @param song - the song to be added.
   */
  @Override
  public void addSong(ISong song) {
    System.out.println(song.getSongTitle() + "\n\t" + song.getAlbum() + " (" + song.getYear()
        + "), " + song.getArtist());
  }

  /**
   * Hard coded.
   * 
   * @param title  - the title of the song to be removed.
   * @param artist - the artist name of the song to be removed.
   * @return null if the song isn't stored (this is hard coded here) and the removed song if it is
   *         stored.
   */
 //  @Override
  public ISong removeSong(String title, String artist) {
    if (title.equals("null")) //Song isn't stored.
      return null;
    return new SongFrontendDeveloperPlaceholder(title, artist, "", 0); //Song is stored.
  }

  /**
   * Hard coded. Returns the number of stored songs.
   */
  @Override
  public int getNumberOfSongs() {
    return 5;
  }

  /**
   * Hard coded. Emulates the method that would return corresponding songs to the search keyword.
   * 
   * @param title - the search keyword
   * @return the list of corresponding songs.
   */
  @Override
  public List<ISong> searchByTitleWord(String title) {
    //Initializing values.
    List<ISong> songs = new ArrayList<ISong>();
    SongFrontendDeveloperPlaceholder song1 =
        new SongFrontendDeveloperPlaceholder("ABC", "Me", "Alphabet", 1990);
    SongFrontendDeveloperPlaceholder song2 =
        new SongFrontendDeveloperPlaceholder("Alpha", "Gamma", "Beta", 1990);
    SongFrontendDeveloperPlaceholder song3 =
        new SongFrontendDeveloperPlaceholder("A Little Orange", "Beck", "Fruits", 2033);
    SongFrontendDeveloperPlaceholder song4 =
        new SongFrontendDeveloperPlaceholder("An Opening", "I dunno", "Mr. Snufflebottoms", 9043);

    //Add songs to the list.
    songs.add(song1);
    songs.add(song2);
    songs.add(song3);
    songs.add(song4);

    return songs;
  }

  /**
   * Hard coded. Emulates the method that would return corresponding songs to the search keyword.
   * 
   * @param title - the search keyword
   * @return the list of corresponding songs.
   */
  @Override
  public List<ISong> searchByArtist(String artist) {
    //Initializing values.
    List<ISong> songs = new ArrayList<ISong>();
    SongFrontendDeveloperPlaceholder song1 =
        new SongFrontendDeveloperPlaceholder("ABC", "Mr. Snufflebottoms", "Alphabet", 1990);
    SongFrontendDeveloperPlaceholder song2 =
        new SongFrontendDeveloperPlaceholder("Alpha", "Mr. Snufflebottoms", "Beta", 1984);
    SongFrontendDeveloperPlaceholder song3 =
        new SongFrontendDeveloperPlaceholder("A Little Grape", "Mr. Snufflebottoms", "Fruits", 900);
    SongFrontendDeveloperPlaceholder song4 = new SongFrontendDeveloperPlaceholder("An Orange",
        "Mr. Snufflebottoms", "Mr. Snufflebottoms' Magnum Opus", 4000);

    //Add songs to the list.
    songs.add(song1);
    songs.add(song2);
    songs.add(song3);
    songs.add(song4);

    return songs;
  }
}
