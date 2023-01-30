// --== CS400 Project Two File Header ==--
// Name: Wendell Cai 
// CSL Username: wendell
// Email: wcai54@wisc.edu
// Lecture #: 002 @1:00pm
// Notes to Grader: ?

/**
 * class that implement ISong
 */
public class Song implements ISong {

    private String title;
    private String artist;
    private String album;
    private int year;

    /**
     * construct a song
     * 
     * @param title
     * @param artist
     * @param album
     * @param year
     */
    public Song(String title, String artist, String album, int year) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.year = year;
    }

    @Override
    /**
     * normal compares, follow order:
     * title
     * artist
     * ablum
     * year
     */
    public int compareTo(ISong o) {
        int ret;
        ret = title.compareTo(o.getSongTitle());
        if (ret != 0) {
            return ret;
        }
        ret = artist.compareTo(o.getArtist());
        if (ret != 0) {
            return ret;
        }
        ret = album.compareTo(o.getAlbum());
        if (ret != 0) {
            return ret;
        }
        return year - o.getYear();
    }

    /**
     * retrieve the title of this song object
     */
    @Override
    public String getSongTitle() {
        return title;
    }

    /**
     * retrieve the artist of this song object
     */
    @Override
    public String getArtist() {
        return artist;
    }

    /**
     * retrieve the Album of this song object
     */
    @Override
    public String getAlbum() {
        return album;
    }

    /**
     * retirieve the year of publish of this song object
     */
    @Override
    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return title + "\n\t " + album + " (" + (year == 0 ? "-" : year) + "), " + artist;
    }

}
