import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlbumTest {

    @Test
    void rename() {
        alb = new Album("asdf");
        alb.setName("fdsa");
        assert(alb.getName() == "fdsa");
    }

    @Test
    void addClip() {
        alb = new Album("asdf");
        SoundClip sc = new SoundClip(new File("asd"));
        alb.addSoundClip(sc);
        assert(alb.getClips().size() == 1);
    }
}