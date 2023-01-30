import java.util.List;

public class SongSearcherApp {
    public static void main(String[] args) throws Exception {
        SongLoader loader = new SongLoader();
        List<ISong> songs = loader.loadSongs("songs.xml");
        SongSearcherBackend backend = new SongSearcherBackend();
        for(ISong song : songs) backend.addSong(song);
        SongSearcherFrontend frontend = new SongSearcherFrontend(backend);
        frontend.runCommandLoop();
    }
}
