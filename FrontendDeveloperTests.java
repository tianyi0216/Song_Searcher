// --== CS400 Project Two File Header ==--
// Name: Bill Zhu
// CSL Username: bzhu
// Email: wlzhu@wisc.edu
// Lecture #: 002 @1:00pm
// Notes to Grader: Used this resource to split the output lines:
// https://stackoverflow.com/questions/454908/split-java-string-by-new-line

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Class that tests the SongSearcherFrontend class through Junit tests.
 * 
 * @author williamzhu
 *
 */
class FrontendDeveloperTests {

  /**
   * Tests Command Loop making sure that the output is expected.
   */
  @Test
  void testRunCommandLoop() {
    // initialization and declaration of testing variables.
    SongSearcherBackendFrontendDeveloperPlaceholder backend = new SongSearcherBackendFrontendDeveloperPlaceholder();
    SongSearcherFrontend test = new SongSearcherFrontend(backend);

    // Simulates user input.
    TextUITester tester = new TextUITester("1 \n g \n q \n");
    test.runCommandLoop();
    String output = tester.checkOutput();

    String[] lines = output.split("[\r\n]+"); // Breaks output to individual lines.

    int x = 0;
    // Checking appropriate output.
    Assertions.assertEquals(lines[x], "Hello! Welcome to the Song Searcher!");
    x += 1;
    Assertions.assertEquals(lines[x], "------------------------------------");
    x += 1;
    Assertions.assertEquals(lines[x], "Command Menu:");
    x += 1;
    Assertions.assertEquals(lines[x], "\t1) Search by [T]itle Word");
    x += 1;
    Assertions.assertEquals(lines[x], "\t2) Search by [A]rtist");
    x += 1;
    Assertions.assertEquals(lines[x], "\t3) [I]nsert a song");
    x += 1;
    Assertions.assertEquals(lines[x], "\t4) [Q]uit");
    x += 1;
    Assertions.assertEquals(lines[x], "Choose a command from the menu above:");
    x += 1;
    Assertions.assertEquals(lines[x], "Choose a word that you would like to search for:");
    x += 1;
    Assertions.assertEquals(lines[x], "Found 4 out of 5 matches.");
    x += 1;
    Assertions.assertEquals(lines[x], "1. ABC");
    x += 1;
    Assertions.assertEquals(lines[x], "\tAlphabet (1990), Me");
    x += 1;
    Assertions.assertEquals(lines[x], "2. Alpha");
    x += 1;
    Assertions.assertEquals(lines[x], "\tBeta (1990), Gamma");
    x += 1;
    Assertions.assertEquals(lines[x], "3. A Little Orange");
    x += 1;
    Assertions.assertEquals(lines[x], "\tFruits (2033), Beck");
    x += 1;
    Assertions.assertEquals(lines[x], "4. An Opening");
    x += 1;
    Assertions.assertEquals(lines[x], "\tMr. Snufflebottoms (9043), I dunno");
    x += 1;
    Assertions.assertEquals(lines[x], "Command Menu:");
    x += 1;
    Assertions.assertEquals(lines[x], "\t1) Search by [T]itle Word");
    x += 1;
    Assertions.assertEquals(lines[x], "\t2) Search by [A]rtist");
    x += 1;
    Assertions.assertEquals(lines[x], "\t3) [I]nsert a song");
    x += 1;
    Assertions.assertEquals(lines[x], "\t4) [Q]uit");
    x += 1;
    Assertions.assertEquals(lines[x], "Choose a command from the menu above:");
    x += 1;
    Assertions.assertEquals(lines[x], "Have a nice day!");
  }

  @Test
  void testDisplayCommandMenu() {
    // initialization and declaration of testing variables.
    SongSearcherBackendFrontendDeveloperPlaceholder backend = new SongSearcherBackendFrontendDeveloperPlaceholder();
    SongSearcherFrontend test = new SongSearcherFrontend(backend);

    // Simulates user input.
    TextUITester tester = new TextUITester("");
    test.displayCommandMenu();
    String output = tester.checkOutput();

    String[] lines = output.split("[\r\n]+"); // Breaks output to individual lines.

    int x = 0;
    // Checking appropriate output.
    Assertions.assertEquals(lines[x], "Command Menu:");
    x += 1;
    Assertions.assertEquals(lines[x], "\t1) Search by [T]itle Word");
    x += 1;
    Assertions.assertEquals(lines[x], "\t2) Search by [A]rtist");
    x += 1;
    Assertions.assertEquals(lines[x], "\t3) [I]nsert a song");
    x += 1;
    Assertions.assertEquals(lines[x], "\t4) [Q]uit");
    x += 1;
    Assertions.assertEquals(lines[x], "Choose a command from the menu above:");
  }

