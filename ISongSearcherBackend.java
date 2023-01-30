// --== CS400 Project Two File Header ==--
// Name: Tianyi Xu
// CSL Username: tianyi
// Email: txu223@wisc.edu
// Lecture #: 002 
// Notes to Grader: 

import java.util.List;

/**
 * An interface used to search, add and remove song from/into the database
 * of songs.
 */
public interface ISongSearcherBackend{

    // the method to add and remove a song into the collection
    public void addSong(ISong song);
    public ISong removeSong(String title, String artist);

    public int getNumberOfSongs(); // the method to keep track of how many songs are in the database

    // these method returns a collection of songs based on their title word or the artist
    // results are sorted in the order decided by the comparable of song objects.
    public List<ISong> searchByTitleWord(String title);
    public List<ISong> searchByArtist(String artist);
}
