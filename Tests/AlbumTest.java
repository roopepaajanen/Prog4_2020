import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlbumTest {

    private Album alb;

    @Test
    void getAlbumName() { //test if album name is correct
        alb = new Album("xyz");
        assert(alb.albumName == "xyz");
    }
}