  @Test
  void testDisplaySongs() {
    // initialization and declaration of testing variables.
    SongSearcherBackendFrontendDeveloperPlaceholder backend = new SongSearcherBackendFrontendDeveloperPlaceholder();
    SongSearcherFrontend test = new SongSearcherFrontend(backend);
    List<ISong> list = new ArrayList<ISong>();

    // Test song
    SongFrontendDeveloperPlaceholder song1 = new SongFrontendDeveloperPlaceholder("Eternal", "Mr. Snugglebottoms", "Kittens", 2022);
    list.add(song1);

    // Simulates user input.
    TextUITester tester = new TextUITester("");
    test.displaySongs(list);
    String output = tester.checkOutput();

    String[] lines = output.split("[\r\n]+"); // Breaks output to individual lines.

    // Checking appropriate output.
    Assertions.assertEquals(lines[0], "Found 1 out of 5 matches.");
    Assertions.assertEquals(lines[1], "1. Eternal");
    Assertions.assertEquals(lines[2], "\tKittens (2022), Mr. Snugglebottoms");
  }

  @Test
  void testTitleSearch() {
    // initialization and declaration of testing variables.
    SongSearcherBackendFrontendDeveloperPlaceholder backend =
        new SongSearcherBackendFrontendDeveloperPlaceholder();
    SongSearcherFrontend test = new SongSearcherFrontend(backend);

    // Simulates user input.
    TextUITester tester = new TextUITester("1 \n G \n Q");
    test.runCommandLoop();
    String output = tester.checkOutput();

    String[] lines = output.split("[\r\n]+"); // Breaks output to individual lines.

    int x = 0;
    // Checking appropriate output.
    Assertions.assertEquals(lines[x], "Hello! Welcome to the Song Searcher!");
    x += 1;
    Assertions.assertEquals(lines[x], "------------------------------------");
    x += 1;
    Assertions.assertEquals(lines[x], "Command Menu:");
    x += 1;
    Assertions.assertEquals(lines[x], "\t1) Search by [T]itle Word");
    x += 1;
    Assertions.assertEquals(lines[x], "\t2) Search by [A]rtist");
    x += 1;
    Assertions.assertEquals(lines[x], "\t3) [I]nsert a song");
    x += 1;
    Assertions.assertEquals(lines[x], "\t4) [Q]uit");
    x += 1;
    Assertions.assertEquals(lines[x], "Choose a command from the menu above:");
    x += 1;
    Assertions.assertEquals(lines[x], "Choose a word that you would like to search for:");
    x += 1;
    Assertions.assertEquals(lines[x], "Found 4 out of 5 matches.");
    x += 1;
    Assertions.assertEquals(lines[x], "1. ABC");
    x += 1;
    Assertions.assertEquals(lines[x], "\tAlphabet (1990), Me");
    x += 1;
    Assertions.assertEquals(lines[x], "2. Alpha");
    x += 1;
    Assertions.assertEquals(lines[x], "\tBeta (1990), Gamma");
    x += 1;
    Assertions.assertEquals(lines[x], "3. A Little Orange");
    x += 1;
    Assertions.assertEquals(lines[x], "\tFruits (2033), Beck");
    x += 1;
    Assertions.assertEquals(lines[x], "4. An Opening");
    x += 1;
    Assertions.assertEquals(lines[x], "\tMr. Snufflebottoms (9043), I dunno");
    x += 1;
    Assertions.assertEquals(lines[x], "Command Menu:");
    x += 1;
    Assertions.assertEquals(lines[x], "\t1) Search by [T]itle Word");
    x += 1;
    Assertions.assertEquals(lines[x], "\t2) Search by [A]rtist");
    x += 1;
    Assertions.assertEquals(lines[x], "\t3) [I]nsert a song");
    x += 1;
    Assertions.assertEquals(lines[x], "\t4) [Q]uit");
    x += 1;
    Assertions.assertEquals(lines[x], "Choose a command from the menu above:");
    x += 1;
    Assertions.assertEquals(lines[x], "Have a nice day!");
  }

