// --== CS400 Project Two File Header ==--
// Name: Tianyi Xu
// CSL Username: tianyi
// Email: txu223@wisc.edu
// Lecture #: 002 
// Notes to Grader: N/A

import java.util.List;
import java.util.ArrayList;
/**
 * The backend that utilizes a RBTree to searches for beginning title words and artists name from a 
 * database of songs. The class also has functions to track the number of songs added, add a song
 * or remove a song.
 * 
 * @author Tianyi Xu
 */
public class SongSearcherBackend implements ISongSearcherBackend {
    protected int size; // the number of songs currently in the database.

    protected RedBlackTree<ISong> titleTree; // the tree to store songs sorted by their title

    protected RedBlackTree<ISong> artistTree; // the tree to store songs sorted by their artist

    /**
     * Constructor for backend object, initializes size to 0 and initializes two trees to be 
     * sorted base on title and artist using two comparators
     */
    public SongSearcherBackend(){
        this.size = 0;

        // title tree using comparators that sort songs base in this order:
        // title, artist, album, year
        this.titleTree = new RedBlackTree<ISong>( );
        
        // artist tree using comparator that sort songs base in this order:
        // artist, title, album, year
        this.artistTree = new RedBlackTree<ISong>(
            (ISong a, ISong b) -> {
                if(a.getArtist().compareToIgnoreCase(b.getArtist()) != 0) 
                    return a.getArtist().compareToIgnoreCase(b.getArtist());
                else
                    return a.compareTo(b);
            }
         );
    }
    
    /**
     * Add a song into the database collections. Will add to both trees
     * @param song the song to be added to the database
     */
    @Override
    public void addSong(ISong song){
        // use a try catch to avoid program crashing when inserting null/duplicate songs
        try{
            boolean title = titleTree.insert(song);
            boolean artist = artistTree.insert(song);
            if(title && artist)
                this.size++; 
        } catch (IllegalArgumentException i){
            // continue the program if found duplicate song
        } 
    }
    
    /**
     * return the number of songs stored in the database
     * 
     * @return size the number of songs stored in the backend object
     */
    @Override
    public int getNumberOfSongs(){
        return size;
    } 

    /**
     * Try to remove a song from the database collection with same title/artist.
     * Uses get method from rbtree to find the song, then use remove method to remove the song
     * Must enter exact title name and artist to remove, not case sensitive.
     * E.g removeSong("lemon","yonezu kenshi") will remove song with title: "Lemon", artist
     * "Yonezu Kenshi", but removeSong ("lemon", "yonezu") or removeSong("lemo", "Yonezu kenshi")
     *  will not work
     * 
     * @param title the title of the song try to remove
     * @param artist the name of the artist of the song try to remove
     * @return the song removed from the database
     */
    @Override
    public ISong removeSong(String title, String artist){
        // find the song to remove from title tree, uses lamda function for condition.
        ISong toRemoveTitle = titleTree.get(
            (ISong a) -> {
                // return 0 if both are equal, or will sort base on title
                if( a.getSongTitle().compareTo(title) == 0){
                    if(a.getArtist().compareTo(artist) == 0)
                        return 0;
                    else  
                        return a.getArtist().compareTo(artist);
                        // if title are equal but artist are not, then sort base on artist
                } else 
                    return a.getSongTitle().compareTo(title);
            }
        );
        
        // find from artist tree, use similar algorithm as previous lambda expression
        ISong toRemoveArtist = titleTree.get(
            (ISong a) -> {
                if( a.getArtist().compareTo(artist) == 0){
                    if(a.getSongTitle().compareTo(title) == 0)
                        return 0;
                    else  
                        return a.getSongTitle().compareTo(title);
                } else 
                    return a.getArtist().compareTo(artist);
            }
        );
        
        // in case one of the tree wasn't able to get the song, 
        if(toRemoveTitle != null) 
            toRemoveArtist = toRemoveTitle;
        else if(toRemoveArtist != null){
            toRemoveTitle = toRemoveArtist;
        } else {
            return null;
        }

        // if find, remove both from the tree and return the result.
        boolean removeTitle = titleTree.remove(toRemoveTitle);
        boolean removeArtist = artistTree.remove(toRemoveArtist);
        if(removeTitle && removeArtist) this.size--;
        
        return toRemoveTitle;   
    } 
 
