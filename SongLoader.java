// --== CS400 Project Two File Header ==--
// Name: Wendell Cai 
// CSL Username: wendell
// Email: wcai54@wisc.edu
// Lecture #: 002 @1:00pm
// Notes to Grader: ?

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * an implementation for ISongLoader
 */
public class SongLoader implements ISongLoader {

    /**
     * This method loads the list of songs described within a XML file.
     * @param filepath is relative to executable's working directory
     * @return a list of song objects that were read from specified file
     */
    @Override
    public List<ISong> loadSongs(String filepath) throws FileNotFoundException {
        ArrayList<ISong> data = new ArrayList<>();

        File file = new File(filepath);
        Scanner fileReader;

        fileReader = new Scanner((file), "UTF-8");

        // No file found
        if (!file.exists()) {
            throw new FileNotFoundException("File does not exist.");
        }

        // Skip the first two lines
        fileReader.nextLine();
        fileReader.nextLine();

        String songRawString = "";
        while (fileReader.hasNextLine()) {
            String line = fileReader.nextLine();
            if (line.matches("<Records>") || line.matches("</Records>")) {
                continue;
            }

            // situation where the song data started
            if (line.contains("<Song>")) {
                songRawString += line.replace("<Song>", "");

                // get string represent the whole song's information
                while (!songRawString.contains("</Song>")) {
                    songRawString += fileReader.nextLine();
                }
                songRawString.replace("</Song>", "");

                Song song;
                try {
                    song = constructSong(songRawString);
                    data.add(song);
                } catch (Exception e) {
                    // ignore the invalid songs
                }

                songRawString = "";
            }
        }

        fileReader.close();

        return data;
    }

    private Song constructSong(String songRawString) {
        // split into arrays that based on the < (tag start)
        String[] field = songRawString.split("<");

        // System.out.println(field.length);

        String startString = "Title>";
        int start = field[1].indexOf(startString) + startString.length();
        String title = convertToNormalString(field[1].substring(start));

        startString = "ArtistName>";
        start = field[3].indexOf(startString) + startString.length();
        String artist = convertToNormalString(field[3].substring(start));

        startString = "Album>";
        start = field[5].indexOf(startString) + startString.length();
        String album = convertToNormalString(field[5].substring(start));

        startString = "Year>";
        start = field[7].indexOf(startString) + startString.length();
        String yearString = field[7].substring(start);
        int year;
        try {
            year = Integer.parseInt(yearString);
        } catch (Exception e) {
            year = 0;
        }

        return new Song(title, artist, album, year);
    }

    private String convertToNormalString(String xmlString) {
        // replace all xml notations to normal characters
        xmlString = xmlString.replace("&lt;", "<");
        xmlString = xmlString.replace("&gt;", ">");
        xmlString = xmlString.replace("&apos;", "\'");
        xmlString = xmlString.replace("&quot;", "\"");
        xmlString = xmlString.replace("&amp;", "&");
        return xmlString;
    }

}