  @Test
  void testArtistSearch() {
    // initialization and declaration of testing variables.
    SongSearcherBackendFrontendDeveloperPlaceholder backend = new SongSearcherBackendFrontendDeveloperPlaceholder();
    SongSearcherFrontend test = new SongSearcherFrontend(backend);

    // Simulates user input.
    TextUITester tester = new TextUITester("2 \n f \n Q");
    test.runCommandLoop();
    String output = tester.checkOutput();

    String[] lines = output.split("[\r\n]+"); // Breaks output to individual lines.

    int x = 0;
    // Checking appropriate output.
    Assertions.assertEquals(lines[x], "Hello! Welcome to the Song Searcher!");
    x += 1;
    Assertions.assertEquals(lines[x], "------------------------------------");
    x += 1;
    Assertions.assertEquals(lines[x], "Command Menu:");
    x += 1;
    Assertions.assertEquals(lines[x], "\t1) Search by [T]itle Word");
    x += 1;
    Assertions.assertEquals(lines[x], "\t2) Search by [A]rtist");
    x += 1;
    Assertions.assertEquals(lines[x], "\t3) [I]nsert a song");
    x += 1;
    Assertions.assertEquals(lines[x], "\t4) [Q]uit");
    x += 1;
    Assertions.assertEquals(lines[x], "Choose a command from the menu above:");
    x += 1;
    Assertions.assertEquals(lines[x], "Choose an artist that you would like to search for:");
    x += 1;
    Assertions.assertEquals(lines[x], "Found 4 out of 5 matches.");
    x += 1;
    Assertions.assertEquals(lines[x], "1. ABC");
    x += 1;
    Assertions.assertEquals(lines[x], "\tAlphabet (1990), Mr. Snufflebottoms");
    x += 1;
    Assertions.assertEquals(lines[x], "2. Alpha");
    x += 1;
    Assertions.assertEquals(lines[x], "\tBeta (1984), Mr. Snufflebottoms");
    x += 1;
    Assertions.assertEquals(lines[x], "3. A Little Grape");
    x += 1;
    Assertions.assertEquals(lines[x], "\tFruits (900), Mr. Snufflebottoms");
    x += 1;
    Assertions.assertEquals(lines[x], "4. An Orange");
    x += 1;
    Assertions.assertEquals(lines[x],
        "\tMr. Snufflebottoms' Magnum Opus (4000), Mr. Snufflebottoms");
    x += 1;
    Assertions.assertEquals(lines[x], "Command Menu:");
    x += 1;
    Assertions.assertEquals(lines[x], "\t1) Search by [T]itle Word");
    x += 1;
    Assertions.assertEquals(lines[x], "\t2) Search by [A]rtist");
    x += 1;
    Assertions.assertEquals(lines[x], "\t3) [I]nsert a song");
    x += 1;
    Assertions.assertEquals(lines[x], "\t4) [Q]uit");
    x += 1;
    Assertions.assertEquals(lines[x], "Choose a command from the menu above:");
    x += 1;
    Assertions.assertEquals(lines[x], "Have a nice day!");
  }