    /**
     * Find all songs started with the title word given from user and return a list
     * Spacing allowed and not case sensitive E.g searching for "helloworld" 
     * will find songs "hello world Song". 
     * 
     * @param title the title to search for
     * @return a list that contains all songs start with the title
     */
    @Override
    public List<ISong> searchByTitleWord(String title){
        List<ISong> titleList = new ArrayList<ISong>(); // list to store search 
        if(this.size == 0) return titleList; // don't do search if no song is in this database

        // use lambda for the condition, title need to start with 
        titleList = titleTree.getAll(
            (ISong a) -> {
                String titleConverted = title.toLowerCase().replaceAll(" ", "");
                String songTitle = a.getSongTitle().toLowerCase().replaceAll(" ", "");
                if(songTitle.startsWith(titleConverted) ||
                    a.getSongTitle().equalsIgnoreCase(title)) 
                    return 0;
                else 
                    return a.getSongTitle().compareToIgnoreCase(title);
            }
        );
	
	// Try to get all uppercase songs since they will be stored at different places 
        List<ISong> titleList_Caps = titleTree.getAll(
            (ISong a) -> {
                String titleConverted = title.toUpperCase().replaceAll(" ", "");
                String songTitle = a.getSongTitle().toUpperCase().replaceAll(" ", "");
                if(songTitle.startsWith(titleConverted) || a.getSongTitle().equalsIgnoreCase(title))
                    return 0;
                else
                    return a.getSongTitle().compareTo(title.toUpperCase());
            }
        );
	
	// try to get all lowercase songs as they will be stored differently too
        List<ISong> titleList_lower = titleTree.getAll(
            (ISong a) -> {
                String titleConverted = title.toLowerCase().replaceAll(" ", "");
                String songTitle = a.getSongTitle().toLowerCase().replaceAll(" ", "");
                if(songTitle.startsWith(titleConverted) ||
                    a.getSongTitle().equalsIgnoreCase(title)) 
                    return 0;
                else 
                    return a.getSongTitle().compareTo(title.toLowerCase());
            }
        );
	
	// add all songs to a list
        for(ISong song: titleList_Caps){
            if (!titleList.contains(song))
                titleList.add(song);
        }

        for(ISong song: titleList_lower){
            if (!titleList.contains(song))
                titleList.add(song);
        }
        return titleList;
    }

    /**
     * Find all songs in the database have matching artist,search are not case 
     * sensitive and can ignore space. E.g, search for "tayLORs" will find
     * all songs by Taylor Swift. But limited to beginning only. E.g search
     * for swift will not find Taylor Swift.
     * 
     * @param artist the artist name to search for
     * @return a list contains all songs that has the artist name passed in
     */
    @Override
    public List<ISong> searchByArtist(String artist){
        List<ISong> artistList = new ArrayList<ISong>(); // list to store songs
        if(this.size == 0) return artistList; // don't do search if no song is in this database

        // use lambda for condition, compare two song base on artist
        artistList = artistTree.getAll(
            (ISong a) -> {
                if(a.getArtist().replaceAll(" ", "").toLowerCase().
                    startsWith(artist.replaceAll(" ", "").toLowerCase()) ||
                    a.getArtist().equalsIgnoreCase(artist)) 
                    return 0;
                else 
                    return a.getArtist().compareToIgnoreCase(artist);
            });
	
	// get all capitalized artist
        List<ISong> artistList_Caps = titleTree.getAll(
            (ISong a) -> {
                String artistConverted = artist.toUpperCase().replaceAll(" ", "");
                String songArtist = a.getArtist().toUpperCase().replaceAll(" ", "");
                if(songArtist.startsWith(artistConverted) || a.getArtist().equalsIgnoreCase(artist))
                    return 0;
                else
                    return a.getArtist().compareTo(artist.toUpperCase());
            }
        );
    	
	// get all artist start with lowercase
        List<ISong> artistList_lower = titleTree.getAll(
            (ISong a) -> {
                String artistConverted = artist.toLowerCase().replaceAll(" ", "");
                String songArtist = a.getArtist().toLowerCase().replaceAll(" ", "");
                if(songArtist.startsWith(artistConverted) ||
                    a.getArtist().equalsIgnoreCase(artist)) 
                    return 0;
                else 
                    return a.getArtist().compareTo(artist.toLowerCase());
            }
        );
    	
	// add all artist to the list
        for(ISong song: artistList_Caps){
            if (!artistList.contains(song))
                artistList.add(song);
        }
    
        for(ISong song: artistList_lower){
            if (!artistList.contains(song))
                artistList.add(song);
        }
        return artistList;
    }
}


