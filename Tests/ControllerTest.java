import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    String albumName = "Root";
    private ArrayList<Album> listOfAlbums = new ArrayList<Album>();
    public Album rootAlbum = new Album("rootAlbum");

    @Test
    void createAlbum(String albumName) {
        this.listOfAlbums.add(new Album(albumName,rootAlbum));
        assert()
    }
}