  @Test
  void testInsertSong() {
    // initialization and declaration of testing variables.
    SongSearcherBackendFrontendDeveloperPlaceholder backend =
        new SongSearcherBackendFrontendDeveloperPlaceholder();
    SongSearcherFrontend test = new SongSearcherFrontend(backend);

    // Simulates user input.
    TextUITester tester =
        new TextUITester("I\nThe Grand Escape\nWilbur\nSomething\nt\nr\ny\n1990\nQ");
    test.runCommandLoop();
    String output = tester.checkOutput();

    String[] lines = output.split("[\r\n]+"); // Breaks output to individual lines.

    int x = 0;
    // Checking appropriate output.
    Assertions.assertEquals(lines[x], "Hello! Welcome to the Song Searcher!");
    x += 1;
    Assertions.assertEquals(lines[x], "------------------------------------");
    x += 1;
    Assertions.assertEquals(lines[x], "Command Menu:");
    x += 1;
    Assertions.assertEquals(lines[x], "\t1) Search by [T]itle Word");
    x += 1;
    Assertions.assertEquals(lines[x], "\t2) Search by [A]rtist");
    x += 1;
    Assertions.assertEquals(lines[x], "\t3) [I]nsert a song");
    x += 1;
    Assertions.assertEquals(lines[x], "\t4) [Q]uit");
    x += 1;
    Assertions.assertEquals(lines[x], "Choose a command from the menu above:");
    x += 1;
    Assertions.assertEquals(lines[x], "Title of song:");
    x += 1;
    Assertions.assertEquals(lines[x], "Artist of song:");
    x += 1;
    Assertions.assertEquals(lines[x], "Album:");
    x += 1;
    Assertions.assertEquals(lines[x], "Year:");
    x += 1;
    Assertions.assertEquals(lines[x], "Error: enter an Integer");
    x += 1;
    Assertions.assertEquals(lines[x], "Error: enter an Integer");
    x += 1;
    Assertions.assertEquals(lines[x], "Error: enter an Integer");
    x += 1;
    Assertions.assertEquals(lines[x], "The Grand Escape");
    x += 1;
    Assertions.assertEquals(lines[x], "\tSomething (1990), Wilbur");
    x += 1;
    Assertions.assertEquals(lines[x], "Command Menu:");
    x += 1;
    Assertions.assertEquals(lines[x], "\t1) Search by [T]itle Word");
    x += 1;
    Assertions.assertEquals(lines[x], "\t2) Search by [A]rtist");
    x += 1;
    Assertions.assertEquals(lines[x], "\t3) [I]nsert a song");
    x += 1;
    Assertions.assertEquals(lines[x], "\t4) [Q]uit");
    x += 1;
    Assertions.assertEquals(lines[x], "Choose a command from the menu above:");
    x += 1;
    Assertions.assertEquals(lines[x], "Have a nice day!");
  }

// Removed Remove method.
//  @Test
//  void testRemoveSong() {
//    // initialization and declaration of testing variables.
//    SongSearcherBackendFrontendDeveloperPlaceholder backend =
//        new SongSearcherBackendFrontendDeveloperPlaceholder();
//    SongSearcherFrontend test = new SongSearcherFrontend(backend);
//
//    // Simulates user input.
//    TextUITester tester = new TextUITester("R\nnull\nWilbur\nR\nThe Grand Escape\nWilbur\nQ");
//    test.runCommandLoop();
//    String output = tester.checkOutput();
//
//    String[] lines = output.split("[\r\n]+"); // Breaks output to individual lines.
//
//    int x = 0;
//    // Checking appropriate output.
//    Assertions.assertEquals(lines[0], "Hello! Welcome to the Song Searcher!");
//    Assertions.assertEquals(lines[1], "------------------------------------");
//    Assertions.assertEquals(lines[2], "Command Menu:");
//    Assertions.assertEquals(lines[3], "\t1) Search by [T]itle Word");
//    Assertions.assertEquals(lines[4], "\t2) Search by [A]rtist");
//    Assertions.assertEquals(lines[5], "\t3) [I]nsert a song");
//    Assertions.assertEquals(lines[6], "\t4) [R]emove a song");
//    Assertions.assertEquals(lines[7], "\t5) [Q]uit");
//    Assertions.assertEquals(lines[8], "Choose a command from the menu above:");
//    Assertions.assertEquals(lines[9], "Title of song:");
//    Assertions.assertEquals(lines[10], "Author of song:");
//    Assertions.assertEquals(lines[11], "Invalid - no applicable song.");
//    Assertions.assertEquals(lines[12], "Command Menu:");
//    Assertions.assertEquals(lines[13], "\t1) Search by [T]itle Word");
//    Assertions.assertEquals(lines[14], "\t2) Search by [A]rtist");
//    Assertions.assertEquals(lines[15], "\t3) [I]nsert a song");
//    Assertions.assertEquals(lines[16], "\t4) [R]emove a song");
//    Assertions.assertEquals(lines[17], "\t5) [Q]uit");
//    Assertions.assertEquals(lines[18], "Choose a command from the menu above:");
//    Assertions.assertEquals(lines[19], "Title of song:");
//    Assertions.assertEquals(lines[20], "Author of song:");
//    Assertions.assertEquals(lines[21], "Command Menu:");
//    Assertions.assertEquals(lines[22], "\t1) Search by [T]itle Word");
//    Assertions.assertEquals(lines[23], "\t2) Search by [A]rtist");
//    Assertions.assertEquals(lines[24], "\t3) [I]nsert a song");
//    Assertions.assertEquals(lines[25], "\t4) [R]emove a song");
//    Assertions.assertEquals(lines[26], "\t5) [Q]uit");
//    Assertions.assertEquals(lines[27], "Choose a command from the menu above:");
//    Assertions.assertEquals(lines[28], "Have a nice day!");
//  }
}
