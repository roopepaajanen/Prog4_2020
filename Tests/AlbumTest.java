import org.junit.jupiter.api.Test;

class AlbumTest {

    private Album alb;
    private Album subAlb;

    @Test
    void getAlbumName() { //test if album name is correct and the getAlbumName() method works.
        alb = new Album("1. Album");
        System.out.println(alb.toString());
        subAlb = new Album("2. Album", alb);
        assert(alb.toString().equals("1. Album"));
        assert(subAlb.toString().equals("2. Album"));
    }

    @Test
    void getSongsFromAlbum(String desiredAlbumName){

    }


}