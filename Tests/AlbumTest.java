import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlbumTest {

    private Album alb;
    private Album subAlb;

    @Test
    void getAlbumName() { //test if album name is correct and the getAlbumName() method works.
        alb = new Album("1. Album");
        System.out.println(alb.getAlbumName());
        subAlb = new Album("2. Album", alb)
        assert(alb.getAlbumName() == "1. Album");
        assert(subAlb.getAlbumName() == "2. Album");
    }


}