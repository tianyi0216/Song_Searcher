// --== CS400 Project Two File Header ==--
// Name: Bill Zhu
// CSL Username: bzhu
// Email: wlzhu@wisc.edu
// Lecture #: 002 @1:00pm
// Notes to Grader:

import java.util.List;
import java.util.Scanner;

/**
 * This class creates the user interface for BL blue team's Project Two:
 * SongSearcher.
 * 
 * @author williamzhu
 *
 */
public class SongSearcherFrontend implements ISongSearcherFrontend {
  private Scanner scanner = new Scanner(System.in); // Allows for user input.
  protected ISongSearcherBackend backend; // Backend object that is used in the Frontend class.
  protected String userInput = ""; // Variable to store user input.

  /**
   * Constructor - initializes the backend object.
   * 
   * @param backend the backend object.
   */
  public SongSearcherFrontend(ISongSearcherBackend backend) {
    this.backend = backend;
  }

  /**
   * Creates the interface that is shown. Uses the other methods to make a
   * coherent interface.
   */
  public void runCommandLoop() {
    scanner = new Scanner(System.in); // Enables user input.
    System.out.println("Hello! Welcome to the Song Searcher!"); // Start up
    System.out.println("------------------------------------");

    // Commands that aren't quit.
    while (!this.userInput.equalsIgnoreCase("Q")) {
      displayCommandMenu();
      this.userInput = scanner.nextLine().trim();

      // Converting matching commands
      if (this.userInput.equals("1"))
        this.userInput = "T";
      if (this.userInput.equals("2"))
        this.userInput = "A";
      if (this.userInput.equals("3"))
        this.userInput = "I";

      // Removed Remove Method
      // if (this.userInput.equals("4"))
      // this.userInput = "R";

      if (this.userInput.equals("4"))
        this.userInput = "Q";

      // Calls methods according to the user input.
      switch (userInput.toUpperCase()) {
        case "T":
          titleSearch();
          break;
        case "A":
          artistSearch();
          break;
        case "I":
          insertSong();
          break;

        // Removed remove method
        // case "R":
        // removeSong();
        // break;

        case "Q":
          break;
        default:
          System.out.println("Please enter a valid command:");
      }
    }
    // Quit command pressed - close out of interface.
    scanner.close();
    System.out.println("Have a nice day!");
  }

  /**
   * Displays the list of commands.
   */
  @Override
  public void displayCommandMenu() {
    System.out.println("Command Menu:");
    System.out.println("\t1) Search by [T]itle Word");
    System.out.println("\t2) Search by [A]rtist");
    System.out.println("\t3) [I]nsert a song");

    // Removed Remove Method
    // System.out.println("\t4) [R]emove a song");

    System.out.println("\t4) [Q]uit");
    System.out.println("Choose a command from the menu above:");
  }

  /**
   * Formats the songs into a readable fashion.
   */
  public void displaySongs(List<ISong> songs) {
    System.out
        .println("Found " + songs.size() + " out of " + backend.getNumberOfSongs() + " matches.");

    // Default values if values are actually equal to null.
    String albumPlaceholder = "Unknown";
    String artistPlaceholder = "Unknown";
    String yearPlaceholder = "Unknown";

    // Prints out the songs in proper format.
    for (int i = 0; i < songs.size(); i++) {
      if (songs.get(i).getAlbum() != null)
        albumPlaceholder = songs.get(i).getAlbum();
      if (songs.get(i).getArtist() != null)
        artistPlaceholder = songs.get(i).getArtist();
      if (songs.get(i).getYear() != 0)
        yearPlaceholder = Integer.toString(songs.get(i).getYear());
      System.out.println((i + 1) + ". " + songs.get(i).getSongTitle());
      System.out
          .println("\t" + albumPlaceholder + " (" + yearPlaceholder + "), " + artistPlaceholder);
    }
  }

  /**
   * Creates text interface for searching for songs by title.
   */
  @Override
  public void titleSearch() {
    System.out.println("Choose a word that you would like to search for:");
    this.userInput = scanner.nextLine();
    List<ISong> songs = backend.searchByTitleWord(this.userInput);
    displaySongs(songs);
  }

  /**
   * Creates text interface for searching for songs by artist.
   */
  @Override
  public void artistSearch() {
    System.out.println("Choose an artist that you would like to search for:");
    this.userInput = scanner.nextLine();
    List<ISong> songs = backend.searchByArtist(this.userInput);
    displaySongs(songs);
  }

  /**
   * Creates text interface for inserting a song - lets the users input the song
   * data.
   */
  @Override
  public void insertSong() {
    // Initial data values.
    String titleInsertedSong = "";
    String artistInsertedSong = "";
    String albumInsertedSong = "";
    int yearInsertedSong = 0;

    // Code that could be implemented but isn't a part of the proposal document.
    // while (!userInputInsertion.equalsIgnoreCase("Y")) {

    // Lets the user input the song data.
    System.out.println("Title of song:");
    titleInsertedSong = scanner.nextLine().trim();
    System.out.println("Artist of song:");
    artistInsertedSong = scanner.nextLine().trim();
    System.out.println("Album:");
    albumInsertedSong = scanner.nextLine().trim();
    System.out.println("Year:");
    yearInsertedSong = insertYearHelper();

    // Code that could be implemented but isn't a part of the proposal document.
    // System.out.println("Is this correct?");
    // System.out.println("Song title: " + titleInsertedSong);
    // System.out.println("Artist: " + artistInsertedSong);
    // System.out.println("Album: " + albumInsertedSong);
    // System.out.println("Year: " + yearInsertedSong);
    // System.out.println("[Y/N]");
    // userInputInsertion = scanner.nextLine().trim();
    // }

    Song song = new Song(titleInsertedSong, artistInsertedSong, albumInsertedSong, yearInsertedSong);
    backend.addSong(song);
  }

  /**
   * helper method for the insertSong method. Makes sure that the user input for
   * year is an integer.
   * 
   * @return the user input for year.
   */
  private int insertYearHelper() {
    int year = 0; // Stores user input for year.
    try {
      year = Integer.parseInt(scanner.nextLine().trim());
      return year;
    } catch (NumberFormatException e) { // Not an integer - try again.
      System.out.println("Error: enter an Integer");
      return insertYearHelper();
    }
  }

  /**
   * Creates the text interface for removing a song.
   */
  /*
   * @Override
   * public void removeSong() {
   * // Initial values for song removal.
   * String titleRemoveSong = "";
   * String artistRemoveSong = "";
   * 
   * // Allows for user input to select which song to remove.
   * System.out.println("Title of song:");
   * titleRemoveSong = scanner.nextLine().trim();
   * System.out.println("Author of song:");
   * artistRemoveSong = scanner.nextLine().trim();
   * 
   * // No song stored - print out error message.
   * if (backend.removeSong(titleRemoveSong, artistRemoveSong) == null)
   * System.out.println("Invalid - no applicable song.");
   * }
   */
}
