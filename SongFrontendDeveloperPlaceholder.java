
public class SongFrontendDeveloperPlaceholder implements ISong{

  private String title;
  private String artist;
  private String album;
  private int year;
  
  public SongFrontendDeveloperPlaceholder (String title, String artist, String album, int year) {
    this.title = title;
    this.artist = artist;
    this.album = album;
    this.year = year;
  }
  
  @Override
  public int compareTo(ISong o) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public String getSongTitle() {
    // TODO Auto-generated method stub
    return this.title;
  }

  @Override
  public String getArtist() {
    // TODO Auto-generated method stub
    return this.artist;
  }

  @Override
  public String getAlbum() {
    // TODO Auto-generated method stub
    return this.album;
  }

  @Override
  public int getYear() {
    // TODO Auto-generated method stub
    return this.year;
  }

}
