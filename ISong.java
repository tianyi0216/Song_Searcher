// --== CS400 Project Two File Header ==--
// Name: Wendell Cai
// CSL Username: wendell
// Email: wcai54@wisc.edu
// Lecture #: 002 @1:00pm
// Notes to Grader: Na

/**
 * Instances of classes that implement this interface represent a single
 * song object that can be stored, sorted, and searched for based on
 * these accessors below.
 */

public interface ISong extends Comparable<ISong> {

    // constructor args (String title, String artist, String album, int year)

    String getSongTitle(); // retrieve the title of this song object

    String getArtist(); // retrieve the artist of this song object

    String getAlbum(); // retrieve the Album of this song object

    int getYear(); // retirieve the year of publish of this song object

    // compareTo() method supports sorting shows in descending order by rating
    // the order of comparison is title, artist, album, year

    // toString() method print the song's informaton

}
