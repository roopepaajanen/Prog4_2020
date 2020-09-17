import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    private Controller control;
    private Album alb;
    ArrayList<SoundClip> testlist = new ArrayList<SoundClip>();

    //Vi fick inte testclasserna och fungera. Feedback vad som kan vara fel med methoderna i controller och album är mycket välkomna :)
    //Vi tror att själva koden är rätt(?) men vi har gjort någonting fel i testklassen.


    @Test
    void createAlbum() {
        alb = new Album("1.Album");
        control.createSubAlbum("first sub", alb);

        assert(control.rootAlbum != null);
    }

    @Test
    void addSongToAlbum() {
        SoundClip clip = new SoundClip(new File("testitesti"));
        Album album = new Album("1.Album");

        control.addSongToAlbum("1.Album", clip);
        ArrayList<SoundClip> wantedName = control.getSongsFromAlbum("1.Album");
        //assert(testlist wantedName));



        //String wantedSong = String.valueOf(control.getSongsFromAlbum("testitesti"));


    }

    @Test
    void removeSongFromAlbum() {

    }
}